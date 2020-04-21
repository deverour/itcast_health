package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.Setmeal;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface SetmealDao {
    void add(Setmeal setmeal);

    void addSetmealAndCheckGroup(Map<String,Integer> map);

    Page<Setmeal> selectByCondition(@Param("queryString") String queryString);

}
