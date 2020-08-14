package com.ontg.fegintest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("/getMember")
    public String getMember() {
        return "this is client1";
    }

    @Autowired
    private MemberApifeign memberApifeign;//把2.2编写的接口注入进来

    @RequestMapping("/getorder")
    public String getOrder() {
        String result = memberApifeign.getMember();//调用2.2 中编写的接口
        return result;
    }
}
