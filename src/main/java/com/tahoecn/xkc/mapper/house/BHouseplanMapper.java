package com.tahoecn.xkc.mapper.house;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.house.BHouseplan;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-08-08
 */
public interface BHouseplanMapper extends BaseMapper<BHouseplan> {
	/**
	 * 置业计划详情
	 */
	Map<String, Object> HousePlan_Detail_Find(JSONObject param);
	/**
	 * 微楼书详情
	 */
	Map<String, Object> MicroBuildingBook_Detail_Find(JSONObject param);

}
