package com.har.unmanned.mfront.model.extend;

import com.har.unmanned.mfront.model.CodeGoods;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author cc
 * @create 2017/09/19 17:05
 **/
@Getter
@Setter
@Accessors(chain = true)
public class CodeGoodsDomain extends CodeGoods {
    private Integer quantity;  // 商品的货架库存数量
}
