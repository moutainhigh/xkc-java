package com.tahoecn.xkc.service.sys.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tahoecn.log.Log;
import com.tahoecn.log.LogFactory;
import com.tahoecn.xkc.mapper.sys.TaskMapper;
import com.tahoecn.xkc.model.vo.TaskReturnVo;
import com.tahoecn.xkc.service.sys.IRuleRemindService;
import com.tahoecn.xkc.service.sys.ITaskService;

/**
 * TODO 调度总控服务类
 * 
 * @ClassName: TaskServiceImpl
 * @author: wq_qycc
 * @date: 2019年8月2日
 */
@Service
public class TaskServiceImpl implements ITaskService {
	private static final Log log = LogFactory.get();

	@Autowired
	TaskMapper taskMapper;

	@Autowired
	IRuleRemindService remindService;

	@Override
	public void taskRun(String code) {
		if (code == null || "".equals(code))
			return;
		switch (code) {
		case "ServiceAutoOC2Sale":
			serviceAutoOC2Sale();
			break;
		case "ServiceAutoVoucher":
			serviceAutoVoucher();
			break;
		case "ServiceAutoSyncCustomer":
			serviceAutoSyncCustomer();
			break;
		case "ServiceAutoProject":
			serviceAutoProject();
			break;
		case "ServiceAutoOpportunity":
			serviceAutoOpportunity();
			break;
		case "ServiceAutoRemindNew":
			remindService.serviceAutoRemindNew();
			break;
		case "ServiceAutoUCUser":
			serviceAutoUCUser();
			break;
		case "ServiceAutoSaleModiApply":
			serviceAutoSaleModiApply();
			break;
		case "ServiceAutoRoom":
			serviceAutoRoom();
			break;
		case "ServiceAutoBuilding":
			serviceAutoBuilding();
			break;
		case "ServiceAutoContract":
			serviceAutoContract();
			break;
		case "ServiceAutoMonthYs":
			serviceAutoMonthYs();
			break;
		case "ServiceAutoFee":
			serviceAutoFee();
			break;
		case "ServiceAutoGetin":
			serviceAutoGetin();
			break;
		case "ServiceAutoRemind_QD":
			remindService.serviceAutoRemind_QD();
			break;
		case "ServiceAutoRreclaim":
			serviceAutoRreclaim();
			break;
		case "ServiceAutoTrade":
			serviceAutoTrade();
			break;
		case "ServiceAutoCommission":
			serviceAutoCommission();
			break;
		case "ServiceAutoOrder":
			serviceAutoOrder();
			break;
		case "ServiceAutoOwnerChannelProtectedJob":
			serviceAutoOwnerChannelProtectedJob();
			break;
		case "ServiceAutoBizParamOption":
			serviceAutoBizParamOption();
			break;
		case "ServiceAutoUCUserChange":
			serviceAutoUCUserChange();
			break;

		default:
			break;
		}
	}

	/**
	 * 插入销售数据
	 */
	private void serviceAutoOC2Sale() {
		taskMapper.TaskOC2SaleDetail_Insert();
	}

	/**
	 * 插入发票数据
	 */
	private void serviceAutoVoucher() {
		taskMapper.TaskVoucherDetail_Insert();
	}

	/**
	 * 同步客户信息到明源
	 */
	private void serviceAutoSyncCustomer() {
		List<Map<String, Object>> list = taskMapper.TaskSyncCustomerList_Select();
		for (int i = 0; i < list.size(); i++) {
			String TaskID = String.valueOf(list.get(i).get("ID"));
			String TaskType = String.valueOf(list.get(i).get("TaskType"));
			String TaskPara = String.valueOf(list.get(i).get("TaskPara"));
			try {
				// 同步客户
				int optionType = -1;
				switch (TaskType) {
				case "SyncCustomerOpportunity":
					optionType = 0;
					break;
				case "SyncCustomer":
					optionType = 1;
					break;
				case "SyncOpportunity":
					optionType = 2;
					break;
				}

				TaskReturnVo v = TaskReturnVo.success();

				if (optionType == 0 || optionType == 1) {
					String CustomerId = taskMapper.TaskOpportunityCustomerID_Select(TaskPara);
					Map<String, Object> cust = taskMapper.LocalCustomerDetail_Select(CustomerId);
					if (cust != null) {
						try {
							try {
								taskMapper.MYUserDetail_Insert(cust);
							} catch (Exception e) {
							}
							taskMapper.MYCustomerDetail_Insert(cust);
						} catch (Exception e) {
							v = TaskReturnVo.error(9, e.getLocalizedMessage());
						}
					} else {
						v = TaskReturnVo.error(91, "客户信息不正确");
					}

				}
				if (v.getErrCode() == 0) {
					if (optionType == 0 || optionType == 2) {
						List<Map<String, Object>> lodList = taskMapper.LocalOpportunityDetail_Select(TaskPara);
						List<Map<String, Object>> locList = taskMapper.LocalOpportunityCustomerList_Select(TaskPara);
						if (lodList != null && lodList.size() > 0) {
							try {
								setOpportunity(lodList, locList);
							} catch (Exception e) {
								v.setErrCode(StringUtils.isBlank(v.getErrMsg()) ? 9 : 99);
								v.setErrMsg(e.getLocalizedMessage());
							}
						} else {
							v.setErrCode(StringUtils.isBlank(v.getErrMsg()) ? 91 : 90);
							v.setErrMsg(StringUtils.isBlank(v.getErrMsg()) ? "机会信息不正确" : v.getErrMsg());
						}
					}
				}

				// 更新任务状态
				Map<String, Object> map = new HashedMap<>();
				map.put("TaskID", TaskID);
				map.put("EndRemark", v.getErrMsg());
				map.put("TaskResult", v.getErrCode() == 0 ? "Success" : "Error");
				taskMapper.TaskDetail_Update(map);
			} catch (Exception e) {
				Map<String, Object> map = new HashedMap<>();
				map.put("TaskID", TaskID);
				map.put("EndRemark", e.getLocalizedMessage());
				map.put("TaskResult", "ErrorCatch");
				taskMapper.TaskDetail_Update(map);
			}
		}
	}

