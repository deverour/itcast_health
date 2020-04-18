package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.dao.CheckItemDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.CheckItem;
import com.itheima.health.service.CheckItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;


@Service
@Slf4j
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    private CheckItemDao checkItemDao ;
    @Override
    public void add(CheckItem checkItem) {
        log.debug("CheckItemServiceImpl checkitem:{}",checkItem);
        checkItemDao.add(checkItem);


    }

    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        log.debug(" queryPageBean:{}",queryPageBean);
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<CheckItem> pageData = checkItemDao.selectByCondition(queryPageBean.getQueryString());
        return new PageResult(pageData.getTotal(),pageData.getResult());
    }

    @Override
    public void deleteById(Integer id) {

        log.debug("deleteById:{}",id);
        Long count = checkItemDao.countCheckItemsById(id);
        if (count > 0) {
            throw new RuntimeException("有关联数据，不能删除");
        }
        checkItemDao.deleteCheckItemById(id);
    }

    @Override
    public CheckItem findById(Integer id) {
        log.debug("id:{}",id);
        return checkItemDao.findById(id);
    }
}
