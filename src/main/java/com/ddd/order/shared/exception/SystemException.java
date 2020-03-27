package com.ddd.order.shared.exception;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-19 16:34
 */
public class SystemException extends AppException {
    private static final long serialVersionUID = 1L;
    Map<String, Object> map = Maps.newHashMap();
    public SystemException(Throwable cause) {
        super(CommonErrorCode.SYSTEM_ERROR, (Map<String, Object>) Maps.newHashMap().put("detail", cause.getMessage()), cause);
    }
}
