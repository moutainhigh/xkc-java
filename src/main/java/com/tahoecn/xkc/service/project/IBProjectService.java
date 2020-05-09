package com.tahoecn.xkc.service.project;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tahoecn.xkc.model.project.BProject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-06-25
 */
public interface IBProjectService extends IService<BProject> {

    Map<String,Object> findByOrgID(String orgID,String Name,int PageIndex,int PageSize);

    List<Map<String,Object>> ProjectInfoList_SelectN(IPage page,String name, String cityID);

    int isReport(String projectId, String userID, String mobile);

    int ProjectIsNoAllot_Select(String projectID);

    List<String> getProjectIDs();

    List<Map<String, Object>> ProjectList_Select(String name,String userID);

    List<Map<String, Object>> addName(List<Map<String, Object>> list);

    Map<String, Object> findAllProject(String name, int pageIndex, int pageSize, Object userId);
}
