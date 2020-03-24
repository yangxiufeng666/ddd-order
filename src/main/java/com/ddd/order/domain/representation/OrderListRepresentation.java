package com.ddd.order.domain.representation;

import com.ddd.order.application.query.representation.OrderRepresentation;
import com.ddd.order.infrastructure.common.Representation;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-24 17:46
 */
@Getter
@AllArgsConstructor
public class OrderListRepresentation extends Representation {

    private static final long serialVersionUID = 1L;
    private List<OrderRepresentation> orderList;

    private long total;

    private int pages;



}
