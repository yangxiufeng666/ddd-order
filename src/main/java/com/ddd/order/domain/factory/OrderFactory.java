package com.ddd.order.domain.factory;

import com.ddd.order.domain.entity.Order;
import com.ddd.order.domain.entity.OrderItem;
import com.ddd.order.domain.service.OrderIdGenerator;
import com.ddd.order.domain.valueobject.Address;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
        List<OrderItem> items = orderItems.stream()
                .map(item -> OrderItem.create(item.getProductId(), item.getCount(), item.getItemPrice(), orderId))
                .collect(Collectors.toList());
        Order order = Order.create(orderId, address, items);
        return order;
    }

}
