package com.ddd.order.interfaces;

import com.ddd.order.application.command.OrderCmdService;
import com.ddd.order.application.command.cmd.ChangeAddressDetailCmd;
import com.ddd.order.application.command.cmd.CreateOrderCmd;
import com.ddd.order.application.query.OrderQueryService;
import com.ddd.order.application.query.qry.OrderListQry;
import com.ddd.order.application.query.representation.OrderRepresentation;
import com.ddd.order.application.query.representation.OrderWithItemsRepresentation;
import com.ddd.order.domain.representation.OrderListRepresentation;
import com.ddd.order.shared.Response;
import com.ddd.order.shared.ResponseWithData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    private final OrderQueryService orderQueryService;

    public OrderController(OrderCmdService orderCmdService, OrderQueryService orderQueryService) {
        this.orderCmdService = orderCmdService;
        this.orderQueryService = orderQueryService;
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
    @ApiOperation("获取订单信息")
    @GetMapping("/{orderId}")
    public ResponseWithData<OrderRepresentation> getOrder(@PathVariable("orderId") String orderId){
        return ResponseWithData.of(orderQueryService.getOrder(orderId));
    }
    @ApiOperation("获取订单详细信息")
    @GetMapping("withItem/{orderId}")
    public ResponseWithData<OrderWithItemsRepresentation> getOrderWithItems(@PathVariable("orderId") String orderId){
        return ResponseWithData.of(orderQueryService.getOrderWithItems(orderId));
    }
    @ApiOperation("获取订单列表")
    @GetMapping("list")
    public ResponseWithData<OrderListRepresentation> getOrderList(OrderListQry orderListQry){
        return ResponseWithData.of(orderQueryService.listOrder(orderListQry));
    }

}
