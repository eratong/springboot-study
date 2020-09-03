package com.ontg.springbean.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Test11 {

    String test2="test1";

    public void s(){
        System.out.println("Test11======"+test2);
    }
}
