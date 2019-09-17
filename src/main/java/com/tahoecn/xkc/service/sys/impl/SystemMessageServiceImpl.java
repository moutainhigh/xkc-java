package com.tahoecn.xkc.service.sys.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tahoecn.xkc.common.enums.MessageType;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.mapper.sys.SystemMessageMapper;
import com.tahoecn.xkc.model.vo.UnreadCountVo;
import com.tahoecn.xkc.service.sys.ISystemMessageService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-06-17
 */
@Service
public class SystemMessageServiceImpl implements ISystemMessageService {

    @Autowired
    private SystemMessageMapper systemMessageMapper;

    /**
	 * 未读消息数
	 */
    @Override
    public List<UnreadCountVo> UnreadCountListByMessageType_Select(Map<String, Object> map) {
        return systemMessageMapper.UnreadCountListByMessageType_Select(map);
    }

    /**
     * 消息列表-普通消息
     */
	@Override
	public List<Map<String,Object>> SystemMessageListByMessageType_Select(Map<String, Object> map) {
		return systemMessageMapper.SystemMessageListByMessageType_Select(map);
	}
	
	/**
     * 消息列表-普通消息-总数
     */
	@Override
	public int SystemMessageListByMessageType_SelectCount(Map<String, Object> map) {
		return systemMessageMapper.SystemMessageListByMessageType_SelectCount(map);
	}

	/**
	 * 更新消息信息
	 */
	@Override
	public void updMessage(Map<String, Object> map) {
		systemMessageMapper.updMessage(map);
	}

	/**
	 * 消息列表-渠道任务消息
	 */
	@Override
	public List<Map<String, Object>> ListByMessageTypeChannelTask_Select(Map<String, Object> map) {
		return systemMessageMapper.ListByMessageTypeChannelTask_Select(map);
	}
	
	/**
	 * 消息列表-渠道任务消息-总数
	 */
	@Override
	public int ListByMessageTypeChannelTask_SelectCount(Map<String, Object> map) {
		return systemMessageMapper.ListByMessageTypeChannelTask_SelectCount(map);
	}
	
	/**
	 * 消息列表-机会消息-自渠
	 */
	@Override
	public List<Map<String,Object>> SystemMessageListByMessageTypeOpportunityZQ_Select(Map<String, Object> map){
		return systemMessageMapper.SystemMessageListByMessageTypeOpportunityZQ_Select(map);
	}

	/**
	 * 消息列表-机会消息-自渠-总数
	 */
	@Override
	public int SystemMessageListByMessageTypeOpportunityZQ_SelectCount(Map<String, Object> map){
		return systemMessageMapper.SystemMessageListByMessageTypeOpportunityZQ_SelectCount(map);
	}
	
	/**
	 * 更新消息信息-自渠
	 */
	@Override
	public void updMessageZQ(Map<String, Object> map){
		systemMessageMapper.updMessageZQ(map);
	}

	/**
	 * 消息列表-丢失消息
	 */
	@Override
	public List<Map<String, Object>> ListByMessageTypeKHDS_Select(Map<String, Object> map) {
		return systemMessageMapper.SystemMessageListByMessageTypeKHDS_Select(map);
	}

	/**
	 * 消息列表-丢失消息-总数
	 */
	@Override
	public int ListByMessageTypeKHDS_SelectCount(Map<String, Object> map) {
		return systemMessageMapper.SystemMessageListByMessageTypeKHDS_SelectCount(map);
	}
	
	/**
	 * 顾问客户消息列表
	 */
	@Override
	public List<Map<String,Object>> SystemMessageListByMessageTypeOpportunity_Select(Map<String, Object> map){
		return systemMessageMapper.SystemMessageListByMessageTypeOpportunity_Select(map);
	}

	/**
	 * 顾问客户消息列表-总数
	 */
	@Override
	public int SystemMessageListByMessageTypeOpportunity_SelectCount(Map<String, Object> map){
		return systemMessageMapper.SystemMessageListByMessageTypeOpportunity_SelectCount(map);
	}
	
	/**
	 * 拓客客户消息列表
	 */
	@Override
	public List<Map<String,Object>> SystemMessageListByMessageTypeClue_Select(Map<String, Object> map){
		return systemMessageMapper.SystemMessageListByMessageTypeClue_Select(map);
	}

	/**
	 * 拓客客户消息列表-总数
	 */
	@Override
	public int SystemMessageListByMessageTypeClue_SelectCount(Map<String, Object> map){
		return systemMessageMapper.SystemMessageListByMessageTypeClue_SelectCount(map);
	}
	
	/**
	 * 该项目是否有分享项目信息
	 */
	@Override
	public int IsExistsShareProject(Map<String, Object> map){
		return systemMessageMapper.IsExistsShareProject(map);
	}

