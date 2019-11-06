package com.tahoecn.xkc.mapper.project;

import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.project.SAccountuserprojectjob;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-06-28
 */
public interface SAccountuserprojectjobMapper extends BaseMapper<SAccountuserprojectjob> {

	/**
	 * 用户切换岗位
	 * @param map
	 */
	void mUserProjectJobChange_Update(Map<String, Object> map);

}
