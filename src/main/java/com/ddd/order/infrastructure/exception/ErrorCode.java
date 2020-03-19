package com.ddd.order.infrastructure.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-19 13:42
 */
public enum  ErrorCode {
    /**
     * 没有找到订单
     */
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "没有找到订单"),
    /**
     * 没有找到产品
     */
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "没有找到产品"),
    /**
     * 请求数据格式验证失败
     */
    REQUEST_VALIDATION_FAILED(HttpStatus.BAD_REQUEST.value(), "请求数据格式验证失败"),
    /**
     * 系统错误
     */
    SYSTEM_ERROR(INTERNAL_SERVER_ERROR.value(), "系统错误");

    private int status;

    private String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
