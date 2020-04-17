package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.common.MessageConst;
import com.itheima.health.entity.Result;
import com.itheima.health.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：seanyang
 * @date ：Created in 2019/7/13
 * @description ：
 * @version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/web/user")
public class UserController {
	@Reference
	private UserService userService;

	@RequestMapping("/login")
	public Result login(String username,String password){
		System.out.println("9002.login");
		log.debug("oms backend u:{},p:{}",username,password);
		try{
		    if(userService.login(username,password)){
		    	return new Result(true, MessageConst.ACTION_SUCCESS,username);
			}
		}catch(Exception e){
		    e.printStackTrace();
		}
		return new Result(true, MessageConst.ACTION_FAIL);
	}
}
