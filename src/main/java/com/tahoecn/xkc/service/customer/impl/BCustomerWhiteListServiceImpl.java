package com.tahoecn.xkc.service.customer.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.customer.BCustomerWhiteListMapper;
import com.tahoecn.xkc.model.customer.BCustomerWhiteList;
import com.tahoecn.xkc.service.customer.IBCustomerWhiteListService;
import org.springframework.stereotype.Service;

/**
 * @author mystic
 */
@Service
public class BCustomerWhiteListServiceImpl extends ServiceImpl<BCustomerWhiteListMapper, BCustomerWhiteList> implements IBCustomerWhiteListService {
    @Override
    public boolean judgeIsWhiteCustomer(String customerID) {
        QueryWrapper<BCustomerWhiteList> wrapper = new QueryWrapper<>();
        wrapper.eq("CustomerID", customerID).eq("IsDel", 0);
        int count = count(wrapper);

        if (count > 0) {
            return true;
        }

        return false;
    }
}
