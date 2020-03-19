package com.ddd.order.infrastructure.exception;

import com.google.common.collect.Maps;

import java.time.Instant;
import java.util.Collections;
import java.util.Map;

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

    public ErrorDetail(ErrorCode code, int status, String message, String path, Instant timestamp) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.path = path;
        this.timestamp = timestamp;
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
