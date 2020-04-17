package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.health.service.UserService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ：seanyang
 * @date ：Created in 2019/7/13
 * @description ：用户业务实现类
 * @version: 1.0
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
	@Override
	public boolean login(String username, String password) {
//		log.debug("u:${} p:{}",username,password);
		log.info("u:{} p:{}",username,password);
		if("admin".equals(username) && "123".equals(password)){
			return true;
		}
		return false;
	}
}
