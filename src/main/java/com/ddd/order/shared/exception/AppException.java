package com.ddd.order.shared.exception;

import org.springframework.util.StringUtils;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
import static java.util.Collections.unmodifiableMap;

/**
 * Base exception class for all business exceptions
 * @author Mr.Yangxiufeng
 */
public abstract class AppException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final ErrorCode code;
    private final Map<String, Object> data = newHashMap();

    protected AppException(ErrorCode code, Map<String, Object> data) {
        super(format(code, data));
        this.code = code;
        if (!StringUtils.isEmpty(data)) {
            this.data.putAll(data);
        }
    }

    protected AppException(ErrorCode code, Map<String, Object> data, Throwable cause) {
        super(format(code, data), cause);
        this.code = code;
        if (!StringUtils.isEmpty(data)) {
            this.data.putAll(data);
        }
    }

    private static String format(ErrorCode errorCode, Map<String, Object> data) {
        return String.format("[%s]%s:%s.", errorCode.toString(), errorCode.getMessage(), StringUtils.isEmpty(data) ? "" : data.toString());
    }

    public ErrorCode getCode() {
        return code;
    }

    public Map<String, Object> getData() {
        return unmodifiableMap(data);
    }

    public int httpStatus() {
        return code.getStatus();
    }

    public String userMessage() {
        return code.getMessage();
    }

}
