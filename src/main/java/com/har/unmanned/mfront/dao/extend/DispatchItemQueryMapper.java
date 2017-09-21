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
     *  未确认商品
     * @param dispatchNo
     * @param goodsIds
     * @return
     */
    List<DispatchItemDomain> notConfirGoods(@Param("dispatchNo") String dispatchNo,@Param("goodsIds") String goodsIds);

    /**
     *  更新货架库存
     * @param shopStockId
     * @param dispatchQuantity
     * @return
     */
   int updateShopStock(@Param("shopStockId") String shopStockId,@Param("dispatchQuantity") String dispatchQuantity);
}
