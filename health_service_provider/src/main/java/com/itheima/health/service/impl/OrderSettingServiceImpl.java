package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.health.dao.OrderSettingDao;
import com.itheima.health.pojo.OrderSetting;
import com.itheima.health.service.OrderSettingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class OrderSettingServiceImpl implements OrderSettingService {

    @Autowired
    private OrderSettingDao orderSettingDao;

    @Transactional
    @Override
    public void add(List<OrderSetting> orderSettingList) {
        log.debug(">>>>> orderSettingList:{}",orderSettingList);
        for (OrderSetting orderSetting:orderSettingList){
            Long count = orderSettingDao.countByOrderDate(orderSetting.getOrderDate());
            if (count > 0){
                orderSettingDao.update(orderSetting);
            }else {
                orderSettingDao.add(orderSetting);
            }
        }
    }

    @Override
    public List<Map> getOrderSettingByMonth(String date) {
        log.debug("getOrderSettingByMonth data:{}",date);
        List<Map> listMap = new ArrayList<>();
        String beginDate = date + "-1";
        String endDate = date+ "-31";
        List<OrderSetting> orderSettingList = orderSettingDao.getOrderSettingByMonth(beginDate,endDate);
        for (OrderSetting orderSetting:orderSettingList){
            Map m = new HashMap();
            m.put("date",orderSetting.getOrderDate().getDate());
            m.put("number",orderSetting.getNumber());
            m.put("reservations",orderSetting.getReservations());
            listMap.add(m);
        }


        return listMap;
    }

    @Override
    public void editOrderSettingByDate(OrderSetting orderSetting) {
        log.debug(">>>>>orderSetting:{}",orderSetting);
        Long count =  orderSettingDao.countByOrderDate(orderSetting.getOrderDate());
        if (count > 0){
            orderSettingDao.update(orderSetting);
        }else {
            orderSettingDao.add(orderSetting);
        }
    }
}
