package com.ontg.redis.Task;

import com.ontg.redis.service.RedisTimeOutHandler;
import io.lettuce.core.RedisCommandTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class RedisConnection {

    private final static Logger logger = LoggerFactory.getLogger(RedisConnection.class);

    private volatile int count = 0;
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisTimeOutHandler redisTimeOutHandler;


    @Autowired
    private RedisReConnectTask redisReConnectTask;

    @Scheduled(cron = "*/5 * * * * ?")
    public void run() {

        logger.info("RedisConnection start -------");
        redisReConnectTask.testcache("r");
//        throw new RuntimeException("ssssss");
//        BoundValueOperations test2 = redisTemplate.boundValueOps("test2");
//        test2.set("test2");
        try {
            BoundValueOperations test2 = redisTemplate.boundValueOps("test2");
            test2.set("test2");
        } catch (Exception e) {
            System.out.println(e);

//            redisTimeOutHandler.redisTimeOut(e);

        }

//        try {
//        } catch (Exception e) {
//            if(e instanceof RedisCommandTimeoutException){
//                throw new RedisCommandTimeoutException();
//            }
//        }
    }

    //    public void stop(){
//        System.exit(0);
//    }
    private  static ConfigurableApplicationContext run=null;

    public void stop() {

        run.stop();
    }

//    private volatile int attempts;
//    @Scheduled(cron = "*/5 * * * * ?")
//    public void s(){
//
//        try {
////            ConnectionWatchdog.run(1);
//            System.out.println(attempts);
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


}
