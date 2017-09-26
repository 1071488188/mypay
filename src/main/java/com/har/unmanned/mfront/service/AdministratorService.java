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
    public JSONObject settlementRecords(InputParameter inputParameter)throws Exception;

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
    JSONObject balanceDetails(InputParameter inputParameter)throws Exception;

    /**
     * 提现
     * @param inputParameter
     * @throws Exception
     */
    void withdrawDeposit(InputParameter inputParameter)throws Exception;
}
