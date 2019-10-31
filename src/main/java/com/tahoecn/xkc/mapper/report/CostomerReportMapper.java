package com.tahoecn.xkc.mapper.report;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.common.annotation.DataSource;
import com.tahoecn.xkc.common.enums.DataSourceEnum;
import com.tahoecn.xkc.model.customer.CostomerReport;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-31
 */
@DataSource(DataSourceEnum.DB3)
@DS("s199")
public interface CostomerReportMapper extends BaseMapper<CostomerReport> {

}
