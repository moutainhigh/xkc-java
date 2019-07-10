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

    List<Map<String,Object>> GetDistributionList_Select(String project);

    IPage<Map<String,Object>> CustomerChange_BakPageList_Select(IPage page, String projectID, String sqlWhere);

    List<Map<String,Object>> SetExcelToCustomerChange_BakList(String projectID, String sqlWhere);

    Map<String,Object> CustomerChangeDetailAll_Select(String projectId, String customerID, String clueID);
    /**
     * 验证是否是本项目老业主
     */
    List<Map<String,Object>> IsProjectOwner_Select(String projectId, String phone);
}
