package com.learn.seckill.exception;

/**
 * 重复秒杀异常（运行时异常）
 * Created by aguai on 2017-07-09.
 */
public class RepeatKillException extends SeckillException {

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
