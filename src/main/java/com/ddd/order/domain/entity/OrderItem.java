package com.ddd.order.domain.entity;

import com.ddd.order.infrastructure.common.Entity;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-18 15:34
 */
public class OrderItem extends Entity {
    private String productId;
    private int count;
    private int itemPrice;

    public OrderItem() {
    }

    public OrderItem(String productId, int count, int itemPrice) {
        this.productId = productId;
        this.count = count;
        this.itemPrice = itemPrice;
    }
    public static OrderItem create(String productId, int count, int itemPrice){
        return new OrderItem(productId, count, itemPrice);
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
}
