package com.har.unmanned.mfront.api.demo;


import com.alibaba.fastjson.JSONObject;

/**
 *  api Demo测试
 * @author Administrator
 *
 */
public interface DemoResource {

    /**
     * @api {post} /api/v1/demo/test 1.api Demo测试
     * @apiVersion 1.0.0
     * @apiName Demo api Demo测试
     * @apiGroup Demo
     * @apiPermission none
     *
     * @apiDescription Demo测试
     *
     * @apiParam {Number} price 	价格
     * @apiParam {String} date  	时间
     * @apiParam {String} province  地区
     *
     * @apiSuccess (200) {String} RespCode          响应编码，8位
     * @apiSuccess (200) {String} RespDesc          响应描述
     * @apiSuccess (200) {Object[]} Data			响应数据
     * @apiSuccess (200) {String} Data.id				ID
     * @apiSuccess (200) {String} Data.title          	标题
     * @apiSuccess (200) {String} Data.realType       	类型
     * @apiSuccess (200) {String} Data.date           	公告时间
     * @apiSuccess (200) {String} Data.province       	行政区域
     * */
    Object demoTest(JSONObject params);


}
