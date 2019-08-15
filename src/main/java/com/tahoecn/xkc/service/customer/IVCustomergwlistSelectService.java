package com.tahoecn.xkc.service.customer;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.customer.VCustomergwlistSelect;
import com.tahoecn.xkc.model.dto.GWCustomerPageDto;
import com.tahoecn.xkc.model.vo.CustomerActionVo;

/**
 * 
 *客户
 */
public interface IVCustomergwlistSelectService extends IService<VCustomergwlistSelect> {
	/**
	 * 置业顾问客户列表
	 * @return
	 */
	 public Result customerList(GWCustomerPageDto gWCustomerPageDto);
	 
	 /**
	  * 设置子集信息时待选客户列表
	  * @param gWCustomerPageDto
	  * @return
	  */
	 public Result customerListForSetChild(GWCustomerPageDto gWCustomerPageDto);
	 
	 /**
	  * 设置父级ID
	  * @param paramAry
	  * @return
	  */
	 public Result updateParentID(JSONObject paramAry);
	 
	 /**
	  * 删除组员关系
	  * @param paramAry
	  * @return
	  */
	 public Result deleteParentID(JSONObject paramAry);
	 
	 /**
	  * 置业顾问客户基本信息
	  * @param paramAry
	  * @return
	  */
	 public Result customerBase(JSONObject paramAry);
	 
	 /**
	  * 置业顾问跟进记录
	  * @param paramAry
	  * @return
	  */
	 public Result mCustomerFollowUpList_Select(JSONObject paramAry);
	 
	 /**
	  * 置业顾问销售轨迹
	  * @param paramAry
	  * @return
	  */
	 public Result TrackList(JSONObject paramAry);
	 
	 /**
	  * 新增跟进记录
	  * @param paramAry
	  * @return
	  */
	 public Result mCustomerFollowUpDetail_Insert(JSONObject paramAry);
	 
	 /**
	  * 用户关注客户新增 
	  * @param paramAry
	  * @return
	  */
	 public Result UserFollowDetail_Insert(JSONObject paramAry);
	 
	 /**
	  * 用户关注客户取消关注
	  * @param paramAry
	  * @return
	  */
	 public Result UserFollowDetail_Delete(JSONObject paramAry);
	 
	 /**
	  * 置业顾问客户标签新增
	  * @param paramAry
	  * @return
	  */
	 public Result CustomerTagAdd(JSONObject paramAry);
	 
	 /**
	  * 置业顾问顾问标签新增
	  * @param paramAry
	  * @return
	  */
	 public Result TagAdd(JSONObject paramAry);
	 
	 /**
	  * 置业顾问顾顾问标签列表
	  * @param paramAry
	  * @return
	  */
	 public Result TagList(JSONObject paramAry);
	 
	 /**
	  * 置业顾问顾客户意向列表
	  * @param paramAry
	  * @return
	  */
	 public Result IntentProjectList(JSONObject paramAry);
	 
	 /**
	  * 置业顾问客户意向新增
	  * @param paramAry
	  * @return
	  */
	 public Result IntentProjectAdd(JSONObject paramAry);
	 /**
	  * 置业顾问项目列表
	  * @param paramAry
	  * @return
	  */
	 public Result ProjectList(JSONObject paramAry);
	 
	 /**
	  * 置业顾问客户资料信息
	  * @param paramAry
	  * @return
	  */
	 public Result Detail(JSONObject paramAry);
	 
	 /**
	  * 置业顾问客户丢失详细信息
	  * @param paramAry
	  * @return
	  */
	 public Result GiveUpDetail(JSONObject paramAry);
	 
	 /**
	  * 置业顾问丢失申请提交
	  * @param paramAry
	  * @return
	  */
	 public Result GiveUpApplyAdd(JSONObject paramAry);
	 
	 /**
	  * 置业顾问获取丢失状态
	  * @param paramAry
	  * @return
	  */
	 public Result GiveUpStatusFind(JSONObject paramAry);
	 
	 /**
	  * 置业顾问客户成交信息
	  * @param paramAry
	  * @return
	  */
	 public Result DealDetail(JSONObject paramAry);
	 
	 /**
	  * 置业顾问用户基本信息修改 
	  * @param paramAry
	  * @return
	  */
	 public Result mUserDetail_Update(JSONObject paramAry);
	 
	 /**
	  * 修改用户资料
	  * @param paramAry
	  * @return
	  */
	 public Result UserDetail_Update(JSONObject paramAry);
	 
	 /**
	  * 修改兼职用户资料
	  * @param paramAry
	  * @return
	  */
	 public Result JZDetail_Update(JSONObject paramAry);
	 /**
	  * 置业顾问案场顾问客户登记
	  * @param paramAry
	  * @return
	  */
	 public Result mCustomerGWDetail_Insert(JSONObject paramAry);
	 
	 /**
	  * 获取客户详情
	  * @param paramAry
	  * @return
	  */
	 public Result CustomerFind(JSONObject paramAry);
	 
	 /**
	  * 客户完善资料
	  * @param paramAry
	  * @return
	  */
	 public Result mCustomerGWDetail_Update(JSONObject paramAry);
	 
	 /**
	  * 客户认购查询
	  * @param paramAry
	  * @return
	  */
	 public Result SubscribeFind(JSONObject paramAry);
	 
	 /**
	  * 客户认购
	  * @param paramAry
	  * @return
	  */
	 public Result mCustomerSubscribeDetail_Insert(JSONObject paramAry);
	 
	 /**
	  * 关联权益人列表
	  * @param paramAry
	  * @return
	  */
	 public Result RelPeopleList(JSONObject paramAry);
	 
	 /**
	  * 用户关注客户列表
	  * @param paramAry
	  * @return
	  */
	 public Result UserFollowList_Select(JSONObject paramAry);
	 
	 public Result CustomerOpportunityFollowUpDetail_Update(String opportunityID,String userID);
	 
	 public Result CustomerFollowUp_Insert(CustomerActionVo customerActionVo);
	 
	 /**
	  * 添加客户信息变更记录
	  * @param customerID
	  * @param pmap
	  */
	 public void addCustomerChangeInfo(Map<String,Object> pmap);
	 
	 /**
	  * 根据机会ID获取变更记录
	  * @param OpportunityID
	  * @return
	  */
	 public Result getCustomerChangeList(String OpportunityID);
	 
	 /**
	  * 校验副手机号
	  * @param OpportunityID
	  * @return
	  */
	 public Result verifySpareMobile(String SpareMobile);
	 
}
