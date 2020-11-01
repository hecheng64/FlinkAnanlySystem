package com.youfan.entity;

/**
 * Created by Administrator on 2020/2/18.
 */
public class MiaoshaInfoEntity {
    private String timeinfo = "";//分钟,小时
    private String timeinfoString = "";//时间格式字符串
    private String groupByField = "";//分组字段
    private Long orderPayNums = 0l;//成交数
    private String payType = "";
    private Long payNums=0l;

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Long getPayNums() {
        return payNums;
    }

    public void setPayNums(Long payNums) {
        this.payNums = payNums;
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

    public Long getOrderPayNums() {
        return orderPayNums;
    }

    public void setOrderPayNums(Long orderPayNums) {
        this.orderPayNums = orderPayNums;
    }

}
