package com.ontg.redis.Exception;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.stereotype.Component;

/**
 * 服务异常捕获处理器
 * <p>
 * 如捕获Service向外抛出的异常
 */
@Component
@Aspect
//@Slf4j
public class ServiceExcepHandler {

    //    @Around("@annotation(com.ahdruid.aop.annotation.ServiceExcepCatch)  || @within(com.ahdruid.aop.annotation.ServiceExcepCatch)")
//    public ReturnMsg serviceExcepHandler(ProceedingJoinPoint proceedingJoinPoint) {
//        ReturnMsg returnMsg = new ReturnMsg();
//        try {
//            returnMsg = (ReturnMsg) proceedingJoinPoint.proceed();
//        } catch (Throwable throwable) {
//            log.error("ServiceExcepHandler serviceExcepHandler failed", throwable);
//            returnMsg.setError(BaseErrorEnum.SYS_ERROR_UNKNOW);
//        }
//        return returnMsg;
//    }
    @Around("@annotation(com.ontg.redis.Exception.ServiceExcepCatch)  || @within(com.ontg.redis.Exception.ServiceExcepCatch)")
    public String serviceExcepHandler(ProceedingJoinPoint proceedingJoinPoint) {
//        ReturnMsg returnMsg = new ReturnMsg();
//        try {
//            returnMsg = (ReturnMsg) proceedingJoinPoint.proceed();
//        } catch (Throwable throwable) {
//            log.error("ServiceExcepHandler serviceExcepHandler failed", throwable);
//            returnMsg.setError(BaseErrorEnum.SYS_ERROR_UNKNOW);
//        }
        System.out.println("serviceExcepHandler start ------");
        try {
            proceedingJoinPoint.proceed();
//            System.out.println(proceedingJoinPoint.proceed());
//            System.out.println(JSON.toJSONString(proceedingJoinPoint.proceed()));
        } catch (Throwable throwable) {
            System.out.println(throwable instanceof QueryTimeoutException);
            if(throwable instanceof QueryTimeoutException){
                System.out.println("QueryTimeoutException handler");
            }
        }
        return "returnMsg";
    }

}