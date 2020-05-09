package com.tahoecn.xkc.service.risk.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.risk.BRisknnamelogMapper;
import com.tahoecn.xkc.model.risk.BRisknnamelog;
import com.tahoecn.xkc.service.risk.IBRisknnamelogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author YYY
 * @since 2020-05-06
 */
@Service
public class BRisknnamelogServiceImpl extends ServiceImpl<BRisknnamelogMapper, BRisknnamelog> implements IBRisknnamelogService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(BRisknnamelog data) {
        this.baseMapper.insert(data);
    }
}
