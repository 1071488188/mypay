package com.har.unmanned.mfront.dao.extend;


import com.har.unmanned.mfront.dao.SysUserMapper;
import com.har.unmanned.mfront.model.extend.SysUserExtend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统用户扩展
 */
public interface SysUserMapperExtend extends SysUserMapper {
    /**
     * 判断用户是否拥有管理员权限
     * @param userId
     * @param roleId
     * @return
     */
    public Integer getuserRole(@Param("userId") Long userId,@Param("roleId") Integer roleId,@Param("mobile") String mobile);
    /**
     * 判断用户是否拥有权限
     * @param userId
     * @param roleId
     * @return
     */
    public Integer queryUserRole(@Param("userId") String userId,@Param("roleId") Integer roleId);

    /**
     * 根据用户是否禁用状态查询用户所存在的角色
     * @param userId
     * @param status
     * @param mobile
     * @return
     */
    public List<SysUserExtend> getUserAndRole(@Param("userId") Long userId, @Param("status") Integer status, @Param("mobile") String mobile);

    /**
     * 
     * @param userId
     * @param status
     * @param mobile
     * @param roleId
     * @return
     */
    public Integer distributionIsBinding(@Param("userId") Long userId, @Param("status") Integer status,@Param("mobile") String mobile,@Param("roleId") Integer roleId);

}