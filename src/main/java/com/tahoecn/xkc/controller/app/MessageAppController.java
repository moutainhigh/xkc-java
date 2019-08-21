package com.tahoecn.xkc.controller.app;


import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tahoecn.xkc.common.enums.MessageCate;
import com.tahoecn.xkc.common.enums.MessageType;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.vo.UnreadCountVo;
import com.tahoecn.xkc.service.sys.ISystemMessageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	@Value("${ThemeUrl}")
    private String ThemeUrl;
	@Value("${H5Url}")
	private String H5Url;
    @Autowired
    private ISystemMessageService iSystemMessageService;

    @ResponseBody
    @ApiOperation(value = "未读消息数接口", notes = "未读消息数接口")
    @RequestMapping(value = "/mMessageUnreadCount_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mMessageUnreadCount_Select(@RequestBody JSONObject jsonParam) {
    	try{
    		Map paramMap = (HashMap)jsonParam.get("_param");
    		System.out.println("================================="+jsonParam);
    		String ProjectID = (String)paramMap.get("ProjectID");//项目id
    		String UserID = (String)paramMap.get("UserID");//用户id
    		String JobCode = (String)paramMap.get("JobCode");//岗位代码
    		String TypeCode = (String)paramMap.get("TypeCode");//页签代码
    		String CurDate = (String)paramMap.get("CurDate");//当日
    		/*GJ(置业顾问--跟进):当日待跟进,到访提醒,跟进将逾期
    		YQ(置业顾问--逾期):当日跟进逾期,当日认购逾期,当日签约逾期,当日回款逾期
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
	    	map.put("CurDate", CurDate);
	    	//1.该项目是否有分享项目信息
    		int isCount = iSystemMessageService.IsExistsShareProject(map);
    		//2.统计消息类型id
    		String[] msgType = null;
    		msgType = MsgType(JobCode,TypeCode,isCount);
    		//原逻辑
    		/*switch (JobCode.toUpperCase()){
    		case "FJ":
    			msgType = new String[]{MessageType.系统通知.getTypeID()};
    			break;
    		case "GW":
    			switch(TypeCode.toUpperCase()){
    			case "GJ":
    				if(isCount == 0){
    					msgType = new String[]{MessageType.当日待跟进.getTypeID(),
                                MessageType.分配待跟进.getTypeID(),
                                MessageType.当日跟进逾期.getTypeID(),
                                MessageType.当日认购逾期.getTypeID(),
                                MessageType.当日签约逾期.getTypeID(),
                                MessageType.当日回款逾期.getTypeID()};
    				}else{
    					msgType = new String[]{MessageType.当日待跟进.getTypeID(),
                                MessageType.分配待跟进.getTypeID(),
                                MessageType.当日跟进逾期.getTypeID(),
                                MessageType.当日认购逾期.getTypeID(),
                                MessageType.当日签约逾期.getTypeID(),
                                MessageType.当日回款逾期.getTypeID(),
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
    				msgType = new String[]{MessageType.当日认购逾期.getTypeID(),
                            MessageType.当日签约逾期.getTypeID(),
                            MessageType.当日回款逾期.getTypeID()};
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
    				msgType = new String[]{MessageType.当日认购逾期.getTypeID(),
    						MessageType.当日签约逾期.getTypeID(),
    						MessageType.当日回款逾期.getTypeID()};
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
                	msgType = new String[] {MessageType.当日认购逾期.getTypeID(),
                    		MessageType.当日签约逾期.getTypeID(),
                            MessageType.当日回款逾期.getTypeID()};
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
    			msgType = new String[] {MessageType.当日跟进逾期.getTypeID(),
                        MessageType.带看通知.getTypeID(),
                        MessageType.认筹通知.getTypeID(),
                        MessageType.认购通知.getTypeID(),
                        MessageType.签约通知.getTypeID(),
                        MessageType.退房通知.getTypeID(),
                        MessageType.无效通知.getTypeID()};
                break;
    		case "ZQJL":
    			msgType = new String[] {MessageType.当日跟进逾期.getTypeID(),
                        MessageType.带看通知.getTypeID(),
                        MessageType.认筹通知.getTypeID(),
                        MessageType.认购通知.getTypeID(),
                        MessageType.签约通知.getTypeID(),
                        MessageType.退房通知.getTypeID(),
                        MessageType.无效通知.getTypeID()};
                break;
    		case "ZQ":
    			if(isCount == 0){
    				msgType = new String[] {MessageType.当日跟进逾期.getTypeID(),
                            MessageType.带看通知.getTypeID(),
                            MessageType.认筹通知.getTypeID(),
                            MessageType.认购通知.getTypeID(),
                            MessageType.签约通知.getTypeID(),
                            MessageType.退房通知.getTypeID(),
                            MessageType.无效通知.getTypeID()};
    			}else{
    				msgType = new String[] {MessageType.当日跟进逾期.getTypeID(),
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
    		}*/
    		//3.查询数据
    		List<UnreadCountVo> msgArray = null;
    		if(msgType != null){
//    			if("GW".equals(JobCode.toUpperCase()) && "YQ".equals(TypeCode.toUpperCase())){
//    				String[] msgTypeTemp = msgType.clone();
//    				String[] temp = new String[] {MessageType.系统通知.getTypeID()};
//    				msgTypeTemp = ArrayUtil.ArrayUnion(msgTypeTemp,temp);
//    				msgArray = UnreadCountListByMessageType_Select(msgTypeTemp,map);
//    			}else{
    				msgArray = UnreadCountListByMessageType_Select(msgType,map);
//    			}
    		}
    		return Result.ok(GetUnreadMessageTypeList(msgArray,msgType,JobCode));
    	}catch(Exception e){
    		e.printStackTrace();
    		return Result.errormsg(1,"系统异常，请联系管理员");
    	}
    }
    /**
     * 统计消息类型id
     * @param jobCode 岗位代码
     * @param typeCode 页签代码--逾期时传YQ，其余传空
     * 置业顾问GW：当日待跟进、当日跟进逾期、当日认购逾期、当日签约逾期、当日回款逾期、分配待跟进、（我的——逾期：认购逾期、签约逾期、回款逾期）
	 * 销售负责人XSFZR：当日认购逾期、当日签约逾期、当日回款逾期、（我的——逾期：认购逾期、签约逾期、回款逾期）
	 * 营销负责人YXJL：当日认购逾期、当日签约逾期、当日回款逾期、（我的——逾期：认购逾期、签约逾期、回款逾期）
	 * 自渠负责人ZQFZR：当日跟进逾期、带看通知、认筹通知、认购通知、签约通知、退房通知、无效通知
	 * 自渠人员ZQ：当日跟进逾期、带看通知、认筹通知、认购通知、签约通知、退房通知、无效通知
	 * 小蜜蜂JZ：渠道任务通知
     * @return
     */
    private String[] MsgType(String JobCode, String TypeCode,int isCount) {
    	String[] msgType = null;
    	if(StringUtils.isEmpty(TypeCode)){//消息列表
    		switch (JobCode.toUpperCase()){
    		case "GW":
    			msgType = new String[]{MessageType.当日待跟进.getTypeID(),
    					MessageType.当日跟进逾期.getTypeID(),
    					MessageType.当日认购逾期.getTypeID(),
    					MessageType.当日签约逾期.getTypeID(),
    					MessageType.当日回款逾期.getTypeID(),
    					MessageType.分配待跟进.getTypeID()};
    			break;
    		case "XSFZR":
    			msgType = new String[]{MessageType.当日认购逾期.getTypeID(),
    					MessageType.当日签约逾期.getTypeID(),
    					MessageType.当日回款逾期.getTypeID()};
    			break;
    		case "YXJL":
    			msgType = new String[]{MessageType.当日认购逾期.getTypeID(),
    					MessageType.当日签约逾期.getTypeID(),
    					MessageType.当日回款逾期.getTypeID()};
    			break;
    		case "ZQFZR"://当日跟进逾期、带看通知、认筹通知、认购通知、签约通知、退房通知、无效通知
    			msgType = new String[]{MessageType.当日跟进逾期.getTypeID(),
    					MessageType.带看通知.getTypeID(),
    					MessageType.认筹通知.getTypeID(),
    					MessageType.认购通知.getTypeID(),
    					MessageType.签约通知.getTypeID(),
    					MessageType.退房通知.getTypeID(),
    					MessageType.无效通知.getTypeID()};
    			break;
    		case "ZQ"://当日跟进逾期、带看通知、认筹通知、认购通知、签约通知、退房通知、无效通知
    			msgType = new String[]{MessageType.当日跟进逾期.getTypeID(),
    					MessageType.带看通知.getTypeID(),
    					MessageType.认筹通知.getTypeID(),
    					MessageType.认购通知.getTypeID(),
    					MessageType.签约通知.getTypeID(),
    					MessageType.退房通知.getTypeID(),
    					MessageType.无效通知.getTypeID()};
    			break;
    		case "JZ"://渠道任务通知
    			msgType = new String[]{MessageType.渠道任务通知.getTypeID()};
    			break;
    		}
    	}else{//逾期
    		msgType = new String[]{MessageType.认购逾期.getTypeID(),
    				MessageType.签约逾期.getTypeID(),
    				MessageType.回款逾期.getTypeID()};
    	}
    	
		return msgType;
	}

	private List<Map<String, Object>> GetUnreadMessageTypeList(List<UnreadCountVo> msgArray, String[] msgType, String jobCode) {
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	Map<String,Map<String,Object>> resJo = new LinkedHashMap<String,Map<String,Object>>();
    	if(msgType != null && msgType.length != 0){
    		for(String item : msgType){
    			Map<String,Object> res = new HashMap<String,Object>();
    			if(MessageType.楼盘动态.getTypeID().equals(item)){
    				res.put("TypeID", MessageType.楼盘动态.getTypeID());
    				res.put("TypeName", MessageType.楼盘动态.toString());
    				res.put("TypeCate", MessageCate.楼盘动态.getCateID());
    				res.put("Icon", ThemeUrl + "images/icon_loupandongtai.png");
    				res.put("Count", 0);
    				res.put("Content", "");
    				res.put("Url", H5Url);
    			}
    			if (item.equals(MessageType.预约客户.getTypeID()))
    			{
    				res.put("TypeID", MessageType.预约客户.getTypeID());
    				res.put("TypeName", MessageType.预约客户.toString());
    				res.put("TypeCate", MessageCate.预约客户列表.getCateID());
    				res.put("Icon", ThemeUrl + "images/icon_yuyuekehu.png");
    				res.put("Count", 0);
    				res.put("Content", "");
    			}
    			if (item.equals(MessageType.系统通知.getTypeID()))
    			{
    				res.put("TypeID", MessageType.系统通知.getTypeID());
    				res.put("TypeName", MessageType.系统通知.toString());
    				res.put("TypeCate", MessageCate.系统通知.getCateID());
    				res.put("Icon", ThemeUrl + "images/menu_icon_xttz.png");
    				res.put("Count", 0);
    				res.put("Content", "");
    			}
    			if (item.equals(MessageType.渠道任务通知.getTypeID()))
    			{
    				res.put("TypeID", MessageType.渠道任务通知.getTypeID());
    				res.put("TypeName", MessageType.渠道任务通知.toString());
    				res.put("TypeCate", MessageCate.渠道任务通知.getCateID());
    				res.put("Icon", ThemeUrl + "images/menu_icon_xttz.png");
    				res.put("Count", 0);
    				res.put("Content", "");
    			}
    			if (item.equals(MessageType.带看通知.getTypeID()))
    			{
    				res.put("TypeID", MessageType.带看通知.getTypeID());
    				res.put("TypeName", MessageType.到访通知.toString());
    				res.put("TypeCate", MessageCate.拓客客户列表.getCateID());
    				res.put("Icon", ThemeUrl + "images/icon_daikan.png");
    				res.put("Count", 0);
    				res.put("Content", "");
    			}
    			if (item.equals(MessageType.认筹通知.getTypeID()))
    			{
    				res.put("TypeID",  MessageType.认筹通知.getTypeID());
    				res.put("TypeName", MessageType.认筹通知.toString());
    				res.put("TypeCate", MessageCate.拓客客户列表.getCateID());
    				res.put("Icon", ThemeUrl + "images/icon_renchou.png");
    				res.put("Count", 0);
    				res.put("Content", "");
    			}
    			if (item.equals(MessageType.认购通知.getTypeID()))
    			{
    				res.put("TypeID",  MessageType.认购通知.getTypeID());
    				res.put("TypeName", MessageType.认购通知.toString());
    				res.put("TypeCate", MessageCate.拓客客户列表.getCateID());
    				res.put("Icon", ThemeUrl + "images/icon_rengou.png");
    				res.put("Count", 0);
    				res.put("Content", "");
    			}
    			if (item.equals(MessageType.签约通知.getTypeID()))
    			{
    				res.put("TypeID",  MessageType.签约通知.getTypeID());
    				res.put("TypeName", MessageType.签约通知.toString());
    				res.put("TypeCate", MessageCate.拓客客户列表.getCateID());
    				res.put("Icon", ThemeUrl + "images/icon_qianyue.png");
    				res.put("Count", 0);
    				res.put("Content", "");
    			}
    			if (item.equals(MessageType.退房通知.getTypeID()))
    			{
    				res.put("TypeID",  MessageType.退房通知.getTypeID());
    				res.put("TypeName", MessageType.退房通知.toString());
    				res.put("TypeCate", MessageCate.拓客客户列表.getCateID());
    				res.put("Icon", ThemeUrl + "images/icon_tuifang.png");
    				res.put("Count", 0);
    				res.put("Content", "");
    			}
    			if (item.equals(MessageType.无效通知.getTypeID()))
    			{
    				res.put("TypeID",  MessageType.无效通知.getTypeID());
    				res.put("TypeName", MessageType.无效通知.toString());
    				res.put("TypeCate", MessageCate.拓客客户列表.getCateID());
    				res.put("Icon", ThemeUrl + "images/icon_wuxiao.png");
    				res.put("Count", 0);
    				res.put("Content", "");
    			}
    			if (item.equals(MessageType.客户丢失通知.getTypeID()))
    			{
    				res.put("TypeID",  MessageType.客户丢失通知.getTypeID());
    				res.put("TypeName", MessageType.客户丢失通知.toString());
    				res.put("TypeCate", (jobCode.toUpperCase() == "YXJL" || jobCode.toUpperCase() == "XSJL") ? MessageCate.客户丢失列表.getCateID() : MessageCate.系统通知.getCateID());
    				res.put("Icon", ThemeUrl + "images/icon_kehudiushi.png");
    				res.put("Count", 0);
    				res.put("Content", "");
    			}
    			if (item.equals(MessageType.当日待跟进.getTypeID()) || item.equals(MessageType.今日待跟进.getTypeID()))
    			{
    				res.put("TypeID",  MessageType.当日待跟进.getTypeID());
    				res.put("TypeName", MessageType.当日待跟进.toString());
    				res.put("TypeCate", MessageCate.顾问客户列表.getCateID());
    				res.put("Icon", ThemeUrl + "images/icon_daihuifang.png");
    				res.put("Count", 0);
    				res.put("Content", "");
    			}
    			if (item.equals(MessageType.待完善首访客户资料.getTypeID()))
    			{
    				res.put("TypeID",  MessageType.待完善首访客户资料.getTypeID());
    				res.put("TypeName", MessageType.待完善首访客户资料.toString());
    				res.put("TypeCate", MessageCate.顾问客户列表.getCateID());
    				res.put("Icon", ThemeUrl + "images/icon_daiwanshan.png");
    				res.put("Count", 0);
    				res.put("Content", "");
    			}
    			if (item.equals(MessageType.首访信息录入逾期.getTypeID()))
    			{
    				res.put("TypeID",  MessageType.首访信息录入逾期.getTypeID());
    				res.put("TypeName", MessageType.首访信息录入逾期.toString());
    				res.put("TypeCate", MessageCate.全部顾问客户列表.getCateID());
    				res.put("Icon", ThemeUrl + "images/icon_daiwanshan.png");
    				res.put("Count", 0);
    				res.put("Content", "");
    			}
    			if (item.equals(MessageType.当日跟进逾期.getTypeID()) ||item.equals(MessageType.跟进即将逾期.getTypeID()))
    			{
    				res.put("TypeID",  MessageType.当日跟进逾期.getTypeID());
    				res.put("TypeName", MessageType.当日跟进逾期.toString());
    				res.put("TypeCate", MessageCate.顾问客户列表.getCateID());
    				res.put("Icon", ThemeUrl + "images/icon_genjinyuqi.png");
    				res.put("Count", 0);
    				res.put("Content", "");
    			}
    			if (item.equals(MessageType.当日认购逾期.getTypeID()) || item.equals(MessageType.认购即将逾期.getTypeID()))
    			{
    				res.put("TypeID",  MessageType.当日认购逾期.getTypeID());
    				res.put("TypeName", MessageType.当日认购逾期.toString());
    				res.put("TypeCate", MessageCate.顾问客户列表.getCateID());
    				res.put("Icon", ThemeUrl + "images/icon_rengouyuqi.png");
    				res.put("Count", 0);
    				res.put("Content", "");
    			}
    			if (item.equals(MessageType.当日签约逾期.getTypeID()) || item.equals(MessageType.签约即将逾期.getTypeID()))
    			{
    				res.put("TypeID",  MessageType.当日签约逾期.getTypeID());
    				res.put("TypeName", MessageType.当日签约逾期.toString());
    				res.put("TypeCate", MessageCate.顾问客户列表.getCateID());
    				res.put("Icon", ThemeUrl + "images/icon_qianyueyuqi.png");
    				res.put("Count", 0);
    				res.put("Content", "");
    			}
    			if (item.equals(MessageType.当日回款逾期.getTypeID()) || item.equals(MessageType.回款即将逾期.getTypeID()))
    			{
    				res.put("TypeID",  MessageType.当日回款逾期.getTypeID());
    				res.put("TypeName", MessageType.当日回款逾期.toString());
    				res.put("TypeCate", MessageCate.顾问客户列表.getCateID());
    				res.put("Icon", ThemeUrl + "images/icon_huikuanyuqi.png");
    				res.put("Count", 0);
    				res.put("Content", "");
    			}
    			if (item.equals(MessageType.回收提醒.getTypeID()))
    			{
    				res.put("TypeID",  MessageType.回收提醒.getTypeID());
    				res.put("TypeName", MessageType.回收提醒.toString());
    				res.put("TypeCate", MessageCate.系统通知.getCateID());
    				res.put("Icon", ThemeUrl + "images/icon_huisoutixing.png");
    				res.put("Count", 0);
    				res.put("Content", "");
    			}
    			if (item.equals(MessageType.到访提醒.getTypeID()))
    			{
    				res.put("TypeID",  MessageType.到访提醒.getTypeID());
    				res.put("TypeName", MessageType.到访提醒.toString());
    				res.put("TypeCate", MessageCate.系统通知.getCateID());
    				res.put("Icon", ThemeUrl + "images/icon_daiwanshan.png");
    				res.put("Count", 0);
    				res.put("Content", "");
    			}
    			if (item.equals(MessageType.跟进逾期.getTypeID()))
    			{
    				res.put("TypeID",  MessageType.跟进逾期.getTypeID());
    				res.put("TypeName", MessageType.跟进逾期.toString());
    				res.put("TypeCate", jobCode.toUpperCase() == "GW" ? MessageCate.顾问客户列表.getCateID() : MessageCate.全部顾问客户列表.getCateID());
    				res.put("Icon", ThemeUrl + "images/icon_genjinyuqi.png");
    				res.put("Count", 0);
    				res.put("Content", "");
    			}
    			if (item.equals(MessageType.认购逾期.getTypeID()))
    			{
    				res.put("TypeID",  MessageType.认购逾期.getTypeID());
    				res.put("TypeName", MessageType.认购逾期.toString());
    				res.put("TypeCate", jobCode.toUpperCase() == "GW" ? MessageCate.顾问客户列表.getCateID() : MessageCate.全部顾问客户列表.getCateID());
    				res.put("Icon", ThemeUrl + "images/icon_rengouyuqi.png");
    				res.put("Count", 0);
    				res.put("Content", "");
    			}
    			if (item.equals(MessageType.签约逾期.getTypeID()))
    			{
    				res.put("TypeID",  MessageType.签约逾期.getTypeID());
    				res.put("TypeName", MessageType.签约逾期.toString());
    				res.put("TypeCate", jobCode.toUpperCase() == "GW" ? MessageCate.顾问客户列表.getCateID() : MessageCate.全部顾问客户列表.getCateID());
    				res.put("Icon", ThemeUrl + "images/icon_qianyueyuqi.png");
    				res.put("Count", 0);
    				res.put("Content", "");
    			}
    			if (item.equals(MessageType.回款逾期.getTypeID()))
    			{
    				res.put("TypeID",  MessageType.回款逾期.getTypeID());
    				res.put("TypeName", MessageType.回款逾期.toString());
    				res.put("TypeCate", jobCode.toUpperCase() == "GW" ? MessageCate.顾问客户列表.getCateID() : MessageCate.全部顾问客户列表.getCateID());
    				res.put("Icon", ThemeUrl + "images/icon_huikuanyuqi.png");
    				res.put("Count", 0);
    				res.put("Content", "");
    			}
    			if (item.equals(MessageType.分配待跟进.getTypeID()))
    			{
    				res.put("TypeID",  MessageType.分配待跟进.getTypeID());
    				res.put("TypeName", MessageType.分配待跟进.toString());
    				res.put("TypeCate", MessageCate.顾问客户列表.getCateID());
    				res.put("Icon", ThemeUrl + "images/icon_feipeidaigenjin.png");
    				res.put("Count", 0);
    				res.put("Content", "");
    			}
    			if (item.equals(MessageType.客户即将失效.getTypeID()))
    			{
    				res.put("TypeID",  MessageType.客户即将失效.getTypeID());
    				res.put("TypeName", MessageType.客户即将失效.toString());
    				res.put("TypeCate", MessageCate.拓客客户列表.getCateID());
    				res.put("Icon", ThemeUrl + "images/icon_huisoutixing.png");
    				res.put("Count", 0);
    				res.put("Content", "");
    			}
    			if (item.equals(MessageType.客户失效通知.getTypeID()))
    			{
    				res.put("TypeID",  MessageType.客户失效通知.getTypeID());
    				res.put("TypeName", MessageType.客户失效通知.toString());
    				res.put("TypeCate", MessageCate.拓客客户列表.getCateID());
    				res.put("Icon", ThemeUrl + "images/icon_genjinyuqi.png");
    				res.put("Count", 0);
    				res.put("Content", "");
    			}
    			if (item.equals(MessageType.到访即将逾期.getTypeID()))
    			{
    				res.put("TypeID",  MessageType.到访即将逾期.getTypeID());
    				res.put("TypeName", MessageType.到访即将逾期.toString());
    				res.put("TypeCate", MessageCate.拓客客户列表.getCateID());
    				res.put("Icon", ThemeUrl + "images/icon_genjinyuqi.png");
    				res.put("Count", 0);
    				res.put("Content", "");
    			}
    			if (item.equals(MessageType.成交即将逾期.getTypeID()))
    			{
    				res.put("TypeID",  MessageType.成交即将逾期.getTypeID());
    				res.put("TypeName", MessageType.成交即将逾期.toString());
    				res.put("TypeCate", MessageCate.拓客客户列表.getCateID());
    				res.put("Icon", ThemeUrl + "images/icon_genjinyuqi.png");
    				res.put("Count", 0);
    				res.put("Content", "");
    			}
    			
    			if (item.equals(MessageType.催办.getTypeID()))
    			{
    				res.put("TypeID",  MessageType.催办.getTypeID());
    				res.put("TypeName", MessageType.催办.toString());
    				res.put("TypeCate", MessageCate.顾问客户列表.getCateID());
    				res.put("Icon", ThemeUrl + "images/icon_cuiban.png");
    				res.put("Count", 0);
    				res.put("Content", "");
    			}
    			resJo.put(item, res);
    		}
    	}
		String TypeIDTemp = MessageType.催办.getTypeID();
		if(msgArray != null && msgArray.size() != 0){
			for(UnreadCountVo item : msgArray){
				String TypeID = item.getMessageType();
				int Count = item.getMessageCount();
				String Content = item.getContent();
				if (resJo.get(TypeID) != null){
					resJo.get(TypeID).put("Count", Count);
					resJo.get(TypeID).put("Content", Content);
				}else{
					if (TypeID.equals(MessageType.首访信息录入逾期催办.getTypeID())
							|| TypeID.equals(MessageType.跟进逾期催办.getTypeID())
							|| TypeID.equals(MessageType.认购逾期催办.getTypeID())
							|| TypeID.equals(MessageType.签约逾期催办.getTypeID())
							|| TypeID.equals(MessageType.回款逾期催办.getTypeID())){
						if (resJo.get(TypeIDTemp) != null){
							int CountTemp = (int) resJo.get(TypeIDTemp).get("Count");
							String ContentTemp = (String) resJo.get(TypeIDTemp).get("Content");
							resJo.get(TypeIDTemp).put("Count", CountTemp + Count);
							resJo.get(TypeIDTemp).put("Content", ContentTemp + (Content.isEmpty() ? "" : Content + ","));
						}
					}
				}
			}
		}
		if (resJo.get(TypeIDTemp) != null){
            String ContentTemp = (String) resJo.get(TypeIDTemp).get("Content");
            resJo.get(TypeIDTemp).put("Content", StringUtils.isEmpty(ContentTemp) ? "" : ContentTemp.substring(0, ContentTemp.length() - 1));
        }
        for(Map<String, Object> item : resJo.values()){
            list.add(item);
        }
        return list;
		
	}

	/**
     * 未读消息数
     */
    private List<UnreadCountVo> UnreadCountListByMessageType_Select(String[] msgTypeTemp, Map<String, Object> map) {
    	//原逻辑
    	/*List<UnreadCountVo> list = new ArrayList<UnreadCountVo>();
    	String strMessageType = "AND ( MessageType = "+String.join("' OR MessageType ='", msgTypeTemp)+" )";
		map.put("MessageType", strMessageType);
		String JobCode = (String) map.get("JobCode");
		
		String sqlWhere = "";
		if(!"ZQFZR".equals(JobCode)){
			sqlWhere = " AND T.Receiver = '" + map.get("UserID") + "' ";
		}else{
			sqlWhere = " AND EXISTS(SELECT id FROM dbo.B_SalesGroupMember "
					+ "WHERE ProjectID='" + map.get("ProjectID") + "'  AND IsDel=0 AND Status=1 AND MemberID=T.Receiver "
					+ "AND RoleID IN('48FC928F-6EB5-4735-BF2B-29B1F591A582', '9584A4B7-F105-44BA-928D-F2FBA2F3B4A4', 'B0BF5636-94AD-4814-BB67-9C1873566F29'))";
		}
		map.put("sqlWhere", sqlWhere);
		list = iSystemMessageService.UnreadCountListByMessageType_Select(map);*/
		/*app改造-未读消息数，今日待跟进，当日跟进逾期，当日认购逾期，当日 签约逾期，当日回款逾期-start*/
    	List<UnreadCountVo> list = new ArrayList<UnreadCountVo>();
    	String JobCode = (String) map.get("JobCode");
    	String sqlWhere = "";
    	for(String item : msgTypeTemp){
    		if(MessageType.系统通知.getTypeID().equals(item)){
    			map.put("MessageType", item);
    			UnreadCountVo v = new UnreadCountVo();
				v.setMessageType(item);
				v.setMessageCount(ListByMessageType_Select_Count(map));
				list.add(v);
	    	}
	    	if(MessageType.渠道任务通知.getTypeID().equals(item)){
	    		map.put("sqlWhere", "AND A.MessageType = '"+item+"' AND A.IsRead = '0'");
	    		UnreadCountVo v = new UnreadCountVo();
				v.setMessageType(item);
				v.setMessageCount(iSystemMessageService.ListByMessageTypeChannelTask_SelectCount(map));
				list.add(v);
	    	}
	    	if(MessageType.带看通知.getTypeID().equals(item)
	    			|| MessageType.认筹通知.getTypeID().equals(item)
	    			|| MessageType.认购通知.getTypeID().equals(item)
	    			|| MessageType.签约通知.getTypeID().equals(item)
	    			|| MessageType.退房通知.getTypeID().equals(item)
	    			|| MessageType.无效通知.getTypeID().equals(item)){
	    		map.put("IsRead", "0");
	    		UnreadCountVo v = new UnreadCountVo();
				v.setMessageType(item);
				v.setMessageCount(ListByMessageTypeOpportunityZQ_Select_Count(map));
				list.add(v);
	    	}
	    	if(MessageType.客户丢失通知.getTypeID().equals(item)){
	    		map.put("sqlWhere", "AND A.MessageType = '"+item+"' AND A.IsRead = '0'");
	    		UnreadCountVo v = new UnreadCountVo();
				v.setMessageType(item);
				v.setMessageCount(iSystemMessageService.ListByMessageTypeKHDS_SelectCount(map));
				list.add(v);
	    	}
	    	if(MessageType.待完善首访客户资料.getTypeID().equals(item)
	    			|| MessageType.首访信息录入逾期.getTypeID().equals(item)
	    			|| MessageType.回收提醒.getTypeID().equals(item)
	    			|| MessageType.到访提醒.getTypeID().equals(item)
	    			|| MessageType.跟进逾期.getTypeID().equals(item)
	    			|| MessageType.认购逾期.getTypeID().equals(item)
	    			|| MessageType.签约逾期.getTypeID().equals(item)
	    			|| MessageType.回款逾期.getTypeID().equals(item)
	    			|| MessageType.分配待跟进.getTypeID().equals(item)){
		    	if(MessageType.回收提醒.getTypeID().equals(item)
		    			|| MessageType.到访提醒.getTypeID().equals(item)){
		    		map.put("IsApprove", "");
		    	}else{
		    		map.put("IsApprove", "0");
		    	}
	    		UnreadCountVo v = new UnreadCountVo();
				v.setMessageType(item);
				v.setMessageCount(ListByMessageTypeOpportunity_Select_Count(map));
				list.add(v);
	    	}
	    	if(MessageType.催办.getTypeID().equals(item)){
	    		String[] msgType = new String[]{MessageType.跟进逾期催办.getTypeID(),
	    					MessageType.认购逾期催办.getTypeID(),
	    					MessageType.签约逾期催办.getTypeID(),
	    					MessageType.回款逾期催办.getTypeID()};
	    		map.put("MessageType", String.join("' OR MessageType ='", msgType));
		    	map.put("IsApprove", "0");
	    		UnreadCountVo v = new UnreadCountVo();
				v.setMessageType(item);
				v.setMessageCount(ListByMessageTypeOpportunity_Select_Count(map));
				list.add(v);
	    	}
	    	int isCount = iSystemMessageService.IsExistsShareProject(map);
	    	if(isCount == 1){
	    		if(MessageType.预约客户.getTypeID().equals(item)){
	    			UnreadCountVo v = new UnreadCountVo();
					v.setMessageType(item);
					v.setMessageCount(ListByMessageTypeOpportunity_Select_Count(map));
					list.add(v);
	    		}
	    	}
	    	if(MessageType.客户即将失效.getTypeID().equals(item)
	    			|| MessageType.客户失效通知.getTypeID().equals(item)
	    			|| MessageType.到访即将逾期.getTypeID().equals(item)
	    			|| MessageType.成交即将逾期.getTypeID().equals(item)){
	    		map.put("IsRead", "0");
	    		UnreadCountVo v = new UnreadCountVo();
				v.setMessageType(item);
				v.setMessageCount(ListByMessageTypeClue_Select_Count(map));
				list.add(v);
	    	}
	    	if(MessageType.四类消息通知.getTypeID().equals(item)){
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
	    		map.put("MessageType", String.join("' OR MessageType ='", msgType));
	    		UnreadCountVo v = new UnreadCountVo();
				v.setMessageType(item);
				v.setMessageCount(ListByMessageType_Select_Count(map));
				list.add(v);
	    	}
    		
			if(MessageType.当日待跟进.getTypeID().equals(item)){
				UnreadCountVo v = new UnreadCountVo();
				v.setMessageType(item);
				v.setMessageCount(iSystemMessageService.ListDRDGJ_SelectCount(map));
				v.setContent("");
				list.add(v);
			}
			if(MessageType.当日跟进逾期.getTypeID().equals(item)){
				UnreadCountVo v = new UnreadCountVo();
				v.setMessageType(item);
				if("GW".equals(JobCode)){
	    			v.setMessageCount(iSystemMessageService.ListDRGJYQOpportunity_SelectCount(map));
	    		}else{
	    			sqlWhere = "";
	    			if(!"ZQFZR".equals(JobCode)){//ZQ
	    				sqlWhere += " AND o.SaleUserID = '" + map.get("UserID") + "' ";
	    			}else{//ZQFZR
	    				sqlWhere += " AND EXISTS(SELECT id FROM dbo.B_SalesGroupMember "
	    						+ "WHERE ProjectID='" + map.get("ProjectID") + "'  AND IsDel=0 AND Status=1 AND MemberID=o.SaleUserID "
	    						+ "AND RoleID IN('48FC928F-6EB5-4735-BF2B-29B1F591A582', '9584A4B7-F105-44BA-928D-F2FBA2F3B4A4', 'B0BF5636-94AD-4814-BB67-9C1873566F29'))";
	    			}
	    			map.put("sqlWhere", sqlWhere);
	    			v.setMessageCount(iSystemMessageService.ListDRGJYQClue_SelectCount(map));
	    		}
				v.setContent("");
				list.add(v);
			}
			if(MessageType.当日认购逾期.getTypeID().equals(item)){
				UnreadCountVo v = new UnreadCountVo();
				v.setMessageType(item);
				v.setMessageCount(iSystemMessageService.ListDRRGYQ_SelectCount(map));
				v.setContent("");
				list.add(v);
			}
			if(MessageType.当日签约逾期.getTypeID().equals(item)){
				UnreadCountVo v = new UnreadCountVo();
				v.setMessageType(item);
				v.setMessageCount(iSystemMessageService.ListDRQYYQ_SelectCount(map));
				v.setContent("");
				list.add(v);
			}
			if(MessageType.当日回款逾期.getTypeID().equals(item)){
				UnreadCountVo v = new UnreadCountVo();
				v.setMessageType(item);
				v.setMessageCount(iSystemMessageService.ListDRHKYQ_SelectCount(map));
				v.setContent("");
				list.add(v);
			}
    	}
		/*app改造-未读消息数，今日待跟进，当日跟进逾期，当日认购逾期，当日 签约逾期，当日回款逾期-end*/
		return list;
	}
    /**
     * 消息列表-普通消息-总数
     */
    private int ListByMessageType_Select_Count(Map<String, Object> map) {
    	String sqlWhere = "";
    	sqlWhere = " AND ( MessageType = '" + map.get("MessageType") + "' )";
    	map.put("sqlWhere", sqlWhere);
    	//总数
    	return iSystemMessageService.SystemMessageListByMessageType_SelectCount(map);
	}
    /**
     * 消息列表-机会消息-自渠-总数
     */
	private int ListByMessageTypeOpportunityZQ_Select_Count(Map<String, Object> map) {
		String JobCode = (String) map.get("JobCode");
		String IsApprove = (String) map.get("IsApprove");
		String IsRead = (String) map.get("IsRead");
		String sqlWhere = "";
		if(IsApprove != null && !"".equals(IsApprove)){
			sqlWhere = " AND ISNULL(IsApprove,0) = '0' ";
		}
		if(IsRead != null && !"".equals(IsRead)){
			sqlWhere = " AND ISNULL(IsRead,0) = '0' ";
		}
		sqlWhere += " AND ( MessageType = '" + map.get("MessageType") + "'  )";
		if(!"ZQFZR".equals(JobCode)){
			sqlWhere += " AND Receiver = '" + map.get("UserID") + "' ";
		}else{
			sqlWhere += " AND EXISTS(SELECT id FROM dbo.B_SalesGroupMember "
					+ "WHERE ProjectID = '" + map.get("ProjectID") + "'  AND IsDel=0 AND Status=1 AND MemberID=Receiver "
					+ "AND RoleID IN('48FC928F-6EB5-4735-BF2B-29B1F591A582', '9584A4B7-F105-44BA-928D-F2FBA2F3B4A4', 'B0BF5636-94AD-4814-BB67-9C1873566F29'))";
		}
		
		map.put("sqlWhere", sqlWhere);
		//总数
		return iSystemMessageService.SystemMessageListByMessageTypeOpportunityZQ_SelectCount(map);
	}
	/**
     * 消息列表-机会消息-总数
     */
    private int ListByMessageTypeOpportunity_Select_Count(Map<String, Object> map) {
    	String IsApprove = (String) map.get("IsApprove");
		String IsRead = (String) map.get("IsRead");
		String sqlWhere = "";
		if(IsApprove != null && !"".equals(IsApprove)){
			sqlWhere = " AND ISNULL(IsApprove,0) = '0' ";
		}
		if(IsRead != null && !"".equals(IsRead)){
			sqlWhere = " AND ISNULL(IsRead,0) = '0' ";
		}
		sqlWhere += " AND ( MessageType = '" + map.get("MessageType") + "'  )";
		map.put("sqlWhere", sqlWhere);
		//总数
		return iSystemMessageService.SystemMessageListByMessageTypeOpportunity_SelectCount(map);
	}
    /**
     * 消息列表-线索消息-总数
     */
    private int ListByMessageTypeClue_Select_Count(Map<String, Object> map) {
    	String IsApprove = (String) map.get("IsApprove");
		String IsRead = (String) map.get("IsRead");
		String JobCode = (String) map.get("JobCode");
		
		String sqlWhere = "";
		if(IsApprove != null && !"".equals(IsApprove)){
			sqlWhere = " AND ISNULL(IsApprove,0) = '0' ";
		}
		if(IsRead != null && !"".equals(IsRead)){
			sqlWhere = " AND ISNULL(IsRead,0) = '0' ";
		}
		sqlWhere += " AND ( MessageType = '" + map.get("MessageType") + "'  )";
		if(!"ZQFZR".equals(JobCode)){
			sqlWhere += " AND Receiver = '" + map.get("UserID") + "' ";
		}else{
			sqlWhere += " AND EXISTS(SELECT id FROM dbo.B_SalesGroupMember "
					+ "WHERE ProjectID='" + map.get("ProjectID") + "'  AND IsDel=0 AND Status=1 AND MemberID=Receiver "
					+ "AND RoleID IN('48FC928F-6EB5-4735-BF2B-29B1F591A582', '9584A4B7-F105-44BA-928D-F2FBA2F3B4A4', 'B0BF5636-94AD-4814-BB67-9C1873566F29'))";
		}
		map.put("sqlWhere", sqlWhere);
		//总数
		return iSystemMessageService.SystemMessageListByMessageTypeClue_SelectCount(map);
	}
	@ResponseBody
    @ApiOperation(value = "消息列表", notes = "消息列表")
    @RequestMapping(value = "/mMessageList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mMessageList_Select(@RequestBody JSONObject jsonParam) {
    	try{
	    	Map paramMap = (HashMap)jsonParam.get("_param");
	    	System.out.println("================================="+jsonParam);
	    	String UserID = (String)paramMap.get("UserID");
	    	String ProjectID = (String)paramMap.get("ProjectID");
	    	String TypeID = (String)paramMap.get("TypeID");//消息类型ID
	    	Integer PageIndex = (Integer)paramMap.get("PageIndex");
	    	Integer PageSize = (Integer)paramMap.get("PageSize");
	    	String IsRead = (String)paramMap.get("IsRead");//自渠是否已读（必填）
	    	String IsApprove = (String)paramMap.get("IsApprove");//是否处理
	    	String JobCode = (String)paramMap.get("JobCode");//岗位代码
	    	String CurDate = (String)paramMap.get("CurDate");//
	    	
	    	Map<String,Object> map = new HashMap<String,Object>();
	    	map.put("UserID", UserID);
	    	map.put("ProjectID", ProjectID);
	    	map.put("MessageType", TypeID);
	    	map.put("PageIndex", PageIndex);
	    	map.put("PageSize", PageSize);
	    	map.put("IsRead", IsRead);
	    	map.put("IsApprove", IsApprove);
	    	map.put("JobCode", JobCode);
	    	map.put("CurDate", CurDate);
	    	
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
	    		map.put("sqlWhere", "AND A.MessageType = '"+TypeID+"' AND A.IsRead =  '"+IsRead+"'");
	    		//列表
	    		result.put("List", JSON.parseArray(JSON.toJSONString(iSystemMessageService.ListByMessageTypeChannelTask_Select(map))));
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
	    		map.put("sqlWhere", "AND A.MessageType = '"+TypeID+"' AND A.IsRead =  '"+IsRead+"'");
	    		//列表
	    		result.put("List", JSON.parseArray(JSON.toJSONString(iSystemMessageService.ListByMessageTypeKHDS_Select(map))));
	    		//总数
	    		result.put("AllCount", iSystemMessageService.ListByMessageTypeKHDS_SelectCount(map));
	    		//更新操作
	    		iSystemMessageService.updMessageZQ(map);
	    	}
	    	if(MessageType.待完善首访客户资料.getTypeID().equals(TypeID)){
	    		result.put("EmptyHandleMsg", "今日的任务全部完成了，你真棒。");
	    		result.put("EmptyHandleIconType", 3);
	    		result.put("EmptyUnHandleMsg", "今日的任务全部完成了，你真棒");
	    		result.put("EmptyUnHandleIconType", 3);
	    		result = ListByMessageTypeOpportunity_Select(result,map);
	    	}
	    	if(MessageType.当日待跟进.getTypeID().equals(TypeID) || MessageType.今日待跟进.getTypeID().equals(TypeID)){
	    		result.put("EmptyHandleMsg", "今日的任务全部完成了，你真棒。");
	    		result.put("EmptyHandleIconType", 3);
	    		result.put("EmptyUnHandleMsg", "今日的任务全部完成了，你真棒");
	    		result.put("EmptyUnHandleIconType", 3);
//	    		result = ListByMessageTypeOpportunity_Select(result,map);
	    		result = ListDRDGJ_Select(result,map);
	    	}
	    	if(MessageType.首访信息录入逾期.getTypeID().equals(TypeID)){
	    		result.put("EmptyHandleMsg", "暂无首访信息录入逾期的客户，请继续保持");
	    		result.put("EmptyHandleIconType", 3);
	    		result.put("EmptyUnHandleMsg", "暂无首访信息录入逾期的客户，请继续保持");
	    		result.put("EmptyUnHandleIconType", 3);
	    		result = ListByMessageTypeOpportunity_Select(result,map);
	    	}
	    	if(MessageType.当日跟进逾期.getTypeID().equals(TypeID) || MessageType.跟进即将逾期.getTypeID().equals(TypeID)){
	    		result.put("EmptyHandleMsg", "暂无列表信息，请及时处理即将逾期的客户。");
	    		result.put("EmptyHandleIconType", 2);
	    		result.put("EmptyUnHandleMsg", "暂无当日跟进逾期的客户，请继续保持和客户的沟通频次。");
	    		result.put("EmptyUnHandleIconType", 1);
//	    		if("GW".equals(JobCode) || "YXJL".equals(JobCode)){
//	    			result = ListByMessageTypeOpportunity_Select(result,map);
//	    		}else{
//	    			result = ListByMessageTypeClue_Select(result,map);
//	    		}
	    		if("GW".equals(JobCode)){
	    			result = ListDRGJYQOpportunity_Select(result,map);
	    		}else{
	    			result = ListDRGJYQClue_Select(result,map);
	    		}
	    	}
	    	if(MessageType.当日认购逾期.getTypeID().equals(TypeID) || MessageType.认购即将逾期.getTypeID().equals(TypeID)){
	    		result.put("EmptyHandleMsg", "暂无列表信息，请及时处即将理逾期的客户。");
	    		result.put("EmptyHandleIconType", 2);
	    		result.put("EmptyUnHandleMsg", "暂无当日认购逾期的客户，请注意认购时间。");
	    		result.put("EmptyUnHandleIconType", 2);
//	    		result = ListByMessageTypeOpportunity_Select(result,map);
	    		result = ListDRRGYQ_Select(result,map);
	    	}
	    	if(MessageType.当日签约逾期.getTypeID().equals(TypeID) || MessageType.签约即将逾期.getTypeID().equals(TypeID)){
	    		result.put("EmptyHandleMsg", "暂无列表信息，请及时处理即将逾期的客户。");
	    		result.put("EmptyHandleIconType", 2);
	    		result.put("EmptyUnHandleMsg", "暂无当日签约逾期的客户，请注意签约时间。");
	    		result.put("EmptyUnHandleIconType", 2);
//	    		result = ListByMessageTypeOpportunity_Select(result,map);
	    		result = ListDRQYYQ_Select(result,map);
	    	}
	    	if(MessageType.当日回款逾期.getTypeID().equals(TypeID)||MessageType.回款即将逾期.getTypeID().equals(TypeID)){
	    		result.put("EmptyHandleMsg", "暂无列表信息，请及时处理即将逾期的客户。");
	    		result.put("EmptyHandleIconType", 2);
	    		result.put("EmptyUnHandleMsg", "暂无当日回款逾期的客户，请注意回款时间。");
	    		result.put("EmptyUnHandleIconType", 2);
//	    		result = ListByMessageTypeOpportunity_Select(result,map);
	    		result = ListDRHKYQ_Select(result,map);
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
	    	int isCount = iSystemMessageService.IsExistsShareProject(map);
	    	if(isCount == 1){
	    		if(MessageType.预约客户.getTypeID().equals(TypeID)){
	    			result.put("EmptyHandleMsg", "暂无待办任务，努力去挖掘客户资源");
	    			result.put("EmptyHandleIconType", 1);
	    			result.put("EmptyUnHandleMsg", "暂无待办任务，努力去挖掘客户资源");
	    			result.put("EmptyUnHandleIconType", 1);
	    			result = ListByMessageTypeOpportunity_Select(result,map);
	    		}
	    	}
	    	result.put("PageSize", PageSize);
	    	return Result.ok(result);
    	}catch(Exception e){
    		e.printStackTrace();
    		return Result.errormsg(1,"系统异常，请联系管理员");
    	}
    }
	/**
	 * 今日待跟进列表
	 */
	private Map<String, Object> ListDRDGJ_Select(Map<String, Object> result, Map<String, Object> map) {
		//列表
		result.put("List", JSON.parseArray(JSON.toJSONString(iSystemMessageService.ListDRDGJ_Select(map))));
		//总数
		result.put("AllCount", iSystemMessageService.ListDRDGJ_SelectCount(map));
		return result;
	}
	/**
	 * 当日跟进逾期-销售团队负责人/营销负责人
	 */
	private Map<String, Object> ListDRGJYQOpportunity_Select(Map<String, Object> result, Map<String, Object> map) {
		//列表
		result.put("List", JSON.parseArray(JSON.toJSONString(iSystemMessageService.ListDRGJYQOpportunity_Select(map))));
		//总数
		result.put("AllCount", iSystemMessageService.ListDRGJYQOpportunity_SelectCount(map));
		return result;
	}
	/**
	 * 当日跟进逾期
	 */
	private Map<String, Object> ListDRGJYQClue_Select(Map<String, Object> result, Map<String, Object> map) {
		String JobCode = (String) map.get("JobCode");
		
		String sqlWhere = "";
		if(!"ZQFZR".equals(JobCode)){//ZQ
			sqlWhere += " AND o.SaleUserID = '" + map.get("UserID") + "' ";
		}else{//ZQFZR
			sqlWhere += " AND EXISTS(SELECT id FROM dbo.B_SalesGroupMember "
					+ "WHERE ProjectID='" + map.get("ProjectID") + "'  AND IsDel=0 AND Status=1 AND MemberID=o.SaleUserID "
					+ "AND RoleID IN('48FC928F-6EB5-4735-BF2B-29B1F591A582', '9584A4B7-F105-44BA-928D-F2FBA2F3B4A4', 'B0BF5636-94AD-4814-BB67-9C1873566F29'))";
		}
		map.put("sqlWhere", sqlWhere);
		//列表
		result.put("List", JSON.parseArray(JSON.toJSONString(iSystemMessageService.ListDRGJYQClue_Select(map))));
		//总数
		result.put("AllCount", iSystemMessageService.ListDRGJYQClue_SelectCount(map));
		return result;
	}
	/**
	 * 当日认购逾期列表
	 */
    private Map<String, Object> ListDRRGYQ_Select(Map<String, Object> result, Map<String, Object> map) {
		//列表
		result.put("List", JSON.parseArray(JSON.toJSONString(iSystemMessageService.ListDRRGYQ_Select(map))));
		//总数
		result.put("AllCount", iSystemMessageService.ListDRRGYQ_SelectCount(map));
		return result;
	}
    /**
     * 当日签约逾期
     */
    private Map<String, Object> ListDRQYYQ_Select(Map<String, Object> result, Map<String, Object> map) {
		//列表
		result.put("List", JSON.parseArray(JSON.toJSONString(iSystemMessageService.ListDRQYYQ_Select(map))));
		//总数
		result.put("AllCount", iSystemMessageService.ListDRQYYQ_SelectCount(map));
		return result;
	}
    /**
     * 当日回款逾期
     */
    private Map<String, Object> ListDRHKYQ_Select(Map<String, Object> result, Map<String, Object> map) {
		//列表
		result.put("List", JSON.parseArray(JSON.toJSONString(iSystemMessageService.ListDRHKYQ_Select(map))));
		//总数
		result.put("AllCount", iSystemMessageService.ListDRHKYQ_SelectCount(map));
		return result;
	}
	/**
     * 消息列表-普通消息
     * @param result
     * @param map
     * @return
     */
    private Map<String, Object> ListByMessageType_Select(Map<String, Object> result, Map<String, Object> map) {
    	String sqlWhere = "";
    	sqlWhere = " AND ( MessageType = '" + map.get("MessageType") + "' )";
    	map.put("sqlWhere", sqlWhere);
    	//列表
    	result.put("List", JSON.parseArray(JSON.toJSONString(iSystemMessageService.SystemMessageListByMessageType_Select(map))));
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
			sqlWhere = " AND ISNULL(IsApprove,0) = '" + map.get("IsApprove") + "' ";
		}
		if(IsRead != null && !"".equals(IsRead)){
			sqlWhere = " AND ISNULL(IsRead,0) = '" + map.get("IsRead") + "' ";
		}
		sqlWhere += " AND ( MessageType = '" + map.get("MessageType") + "'  )";
		if(!"ZQFZR".equals(JobCode)){
			sqlWhere += " AND Receiver = '" + map.get("UserID") + "' ";
		}else{
			sqlWhere += " AND EXISTS(SELECT id FROM dbo.B_SalesGroupMember "
					+ "WHERE ProjectID='" + map.get("ProjectID") + "'  AND IsDel=0 AND Status=1 AND MemberID=Receiver "
					+ "AND RoleID IN('48FC928F-6EB5-4735-BF2B-29B1F591A582', '9584A4B7-F105-44BA-928D-F2FBA2F3B4A4', 'B0BF5636-94AD-4814-BB67-9C1873566F29'))";
		}
		map.put("sqlWhere", sqlWhere);
		//列表
		result.put("List", JSON.parseArray(JSON.toJSONString(iSystemMessageService.SystemMessageListByMessageTypeClue_Select(map))));
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
			sqlWhere = " AND ISNULL(IsApprove,0) = '" + map.get("IsApprove") + "' ";
		}
		if(IsRead != null && !"".equals(IsRead)){
			sqlWhere = " AND ISNULL(IsRead,0) = '" + map.get("IsRead") + "' ";
		}
		sqlWhere += " AND ( MessageType = '" + map.get("MessageType") + "'  )";
		
		map.put("sqlWhere", sqlWhere);
		//列表
		result.put("List", JSON.parseArray(JSON.toJSONString(iSystemMessageService.SystemMessageListByMessageTypeOpportunity_Select(map))));
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
			sqlWhere = " AND ISNULL(IsApprove,0) = '" + map.get("IsApprove") + "' ";
		}
		if(IsRead != null && !"".equals(IsRead)){
			sqlWhere = " AND ISNULL(IsRead,0) = '" + map.get("IsRead") + "' ";
		}
		sqlWhere += " AND ( MessageType = '" + map.get("MessageType") + "'  )";
		if(!"ZQFZR".equals(JobCode)){
			sqlWhere += " AND Receiver = '" + map.get("UserID") + "' ";
		}else{
			sqlWhere += " AND EXISTS(SELECT id FROM dbo.B_SalesGroupMember "
					+ "WHERE ProjectID = '" + map.get("ProjectID") + "'  AND IsDel=0 AND Status=1 AND MemberID=Receiver "
					+ "AND RoleID IN('48FC928F-6EB5-4735-BF2B-29B1F591A582', '9584A4B7-F105-44BA-928D-F2FBA2F3B4A4', 'B0BF5636-94AD-4814-BB67-9C1873566F29'))";
		}
		
		map.put("sqlWhere", sqlWhere);
		//列表
		result.put("List", JSON.parseArray(JSON.toJSONString(iSystemMessageService.SystemMessageListByMessageTypeOpportunityZQ_Select(map))));
		//总数
		result.put("AllCount", iSystemMessageService.SystemMessageListByMessageTypeOpportunityZQ_SelectCount(map));
		//更新操作
		iSystemMessageService.updMessageZQ(map);
		return result;
	}
	
	@ResponseBody
    @ApiOperation(value = "模块未读消息数", notes = "模块未读消息数")
    @RequestMapping(value = "/mMessageModelUnreadCount_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mMessageModelUnreadCount_Select(@RequestBody JSONObject jsonParam) {
    	try{
    		@SuppressWarnings("unchecked")
			Map<String,Object> paramMap = (HashMap<String,Object>)jsonParam.get("_param");
    		String ProjectID = (String) paramMap.get("ProjectID");
    		String UserID = (String)paramMap.get("UserID");//用户id
    		String JobCode = (String)paramMap.get("JobCode");//岗位代码
    		String TypeCode = (String)paramMap.get("TypeCode");//页签代码
    		String CurrentDate = (String)paramMap.get("CurrentDate");//当日
    		/*GJ(置业顾问--跟进):当日待跟进,到访提醒,跟进将逾期
    		YQ(置业顾问--逾期):当日跟进逾期,当日认购逾期,当日签约逾期,当日回款逾期
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
	    	map.put("CurrentDate", CurrentDate);
	    	//1.该项目是否有分享项目信息
    		int isCount = iSystemMessageService.IsExistsShareProject(map);
    		//2.统计消息类型id
    		String[] msgType = null;
    		Map<String,Object> res = new HashMap<String,Object>();
    		switch(JobCode.toUpperCase()){
    		case "GW":{
    			res.put("GJCount", 0);
                res.put("YQCount", 0);
                res.put("XXCount", 0);
                res.put("CBCount", 0);
                res.put("YJCount", 0);
                res.put("DBCount", 0);
                if(isCount == 1){//该项目是否有分享项目
                	res.put("DTCount", 0);//动态数
                    res.put("YYCount", 0);//预约客户数
                    msgType = new String[]{MessageType.当日待跟进.getTypeID(),
                            MessageType.分配待跟进.getTypeID(),
                            MessageType.当日跟进逾期.getTypeID(),
                            MessageType.当日认购逾期.getTypeID(),
                            MessageType.当日签约逾期.getTypeID(),
                            MessageType.当日回款逾期.getTypeID(),
                            MessageType.跟进逾期.getTypeID(),
                            MessageType.认购逾期.getTypeID(),
                            MessageType.签约逾期.getTypeID(),
                            MessageType.回款逾期.getTypeID(),
                            MessageType.跟进逾期催办.getTypeID(),
                            MessageType.认购逾期催办.getTypeID(),
                            MessageType.签约逾期催办.getTypeID(),
                            MessageType.回款逾期催办.getTypeID(),
                            MessageType.到访提醒.getTypeID(),
                            MessageType.客户丢失通知.getTypeID(),
                            MessageType.回收提醒.getTypeID(),
                            MessageType.系统通知.getTypeID(),
                            MessageType.楼盘动态.getTypeID(),
                            MessageType.预约客户.getTypeID()};
                }else{
                	msgType = new String[] {MessageType.当日待跟进.getTypeID(),
                            MessageType.分配待跟进.getTypeID(),
                            MessageType.当日跟进逾期.getTypeID(),
                            MessageType.当日认购逾期.getTypeID(),
                            MessageType.当日签约逾期.getTypeID(),
                            MessageType.当日回款逾期.getTypeID(),
                            MessageType.跟进逾期.getTypeID(),
                            MessageType.认购逾期.getTypeID(),
                            MessageType.签约逾期.getTypeID(),
                            MessageType.回款逾期.getTypeID(),
                            MessageType.跟进逾期催办.getTypeID(),
                            MessageType.认购逾期催办.getTypeID(),
                            MessageType.签约逾期催办.getTypeID(),
                            MessageType.回款逾期催办.getTypeID(),
                            MessageType.到访提醒.getTypeID(),
                            MessageType.客户丢失通知.getTypeID(),
                            MessageType.回收提醒.getTypeID(),
                            MessageType.系统通知.getTypeID()};
                }
                List<UnreadCountVo> msgArray = UnreadCountListByMessageType_Select(msgType,map);
                for(UnreadCountVo msg : msgArray){
                	String TypeID = msg.getMessageType();
                    int Count = msg.getMessageCount();
                    if (MessageType.当日待跟进.getTypeID().equals(TypeID)
                    		|| MessageType.今日待跟进.getTypeID().equals(TypeID)
                            || MessageType.分配待跟进.getTypeID().equals(TypeID)
                            || MessageType.当日跟进逾期.getTypeID().equals(TypeID)
                            || MessageType.跟进即将逾期.getTypeID().equals(TypeID)
                            || MessageType.当日认购逾期.getTypeID().equals(TypeID)
                            || MessageType.认购即将逾期.getTypeID().equals(TypeID)
                            || MessageType.当日签约逾期.getTypeID().equals(TypeID)
                            || MessageType.签约即将逾期.getTypeID().equals(TypeID)
                            || MessageType.当日回款逾期.getTypeID().equals(TypeID)
                            || MessageType.回款即将逾期.getTypeID().equals(TypeID)){
                    	int CountTemp = (int) res.get("GJCount");
                    	res.put("GJCount", CountTemp + Count);
                    }
                    if (MessageType.跟进逾期.getTypeID().equals(TypeID)
                            || MessageType.认购逾期.getTypeID().equals(TypeID)
                            || MessageType.签约逾期.getTypeID().equals(TypeID)
                            || MessageType.回款逾期.getTypeID().equals(TypeID)
                            || MessageType.跟进逾期催办.getTypeID().equals(TypeID)
                            || MessageType.认购逾期催办.getTypeID().equals(TypeID)
                            || MessageType.签约逾期催办.getTypeID().equals(TypeID)
                            || MessageType.回款逾期催办.getTypeID().equals(TypeID)){
                        int CountTemp = (int) res.get("YQCount");
                        res.put("YQCount", CountTemp + Count);
                    }
                    if(MessageType.到访提醒.getTypeID().equals(TypeID)
                    		|| MessageType.客户丢失通知.getTypeID().equals(TypeID)
                    		|| MessageType.回收提醒.getTypeID().equals(TypeID)
                    		|| MessageType.系统通知.getTypeID().equals(TypeID)){
                    	int CountTemp = (int) res.get("XXCount");
                        res.put("XXCount", CountTemp + Count);
                    }
//                    if (isCount.Count > 0){ //该项目是否有分享项目,判断是否有一条查询结果（c#），0也算一条记录；
                    //Java中没有会返回0
                        if (MessageType.楼盘动态.getTypeID().equals(TypeID)){
                        	int CountTemp = ("null".equals(res.get("DTCount"))|| res.get("DTCount") == null) ? 0 : (int) res.get("DTCount");
                            res.put("DTCount", CountTemp + Count);
                        }
                        if (MessageType.预约客户.getTypeID().equals(TypeID)){
                            int CountTemp = ("null".equals(res.get("YYCount"))|| res.get("YYCount") == null) ? 0 : (int) res.get("YYCount");
                            res.put("YYCount", CountTemp + Count);
                        }
//                    }
                }
    		}
    		break;
    		case "YXJL":{
    			res.put("GJCount", 0);
                res.put("YQCount", 0);
                res.put("XXCount", 0);
                res.put("CBCount", 0);
                res.put("YJCount", 0);
                res.put("DBCount", 0);
                msgType = new String[] {
                        MessageType.跟进逾期.getTypeID(),
                        MessageType.认购逾期.getTypeID(),
                        MessageType.签约逾期.getTypeID(),
                        MessageType.回款逾期.getTypeID(),
                        MessageType.客户丢失通知.getTypeID(),
                        MessageType.回收提醒.getTypeID(),
                        MessageType.系统通知.getTypeID()};
                List<UnreadCountVo> msgArray = UnreadCountListByMessageType_Select(msgType,map);
                for(UnreadCountVo msg : msgArray){
                	String TypeID = msg.getMessageType();
                    int Count = msg.getMessageCount();
                    if (MessageType.跟进逾期.getTypeID().equals(TypeID)
                        || MessageType.认购逾期.getTypeID().equals(TypeID)
                        || MessageType.签约逾期.getTypeID().equals(TypeID)
                        || MessageType.回款逾期.getTypeID().equals(TypeID)){
                        int CountTemp = (int) res.get("YJCount");
                        res.put("YJCount", CountTemp + Count);
                    }
                    if (MessageType.客户丢失通知.getTypeID().equals(TypeID)){
                        int CountTemp = (int) res.get("DBCount");
                        res.put("DBCount", CountTemp + Count);
                    }
                    if (MessageType.回收提醒.getTypeID().equals(TypeID)
                        || MessageType.系统通知.getTypeID().equals(TypeID)){
                        int CountTemp = (int) res.get("XXCount");
                        res.put("XXCount", CountTemp + Count);
                    }
                }
    		}
    		break;
    		case "ZQFZR":{
    			res.put("GJCount", 0);
                res.put("XXCount", 0);
                msgType = new String[] {
                        MessageType.带看通知.getTypeID(),
                        MessageType.认筹通知.getTypeID(),
                        MessageType.认购通知.getTypeID(),
                        MessageType.签约通知.getTypeID(),
                        MessageType.退房通知.getTypeID(),
                        MessageType.无效通知.getTypeID(),
                        MessageType.系统通知.getTypeID()};
                List<UnreadCountVo> msgArray = UnreadCountListByMessageType_Select(msgType,map);
                for(UnreadCountVo msg : msgArray){
                	String TypeID = msg.getMessageType();
                    int Count = msg.getMessageCount();
                    if (MessageType.带看通知.getTypeID().equals(TypeID)
                        || MessageType.认筹通知.getTypeID().equals(TypeID)
                        || MessageType.认购通知.getTypeID().equals(TypeID)
                        || MessageType.签约通知.getTypeID().equals(TypeID)
                        || MessageType.退房通知.getTypeID().equals(TypeID)
                        || MessageType.无效通知.getTypeID().equals(TypeID)){
                        int CountTemp = (int) res.get("GJCount");
                        res.put("GJCount", CountTemp + Count);
                    }
                    if (MessageType.系统通知.getTypeID().equals(TypeID)){
                        int CountTemp = (int) res.get("XXCount");
                        res.put("XXCount", CountTemp + Count);
                    }
                }
    		}
    		break;
    		case "ZQJL":{
    			res.put("GJCount", 0);
                res.put("XXCount", 0);
                msgType = new String[] {
                        MessageType.带看通知.getTypeID(),
                        MessageType.认筹通知.getTypeID(),
                        MessageType.认购通知.getTypeID(),
                        MessageType.签约通知.getTypeID(),
                        MessageType.退房通知.getTypeID(),
                        MessageType.无效通知.getTypeID(),
                        MessageType.系统通知.getTypeID()};
                List<UnreadCountVo> msgArray = UnreadCountListByMessageType_Select(msgType,map);
                for(UnreadCountVo msg : msgArray){
                	String TypeID = msg.getMessageType();
                    int Count = msg.getMessageCount();
                    if (MessageType.带看通知.getTypeID().equals(TypeID)
                        || MessageType.认筹通知.getTypeID().equals(TypeID)
                        || MessageType.认购通知.getTypeID().equals(TypeID)
                        || MessageType.签约通知.getTypeID().equals(TypeID)
                        || MessageType.退房通知.getTypeID().equals(TypeID)
                        || MessageType.无效通知.getTypeID().equals(TypeID)){
                        int CountTemp = (int) res.get("GJCount");
                        res.put("GJCount", CountTemp + Count);
                    }
                    if (MessageType.系统通知.getTypeID().equals(TypeID)){
                        int CountTemp = (int) res.get("XXCount");
                        res.put("XXCount", CountTemp + Count);
                    }
                }
    		}
    		break;
    		case "ZQ":{
    			res.put("GJCount", 0);
                res.put("XXCount", 0);
                if(isCount == 1){//该项目是否有分享项目
                	res.put("DTCount", 0);//动态数
                    res.put("YYCount", 0);//预约客户数
                    msgType = new String[] {
                            MessageType.带看通知.getTypeID(),
                            MessageType.认筹通知.getTypeID(),
                            MessageType.认购通知.getTypeID(),
                            MessageType.签约通知.getTypeID(),
                            MessageType.退房通知.getTypeID(),
                            MessageType.无效通知.getTypeID(),
                            MessageType.系统通知.getTypeID(),
                            MessageType.楼盘动态.getTypeID(),
                            MessageType.预约客户.getTypeID()};
                }else{
                	msgType = new String[] {
                            MessageType.带看通知.getTypeID(),
                            MessageType.认筹通知.getTypeID(),
                            MessageType.认购通知.getTypeID(),
                            MessageType.签约通知.getTypeID(),
                            MessageType.退房通知.getTypeID(),
                            MessageType.无效通知.getTypeID(),
                            MessageType.系统通知.getTypeID()};
                }
                List<UnreadCountVo> msgArray = UnreadCountListByMessageType_Select(msgType,map);
                for(UnreadCountVo msg : msgArray){
                	String TypeID = msg.getMessageType();
                    int Count = msg.getMessageCount();
                    if (MessageType.带看通知.getTypeID().equals(TypeID)
                        || MessageType.认筹通知.getTypeID().equals(TypeID)
                        || MessageType.认购通知.getTypeID().equals(TypeID)
                        || MessageType.签约通知.getTypeID().equals(TypeID)
                        || MessageType.退房通知.getTypeID().equals(TypeID)
                        || MessageType.无效通知.getTypeID().equals(TypeID)){
                        int CountTemp = (int) res.get("GJCount");
                        res.put("GJCount", CountTemp + Count);
                    }
                    if (MessageType.系统通知.getTypeID().equals(TypeID)){
                        int CountTemp = (int) res.get("XXCount");
                        res.put("XXCount", CountTemp + Count);
                    }
//                  if (isCount.Count > 0){ //该项目是否有分享项目,判断是否有一条查询结果（c#），0也算一条记录；
                  //Java中没有会返回0
                    	if (MessageType.楼盘动态.getTypeID().equals(TypeID)){
                            int CountTemp = ("null".equals(res.get("DTCount"))|| res.get("DTCount") == null) ? 0 : (int) res.get("DTCount");
                            res.put("DTCount", CountTemp + Count);
                        }
                        if (MessageType.预约客户.getTypeID().equals(TypeID)){
                            int CountTemp = ("null".equals(res.get("YYCount"))|| res.get("YYCount") == null) ? 0 : (int) res.get("YYCount");
                            res.put("YYCount", CountTemp + Count);
                        }
//                    }
                }
    		}
    		break;
    		}
    		return Result.ok(res);
    	}catch(Exception e){
    		e.printStackTrace();
    		return Result.errormsg(1,"系统异常，请联系管理员");
    	}
	}
	
	@ResponseBody
    @ApiOperation(value = "催办消息添加", notes = "催办消息添加")
    @RequestMapping(value = "/mMessageCBDetail_Insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mMessageCBDetail_Insert(@RequestBody JSONObject jsonParam) {
    	try{
    		Map paramMap = (HashMap)jsonParam.get("_param");
    		String MessageID = (String) paramMap.get("MessageID");
    		String UserID = (String) paramMap.get("UserID");
    		
    		Map<String,Object> map = new HashMap<String,Object>();
    		map.put("MessageID", MessageID);
    		//1.消息详情
    		List<Map<String,Object>> info = iSystemMessageService.SystemMessageDetail_Select(map);
    		if(info != null && info.size() > 0){
    			String ProjectID = (String) info.get(0).get("ProjectID");
    			String BizID = (String) info.get(0).get("BizID");
    			String BizType = (String) info.get(0).get("BizType");
    			String Receiver = (String) info.get(0).get("CBReceiver");
                int IsNeedPush = 1;
                String MessType = MessageType.催办.getTypeID();
                String strMessageType = (String) info.get(0).get("MessageType");
                
                if (strMessageType.equals(MessageType.首访信息录入逾期.getTypeID())){
                	MessType = MessageType.首访信息录入逾期催办.getTypeID();
                }
                if (strMessageType.equals(MessageType.跟进逾期.getTypeID())){
                	MessType = MessageType.跟进逾期催办.getTypeID();
                }
                if (strMessageType.equals(MessageType.认购逾期.getTypeID())){
                	MessType = MessageType.认购逾期催办.getTypeID();
                }
                if (strMessageType.equals(MessageType.签约逾期.getTypeID())){
                	MessType = MessageType.签约逾期催办.getTypeID();
                }
                if (strMessageType.equals(MessageType.回款逾期.getTypeID())){
                	MessType = MessageType.回款逾期催办.getTypeID();
                }
                map.put("ProjectID", ProjectID);
                map.put("BizID", BizID);
                map.put("BizType", BizType);
                map.put("Subject", MessType);
                map.put("Content", MessType);
                map.put("Receiver", Receiver);
                map.put("MessageType", MessType);
                map.put("Sender", UserID);
                map.put("Creator", UserID);
                map.put("IsNeedPush", IsNeedPush);
                iSystemMessageService.SystemMessageDetail_Insert(map);
                return Result.ok("消息添加成功");
    		}else{
    			return Result.errormsg(1,"消息信息无效");
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		return Result.errormsg(1,"系统异常，请联系管理员");
    	}
	}
	
	@ResponseBody
	@ApiOperation(value = "设消息为已读", notes = "设消息为已读")
	@RequestMapping(value = "/mMessageReadDetail_Update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mMessageReadDetail_Update(@RequestBody JSONObject jsonParam) {
		try{
			Map paramMap = (HashMap)jsonParam.get("_param");
			String MessageID = (String) paramMap.get("MessageID");
			String UserID = (String) paramMap.get("UserID");
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("MessageID", MessageID);
			map.put("UserID", UserID);
			//设消息为已读
			iSystemMessageService.SystemMessageReadDetail_Update(map);
			return Result.ok("设置已读成功");
		}catch(Exception e){
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ResponseBody
	@ApiOperation(value = "动态未读消息数更改", notes = "动态未读消息数更改")
	@RequestMapping(value = "/mMessageDynamicReadList_Update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mMessageDynamicReadList_Update(@RequestBody JSONObject jsonParam) {
		try{
			Map paramMap = (HashMap)jsonParam.get("_param");
			String ProjectID = (String) paramMap.get("ProjectID");
			String UserID = (String) paramMap.get("UserID");
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("ProjectID", ProjectID);
			map.put("UserID", UserID);
			//设消息为已读
			iSystemMessageService.mMessageDynamicReadList_Update(map);
			return Result.ok("设置动态消息已读成功");
		}catch(Exception e){
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ResponseBody
	@ApiOperation(value = "消息日历", notes = "消息日历")
	@RequestMapping(value = "/mMessageCalendar_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mMessageCalendar_Select(@RequestBody JSONObject jsonParam) {
		try{
			Map paramMap = (HashMap)jsonParam.get("_param");
			return Result.ok(iSystemMessageService.mMessageCalendar_Select(paramMap));
		}catch(Exception e){
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
}
