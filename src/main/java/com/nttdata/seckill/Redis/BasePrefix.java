package com.nttdata.seckill.Redis;

/**
 * @version v1.0
 * @ProjectName: SecKill
 * @ClassName: BasePrefix
 * @Description: TODO(一句话描述该类的功能)
 * @Author: ${战血石}
 * @Date: 2020/6/30 18:25
 */
public abstract class BasePrefix implements KeyPrefix {
    private int expireSeconds;
    private String prefix;

    public BasePrefix(String prefix) {//永不过期前缀
        this.expireSeconds = 0;
        this.prefix = prefix;
    }

    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    @Override
    public int getExpireSeconds() {//默认0代表永不过期
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        String className=getClass().getSimpleName();
        return className+":"+prefix;
    }
}
