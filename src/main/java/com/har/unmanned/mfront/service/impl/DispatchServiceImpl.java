package com.har.unmanned.mfront.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.har.bigdata.log.LogHelper;
import com.har.bigdata.log.LogType;
import com.har.unmanned.mfront.config.CodeConstants;
import com.har.unmanned.mfront.config.ErrorCode;
import com.har.unmanned.mfront.dao.DispatchMapper;
import com.har.unmanned.mfront.dao.extend.DispatchItemQueryMapper;
import com.har.unmanned.mfront.dao.extend.DispatchQueryMapper;
import com.har.unmanned.mfront.dao.extend.SysUserMapperExtend;
import com.har.unmanned.mfront.exception.ApiBizException;
import com.har.unmanned.mfront.model.Dispatch;
import com.har.unmanned.mfront.model.DispatchExample;
import com.har.unmanned.mfront.model.ShopStock;
import com.har.unmanned.mfront.model.extend.DispatchDomain;
import com.har.unmanned.mfront.model.extend.DispatchItemDomain;
import com.har.unmanned.mfront.service.DispatchService;
import com.har.unmanned.mfront.utils.CheckUtil;
import com.har.unmanned.mfront.utils.DateUtil;
import com.har.unmanned.mfront.utils.PageUtil;
import com.har.unmanned.mfront.utils.UserUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author huangjj
 * @create 2017-09-19 16:01
 **/
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

    @Override
    public JSONObject dispatchList(JSONObject param) throws Exception {
        LogHelper.save(LogType.RECEIVE, "配送中心列表service传入参数", param);
        //返回参数
        JSONObject respParam = new JSONObject();
        //验证用户权限
        if (this.sysUserMapperExtend.queryUserRole(userUtil.userInfo().getUserId().toString(), CodeConstants.Role.DISPATCHOR) <= 0) {
            throw new ApiBizException(ErrorCode.E00000015.CODE, ErrorCode.E00000015.MSG, param);
        }
        //参数封装
        DispatchDomain dispatchDomain = new DispatchDomain();
        dispatchDomain.setOpenid(userUtil.userInfo().getOpenid());
        dispatchDomain.setStatusString(param.getString("status"));
        // 提现流水总数
        int count = this.dispatchQueryMapper.dispatchCount(dispatchDomain);
        respParam.put("totalCount", count);// 总条数
        JSONArray dataList = new JSONArray();
        if (count > 0) {
            // 分页请求参数
            PageUtil.startPage(param.getInteger("page"), param.getInteger("pageSize"));
            List<DispatchDomain> dispatchList = this.dispatchQueryMapper.dispatchList(dispatchDomain);
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
                    carrierNoteJson.put("spec", itemDomain.getSpec());//商品规格
                    carrierNoteJson.put("quantity", itemDomain.getQuantity());//商品数量
                    carrierNote.add(carrierNoteJson);
                }
                jsonObject.put("carrierNote", carrierNote);//取货单
                dataList.add(jsonObject);
            }
        }
        respParam.put("data", dataList);// 记录列表
        return respParam;
    }

    @Override
    public void updateDispatchStatus(JSONObject param) throws Exception {
        LogHelper.save(LogType.RECEIVE, "更新配送单状态service传入参数", param);
        //返回参数
        JSONObject respParam = new JSONObject();
        //验证用户权限
        if (this.sysUserMapperExtend.queryUserRole(userUtil.userInfo().getUserId().toString(), CodeConstants.Role.DISPATCHOR) <= 0) {
            throw new ApiBizException(ErrorCode.E00000015.CODE, ErrorCode.E00000015.MSG, param);
        }
        DispatchExample dispatchExample = new DispatchExample();
        DispatchExample.Criteria criteria = dispatchExample.or();
        criteria.andDispatchNoEqualTo(param.getString("dispatchNo"));//配送单号
        Dispatch dispatch = new Dispatch();
        dispatch.setStatus(param.getInteger("status"));//配送单状态

        this.dispatchMapper.updateByExampleSelective(dispatch, dispatchExample);
    }

    @Override
    public JSONObject replenishmentList(JSONObject param) throws Exception {
        LogHelper.save(LogType.RECEIVE, "补货列表service传入参数", param);
        // 返回参数
        JSONObject respParam = new JSONObject();
        // 验证用户权限
        if (this.sysUserMapperExtend.queryUserRole(userUtil.userInfo().getUserId().toString(), CodeConstants.Role.DISPATCHOR) <= 0) {
            throw new ApiBizException(ErrorCode.E00000015.CODE, ErrorCode.E00000015.MSG, param);
        }
        //配送单商品列表
        List<DispatchItemDomain> dispatchGoodsList = this.dispatchItemQueryMapper.dispatchGoodsList(param.getString("dispatchNo"));
        //货架商品列表
        List<DispatchItemDomain> shopStockGoodsList = this.dispatchItemQueryMapper.shopStockGoodsList(param.getString("dispatchNo"));
        //货架商品存在，以货架商品摆放位置为准
        for (DispatchItemDomain dispatchGoods : dispatchGoodsList) {
            for (DispatchItemDomain shopGoods : shopStockGoodsList) {
                if (CheckUtil.isEquals(dispatchGoods.getGoodsId().toString(), shopGoods.getGoodsId().toString())) {
                    dispatchGoods.setLayer(shopGoods.getLayer());
                }
            }
        }
        JSONObject goodsJson = new JSONObject();
        //补货总数
        int replenishmentNum = 0;
        //分装货架商品分层
        if (!dispatchGoodsList.isEmpty()) {
            for (int i = 1; i <= dispatchGoodsList.get(dispatchGoodsList.size() - 1).getLayer(); i++) {
                JSONArray goodsArray = new JSONArray();
                for (DispatchItemDomain dispatchGoods : dispatchGoodsList) {
                    if (CheckUtil.isEquals(dispatchGoods.getLayer().toString(), String.valueOf(i))) {
                        JSONObject goods = new JSONObject();
                        goods.put("goodsName", dispatchGoods.getGoodsName());//商品名称
                        goods.put("goodsValue", dispatchGoods.getPrice());//商品单价
                        goods.put("goodsPicture", dispatchGoods.getGoodsPicture());//商品图片
                        goods.put("goodsQuantity", dispatchGoods.getQuantity());//商品数量
                        goods.put("goodsId", dispatchGoods.getGoodsId());//商品ID
                        goodsArray.add(goods);
                        replenishmentNum += dispatchGoods.getQuantity();
                    }
                }
                goodsJson.put(String.valueOf(i), goodsArray);
            }
        }
        respParam.put("goods", goodsJson);//商品列表
        respParam.put("replenishmentNum", replenishmentNum);//补货总数
        respParam.put("species", dispatchGoodsList.size());//种类
        return respParam;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, ApiBizException.class})
    public void confirmReplenishment(JSONObject param) throws Exception, ApiBizException {
        LogHelper.save(LogType.RECEIVE, "确认补货service传入参数", param);
        // 返回参数
        JSONObject respParam = new JSONObject();
        // 验证用户权限
        if (this.sysUserMapperExtend.queryUserRole(userUtil.userInfo().getUserId().toString(), CodeConstants.Role.DISPATCHOR) <= 0) {
            throw new ApiBizException(ErrorCode.E00000015.CODE, ErrorCode.E00000015.MSG, param);
        }
        //查询未确认的商品
        String goodsIds = StringUtils.join(param.getJSONArray("goodsIds"), ",");
        List<DispatchItemDomain> notConfirGoods = this.dispatchItemQueryMapper.notConfirGoods(param.getString("dispatchNo"), goodsIds);
        if (!notConfirGoods.isEmpty()) {
            throw new ApiBizException(ErrorCode.E00000001.CODE, notConfirGoods.get(0).getGoodsName() + "未确认，请确认!", param);
        }
        //配送单商品列表
        List<DispatchItemDomain> dispatchGoodsList = this.dispatchItemQueryMapper.dispatchGoodsList(param.getString("dispatchNo"));
        //货架商品列表
        List<DispatchItemDomain> shopStockGoodsList = this.dispatchItemQueryMapper.shopStockGoodsList(param.getString("dispatchNo"));
        //更新货架库存
        for (DispatchItemDomain dispatchGoods : dispatchGoodsList) {
            //货架商品存在则更新，否则新增货架库存
            if (shopStockGoodsList.contains(dispatchGoods.getGoodsId())) {
                //更新货架库存
                ShopStock shopStock = new ShopStock();
                for (DispatchItemDomain shopStockGoods : shopStockGoodsList) {
                    if (CheckUtil.isEquals(dispatchGoods.getGoodsId().toString(), shopStockGoods.getGoodsId().toString())) {

                    }
                }

            } else {
                //新增货架库存

            }
        }
    }

    @Override
    public JSONObject validateCode(JSONObject param) throws Exception {
        return null;
    }
}
