package com.tahoecn.xkc.service.project.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.project.SAccountuserprojectjobMapper;
import com.tahoecn.xkc.model.project.SAccountuserprojectjob;
import com.tahoecn.xkc.service.project.ISAccountuserprojectjobService;

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
public class SAccountuserprojectjobServiceImpl extends ServiceImpl<SAccountuserprojectjobMapper, SAccountuserprojectjob> implements ISAccountuserprojectjobService {

	@Autowired
    private SAccountuserprojectjobMapper sAccountuserprojectjobMapper;
	
	/**
	 * 用户切换岗位
	 * @param map
	 */
	@Override
	public void changeUserProjectJob(Map<String, Object> map) {
		sAccountuserprojectjobMapper.changeUserProjectJob(map);
	}

}
