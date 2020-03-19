package com.ddd.order.infrastructure.exception;

import com.ddd.order.infrastructure.log.RequestIdMdcFilter;
import org.slf4j.MDC;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-19 16:07
 */
public class ErrorRepresentation {
    private final String requestId;

    private final ErrorDetail error;

    private ErrorRepresentation(ErrorDetail error) {
        this.error = error;
        this.requestId = MDC.get(RequestIdMdcFilter.REQUEST_ID);
    }
    public static ErrorRepresentation from(ErrorDetail error) {
        return new ErrorRepresentation(error);
    }

    public String getRequestId() {
        return requestId;
    }

    public int status(){
        return error.getStatus();
    }

    public ErrorDetail getError() {
        return error;
    }
}
