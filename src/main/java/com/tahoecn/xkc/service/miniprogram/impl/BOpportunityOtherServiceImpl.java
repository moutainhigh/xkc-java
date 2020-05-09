package com.tahoecn.xkc.service.miniprogram.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.miniprogram.BOpportunityOtherMapper;
import com.tahoecn.xkc.model.miniprogram.BOpportunityOther;
import com.tahoecn.xkc.service.miniprogram.IBOpportunityOtherService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description: 上海报备其他备份服务实现类
 * @author: 张晓东
 * @time: 2020/4/28 10:38
 */
@Service
public class BOpportunityOtherServiceImpl extends ServiceImpl<BOpportunityOtherMapper, BOpportunityOther> implements IBOpportunityOtherService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(BOpportunityOther other) {
        this.baseMapper.insert(other);
    }
}
