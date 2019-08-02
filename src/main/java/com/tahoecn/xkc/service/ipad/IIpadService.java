package com.tahoecn.xkc.service.ipad;

import com.alibaba.fastjson.JSONObject;
import com.tahoecn.xkc.converter.Result;

public interface IIpadService {
	
	/**
	 * 项目列表
	 * @param param
	 * @return
	 */
	public Result mLFProjectList_Select(JSONObject param);
	
	/**
	 * 切换项目
	 * @param param
	 * @return
	 */
	public Result UserProjectChange_Update(JSONObject Parameter);
	
	/**
	 * 置业顾问列表
	 * @param paramAry
	 * @return
	 */
	public Result mLFCustomerGWList_Select(JSONObject paramAry);
	
	/**
	 * 查询客户详情
	 * @param paramAry
	 * @return
	 */
	public Result mLFCustomerDetailByMobile_Select(JSONObject paramAry);
	
	/**
	 * 客户登记
	 * @param paramAry
	 * @return
	 */
	public Result mLFCustomerDetail_Insert(JSONObject paramAry);
	
	/**
	 * 待分配列表
	 * @param paramAry
	 * @return
	 */
	public Result mLFCustomerNeedFPList_Select(JSONObject paramAry);
	
	/**
	 * 接待记录列表
	 * @param paramAry
	 * @return
	 */
	public Result mLFReceptRecordList_Select(JSONObject paramAry);
	
	/**
	 * 根据顾问筛选的接待记录客户列表
	 * @param paramAry
	 * @return
	 */
	public Result mLFReceptRecordCustomerList_Select(JSONObject paramAry);
	
	/**
	 * 获取有效置业顾问列表
	 * @param paramAry
	 * @return
	 */
	public Result mLFReceptRecordList_Select_forSaleUser(JSONObject paramAry);
}
