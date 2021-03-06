package com.har.unmanned.mfront.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.har.bigdata.exception.CommonExceptionLevel;
import com.har.bigdata.util.AESUtil;
import com.har.unmanned.mfront.api.wxUser.InputParameter;
import com.har.unmanned.mfront.config.CodeConstants;
import com.har.unmanned.mfront.config.ErrorCode;
import com.har.unmanned.mfront.dao.ShopMapper;
import com.har.unmanned.mfront.dao.ShopOrderItemMapper;
import com.har.unmanned.mfront.dao.ShopOrderMapper;
import com.har.unmanned.mfront.dao.extend.ShopWechatQueryMapper;
import com.har.unmanned.mfront.exception.ApiBizException;
import com.har.unmanned.mfront.model.*;
import com.har.unmanned.mfront.model.extend.*;
import com.har.unmanned.mfront.service.IWxUserShopService;
import com.har.unmanned.mfront.service.WxPayService;
import com.har.unmanned.mfront.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

@Service
@Slf4j
public class WxUserShopServiceImpl implements IWxUserShopService {
    @Autowired
    private UserUtil userUtil;
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private ShopOrderMapper shopOrderMapper;
    @Autowired
    private ShopOrderItemMapper shopOrderItemMapper;
    @Autowired
    private ShopWechatQueryMapper shopWechatQueryMapper;
    @Autowired
    private WxPayService wxPayService;
    @Autowired
    private RedisServiceImpl redisService;
    @Autowired
    private WeiXinUtils weiXinUtils;
    @Value("${har.picPath}")
    private String picPath;
    @Value("${wx.pay.paternerKey}")
    private String paternerKey;
    @Value("${wx.pay.msgKey}")
    private String msgKey;
    @Value("${wx.pay.defaultIp}")
    private String defaultIp;
    private static final String UNMANNED = "unmanned:order:";

