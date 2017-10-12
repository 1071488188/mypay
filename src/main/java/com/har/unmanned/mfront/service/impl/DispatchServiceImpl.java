package com.har.unmanned.mfront.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.har.bigdata.log.LogHelper;
import com.har.bigdata.log.LogType;
import com.har.unmanned.mfront.config.CodeConstants;
import com.har.unmanned.mfront.config.ErrorCode;
import com.har.unmanned.mfront.dao.DispatchMapper;
import com.har.unmanned.mfront.dao.ShopStockMapper;
import com.har.unmanned.mfront.dao.extend.DispatchItemQueryMapper;
import com.har.unmanned.mfront.dao.extend.DispatchQueryMapper;
import com.har.unmanned.mfront.dao.extend.ShopStockExtendMapper;
import com.har.unmanned.mfront.dao.extend.SysUserMapperExtend;
import com.har.unmanned.mfront.exception.ApiBizException;
import com.har.unmanned.mfront.model.Dispatch;
import com.har.unmanned.mfront.model.DispatchExample;
import com.har.unmanned.mfront.model.ShopStock;
import com.har.unmanned.mfront.model.ShopWechat;
import com.har.unmanned.mfront.model.extend.DispatchDomain;
import com.har.unmanned.mfront.model.extend.DispatchItemDomain;
import com.har.unmanned.mfront.service.DispatchService;
import com.har.unmanned.mfront.utils.CheckUtil;
import com.har.unmanned.mfront.utils.DateUtil;
import com.har.unmanned.mfront.utils.PageUtil;
import com.har.unmanned.mfront.utils.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huangjj
 * @create 2017-09-19 16:01
 **/
@Slf4j
@Service
public class DispatchServiceImpl implements DispatchService {
    @Autowired
    UserUtil userUtil;
    @Autowired
    private DispatchQueryMapper dispatchQueryMapper;
    @Autowired
    private SysUserMapperExtend sysUserMapperExtend;
    @Autowired
    private DispatchMapper dispatchMapper;
    @Autowired
    private DispatchItemQueryMapper dispatchItemQueryMapper;
    @Autowired
    private ShopStockMapper shopStockMapper;
    @Autowired
    private ShopStockExtendMapper shopStockExtendMapper;

    @Value("${har.picPath}")
    String basePicPath;

    /***
     * 检查用户授权
     * @return
     */
    private ShopWechat checkUserAuth() throws ApiBizException {
        ShopWechat wechatUser = userUtil.userInfo();

        //验证用户权限
        int userNum = sysUserMapperExtend.queryUserRole(wechatUser.getUserId().toString(), CodeConstants.Role.DISPATCHOR);
        if (userNum <= 0) {
            log.info("{},{}", "用户权限不足", JSONObject.toJSON(wechatUser));
            throw new ApiBizException(ErrorCode.E00000015.CODE, ErrorCode.E00000015.MSG, JSONObject.toJSON(wechatUser));
        }

        return wechatUser;
    }

    /**
     * 检查配送单状态
     *
     * @param dispatchNo 配送单单号
     * @return 配送单状态
     */
    private Integer getDispatchStatus(String dispatchNo) {
        Integer status = -1;

        // 检查配送单状态
        DispatchExample example = new DispatchExample();
        DispatchExample.Criteria criteria = example.or();
        criteria.andDispatchNoEqualTo(dispatchNo);
        List<Dispatch> dispatchesList = dispatchMapper.selectByExample(example);

        if (!dispatchesList.isEmpty() && dispatchesList.size() > 0) {
            status = dispatchesList.get(0).getStatus();
        }

        return status;
    }

