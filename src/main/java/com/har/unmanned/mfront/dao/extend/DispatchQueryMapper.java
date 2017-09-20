package com.har.unmanned.mfront.dao.extend;

import com.har.unmanned.mfront.model.extend.DispatchDomain;

import java.util.List;

public interface DispatchQueryMapper {
    /**
     * 配送列表总数
     * @param dispatchDomain
     * @return
     */
    int dispatchCount(DispatchDomain dispatchDomain);

    /**
     * 配送列表
     * @param dispatchDomain
     * @return
     */
    List<DispatchDomain> dispatchList(DispatchDomain dispatchDomain);
}
