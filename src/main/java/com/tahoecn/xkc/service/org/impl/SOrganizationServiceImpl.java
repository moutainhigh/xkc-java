package com.tahoecn.xkc.service.org.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.org.SOrganizationMapper;
import com.tahoecn.xkc.model.org.SOrganization;
import com.tahoecn.xkc.service.org.ISOrganizationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-06
 */
@Service
public class SOrganizationServiceImpl extends ServiceImpl<SOrganizationMapper, SOrganization> implements ISOrganizationService {

    @Override
    public List<Map<String, Object>> SystemOrganization_Select(String authCompanyID, String orgID, String productID, String pid, String status) {
        return baseMapper.SystemOrganization_Select(authCompanyID, orgID, productID, pid, status);
    }
}
