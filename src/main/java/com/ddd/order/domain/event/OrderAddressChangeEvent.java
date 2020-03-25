package com.ddd.order.domain.event;

import lombok.Getter;
import lombok.ToString;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-25 14:59
 */
@Getter
@ToString
public class OrderAddressChangeEvent extends OrderEvent {
    private String oldAddress;
    private String newAddress;

    public OrderAddressChangeEvent(String orderId, String oldAddress, String newAddress) {
        super(orderId);
        this.oldAddress = oldAddress;
        this.newAddress = newAddress;
    }
}
