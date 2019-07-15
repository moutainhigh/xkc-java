package com.tahoecn.xkc.mapper.customer;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.customer.BOpportunitygiveup;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-15
 */
public interface BOpportunitygiveupMapper extends BaseMapper<BOpportunitygiveup> {
	/**
	 * 机会列表中是否存在丢失中的机会
	 */
	List<Map<String, Object>> sOpportunityGiveUpListDetail_Select(Map<String, Object> paramMap);

	void updMessByOpportunity(Map<String, Object> paramMap);
	void updMessByOpportunityGiveUp(Map<String, Object> paramMap);
	void insCustomerFollowUp(Map<String, Object> paramMap);
	void upOpportunity(Map<String, Object> paramMap);
	void insCustomerTrack(Map<String, Object> paramMap);
	void upCustomerPublicPool(Map<String, Object> paramMap);
	void insMessage(Map<String, Object> paramMap);
	void insTask(Map<String, Object> paramMap);
	/**
	 * 协作人置空
	 */
	void mCustomerYXJLSalePartnerSetNull_Update(Map<String, Object> paramMap);


}
