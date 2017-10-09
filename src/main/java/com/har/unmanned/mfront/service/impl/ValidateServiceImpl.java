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
    @Value("${har.frontProxy.sms.template}")
    private String template;
    @Value("${har.frontProxy.sms.optType}")
    private String optType;
    @Autowired
    private SysUserMapperExtend sysUserMapperExtend;
    @Autowired
    private UserUtil userUtil;
    @Autowired
    private AdministratorService administratorService;
    @Autowired
    ApiRequestClient apiRequestClient;
    @Autowired
    ShopWechatMapper shopWechatMapper;
    private static int ADMINROLEID = 3;//网点管理员角色id
    private static int DISTRIBUTIONROLEID=4;//配送员角色id
    private static int DAMAGED=5;//库存盘点

    @Override
    public void sendValidateCode(JSONObject reqParam) throws Exception {
        log.info("发送验证码ServiceImpl传入参数：" + reqParam);
        //传入参数
        JSONObject param = new JSONObject();
        param.put("phone_number", reqParam.getString("mobile"));// 手机号
        param.put("sms_tmp", template);// 发送内容
        param.put("opt_type", optType);// 发送短信操作类型
        apiRequestClient.post(param, "/api/captcha/send");
    }

    @Override
    public void checkValidateCode(JSONObject reqParam) throws Exception {
        log.info("验证验证码ServiceImpl传入参数：" + reqParam);
        //传入参数
        JSONObject param = new JSONObject();
        param.put("phone_number", reqParam.getString("mobile"));// 手机号
        param.put("opt_type", optType);//生成验证码的类型
        param.put("validate_code_input", reqParam.getString("validate_code_input"));// 验证码
        apiRequestClient.post(param, "/api/captcha/check");
    }

    @Override
    public JSONObject permissionsValidation(InputParameter inputParameter) throws Exception {
        Integer roleType=Integer.parseInt(inputParameter.getRoleType());
        ShopWechat shopWechat=userUtil.userInfo();
        JSONObject jsonObject=new JSONObject();
        Long userId=shopWechat.getUserId();
        //1判断系统用户id是否存在
        if(CheckUtil.isNull(userId)){
            jsonObject.put("roleType",0);
        }else {
            //2查询用户为启用状态且角色为配送员或管理员的
            List<SysUserExtend> sysUserExtendList= sysUserMapperExtend.getUserAndRole(userId,1,null);
            if(CheckUtil.isNull(sysUserExtendList)){
                jsonObject.put("roleType",0);
            }else{
                int roleTypeFlag=0;
                for(SysUserExtend sysUserExtend:sysUserExtendList){
                    if(roleType==sysUserExtend.getRoleType()){
                        roleTypeFlag=roleType;
                        break;
                    }
                }
                jsonObject.put("roleType",roleTypeFlag);
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
//        checkValidateCode(verifJsonObject);
        ShopWechat shopWechat = userUtil.userInfo();
        if(!CheckUtil.isNull(shopWechat.getUserId())){
            throw new ApiBizException(ErrorCode.E00000001.CODE,"您已经绑定角色,或绑定信息已被删除",null);
        }
        //2判断是绑定管理员还是配送员
        if("3".equals(roleType)){
            returnJsonObject= bindingRole(inputParameter, cellPhoneNumber, roleType, shopWechat,ADMINROLEID);
        }else if("4".equals(roleType)) {
            returnJsonObject= bindingRole(inputParameter, cellPhoneNumber, roleType, shopWechat,DISTRIBUTIONROLEID);
        }else if("5".equals(roleType)) {
            returnJsonObject= bindingRole(inputParameter, cellPhoneNumber, roleType, shopWechat,DAMAGED);
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
        List<SysUserExtend> sysUserExtendList=sysUserMapperExtend.getUserAndRole(null,1,cellPhoneNumber);
        log.info("根据手机号查询系统用户是否存在,手机号{},查询结果{}", cellPhoneNumber,sysUserExtendList);
        if(CheckUtil.isNull(sysUserExtendList)) {
            throw new ApiBizException(ErrorCode.E00000001.CODE, "当前手机号不存在系统角色!", inputParameter);
        }
        SysUserExtend roleSysUserExtend=null;
        for(SysUserExtend sysUserExtended:sysUserExtendList){
            if(sysUserExtended.getRoleType()==roleid){
                roleSysUserExtend=new SysUserExtend();
                roleSysUserExtend=sysUserExtended;
                break;
            }
        }
        if(CheckUtil.isNull(roleSysUserExtend)){
            throw new ApiBizException(ErrorCode.E00000001.CODE, "当前手机号不存在系统角色!", inputParameter);
        }
        //3将手机号和管理员id写入微信用户表
        shopWechat.setUserId(roleSysUserExtend.getUserId());
        shopWechat.setPhone(cellPhoneNumber);
        shopWechatMapper.updateByPrimaryKeySelective(shopWechat);
        returnJsonObject.put("roleType",roleType);
        return returnJsonObject;
    }
}
