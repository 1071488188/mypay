package com.har.unmanned.mfront.task.token;

import com.har.unmanned.mfront.wxapi.fixed.WxTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by jiang on 2017/9/26.
 */
@Component
public class WxToken {
    @Autowired
    WxTokenService wxTokenService;
    /**
     * @throws
     * @Title: pushMsg
     * @Description: TODO(每隔1小时获取微信token)
     * @author jiangjj
     */
    @Scheduled(cron = "0 0 0/1 * * ? ")
    public void getToken() {
        wxTokenService.postToken();
    }
}
