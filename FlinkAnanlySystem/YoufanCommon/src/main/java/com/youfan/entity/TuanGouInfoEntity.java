package com.youfan.entity;

/**
 * Created by Administrator on 2020/2/18.
 */
public class TuanGouInfoEntity {
    private String timeinfo;//分钟,小时
    private String timeinfoString = "";//时间格式字符串
    private String productType = "";
    private String groupByField;//分组字段
    private Long times = 0l;//次数
    private Long orderTimes = 0l;//订单数
    private String userId;
    private Long userNums;//用户数量


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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
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

    public Long getOrderTimes() {
        return orderTimes;
    }

    public void setOrderTimes(Long orderTimes) {
        this.orderTimes = orderTimes;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getUserNums() {
        return userNums;
    }

    public void setUserNums(Long userNums) {
        this.userNums = userNums;
    }
}
