package com.har.unmanned.mfront.api.dispatch;

import com.alibaba.fastjson.JSONObject;
import com.har.bigdata.exception.CommonExceptionLevel;
import com.har.bigdata.log.LogHelper;
import com.har.bigdata.log.LogType;
import com.har.unmanned.mfront.api.dispatch.validgroup.DispatchGroup;
import com.har.unmanned.mfront.api.dispatch.validgroup.PageGroup;
import com.har.unmanned.mfront.config.ErrorCode;
import com.har.unmanned.mfront.exception.ApiBizException;
import com.har.unmanned.mfront.service.DispatchService;
import com.har.unmanned.mfront.utils.CheckUtil;
import com.har.unmanned.mfront.utils.RespMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author huangjj
 * @create 2017-09-19 14:57
 **/
@Slf4j
@RestController
@RequestMapping(value = "/api/v1/dispatch", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class DispatchResourceImpl implements DispatchResource {
    @Autowired
    DispatchService dispatchService;

    @Override
    @GetMapping("/dispatchList")
    public JSONObject dispatchList(@Validated({PageGroup.class}) InputParameter inputParameter) throws ApiBizException {
        log.info("{},{}", "配送列表传入数据", JSONObject.toJSON(inputParameter));
        LogHelper.save(LogType.RECEIVE, "配送列表_开始", JSONObject.toJSON(inputParameter));
        // 返回消息
        RespMessage respMessage = new RespMessage();

        try {
            // 请求参数
            JSONObject reqParam = new JSONObject();
            reqParam.put("page", inputParameter.getPage());
            reqParam.put("pageSize", inputParameter.getPageSize());
            reqParam.put("status", inputParameter.getStatus());

            log.info("{},{}", "配送列表请求参数", reqParam);
            LogHelper.save(LogType.REQUEST, "配送列表列请求参数", reqParam);
            JSONObject retJson = dispatchService.dispatchList(reqParam);

            respMessage.setData(retJson);
        } catch (ApiBizException e) {
            e.printStackTrace();
            log.error("{},{}", "配送列表错误", JSONObject.toJSON(e.getObject()));
            throw new ApiBizException(e.getErrCode(), e.getMessage(), JSONObject.toJSON(e.getObject()));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("{},{}", "配送列表错误", JSONObject.toJSON(inputParameter));
            throw new ApiBizException(ErrorCode.E00000014.CODE, ErrorCode.E00000014.MSG, JSONObject.toJSON(inputParameter));
        }

        log.info("{},{}", "配送列表返回数据", respMessage.getRespMessage());
        LogHelper.save(LogType.RECEIVE, "配送列表返回数据", respMessage.getRespMessage());
        return respMessage.getRespMessage();
    }

//    @Override
    @PostMapping("/updateDispatchStatus")
    public JSONObject updateDispatchStatus(@RequestBody JSONObject reqParam) throws ApiBizException {
        log.info("{},{}", "更新配送单状态", reqParam);
        LogHelper.save(LogType.RECEIVE, "更新配送单状态", reqParam);
        // 返回消息
        RespMessage respMessage = new RespMessage();

        try {
            log.info("{},{}", "更新配送单状态请求参数", reqParam);
            LogHelper.save(LogType.REQUEST, "更新配送单状态请求参数", reqParam);

            if (CheckUtil.isNull(reqParam.getString("dispatchNo"))
                    || CheckUtil.isNull(reqParam.getString("status"))) {
                log.info("{},{},{}", "更新配送单状态", "参数不全", reqParam);
                throw new ApiBizException(ErrorCode.E00000012.CODE, ErrorCode.E00000012.MSG, reqParam, CommonExceptionLevel.COMMONEXCEPTION);
            }

//            dispatchService.updateDispatchStatus(reqParam);
        } catch (ApiBizException e) {
            e.printStackTrace();
            log.error("{},{}", "更新配送单状态错误", JSONObject.toJSON(e.getObject()));
            throw new ApiBizException(e.getErrCode(), e.getMessage(), JSONObject.toJSON(e.getObject()));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("{},{}", "更新配送单状态错误", reqParam);
            throw new ApiBizException(ErrorCode.E00000023.CODE, ErrorCode.E00000023.MSG, reqParam);
        }

        log.info("{},{}", "更新配送单状态返回数据", respMessage.getRespMessage());
        LogHelper.save(LogType.RECEIVE, "更新配送单状态返回数据", respMessage.getRespMessage());
        return respMessage.getRespMessage();
    }

    @Override
    @GetMapping("/replenishmentList")
    public JSONObject replenishmentList(@Validated({DispatchGroup.class}) InputParameter inputParameter) throws ApiBizException {
        log.info("{},{}", "补货列表传入参数", JSONObject.toJSON(inputParameter));
        LogHelper.save(LogType.RECEIVE, "补货列表传入参数", JSONObject.toJSON(inputParameter));
        // 返回消息
        RespMessage respMessage = new RespMessage();

        try {
            // 请求参数
            JSONObject reqParam = new JSONObject();
            reqParam.put("dispatchNo", inputParameter.getDispatchNo());

            log.info("{},{}", "补货列表请求参数", reqParam);
            LogHelper.save(LogType.REQUEST, "补货列表请求参数", reqParam);

            if (CheckUtil.isNull(reqParam.getString("dispatchNo"))) {
                log.info("{},{},{}", "补货列表", "参数不全", reqParam);
                throw new ApiBizException(ErrorCode.E00000012.CODE, ErrorCode.E00000012.MSG, reqParam, CommonExceptionLevel.COMMONEXCEPTION);
            }

            JSONObject retData = dispatchService.replenishmentList(reqParam);
            respMessage.setData(retData);
        } catch (ApiBizException e) {
            e.printStackTrace();
            log.error("{},{}", "补货列表错误", JSONObject.toJSON(e.getObject()));
            throw new ApiBizException(e.getErrCode(), e.getMessage(), JSONObject.toJSON(e.getObject()));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("{},{}", "补货列表错误", JSONObject.toJSON(inputParameter));
            throw new ApiBizException(ErrorCode.E00000014.CODE, ErrorCode.E00000014.MSG, JSONObject.toJSON(inputParameter));
        }

        log.info("{},{}", "补货列表返回数据", respMessage.getRespMessage());
        LogHelper.save(LogType.RECEIVE, "补货列表返回数据", respMessage.getRespMessage());
        return respMessage.getRespMessage();
    }

    @Override
    @PostMapping("/confirmReplenishment")
    public JSONObject confirmReplenishment(@RequestBody JSONObject reqParam) throws ApiBizException {
        log.info("{},{}", "确认补货传入参数", reqParam);
        LogHelper.save(LogType.RECEIVE, "确认补货传入参数", reqParam);
        // 返回消息
        RespMessage respMessage = new RespMessage();

        try {
            log.info("{},{}", "确认补货请求参数", reqParam);
            LogHelper.save(LogType.REQUEST, "确认补货请求参数", reqParam);

            if (CheckUtil.isNull(reqParam.getString("dispatchNo")) ||
                    CheckUtil.isNull(reqParam.getString("goodsIds"))) {
                log.info("{},{},{}", "确认补货", "参数不全", reqParam);
                throw new ApiBizException(ErrorCode.E00000012.CODE, ErrorCode.E00000012.MSG, reqParam, CommonExceptionLevel.COMMONEXCEPTION);
            }

            dispatchService.confirmReplenishment(reqParam);
        } catch (ApiBizException e) {
            e.printStackTrace();
            log.error("{},{}", "确认补货错误", JSONObject.toJSON(e.getObject()));
            throw new ApiBizException(e.getErrCode(), e.getMessage(), JSONObject.toJSON(e.getObject()));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("{},{}", "确认补货错误", reqParam);
            throw new ApiBizException(ErrorCode.E00000025.CODE, ErrorCode.E00000025.MSG, reqParam);
        }

        log.info("{},{}", "确认补货返回数据", respMessage.getRespMessage());
        LogHelper.save(LogType.RECEIVE, "确认补货返回数据", respMessage.getRespMessage());
        return respMessage.getRespMessage();
    }
}
