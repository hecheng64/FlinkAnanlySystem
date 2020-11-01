package com.youfan.yewu;

import java.util.Date;

/**
 * Created by Administrator on 2020/3/7.
 */
public class ZhidingHuoDongInfo {
    private Long id;
    private String huodongName;
    private String huodongArea;
    private String huodongDesc;
    private Date huodongStartTime;
    private Date huodongEndTime;
    private Long productId;
    private Long productTypeId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Long productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHuodongName() {
        return huodongName;
    }

    public void setHuodongName(String huodongName) {
        this.huodongName = huodongName;
    }

    public String getHuodongArea() {
        return huodongArea;
    }

    public void setHuodongArea(String huodongArea) {
        this.huodongArea = huodongArea;
    }

    public String getHuodongDesc() {
        return huodongDesc;
    }

    public void setHuodongDesc(String huodongDesc) {
        this.huodongDesc = huodongDesc;
    }

    public Date getHuodongStartTime() {
        return huodongStartTime;
    }

    public void setHuodongStartTime(Date huodongStartTime) {
        this.huodongStartTime = huodongStartTime;
    }

    public Date getHuodongEndTime() {
        return huodongEndTime;
    }

    public void setHuodongEndTime(Date huodongEndTime) {
        this.huodongEndTime = huodongEndTime;
    }
}
