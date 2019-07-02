package com.tahoecn.xkc.controller.app;


import com.alibaba.fastjson.JSONObject;
import com.tahoecn.xkc.common.enums.MessageType;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.ResponseMessage;
import com.tahoecn.xkc.service.sys.ISystemMessageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YYY
 * @since 2019-06-25
 */
@RestController
@Api(tags = "APP-消息", value = "APP-消息")
@RequestMapping("/app/message")
public class MessageAppController extends TahoeBaseController {

    @Autowired
    private ISystemMessageService iSystemMessageService;

    @ResponseBody
    @ApiOperation(value = "未读消息数接口", notes = "未读消息数接口")
    @RequestMapping(value = "/mMessageUnreadCount_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage mMessageUnreadCount_Select(@RequestBody JSONObject jsonParam) {
        // 直接将json信息打印出来
        System.out.println(jsonParam.toJSONString());
        Map paramMap = (HashMap)jsonParam.get("_param");
        String jobCode = (String)paramMap.get("JobCode");
        String userId = (String)paramMap.get("UserID");
        String typeCode = (String)paramMap.get("TypeCode");
        String projectId = (String)paramMap.get("ProjectID");

        List unreadCountList = iSystemMessageService.UnreadCountListByMessageType_Select(projectId,userId);

        return ResponseMessage.ok(unreadCountList);
    }
    
