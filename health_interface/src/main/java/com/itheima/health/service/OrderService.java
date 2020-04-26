package com.itheima.health.service;

import com.itheima.health.entity.Result;

import java.util.Map;

public interface OrderService {

    Result addOrder(Map<String,String> map);

    Map<String,Object > findByid4OrderDetail(Integer id);

}
