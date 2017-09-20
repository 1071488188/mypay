package com.har.unmanned.mfront.api.dispatch;

import com.har.unmanned.mfront.api.dispatch.ValidGroup.ValidateCodeGroup;
import com.har.unmanned.mfront.api.smsvalidate.ValidGroup.SendValidateGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 短信管理入参实体
 * Created by huangjj on 2017/9/19.
 */
@Data
public class InputParameter {
    @NotNull(message = "请输入手机号！", groups = ValidateCodeGroup.class)
    @Pattern(regexp = "^[0-9]{11}$", message = "请输入正确手机号！", groups = ValidateCodeGroup.class)
    private String mobile;//手机号
    @NotNull(message = "请输入验证码！", groups = ValidateCodeGroup.class)
    @Pattern(regexp = "^[0-9]{6}$", message = "请输入正确验证码！", groups = ValidateCodeGroup.class)
    private String validateCode;//验证码

}
