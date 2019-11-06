package com.tahoecn.xkc.service.sys.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tahoecn.log.Log;
import com.tahoecn.log.LogFactory;
import com.tahoecn.xkc.mapper.sys.RuleRemindMapper;
import com.tahoecn.xkc.service.sys.IRuleRemindService;

/**
 * TODO 消息推送服务类
 * 
 * @ClassName: RuleRemindServiceImpl
 * @author: wq_qycc
 * @date: 2019年8月2日
 */
@Service
public class RuleRemindServiceImpl implements IRuleRemindService {
	private static final Log log = LogFactory.get();

	@Autowired
	RuleRemindMapper remindMapper;

	/**
	 * 案场消息提醒NEW
	 */
	@Override
	public void serviceAutoRemindNew() {

		List<Map<String, Object>> list = remindMapper.RemindRuleList_Select();
		if (list != null && list.size() > 0) {
			String ps = "";
			for (Map<String, Object> map : list) {
				String projectId = String.valueOf(map.get("ProjectID"));
				if (StringUtils.isNotBlank(projectId) && !ps.contains(projectId)) {
					ps += projectId + ",";
					// 置业顾问消息
					Integer WaitForVisit = objToInt(map.get("WaitForVisit"));
					// Integer OverdueUnFollowup =
					// objToInt(map.get("OverdueUnFollowup"));//跟进逾期提醒
					Integer OverdueFollowupAgo = objToInt(map.get("OverdueFollowupAgo"));// 跟进逾期前提醒
					Integer OverdueFollowupAgoTime = objToInt(map.get("OverdueFollowupAgoTime"));// 跟进逾期前多少天提醒
					Integer OverdueUnSub = objToInt(map.get("OverdueUnSub"));// 认购逾期
					Integer OverdueSubAgo = objToInt(map.get("OverdueSubAgo"));// 认购逾期前是否选中
					Integer OverdueSubAgoTime = objToInt(map.get("OverdueSubAgoTime"));// 认购逾期前多少天提醒
					Integer OverdueUnSign = objToInt(map.get("OverdueUnSign"));// 签约逾期
					Integer OverdueSignAgo = objToInt(map.get("OverdueSignAgo"));// 签约逾期前是否选中
					Integer OverdueSignAgoTime = objToInt(map.get("OverdueSignAgoTime"));// 签约逾期前多少天
					Integer OverdueUnPayment = objToInt(map.get("OverdueUnPayment"));// 逾期未回款
					Integer OverduePaymentAgo = objToInt(map.get("OverduePaymentAgo"));// 回款逾期前是否选中
					Integer OverduePaymentAgoTime = objToInt(map.get("OverduePaymentAgoTime"));// 回款逾期前多少天

					if (WaitForVisit == 1) {
						sendMessage("WaitingForTodayList_Select", "今日待跟进", "487F2C39-779D-097B-455B-799AC0B3CBB4",
								projectId, 0, null);
					}
					if (OverdueFollowupAgo == 1 && OverdueFollowupAgoTime > 0) {
						sendMessage("FollowUpOverdueList_Select", "跟进逾期前", "12D36558-A8A1-20D4-58E5-612338026AE7",
								projectId, 0, null);
					}
					if (OverdueUnSub == 1) {
						sendMessage("OverdueSubList_Select", "逾期未认购", "0848126B-E21C-40DD-82C0-1ACFF0A39552", projectId,
								0, "", 0);
					}
					if (OverdueSubAgo == 1 && OverdueSubAgoTime > 0) {
						sendMessage("OverdueSubList_Select", "认购即将逾期", "B5CB4E80-B0D2-959A-7FFB-1C391FF9AD9E",
								projectId, 0, "", OverdueSubAgoTime);
					}
					if (OverdueUnSign == 1) {
						sendMessage("OverdueSignList_Select", "逾期未签约", "D138B44C-932D-43A4-8735-4A36AA1784A2",
								projectId, 0, "", 0);
					}
					if (OverdueSignAgo == 1 && OverdueSignAgoTime > 0) {
						sendMessage("OverdueSignList_Select", "逾期即将签约", "BE78B012-2536-DFDC-0D20-A5A86DD3470F",
								projectId, 0, "", OverdueSignAgoTime);
					}
					if (OverdueUnPayment == 1) {
						sendMessage("OverduePaymentList_Select", "回款逾期", "87BE4B55-8920-4204-8FD6-77AF5130BCD2",
								projectId, 0, "", 0);
					}
					if (OverduePaymentAgo == 1 && OverduePaymentAgoTime > 0) {
						sendMessage("OverduePaymentList_Select", "回款即将逾期", "0F9709DD-A8FC-6106-99FC-688E10C760B1",
								projectId, 0, "", OverduePaymentAgoTime);
					}

					// 销售团队负责人/营销负责人
					// Integer OverdueFollowupM =
					// objToInt(map.get("OverdueFollowupM"));//跟进逾期提醒
					// Integer OverdueFollowupAgoM=
					// objToInt(map.get("OverdueFollowupAgoM"));//跟进逾期前提醒
					// Integer OverdueFollowupAgoTimeM =
					// objToInt(map.get("OverdueFollowupAgoTimeM"));//跟进逾期前多少天提醒
					Integer OverdueUnSubM = objToInt(map.get("OverdueUnSubM"));// 认购逾期提醒
					Integer OverdueSubAgoM = objToInt(map.get("OverdueSubAgoM"));// 认购逾期前提醒
					Integer OverdueSubAgoTimeM = objToInt(map.get("OverdueSubAgoTimeM"));// 认购逾期前多少天提醒
					Integer OverdueSubM = objToInt(map.get("OverdueSubM"));// 认购逾期后是否选中
					Integer OverdueSubTimeM = objToInt(map.get("OverdueSubTimeM"));// 认购逾期后多少天提醒
					Integer OverdueUnSignM = objToInt(map.get("OverdueUnSignM"));// 签约逾期
					Integer OverdueSignAgoM = objToInt(map.get("OverdueSignAgoM"));// 签约逾期前是否选中
					Integer OverdueSignAgoTimeM = objToInt(map.get("OverdueSignAgoTimeM"));// 签约逾期前多少天
					Integer OverdueSignM = objToInt(map.get("OverdueSignM"));// 签约逾期后
					Integer OverdueSignMTime = objToInt(map.get("OverdueSignMTime"));// 签约逾期后多少天
					Integer OverdueUnPaymentM = objToInt(map.get("OverdueUnPaymentM"));// 回款逾期
					Integer OverduePaymentAgoM = objToInt(map.get("OverduePaymentAgoM"));// 回款逾期前
					Integer OverduePaymentAgoTimeM = objToInt(map.get("OverduePaymentAgoTimeM"));// 回款逾期前多少天
					Integer OverduePaymentM = objToInt(map.get("OverduePaymentM"));// 回款逾期后是否选中
					Integer OverduePaymentMTime = objToInt(map.get("OverduePaymentMTime"));// 回款逾期后多少天

					// if (OverdueFollowupAgoM==1&&OverdueFollowupAgoTimeM>0) {
					// sendMessage("FollowUpOverdueMList_Select", "跟进逾期前",
					// "12D36558-A8A1-20D4-58E5-612338026AE7", projectId, 0,"",
					// OverdueFollowupAgoTimeM);
					// }
					if (OverdueUnSubM == 1) {
						sendMessage("OverdueSubMList_Select", "逾期未认购", "0848126B-E21C-40DD-82C0-1ACFF0A39552",
								projectId, 0, "", 0);
					}
					if (OverdueSubAgoM == 1 && OverdueSubAgoTimeM > 0) {
						sendMessage("OverdueSubMBeforeList_Select", "认购即将逾期", "B5CB4E80-B0D2-959A-7FFB-1C391FF9AD9E",
								projectId, 0, "", -OverdueSubAgoTimeM);
					}
					if (OverdueSubM == 1 && OverdueSubTimeM > 0) {
						sendMessage("OverdueSubMAfterList_Select", "认购逾期后", "0848126B-E21C-40DD-82C0-1ACFF0A39552",
								projectId, 0, "", OverdueSubTimeM);
					}
					if (OverdueUnSignM == 1) {
						sendMessage("OverdueSignMList_Select", "逾期未签约", "D138B44C-932D-43A4-8735-4A36AA1784A2",
								projectId, 0, "", 0);
					}
					if (OverdueSignAgoM == 1 && OverdueSignAgoTimeM > 0) {
						sendMessage("OverdueSignMBeforeList_Select", "签约即将逾期", "BE78B012-2536-DFDC-0D20-A5A86DD3470F",
								projectId, 0, "", OverdueSignAgoTimeM);
					}
					if (OverdueSignM == 1 && OverdueSignMTime > 0) {
						sendMessage("OverdueSignMAfterList_Select", "签约逾期后", "D138B44C-932D-43A4-8735-4A36AA1784A2",
								projectId, 0, "", -OverdueSignMTime);
					}
					if (OverdueUnPaymentM == 1) {
						sendMessage("OverdueUnPaymentMList_Select", "回款逾期", "87BE4B55-8920-4204-8FD6-77AF5130BCD2",
								projectId, 0, "", 0);
					}
					if (OverduePaymentAgoM == 1 && OverduePaymentAgoTimeM > 0) {
						sendMessage("OverdueUnPaymentMBeforeList_Select", "回款逾期前",
								"0F9709DD-A8FC-6106-99FC-688E10C760B1", projectId, 0, "", -OverduePaymentAgoTimeM);
					}
					if (OverduePaymentM == 1 && OverduePaymentMTime > 0) {
						sendMessage("OverdueUnPaymentMAfterList_Select", "回款逾期后",
								"87BE4B55-8920-4204-8FD6-77AF5130BCD2", projectId, 0, "", OverduePaymentMTime);
					}

				}
			}
		}

		// 更改已经的逾期的即将逾期的消息类型为失效
		remindMapper.ToFollowOverdueDate_Update();
	}

