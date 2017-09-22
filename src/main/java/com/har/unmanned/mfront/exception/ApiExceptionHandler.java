package com.har.unmanned.mfront.exception;


import com.alibaba.fastjson.JSONObject;
import com.har.bigdata.exception.CommonException;
import com.har.bigdata.log.LogHelper;
import com.har.unmanned.mfront.config.Constants;
import com.har.unmanned.mfront.config.ErrorCode;
import com.har.unmanned.mfront.utils.CheckUtil;
import com.har.unmanned.mfront.utils.ContextHolderUtils;
import com.har.unmanned.mfront.utils.RespMessage;
import com.har.unmanned.mfront.utils.WxAuthUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.net.URLEncoder;

/**
 * 对外API异常处理器
 *
 * @author tanzeng
 */
@Slf4j
@ControllerAdvice
@ResponseBody
@Order(2)
public class ApiExceptionHandler {
    @Autowired
    WxAuthUtil wxAuthUtil;

    /**
     * 所有异常报错
     *
     * @param request
     * @param ex
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    public String allExceptionHandler(HttpServletRequest request,
                                      Exception ex) throws Exception {
        RespMessage respMessage = new RespMessage();
        log.info("异常信息信息{}", ex);
        try {
            if (ex instanceof ApiBizException) {
                String errcode = ((ApiBizException) ex).getErrCode();
                respMessage.setRespCode(errcode);
                respMessage.setRespDesc(ex.getMessage());
                HttpServletRequest httpServletRequest = ContextHolderUtils.getRequest();
                //当为未授权的时候跳转拼装用户授权地址
                if (!CheckUtil.isNull(httpServletRequest) && ErrorCode.E00000006.CODE.equals(errcode)) {
                    String currenturl = httpServletRequest.getHeader(Constants.constantHead.CURRENTURL);
                    JSONObject jsonObject = new JSONObject();
                    String reduurl = wxAuthUtil.getAuthUrl(Constants.SCOPR_base, URLEncoder.encode(currenturl));
                    jsonObject.put("redirectUrl", reduurl);
                    respMessage.setData(jsonObject);
                }
                LogHelper.saveCommonException((CommonException) ex);
            } else {
                LogHelper.saveException(Constants.Topic, ex);
                respMessage.setRespCode(ErrorCode.E00000001.CODE);
                respMessage.setRespDesc(ErrorCode.E00000001.MSG);
            }
        } catch (Exception e) {
            LogHelper.saveException(Constants.Topic, e);
            e.printStackTrace();
            log.info("{}", e);
            respMessage.setRespCode(ErrorCode.E00000001.CODE);
            respMessage.setRespDesc(ErrorCode.E00000001.MSG);
            return respMessage.getRespMessage().toJSONString();
        }

        return respMessage.getRespMessage().toJSONString();
    }

}

