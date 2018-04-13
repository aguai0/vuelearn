package com.learn.seckill.dto;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * Created by aguai on 2017-07-16.
 */
public class Result <T>{

    private Boolean state;

    private T data;

    private String message;

    public Result(Boolean state, T data) {
        this.state = state;
        this.data = data;
    }

    public Result(Boolean state, String message) {
        this.state = state;
        this.message = message;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