    @Override
    public JSONObject selectGoodsList(String param) throws Exception {
        JSONObject respJson = new JSONObject();
        log.info("service传入参数: " + param);
        respJson.put("shopCode", param);
        ShopWechat shopWechat = userUtil.userInfo();
        log.info("获取到的授权用户信息: " + JSONObject.toJSONString(shopWechat));
        String shopCode = AESUtil.getInstance(msgKey).decrypt(param);
        log.info("解密的货架编号信息: " + shopCode);
        List<ShopStockDomain> shopStockDomains = shopWechatQueryMapper.selectShopGoodsList(shopCode); //条件: 商品正常, 商品货架库存大于0, 货架上架
        log.info("获取到的货架产品列表: {}", JSONObject.toJSONString(shopStockDomains));
        if (shopStockDomains.isEmpty()) { // 该货架没有可购买商品
            log.error("货架已下架或货架没有任何可购买商品");
            throw new ApiBizException(ErrorCode.E00000001.CODE, "对不起, 没有可购买商品", param);
        }

        // 查询此货架的最大商品层级信息
        Integer maxLayer = shopWechatQueryMapper.selectMaxLayer(shopCode);
        log.info("获取到的最大层级信息: {}", maxLayer);
        JSONArray dataList = new JSONArray();
        for (int i = 0; i <= maxLayer; i++) { // 从第一层开始到最大层
            JSONObject object = new JSONObject(); // 每一层的所有信息
            JSONArray goodsList = new JSONArray(); // 每一层的商品列表
            for (ShopStockDomain domain : shopStockDomains) {
                if (CheckUtil.isEquals(domain.getLayer().toString(), String.valueOf(i))) {
                    JSONObject goods = new JSONObject();
                    String goodsId = Base64Utils.encodeToString(AESUtil.getInstance(msgKey).encrypt(domain.getGoodsId().toString()).getBytes());
                    goods.put("goodsId", goodsId); //将商品id加密后再编码
                    goods.put("name", domain.getName());//商品名称
                    goods.put("image", picPath + (CheckUtil.isNull(domain.getImage()) ? "" : domain.getImage()));//商品图片
                    goods.put("price", domain.getPrice());//商品单价
                    goods.put("quantity", domain.getQuantity());//商品库存
                    if (CheckUtil.isNull(domain.getBarCode())) {
                        goods.put("barCode", "-1");//商品条形码
                    } else {
                        goods.put("barCode", domain.getBarCode());//商品条形码
                    }
                    goodsList.add(goods);
                }
            }
            if (!goodsList.isEmpty()) {
                object.put("layer", i);
                object.put("goodsList", goodsList);
                dataList.add(object);
            }
        }
        //JSONArray dataList = formatGoodsList(shopStockDomains);
        respJson.put("dataList", dataList);
        if (shopStockDomains.isEmpty()) {
            JSONArray array = new JSONArray();
            respJson.put("recentPurchaseList", array);
            return respJson;
        }
        JSONArray recentPurchaseList = new JSONArray();
        List<ShopStockDomain> codeGoods = shopWechatQueryMapper.selectRecentlyBuyList(shopWechat.getOpenid(), shopStockDomains.get(0).getShopId().toString()); // 查询最近购买, 条件: 订单已支付成功, 货架已上架
        for (ShopStockDomain codeGood : codeGoods) {
            JSONObject object = new JSONObject();
            String goodsId = Base64Utils.encodeToString(AESUtil.getInstance(msgKey).encrypt(codeGood.getGoodsId().toString()).getBytes());
            object.put("goodsId", goodsId);
            object.put("name", codeGood.getName());
            object.put("image", picPath + (CheckUtil.isNull(codeGood.getImage()) ? "" : codeGood.getImage()));
            object.put("price", codeGood.getPrice());
            object.put("quantity", codeGood.getQuantity());
            object.put("layer", codeGood.getLayer());
            object.put("status", codeGood.getStatus());
            recentPurchaseList.add(object);
        }
        respJson.put("recentPurchaseList", recentPurchaseList);
        System.out.println(respJson);
        return respJson;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ShopOrder submitOrder(InputParameter param) throws Exception {
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
        String shopCode = AESUtil.getInstance(msgKey).decrypt(param.getShopCode());
        criteria.andShopCodeEqualTo(shopCode);
        criteria.andStatusEqualTo(CodeConstants.ShopStatus.ON); // 货架上架
        List<Shop> shops = shopMapper.selectByExample(shopExample);
        // 货架不存在
        if (shops.isEmpty()) {
            log.error("查询货架信息异常:" + param);
            throw new ApiBizException(ErrorCode.E00000001.CODE, ErrorCode.E00000001.MSG, shopCode);
        }
        Shop shop = shops.get(0);
        log.info("获取到的货架信息:" + JSONObject.toJSONString(shop));

        /** 3. 生成订单记录 */
        JSONArray goodsList = param.getGoodsList();
        log.info("所传入的购买的商品信息的集合:" + goodsList);
        List<Long> ids = new ArrayList(); // 用于查询商品信息
        JSONObject reqJson = new JSONObject(); // 优化请求中的商品id以及数量信息, 使商品id与数量一一对应, 类似于{'1':'3','9':'2'}
        for (Object o : goodsList) {
            JSONObject object = (JSONObject) JSONObject.toJSON(o);
            String goodsId = AESUtil.getInstance(msgKey).decrypt(new String(Base64Utils.decodeFromString(object.getString("goodsId")))); // 将商品id解码后再解密
            ids.add(Long.parseLong(goodsId));
            reqJson.put(goodsId, object.getString("goodsNum"));
        }
        log.info("ids: " + ids);
        List<CodeGoodsDomain> codeGoods = shopWechatQueryMapper.selectGoodsInfo(shop.getId(), ids); // 查询用户购买的门店商品列表
        if (codeGoods.size() != ids.size()) {
            log.error("传入的商品种类数量和查询出来的商品种类数量不一致\r\n" + "ids: " + ids + "\r\ncodeGoods" + codeGoods);
            throw new ApiBizException(ErrorCode.E00000001.CODE, ErrorCode.E00000001.MSG, param);
        }
        log.info("用户所购买的商品集合: " + JSONObject.toJSONString(codeGoods));
        int totalMoney = 0; //支付的总金额
        for (CodeGoodsDomain domain : codeGoods) {
            int num = reqJson.getIntValue(domain.getId().toString());
            //if (num > domain.getQuantity()) { // 如果当前商品的购买数量超过库存总数
            //    log.error("商品" + domain.getName() + "下单数量" + num + "超过库存数量");
            //    throw new ApiBizException(ErrorCode.E00000001.CODE, domain.getName() + "数量" + num + "超过库存数量" + domain.getQuantity(), param);
            //} else {
                totalMoney += domain.getPrice() * num;
            //}
        }

        try {
            // 1. 门店销售单
            ShopOrder shopOrder = new ShopOrder();
            shopOrder.setShopId(shop.getId()); //设置门店id
            shopOrder.setOpenid(shopWechat.getOpenid()); //下单人员
            //shopOrder.setName(shopWechat.getName()); //微信号昵称
            if (!CheckUtil.isNull(shopWechat.getName())) {
                shopOrder.setName(Base64.encodeBase64String(shopWechat.getName().getBytes("UTF-8")));
            }
            shopOrder.setOrderNo(StringUtil.getRandomStrByCurrentTime(5, RandomUtils.generateNumberString(4))); //订单号
            shopOrder.setOrderTime(new Date()); //下单时间
            shopOrder.setAmount(totalMoney); // 订单金额, 单位(分)
            BigDecimal ratio = shop.getRatio();
            if (CheckUtil.isNull(ratio) || ratio.intValue() == 0) {
                shopOrder.setRatio(new BigDecimal(0)); //佣金比例
                shopOrder.setCommission(0); // 佣金, 单位(分)
            } else {
                BigDecimal commission = ratio.divide(new BigDecimal(100)).multiply(new BigDecimal(totalMoney));
                DecimalFormat format = new DecimalFormat("0");
                shopOrder.setRatio(ratio);
                shopOrder.setCommission(Integer.parseInt(format.format(commission))); // 单位(分)
            }
            shopOrder.setLocation(shop.getAddress()); //下单具体位置
            shopOrder.setLatitude(shop.getLatitude()); //下单经纬度
            shopOrder.setLongitude(shop.getLongitude());
            shopOrder.setStatus(CodeConstants.OrderStatus.UNPAID); //未支付
            shopOrderMapper.insert(shopOrder);
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
        } catch (Exception e) {
            e.printStackTrace();
            String string = JSONObject.toJSONString(param);
            log.error("用户提交订单失败: " + string);
            throw new ApiBizException(ErrorCode.E00000026.CODE, ErrorCode.E00000026.MSG, string, CommonExceptionLevel.WARN);
        }

    }

    @Override
    public JSONObject payOrder(ShopOrder shopOrder) throws Exception {
        JSONObject respJson = new JSONObject();
        log.info("----------------微信统一下单开始-----------------");
        log.info("支付订单service传入参数: " + JSONObject.toJSONString(shopOrder));
        String userIp = ContextHolderUtils.getIp();
        String ip;
        if (!CheckUtil.isNull(userIp)) {
            String[] ips = userIp.split(",");
            if (ips.length == 0) {
                ip = defaultIp;
            } else {
                ip = ips[0];
            }
        } else {
            ip = defaultIp;
        }

        Map<String, String> map = wxPayService.paymentOrderH5(shopOrder.getOpenid(), shopOrder.getAmount().toString(), "wxpay", shopOrder.getOrderNo(), ip);
        JSONObject object = (JSONObject) JSONObject.toJSON(map);
        respJson.put("singData", object);
        respJson.put("orderNo", shopOrder.getOrderNo());
        log.info("----------------微信统一下单结束-----------------");
        return respJson;
    }

    @Override
    public void callBack(String param) throws Exception {
        log.info("----------------微信回调开始-----------------");
        log.info("微信回调service传入参数: " + param);
        TreeMap<String, String> map = (TreeMap<String, String>) XMLUtil.doXMLParse(param);
        String orderNo = map.get("out_trade_no");
        ShopOrderExample example = new ShopOrderExample();
        ShopOrderExample.Criteria criteria = example.createCriteria();
        criteria.andOrderNoEqualTo(orderNo);
        List<ShopOrder> shopOrders = shopOrderMapper.selectByExample(example);
        ShopOrder shopOrder = shopOrders.get(0);
        log.info("相关的订单信息: " + JSONObject.toJSONString(shopOrder));

        /** 验证微信订单支付情况 */
        if (shopOrder.getStatus().intValue() == CodeConstants.OrderStatus.UNPAID.intValue()) {
            if (!CheckUtil.isEquals(map.get("return_code"), "SUCCESS")) { // 通信异常
                log.error("微信回调异常: " + map.get("return_msg"));
                throw new ApiBizException(ErrorCode.E00000018.CODE, map.get("return_msg"), param, CommonExceptionLevel.WARN);
            }
            if (!CheckUtil.isEquals(map.get("result_code"), "SUCCESS")) { // 支付异常
                log.error("微信回调异常: " + map.get("err_code_des"));
                throw new ApiBizException(ErrorCode.E00000018.CODE, map.get("err_code_des"), param, CommonExceptionLevel.WARN);
            }
            boolean validSign = weiXinUtils.isValidSign(map, paternerKey);
            if (!validSign) {
                log.error("微信签名验证异常: " + param);
                throw new ApiBizException(ErrorCode.E00000019.CODE, ErrorCode.E00000019.MSG, param, CommonExceptionLevel.WARN);
            }
            Map<String, String> orderquery = wxPayService.orderQuery(orderNo);
            if (!CheckUtil.isEquals(orderquery.get("trade_state"), "SUCCESS")) {
                log.error("微信支付结果验证异常: " + orderquery);
                throw new ApiBizException(ErrorCode.E00000020.CODE, "订单" + orderNo + "支付失败", orderquery, CommonExceptionLevel.WARN);
            }

            log.info("订单支付成功");
            /** 处理后台业务逻辑 */
            JSONArray errorList = new JSONArray(); // 用于存放更新失败的商品信息(货架实际商品数量大于库存数量)
            JSONArray successList = new JSONArray(); // 用于存放更新货架商品库存过程中发生异常, 而更新成功的部分商品库存信息
            try {
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
                    Integer integer = shopWechatQueryMapper.updateGoodsStock(shopId.toString(), goodsId.toString(), num.toString());
                    if (integer.intValue() != 1) { // 更新失败
                        log.error("商品id: "+ shopOrderItem.getGoodsId() +"库存不足");
                        JSONObject errorGoods = new JSONObject();
                        errorGoods.put("goodsId", goodsId);
                        errorGoods.put("shopId", shopId);
                        errorGoods.put("buyNum", num);
                        errorList.add(errorGoods);
                    } else if (integer.intValue() == 1) { // 更新成功
                        log.info("商品id: " + shopOrderItem.getGoodsId() + "库存信息更新成功");
                        JSONObject successGoods = new JSONObject();
                        successGoods.put("goodsId", goodsId);
                        successGoods.put("shopId", shopId);
                        successGoods.put("buyNum", num);
                        successList.add(successGoods);
                    }
                }
                if (errorList.size() > 0) { // 因库存数量异常导致回调库存扣减失败(货架实际库存大于数据库库存, 而用户购买数量大于数据库库存)
                    for (Object o : errorList) {
                        JSONObject object = (JSONObject) o;
                        String goodsId = object.getString("goodsId");
                        String shopId = object.getString("shopId");
                        shopWechatQueryMapper.resetGoodsStock(shopId, goodsId); // 将商品库存清零
                    }
                    throw new ApiBizException(ErrorCode.E00000021.CODE, ErrorCode.E00000021.MSG, errorList, CommonExceptionLevel.WARN);
                }
            } catch (ApiBizException e) {
                e.printStackTrace();
                log.error("库存异常: " + e.getMessage());
                throw new ApiBizException(e.getErrCode(), e.getMessage(), "订单信息: \r\n" + JSONObject.toJSONString(shopOrder) + "\r\n, 清零货架相关信息: \r\n" + errorList, CommonExceptionLevel.WARN);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("订单或库存信息更新失败: " + e.getMessage());
                throw new ApiBizException(ErrorCode.E00000022.CODE, ErrorCode.E00000022.MSG, "订单信息: \r\n" + JSONObject.toJSONString(shopOrder) + "\r\n, 更新库存成功的相关信息: \r\n" + successList, CommonExceptionLevel.WARN);
            }
        }
        log.info("----------------微信回调结束-----------------");
    }

