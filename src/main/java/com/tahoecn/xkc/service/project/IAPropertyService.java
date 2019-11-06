package com.tahoecn.xkc.service.project;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.house.BDiscount;

/**
 * <p>
 *  置业计划
 * </p>
 *
 * @author YYY
 * @since 2019-07-08
 */
public interface IAPropertyService extends IService<BDiscount> {

	/**
	 * 获取列表带分页-(默认)
	 */
	Map<String, Object> Discount_List_Select(IPage<Map<String, Object>> page, JSONObject param);
	/**
	 * 添加折扣
	 */
	String Discount_Detail_Add(JSONObject param);
	/**
	 * 删除折扣
	 */
	void Discount_Detail_DeleteById(JSONObject param);



}
