package com.tahoecn.xkc.service.customer.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.customer.BOpportunitygiveupMapper;
import com.tahoecn.xkc.model.customer.BOpportunitygiveup;
import com.tahoecn.xkc.service.customer.IBOpportunitygiveupService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-15
 */
@Service
public class BOpportunitygiveupServiceImpl extends ServiceImpl<BOpportunitygiveupMapper, BOpportunitygiveup> implements IBOpportunitygiveupService {
	
	/**
	 * 机会列表中是否存在丢失中的机会
	 */
	@Override
	public List<Map<String, Object>> GiveUpListStatusFind(Map<String, Object> paramMap) {
		return baseMapper.sOpportunityGiveUpListDetail_Select(paramMap);
	}
	/**
	 * 客户重新分配顾问
	 * 0.已办机会消息 1.变更机会顾问 2.新增销售轨迹 3.删除公共池机会 4.分配待跟进消息提醒
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void mCustomerYXJLAdviser_Update(Map<String, Object> paramMap) {
		//1
		baseMapper.updSMessByOpportunity_Adviser(paramMap);
		//2
		baseMapper.updSMessByOpportunityGiveUp_Adviser(paramMap);
		//3
		baseMapper.insBCustomerFollowUp_Adviser(paramMap);
		//4
		baseMapper.updBOpportunity_Adviser(paramMap);
		//5
		baseMapper.insBCustomerTrack_Adviser(paramMap);
		//6
		baseMapper.updBCustomerPublicPool_Adviser(paramMap);
		//7
		baseMapper.insSMessage_Adviser(paramMap);
		//8
		baseMapper.insSTask(paramMap);
	}
	/**
	 * 协作人置空
	 */
	@Override
	public void mCustomerYXJLSalePartnerSetNull_Update(Map<String, Object> paramMap) {
		baseMapper.mCustomerYXJLSalePartnerSetNull_Update(paramMap);
	}
	/**
	 * 获取SalesUser列表信息
	 */
	@Override
	public IPage<Map<String, Object>> mCustomerYXJLAdviserList_Select(IPage<Map<String, Object>> page, 
			String projectID, String where, String order, String siteUrl) {
		return baseMapper.mCustomerYXJLAdviserList_Select(page, projectID, where, order, siteUrl);
	}
	/**
	 * 获取CustomerPublicPool列表信息
	 */
	@Override
	public IPage<Map<String, Object>> mCustomerYXJLList_Select(IPage<Map<String, Object>> page, 
			String projectID, String where, String order) {
		return baseMapper.mCustomerYXJLList_Select(page, projectID, where, order);
	}
	/**
	 * 客户激活
	 * 1.删除丢失机会 2.新增销售机会 2.新增销售轨迹 3.删除公共池机会 4.分配待跟进消息提醒
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void mCustomerYXJLActive_Update(Map<String, Object> paramMap) {
		//1
		baseMapper.updBOpportunity_Active(paramMap);
		//2
		baseMapper.insBOpportunity_Active(paramMap);
		//3
		baseMapper.callPOpportunityCustomerRank_Active(paramMap);
		//4
		baseMapper.insBCustomerTrack_Active(paramMap);
		//5
		baseMapper.insBOpportunityCustomerRel_Active(paramMap);
		//6
		baseMapper.insSMessage_Active(paramMap);
		//7
		baseMapper.insSTask(paramMap);
	}
	/**
	 * 客户回收
	 * 0.在途丢失申请拒回 0.删除机会消息 1.删除公共池机会 2.增加公共池机会 3.回收消息提醒 4.变更机会顾问
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void mCustomerYXJLRecovery_Update(Map<String, Object> paramMap) {
		//1
		baseMapper.updBOpportunityGiveUp_Recovery(paramMap);
		//2
		baseMapper.updMessByOpportunity_Recovery(paramMap);
		//3
		baseMapper.updMessByOpportunityGiveUp_Recovery(paramMap);
		//4
		baseMapper.updBCustomerPublicPool_Adviser(paramMap);
		//5
		baseMapper.insBCustomerTrack_Recovery(paramMap);
		//6
		baseMapper.insBCustomerPublicPool_Recovery(paramMap);
		//7
		baseMapper.insSMessage_Recovery(paramMap);
		//8
		baseMapper.updBOpportunity_Recovery(paramMap);
		//9
		baseMapper.insSTask(paramMap);
	}
	/**
	 * 案场销售经理客户列表
	 */
	@Override
	public Map<String, Object> mCustomerXSJLList_Select(Map<String, Object> map) {
		Map<String, Object> result = new HashMap<String, Object>();
		if("GJKH".equals(map.get("Type"))){
			result.put("List", baseMapper.mCustomerXSJLList_Select_GJKH(map));
			result.put("AllCount", baseMapper.mCustomerXSJLList_Select_GJKH_count(map));
		}else{
			result.put("List", baseMapper.mCustomerXSJLList_Select(map));
			result.put("AllCount", baseMapper.mCustomerXSJLList_Select_count(map));
		}
		result.put("PageSize", map.get("PageSize"));
		return result;
	}
	/**
	 * 客户丢失销售顾问
	 * 1.驳回 更新审批状态 2.发送消息
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void mCustomerYXJLLoseDetail_Rejected(Map<String, Object> paramMap) {
		//1
		baseMapper.updBOpportunityGiveUp(paramMap);
		//2
		baseMapper.updSMessage(paramMap);
		//3
		baseMapper.insSMessage(paramMap);
	}
	/**
	 * 客户丢失销售顾问
	 * 1.更新审批状态 2.更新机会丢失状态 3.发送丢失审批消息给申请人 4.发送丢失消息给报备人 5.更新线索状态为失效
	 */
	@Override
	public void mCustomerYXJLLoseDetail_Pass(Map<String, Object> paramMap) {
//		UPDATE B_OpportunityGiveUp SET Approver = '{UserID}', ApprovalStatus = '{ApproveState}', ApprovalDate = GETDATE(), Editor = '{UserID}', EditeTime = getdate() WHERE ID IN( SELECT name FROM F_StrToTable('{LostID}') WHERE name <> '') AND ApprovalStatus = 0;
//		UPDATE B_Opportunity SET Status = 6 WHERE ID IN( SELECT OpportunityID FROM dbo.B_OpportunityGiveUp WHERE ID IN( SELECT name FROM F_StrToTable('{LostID}') WHERE name <> ''));
//		UPDATE S_Message SET IsApprove = 1, Editor = '{UserID}', EditTime = GETDATE() WHERE BizType = 'OpportunityGiveUp' AND ISNULL(IsApprove,0) = 0 AND BizID IN( SELECT name FROM F_StrToTable('{LostID}') WHERE name <> '');
//		UPDATE S_Message SET IsApprove = 1, Editor = '{UserID}', EditTime = GETDATE() WHERE BizType = 'Opportunity' AND ISNULL(IsApprove,0) = 0 AND BizID IN( SELECT OpportunityID FROM dbo.B_OpportunityGiveUp WHERE ID IN( SELECT name FROM F_StrToTable('{LostID}') WHERE name <> ''));
//		INSERT INTO S_Message( ID, ProjectID, BizID, BizType, Subject, Content, Sender, SendTime, MessageType, Receiver, IsRead, IsPush, IsNeedPush, Creator, CreateTime, IsDel, Status) SELECT NEWID(), '{ProjectID}', ID, 'OpportunityGiveUp', '客户丢失通知', dbo.F_GetSaleUserName('{UserID}') + '经理已通过' + CustomerName + '丢失审批', '{UserID}', GETDATE(), 'DD81CE6E-C855-F065-DAA2-7D0C80310088', Creator, 0, 0, 1, '{UserID}', GETDATE(), 0, 1 FROM dbo.B_OpportunityGiveUp WHERE ID IN( SELECT name FROM F_StrToTable('{LostID}') WHERE name <> '' );
//		INSERT INTO S_Message( ID, ProjectID, BizID, BizType, Subject, Content, Sender, SendTime, MessageType, Receiver, IsRead, IsPush, IsNeedPush, Creator, CreateTime, IsDel, Status) SELECT NEWID(), '{ProjectID}', l.LostID, 'OpportunityGiveUp', '客户丢失通知', c.Name + '(' + c.Mobile + ') 已丢失', '{UserID}', GETDATE(), 'DD81CE6E-C855-F065-DAA2-7D0C80310088', c.ReportUserID, 0, 0, 1, '{UserID}', GETDATE(), 0, 1 FROM dbo.B_Opportunity o INNER JOIN dbo.B_Clue c ON o.ClueID = c.ID INNER JOIN( SELECT OpportunityID, ID LostID FROM dbo.B_OpportunityGiveUp WHERE ID IN( SELECT name FROM F_StrToTable('{LostID}') WHERE name <> '' ) ) l ON l.OpportunityID = o.ID WHERE c.ReportUserID <> '' AND c.ReportUserID IS NOT NULL;
//		UPDATE dbo.B_Clue SET Status = 3,InvalidType = 10,InvalidTime=GETDATE(),InvalidReason='跟进丢失' WHERE ID IN( SELECT ClueID FROM dbo.B_Opportunity o WHERE o.ID IN( SELECT OpportunityID FROM dbo.B_OpportunityGiveUp WHERE ID IN( SELECT name FROM F_StrToTable('{LostID}') WHERE name <> '')));
//		INSERT INTO B_CustomerTrack SELECT NewID() ID, B.CustomerID, B.CustomerMobile, B.CustomerName, B.OpportunityID, getdate() TrackTime, A.ID TrackUserID, A.Name TrackUserName, A.TelPhone TrackUserMobile, '9956D7FB-6870-5C32-9243-FEC4D5268075' TrackType, A.OrgID, A.ID Creator, getdate() CreateTime, A.ID Editor, getdate() EditeTime, 0 IsDel, 1 Status FROM( SELECT '{UserID}' UserID, K.OpportunityID, P.CustomerID, P.CustomerName, P.CustomerMobile FROM B_OpportunityGiveUp K JOIN B_Opportunity P ON K.OpportunityID = P.ID WHERE K.ID IN( SELECT name FROM F_StrToTable('{LostID}') WHERE name <> '' ) ) B JOIN V_SalesGroupUser A ON A.ID = B.UserID WHERE A.ID = '{UserID}' AND ProjectID = '{ProjectID}';
//		UPDATE B_Clue SET Status = 3 WHERE ID IN( SELECT P.ClueID FROM B_OpportunityGiveUp K JOIN B_Opportunity P ON K.OpportunityID = P.ID WHERE K.ID IN( SELECT name FROM F_StrToTable('{LostID}') WHERE name <> '') AND P.ClueID <> '' AND P.ClueID IS NOT NULL);
//		INSERT INTO dbo.S_Task( ID, TaskType, TaskPara, StartTime, CreateTime) SELECT NEWID() ID, 'SyncOpportunity' TaskType, OpportunityID TaskPara, GETDATE() StartTime, GETDATE() CreateTime FROM dbo.B_OpportunityGiveUp WHERE ID IN( SELECT name FROM F_StrToTable('{LostID}') WHERE name <> '');
		baseMapper.updBOpportunityGiveUp(paramMap);
		baseMapper.updBOpportunity_Pass(paramMap);
		baseMapper.updSMessage(paramMap);
		baseMapper.updSMessage_Pass(paramMap);
		baseMapper.insSMessage_Pass1(paramMap);
		baseMapper.insSMessage_Pass2(paramMap);
		baseMapper.updBClue_Pass1(paramMap);
		baseMapper.insBCustomerTrack_Pass(paramMap);
		baseMapper.updBClue_Pass2(paramMap);
		baseMapper.insSTask_Pass(paramMap);
	}
}
