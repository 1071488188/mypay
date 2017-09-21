package com.har.unmanned.mfront.model;

import java.io.Serializable;
import java.util.Date;

public class ShopExpressive implements Serializable {
    private Integer id;

    private String expressiveNo;

    private Integer money;

    private Long userId;

    private Date applyTime;

    private Long auditor;

    private Date auditTime;

    private Long defrayor;

    private Date defrayTime;

    private String accountBank;

    private String accountNo;

    private Integer status;

    private Long shopId;

    private Integer type;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExpressiveNo() {
        return expressiveNo;
    }

    public void setExpressiveNo(String expressiveNo) {
        this.expressiveNo = expressiveNo == null ? null : expressiveNo.trim();
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
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

    public Long getAuditor() {
        return auditor;
    }

    public void setAuditor(Long auditor) {
        this.auditor = auditor;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Long getDefrayor() {
        return defrayor;
    }

    public void setDefrayor(Long defrayor) {
        this.defrayor = defrayor;
    }

    public Date getDefrayTime() {
        return defrayTime;
    }

    public void setDefrayTime(Date defrayTime) {
        this.defrayTime = defrayTime;
    }

    public String getAccountBank() {
        return accountBank;
    }

    public void setAccountBank(String accountBank) {
        this.accountBank = accountBank == null ? null : accountBank.trim();
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo == null ? null : accountNo.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", expressiveNo=").append(expressiveNo);
        sb.append(", money=").append(money);
        sb.append(", userId=").append(userId);
        sb.append(", applyTime=").append(applyTime);
        sb.append(", auditor=").append(auditor);
        sb.append(", auditTime=").append(auditTime);
        sb.append(", defrayor=").append(defrayor);
        sb.append(", defrayTime=").append(defrayTime);
        sb.append(", accountBank=").append(accountBank);
        sb.append(", accountNo=").append(accountNo);
        sb.append(", status=").append(status);
        sb.append(", shopId=").append(shopId);
        sb.append(", type=").append(type);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}