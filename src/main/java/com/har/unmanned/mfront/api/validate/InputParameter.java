package com.har.unmanned.mfront.api.validate;


import com.har.unmanned.mfront.api.validate.ValidGroup.BindingPhoneGroup;
import com.har.unmanned.mfront.api.validate.ValidGroup.SendValidateGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 验证接口入参实体
 * Created by huangjj on 2017/9/19.
 */
@Data
public class InputParameter {

    @NotNull(message = "请输入手机号", groups = {SendValidateGroup.class,BindingPhoneGroup.class})
    @Pattern(regexp = "^[0-9]{11}$", message = "请输入正确手机号", groups = {SendValidateGroup.class,BindingPhoneGroup.class})
    private String cellPhoneNumber;// 手机号

    @NotNull(message = "请输入校验码", groups = BindingPhoneGroup.class)
    @Pattern(regexp = "^[0-9]{6}$", message = "请输入校验码", groups = BindingPhoneGroup.class)
    private String verificationCode;// 验证码

    @NotNull(message = "传入参数不完整", groups = BindingPhoneGroup.class)
    @Pattern(regexp = "^[3-4]{1}$", message = "传入参数不完整", groups = BindingPhoneGroup.class)
    private String roleType;//角色类型3、网点管理员4、配送员

}
