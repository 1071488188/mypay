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

    public String getAmountZh() {
        if(!CheckUtil.isNull(amountZh)){
          return  CheckUtil.m2(Double.parseDouble(amountZh)) ;
        }
        return amountZh;
    }

    public void setAmountZh(String amountZh) {
        this.amountZh = amountZh;
    }

    public String getCommissionZh() {
        if(!CheckUtil.isNull(commissionZh)){
            return  CheckUtil.m2(Double.parseDouble(commissionZh)) ;
        }
        return commissionZh;
    }

    public void setCommissionZh(String commissionZh) {
        this.commissionZh = commissionZh;
    }
}