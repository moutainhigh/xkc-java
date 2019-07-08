package com.tahoecn.xkc.service.project;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.project.VProjectbuilding;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-06
 */
public interface IVProjectbuildingService extends IService<VProjectbuilding> {

	/**
	 * 获取项目楼栋信息信息
	 */
	List<Map<String, Object>> BuildingDetailByProjectIDTop_Select(Map<String,Object> paramMap);

	/**
	 * 获取项目楼栋信息信息
	 */
	List<Map<String, Object>> BuildingDetail_Select(Map<String, Object> paramMap);

	/**
	 * 获取项目楼栋列表信息
	 */
	List<Map<String, Object>> BuildingList_Select(Map<String, Object> paramMap);

}
