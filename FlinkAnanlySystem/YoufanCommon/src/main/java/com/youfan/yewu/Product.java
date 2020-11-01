package com.youfan.yewu;

/**
 * Created by Administrator on 2020/2/23.
 */
public class Product {
    private Long id;
    private String productName;
    private Long productTypeid;
    private Double originalPrice;
    private Double huodongPrice;
    private Long shopid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductTypeid() {
        return productTypeid;
    }

    public void setProductTypeid(Long productTypeid) {
        this.productTypeid = productTypeid;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getHuodongPrice() {
        return huodongPrice;
    }

    public void setHuodongPrice(Double huodongPrice) {
        this.huodongPrice = huodongPrice;
    }

    public Long getShopid() {
        return shopid;
    }

    public void setShopid(Long shopid) {
        this.shopid = shopid;
    }
}
