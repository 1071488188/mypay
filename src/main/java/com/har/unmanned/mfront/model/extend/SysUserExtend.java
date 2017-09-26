package com.har.unmanned.mfront.model.extend;

import com.har.unmanned.mfront.model.SysUser;
import lombok.Data;

/**
 * 系统用户扩展
 */
@Data
public class SysUserExtend extends SysUser {
    private Integer roleType;//权限类型3、网点管理员4、配送员

}