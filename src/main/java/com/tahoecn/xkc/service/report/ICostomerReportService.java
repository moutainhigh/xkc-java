package com.tahoecn.xkc.service.report;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.common.annotation.DataSource;
import com.tahoecn.xkc.common.enums.DataSourceEnum;
import com.tahoecn.xkc.model.customer.CostomerReport;
import com.tahoecn.xkc.model.reprot.kfCostomerReportDetailVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-31
 */
@DataSource(DataSourceEnum.DB3)
@DS("s199")
public interface ICostomerReportService extends IService<CostomerReport> {

   public Page<kfCostomerReportDetailVO> kfCostomerReportDetail(Page<kfCostomerReportDetailVO> page, Map<String, Object> params);

}
