package com.learn.base.entity;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity implements Serializable{
    private static final long serialVersionUID = -4146248420367702680L;

    protected Date createTime;
    protected Date modifyTime;
    protected Long createEmp;
    protected Long modifyEmp;
    protected String createEmpName;
    protected String modifyEmpName;
    protected Integer createType;
    protected Integer modifyType;
    protected Integer currentPage = Integer.valueOf(1);
    protected Integer pageSize = Integer.valueOf(20);
    protected Integer startIndex;
    protected Integer endIndex;
    protected String orderByColumn;

    public BaseEntity() {
    }

    public Integer getStartIndex() {
        if(this.startIndex == null) {
            this.setStartIndex(Integer.valueOf((this.currentPage.intValue() - 1) * this.pageSize.intValue()));
        }

        return this.startIndex;
    }

    public Integer getEndIndex() {
        if(this.endIndex == null) {
            this.setEndIndex(Integer.valueOf(this.getStartIndex().intValue() + this.pageSize.intValue()));
        }

        return this.endIndex;
    }

    public Integer getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return this.modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Long getCreateEmp() {
        return this.createEmp;
    }

    public void setCreateEmp(Long createEmp) {
        this.createEmp = createEmp;
    }

    public Long getModifyEmp() {
        return this.modifyEmp;
    }

    public void setModifyEmp(Long modifyEmp) {
        this.modifyEmp = modifyEmp;
    }

    public String getCreateEmpName() {
        return this.createEmpName;
    }

    public void setCreateEmpName(String createEmpName) {
        this.createEmpName = createEmpName;
    }

    public String getModifyEmpName() {
        return this.modifyEmpName;
    }

    public void setModifyEmpName(String modifyEmpName) {
        this.modifyEmpName = modifyEmpName;
    }

    public Integer getCreateType() {
        return this.createType;
    }

    public void setCreateType(Integer createType) {
        this.createType = createType;
    }

    public Integer getModifyType() {
        return this.modifyType;
    }

    public void setModifyType(Integer modifyType) {
        this.modifyType = modifyType;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public void setEndIndex(Integer endIndex) {
        this.endIndex = endIndex;
    }

    public String getOrderByColumn() {
        return this.orderByColumn;
    }

    public void setOrderByColumn(String orderByColumn) {
        this.orderByColumn = orderByColumn;
    }
}