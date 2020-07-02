package com.nttdata.seckill.Redis;

/**
 * @version v1.0
 * @ProjectName: SecKill
 * @ClassName: KeyPrefix
 * @Description: TODO(一句话描述该类的功能)
 * @Author: ${战血石}
 * @Date: 2020/6/30 18:18
 */
public interface KeyPrefix {
    public int getExpireSeconds();

    public String getPrefix();
}
