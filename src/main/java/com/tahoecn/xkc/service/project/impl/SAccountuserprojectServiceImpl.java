package com.tahoecn.xkc.service.project.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.project.SAccountuserprojectMapper;
import com.tahoecn.xkc.model.project.SAccountuserproject;
import com.tahoecn.xkc.service.project.ISAccountuserprojectService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-06-28
 */
@Service
public class SAccountuserprojectServiceImpl extends ServiceImpl<SAccountuserprojectMapper, SAccountuserproject> implements ISAccountuserprojectService {

	@Autowired
    private SAccountuserprojectMapper sAccountuserprojectMapper;
	
	/**
	 * 用户切换项目
	 * @param map
	 */
	@Override
	public void changeUserProject(Map<String, Object> map) {
		sAccountuserprojectMapper.changeUserProject(map);
	}

}
