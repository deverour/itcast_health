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
        Map m = new HashMap();
        m.put("date",19);
        m.put("number",100);
        m.put("reservations",10);
        listMap.add(m);

        return listMap;
    }
}
