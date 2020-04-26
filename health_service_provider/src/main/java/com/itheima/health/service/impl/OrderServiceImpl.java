package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.health.common.MessageConst;
import com.itheima.health.entity.Result;
import com.itheima.health.service.OrderService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


@Service
public class OrderServiceImpl implements OrderService {

    @Transactional
    @Override
    public Result addOrder(Map<String, String> map) {
        return new Result(true, MessageConst.ORDER_SUCCESS,"99");
    }
}
