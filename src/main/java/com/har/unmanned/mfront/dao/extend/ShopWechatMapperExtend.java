package com.har.unmanned.mfront.dao.extend;

import com.har.unmanned.mfront.model.ShopWechat;
import org.apache.ibatis.annotations.Param;

/**
 * 微信用户扩展mapper
 */
public interface ShopWechatMapperExtend {
    /**
     * 根据openid查询用户信息
     * @param openid
     * @return
     */
    ShopWechat  selectByOpenId(@Param("openid") String openid);
}
