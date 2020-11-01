package com.youfan.input;

public class ScanPageLog {
    private String pageId;//页面id
    private String visitTime;//访问时间
    private String jumpTime;//跳出时间
    private String remainTime;//停留时间
    private String productId;//商品id
    private String adId;//广告id
    private String productTypeId;//商品类别id
    private String huodongId;// 活动id
    private String miaoshaId;//秒杀活动id
    private String tuangouId;//团购活动id
    private String deviceType;//0、app端 1、pc端 2、小程序端
    private DeviceComomInfo deviceComomInfo;
    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }

    public String getJumpTime() {
        return jumpTime;
    }

    public void setJumpTime(String jumpTime) {
        this.jumpTime = jumpTime;
    }

    public String getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(String remainTime) {
        this.remainTime = remainTime;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getHuodongId() {
        return huodongId;
    }

    public void setHuodongId(String huodongId) {
        this.huodongId = huodongId;
    }

    public String getMiaoshaId() {
        return miaoshaId;
    }

    public void setMiaoshaId(String miaoshaId) {
        this.miaoshaId = miaoshaId;
    }

    public String getTuangouId() {
        return tuangouId;
    }

    public void setTuangouId(String tuangouId) {
        this.tuangouId = tuangouId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public DeviceComomInfo getDeviceComomInfo() {
        return deviceComomInfo;
    }

    public void setDeviceComomInfo(DeviceComomInfo deviceComomInfo) {
        this.deviceComomInfo = deviceComomInfo;
    }
}
