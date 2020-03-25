package com.ddd.order.domain.valueobject;

import lombok.Getter;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-18 15:41
 */
public enum OrderStatus {
    /**
     * 订单已创建
     */
    CREATED(1),
    /**
     * 订单已支付
     */
    PAID(2),
    /**
     * 订单已取消
     */
    CANCELED(3);

    @Getter
    private final int code;

    OrderStatus(int code) {
        this.code = code;
    }

    public static OrderStatus of(int value){
        switch (value){
            case 1:
                return OrderStatus.CREATED;
            case 2:
                return OrderStatus.PAID;
            case 3:
                return OrderStatus.CANCELED;
            default:
                return null;
        }
    }
}
