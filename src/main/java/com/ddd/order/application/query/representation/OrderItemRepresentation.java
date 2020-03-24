package com.ddd.order.application.query.representation;

import com.ddd.order.infrastructure.common.Representation;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-19 17:49
 */
public class OrderItemRepresentation extends Representation {

    private static final long serialVersionUID = 1L;

    private String productId;

    private int count;

    private Integer itemPrice;

    private Integer totalItemPrice;

    public OrderItemRepresentation(String productId, int count, Integer itemPrice) {
        this.productId = productId;
        this.count = count;
        this.itemPrice = itemPrice;
        this.totalItemPrice = count * itemPrice;
    }

    public String getProductId() {
        return productId;
    }

    public int getCount() {
        return count;
    }

    public Integer getItemPrice() {
        return itemPrice;
    }

    public Integer getTotalItemPrice() {
        return totalItemPrice;
    }
}
