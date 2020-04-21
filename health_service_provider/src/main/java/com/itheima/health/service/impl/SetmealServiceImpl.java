package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.dao.SetmealDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.pojo.Setmeal;
import com.itheima.health.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.apache.yetus.audience.InterfaceAudience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;


@Service
@Slf4j
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealDao setmealDao;

    @Transactional
    @Override
    public void add(Setmeal setmeal, Integer[] checkGroupIds) {
        System.out.println("SetmealServiceImpl.add #################start!!!!!!!!!!!!!!!!!!!!!!");
        log.debug(">>>>>>checkGroupIds:{},setmeal:{}",checkGroupIds,setmeal);
        setmealDao.add(setmeal);
        for (Integer checkGoupId:checkGroupIds){
            Map<String,Integer> map = new HashMap<>();
            map.put("setmeal_id",setmeal.getId());
            map.put("checkgroup_id",checkGoupId);
            setmealDao.addSetmealAndCheckGroup(map);
        }
    }

    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<Setmeal> pageSetmeal = setmealDao.selectByCondition(queryString);
        return new PageResult(pageSetmeal.getTotal(),pageSetmeal.getResult());
    }
}
