package com.har.unmanned.mfront.api.wxUser;

import com.alibaba.fastjson.JSONArray;
import com.har.unmanned.mfront.api.administrator.ValidGroup.PageGroup;
import com.har.unmanned.mfront.api.wxUser.validGroup.IndexGroup;
import com.har.unmanned.mfront.api.wxUser.validGroup.OrderGroup;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 微信用户入参实体
 * Created by cc on 2017/9/19.
 */
@Data
public class InputParameter {
    @NotNull(message = "货架编号不能为空!", groups = {IndexGroup.class, OrderGroup.class})
    private String shopCode;

    @NotNull(message = "页码不能为空!", groups = PageGroup.class)
    @Min(value = 1, message = "页码输入错误", groups = PageGroup.class)
    private Integer page;
    @NotNull(message = "每页显示条数不能为空!", groups = PageGroup.class)
    @Min(value = 1, message = "每页显示条数错误", groups = PageGroup.class)
    private Integer pageSize;

    @NotNull(message = "所选商品不能为空!", groups = OrderGroup.class)
    private JSONArray goodsList;

    private String location;
    private BigDecimal longitude;
    private BigDecimal latitude;
}
