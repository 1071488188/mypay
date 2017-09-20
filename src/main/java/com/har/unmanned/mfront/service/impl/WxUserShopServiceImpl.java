package com.har.unmanned.mfront.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.har.unmanned.mfront.api.wxUser.InputParameter;
import com.har.unmanned.mfront.config.ErrorCode;
import com.har.unmanned.mfront.dao.*;
import com.har.unmanned.mfront.dao.extend.ShopWechatQueryMapper;
import com.har.unmanned.mfront.exception.ApiBizException;
import com.har.unmanned.mfront.model.*;
import com.har.unmanned.mfront.model.extend.CodeGoodsDomain;
import com.har.unmanned.mfront.model.extend.ShopOrderDomain;
import com.har.unmanned.mfront.model.extend.ShopOrderItemDomain;
import com.har.unmanned.mfront.model.extend.ShopStockDomain;
import com.har.unmanned.mfront.service.IWxUserShopService;
import com.har.unmanned.mfront.utils.CheckUtil;
import com.har.unmanned.mfront.utils.RandomUtils;
import com.har.unmanned.mfront.utils.StringUtil;
import com.har.unmanned.mfront.utils.UserUtil;
import lombok.extern.slf4j.Slf4j;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

@Service
@Slf4j
public class WxUserShopServiceImpl extends IWxUserShopService {
    @Autowired
    private UserUtil userUtil;
    @Autowired
    private ShopOrderMapper shopOrderMapper;
    @Autowired
    private ShopOrderItemMapper shopOrderItemMapper;
    @Autowired
    private ShopStockMapper shopStockMapper;
    @Autowired
    private ShopWechatMapper shopWechatMapper;
    @Autowired
    private ShopWechatQueryMapper shopWechatQueryMapper;
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private RedisServiceImpl redisService;
    @Value("${har.picPath}")
    private String picPath;
    private static final String UNMANNED = "unmanned:order:";