    @Override
    public JSONObject buyRecord(String param) throws Exception {
        log.info("-----------------用户所购买的商品信息service开始----------------");
        JSONObject respJson = new JSONObject();
        ShopWechat shopWechat = userUtil.userInfo();
        //ShopExample example = new ShopExample();
        //ShopExample.Criteria criteria = example.createCriteria();
        //String shopCode = AESUtil.getInstance(msgKey).decrypt(param);
        //criteria.andShopCodeEqualTo(shopCode);
        //List<Shop> shops = shopMapper.selectByExample(example);
        //if (shops.isEmpty()) {
        //    throw new ApiBizException(ErrorCode.E00000001.CODE, ErrorCode.E00000001.MSG, shopCode);
        //}
        List<ShopOrderDomain> shopOrderDomains = shopWechatQueryMapper.selectBuyHistory(shopWechat.getOpenid(), null);
        //List<ShopOrderDomain> shopOrderDomains = shopWechatQueryMapper.selectBuyHistory(shopWechat.getOpenid(), shops.get(0).getId().toString());
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
                item.put("price", CheckUtil.isNull(itemDomain.getPrice()) ? "" : StringUtil.conversionYUAN(itemDomain.getPrice().toString(), 2));
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
    public JSONObject userInfo() throws Exception {
        log.info("-----------------查询用户详情service开始----------------");
        JSONObject respJson = new JSONObject();
        ShopWechat shopWechat = userUtil.userInfo();
        respJson.put("name", shopWechat.getName());
        respJson.put("headimgUrl", CheckUtil.isNull(shopWechat.getHeadimgUrl()) ? "" : shopWechat.getHeadimgUrl());
        log.info("-----------------查询用户详情service结束----------------");
        return respJson;
    }

    /**
     * 格式化商品列表, 使其按照layer分类, 将每一层的商品数量统计出来, 传入的list是按照layer排好顺序的
     * @param list
     * @return
     */
    private JSONArray formatGoodsList(List<ShopStockDomain> list) {
        log.info("-----------------格式化商品开始----------------");
        log.info("传入的商品集合: " + JSONObject.toJSONString(list));
        JSONArray dataList = new JSONArray(); // 返回的数据
        JSONArray layerGoodsList = new JSONArray(); // 层级的数据集合, 如果到下一层, 本层数据会备份一份然后清空
        Map map = new HashMap(); //层级是否变化标志, 类似指针, 当商品列表循环到下一层级时, 指针指向下一层级
        for (ShopStockDomain shopStockDomain : list) {
            if (map.isEmpty()) { // 首次进入, 标志中放入层级信息
                map.put("layer", shopStockDomain.getLayer());
            }
            if (map.containsValue(shopStockDomain.getLayer())) { // 如果层级信息没有变化, 往层级的数据集合中放入商品信息
                JSONObject object = new JSONObject();
                object.put("goodsId", shopStockDomain.getGoodsId());
                object.put("name", shopStockDomain.getName());
                object.put("image", picPath + (CheckUtil.isNull(shopStockDomain.getImage()) ? "" : shopStockDomain.getImage()));
                object.put("price", shopStockDomain.getPrice());
                object.put("quantity", shopStockDomain.getQuantity());
                layerGoodsList.add(object);
            } else { // 层级信息变化, 复制一份, 放入需返回的数据中, 清空层级的数据集合, 再把当前商品信息放入层级数据集合
                JSONArray clone = (JSONArray) layerGoodsList.clone(); // 复制一份, 以免layerGoodsList清除影响dataList中的数据
                JSONObject tempJson = new JSONObject();
                tempJson.put("layer", map.get("layer").toString());
                tempJson.put("goodsList", clone);
                dataList.add(tempJson);
                layerGoodsList.clear();
                JSONObject object = new JSONObject();
                object.put("goodsId", shopStockDomain.getGoodsId());
                object.put("name", shopStockDomain.getName());
                object.put("image", picPath + (CheckUtil.isNull(shopStockDomain.getImage()) ? "" : shopStockDomain.getImage()));
                object.put("price", shopStockDomain.getPrice());
                object.put("quantity", shopStockDomain.getQuantity());
                layerGoodsList.add(object);
                map.put("layer", shopStockDomain.getLayer());
            }
        }
        JSONObject tempJson = new JSONObject();
        tempJson.put("layer", map.get("layer").toString()); // 放入最后一层的数据
        tempJson.put("goodsList", layerGoodsList); // 放入最后一层的数据
        dataList.add(tempJson);
        log.info("格式化后的数据: " + dataList);
        log.info("-----------------格式化商品开始----------------");
        return dataList;
    }
    public static void main(String[] args) throws Exception {
        //String s = "测试";
        //String s1 = new String(Base64Utils.encode(s.getBytes("utf-8")));
        //System.out.println(s1);
        //System.out.println(new String(Base64Utils.decode(s1.getBytes("utf-8"))));
        //String test = AESUtil.getInstance("B@1dsCC%ejk589^2").encrypt("test");
        //System.out.println(test);
        //System.out.println(AESUtil.getInstance("B@1dsCC%ejk589^2").decrypt("vbeDcMlSEQLgjFo8BLDTmg=="));
        //System.out.println(AESUtil.getInstance("B@1dsCC%ejk589^2").encrypt("0000074"));
        //System.out.println(Base64Utils.encodeToString(AESUtil.getInstance("B@1dsCC%ejk589^2").encrypt("123").getBytes()));
        //vbeDcMlSEQLgjFo8BLDTmg==    80
        //DZ2vwnwb1dJPuQP9aI6Xbg==    46
        System.out.println(AESUtil.getInstance("B@1dsCC%ejk589^2").encrypt("test"));
    }
}
