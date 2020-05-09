package com.tahoecn.xkc.async;

import com.tahoecn.xkc.model.miniprogram.BOpportunityOther;
import com.tahoecn.xkc.service.miniprogram.IBOpportunityOtherService;

/**
 * @description: 写BOpportunityOther表信息, 实现业务为上海无法查看集团报备人员
 * @author: 张晓东
 * @time: 2020/4/28 17:32
 */
public class BOpportunityOtherRunnable extends BaseRunnable {

    private IBOpportunityOtherService service;
    private BOpportunityOther other;

    public BOpportunityOtherRunnable(IBOpportunityOtherService service, BOpportunityOther other) {
        this.service = service;
        this.other = other;
    }

    @Override
    public void invoke()  throws Exception {
        this.service.insert(this.other);
    }
}
