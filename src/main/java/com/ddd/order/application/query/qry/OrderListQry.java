package com.ddd.order.application.query.qry;

import lombok.Data;
import org.ddd.shared.core.Query;

import javax.validation.constraints.NotNull;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-24 17:10
 */
@Data
public class OrderListQry extends Query {
    private static final long serialVersionUID = 1L;
    @NotNull(message = "pageNum不能为空")
    private int pageNum;
    @NotNull(message = "pageSize不能为空")
    private int pageSize;

}
