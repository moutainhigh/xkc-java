package com.tahoecn.xkc.service.report.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.channel.BChannelorgMapper;
import com.tahoecn.xkc.mapper.report.ReportMapper;
import com.tahoecn.xkc.model.channel.BChannelorg;
import com.tahoecn.xkc.service.report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-06-25
 */
@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper, BChannelorg> implements ReportService {

    @Override
    public List<Map<String, Object>> CustomerRankNew_Select(String orglevel, String accountID, String startDate, String endDate, String s) {
        return baseMapper.CustomerRankNew_Select(orglevel, accountID, startDate, endDate, s);
    }

    @Override
    public List<Map<String, Object>> CustomerRankSalesNew_Select(String orgID, String startDate, String endDate, String s) {
        return baseMapper.CustomerRankSalesNew_Select(orgID, startDate, endDate, s);
    }

    @Override
    public List<Map<String, Object>> CustomerRankDetail_Select(String orgID, String startDate, String endDate) {
        return baseMapper.CustomerRankDetail_Select(orgID, startDate, endDate);
    }

    @Override
    public List<Map<String, Object>> ChannelCustomerReport_Select(String orglevel, String accountID, String startDate, String endDate, String s) {
        return baseMapper.ChannelCustomerReport_Select(orglevel, accountID, startDate, endDate, s);
    }
}
