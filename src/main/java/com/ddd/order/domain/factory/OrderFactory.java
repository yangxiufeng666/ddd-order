package com.ddd.order.domain.factory;

import com.ddd.order.domain.entity.Order;
import com.ddd.order.domain.entity.OrderItem;
import com.ddd.order.domain.service.OrderIdGenerator;
import com.ddd.order.domain.valueobject.Address;
import com.ddd.order.domain.valueobject.OrderStatus;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-19 14:12
 */
@Component
public class OrderFactory {

    private OrderIdGenerator idGenerator;

    public OrderFactory(OrderIdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    public Order create(Address address, List<OrderItem> orderItems){
        String orderId = idGenerator.generate();
        return new Order.Builder()
                .setId(orderId)
                .setAddress(address)
                .setItems(orderItems)
                .setOrderStatus(OrderStatus.CREATED.getCode())
                .build();
//        Order order = Order.create(orderId, address, orderItems);
//        return order;
    }
}