    @ResponseBody
    @ApiOperation(value = "消息列表", notes = "消息列表")
    @RequestMapping(value = "/mMessageList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage mMessageList_Select(@RequestBody JSONObject jsonParam) {
    	
    	try{
	    	// 直接将json信息打印出来
	    	System.out.println(jsonParam.toJSONString());
	    	Map paramMap = (HashMap)jsonParam.get("_param");
	    	String UserID = (String)paramMap.get("UserID");
	    	String ProjectID = (String)paramMap.get("ProjectID");
	    	String TypeID = (String)paramMap.get("TypeID");
	    	String PageIndex = (String)paramMap.get("PageIndex");
	    	String PageSize = (String)paramMap.get("PageSize");
	    	String IsRead = (String)paramMap.get("IsRead");
	    	String IsApprove = (String)paramMap.get("IsApprove");
	    	String JobCode = (String)paramMap.get("JobCode");
	    	
	    	Map<String,Object> map = new HashMap<String,Object>();
	    	map.put("UserID", UserID);
	    	map.put("ProjectID", ProjectID);
	    	map.put("MessageType", TypeID);
	    	map.put("PageIndex", PageIndex);
	    	map.put("PageSize", PageSize);
	    	map.put("IsRead", IsRead);
	    	map.put("IsApprove", IsApprove);
	    	map.put("JobCode", JobCode);
	    	
	    	Map<String,Object> result = new HashMap<String,Object>();
	    	if(MessageType.系统通知.getTypeID().equals(TypeID)){
	    		result.put("EmptyHandleMsg", "暂无通知");
	    		result.put("EmptyHandleIconType", 0);
	    		result.put("EmptyUnHandleMsg", "暂无通知");
	    		result.put("EmptyUnHandleIconType", 0);
	    		map.put("MessageType", TypeID);
	    		result = ListByMessageType_Select(result,map);
//	    		map.put("sqlWhere", "AND MessageType = #{MessageType}");
//	    		result.put("List", iSystemMessageService.SystemMessageListByMessageType_Select(map));
//	    		result.put("AllCount", iSystemMessageService.SystemMessageListByMessageType_SelectCount(map));
//	    		//更新操作
//	    		iSystemMessageService.updMessage(map);
	    	}
	    	if(MessageType.渠道任务通知.getTypeID().equals(TypeID)){
	    		result.put("EmptyHandleMsg", "暂无通知");
	    		result.put("EmptyHandleIconType", 0);
	    		result.put("EmptyUnHandleMsg", "暂无通知");
	    		result.put("EmptyUnHandleIconType", 0);
	    		map.put("sqlWhere", "AND A.MessageType = #{MessageType} AND A.IsRead =  #{IsRead}");
	    		//列表
	    		result.put("List", iSystemMessageService.ListByMessageTypeChannelTask_Select(map));
	    		//总数
	    		result.put("AllCount", iSystemMessageService.ListByMessageTypeChannelTask_SelectCount(map));
	    		//更新操作
	    		iSystemMessageService.updMessage(map);
	    	}
	    	if(MessageType.带看通知.getTypeID().equals(TypeID)
	    			|| MessageType.认筹通知.getTypeID().equals(TypeID)
	    			|| MessageType.认购通知.getTypeID().equals(TypeID)
	    			|| MessageType.签约通知.getTypeID().equals(TypeID)
	    			|| MessageType.退房通知.getTypeID().equals(TypeID)
	    			|| MessageType.无效通知.getTypeID().equals(TypeID)){
	    		result.put("EmptyHandleMsg", "暂无通知");
	    		result.put("EmptyHandleIconType", 1);
	    		result.put("EmptyUnHandleMsg", "暂无通知");
	    		result.put("EmptyUnHandleIconType", 1);
	    		result = ListByMessageTypeOpportunityZQ_Select(result,map);
	    	}
	    	if(MessageType.客户丢失通知.getTypeID().equals(TypeID)){
	    		result.put("EmptyHandleMsg", "您的团队与客户沟通的很棒，请鼓励他们。");
	    		result.put("EmptyHandleIconType", 2);
	    		result.put("EmptyUnHandleMsg", "请及时关注丢失申请，提高客户转换率。");
	    		result.put("EmptyUnHandleIconType", 2);
	    		map.put("sqlWhere", "AND A.MessageType = #{MessageType} AND A.IsRead =  #{IsRead}");
	    		//列表
	    		result.put("List", iSystemMessageService.ListByMessageTypeKHDS_Select(map));
	    		//总数
	    		result.put("AllCount", iSystemMessageService.ListByMessageTypeKHDS_SelectCount(map));
	    		//更新操作
	    		iSystemMessageService.updMessageZQ(map);
	    	}
	    	if(MessageType.今日待跟进.getTypeID().equals(TypeID)
	    			|| MessageType.待完善首访客户资料.getTypeID().equals(TypeID)){
	    		result.put("EmptyHandleMsg", "今日的任务全部完成了，你真棒。");
	    		result.put("EmptyHandleIconType", 3);
	    		result.put("EmptyUnHandleMsg", "今日的任务全部完成了，你真棒");
	    		result.put("EmptyUnHandleIconType", 3);
	    		result = ListByMessageTypeOpportunity_Select(result,map);
	    	}
	    	if(MessageType.首访信息录入逾期.getTypeID().equals(TypeID)){
	    		result.put("EmptyHandleMsg", "暂无首访信息录入逾期的客户，请继续保持");
	    		result.put("EmptyHandleIconType", 3);
	    		result.put("EmptyUnHandleMsg", "暂无首访信息录入逾期的客户，请继续保持");
	    		result.put("EmptyUnHandleIconType", 3);
	    		result = ListByMessageTypeOpportunity_Select(result,map);
	    	}
	    	if(MessageType.跟进即将逾期.getTypeID().equals(TypeID)){
	    		result.put("EmptyHandleMsg", "暂无列表信息，请及时处理即将逾期的客户。");
	    		result.put("EmptyHandleIconType", 2);
	    		result.put("EmptyUnHandleMsg", "暂无跟进即将逾期的客户，请继续保持和客户的沟通频次。");
	    		result.put("EmptyUnHandleIconType", 1);
	    		if("GW".equals(JobCode) || "YXJL".equals(JobCode)){
	    			result = ListByMessageTypeOpportunity_Select(result,map);
	    		}else{
	    			result = ListByMessageTypeClue_Select(result,map);
	    		}
	    	}
	    	if(MessageType.认购即将逾期.getTypeID().equals(TypeID)){
	    		result.put("EmptyHandleMsg", "暂无列表信息，请及时处即将理逾期的客户。");
	    		result.put("EmptyHandleIconType", 2);
	    		result.put("EmptyUnHandleMsg", "暂无认购即将逾期的客户，请注意认购时间。");
	    		result.put("EmptyUnHandleIconType", 2);
	    		result = ListByMessageTypeOpportunity_Select(result,map);
	    	}
	    	if(MessageType.签约即将逾期.getTypeID().equals(TypeID)){
	    		result.put("EmptyHandleMsg", "暂无列表信息，请及时处理即将逾期的客户。");
	    		result.put("EmptyHandleIconType", 2);
	    		result.put("EmptyUnHandleMsg", "暂无签约即将逾期的客户，请注意签约时间。");
	    		result.put("EmptyUnHandleIconType", 2);
	    		result = ListByMessageTypeOpportunity_Select(result,map);
	    	}
	    	if(MessageType.回款即将逾期.getTypeID().equals(TypeID)){
	    		result.put("EmptyHandleMsg", "暂无列表信息，请及时处理即将逾期的客户。");
	    		result.put("EmptyHandleIconType", 2);
	    		result.put("EmptyUnHandleMsg", "暂无回款即将逾期的客户，请注意回款时间。");
	    		result.put("EmptyUnHandleIconType", 2);
	    		result = ListByMessageTypeOpportunity_Select(result,map);
	    	}
	    	if(MessageType.回收提醒.getTypeID().equals(TypeID)){
	    		result.put("EmptyHandleMsg", "暂无通知");
	    		result.put("EmptyHandleIconType", 0);
	    		result.put("EmptyUnHandleMsg", "暂无通知");
	    		result.put("EmptyUnHandleIconType", 0);
	    	}
	    	if(MessageType.到访提醒.getTypeID().equals(TypeID)){
	    		result.put("EmptyHandleMsg", "暂无通知");
	    		result.put("EmptyHandleIconType", 0);
	    		result.put("EmptyUnHandleMsg", "暂无通知");
	    		result.put("EmptyUnHandleIconType", 0);
	    		result = ListByMessageTypeOpportunity_Select(result,map);
	    	}
	    	if(MessageType.跟进逾期.getTypeID().equals(TypeID)){
	    		if("YXJL".equals(JobCode)){
	    			result.put("EmptyHandleMsg", "暂无列表信息，请敦促您的团队及时处理逾期的客户。");
	    			result.put("EmptyHandleIconType", 2);
	    			result.put("EmptyUnHandleMsg", "您的团队太棒了，暂无跟进逾期的情况。");
	    			result.put("EmptyUnHandleIconType", 3);
	    		}else{
	    			result.put("EmptyHandleMsg", "暂无列表信息，请及时处理逾期的客户。");
	    			result.put("EmptyHandleIconType", 2);
	    			result.put("EmptyUnHandleMsg", "暂无跟进逾期的客户，请继续保持。");
	    			result.put("EmptyUnHandleIconType", 3);
	    		}
	    		result = ListByMessageTypeOpportunity_Select(result,map);
	    	}
	    	if(MessageType.认购逾期.getTypeID().equals(TypeID)){
	    		if("YXJL".equals(JobCode)){
	    			result.put("EmptyHandleMsg", "暂无列表信息，请敦促您的团队及时处理逾期的客户。");
	    			result.put("EmptyHandleIconType", 2);
	    			result.put("EmptyUnHandleMsg", "您的团队太棒了，暂无认购逾期的情况。");
	    			result.put("EmptyUnHandleIconType", 3);
	    		}else{
	    			result.put("EmptyHandleMsg", "暂无列表信息，请及时处理逾期的客户。");
	    			result.put("EmptyHandleIconType", 2);
	    			result.put("EmptyUnHandleMsg", "暂无认购逾期的客户，请注意认购时间。");
	    			result.put("EmptyUnHandleIconType", 2);
	    		}
	    		result = ListByMessageTypeOpportunity_Select(result,map);
	    	}
	    	if(MessageType.签约逾期.getTypeID().equals(TypeID)){
	    		if("YXJL".equals(JobCode)){
	    			result.put("EmptyHandleMsg", "暂无列表信息，请敦促您的团队及时处理逾期的客户。");
	    			result.put("EmptyHandleIconType", 2);
	    			result.put("EmptyUnHandleMsg", "您的团队太棒了，暂无签约逾期的情况。");
	    			result.put("EmptyUnHandleIconType", 3);
	    		}else{
	    			result.put("EmptyHandleMsg", "暂无列表信息，请及时处理逾期的客户。");
	    			result.put("EmptyHandleIconType", 2);
	    			result.put("EmptyUnHandleMsg", "暂无签约逾期的客户，请注意签约时间。");
	    			result.put("EmptyUnHandleIconType", 2);
	    		}
	    		result = ListByMessageTypeOpportunity_Select(result,map);
	    	}
	    	if(MessageType.回款逾期.getTypeID().equals(TypeID)){
	    		if("YXJL".equals(JobCode)){
	    			result.put("EmptyHandleMsg", "暂无列表信息，请敦促您的团队及时处理逾期的客户。");
	    			result.put("EmptyHandleIconType", 2);
	    			result.put("EmptyUnHandleMsg", "您的团队太棒了，暂无回款逾期的情况。");
	    			result.put("EmptyUnHandleIconType", 3);
	    		}else{
	    			result.put("EmptyHandleMsg", "暂无列表信息，请及时处理逾期的客户。");
	    			result.put("EmptyHandleIconType", 2);
	    			result.put("EmptyUnHandleMsg", "暂无回款逾期的客户，请注意回款时间。");
	    			result.put("EmptyUnHandleIconType", 2);
	    		}
	    		result = ListByMessageTypeOpportunity_Select(result,map);
	    	}
	    	if(MessageType.分配待跟进.getTypeID().equals(TypeID)){
	    		result.put("EmptyHandleMsg", "暂无待办任务，努力去挖掘客户资源");
	    		result.put("EmptyHandleIconType", 1);
	    		result.put("EmptyUnHandleMsg", "暂无待办任务，努力去挖掘客户资源");
	    		result.put("EmptyUnHandleIconType", 1);
	    		result = ListByMessageTypeOpportunity_Select(result,map);
	    	}
	    	if(MessageType.客户即将失效.getTypeID().equals(TypeID)
	    			|| MessageType.客户失效通知.getTypeID().equals(TypeID)
	    			|| MessageType.到访即将逾期.getTypeID().equals(TypeID)
	    			|| MessageType.成交即将逾期.getTypeID().equals(TypeID)){
	    		result.put("EmptyHandleMsg", "暂无通知");
	    		result.put("EmptyHandleIconType", 0);
	    		result.put("EmptyUnHandleMsg", "暂无通知");
	    		result.put("EmptyUnHandleIconType", 0);
	    		result = ListByMessageTypeClue_Select(result,map);
	    	}
	    	if(MessageType.四类消息通知.getTypeID().equals(TypeID)){
	    		String[] msgType = null;
	    		if("GW".equals(JobCode)){
	    			msgType = new String[]{MessageType.到访提醒.getTypeID(),
	    					MessageType.客户丢失通知.getTypeID(),
	    					MessageType.回收提醒.getTypeID(),
	    					MessageType.系统通知.getTypeID()};
	    		}else{
	    			msgType = new String[]{MessageType.回收提醒.getTypeID(),
	    					MessageType.系统通知.getTypeID()};
	    		}
	    		result.put("EmptyHandleMsg", "暂无通知");
	    		result.put("EmptyHandleIconType", 0);
	    		result.put("EmptyUnHandleMsg", "暂无通知");
	    		result.put("EmptyUnHandleIconType", 0);
	    		map.put("MessageType", String.join("' OR MessageType ='", msgType));
	    		result = ListByMessageType_Select(result,map);
	    	}
	    	if(MessageType.催办.getTypeID().equals(TypeID)){
	    		String[] msgType = new String[]{MessageType.跟进逾期催办.getTypeID(),
	    					MessageType.认购逾期催办.getTypeID(),
	    					MessageType.签约逾期催办.getTypeID(),
	    					MessageType.回款逾期催办.getTypeID()};
	    		result.put("EmptyHandleMsg", "暂无催办事项，请及时关注。");
	    		result.put("EmptyHandleIconType", 2);
	    		result.put("EmptyUnHandleMsg", "暂无催办事项，请及时关注。");
	    		result.put("EmptyUnHandleIconType", 2);
	    		map.put("MessageType", String.join("' OR MessageType ='", msgType));
	    		result = ListByMessageTypeOpportunity_Select(result,map);
	    	}
	    	int count = iSystemMessageService.IsExistsShareProject(map);
	    	if(count == 1){
	    		if(MessageType.预约客户.getTypeID().equals(TypeID)){
	    			result.put("EmptyHandleMsg", "暂无待办任务，努力去挖掘客户资源");
	    			result.put("EmptyHandleIconType", 1);
	    			result.put("EmptyUnHandleMsg", "暂无待办任务，努力去挖掘客户资源");
	    			result.put("EmptyUnHandleIconType", 1);
	    			result = ListByMessageTypeOpportunity_Select(result,map);
	    		}
	    	}
	    	return ResponseMessage.ok(result);
    	}catch(Exception e){
    		e.printStackTrace();
    		return ResponseMessage.error("系统异常，请联系管理员");
    	}
    }

