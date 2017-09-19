package com.har.unmanned.mfront.api.demo;

import com.alibaba.fastjson.JSONObject;

public interface WxShopResource {
    /**
     * @api {get} /api/v1/wxShop 1.微商城首页
     * @apiVersion 1.0.0
     * @apiName wxShop
     * @apiGroup wxShop
     * @apiPermission none
     *
     * @apiDescription 普通用户进入微商城首页
     *
     * @apiParam {String} shopCode 	货架编号
     *
     * @apiSuccess (200) {String} RespCode          响应编码，8位
     * @apiSuccess (200) {String} RespDesc          响应描述
     * @apiSuccess (200) {Object} Data			响应数据
     * @apiSuccess (200) {String} Data.shopCode		货架编号
     * @apiSuccess (200) {Object[]} Data.recentPurchaseList   最近购买的集合(两个)
     * @apiSuccess (200) {Number} Data.recentPurchaseList.id    商品id
     * @apiSuccess (200) {String} Data.recentPurchaseList.name   商品名称
     * @apiSuccess (200) {String} Data.recentPurchaseList.image   商品图片
     * @apiSuccess (200) {Number} Data.recentPurchaseList.price   商品单价(元)
     * @apiSuccess (200) {Number} Data.recentPurchaseList.quantity   商品库存数量
     * @apiSuccess (200) {Number} Data.recentPurchaseList.layer   商品摆放位置
     * @apiSuccess (200) {Object[]} Data.dataList   数据的集合
     * @apiSuccess (200) {Number} Data.dataList.layer   货架的层级(1, 2, 3, 4, 5)
     * @apiSuccess (200) {Object[]} Data.dataList.goodsList  每一层所对应的商品
     * @apiSuccess (200) {Number} Data.dataList.goodsList.id    商品id
     * @apiSuccess (200) {String} Data.dataList.goodsList.name   商品名称
     * @apiSuccess (200) {String} Data.dataList.goodsList.image   商品图片
     * @apiSuccess (200) {Number} Data.dataList.goodsList.price   商品单价(元)
     * @apiSuccess (200) {Number} Data.dataList.goodsList.quantity   商品库存数量
     * */
    JSONObject wxShop(JSONObject params);

    /**
     * @api {post} /api/v1/wxShop/submitOrder 2.提交订单
     * @apiVersion 1.0.0
     * @apiName submitOrder
     * @apiGroup wxShop
     * @apiPermission none
     *
     * @apiDescription 用户购买商品, 提交订单
     *
     * @apiParam {String} shopCode 货架编号
     * @apiParam {Object[]} goodsList 	所购买的商品的集合
     * @apiParam {String} goodsList.id 	商品id
     * @apiParam {Number} goodsList.num 商品的个数
     *
     * @apiSuccess (200) {String} RespCode      响应编码，8位
     * @apiSuccess (200) {String} RespDesc      响应描述
     * @apiSuccess (200) {Object} Data			响应数据
     * @apiSuccess (200) {String} Data.orderNo  订单号
     * @apiSuccess (200) {String} Data.singData	支付签名数据
     * */
    JSONObject submitOrder(JSONObject params);

    /**
     * @api {get} /api/v1/wxShop/buyRecord 3.购买记录
     * @apiVersion 1.0.0
     * @apiName buyRecord
     * @apiGroup wxShop
     * @apiPermission none
     *
     * @apiDescription 用户的消费记录
     *
     * @apiSuccess (200) {String} RespCode      响应编码，8位
     * @apiSuccess (200) {String} RespDesc      响应描述
     * @apiSuccess (200) {Object} Data			响应数据
     * @apiSuccess (200) {Object[]} Data.dataList	响应集合
     * @apiSuccess (200) {String} Data.dataList.orderNo	订单号
     * @apiSuccess (200) {String} Data.dataList.amount	订单金额
     * @apiSuccess (200) {String} Data.dataList.orderTimeString	  消费时间(YYYY.MM.dd hh:mm:ss)
     * @apiSuccess (200) {Object[]} Data.dataList.goodsList	      购买的商品集合
     * @apiSuccess (200) {String} Data.dataList.goodsList.name    商品名称
     * @apiSuccess (200) {String} Data.dataList.goodsList.image   商品图片
     * @apiSuccess (200) {Number} Data.dataList.goodsList.price   商品单价(元)
     * @apiSuccess (200) {Number} Data.dataList.goodsList.quantity   商品的购买数量
     * */
    JSONObject buyRecord(JSONObject params);
}
