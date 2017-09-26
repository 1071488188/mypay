package com.har.unmanned.mfront.dao.extend;

import com.har.unmanned.mfront.model.extend.DispatchItemDomain;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DispatchItemQueryMapper {
    /**
     * 配送单商品列表
     *
     * @param dispatchNo
     * @return
     */
    List<DispatchItemDomain> dispatchGoodsList(@Param("dispatchNo") String dispatchNo);
    /**
     * 货架商品列表
     *
     * @param dispatchNo
     * @return
     */
    List<DispatchItemDomain> shopStockGoodsList(@Param("dispatchNo") String dispatchNo);

    /**
     * 获取货架最大层数
     * @param dispatchNo
     * @return
     */
   Integer getMaxLayer(@Param("dispatchNo") String dispatchNo);
}
