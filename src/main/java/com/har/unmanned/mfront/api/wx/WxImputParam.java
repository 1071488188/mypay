package com.har.unmanned.mfront.api.wx;

import com.har.unmanned.mfront.api.validate.ValidGroup.BindingPhoneGroup;
import com.har.unmanned.mfront.api.validate.ValidGroup.SendValidateGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by jiang on 2017/10/18.
 */
@Data
public class WxImputParam {
    @NotNull(message = "标题不能为空")
    private String title;//推送title
    @NotNull(message = "配送单号不能为空")
    private String distributionNumber;//配送单号
    @NotNull(message = "配送时间不能为空")
    private String deliveryTime;//配送时间
    @NotNull(message = "配送网点不能为空")
    private String distributionNetwork;//配送网点
    @NotNull(message = "配送总计不能为空")
    private String totalDistribution;//配送总计
    @NotNull(message = "未获取到待推送人信息")
    private String openId;
    private String remark;//备注
}
