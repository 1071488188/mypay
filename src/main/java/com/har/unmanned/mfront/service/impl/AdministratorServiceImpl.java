package com.har.unmanned.mfront.service.impl;

import com.har.unmanned.mfront.config.ErrorCode;
import com.har.unmanned.mfront.exception.ApiBizException;
import com.har.unmanned.mfront.model.ShopWechat;
import com.har.unmanned.mfront.service.AdministratorService;
import com.har.unmanned.mfront.utils.CheckUtil;
import com.har.unmanned.mfront.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jiang on 2017/9/20.
 */
@Service
public class AdministratorServiceImpl implements AdministratorService {
    @Autowired
    UserUtil userUtil;
    @Override
    public void verifyPermissions() throws Exception {
        //获取当前用户信息
        ShopWechat shopWechat= userUtil.userInfo();
        String openid=shopWechat.getOpenid();
        Long userId=shopWechat.getUserId();
        if(CheckUtil.isNull(userId)){
            throw new ApiBizException(ErrorCode.E00000001.CODE,"无权限操作",shopWechat);
        }

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
