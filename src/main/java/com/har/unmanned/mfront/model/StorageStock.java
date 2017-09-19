package com.har.unmanned.mfront.model;

import java.io.Serializable;
import java.util.Date;

public class StorageStock implements Serializable {
    private Long id;

    private Integer storageId;

    private Long goodsId;

    private Integer quantity;

    private Integer initialQuantity;

    private Date initialTime;

    private Date createTime;

    private Integer minQuantity;

    private Integer buyBaseQuantity;

    private Integer buyQuantity;

    private String storageLocation;

    private Integer status;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStorageId() {
        return storageId;
    }

    public void setStorageId(Integer storageId) {
        this.storageId = storageId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(Integer minQuantity) {
        this.minQuantity = minQuantity;
    }

    public Integer getBuyBaseQuantity() {
        return buyBaseQuantity;
    }

    public void setBuyBaseQuantity(Integer buyBaseQuantity) {
        this.buyBaseQuantity = buyBaseQuantity;
    }

    public Integer getBuyQuantity() {
        return buyQuantity;
    }

    public void setBuyQuantity(Integer buyQuantity) {
        this.buyQuantity = buyQuantity;
    }

    public String getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(String storageLocation) {
        this.storageLocation = storageLocation == null ? null : storageLocation.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", storageId=").append(storageId);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", quantity=").append(quantity);
        sb.append(", initialQuantity=").append(initialQuantity);
        sb.append(", initialTime=").append(initialTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", minQuantity=").append(minQuantity);
        sb.append(", buyBaseQuantity=").append(buyBaseQuantity);
        sb.append(", buyQuantity=").append(buyQuantity);
        sb.append(", storageLocation=").append(storageLocation);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}