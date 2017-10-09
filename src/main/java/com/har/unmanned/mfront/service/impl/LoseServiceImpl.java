package com.har.unmanned.mfront.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.har.bigdata.log.LogHelper;
import com.har.bigdata.log.LogType;
import com.har.unmanned.mfront.config.CodeConstants;
import com.har.unmanned.mfront.config.ErrorCode;
import com.har.unmanned.mfront.dao.ShopMapper;
import com.har.unmanned.mfront.dao.ShopStockLoseItemMapper;
import com.har.unmanned.mfront.dao.ShopStockLoseMapper;
import com.har.unmanned.mfront.dao.ShopStockMapper;
import com.har.unmanned.mfront.dao.extend.DispatchItemQueryMapper;
import com.har.unmanned.mfront.dao.extend.SysUserMapperExtend;
import com.har.unmanned.mfront.exception.ApiBizException;
import com.har.unmanned.mfront.model.*;
import com.har.unmanned.mfront.model.extend.DispatchItemDomain;
import com.har.unmanned.mfront.service.LoseService;
import com.har.unmanned.mfront.utils.CheckUtil;
import com.har.unmanned.mfront.utils.DateUtil;
import com.har.unmanned.mfront.utils.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author zhanggr
 * @create 2017-09-28
 **/
@Slf4j
@Service
public class LoseServiceImpl implements LoseService {
    @Autowired
    UserUtil userUtil;
    @Autowired
    private SysUserMapperExtend sysUserMapperExtend;
    @Autowired
    private DispatchItemQueryMapper dispatchItemQueryMapper;
    @Autowired
    private ShopStockLoseMapper shopStockLoseMapper;
    @Autowired
    private ShopStockLoseItemMapper shopStockLoseItemMapper;
    @Autowired
    private ShopStockMapper shopStockMapper;
    @Autowired
    private ShopMapper shopMapper;

    @Value("${har.picPath}")
    String basePicPath;

    /***
     * 检查用户授权
     * @return
     */
    private ShopWechat checkUserAuth() throws ApiBizException {
        ShopWechat wechatUser = userUtil.userInfo();

        //验证用户权限
        int userNum = sysUserMapperExtend.queryUserRole(wechatUser.getUserId().toString(), CodeConstants.Role.LOSE);
        if (userNum <= 0) {
            log.info("{},{}", "用户权限不足", JSONObject.toJSON(wechatUser));
            throw new ApiBizException(ErrorCode.E00000015.CODE, ErrorCode.E00000015.MSG, JSONObject.toJSON(wechatUser));
        }

        return wechatUser;
    }

