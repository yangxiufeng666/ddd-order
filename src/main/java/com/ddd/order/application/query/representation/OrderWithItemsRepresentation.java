package com.ddd.order.application.query.representation;

import java.util.Date;
import java.util.List;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-19 17:48
 */
public class OrderWithItemsRepresentation extends OrderRepresentation{

    private static final long serialVersionUID = 1L;

    private List<OrderItemRepresentation> items;

    public OrderWithItemsRepresentation(String orderId, int totalPrice, int orderStatus, Date createTime, List<OrderItemRepresentation> items) {
        super(orderId, totalPrice, orderStatus, createTime);
        this.items = items;
    }

    public List<OrderItemRepresentation> getItems() {
        return items;
    }
}
