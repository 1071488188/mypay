package com.har.unmanned.mfront.model.extend;

import com.har.unmanned.mfront.model.ShopOrder;
import com.har.unmanned.mfront.model.ShopOrderItem;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cc
 * @create 2017/09/20 9:45
 **/
@Getter
@Setter
@Accessors(chain = true)
public class ShopOrderDomain extends ShopOrder {
    private String payTimeString;
    private List<ShopOrderItemDomain> items = new ArrayList<>();
}
