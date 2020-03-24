package com.ddd.order.domain.converter;

import com.ddd.order.domain.entity.OrderItem;
import com.ddd.order.infrastructure.repository.dataobject.OrderItemDO;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-18 18:12
 */
public class OrderItemConverter {
    public static List<OrderItemDO> toOrderItemDO(List<OrderItem> items){
        List<OrderItemDO> itemDOs = items.stream().map(it -> {
            OrderItemDO itemDO = new OrderItemDO();
            itemDO.setItemCount(it.getCount());
            itemDO.setProductId(it.getProductId());
            itemDO.setItemPrice(it.getItemPrice());
            itemDO.setOrderId(it.getOrderId());
            return itemDO;
        }).collect(Collectors.toList());
        return itemDOs;
    }

    public static OrderItemDO toOrderItemDO(OrderItem item){
        OrderItemDO itemDO = new OrderItemDO();
        itemDO.setItemCount(item.getCount());
        itemDO.setProductId(item.getProductId());
        itemDO.setItemPrice(item.getItemPrice());
        itemDO.setOrderId(item.getOrderId());
        return itemDO;
    }

    public static List<OrderItem> toOrderItem(List<OrderItemDO> itemDOs){
        List<OrderItem> items = itemDOs.stream().map(it ->
             OrderItem.create(it.getProductId(), it.getItemCount(), it.getItemPrice(), it.getOrderId())
        ).collect(Collectors.toList());
        return items;
    }
}
