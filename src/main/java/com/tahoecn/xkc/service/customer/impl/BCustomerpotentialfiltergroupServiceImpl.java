package com.tahoecn.xkc.service.customer.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.customer.BCustomerpotentialfiltergroupMapper;
import com.tahoecn.xkc.model.customer.BCustomerpotentialfiltergroup;
import com.tahoecn.xkc.service.customer.IBCustomerpotentialfiltergroupService;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-10
 */
@Service
public class BCustomerpotentialfiltergroupServiceImpl extends ServiceImpl<BCustomerpotentialfiltergroupMapper, BCustomerpotentialfiltergroup> implements IBCustomerpotentialfiltergroupService {

	/**
	 * 潜在客户筛选分组删除
	 */
	@Override
	public void mCustomerPotentialFilterGroupDetail_Delete(String ID) {
		BCustomerpotentialfiltergroup group = new BCustomerpotentialfiltergroup();
		group.setId(ID);
		group.setIsDel(1);
		baseMapper.updateById(group);
	}
	/**
	 * 潜在客户分组新增
	 */
	@Override
	public void mCustomerPotentialFilterGroupDetail_Insert(Map<String,Object> map) {
		BCustomerpotentialfiltergroup group = new BCustomerpotentialfiltergroup();
		group.setProjectID(map.get("ProjectID").toString());
		group.setName(map.get("Name").toString());
		group.setFilter(map.get("Filter").toString());
		group.setFilterDesc(map.get("FilterDesc").toString());
//		group.setCreator(Creator);
		group.setCreateTime(new Date());
		group.setIsDel(0);
		group.setStatus(1);
		baseMapper.insert(group);
		group.setCreator(group.getId());
		baseMapper.updateById(group);
	}

}
