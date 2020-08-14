package com.ontg.fegintest.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "member")          //指定被调用应用的名字（启动应用时像注册中心注册的名字）
public interface MemberApifeign {

    @RequestMapping("/getMember")  //  把member项目中被调用的接口摘抄过来
    public String getMember();
}