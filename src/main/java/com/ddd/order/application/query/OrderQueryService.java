package com.ddd.order.application.query;

import com.ddd.order.application.query.presentation.OrderItemRepresentation;
import com.ddd.order.application.query.presentation.OrderRepresentation;
import com.ddd.order.application.query.presentation.OrderWithItemsRepresentation;
import com.ddd.order.domain.converter.OrderItemConverter;
import com.ddd.order.domain.entity.Order;
import com.ddd.order.domain.repository.OrderQueryRepository;
import com.ddd.order.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-19 17:01
 */
@Service
public class OrderQueryService {

    private OrderQueryRepository orderQueryRepository;
    private OrderRepository orderRepository;

    public OrderQueryService(OrderQueryRepository orderQueryRepository, OrderRepository orderRepository) {
        this.orderQueryRepository = orderQueryRepository;
        this.orderRepository = orderRepository;
    }

    public OrderRepresentation getOrder(String orderId){
        Order order = orderRepository.byId(orderId);
        OrderRepresentation presentation = OrderRepresentation.create(order.getId(), order.getTotalPrice(), order.getOrderStatus().getCode(), order.getCreateTime());
        return presentation;
    }

    public OrderWithItemsRepresentation getOrderWithItems(String orderId){
        Order order = orderQueryRepository.withItemById(orderId);
        OrderWithItemsRepresentation representation = new OrderWithItemsRepresentation(order.getId(),
                order.getTotalPrice(), order.getOrderStatus().getCode(),order.getCreateTime(),
                order.getItems().stream().map(orderItem -> {
                    OrderItemRepresentation item = new OrderItemRepresentation(orderItem.getProductId(), orderItem.getCount(), orderItem.getItemPrice());
                    return item;
                }).collect(Collectors.toList()));
        return representation;
    }
}
