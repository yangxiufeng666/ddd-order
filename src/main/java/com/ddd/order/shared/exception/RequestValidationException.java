package com.ddd.order.shared.exception;

import java.util.Map;

import static com.ddd.order.shared.exception.CommonErrorCode.REQUEST_VALIDATION_FAILED;


/**
 * @author Mr.Yangxiufeng
 */
public class RequestValidationException extends AppException {
    private static final long serialVersionUID = 1L;

    public RequestValidationException(Map<String, Object> data) {
        super(REQUEST_VALIDATION_FAILED, data);
    }
}
