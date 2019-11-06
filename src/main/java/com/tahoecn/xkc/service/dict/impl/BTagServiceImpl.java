package com.tahoecn.xkc.service.dict.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.dict.BTagMapper;
import com.tahoecn.xkc.model.dict.BTag;
import com.tahoecn.xkc.service.dict.IBTagService;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-08
 */
@Service
public class BTagServiceImpl extends ServiceImpl<BTagMapper, BTag> implements IBTagService {
	/**
	 * 获取Tag列表信息
	 */
	@Override
	public List<Map<String, Object>> BTaglist(String userID) {
		return baseMapper.BTaglist(userID);
	}

}
