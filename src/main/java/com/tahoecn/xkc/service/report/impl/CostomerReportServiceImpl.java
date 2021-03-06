package com.tahoecn.xkc.service.report.impl;

import org.springframework.stereotype.Service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.common.annotation.DataSource;
import com.tahoecn.xkc.common.enums.DataSourceEnum;
import com.tahoecn.xkc.mapper.report.CostomerReportMapper;
import com.tahoecn.xkc.model.customer.CostomerReport;
import com.tahoecn.xkc.service.report.ICostomerReportService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-31
 */

@Service
@Transactional(readOnly = true)
@DataSource(DataSourceEnum.DB3)
public class CostomerReportServiceImpl extends ServiceImpl<CostomerReportMapper, CostomerReport> implements ICostomerReportService {


    @Override
    public IPage<CostomerReport> page(IPage<CostomerReport> page, Wrapper<CostomerReport> queryWrapper) {
        return super.page(page, queryWrapper);
    }

}
