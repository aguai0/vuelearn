package com.learn.seckill.exception;

/**
 * 秒杀关闭异常
 * Created by aguai on 2017-07-09.
 */
public class SeckillCloseException extends SeckillException {
    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
