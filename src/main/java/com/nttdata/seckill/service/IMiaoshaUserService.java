package com.nttdata.seckill.service;

import com.nttdata.seckill.VO.LoginVO;
import com.nttdata.seckill.common.CodeMsg;
import com.nttdata.seckill.domain.MiaoshaUser;

import javax.servlet.http.HttpServletResponse;

/**
 * @version v1.0
 * @ProjectName: SecKill
 * @ClassName: IMiaoshaUserService
 * @Description: TODO(一句话描述该类的功能)
 * @Author: ${战血石}
 * @Date: 2020/7/1 14:37
 */
public interface IMiaoshaUserService {
    public MiaoshaUser getMiaoshaUserById(String id);
    public CodeMsg Login(HttpServletResponse response,LoginVO loginVO);
    public MiaoshaUser getMiaoshaUserByToken(HttpServletResponse response,String token);
}
