package com.tahoecn.xkc.service.report.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.common.annotation.DataSource;
import com.tahoecn.xkc.common.enums.DataSourceEnum;
import com.tahoecn.xkc.mapper.report.CbFyMapper;
import com.tahoecn.xkc.model.channel.CbFy;
import com.tahoecn.xkc.service.report.ICbFyService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-08
 */
@Service
public class CbFyServiceImpl extends ServiceImpl<CbFyMapper, CbFy> implements ICbFyService {

    @Override
    //@DS("slave_my")
    @DataSource(DataSourceEnum.DB2)
    public void save1() {
        CbFy cbFy = new CbFy();
        cbFy.setFyGUID("123231123");
        baseMapper.insert(cbFy);
    }
}
