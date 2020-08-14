package com.ontg.redis.Exception;

public class RedisCustomExecption extends RuntimeException {

//    //异常错误码
//    private int code;
//    //异常信息
//    private String msg;
//    //异常构造方法 在使用时方便传入错误码和信息
//    public MyException(int code, String msg) {
//        this.code = code;
//        this.msg = msg;
//    }

//    public RedisCustomExecption(){
//        super();
//    }

    private String msg;

    public RedisCustomExecption(String msg) {
        this.msg = msg;
    }
}
