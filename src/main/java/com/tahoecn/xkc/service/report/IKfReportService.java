package com.tahoecn.xkc.service.report;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.customer.CostomerReport;
import com.tahoecn.xkc.model.customer.CostomerReportVO;
import com.tahoecn.xkc.model.reprot.KfCostomerReportDetailVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-31
 */
public interface IKfReportService extends IService<CostomerReport> {

   public Page<KfCostomerReportDetailVO> kfCostomerReportDetail(Page<KfCostomerReportDetailVO> page, CostomerReportVO report);

}
