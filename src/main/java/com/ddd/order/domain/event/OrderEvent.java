package com.ddd.order.domain.event;

import com.ddd.order.shared.event.DomainEvent;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-25 11:23
 */
public abstract class OrderEvent extends DomainEvent {
    private String orderId;

    public OrderEvent(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
}
