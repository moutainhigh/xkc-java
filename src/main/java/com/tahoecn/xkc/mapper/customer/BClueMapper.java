package com.tahoecn.xkc.mapper.customer;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tahoecn.xkc.model.clue.CStatus;
import com.tahoecn.xkc.model.customer.BClue;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-03
 */
public interface BClueMapper extends BaseMapper<BClue> {

    Map<String, Object> BrokerClueDetail_Select(@Param("ClueID") String clueID);

    List<Map<String, Object>> TrackList_Select(@Param("ClueID")String clueID);

    Map<String, Object> IsExistReportProtectClue_Select(@Param("IntentProjectID")String projectId, @Param("Mobile")String mobile);

    List<Map<String, Object>> RuleClueList_Select(@Param("IntentProjectID")String projectId, @Param("Mobile")String mobile, @Param("ReportUserID")String userID);
    /**
	 * 案场助手统计
	 */
	Map<String, Object> CaseFieDetail_Select(Map<String, Object> paramMap);
	/**
	 * 客户信息
	 */
	List<Map<String, Object>> CaseFieCustomerDetail_Select(Map<String, Object> paramMap);
	/**
	 * 客户无效信息
	 */
	List<Map<String, Object>> CaseFieInvalDetail_Select(Map<String, Object> paramMap);
	/**
	 * 验证是否存超过到访保护期
	 */
	Map<String, Object> IsOverdueCome_Select(Map<String, Object> obj);
	/**
	 * 扫码确认线索无效后，更新线索信息
	 */
	void ClueConfirmInvalid_Update(Map parameter);
	/**
	 * 根据手机号、项目id获取有效线索
	 */
	List<Map<String, Object>> RuleClueList_Select(Map<String, Object> obj);

	Object ClueConfirm_Update(Map parameter);
	/**
	 * 待确认查询
	 */
	IPage<Map<String, Object>> CaseFieToBeConfirmedList_Select(IPage page, 
			 @Param("Status")String Status,  @Param("ProjectID")String ProjectID, 
			 @Param("sqlWhere")String sqlWhere);
	/**
	 * 待分配
	 */
	IPage<Map<String, Object>> CaseFieDistributionList_Select(IPage page, @Param("ProjectID")String ProjectID,
			@Param("UserID")String UserID, @Param("sqlWhere")String sqlWhere);
	/**
	 * 报备信息列表
	 */
	IPage<Map<String, Object>> CaseFielInquiriesList_Select(IPage page, @Param("ProjectID")String ProjectID, @Param("sqlWhere")String sqlWhere);
	
	List<CStatus> selectCustomerStatus(@Param("clueId") String clueId);

	//验证是否重复报备
	String isRepeatedReg(@Param("mobile") String mobile, @Param("projectId") String projectId, @Param("reportUserId") String reportUserId);
	//验证是否已经被他人报备
	String isProtected(@Param("mobile") String mobile, @Param("projectId") String projectId, @Param("reportUserId") String reportUserId);

    List<Map<String, Object>> IsProjectOwner_Select(@Param("projectId")String projectId, @Param("mobile") String mobile);

    Map<String, Object> getCustomerID(@Param("oppID")String oppID);

    void CustomerTrack_Insert(@Param("oppID")String oppID,@Param("customerID") String customerID, @Param("clueID")String clueID);

    void updateComeOverdueTimeByDay(@Param("extendArriveProDays")Integer extendArriveProDays, @Param("clueRuleId")String clueRuleId);

	void updateComeOverdueTimeByDate(@Param("extendArriveProEndDate")Date extendArriveProEndDate, @Param("clueRuleId")String clueRuleId);

	void updateTradeOverdueTimeByDay(@Param("extendSigningProDays")Integer extendSigningProDays, @Param("clueRuleId")String clueRuleId);

	void updateTradeOverdueTimeByDate(@Param("extendSigningProEndDate")Date extendSigningProEndDate, @Param("clueRuleId")String clueRuleId);
}
