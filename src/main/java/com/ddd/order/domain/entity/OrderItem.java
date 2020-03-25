package com.ddd.order.domain.entity;

import com.ddd.order.shared.Entity;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-18 15:34
 */
public class OrderItem extends Entity {
    private static final long serialVersionUID = 1L;
    private String productId;
    private int count;
    private int itemPrice;
    private String orderId;

    public OrderItem() {
    }

    public OrderItem(String productId, int count, int itemPrice, String orderId) {
        this.productId = productId;
        this.count = count;
        this.itemPrice = itemPrice;
        this.orderId = orderId;
    }
    public static OrderItem create(String productId, int count, int itemPrice){
        return new OrderItem(productId, count, itemPrice, null);
    }
    public static OrderItem create(String productId, int count, int itemPrice, String orderId){
        return new OrderItem(productId, count, itemPrice, orderId);
    }
    public int totalPrice(){
        return itemPrice * count;
    }

    public String getProductId() {
        return productId;
    }

    public int getCount() {
        return count;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public String getOrderId() {
        return orderId;
    }
}
