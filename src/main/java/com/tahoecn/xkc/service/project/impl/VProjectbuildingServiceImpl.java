package com.tahoecn.xkc.service.project.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.project.VProjectbuildingMapper;
import com.tahoecn.xkc.model.project.VProjectbuilding;
import com.tahoecn.xkc.service.project.IVProjectbuildingService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-06
 */
@Service
public class VProjectbuildingServiceImpl extends ServiceImpl<VProjectbuildingMapper, VProjectbuilding> implements IVProjectbuildingService {

	@Autowired 
	private VProjectbuildingMapper vProjectbuildingMapper;
	/**
	 * 获取项目楼栋信息信息
	 */
	@Override
	public List<Map<String, Object>> BuildingDetailByProjectIDTop_Select(Map<String, Object> paramMap) {
		return vProjectbuildingMapper.ProjectBuildingDetailByProjectIDTop_Select(paramMap);
	}
	/**
	 * 获取项目楼栋信息信息
	 */
	@Override
	public List<Map<String, Object>> BuildingDetail_Select(Map<String, Object> paramMap) {
		return vProjectbuildingMapper.ProjectBuildingDetail_Select(paramMap);
	}
	/**
	 * 获取项目楼栋列表信息
	 */
	@Override
	public List<Map<String, Object>> BuildingList_Select(Map<String, Object> paramMap) {
		return vProjectbuildingMapper.ProjectBuildingList_Select(paramMap);
	}

}
