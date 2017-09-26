package com.har.unmanned.mfront.api.administrator;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.har.unmanned.mfront.api.administrator.ValidGroup.BindManagerGroup;
import com.har.unmanned.mfront.api.administrator.ValidGroup.CloseAnAccountGroup;
import com.har.unmanned.mfront.api.administrator.ValidGroup.PageGroup;
import com.har.unmanned.mfront.api.administrator.ValidGroup.WithdrawDepositGroup;
import com.har.unmanned.mfront.config.ErrorCode;
import com.har.unmanned.mfront.service.AdministratorService;
import com.har.unmanned.mfront.utils.RespMessage;
import com.har.unmanned.mfront.utils.aop.ControlLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jiang on 2017/9/19.
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/v1/admin", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class AdministratorResourceImpl implements AdministratorResource {
    @Autowired
    AdministratorService administratorService;
    /**
     * 提现
     *
     * @param inputParameter
     * @return
     */
    @Override
    @PostMapping("/withdrawDeposit")
    @ControlLog(description="提现")
    public String withdrawDeposit(@Validated({WithdrawDepositGroup.class}) @RequestBody InputParameter inputParameter)throws Exception {
        administratorService.withdrawDeposit(inputParameter);
        return new RespMessage(ErrorCode.E00000000.CODE,ErrorCode.E00000000.MSG,null).getRespMessage().toString();
    }

    /**
     * 消费记录
     *
     * @param inputParameter
     * @return
     */
    @Override
    @GetMapping("/expenseCalendar")
    @ControlLog(description="消费记录")
    public String expenseCalendar(@Validated({PageGroup.class}) InputParameter inputParameter)throws Exception {
       JSONObject jsonObject= administratorService.expenseCalendar(inputParameter);
        return new RespMessage(ErrorCode.E00000000.CODE,ErrorCode.E00000000.MSG,jsonObject).getRespMessage().toString();
    }

    /**
     * 结算记录
     *
     * @param inputParameter
     * @return
     */
    @Override
    @GetMapping("/settlementRecords")
    @ControlLog(description="结算记录")
    public String settlementRecords(@Validated({PageGroup.class}) InputParameter inputParameter)throws Exception {
        JSONObject jsonObject=administratorService.settlementRecords(inputParameter);
        return new RespMessage(ErrorCode.E00000000.CODE,ErrorCode.E00000000.MSG,jsonObject).getRespMessage().toString();
    }

    /**
     * 结算
     *
     * @param inputParameter
     * @return
     */
    @Override
    @PostMapping("/closeAnAccount")
    @ControlLog(description="结算")
    public String closeAnAccount(@Validated({CloseAnAccountGroup.class}) @RequestBody InputParameter inputParameter) throws Exception{
        administratorService.closeAnAccount(inputParameter);
        return new RespMessage(ErrorCode.E00000000.CODE,ErrorCode.E00000000.MSG,null).getRespMessage().toString();
    }

    /**
     * 余额明细
     *
     * @param inputParameter
     * @return
     */
    @Override
    @GetMapping("/balanceDetails")
    @ControlLog(description="余额明细")
    public String balanceDetails(@Validated({PageGroup.class}) InputParameter inputParameter) throws Exception{
        JSONObject jsonObject=administratorService.balanceDetails(inputParameter);
        return  new RespMessage(ErrorCode.E00000000.CODE,ErrorCode.E00000000.MSG,jsonObject).getRespMessage().toString();
    }
}
