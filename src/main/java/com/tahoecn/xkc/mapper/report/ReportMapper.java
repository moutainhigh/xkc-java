package com.tahoecn.xkc.mapper.report;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tahoecn.xkc.common.annotation.DataSource;
import com.tahoecn.xkc.common.enums.DataSourceEnum;
import com.tahoecn.xkc.model.channel.BChannelorg;
import com.tahoecn.xkc.model.dto.ChannelDto;
import com.tahoecn.xkc.model.dto.ChannelInsertDto;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-06-25
 */
public interface ReportMapper extends BaseMapper<BChannelorg> {

    @DataSource(DataSourceEnum.DB3)
    List<Map<String,Object>> CustomerRankNew_Select(@Param("orglevel")String orglevel, @Param("accountID")String accountID,
                                                    @Param("startDate")String startDate, @Param("endDate")String endDate, @Param("sql")String sql);

    @DataSource(DataSourceEnum.DB3)
    List<Map<String,Object>> CustomerRankSalesNew_Select(@Param("orgID")String orgID, @Param("startDate")String startDate,
                                                         @Param("endDate")String endDate, @Param("sql")String sql);

    @DataSource(DataSourceEnum.DB3)
    List<Map<String,Object>> CustomerRankDetail_Select(@Param("orgID")String orgID, @Param("startDate")String startDate, @Param("endDate")String endDate);

    @DataSource(DataSourceEnum.DB3)
    List<Map<String,Object>> ChannelCustomerReport_Select(@Param("orglevel")String orglevel, @Param("accountID")String accountID,
                                                          @Param("startDate")String startDate, @Param("endDate")String endDate, @Param("sql")String sql);
    
    @DataSource(DataSourceEnum.DB3)
    List<Map<String,Object>> KfChannelCustomerReport_Select(@Param("orglevel")String orglevel, @Param("accountID")String accountID,
    		@Param("startDate")String startDate, @Param("endDate")String endDate, @Param("sql")String sql);

    @DataSource(DataSourceEnum.DB3)
    List<Map<String,Object>> ChannelCustomerReportDL_Select(@Param("orglevel")String orglevel, @Param("accountID")String accountID,
                                                            @Param("startDate")String startDate, @Param("endDate")String endDate);

    @DataSource(DataSourceEnum.DB3)
    IPage<Map<String, Object>> mChannelCheckReportList_Select(IPage page, @Param("StartTime")Date startTime, @Param("EndTime")Date endTime, 
    		@Param("ProjectID")String projectID, @Param("CheckDate")String checkDate, @Param("Name")String name, @Param("Mobile")String mobile,
    		 @Param("TaskName")String taskName, @Param("ReportName")String reportName);

    @DataSource(DataSourceEnum.DB3)
    List<Map<String, Object>> mChannelCheckReportList_Export(@Param("StartTime")Date startTime, @Param("EndTime")Date endTime,
    		@Param("ProjectID")String projectID, @Param("CheckDate")String checkDate, @Param("Name")String name, @Param("Mobile")String mobile,
   		 @Param("TaskName")String taskName, @Param("ReportName")String reportName);
}
