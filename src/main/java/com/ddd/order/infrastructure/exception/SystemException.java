package com.ddd.order.infrastructure.exception;

import com.google.common.collect.ImmutableMap;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-19 16:34
 */
public class SystemException extends AppException {
    public SystemException(Throwable cause) {
        super(ErrorCode.SYSTEM_ERROR, ImmutableMap.of("detail", cause.getMessage()), cause);
    }
}
