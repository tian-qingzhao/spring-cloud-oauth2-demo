package com.tqz.oauth2.gateway.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.time.ZonedDateTime;

/**
 * <p>返回实体类
 *
 * @author tianqingzhao
 * @since 2022/8/27 17:00
 */
public class Result<T> {
    
    public static final String SUCCESSFUL_CODE = "000000";
    
    public static final String SUCCESSFUL_MESG = "处理成功";
    
    private String code;
    
    private String mesg;
    
    private Instant time;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
    
    public Result() {
        this.time = ZonedDateTime.now().toInstant();
    }
    
    /**
     * @param errorType
     */
    public Result(SystemErrorType errorType) {
        this.code = errorType.getCode();
        this.mesg = errorType.getMesg();
        this.time = ZonedDateTime.now().toInstant();
    }
    
    /**
     * @param errorType
     * @param data
     */
    public Result(SystemErrorType errorType, T data) {
        this(errorType);
        this.data = data;
    }
    
    /**
     * 内部使用，用于构造成功的结果
     *
     * @param code
     * @param mesg
     * @param data
     */
    private Result(String code, String mesg, T data) {
        this.code = code;
        this.mesg = mesg;
        this.data = data;
        this.time = ZonedDateTime.now().toInstant();
    }
    
    /**
     * 快速创建成功结果并返回结果数据
     *
     * @param data
     * @return Result
     */
    public static Result success(Object data) {
        return new Result<>(SUCCESSFUL_CODE, SUCCESSFUL_MESG, data);
    }
    
    /**
     * 快速创建成功结果
     *
     * @return Result
     */
    public static Result success() {
        return success(null);
    }
    
    /**
     * 系统异常类没有返回数据
     *
     * @return Result
     */
    public static Result fail() {
        return new Result(SystemErrorType.SYSTEM_ERROR);
    }
    
    
    /**
     * 系统异常类并返回结果数据
     *
     * @param errorType
     * @param data
     * @return Result
     */
    public static Result fail(SystemErrorType errorType, Object data) {
        return new Result<>(errorType, data);
    }
    
    public static Result fail(String code, String msg) {
        return new Result(code, msg, null);
    }
    
    /**
     * 系统异常类并返回结果数据
     *
     * @param errorType
     * @return Result
     */
    public static Result fail(SystemErrorType errorType) {
        return Result.fail(errorType, null);
    }
    
    /**
     * 系统异常类并返回结果数据
     *
     * @param data
     * @return Result
     */
    public static Result fail(Object data) {
        return new Result<>(SystemErrorType.SYSTEM_ERROR, data);
    }
    
    
    /**
     * 成功code=000000
     *
     * @return true/false
     */
    @JsonIgnore
    public boolean isSuccess() {
        return SUCCESSFUL_CODE.equals(this.code);
    }
    
    /**
     * 失败
     *
     * @return true/false
     */
    @JsonIgnore
    public boolean isFail() {
        return !isSuccess();
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getMesg() {
        return mesg;
    }
    
    public void setMesg(String mesg) {
        this.mesg = mesg;
    }
    
    public Instant getTime() {
        return time;
    }
    
    public void setTime(Instant time) {
        this.time = time;
    }
    
    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
}
