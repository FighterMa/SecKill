package com.nttdata.seckill.VO;

import javax.validation.constraints.NotNull;

/**
 * @version v1.0
 * @ProjectName: SecKill
 * @ClassName: LoginVO
 * @Description: TODO(一句话描述该类的功能)
 * @Author: ${战血石}
 * @Date: 2020/6/30 23:19
 */
public class LoginVO {

    private String mobile;

    private String password;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginVO{" +
                "mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
