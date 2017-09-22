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
    @GetMapping("/adminInit")
    @ResponseBody
    public String adminInit() throws Exception {
        log.info("------------------管理员初始化开始--------------------------------");
        JSONObject jsonObject=new JSONObject();
        int flag=administratorService.adminInit();
        jsonObject.put("whetherNetwork",flag);
        log.info("管理员初始化接口返回参数{}",jsonObject);
        log.info("------------------管理员初始化结束--------------------------------");
        return new RespMessage(ErrorCode.E00000000.CODE,ErrorCode.E00000000.MSG,jsonObject).getRespMessage().toString();
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
        log.info("------------------绑定手机号开始-------------------------------");
        administratorService.bindManager(inputParameter);
        log.info("------------------绑定手机号结束-------------------------------");
        return new RespMessage(ErrorCode.E00000000.CODE,ErrorCode.E00000000.MSG,null).getRespMessage().toString();
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
        log.info("------------------提现开始-------------------------------");
        administratorService.withdrawDeposit(inputParameter);
        log.info("------------------提现结束-------------------------------");
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
    public String expenseCalendar(@Validated({PageGroup.class}) InputParameter inputParameter)throws Exception {
        log.info("------------------消费记录开始-------------------------------");
       JSONObject jsonObject= administratorService.expenseCalendar(inputParameter);
        log.info("------------------消费记录开始-------------------------------");
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
    public String settlementRecords(@Validated({PageGroup.class}) InputParameter inputParameter)throws Exception {
        log.info("------------------结算记录开始-------------------------------");
        JSONArray jsonArray=administratorService.settlementRecords(inputParameter);
        log.info("------------------结算记录结束-------------------------------");
        return new RespMessage(ErrorCode.E00000000.CODE,ErrorCode.E00000000.MSG,jsonArray).getRespMessage().toString();
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
        log.info("------------------结算开始-------------------------------");
        administratorService.closeAnAccount(inputParameter);
        log.info("------------------结算结束-------------------------------");
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
    public String balanceDetails(@Validated({PageGroup.class}) InputParameter inputParameter) throws Exception{
        log.info("------------------余额明细开始-------------------------------");
        JSONArray jsonArray=administratorService.balanceDetails(inputParameter);
        log.info("------------------余额明细结束-------------------------------");
        return  new RespMessage(ErrorCode.E00000000.CODE,ErrorCode.E00000000.MSG,jsonArray).getRespMessage().toString();
    }
}
