package com.tahoecn.xkc.mapper.project;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.project.VProjectbuilding;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-06
 */
public interface VProjectbuildingMapper extends BaseMapper<VProjectbuilding> {

	/**
	 * 获取项目楼栋信息信息
	 */
	List<Map<String, Object>> ProjectBuildingDetailByProjectIDTop_Select(Map<String, Object> paramMap);
	/**
	 * 获取项目楼栋信息信息
	 */
	List<Map<String, Object>> ProjectBuildingDetail_Select(Map<String, Object> paramMap);

}
