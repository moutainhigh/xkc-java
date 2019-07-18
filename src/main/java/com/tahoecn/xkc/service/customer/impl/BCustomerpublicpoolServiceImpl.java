package com.tahoecn.xkc.service.customer.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.customer.BClueMapper;
import com.tahoecn.xkc.mapper.customer.BCustomerpublicpoolMapper;
import com.tahoecn.xkc.mapper.opportunity.BOpportunityMapper;
import com.tahoecn.xkc.mapper.project.BProjectMapper;
import com.tahoecn.xkc.model.customer.BClue;
import com.tahoecn.xkc.model.customer.BCustomerpublicpool;
import com.tahoecn.xkc.model.opportunity.BOpportunity;
import com.tahoecn.xkc.model.project.BProject;
import com.tahoecn.xkc.service.customer.IBCustomerpublicpoolService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-11
 */
@Service
public class BCustomerpublicpoolServiceImpl extends ServiceImpl<BCustomerpublicpoolMapper, BCustomerpublicpool> implements IBCustomerpublicpoolService {
	@Autowired
	private BProjectMapper bProjectMapper;
	@Autowired
	private BOpportunityMapper bOpportunityMapper;
	@Autowired
	private BClueMapper bClueMapper;
	/**
	 * 客户公共池查询
	 */
	@Override
	public Map<String, Object> mCustomerGGCList_Select(Map<String, Object> paramMap) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("List", baseMapper.mCustomerGGCList_Select(paramMap));
		result.put("AllCount", baseMapper.mCustomerGGCList_Select_count(paramMap));
		result.put("PageSize", paramMap.get("PageSize"));
		return result;
	}
	/**
	 * 今日可抢客户数
	 */
	@Override
	public Map<String, Object> mCustomerGGCGrabDetail_Select(Map<String,Object> data, Map<String, Object> paramMap) {
		int AllGrabCount = 0;
		int AlreadyGrabCount = 0;
		int RemnantGrabCount = 0;
		
		String JobID = (String) paramMap.get("JobID");
		if("48FC928F-6EB5-4735-BF2B-29B1F591A582".equals(JobID)){
			AlreadyGrabCount = baseMapper.GGCGrab_Select1(paramMap);
			BProject bp = bProjectMapper.selectById(paramMap.get("ProjectID").toString());
			AllGrabCount = bp.getEveryDayApplyCustomerNum() == null ? 0 : bp.getEveryDayApplyCustomerNum();
			RemnantGrabCount = AllGrabCount - AlreadyGrabCount;
		}else if("0269F35E-B32D-4D12-8496-4E6E4CE597B7".equals(JobID)){
			AlreadyGrabCount = baseMapper.GGCGrab_Select2(paramMap);
			BProject bp = bProjectMapper.selectById(paramMap.get("ProjectID").toString());
			AllGrabCount = bp.getEveryDayApplyCustomerNum() == null ? 0 : bp.getEveryDayApplyCustomerNum();
			RemnantGrabCount = AllGrabCount - AlreadyGrabCount;
		}
		data.put("TodayAllGrabCount", AllGrabCount);
		data.put("TodayAlreadyGrabCount", AlreadyGrabCount);
		data.put("TodayRemnantGrabCount", RemnantGrabCount);
		return data;
	}
	/**
	 * 客户分享传播池查询
	 */
	@Override
	public Map<String, Object> mCustomerSharePageList_Select(Map<String,Object> paramMap) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("List", baseMapper.mCustomerSharePageList_Select(paramMap));
		result.put("AllCount", baseMapper.mCustomerSharePageList_Select_count(paramMap));
		result.put("PageSize", paramMap.get("PageSize"));
		return result;
	}
	/**
	 * 分享传播今日可抢客户数
	 */
	@Override
	public Map<String, Object> mCustomerFXCBGrabDetail_Select(Map<String, Object> data, Map<String,Object> paramMap) {
		int AllGrabCount = 0;
		int AlreadyGrabCount = 0;
		int RemnantGrabCount = 0;
		
		BProject bp = bProjectMapper.selectById(paramMap.get("ProjectID").toString());
		AllGrabCount = bp.getEveryDayApplyCustomerNum() == null ? 0 : bp.getEveryDayApplyCustomerNum();
		String JobID = (String) paramMap.get("JobID");
		if("0269F35E-B32D-4D12-8496-4E6E4CE597B7".equals(JobID)){
			AlreadyGrabCount = baseMapper.FXCBGrab_Select1(paramMap);
			RemnantGrabCount = AllGrabCount - AlreadyGrabCount;
		}else if("48FC928F-6EB5-4735-BF2B-29B1F591A582".equals(JobID)){
			AlreadyGrabCount = baseMapper.FXCBGrab_Select2(paramMap);
			RemnantGrabCount = AllGrabCount - AlreadyGrabCount;
		}
		data.put("TodayAllGrabCount", AllGrabCount);
		data.put("TodayAlreadyGrabCount", AlreadyGrabCount);
		data.put("TodayRemnantGrabCount", RemnantGrabCount);
		return data;
	}
	/**
	 * 获取公共池内客户的机会，线索以及状态，先将其置为无效
	 */
	@Override
	public Map<String,Object> mGetPublicPoolDetail_Select(Map<String, Object> obj) {
		Map<String,Object> map = baseMapper.mGetPublicPoolDetail_Select(obj);
//		UPDATE dbo.B_Opportunity SET Status = 6 WHERE ID = @OpportunityID
//		UPDATE dbo.B_Clue SET Status = 3 WHERE ID = @ClueID
		BOpportunity unity = new BOpportunity();
		unity.setStatus(6);
		unity.setId(map.get("OpportunityID").toString());
		bOpportunityMapper.updateById(unity);
		BClue clue = new BClue();
		clue.setStatus(3);
		clue.setId(map.get("ClueID").toString());
		bClueMapper.updateById(clue);
		return map;
	}

}
