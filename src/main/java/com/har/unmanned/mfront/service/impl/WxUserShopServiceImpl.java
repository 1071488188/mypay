package com.har.unmanned.mfront.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.har.bigdata.exception.CommonExceptionLevel;
import com.har.bigdata.log.LogHelper;
import com.har.unmanned.mfront.api.wxUser.InputParameter;
import com.har.unmanned.mfront.config.CodeConstants;
import com.har.unmanned.mfront.config.ErrorCode;
import com.har.unmanned.mfront.dao.ShopMapper;
import com.har.unmanned.mfront.dao.ShopOrderItemMapper;
import com.har.unmanned.mfront.dao.ShopOrderMapper;
import com.har.unmanned.mfront.dao.extend.ShopWechatQueryMapper;
import com.har.unmanned.mfront.exception.ApiBizException;
import com.har.unmanned.mfront.model.*;
import com.har.unmanned.mfront.model.extend.CodeGoodsDomain;
import com.har.unmanned.mfront.model.extend.ShopOrderDomain;
import com.har.unmanned.mfront.model.extend.ShopOrderItemDomain;
import com.har.unmanned.mfront.model.extend.ShopStockDomain;
import com.har.unmanned.mfront.service.IWxUserShopService;
import com.har.unmanned.mfront.service.WxPayService;
import com.har.unmanned.mfront.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
    private ShopWechatQueryMapper shopWechatQueryMapper;
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private WxPayService wxPayService;
    @Autowired
    private RedisServiceImpl redisService;
    @Value("${har.picPath}")
    private String picPath;
    private static final String UNMANNED = "unmanned:order:";
    @Value("${wx.pay.appsecret}")
    private String appsecret;

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
            object.put("name", codeGood.getName());
            object.put("image", picPath + (CheckUtil.isNull(codeGood.getImage()) ? "" : codeGood.getImage()));
            object.put("price", codeGood.getPrice());
            object.put("quantity", codeGood.getQuantity());
            object.put("layer", codeGood.getLayer());
            recentPurchaseList.add(object);
        }
        respJson.put("recentPurchaseList", recentPurchaseList);
        return respJson;
    }

    @Override
    @Transactional(rollbackFor = ApiBizException.class)
    public ShopOrder submitOrder(InputParameter param) throws ApiBizException {
        log.info("----------------提交订单开始-----------------");
        log.info("提交订单service传入参数: " + param);
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
        List<Long> ids = new ArrayList(); // 用于查询商品信息
        JSONObject reqJson = new JSONObject(); // 优化请求中的商品id以及数量信息, 使商品id与数量一一对应, 类似于{'1':'3','9':'2'}
        for (Object o : goodsList) {
            JSONObject object = (JSONObject) o;
            ids.add(object.getLong("id"));
            reqJson.put(object.getString("id"), object.getString("num"));
        }
        List<CodeGoodsDomain> codeGoods = shopWechatQueryMapper.selectGoodsInfo(shop.getId(), ids); // 查询用户购买的门店商品列表
        log.info("用户所购买的商品集合: " + JSONObject.toJSONString(codeGoods));
        int totalMoney = 0; //支付的总金额
        for (CodeGoodsDomain domain : codeGoods) {
            int num = reqJson.getIntValue(domain.getId().toString());
            if (num > domain.getQuantity()) { // 如果当前商品的购买数量超过库存总数
                log.error("商品" + domain.getName() + "下单数量" + num + "超过库存数量");
                throw new ApiBizException(ErrorCode.E00000001.CODE, "商品" + domain.getName() + "下单数量" + num + "超过库存数量" + domain.getQuantity(), param);
            } else {
                totalMoney += domain.getPrice() * num;
            }
        }
        // 1. 门店销售单
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
        shopOrder.setStatus(CodeConstants.OrderStatus.UNPAID); //未支付
        shopOrderMapper.insertSelective(shopOrder);
        // 2. 订单明细
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

        log.info("----------------提交订单结束-----------------");
        return shopOrder;
    }

    @Override
    public JSONObject payOrder(ShopOrder shopOrder) throws Exception {
        log.info("----------------支付订单开始-----------------");
        log.info("支付订单service传入参数: " + JSONObject.toJSONString(shopOrder));
        String ip = ContextHolderUtils.getIp();
        Map<String, String> map = wxPayService.paymentOrderHbxWeb(shopOrder.getOpenid(), shopOrder.getAmount().toString(), "微信支付", shopOrder.getOrderNo(), ip);
        JSONObject respJson = (JSONObject) JSONObject.toJSON(map);
        log.info("----------------支付订单结束-----------------");
        return respJson;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, ApiBizException.class, IOException.class})
    public String callBack(String param) throws JDOMException, IOException, ApiBizException {
        log.info("----------------微信回调开始-----------------");
        log.info("微信回调service传入参数: " + param);
        StringBuilder sb = new StringBuilder(); // 返回结果, 用于通知微信
        Map resultMap = new HashMap(); // 存放支付结果信息, 用于返回
        TreeMap<String, String> map = (TreeMap<String, String>) XMLUtil.doXMLParse(param);
        String orderNo = map.get("out_trade_no");
        ShopOrderExample example = new ShopOrderExample();
        ShopOrderExample.Criteria criteria = example.createCriteria();
        criteria.andOrderNoEqualTo(orderNo);
        List<ShopOrder> shopOrders = shopOrderMapper.selectByExample(example);
        ShopOrder shopOrder = shopOrders.get(0);

        boolean flag = false; // true 支付成功, false 支付失败, 用于处理后台逻辑, 支付成功后更新订单和库存信息
        /** 验证微信订单支付情况 */
        if (shopOrder.getStatus().intValue() == CodeConstants.OrderStatus.UNPAID.intValue()) {
            try {
                if (!CheckUtil.isEquals(map.get("return_code"), "SUCCESS")) { // 通信异常
                    log.error("微信回调异常: " + map.get("return_msg"));
                    throw new ApiBizException(ErrorCode.E00000018.CODE, map.get("return_msg"), param, CommonExceptionLevel.WARN);
                }
                if (!CheckUtil.isEquals(map.get("result_code"), "SUCCESS")) { // 支付异常
                    log.error("微信回调异常: " + map.get("err_code_des"));
                    throw new ApiBizException(ErrorCode.E00000018.CODE, map.get("err_code_des"), param, CommonExceptionLevel.WARN);
                }
                boolean validSign = WeiXinUtils.isValidSign(map, appsecret);
                if (!validSign) {
                    log.error("微信签名验证异常: " + param);
                    throw new ApiBizException(ErrorCode.E00000019.CODE, ErrorCode.E00000019.MSG, param, CommonExceptionLevel.WARN);
                }
                Map<String, String> orderquery = wxPayService.orderquery(orderNo);
                if (!CheckUtil.isEquals(orderquery.get("trade_state"), "SUCCESS")) {
                    log.error("微信支付结果验证异常: " + orderquery);
                    throw new ApiBizException(ErrorCode.E00000020.CODE, "订单" + orderNo + "支付失败", orderquery, CommonExceptionLevel.WARN);
                }
                flag = true;
            } catch (ApiBizException e) {
                e.printStackTrace();
                resultMap.put("return_code", "FAIL");
                resultMap.put("return_msg", "签名验证失败");
                LogHelper.saveCommonException(e);
            } catch (Exception e) {
                e.printStackTrace();
                resultMap.put("return_code", "FAIL");
                resultMap.put("return_msg", "签名验证失败");
            }


            /** 处理后台业务逻辑 */
            try {
                if (flag) { // 支付成功
                    // 1. 更新基础订单信息
                    shopOrder.setPayNo(map.get("transaction_id")); // 微信订单号
                    shopOrder.setPayTime(new Date()); // 付款成功时间
                    shopOrder.setStatus(CodeConstants.OrderStatus.SUCCESS); // 订单支付成功
                    shopOrderMapper.updateByPrimaryKeySelective(shopOrder);

                    // 2. 扣减商品的库存
                    ShopOrderItemExample itemExample = new ShopOrderItemExample();
                    ShopOrderItemExample.Criteria itemCriteria = itemExample.createCriteria();
                    itemCriteria.andOrderIdEqualTo(shopOrder.getId());
                    itemCriteria.andShopIdEqualTo(shopOrder.getShopId());
                    List<ShopOrderItem> shopOrderItems = shopOrderItemMapper.selectByExample(itemExample);
                    for (ShopOrderItem shopOrderItem : shopOrderItems) {
                        Long shopId = shopOrderItem.getShopId(); //货架id
                        Long goodsId = shopOrderItem.getGoodsId(); // 商品id
                        Integer num = shopOrderItem.getQuantity(); // 商品数量
                        shopWechatQueryMapper.updateGoodsStock(shopId, goodsId , num);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                log.error("后台逻辑处理失败: " + orderNo);
                throw new ApiBizException(ErrorCode.E00000001.CODE, "订单" + orderNo + "后台逻辑处理失败", param, CommonExceptionLevel.WARN);
            }
        }

        /** 返回数据 */
        if (resultMap.isEmpty()) {
            resultMap.put("return_code", "SUCCESS");
            resultMap.put("return_msg", "支付成功");
        }
        sb.append("<xml>");
        for (Object o : resultMap.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            sb.append("<").append(entry.getKey()).append(">")
                    .append(entry.getValue())
                    .append("</").append(entry.getKey()).append(">");
        }
        sb.append("</xml>");
        log.info("----------------微信回调结束-----------------");
        return sb.toString();
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
        respJson.put("dataList", dataList);
        log.info("返回的记录信息: " + respJson);
        log.info("-----------------用户所购买的商品信息service结束----------------");
        return respJson;
    }

    @Override
    public JSONObject userInfo() throws ApiBizException, UnsupportedEncodingException {
        log.info("-----------------查询用户详情service开始----------------");
        JSONObject respJson = new JSONObject();
        ShopWechat shopWechat = userUtil.userInfo();

        respJson.put("name", CheckUtil.isNull(shopWechat.getName()) ? "" : new String(Base64.decodeBase64(shopWechat.getName().getBytes("utf-8")), "utf-8")); // 解码用户名
        respJson.put("headimgUrl", CheckUtil.isNull(shopWechat.getHeadimgUrl()) ? "" : shopWechat.getHeadimgUrl());
        log.info("-----------------查询用户详情service结束----------------");
        return respJson;
    }

    private JSONObject formatGoodsList(List<ShopStockDomain> list) {
        log.info("-----------------格式化商品开始----------------");
        log.info("传入的商品集合: " + JSONObject.toJSONString(list));
        JSONObject dataList = new JSONObject();
        JSONArray goodsList = new JSONArray();
        Map map = new HashMap(); //
        for (ShopStockDomain shopStockDomain : list) {
            if (map.containsValue(shopStockDomain.getLayer())) {
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
                    Object clone = goodsList.clone(); // 复制一份, 以免goodsList清除影响dataList中的数据
                    dataList.put(map.get("layer").toString(), clone);
                    goodsList.clear();
                    JSONObject object = new JSONObject();
                    object.put("goodsId", shopStockDomain.getGoodsId());
                    object.put("name", shopStockDomain.getName());
                    object.put("image", picPath + (CheckUtil.isNull(shopStockDomain.getImage()) ? "" : shopStockDomain.getImage()));
                    object.put("price", shopStockDomain.getPrice());
                    object.put("quantity", shopStockDomain.getQuantity());
                    goodsList.add(object);
                }
                map.put("layer", shopStockDomain.getLayer());
            }
        }
        dataList.put(map.get("layer").toString(), goodsList); // 放入最后一层的数据
        log.info("格式化后的数据: " + dataList);
        log.info("-----------------格式化商品开始----------------");
        return dataList;
    }
}
