package com.har.unmanned.mfront.model.extend;

import com.har.unmanned.mfront.config.CodeConstants;
import com.har.unmanned.mfront.model.ShopExpressive;
import lombok.Data;

/**
 * 提现流水扩展
 */
@Data
public class ShopExpressiveExtend extends ShopExpressive {
    private String applyTimeZh;
    private String moneyZh;
    private String typeZh;

    public String getTypeZh() {
        if(super.getType()== CodeConstants.WithdrawCurrentType.COMMISSIONSETTLEMENT){
            return "佣金结算";
        }else if (super.getType()== CodeConstants.WithdrawCurrentType.WITHDRAWDEPOSIT){
            return "提现";
        }else if(super.getType()== CodeConstants.WithdrawCurrentType.RETURNEDMONEY){
            return "打款失败退款";
        }
        return typeZh;
    }

    public void setTypeZh(String typeZh) {
        this.typeZh = typeZh;
    }
}