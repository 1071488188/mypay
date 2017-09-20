package com.har.unmanned.mfront.model.extend;

import com.har.unmanned.mfront.model.ShopOrderItem;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author cc
 * @create 2017/09/20 10:12
 **/
@Getter
@Setter
@Accessors(chain = true)
public class ShopOrderItemDomain extends ShopOrderItem {
    private String name; // 商品名称
    private String image; // 商品图片
}
