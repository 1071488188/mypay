package com.har.unmanned.mfront.model;

import java.io.Serializable;

public class ShopCourier implements Serializable {
    private Integer id;

    private Long shopId;

    private Long dispatchor;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getDispatchor() {
        return dispatchor;
    }

    public void setDispatchor(Long dispatchor) {
        this.dispatchor = dispatchor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", shopId=").append(shopId);
        sb.append(", dispatchor=").append(dispatchor);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}