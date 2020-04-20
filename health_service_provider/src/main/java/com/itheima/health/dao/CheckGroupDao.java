package com.itheima.health.dao;

import com.itheima.health.pojo.CheckGroup;

import java.util.Map;

public interface CheckGroupDao {

    void add(CheckGroup checkGroup);

    void addCheckGroupAndCheckItem(Map map);
}
