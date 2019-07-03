package com.tahoecn.xkc.service.project.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tahoecn.xkc.model.project.BProject;
import com.tahoecn.xkc.mapper.project.BProjectMapper;
import com.tahoecn.xkc.service.project.IBProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class BProjectServiceImpl extends ServiceImpl<BProjectMapper, BProject> implements IBProjectService {

    @Override
    public List<Map<String,Object>> findByOrgID(IPage page,String orgID) {
        return baseMapper.findByOrgID(page,orgID);
    }

    @Override
    public List<Map<String,Object>> ProjectInfoList_SelectN(IPage page,String name, String cityID) {
        return baseMapper.ProjectInfoList_SelectN(page,name,cityID);
    }

}
