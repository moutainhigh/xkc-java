package com.tahoecn.xkc.service.job.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.job.SJobsMapper;
import com.tahoecn.xkc.model.job.SJobs;
import com.tahoecn.xkc.model.sys.SAccount;
import com.tahoecn.xkc.service.job.ISJobsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-04
 */
@Service
public class SJobsServiceImpl extends ServiceImpl<SJobsMapper, SJobs> implements ISJobsService {

    @Override
    public IPage<Map<String,Object>> SystemJobList_Select(IPage page, String authCompanyID, String productID, String orgID) {
        return baseMapper.SystemJobList_Select(page,authCompanyID,productID,orgID);
    }

    @Override
    public IPage<SAccount> SystemUserList_Select(IPage page, String jobID, String authCompanyID) {
        return baseMapper.SystemUserList_Select(page,jobID,authCompanyID);
    }

    @Override
    public IPage<Map<String,Object>> SystemJobAllList_Select(IPage page, String authCompanyID, String productID, String orgID) {
        return baseMapper.SystemJobAllList_Select(page,authCompanyID,productID,orgID);
    }
}
