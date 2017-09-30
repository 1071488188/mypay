package com.har.unmanned.mfront.api.validate;

import com.alibaba.fastjson.JSONObject;

/**
 * 验证接口
 * @apiDefine vlidate 验证接口
 * @author huangjj
 * @create 2017-09-19 9:59
 **/

public interface ValidateResource {
    /**
     * @api {post} /api/v1/validation/sendValidate 1.api 发送短信验证码
     * @apiVersion 1.0.0
     * @apiName sendValidate
     * @apiGroup vlidate
     * @apiPermission none
     *
     * @apiDescription 发送短信验证码git
     *
     * @apiParam {String} cellPhoneNumber  	手机号
     *
     * @apiSuccess (200) {String} RespCode          响应编码，8位
     * @apiSuccess (200) {String} RespDesc          响应描述
     * */
    JSONObject sendValidateCode(InputParameter inputParameter) throws Exception;

    /**
     * @api {get} /api/v1/validation/permissionsValidation 2.api 初始化权限验证
     * @apiHeader {String} currenturl 当前url
     * @apiVersion 1.0.0
     * @apiName permissionsValidation
     * @apiGroup vlidate
     * @apiPermission none
     *
     * @apiDescription 当前用户权限验证
     *
     * @apiParam {String} roleType  	角色类型3、网点管理员4、配送员5、库存盘点
     *
     * @apiSuccess (200) {String} RespCode          响应编码，8位
     * @apiSuccess (200) {String} RespDesc          响应描述
     * @apiSuccess (200) {Object} Data			响应数据
     * @apiSuccess (200) {String} Data.roleType		 角色类型0、未绑定信息3、网点管理员4、配送员5、库存盘点
     */
    JSONObject permissionsValidation(InputParameter inputParameter) throws Exception;

    /**
     * @api {post} /api/v1/validation/bindingPhone 3.api 绑定手机号
     * @apiHeader {String} currenturl 当前url
     * @apiVersion 1.0.0
     * @apiName bindingPhone
     * @apiGroup vlidate
     * @apiPermission none
     *
     * @apiDescription 当前用户权限验证
     *
     * @apiParam {String} cellPhoneNumber  	手机号
     * @apiParam {String} verificationCode  	验证码
     * @apiParam {String} roleType          	角色类型3、网点管理员4、配送员5、库存盘点
     *
     * @apiSuccess (200) {String} RespCode          响应编码，8位
     * @apiSuccess (200) {String} RespDesc          响应描述
     * @apiSuccess (200) {Object} Data			响应数据
     * @apiSuccess (200) {String} Data.roleType		角色类型3、网点管理员4、配送员5、库存盘点
     */
    JSONObject bindingPhone(InputParameter inputParameter) throws Exception;

}
