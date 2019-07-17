package com.tahoecn.xkc.mapper.job;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tahoecn.xkc.model.job.SJobs;
import com.tahoecn.xkc.model.sys.SAccount;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-04
 */
public interface SJobsMapper extends BaseMapper<SJobs> {
    void userJobInsert(@Param("UserID") String UserID, @Param("RoleID") String RoleID,@Param("ProjectID") String ProjectID);

    IPage<Map<String,Object>> SystemJobList_Select(IPage page, @Param("AuthCompanyID") String authCompanyID, @Param("ProductID")String productID, @Param("OrgID")String orgID);

    IPage<SAccount> SystemUserList_Select(IPage page, @Param("JobID")String jobID, @Param("AuthCompanyID")String authCompanyID);

    IPage<Map<String,Object>> SystemJobAllList_Select(IPage page, @Param("AuthCompanyID") String authCompanyID, @Param("ProductID")String productID, @Param("OrgID")String orgID);

    IPage<SAccount> SystemOrgUserList_Select(IPage page, @Param("UserName")String userName, @Param("EmployeeName")String employeeName, @Param("JobID")String jobID);

    void SystemJobUserRel_Insert(@Param("UserIDS") String userIDS, @Param("JobID")String jobID);
}
