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

    @NotNull(message = "请选择商品!", groups = OrderGroup.class)
    private JSONArray goodsList;

    private String location;
    private BigDecimal longitude;
    private BigDecimal latitude;
}
