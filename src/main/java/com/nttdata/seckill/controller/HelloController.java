package com.nttdata.seckill.controller;

import com.nttdata.seckill.Redis.UserKey;
import com.nttdata.seckill.VO.LoginVO;
import com.nttdata.seckill.common.CodeMsg;
import com.nttdata.seckill.common.ResponseResult;
import com.nttdata.seckill.domain.User;
import com.nttdata.seckill.service.IUserService;
import com.nttdata.seckill.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @version v1.0
 * @ProjectName: SecKill
 * @ClassName: HelloController
 * @Description: TODO(一句话描述该类的功能)
 * @Author: ${战血石}
 * @Date: 2020/6/29 9:31
 */
@Controller()
@RequestMapping("/seckill")
public class HelloController {
    @Autowired
    IUserService iUserService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/hello")
    @ResponseBody
    public ResponseResult<String> hello() {
        return ResponseResult.success("Hello ,我是数据");
    }

    @RequestMapping("/errorhello")
    @ResponseBody
    public ResponseResult<String> errorhello() {
        return ResponseResult.error(CodeMsg.ERROR);
    }

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("name","thymeleaf");
        return "thymeleaf";
    }

    @RequestMapping("/db/getUser")
    @ResponseBody
    public ResponseResult<User>  getUser(int id) {
        User user= iUserService.getUserById(1);
        if(user==null ||"".equals(user.getId())){
            return ResponseResult.error(CodeMsg.ERROR);
        }
        return ResponseResult.success(user);
    }

    @RequestMapping("/db/insert")
    @ResponseBody
    public ResponseResult<Boolean>  insertUser() {
        return ResponseResult.success(iUserService.insertUser());
    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public ResponseResult<Long>  redisget() {
        Long v1=redisService.get(UserKey.getById,"key1",Long.class);
        return ResponseResult.success(v1);
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public ResponseResult<String>  redisset() {
        redisService.set(UserKey.getById,"key1","111");
        String v1=redisService.get(UserKey.getById,"key1",String.class);
        return ResponseResult.success(v1);
    }

    @RequestMapping("/to_login")
    public String to_login() {
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public String do_login(LoginVO loginVO) {
        System.out.println(loginVO.getMobile()+":"+loginVO.getPassword());
        return "login";
    }

}
