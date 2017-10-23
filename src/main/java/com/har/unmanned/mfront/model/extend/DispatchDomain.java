package com.har.unmanned.mfront.model.extend;

import com.har.unmanned.mfront.model.Dispatch;
import lombok.Data;

import java.util.List;

/**
 * @author huangjj
 * @create 2017-09-19 17:58
 **/
@Data
public class DispatchDomain extends Dispatch{
    //微信openId
    private String openid;
    //取货位置
    private String storeAddress;
    //货架位置
    private String shopAddress;
    //货架编号
    private String shopCode;
    //配送状态（逗号分隔字符串）
    private String statusString;
    //取货单
    private List<DispatchItemDomain> carrierNote;

}
