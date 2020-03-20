package com.ddd.order.application.query.presentation;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-19 17:49
 */
public class OrderItemRepresentation {
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
