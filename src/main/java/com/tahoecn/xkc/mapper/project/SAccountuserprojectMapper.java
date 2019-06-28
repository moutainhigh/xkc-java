package com.tahoecn.xkc.mapper.project;

import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.project.SAccountuserproject;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-06-28
 */
public interface SAccountuserprojectMapper extends BaseMapper<SAccountuserproject> {

	/**
	 * 用户切换项目
	 * @param map
	 */
	void changeUserProject(Map<String, Object> map);

}
