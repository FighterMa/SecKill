package com.nttdata.seckill.common;

/**
 * @version v1.0
 * @ProjectName: SecKill
 * @ClassName: CodeMsg
 * @Description: TODO(一句话描述该类的功能)
 * @Author: ${战血石}
 * @Date: 2020/6/29 11:19
 */
public class CodeMsg {
    private int code;

    private String Msg;
    //通用异常
    public static CodeMsg SUCCESS=new CodeMsg(0,"SUCCESS");
    public static CodeMsg ERROR=new CodeMsg(500501,"ERROR");
    //登录异常
    public static CodeMsg MOBILE_EMPTY=new CodeMsg(500100,"手机号不能为空");
    public static CodeMsg MOBILE_LENGTH=new CodeMsg(500101,"手机号错误");
    public static CodeMsg PASSWORD_EMPTY=new CodeMsg(500102,"密码不能为空");
    public static CodeMsg PASSWORD_LENGTH=new CodeMsg(500103,"密码不能少于六位");
    public static CodeMsg MOBILE_NOTEXISTS=new CodeMsg(500104,"手机号不存在");
    public static CodeMsg PASSWORD_ERROR=new CodeMsg(500105,"密码错误");
    private CodeMsg(int code, String Msg) {
        this.code=code;
        this.Msg=Msg;
    }

    public String getMsg() {
        return Msg;
    }

    public int getCode() {
        return code;
    }



}
