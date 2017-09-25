package com.har.unmanned.mfront.api.validate;


import com.har.unmanned.mfront.api.validate.ValidGroup.SendValidateGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 短信管理入参实体
 * Created by huangjj on 2017/9/19.
 */
@Data
public class InputParameter {
    @NotNull(message = "请输入手机号", groups = SendValidateGroup.class)
    @Pattern(regexp = "^[0-9]{11}$", message = "请输入正确手机号", groups = SendValidateGroup.class)
    private String phone;// 手机号

    @NotNull(message = "请输入短信内容", groups = SendValidateGroup.class)
    @Pattern(regexp = "^[0-9]{11}$", message = "请输入短信内容", groups = SendValidateGroup.class)
    private String content;// 短信内容

    @NotNull(message = "请输入短信类型", groups = SendValidateGroup.class)
    @Pattern(regexp = "^[0-9]{11}$", message = "请输入短信类型", groups = SendValidateGroup.class)
    private String optType;// 短信类型

    @NotNull(message = "请输入校验码", groups = SendValidateGroup.class)
    @Pattern(regexp = "^[0-9]{11}$", message = "请输入校验码", groups = SendValidateGroup.class)
    private String validateCode;// 短信类型
}
