package com.nttdata.seckill.controller;

import com.nttdata.seckill.VO.LoginVO;
import com.nttdata.seckill.common.CodeMsg;
import com.nttdata.seckill.common.ResponseResult;
import com.nttdata.seckill.service.IMiaoshaUserService;
import com.nttdata.seckill.service.IUserService;
import com.nttdata.seckill.service.RedisService;
import com.nttdata.seckill.service.impl.MiaoshaUserService;
import com.nttdata.seckill.util.ValidateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/login")
public class LoginController {

    private static Logger logger =Logger.getLogger(LoginController.class);
    @Autowired
    IUserService iUserService;

    @Autowired
    RedisService redisService;

    @Autowired
    IMiaoshaUserService iMiaoshaUserService;

    @RequestMapping("/to_login")
    public String to_login() {
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public ResponseResult<Boolean> do_login(HttpServletResponse response, LoginVO loginVO) {

        logger.info(loginVO.toString());
        CodeMsg errValidate=Login(response,loginVO);

        if(errValidate.getCode()==0){
            return ResponseResult.success(true);
        }else{
            return ResponseResult.error(errValidate);
        }
    }

    /***
     * 登录接口
     * @param loginVO
     * @return
     */
    public CodeMsg Login(HttpServletResponse response,LoginVO loginVO) {
        String mobile=loginVO.getMobile();
        String password=loginVO.getPassword();
        //校验数据格式
        CodeMsg errValidate=Volatile(mobile,password);

        if(errValidate!=null)
            return errValidate;
        return iMiaoshaUserService.Login(response,loginVO);
    }



    /***
     * 输入校验
     * @param mobile
     * @param password
     * @return
     */
    private CodeMsg Volatile(String mobile,String  password){
        CodeMsg mobileCodeMsg= ValidateUtil.ValidateMobile(mobile);
        if(mobileCodeMsg!=null)
            return mobileCodeMsg;
        CodeMsg passwordCodeMsg= ValidateUtil.ValidatePassword(password);
        if(passwordCodeMsg!=null)
            return passwordCodeMsg;
        return null;
    }

}
