package com.tahoecn.xkc.service.house.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.house.BHouseplanMapper;
import com.tahoecn.xkc.model.house.BHouseplan;
import com.tahoecn.xkc.service.house.IBHouseplanService;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-08-08
 */
@Service
public class BHouseplanServiceImpl extends ServiceImpl<BHouseplanMapper, BHouseplan> implements IBHouseplanService {
	/**
	 * 置业计划新增
	 */
	@Override
	public String HousePlan_Detail_Add(JSONObject param) {
		BHouseplan plan = new BHouseplan();
		plan.setProjectID(param.getString("ProjectID"));
		plan.setCustomerID(param.getString("CustomerID"));
		plan.setCustomerName(param.getString("CustomerName"));
		plan.setSalesUserID(param.getString("SalesUserID"));
		plan.setSalesUserName(param.getString("SalesUserName"));
		plan.setHouseID(param.getString("HouseID"));
		plan.setHouseName(param.getString("HouseName"));
		plan.setApartmentDiagram(param.getString("ApartmentDiagram"));
		plan.setBuildingArea(param.getDouble("BuildingArea"));
		plan.setBuildingPrice(param.getDouble("BuildingPrice"));
		plan.setTotalPrice(param.getDouble("TotalPrice"));
		plan.setDiscountTotal(param.getDouble("DiscountTotal"));
		plan.setDiscountDetail(param.getString("DiscountDetail"));
		plan.setPayTypeID(param.getString("PayTypeID"));
		plan.setPayTypeName(param.getString("PayTypeName"));
		plan.setDownPayment(param.getDouble("DownPayment"));
		plan.setLoanTypeID(param.getString("LoanTypeID"));
		plan.setLoanBusiness(param.getDouble("LoanBusiness"));
		plan.setLoanTypeName(param.getString("LoanTypeName"));
		plan.setLoanBusinessRate(param.getDouble("LoanBusinessRate"));
		plan.setLoanAccumulationFund(param.getDouble("LoanAccumulationFund"));
		plan.setLoanAccumulationFundRate(param.getDouble("LoanAccumulationFundRate"));
		plan.setLoanTerm(param.getInteger("LoanTerm"));
		plan.setRepaymentType(param.getInteger("RepaymentType"));
		plan.setLoanTotal(param.getDouble("LoanTotal"));
		plan.setMonthlyPayment(param.getString("MonthlyPayment"));
		plan.setInterestTotal(param.getDouble("InterestTotal"));
		plan.setPrincipalTotal(param.getDouble("PrincipalTotal"));
		plan.setCreator(param.getString("UserID"));
		plan.setCreateTime(new Date());
		plan.setDownPaymentratio(param.getDouble("DownPaymentratio"));
		plan.setIsDel(0);
		plan.setStatus(0);
		baseMapper.insert(plan);
		return plan.getId();
	}
	/**
	 * 置业计划详情
	 */
	@Override
	public Map<String, Object> HousePlan_Detail_Find(JSONObject param) {
		return baseMapper.HousePlan_Detail_Find(param);
	}
	/**
	 * 微楼书详情
	 */
	@Override
	public Map<String, Object> MicroBuildingBook_Detail_Find(JSONObject param) {
		return baseMapper.MicroBuildingBook_Detail_Find(param);
	}

}
