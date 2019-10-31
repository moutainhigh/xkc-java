package com.tahoecn.xkc.service.report.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.common.annotation.DataSource;
import com.tahoecn.xkc.common.enums.DataSourceEnum;
import com.tahoecn.xkc.mapper.channel.BChannelorgMapper;
import com.tahoecn.xkc.mapper.report.ReportMapper;
import com.tahoecn.xkc.model.channel.BChannelorg;
import com.tahoecn.xkc.service.report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @Override
    public List<Map<String, Object>> ChannelCustomerReportDL_Select(String orglevel, String accountID, String startDate, String endDate) {
        return baseMapper.ChannelCustomerReportDL_Select(orglevel, accountID, startDate, endDate);
    }

    @Override
    @DataSource(DataSourceEnum.DB3)
    public IPage<Map<String, Object>> mChannelCheckReportList_Select(IPage page, Date startTime, Date endTime, String projectID, String checkDate, String name, String mobile, String taskName, String reportName) {
        endTime = new Date(endTime.getTime() + 60 * 60 * 24 * 1000);
        IPage<Map<String, Object>> list=baseMapper.mChannelCheckReportList_Select(page, startTime, endTime, projectID, checkDate, name, mobile, taskName, reportName);
        List<Map<String, Object>> result=new ArrayList<>();
        for (Map<String, Object> map : list.getRecords()) {
            Date checkInTime = (Date) map.get("CheckInTime");
            Date CheckOutTime = (Date) map.get("CheckOutTime");
            if (checkInTime!=null&&CheckOutTime!=null){
            Long l = DateUtil.betweenMs(checkInTime, CheckOutTime);
            String gapTime = getGapTime(l);
                String[] split = gapTime.split(":");
                StringBuilder sb=new StringBuilder();
                sb.append(split[0]).append("小时").append(split[1]).append("分钟");
                map.put("WorkTime",sb.toString());
            }
            result.add(map);
        }
        return list.setRecords(result);
    }

    @Override
    public List<Map<String, Object>> mChannelCheckReportList_Export(Date startTime, Date endTime, String projectID, String checkDate, String name, String mobile, String taskName, String reportName) {
        List<Map<String, Object>> list=baseMapper.mChannelCheckReportList_Export(startTime, endTime, projectID, checkDate, name, mobile, taskName, reportName);
        List<Map<String, Object>> result=new ArrayList<>();
        for (Map<String, Object> map : list) {
            Date checkInTime = (Date) map.get("CheckInTime");
            Date CheckOutTime = (Date) map.get("CheckOutTime");
            if (checkInTime!=null&&CheckOutTime!=null){
                Long l = DateUtil.betweenMs(checkInTime, CheckOutTime);
                String gapTime = getGapTime(l);
                String[] split = gapTime.split(":");
                StringBuilder sb=new StringBuilder();
                sb.append(split[0]).append("小时").append(split[1]).append("分钟");
                map.put("WorkTime",sb.toString());
            }
            result.add(map);
        }
        return result;
    }

    private String getGapTime(long time) {
        long hours = time / (1000 * 60 * 60);
        long minutes = (time - hours * (1000 * 60 * 60)) / (1000 * 60);
        String diffTime = "";
        if (minutes < 10) {
            diffTime = hours + ":0" + minutes;
        } else {
            diffTime = hours + ":" + minutes;
        }
        return diffTime;
    }

}
