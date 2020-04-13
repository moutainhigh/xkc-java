package com.tahoecn.xkc.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.tahoecn.xkc.mapper.sys.SyncCustomerMapper;
import com.tahoecn.xkc.model.customer.BCustomerWhiteList;
import com.tahoecn.xkc.service.customer.IBCustomerWhiteListService;
import com.tahoecn.xkc.service.sys.ISyncCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author mystic
 */
@Service
public class SyncCustomerServiceImpl implements ISyncCustomerService {

    @Autowired
    private IBCustomerWhiteListService customerWhiteListService;

    @Autowired
    private SyncCustomerMapper syncCustomerMapper;

    @Override
    public void updateByCustomerID(String customerID, String customerMobile, String editor) {
        BCustomerWhiteList bCustomerWhiteList = new BCustomerWhiteList();
        UpdateWrapper<BCustomerWhiteList> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("IsDel", 0)
                .set("CustomerMobile", customerMobile)
                .set("EditeTime", new Date())
                .set("Editor", editor)
                .eq("CustomerID", customerID);
        this.customerWhiteListService.update(bCustomerWhiteList, updateWrapper);

        // 同步更新明源系统
        Map<String, Object> map = new HashMap<>();
        map.put("customerMobile", customerMobile);
        map.put("editor", editor);
        map.put("customerID", customerID);
        this.syncCustomerMapper.updateByCustomerID(map);
    }

    @Override
    public void save(String customerID, String customerMobile, String editor) {
        BCustomerWhiteList bCustomerWhiteList = new BCustomerWhiteList();
        String id = UUID.randomUUID().toString().toUpperCase();
        bCustomerWhiteList.setId(id);
        bCustomerWhiteList.setCustomerID(customerID);
        bCustomerWhiteList.setCustomerMobile(customerMobile);
        bCustomerWhiteList.setCreateTime(new Date());
        bCustomerWhiteList.setCreator(editor);
        bCustomerWhiteList.setIsDel(0);
        this.customerWhiteListService.save(bCustomerWhiteList);

        // 同步明源系统
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("customerID", customerID);
        map.put("customerMobile", customerMobile);
        map.put("creator", editor);
        this.syncCustomerMapper.save(map);
    }

    @Override
    public void deleteByCustomerID(String customerID, String editor) {
        BCustomerWhiteList bCustomerWhiteList = new BCustomerWhiteList();
        UpdateWrapper<BCustomerWhiteList> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("IsDel", 1)
                .set("EditeTime", new Date())
                .set("Editor", editor)
                .eq("CustomerID", customerID);
        this.customerWhiteListService.update(bCustomerWhiteList, updateWrapper);

        Map<String, Object> map = new HashMap<>();
        map.put("editor", editor);
        map.put("customerID", customerID);
        this.syncCustomerMapper.deleteByCustomerID(map);
    }
}