    /**
     * 消息列表-普通消息
     * @param result
     * @param map
     * @return
     */
    private Map<String, Object> ListByMessageType_Select(Map<String, Object> result, Map<String, Object> map) {
    	String sqlWhere = "";
    	sqlWhere = " AND ( MessageType = #{MessageType} )";
    	map.put("sqlWhere", sqlWhere);
    	//列表
    	result.put("List", iSystemMessageService.SystemMessageListByMessageType_Select(map));
    	//总数
    	result.put("AllCount", iSystemMessageService.SystemMessageListByMessageType_SelectCount(map));
		//更新操作
		iSystemMessageService.updMessage(map);
		return result;
	}

	/**
     * 消息列表-线索消息
     */
    private Map<String, Object> ListByMessageTypeClue_Select(Map<String, Object> result, Map<String, Object> map) {
    	String IsApprove = (String) map.get("IsApprove");
		String IsRead = (String) map.get("IsRead");
		String JobCode = (String) map.get("JobCode");
		
		String sqlWhere = "";
		if(IsApprove != null && !"".equals(IsApprove)){
			sqlWhere = " AND ISNULL(IsApprove,0) = #{IsApprove}";
		}
		if(IsRead != null && !"".equals(IsRead)){
			sqlWhere = " AND ISNULL(IsRead,0) = #{IsRead}";
		}
		sqlWhere += " AND ( MessageType = #{MessageType} )";
		if(!"ZQFZR".equals(JobCode)){
			sqlWhere += " AND Receiver = #{UserID}";
		}else{
			sqlWhere += " AND EXISTS(SELECT id FROM dbo.B_SalesGroupMember "
					+ "WHERE ProjectID='{0}' AND IsDel=0 AND Status=1 AND MemberID=Receiver "
					+ "AND RoleID IN('48FC928F-6EB5-4735-BF2B-29B1F591A582', '9584A4B7-F105-44BA-928D-F2FBA2F3B4A4', 'B0BF5636-94AD-4814-BB67-9C1873566F29'))";
		}
		map.put("sqlWhere", sqlWhere);
		//列表
		result.put("List", iSystemMessageService.SystemMessageListByMessageTypeClue_Select(map));
		//总数
		result.put("AllCount", iSystemMessageService.SystemMessageListByMessageTypeClue_SelectCount(map));
		//更新操作
		iSystemMessageService.updMessageZQ(map);
		return result;
	}

