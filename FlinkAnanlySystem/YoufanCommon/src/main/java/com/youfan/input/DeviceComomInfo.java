package com.youfan.input;

public class DeviceComomInfo {
    private String userId;//用户id,未登陆，用户id为-1
    private String deviceId;//设备id
    private String openTime;//打开终端时间
    private String leaveTime;//退出终端时间
    private String remoteIP;//客户端的ip
    private String country;//国家
    private String province;//省
    private String city;//城市
    private String channelinfo;//渠道信息
    private boolean isNew = false;//是否为新增用户
    private boolean hourActive = false;//是否为小时活跃
    private boolean dayActive = false;//是否为天活跃
    private boolean monthActive = false;//是否为月活跃
    private boolean weekActive = false;//是否为周活跃
    private boolean fiveMinuteActive = false;//是否为5分钟活跃


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(String leaveTime) {
        this.leaveTime = leaveTime;
    }

    public String getRemoteIP() {
        return remoteIP;
    }

    public void setRemoteIP(String remoteIP) {
        this.remoteIP = remoteIP;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public boolean isHourActive() {
        return hourActive;
    }

    public void setHourActive(boolean hourActive) {
        this.hourActive = hourActive;
    }

    public boolean isDayActive() {
        return dayActive;
    }

    public void setDayActive(boolean dayActive) {
        this.dayActive = dayActive;
    }

    public boolean isMonthActive() {
        return monthActive;
    }

    public void setMonthActive(boolean monthActive) {
        this.monthActive = monthActive;
    }

    public boolean isWeekActive() {
        return weekActive;
    }

    public void setWeekActive(boolean weekActive) {
        this.weekActive = weekActive;
    }

    public boolean isFiveMinuteActive() {
        return fiveMinuteActive;
    }

    public void setFiveMinuteActive(boolean fiveMinuteActive) {
        this.fiveMinuteActive = fiveMinuteActive;
    }

    public String getChannelinfo() {
        return channelinfo;
    }

    public void setChannelinfo(String channelinfo) {
        this.channelinfo = channelinfo;
    }
}
