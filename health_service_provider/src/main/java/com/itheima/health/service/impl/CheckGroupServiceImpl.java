package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.health.dao.CheckGroupDao;
import com.itheima.health.pojo.CheckGroup;
import com.itheima.health.service.CheckGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
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
            Map map = new HashMap();
            map.put("checkgroup_id",checkGroup.getId());
            map.put("checkitem_id",checkItemId);
            checkGroupDao.addCheckGroupAndCheckItem(map);
        }




    }
}
