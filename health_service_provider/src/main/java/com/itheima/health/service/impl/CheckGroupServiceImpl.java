package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.dao.CheckGroupDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.CheckGroup;
import com.itheima.health.service.CheckGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupDao checkGroupDao;

    @Transactional
    @Override
    public void add(CheckGroup checkGroup, Integer[] checkItemIds) {
        log.debug(">>>> checkItemIds:{} checkgroup:{}",checkItemIds,checkGroup);
        checkGroupDao.add(checkGroup);
        log.debug(">>>>add checkgroup_id:{} ",checkGroup.getId());
        for (Integer checkItemId:checkItemIds){
            HashMap<String,Integer> map = new HashMap<String,Integer>();
            map.put("checkgroup_id",checkGroup.getId());
            map.put("checkitem_id",checkItemId);
            checkGroupDao.addCheckGroupAndCheckItem(map);
        }

    }

    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        log.debug("c:{}, p:{},q:{} ",currentPage,pageSize,queryString);
        PageHelper.startPage(currentPage,pageSize);
        Page<CheckGroup> pageData = checkGroupDao.selectByCondition(queryString);
        return new PageResult(pageData.getTotal(),pageData.getResult());
    }

    @Override
    public CheckGroup findById(Integer id) {
        log.debug(">>>> findById:{}",id);
        return checkGroupDao.findById(id);
    }

    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id) {
        log.debug(">>>> findCheckItemIdsByCheckGroupId:{}",id);
        return checkGroupDao.findCheckItemIdsByCheckGroupId(id);

    }

    @Transactional
    @Override
    public void edit(CheckGroup checkGroup, Integer[] checkItemIds) {
        log.debug(">>>> checkItemIds:{} checkgroup:{}",checkItemIds,checkGroup);
        checkGroupDao.edit(checkGroup);
        checkGroupDao.deleteCheckItemsListByCheckGroupId(checkGroup.getId());


        for (Integer checkItemId:checkItemIds){
            HashMap<String,Integer> map = new HashMap<String,Integer>();
            map.put("checkgroup_id",checkGroup.getId());
            map.put("checkitem_id",checkItemId);
            checkGroupDao.addCheckGroupAndCheckItem(map);
        }

    }

    @Override
    public List<CheckGroup> findAll() {
        return checkGroupDao.findAll();
    }


}
