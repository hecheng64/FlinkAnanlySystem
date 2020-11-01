package com.youfan.entity;

/**
 * Created by Administrator on 2020/2/28.
 */
public class AdvertisingInfo {
    private String userId = "";//用户id
    private String timeinfo = "";//分钟,小时
    private String timeinfoString = "";//时间格式字符串
    private String deviceType;//设备类型
    private String groupByField = "";//分组字段
    private Long times = 0l;//访问次数
    private Long userNums = 0l;//用户数
    private Long userOrderNums = 0l;//成交订单的用户数
    private String adId = "";//广告id
    private String productId = "";//商品id

    private String productName;
    private String userName;
    private String deviceName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

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

    public Long getUserNums() {
        return userNums;
    }

    public void setUserNums(Long userNums) {
        this.userNums = userNums;
    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public Long getUserOrderNums() {
        return userOrderNums;
    }

    public void setUserOrderNums(Long userOrderNums) {
        this.userOrderNums = userOrderNums;
    }

    public String getTimeinfoString() {
        return timeinfoString;
    }

    public void setTimeinfoString(String timeinfoString) {
        this.timeinfoString = timeinfoString;
    }
}
