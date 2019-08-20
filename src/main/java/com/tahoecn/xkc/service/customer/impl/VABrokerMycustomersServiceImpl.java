package com.tahoecn.xkc.service.customer.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.customer.VABrokerMycustomersMapper;
import com.tahoecn.xkc.model.customer.VABrokerMycustomers;
import com.tahoecn.xkc.service.customer.IVABrokerMycustomersService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
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
        return baseMapper.mGetMyCustomers_Select(page, where, order, search);
    }

    @Override
    public int getWuXiao(String brokerID) {
        return baseMapper.getWuXiao(brokerID);
    }
}
