package com.tahoecn.xkc.mapper.report;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tahoecn.xkc.model.customer.CostomerReport;
import com.tahoecn.xkc.model.customer.CostomerReportVO;
import com.tahoecn.xkc.model.reprot.KfCostomerReportDetailVO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * @program: xkc
 * @Date: 2020/4/1 0001 14:26
 * @Author: houzh
 * @Description:
 */
public interface KfReportMapper  extends BaseMapper<CostomerReport> {

 Page<KfCostomerReportDetailVO> kfCostomerReportDetail(Page<KfCostomerReportDetailVO> page,@Param("report") CostomerReportVO report);

 List<Map<String, Object>> getPOrgList(@Param("userName") String userName);

 List<Map<String, Object>> getCOrgList(@Param("userName") String userName, @Param("orgID") String orgID);

 List<Map<String, Object>> getOrgList(@Param("userName") String userName, @Param("orgID") String orgID);

}
