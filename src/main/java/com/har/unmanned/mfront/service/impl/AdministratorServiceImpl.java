package com.har.unmanned.mfront.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.har.unmanned.mfront.api.administrator.InputParameter;
import com.har.unmanned.mfront.config.CodeConstants;
import com.har.unmanned.mfront.config.ErrorCode;
import com.har.unmanned.mfront.dao.ShopMapper;
import com.har.unmanned.mfront.dao.ShopWechatMapper;
import com.har.unmanned.mfront.dao.SysUserMapper;
import com.har.unmanned.mfront.dao.extend.*;
import com.har.unmanned.mfront.exception.ApiBizException;
import com.har.unmanned.mfront.model.*;
import com.har.unmanned.mfront.model.extend.ShopCommissionExtend;
import com.har.unmanned.mfront.model.extend.ShopExpressiveExtend;
import com.har.unmanned.mfront.model.extend.ShopOrderExtend;
import com.har.unmanned.mfront.service.AdministratorService;
import com.har.unmanned.mfront.service.ValidateService;
import com.har.unmanned.mfront.utils.CheckUtil;
import com.har.unmanned.mfront.utils.PageUtil;
import com.har.unmanned.mfront.utils.UserUtil;
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
    @Autowired
    private ValidateService securityCodeService;
    @Autowired
    RedisServiceImpl redisService;
    private static int roleId = 3;//网点管理员权限
    private static String SETTLEMENTOFREPEATSUBMISSION="settlementofrepeatsubmission";//结算防重复提交
    private static String WITHDRAWALPREVENTIONANDREPEATSUBMISSION="withdrawalpreventionandrepeatsubmission";//提现防重复提交

    @Override
    public void verifyPermissions() throws Exception {
        //获取当前用户信息
        ShopWechat shopWechat = userUtil.userInfo();
        log.info("当前用户信息{}", shopWechat);
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
        Page page1=PageUtil.startPage(page, pageSize);
        List<ShopOrderExtend> shopOrderExtendList = shopOrderMapperExtend.sumByShopId(shop.getId());
        log.info("总条数{}", page1.getTotal());
        jsonObject.put("particulars", shopOrderExtendList);
        log.info("查询列表{}", shopOrderExtendList);
        return jsonObject;
    }

    @Override
    public JSONObject settlementRecords(InputParameter inputParameter) throws Exception {
        JSONObject jsonObject =new JSONObject();

        Integer page = inputParameter.getPage();
        Integer pageSize = inputParameter.getPageSize();
        Shop shop = getShop();
        //2查询网点佣金记录列表
       Page page1= PageUtil.startPage(page, pageSize);
        List<ShopCommissionExtend> shopCommissionExtendList = shopCommissionMapperExtend.selectListByShopId(shop.getId());
        if (!CheckUtil.isNull(shopCommissionExtendList) && shopCommissionExtendList.size() > 0) {
            log.info("查询网点佣金记录列表{}", shopCommissionExtendList.size());

        }
        jsonObject.put("totalCount",page1.getTotal());
        jsonObject.put("totalList",shopCommissionExtendList);
        return jsonObject;
    }

    @Override
    public void closeAnAccount(InputParameter inputParameter) throws Exception {
        Long billingId = Long.parseLong(inputParameter.getBillingId());
        //获取当前用户信息
        ShopWechat shopWechat = userUtil.userInfo();
        Object object=redisService.get(SETTLEMENTOFREPEATSUBMISSION);
        if (!CheckUtil.isNull(object)){
            throw new ApiBizException(ErrorCode.E00000001.CODE, "请勿重复点击", shopWechat);
        }
        redisService.put(SETTLEMENTOFREPEATSUBMISSION, shopWechat.getOpenid(), 10);
        //1验证是否有权限
        verifyPermissions();
        //2查询当前用户是否包含该清单
        ShopCommissionExtend shopCommissionExtend = shopCommissionMapperExtend.countByUserIdAndId(billingId, shopWechat.getUserId());
        log.info("清单id{},查询当前用户是否包含清单结果{}", billingId, shopCommissionExtend);
        if (CheckUtil.isNull(shopCommissionExtend)) {
            throw new ApiBizException(ErrorCode.E00000001.CODE, "该记录不存在或已结算", inputParameter);
        }
        updateBalence(billingId, shopWechat, shopCommissionExtend);


    }

    /**
     * 增加余额以及保存流水
     * @param billingId
     * @param shopWechat
     * @param shopCommissionExtend
     */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    private synchronized void updateBalence(Long billingId, ShopWechat shopWechat, ShopCommissionExtend shopCommissionExtend) {
        //3修改状态为已结算
        ShopCommission shopCommission = new ShopCommission();
        shopCommission.setId(billingId);
        shopCommission.setUserId(shopWechat.getUserId());
        shopCommission.setApplyTime(new Date());
        shopCommission.setStatus(CodeConstants.CommissionStatus.HAVEALREADYSETTLED);
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
        int flag=shopExpressiveMapperExtend.insertSelective(shopExpressive);
        log.info("清单id{},保存流水结果{}", billingId, flag);
    }

    @Override
    public JSONObject balanceDetails(InputParameter inputParameter) throws Exception {
        JSONObject jsonObject =new JSONObject();
        int pageSize = inputParameter.getPageSize();
        int page = inputParameter.getPage();
        //1查询当前管理员所在网点
        Shop shop=getShop();
        //2查询网点余额明细
        Page page1=PageUtil.startPage(page, pageSize);
        List<ShopExpressiveExtend> list=shopExpressiveMapperExtend.selectBalanceOfSubsidiary(shop.getId());
        if(!CheckUtil.isNull(list)&&list.size()>0){
            log.info("查询余额明细结果总条数{}", list.size());
        }
        Double money= Double.parseDouble(shop.getShopAccountMoney()+"");

        jsonObject.put("shopAccountMoneyZh",CheckUtil.m2(Double.parseDouble((money/100)+"")));
        jsonObject.put("totalList",list);
        jsonObject.put("totalCount",page1.getTotal());
        return jsonObject;
    }

    @Override
    public  void withdrawDeposit(InputParameter inputParameter) throws Exception {
        int reflectTheAmountOf=(int)(Double.parseDouble(inputParameter.getReflectTheAmountOf())*100);//提现金额
        log.info("提现传入金额{}", reflectTheAmountOf);
        //1得到当前网点账户
        Shop shop=getShop();
        Object object=redisService.get(WITHDRAWALPREVENTIONANDREPEATSUBMISSION);
        if (!CheckUtil.isNull(object)){
            throw new ApiBizException(ErrorCode.E00000001.CODE, "请勿重复点击", shop);
        }
        redisService.put(WITHDRAWALPREVENTIONANDREPEATSUBMISSION, shop.getId()+"", 10);
        //2、判断余额是否足够
        if(shop.getShopAccountMoney()-reflectTheAmountOf<0){
            throw new ApiBizException(ErrorCode.E00000001.CODE, "余额不足", inputParameter);
        }
        boolean deductBalence=deductBalence(inputParameter, reflectTheAmountOf, shop);
        if(deductBalence==false){
            throw new ApiBizException(ErrorCode.E00000001.CODE, "余额不足", inputParameter);
        }
    }

    /**
     * 扣减余额增加流水
     * @param inputParameter
     * @param reflectTheAmountOf
     * @param shop
     * @throws ApiBizException
     */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    private boolean  deductBalence(InputParameter inputParameter, int reflectTheAmountOf, Shop shop) throws ApiBizException {
        boolean notSufficientFunds=false;//余额是否充足
        int flag=shopMapperExtend.deductTheBalanceOfTheBranch(shop.getId(),Integer.parseInt(reflectTheAmountOf+""));
        log.info("扣减余额结果{}", flag);
        if(flag>=1){
            //3添加流水
            ShopExpressive shopExpressive = new ShopExpressive();
            shopExpressive.setMoney(reflectTheAmountOf);
            shopExpressive.setType(CodeConstants.WithdrawCurrentType.WITHDRAWDEPOSIT);
            shopExpressive.setUserId(shop.getUserId());
            shopExpressive.setApplyTime(new Date());
            shopExpressive.setShopId(shop.getId());
            shopExpressive.setExpressiveNo(System.currentTimeMillis() + "");
            shopExpressive.setStatus(CodeConstants.WithdrawalState.FORWITHDRAWAL);
            shopExpressive.setAccountBank(shop.getAccountBank());
            shopExpressive.setAccountNo(shop.getAccountNo());
            int flag1=shopExpressiveMapperExtend.insertSelective(shopExpressive);
            log.info("生成流水结果{}", flag1);
            notSufficientFunds=true;
        }

        return notSufficientFunds;
    }

    private Shop getShop() throws Exception {
        verifyPermissions();//验证是否有权限
        ShopWechat shopWechat = userUtil.userInfo();
        ShopExample shopExample = new ShopExample();
        ShopExample.Criteria criteria = shopExample.createCriteria();
        criteria.andUserIdEqualTo(shopWechat.getUserId());
        shopExample.setOrderByClause(" id asc");
        List<Shop> shopList = shopMapper.selectByExample(shopExample);
        Shop shop=shopList.get(0);
        log.info("当前用户openid{},当前用户userid{},当前用户所在网点信息{}", shopWechat.getOpenid(),shopWechat.getUserId(),shop);
        return shop;
    }

    @Override
    public String toString() {
        return super.toString();
    }


}
