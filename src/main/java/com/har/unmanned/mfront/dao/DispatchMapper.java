package com.har.unmanned.mfront.dao;

import com.har.unmanned.mfront.model.Dispatch;
import com.har.unmanned.mfront.model.DispatchExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DispatchMapper {
    int countByExample(DispatchExample example);

    int deleteByExample(DispatchExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Dispatch record);

    int insertSelective(Dispatch record);

    List<Dispatch> selectByExample(DispatchExample example);

    Dispatch selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Dispatch record, @Param("example") DispatchExample example);

    int updateByExample(@Param("record") Dispatch record, @Param("example") DispatchExample example);

    int updateByPrimaryKeySelective(Dispatch record);

    int updateByPrimaryKey(Dispatch record);
}