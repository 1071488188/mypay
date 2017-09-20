package com.har.unmanned.mfront.dao.extend;

import com.har.unmanned.mfront.dao.ShopCommissionMapper;
import com.har.unmanned.mfront.model.extend.ShopCommissionExtend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 佣金结算清单扩展
 */
public interface ShopCommissionMapperExtend extends ShopCommissionMapper{
    /**
     * 查询网点佣金记录清单
     * @param shopId
     * @return
     */
    public List<ShopCommissionExtend> selectListByShopId(@Param("shopId") Long shopId);

    /**
     * 查询该用户是否有当前结算清单
     * @param id
     * @param userId
     * @return
     */
    public int countByUserIdAndId(@Param("id") Long id,@Param("userId") Long userId);
}