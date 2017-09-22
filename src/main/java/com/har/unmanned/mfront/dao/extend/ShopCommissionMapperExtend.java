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
    public ShopCommissionExtend countByUserIdAndId(@Param("id") Long id,@Param("userId") Long userId);

    /**
     * 根据时间查询上月订单统计数据并去除已经统计过的网点数据
     * @param startTime
     * @param endTime
     * @return
     */
    public List<ShopCommissionExtend> selectByShop(@Param("startTime") String startTime,@Param("endTime") String endTime);

    /**
     * 批量插入生成待结算清单
     * @param list
     * @return
     */
    public int bulkInsert(@Param("list")  List<ShopCommissionExtend> list);

}