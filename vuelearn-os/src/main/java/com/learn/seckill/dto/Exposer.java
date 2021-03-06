package com.learn.seckill.dto;

import java.util.Date;

/**
 * Created by aguai on 2017-07-09.
 */
public class Exposer {

    /**
     * 是否开启秒杀
     */
    private Boolean exposed;

    /**
     *  加密方式
     */
    private String md5;

    /**
     * 秒杀ID
     */
    private Long seckillId;

    /**
     * 系统时间
     */
    private Long nowTime;

    /**
     * 秒杀开启时间
     */
    private Long startTime;

    /**
     * 秒杀结束时间
     */
    private Long endTime;

    public Exposer(Boolean exposed, Long seckillId) {
        this.exposed = exposed;
        this.seckillId = seckillId;
    }

    public Exposer(Boolean exposed, String md5, Long seckillId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.seckillId = seckillId;
    }

    public Exposer(Boolean exposed, Long seckillId, Long nowTime, Long startTime, Long endTime) {
        this.exposed = exposed;
        this.seckillId = seckillId;
        this.nowTime = nowTime;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Boolean getExposed() {
        return exposed;
    }

    public void setExposed(Boolean exposed) {
        this.exposed = exposed;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }

    public Long getNowTime() {
        return nowTime;
    }

    public void setNowTime(Long nowTime) {
        this.nowTime = nowTime;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Exposer{" +
                "exposed=" + exposed +
                ", md5='" + md5 + '\'' +
                ", seckillId=" + seckillId +
                ", nowTime=" + nowTime +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