	/**
	 * @param lodList
	 * @param locList
	 */
	@Transactional
	private void setOpportunity(List<Map<String, Object>> lodList, List<Map<String, Object>> locList) {
		for (Map<String, Object> map : lodList) {
			taskMapper.MYOpportunityDetail_Insert(map);
			taskMapper.MyOpportunityCustomerDetail_Delete(map);
		}
		for (Map<String, Object> map : locList) {
			taskMapper.MyOpportunityCustomerDetail_Delete(map);
		}
	}

	/**
	 * 查询明源项目列表信息
	 */
	private void serviceAutoProject() {
		List<Map<String, Object>> pList = taskMapper.MYProjectList_Select();
		if (pList != null && pList.size() > 0) {
			taskMapper.TaskMYProjectList_Delete();
			for (Map<String, Object> map : pList) {
				try {
					taskMapper.TaskMYProjectDetail_Insert(map);
				} catch (Exception e) {
					log.error(e);
				}
			}
		}
		try {
			taskMapper.TaskProjectDetail_Insert();
		} catch (Exception e) {
			log.error(e);
			throw new RuntimeException("插入项目数据失败");
		}
	}

	/**
	 * 获取明源机会列表信息
	 */
	private void serviceAutoOpportunity() {
		Long LastTime = taskMapper.TaskMYOpportunityDetailLast_Select();
		List<Map<String, Object>> list = taskMapper.MYOpportunityList_Select(LastTime);
		if (list != null && list.size() > 0) {
			for (Map<String, Object> map : list) {
				try {
					taskMapper.TaskMYOpportunityDetail_Insert(map);
				} catch (Exception e) {
					log.error(e);
				}
			}
		}
		try {
			taskMapper.TaskOpportunityDetail_Insert();
		} catch (Exception e) {
			throw new RuntimeException("插入机会数据失败");
		}
	}

	/**
	 * 同步UC用户信息
	 */
	private void serviceAutoUCUser() {
		// TODO 未作
	}

	/**
	 * 获取明源销售变更信息
	 */
	private void serviceAutoSaleModiApply() {
		taskMapper.TaskSaleModiApplyDetail_Insert();
	}

	/**
	 * 获取明源房间列表信息
	 */
	private void serviceAutoRoom() {
		Long lastTime = taskMapper.TaskMYRoomDetailLast_Select();
		List<Map<String, Object>> list = taskMapper.MYRoomList_Select(lastTime);
		if (list != null && list.size() > 0) {
			for (Map<String, Object> map : list) {
				try {
					taskMapper.TaskMYRoomDetail_Insert(map);
				} catch (Exception e) {
					log.error(e);
				}
			}
		}
		try {
			taskMapper.TaskRoomDetail_Insert();
		} catch (Exception e) {
			throw new RuntimeException("插入房间数据失败");
		}
	}

	/**
	 * 获取明源楼栋列表信息
	 */
	private void serviceAutoBuilding() {
		Long lastTime = taskMapper.TaskMYBuildingDetailLast_Select();
		List<Map<String, Object>> list = taskMapper.MYBuildingList_Select(lastTime);
		if (list != null && list.size() > 0) {
			for (Map<String, Object> map : list) {
				try {
					taskMapper.TaskMYBuildingDetail_Insert(map);
				} catch (Exception e) {
					log.error(e);
				}
			}
		}
		try {
			taskMapper.TaskBuildingDetail_Insert();
		} catch (Exception e) {
			throw new RuntimeException("插入楼栋数据失败");
		}
	}

