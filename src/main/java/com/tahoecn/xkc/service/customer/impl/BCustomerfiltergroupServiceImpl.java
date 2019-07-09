package com.tahoecn.xkc.service.customer.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.customer.BCustomerfiltergroupMapper;
import com.tahoecn.xkc.model.customer.BCustomerfiltergroup;
import com.tahoecn.xkc.service.customer.IBCustomerfiltergroupService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-08
 */
@Service
public class BCustomerfiltergroupServiceImpl extends ServiceImpl<BCustomerfiltergroupMapper, BCustomerfiltergroup> implements IBCustomerfiltergroupService {

    @Override
    public List<Map<String, Object>> groupList(String jobCode, String projectID, String userID) {
        return baseMapper.groupList(jobCode,projectID,userID);
    }
}
