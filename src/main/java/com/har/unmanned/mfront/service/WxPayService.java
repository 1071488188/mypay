package com.har.unmanned.mfront.service;

import java.util.Map;

/**
 * @author cc
 * @create 2017/09/19 18:24
 **/
public abstract class WxPayService {
    /**
     * 微信支付统一下单(web)
     * @param openid 用户标识符
     * @param total_fee 订单总金额
     * @param body	商品描述
     * @param out_trade_no  商户订单号
     * @param ip  请求ip
     * @return
     * @throws Exception
     */
    public abstract Map<String, String> paymentOrderHbxWeb(String openid, String total_fee,String body,
                                                   String out_trade_no, String ip) throws Exception;

    /**
     * 查询订单(app)
     * @param out_trade_no  商户订单号
     * @return
     * @throws Exception
     */
    public abstract Map<String, String> orderquery(String out_trade_no) throws Exception;

}
