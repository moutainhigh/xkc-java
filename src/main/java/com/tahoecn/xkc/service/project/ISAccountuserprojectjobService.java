package com.tahoecn.xkc.service.project;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.project.SAccountuserprojectjob;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-06-28
 */
public interface ISAccountuserprojectjobService extends IService<SAccountuserprojectjob> {

	/**
	 * 用户切换岗位
	 * @param map
	 */
	void mUserProjectJobChange_Update(Map<String, Object> map);

}
