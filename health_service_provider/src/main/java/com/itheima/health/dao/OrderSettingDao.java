package com.itheima.health.dao;

import com.itheima.health.pojo.OrderSetting;
import org.apache.ibatis.annotations.Param;

import java.util.Date;


public interface OrderSettingDao {

    Long countByOrderDate(@Param("date") Date date);

    void add(OrderSetting orderSetting);

    void update(OrderSetting orderSetting);

}
