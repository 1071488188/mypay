package com.har.unmanned.mfront.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Shop implements Serializable {
    private Long id;

    private String shopCode;

    private String company;

    private String companyType;

    private String position;

    private String openid;

    private String contacts;

    private String telephone;

    private BigDecimal ratio;

    private String accountBank;

    private String accountNo;

    private Date shopTime;

    private String contractNo;

    private Long marketor;

    private Long dispatchor;

    private Long level;

    private Integer status;

    private String address;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private Long userId;

    private Integer shopAccountMoney;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode == null ? null : shopCode.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType == null ? null : companyType.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
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

    public Date getShopTime() {
        return shopTime;
    }

    public void setShopTime(Date shopTime) {
        this.shopTime = shopTime;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo == null ? null : contractNo.trim();
    }

    public Long getMarketor() {
        return marketor;
    }

    public void setMarketor(Long marketor) {
        this.marketor = marketor;
    }

    public Long getDispatchor() {
        return dispatchor;
    }

    public void setDispatchor(Long dispatchor) {
        this.dispatchor = dispatchor;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getShopAccountMoney() {
        return shopAccountMoney;
    }

    public void setShopAccountMoney(Integer shopAccountMoney) {
        this.shopAccountMoney = shopAccountMoney;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", shopCode=").append(shopCode);
        sb.append(", company=").append(company);
        sb.append(", companyType=").append(companyType);
        sb.append(", position=").append(position);
        sb.append(", openid=").append(openid);
        sb.append(", contacts=").append(contacts);
        sb.append(", telephone=").append(telephone);
        sb.append(", ratio=").append(ratio);
        sb.append(", accountBank=").append(accountBank);
        sb.append(", accountNo=").append(accountNo);
        sb.append(", shopTime=").append(shopTime);
        sb.append(", contractNo=").append(contractNo);
        sb.append(", marketor=").append(marketor);
        sb.append(", dispatchor=").append(dispatchor);
        sb.append(", level=").append(level);
        sb.append(", status=").append(status);
        sb.append(", address=").append(address);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", userId=").append(userId);
        sb.append(", shopAccountMoney=").append(shopAccountMoney);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}