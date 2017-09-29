package com.har.unmanned.mfront.dao;

import com.har.unmanned.mfront.model.ShopStockLose;
import com.har.unmanned.mfront.model.ShopStockLoseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopStockLoseMapper {
    int countByExample(ShopStockLoseExample example);

    int deleteByExample(ShopStockLoseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShopStockLose record);

    int insertSelective(ShopStockLose record);

    List<ShopStockLose> selectByExample(ShopStockLoseExample example);

    ShopStockLose selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShopStockLose record, @Param("example") ShopStockLoseExample example);

    int updateByExample(@Param("record") ShopStockLose record, @Param("example") ShopStockLoseExample example);

    int updateByPrimaryKeySelective(ShopStockLose record);

    int updateByPrimaryKey(ShopStockLose record);
}