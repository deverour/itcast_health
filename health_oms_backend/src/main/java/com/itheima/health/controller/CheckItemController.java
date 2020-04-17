package com.itheima.health.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.common.MessageConst;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.CheckItem;
import com.itheima.health.service.CheckItemService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkitem")
public class CheckItemController {

    @Reference
    private CheckItemService checkItemService;


    @RequestMapping("/add")
    public Result add(@RequestBody CheckItem checkItem){
        System.out.println("start!!!!!!!!!!!!!!!!!!!!!!!!");
        try{
            //log.debug("CheckItemController checkItem:{}",checkItem);
            checkItemService.add(checkItem);
            return new Result(true, MessageConst.ADD_CHECKITEM_SUCCESS);

        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConst.ADD_CHECKITEM_FAIL);
        }


    }
}
