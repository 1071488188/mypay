package com.har.unmanned.mfront.dao;

import com.har.unmanned.mfront.model.CodeClassify;
import com.har.unmanned.mfront.model.CodeClassifyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CodeClassifyMapper {
    int countByExample(CodeClassifyExample example);

    int deleteByExample(CodeClassifyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CodeClassify record);

    int insertSelective(CodeClassify record);

    List<CodeClassify> selectByExample(CodeClassifyExample example);

    CodeClassify selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CodeClassify record, @Param("example") CodeClassifyExample example);

    int updateByExample(@Param("record") CodeClassify record, @Param("example") CodeClassifyExample example);

    int updateByPrimaryKeySelective(CodeClassify record);

    int updateByPrimaryKey(CodeClassify record);
}