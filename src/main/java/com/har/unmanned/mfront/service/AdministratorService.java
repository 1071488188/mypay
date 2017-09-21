package com.har.unmanned.mfront.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.har.unmanned.mfront.api.administrator.InputParameter;

/**
 * 管理员接口service
 * Created by jiang on 2017/9/20.
 */
public interface AdministratorService {
    /**
     * 验证是否有权限
     */
    public void verifyPermissions() throws Exception;

    /**
     * 初始化是否为网点管理员
     * @return
     * @throws Exception
     */
    public  int adminInit()throws Exception;

    /**
     * 绑定手机号
     * @param inputParameter
     * @throws Exception
     */
    public void bindManager(InputParameter inputParameter) throws Exception;

    /**
     * 消费记录
     * @param inputParameter
     * @return
     * @throws Exception
     */
    public JSONObject expenseCalendar(InputParameter inputParameter)throws Exception;

    /**
     * 结算记录
     * @param inputParameter
     * @return
     * @throws Exception
     */
    public JSONArray settlementRecords(InputParameter inputParameter)throws Exception;

    /**
     * 结算
     * @param inputParameter
     * @throws Exception
     */
    public void closeAnAccount(InputParameter inputParameter)throws Exception;

    /**
     * 余额明细
     * @param inputParameter
     * @return
     * @throws Exception
     */
    JSONArray balanceDetails(InputParameter inputParameter)throws Exception;
}
