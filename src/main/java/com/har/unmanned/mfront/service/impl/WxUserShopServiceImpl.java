package com.har.unmanned.mfront.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.har.bigdata.log.LogHelper;
import com.har.bigdata.log.LogType;
import com.har.unmanned.mfront.api.wxUser.InputParameter;
import com.har.unmanned.mfront.config.ErrorCode;
import com.har.unmanned.mfront.dao.ShopMapper;
import com.har.unmanned.mfront.dao.ShopOrderItemMapper;
import com.har.unmanned.mfront.dao.ShopOrderMapper;
import com.har.unmanned.mfront.dao.ShopStockMapper;
import com.har.unmanned.mfront.dao.extend.ShopWechatQueryMapper;
import com.har.unmanned.mfront.exception.ApiBizException;
import com.har.unmanned.mfront.model.*;
import com.har.unmanned.mfront.model.extend.CodeGoodsDomain;
import com.har.unmanned.mfront.service.IWxUserShopService;
import com.har.unmanned.mfront.utils.CheckUtil;
import com.har.unmanned.mfront.utils.RandomUtils;
import com.har.unmanned.mfront.utils.StringUtil;
import com.har.unmanned.mfront.utils.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import scala.annotation.meta.param;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	private ShopWechatQueryMapper shopWechatQueryMapper;
	@Autowired
	private ShopMapper shopMapper;
	@Autowired
	private RedisServiceImpl redisService;

	private static final String UNMANNED = "unmanned:order:";

	@Override
	public JSONObject selectGoodsList(String param) throws ApiBizException {
		log.info("service传入参数: " + param);
		ShopWechat shopWechat = userUtil.userInfo();
		log.info("获取到的授权用户信息: " + JSONObject.toJSONString(shopWechat));

		return null;
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
			log.info("提交订单重复请求:" + param);
			throw new ApiBizException(ErrorCode.E00000001.CODE, "提交订单重复请求", param);
		}
		redisService.put(UNMANNED + redisKey, "", 10);


		/** 2. 获取货架信息 */
		ShopExample shopExample = new ShopExample();
		ShopExample.Criteria criteria = shopExample.createCriteria();
		criteria.andShopCodeEqualTo(param.getShopCode());
		List<Shop> shops = shopMapper.selectByExample(shopExample);
		if (shops.isEmpty()) {
			log.info("查询货架信息异常:" + param);
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
	public JSONObject buyRecord() {

		return null;
	}

	@Override
	public JSONObject userInfo() {
		log.info("查询用户详情service");
		return null;
	}

}
