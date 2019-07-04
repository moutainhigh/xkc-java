package com.tahoecn.xkc.service.customer;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.customer.VCustomergwlistSelect;
import com.tahoecn.xkc.model.dto.GWCustomerPageDto;

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
	 
}
