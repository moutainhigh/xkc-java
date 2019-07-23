package com.tahoecn.xkc.service.customer.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.customer.ASharepoolMapper;
import com.tahoecn.xkc.mapper.customer.BCustomerpotentialMapper;
import com.tahoecn.xkc.model.customer.ASharepool;
import com.tahoecn.xkc.model.customer.BCustomerpotential;
import com.tahoecn.xkc.service.customer.IASharepoolService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-18
 */
@Service
public class ASharepoolServiceImpl extends ServiceImpl<ASharepoolMapper, ASharepool> implements IASharepoolService {
	@Autowired
	private BCustomerpotentialMapper bCustomerpotentialMapper;
	/**
	 * 修改分享传播池信息
	 */
	@Override
	public void mSharePoolDetail_Update(Map<String, Object> para1) {
//		Update A_SharePool SET Status ={Status},Editor ='{UserID}',EditeTime = GETDATE() Where ID = '{FXCBID}'
		ASharepool pool = new ASharepool();
		pool.setStatus((int)para1.get("Status"));
		pool.setEditor(para1.get("UserID").toString());
		pool.setEditeTime(new Date());
		pool.setId(para1.get("FXCBID").toString());
		baseMapper.updateById(pool);
	}
	/**
	 * 新增客户信息/分享传播新增新机会老客户信息
	 * @param sqlKey 查询方法
	 * @param parameter 参数
	 */
	@Override
	public void CustomerGWDetail(String sqlKey, JSONObject parameter) {
		if("mFXCBNewCustomerGWDetail_Insert".equals(sqlKey)){//新机会-新客户
			mFXCBNewCustomerGWDetail_Insert(parameter);
		}else if("mFXCBOldCustomerGWDetail_Insert".equals(sqlKey)){//新机会-老客户
			mFXCBOldCustomerGWDetail_Insert(parameter);
		}
	}
	/**
	 * 分享传播新增新机会老客户信息
	 * 1.新增机会 2.新增分接登记 3.新增销售轨迹
	 */
	private void mFXCBOldCustomerGWDetail_Insert(JSONObject parameter) {
		baseMapper.updBOpportunity_old(parameter);
		parameter.put("LastFirstName",parameter.getString("LastName")+parameter.getString("FirstName"));
		baseMapper.insBOpportunity_old(parameter);
		baseMapper.insBOpportunityCustomerRel_old(parameter);
		parameter.put("CustomerRank","41FA0234-F8AE-434F-8BCD-6E9BE1D059DA");
		baseMapper.callPOpportunityCustomerRank(parameter);
		if("E0C5FDD1-800B-39F5-1A20-C0A5A3C3B450".equals(parameter.getString("VisitType"))){
			parameter.put("CustomerRank","ED0AD9E6-AF72-424C-9EE0-9884FF31FA42");
			baseMapper.callPOpportunityCustomerRank(parameter);
		}
		baseMapper.callPSyncClueOpportunity_Update(parameter);
	}
	/**
	 * 新增客户信息
	 * 顾问新增客户 0.新增客户信息 1.新增客户特性 2.新增机会 2.新增销售轨迹 4.客户标签 5.跟进记录, 6.增加关联权益人
	 */
	private void mFXCBNewCustomerGWDetail_Insert(JSONObject parameter) {
		String FollwUpWay = parameter.getString("FollwUpWay");
		String IsLittleBooking = parameter.getString("IsLittleBooking");
		baseMapper.insBCustomer_new(parameter);//0.新增客户信息
		baseMapper.insBCustomerAttribute_new(parameter);//1.新增客户特性
		baseMapper.insBOpportunity_new(parameter);//2.新增机会
		baseMapper.insBCustomerTag_new(parameter);//3.新增销售轨迹
		baseMapper.insBOpportunityCustomerRel_new(parameter);//4.客户标签
		//5.跟进记录
		parameter.put("CustomerRank","41FA0234-F8AE-434F-8BCD-6E9BE1D059DA");
		baseMapper.callPOpportunityCustomerRank(parameter);
		if("EEB32C04-5B7C-4676-A5DC-5F95E56370EB".equals(FollwUpWay) 
				|| "44775694-7C97-455C-B48E-154C6BFE2D94".equals(FollwUpWay)){
			parameter.put("CustomerRank","FC420696-6F6C-40B1-BE17-96FCEC75B0F2");
			baseMapper.callPOpportunityCustomerRank(parameter);
		}
		if("E30825AA-B894-4A5F-AF55-24CAC34C8F1F".equals(FollwUpWay)){
			parameter.put("CustomerRank","ED0AD9E6-AF72-424C-9EE0-9884FF31FA42");
			baseMapper.callPOpportunityCustomerRank(parameter);
		}
		if("1DCCBDB8-AD44-44D4-B23A-571A38337D5C".equals(IsLittleBooking)){
			parameter.put("CustomerRank","D47878CE-D560-44C0-A6F8-4C92CCAC9EE0");
			baseMapper.callPOpportunityCustomerRank(parameter);
		}
		baseMapper.callPSyncClueOpportunity_Update(parameter);//6.增加关联权益人
	}
	/**
	 * 判断是否存在潜在客户，如果不存在新增，存在修改
	 */
	@Override
	public Map<String, Object> CustomerPotential_Update(Map<String, Object> jParameter) {
		String CustomerID = "";
//		select @CustomerID=ID from B_CustomerPotential where Mobile='{Mobile}';
		QueryWrapper<BCustomerpotential> wrapper = new QueryWrapper<BCustomerpotential>();
		wrapper.eq("Mobile", jParameter.get("Mobile").toString());
		BCustomerpotential tial = bCustomerpotentialMapper.selectOne(wrapper);
		CustomerID = tial.getId();
		if(CustomerID == null || "".equals(CustomerID)){
			BCustomerpotential t = new BCustomerpotential();
			t.setName(jParameter.get("Name").toString());
			t.setLastName(jParameter.get("Name").toString());
			t.setGender(jParameter.get("Gender").toString());
			t.setMobile(jParameter.get("Mobile").toString());
			t.setCreator("99");
			t.setCreateTime(new Date());
			t.setIsDel(0);
			t.setStatus(1);
			bCustomerpotentialMapper.insert(t);
			CustomerID = t.getId();
		}else{
			BCustomerpotential tu = new BCustomerpotential();
			tu.setName(jParameter.get("Name").toString());
			tu.setLastName(jParameter.get("Name").toString());
			tu.setGender(jParameter.get("Gender").toString());
			tu.setCreator("99");
			tu.setCreateTime(new Date());
			tu.setId(CustomerID);
			bCustomerpotentialMapper.updateById(tu);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("CustomerPotentialID", CustomerID);
		return map;
	}
	

}
