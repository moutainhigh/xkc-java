package com.tahoecn.xkc.service.user.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.user.CWxuserMapper;
import com.tahoecn.xkc.model.user.CWxuser;
import com.tahoecn.xkc.service.user.ICWxuserService;

import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-17
 */
@Service
public class CWxuserServiceImpl extends ServiceImpl<CWxuserMapper, CWxuser> implements ICWxuserService {
	/**
	 * 获取该分享人的信息
	 */
	@Override
	public Map<String, Object> mGetShareDetail_Select(JSONObject parameter) {
		String  Category = "";
		String  ShareWXUserID = "";
		String  ShareLogID = "";
		String  SaleUserID = "";
		String  JobID = "";
		//根据电话获取信息
		Map<String, Object> user = baseMapper.selWXUserByMobil(parameter);
		Category = user.get("Category").toString();
		ShareWXUserID = user.get("ShareWXUserID").toString();
		ShareLogID = user.get("ShareLogID").toString();
		if("0".equals(Category)){
			Map<String, Object> id = baseMapper.selJobID(ShareLogID);
			SaleUserID = id.get("BindChannelUserID").toString();
			JobID = id.get("BindAdviserGroupID").toString();
		}else if("1".equals(Category)){
			if(ShareWXUserID != null && !"".equals(ShareWXUserID)){
				Map<String, Object> id = baseMapper.selUserID(ShareWXUserID);
				SaleUserID = id.get("BindChannelUserID").toString();
				JobID = id.get("BindAdviserGroupID").toString();
			}else{
				SaleUserID = "";
				JobID = "";
			}
		}
		Map<String,Object> result = baseMapper.getShareDetail(JobID,SaleUserID);
		return result;
	}
}
