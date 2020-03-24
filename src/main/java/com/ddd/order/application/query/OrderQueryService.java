package com.ddd.order.application.query;

import com.ddd.order.application.query.qry.OrderListQry;
import com.ddd.order.application.query.representation.OrderItemRepresentation;
import com.ddd.order.application.query.representation.OrderRepresentation;
import com.ddd.order.application.query.representation.OrderWithItemsRepresentation;
import com.ddd.order.domain.entity.Order;
import com.ddd.order.domain.repository.OrderQueryRepository;
import com.ddd.order.domain.repository.OrderRepository;
import com.ddd.order.domain.representation.OrderListRepresentation;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public OrderListRepresentation listOrder(OrderListQry qry){
        OrderListRepresentation orderList = orderQueryRepository.listOrder(qry);
        return orderList;
    }
}
