package com.har.unmanned.mfront.dao;

import com.har.unmanned.mfront.model.ShopExpressive;
import com.har.unmanned.mfront.model.ShopExpressiveExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopExpressiveMapper {
    int countByExample(ShopExpressiveExample example);

    int deleteByExample(ShopExpressiveExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopExpressive record);

    int insertSelective(ShopExpressive record);

    List<ShopExpressive> selectByExample(ShopExpressiveExample example);

    ShopExpressive selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopExpressive record, @Param("example") ShopExpressiveExample example);

    int updateByExample(@Param("record") ShopExpressive record, @Param("example") ShopExpressiveExample example);

    int updateByPrimaryKeySelective(ShopExpressive record);

    int updateByPrimaryKey(ShopExpressive record);
}