package com.tahoecn.xkc.mapper.report;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tahoecn.xkc.model.channel.BChannelorg;
import com.tahoecn.xkc.model.dto.ChannelDto;
import com.tahoecn.xkc.model.dto.ChannelInsertDto;
import org.apache.ibatis.annotations.Param;

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

    List<Map<String,Object>> CustomerRankNew_Select(@Param("orglevel")String orglevel, @Param("accountID")String accountID,
                                                    @Param("startDate")String startDate, @Param("endDate")String endDate, @Param("sql")String sql);

    List<Map<String,Object>> CustomerRankSalesNew_Select(@Param("orgID")String orgID, @Param("startDate")String startDate,
                                                         @Param("endDate")String endDate, @Param("sql")String sql);

    List<Map<String,Object>> CustomerRankDetail_Select(@Param("orgID")String orgID, @Param("startDate")String startDate, @Param("endDate")String endDate);

    List<Map<String,Object>> ChannelCustomerReport_Select(@Param("orglevel")String orglevel, @Param("accountID")String accountID,
                                                          @Param("startDate")String startDate, @Param("endDate")String endDate, @Param("sql")String sql);

    List<Map<String,Object>> ChannelCustomerReportDL_Select(@Param("orglevel")String orglevel, @Param("accountID")String accountID,
                                                            @Param("startDate")String startDate, @Param("endDate")String endDate);
}
