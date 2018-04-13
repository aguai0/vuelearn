package com.learn.seckill.dao;

import com.learn.seckill.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

/**
 * Created by aguai on 2017-07-02.
 */
public interface SuccessKillDao {

    /**
     * 插入秒杀成功记录
     *
     * @param seckillId
     * @param userPhone
     * @return
     */
    Integer insertSuccessKilled(@Param("seckillId") Long seckillId, @Param("userPhone") Long userPhone);

    /**
     * 根据Id查询秒杀成功记录，并且携带秒杀实体对象Seckill
     *
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") Long seckillId, @Param("userPhone") Long userPhone);
}
