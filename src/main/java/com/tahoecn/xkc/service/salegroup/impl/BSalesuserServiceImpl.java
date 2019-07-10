package com.tahoecn.xkc.service.salegroup.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.salegroup.BSalesuserMapper;
import com.tahoecn.xkc.model.salegroup.BSalesuser;
import com.tahoecn.xkc.service.salegroup.IBSalesuserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-05
 */
@Service
public class BSalesuserServiceImpl extends ServiceImpl<BSalesuserMapper, BSalesuser> implements IBSalesuserService {

    @Override
    public List<Map<String, Object>> UserSalesList_Select(String projectID, String teamID) {
        return baseMapper.UserSalesList_Select(projectID,teamID);
    }
}
