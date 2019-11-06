package com.tahoecn.xkc.service.project;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.project.SAccountuserproject;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-06-28
 */
public interface ISAccountuserprojectService extends IService<SAccountuserproject> {

	/**
	 * 用户切换项目
	 * @param map
	 */
	void changeUserProject(Map<String, Object> map);

}
