package com.tahoecn.xkc.controller.app;


import com.alibaba.fastjson.JSONObject;
import com.tahoecn.xkc.common.enums.MessageType;
import com.tahoecn.xkc.common.utils.ArrayUtil;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.ResponseMessage;
import com.tahoecn.xkc.model.vo.UnreadCountVo;
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
    	try{
    		Map paramMap = (HashMap)jsonParam.get("_param");
    		String ProjectID = (String)paramMap.get("ProjectID");//项目id
    		String UserID = (String)paramMap.get("UserID");//用户id
    		String JobCode = (String)paramMap.get("JobCode");//岗位代码
    		String TypeCode = (String)paramMap.get("TypeCode");//页签代码
    		/*GJ(置业顾问--跟进):今日待跟进,到访提醒,跟进将逾期
    		YQ(置业顾问--逾期):跟进即将逾期,认购即将逾期,签约即将逾期,回款即将逾期
    		XX(置业顾问--消息):丢失审批通知,客户被回收通知,系统通知,到访通知
    		CB(置业顾问--催办):跟进逾期催办,认购逾期催办,签约逾期催办,回款逾期催办
    		YJ(营销经理--预警):跟进逾期,认购逾期,签约逾期,回款逾期
    		DB(营销经理--待办):客户丢失通知,回收提醒
    		ZQ(自渠):带看通知，认筹通知，认购通知，签约通知，退房通知，无效通知*/
    		
    		Map<String,Object> map = new HashMap<String,Object>();
	    	map.put("ProjectID", ProjectID);
	    	map.put("UserID", UserID);
	    	map.put("JobCode", JobCode);
	    	map.put("TypeCode", TypeCode);
	    	//1.该项目是否有分享项目信息
    		int count = iSystemMessageService.IsExistsShareProject(map);
    		//2.统计消息类型id
    		String[] msgType = null;
    		switch (JobCode.toUpperCase()){
    		case "FJ":
    			msgType = new String[]{MessageType.系统通知.getTypeID()};
    			break;
    		case "GW":
    			switch(TypeCode.toUpperCase()){
    			case "GJ":
    				if(count == 0){
    					msgType = new String[]{MessageType.今日待跟进.getTypeID(),
                                MessageType.分配待跟进.getTypeID(),
                                MessageType.跟进即将逾期.getTypeID(),
                                MessageType.认购即将逾期.getTypeID(),
                                MessageType.签约即将逾期.getTypeID(),
                                MessageType.回款即将逾期.getTypeID()};
    				}else{
    					msgType = new String[]{MessageType.今日待跟进.getTypeID(),
                                MessageType.分配待跟进.getTypeID(),
                                MessageType.跟进即将逾期.getTypeID(),
                                MessageType.认购即将逾期.getTypeID(),
                                MessageType.签约即将逾期.getTypeID(),
                                MessageType.回款即将逾期.getTypeID(),
                                MessageType.楼盘动态.getTypeID(),
                                MessageType.预约客户.getTypeID()};
    				}
    				break;
    			case "YQ":
    				msgType = new String[]{MessageType.认购逾期.getTypeID(),
                            MessageType.签约逾期.getTypeID(),
                            MessageType.回款逾期.getTypeID(),
                            MessageType.催办.getTypeID()};
    				break;
    			}
    			break;
    		case "XSJL":
    			switch(TypeCode.toUpperCase()){
    			case "YJ":
    				msgType = new String[]{MessageType.认购即将逾期.getTypeID(),
                            MessageType.签约即将逾期.getTypeID(),
                            MessageType.回款即将逾期.getTypeID()};
    				break;
    			case "DB":
    				msgType = new String[]{MessageType.客户丢失通知.getTypeID()};
    				break;
    			case "YQ":
    				msgType = new String[]{MessageType.认购逾期.getTypeID(),
    						MessageType.签约逾期.getTypeID(),
    						MessageType.回款逾期.getTypeID()};
    				break;
    			}
    			break;
    		case "XSFZR":
    			switch(TypeCode.toUpperCase()){
    			case "YJ":
    				msgType = new String[]{MessageType.认购即将逾期.getTypeID(),
    						MessageType.签约即将逾期.getTypeID(),
    						MessageType.回款即将逾期.getTypeID()};
    				break;
    			case "DB":
    				msgType = new String[]{MessageType.客户丢失通知.getTypeID()};
    				break;
    			case "YQ":
    				msgType = new String[]{MessageType.认购逾期.getTypeID(),
    						MessageType.签约逾期.getTypeID(),
    						MessageType.回款逾期.getTypeID()};
    				break;
    			}
    			break;
    		case "YXJL":
    			switch (TypeCode.toUpperCase()){
                case "YJ":
                	msgType = new String[] {MessageType.认购即将逾期.getTypeID(),
                    		MessageType.签约即将逾期.getTypeID(),
                            MessageType.回款即将逾期.getTypeID()};
                        break;
                case "DB":
                    msgType = new String[] {MessageType.客户丢失通知.getTypeID()};
                    break;
                case "YQ":
                    msgType = new String[] {MessageType.认购逾期.getTypeID(),
                    		MessageType.签约逾期.getTypeID(),
                    		MessageType.回款逾期.getTypeID()};
                    break;
                }
                break;
    		case "TK":
    			msgType = new String[] {MessageType.客户丢失通知.getTypeID()};
                break;
    		case "DZ":
    			msgType = new String[] {MessageType.客户丢失通知.getTypeID()};
                break;
    		case "ZQFZR":
    			msgType = new String[] {MessageType.跟进即将逾期.getTypeID(),
                        MessageType.带看通知.getTypeID(),
                        MessageType.认筹通知.getTypeID(),
                        MessageType.认购通知.getTypeID(),
                        MessageType.签约通知.getTypeID(),
                        MessageType.退房通知.getTypeID(),
                        MessageType.无效通知.getTypeID()};
                break;
    		case "ZQJL":
    			msgType = new String[] {MessageType.跟进即将逾期.getTypeID(),
                        MessageType.带看通知.getTypeID(),
                        MessageType.认筹通知.getTypeID(),
                        MessageType.认购通知.getTypeID(),
                        MessageType.签约通知.getTypeID(),
                        MessageType.退房通知.getTypeID(),
                        MessageType.无效通知.getTypeID()};
                break;
    		case "ZQ":
    			if(count == 0){
    				msgType = new String[] {MessageType.跟进即将逾期.getTypeID(),
                            MessageType.带看通知.getTypeID(),
                            MessageType.认筹通知.getTypeID(),
                            MessageType.认购通知.getTypeID(),
                            MessageType.签约通知.getTypeID(),
                            MessageType.退房通知.getTypeID(),
                            MessageType.无效通知.getTypeID()};
    			}else{
    				msgType = new String[] {MessageType.跟进即将逾期.getTypeID(),
                            MessageType.带看通知.getTypeID(),
                            MessageType.认筹通知.getTypeID(),
                            MessageType.认购通知.getTypeID(),
                            MessageType.签约通知.getTypeID(),
                            MessageType.退房通知.getTypeID(),
                            MessageType.无效通知.getTypeID(),
                            MessageType.楼盘动态.getTypeID(),
                            MessageType.预约客户.getTypeID()};
    			}
    			break;
    		case "JZ":
                msgType = new String[] {MessageType.渠道任务通知.getTypeID()};
                break;
            default:
                msgType = new String[] {MessageType.系统通知.getTypeID()};
                break;
    		}
    		List<UnreadCountVo> unreadCountList = null;
    		if(msgType != null){
    			if("GW".equals(JobCode.toUpperCase()) && "YQ".equals(TypeCode.toUpperCase())){
    				String[] msgTypeTemp = msgType.clone();
    				String[] temp = new String[] {MessageType.系统通知.getTypeID()};
    				msgTypeTemp = ArrayUtil.ArrayUnion(msgTypeTemp,temp);
    				unreadCountList = UnreadCountListByMessageType_Select(msgTypeTemp,map);
    			}else{
    				unreadCountList = UnreadCountListByMessageType_Select(msgType,map);
    			}
    		}
    		return ResponseMessage.ok(unreadCountList);
    	}catch(Exception e){
    		e.printStackTrace();
    		return ResponseMessage.error("系统异常，请联系管理员");
    	}
    }
    
    /**
     * 未读消息数
     */
    private List<UnreadCountVo> UnreadCountListByMessageType_Select(String[] msgTypeTemp, Map<String, Object> map) {
    	String strMessageType = "AND ( MessageType = "+String.join("' OR MessageType ='", msgTypeTemp)+" )";
		map.put("MessageType", strMessageType);
		String JobCode = (String) map.get("JobCode");
		
		String sqlWhere = "";
		if(!"ZQFZR".equals(JobCode)){
			sqlWhere = " AND T.Receiver = #{UserID}";
		}else{
			sqlWhere = " AND EXISTS(SELECT id FROM dbo.B_SalesGroupMember "
					+ "WHERE ProjectID=#{ProjectID} AND IsDel=0 AND Status=1 AND MemberID=T.Receiver "
					+ "AND RoleID IN('48FC928F-6EB5-4735-BF2B-29B1F591A582', '9584A4B7-F105-44BA-928D-F2FBA2F3B4A4', 'B0BF5636-94AD-4814-BB67-9C1873566F29'))";
		}
		map.put("sqlWhere", sqlWhere);
		return iSystemMessageService.UnreadCountListByMessageType_Select(map);
	}

	@ResponseBody
    @ApiOperation(value = "消息列表", notes = "消息列表")
    @RequestMapping(value = "/mMessageList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage mMessageList_Select(@RequestBody JSONObject jsonParam) {
    	try{
	    	Map paramMap = (HashMap)jsonParam.get("_param");
	    	String UserID = (String)paramMap.get("UserID");
	    	String ProjectID = (String)paramMap.get("ProjectID");
	    	String TypeID = (String)paramMap.get("TypeID");//消息类型ID
	    	String PageIndex = (String)paramMap.get("PageIndex");
	    	String PageSize = (String)paramMap.get("PageSize");
	    	String IsRead = (String)paramMap.get("IsRead");//自渠是否已读（必填）
	    	String IsApprove = (String)paramMap.get("IsApprove");//是否处理
	    	String JobCode = (String)paramMap.get("JobCode");//岗位代码
	    	
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
					+ "WHERE ProjectID=#{ProjectID} AND IsDel=0 AND Status=1 AND MemberID=Receiver "
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
