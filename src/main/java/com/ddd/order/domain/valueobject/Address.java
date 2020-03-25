package com.ddd.order.domain.valueobject;

import com.ddd.order.shared.ValueObject;

import javax.validation.constraints.NotBlank;


/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-18 15:35
 */
public class Address extends ValueObject{
    @NotBlank(message = "省不能为空")
    private String province;
    @NotBlank(message = "市不能为空")
    private String city;
    @NotBlank(message = "详细不能为空")
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
    public Address changeDetailTo(String detail){
        return new Address(this.province, this.getCity(), detail);
    }
}
