package com.har.unmanned.mfront.api.dispatch;

import com.alibaba.fastjson.JSONObject;

/**
 * 配送中心
 *
 * @author huangjj
 * @create 2017-09-19 9:50
 **/

public interface DispatchResource {
    /**
     * @api {get} /api/v1/dispatch/dispatchList 1.api 配送中心列表
     * @apiVersion 1.0.0
     * @apiName dispatch api 配送中心列表
     * @apiGroup dispatch
     * @apiPermission none
     * @apiDescription 配送中心列表
     * @apiParam {Number} status[] 	状态（0: 未接单、1: 已接单、2: 派送中、3: 已完成）
     * @apiSuccess (200) {String} RespCode          响应编码，8位
     * @apiSuccess (200) {String} RespDesc          响应描述
     * @apiSuccess (200) {Object[]} Data			响应数据
     * @apiSuccess (200) {String} Data.createTime		创建时间
     * @apiSuccess (200) {String} Data.storeName        取货位置
     * @apiSuccess (200) {String} Data.position       	配送位置
     * @apiSuccess (200) {String} Data.status       	状态（0: 未接单、1: 已接单、2: 派送中、3: 已完成）
     * @apiSuccess (200) {String} Data.dispatchNo       配送单号
     * @apiSuccess (200) {Object[]} Data.carrierNote    取货单
     * @apiSuccess (200) {String} Data.carrierNote.goodsName    商品名称
     * @apiSuccess (200) {String} Data.carrierNote.spec         商品规格
     * @apiSuccess (200) {Number} Data.carrierNote.quantity     商品数量
     */
    JSONObject dispatchList(JSONObject params);

    /**
     * @api {post} /api/v1/dispatch/updateDispatchStatus 2.api 更新配送单状态
     * @apiVersion 1.0.0
     * @apiName dispatch api 更新配送单状态
     * @apiGroup dispatch
     * @apiPermission none
     * @apiDescription 更新配送单状态
     * @apiParam {String} dispatchNo 	配送单号
     * @apiParam {Number} status 	    状态（0: 未接单、1: 已接单、2: 派送中、3: 已完成）
     * @apiSuccess (200) {String} RespCode          响应编码，8位
     * @apiSuccess (200) {String} RespDesc          响应描述
     */
    JSONObject updateDispatchStatus(JSONObject params);

    /**
     * @api {get} /api/v1/dispatch/replenishmentList 3.api 补货列表
     * @apiVersion 1.0.0
     * @apiName dispatch api 补货列表
     * @apiGroup dispatch
     * @apiPermission none
     * @apiDescription 补货列表
     * @apiParam {String} dispatchNo 	配送单号
     * @apiSuccess (200) {String} RespCode          响应编码，8位
     * @apiSuccess (200) {String} RespDesc          响应描述
     * @apiSuccess (200) {Object[]} Data			响应数据
     * @apiSuccess (200) {String} Data.replenishmentNum 补货总数
     * @apiSuccess (200) {Object[]} Data.layer		    层级(layer为：jsonObject的key)
     * @apiSuccess (200) {String} Data.layer.goodsName      商品名称
     * @apiSuccess (200) {String} Data.layer.goodsValue     商品单价
     * @apiSuccess (200) {String} Data.layer.goodsPicture   商品图片路径
     * @apiSuccess (200) {Number} Data.layer.goodsId        商品ID
     */
    JSONObject replenishmentList(JSONObject params);

    /**
     * @api {post} /api/v1/dispatch/confirmReplenishment 4.api 确认补货
     * @apiVersion 1.0.0
     * @apiName dispatch api 确认补货
     * @apiGroup dispatch
     * @apiPermission none
     * @apiDescription 确认补货
     * @apiParam {String} dispatchNo 	        配送单号
     * @apiParam {Object[]} Data.layer		    层级(layer为：jsonObject的key)
     * @apiParam {Number} Data.layer.goodsId        商品ID
     * @apiParam {Number} Data.layer.ifConfirm      是否确认(0：未确认 、1:已确认)
     * @apiSuccess (200) {String} RespCode          响应编码，8位
     * @apiSuccess (200) {String} RespDesc          响应描述
     */
    JSONObject confirmReplenishment(JSONObject params);
}
