package com.ddd.order.infrastructure.exception;

import com.ddd.order.shared.exception.AppException;
import com.ddd.order.shared.exception.CommonErrorCode;
import com.google.common.collect.ImmutableMap;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-27 13:49
 */
public class OrderNotFoundException extends AppException {
    private static final long serialVersionUID = 1L;

    public OrderNotFoundException(String orderId) {
        super(CommonErrorCode.NOT_FOUND, ImmutableMap.of("orderId",orderId));
    }
}
