package com.itheima.health.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.common.MessageConst;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.CheckItem;
import com.itheima.health.service.CheckItemService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("/checkitem")
public class CheckItemController {

    @Reference
    private CheckItemService checkItemService;


    @RequestMapping("/add")
    public Result add(@RequestBody CheckItem checkItem){
        System.out.println("start!!!!!!!!!!!!!!!!!!!!!!!!");
        try{
            log.debug("CheckItemController checkItem:{}",checkItem);
            checkItemService.add(checkItem);
            return new Result(true, MessageConst.ADD_CHECKITEM_SUCCESS);

        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConst.ADD_CHECKITEM_FAIL);
        }
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        try {
            log.debug("queryPageBean:{}",queryPageBean);
            return checkItemService.pageQuery(queryPageBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new PageResult(0l,new ArrayList());
    }
    @RequestMapping("/delete")
    public Result deleteById(Integer id){


        try {
            log.debug("delete id:{}",id);
            checkItemService.deleteById(id);
            return new Result(true,MessageConst.DELETE_CHECKITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,e.getMessage());
        }

    }

    @RequestMapping("/findById")
    public Result findById(Integer id){
        try {
            log.debug("id:{}",id);
            CheckItem checkItem = checkItemService.findById(id);
            //System.out.println("checkItem:::"+checkItem);
            return new Result(true,MessageConst.QUERY_CHECKITEM_SUCCESS,checkItem);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConst.QUERY_CHECKITEM_FAIL);

        }

    }

    @RequestMapping("/edit")
    public Result edit(@RequestBody CheckItem checkItem){
        try {
            log.debug("checkItem:{}",checkItem);
            checkItemService.edit(checkItem);
            return new Result(true,MessageConst.EDIT_CHECKITEM_SUCCESS);

        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConst.EDIT_CHECKITEM_FAIL);
        }
    }

}
