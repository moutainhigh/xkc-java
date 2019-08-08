package com.tahoecn.xkc.mapper.house;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tahoecn.xkc.model.house.BDiscount;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-08-08
 */
public interface BDiscountMapper extends BaseMapper<BDiscount> {
	/**
	 * 获取列表带分页-(默认)
	 */
	IPage<Map<String, Object>> Discount_List_Select(IPage<Map<String, Object>> page, 
			@Param("Parameter")String Parameter);
}
