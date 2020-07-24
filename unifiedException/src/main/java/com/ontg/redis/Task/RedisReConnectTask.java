package com.ontg.redis.Task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * 处理redis重连
 */

@Component
public class RedisReConnectTask {
    private static final Logger logger = LoggerFactory.getLogger(RedisReConnectTask.class);
    @Autowired
    private RedisTemplate redisTemplate;

    private final Object lock = new Object();
    private int  attempts=0;//当前尝试次数
    private int count=10;//需要重连次数

    @Cacheable(cacheNames = "test:hello")
    public String testcache(String name) {
        System.out.println("invoke...");
        return "hello " + name;
    }


    public void runRedisHeart() {

        synchronized (lock){
            Timer timer = new Timer();
            TimerTask timerTask= new TimerTask() {
                @Override
                public  void run() {
                    try {
                        for (; attempts < count+1; attempts++) {
                            if(attempts==count){
                                //重连十次失败,优雅关闭程序
//                                System.out.println(Main.SIGTERM.get());
//                                Main.SIGTERM.set(true);
                                Thread.sleep(10000);
                                System.exit(0);
                                timer.cancel();
                                break;
                            }
                            Thread.sleep(60000);
                            logger.info("runRedisHeart try "+(1+attempts)+" time-----");
                            //尝试重连
                            try {
                                BoundValueOperations redisHeart = redisTemplate.boundValueOps("redisHeart");
                                redisHeart.set("redisHeart",30, TimeUnit.SECONDS);
                                logger.info("redis reconnect success-----");
                                timer.cancel();
                                attempts=0;
                                break;//重连上了
                            } catch (Exception e) {
                                logger.info("redis reconnect error-----"+e.getMessage());
                            }
                        }
                    } catch (Exception e) {
                        logger.info("runRedisHeart error"+e.getMessage());
                    }
                }
            };
            //执行
            timer.schedule(timerTask,0);

        }

    }
}
