package com.har.unmanned.mfront.api.administrator;

import com.alibaba.fastjson.JSONObject;

/**
 * 管理员接口
 * @apiDefine admin 管理员接口
 * Created by jiang on 2017/9/19.
 */
public interface AdministratorResource {
    /**
     * @api {get} /api/v1/admin/adminInit 1.api 是否为网点管理员初始化接口
     * @apiHeader {String} currenturl 当前url
     * @apiVersion 1.0.0
     * @apiName adminInit
     * @apiGroup admin
     * @apiPermission none
     *
     * @apiDescription 是否为网点管理员初始化接口
     *
     * @apiSuccess (200) {String} RespCode          响应编码，8位
     * @apiSuccess (200) {String} RespDesc          响应描述
     * @apiSuccess (200) {Object} Data
     * @apiSuccess (200) {Number} Data.whetherNetwork 是否为网点管理员，0、否1、是
     */
    String adminInit()throws Exception;
//    /**
//     * @api {post} /api/v1/admin/bindManager 2.api 绑定手机号
//     * @apiHeader {String} currenturl 当前url
//     * @apiVersion 1.0.0
//     * @apiName bindManager
//     * @apiGroup admin
//     * @apiPermission none
//     *
//     * @apiDescription 绑定手机号
//     *
//     * @apiParam {String} cellPhoneNumber  手机号
//     * @apiParam {String} verificationCode  验证码
//     *
//     * @apiSuccess (200) {String} RespCode          响应编码，8位
//     * @apiSuccess (200) {String} RespDesc          响应描述
//     * @apiSuccess (200) {Object} Data
//     */
//    String bindManager(InputParameter inputParameter) throws Exception;

    /**
     * @api {get} /api/v1/admin/expenseCalendar 3.api 消费记录
     * @apiHeader {String} currenturl 当前url
     * @apiVersion 1.0.0
     * @apiName expenseCalendar
     * @apiGroup admin
     * @apiPermission none
     *
     * @apiDescription 消费记录
     *
     * @apiParam {String} page  页码
     * @apiParam {String} pageSize  每页显示条数
     *
     * @apiSuccess (200) {String} RespCode          响应编码，8位
     * @apiSuccess (200) {String} RespDesc          响应描述
     * @apiSuccess (200) {Object} Data			响应数据
     * @apiSuccess (200) {String} Data.theNumberOfConsumer		    消费人数
     * @apiSuccess (200) {Object[]} Data.particulars          	    列表
     * @apiSuccess (200) {String} Data.particulars.headimgUrl      头像
     * @apiSuccess (200) {String} Data.particulars.name           	昵称
     * @apiSuccess (200) {String} Data.particulars.amountZh   累计消费
     */
    String expenseCalendar(InputParameter inputParameter)throws Exception;

    /**
     * @api {get} /api/v1/admin/settlementRecords 4.api 结算记录
     * @apiHeader {String} currenturl 当前url
     * @apiVersion 1.0.0
     * @apiName settlementRecords
     * @apiGroup admin
     * @apiPermission none
     *
     * @apiDescription 结算记录
     *
     * @apiParam {String} page  页码
     * @apiParam {String} pageSize  每页显示条数
     *
     * @apiSuccess (200) {String} RespCode          响应编码，8位
     * @apiSuccess (200) {String} RespDesc          响应描述
     * @apiSuccess (200) {Object} Data			响应数据
     * @apiSuccess (200) {Number} Data.totalCount			总条数
     * @apiSuccess (200) {Object[]} Data.totalList		列表
     * @apiSuccess (200) {String} Data.totalList.id
     * @apiSuccess (200) {String} Data.totalList.time		    时间
     * @apiSuccess (200) {String} Data.totalList.amountZh		营业额
     * @apiSuccess (200) {String} Data.totalList.ratio		    当前返佣比例
     * @apiSuccess (200) {String} Data.totalList.commissionZh		佣金已结算
     * @apiSuccess (200) {String} Data.totalList.status		结算状态0、待结算1、已结算
     */
    String settlementRecords(InputParameter inputParameter)throws Exception;

    /**
     * @api {post} /api/v1/admin/closeAnAccount 5.api 结算
     * @apiHeader {String} currenturl 当前url
     * @apiVersion 1.0.0
     * @apiName closeAnAccount
     * @apiGroup admin
     * @apiPermission none
     *
     * @apiDescription 结算
     *
     * @apiParam {String} billingId  结算记录id
     *
     * @apiSuccess (200) {String} RespCode          响应编码，8位
     * @apiSuccess (200) {String} RespDesc          响应描述
     * @apiSuccess (200) {Object} Data
     */
    String closeAnAccount(InputParameter inputParameter)throws Exception;

    /**
     * @api {get} /api/v1/admin/balanceDetails 6.api 余额明细
     * @apiHeader {String} currenturl 当前url
     * @apiVersion 1.0.0
     * @apiName balanceDetails
     * @apiGroup admin
     * @apiPermission none
     *
     * @apiDescription 余额明细
     *
     * @apiParam {String} page  页码
     * @apiParam {String} pageSize  每页显示条数
     *
     * @apiSuccess (200) {String} RespCode          响应编码，8位
     * @apiSuccess (200) {String} RespDesc          响应描述
     * @apiSuccess (200) {Object} Data			响应数据
     * @apiSuccess (200) {String} Data.shopAccountMoneyZh		  账户余额
     * @apiSuccess (200) {Number} Data.totalCount			总条数
     * @apiSuccess (200) {Object[]} Data.totalList		响应数据
     * @apiSuccess (200) {String} Data.totalList.applyTimeZh		  时间
     * @apiSuccess (200) {String} Data.totalList.typeZh		      明细名称
     * @apiSuccess (200) {String} Data.totalList.moneyZh		      金额
     */
    String balanceDetails(InputParameter inputParameter)throws Exception;

    /**
     * @api {post} /api/v1/admin/withdrawDeposit 7.api 提现
     * @apiHeader {String} currenturl 当前url
     * @apiVersion 1.0.0
     * @apiName withdrawDeposit
     * @apiGroup admin
     * @apiPermission none
     *
     * @apiDescription 提现
     *
     * @apiParam {String} reflectTheAmountOf  提现金额
     *
     * @apiSuccess (200) {String} RespCode          响应编码，8位
     * @apiSuccess (200) {String} RespDesc          响应描述
     * @apiSuccess (200) {Object} Data
     */
    String withdrawDeposit(InputParameter inputParameter)throws Exception;
}
