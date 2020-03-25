package com.ddd.order.domain.entity;

import com.ddd.order.domain.valueobject.Address;
import com.ddd.order.domain.valueobject.OrderStatus;
import com.ddd.order.shared.AggregateRoot;

import java.util.Date;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-18 15:33
 */
public class Order extends AggregateRoot {

    private static final long serialVersionUID = 1L;
    private String id;
    private int totalPrice;
    private Address address;
    private List<OrderItem> items = newArrayList();
    private Date createTime;
    private OrderStatus orderStatus;

    public Order() {
    }

    public Order(String id, Address address, List<OrderItem> items, Date createTime) {
        this.id = id;
        this.address = address;
        this.items.addAll(items);
        this.createTime = createTime;
        this.orderStatus = OrderStatus.CREATED;
        this.totalPrice = calculateTotalPrice(this.items);
    }
    public static Order create(String id, Address address, List<OrderItem> items){
        return new Order(id, address, items, new Date());
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

    public Date getCreateTime() {
        return createTime;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
}
