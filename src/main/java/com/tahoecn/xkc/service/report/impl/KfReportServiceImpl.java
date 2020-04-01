package com.tahoecn.xkc.service.report.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.report.CostomerReportMapper;
import com.tahoecn.xkc.mapper.report.KfReportMapper;
import com.tahoecn.xkc.model.customer.CostomerReport;
import com.tahoecn.xkc.model.customer.CostomerReportVO;
import com.tahoecn.xkc.model.reprot.KfCostomerReportDetailVO;
import com.tahoecn.xkc.service.report.IKfReportService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-31
 */
@Service
public class KfReportServiceImpl extends ServiceImpl<CostomerReportMapper, CostomerReport> implements IKfReportService {

    @Autowired
    private KfReportMapper kfReportMapper;

    @Override
    public Page<KfCostomerReportDetailVO> kfCostomerReportDetail(Page<KfCostomerReportDetailVO> page, CostomerReportVO report) {
        return kfReportMapper.kfCostomerReportDetail(page, report);
    }
}