    @Override
    public JSONObject dispatchList(JSONObject param) throws ApiBizException {
        log.info("{},{}", "配送中心列表传入参数", param);
        LogHelper.save(LogType.RECEIVE, "配送中心列表service传入参数", param);

        //返回参数
        JSONObject retData = new JSONObject();

        //############################请求数据#######################################
        // 数据状态
        String status = param.getString("status");
        // 页数
        int page = param.getInteger("page");
        // 条数
        int pageSize = param.getInteger("pageSize");
        //############################请求数据#######################################

        // 检查授权
        ShopWechat wechatUser = checkUserAuth();

        // 参数封装
        DispatchDomain dispatchDomain = new DispatchDomain();
        dispatchDomain.setOpenid(wechatUser.getOpenid());
        dispatchDomain.setStatusString(status);
        // 配送单总数
        int count = dispatchQueryMapper.dispatchCount(dispatchDomain);
        retData.put("totalCount", count);// 总条数
        log.info("{},{},{}", "用户配送单总数", wechatUser.getOpenid(), count);
        LogHelper.save(LogType.RECEIVE, "用户配送单总数", count);

        JSONArray dataList = new JSONArray();
        if (count > 0) {
            // 分页请求参数
            PageUtil.startPage(page, pageSize);
            List<DispatchDomain> dispatchList = dispatchQueryMapper.dispatchList(dispatchDomain);
            for (DispatchDomain item : dispatchList) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("createTime", DateUtil.convertDateToStr(item.getCreateTime()));// 创建时间
                jsonObject.put("storeAddress", item.getStoreAddress());//取货位置
                jsonObject.put("shopAddress", item.getShopAddress());//货架位置
                jsonObject.put("status", item.getStatus());//状态
                jsonObject.put("dispatchNo", item.getDispatchNo());//配送单号

                //取货单
                JSONArray carrierNote = new JSONArray();
                for (DispatchItemDomain itemDomain : item.getCarrierNote()) {
                    JSONObject carrierNoteJson = new JSONObject();
                    carrierNoteJson.put("goodsName", itemDomain.getGoodsName());//商品名称
                    carrierNoteJson.put("quantity", itemDomain.getStockQuantity());//商品数量
                    carrierNote.add(carrierNoteJson);
                }
                jsonObject.put("carrierNote", carrierNote);//取货单
                dataList.add(jsonObject);
            }
        }
        retData.put("dataList", dataList);// 记录列表
        log.info("{},{},{}", "用户配送单明细", wechatUser.getOpenid(), dataList);
        LogHelper.save(LogType.RECEIVE, "用户配送单明细", dataList);
        return retData;
    }

    @Override
    public void updateDispatchStatus(JSONObject param) throws ApiBizException {
        log.info("{},{}", "更新配送单状态传入数据", param);
        LogHelper.save(LogType.RECEIVE, "更新配送单状态传入数据", param);

        //############################请求数据#######################################
        // 配送单
        String dispatchNo = param.getString("dispatchNo");
        // 数据状态
        Integer status = param.getInteger("status");
        //############################请求数据#######################################

        // 当前状态
        Integer nowState = getDispatchStatus(dispatchNo);

        if (nowState == CodeConstants.DispatchStatus.PENDING_DELIVERY) {
            if (status != CodeConstants.DispatchStatus.IN_DELIVERY)
                throw new ApiBizException(ErrorCode.E00000024.CODE, "该配送单暂不能操作", param);
        } else if (nowState == CodeConstants.DispatchStatus.IN_DELIVERY) {
            if (status != CodeConstants.DispatchStatus.SHELVES)
                throw new ApiBizException(ErrorCode.E00000024.CODE, "该配送单暂不能操作", param);
        }

        DispatchExample dispatchExample = new DispatchExample();
        DispatchExample.Criteria criteria = dispatchExample.or();
        criteria.andDispatchNoEqualTo(dispatchNo);//配送单号

        Dispatch dispatch = new Dispatch();
        dispatch.setStatus(status);//配送单状态

        dispatchMapper.updateByExampleSelective(dispatch, dispatchExample);

        log.info("{},{},{}", "配送单更新完成", dispatchNo, status);
        LogHelper.save(LogType.RECEIVE, "配送单更新完成", param);
    }

    @Override
    public JSONObject replenishmentList(JSONObject param) throws ApiBizException {
        log.info("{},{}", "补货列表传入数据", param);
        LogHelper.save(LogType.RECEIVE, "补货列表传入参数", param);
        //############################请求数据#######################################
        // 配送单
        String dispatchNo = param.getString("dispatchNo");
        // 是否存在侧边栏
        boolean layerByZero = false;
        //############################请求数据#######################################
        // 返回参数
        JSONObject respParam = new JSONObject();
        // 检查授权
        checkUserAuth();

        // 配送单商品列表
        List<DispatchItemDomain> dispatchGoodsList = dispatchItemQueryMapper.dispatchGoodsList(dispatchNo);
        log.info("{},{}", "配送单商品列表", JSONObject.toJSON(dispatchGoodsList));
        LogHelper.save(LogType.RECEIVE, "配送单商品列表", JSONObject.toJSON(dispatchGoodsList));

        // 货架商品列表
        List<DispatchItemDomain> shopStockGoodsList = dispatchItemQueryMapper.shopStockGoodsList(dispatchNo);
        log.info("{},{}", "货架商品列表", JSONObject.toJSON(shopStockGoodsList));
        LogHelper.save(LogType.RECEIVE, "货架商品列表", JSONObject.toJSON(shopStockGoodsList));

        // 获取货架最大层数（+1）
        Integer maxLayer = dispatchItemQueryMapper.getMaxLayer(dispatchNo) + 1;

        Map<Long, DispatchItemDomain> map = new HashMap<>();
        for (DispatchItemDomain domain : shopStockGoodsList) {
            map.put(domain.getGoodsId(), domain);
        }

        // 货架商品存在，以货架商品摆放位置为准
        for (DispatchItemDomain dispatchGoods : dispatchGoodsList) {
            long goodsId = dispatchGoods.getGoodsId();
            int layer = dispatchGoods.getLayer();
            // 层数为0并且条件成立
            if (!layerByZero && layer == 0) {
                layerByZero = true;
            }

            if (map.get(goodsId) != null) {
                dispatchGoods.setLayer(map.get(goodsId).getLayer());
            }
        }

        // 货架商品列表
        JSONArray goodsArray = new JSONArray();
        // 配送单商品
        JSONObject disObj = new JSONObject();
        // 补货总数
        int replenishmentNum = 0;
        // 循环最小
        int forMinLayer = 1;
        if (layerByZero) forMinLayer = 0;

        // 分装货架商品分层
        for (int i = forMinLayer; i < maxLayer; i++) {
            JSONArray disArray = new JSONArray();
            for (DispatchItemDomain dispatchGoods : dispatchGoodsList) {
                if (CheckUtil.isEquals(dispatchGoods.getLayer().toString(), String.valueOf(i))) {
                    JSONObject goods = new JSONObject();
                    goods.put("goodsName", dispatchGoods.getGoodsName());//商品名称
                    goods.put("goodsValue", dispatchGoods.getPrice());//商品单价
                    goods.put("goodsPicture", basePicPath.concat(dispatchGoods.getGoodsPicture()));//商品图片
                    goods.put("goodsQuantity", dispatchGoods.getQuantity());//商品数量
                    goods.put("goodsId", dispatchGoods.getGoodsId());//商品ID
                    replenishmentNum += dispatchGoods.getQuantity();
                    disArray.add(goods);
                }
            }

            disObj.put("layer", i);
            disObj.put("goodsList", disArray);
            goodsArray.add(disObj);
            disObj = new JSONObject();
        }

        respParam.put("dataList", goodsArray);//商品列表
        respParam.put("replenishmentNum", replenishmentNum);//补货总数
        respParam.put("species", dispatchGoodsList.size());//种类
        return respParam;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, ApiBizException.class})
    public void confirmReplenishment(JSONObject param) throws ApiBizException {
        log.info("{},{}", "确认补货传入数据", param);
        LogHelper.save(LogType.RECEIVE, "确认补货传入参数", param);
        //############################请求数据#######################################
        // 配送单
        String dispatchNo = param.getString("dispatchNo");
        // 补货清单
        JSONArray goodsIdsArray = param.getJSONArray("goodsIds");
        //############################请求数据#######################################
        // 返回参数
        JSONObject respParam = new JSONObject();
        // 检查授权
        checkUserAuth();

        // 检查配送单状态
        Integer status = getDispatchStatus(dispatchNo);
        if (status != CodeConstants.DispatchStatus.IN_DELIVERY) {
            log.info("{},{},{}", "该配送单暂不能操作", param, status);
            throw new ApiBizException(ErrorCode.E00000024.CODE, "该配送单暂不能操作", param);
        }

        Map<Long, Long> goodsIds = new HashMap<>();
        for (int i = 0; i < goodsIdsArray.size(); i++) {
            Long goodsId = new Long(goodsIdsArray.get(i).toString());

            goodsIds.put(goodsId, goodsId);
        }

        // 配送单商品列表
        List<DispatchItemDomain> dispatchGoodsList = dispatchItemQueryMapper.dispatchGoodsList(dispatchNo);
        log.info("{},{}", "配送单商品列表", JSONObject.toJSON(dispatchGoodsList));
        LogHelper.save(LogType.RECEIVE, "配送单商品列表", JSONObject.toJSON(dispatchGoodsList));

        // 查询未确认的商品
        for (DispatchItemDomain domain : dispatchGoodsList) {
            if (CheckUtil.isNull(goodsIds.get(domain.getGoodsId()))) {
                log.info("{},{},{}", "提交的商品与配送单商品不匹配", goodsIdsArray, JSONObject.toJSON(dispatchGoodsList));
                throw new ApiBizException(ErrorCode.E00000024.CODE, "你还有商品未确认，请确认勾选后提交", param);
            }
        }

        // 配送单商品Map
        Map<Long, DispatchItemDomain> dispatchGoodsMap = new HashMap<>();
        for (DispatchItemDomain domain : dispatchGoodsList) {
            dispatchGoodsMap.put(domain.getGoodsId(), domain);
        }

        // 货架商品列表
        List<DispatchItemDomain> shopStockGoodsList = dispatchItemQueryMapper.shopStockGoodsList(dispatchNo);
        log.info("{},{}", "货架商品列表", JSONObject.toJSON(shopStockGoodsList));
        LogHelper.save(LogType.RECEIVE, "货架商品列表", JSONObject.toJSON(shopStockGoodsList));

        // 货架商品Map
        Map<Long, DispatchItemDomain> shopStockGoodsMap = new HashMap<>();
        for (DispatchItemDomain domain : shopStockGoodsList) {
            shopStockGoodsMap.put(domain.getGoodsId(), domain);
        }

        // 货架商品存在，以货架商品摆放位置为准
        for (DispatchItemDomain dispatchGoods : dispatchGoodsList) {
            long goodsId = dispatchGoods.getGoodsId();

            if (shopStockGoodsMap.get(goodsId) != null) {
                dispatchGoods.setLayer(shopStockGoodsMap.get(goodsId).getLayer());
            }
        }

        // 循环派送单，更新货架库存
        for (DispatchItemDomain dispatchGoods : dispatchGoodsList) {
            // 货架商品
            DispatchItemDomain shopStockGoods = shopStockGoodsMap.get(dispatchGoods.getGoodsId());
            ShopStock shopStock = new ShopStock();

            // 商品不在货架上，新增数据,否则去除货架库存，增加
            if (CheckUtil.isNull(shopStockGoods)) {
                shopStock.setShopId(dispatchGoods.getShopId());// 货架id
                shopStock.setGoodsId(dispatchGoods.getGoodsId());// 商品id
                shopStock.setQuantity(dispatchGoods.getQuantity());// 数量
                shopStock.setInitialQuantity(dispatchGoods.getQuantity());// 初始数量
                shopStock.setInitialTime(new Date());// 时间
                shopStock.setLayer(dispatchGoods.getLayer());// 摆放位置
                shopStock.setDispatchQuantity(0);// 配送中数量
                shopStock.setMinQuantity(dispatchGoods.getShopMinQuantity());// 最低库存
                shopStock.setShopBaseQuantity(dispatchGoods.getShopBaseQuantity());// 基准库存
                shopStock.setStatus(CodeConstants.ShopStockStatus.UP);// 状态

                shopStockMapper.insertSelective(shopStock);
            } else {
                // 更新库存信息
                shopStock.setId(shopStockGoods.getId());
                shopStock.setQuantity(dispatchGoods.getQuantity());
                shopStockExtendMapper.updateShopStock(shopStock);

                // 更新配送单信息
                JSONObject updateParam = new JSONObject();
                updateParam.put("dispatchNo", dispatchNo);
                updateParam.put("status", CodeConstants.DispatchStatus.SHELVES);
                updateDispatchStatus(updateParam);
            }
        }
    }
}
