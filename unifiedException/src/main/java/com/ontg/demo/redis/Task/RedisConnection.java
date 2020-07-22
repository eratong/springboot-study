package com.ontg.demo.redis.Task;

import com.ontg.demo.redis.Exception.RedisCustomExecption;
import com.ontg.demo.redis.UnifiedMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class RedisConnection {

    private final static Logger logger = LoggerFactory.getLogger(RedisConnection.class);

    private volatile int count = 0;
    @Autowired
    private RedisTemplate redisTemplate;


    @Scheduled(cron = "*/5 * * * * ?")
    public void run() {

        logger.info("RedisConnection start -------");
        try {
            BoundValueOperations test2 = redisTemplate.boundValueOps("test2");
            test2.set("test2");
        } catch (Exception e) {
            System.out.println("save");
//            channelInactive

        }
    }

    //    public void stop(){
//        System.exit(0);
//    }
    private  static ConfigurableApplicationContext run=null;

    public void stop() {

        run.stop();
    }
}
