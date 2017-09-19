package com.har.unmanned.mfront.model.extend;

import com.har.unmanned.mfront.model.CodeGoods;
import com.har.unmanned.mfront.model.ShopWechat;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * 微信用户扩展属性
 * @author cc
 * @create 2017/09/19 11:31
 **/
@Getter
@Setter
@Accessors(chain = true)
public class ShopWechatDomain extends ShopWechat {
    private List<CodeGoods> goodsList = new ArrayList<>();  // 用户的商品列表
}
