package com.nttdata.seckill.config;

import com.nttdata.seckill.common.Constant;
import com.nttdata.seckill.domain.MiaoshaUser;
import com.nttdata.seckill.service.IMiaoshaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @version v1.0
 * @ProjectName: SecKill
 * @ClassName: UserArgumentResolver
 * @Description: TODO(一句话描述该类的功能)
 * @Author: ${战血石}
 * @Date: 2020/7/2 11:33
 */
@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    IMiaoshaUserService iMiaoshaUserService;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class<?> clazz=methodParameter.getParameterType();
        return clazz== MiaoshaUser.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  @Nullable ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  @Nullable WebDataBinderFactory webDataBinderFactory
                                  ) throws Exception {
        HttpServletRequest request= (HttpServletRequest)nativeWebRequest.getNativeRequest();
        HttpServletResponse response= (HttpServletResponse)nativeWebRequest.getNativeResponse();
        String paramToken=request.getParameter(Constant.COOKIE_NAME_TOKEN);
        String cookieToken=getCookieValue(request,Constant.COOKIE_NAME_TOKEN);
        if(org.springframework.util.StringUtils.isEmpty(cookieToken)&&org.springframework.util.StringUtils.isEmpty(paramToken))
            return "login";
        String token= !org.springframework.util.StringUtils.isEmpty(cookieToken)?cookieToken:paramToken;
        MiaoshaUser miaoshaUser=iMiaoshaUserService.getMiaoshaUserByToken(response,token);
        return miaoshaUser;
    }

    private String getCookieValue(HttpServletRequest request,String cookieNameToken) {
        Cookie[] cookies=request.getCookies();
        for(Cookie cookie:cookies){
            if(cookieNameToken.equals(cookie.getName())){
                return cookie.getValue();
            }
        }
        return null;
    }
}