	/**
	 * 获取明源合同列表信息
	 */
	private void serviceAutoContract() {
		Long lastTime = taskMapper.TaskMYContractDetailLast_Select();
		List<Map<String, Object>> list = taskMapper.MYContractList_Select(lastTime);
		if (list != null && list.size() > 0) {
			for (Map<String, Object> map : list) {
				try {
					taskMapper.TaskMYContractDetail_Insert(map);
				} catch (Exception e) {
					log.error(e);
				}
			}
		}
		try {
			taskMapper.TaskContractDetail_Insert();
		} catch (Exception e) {
			throw new RuntimeException("插入合同数据失败");
		}
	}

	/**
	 * 获取明源月度计划信息
	 */
	private void serviceAutoMonthYs() {
		taskMapper.TaskMonthYsDetail_Insert();
	}

	/**
	 * 获取明源费用列表信息
	 */
	private void serviceAutoFee() {
		Long lastTime = taskMapper.TaskMYFeeDetailLast_Select();
		List<Map<String, Object>> list = taskMapper.MYFeeList_Select(lastTime);
		if (list != null && list.size() > 0) {
			for (Map<String, Object> map : list) {
				try {
					taskMapper.TaskMYFeeDetail_Insert(map);
				} catch (Exception e) {
					log.error(e);
				}
			}
		}
		try {
			taskMapper.TaskFeeDetail_Insert();
		} catch (Exception e) {
			throw new RuntimeException("插入费用数据失败");
		}
	}

	/**
	 * 获取明源应收信息
	 */
	private void serviceAutoGetin() {
		taskMapper.TaskGetinDetail_Insert();
	}

	/**
	 * 回收服务
	 */
	private void serviceAutoRreclaim() {
		List<Map<String, Object>> list = taskMapper.RreclaimProject_Select();
		if (list != null && list.size() > 0) {
			for (Map<String, Object> map : list) {
				try {
					Map<String, Object> mp = new HashMap<>();
					mp.put("ProjectID", map.get("ProjectID"));
					mp.put("Reason", "跟进超时");
					mp.put("CreateTime", new Date());
					taskMapper.RreclaimFollowup_Update(mp);
				} catch (Exception e) {
					log.error(e);
				}
			}
		}
	}

	/**
	 * 获取明源交易列表信息
	 */
	private void serviceAutoTrade() {
		Long lastTime = taskMapper.TaskMYTradeDetailLast_Select();
		List<Map<String, Object>> list = taskMapper.MYTradeList_Select(lastTime);
		if (list != null && list.size() > 0) {
			for (Map<String, Object> map : list) {
				try {
					taskMapper.TaskMYTradeDetail_Insert(map);
				} catch (Exception e) {
					log.error(e);
				}
			}
		}
		try {
			taskMapper.TaskTradeDetail_Insert();
		} catch (Exception e) {
			throw new RuntimeException("插入费用数据失败");
		}
	}

	/**
	 * 生成佣金服务
	 */
	private void serviceAutoCommission() {
		List<Map<String, Object>> list = taskMapper.CommissionRule_Select();
		if (list != null && list.size() > 0) {
			for (Map<String, Object> map : list) {
				try {
					taskMapper.CommissionRecordsTemp_Create(map);
				} catch (Exception e) {
					log.error(e);
				}
			}
		}
		taskMapper.CommissionRecords_Create();
	}

	/**
	 * 获取明源订单列表信息
	 */
	private void serviceAutoOrder() {
		Long lastTime = taskMapper.TaskMYOrderDetailLast_Select();
		List<Map<String, Object>> list = taskMapper.MYOrderList_Select(lastTime);
		if (list != null && list.size() > 0) {
			for (Map<String, Object> map : list) {
				try {
					taskMapper.TaskMYOrderDetail_Insert(map);
				} catch (Exception e) {
					log.error(e);
				}
			}
		}
		try {
			taskMapper.TaskOrderDetail_Insert();
		} catch (Exception e) {
			throw new RuntimeException("插入费用数据失败");
		}
	}

	/**
	 * 自渠线索保护
	 */
	private void serviceAutoOwnerChannelProtectedJob() {
		taskMapper.OwnerChannelOverdueTime_Update();
	}

	/**
	 * 获取明源媒体信息
	 */
	private void serviceAutoBizParamOption() {
		taskMapper.TaskBizParamOptionDetail_Insert();
	}

	/**
	 * 同步UC用户变更信息
	 */
	private void serviceAutoUCUserChange() {
		// TODO 未作
	}

}
