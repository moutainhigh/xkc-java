package com.tahoecn.xkc.service.channel.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.channel.BChanneluserMapper;
import com.tahoecn.xkc.model.channel.BChanneluser;
import com.tahoecn.xkc.model.dto.ChannelDto;
import com.tahoecn.xkc.service.channel.IBChanneluserService;
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
public class BChanneluserServiceImpl extends ServiceImpl<BChanneluserMapper, BChanneluser> implements IBChanneluserService {

    @Override
    public List<Map<String, String>> AgenApproverList() {
        return baseMapper.AgenApproverList();
    }


}
