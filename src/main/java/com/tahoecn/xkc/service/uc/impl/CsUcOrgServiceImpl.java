package com.tahoecn.xkc.service.uc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.uc.CsUcOrgMapper;
import com.tahoecn.xkc.model.CsUcOrg;
import com.tahoecn.xkc.service.uc.CsUcOrgService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * UC组织机构信息 服务实现类
 * </p>
 *
 * @author zghw
 * @since 2018-11-15
 */
@Transactional
@Service
public class CsUcOrgServiceImpl extends ServiceImpl<CsUcOrgMapper, CsUcOrg> implements CsUcOrgService {

	@Override
	public void synOrgInfo(CsUcOrg orgInfo) {
		CsUcOrg ucOrg = new CsUcOrg();
		ucOrg.setFdSid(orgInfo.getFdSid());
		QueryWrapper<CsUcOrg> queryWrapper = new QueryWrapper<CsUcOrg>(ucOrg);
		CsUcOrg orgInfoOne = baseMapper.selectOne(queryWrapper);
		if (orgInfoOne != null) {
			orgInfo.setId(orgInfoOne.getId());
			orgInfo.setCreateDate(orgInfoOne.getCreateDate());
			baseMapper.updateById(orgInfo);
		} else {
			baseMapper.insert(orgInfo);
		}
	}
}
