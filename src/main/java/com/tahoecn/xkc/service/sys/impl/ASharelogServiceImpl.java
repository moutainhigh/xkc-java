package com.tahoecn.xkc.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.sys.ASharelogMapper;
import com.tahoecn.xkc.mapper.user.CWxuserMapper;
import com.tahoecn.xkc.model.sys.ASharelog;
import com.tahoecn.xkc.model.user.CWxuser;
import com.tahoecn.xkc.service.sys.IASharelogService;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-18
 */
@Service
public class ASharelogServiceImpl extends ServiceImpl<ASharelogMapper, ASharelog> implements IASharelogService {

	@Autowired
	private CWxuserMapper cWxuserMapper;
	/**
	 * 分享页面
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void mShareAppLog_Insert(Map<String, Object> paramMap) {
		String Category = (String) paramMap.get("Category");
		String ProjectID = (String) paramMap.get("ProjectID");
		String UserID = (String) paramMap.get("UserID");
		String WXUserID = "";
		String ShareProjectID = "";
		if("2".equals(Category) || "3".equals(Category)){
			ShareProjectID = ProjectID;
		}else{
			Map<String,Object> pro = baseMapper.selShareProjectID(ProjectID);
			ShareProjectID = (String) pro.get("ID");
		}
//		SELECT @WXUserID = ID FROM dbo.C_WXUser WHERE BindChannelUserID = '{UserID}'
		QueryWrapper<CWxuser> wrapper = new QueryWrapper<CWxuser>();
		wrapper.eq("BindChannelUserID", UserID);
		CWxuser wx = cWxuserMapper.selectOne(wrapper);
		WXUserID = wx.getId();
		
		ASharelog log = new ASharelog();
		log.setId(paramMap.get("ID").toString());
		log.setShareID(ShareProjectID);
		log.setPagePath(paramMap.get("PagePath").toString());
		log.setWXUserID(WXUserID);
		log.setAdviserGroupID(paramMap.get("JobID").toString());
		log.setCategory((int)paramMap.get("Category"));
		log.setRemark(paramMap.get("Remark").toString());
		log.setShareWay((int)paramMap.get("ShareWay"));
		log.setCreator(paramMap.get("UserID").toString());
		log.setCreateTime(new Date());
		log.setIsDel(0);
		log.setStatus(1);
		baseMapper.insert(log);
	}

}
