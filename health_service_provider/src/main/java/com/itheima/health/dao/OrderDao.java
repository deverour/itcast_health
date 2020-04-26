package com.itheima.health.dao;

import com.itheima.health.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderDao {

    List<Order> findByCondition(Order order);

    void add(Order order);

    Map<String,Object> findById4Detail(@Param("id") Integer id);

}
