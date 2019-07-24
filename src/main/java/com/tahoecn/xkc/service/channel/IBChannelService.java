package com.tahoecn.xkc.service.channel;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.customer.BClue;
import com.tahoecn.xkc.model.vo.ChannelRegisterModel;
import com.tahoecn.xkc.model.vo.RegisterRuleBaseModel;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-06-25
 */
public interface IBChannelService extends IService<BClue> {

	ChannelRegisterModel newChannelRegisterModel(String userId, String adviserGroupID, String projecId);

	Map<String, Object> ValidateForReport(String mobile, String projectId, ChannelRegisterModel channelRegisterModel);

	String GetMessageForReturn(int InvalidType, RegisterRuleBaseModel userRule);
	/**
	 * 现场确认验证
	 */
//	Map<String,Object> ValidateForConfirmation(String ClueID,ChannelRegisterModel channel);

	/**
	 * 扫码确认
	 */
	Result mCaseFieCustomerDetail_Select(JSONObject jsonParam);
}
