package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.health.pojo.Setmeal;
import com.itheima.health.service.SetmealService;
import lombok.extern.slf4j.Slf4j;



@Service
@Slf4j
public class SetmealServiceImpl implements SetmealService {

    @Override
    public void add(Setmeal setmeal, Integer[] checkGroupIds) {
        System.out.println("SetmealServiceImpl.add #################start!!!!!!!!!!!!!!!!!!!!!!");
        log.debug(">>>>>>checkGroupIds:{},setmeal:{}",checkGroupIds,setmeal);


    }
}
