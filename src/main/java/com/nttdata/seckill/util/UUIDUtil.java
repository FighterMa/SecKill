package com.nttdata.seckill.util;

import java.util.UUID;

/**
 * @version v1.0
 * @ProjectName: SecKill
 * @ClassName: UUIDUtil
 * @Description: TODO(一句话描述该类的功能)
 * @Author: ${战血石}
 * @Date: 2020/7/1 21:05
 */
public class UUIDUtil {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
