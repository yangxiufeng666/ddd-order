package com.ddd.order.domain.repository.impl;

import com.ddd.order.application.query.qry.OrderListQry;
import com.ddd.order.application.query.representation.OrderRepresentation;
import com.ddd.order.domain.converter.OrderConverter;
import com.ddd.order.domain.converter.OrderItemConverter;
import com.ddd.order.domain.entity.Order;
import com.ddd.order.domain.repository.OrderQueryRepository;
import com.ddd.order.domain.representation.OrderListRepresentation;
import com.ddd.order.infrastructure.repository.dataobject.OrderDO;
import com.ddd.order.infrastructure.repository.dataobject.OrderItemDO;
import com.ddd.order.infrastructure.repository.mapper.OrderDOMapper;
import com.ddd.order.infrastructure.repository.mapper.OrderItemDOMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public OrderListRepresentation listOrder(OrderListQry qry) {
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        List<OrderDO> orderDOList = orderMapper.listOrder();
        PageInfo<OrderDO> pageInfo = PageInfo.of(orderDOList);
        List<OrderRepresentation> list = orderDOList.stream().map(orderDO -> OrderRepresentation.create(orderDO.getId(), orderDO.getTotalPrice(),
                    orderDO.getOrderStatus(), orderDO.getCreateTime())).collect(Collectors.toList());
        OrderListRepresentation orderListRepresentation = new OrderListRepresentation(list, pageInfo.getTotal(), pageInfo.getPages());
        return orderListRepresentation;
    }
}
