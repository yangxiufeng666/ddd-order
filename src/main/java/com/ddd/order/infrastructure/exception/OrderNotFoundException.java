package com.ddd.order.infrastructure.exception;

import com.google.common.collect.ImmutableMap;
import org.ddd.shared.web.exception.AppException;
import org.ddd.shared.web.exception.CommonErrorCode;

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
