package com.youfan.yewu;

import java.util.Date;

/**
 * Created by Administrator on 2020/3/7.
 */
public class MiaoshaInfo {
    private Long id;
    private String miaoshaName;
    private String miaoshaDesc;
    private Date miaoshaStartTime;
    private Date miaoshaEndTime;
    private Long miaoshanums;
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

    public String getMiaoshaName() {
        return miaoshaName;
    }

    public void setMiaoshaName(String miaoshaName) {
        this.miaoshaName = miaoshaName;
    }

    public String getMiaoshaDesc() {
        return miaoshaDesc;
    }

    public void setMiaoshaDesc(String miaoshaDesc) {
        this.miaoshaDesc = miaoshaDesc;
    }

    public Date getMiaoshaStartTime() {
        return miaoshaStartTime;
    }

    public void setMiaoshaStartTime(Date miaoshaStartTime) {
        this.miaoshaStartTime = miaoshaStartTime;
    }

    public Date getMiaoshaEndTime() {
        return miaoshaEndTime;
    }

    public void setMiaoshaEndTime(Date miaoshaEndTime) {
        this.miaoshaEndTime = miaoshaEndTime;
    }

    public Long getMiaoshanums() {
        return miaoshanums;
    }

    public void setMiaoshanums(Long miaoshanums) {
        this.miaoshanums = miaoshanums;
    }
}
