package com.learn.base.web;

import com.learn.common.utils.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseRemoteController {

    /**
     * 子类用logger.
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 返回结果-成功 列表.
     */
    protected Map<String, Object> successList(List<? extends Object> list, Integer total) {
        return Maps.create("datas", list, "total", total);
    }

    /**
     * 返回结果-成功 列表. (去count)
     */
    protected Map<String, Object> successListNoCount(List<? extends Object> list, Integer currentPage) {
        return Maps.create("datas", list, "currentPage", currentPage);
    }

    /**
     * 返回结果-成功 对象.
     */
    protected Map<String, Object> successRe(Object re) {
        return Maps.create("data", re);
    }

    /**
     * 返回结果-成功 对象.
     */
    protected Map<String, Object> success() {
        return new HashMap<String, Object>();
    }

    /**
     * 返回结果-业务错误.
     */
    protected void fail(String msg) {
        throw new RuntimeException(msg);
    }

    /**
     * 返回结果-错误.
     */
    protected void error(String msg) {
        throw new RuntimeException(msg);
    }

}
