package com.har.unmanned.mfront.dao.extend;

import com.har.unmanned.mfront.model.extend.CodeGoodsDomain;
import com.har.unmanned.mfront.model.extend.ShopOrderDomain;
import com.har.unmanned.mfront.model.extend.ShopStockDomain;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 微信用户扩展mapper
 */
public interface ShopWechatQueryMapper {
    /**
     * 查询用户最近购买
     *
     * @param openId
     * @return
     */
    List<ShopStockDomain> selectRecentlyBuyList(@Param("openId") String openId, @Param("shopId")String shopId);

    /**
     * 查询货架所有商品(购买首页)
     * @return
     */
    List<ShopStockDomain> selectShopGoodsList(String shopCode);

    /**
     * 用户购买记录
     * @return
     */
    List<ShopOrderDomain> selectBuyHistory(@Param("openId")String openId, @Param("shopId")String shopId);

    /**
     * 根据id查询多个商品
     * @param ids
     * @return
     */
    List<CodeGoodsDomain> selectGoodsInfo( @Param("shopId")Long shopId, @Param("ids") List ids);

    /**
     * 更新商品库存
     * @param goodsId
     * @param num
     */
    Integer updateGoodsStock(@Param("shopId") String shopId, @Param("goodsId")String goodsId, @Param("num")String num);
}
