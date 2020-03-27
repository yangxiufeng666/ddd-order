package com.ddd.order.domain.repository.impl;

import com.ddd.order.domain.converter.OrderConverter;
import com.ddd.order.domain.converter.OrderItemConverter;
import com.ddd.order.domain.entity.Order;
import com.ddd.order.domain.entity.OrderItem;
import com.ddd.order.domain.repository.OrderRepository;
import com.ddd.order.infrastructure.exception.OrderNotFoundException;
import com.ddd.order.infrastructure.repository.dataobject.OrderDO;
import com.ddd.order.infrastructure.repository.dataobject.OrderItemDO;
import com.ddd.order.infrastructure.repository.mapper.OrderDOMapper;
import com.ddd.order.infrastructure.repository.mapper.OrderItemDOMapper;
import org.springframework.stereotype.Component;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-18 16:03
 */
@Component
public class OrderRepositoryImpl implements OrderRepository {

    private OrderDOMapper orderMapper;

    private OrderItemDOMapper orderItemMapper;

    public OrderRepositoryImpl(OrderDOMapper orderMapper, OrderItemDOMapper orderItemDOMapper) {
        this.orderMapper = orderMapper;
        this.orderItemMapper = orderItemDOMapper;
    }

    @Override
    public void save(Order order) {
        OrderDO data = orderMapper.selectByPrimaryKey(order.getId());
        OrderDO orderDO = OrderConverter.toOrderDO(order);
        if (null == data){
            orderMapper.insert(orderDO);
        }else {
            orderMapper.updateByPrimaryKeySelective(orderDO);
        }
    }

    @Override
    public void saveItem(OrderItem item) {
        OrderItemDO itemDO = OrderItemConverter.toOrderItemDO(item);
        orderItemMapper.insert(itemDO);
    }

    @Override
    public Order byId(String orderId) {
        OrderDO orderDO = orderMapper.selectByPrimaryKey(orderId);
//        if (null == orderDO){
//            throw new OrderNotFoundException(orderId);
//        }
        Order order = OrderConverter.toOrder(orderDO);
        return order;
    }
}
