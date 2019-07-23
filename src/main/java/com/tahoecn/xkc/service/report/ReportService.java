package com.tahoecn.xkc.service.report;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.channel.BChannelorg;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author YYY
 * @since 2019-06-25
 */
public interface ReportService extends IService<BChannelorg> {

    List<Map<String,Object>> CustomerRankNew_Select(String orglevel, String accountID, String startDate, String endDate, String s);

    List<Map<String,Object>> CustomerRankSalesNew_Select(String orgID, String startDate, String endDate, String s);

    List<Map<String,Object>> CustomerRankDetail_Select(String orgID, String startDate, String endDate);

    List<Map<String,Object>> ChannelCustomerReport_Select(String orglevel, String accountID, String startDate, String endDate, String s);

    List<Map<String, Object>> mChannelCheckReportList_Select(IPage page, Date startTime, Date endTime, String projectID);

    List<Map<String, Object>> mChannelCheckReportList_Export(Date startTime, Date endTime, String projectID);
}
