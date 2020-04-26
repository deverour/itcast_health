package com.itheima.health.mobile.controller;


import com.itheima.health.common.MessageConst;
import com.itheima.health.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/mobile/validateCode")
public class ValidateCodeController {

    @RequestMapping("/send4Order")
    public Result send4Order(String telephone){

        try {
            log.debug(">>>>> telephone:{}",telephone);
            String validateCode = "8888";
            return new Result(true, MessageConst.SEND_VALIDATECODE_SUCCESS);

        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConst.SEND_VALIDATECODE_FAIL);
        }

    }
}
