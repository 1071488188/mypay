package com.har.unmanned.mfront.dao;

import com.har.unmanned.mfront.model.DispatchorStock;
import com.har.unmanned.mfront.model.DispatchorStockExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DispatchorStockMapper {
    int countByExample(DispatchorStockExample example);

    int deleteByExample(DispatchorStockExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DispatchorStock record);

    int insertSelective(DispatchorStock record);

    List<DispatchorStock> selectByExample(DispatchorStockExample example);

    DispatchorStock selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DispatchorStock record, @Param("example") DispatchorStockExample example);

    int updateByExample(@Param("record") DispatchorStock record, @Param("example") DispatchorStockExample example);

    int updateByPrimaryKeySelective(DispatchorStock record);

    int updateByPrimaryKey(DispatchorStock record);
}