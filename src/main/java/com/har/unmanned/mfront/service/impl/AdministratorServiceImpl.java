package com.har.unmanned.mfront.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.har.unmanned.mfront.api.administrator.InputParameter;
import com.har.unmanned.mfront.config.CodeConstants;
import com.har.unmanned.mfront.config.ErrorCode;
import com.har.unmanned.mfront.dao.*;
import com.har.unmanned.mfront.dao.extend.*;
import com.har.unmanned.mfront.exception.ApiBizException;
import com.har.unmanned.mfront.model.*;
import com.har.unmanned.mfront.model.extend.ShopCommissionExtend;
import com.har.unmanned.mfront.model.extend.ShopExpressiveExtend;
import com.har.unmanned.mfront.model.extend.ShopOrderExtend;
import com.har.unmanned.mfront.service.AdministratorService;
import com.har.unmanned.mfront.utils.CheckUtil;
import com.har.unmanned.mfront.utils.PageUtil;
import com.har.unmanned.mfront.utils.UserUtil;
import kafka.utils.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
    @Autowired
    ShopMapper shopMapper;
    @Autowired
    ShopOrderMapperExtend shopOrderMapperExtend;
    @Autowired
    ShopCommissionMapperExtend shopCommissionMapperExtend;
    @Autowired
    ShopMapperExtend shopMapperExtend;
    @Autowired
    ShopExpressiveMapperExtend shopExpressiveMapperExtend;
    private static int roleId = 3;//网点管理员权限

    @Override
    public void verifyPermissions() throws Exception {
        //获取当前用户信息
        ShopWechat shopWechat = userUtil.userInfo();
        String openid = shopWechat.getOpenid();
        Long userId = shopWechat.getUserId();
        if (CheckUtil.isNull(userId)) {
            throw new ApiBizException(ErrorCode.E00000001.CODE, "无权限操作", shopWechat);
        }
        //判断用户是否为网点管理员
        int count = sysUserMapperExtend.getuserRole(userId, roleId, null);
        if (count == 0) {
            throw new ApiBizException(ErrorCode.E00000001.CODE, "无权限操作", shopWechat);
        }

    }

    @Override
    public int adminInit() throws Exception {
        int flag = 0;
        //获取当前用户信息
        ShopWechat shopWechat = userUtil.userInfo();
        log.info("查询是否为网点管理员当前用户信息{}", shopWechat);
        String openid = shopWechat.getOpenid();
        Long userId = shopWechat.getUserId();
        if (!CheckUtil.isNull(userId)) {
            //判断用户是否为网点管理员
            int count = sysUserMapperExtend.getuserRole(userId, roleId, null);
            if (count > 0) {
                flag = 1;
            }
        }
        log.info("用户信息{},查询是否为网点管理员返回参数{}", shopWechat, flag);
        return flag;
    }

    @Override
    public void bindManager(InputParameter inputParameter) throws Exception {
        String cellPhoneNumber = inputParameter.getCellPhoneNumber();//手机号
        String verificationCode = inputParameter.getVerificationCode();//验证码
        int flag = adminInit();
        if (flag == 1) {
            throw new ApiBizException(ErrorCode.E00000001.CODE, "您已经是网点管理员!", inputParameter);
        }
        //根据手机号查询当前手机号是否为网点管理员
        int count = sysUserMapperExtend.getuserRole(null, roleId, cellPhoneNumber);
        if (count == 0) {
            throw new ApiBizException(ErrorCode.E00000001.CODE, "当前手机号暂无网点管理员数据!", inputParameter);
        }
        SysUserExample sysUserExample = new SysUserExample();
        SysUserExample.Criteria criteria = sysUserExample.createCriteria();
        criteria.andMobileEqualTo(cellPhoneNumber);
        sysUserExample.setOrderByClause(" userId asc");
        List<SysUser> sysUserList = sysUserMapper.selectByExample(sysUserExample);
        SysUser sysUser = sysUserList.get(0);
        ShopWechat shopWechat = userUtil.userInfo();
        shopWechat.setUserId(sysUser.getUserId());
        shopWechatMapper.updateByExampleSelective(shopWechat, null);

    }

    @Override
    public JSONObject expenseCalendar(InputParameter inputParameter) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Integer page = inputParameter.getPage();
        Integer pageSize = inputParameter.getPageSize();
        //1根据用户user_id查询所管理的网点
        Shop shop = getShop();
        log.info("当前用户网点查询结果{}", shop);
        //2查询累计消费人数
        int countByShopId = shopOrderMapperExtend.countByShopId(shop.getId());
        jsonObject.put("theNumberOfConsumer", countByShopId);
        log.info("查询累计消费人数{}", countByShopId);
        //3查询列表
        PageUtil.startPage(page, pageSize);
        List<ShopOrderExtend> shopOrderExtendList = shopOrderMapperExtend.sumByShopId(shop.getId());
        jsonObject.put("particulars", shopOrderExtendList);
        log.info("查询列表{}", shopOrderExtendList);
        return jsonObject;
    }

    @Override
    public JSONArray settlementRecords(InputParameter inputParameter) throws Exception {
        JSONArray jsonArray = new JSONArray();
        Integer page = inputParameter.getPage();
        Integer pageSize = inputParameter.getPageSize();
        Shop shop = getShop();
        //1根据用户user_id查询所管理的网点
        log.info("当前用户网点查询结果{}", shop);
        //2查询网点佣金记录列表
        PageUtil.startPage(page, pageSize);
        List<ShopCommissionExtend> shopCommissionExtendList = shopCommissionMapperExtend.selectListByShopId(shop.getId());
        if (!CheckUtil.isNull(shopCommissionExtendList) && shopCommissionExtendList.size() > 0) {
            jsonArray.add(shopCommissionExtendList);
        }
        return jsonArray;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public void closeAnAccount(InputParameter inputParameter) throws Exception {
        Long billingId = Long.parseLong(inputParameter.getBillingId());
        //获取当前用户信息
        ShopWechat shopWechat = userUtil.userInfo();
        //1验证是否有权限
        verifyPermissions();
        //2查询当前用户是否包含该清单
        ShopCommissionExtend shopCommissionExtend = shopCommissionMapperExtend.countByUserIdAndId(billingId, shopWechat.getUserId());
        log.info("清单id{},查询当前用户是否包含清单结果{}", billingId, shopCommissionExtend);
        if (CheckUtil.isNull(shopCommissionExtend)) {
            throw new ApiBizException(ErrorCode.E00000001.CODE, "无权执行该操作", inputParameter);
        }
        updateBalence(billingId, shopWechat, shopCommissionExtend);


    }

    /**
     * 修改修改余额以及保存流水
     * @param billingId
     * @param shopWechat
     * @param shopCommissionExtend
     */
    private synchronized void updateBalence(Long billingId, ShopWechat shopWechat, ShopCommissionExtend shopCommissionExtend) {
        //3修改状态为已结算
        ShopCommission shopCommission = new ShopCommission();
        shopCommission.setId(billingId);
        shopCommission.setUserId(shopWechat.getUserId());
        shopCommission.setApplyTime(new Date());
        int updateByPrimaryKeySelective = shopCommissionMapperExtend.updateByPrimaryKeySelective(shopCommission);
        log.info("清单id{},修改状态为已结算结果{}", billingId, updateByPrimaryKeySelective);
        //4修改网点余额
        int updateShopAccountMoneyAndShopId = shopMapperExtend.updateShopAccountMoneyAndShopId(shopCommissionExtend.getShopId(), shopCommissionExtend.getCommission());
        log.info("清单id{},修改网点余额结果{}", billingId, updateShopAccountMoneyAndShopId);
        //5添加流水
        ShopExpressive shopExpressive = new ShopExpressive();
        shopExpressive.setMoney(shopCommissionExtend.getCommission());
        shopExpressive.setType(CodeConstants.WithdrawCurrentType.COMMISSIONSETTLEMENT);
        shopExpressive.setUserId(shopWechat.getUserId());
        shopExpressive.setApplyTime(new Date());
        shopExpressive.setShopId(shopCommissionExtend.getShopId());
        shopExpressive.setExpressiveNo(System.currentTimeMillis() + "");
        shopExpressiveMapperExtend.insertSelective(shopExpressive);
    }

    @Override
    public JSONArray balanceDetails(InputParameter inputParameter) throws Exception {
        JSONArray jsonArray=new JSONArray();
        int pageSize = inputParameter.getPageSize();
        int page = inputParameter.getPage();
        //1查询当前管理员所在王丹
        Shop shop=getShop();
        //2查询网点余额明细
        PageUtil.startPage(page, pageSize);
        List<ShopExpressiveExtend> list=shopExpressiveMapperExtend.selectBalanceOfSubsidiary(shop.getId());
        jsonArray.add(list);
        return jsonArray;
    }

    private Shop getShop() throws Exception {
        verifyPermissions();//验证是否有权限
        ShopWechat shopWechat = userUtil.userInfo();
        ShopExample shopExample = new ShopExample();
        ShopExample.Criteria criteria = shopExample.createCriteria();
        criteria.andUserIdEqualTo(shopWechat.getUserId());
        shopExample.setOrderByClause(" id asc");
        List<Shop> shopList = shopMapper.selectByExample(shopExample);
        return shopList.get(0);
    }

    @Override
    public String toString() {
        return super.toString();
    }


}
