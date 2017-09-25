package com.har.unmanned.mfront.model.extend;

import com.har.unmanned.mfront.model.ShopOrder;
import com.har.unmanned.mfront.utils.CheckUtil;
import lombok.Data;

/**
 * 订单扩展
 * Created by jiang on 2017/9/20.
 */
@Data
public class ShopOrderExtend extends ShopOrder {
    private String headimgUrl;
    private String amountZh;
}
