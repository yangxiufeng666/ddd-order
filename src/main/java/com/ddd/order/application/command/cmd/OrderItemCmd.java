package com.ddd.order.application.command.cmd;

import com.ddd.order.shared.Command;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-18 16:15
 */
@Data
public class OrderItemCmd extends Command {
    @NotBlank(message = "产品ID不能为空")
    private String productId;

    @Min(value = 1, message = "产品数量必须大于0")
    private int count;

    @NotNull(message = "产品单价不能为空")
    private Integer itemPrice;
}
