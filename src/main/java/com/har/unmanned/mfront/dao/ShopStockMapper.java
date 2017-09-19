package com.har.unmanned.mfront.dao;

import com.har.unmanned.mfront.model.ShopStock;
import com.har.unmanned.mfront.model.ShopStockExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopStockMapper {
    int countByExample(ShopStockExample example);

    int deleteByExample(ShopStockExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShopStock record);

    int insertSelective(ShopStock record);

    List<ShopStock> selectByExample(ShopStockExample example);

    ShopStock selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShopStock record, @Param("example") ShopStockExample example);

    int updateByExample(@Param("record") ShopStock record, @Param("example") ShopStockExample example);

    int updateByPrimaryKeySelective(ShopStock record);

    int updateByPrimaryKey(ShopStock record);
}