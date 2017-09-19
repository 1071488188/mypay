package com.har.unmanned.mfront.dao.extend;

import com.har.unmanned.mfront.model.CodeGoods;
import com.har.unmanned.mfront.model.ShopWechat;
import com.har.unmanned.mfront.model.extend.CodeGoodsDomain;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 微信用户扩展mapper
 */
public interface ShopWechatQueryMapper {
    /**
     * 查询用户购买历史记录
     *
     * @param openId
     * @return
     */
    List<CodeGoods> selectBuyHistory(String openId);

    /**
     * 查询所有商品
     * @return
     */
    List<CodeGoods> selectGoodsList();

    /**
     * 用户购买记录
     * @return
     */
    List<CodeGoods> buyRecordList(String openId);

    /**
     * 根据id查询多个商品
     * @param ids
     * @return
     */
    List<CodeGoodsDomain> selectGoodsInfo( @Param("shopId")Long shopId, @Param("ids") List ids);
}
