package com.tahoecn.xkc.service.job;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.dto.SMenusXkcDto;
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

    IPage<Map<String,Object>> SystemJobList_Select(IPage page,String authCompanyID, String productID, String orgID);

    List<SAccount> SystemUserList_Select(String jobID, String authCompanyID);

    IPage<Map<String,Object>> SystemJobAllList_Select(IPage page,String authCompanyID, String productID, String orgID);

    boolean SystemJobUser_Insert(SAccount account, String jobID);

    IPage<SAccount> SystemOrgUserList_Select(IPage page, String userName, String employeeName, String jobID);

    boolean SystemJobUserRel_Insert(String userIDS, String jobID);

    boolean SystemJobAuth_Insert(String menus, String jobID);

    boolean SystemJobUserRel_Delete(String userIDS, String jobID);

    boolean SystemJobAuthOrg_Insert(String orgIDS, String jobID);

    boolean SystemJobUser_Update(SAccount account);

    List<Map<String, Object>> SystemJobAuthOrg_Select(String pid, String jobID);

    List<Map<String, Object>> CommonJobList_Select();

    List<SMenusXkcDto> MenuOrFunIDList_Select_Tree();

    List<Map<String, Object>> MenuOrFunIDList_Select(String JobID);

    Boolean FunctionAuthorization_Insert(String jobID, String mainID, String sonID);

    boolean SystemJob_Insert(SJobs jobs);
}
