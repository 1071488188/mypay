package com.har.unmanned.mfront.model.extend;

import com.har.unmanned.mfront.model.DispatchItem;
import lombok.Data;

/**
 * @author huangjj
 * @create 2017-09-19 18:24
 **/
@Data
public class DispatchItemDomain extends DispatchItem{
    //商品名称
    private String goodsName;
    // 商品规格
    private String spec;
    // 商品图片
    private String goodsPicture;
    // 商品摆放层级
    private Integer layer;
    //货架库存数量
    private Integer stockQuantity;
    //配送中数量
    private Integer dispatchQuantity;
    //货架库存ID
    private Integer shopStockId;

}
