package com.har.unmanned.mfront.api.lose;

import com.alibaba.fastjson.JSONObject;
import com.har.unmanned.mfront.exception.ApiBizException;

/**
 * 货架遗损（盘存）
 * @apiDefine lose 货架遗损
 * @author zhanggr
 * @create 2017-09-28
 **/
public interface LoseResource {

    /**
     * @api {get} /api/v1/lose/shopStock 1.api 货架商品
     * @apiVersion 1.0.0
     * @apiName shopStock
     * @apiGroup lose
     * @apiPermission none
     *
     * @apiDescription 货架商品列表
     *
     * @apiParam {String} shopCode 	货架编号
     *
     * @apiSuccess (200) {String} RespCode          响应编码，8位
     * @apiSuccess (200) {String} RespDesc          响应描述
     * @apiSuccess (200) {Object[]} Data			响应数据
     * @apiSuccess (200) {Object[]} Data.goods		    层级为key，value为每层商品集合
     * @apiSuccess (200) {String} Data.goods.layer.goodsName      商品名称
     * @apiSuccess (200) {String} Data.goods.layer.goodsValue     商品单价
     * @apiSuccess (200) {String} Data.goods.layer.goodsPicture   商品图片路径
     * @apiSuccess (200) {Number} Data.goods.layer.goodsQuantity  商品数量
     * @apiSuccess (200) {Number} Data.goods.layer.goodsId        商品ID
     */
    JSONObject shopStock(InputParameter inputParameter) throws Exception;

    /**
     * @api {post} /api/v1/lose/confirmStockLose 2.api 确认货架库存
     * @apiVersion 1.0.0
     * @apiName confirmStockLose
     * @apiGroup lose
     * @apiPermission none
     *
     * @apiDescription 货架盘存
     *
     * @apiParam {String} shopCode 	        货架编号
     * @apiParam {Object[]} stocks	        货架盘存集合
     * @apiParam {Number} stocks.goodsId 	商品ID
     * @apiParam {Number} stocks.goodsNum   商品个数
     *
     * @apiSuccess (200) {String} RespCode          响应编码，8位
     * @apiSuccess (200) {String} RespDesc          响应描述
     */
    JSONObject confirmStockLose(JSONObject param) throws ApiBizException;

}
