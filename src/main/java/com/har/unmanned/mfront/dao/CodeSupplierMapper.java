package com.har.unmanned.mfront.dao;

import com.har.unmanned.mfront.model.CodeSupplier;
import com.har.unmanned.mfront.model.CodeSupplierExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CodeSupplierMapper {
    int countByExample(CodeSupplierExample example);

    int deleteByExample(CodeSupplierExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CodeSupplier record);

    int insertSelective(CodeSupplier record);

    List<CodeSupplier> selectByExample(CodeSupplierExample example);

    CodeSupplier selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CodeSupplier record, @Param("example") CodeSupplierExample example);

    int updateByExample(@Param("record") CodeSupplier record, @Param("example") CodeSupplierExample example);

    int updateByPrimaryKeySelective(CodeSupplier record);

    int updateByPrimaryKey(CodeSupplier record);
}