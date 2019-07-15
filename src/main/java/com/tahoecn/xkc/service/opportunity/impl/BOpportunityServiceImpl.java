package com.tahoecn.xkc.service.opportunity.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.customer.BCustomerfollowupMapper;
import com.tahoecn.xkc.mapper.customer.BCustomerpublicpoolMapper;
import com.tahoecn.xkc.mapper.customer.SMessageMapper;
import com.tahoecn.xkc.mapper.customer.STaskMapper;
import com.tahoecn.xkc.mapper.opportunity.BOpportunityMapper;
import com.tahoecn.xkc.mapper.salegroup.BSalesgroupmemberMapper;
import com.tahoecn.xkc.mapper.sys.SAccountMapper;
import com.tahoecn.xkc.model.customer.BCustomerfollowup;
import com.tahoecn.xkc.model.customer.BCustomerpublicpool;
import com.tahoecn.xkc.model.customer.SMessage;
import com.tahoecn.xkc.model.customer.STask;
import com.tahoecn.xkc.model.opportunity.BOpportunity;
import com.tahoecn.xkc.model.salegroup.BSalesgroupmember;
import com.tahoecn.xkc.model.sys.SAccount;
import com.tahoecn.xkc.service.opportunity.IBOpportunityService;

import java.util.Date;
import java.util.HashMap;
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
 * @since 2019-07-06
 */
@Service
public class BOpportunityServiceImpl extends ServiceImpl<BOpportunityMapper, BOpportunity> implements IBOpportunityService {
	
	@Autowired
	private SAccountMapper sAccountMapper;
	@Autowired
	private BCustomerfollowupMapper bCustomerfollowupMapper;
	@Autowired
	private BCustomerpublicpoolMapper bCustomerpublicpoolMapper;
	@Autowired
	private STaskMapper sTaskMapper;
	@Autowired
	private BSalesgroupmemberMapper bSalesgroupmemberMapper;
	@Autowired
	private SMessageMapper sMessageMapper;
	/**
	 * 客户确认归属
	 */
	@Override
	public void mCustomerBelong_Update(Map<String,Object> paramMap) {
		//存储过程
		/*EXEC P_CustomerBelong_Update @ProjectID = N'{ProjectID}',
			    @UserID = N'{UserID}',
			    @OrgID = N'{OrgID}',
			    @JobID = N'{JobID}',
			    @OpportunityIDList = N'{OpportunityIDList}';*/
		baseMapper.mCustomerBelong_Update(paramMap);
	}

	/**
	 * 客户回收
	 */
	@Override
	public void mCustomerRecovery_Update(Map<String,Object> paramMap) {
		//调用存储过程
		baseMapper.mCustomerRecovery_Update(paramMap);
		/*EXEC P_CustomerRecovery_Update @ProjectID = N'{ProjectID}',
			    @UserID = N'{UserID}',
			    @OrgID = N'{OrgID}',
			    @JobID = N'{JobID}',
			    @Code = N'{Code}',
			    @AdviserIDList = N'{AdviserIDList}',
			    @OpportunityIDList = N'{OpportunityIDList}';*/
		/*String Code = (String) paramMap.get("Code");
		String AdviserIDList = (String) paramMap.get("AdviserIDList");
		List<Map<String,Object>> OpportunityIDTable = null;
		String Opportunity = "";
		if(AdviserIDList != null && !"".equals(AdviserIDList)){
			if(Code.equals("YQWGJ")){//逾期未跟进
				OpportunityIDTable = baseMapper.getYQWGJ(paramMap);
			}
			if(Code.equals("YQWRG")){//逾期未认购
				OpportunityIDTable = baseMapper.getYQWRG(paramMap);
			}
			if(Code.equals("YQWQY")){ //--逾期未签约
				OpportunityIDTable = baseMapper.getYQWQY(paramMap);
			}
			if(Code.equals("YQWJK")){ //逾期未交款
				OpportunityIDTable = baseMapper.getYQWJK(paramMap);
			}
			for (Map<String,Object> item : OpportunityIDTable){
				Opportunity += item.get("ID").toString() + ",";
			}
//			Opportunity = "OpportunityID IN (" +  Opportunity + ")";
		}
		paramMap.put("OpportunityIDTable", Opportunity);
		if(Code.equals("YQWGJ") || Code.equals("YQWRG") || Code.equals("YQWQY") || Code.equals("YQWJK")){
			baseMapper.updateCustomerRecovery1(paramMap);
			baseMapper.updateCustomerRecovery2(paramMap);
			baseMapper.updateCustomerRecovery3(paramMap);
			baseMapper.updateCustomerRecovery4(paramMap);
			insertCustomerRecovery1(paramMap);
			insertCustomerRecovery2(paramMap);
			insertCustomerRecovery3(paramMap);
			baseMapper.updateCustomerRecovery5(paramMap);
			insertCustomerRecovery4(paramMap);
		}*/
	}

	
	private void insertCustomerRecovery4(Map<String, Object> paramMap) {
		STask task = new STask();
		task.setTaskType("SyncOpportunity");
		task.setTaskPara(paramMap.get("OpportunityIDTable").toString());
		task.setStartTime(new Date());
		task.setCreateTime(new Date());
		sTaskMapper.insert(task);
	}

