package com.har.unmanned.mfront.dao;

import com.har.unmanned.mfront.model.ShopCourier;
import com.har.unmanned.mfront.model.ShopCourierExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopCourierMapper {
    int countByExample(ShopCourierExample example);

    int deleteByExample(ShopCourierExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopCourier record);

    int insertSelective(ShopCourier record);

    List<ShopCourier> selectByExample(ShopCourierExample example);

    ShopCourier selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopCourier record, @Param("example") ShopCourierExample example);

    int updateByExample(@Param("record") ShopCourier record, @Param("example") ShopCourierExample example);

    int updateByPrimaryKeySelective(ShopCourier record);

    int updateByPrimaryKey(ShopCourier record);
}