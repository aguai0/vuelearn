package com.learn.seckill.dto;

import com.learn.seckill.entity.SuccessKilled;
import com.learn.seckill.enums.SeckillStateEnum;

/**
 *  执行秒杀结果
 * Created by aguai on 2017-07-09.
 */
public class SeckillExcution {

    /**
     * 秒杀ID
     */
    private  Long seckillId;

    //秒杀执行结果的状态
    private int state;

    //状态的明文标识
    private String stateInfo;

    /**
     * 秒杀成功对象
     */
    private SuccessKilled successKilled;

    public SeckillExcution(Long seckillId, SeckillStateEnum stateEnum, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.successKilled = successKilled;
    }

    public SeckillExcution(Long seckillId, SeckillStateEnum stateEnum) {
        this.seckillId = seckillId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    public Long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }

    @Override
    public String toString() {
        return "SeckillExcution{" + "seckillId=" + seckillId + ", state=" + state + ", stateInfo='" + stateInfo + '\'' + ", successKilled=" + successKilled + '}';
    }
}
