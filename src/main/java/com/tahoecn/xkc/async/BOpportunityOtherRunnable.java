package com.tahoecn.xkc.async;

import com.tahoecn.xkc.model.miniprogram.BOpportunityOther;
import com.tahoecn.xkc.service.miniprogram.IBOpportunityOtherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description:
 * @author: 张晓东
 * @time: 2020/4/28 17:32
 */
public class BOpportunityOtherRunnable implements Runnable {

    private final Logger log = LoggerFactory.getLogger(BOpportunityOtherRunnable.class);

    private IBOpportunityOtherService service;
    private BOpportunityOther other;

    public BOpportunityOtherRunnable(IBOpportunityOtherService service, BOpportunityOther other) {
        this.service = service;
        this.other = other;
    }

    @Override
    public void run() {
        try {
            this.service.insert(this.other);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("async insert error -> className : {} , args : {} , e : {}", service.getClass().getName(), other, e);
        }
    }

}
