package com.nttdata.seckill.util;

import com.nttdata.seckill.common.CodeMsg;

/**
 * @version v1.0
 * @ProjectName: SecKill
 * @ClassName: ValidateUtil
 * @Description: TODO(一句话描述该类的功能)
 * @Author: ${战血石}
 * @Date: 2020/7/1 13:58
 */
public class ValidateUtil {
    public static CodeMsg ValidateMobile(String mobile){
        if(mobile==null ||"".equals(mobile))
            return  CodeMsg.MOBILE_EMPTY;
        if(mobile.length()!=11)
            return  CodeMsg.MOBILE_LENGTH;
        return null;
    }

    public static CodeMsg ValidatePassword(String password){
        if(password==null ||"".equals(password))
            return  CodeMsg.PASSWORD_EMPTY;
        if(password.length()<6)
            return  CodeMsg.PASSWORD_LENGTH;
        return null;
    }
}
