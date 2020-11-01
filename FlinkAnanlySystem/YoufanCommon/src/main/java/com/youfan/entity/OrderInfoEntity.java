package com.youfan.entity;

/**
 * Created by Administrator on 2020/2/18.
 */
public class OrderInfoEntity {
    private String userId;//用户id
    private String timeinfo;//分钟,小时
    private String timeinfoString = "";//时间格式字符串
    private String groupByField;//分组字段
    private Long times = 0l;//访问次数

    private Long userNum = 0l;//用户数
    private boolean userNumFlag = false;
    private String userName;

    private Double orderAmount;//订单总额

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTimeinfo() {
        return timeinfo;
    }

    public void setTimeinfo(String timeinfo) {
        this.timeinfo = timeinfo;
    }

    public String getTimeinfoString() {
        return timeinfoString;
    }

    public void setTimeinfoString(String timeinfoString) {
        this.timeinfoString = timeinfoString;
    }

    public String getGroupByField() {
        return groupByField;
    }

    public void setGroupByField(String groupByField) {
        this.groupByField = groupByField;
    }

    public Long getTimes() {
        return times;
    }

    public void setTimes(Long times) {
        this.times = times;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserNum() {
        return userNum;
    }

    public void setUserNum(Long userNum) {
        this.userNum = userNum;
    }

    public boolean isUserNumFlag() {
        return userNumFlag;
    }

    public void setUserNumFlag(boolean userNumFlag) {
        this.userNumFlag = userNumFlag;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }
}
