package com.tahoecn.xkc.service.sys;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.sys.SCommonjobs;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-06-26
 */
public interface ISCommonjobsService extends IService<SCommonjobs> {

	List<SCommonjobs> SystemCommonJobsList_Select(IPage page,String AuthCompanyID,String ProductID,String JobName);
	
	List<SCommonjobs> SystemCommonJobsList_SelectList();
	
	Boolean SystemCommonJobNameIsExists_Select(Map<String, Object> map);
	
	void SystemCommonJobStatus_Update(Map<String, Object> map);
	
	void SystemCommonJob_Delete(Map<String, Object> map);
	
	String SystemCommonJob_Insert(Map<String, Object> map);
	
	void SystemCommonJob_Update(Map<String, Object> map);
}