	/**
	 * 消息详情
	 */
	@Override
	public List<Map<String, Object>> SystemMessageDetail_Select(Map<String, Object> map) {
		return systemMessageMapper.SystemMessageDetail_Select(map);
	}

	/**
	 * 消息添加
	 */
	@Override
	public void SystemMessageDetail_Insert(Map<String, Object> map) {
		systemMessageMapper.SystemMessageDetail_Insert(map);
	}

	/**
	 * 设消息为已读
	 */
	@Override
	public void SystemMessageReadDetail_Update(Map<String, Object> map) {
		systemMessageMapper.SystemMessageReadDetail_Update(map);
	}

    /**
     * H5消息列表
     */
    @Override
    public IPage<Map<String, Object>> mMessageAllList_Select(IPage page, String userID) {
        IPage<Map<String, Object>> list = systemMessageMapper.mMessageAllList_Select(page, userID);
        for (Map<String, Object> map : list.getRecords()) {
            Object id = map.get("ID");
            systemMessageMapper.mMessageAllList_Updata(id.toString());
        }
        return list;
    }

	@Override
	public Result DetailByHandle_Update(String[] BizIDs, String BizType,int msgHandType) {
		Result re = new Result();
		try {
			String SetStatus = "";
			String Where = "";
			String[] msgType = null;
			String strMessageType = "";
			String strBizID = "";
	        strBizID = String.join("' OR BizID ='", BizIDs);
	        Where += String.format(" AND ( BizID='%s' )", strBizID);
	        switch (msgHandType){
	            case 1:
	                msgType = new String[] {MessageType.待完善首访客户资料.getTypeID(),MessageType.首访信息录入逾期.getTypeID(),MessageType.首访信息录入逾期催办.getTypeID()};
	                strMessageType = String.join("' OR MessageType ='", msgType);
	                Where += String.format(" AND ( MessageType='%s' )", strMessageType);
	                break;
	            case 2:
	                msgType = new String[] {MessageType.分配待跟进.getTypeID(),MessageType.当日跟进逾期.getTypeID(),MessageType.跟进逾期.getTypeID(),MessageType.跟进逾期催办.getTypeID(),MessageType.当日待跟进.getTypeID()};
	                strMessageType = String.join("' OR MessageType ='", msgType);
	                Where += String.format(" AND ( MessageType='%s' )", strMessageType);
	                break;
	            case 3:
	                msgType = new String[] {MessageType.待完善首访客户资料.getTypeID(),MessageType.分配待跟进.getTypeID(),MessageType.当日跟进逾期.getTypeID(),MessageType.跟进逾期.getTypeID(),MessageType.跟进逾期催办.getTypeID(),MessageType.当日待跟进.getTypeID(),MessageType.当日认购逾期.getTypeID(),MessageType.认购逾期.getTypeID(),MessageType.认购逾期催办.getTypeID()};
	                strMessageType = String.join("' OR MessageType ='", msgType);
	                Where += String.format(" AND ( MessageType='%s' )", strMessageType);
	                break;
	            case 4:
	                msgType = new String[] {MessageType.当日签约逾期.getTypeID(),MessageType.签约逾期.getTypeID(),MessageType.签约逾期催办.getTypeID()};
	                strMessageType = String.join("' OR MessageType ='", msgType);
	                Where += String.format(" AND ( MessageType='%s' )", strMessageType);
	                break;
	            case 5:
	                msgType = new String[] {MessageType.当日回款逾期.getTypeID(),MessageType.回款逾期.getTypeID(),MessageType.回款逾期催办.getTypeID()};
	                strMessageType = String.join("' OR MessageType ='", msgType);
	                Where += String.format(" AND ( MessageType='%s' )", strMessageType);
	                break;
	            case 6:
	                SetStatus = ",Sataus=0";
	                Where += "";
	                break;
	            case 7:
	                Where += "";
	                break;
	            default:
	                break;
	        }
	        systemMessageMapper.SystemMessageDetailByHandle_Update(BizType, SetStatus, Where);
	        re.setErrmsg("成功");
	        re.setErrcode(0);
		} catch (Exception e) {
			re.setErrmsg("失败");
	        re.setErrcode(9);
			e.printStackTrace();
		}
        return re;
	}
	/**
	 * 设置动态消息已读
	 */
	@Override
	public void mMessageDynamicReadList_Update(Map<String, Object> map){
		systemMessageMapper.mMessageDynamicReadList_Update(map);
	}
	
