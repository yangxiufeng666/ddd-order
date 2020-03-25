package com.ddd.order.domain.service;

import com.ddd.order.domain.entity.Order;
import com.ddd.order.domain.entity.OrderItem;
import com.ddd.order.domain.repository.OrderRepository;
import com.ddd.order.shared.DomainService;
import org.springframework.stereotype.Service;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-20 14:13
 */
@Service
public class OrderDomainService implements DomainService {

    private OrderRepository orderRepository;

    public OrderDomainService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void save(Order order) {
        orderRepository.save(order);
        order.getItems().forEach(orderItem -> {
            OrderItem item = OrderItem.create(orderItem.getProductId(), orderItem.getCount(), orderItem.getItemPrice(), order.getId());
            orderRepository.saveItem(item);
        });
    }

}
