package com.nttdata.seckill.Redis;

/**
 * @version v1.0
 * @ProjectName: SecKill
 * @ClassName: UserKey
 * @Description: TODO(一句话描述该类的功能)
 * @Author: ${战血石}
 * @Date: 2020/6/30 21:10
 */
public class UserKey extends BasePrefix {
    public UserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public UserKey(String prefix) {
        super(prefix);
    }

    public static UserKey getById=new UserKey("id");
    public static UserKey getByName=new UserKey("name");

}
