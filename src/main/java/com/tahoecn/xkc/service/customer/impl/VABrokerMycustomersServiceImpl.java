package com.tahoecn.xkc.service.customer.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.customer.VABrokerMycustomersMapper;
import com.tahoecn.xkc.model.customer.VABrokerMycustomers;
import com.tahoecn.xkc.service.customer.IVABrokerMycustomersService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 1 服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-02
 */
@Service
public class VABrokerMycustomersServiceImpl extends ServiceImpl<VABrokerMycustomersMapper, VABrokerMycustomers> implements IVABrokerMycustomersService {

    @Autowired
    private BCustomerWhiteListServiceImpl customerWhiteListService;

    @Override
    public IPage<Map<String, Object>> mGetMyCustomers_Select(IPage page, int sort, String filter, String customerInfo, String brokerID) {
        String order;
        String where;
        String search="";
        if (StringUtils.isNotBlank(customerInfo)) {
            search = "and (CustomerName like '%" + customerInfo + "%' or Mobile like '%" + customerInfo + "%' )";
        }
        if (sort == 1) {
            order = " order by ReportTime desc ";
        } else {
            order = " order by ReportTime asc ";
        }
        if (StringUtils.equals(filter, "全部")) {
            where = " where ReportUserID='" + brokerID + "' ";
        } else {
            where = " where ReportUserID='" + brokerID + "' and StatusText='" + filter + "'";
        }
        IPage<Map<String, Object>> mapIPage = baseMapper.mGetMyCustomers_Select(page, where, order, search);
        for (Map<String, Object> record : mapIPage.getRecords()) {
            if (StringUtils.equals((String)record.get("StatusText"),"来访")) {
                record.put("StatusText","到访");
            }
        }

        // 客户vip白名单过滤
        // chenghong  白名单start
       /* Iterator<Map<String, Object>> iterator = mapIPage.getRecords().iterator();
        while (iterator.hasNext()) {
            Map<String, Object> map = iterator.next();
            String customerID = (String)map.get("CustomerID");
            String customerPotentialID = (String)map.get("CustomerPotentialID");
            if ((customerID != null && customerWhiteListService.judgeIsWhiteCustomer(customerID))
                    || (customerPotentialID != null && customerWhiteListService.judgeIsWhiteCustomer(customerPotentialID))) {
                String customerName = (String) map.get("CustomerName");
                if (customerName != null) {
                    map.put("CustomerName", StringShieldUtil.getFilterStrHasFirstChar(customerName));
                }

                String mobile = (String) map.get("Mobile");
                if (mobile != null) {
                    map.put("Mobile", StringShieldUtil.getAllStarStr(mobile));
                }
            }
        }*/
        // chenghong  白名单end

        return mapIPage;
    }

    @Override
    public int getWuXiao(String brokerID) {
        return baseMapper.getWuXiao(brokerID);
    }
}
