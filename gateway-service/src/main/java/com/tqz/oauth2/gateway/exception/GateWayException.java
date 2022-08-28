package com.tqz.oauth2.gateway.exception;

import com.tqz.oauth2.gateway.entity.SystemErrorType;

/**
 * <p>网关异常类
 *
 * @author tianqingzhao
 * @since 2022/8/27 17:00
 */
public class GateWayException extends RuntimeException {
    
    private String code;
    
    private String msg;
    
    public GateWayException() {
    }
    
    public GateWayException(SystemErrorType errorType) {
        this.code = errorType.getCode();
        this.msg = errorType.getMesg();
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
