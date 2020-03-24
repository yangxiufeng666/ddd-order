package com.ddd.order.application.query.representation;

import com.ddd.order.infrastructure.common.Representation;

import java.util.Date;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-19 17:02
 */
public class OrderRepresentation extends Representation {
    private static final long serialVersionUID = 1L;

    private String orderId;
    private int totalPrice;
    private int orderStatus;
    private Date createTime;

    public OrderRepresentation(String orderId, int totalPrice, int orderStatus, Date createTime) {
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.createTime = createTime;
    }

    public static OrderRepresentation create(String orderId, int totalPrice, int orderStatus, Date createTime){
        return new OrderRepresentation(orderId, totalPrice, orderStatus, createTime);
    }

    public String getOrderId() {
        return orderId;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }
}
