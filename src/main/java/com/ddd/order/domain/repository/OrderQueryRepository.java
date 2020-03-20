package com.ddd.order.domain.repository;

import com.ddd.order.domain.entity.Order;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-19 17:56
 */
public interface OrderQueryRepository {
    Order withItemById(String orderId);
}
