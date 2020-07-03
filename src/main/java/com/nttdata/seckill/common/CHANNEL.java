package com.nttdata.seckill.common;

/**
 * @version v1.0
 * @ProjectName: SecKill
 * @ClassName: CHANNEL
 * @Description: TODO(一句话描述该类的功能)
 * @Author: ${战血石}
 * @Date: 2020/7/3 22:03
 */
public enum CHANNEL {

    PC("电脑端", 1), ANDROID("安卓", 2), IOS("苹果", 3);
    private int channelCode;
    private String channelName;

    CHANNEL(String channelName, int channelCode) {

        this.channelName = channelName;
        this.channelCode = channelCode;
    }

    public int getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(int channelCode) {
        this.channelCode = channelCode;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }





}
