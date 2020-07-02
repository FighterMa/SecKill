package com.nttdata.seckill.util;

import com.nttdata.seckill.common.Constant;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * @version v1.0
 * @ProjectName: SecKill
 * @ClassName: MD5Util
 * @Description: TODO(一句话描述该类的功能)
 * @Author: ${战血石}
 * @Date: 2020/6/30 22:34
 */
public class MD5Util {

    public static String md5(String passWord){
        return DigestUtils.md5Hex(passWord);
    }
    //第一层加密，界面数据加密
    public static String inputToForm(String passWord){
        return md5(Constant.PasswordPrefix+passWord);
    }
    //第二层加密，入库数据加密
    public static String fromToDB(String passWord){
        return md5(passWord+Constant.PasswordSuffix);
    }

    public static String inputToDB(String passWord){
        return  fromToDB(inputToForm(passWord));
    }

}
