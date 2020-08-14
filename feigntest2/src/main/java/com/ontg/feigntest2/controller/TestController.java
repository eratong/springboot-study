package com.ontg.feigntest2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("/getorder")
    public String getMember() {
        return "this is client2";
    }
}
