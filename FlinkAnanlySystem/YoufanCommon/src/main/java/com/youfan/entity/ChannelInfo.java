package com.youfan.entity;

/**
 * Created by Administrator on 2020/2/18.
 */
public class ChannelInfo {
    private String deviceName;
    private String userId;//用户id
    private String timeinfo;//分钟,小时
    private String timeinfoString = "";//时间格式字符串
    private String deviceType;//0、app端 1、pc端 2、小程序端
    private String channelinfo;//渠道
    private String groupByField;//分组字段
    private Long times = 0l;//访问次数
    private Long newusers = 0l;//新增用户数量

    private Long hourActivenums = 0l;//小时活跃数量
    private Long dayActivenums = 0l;//天活跃数量
    private Long monthActivenums = 0l;//月活跃数量
    private Long weekActivenums = 0l;//周活跃数量

    private Long userNums = 0l;//用户数量

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTimeinfo() {
        return timeinfo;
    }

    public void setTimeinfo(String timeinfo) {
        this.timeinfo = timeinfo;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
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

    public Long getNewusers() {
        return newusers;
    }

    public void setNewusers(Long newusers) {
        this.newusers = newusers;
    }

    public Long getHourActivenums() {
        return hourActivenums;
    }

    public void setHourActivenums(Long hourActivenums) {
        this.hourActivenums = hourActivenums;
    }

    public Long getDayActivenums() {
        return dayActivenums;
    }

    public void setDayActivenums(Long dayActivenums) {
        this.dayActivenums = dayActivenums;
    }

    public Long getMonthActivenums() {
        return monthActivenums;
    }

    public void setMonthActivenums(Long monthActivenums) {
        this.monthActivenums = monthActivenums;
    }

    public Long getWeekActivenums() {
        return weekActivenums;
    }

    public void setWeekActivenums(Long weekActivenums) {
        this.weekActivenums = weekActivenums;
    }

    public Long getUserNums() {
        return userNums;
    }

    public void setUserNums(Long userNums) {
        this.userNums = userNums;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChannelinfo() {
        return channelinfo;
    }

    public void setChannelinfo(String channelinfo) {
        this.channelinfo = channelinfo;
    }

    public String getTimeinfoString() {
        return timeinfoString;
    }

    public void setTimeinfoString(String timeinfoString) {
        this.timeinfoString = timeinfoString;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}
