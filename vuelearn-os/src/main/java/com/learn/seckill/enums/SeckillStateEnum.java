package com.learn.seckill.enums;

import com.alibaba.fastjson.JSON;

/**
 * Created by aguai on 2017-07-10.
 */
public enum  SeckillStateEnum {
    SUCCESS(1,"秒杀成功"),
    END(2,"秒杀结束"),
    REPEAT_KILL(-1,"重复秒杀"),
    INNER_ERROR(-2,"系统异常"),
    DATA_REWRITE(-3,"数据篡改");
    private int state;

    private String stateInfo;

    SeckillStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static SeckillStateEnum getStateOf(int index) {
        for (SeckillStateEnum stateEnum : values()) {
            if (stateEnum.getState() == index) {
                return stateEnum;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        for(SeckillStateEnum stateEnum:  SeckillStateEnum.values()){
            System.out.println("stateEnum:"+ JSON.toJSONString(stateEnum));
            for(int i = 0; i < 3; i++) {
                int t=0;
                switch(stateEnum){
                    case SUCCESS :
                        System.out.println("1-秒杀成功");
                        t=1*i;
                        break;
                    case END :
                        System.out.println("2-秒杀结束");
                        t=2*i;
                        break;
                    case REPEAT_KILL :
                        System.out.println("3-重复秒杀");
                        t=3*i;
                        break;
                    case INNER_ERROR:
                        System.out.println("4-系统异常");
                        t=4*i;
                        break;
                    case DATA_REWRITE:
                        System.out.println("4-数据篡改");
                        t=5*i;
                        break;
                }
                System.out.println("t:"+t);
            }
        }
    }
}
