package com.learn.seckill.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.learn.seckill.entity.Seckill;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


/**
 * Created by aguai on 2017-08-14.
 */
public class JedisDao {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(JedisDao.class);
    private final JedisPool jedisPool;

    /**
     * 序列化对象
     */
    private RuntimeSchema<Seckill> runtimeSchema = RuntimeSchema.createFrom(Seckill.class);

    public JedisDao(String ip, int port) {
        jedisPool = new JedisPool(ip, port);
    }

    /**
     * 通过key从缓存中获取对象信息
     *
     * @param seckillId
     * @return
     */
    public Seckill getSeckill(long seckillId) {

        try {

            Jedis jedis = jedisPool.getResource();
            try {
                String key = "seckill:" + seckillId;
                byte[] bytes = jedis.get(key.getBytes());
                //bytes不等于null说明redis缓存中存在数据
                if(bytes != null) {
                    //获得一个空对象
                    Seckill seckill = runtimeSchema.newMessage();
                    //反序列化操作，获取对象信息
                    ProtostuffIOUtil.mergeFrom(bytes, seckill, runtimeSchema);

                    return seckill;
                }
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 对象put到缓存中
     *
     * @param seckill
     * @return
     */
    public String putSeckill(Seckill seckill) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "seckill:" + seckill.getSeckillId();
                //把一个对象序列化成一个字节数组
                byte[] bytes = ProtostuffIOUtil.toByteArray(seckill, runtimeSchema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                int timeout = 60 * 60;
                String result = jedis.setex(key.getBytes(), timeout, bytes);

                return result;
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
