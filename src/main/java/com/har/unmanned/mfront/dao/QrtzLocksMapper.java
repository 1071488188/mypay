package com.har.unmanned.mfront.dao;

import com.har.unmanned.mfront.model.QrtzLocks;
import com.har.unmanned.mfront.model.QrtzLocksExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QrtzLocksMapper {
    int countByExample(QrtzLocksExample example);

    int deleteByExample(QrtzLocksExample example);

    int deleteByPrimaryKey(@Param("schedName") String schedName, @Param("lockName") String lockName);

    int insert(QrtzLocks record);

    int insertSelective(QrtzLocks record);

    List<QrtzLocks> selectByExample(QrtzLocksExample example);

    int updateByExampleSelective(@Param("record") QrtzLocks record, @Param("example") QrtzLocksExample example);

    int updateByExample(@Param("record") QrtzLocks record, @Param("example") QrtzLocksExample example);
}