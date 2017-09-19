package com.har.unmanned.mfront.exception;

import com.alibaba.fastjson.JSONObject;

import com.har.unmanned.mfront.config.ErrorCode;
import com.har.unmanned.mfront.utils.RespMessage;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

/**
 * @author jiangjj
 * @description:入参数据异常处理
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class CommonExceptionAdvice {


    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public JSONObject handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("缺少请求参数{}", e);
        RespMessage respMessage = new RespMessage();
        respMessage.setRespCode(ErrorCode.E00000001.CODE);
        respMessage.setRespDesc("缺少请求参数");
        return respMessage.getRespMessage();
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public JSONObject handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        RespMessage respMessage = new RespMessage();
        respMessage.setRespCode(ErrorCode.E00000001.CODE);
        respMessage.setRespDesc("数据输入错误，请检查填写内容");
        log.error("参数解析失败{}", e);
        return respMessage.getRespMessage();
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JSONObject handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("参数验证失败{}", e);
        RespMessage respMessage = new RespMessage();
        respMessage.setRespCode(ErrorCode.E00000001.CODE);
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String message = String.format("%s:%s", field, code);
        log.info("参数验证失败{}", message);
        respMessage.setRespDesc(code);
        return respMessage.getRespMessage();
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public JSONObject handleBindException(BindException e) {

        RespMessage respMessage = new RespMessage();
        respMessage.setRespCode(ErrorCode.E00000001.CODE);
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String message = String.format("%s:%s", field, code);
        log.error("参数绑定失败{}", message);
        respMessage.setRespDesc(code);
        return respMessage.getRespMessage();
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public JSONObject handleServiceException(ConstraintViolationException e) {
        RespMessage respMessage = new RespMessage();
        respMessage.setRespCode(ErrorCode.E00000001.CODE);
        log.error("参数验证失败{}", e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String message = violation.getMessage();
        respMessage.setRespDesc(message);
        return respMessage.getRespMessage();
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public JSONObject handleValidationException(ValidationException e) {
        RespMessage respMessage = new RespMessage();
        respMessage.setRespCode(ErrorCode.E00000001.CODE);
        log.error("参数验证失败{}", e);
        respMessage.setRespDesc("参数验证失败");
        return respMessage.getRespMessage();
    }

    @ExceptionHandler(MultipartException.class)
    public JSONObject handleAll(Throwable t) {
        RespMessage respMessage = new RespMessage();
        respMessage.setRespCode(ErrorCode.E00000001.CODE);
        log.error("上传文件过大{}", t);
        respMessage.setRespDesc("上传文件过大");
        return respMessage.getRespMessage();
    }

}