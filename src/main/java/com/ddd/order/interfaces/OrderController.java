package com.ddd.order.interfaces;

import com.ddd.order.application.command.OrderCmdService;
import com.ddd.order.application.command.cmd.ChangeAddressDetailCmd;
import com.ddd.order.application.command.cmd.CreateOrderCmd;
import com.ddd.order.infrastructure.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-19 9:27
 */
@RestController
@RequestMapping("order")
@Api(tags = {"订单"})
@Slf4j
public class OrderController {
    private final OrderCmdService orderCmdService;

    public OrderController(OrderCmdService orderCmdService) {
        this.orderCmdService = orderCmdService;
    }
    @ApiOperation("创建订单")
    @PostMapping("create")
    public Response createOrder(@RequestBody @Valid CreateOrderCmd command){
        orderCmdService.createOrder(command);
        return Response.buildSuccess();
    }
    @ApiOperation("修改订单详细地址")
    @PostMapping("/{orderId}/changeAddressDetail")
    public Response changeAddressDetail(@PathVariable("orderId") String orderId, @RequestBody @Valid ChangeAddressDetailCmd command){
        orderCmdService.changeAddressDetail(orderId, command);
        return Response.buildSuccess();
    }
}
