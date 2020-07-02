package com.nttdata.seckill.service;

import com.alibaba.fastjson.JSON;
import com.nttdata.seckill.Redis.KeyPrefix;
import com.nttdata.seckill.Redis.RedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @version v1.0
 * @ProjectName: SecKill
 * @ClassName: RedisService
 * @Description: TODO(一句话描述该类的功能)
 * @Author: ${战血石}
 * @Date: 2020/6/30 14:04
 */
@Service
public class RedisService {

    @Autowired
    JedisPool jedisPool;
//    @Autowired
//    RedisConfig redisConfig;
//    public void getConfig(){
//            JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
//            jedisPoolConfig.setMaxIdle(redisConfig.getPoolMaxIdle());
//            jedisPoolConfig.setMaxTotal(redisConfig.getPoolMaxTotal());
//            jedisPoolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait()*1000);
//            JedisPool jp=new JedisPool(jedisPoolConfig,redisConfig.getHost(),redisConfig.port,redisConfig.timeout*1000,redisConfig.getPassword(),0);
//    }
    /***
     * 设置数据
     * @param keyPrefix
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> boolean set(KeyPrefix keyPrefix,String key, T data){
        Jedis jedis=null;
        try{
            jedis= jedisPool.getResource();
            String value=BeanToString(data);
            int expireSeconds=keyPrefix.getExpireSeconds();
            String realKey=keyPrefix.getPrefix()+key;
            if(expireSeconds<=0){
                jedis.set(realKey,value);
            }else{
                jedis.setex(realKey,expireSeconds,value);
            }
            return true;
        }finally {
            returnToPool(jedis);
        }
    }
    /***
     * 取得数据
     * @param keyPrefix
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T get(KeyPrefix keyPrefix,String key,Class<T> clazz){
        Jedis jedis=null;
        try{
            //真正的key
            String realKey=keyPrefix.getPrefix()+key;
            jedis= jedisPool.getResource();
            //取得的值
            String  value=jedis.get(realKey);
            T t=StringToBean(value,clazz);
            return t;
        }catch(Exception ex){
            ex.printStackTrace();
            return  null;
        }finally {
            returnToPool(jedis);
        }
    }
    /***
     * 判断是否存在
     * @param keyPrefix
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> boolean exists(KeyPrefix keyPrefix,String key,Class<T> clazz){
        Jedis jedis=null;
        try{
            //真正的key
            String realKey=keyPrefix.getPrefix()+key;
            return jedis.exists(realKey);
        }finally {
            returnToPool(jedis);
        }
    }

    /***
     * 增1
     * @param keyPrefix
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T>  Long incr(KeyPrefix keyPrefix,String key,Class<T> clazz){
        Jedis jedis=null;
        try{
            //真正的key
            String realKey=keyPrefix.getPrefix()+key;
            return jedis.incr(realKey);
        }finally {
            returnToPool(jedis);
        }
    }
    /***
     * 减1
     * @param keyPrefix
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T>  Long decr(KeyPrefix keyPrefix,String key,Class<T> clazz){
        Jedis jedis=null;
        try{
            //真正的key
            String realKey=keyPrefix.getPrefix()+key;
            return jedis.decr(realKey);
        }finally {
            returnToPool(jedis);
        }
    }

    //关闭jedis
    private void returnToPool(Jedis jedis) {
        if(jedis!=null)
            jedis.close();
    }
    //讲对象转换成字符串
    private <T> String BeanToString(T value){
        if(value==null)
            return null;
        Class<?> clazz=value.getClass();
        if(clazz==int.class||clazz==Integer.class){
            return value+"";
        }else if(clazz==Long.class){
            return value+"";
        }else if(clazz==String.class){
            return value+"";
        }
        return JSON.toJSONString(value);
    }
    //字符串转换成对象
    private <T> T StringToBean(String value,Class<T> clazz){
        if(value==null||value.length()==0||"".equals(value)){
            return null;
        }
        if(clazz==int.class||clazz==Integer.class){
            return (T)Integer.valueOf(value);
        }else if(clazz==Long.class){
            return (T)Long.valueOf(value);
        }else if(clazz==String.class){
            return (T)value;
        }
        return JSON.toJavaObject(JSON.parseObject(value),clazz);
    }

}
