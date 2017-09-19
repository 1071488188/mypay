package com.har.unmanned.mfront.dao;

import com.har.unmanned.mfront.model.ShopOrderItem;
import com.har.unmanned.mfront.model.ShopOrderItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopOrderItemMapper {
    int countByExample(ShopOrderItemExample example);

    int deleteByExample(ShopOrderItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShopOrderItem record);

    int insertSelective(ShopOrderItem record);

    List<ShopOrderItem> selectByExample(ShopOrderItemExample example);

    ShopOrderItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShopOrderItem record, @Param("example") ShopOrderItemExample example);

    int updateByExample(@Param("record") ShopOrderItem record, @Param("example") ShopOrderItemExample example);

    int updateByPrimaryKeySelective(ShopOrderItem record);

    int updateByPrimaryKey(ShopOrderItem record);
}