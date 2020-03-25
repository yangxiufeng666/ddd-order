package com.ddd.order.domain.event;

import com.ddd.order.domain.entity.OrderItem;
import com.ddd.order.domain.valueobject.Address;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-25 11:25
 */
@Getter
@ToString
public class OrderCreatedEvent extends OrderEvent{
    private Address address;
    private List<OrderItem> items;
    private Date createdTime;

    public OrderCreatedEvent(String orderId, Address address, List<OrderItem> items) {
        super(orderId);
        this.address = address;
        this.items = items;
        this.createdTime = new Date();
    }
}
