package com.har.unmanned.mfront.api.lose;

import com.har.unmanned.mfront.api.lose.ValidGroup.LoseGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 入参实体
 * Created by zhanggr on 2017/9/19.
 */
@Data
public class InputParameter {
    @NotNull(message = "货架编号不能为空", groups = LoseGroup.class)
    private String shopCode;// 货架编号
}
