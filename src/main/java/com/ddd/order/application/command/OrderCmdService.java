package com.ddd.order.application.command;

import com.ddd.order.application.command.cmd.ChangeAddressDetailCmd;
import com.ddd.order.application.command.cmd.CreateOrderCmd;
import com.ddd.order.domain.entity.Order;
import com.ddd.order.domain.entity.OrderItem;
import com.ddd.order.domain.event.OrderAddressChangeEvent;
import com.ddd.order.domain.event.OrderCreatedEvent;
import com.ddd.order.domain.factory.OrderFactory;
import com.ddd.order.domain.repository.OrderRepository;
import com.ddd.order.domain.service.OrderDomainService;
import lombok.extern.slf4j.Slf4j;
import org.ddd.shared.core.event.DomainEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-18 17:40
 */
@Service
@Slf4j
public class OrderCmdService {

    private OrderRepository orderRepository;

    private OrderFactory orderFactory;

    private OrderDomainService orderDomainService;

    private DomainEventPublisher domainEventPublisher;

    public OrderCmdService(OrderRepository orderRepository, OrderFactory orderFactory, OrderDomainService orderDomainService) {
        this.orderRepository = orderRepository;
        this.orderFactory = orderFactory;
        this.orderDomainService = orderDomainService;
    }
    @Autowired
    public void setDomainEventPublisher(DomainEventPublisher domainEventPublisher) {
        this.domainEventPublisher = domainEventPublisher;
    }

    @Transactional(rollbackFor = Exception.class)
    public void createOrder(CreateOrderCmd cmd){
        List<OrderItem> orderItemList = cmd.getItems()
                .stream()
                .map(orderItemCmd -> OrderItem.create(orderItemCmd.getProductId(),
                        orderItemCmd.getCount(),
                        orderItemCmd.getItemPrice()))
                .collect(Collectors.toList());
        Order order = orderFactory.create(cmd.getAddress(),orderItemList);
        orderDomainService.save(order);
        //发布领域事件、领域事件可以在ApplicationService中发、也可以在repository中、也可以用事件表
        domainEventPublisher.publish(new OrderCreatedEvent(order.getId(), order.getAddress(), order.getItems()));
    }
    @Transactional(rollbackFor = Exception.class)
    public void changeAddressDetail(String orderId, ChangeAddressDetailCmd command){
        Order order = orderRepository.byId(orderId);
        String oldAddress = order.getAddress().getDetail();
        String newAddress = command.getDetail();
        order.changeAddressDetail(command.getDetail());
        orderRepository.save(order);
        //发布领域事件、领域事件可以在ApplicationService中发、也可以在repository中、也可以用事件表
        domainEventPublisher.publish(new OrderAddressChangeEvent(orderId, oldAddress, newAddress));
    }

}