	/**
	 * 渠道消息提醒
	 */
	@Override
	public void serviceAutoRemind_QD() {

		List<Map<String, Object>> list = remindMapper.ChannelMessageList_Select();
		for (Map<String, Object> map : list) {
			String projectId = String.valueOf(map.get("ProjectID"));
			Integer ChannelSource = objToInt(map.get("ChannelSource"));
			// Integer CustomerVisitsRemind =
			// objToInt(map.get("CustomerVisitsRemind"));// 客户到访提醒
			// Integer AllotRemind = objToInt(map.get("AllotRemind"));//
			// 客户分配提醒
			Integer RecognizeRemind = objToInt(map.get("RecognizeRemind"));// 客户认筹提醒
			Integer OverdueSubRemind = objToInt(map.get("OverdueSubRemind"));// 客户认购提醒
			Integer OverdueSignRemind = objToInt(map.get("OverdueSignRemind"));// 客户签约提醒
			Integer CheckOutRemind = objToInt(map.get("CheckOutRemind"));// 客户退房提醒
			Integer CustomerInvalidRemind = objToInt(map.get("CustomerInvalidRemind"));// 客户无效提醒
			if (ChannelSource == 0)// 推荐渠道
			{
				if (RecognizeRemind == 1)// 客户认筹提醒
					sendMessage("RecognizeRemindList_Select", "客户认筹提醒", "FE54E9CB-C0EC-45A9-B139-A47BAC9DA7CF",
							projectId, 0, "Clue");
				if (OverdueSubRemind == 1)// 客户认购提醒
					sendMessage("OverdueSubRemindList_Select", "客户认购提醒", "814E3403-C0F3-43DC-9763-7C175595DA0F",
							projectId, 0, "Clue");
				if (OverdueSignRemind == 1)// 客户签约提醒
					sendMessage("OverdueSignRemindList_Select", "客户签约提醒", "0F45C158-A9B0-40A9-B9DD-6333A4E99245",
							projectId, 0, "Clue");
				if (CheckOutRemind == 1)// 客户退房提醒
					sendMessage("CheckOutRemindList_Select", "客户退房提醒", "EB7423A9-3C97-4E87-BDF2-2C1DDCC55FE2",
							projectId, 0, "Clue");
				if (CustomerInvalidRemind == 1)// 客户无效提醒
					sendMessage("CustomerInvalidRemindList_Select", "客户无效提醒", "3698A744-03D6-4715-A642-70455A1C7E9A",
							projectId, 0, "Clue");
			} else if (ChannelSource == 1)// 自有渠道
			{
				if (RecognizeRemind == 1)// 客户认筹提醒
					sendMessage("RecognizeRemindList_Select", "客户认筹提醒", "FE54E9CB-C0EC-45A9-B139-A47BAC9DA7CF",
							projectId, 1, "Clue");
				if (OverdueSubRemind == 1)// 客户认购提醒
					sendMessage("OverdueSubRemindList_Select", "客户认购提醒", "814E3403-C0F3-43DC-9763-7C175595DA0F",
							projectId, 1, "Clue");
				if (OverdueSignRemind == 1)// 客户签约提醒
					sendMessage("OverdueSignRemindList_Select", "客户签约提醒", "0F45C158-A9B0-40A9-B9DD-6333A4E99245",
							projectId, 1, "Clue");
				if (CheckOutRemind == 1)// 客户退房提醒
					sendMessage("CheckOutRemindList_Select", "客户退房提醒", "EB7423A9-3C97-4E87-BDF2-2C1DDCC55FE2",
							projectId, 1, "Clue");
				if (CustomerInvalidRemind == 1)// 客户无效提醒
					sendMessage("CustomerInvalidRemindList_Select", "客户无效提醒", "3698A744-03D6-4715-A642-70455A1C7E9A",
							projectId, 1, "Clue");
				// 跟进逾期前提醒
				sendMessage("FollowUpOverdueListZQ_Select", "跟进逾期前", "12D36558-A8A1-20D4-58E5-612338026AE7", projectId,
						1, "Clue");
			} else if (ChannelSource == 2)// 分销渠道
			{
				if (RecognizeRemind == 1)// 客户认筹提醒
					sendMessage("RecognizeRemindList_Select", "客户认筹提醒", "FE54E9CB-C0EC-45A9-B139-A47BAC9DA7CF",
							projectId, 2, "Clue");
				if (OverdueSubRemind == 1)// 客户认购提醒
					sendMessage("OverdueSubRemindList_Select", "客户认购提醒", "814E3403-C0F3-43DC-9763-7C175595DA0F",
							projectId, 2, "Clue");
				if (OverdueSignRemind == 1)// 客户签约提醒
					sendMessage("OverdueSignRemindList_Select", "客户签约提醒", "0F45C158-A9B0-40A9-B9DD-6333A4E99245",
							projectId, 2, "Clue");
				if (CheckOutRemind == 1)// 客户退房提醒
					sendMessage("CheckOutRemindList_Select", "客户退房提醒", "EB7423A9-3C97-4E87-BDF2-2C1DDCC55FE2",
							projectId, 2, "Clue");
				if (CustomerInvalidRemind == 1)// 客户无效提醒
					sendMessage("CustomerInvalidRemindList_Select", "客户无效提醒", "3698A744-03D6-4715-A642-70455A1C7E9A",
							projectId, 2, "Clue");
			}
		}

	}

