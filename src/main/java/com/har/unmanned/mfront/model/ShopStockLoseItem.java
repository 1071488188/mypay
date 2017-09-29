package com.har.unmanned.mfront.model;

import java.io.Serializable;

public class ShopStockLoseItem implements Serializable {
    private Long id;

    private Long loseId;

    private Long goodsId;

    private Integer goodsOriginalQuantity;

    private Integer goodsNowQuantity;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLoseId() {
        return loseId;
    }

    public void setLoseId(Long loseId) {
        this.loseId = loseId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getGoodsOriginalQuantity() {
        return goodsOriginalQuantity;
    }

    public void setGoodsOriginalQuantity(Integer goodsOriginalQuantity) {
        this.goodsOriginalQuantity = goodsOriginalQuantity;
    }

    public Integer getGoodsNowQuantity() {
        return goodsNowQuantity;
    }

    public void setGoodsNowQuantity(Integer goodsNowQuantity) {
        this.goodsNowQuantity = goodsNowQuantity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", loseId=").append(loseId);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", goodsOriginalQuantity=").append(goodsOriginalQuantity);
        sb.append(", goodsNowQuantity=").append(goodsNowQuantity);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}