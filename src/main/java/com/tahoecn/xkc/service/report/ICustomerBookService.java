package com.tahoecn.xkc.service.report;


import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.customer.CustomerBook;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-08-28
 */
public interface ICustomerBookService extends IService<CustomerBook> {

    List<Map<String,Object>> listOpp(String id);

    List<Map<String,Object>> listClue(String id);

    List<Map<String,Object>> customerPayInfo(String id);

    List<Map<String,Object>> CustomerNEWSignInfo_Select(String customerID);
}
