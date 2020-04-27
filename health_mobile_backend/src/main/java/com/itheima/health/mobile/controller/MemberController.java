package com.itheima.health.mobile.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.common.MessageConst;
import com.itheima.health.entity.Result;
import com.itheima.health.service.MemberService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/mobile/member")
public class MemberController {

    @Reference
    private MemberService memberService;

    @RequestMapping("/smsLogin")
    public Result smsLogin(@RequestBody Map<String,String> map){
        try {
            String telephone = map.get("telephone");
            String code = map.get("validateCode");
            System.out.println("code"+code);

            if (!code.equals("8888")){
                return new Result(false,MessageConst.VALIDATECODE_ERROR);
            }
            memberService.smsLogin(telephone);
            return new Result(true,MessageConst.LOGIN_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConst.ACTION_FAIL);
        }
    }
}
