package com.har.unmanned.mfront.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ShopCommission implements Serializable {
    private Long id;

    private Long shopId;

    private String commissionNo;

    private Date startTime;

    private Date endTime;

    private Integer orderNum;

    private Integer amount;

    private BigDecimal ratio;

    private Integer commission;

    private Integer status;

    private Long userId;

    private Date applyTime;

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

    public String getCommissionNo() {
        return commissionNo;
    }

    public void setCommissionNo(String commissionNo) {
        this.commissionNo = commissionNo == null ? null : commissionNo.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    public Integer getCommission() {
        return commission;
    }

    public void setCommission(Integer commission) {
        this.commission = commission;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", shopId=").append(shopId);
        sb.append(", commissionNo=").append(commissionNo);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", orderNum=").append(orderNum);
        sb.append(", amount=").append(amount);
        sb.append(", ratio=").append(ratio);
        sb.append(", commission=").append(commission);
        sb.append(", status=").append(status);
        sb.append(", userId=").append(userId);
        sb.append(", applyTime=").append(applyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}