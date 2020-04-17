package com.tahoecn.xkc.service.customer;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.customer.BCustomerWhiteList;

/**
 * @author mystic
 */
public interface IBCustomerWhiteListService extends IService<BCustomerWhiteList> {

    /**
     * 根据顾客ID 判断是否是白名单用户
     * @param customerID 顾客ID
     * @return
     */
    boolean judgeIsWhiteCustomer(String customerID);
}
