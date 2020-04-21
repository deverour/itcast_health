package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.Setmeal;

/**
 * @author ：seanyang
 * @date ：Created in 2019/7/18
 * @description ：套餐业务接口
 * @version: 1.0
 */
public interface SetmealService {
    /**
     * 新增套餐
     * @param setmeal 套餐表单数据
     * @param checkGroupIds 套餐选中检查组列表
     */
    void add(Setmeal setmeal,Integer[] checkGroupIds);


}
