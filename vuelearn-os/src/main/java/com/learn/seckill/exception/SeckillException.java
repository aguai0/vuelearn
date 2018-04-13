package com.learn.seckill.exception;

/**
 * 秒杀相关业务异常
 * Created by aguai on 2017-07-09.
 */
public class SeckillException extends RuntimeException {

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
