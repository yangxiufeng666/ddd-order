package com.ddd.order.infrastructure.exception;

import com.google.common.collect.Maps;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.Collections;
import java.util.Map;

import static java.time.Instant.now;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-19 13:44
 */
public class ErrorDetail {
    private final ErrorCode code;
    private final int status;
    private final String message;
    private final String path;
    private final Instant timestamp;
    private final Map<String, Object> data = Maps.newHashMap();

    private ErrorDetail(AppException ex, String path) {
        this.code = ex.getCode();
        this.status = ex.httpStatus();
        this.message = ex.userMessage();
        this.path = path;
        this.timestamp = now();
        if (!StringUtils.isEmpty(ex.getData())) {
            this.data.putAll(ex.getData());
        }
    }

    public ErrorDetail(ErrorCode code, int status, String message, String path, Instant timestamp) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.path = path;
        this.timestamp = timestamp;
    }

    public static ErrorDetail from(AppException ex, String path) {
        return new ErrorDetail(ex, path);
    }

    public ErrorCode getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Map<String, Object> getData() {
        return Collections.unmodifiableMap(data);
    }
}
