package com.har.unmanned.mfront.model;

import java.io.Serializable;
import java.util.Date;

public class ShopStock implements Serializable {
    private Long id;

    private Long shopId;

    private Long goodsId;

    private Integer quantity;

    private Integer initialQuantity;

    private Date initialTime;

    private Integer layer;

    private Integer dispatchQuantity;

    private Integer minQuantity;

    private Integer shopBaseQuantity;

    private Integer status;

    private Long templateId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getInitialQuantity() {
        return initialQuantity;
    }

    public void setInitialQuantity(Integer initialQuantity) {
        this.initialQuantity = initialQuantity;
    }

    public Date getInitialTime() {
        return initialTime;
    }

    public void setInitialTime(Date initialTime) {
        this.initialTime = initialTime;
    }

    public Integer getLayer() {
        return layer;
    }

    public void setLayer(Integer layer) {
        this.layer = layer;
    }

    public Integer getDispatchQuantity() {
        return dispatchQuantity;
    }

    public void setDispatchQuantity(Integer dispatchQuantity) {
        this.dispatchQuantity = dispatchQuantity;
    }

    public Integer getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(Integer minQuantity) {
        this.minQuantity = minQuantity;
    }

    public Integer getShopBaseQuantity() {
        return shopBaseQuantity;
    }

    public void setShopBaseQuantity(Integer shopBaseQuantity) {
        this.shopBaseQuantity = shopBaseQuantity;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", shopId=").append(shopId);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", quantity=").append(quantity);
        sb.append(", initialQuantity=").append(initialQuantity);
        sb.append(", initialTime=").append(initialTime);
        sb.append(", layer=").append(layer);
        sb.append(", dispatchQuantity=").append(dispatchQuantity);
        sb.append(", minQuantity=").append(minQuantity);
        sb.append(", shopBaseQuantity=").append(shopBaseQuantity);
        sb.append(", status=").append(status);
        sb.append(", templateId=").append(templateId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}