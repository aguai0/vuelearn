package com.learn.seckill.service.impl;

import com.learn.seckill.cache.JedisDao;
import com.learn.seckill.dao.SeckillDao;
import com.learn.seckill.dao.SuccessKillDao;
import com.learn.seckill.dto.Exposer;
import com.learn.seckill.dto.SeckillExcution;
import com.learn.seckill.entity.Seckill;
import com.learn.seckill.entity.SuccessKilled;
import com.learn.seckill.enums.SeckillStateEnum;
import com.learn.seckill.exception.RepeatKillException;
import com.learn.seckill.exception.SeckillCloseException;
import com.learn.seckill.exception.SeckillException;
import com.learn.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by aguai on 2017-07-10.
 */
@Service
public class SeckillServiceImpl implements SeckillService{
    private  Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String SLAT = "asdfcas!23@@32!#@dsSD232";

    @Autowired
    SeckillDao seckillDao;

    @Autowired
    SuccessKillDao successKillDao;

    @Autowired
    JedisDao jedisDao;

    public List<Seckill> queryAllSeckill() {
        return seckillDao.queryByAll(0,4);
    }

    public Seckill queryById(Long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    public Exposer exportSeckillUrl(Long seckillId) {
        Seckill seckill = jedisDao.getSeckill(seckillId);
        if(seckill == null){
            seckill = seckillDao.queryById(seckillId);
            if (seckill == null) {
                return new Exposer(false, seckillId);
            }else{
                jedisDao.putSeckill(seckill);
            }
        }
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();

        //系统当前时间
        Date nowTime = new Date();
        if (startTime.getTime() > nowTime.getTime() || endTime.getTime() < nowTime.getTime()) {
            return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }

        String md5 = getMd5(seckillId);
        return new Exposer(true,md5,seckillId);
    }

    private String getMd5(Long seckillId){
        String base = seckillId + "/" + SLAT;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }

    @Transactional
    public SeckillExcution excuteSeckill(Long seckillId, Long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException {

        if(StringUtils.isEmpty(md5) || !md5.equals(this.getMd5(seckillId))) {
            throw new SeckillException("seckill data rewrite");
        }
        try {
            //记录购买行为
            Integer insertCount = successKillDao.insertSuccessKilled(seckillId, userPhone);
            if(insertCount <= 0) {
                //重复秒杀
                throw new RepeatKillException("seckill repeated");
            } else {
                //秒杀操作的逻辑减库存，增加购买明显.热点商品竞争
                Integer updateCount = seckillDao.reduceNumber(seckillId, new Date());
                if(updateCount < 0) {
                    //没有更新到记录，秒杀结束
                    throw new SeckillCloseException("seckill is closed");
                } else {
                    //秒少成功
                    SuccessKilled successKilled = this.successKillDao.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExcution(seckillId, SeckillStateEnum.SUCCESS, successKilled);
                }
            }
        } catch (SeckillCloseException e1) {
            throw e1;
        } catch (RepeatKillException e2) {
            throw e2;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            //编译期异常转换为运行时异常
            throw new SeckillException("seckill inner error:" + e.getMessage());
        }
    }
}
