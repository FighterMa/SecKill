package com.nttdata.seckill.common;

/**
 * @version v1.0
 * @ProjectName: SecKill
 * @ClassName: ResponseResult
 * @Description: TODO(一句话描述该类的功能)
 * @Author: ${战血石}
 * @Date: 2020/6/29 11:12
 */
public class ResponseResult<T> {
    private int code;
    private String msg;
    private T data;
    //成功响应用
    private ResponseResult(T data) {
        this.code=0;
        this.msg="success";
        this.data=data;
    }
    //失败响应用
    private ResponseResult(CodeMsg cm) {
        if(cm==null)
            return;
        this.code=cm.getCode();
        this.msg=cm.getMsg();
    }
    //成功响应返回结果
    public static <T> ResponseResult<T> success(T data){
        return new ResponseResult<T>(data);
    }
    //失败响应返回结果
    public static <T> ResponseResult<T> error(CodeMsg cm){
        return new ResponseResult<T>(cm);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
