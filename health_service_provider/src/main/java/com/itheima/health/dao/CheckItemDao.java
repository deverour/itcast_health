package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.CheckItem;
import org.apache.ibatis.annotations.Param;

public interface  CheckItemDao {


    void add(CheckItem checkItem);

    Page<CheckItem> selectByCondition(@Param("queryString") String queryString);

    Long countCheckItemsById(@Param("checkItemId") Integer checkItemId);

    void deleteCheckItemById(@Param("checkItemId") Integer checkItemId);
}
