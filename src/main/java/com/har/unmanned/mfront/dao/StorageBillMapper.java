package com.har.unmanned.mfront.dao;

import com.har.unmanned.mfront.model.StorageBill;
import com.har.unmanned.mfront.model.StorageBillExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StorageBillMapper {
    int countByExample(StorageBillExample example);

    int deleteByExample(StorageBillExample example);

    int deleteByPrimaryKey(Long id);

    int insert(StorageBill record);

    int insertSelective(StorageBill record);

    List<StorageBill> selectByExample(StorageBillExample example);

    StorageBill selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") StorageBill record, @Param("example") StorageBillExample example);

    int updateByExample(@Param("record") StorageBill record, @Param("example") StorageBillExample example);

    int updateByPrimaryKeySelective(StorageBill record);

    int updateByPrimaryKey(StorageBill record);
}