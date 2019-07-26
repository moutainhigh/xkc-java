package com.tahoecn.xkc.service.customer.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.customer.BCustomerfiltergroupMapper;
import com.tahoecn.xkc.model.customer.BCustomerfiltergroup;
import com.tahoecn.xkc.model.customer.BCustomerpotentialfiltergroup;
import com.tahoecn.xkc.service.customer.IBCustomerfiltergroupService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-08
 */
@Service
public class BCustomerfiltergroupServiceImpl extends ServiceImpl<BCustomerfiltergroupMapper, BCustomerfiltergroup> implements IBCustomerfiltergroupService {

    @Override
    public List<Map<String, Object>> groupList(String jobCode, String projectID, String userID) {
        return baseMapper.groupList(jobCode,projectID,userID);
    }
    /**
	 * 分组删除
	 */
	@Override
	public void mCustomerFilterGroupDetail_Delete(String ID) {
		BCustomerfiltergroup group = new BCustomerfiltergroup();
		group.setId(ID);
		group.setIsDel(1);
		baseMapper.updateById(group);
	}
	/**
	 * 潜在客户分组新增
	 */
	@Override
	public void mCustomerFilterGroupDetail_Insert(Map<String,Object> map) {
		BCustomerfiltergroup group = new BCustomerfiltergroup();
		group.setProjectID(map.get("ProjectID").toString());
		group.setName(map.get("Name").toString());
		group.setFilter(map.get("Filter").toString());
		group.setFilterDesc(map.get("FilterDesc").toString());
		group.setCreator(map.get("UserID").toString());
		group.setCreateTime(new Date());
		group.setIsDel(0);
		group.setStatus(1);
		baseMapper.insert(group);
	}
	/**
	 * 潜在客户筛选分组列表
	 */
	@Override
	public List<Map<String,Object>> mCustomerFilterGroupList_Select(Map<String,Object> paramMap) {
		return baseMapper.mCustomerFilterGroupList_Select(paramMap);
	}
}
