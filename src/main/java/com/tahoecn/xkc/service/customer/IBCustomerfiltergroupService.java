package com.tahoecn.xkc.service.customer;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.customer.BCustomerfiltergroup;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-08
 */
public interface IBCustomerfiltergroupService extends IService<BCustomerfiltergroup> {

    List<Map<String, Object>> groupList(String jobCode, String projectID, String userID);
}
