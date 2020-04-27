package com.itheima.health.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @PreAuthorize("hasAnyAuthority('add')")
    @RequestMapping("/addData")
    public String addData(){
        return "add ok";
    }

    @PreAuthorize("hasAnyAuthority('update')")
    @RequestMapping("/updateData")
    public String updatedData(){
        return "update ok";
    }

    @PreAuthorize("hasAnyAuthority('delete')")
    @RequestMapping("/deleteData")
    public String deletedData(){
        return "delete ok";
    }

    @PreAuthorize("hasAnyAuthority('find')")
    @RequestMapping("/findData")
    public String findData(){
        return "find ok";
    }
}
