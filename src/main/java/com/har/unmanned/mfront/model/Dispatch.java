package com.har.unmanned.mfront.model;

import java.io.Serializable;
import java.util.Date;

public class Dispatch implements Serializable {
    private Long id;

    private Long typeId;

    private String dispatchNo;

    private Long shopId;

    private Long storageId;

    private Long dispatchor;

    private Date completeTime;

    private Date ordersTime;

    private Date createTime;

    private Integer status;

    private String billId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getDispatchNo() {
        return dispatchNo;
    }

    public void setDispatchNo(String dispatchNo) {
        this.dispatchNo = dispatchNo == null ? null : dispatchNo.trim();
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getStorageId() {
        return storageId;
    }

    public void setStorageId(Long storageId) {
        this.storageId = storageId;
    }

    public Long getDispatchor() {
        return dispatchor;
    }

    public void setDispatchor(Long dispatchor) {
        this.dispatchor = dispatchor;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public Date getOrdersTime() {
        return ordersTime;
    }

    public void setOrdersTime(Date ordersTime) {
        this.ordersTime = ordersTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId == null ? null : billId.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", typeId=").append(typeId);
        sb.append(", dispatchNo=").append(dispatchNo);
        sb.append(", shopId=").append(shopId);
        sb.append(", storageId=").append(storageId);
        sb.append(", dispatchor=").append(dispatchor);
        sb.append(", completeTime=").append(completeTime);
        sb.append(", ordersTime=").append(ordersTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", status=").append(status);
        sb.append(", billId=").append(billId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}