	private void insertCustomerRecovery3(Map<String, Object> paramMap) {
		String UserID = paramMap.get("UserID").toString();
		String Opportunity = paramMap.get("OpportunityIDTable").toString();
		QueryWrapper<BOpportunity> wrapper = new QueryWrapper<BOpportunity>();
		wrapper.in("ID", Opportunity);
		wrapper.isNotNull("SaleUserID");
		BOpportunity unity = baseMapper.selectOne(wrapper);
		Map<String,Object> userName = getSaleUserName(UserID);
		SMessage mess = new SMessage();
        mess.setProjectID(unity.getProjectID());
        mess.setBizID(unity.getId());
        mess.setBizType("Opportunity");
        mess.setSubject("客户回收");
        mess.setContent(userName.get("FollwUpUserName") + "经理已回收" + unity.getCustomerName() + unity.getCustomerMobile());
        mess.setSender(UserID);
        mess.setSendTime(new Date());
        mess.setMessageType("76D9C77E-CD2D-CB4C-4DBC-8F19CDAFE607");
        mess.setReceiver(unity.getSaleUserID());
        mess.setIsRead(0);
        mess.setIsPush(0);
        mess.setIsNeedPush(1);
        mess.setCreator(UserID);
        mess.setCreateTime(new Date());
        mess.setIsDel(0);
        mess.setStatus(1);
        sMessageMapper.insert(mess);
	}

