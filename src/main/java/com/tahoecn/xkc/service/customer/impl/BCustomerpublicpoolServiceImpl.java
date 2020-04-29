package com.tahoecn.xkc.service.customer.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.common.utils.StringShieldUtil;
import com.tahoecn.xkc.mapper.customer.BClueMapper;
import com.tahoecn.xkc.mapper.customer.BCustomerpotentialMapper;
import com.tahoecn.xkc.mapper.customer.BCustomerpublicpoolMapper;
import com.tahoecn.xkc.mapper.opportunity.BOpportunityMapper;
import com.tahoecn.xkc.mapper.project.BProjectMapper;
import com.tahoecn.xkc.mapper.salegroup.BSalesuserMapper;
import com.tahoecn.xkc.model.customer.BClue;
import com.tahoecn.xkc.model.customer.BCustomerpotential;
import com.tahoecn.xkc.model.customer.BCustomerpublicpool;
import com.tahoecn.xkc.model.opportunity.BOpportunity;
import com.tahoecn.xkc.model.project.BProject;
import com.tahoecn.xkc.model.salegroup.BSalesuser;
import com.tahoecn.xkc.service.customer.IBCustomerpublicpoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
	@Autowired
	private BCustomerpotentialMapper bCustomerpotentialMapper;
	@Autowired
	private BSalesuserMapper bSalesuserMapper;
	@Autowired
	private BCustomerWhiteListServiceImpl customerWhiteListService;
	/**
	 * 客户公共池查询
	 */
	@Override
	public Map<String, Object> mCustomerGGCList_Select(Map<String, Object> paramMap) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> maps = baseMapper.mCustomerGGCList_Select(paramMap);

		// 客户vip白名单过滤
		// chenghong  白名单start
		Iterator<Map<String, Object>> iterator = maps.iterator();
		while (iterator.hasNext()) {
			Map<String, Object> map = iterator.next();
			String customerID = (String)map.get("CustomerID");
			if (customerID != null && customerWhiteListService.judgeIsWhiteCustomer(customerID)) {
				String customerName = (String)map.get("CustomerName");
				if (customerName != null) {
					map.put("CustomerName", StringShieldUtil.getFilterStrHasFirstChar(customerName));
				}

				String customerMobile = (String)map.get("CustomerMobile");
				if (customerMobile != null) {
					map.put("CustomerMobile", StringShieldUtil.getAllStarStr(customerMobile));
				}
			}
		}
		// chenghong  白名单end

		result.put("List", maps);
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
		clue.setId(map.get("ClueID")==null ? "" : map.get("ClueID").toString());
		bClueMapper.updateById(clue);
		return map;
	}
	/**
	 * 公共池抢客
	 */
	@Override
	public String mCustomerGGCList_Insert(Map<String, Object> paramMap) {
		paramMap.put("MessageInfo", null);
		baseMapper.mCustomerGGCList_Insert(paramMap);
		return paramMap.get("MessageInfo").toString();
	}
	/**
	 * 自渠抢客公共池客户
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void mGGCCustomerByZQDetail_Update(Map<String, Object> param) {
		String ClueID = param.get("ClueID").toString();
		String UserID = param.get("UserID").toString();
		String PublicID = param.get("PublicID").toString();
		String OrgName = "";
		String OrgID = "";
		String AdviserGroupID = "";
		String NewClueID = "";
		String CustomerPotentialID = "";
		String CustomerPotentialName = "";
		String CustomerPotentialMobile = "";
		//新线索ID
		NewClueID = UUID.randomUUID().toString();
		//获取该线索的潜在客户ID
		BClue clue = bClueMapper.selectById(ClueID);
		CustomerPotentialID = clue.getCustomerPotentialID();
		//获取潜在客户信息
		BCustomerpotential tial = bCustomerpotentialMapper.selectById(CustomerPotentialID);
		CustomerPotentialMobile = tial.getMobile();
		CustomerPotentialName = tial.getName();
		//获取自渠所在组织
		Map<String,Object> map = baseMapper.fromBSalesGroupMember(param);
		OrgName = map.get("").toString();
		OrgID = map.get("").toString();
		AdviserGroupID = map.get("").toString();
		//创建线索
		param.put("NewClueID", NewClueID);
		param.put("OrgName", OrgName);
		param.put("AdviserGroupID", AdviserGroupID);
		baseMapper.newBClue(param);
		//添加跟进记录
		param.put("CustomerPotentialName", CustomerPotentialName);
		param.put("CustomerPotentialMobile", CustomerPotentialMobile);
		param.put("NewClueID", NewClueID);
		param.put("OrgID", OrgID);
		baseMapper.insBCustomerPotentialFollowUp(param);
		//修改公共池状态
		BCustomerpublicpool pool = new BCustomerpublicpool();
		pool.setIsDel(1);
		pool.setEditor(UserID);
		pool.setEditeTime(new Date());
		pool.setId(PublicID);
		baseMapper.updateById(pool);
	}
	/**
	 * 分享传播池客户信息
	 */
	@Override
	public List<Map<String,Object>> mShareCustomerList_Select(Map<String,Object> parameter) {
		String UserID = (String) parameter.get("UserID");
		BSalesuser bs = bSalesuserMapper.selectById(UserID);
		String SaleUserName = bs.getName();
		parameter.put("SaleUserName", SaleUserName);
		return baseMapper.mShareCustomerList_Select(parameter);
	}

}
