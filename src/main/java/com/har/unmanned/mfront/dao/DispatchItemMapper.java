package com.har.unmanned.mfront.dao;

import com.har.unmanned.mfront.model.DispatchItem;
import com.har.unmanned.mfront.model.DispatchItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DispatchItemMapper {
    int countByExample(DispatchItemExample example);

    int deleteByExample(DispatchItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DispatchItem record);

    int insertSelective(DispatchItem record);

    List<DispatchItem> selectByExample(DispatchItemExample example);

    DispatchItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DispatchItem record, @Param("example") DispatchItemExample example);

    int updateByExample(@Param("record") DispatchItem record, @Param("example") DispatchItemExample example);

    int updateByPrimaryKeySelective(DispatchItem record);

    int updateByPrimaryKey(DispatchItem record);
}