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

//    public Order() {
//    }

//    public Order(String id, Address address, List<OrderItem> items, Date createTime) {
//        this.id = id;
//        this.address = address;
//        this.items.addAll(items);
//        this.createTime = createTime;
//        this.orderStatus = OrderStatus.CREATED;
//        this.totalPrice = calculateTotalPrice(this.items);
//    }
//
//    public Order(String id, int totalPrice, Address address, List<OrderItem> items, Date createTime, OrderStatus orderStatus) {
//        this.id = id;
//        this.totalPrice = totalPrice;
//        this.address = address;
//        this.items = items;
//        this.createTime = createTime;
//        this.orderStatus = orderStatus;
//    }

    private Order(Builder builder) {
        this.id = builder.id;
        this.address = builder.address;
        this.totalPrice = builder.totalPrice;
        this.items.addAll(builder.items);
        this.orderStatus = builder.orderStatus;
        this.createTime = builder.createTime;
    }

//    public static Order create(String id, Address address, List<OrderItem> items){
//        return new Order(id, address, items, new Date());
//    }
//    public static Order create(String id, Address address, int totalPrice, int status, List<OrderItem> items, Date createTime){
//        return new Order(id,totalPrice, address, items, createTime, OrderStatus.of(status));
//    }

    public static class Builder{
        private String id;
        private Address address;
        private Integer totalPrice;
        private List<OrderItem> items = newArrayList();
        private Date createTime;
        private OrderStatus orderStatus;

        public Order build(){
            if (null == createTime){
                createTime = new Date();
            }
            if (null == orderStatus){
                throw new IllegalArgumentException("orderStatus should not be null");
            }
            return new Order(this);
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setAddress(Address address) {
            this.address = address;
            return this;
        }

        public Builder setItems(List<OrderItem> items) {
            this.items.addAll(items);
            totalPrice = calculateTotalPrice(items);
            return this;
        }

        public Builder setCreateTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        public Builder setOrderStatus(int status) {
            this.orderStatus = OrderStatus.of(status);
            return this;

        }

        public Builder setTotalPrice(Integer totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }
        private int calculateTotalPrice(List<OrderItem> items){
            return items.stream()
                    .map(OrderItem::totalPrice)
                    .reduce(0,Integer::sum);
        }
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
