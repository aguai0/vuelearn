package com.learn.seckill.dao;

import com.learn.seckill.entity.Seckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by aguai on 2017-07-02.
 */
public interface SeckillDao {

    /**
     * 减库存操作
     * @param seckillId
     * @param seckillTime
     * @return
     */
    Integer reduceNumber(@Param("seckillId") Long seckillId, @Param("seckillTime") Date seckillTime);

    /**
     * 根据I的查询秒杀信息
     * @param seckillId
     * @return
     */
    Seckill queryById(Long seckillId);

    /**
     *  查询所有秒杀记录
     * @param offset
     * @param limit
     * @return
     */
    List<Seckill> queryByAll(@Param("offset") Integer offset, @Param("limit") Integer limit);
}
