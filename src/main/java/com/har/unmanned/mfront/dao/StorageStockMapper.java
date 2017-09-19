package com.har.unmanned.mfront.dao;

import com.har.unmanned.mfront.model.StorageStock;
import com.har.unmanned.mfront.model.StorageStockExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StorageStockMapper {
    int countByExample(StorageStockExample example);

    int deleteByExample(StorageStockExample example);

    int deleteByPrimaryKey(Long id);

    int insert(StorageStock record);

    int insertSelective(StorageStock record);

    List<StorageStock> selectByExample(StorageStockExample example);

    StorageStock selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") StorageStock record, @Param("example") StorageStockExample example);

    int updateByExample(@Param("record") StorageStock record, @Param("example") StorageStockExample example);

    int updateByPrimaryKeySelective(StorageStock record);

    int updateByPrimaryKey(StorageStock record);
}