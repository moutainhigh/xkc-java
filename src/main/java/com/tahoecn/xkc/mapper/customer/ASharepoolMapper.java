package com.tahoecn.xkc.mapper.customer;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.customer.ASharepool;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-18
 */
public interface ASharepoolMapper extends BaseMapper<ASharepool> {
	//0.新增客户信息
	void insBCustomer_new(JSONObject parameter);
	//1.新增客户特性
	void insBCustomerAttribute_new(JSONObject parameter);
	//2.新增机会
	void insBOpportunity_new(JSONObject parameter);
	//3.新增销售轨迹
	void insBCustomerTag_new(JSONObject parameter);
	//4.客户标签
	void insBOpportunityCustomerRel_new(JSONObject parameter);
	//5.跟进记录
	void callPOpportunityCustomerRank(JSONObject parameter);
	//6.增加关联权益人
	void callPSyncClueOpportunity_Update(JSONObject parameter);
	void updBOpportunity_old(JSONObject parameter);
	void insBOpportunity_old(JSONObject parameter);
	void insBOpportunityCustomerRel_old(JSONObject parameter);

}
