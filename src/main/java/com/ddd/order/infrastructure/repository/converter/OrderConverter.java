package com.ddd.order.infrastructure.repository.converter;

import com.ddd.order.domain.entity.Order;
import com.ddd.order.domain.pojo.OrderStatus;
import com.ddd.order.domain.valueobject.Address;
import com.ddd.order.infrastructure.repository.dataobject.OrderDO;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-18 16:08
 */
public class OrderConverter {
    public static OrderDO toOrderDO(Order order){
        OrderDO target = new OrderDO();
        target.setId(order.getId());
        target.setTotalPrice(order.getTotalPrice());
        Address address = order.getAddress();
        target.setProvince(address.getProvince());
        target.setCity(address.getCity());
        target.setDetailAddress(address.getDetail());
        OrderStatus orderStatus = order.getOrderStatus();
        target.setOrderStatus(orderStatus.ordinal());
        return target;
    }
}
