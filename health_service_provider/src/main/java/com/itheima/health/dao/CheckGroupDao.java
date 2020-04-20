package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.CheckGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CheckGroupDao {

    void add(CheckGroup checkGroup);

    void edit(CheckGroup checkGroup);

    void deleteCheckItemsListByCheckGroupId(@Param("checkGroupId") Integer checkGroupId);

    void addCheckGroupAndCheckItem(Map map);

    Page<CheckGroup> selectByCondition(@Param("queryString") String queryString);

    CheckGroup findById(Integer id);

    List<Integer> findCheckItemIdsByCheckGroupId(@Param("id") Integer id);
}
