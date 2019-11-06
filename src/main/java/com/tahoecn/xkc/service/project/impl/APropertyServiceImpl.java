package com.tahoecn.xkc.service.project.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.house.BDiscountMapper;
import com.tahoecn.xkc.model.house.BDiscount;
import com.tahoecn.xkc.service.project.IAPropertyService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  置业计划
 * </p>
 *
 * @author YYY
 * @since 2019-07-08
 */
@Service
public class APropertyServiceImpl extends ServiceImpl<BDiscountMapper, BDiscount> implements IAPropertyService {

	/**
	 * 获取列表带分页-(默认)
	 */
	@Override
	public Map<String, Object> Discount_List_Select(IPage<Map<String, Object>> page, JSONObject param) {
		String Parameter = param.getString("Parameter");
		IPage<Map<String,Object>> list = baseMapper.Discount_List_Select(page, Parameter);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("AllCount", list.getTotal());
		map.put("List", list.getRecords());
		map.put("PageSize", list.getSize());
		return map;
	}
	/**
	 * 添加折扣
	 */
	@Override
	public String Discount_Detail_Add(JSONObject param) {
		BDiscount bd = new BDiscount();
	    bd.setSaleUserID(param.getString("SaleUserID"));
	    bd.setName(param.getString("Name"));
	    bd.setContent(param.getString("Content"));
	    bd.setFormula(param.getString("Formula"));
	    bd.setCreator(param.getString("UserID"));
	    bd.setCreateTime(new Date());
	    bd.setIsDel(0);
	    bd.setStatus(0);
	    baseMapper.insert(bd);
		return bd.getId();
	}
	/**
	 * 删除折扣
	 */
	@Override
	public void Discount_Detail_DeleteById(JSONObject param) {
		BDiscount bd = new BDiscount();
		bd.setId(param.getString("ID"));
		bd.setIsDel(1);
		bd.setEditor(param.getString("UserID"));
		bd.setEditTime(new Date());
		baseMapper.updateById(bd);
	}

}
