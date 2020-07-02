package com.nttdata.seckill.service.impl;

import com.nttdata.seckill.Redis.MiaoshaUserKey;
import com.nttdata.seckill.VO.LoginVO;
import com.nttdata.seckill.common.CodeMsg;
import com.nttdata.seckill.common.Constant;
import com.nttdata.seckill.dao.MiaoshaUserDAO;
import com.nttdata.seckill.domain.MiaoshaUser;
import com.nttdata.seckill.service.IMiaoshaUserService;
import com.nttdata.seckill.service.RedisService;
import com.nttdata.seckill.util.MD5Util;
import com.nttdata.seckill.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @version v1.0
 * @ProjectName: SecKill
 * @ClassName: MiaoshaUserService
 * @Description: TODO(一句话描述该类的功能)
 * @Author: ${战血石}
 * @Date: 2020/7/1 14:37
 */
@Service
public class MiaoshaUserService implements IMiaoshaUserService {
    @Autowired
    MiaoshaUserDAO miaoshaUserDAO;
    @Autowired
    RedisService redisService;

    @Override
    public MiaoshaUser getMiaoshaUserById(String id) {
        return miaoshaUserDAO.getMiaoshaUserById(id);
    }

    @Override
    public CodeMsg Login(HttpServletResponse response, LoginVO loginVO){
        //账号是否存在
        MiaoshaUser miaoshaUser=miaoshaUserDAO.getMiaoshaUserById(loginVO.getMobile());
        if(miaoshaUser==null)
            return CodeMsg.MOBILE_NOTEXISTS;
        //密码是否错误
        String password= MD5Util.fromToDB(loginVO.getPassword());
        if(!password.equals(miaoshaUser.getPassword())){
            return CodeMsg.PASSWORD_ERROR;
        }
        addCookie(response,miaoshaUser);
        return CodeMsg.SUCCESS;
    }

    @Override
    public MiaoshaUser getMiaoshaUserByToken(HttpServletResponse response,String token) {
        if(StringUtils.isEmpty(token))
            return null;
        MiaoshaUser miaoshaUser=redisService.get(MiaoshaUserKey.tokenPrefix,token,MiaoshaUser.class);
        if(miaoshaUser!=null) {
            addCookie(response, miaoshaUser);
            return miaoshaUser;
        }
        return null;
    }

    private void addCookie(HttpServletResponse response,MiaoshaUser miaoshaUser){
        //随机生成token
        String token= UUIDUtil.getUUID();
        redisService.set(MiaoshaUserKey.tokenPrefix,token,miaoshaUser);
        Cookie cookie=new Cookie(Constant.COOKIE_NAME_TOKEN,token);
        cookie.setMaxAge(MiaoshaUserKey.tokenPrefix.getExpireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
        MiaoshaUser getmiaoshaUser=redisService.get(MiaoshaUserKey.tokenPrefix,token,MiaoshaUser.class);
    }
}
