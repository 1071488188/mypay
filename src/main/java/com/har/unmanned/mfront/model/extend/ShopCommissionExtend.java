package com.har.unmanned.mfront.model.extend;

import com.har.unmanned.mfront.model.ShopCommission;
import com.har.unmanned.mfront.utils.CheckUtil;
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
    private String amountZh;
    private String commissionZh;

}