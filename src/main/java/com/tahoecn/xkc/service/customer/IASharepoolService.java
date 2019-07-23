package com.tahoecn.xkc.service.customer;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.customer.ASharepool;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-18
 */
public interface IASharepoolService extends IService<ASharepool> {

	/**
	 * 修改分享传播池信息
	 */
	void mSharePoolDetail_Update(Map<String, Object> para1);

	/**
	 * 新增客户信息/分享传播新增新机会老客户信息
	 * @param sqlKey 查询方法
	 * @param parameter 参数
	 */
	void CustomerGWDetail(String sqlKey, JSONObject parameter);
	/**
	 * 判断是否存在潜在客户，如果不存在新增，存在修改
	 */
	Map<String, Object> CustomerPotential_Update(Map<String, Object> jParameter);
	
}
