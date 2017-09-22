package com.har.unmanned.mfront.api.smsvalidate;

import com.alibaba.fastjson.JSONObject;

/**
 * 短信管理
 * @apiDefine smsValidate 短信管理
 * @author huangjj
 * @create 2017-09-19 9:59
 **/

public interface SmsValidateResource {
    /**
     * @api {post} /api/v1/smsValidate/sendValidate 1.api 发送短信验证码
     * @apiVersion 1.0.0
     * @apiName sendValidate
     * @apiGroup smsValidate
     * @apiPermission none
     *
     * @apiDescription 发送短信验证码git
     *
     * @apiParam {String} mobile  	手机号
     *
     * @apiSuccess (200) {String} RespCode          响应编码，8位
     * @apiSuccess (200) {String} RespDesc          响应描述
     * */
    JSONObject sendValidate(InputParameter inputParameter) throws Exception;


}
