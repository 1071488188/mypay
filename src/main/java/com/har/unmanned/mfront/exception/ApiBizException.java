package com.har.unmanned.mfront.exception;

import com.har.bigdata.exception.CommonException;

public class ApiBizException extends CommonException {
	
	private static final long serialVersionUID = -2338126733954948240L;
	
	/**
     * 异常
     * @param msg 错误信息
     * @param object 相关对象
     */
    public ApiBizException(String msg, Object object) {
        super(msg, object);
    }

	/**
     * 异常
     * @param errCode 错误代码
     * @param msg     错误信息
     * @param object  相关对象
     */
    public ApiBizException(String errCode, String msg, Object object) {
        super(errCode, msg, object);
    }


    /**
     * 异常
     * @param msg     错误信息
     * @param object  相关对象
     * @param commonExceptionLevel  警告/错误
     */
    public ApiBizException(String msg, Object object, int commonExceptionLevel) {
        super(msg, object, commonExceptionLevel);
    }
    
    /**
     * 异常
     * @param errCode 错误代码
     * @param msg     错误信息
     * @param object  相关对象
     * @param commonExceptionLevel  警告/错误
     */
    public ApiBizException(String errCode, String msg, Object object, 
    		int commonExceptionLevel) {
        super(errCode, msg, object, commonExceptionLevel);
    }
}
