package com.learn.seckill.service;

import com.learn.seckill.dto.Exposer;
import com.learn.seckill.dto.SeckillExcution;
import com.learn.seckill.entity.Seckill;
import com.learn.seckill.exception.RepeatKillException;
import com.learn.seckill.exception.SeckillCloseException;
import com.learn.seckill.exception.SeckillException;

import java.util.List;

/**
 * Created by aguai on 2017-07-09.
 */
public interface SeckillService {

    /**
     * 查询所有秒杀记录
     *
     * @return
     */
    List<Seckill> queryAllSeckill();

    /**
     * 查询单个秒杀记录
     *
     * @param seckill
     * @return
     */
    Seckill queryById(Long seckill);

    /**
     * 秒杀开启时输出秒杀接口地址，否则输出系统时间和秒杀时间
     * @param seckill
     * @return
     */
    Exposer exportSeckillUrl(Long seckill);

    /**
     * 秒杀执行操作
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     * @throws SeckillException
     * @throws RepeatKillException
     * @throws SeckillCloseException
     */
    SeckillExcution excuteSeckill(Long seckillId, Long userPhone, String md5)
            throws SeckillException,RepeatKillException,SeckillCloseException;

}
