package com.tahoecn.xkc.service.channel.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.channel.BPojectchannelorgrelMapper;
import com.tahoecn.xkc.model.channel.BPojectchannelorgrel;
import com.tahoecn.xkc.service.channel.IBPojectchannelorgrelService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-06-25
 */
@Service
public class BPojectchannelorgrelServiceImpl extends ServiceImpl<BPojectchannelorgrelMapper, BPojectchannelorgrel> implements IBPojectchannelorgrelService {

    public void updateToDelete(String orgId, String userId) {
        baseMapper.updateToDelete(orgId,userId);
    }
}
