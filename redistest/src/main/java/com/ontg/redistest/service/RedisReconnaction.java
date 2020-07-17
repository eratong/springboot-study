package com.ontg.redistest.service;

import org.springframework.stereotype.Service;

import java.util.Timer;
import java.util.TimerTask;

@Service
public class RedisReconnaction {

    public void sn(){
        Timer timer = new Timer();
        TimerTask timerTask= new TimerTask() {
            @Override
            public  void run() {
                try {

                    for (int i = 0; i < 10; i++) {
                        System.out.println("runRedisHeart try "+i+" time-----");

                        if (i > 10) {
                            //优雅关闭程序
                            timer.cancel();
                        }
                        //尝试重连
//                        System.out.println("try");
                        try {
                            System.out.println("try");
                          throw  new Exception("error");
//                            timer.cancel();//重连上了
                        } catch (Exception e) {
                            System.out.println("redis reconnect error-----"+e.getMessage());
                        }
//                        System.out.println("ssssss");
                        Thread.sleep(6000);
                    }
                } catch (Exception e) {
                    System.out.println("runRedisHeart error"+e.getMessage());
                }
            }
        };
        //执行
        timer.schedule(timerTask,0);
    }
}
