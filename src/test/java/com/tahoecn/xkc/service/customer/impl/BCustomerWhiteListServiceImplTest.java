package com.tahoecn.xkc.service.customer.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.tahoecn.xkc.XkcApplication;
import com.tahoecn.xkc.model.customer.BCustomerWhiteList;
import com.tahoecn.xkc.service.customer.IBCustomerWhiteListService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * @author mystic
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = XkcApplication.class)
public class BCustomerWhiteListServiceImplTest {

    @Autowired
    private IBCustomerWhiteListService ibCustomerWhiteListService;

    @Test
    public void save(){
        String customerID = "134532324242342";
        String customerMobile = "18650085656";

        QueryWrapper<BCustomerWhiteList> wrapper = new QueryWrapper<>();

        wrapper.eq("CustomerID", customerID);

        BCustomerWhiteList serviceOne = this.ibCustomerWhiteListService.getOne(wrapper);

        if (serviceOne == null) {
            BCustomerWhiteList bCustomerWhiteList = new BCustomerWhiteList();
            bCustomerWhiteList.setId(UUID.randomUUID().toString().toUpperCase());
            bCustomerWhiteList.setCustomerID(customerID);
            bCustomerWhiteList.setCustomerMobile(customerMobile);
            bCustomerWhiteList.setCreator("999");
            bCustomerWhiteList.setCreateTime(new Date());
            bCustomerWhiteList.setEditor("999");
            bCustomerWhiteList.setEditeTime(new Date());
            bCustomerWhiteList.setIsDel(0);

            this.ibCustomerWhiteListService.save(bCustomerWhiteList);
        }
    }

    @Test
    public void delete() {
        BCustomerWhiteList bCustomerWhiteList = new BCustomerWhiteList();
        UpdateWrapper<BCustomerWhiteList> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("IsDel", 1).eq("CustomerID", "134532324242342");

        this.ibCustomerWhiteListService.update(bCustomerWhiteList, updateWrapper);

        // this.ibCustomerWhiteListService.updateById();



        updateWrapper.set("EditeTime", new Date());

        this.ibCustomerWhiteListService.update(bCustomerWhiteList, updateWrapper);
    }

}