	private Integer objToInt(Object obj) {
		if (obj != null)
			return Integer.valueOf(String.valueOf(obj));
		return 0;
	}

	private void sendMessage(String dataID, String subject, String messageType, String projectID, Integer overdueTime,
			String Unit, Integer Time) {
		Map<String, Object> map = new HashMap<>();
		map.put("ProjectID", projectID);
		map.put("OverdueTime", overdueTime);
		map.put("Unit", Unit);
		map.put("Time", Time);
		map.put("MessageType", messageType);

		try {
			Object obj = RuleRemindMapper.class.getMethod(dataID, Map.class).invoke(remindMapper, map);
			if (obj != null) {
				@SuppressWarnings("unchecked")
				List<Map<String, Object>> list = (List<Map<String, Object>>) obj;
				if (list != null && list.size() > 0)
					insertMessage(projectID, subject, "Opportunity", messageType, list);
			}
		} catch (Exception e) {
			log.error(e);
		}
	}

	private void sendMessage(String dataID, String subject, String messageType, String projectID, Integer Source,
			String BizType) {
		Map<String, Object> map = new HashMap<>();
		map.put("ProjectID", projectID);
		map.put("Source", Source);
		map.put("MessageType", messageType);

		try {
			Object obj = RuleRemindMapper.class.getMethod(dataID, Map.class).invoke(remindMapper, map);
			if (obj != null) {
				@SuppressWarnings("unchecked")
				List<Map<String, Object>> list = (List<Map<String, Object>>) obj;
				if (list != null && list.size() > 0)
					insertMessage(projectID, subject, BizType, messageType, list);
			}
		} catch (Exception e) {
			log.error(e);
		}
	}