	private void insertCustomerRecovery2(Map<String, Object> paramMap) {
		String UserID = paramMap.get("UserID").toString();
		String Opportunity = paramMap.get("OpportunityIDTable").toString();
		String ProjectID = paramMap.get("ProjectID").toString();
		QueryWrapper<BOpportunity> wrapper = new QueryWrapper<BOpportunity>();
		wrapper.in("ID", Opportunity);
		BOpportunity unity = baseMapper.selectOne(wrapper);
		Map<String,Object> userName = getSaleUserName(UserID);
		Map<String,Object> userName1 = getSaleUserName(unity.getSaleUserID());
		String SaleTeamID = getSaleGroupID(UserID,ProjectID);
		String SaleTeamName = getSaleGroupName(UserID,ProjectID);
		BCustomerpublicpool pool = new BCustomerpublicpool();
		pool.setClueID(unity.getClueID());
		pool.setOpportunityID(unity.getId());
		pool.setCustomerID(unity.getCustomerID());
		pool.setName(unity.getCustomerName());
		pool.setMobile(unity.getCustomerMobile());
		pool.setCustomerLevel(unity.getCustomerLevel());
		pool.setReason(userName.get("FollwUpUserName") + "经理回收");
		pool.setTheFirstVisitDate(unity.getTheFirstVisitDate());
		pool.setSaleUserID(unity.getSaleUserID());
		pool.setSaleUserName(userName1.get("FollwUpUserName").toString());
		pool.setSaleTeamID(SaleTeamID);
		pool.setSaleTeamName(SaleTeamName==null || "".equals(SaleTeamName) ? "无" : SaleTeamName);
		pool.setCreator(UserID);
		pool.setCreateTime(new Date());
		bCustomerpublicpoolMapper.insert(pool);
	}
	private String getSaleGroupName(String userID, String projectID) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userID", userID);
		map.put("projectID", projectID);
		Map<String,Object> result = bSalesgroupmemberMapper.getSaleGroupName(map);
		if(result.get("ReceptionGroupID") == null || "".equals(result.get("ReceptionGroupID"))){
			return result.get("pName").toString();
		}else{
			return result.get("gName").toString();
		}
	}

	private String getSaleGroupID(String UserID,String ProjectID) {
		QueryWrapper<BSalesgroupmember> w = new QueryWrapper<BSalesgroupmember>();
		w.in("ProjectID", ProjectID);
		w.in("MemberID", UserID);
		w.in("IsDel", 0);
		w.in("Status", 1);
		BSalesgroupmember ber = bSalesgroupmemberMapper.selectOne(w);
		return ber.getReceptionGroupID();
		
	}

	private Map<String,Object> getSaleUserName(String UserID){
		Map<String,Object> map = new HashMap<String,Object>();
		QueryWrapper<SAccount> w = new QueryWrapper<SAccount>();
		w.in("ID", UserID);
		SAccount acc = sAccountMapper.selectOne(w);
		map.put("FollwUpUserMobile", acc.getMobile());
		if(UserID == null || UserID.equals("") || UserID.equals("99") || UserID.equals("sys")){
			map.put("FollwUpUserName", "系统");
		}else{
			if(acc.getEmployeeName() == null || "".equals(acc.getEmployeeName())){
				map.put("FollwUpUserName", "系统");
			}else{
				map.put("FollwUpUserName", acc.getEmployeeName());
			}
		}
		return map;
	}
	private void insertCustomerRecovery1(Map<String, Object> paramMap) {
		String UserID = paramMap.get("UserID").toString();
		String Opportunity = paramMap.get("OpportunityIDTable").toString();
		QueryWrapper<BOpportunity> wrapper = new QueryWrapper<BOpportunity>();
		wrapper.in("ID", Opportunity);
		BOpportunity unity = baseMapper.selectOne(wrapper);
		Map<String,Object> userName = getSaleUserName(UserID);
		BCustomerfollowup up = new BCustomerfollowup();
		up.setCustomerID(unity.getCustomerID());
		up.setCustomerMobile(unity.getCustomerMobile());
		up.setCustomerName(unity.getCustomerName());
//		up.setOpportunityID(unity.getOpportunitySource());
		up.setFollwUpUserID(UserID);
		up.setFollwUpUserName(userName.get("FollwUpUserName").toString());
		up.setFollwUpUserMobile(userName.get("FollwUpUserMobile").toString());
		up.setFollwUpType("69331990-DBF4-0A2F-80CD-7BC424AA8904");
		up.setFollwUpWay("");
		up.setFollowUpContent("回收客户");
		up.setIntentionLevel("");
		up.setOrgID(paramMap.get("OrgID").toString());
		up.setCreator(UserID);
		up.setCreateTime(new Date());
		up.setIsDel(0);
		up.setStatus(1);
		up.setFollwUpUserRole(paramMap.get("JobID").toString());
		up.setCustomerRank(unity.getCustomerRank());
		up.setProjectID(unity.getProjectID());
		bCustomerfollowupMapper.insert(up);
		up.setOpportunityID(up.getId());
		bCustomerfollowupMapper.updateById(up);
	}
	/**
	 * 客户分配置业顾问
	 */
	@Override
	public void mCustomerAllotAdviser_Update(Map<String,Object> paramMap) {
		baseMapper.mCustomerAllotAdviser_Update(paramMap);
	}
	/**
	 * 自有渠道客户列表
	 */
	@Override
	public Map<String,Object> mCustomerZYQDList_Select(Map<String,Object> paramMap) {
		List<List<?>> result = baseMapper.mCustomerZYQDList_Select(paramMap);
		List<?> list = result.get(0);
		List<?> recordCount = result.get(1);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("List", list);
		map.put("AllCount", recordCount.get(0));
		map.put("PageSize", paramMap.get("PageSize"));
		return map;
	}
	/**
	 * 丢失客户列表
	 */
	@Override
	public Map<String,Object> mCustomerDSList_Select(Map<String,Object> paramMap) {
		List<List<?>> result = baseMapper.mCustomerDSList_Select(paramMap);
		List<?> list = result.get(0);
		List<?> recordCount = result.get(1);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("List", list);
		map.put("AllCount", recordCount.get(0));
		map.put("PageSize", paramMap.get("PageSize"));
		return map;
	}
	/**
	 * 正常跟进列表
	 */
	@Override
	public Map<String,Object> mCustomerGJList_Select(Map<String,Object> paramMap) {
		List<List<?>> result = baseMapper.mCustomerGJList_Select(paramMap);
		List<?> list = result.get(0);
		List<?> recordCount = result.get(1);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("List", list);
		map.put("AllCount", recordCount.get(0));
		map.put("PageSize", paramMap.get("PageSize"));
		return map;
	}
	/**
	 * 撞单客户列表
	 */
	@Override
	public Map<String,Object> mCustomerZDList_Select(Map<String,Object> paramMap) {
		List<List<?>> result = baseMapper.mCustomerZDList_Select(paramMap);
		List<?> list = result.get(0);
		List<?> recordCount = result.get(1);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("List", list);
		map.put("AllCount", recordCount.get(0));
		map.put("PageSize", paramMap.get("PageSize"));
		return map;
	}
	/**
	 * 公共客户列表
	 */
	@Override
	public Map<String,Object> mCustomerGGList_Select(Map<String,Object> paramMap) {
		List<List<?>> result = baseMapper.mCustomerGGList_Select(paramMap);
		List<?> list = result.get(0);
		List<?> recordCount = result.get(1);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("List", list);
		map.put("AllCount", recordCount.get(0));
		map.put("PageSize", paramMap.get("PageSize"));
		return map;
	}
	/**
	 * 逾期客户未交款列表
	 */
	@Override
	public Map<String,Object> mCustomerYQWJKList_Select(Map<String,Object> paramMap) {
		List<List<?>> result = baseMapper.mCustomerYQWJKList_Select(paramMap);
		List<?> list = result.get(0);
		List<?> recordCount = result.get(1);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("List", list);
		map.put("AllCount", recordCount.get(0));
		map.put("PageSize", paramMap.get("PageSize"));
		return map;
	}
	/**
	 * 逾期客户未签约列表
	 */
	@Override
	public Map<String,Object> mCustomerYQWQYList_Select(Map<String,Object> paramMap) {
		List<List<?>> result = baseMapper.mCustomerYQWQYList_Select(paramMap);
		List<?> list = result.get(0);
		List<?> recordCount = result.get(1);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("List", list);
		map.put("AllCount", recordCount.get(0));
		map.put("PageSize", paramMap.get("PageSize"));
		return map;
	}
	/**
	 * 逾期客户未认购列表
	 */
	@Override
	public Map<String,Object> mCustomerYQWRGList_Select(Map<String,Object> paramMap) {
		List<List<?>> result = baseMapper.mCustomerYQWRGList_Select(paramMap);
		List<?> list = result.get(0);
		List<?> recordCount = result.get(1);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("List", list);
		map.put("AllCount", recordCount.get(0));
		map.put("PageSize", paramMap.get("PageSize"));
		return map;
	}
	/**
	 * 逾期客户未跟进列表
	 */
	@Override
	public Map<String,Object> mCustomerYQWGJList_Select(Map<String,Object> paramMap) {
		List<List<?>> result = baseMapper.mCustomerYQWGJList_Select(paramMap);
		List<?> list = result.get(0);
		List<?> recordCount = result.get(1);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("List", list);
		map.put("AllCount", recordCount.get(0));
		map.put("PageSize", paramMap.get("PageSize"));
		return map;
	}
	/**
	 * 客户置业顾问列表
	 */
	@Override
	public Map<String,Object> mCustomerAdviserList_Select(Map<String,Object> paramMap) {
		List<List<?>> result = baseMapper.mCustomerAdviserList_Select(paramMap);
		List<?> list = result.get(0);
		List<?> recordCount = result.get(1);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("List", list);
		map.put("AllCount", recordCount.get(0));
		map.put("PageSize", paramMap.get("PageSize"));
		return map;
	}
	/**
	 * 逾期未签约列表
	 */
	@Override
	public Map<String,Object> R_ACYQWQYList_Select(Map<String,Object> paramMap) {
		List<List<?>> result = baseMapper.R_ACYQWQYList_Select(paramMap);
		List<?> list = result.get(0);
		List<?> recordCount = result.get(1);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("List", list);
		map.put("AllCount", recordCount.get(0));
		map.put("PageSize", paramMap.get("PageSize"));
		return map;
	}
	/**
	 * 逾期未交款列表
	 */
	@Override
	public Map<String,Object> R_ACYQWJKList_Select(Map<String,Object> paramMap) {
		List<List<?>> result = baseMapper.R_ACYQWJKList_Select(paramMap);
		List<?> list = result.get(0);
		List<?> recordCount = result.get(1);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("List", list);
		map.put("AllCount", recordCount.get(0));
		map.put("PageSize", paramMap.get("PageSize"));
		return map;
	}
	
}
