package com.ddd.order.application.command;

import com.ddd.order.application.command.cmd.CreateOrderCmd;
import com.ddd.order.domain.entity.Order;
import com.ddd.order.domain.entity.OrderItem;
import com.ddd.order.domain.repository.OrderRepository;
import com.ddd.order.domain.valueobject.Address;
import com.ddd.order.infrastructure.utils.UuidGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-18 17:40
 */
@Service
public class OrderCmdService {

    private OrderRepository orderRepository;

    public OrderCmdService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public String createOrder(CreateOrderCmd cmd){
        List<OrderItem> items = cmd.getItems().stream()
                .map(item -> OrderItem.create(item.getProductId(), item.getCount(), item.getItemPrice()))
                .collect(Collectors.toList());
        String orderId = UuidGenerator.newUuid();
        Order order = Order.create(orderId,cmd.getAddress(), items);
        orderRepository.save(order);
        return orderId;
    }

}
