package com.har.unmanned.mfront.api.lose;

import com.alibaba.fastjson.JSONObject;
import com.har.bigdata.exception.CommonExceptionLevel;
import com.har.bigdata.log.LogHelper;
import com.har.bigdata.log.LogType;
import com.har.unmanned.mfront.api.lose.ValidGroup.LoseGroup;
import com.har.unmanned.mfront.config.ErrorCode;
import com.har.unmanned.mfront.exception.ApiBizException;
import com.har.unmanned.mfront.service.LoseService;
import com.har.unmanned.mfront.utils.CheckUtil;
import com.har.unmanned.mfront.utils.RespMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhanggr
 * @create 2017-09-28
 **/
@Slf4j
@RestController
@RequestMapping(value = "/api/v1/lose", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class LoseResourceImpl implements LoseResource {
    @Autowired
    LoseService loseService;

    @Override
    @GetMapping("/shopStock")
    public JSONObject shopStock(@Validated({LoseGroup.class}) InputParameter inputParameter) throws ApiBizException {
        log.info("{},{}", "货架库存传入参数", JSONObject.toJSON(inputParameter));
        LogHelper.save(LogType.RECEIVE, "货架库存传入参数", JSONObject.toJSON(inputParameter));
        // 返回消息
        RespMessage respMessage = new RespMessage();

        try {
            // 请求参数
            JSONObject reqParam = new JSONObject();
            reqParam.put("shopCode", inputParameter.getShopCode());

            JSONObject retJson = loseService.shopStock(reqParam);
            respMessage.setData(retJson);
        } catch (ApiBizException e) {
            e.printStackTrace();
            log.error("{},{}", "货架库存错误", JSONObject.toJSON(e.getObject()));
            throw new ApiBizException(e.getErrCode(), e.getMessage(), JSONObject.toJSON(e.getObject()), CommonExceptionLevel.COMMONEXCEPTION);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("{},{}", "货架库存错误", JSONObject.toJSON(inputParameter));
            throw new ApiBizException(ErrorCode.E00000027.CODE, ErrorCode.E00000027.MSG, JSONObject.toJSON(inputParameter), CommonExceptionLevel.WARN);
        }

        log.info("{},{}", "货架库存返回数据", respMessage.getRespMessage());
        LogHelper.save(LogType.RECEIVE, "货架库存返回数据", respMessage.getRespMessage());
        return respMessage.getRespMessage();
    }

    @Override
    @PostMapping("/confirmStockLose")
    public JSONObject confirmStockLose(@RequestBody JSONObject reqParam) throws ApiBizException {
        log.info("{},{}", "货架盘存传入参数", reqParam);
        LogHelper.save(LogType.RECEIVE, "货架盘存传入参数", reqParam);
        // 返回消息
        RespMessage respMessage = new RespMessage();

        try {
            if (CheckUtil.isNull(reqParam.getString("shopCode")) ||
                    CheckUtil.isNull(reqParam.getString("stocks"))) {
                log.info("{},{},{}", "货架盘存", "参数不全", reqParam);
                throw new ApiBizException(ErrorCode.E00000012.CODE, ErrorCode.E00000012.MSG, reqParam, CommonExceptionLevel.COMMONEXCEPTION);
            }

            loseService.stockLose(reqParam);
        } catch (ApiBizException e) {
            e.printStackTrace();
            log.error("{},{}", "货架盘存错误", JSONObject.toJSON(e.getObject()));
            throw new ApiBizException(e.getErrCode(), e.getMessage(), JSONObject.toJSON(e.getObject()), CommonExceptionLevel.COMMONEXCEPTION);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("{},{}", "货架盘存错误", reqParam);
            throw new ApiBizException(ErrorCode.E00000028.CODE, ErrorCode.E00000028.MSG, reqParam, CommonExceptionLevel.WARN);
        }

        log.info("{},{}", "货架盘存返回数据", respMessage.getRespMessage());
        LogHelper.save(LogType.RECEIVE, "货架盘存返回数据", respMessage.getRespMessage());
        return respMessage.getRespMessage();
    }

}
