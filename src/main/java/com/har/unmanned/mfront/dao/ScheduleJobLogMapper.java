package com.har.unmanned.mfront.dao;

import com.har.unmanned.mfront.model.ScheduleJobLog;
import com.har.unmanned.mfront.model.ScheduleJobLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ScheduleJobLogMapper {
    int countByExample(ScheduleJobLogExample example);

    int deleteByExample(ScheduleJobLogExample example);

    int deleteByPrimaryKey(Long logId);

    int insert(ScheduleJobLog record);

    int insertSelective(ScheduleJobLog record);

    List<ScheduleJobLog> selectByExample(ScheduleJobLogExample example);

    ScheduleJobLog selectByPrimaryKey(Long logId);

    int updateByExampleSelective(@Param("record") ScheduleJobLog record, @Param("example") ScheduleJobLogExample example);

    int updateByExample(@Param("record") ScheduleJobLog record, @Param("example") ScheduleJobLogExample example);

    int updateByPrimaryKeySelective(ScheduleJobLog record);

    int updateByPrimaryKey(ScheduleJobLog record);
}