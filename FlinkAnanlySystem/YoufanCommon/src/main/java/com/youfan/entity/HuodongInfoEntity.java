package com.youfan.entity;

/**
 * Created by Administrator on 2020/2/18.
 */
public class HuodongInfoEntity {
    private String timeinfo = "";//分钟,小时
    private String timeinfoString = "";//时间格式字符串
    private String groupByField = "";//分组字段
    private String huodongArea = "";//地区
    private Long areaNum = 0l;//地区数量
    private Long orderNums = 0l;//订单的数量

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

    public String getHuodongArea() {
        return huodongArea;
    }

    public void setHuodongArea(String huodongArea) {
        this.huodongArea = huodongArea;
    }

    public Long getAreaNum() {
        return areaNum;
    }

    public void setAreaNum(Long areaNum) {
        this.areaNum = areaNum;
    }

    public Long getOrderNums() {
        return orderNums;
    }

    public void setOrderNums(Long orderNums) {
        this.orderNums = orderNums;
    }
}
