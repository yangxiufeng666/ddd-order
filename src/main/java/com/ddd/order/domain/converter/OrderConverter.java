package com.ddd.order.domain.converter;

import com.ddd.order.domain.entity.Order;
import com.ddd.order.domain.entity.OrderItem;
import com.ddd.order.domain.valueobject.OrderStatus;
import com.ddd.order.domain.valueobject.Address;
import com.ddd.order.infrastructure.repository.dataobject.OrderDO;
import com.ddd.order.infrastructure.repository.dataobject.OrderItemDO;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-18 16:08
 */
public class OrderConverter {
    public static OrderDO toOrderDO(Order order){
        OrderDO target = new OrderDO();
        target.setId(order.getId());
        target.setTotalPrice(order.getTotalPrice());
        Address address = order.getAddress();
        target.setProvince(address.getProvince());
        target.setCity(address.getCity());
        target.setDetailAddress(address.getDetail());
        OrderStatus orderStatus = order.getOrderStatus();
        target.setOrderStatus(orderStatus.getCode());
        target.setCreateTime(order.getCreateTime());
        return target;
    }
    public static Order toOrder(OrderDO orderDO){
        return new Order(orderDO.getId(),
                new Address(orderDO.getProvince(), orderDO.getCity(), orderDO.getDetailAddress()),
                Lists.newArrayList(), orderDO.getCreateTime());
    }
    public static Order toOrderWithItems(OrderDO orderDO, List<OrderItemDO> itemDOs){
        return new Order(orderDO.getId(),
                new Address(orderDO.getProvince(), orderDO.getCity(), orderDO.getDetailAddress()),
                OrderItemConverter.toOrderItem(itemDOs), orderDO.getCreateTime());
    }

    public static List<Order> toOrderList(List<OrderDO> orderDOList){
        if (null == orderDOList){
            return Lists.newArrayList();
        }
        return orderDOList.stream().map(orderDO -> toOrder(orderDO)).collect(Collectors.toList());
    }
}
