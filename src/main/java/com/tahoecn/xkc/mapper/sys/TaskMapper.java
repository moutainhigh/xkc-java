package com.tahoecn.xkc.mapper.sys;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tahoecn.xkc.common.annotation.DataSource;
import com.tahoecn.xkc.common.enums.DataSourceEnum;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-25
 */
public interface TaskMapper {

	/**
	 * 更新任务状态
	 * 
	 * @param map
	 */
	void TaskDetail_Update(Map<String, Object> map);

	/**
	 * 插入应收数据
	 */
	void TaskGetinDetail_Insert();

	/**
	 * 插入发票数据
	 */
	void TaskVoucherDetail_Insert();

	/**
	 * 插入月度计划数据
	 */
	void TaskMonthYsDetail_Insert();

	/**
	 * 插入销售数据
	 */
	void TaskOC2SaleDetail_Insert();

	/**
	 * 获取同步客户列表
	 * 
	 * @return
	 */
	List<Map<String, Object>> TaskSyncCustomerList_Select();

	/**
	 * 机会客户ID信息
	 * 
	 * @param OpportunityID
	 * @return
	 */
	String TaskOpportunityCustomerID_Select(@Param("OpportunityID") String OpportunityID);

	/**
	 * 查询本地客户信息
	 * 
	 * @param CustomerID
	 * @return
	 */
	@DataSource(DataSourceEnum.DB2)
	Map<String, Object> LocalCustomerDetail_Select(@Param("CustomerID") String CustomerID);

	/**
	 * 添加用户信息
	 * 
	 * @param map
	 */
	@DataSource(DataSourceEnum.DB2)
	void MYUserDetail_Insert(Map<String, Object> map);

	/**
	 * 添加明源客户信息
	 * 
	 * @param map
	 */
	@DataSource(DataSourceEnum.DB2)
	void MYCustomerDetail_Insert(Map<String, Object> map);

	/**
	 * 查询本地机会信息
	 * 
	 * @param OpportunityID
	 * @return
	 */
	@DataSource(DataSourceEnum.DB2)
	List<Map<String, Object>> LocalOpportunityDetail_Select(@Param("OpportunityID") String OpportunityID);

	/**
	 * 查询本地机会客户信息
	 * 
	 * @param OpportunityID
	 * @return
	 */
	@DataSource(DataSourceEnum.DB2)
	List<Map<String, Object>> LocalOpportunityCustomerList_Select(@Param("OpportunityID") String OpportunityID);

	/**
	 * 添加明源机会信息
	 * 
	 * @param map
	 */
	@DataSource(DataSourceEnum.DB2)
	void MYOpportunityDetail_Insert(Map<String, Object> map);

	/**
	 * 删除机会客户信息
	 * 
	 * @param map
	 */
	@DataSource(DataSourceEnum.DB2)
	void MyOpportunityCustomerDetail_Delete(Map<String, Object> map);

	/**
	 * 添加机会客户信息
	 * 
	 * @param map
	 */
	@DataSource(DataSourceEnum.DB2)
	void MyOpportunityCustomerDetail_Insert(Map<String, Object> map);

	/**
	 * 获取明源项目列表
	 * 
	 * @return
	 */
	@DataSource(DataSourceEnum.DB2)
	List<Map<String, Object>> MYProjectList_Select();

	/**
	 * 清空本地明源项目数据
	 */
	void TaskMYProjectList_Delete();

	/**
	 * 插入本地明源项目数据
	 * 
	 * @param list
	 * @return
	 */
	Integer TaskMYProjectDetail_Insert(Map<String, Object> map);

	/**
	 * 插入项目数据
	 */
	void TaskProjectDetail_Insert();

	/**
	 * 获取本地明源机会最后数据时间戳
	 * 
	 * @return
	 */
	Long TaskMYOpportunityDetailLast_Select();

	/**
	 * 获取明源机会列表
	 * 
	 * @param LastTime
	 * @return
	 */
	@DataSource(DataSourceEnum.DB2)
	List<Map<String, Object>> MYOpportunityList_Select(@Param("LastTime") Long lastTime);

	/**
	 * 插入本地明源机会信息
	 * 
	 * @param list
	 */
	void TaskMYOpportunityDetail_Insert(Map<String, Object> map);

	/**
	 * 插入机会数据
	 */
	void TaskOpportunityDetail_Insert();

	/**
	 * 插入销售变更数据
	 * 
	 * @return
	 */
	Long TaskSaleModiApplyDetail_Insert();

	/**
	 * 获取本地明源房间最后数据时间戳
	 * 
	 * @return
	 */
	Long TaskMYRoomDetailLast_Select();

