package com.ddd.order.infrastructure.repository;

import com.ddd.order.domain.entity.Order;
import com.ddd.order.domain.repository.OrderRepository;
import com.ddd.order.infrastructure.repository.converter.OrderConverter;
import com.ddd.order.infrastructure.repository.converter.OrderItemConverter;
import com.ddd.order.infrastructure.repository.dataobject.OrderDO;
import com.ddd.order.infrastructure.repository.dataobject.OrderItemDO;
import com.ddd.order.infrastructure.repository.mapper.OrderDOMapper;
import com.ddd.order.infrastructure.repository.mapper.OrderItemDOMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-18 16:03
 */
@Component
public class OrderRepositoryImpl implements OrderRepository {

    private OrderDOMapper orderMapper;

    private OrderItemDOMapper orderItemDOMapper;

    public OrderRepositoryImpl(OrderDOMapper orderMapper, OrderItemDOMapper orderItemDOMapper) {
        this.orderMapper = orderMapper;
        this.orderItemDOMapper = orderItemDOMapper;
    }

    @Override
    public void save(Order order) {
        OrderDO data = orderMapper.selectByPrimaryKey(order.getId());
        OrderDO orderDO = orderDO = OrderConverter.toOrderDO(order);
        if (null == data){
            orderMapper.insert(orderDO);
        }else {
            orderMapper.updateByPrimaryKeySelective(orderDO);
        }
        List<OrderItemDO> items = OrderItemConverter.toOrderItemDO(order.getItems());
        items.forEach(orderItemDO -> orderItemDOMapper.insert(orderItemDO));
    }
}
