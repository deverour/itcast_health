package com.itheima.health.mobile.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.common.MessageConst;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Order;
import com.itheima.health.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/mobile/order")
public class OrderController {

    @Reference
    private OrderService orderService;

    @RequestMapping("/submit")
    public Result submitOrder(@RequestBody  Map<String,String> map){
        try {
            String telephone = map.get("telephone");
            System.out.println("telephone:"+telephone);
            String code = map.get("validateCode");
            map.put("orderType", Order.ORDERTYPE_WEIXIN);
            Result result = orderService.addOrder(map);
            return  result;

        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConst.VALIDATECODE_ERROR);
        }

    }

    @RequestMapping("/findById")
    public Result findById4OrderDetail(Integer id){
        try {
            Map<String,Object> map = orderService.findByid4OrderDetail(id);
            return new Result(true,MessageConst.QUERY_ORDER_SUCCESS,map);

        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConst.QUERY_ORDER_FAIL);
        }
    }
}
