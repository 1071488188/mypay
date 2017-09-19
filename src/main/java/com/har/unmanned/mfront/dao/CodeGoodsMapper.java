package com.har.unmanned.mfront.dao;

import com.har.unmanned.mfront.model.CodeGoods;
import com.har.unmanned.mfront.model.CodeGoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CodeGoodsMapper {
    int countByExample(CodeGoodsExample example);

    int deleteByExample(CodeGoodsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CodeGoods record);

    int insertSelective(CodeGoods record);

    List<CodeGoods> selectByExample(CodeGoodsExample example);

    CodeGoods selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CodeGoods record, @Param("example") CodeGoodsExample example);

    int updateByExample(@Param("record") CodeGoods record, @Param("example") CodeGoodsExample example);

    int updateByPrimaryKeySelective(CodeGoods record);

    int updateByPrimaryKey(CodeGoods record);
}