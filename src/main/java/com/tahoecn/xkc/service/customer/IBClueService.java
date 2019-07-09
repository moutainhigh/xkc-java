package com.tahoecn.xkc.service.customer;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.customer.BClue;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-03
 */
public interface IBClueService extends IService<BClue> {

    Map<String, Object> mBrokerCustomerDetail_Select(String clueID);

    Map<String,Object> ValidateForReport(String userID, String mobile, String projectId, Map<String, Object> map);

    boolean IsExistReportProtectClue_Select(String projectId, String mobile);

    List<Map<String, Object>> RuleClueList_Select(String projectId, String mobile, String userID);

    String getMessage(int invalidType, Map<String, Object> map);

    String GetMessageForReturn(int invalidType, Map<String, Object> map);

    boolean createClue(String channelOrgId, Map<String, Object> ruleValidate, Map<String, Object> map, int status, Map paramMap);
}
