package com.har.unmanned.mfront.api.administrator;

/**
 * 管理员接口
 * @apiDefine admin 管理员接口
 * Created by jiang on 2017/9/19.
 */
public interface AdministratorResource {
    /**
     * @api {post} /api/v1/admin/bindManager 1.api 绑定手机号
     * @apiVersion 1.0.0
     * @apiName bindManager
     * @apiGroup admin
     * @apiPermission none
     *
     * @apiDescription 绑定手机号
     *
     * @apiParam {String} cellPhoneNumber  手机号
     * @apiParam {String} verificationCode  验证码
     *
     * @apiSuccess (200) {String} RespCode          响应编码，8位
     * @apiSuccess (200) {String} RespDesc          响应描述
     * @apiSuccess (200) {Object} Data
     */
    String bindManager(InputParameter inputParameter);

    /**
     * @api {get} /api/v1/admin/expenseCalendar 2.api 消费记录
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
     * @apiSuccess (200) {String} Data.particulars.theCumulative   累计消费
     */
    String expenseCalendar(InputParameter inputParameter);

    /**
     * @api {get} /api/v1/admin/settlementRecords 3.api 结算记录
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
     * @apiSuccess (200) {String} Data.id
     * @apiSuccess (200) {String} Data.time		    时间
     * @apiSuccess (200) {String} Data.commission		营业额
     * @apiSuccess (200) {String} Data.ratio		    当前返佣比例
     * @apiSuccess (200) {String} Data.commission		佣金已结算
     * @apiSuccess (200) {String} Data.status		结算状态2、已结算3、待结算
     */
    String settlementRecords(InputParameter inputParameter);

    /**
     * @api {post} /api/v1/admin/closeAnAccount 4.api 结算
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
    String closeAnAccount(InputParameter inputParameter);

    /**
     * @api {get} /api/v1/admin/balanceDetails 5.api 余额明细
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
     * @apiSuccess (200) {Object[]} Data			响应数据
     * @apiSuccess (200) {String} Data.applyTimeZh		  时间
     * @apiSuccess (200) {String} Data.typeZh		      明细名称
     * @apiSuccess (200) {String} Data.money		      金额
     */
    String balanceDetails(InputParameter inputParameter);

    /**
     * @api {post} /api/v1/admin/withdrawDeposit 6.api 提现
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
    String withdrawDeposit(InputParameter inputParameter);
}
