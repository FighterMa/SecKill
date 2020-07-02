package com.nttdata.seckill.Redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @version v1.0
 * @ProjectName: SecKill
 * @ClassName: RedisPoolFactory
 * @Description: TODO(一句话描述该类的功能)
 * @Author: ${战血石}
 * @Date: 2020/6/30 14:59
 */
@Service
public class RedisPoolFactory {
    @Autowired
    RedisConfig redisConfig;

    @Bean
    public JedisPool JedisPoolFactory(){
        JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(redisConfig.getPoolMaxIdle());
        jedisPoolConfig.setMaxTotal(redisConfig.getPoolMaxTotal());
        jedisPoolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait()*1000);
        JedisPool jp=new JedisPool(jedisPoolConfig,redisConfig.getHost(),redisConfig.port,redisConfig.timeout*1000,redisConfig.getPassword(),0);
        return jp;
    }
}
