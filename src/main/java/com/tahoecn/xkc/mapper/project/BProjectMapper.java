package com.tahoecn.xkc.mapper.project;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tahoecn.xkc.model.project.BProject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-06-25
 */
public interface BProjectMapper extends BaseMapper<BProject> {

    List<Map<String,Object>> findByOrgID(IPage page,@Param("OrgID") String orgID);

    List<Map<String,Object>> ProjectInfoList_SelectN(IPage page,@Param("Name")String Name, @Param("CityID")String cityID);

    String ProjectIsNoAllot_Select(@Param("ProjectID") String projectID);
}