	@Override
	public Result Detail_Insert(String UserID, String ProjectID, String BizID,
			String BizType, String Subject, String Content, String Receiver,
			String msgType, Boolean IsNeedPush) {
		Result re = new Result();
		try {
			Map<String,Object> Parameter = new HashMap<String, Object>();
	        Parameter.put("ProjectID", ProjectID);
	        Parameter.put("BizID", BizID);
	        Parameter.put("BizType", BizType);
	        Parameter.put("Subject", Subject);
	        Parameter.put("Content", Content);
	        Parameter.put("Receiver", Receiver);
	        Parameter.put("MessageType", msgType);
	        Parameter.put("Sender", UserID);
	        Parameter.put("Creator", UserID);
	        Parameter.put("IsNeedPush", IsNeedPush ? 1 : 0);
	        systemMessageMapper.SystemMessageDetail_Insert(Parameter);
	        re.setErrmsg("成功");
	        re.setErrcode(0);
		} catch (Exception e) {
			re.setErrmsg("系统异常");
	        re.setErrcode(9);
			e.printStackTrace();
		}
        return re;
	}
	/**
	 * 今日待跟进列表
	 */
	@Override
	public List<Map<String, Object>> ListDRDGJ_Select(Map<String, Object> map) {
		return systemMessageMapper.ListDRDGJ_Select(map);
	}
	/**
	 * 今日待跟进列表-总数
	 */
	@Override
	public int ListDRDGJ_SelectCount(Map<String, Object> map) {
		return systemMessageMapper.ListDRDGJ_SelectCount(map);
	}
	/**
	 * 当日认购逾期列表
	 */
	@Override
	public List<Map<String, Object>> ListDRRGYQ_Select(Map<String, Object> map) {
		return systemMessageMapper.ListDRRGYQ_Select(map);
	}
	/**
	 * 当日认购逾期列表-总数
	 */
	@Override
	public int ListDRRGYQ_SelectCount(Map<String, Object> map) {
		return systemMessageMapper.ListDRRGYQ_SelectCount(map);
	}
	/**
     * 当日签约逾期
     */
	@Override
	public List<Map<String, Object>> ListDRQYYQ_Select(Map<String, Object> map) {
		return systemMessageMapper.ListDRQYYQ_Select(map);
	}
	/**
     * 当日签约逾期-总数
     */
	@Override
	public int ListDRQYYQ_SelectCount(Map<String, Object> map) {
		return systemMessageMapper.ListDRQYYQ_SelectCount(map);
	}
	/**
     * 当日回款逾期
     */
	@Override
	public List<Map<String, Object>> ListDRHKYQ_Select(Map<String, Object> map) {
		return systemMessageMapper.ListDRHKYQ_Select(map);
	}
	/**
     * 当日回款逾期-总数
     */
	@Override
	public int ListDRHKYQ_SelectCount(Map<String, Object> map) {
		return systemMessageMapper.ListDRHKYQ_SelectCount(map);
	}
	/**
	 * 当日跟进逾期-销售团队负责人/营销负责人
	 */
	@Override
	public List<Map<String, Object>> ListDRGJYQOpportunity_Select(Map<String, Object> map) {
		return systemMessageMapper.ListDRGJYQOpportunity_Select(map);
	}
	/**
	 * 当日跟进逾期-销售团队负责人/营销负责人-总数
	 */
	@Override
	public int ListDRGJYQOpportunity_SelectCount(Map<String, Object> map) {
		return systemMessageMapper.ListDRGJYQOpportunity_SelectCount(map);
	}
	/**
	 * 当日跟进逾期-其他
	 */
	@Override
	public List<Map<String, Object>> ListDRGJYQClue_Select(Map<String, Object> map) {
		return systemMessageMapper.ListDRGJYQClue_Select(map);
	}
	/**
	 * 当日跟进逾期-其他-总数
	 */
	@Override
	public int ListDRGJYQClue_SelectCount(Map<String, Object> map) {
		return systemMessageMapper.ListDRGJYQClue_SelectCount(map);
	}
	/**
	 * 消息日历
	 */
	@Override
	public Map<String,Object> mMessageCalendar_Select(Map<String, Object> paramMap) {
		String JobCode = (String)paramMap.get("JobCode");//岗位代码
		Map<String,Object> map = new HashMap<String,Object>();
		/*map.put(MessageType.当日待跟进.getTypeID(), systemMessageMapper.DRDGJCalendar(paramMap));
		if("GW".equals(paramMap.get("JobCode"))){
			map.put(MessageType.当日跟进逾期.getTypeID(), systemMessageMapper.DRGJYQOpportunityCalendar(paramMap));
		}else{
			String JobCode = (String) paramMap.get("JobCode");
			String sqlWhere = "";
			if(!"ZQFZR".equals(JobCode)){//ZQ
				sqlWhere += " AND o.SaleUserID = '" + map.get("UserID") + "' ";
			}else{//ZQFZR
				sqlWhere += " AND EXISTS(SELECT id FROM dbo.B_SalesGroupMember "
						+ "WHERE ProjectID='" + map.get("ProjectID") + "'  AND IsDel=0 AND Status=1 AND MemberID=Receiver "
						+ "AND RoleID IN('48FC928F-6EB5-4735-BF2B-29B1F591A582', '9584A4B7-F105-44BA-928D-F2FBA2F3B4A4', 'B0BF5636-94AD-4814-BB67-9C1873566F29', 'WSD12580-003A-6505-B211-C2C2C2C2C2C2'))";
			}
			paramMap.put("sqlWhere", sqlWhere);
			map.put(MessageType.当日跟进逾期.getTypeID(), systemMessageMapper.DRGJYQClueCalendar(paramMap));
		}
		map.put(MessageType.当日认购逾期.getTypeID(), systemMessageMapper.DRRGYQCalendar(paramMap));
		map.put(MessageType.当日签约逾期.getTypeID(), systemMessageMapper.DRQYYQCalendar(paramMap));
		map.put(MessageType.当日回款逾期.getTypeID(), systemMessageMapper.DRHKYQCalendar(paramMap));
		return map;*/
		List<String> DRDGJ = systemMessageMapper.DRDGJCalendar(paramMap);
		List<String> DRGJYQOpportunity = systemMessageMapper.DRGJYQOpportunityCalendar(paramMap);
		String sqlWhere = "";
		if(!"ZQFZR".equals(JobCode)){//ZQ
			sqlWhere += " AND o.SaleUserID = '" + map.get("UserID") + "' ";
		}else{//ZQFZR
			sqlWhere += " AND EXISTS(SELECT id FROM dbo.B_SalesGroupMember "
					+ "WHERE ProjectID='" + map.get("ProjectID") + "'  AND IsDel=0 AND Status=1 AND MemberID=o.SaleUserID "
					+ "AND RoleID IN('48FC928F-6EB5-4735-BF2B-29B1F591A582', '9584A4B7-F105-44BA-928D-F2FBA2F3B4A4', 'B0BF5636-94AD-4814-BB67-9C1873566F29', 'WSD12580-003A-6505-B211-C2C2C2C2C2C2'))";
		}
		paramMap.put("sqlWhere", sqlWhere);
		List<String> DRGJYQClue = systemMessageMapper.DRGJYQClueCalendar(paramMap);
		List<String> DRRGYQ = systemMessageMapper.DRRGYQCalendar(paramMap);
		List<String> DRQYYQ = systemMessageMapper.DRQYYQCalendar(paramMap);
		List<String> DRHKYQ = systemMessageMapper.DRHKYQCalendar(paramMap);
		int count = 0;
		switch (JobCode.toUpperCase()){
		case "GW":
			map.put(MessageType.当日待跟进.getTypeID(), DRDGJ);
			map.put(MessageType.当日跟进逾期.getTypeID(), DRGJYQOpportunity);
			map.put(MessageType.当日认购逾期.getTypeID(), DRRGYQ);
			map.put(MessageType.当日签约逾期.getTypeID(), DRQYYQ);
			map.put(MessageType.当日回款逾期.getTypeID(), DRHKYQ);
			count = DRDGJ.size() + DRGJYQOpportunity.size() + DRRGYQ.size() + DRQYYQ.size() + DRHKYQ.size();
			break;
		case "XSFZR":
			map.put(MessageType.当日认购逾期.getTypeID(), DRRGYQ);
			map.put(MessageType.当日签约逾期.getTypeID(), DRQYYQ);
			map.put(MessageType.当日回款逾期.getTypeID(), DRHKYQ);
			count = DRRGYQ.size() + DRQYYQ.size() + DRHKYQ.size();
			break;
		case "YXJL":
			map.put(MessageType.当日认购逾期.getTypeID(), DRRGYQ);
			map.put(MessageType.当日签约逾期.getTypeID(), DRQYYQ);
			map.put(MessageType.当日回款逾期.getTypeID(), DRHKYQ);
			count = DRRGYQ.size() + DRQYYQ.size() + DRHKYQ.size();
			break;
		case "XSJL":
			map.put(MessageType.当日认购逾期.getTypeID(), DRRGYQ);
			map.put(MessageType.当日签约逾期.getTypeID(), DRQYYQ);
			map.put(MessageType.当日回款逾期.getTypeID(), DRHKYQ);
			count = DRRGYQ.size() + DRQYYQ.size() + DRHKYQ.size();
			break;
		case "ZQFZR"://当日跟进逾期、带看通知、认筹通知、认购通知、签约通知、退房通知、无效通知
			map.put(MessageType.当日跟进逾期.getTypeID(), DRGJYQClue);
			count = DRGJYQClue.size();
			break;
		case "ZQ"://当日跟进逾期、带看通知、认筹通知、认购通知、签约通知、退房通知、无效通知
			map.put(MessageType.当日跟进逾期.getTypeID(), DRGJYQClue);
			count = DRGJYQClue.size();
			break;
		}
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("result", map);
		result.put("count", count);
 		return result;
	}
}
