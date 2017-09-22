package com.har.unmanned.mfront.dao.extend;

import com.har.unmanned.mfront.dao.ShopMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 网点扩展
 */
public interface ShopMapperExtend extends ShopMapper {
    /**
     * 增加网点余额
     * @param shopAccountMoney
     * @param id
     * @return
     */
    public int updateShopAccountMoneyAndShopId(@Param("id") Long id,@Param("shopAccountMoney") int shopAccountMoney);

    /**
     * 扣减网点余额
     * @param id
     * @param shopAccountMoney
     * @return
     */
    public int deductTheBalanceOfTheBranch(@Param("id") Long id,@Param("shopAccountMoney") int shopAccountMoney);
}