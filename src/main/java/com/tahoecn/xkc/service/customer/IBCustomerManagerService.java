package com.tahoecn.xkc.service.customer;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.customer.BCustomer;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-05
 */
public interface IBCustomerManagerService extends IService<BCustomer> {
    IPage<Map<String, Object>> CustomerManagePageList_Select(IPage page, Map<String, Object> map);
}
