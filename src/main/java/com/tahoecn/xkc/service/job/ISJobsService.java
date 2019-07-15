package com.tahoecn.xkc.service.job;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.job.SJobs;
import com.tahoecn.xkc.model.sys.SAccount;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-04
 */
public interface ISJobsService extends IService<SJobs> {

    IPage<Map<String,Object>> SystemJobList_Select(IPage page, String authCompanyID, String productID, String orgID);

    IPage<SAccount> SystemUserList_Select(IPage page, String jobID, String authCompanyID);

    IPage<Map<String,Object>> SystemJobAllList_Select(IPage page, String authCompanyID, String productID, String orgID);

    boolean SystemJobUser_Insert(SAccount account, String jobID);

    IPage<SAccount> SystemOrgUserList_Select(IPage page, String userName, String employeeName, String jobID);

    boolean SystemJobUserRel_Insert(String userIDS, String jobID);
}
