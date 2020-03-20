package com.ddd.order.infrastructure.repository.mapper;

import com.ddd.order.infrastructure.repository.dataobject.OrderItemDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderItemDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderItemDO record);

    int insertSelective(OrderItemDO record);

    OrderItemDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderItemDO record);

    int updateByPrimaryKey(OrderItemDO record);

    List<OrderItemDO> getByOrderId(@Param("orderId") String orderId);
}