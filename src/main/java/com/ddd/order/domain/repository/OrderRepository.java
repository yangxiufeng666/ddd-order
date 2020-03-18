package com.ddd.order.domain.repository;

import com.ddd.order.domain.entity.Order;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-18 16:01
 */
public interface OrderRepository {
    void save(Order order);
}
