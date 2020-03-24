package com.ddd.order.domain.repository;

import com.ddd.order.domain.entity.Order;
import com.ddd.order.domain.entity.OrderItem;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-18 16:01
 */
public interface OrderRepository {
    void save(Order order);
    void saveItem(OrderItem item);
    Order byId(String orderId);
}
