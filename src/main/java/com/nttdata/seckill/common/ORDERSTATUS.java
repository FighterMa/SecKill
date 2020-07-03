package com.nttdata.seckill.common;

/**
 * @version v1.0
 * @ProjectName: SecKill
 * @ClassName: CHANNEL
 * @Description: TODO(一句话描述该类的功能)
 * @Author: ${战血石}
 * @Date: 2020/7/3 22:03
 */
public enum ORDERSTATUS {

    NWE("新建未支付", 0), PAYED("已支付", 1), SENT("已发货", 2), RECEIVED("已收货", 3), REFUND("已退款", 4), FINISHED("已完成", 5);
    private int statusCode;
    private String statusName;

    ORDERSTATUS(String statusName,int statusCode) {
        this.statusCode = statusCode;
        this.statusName = statusName;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
