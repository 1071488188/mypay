package com.har.unmanned.mfront.api.dispatch;

import com.har.unmanned.mfront.api.dispatch.validgroup.PageGroup;
import com.har.unmanned.mfront.api.dispatch.validgroup.ValidateCodeGroup;
import lombok.Data;

import javax.validation.constraints.Min;
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
    @Min(value = 1, message = "页码输入错误", groups = PageGroup.class)
    private Integer page;//页码
    @Min(value = 1, message = "每页显示条数错误", groups = PageGroup.class)
    private Integer pageSize;//每页显示条数
    @NotNull(message = "配送状态不能为空!", groups = PageGroup.class)
    private Integer status;//配送状态
}
