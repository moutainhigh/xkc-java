package com.tahoecn.xkc.service.channel.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.channel.BChannelorgMapper;
import com.tahoecn.xkc.model.channel.BChannelorg;
import com.tahoecn.xkc.model.dto.ChannelDto;
import com.tahoecn.xkc.service.channel.IBChannelorgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-06-25
 */
@Service
public class BChannelorgServiceImpl extends ServiceImpl<BChannelorgMapper, BChannelorg> implements IBChannelorgService {

    @Autowired
    private BChannelorgMapper channelorgMapper;
    @Override
    public List<ChannelDto> getList(Map map) {

        return channelorgMapper.getList(map);
    }
}
