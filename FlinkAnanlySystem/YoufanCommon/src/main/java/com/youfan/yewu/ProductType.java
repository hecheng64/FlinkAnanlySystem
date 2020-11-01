package com.youfan.yewu;

/**
 * Created by Administrator on 2020/2/23.
 */
public class ProductType {
    private Long id;
    private String productTypeName;
    private Long productTypeleave;
    private Long parentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public Long getProductTypeleave() {
        return productTypeleave;
    }

    public void setProductTypeleave(Long productTypeleave) {
        this.productTypeleave = productTypeleave;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
