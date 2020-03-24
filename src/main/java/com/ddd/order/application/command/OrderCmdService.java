package com.ddd.order.application.command;

import com.ddd.order.application.command.cmd.ChangeAddressDetailCmd;
import com.ddd.order.application.command.cmd.CreateOrderCmd;
import com.ddd.order.domain.entity.Order;
import com.ddd.order.domain.entity.OrderItem;
import com.ddd.order.domain.factory.OrderFactory;
import com.ddd.order.domain.repository.OrderRepository;
import com.ddd.order.domain.service.OrderDomainService;
import com.ddd.order.domain.service.OrderIdGenerator;
import lombok.extern.slf4j.Slf4j;
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

    private OrderIdGenerator idGenerator;

    private OrderDomainService orderDomainService;

    public OrderCmdService(OrderRepository orderRepository, OrderFactory orderFactory, OrderDomainService orderDomainService) {
        this.orderRepository = orderRepository;
        this.orderFactory = orderFactory;
        this.orderDomainService = orderDomainService;
    }

    @Autowired
    public void setIdGenerator(OrderIdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    @Transactional(rollbackFor = Exception.class)
    public void createOrder(CreateOrderCmd cmd){
        String orderId = idGenerator.generate();
        List<OrderItem> items = cmd.getItems().stream()
                .map(item -> OrderItem.create(item.getProductId(), item.getCount(), item.getItemPrice(), orderId))
                .collect(Collectors.toList());
        Order order = Order.create(orderId, cmd.getAddress(), items);
        orderDomainService.save(order);
    }
    @Transactional(rollbackFor = Exception.class)
    public void changeAddressDetail(String orderId, ChangeAddressDetailCmd command){
        Order order = orderRepository.byId(orderId);
        order.changeAddressDetail(command.getDetail());
        orderRepository.save(order);
    }

}