	/**
	 * 获取明源房间列表
	 * 
	 * @param LastTime
	 * @return
	 */
	@DataSource(DataSourceEnum.DB2)
	List<Map<String, Object>> MYRoomList_Select(@Param("LastTime") Long lastTime);

	/**
	 * 插入本地明源房间信息
	 * 
	 * @param map
	 */
	void TaskMYRoomDetail_Insert(Map<String, Object> map);

	/**
	 * 插入房间数据
	 */
	void TaskRoomDetail_Insert();

	/**
	 * 获取本地明源楼栋最后数据时间戳
	 * 
	 * @return
	 */
	Long TaskMYBuildingDetailLast_Select();

	/**
	 * 获取明源楼栋列表
	 * 
	 * @param LastTime
	 * @return
	 */
	@DataSource(DataSourceEnum.DB2)
	List<Map<String, Object>> MYBuildingList_Select(@Param("LastTime") Long lastTime);

	/**
	 * 插入本地明源楼栋信息
	 * 
	 * @param map
	 */
	void TaskMYBuildingDetail_Insert(Map<String, Object> map);

	/**
	 * 插入楼栋数据
	 */
	void TaskBuildingDetail_Insert();

	/**
	 * 获取本地明源合同最后数据时间戳
	 * 
	 * @return
	 */
	Long TaskMYContractDetailLast_Select();

	/**
	 * 获取明源合同列表
	 * 
	 * @param LastTime
	 * @return
	 */
	@DataSource(DataSourceEnum.DB2)
	List<Map<String, Object>> MYContractList_Select(@Param("LastTime") Long lastTime);

	/**
	 * 插入本地明源合同数据
	 * 
	 * @param map
	 */
	void TaskMYContractDetail_Insert(Map<String, Object> map);

	/**
	 * 插入合同数据
	 */
	void TaskContractDetail_Insert();

	/**
	 * 获取本地明源费用最后数据时间戳
	 * 
	 * @return
	 */
	Long TaskMYFeeDetailLast_Select();

	/**
	 * 获取明源费用列表
	 * 
	 * @param LastTime
	 * @return
	 */
	@DataSource(DataSourceEnum.DB2)
	List<Map<String, Object>> MYFeeList_Select(@Param("LastTime") Long lastTime);

	/**
	 * 插入本地明源费用数据
	 * 
	 * @param map
	 */
	void TaskMYFeeDetail_Insert(Map<String, Object> map);

	/**
	 * 插入费用数据
	 */
	void TaskFeeDetail_Insert();

	/**
	 * 获取本地明源交易最后数据时间戳
	 * 
	 * @return
	 */
	Long TaskMYTradeDetailLast_Select();

	/**
	 * 获取明源交易列表
	 * 
	 * @param LastTime
	 * @return
	 */
	@DataSource(DataSourceEnum.DB2)
	List<Map<String, Object>> MYTradeList_Select(@Param("LastTime") Long lastTime);

	/**
	 * 插入本地明源交易数据
	 * 
	 * @param map
	 */
	void TaskMYTradeDetail_Insert(Map<String, Object> map);

	/**
	 * 插入交易数据
	 */
	void TaskTradeDetail_Insert();

	/**
	 * 获取本地明源订单最后数据时间戳
	 * 
	 * @return
	 */
	Long TaskMYOrderDetailLast_Select();

	/**
	 * 获取明源订单列表
	 * 
	 * @param LastTime
	 * @return
	 */
	@DataSource(DataSourceEnum.DB2)
	List<Map<String, Object>> MYOrderList_Select(@Param("LastTime") Long lastTime);

	/**
	 * 插入本地明源订单数据
	 * 
	 * @param map
	 */
	void TaskMYOrderDetail_Insert(Map<String, Object> map);

	/**
	 * 插入订单数据
	 */
	void TaskOrderDetail_Insert();

	/**
	 * 将自渠跟进逾期的客户置为无效
	 */
	void OwnerChannelOverdueTime_Update();

	/**
	 * 插入媒体数据
	 */
	void TaskBizParamOptionDetail_Insert();

	/**
	 * 查询有效的佣金规则
	 * 
	 * @return
	 */
	List<Map<String, Object>> CommissionRule_Select();

	/**
	 * 生成满足生效条件的线索
	 * 
	 * @param map
	 */
	void CommissionRecordsTemp_Create(Map<String, Object> map);

	/**
	 * 生成满足发放条件的线索
	 */
	void CommissionRecords_Create();

	/**
	 * 查询设置回收规则的项目
	 * 
	 * @return
	 */
	List<Map<String, Object>> RreclaimProject_Select();

	/**
	 * 跟进超时回收
	 * 
	 * @param map
	 */
	void RreclaimFollowup_Update(Map<String, Object> map);

}
