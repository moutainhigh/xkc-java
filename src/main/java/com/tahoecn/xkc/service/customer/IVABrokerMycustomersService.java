package com.tahoecn.xkc.service.customer;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.customer.VABrokerMycustomers;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 1 服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-02
 */
public interface IVABrokerMycustomersService extends IService<VABrokerMycustomers> {

    List<Map<String, Object>> mGetMyCustomers_Select(IPage page, int sort, String filter, String customerInfo, String brokerID);
}
