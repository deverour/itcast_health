package com.itheima.health.service;

/**
 * @author ：seanyang
 * @date ：Created in 2019/7/13
 * @description ：用户业务接口
 * @version: 1.0
 */
public interface UserService {
	/**
	 * 登录方法
	 * @param username 用户名
	 * @param password 密  码
	 * @return
	 */
	boolean login(String username,String password);
}
