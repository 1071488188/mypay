package com.har.unmanned.mfront.api.administrator;

import com.alibaba.fastjson.JSONObject;
import com.har.unmanned.mfront.api.administrator.ValidGroup.BindManagerGroup;
import com.har.unmanned.mfront.api.administrator.ValidGroup.CloseAnAccountGroup;
import com.har.unmanned.mfront.api.administrator.ValidGroup.PageGroup;
import com.har.unmanned.mfront.api.administrator.ValidGroup.WithdrawDepositGroup;
import com.har.unmanned.mfront.config.ErrorCode;
import com.har.unmanned.mfront.exception.ApiBizException;
import com.har.unmanned.mfront.service.AdministratorService;
import com.har.unmanned.mfront.utils.RespMessage;
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
@RequestMapping(value = "/admin", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class AdministratorResourceImpl implements AdministratorResource {
    @Autowired
    AdministratorService administratorService;
    @Override
    public String adminInit() throws Exception {
        JSONObject jsonObject=new JSONObject();
        RespMessage respMessage=new RespMessage();
        int flag=administratorService.adminInit();
        jsonObject.put("whetherNetwork",flag);
        log.info("管理员初始化接口返回参数{}",jsonObject);
        return new RespMessage(ErrorCode.E00000000.CODE,ErrorCode.E00000000.MSG,jsonObject).toString();
    }

    /**
     * 绑定手机号
     *
     * @param inputParameter
     * @return
     */
    @Override
    @PostMapping("/bindManager")
    public String bindManager(@Validated({BindManagerGroup.class}) @RequestBody InputParameter inputParameter)throws Exception {
        return null;
    }

    /**
     * 提现
     *
     * @param inputParameter
     * @return
     */
    @Override
    @PostMapping("/withdrawDeposit")
    public String withdrawDeposit(@Validated({WithdrawDepositGroup.class}) @RequestBody InputParameter inputParameter)throws Exception {
        return null;
    }

    /**
     * 消费记录
     *
     * @param inputParameter
     * @return
     */
    @Override
    @GetMapping("/expenseCalendar")
    public String expenseCalendar(@Validated({PageGroup.class}) InputParameter inputParameter)throws Exception {
        return null;
    }

    /**
     * 结算记录
     *
     * @param inputParameter
     * @return
     */
    @Override
    @GetMapping("/settlementRecords")
    public String settlementRecords(@Validated({PageGroup.class}) InputParameter inputParameter)throws Exception {
        return null;
    }

    /**
     * 结算
     *
     * @param inputParameter
     * @return
     */
    @Override
    @PostMapping("/closeAnAccount")
    public String closeAnAccount(@Validated({CloseAnAccountGroup.class}) @RequestBody InputParameter inputParameter) throws Exception{
        return null;
    }

    /**
     * 余额明细
     *
     * @param inputParameter
     * @return
     */
    @Override
    public String balanceDetails(@Validated({PageGroup.class}) @RequestBody InputParameter inputParameter) throws Exception{
        return null;
    }
}
