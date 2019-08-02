package com.tahoecn.xkc.mapper.report;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.common.annotation.DataSource;
import com.tahoecn.xkc.common.enums.DataSourceEnum;
import com.tahoecn.xkc.model.channel.CbFy;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-08
 */
public interface CbFyMapper extends BaseMapper<CbFy> {

    @DataSource(DataSourceEnum.DB2)
    void testSave(CbFy cbFy);

}
