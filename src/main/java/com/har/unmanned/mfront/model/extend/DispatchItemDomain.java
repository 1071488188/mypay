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

}
