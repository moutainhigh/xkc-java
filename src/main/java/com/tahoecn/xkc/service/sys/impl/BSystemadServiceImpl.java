package com.tahoecn.xkc.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.sys.BSystemadMapper;
import com.tahoecn.xkc.model.sys.BSystemad;
import com.tahoecn.xkc.service.sys.IBSystemadService;

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
 * @since 2019-07-01
 */
@Service
public class BSystemadServiceImpl extends ServiceImpl<BSystemadMapper, BSystemad> implements IBSystemadService {

	@Autowired
    private BSystemadMapper bSystemadMapper;
    
	/**
	 * 获取广告列表
	 * @param map
	 * @return
	 */
	@Override
	public List<Map<String,Object>> SystemAD_Detail_Find(Map<String, Object> map) {
		return bSystemadMapper.SystemAD_Detail_Find(map);
	}

	/*
	 * 插入新的广告数据
	 */
	@Override
	public void SystemAD_Insert(Map<String, Object> map) {
		bSystemadMapper.SystemAD_Insert(map);
	}
	/*
	 * 修改原有的广告数据
	 */
	@Override
	public void SystemAD_Update(Map<String, Object> map) {
		bSystemadMapper.SystemAD_Update(map);
	}

}
