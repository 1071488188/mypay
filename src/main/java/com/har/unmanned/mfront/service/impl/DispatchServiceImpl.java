package com.har.unmanned.mfront.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.har.bigdata.log.LogHelper;
import com.har.bigdata.log.LogType;
import com.har.unmanned.mfront.config.CodeConstants;
import com.har.unmanned.mfront.config.ErrorCode;
import com.har.unmanned.mfront.dao.extend.DispatchQueryMapper;
import com.har.unmanned.mfront.exception.ApiBizException;
import com.har.unmanned.mfront.model.ShopWechatExample;
import com.har.unmanned.mfront.model.extend.DispatchDomain;
import com.har.unmanned.mfront.model.extend.DispatchItemDomain;
import com.har.unmanned.mfront.service.DispatchService;
import com.har.unmanned.mfront.utils.CheckUtil;
import com.har.unmanned.mfront.utils.DateUtil;
import com.har.unmanned.mfront.utils.PageUtil;
import com.har.unmanned.mfront.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangjj
 * @create 2017-09-19 16:01
 **/
@Service
public class DispatchServiceImpl implements DispatchService{
    @Autowired
    UserUtil userUtil;
    @Autowired
  private  DispatchQueryMapper dispatchQueryMapper;

    @Override
    public JSONObject dispatchList(JSONObject param) throws Exception {
        LogHelper.save(LogType.RECEIVE, "配送中心列表service传入参数", param);
        //返回参数
        JSONObject respParam = new JSONObject();
        //验证用户权限
        if(CheckUtil.isNull(userUtil.userInfo().getRole()) || !CheckUtil.isEquals(CodeConstants.Role.DISPATCHOR.toString(),userUtil.userInfo().getRole().toString())){
            throw new ApiBizException(ErrorCode.E00000015.CODE,ErrorCode.E00000015.MSG,param);
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
                jsonObject.put("storeAddress",item.getStoreAddress());//取货位置
                jsonObject.put("shopAddress",item.getShopAddress());//货架位置
                jsonObject.put("status",item.getStatus());//状态
                //取货单
                JSONArray carrierNote = new JSONArray();
                for(DispatchItemDomain itemDomain : item.getCarrierNote()){
                    JSONObject carrierNoteJson = new JSONObject();
                    carrierNoteJson.put("goodsName",itemDomain.getGoodsName());//商品名称
                    carrierNoteJson.put("spec",itemDomain.getSpec());//商品规格
                    carrierNoteJson.put("quantity",itemDomain.getQuantity());//商品数量
                    carrierNote.add(carrierNoteJson);
                }
                jsonObject.put("carrierNote",carrierNote);//取货单
                dataList.add(jsonObject);
            }
        }
        respParam.put("data", dataList);// 记录列表
        return respParam;
    }

    @Override
    public void updateDispatchStatus(JSONObject param) throws Exception {

    }

    @Override
    public JSONObject replenishmentList(JSONObject param) throws Exception {
        return null;
    }

    @Override
    public void confirmReplenishment(JSONObject param) throws Exception {

    }
}