	/**
     * 消息列表-机会消息
     */
    private Map<String, Object> ListByMessageTypeOpportunity_Select(Map<String, Object> result,
			Map<String, Object> map) {
    	String IsApprove = (String) map.get("IsApprove");
		String IsRead = (String) map.get("IsRead");
		String sqlWhere = "";
		if(IsApprove != null && !"".equals(IsApprove)){
			sqlWhere = " AND ISNULL(IsApprove,0) = #{IsApprove}";
		}
		if(IsRead != null && !"".equals(IsRead)){
			sqlWhere = " AND ISNULL(IsRead,0) = #{IsRead}";
		}
		sqlWhere += " AND ( MessageType = #{MessageType} )";
		
		
		map.put("sqlWhere", sqlWhere);
		//列表
		result.put("List", iSystemMessageService.SystemMessageListByMessageTypeOpportunity_Select(map));
		//总数
		result.put("AllCount", iSystemMessageService.SystemMessageListByMessageTypeOpportunity_SelectCount(map));
		//更新操作
		iSystemMessageService.updMessageZQ(map);
		return result;
	}

	/**
     * 消息列表-机会消息-自渠
     */
	private Map<String, Object> ListByMessageTypeOpportunityZQ_Select(Map<String, Object> result,
			Map<String, Object> map) {
		
		String IsApprove = (String) map.get("IsApprove");
		String IsRead = (String) map.get("IsRead");
		String JobCode = (String) map.get("JobCode");
		
		String sqlWhere = "";
		if(IsApprove != null && !"".equals(IsApprove)){
			sqlWhere = " AND ISNULL(IsApprove,0) = #{IsApprove}";
		}
		if(IsRead != null && !"".equals(IsRead)){
			sqlWhere = " AND ISNULL(IsRead,0) = #{IsRead}";
		}
		sqlWhere += " AND ( MessageType = #{MessageType} )";
		if(!"ZQFZR".equals(JobCode)){
			sqlWhere += " AND Receiver = #{UserID}";
		}else{
			sqlWhere += " AND EXISTS(SELECT id FROM dbo.B_SalesGroupMember "
					+ "WHERE ProjectID = #{ProjectID} AND IsDel=0 AND Status=1 AND MemberID=Receiver "
					+ "AND RoleID IN('48FC928F-6EB5-4735-BF2B-29B1F591A582', '9584A4B7-F105-44BA-928D-F2FBA2F3B4A4', 'B0BF5636-94AD-4814-BB67-9C1873566F29'))";
		}
		
		map.put("sqlWhere", sqlWhere);
		//列表
		result.put("List", iSystemMessageService.SystemMessageListByMessageTypeOpportunityZQ_Select(map));
		//总数
		result.put("AllCount", iSystemMessageService.SystemMessageListByMessageTypeOpportunityZQ_SelectCount(map));
		//更新操作
		iSystemMessageService.updMessageZQ(map);
		return result;
	}
}
