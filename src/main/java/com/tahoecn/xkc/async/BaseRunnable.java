package com.tahoecn.xkc.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description: 抽象线程
 * @author: 张晓东
 * @time: 2020/4/29 14:17
 */
public abstract class BaseRunnable implements Runnable {

    protected final Logger log = LoggerFactory.getLogger(BOpportunityOtherRunnable.class);

    /**
     * @description: 实现抽象类, 实现自己的执行逻辑
     * @return:
     * @author: 张晓东
     * @time: 2020/4/29 14:25
     */
    abstract void invoke() throws Exception;

    @Override
    public void run() {
        try {
            invoke();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("async insert error -> className : {} , e : {}", this.getClass().getName(), e);
        }
    }
}
