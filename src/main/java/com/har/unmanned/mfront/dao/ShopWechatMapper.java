package com.har.unmanned.mfront.dao;

import com.har.unmanned.mfront.model.ShopWechat;
import com.har.unmanned.mfront.model.ShopWechatExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopWechatMapper {
    int countByExample(ShopWechatExample example);

    int deleteByExample(ShopWechatExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShopWechat record);

    int insertSelective(ShopWechat record);

    List<ShopWechat> selectByExample(ShopWechatExample example);

    ShopWechat selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShopWechat record, @Param("example") ShopWechatExample example);

    int updateByExample(@Param("record") ShopWechat record, @Param("example") ShopWechatExample example);

    int updateByPrimaryKeySelective(ShopWechat record);

    int updateByPrimaryKey(ShopWechat record);
}