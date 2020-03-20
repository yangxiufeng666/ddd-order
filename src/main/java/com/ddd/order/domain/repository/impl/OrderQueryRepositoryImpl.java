package com.ddd.order.domain.repository.impl;

import com.ddd.order.domain.converter.OrderConverter;
import com.ddd.order.domain.converter.OrderItemConverter;
import com.ddd.order.domain.entity.Order;
import com.ddd.order.domain.repository.OrderQueryRepository;
import com.ddd.order.infrastructure.repository.dataobject.OrderDO;
import com.ddd.order.infrastructure.repository.dataobject.OrderItemDO;
import com.ddd.order.infrastructure.repository.mapper.OrderDOMapper;
import com.ddd.order.infrastructure.repository.mapper.OrderItemDOMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-19 18:12
 */
@Component
public class OrderQueryRepositoryImpl implements OrderQueryRepository {

    private OrderDOMapper orderMapper;

    private OrderItemDOMapper orderItemMapper;

    public OrderQueryRepositoryImpl(OrderDOMapper orderMapper, OrderItemDOMapper orderItemMapper) {
        this.orderMapper = orderMapper;
        this.orderItemMapper = orderItemMapper;
    }

    @Override
    public Order withItemById(String orderId) {
        OrderDO orderDO = orderMapper.selectByPrimaryKey(orderId);
        List<OrderItemDO> items = orderItemMapper.getByOrderId(orderId);
        OrderItemConverter.toOrderItem(items);
        Order order = OrderConverter.toOrderWithItems(orderDO, items);
        return order;
    }
}
