package com.tahoecn.xkc.service.house;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.house.BHouseplan;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-08-08
 */
public interface IBHouseplanService extends IService<BHouseplan> {

	/**
	 * 置业计划新增
	 */
	String HousePlan_Detail_Add(JSONObject param);
	/**
	 * 置业计划详情
	 */
	Map<String,Object> HousePlan_Detail_Find(JSONObject param);
	/**
	 * 微楼书详情
	 */
	Map<String,Object> MicroBuildingBook_Detail_Find(JSONObject param);

}
