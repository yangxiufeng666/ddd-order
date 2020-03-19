package com.ddd.order.application.command.cmd;

import com.ddd.order.infrastructure.common.Command;

import javax.validation.constraints.NotBlank;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-19 14:56
 */
public class ChangeAddressDetailCmd extends Command {
    @NotBlank(message = "详细地址不能为空")
    private String detail;

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }
}
