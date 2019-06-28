package com.tahoecn.xkc.service.sys.impl;

import com.tahoecn.xkc.service.sys.ISCommonjobsService;
import com.tahoecn.xkc.mapper.sys.SCommonjobsMapper;
import com.tahoecn.xkc.model.sys.SCommonjobs;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-06-26
 */
@Service
public class SCommonjobsServiceImpl extends ServiceImpl<SCommonjobsMapper, SCommonjobs> implements ISCommonjobsService {
	
	@Autowired
	private SCommonjobsMapper SCommonjobsMapper;

	@Override
	public List<SCommonjobs> SystemCommonJobsList_Select(Map<String, Object> map) {
		List<SCommonjobs> list = SCommonjobsMapper.SystemCommonJobsList_Select(map);
		return list;
	}

	@Override
	public List<SCommonjobs> SystemCommonJobsList_SelectList() {
		List<SCommonjobs> list = SCommonjobsMapper.SystemCommonJobsList_SelectList();
		return list;
	}

	@Override
	public Boolean SystemCommonJobNameIsExists_Select(Map<String, Object> map) {
		String result= SCommonjobsMapper.SystemCommonJobNameIsExists_Select(map);
		if(StringUtils.isNotEmpty(result)){
			return true;
		}else {
			return false;			
		}
	}

	@Override
	public void SystemCommonJobStatus_Update(Map<String, Object> map) {
		SCommonjobsMapper.SystemCommonJobStatus_Update(map);
	}

	@Override
	public void SystemCommonJob_Delete(Map<String, Object> map) {
		SCommonjobsMapper.SystemCommonJob_Delete(map);
	}

	@Override
	public String SystemCommonJob_Insert(Map<String, Object> map) {
		String id = UUID.randomUUID().toString();
		map.put("id", id);
		SCommonjobsMapper.SystemCommonJob_Insert(map);
		return id;
	}

	@Override
	public void SystemCommonJob_Update(Map<String, Object> map) {
		SCommonjobsMapper.SystemCommonJob_Update(map);
	}

}
