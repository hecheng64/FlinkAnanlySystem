package com.youfan.yewu;

import java.util.Date;

/**
 * Created by Administrator on 2020/3/7.
 */
public class TuangouInfo {
    private Long id;
    private String tuangouName;
    private Date tuangouStartTime;
    private Date tuangouEndTime;
    private String tuangouDesc;
    private String taungouArea;
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

    public String getTuangouName() {
        return tuangouName;
    }

    public void setTuangouName(String tuangouName) {
        this.tuangouName = tuangouName;
    }

    public Date getTuangouStartTime() {
        return tuangouStartTime;
    }

    public void setTuangouStartTime(Date tuangouStartTime) {
        this.tuangouStartTime = tuangouStartTime;
    }

    public Date getTuangouEndTime() {
        return tuangouEndTime;
    }

    public void setTuangouEndTime(Date tuangouEndTime) {
        this.tuangouEndTime = tuangouEndTime;
    }

    public String getTuangouDesc() {
        return tuangouDesc;
    }

    public void setTuangouDesc(String tuangouDesc) {
        this.tuangouDesc = tuangouDesc;
    }

    public String getTaungouArea() {
        return taungouArea;
    }

    public void setTaungouArea(String taungouArea) {
        this.taungouArea = taungouArea;
    }
}
