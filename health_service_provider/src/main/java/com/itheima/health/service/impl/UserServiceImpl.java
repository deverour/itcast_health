package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.health.dao.PermissionDao;
import com.itheima.health.dao.RoleDao;
import com.itheima.health.dao.UserDao;
import com.itheima.health.pojo.Role;
import com.itheima.health.pojo.User;
import com.itheima.health.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * @author ：seanyang
 * @date ：Created in 2019/7/13
 * @description ：用户业务实现类
 * @version: 1.0
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private PermissionDao permissionDao;

	@Override
	public boolean login(String username, String password) {
//		log.debug("u:${} p:{}",username,password);
		log.info("u:{} p:{}",username,password);
		if("admin".equals(username) && "123".equals(password)){
			return true;
		}
		return false;
	}

	@Override
	public User findByUserName(String username) {
		User user =userDao.findByUserName(username);
		Set<Role> roleSet =roleDao.findByUserId(user.getId());
		user.setRoles(roleSet);
		 for (Role role:roleSet){
		 	role.setPermissions(permissionDao.findByRoleId(role.getId()));
		 }
		return user;
	}
}
