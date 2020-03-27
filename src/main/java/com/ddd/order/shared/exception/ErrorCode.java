package com.ddd.order.shared.exception;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-27 13:39
 */
public interface ErrorCode {
    int getStatus();

    String getMessage();

    String getCode();
}
