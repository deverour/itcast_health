package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.pojo.CheckGroup;

import java.util.List;


public interface CheckGroupService {
    void add(CheckGroup checkGroup,Integer[] checkItemIds);

    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    CheckGroup findById(Integer id);

    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    void edit(CheckGroup checkGroup,Integer[] checkItemIds);

    List<CheckGroup> findAll();

}
