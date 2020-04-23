package com.itheima.health.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.common.MessageConst;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.OrderSetting;
import com.itheima.health.service.OrderSettingService;
import com.itheima.health.utils.POIUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {

    @Reference
    private OrderSettingService orderSettingService;

    @RequestMapping("/upload")
    public Result upload(@RequestParam("excelFile") MultipartFile multipartFile){
        try {
            String fileName = multipartFile.getOriginalFilename();
            log.debug("@@@@@ excel fileName:{}",fileName);
            List<String[]> list = POIUtils.readExcel(multipartFile);
            List<OrderSetting> orderSettingList = new ArrayList<>();
            for (String[] row: list){
                OrderSetting orderSetting = new OrderSetting();
                orderSetting.setOrderDate(new Date(row[0]));
                orderSetting.setNumber(Integer.parseInt(row[1]));
                orderSettingList.add(orderSetting);

            }
            orderSettingService.add(orderSettingList);
            return new Result(true, MessageConst.IMPORT_ORDERSETTING_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConst.IMPORT_ORDERSETTING_FAIL);
        }
    }

    @RequestMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String date){
        try {
            List<Map> mapList = orderSettingService.getOrderSettingByMonth(date);
            return new Result(true,MessageConst.GET_ORDERSETTING_SUCCESS,mapList);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConst.GET_ORDERSETTING_FAIL);
        }
    }

    @RequestMapping("/editNumberByDate")
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting){
        log.debug("orderSetting:{}",orderSetting);
        try {
            orderSettingService.editOrderSettingByDate(orderSetting);
            return new Result(true,MessageConst.ACTION_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConst.ACTION_FAIL);
        }


    }
}