    @Override
    public JSONObject shopStock(JSONObject param) throws ApiBizException {
        log.info("{},{}", "库存列表传入数据", param);
        LogHelper.save(LogType.RECEIVE, "库存列表传入参数", param);
        //############################请求数据#######################################
        // 货架编号
        String shopCode = param.getString("shopCode");
        //############################请求数据#######################################
        // 返回参数
        JSONObject respParam = new JSONObject();
        // 检查授权
        checkUserAuth();

        // 货架商品列表
        List<DispatchItemDomain> shopStockGoodsList = dispatchItemQueryMapper.shopStockGoodsList2ShopCode(shopCode);
        log.info("{},{}", "货架商品列表", JSONObject.toJSON(shopStockGoodsList));
        LogHelper.save(LogType.RECEIVE, "货架商品列表", JSONObject.toJSON(shopStockGoodsList));

        // 获取货架最大层数
        Integer maxLayer = dispatchItemQueryMapper.getMaxLayer2ShopCode(shopCode);

        // 货架商品列表
        JSONArray goodsArray = new JSONArray();
        // 配送单商品
        JSONObject disObj = new JSONObject();

        // 分装货架商品分层
        for (int i = 1; i <= maxLayer; i++) {
            JSONArray disArray = new JSONArray();
            for (DispatchItemDomain dispatchGoods : shopStockGoodsList) {
                if (CheckUtil.isEquals(dispatchGoods.getLayer().toString(), String.valueOf(i))) {
                    JSONObject goods = new JSONObject();
                    goods.put("goodsName", dispatchGoods.getGoodsName());//商品名称
                    goods.put("goodsValue", dispatchGoods.getPrice());//商品单价
                    goods.put("goodsPicture", basePicPath.concat(dispatchGoods.getGoodsPicture()));//商品图片
                    goods.put("goodsQuantity", dispatchGoods.getQuantity());//商品数量
                    goods.put("goodsId", dispatchGoods.getGoodsId());//商品ID
                    disArray.add(goods);
                }
            }

            disObj.put("layer", i);
            disObj.put("goodsList", disArray);
            goodsArray.add(disObj);
            disObj = new JSONObject();
        }

        respParam.put("dataList", goodsArray);//商品列表
        return respParam;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, ApiBizException.class})
    public void stockLose(JSONObject param) throws ApiBizException {
        log.info("{},{}", "货架盘存传入数据", param);
        LogHelper.save(LogType.RECEIVE, "货架盘存传入参数", param);
        //############################请求数据#######################################
        // 配送单
        String shopCode = param.getString("shopCode");
        // 补货清单
        JSONArray stocksArray = param.getJSONArray("stocks");
        // 货架ID
        long shopId = -1;
        //############################请求数据#######################################
        // 检查授权
        ShopWechat shopWechat = checkUserAuth();

        Map<Long, Long> goodsIds = new HashMap<>();
        Map<Long, Integer> stocks = new HashMap<>();
        for (int i = 0; i < stocksArray.size(); i++) {
            JSONObject stockObj = stocksArray.getJSONObject(i);

            String goodsIdStr = stockObj.getString("goodsId");
            String goodsNumStr = stockObj.getString("goodsNum");
            if (CheckUtil.isNull(goodsIdStr) || CheckUtil.isNull(goodsNumStr)) {
                log.error("{},{},{}", "商品信息校验不通过", stocksArray, stocksArray);
                throw new ApiBizException(ErrorCode.E00000012.CODE, ErrorCode.E00000012.MSG, stocksArray);
            }

            Long goodsId = new Long(goodsIdStr);
            goodsIds.put(goodsId, goodsId);
            stocks.put(goodsId, Integer.parseInt(goodsNumStr));
        }

        ShopExample example = new ShopExample();
        ShopExample.Criteria criteria = example.or();
        criteria.andShopCodeEqualTo(shopCode);
        List<Shop> shopList = shopMapper.selectByExample(example);
        if (!shopList.isEmpty()) {
            shopId = shopList.get(0).getId();
        } else {
            log.info("{},{}", "货架不存在", shopCode);
            throw new ApiBizException(ErrorCode.E00000001.CODE, "货架不存在", param);
        }

        // 货架商品列表
        List<DispatchItemDomain> shopStockGoodsList = dispatchItemQueryMapper.shopStockGoodsList2ShopCode(shopCode);
        log.info("{},{}", "货架商品列表", JSONObject.toJSON(shopStockGoodsList));
        LogHelper.save(LogType.RECEIVE, "货架商品列表", JSONObject.toJSON(shopStockGoodsList));

        Map<Long, DispatchItemDomain> shopStockGoodsMap = new HashMap<>();
        for (DispatchItemDomain domain : shopStockGoodsList) {
            shopStockGoodsMap.put(domain.getGoodsId(), domain);
        }

        // 确认商品是否存在于货架
        Set<Long> goodsIdSet = goodsIds.keySet();
        for (long goodsId : goodsIdSet) {
            if (CheckUtil.isNull(shopStockGoodsMap.get(goodsId))) {
                log.info("{},{},{}", "商品信息校验不通过", stocksArray, JSONObject.toJSON(shopStockGoodsList));
                throw new ApiBizException(ErrorCode.E00000024.CODE, "商品信息校验不通过", param);
            }
        }

        // 保存货架遗损表信息
        ShopStockLose shopStockLose = new ShopStockLose();
        shopStockLose.setLoseNo(DateUtil.getCurrentTime());// 记录编号
        shopStockLose.setShopId(shopId);// 货架id
        shopStockLose.setCreateId(shopWechat.getUserId());// 创建人
        shopStockLose.setCreateTime(new Date());// 创建时间
        shopStockLose.setRemark("货架盘存");
        shopStockLoseMapper.insertSelective(shopStockLose);

        // 更新货架库存
        for (DispatchItemDomain dispatchGoods : shopStockGoodsList) {
            // 更新库存信息
            ShopStock shopStock = new ShopStock();
            shopStock.setId(dispatchGoods.getId());
            shopStock.setQuantity(stocks.get(dispatchGoods.getGoodsId()));// 商品ID
            log.info("{},{}", "更新库存信息", JSONObject.toJSON(shopStock));// 库存
            shopStockMapper.updateByPrimaryKeySelective(shopStock);

            // 保存盘存记录明细
            ShopStockLoseItem item = new ShopStockLoseItem();
            item.setLoseId(shopStockLose.getId());// 盘存记录id
            item.setGoodsId(dispatchGoods.getGoodsId());// 商品ID
            item.setGoodsOriginalQuantity(dispatchGoods.getQuantity());// 原始库存
            item.setGoodsNowQuantity(stocks.get(dispatchGoods.getGoodsId()));// 现有库存
            log.info("{},{}", "保存盘存记录信息", JSONObject.toJSON(item));// 库存
            shopStockLoseItemMapper.insertSelective(item);
        }
    }

}
