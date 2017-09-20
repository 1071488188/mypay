package com.har.unmanned.mfront.dao.extend;


import org.apache.ibatis.annotations.Param;

/**
 * 系统用户扩展
 */
public interface SysUserMapperExtend {
    /**
     * 判断用户是否拥有某权限
     * @param userId
     * @param roleId
     * @return
     */
    public Integer getuserRole(@Param("userId") Long userId,@Param("roleId") Integer roleId,@Param("mobile") String mobile);

}