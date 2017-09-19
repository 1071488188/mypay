package com.har.unmanned.mfront.dao;

import com.har.unmanned.mfront.model.ShopCommission;
import com.har.unmanned.mfront.model.ShopCommissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopCommissionMapper {
    int countByExample(ShopCommissionExample example);

    int deleteByExample(ShopCommissionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShopCommission record);

    int insertSelective(ShopCommission record);

    List<ShopCommission> selectByExample(ShopCommissionExample example);

    ShopCommission selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShopCommission record, @Param("example") ShopCommissionExample example);

    int updateByExample(@Param("record") ShopCommission record, @Param("example") ShopCommissionExample example);

    int updateByPrimaryKeySelective(ShopCommission record);

    int updateByPrimaryKey(ShopCommission record);
}