package com.har.unmanned.mfront.model.extend;

import com.har.unmanned.mfront.model.ShopCommission;
import lombok.Data;

/**
 * 佣金结算清单扩展
 */
@Data
public class ShopCommissionExtend extends ShopCommission {
    /**
     * 时间
     */
    private String time;
}