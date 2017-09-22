package com.har.unmanned.mfront.dao.extend;

import com.har.unmanned.mfront.dao.ShopExpressiveMapper;
import com.har.unmanned.mfront.model.extend.ShopExpressiveExtend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 提现流水扩展
 */
public interface ShopExpressiveMapperExtend extends ShopExpressiveMapper {
    /**
     * 查询余额明细
     *
     * @param shopId
     * @return
     */
    public List<ShopExpressiveExtend> selectBalanceOfSubsidiary(@Param("shopId") Long shopId);
}