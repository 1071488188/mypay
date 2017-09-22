package com.har.unmanned.mfront.api.dispatch;

import com.alibaba.fastjson.JSONObject;
import com.har.unmanned.mfront.exception.ApiBizException;

/**
 * 配送中心
 * @apiDefine dispatch 配送中心接口
 * @author huangjj
 * @create 2017-09-19 9:50
 **/

public interface DispatchResource {
    /**
     * @api {get} /api/v1/dispatch/dispatchList 1.api 配送中心列表
     * @apiVersion 1.0.0
     * @apiName dispatchList
     * @apiGroup dispatch
     * @apiPermission none
     *
     * @apiDescription 配送中心列表
     *
     * @apiParam {String} status 	状态以逗号分隔（0: 未接单、1: 已接单、2: 派送中、3: 已完成）
     * @apiParam {Number} page=1        当前页数
     * @apiParam {Number} pageSize=10   每页显示条数
     *
     * @apiSuccess (200) {String} RespCode          响应编码，8位
     * @apiSuccess (200) {String} RespDesc          响应描述
     * @apiSuccess (200) {Object} Data			响应数据
     * @apiSuccess (200) {String} Data.totalCount        总数
     * @apiSuccess (200) {Object[]} Data.dataList        数据集合
     * @apiSuccess (200) {String} Data.dataList.createTime		创建时间
     * @apiSuccess (200) {String} Data.dataList.storeAddress     取货位置
     * @apiSuccess (200) {String} Data.dataList.shopAddress      货架位置
     * @apiSuccess (200) {String} Data.dataList.status       	状态（0: 未接单、1: 已接单、2: 派送中、3: 已完成）
     * @apiSuccess (200) {String} Data.dataList.dispatchNo       配送单号
     * @apiSuccess (200) {Object[]} Data.dataList.carrierNote    取货单
     * @apiSuccess (200) {String} Data.dataList.carrierNote.goodsName    商品名称
     * @apiSuccess (200) {String} Data.dataList.carrierNote.spec         商品规格
     * @apiSuccess (200) {Number} Data.dataList.carrierNote.quantity     商品数量
     */
    JSONObject dispatchList(InputParameter inputParameter) throws ApiBizException;

    /**
     * @api {post} /api/v1/dispatch/updateDispatchStatus 2.api 更新配送单状态
     * @apiVersion 1.0.0
     * @apiName updateDispatchStatus
     * @apiGroup dispatch
     * @apiPermission none
     *
     * @apiDescription 更新配送单状态
     *
     * @apiParam {String} dispatchNo 	配送单号
     * @apiParam {Number} status 	    状态（0: 未接单、1: 已接单、2: 派送中、3: 已完成）
     *
     * @apiSuccess (200) {String} RespCode          响应编码，8位
     * @apiSuccess (200) {String} RespDesc          响应描述
     */
    JSONObject updateDispatchStatus(JSONObject params) throws ApiBizException;

    /**
     * @api {get} /api/v1/dispatch/replenishmentList 3.api 补货列表
     * @apiVersion 1.0.0
     * @apiName replenishmentList
     * @apiGroup dispatch
     * @apiPermission none
     *
     * @apiDescription 补货列表
     *
     * @apiParam {String} dispatchNo 	配送单号
     *
     * @apiSuccess (200) {String} RespCode          响应编码，8位
     * @apiSuccess (200) {String} RespDesc          响应描述
     * @apiSuccess (200) {Object[]} Data			响应数据
     * @apiSuccess (200) {Number} Data.replenishmentNum 补货总数
     * @apiSuccess (200) {Number} Data.species 种类
     * @apiSuccess (200) {Object[]} Data.goods		    层级(layer为：jsonObject的key)
     * @apiSuccess (200) {String} Data.goods.layer.goodsName      商品名称
     * @apiSuccess (200) {String} Data.goods.layer.goodsValue     商品单价
     * @apiSuccess (200) {String} Data.goods.layer.goodsPicture   商品图片路径
     * @apiSuccess (200) {Number} Data.goods.layer.goodsQuantity  商品数量
     * @apiSuccess (200) {Number} Data.goods.layer.goodsId        商品ID
     */
    JSONObject replenishmentList(JSONObject params) throws ApiBizException;

    /**
     * @api {post} /api/v1/dispatch/confirmReplenishment 4.api 确认补货
     * @apiVersion 1.0.0
     * @apiName confirmReplenishment
     * @apiGroup dispatch
     * @apiPermission none
     *
     * @apiDescription 确认补货
     *
     * @apiParam {String} dispatchNo 	        配送单号
     * @apiParam {Object[]} goodsIds	            商品ID(已确认商品)
     *
     * @apiSuccess (200) {String} RespCode          响应编码，8位
     * @apiSuccess (200) {String} RespDesc          响应描述
     */
    JSONObject confirmReplenishment(JSONObject params) throws ApiBizException;

    /**
     * @api {post} /api/v1/dispatch/bindUser 5.api 用户绑定
     * @apiVersion 1.0.0
     * @apiName validateCode api 验证短信验证码
     * @apiGroup dispatch
     * @apiPermission none
     *
     * @apiDescription 验证短信验证码
     *
     * @apiParam {String} mobile  	    手机号
     * @apiParam {String} validateCode  验证码
     *
     * @apiSuccess (200) {String} RespCode          响应编码，8位
     * @apiSuccess (200) {String} RespDesc          响应描述
     * */
    JSONObject validateCode(InputParameter inputParameter) throws ApiBizException;

}
