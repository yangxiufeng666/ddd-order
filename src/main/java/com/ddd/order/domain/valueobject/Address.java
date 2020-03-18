package com.ddd.order.domain.valueobject;

import com.ddd.order.infrastructure.common.ValueObject;



/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-18 15:35
 */
public class Address extends ValueObject{
    private String province;
    private String city;
    private String detail;

    public Address(String province, String city, String detail) {
        this.province = province;
        this.city = city;
        this.detail = detail;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getDetail() {
        return detail;
    }
}
