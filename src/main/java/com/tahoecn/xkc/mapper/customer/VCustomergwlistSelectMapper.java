package com.tahoecn.xkc.mapper.customer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tahoecn.xkc.model.customer.VCustomergwlistSelect;
import com.tahoecn.xkc.model.dto.GWCustomerPageDto;
import com.tahoecn.xkc.model.vo.CustomerActionVo;
import com.tahoecn.xkc.model.vo.GWCustomerPageVo;

/**
 * <p>
 * 1 Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-01
 */
public interface VCustomergwlistSelectMapper extends BaseMapper<VCustomergwlistSelect> {
	/**
	 * 置业顾问客户列表
	 * @param gWCustomerPageDto
	 * @return
	 */
	List<GWCustomerPageVo> sCustomerGWListNew_Select(GWCustomerPageDto gWCustomerPageDto);
	
	Long sCustomerGWListNew_Select_count(GWCustomerPageDto gWCustomerPageDto);
	
	/**
	 * 置业顾问客户基本信息1
	 * @param opportunityID
	 * @param projectID
	 * @return
	 */
	HashMap<String,Object> sCustomerGWBase_Select(@Param("opportunityID")String opportunityID,@Param("projectID")String projectID);
	
	/**
	 * 置业顾问客户基本信息2
	 * @param opportunityID
	 * @param projectID
	 * @return
	 */
	List<HashMap<String,Object>> sCustomerGWBaseSalerInfo_Select(@Param("opportunityID")String opportunityID,@Param("projectID")String projectID,@Param("siteUrl")String siteUrl);
	
	/**
	 * 置业顾问客跟进记录
	 * @param page
	 * @param opportunityID
	 * @param projectID
	 * @return
	 */
	IPage<Map<String,Object>> mCustomerFollowUpList_Select(IPage<Map<String,Object>> page,@Param("opportunityID")String opportunityID,@Param("projectID")String projectID);
	
	/**
	 * 置业顾问销售轨迹
	 * @param page
	 * @param opportunityID
	 * @return
	 */
	IPage<Map<String,Object>> sCustomerTrackList_Select(IPage<Map<String,Object>> page,@Param("opportunityID")String opportunityID);
	
	/**
	 * 置业顾问 新增跟进记录 开启异地销售，置业顾问申请客户丢失后，协作人不能对客户进行操作，查询符合条件的记录
	 * @param opportunityID
	 * @param userID
	 * @return
	 */
	List<Map<String,Object>> mCustomerSalePartnerIsCanOperate_Select(@Param("opportunityID")String opportunityID,@Param("userID")String userID);
	
	/**
	 * 置业顾问 新增跟进记录 客户跟进记录新增
	 * @param opportunityID
	 * @param customerID
	 * @param userID
	 * @param mode
	 */
	void mCustomerFollowUpDetail_Insert(@Param("opportunityID")String opportunityID,@Param("customerID")String customerID,@Param("userID")String userID,@Param("mode")String mode);
	
	Map<String,Object> CFGetClueRule_Select(@Param("clueID")String clueID);
	
	Map<String,Object> CFGetOppRule_Select(@Param("opportunityID")String opportunityID);
	
	Map<String,Object> CFIsOwnerChannel_Select(@Param("channelTypeID")String channelTypeID);
	
	/**
	 * 置业顾问 创建跟进跟访记录1
	 * @param customerActionVo
	 */
	void CFCreateClueFollowUp_Insert(CustomerActionVo customerActionVo);
	
	/**
	 * 置业顾问 创建跟进跟访记录2
	 * @param customerActionVo
	 */
	void CFCreateOppFollowUp_Insert(CustomerActionVo customerActionVo);
	
	/**
	 * 置业顾问  渠道到访消息设置查询
	 * @param opportunityID
	 * @param clueID
	 * @param projectID
	 * @return
	 */
	Map<String,Object> RemindRuleArriveDetail_Select(@Param("opportunityID")String opportunityID,@Param("clueID")String clueID,@Param("projectID")String projectID);
	
	Map<String,Object> RemindRuleArriveDetail_Select_f(@Param("projectID")String projectID,@Param("protectSource")String protectSource);
	
	Map<String,Object> RemindRuleArriveDetail_Select_s(@Param("clueID")String clueID);
	/**
	 * 置业顾问 客户机会跟进信息更新1
	 * @param opportunityID
	 */
	void CustomerOpportunityFollowUpDetail_Update_insert(@Param("opportunityID")String opportunityID);
	
	/**
	 * 置业顾问 客户机会跟进信息更新2
	 * @param opportunityID
	 */
	void CustomerOpportunityFollowUpDetail_Update(@Param("opportunityID")String opportunityID,@Param("userID")String userID);
	
	/**
	 * 置业顾问 客户机会跟进信息更新3
	 * @param opportunityID
	 * 项目渠道设置客户为永久保护的话，则客户报备、到访都不会有成交保护期，适用身份：中介同行、老业主、自由经纪、员工推荐    
	 * 自渠客户没有成交保护期  
	 */
	void mClueTradeOverdueTime_Update(@Param("opportunityID")String opportunityID);
}
