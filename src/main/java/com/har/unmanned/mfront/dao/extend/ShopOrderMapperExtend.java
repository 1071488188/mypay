package com.har.unmanned.mfront.dao.extend;

import com.har.unmanned.mfront.dao.ShopOrderMapper;
import com.har.unmanned.mfront.model.extend.ShopOrderExtend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopOrderMapperExtend extends ShopOrderMapper{
    /**
     * 根据网点id统计所有消费人数
     * @param shopId
     * @return
     */
    public int countByShopId(@Param("shopId") Long shopId);

    /**
     * 查询消费记录列表
     * @param shopId
     * @return
     */
    public List<ShopOrderExtend> sumByShopId(@Param("shopId") Long shopId);
}