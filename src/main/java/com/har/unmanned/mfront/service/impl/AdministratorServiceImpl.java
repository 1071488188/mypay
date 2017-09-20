package com.har.unmanned.mfront.service.impl;

import com.har.unmanned.mfront.api.administrator.InputParameter;
import com.har.unmanned.mfront.config.ErrorCode;
import com.har.unmanned.mfront.dao.ShopWechatMapper;
import com.har.unmanned.mfront.dao.SysUserMapper;
import com.har.unmanned.mfront.dao.extend.SysUserMapperExtend;
import com.har.unmanned.mfront.exception.ApiBizException;
import com.har.unmanned.mfront.model.ShopWechat;
import com.har.unmanned.mfront.model.SysUser;
import com.har.unmanned.mfront.model.SysUserExample;
import com.har.unmanned.mfront.service.AdministratorService;
import com.har.unmanned.mfront.utils.CheckUtil;
import com.har.unmanned.mfront.utils.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiang on 2017/9/20.
 */
@Service
@Slf4j
public class AdministratorServiceImpl implements AdministratorService {
    @Autowired
    UserUtil userUtil;
    @Autowired
    SysUserMapperExtend sysUserMapperExtend;
    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    ShopWechatMapper shopWechatMapper;
    private static int roleId=3;//网点管理员权限
    @Override
    public void verifyPermissions() throws Exception {
        //获取当前用户信息
        ShopWechat shopWechat= userUtil.userInfo();
        String openid=shopWechat.getOpenid();
        Long userId=shopWechat.getUserId();
        if(CheckUtil.isNull(userId)){
            throw new ApiBizException(ErrorCode.E00000001.CODE,"无权限操作",shopWechat);
        }
        //判断用户是否为网点管理员
       int count= sysUserMapperExtend.getuserRole(userId,roleId,null);
        if(count==0){
            throw new ApiBizException(ErrorCode.E00000001.CODE,"无权限操作",shopWechat);
        }

    }

    @Override
    public int adminInit() throws Exception {
        int flag=0;
        //获取当前用户信息
        ShopWechat shopWechat= userUtil.userInfo();
        log.info("查询是否为网点管理员当前用户信息{}",shopWechat);
        String openid=shopWechat.getOpenid();
        Long userId=shopWechat.getUserId();
        if(!CheckUtil.isNull(userId)){
            //判断用户是否为网点管理员
            int count= sysUserMapperExtend.getuserRole(userId,roleId,null);
            if(count>0){
                flag=1;
            }
        }
        log.info("用户信息{},查询是否为网点管理员返回参数{}",shopWechat,flag);
        return flag;
    }

    @Override
    public void bindManager(InputParameter inputParameter) throws Exception {
       String cellPhoneNumber= inputParameter.getCellPhoneNumber();//手机号
       String verificationCode= inputParameter.getVerificationCode();//验证码
       int flag= adminInit();
       if(flag==1){
           throw new ApiBizException(ErrorCode.E00000001.CODE,"您已经是网点管理员!",inputParameter);
       }
       //根据手机号查询当前手机号是否为网点管理员
        int count= sysUserMapperExtend.getuserRole(null,roleId,cellPhoneNumber);
       if(count==0){
           throw new ApiBizException(ErrorCode.E00000001.CODE,"当前手机号暂无网点管理员数据!",inputParameter);
       }
        SysUserExample sysUserExample=new SysUserExample();
        SysUserExample.Criteria criteria= sysUserExample.createCriteria();
        criteria.andMobileEqualTo(cellPhoneNumber);
        sysUserExample.setOrderByClause(" userId asc");
        List<SysUser> sysUserList= sysUserMapper.selectByExample(sysUserExample);
        SysUser sysUser=sysUserList.get(0);
        ShopWechat shopWechat= userUtil.userInfo();
        shopWechat.setUserId(sysUser.getUserId());
        shopWechatMapper.updateByExampleSelective(shopWechat,null);

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
