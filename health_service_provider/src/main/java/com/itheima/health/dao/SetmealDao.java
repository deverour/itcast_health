package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.Setmeal;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SetmealDao {
    void add(Setmeal setmeal);

    void addSetmealAndCheckGroup(Map<String,Integer> map);

    Page<Setmeal> selectByCondition(@Param("queryString") String queryString);

    List<Setmeal> findAll();

    Setmeal findById(@Param("id") Integer id);
}
