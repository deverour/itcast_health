package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.health.dao.MemberDao;
import com.itheima.health.pojo.Member;
import com.itheima.health.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Date;


@Service
@Slf4j
public class MemberServiceImpl implements MemberService {


    @Autowired
    private MemberDao memberDao;
    @Override
    public void smsLogin(String telephone) {
        log.debug(">>>> telephone:{}",telephone);
        Member member = memberDao.findByTelephone(telephone);
        if (member == null){
            member = new Member();
            member.setPhoneNumber(telephone);
            member.setRegTime(new Date());
            memberDao.add(member);
        }
    }
}
