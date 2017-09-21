package com.har.unmanned.mfront.dao.extend;

import com.har.unmanned.mfront.dao.ShopMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 网点扩展
 */
public interface ShopMapperExtend extends ShopMapper {
    /**
     * 修改网点余额
     * @param shopAccountMoney
     * @param id
     * @return
     */
    public int updateShopAccountMoneyAndShopId(@Param("id") Long id,@Param("shopAccountMoney") int shopAccountMoney);
}