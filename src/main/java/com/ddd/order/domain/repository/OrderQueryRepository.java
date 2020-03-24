package com.ddd.order.domain.repository;

import com.ddd.order.application.query.qry.OrderListQry;
import com.ddd.order.domain.entity.Order;
import com.ddd.order.domain.representation.OrderListRepresentation;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-19 17:56
 */
public interface OrderQueryRepository {
    Order withItemById(String orderId);
    OrderListRepresentation listOrder(OrderListQry qry);
}
