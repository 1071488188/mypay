package com.har.unmanned.mfront.dao;

import com.har.unmanned.mfront.model.ShopStockLoseItem;
import com.har.unmanned.mfront.model.ShopStockLoseItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopStockLoseItemMapper {
    int countByExample(ShopStockLoseItemExample example);

    int deleteByExample(ShopStockLoseItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShopStockLoseItem record);

    int insertSelective(ShopStockLoseItem record);

    List<ShopStockLoseItem> selectByExample(ShopStockLoseItemExample example);

    ShopStockLoseItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShopStockLoseItem record, @Param("example") ShopStockLoseItemExample example);

    int updateByExample(@Param("record") ShopStockLoseItem record, @Param("example") ShopStockLoseItemExample example);

    int updateByPrimaryKeySelective(ShopStockLoseItem record);

    int updateByPrimaryKey(ShopStockLoseItem record);
}