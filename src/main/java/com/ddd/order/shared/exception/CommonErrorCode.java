package com.ddd.order.shared.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-19 13:42
 */
public enum CommonErrorCode implements ErrorCode{
    /**
     * 没有找到
     */
    NOT_FOUND(HttpStatus.NOT_FOUND.value(), "没有找到"),
    /**
     * 请求数据格式验证失败
     */
    REQUEST_VALIDATION_FAILED(HttpStatus.BAD_REQUEST.value(), "请求数据格式验证失败"),
    /**
     * 系统错误
     */
    SYSTEM_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "系统错误");

    private int status;

    private String message;

    CommonErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getCode() {
        return this.name();
    }

}
