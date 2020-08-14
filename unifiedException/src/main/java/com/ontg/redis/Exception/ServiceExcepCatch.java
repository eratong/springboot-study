package com.ontg.redis.Exception;

import java.lang.annotation.*;

/**
 * 服务异常捕获，如捕获Service向外抛出的异常
 * <p>
 * 添加在类上、方法上
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceExcepCatch {
}
