package com.har.unmanned.mfront.api.administrator;

import com.har.unmanned.mfront.api.administrator.ValidGroup.BindManagerGroup;
import com.har.unmanned.mfront.api.administrator.ValidGroup.CloseAnAccountGroup;
import com.har.unmanned.mfront.api.administrator.ValidGroup.PageGroup;
import com.har.unmanned.mfront.api.administrator.ValidGroup.WithdrawDepositGroup;
import lombok.Data;

import javax.validation.constraints.*;

/**
 * 管理员入参实体
 * Created by jiang on 2017/9/19.
 */
@Data
public class InputParameter {
    @NotNull(message = "提现金额不能为空!", groups = WithdrawDepositGroup.class)
    @Digits(fraction = 2, integer = 10, message = "请输入提现正确金额!", groups = WithdrawDepositGroup.class)
    @Min(value = 2, message = "提现金额必须大于等于10!", groups = WithdrawDepositGroup.class)
    private String reflectTheAmountOf;//提现金额
    @NotNull(message = "请输入手机号！", groups = BindManagerGroup.class)
    @Pattern(regexp = "^[0-9]{11}$", message = "请输入正确手机号！", groups = BindManagerGroup.class)
    private String cellPhoneNumber;//手机号
    @NotNull(message = "请输入验证码！", groups = BindManagerGroup.class)
    @Pattern(regexp = "^[0-9]{6}$", message = "请输入正确验证码！", groups = BindManagerGroup.class)
    private String verificationCode;//验证码
    @NotNull(message = "页码不能为空!", groups = PageGroup.class)
    @Min(value = 1, message = "页码输入错误", groups = PageGroup.class)
    private Integer page;//页码
    @NotNull(message = "每页显示条数不能为空!", groups = PageGroup.class)
    @Min(value = 1, message = "每页显示条数错误", groups = PageGroup.class)
    @Max(value = 20, message = "每页显示条数错误", groups = PageGroup.class)
    private Integer pageSize;//每页显示条数
    @NotNull(message = "参数不能为空!", groups = CloseAnAccountGroup.class)
    private String billingId;//结算清单id

}