    @Override
    public JSONObject selectGoodsList(String param) throws ApiBizException {
        JSONObject respJson = new JSONObject();
        log.info("service传入参数: " + param);
        ShopWechat shopWechat = userUtil.userInfo();
        log.info("获取到的授权用户信息: " + JSONObject.toJSONString(shopWechat));
        List<ShopStockDomain> shopStockDomains = shopWechatQueryMapper.selectShopGoodsList(param);
        JSONObject dataList = formatGoodsList(shopStockDomains);
        respJson.put("dataList", dataList);
        if (shopStockDomains.isEmpty()) {
            JSONArray array = new JSONArray();
            respJson.put("recentPurchaseList", array);
            return respJson;
        }
        JSONArray recentPurchaseList = new JSONArray();
        List<ShopStockDomain> codeGoods = shopWechatQueryMapper.selectRecentlyBuyList(shopWechat.getOpenid(), shopStockDomains.get(0).getShopId().toString()); // 查询最近购买
        for (ShopStockDomain codeGood : codeGoods) {
            JSONObject object = new JSONObject();
            object.put("goodsId", codeGood.getGoodsId());
            object.put("name", codeGood.getGoodsId());
            object.put("image", picPath + (CheckUtil.isNull(codeGood.getImage()) ? "" : codeGood.getImage()));
            object.put("price", codeGood.getGoodsId());
            object.put("quantity", codeGood.getGoodsId());
            object.put("layer", codeGood.getGoodsId());
            recentPurchaseList.add(object);
        }
        respJson.put("recentPurchaseList", recentPurchaseList);
        return respJson;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject submitOrder(InputParameter param) throws ApiBizException {
        log.info("service传入参数: " + param);
        ShopWechat shopWechat = userUtil.userInfo();
        log.info("获取到的授权用户信息: " + JSONObject.toJSONString(shopWechat));
        /** 1. 防止重复提交订单 */
        StringBuilder redisKey = new StringBuilder();
        redisKey.append(shopWechat.getOpenid());
        redisKey.append(",").append(param.getShopCode());
        boolean exists = redisService.isKeyExists(UNMANNED + redisKey.toString());
        if (exists) {
            log.error("提交订单重复请求:" + param);
            throw new ApiBizException(ErrorCode.E00000001.CODE, "提交订单重复请求", param);
        }
        redisService.put(UNMANNED + redisKey, "", 10);


        /** 2. 获取货架信息 */
        ShopExample shopExample = new ShopExample();
        ShopExample.Criteria criteria = shopExample.createCriteria();
        criteria.andShopCodeEqualTo(param.getShopCode());
        List<Shop> shops = shopMapper.selectByExample(shopExample);
        if (shops.isEmpty()) {
            log.error("查询货架信息异常:" + param);
            throw new ApiBizException(ErrorCode.E00000001.CODE, "查询货架信息异常", param.getShopCode());
        }
        Shop shop = shops.get(0);
        log.info("获取到的货架信息:" + JSONObject.toJSONString(shop));


        /** 3. 生成订单记录 */
        JSONArray goodsList = param.getGoodsList();
        log.info("所传入的购买的商品信息的集合:" + goodsList);
        List<Long> ids = new ArrayList();
        JSONObject reqJson = new JSONObject(); // 优化请求中的商品id以及数量信息, 使商品id与数量一一对应, 类似于{'1':'3','9':'2'}
        for (Object o : goodsList) {
            JSONObject object = (JSONObject) o;
            ids.add(object.getLong("id"));
            reqJson.put(object.getString("id"), object.getString("num"));
        }
        List<CodeGoodsDomain> codeGoods = shopWechatQueryMapper.selectGoodsInfo(shop.getId(), ids);
        log.info("用户所购买的商品集合: " + JSONObject.toJSONString(codeGoods));
        int totalMoney = 0; //支付的总金额
        for (CodeGoodsDomain domain : codeGoods) {
            int num = reqJson.getIntValue(domain.getId().toString());
            if (num > domain.getQuantity()) { // 如果当前商品的购买数量超过库存总数
                throw new ApiBizException(ErrorCode.E00000001.CODE, "商品" + domain.getName() + "下单数量" + num + "超过库存数量" + domain.getQuantity(), param);
            } else {
                totalMoney += domain.getPrice() * num;
            }
        }
        // 销售单
        ShopOrder shopOrder = new ShopOrder();
        shopOrder.setShopId(shop.getId());
        shopOrder.setOpenid(shopWechat.getOpenid());
        shopOrder.setName(shopWechat.getName());
        shopOrder.setOrderNo(StringUtil.getRandomStrByCurrentTime(5, RandomUtils.generateNumberString(4)));
        shopOrder.setOrderTime(new Date());
        shopOrder.setAmount(totalMoney); // 单位(分)
        BigDecimal ratio = shop.getRatio();
        BigDecimal commission = ratio.divide(new BigDecimal(100)).multiply(new BigDecimal(totalMoney));
        DecimalFormat format = new DecimalFormat("0");
        shopOrder.setCommission(Integer.parseInt(format.format(commission))); // 单位(分)
        shopOrder.setLocation(param.getLocation());
        shopOrder.setLatitude(param.getLatitude());
        shopOrder.setLongitude(param.getLongitude());
        shopOrder.setStatus(0); //未支付
        shopOrderMapper.insertSelective(shopOrder);
        // 订单明细
        for (CodeGoodsDomain codeGood : codeGoods) {
            ShopOrderItem item = new ShopOrderItem();
            item.setOrderId(shopOrder.getId());
            item.setPrice(codeGood.getPrice());
            item.setQuantity(reqJson.getIntValue(codeGood.getId().toString()));
            item.setAmount(codeGood.getPrice() * reqJson.getIntValue(codeGood.getId().toString()));
            item.setGoodsId(codeGood.getId());
            item.setShopId(shop.getId());
            item.setShopCode(shop.getShopCode());
            item.setOrderTime(new Date());
            shopOrderItemMapper.insertSelective(item);
        }

        String string = JSONObject.toJSONString(shopOrder);
        return JSONObject.parseObject(string);
    }

    @Override
    public JSONObject payOrder(JSONObject param) {

        return null;
    }

    @Override
    public JSONObject callBack(JSONObject param) {

        return null;
    }

    @Override
    public JSONObject buyRecord() throws ApiBizException {
        log.info("-----------------用户所购买的商品信息service开始----------------");
        JSONObject respJson = new JSONObject();
        ShopWechat shopWechat = userUtil.userInfo();
        List<ShopOrderDomain> shopOrderDomains = shopWechatQueryMapper.selectBuyHistory(shopWechat.getOpenid());
        log.info("用户所购买的商品信息: " + JSONObject.toJSONString(shopOrderDomains));
        JSONArray dataList = new JSONArray(); // 用户所购买的商品集合
        if (shopOrderDomains.isEmpty()) { //如果没有购买
            respJson.put("dataList", dataList);
            return respJson;
        }
        for (ShopOrderDomain shopOrderDomain : shopOrderDomains) {
            JSONObject shopOrder = new JSONObject();
            shopOrder.put("orderNo", shopOrderDomain.getOrderNo());
            shopOrder.put("amount", CheckUtil.isNull(shopOrderDomain.getAmount()) ? "" : StringUtil.conversionYUAN(shopOrderDomain.getAmount().toString(), 2)); // 单位(元)
            shopOrder.put("payTimeString", shopOrderDomain.getPayTimeString());
            JSONArray goodsList = new JSONArray();
            for (ShopOrderItemDomain itemDomain : shopOrderDomain.getItems()) {
                JSONObject item = new JSONObject();
                item.put("name", itemDomain.getName());
                item.put("image", picPath + (CheckUtil.isNull(itemDomain.getImage()) ? "" : itemDomain.getImage()));
                item.put("price", itemDomain.getPrice());
                item.put("quantity", itemDomain.getQuantity());
                goodsList.add(item);
            }
            shopOrder.put("goodsList", goodsList);
            dataList.add(shopOrder);
        }
        log.info("返回的记录信息: " + respJson);
        log.info("-----------------用户所购买的商品信息service结束----------------");
        return respJson;
    }

    @Override
    public JSONObject userInfo() throws ApiBizException {
        log.info("-----------------查询用户详情service开始----------------");
        JSONObject respJson = new JSONObject();
        ShopWechat shopWechat = userUtil.userInfo();
        respJson.put("name", shopWechat.getName());
        respJson.put("headimgUrl", shopWechat.getHeadimgUrl());
        log.info("-----------------查询用户详情service结束----------------");
        return respJson;
    }

    private JSONObject formatGoodsList(List<ShopStockDomain> list) {
        log.info("-----------------格式化商品开始----------------");
        log.info("传入的商品集合: " + JSONObject.toJSONString(list));
        JSONObject dataList = new JSONObject();
        JSONArray goodsList = new JSONArray();
        for (ShopStockDomain shopStockDomain : list) {
            if (dataList.containsKey(shopStockDomain.getLayer())) {
                JSONObject object = new JSONObject();
                object.put("goodsId", shopStockDomain.getGoodsId());
                object.put("name", shopStockDomain.getName());
                object.put("image", picPath + (CheckUtil.isNull(shopStockDomain.getImage()) ? "" : shopStockDomain.getImage()));
                object.put("price", shopStockDomain.getPrice());
                object.put("quantity", shopStockDomain.getQuantity());
                goodsList.add(object);
            } else {
                if (goodsList.isEmpty()) { // 首次进入
                    JSONObject object = new JSONObject();
                    object.put("goodsId", shopStockDomain.getGoodsId());
                    object.put("name", shopStockDomain.getName());
                    object.put("image", picPath + (CheckUtil.isNull(shopStockDomain.getImage()) ? "" : shopStockDomain.getImage()));
                    object.put("price", shopStockDomain.getPrice());
                    object.put("quantity", shopStockDomain.getQuantity());
                    goodsList.add(object);
                } else {
                    dataList.put(shopStockDomain.getLayer().toString(), goodsList);
                    goodsList.clear();
                    JSONObject object = new JSONObject();
                    object.put("goodsId", shopStockDomain.getGoodsId());
                    object.put("name", shopStockDomain.getName());
                    object.put("image", picPath + (CheckUtil.isNull(shopStockDomain.getImage()) ? "" : shopStockDomain.getImage()));
                    object.put("price", shopStockDomain.getPrice());
                    object.put("quantity", shopStockDomain.getQuantity());
                    goodsList.add(object);
                }
            }
        }

        log.info("格式化后的数据: " + dataList);
        log.info("-----------------格式化商品开始----------------");
        return dataList;
    }
}
