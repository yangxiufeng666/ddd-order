package com.ddd.order.domain.converter;

import com.ddd.order.domain.entity.Order;
import com.ddd.order.domain.valueobject.Address;
import com.ddd.order.domain.valueobject.OrderStatus;
import com.ddd.order.infrastructure.repository.dataobject.OrderDO;
import com.ddd.order.infrastructure.repository.dataobject.OrderItemDO;
import com.google.common.collect.Lists;

import java.util.List;

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
        return Order.create(orderDO.getId(),
                new Address(orderDO.getProvince(), orderDO.getCity(), orderDO.getDetailAddress()),
                orderDO.getTotalPrice(), orderDO.getOrderStatus(),Lists.newArrayList(), orderDO.getCreateTime());
    }
    public static Order toOrderWithItems(OrderDO orderDO, List<OrderItemDO> itemDOs){
        return new Order(orderDO.getId(),
                new Address(orderDO.getProvince(), orderDO.getCity(), orderDO.getDetailAddress()),
                OrderItemConverter.toOrderItem(itemDOs), orderDO.getCreateTime());
    }
}
