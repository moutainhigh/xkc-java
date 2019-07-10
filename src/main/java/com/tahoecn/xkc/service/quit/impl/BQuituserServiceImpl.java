package com.tahoecn.xkc.service.quit.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.quit.BQuituserMapper;
import com.tahoecn.xkc.model.quit.BQuituser;
import com.tahoecn.xkc.service.quit.IBQuituserService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-06-26
 */
@Service
public class BQuituserServiceImpl extends ServiceImpl<BQuituserMapper, BQuituser> implements IBQuituserService {

    @Autowired
    private BQuituserMapper BQuituserMapper;

    @Override
    public IPage<Map<String, Object>> QuitUserOwnTeamList_Select(IPage page, Map<String, Object> map) {
        return BQuituserMapper.QuitUserOwnTeamList_Select(page,map);
    }

    @Override
    public IPage<Map<String, Object>> QuitUserSalesTeamList_Select(IPage page, Map<String, Object> map) {
        return BQuituserMapper.QuitUserSalesTeamList_Select(page,map);
    }

    @Override
    public IPage<Map<String, Object>> QuitUserChannelList_Select(IPage page, Map<String, Object> map) {
       return BQuituserMapper.QuitUserChannelList_Select(page,map);
    }
}
