package com.har.unmanned.mfront.model.extend;

import com.har.unmanned.mfront.model.ShopStock;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author cc
 * @create 2017/09/20 11:30
 **/

@Accessors(chain = true)
@Data
public class ShopStockDomain extends ShopStock {
    private String name; //商品名称
    private String price; //商品价格
    private String image; //商品图片
    private String barCode; //商品条形码
}
