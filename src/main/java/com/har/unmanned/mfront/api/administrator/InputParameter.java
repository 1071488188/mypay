package com.har.unmanned.mfront.api.administrator;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 管理员入参实体
 * Created by jiang on 2017/9/19.
 */
@Data
public class InputParameter {

    @NotNull(message = "提现金额不能为空!")
    @Digits(fraction = 2, integer = 10, message = "请输入提现正确金额!")
    @Min(value = 2, message = "提现金额必须大于等于10!")
    private String reflectTheAmountOf;//提现金额
}