	private void insertMessage(String projectID, String subject, String BizType, String messageType,
			List<Map<String, Object>> list) {
		List<Map<String, Object>> il = new ArrayList<>();
		for (Map<String, Object> map : list) {
			if (map.get("Receiver") != null && "".equals(map.get("Receiver")))
				continue;
			Map<String, Object> jo = new HashMap<>();
			jo.put("ProjectID", StringUtils.isBlank(projectID) ? map.get("ProjectID") : projectID);
			jo.put("BizID", map.get("BizID"));
			jo.put("BizType", BizType);
			jo.put("Subject", subject);

			jo.put("MessageType", messageType);
			jo.put("Receiver", map.get("Receiver"));
			jo.put("Ext1", map.get("Ext1"));
			jo.put("Ext2", map.get("Ext2"));
			switch (messageType) {
			case "FE54E9CB-C0EC-45A9-B139-A47BAC9DA7CF":// 客户认筹提醒
				jo.put("Content", "客户" + map.get("CustomerName") + "、" + map.get("CustomerMobile") + "(客户认筹提醒)");
				break;
			case "814E3403-C0F3-43DC-9763-7C175595DA0F":// 客户认购提醒
				jo.put("Content", "客户" + map.get("CustomerName") + "、" + map.get("CustomerMobile") + "(客户认购提醒)");
				break;
			case "0F45C158-A9B0-40A9-B9DD-6333A4E99245":// 客户签约提醒
				jo.put("Content", "客户" + map.get("CustomerName") + "、" + map.get("CustomerMobile") + "(客户签约提醒)");
				break;
			case "EB7423A9-3C97-4E87-BDF2-2C1DDCC55FE2":// 客户退房提醒
				jo.put("Content", "客户" + map.get("CustomerName") + "、" + map.get("CustomerMobile") + "(客户退房提醒)");
				break;
			case "3698A744-03D6-4715-A642-70455A1C7E9A":// 客户无效提醒
				jo.put("Content", "客户" + map.get("CustomerName") + "、" + map.get("CustomerMobile") + "(客户无效提醒)");
				break;
			default:
				jo.put("Content", subject);
				break;
			}
			il.add(jo);
		}
		if (il.size() > 0)
			remindMapper.RemindMessage_Insert(il);
	}
}
