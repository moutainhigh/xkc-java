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

    List<Map<String,Object>> findByOrgID(IPage page,String orgID);

    List<Map<String,Object>> ProjectInfoList_SelectN(IPage page,String name, String cityID);

}
