package com.ddd.order.infrastructure.exception;

import java.util.Map;

import static com.ddd.order.infrastructure.exception.ErrorCode.REQUEST_VALIDATION_FAILED;


/**
 * @author Mr.Yangxiufeng
 */
public class RequestValidationException extends AppException {
    public RequestValidationException(Map<String, Object> data) {
        super(REQUEST_VALIDATION_FAILED, data);
    }
}
