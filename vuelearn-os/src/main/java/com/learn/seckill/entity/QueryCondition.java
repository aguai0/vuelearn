package com.learn.seckill.entity;

import com.learn.base.entity.BaseEntity;

import java.io.Serializable;

/**
 * Description:
 * All Rights Reserved.
 *
 */
public class QueryCondition extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -2679137143782064902L;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}