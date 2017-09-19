package com.har.unmanned.mfront.dao;

import com.har.unmanned.mfront.model.QrtzSimpropTriggers;
import com.har.unmanned.mfront.model.QrtzSimpropTriggersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QrtzSimpropTriggersMapper {
    int countByExample(QrtzSimpropTriggersExample example);

    int deleteByExample(QrtzSimpropTriggersExample example);

    int deleteByPrimaryKey(@Param("schedName") String schedName, @Param("triggerName") String triggerName, @Param("triggerGroup") String triggerGroup);

    int insert(QrtzSimpropTriggers record);

    int insertSelective(QrtzSimpropTriggers record);

    List<QrtzSimpropTriggers> selectByExample(QrtzSimpropTriggersExample example);

    QrtzSimpropTriggers selectByPrimaryKey(@Param("schedName") String schedName, @Param("triggerName") String triggerName, @Param("triggerGroup") String triggerGroup);

    int updateByExampleSelective(@Param("record") QrtzSimpropTriggers record, @Param("example") QrtzSimpropTriggersExample example);

    int updateByExample(@Param("record") QrtzSimpropTriggers record, @Param("example") QrtzSimpropTriggersExample example);

    int updateByPrimaryKeySelective(QrtzSimpropTriggers record);

    int updateByPrimaryKey(QrtzSimpropTriggers record);
}