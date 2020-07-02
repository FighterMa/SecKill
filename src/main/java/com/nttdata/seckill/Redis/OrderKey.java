package com.nttdata.seckill.Redis;

/**
 * @version v1.0
 * @ProjectName: SecKill
 * @ClassName: OrderKey
 * @Description: TODO(一句话描述该类的功能)
 * @Author: ${战血石}
 * @Date: 2020/6/30 21:11
 */
public class OrderKey extends  BasePrefix{
    public OrderKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public OrderKey(String prefix) {
        super(prefix);
    }
}
