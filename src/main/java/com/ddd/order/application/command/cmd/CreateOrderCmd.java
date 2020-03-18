package com.ddd.order.application.command.cmd;


import com.ddd.order.domain.valueobject.Address;
import com.ddd.order.infrastructure.common.Command;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-18 16:15
 */
@Data
public class CreateOrderCmd extends Command {
    @NotNull(message = "订单地址不能为空")
    private Address address;
    @NotEmpty(message = "订单项不能为空")
    private List<OrderItemCmd> items;

}
