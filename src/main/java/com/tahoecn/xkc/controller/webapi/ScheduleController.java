package com.tahoecn.xkc.controller.webapi;


import com.tahoecn.xkc.schedule.CustomerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 *  调用定时任务
 * </p>
 * @since 2020-06-12
 */
@RestController
@RequestMapping("/webapi/wangxiaobao")
public class ScheduleController {
    @Autowired
    private CustomerTask customerTask;

    @RequestMapping(value = "/pushCustomerToWxb", method = {RequestMethod.GET})
    public void pushCustomerToWxb() throws Exception {
        customerTask.pushCustomerToWxb();
    }
}
