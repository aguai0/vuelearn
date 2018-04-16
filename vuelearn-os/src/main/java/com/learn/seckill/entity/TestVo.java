package com.learn.seckill.entity;

import com.learn.base.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * Description:
 * All Rights Reserved.
 *
 */
public class TestVo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -8900653271436852225L;

    private String name;

    private Date startTime;

    private Date endTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}