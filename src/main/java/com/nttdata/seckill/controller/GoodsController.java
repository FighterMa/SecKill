package com.nttdata.seckill.controller;

import com.nttdata.seckill.VO.LoginVO;
import com.nttdata.seckill.common.CodeMsg;
import com.nttdata.seckill.common.Constant;
import com.nttdata.seckill.common.ResponseResult;
import com.nttdata.seckill.domain.MiaoshaUser;
import com.nttdata.seckill.service.IMiaoshaUserService;
import com.nttdata.seckill.service.IUserService;
import com.nttdata.seckill.service.RedisService;
import com.nttdata.seckill.util.ValidateUtil;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @version v1.0
 * @ProjectName: SecKill
 * @ClassName: HelloController
 * @Description: TODO(一句话描述该类的功能)
 * @Author: ${战血石}
 * @Date: 2020/6/29 9:31
 */
@Controller()
@RequestMapping("/goods")
public class GoodsController {

    private static Logger logger =Logger.getLogger(GoodsController.class);
    @Autowired
    IUserService iUserService;

    @Autowired
    RedisService redisService;

    @Autowired
    IMiaoshaUserService iMiaoshaUserService;

    @RequestMapping("/to_list")
    public String to_list(HttpServletResponse response,Model model,
                          @CookieValue(value= Constant.COOKIE_NAME_TOKEN,required = false)String cookieToken,
                          @RequestParam(value= Constant.COOKIE_NAME_TOKEN,required = false)String paramToken) {
        if(org.springframework.util.StringUtils.isEmpty(cookieToken)&&org.springframework.util.StringUtils.isEmpty(paramToken))
            return "login";
        String token= !org.springframework.util.StringUtils.isEmpty(cookieToken)?cookieToken:paramToken;
        MiaoshaUser miaoshaUser=iMiaoshaUserService.getMiaoshaUserByToken(response,token);
        model.addAttribute("user",miaoshaUser);
        return "goods_list";
    }
}
