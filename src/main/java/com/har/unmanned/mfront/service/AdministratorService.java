package com.har.unmanned.mfront.service;

/**
 * 管理员接口service
 * Created by jiang on 2017/9/20.
 */
public interface AdministratorService {
    /**
     * 验证是否有权限
     */
    public void verifyPermissions() throws Exception;
}
