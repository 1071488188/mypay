package com.har.unmanned.mfront.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.har.unmanned.mfront.api.validate.InputParameter;
import com.har.unmanned.mfront.config.ErrorCode;
import com.har.unmanned.mfront.dao.ShopWechatMapper;
import com.har.unmanned.mfront.dao.extend.SysUserMapperExtend;
import com.har.unmanned.mfront.exception.ApiBizException;
import com.har.unmanned.mfront.model.ShopWechat;
import com.har.unmanned.mfront.model.SysUser;
import com.har.unmanned.mfront.model.SysUserExample;
import com.har.unmanned.mfront.model.extend.SysUserExtend;
import com.har.unmanned.mfront.service.AdministratorService;
import com.har.unmanned.mfront.service.ValidateService;
import com.har.unmanned.mfront.utils.ApiRequestClient;
import com.har.unmanned.mfront.utils.CheckUtil;
import com.har.unmanned.mfront.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidateServiceImpl implements ValidateService {
    private static final Logger log = LoggerFactory.getLogger(ValidateServiceImpl.class);
    @Value("{har.sms.template}")
    private String template;
    @Value("{har.sms.optType}")
    private String optType;
    @Autowired
    private SysUserMapperExtend sysUserMapperExtend;
    @Autowired
    private UserUtil userUtil;
    @Autowired
    private AdministratorService administratorService;
    @Autowired
    ShopWechatMapper shopWechatMapper;
    private static int ADMINROLEID = 3;//网点管理员角色id
    private static int DISTRIBUTIONROLEID=3;//配送员角色id

    @Override
    public void sendValidateCode(JSONObject reqParam) throws Exception {
        log.info("发送验证码ServiceImpl传入参数：" + reqParam);
        //传入参数
        JSONObject param = new JSONObject();
        param.put("phone_number", reqParam.getString("mobile"));// 手机号
        param.put("validate_type", 1);// 生成验证码的类型
        param.put("content", template);// 发送内容
        param.put("opt_type", optType);// 发送短信操作类型
        ApiRequestClient.post(param, "/login/validate/HF50001");
    }

    @Override
    public void checkValidateCode(JSONObject reqParam) throws Exception {
        log.info("验证验证码ServiceImpl传入参数：" + reqParam);
        //传入参数
        JSONObject param = new JSONObject();
        param.put("phone_number", reqParam.getString("mobile"));// 手机号
        param.put("validate_type", 1);//生成验证码的类型
        param.put("validate_code_input", reqParam.getString("validateCode"));// 验证码
        ApiRequestClient.post(param, "/login/validate/HF50002");
    }

    @Override
    public JSONObject permissionsValidation() throws Exception {
        ShopWechat shopWechat=userUtil.userInfo();
        JSONObject jsonObject=new JSONObject();
        Long userId=shopWechat.getUserId();
        //1判断系统用户id是否存在
        if(CheckUtil.isNull(userId)){
            jsonObject.put("roleType",0);
        }else {
            //2查询用户为启用状态且角色为配送员或管理员的
            SysUserExtend sysUserExtend= sysUserMapperExtend.getUserAndRole(userId,1,null);
            if(CheckUtil.isNull(sysUserExtend)){
                jsonObject.put("roleType",0);
            }else{
                jsonObject.put("roleType",sysUserExtend.getRoleType());
            }
        }
        return jsonObject;
    }

    @Override
    public JSONObject bindingPhone(InputParameter inputParameter) throws Exception {
        JSONObject returnJsonObject=new JSONObject();

       String verificationCode= inputParameter.getVerificationCode();
       String cellPhoneNumber= inputParameter.getCellPhoneNumber();
       String roleType= inputParameter.getRoleType();
        JSONObject verifJsonObject=new JSONObject();
        //1验证验证码是否正确
        verifJsonObject.put("mobile",cellPhoneNumber);
        verifJsonObject.put("validate_code_input",verificationCode);
        checkValidateCode(verifJsonObject);
        ShopWechat shopWechat = userUtil.userInfo();
        if(!CheckUtil.isNull(shopWechat.getUserId())){
            throw new ApiBizException(ErrorCode.E00000001.CODE,"您已经绑定角色,或绑定信息已被删除",null);
        }
        //2判断是绑定管理员还是配送员
        if("3".equals(roleType)){
            returnJsonObject= bindingRole(inputParameter, cellPhoneNumber, roleType, shopWechat,DISTRIBUTIONROLEID);
        }else if("4".equals(roleType)) {
            returnJsonObject= bindingRole(inputParameter, cellPhoneNumber, roleType, shopWechat,DISTRIBUTIONROLEID);
        }else{
            throw new ApiBizException(ErrorCode.E00000001.CODE, "参数错误",inputParameter);
        }
        return returnJsonObject;
    }

    private JSONObject bindingRole(InputParameter inputParameter,  String cellPhoneNumber, String roleType, ShopWechat shopWechat,int roleid) throws ApiBizException {
        JSONObject returnJsonObject=new JSONObject();
        //1根据手机号查询当前手机号是否已经被绑定
        int count=sysUserMapperExtend.distributionIsBinding(null ,1,cellPhoneNumber,roleid);
        log.info("根据手机号查询当前手机号是否已经被绑定,手机号{},查询结果{}", cellPhoneNumber,count);
        if (count >0) {
            throw new ApiBizException(ErrorCode.E00000001.CODE, "当前手机号已经被绑定!", inputParameter);
        }
        //2查询出当前手机号所存在的角色信息
        SysUserExtend sysUserExtend=sysUserMapperExtend.getUserAndRole(null,1,cellPhoneNumber);
        log.info("根据手机号查询系统用户是否存在,手机号{},查询结果{}", cellPhoneNumber,sysUserExtend);
        if(CheckUtil.isNull(sysUserExtend)||(!CheckUtil.isNull(sysUserExtend)&&sysUserExtend.getRoleType()!=roleid)) {
            throw new ApiBizException(ErrorCode.E00000001.CODE, "当前手机号不存在系统角色!", inputParameter);
        }
        //3将手机号和管理员id写入微信用户表
        shopWechat.setUserId(sysUserExtend.getUserId());
        shopWechat.setPhone(cellPhoneNumber);
        shopWechatMapper.updateByPrimaryKeySelective(shopWechat);
        returnJsonObject.put("roleType",roleType);
        return returnJsonObject;
    }
}