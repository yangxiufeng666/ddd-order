package com.ddd.order.shared.exception;

import com.ddd.order.shared.Response;
import com.google.common.collect.Maps;

import java.time.Instant;
import java.util.Map;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-19 16:07
 */
public class ErrorResponse extends Response {
    private static final long serialVersionUID = 1L;

    private String path;

    private Map<String, Object> data = Maps.newHashMap();

    private ErrorCode error;

    private final Instant timestamp;

    public ErrorResponse(AppException ex, String path) {
        super();
        this.error = ex.getCode();
        this.setErrCode(String.valueOf(error.getStatus()));
        this.setErrMessage(error.getMessage());
        this.setSuccess(false);
        this.path = path;
        this.data.putAll(ex.getData());
        this.timestamp = Instant.now();
    }

    public String getPath() {
        return path;
    }

    public Map<String, Object> getData() {
        return data;
    }
    public int httpStatus(){
        return error.getStatus();
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}
