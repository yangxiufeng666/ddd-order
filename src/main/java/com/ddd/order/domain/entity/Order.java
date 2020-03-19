package com.ddd.order.domain.entity;

import com.ddd.order.domain.valueobject.OrderStatus;
import com.ddd.order.infrastructure.common.AggregateRoot;
import com.ddd.order.domain.valueobject.Address;

import java.time.Instant;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-18 15:33
 */
public class Order extends AggregateRoot {

    private String id;
    private int totalPrice;
    private Address address;
    private List<OrderItem> items = newArrayList();
    private Instant createTime;
    private OrderStatus orderStatus;

    public Order() {
    }

    public Order(String id, Address address, List<OrderItem> items) {
        this.id = id;
        this.address = address;
        this.items.addAll(items);
        this.createTime = Instant.now();
        this.orderStatus = OrderStatus.CREATED;
        this.totalPrice = calculateTotalPrice(this.items);
    }
    public static Order create(String id, Address address, List<OrderItem> items){
        return new Order(id, address, items);
    }
    private int calculateTotalPrice(List<OrderItem> items){
        return items.stream()
                .map(OrderItem::totalPrice)
                .reduce(0,Integer::sum);
    }

    public void changeAddressDetail(String detail){
        this.address = address.changeDetailTo(detail);
    }


    public String getId() {
        return id;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public Address getAddress() {
        return address;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
}
