package com.har.unmanned.mfront.dao;

import com.har.unmanned.mfront.model.StorageBillItem;
import com.har.unmanned.mfront.model.StorageBillItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StorageBillItemMapper {
    int countByExample(StorageBillItemExample example);

    int deleteByExample(StorageBillItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(StorageBillItem record);

    int insertSelective(StorageBillItem record);

    List<StorageBillItem> selectByExample(StorageBillItemExample example);

    StorageBillItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") StorageBillItem record, @Param("example") StorageBillItemExample example);

    int updateByExample(@Param("record") StorageBillItem record, @Param("example") StorageBillItemExample example);

    int updateByPrimaryKeySelective(StorageBillItem record);

    int updateByPrimaryKey(StorageBillItem record);
}