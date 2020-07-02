package com.nttdata.seckill.Redis;

/**
 * @version v1.0
 * @ProjectName: SecKill
 * @ClassName: UserKey
 * @Description: TODO(一句话描述该类的功能)
 * @Author: ${战血石}
 * @Date: 2020/6/30 21:10
 */
public class MiaoshaUserKey extends BasePrefix {
    private static int EXPIRE_SECONDS=3600*2;
    public MiaoshaUserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public MiaoshaUserKey(String prefix) {
        super(prefix);
    }

    public static MiaoshaUserKey tokenPrefix=new MiaoshaUserKey(EXPIRE_SECONDS,"token");

}
