package com.har.unmanned.mfront.model;

import java.io.Serializable;

public class App implements Serializable {
    private Integer id;

    private String appName;

    private String appId;

    private String frontPrikey;

    private String frontPubkey;

    private String appPrikeyInit;

    private String appPubkeyInit;

    private Integer status;

    private Integer partnerId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getFrontPrikey() {
        return frontPrikey;
    }

    public void setFrontPrikey(String frontPrikey) {
        this.frontPrikey = frontPrikey == null ? null : frontPrikey.trim();
    }

    public String getFrontPubkey() {
        return frontPubkey;
    }

    public void setFrontPubkey(String frontPubkey) {
        this.frontPubkey = frontPubkey == null ? null : frontPubkey.trim();
    }

    public String getAppPrikeyInit() {
        return appPrikeyInit;
    }

    public void setAppPrikeyInit(String appPrikeyInit) {
        this.appPrikeyInit = appPrikeyInit == null ? null : appPrikeyInit.trim();
    }

    public String getAppPubkeyInit() {
        return appPubkeyInit;
    }

    public void setAppPubkeyInit(String appPubkeyInit) {
        this.appPubkeyInit = appPubkeyInit == null ? null : appPubkeyInit.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", appName=").append(appName);
        sb.append(", appId=").append(appId);
        sb.append(", frontPrikey=").append(frontPrikey);
        sb.append(", frontPubkey=").append(frontPubkey);
        sb.append(", appPrikeyInit=").append(appPrikeyInit);
        sb.append(", appPubkeyInit=").append(appPubkeyInit);
        sb.append(", status=").append(status);
        sb.append(", partnerId=").append(partnerId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}