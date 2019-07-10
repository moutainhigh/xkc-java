package com.tahoecn.xkc.mapper.customer;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.customer.BClue;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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
	List<Map<String, Object>> CaseFieToBeConfirmedList_Select(Map<String, Object> parameter);
}
