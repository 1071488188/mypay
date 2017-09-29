package com.har.unmanned.mfront.api.wxUser;

import com.alibaba.fastjson.JSONObject;
import com.har.unmanned.mfront.exception.ApiBizException;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;


/**
 * 微商城
 * @apiDefine wxUserShop 微商城
 * @author cc
 * @create 2017-09-20 15:36
 **/
public interface WxUserShopResource {
    /**
     * @api {get} /api/v1/wxUserShop 1.api 微商城首页
     * @apiVersion 1.0.0
     * @apiName wxUserShop
     * @apiGroup wxUserShop
     * @apiPermission none
     *
     * @apiDescription 微商城首页
     *
     * @apiParam {String} shopCode 	货架编号
     *
     * @apiSuccess (200) {String} RespCode          响应编码，8位
     * @apiSuccess (200) {String} RespDesc          响应描述
     * @apiSuccess (200) {Object} Data			响应数据
     * @apiSuccess (200) {String} Data.shopCode		货架编号
     * @apiSuccess (200) {Object[]} Data.recentPurchaseList   最近购买的集合(两个)
     * @apiSuccess (200) {Number} Data.recentPurchaseList.goodsId    商品id
     * @apiSuccess (200) {String} Data.recentPurchaseList.name   商品名称
     * @apiSuccess (200) {String} Data.recentPurchaseList.image   商品图片
     * @apiSuccess (200) {Number} Data.recentPurchaseList.price   商品单价(元)
     * @apiSuccess (200) {Number} Data.recentPurchaseList.quantity   商品库存数量
     * @apiSuccess (200) {Number} Data.recentPurchaseList.layer   商品摆放位置
     * @apiSuccess (200) {Number} Data.recentPurchaseList.status   商品状态(0: 已售完, 1: 未售完)
     * @apiSuccess (200) {Object} Data.dataList   数据的集合, {'1':[], '2':[]}这种结构
     * @apiSuccess (200) {Number} Data.dataList.layer   货架的层级(1, 2, 3, 4, 5)
     * @apiSuccess (200) {Object[]} Data.dataList.goodsList  每一层所对应的商品
     * @apiSuccess (200) {Number} Data.dataList.goodsList.goodsId    商品id
     * @apiSuccess (200) {String} Data.dataList.goodsList.name   商品名称
     * @apiSuccess (200) {String} Data.dataList.goodsList.image   商品图片
     * @apiSuccess (200) {Number} Data.dataList.goodsList.price   商品单价(元)
     * @apiSuccess (200) {Number} Data.dataList.goodsList.quantity   商品库存数量
     * */
    JSONObject wxUserShop(InputParameter inputParameter) throws Exception;

    /**
     * @api {post} /api/v1/wxUserShop/submitOrder 2.api 提交订单
     * @apiVersion 1.0.0
     * @apiName submitOrder
     * @apiGroup wxUserShop
     * @apiPermission none
     *
     * @apiDescription 提交订单
     *
     * @apiParam {String} shopCode 货架编号
     * @apiParam {Object[]} goodsList 	所购买的商品的集合
     * @apiParam {String} goodsList.goodsId 	商品id
     * @apiParam {Number} goodsList.goodsNum 商品的个数
     *
     * @apiSuccess (200) {String} RespCode      响应编码，8位
     * @apiSuccess (200) {String} RespDesc      响应描述
     * @apiSuccess (200) {Object} Data			响应数据
     * @apiSuccess (200) {String} Data.orderNo  订单号
     * @apiSuccess (200) {Object} Data.singData	支付签名数据
     * @apiSuccess (200) {String} Data.singData.appId	公众号id
     * @apiSuccess (200) {String} Data.singData.timeStamp	时间戳
     * @apiSuccess (200) {String} Data.singData.nonceStr	随机字符串
     * @apiSuccess (200) {String} Data.singData.package	订单详情扩展字符串
     * @apiSuccess (200) {String} Data.singData.signType	签名方式
     * @apiSuccess (200) {String} Data.singData.paySign	签名
     * */
    JSONObject submitOrder(InputParameter inputParameter) throws Exception;

    /**
     * @api {get} /api/v1/wxUserShop/buyRecord 3.api 购买记录
     * @apiVersion 1.0.0
     * @apiName buyRecord
     * @apiGroup wxUserShop
     * @apiPermission none
     *
     * @apiDescription 用户的消费记录
     *
     * @apiParam {String} shopCode 货架编号
     *
     * @apiSuccess (200) {String} RespCode      响应编码，8位
     * @apiSuccess (200) {String} RespDesc      响应描述
     * @apiSuccess (200) {Object} Data			响应数据
     * @apiSuccess (200) {Object[]} Data.dataList	响应集合
     * @apiSuccess (200) {String} Data.dataList.orderNo	订单号
     * @apiSuccess (200) {String} Data.dataList.amount	订单金额
     * @apiSuccess (200) {String} Data.dataList.payTimeString	  消费时间(YYYY.MM.dd hh:mm:ss)
     * @apiSuccess (200) {Object[]} Data.dataList.goodsList	      购买的商品集合
     * @apiSuccess (200) {String} Data.dataList.goodsList.name    商品名称
     * @apiSuccess (200) {String} Data.dataList.goodsList.image   商品图片
     * @apiSuccess (200) {Number} Data.dataList.goodsList.price   商品单价(元)
     * @apiSuccess (200) {Number} Data.dataList.goodsList.quantity   商品的购买数量
     * */
    JSONObject buyRecord(InputParameter inputParameter) throws Exception;

    /**
     * @api {get} /api/v1/wxUserShop/userInfo 4.api 用户信息
     * @apiVersion 1.0.0
     * @apiName userInfo
     * @apiGroup wxUserShop
     * @apiPermission none
     *
     * @apiDescription 用户基本信息
     *
     * @apiSuccess (200) {String} RespCode      响应编码，8位
     * @apiSuccess (200) {String} RespDesc      响应描述
     * @apiSuccess (200) {Object} Data			响应数据
     * @apiSuccess (200) {String} Data.name	用户昵称
     * @apiSuccess (200) {String} Data.headimgUrl 用户头像
     * @apiSuccess (200) {String} Data.shopCode 货架编号
     * */
    JSONObject userInfo() throws Exception;

    /**
     * @api {post} /api/v1/wxUserShop/getWxSign 5.api 获取微信签名
     * @apiVersion 1.0.0
     * @apiName getWxSign
     * @apiGroup wxUserShop
     * @apiPermission none
     *
     * @apiDescription 获取微信签名
     *
     * @apiSuccess (200) {String} RespCode          响应编码，8位
     * @apiSuccess (200) {String} RespDesc          响应描述
     * @apiSuccess (200) {Object} Data			响应数据
     * @apiSuccess (200) {String} Data.appId        公众号的唯一标识
     * @apiSuccess (200) {String} Data.nonceStr     随机串
     * @apiSuccess (200) {String} Data.timestamp    时间戳
     * @apiSuccess (200) {String} Data.signature    签名
     * */
    JSONObject getWxSign(HttpServletRequest request) throws Exception;
}
