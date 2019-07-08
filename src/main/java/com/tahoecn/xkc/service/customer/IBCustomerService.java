package com.tahoecn.xkc.service.customer;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.customer.BCustomer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-05
 */
public interface IBCustomerService extends IService<BCustomer> {

    IPage<Map<String,Object>> customerChangePageList_Select(IPage page, String projectID, String sqlWhere);

    List<Map<String,Object>> setExcelToCustomerChangeList(String projectID, String sqlWhere);
}
