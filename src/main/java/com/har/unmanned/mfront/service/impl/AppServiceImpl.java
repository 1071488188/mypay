package com.har.unmanned.mfront.service.impl;

import com.har.unmanned.mfront.config.CodeConstants;
import com.har.unmanned.mfront.dao.AppMapper;
import com.har.unmanned.mfront.model.App;
import com.har.unmanned.mfront.model.AppExample;
import com.har.unmanned.mfront.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiang on 2017/10/18.
 */
@Service
public class AppServiceImpl  implements AppService {
    @Autowired
    private AppMapper appMapper;
    @Override
    public App getAppById(String appId) {

        AppExample example = new AppExample();
        example.createCriteria().andAppIdEqualTo(appId).andStatusEqualTo(CodeConstants.DataStatus.ENABLED);

        List<App> appList = this.appMapper.selectByExample(example);
        if(appList != null && appList.size() == 1){
            return appList.get(0);
        }

        return null;
    }
}
