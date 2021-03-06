package com.tahoecn.xkc.mapper.sys;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tahoecn.xkc.model.sys.SCommonjobs;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-06-26
 */
public interface SCommonjobsMapper extends BaseMapper<SCommonjobs> {
	
	List<SCommonjobs> SystemCommonJobsList_Select(@Param("AuthCompanyID")String AuthCompanyID, @Param("ProductID")String ProductID, @Param("JobName")String JobName);
	
	List<SCommonjobs> SystemCommonJobsList_SelectList();
	
	String SystemCommonJobNameIsExists_Select(@Param("AuthCompanyID")String AuthCompanyID, @Param("ProductID")String ProductID, @Param("JobName")String JobName);
	
	void SystemCommonJobStatus_Update(Map<String, Object> map);
	
	void SystemCommonJob_Delete(Map<String, Object> map);
	
	void SystemCommonJob_Insert(Map<String, Object> map);
	
	void SystemCommonJob_Update(Map<String, Object> map);

//    void SystemCommonJobAuth_Insert(@Param("OldMenus")String oldMenus,@Param("OldFunctions") String oldFunctions,
//                                    @Param("Menus")String menus, @Param("Functions")String functions,
//                                    @Param("JobID")String jobID);
}
