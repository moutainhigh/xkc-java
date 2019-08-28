package com.tahoecn.xkc.controller.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tahoecn.core.date.DateTime;
import com.tahoecn.xkc.common.enums.ActionType;
import com.tahoecn.xkc.common.enums.MessageType;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.mapper.customer.BCustomerpotentialMapper;
import com.tahoecn.xkc.model.customer.BClue;
import com.tahoecn.xkc.model.customer.BCustomerfiltergroup;
import com.tahoecn.xkc.model.opportunity.BOpportunity;
import com.tahoecn.xkc.model.vo.ChannelRegisterModel;
import com.tahoecn.xkc.model.vo.CustomerActionVo;
import com.tahoecn.xkc.model.vo.FilterItem;
import com.tahoecn.xkc.service.channel.IBChannelService;
import com.tahoecn.xkc.service.customer.IASharepoolService;
import com.tahoecn.xkc.service.customer.IBClueService;
import com.tahoecn.xkc.service.customer.IBCustomerfiltergroupService;
import com.tahoecn.xkc.service.customer.IBCustomerpotentialfiltergroupService;
import com.tahoecn.xkc.service.customer.IBCustomerpublicpoolService;
import com.tahoecn.xkc.service.customer.IBOpportunitygiveupService;
import com.tahoecn.xkc.service.customer.ICustomerHelp;
import com.tahoecn.xkc.service.customer.IVCustomergwlistSelectService;
import com.tahoecn.xkc.service.customer.IVCustomerxsjlsalesuserlistSelectService;
import com.tahoecn.xkc.service.customer.impl.CustomerHelp;
import com.tahoecn.xkc.service.opportunity.IBOpportunityService;
import com.tahoecn.xkc.service.salegroup.IBSalesgroupService;
import com.tahoecn.xkc.service.sys.ISystemMessageService;
import com.tahoecn.xkc.service.user.ICWxuserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YYY
 * @since 2019-06-25
 */
@RestController
@Api(tags = "APP-客户接口", value = "APP-客户接口")
@RequestMapping("/app/customer")
public class AppCustomerController extends TahoeBaseController {
	
	@Value("${SiteUrl}")
    private String SiteUrl;
    @Autowired
    private IBCustomerpotentialfiltergroupService iBCustomerpotentialfiltergroupService;
    @Autowired
    private IBCustomerfiltergroupService iBCustomerfiltergroupService;
    @Autowired
    private IBOpportunityService iBOpportunityService;
    @Autowired
    private IBOpportunitygiveupService iBOpportunitygiveupService;
    @Autowired
    private IVCustomergwlistSelectService iVCustomergwlistSelectService;
    @Autowired
    private IBCustomerpublicpoolService iBCustomerpublicpoolService;
    @Autowired
    private IVCustomerxsjlsalesuserlistSelectService iVCustomerxsjlsalesuserlistSelectService;
    @Autowired
    private ICustomerHelp iCustomerHelp;
    @Autowired
    private IBSalesgroupService iBSalesgroupService;
    @Autowired
    private IBClueService iBClueService;
    @Autowired
    private IASharepoolService iASharepoolService;
    @Autowired
    private ICWxuserService iCWxuserService;
    @Autowired
	private ISystemMessageService iSystemMessageService;
    @Autowired
	private BCustomerpotentialMapper bCustomerpotentialMapper;
    @Autowired
    private IBChannelService iBChannelService;
    
	@ResponseBody
    @ApiOperation(value = "案场销售经理客户丢失审批详细", notes = "案场销售经理客户丢失审批详细")
    @RequestMapping(value = "/mCustomerYXJLLoseDetail_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mCustomerYXJLLoseDetail_Select(@RequestBody JSONObject jsonParam) {
    	try{
    		JSONObject paramMap = jsonParam.getJSONObject("_param");
    		return iVCustomergwlistSelectService.GiveUpDetail(paramMap);
//    		
//    		CSearchModel model = JSON.parseObject(JSON.toJSONString(paramMap), CSearchModel.class);
//            CustomerModel customerModel = new CustomerModel();
//            List<Map<String,Object>> CustomerObj = iVOpportunityService.OpportunityInfo(model.getOpportunityID());
//            if (CustomerObj != null  && CustomerObj.size() > 0){
//                String customerModeType = CustomerModeType.顾问_客户_详情.getTypeID();
//                customerModel = iVOpportunityService.InitCustomerModeData(model, "GWGiveUpCustomer.json", CustomerObj.get(0), customerModeType);
//                return Result.ok(JSONObject.toJSON(customerModel));
//            }else{
//            	return Result.errormsg(11,"不存在客户信息！");
//            }
    	}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
    }
	@ResponseBody
    @ApiOperation(value = "潜在客户分组删除", notes = "潜在客户分组删除")
    @RequestMapping(value = "/mCustomerPotentialFilterGroupDetail_Delete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mCustomerPotentialFilterGroupDetail_Delete(@RequestBody JSONObject jsonParam) {
    	try{
            Map paramMap = (HashMap)jsonParam.get("_param");
            iBCustomerpotentialfiltergroupService.mCustomerPotentialFilterGroupDetail_Delete(paramMap.get("ID").toString());
            return Result.ok("潜在客户分组删除成功");
    	}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
    }
	@ResponseBody
	@ApiOperation(value = "潜在客户分组新增", notes = "潜在客户分组新增")
	@RequestMapping(value = "/mCustomerPotentialFilterGroupDetail_Insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mCustomerPotentialFilterGroupDetail_Insert(@RequestBody JSONObject jsonParam) {
		try{
			JSONObject paramMap = (JSONObject)jsonParam.getJSONObject("_param");
			iBCustomerpotentialfiltergroupService.mCustomerPotentialFilterGroupDetail_Insert(paramMap);
			return Result.ok("潜在客户分组添加成功");
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ResponseBody
	@ApiOperation(value = "潜在客户分组列表", notes = "潜在客户分组列表")
	@RequestMapping(value = "/mCustomerPotentialFilterGroupList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mCustomerPotentialFilterGroupList_Select(@RequestBody JSONObject jsonParam) {
		try{
			Map paramMap = (HashMap)jsonParam.get("_param");
			List<Map<String, Object>> list = iBCustomerpotentialfiltergroupService.mCustomerPotentialFilterGroupList_Select(paramMap);
			for(Map<String, Object> l : list){
				l.put("Filter", JSON.parseArray(l.get("Filter").toString()));
				l.put("FilterDesc", JSON.parseArray(l.get("FilterDesc").toString()));
			}
			return Result.ok(list);
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ResponseBody
    @ApiOperation(value = "分组删除", notes = "分组删除")
    @RequestMapping(value = "/mCustomerFilterGroupDetail_Delete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mCustomerFilterGroupDetail_Delete(@RequestBody JSONObject jsonParam) {
    	try{
            Map paramMap = (HashMap)jsonParam.get("_param");
            iBCustomerfiltergroupService.mCustomerFilterGroupDetail_Delete(paramMap.get("ID").toString());
            return Result.ok("分组删除成功");
    	}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
    }
	@ResponseBody
	@ApiOperation(value = "分组新增", notes = "分组新增")
	@RequestMapping(value = "/mCustomerFilterGroupDetail_Insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mCustomerFilterGroupDetail_Insert(@RequestBody JSONObject jsonParam) {
		try{
			JSONObject paramMap = (JSONObject)jsonParam.getJSONObject("_param");
			iBCustomerfiltergroupService.mCustomerFilterGroupDetail_Insert(paramMap);
			return Result.ok("分组添加成功");
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ResponseBody
	@ApiOperation(value = "分组列表", notes = "分组列表")
	@RequestMapping(value = "/mCustomerFilterGroupList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mCustomerFilterGroupList_Select(@RequestBody JSONObject jsonParam) {
		try{
			Map paramMap = (HashMap)jsonParam.get("_param");
			List<Map<String, Object>> list = iBCustomerfiltergroupService.mCustomerFilterGroupList_Select(paramMap);
			for(Map<String, Object> l : list){
				l.put("Filter", JSON.parseArray(l.get("Filter").toString()));
				l.put("FilterDesc", JSON.parseArray(l.get("FilterDesc").toString()));
			}
			return Result.ok(list);
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ResponseBody
	@ApiOperation(value = "确认归属", notes = "确认归属")
	@RequestMapping(value = "/mCustomerBelong_Update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mCustomerBelong_Update(@RequestBody JSONObject jsonParam) {
		try{
			Map paramMap = (HashMap)jsonParam.get("_param");
			String Opportunity = "";
			List<String> OpportunityIDList = (List) paramMap.get("OpportunityIDList");
            if (OpportunityIDList != null){
                for(String item : OpportunityIDList){
                	Opportunity += item + ",";
                }
                Opportunity = Opportunity.length() > 0 ? Opportunity.substring(0, Opportunity.length() - 1) : Opportunity;
            }
            paramMap.put("OpportunityIDList", Opportunity);
            iBOpportunityService.mCustomerBelong_Update(paramMap);
			return Result.ok("成功");
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ResponseBody
	@ApiOperation(value = "客户回收", notes = "客户回收")
	@RequestMapping(value = "/mCustomerRecovery_Update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mCustomerRecovery_Update(@RequestBody JSONObject jsonParam) {
		try{
			Map paramMap = (HashMap)jsonParam.get("_param");
			String AdviserID = "";
            String OpportunityID = "";
			List<String> OpportunityIDList = (List) paramMap.get("OpportunityIDList");
			List<String> AdviserIDList = (List) paramMap.get("AdviserIDList");
			if (AdviserIDList != null){
                for (String item : AdviserIDList){
                    AdviserID += item + ",";
                }
                AdviserID = AdviserID.length() > 0 ? AdviserID.substring(0, AdviserID.length() - 1) : AdviserID;
            }
			if (OpportunityIDList != null){
				for(String item : OpportunityIDList){
					OpportunityID += item + ",";
				}
				OpportunityID = OpportunityID.length() > 0 ? OpportunityID.substring(0, OpportunityID.length() - 1) : OpportunityID;
			}
			paramMap.put("OpportunityIDList", OpportunityID);
			paramMap.put("AdviserIDList", AdviserID);
			iBOpportunityService.mCustomerRecovery_Update(paramMap);
			return Result.ok("成功");
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ResponseBody
	@ApiOperation(value = "分配职业顾问提交", notes = "分配职业顾问提交")
	@RequestMapping(value = "/mCustomerAllotAdviser_Update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mCustomerAllotAdviser_Update(@RequestBody JSONObject jsonParam) {
		try{
			Map paramMap = (HashMap)jsonParam.get("_param");
			String AdviserID = "";
            String OpportunityID = "";
            String LostID = "";
            String PublicID = "";
			List<String> OpportunityIDList = (List) paramMap.get("OpportunityIDList");
			List<String> AdviserIDList = (List) paramMap.get("AdviserIDList");
			List<String> LostIDList = (List) paramMap.get("LostIDList");
			List<String> PublicIDList = (List) paramMap.get("PublicIDList");
			if (AdviserIDList != null){
				for (String item : AdviserIDList){
					AdviserID += item + ",";
				}
				AdviserID = AdviserID.length() > 0 ? AdviserID.substring(0, AdviserID.length() - 1) : AdviserID;
			}
			if (OpportunityIDList != null){
				for(String item : OpportunityIDList){
					OpportunityID += item + ",";
				}
				OpportunityID = OpportunityID.length() > 0 ? OpportunityID.substring(0, OpportunityID.length() - 1) : OpportunityID;
			}
			if (LostIDList != null){
				for(String item : LostIDList){
					LostID += item + ",";
				}
				LostID = LostID.length() > 0 ? LostID.substring(0, LostID.length() - 1) : LostID;
			}
			if (PublicIDList != null){
				for(String item : PublicIDList){
					PublicID += item + ",";
				}
				PublicID = PublicID.length() > 0 ? PublicID.substring(0, PublicID.length() - 1) : PublicID;
			}
			paramMap.put("OpportunityIDList", OpportunityID);
			paramMap.put("AdviserIDList", AdviserID);
			paramMap.put("LostIDList", LostID);
			paramMap.put("PublicIDList", PublicID);
			iBOpportunityService.mCustomerAllotAdviser_Update(paramMap);
			return Result.ok(true);
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ResponseBody
	@ApiOperation(value = "新-自渠客户列表", notes = "新-自渠客户列表")
	@RequestMapping(value = "/mCustomerZYQDList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mCustomerZYQDList_Select(@RequestBody JSONObject jsonParam) {
		try{
			Map paramMap = (HashMap)jsonParam.get("_param");
			Map<String,Object> map = iBOpportunityService.mCustomerZYQDList_Select(paramMap);
			return Result.ok(map);
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ResponseBody
	@ApiOperation(value = "新-丢失客户列表", notes = "新-丢失客户列表")
	@RequestMapping(value = "/mCustomerDSList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mCustomerDSList_Select(@RequestBody JSONObject jsonParam) {
		try{
			Map paramMap = (HashMap)jsonParam.get("_param");
			Map<String,Object> map = iBOpportunityService.mCustomerDSList_Select(paramMap);
			return Result.ok(map);
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ResponseBody
	@ApiOperation(value = "新-正常跟进列表", notes = "新-正常跟进列表")
	@RequestMapping(value = "/mCustomerGJList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mCustomerGJList_Select(@RequestBody JSONObject jsonParam) {
		try{
			Map paramMap = (HashMap)jsonParam.get("_param");
			Map<String,Object> map = iBOpportunityService.mCustomerGJList_Select(paramMap);
			return Result.ok(map);
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ResponseBody
	@ApiOperation(value = "新-撞单客户列表", notes = "新-撞单客户列表")
	@RequestMapping(value = "/mCustomerZDList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mCustomerZDList_Select(@RequestBody JSONObject jsonParam) {
		try{
			Map paramMap = (HashMap)jsonParam.get("_param");
			Map<String,Object> map = iBOpportunityService.mCustomerZDList_Select(paramMap);
			return Result.ok(map);
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ResponseBody
	@ApiOperation(value = "新-公共客户列表", notes = "新-公共客户列表")
	@RequestMapping(value = "/mCustomerGGList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mCustomerGGList_Select(@RequestBody JSONObject jsonParam) {
		try{
			Map paramMap = (HashMap)jsonParam.get("_param");
			Map<String,Object> map = iBOpportunityService.mCustomerGGList_Select(paramMap);
			return Result.ok(map);
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ResponseBody
	@ApiOperation(value = "逾期客户未交款列表", notes = "逾期客户未交款列表")
	@RequestMapping(value = "/mCustomerYQWJKList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mCustomerYQWJKList_Select(@RequestBody JSONObject jsonParam) {
		try{
			Map<String,Object> paramAry = jsonParam.getJSONObject("_param");
			paramAry.put("OverduePaymentStartDay", "0");
			paramAry.put("OverduePaymentEndDay", "3650");
            if (!StringUtils.isEmpty(paramAry.get("Filter"))){
            	List<Map<String,Object>> Filter = (List) paramAry.get("Filter");
            	for (Map<String,Object> item : Filter){
                	String TagID = (String) item.get("TagID");
                	switch (TagID){
                    	case "883F5965-7D43-4EC9-918C-CF09BD625460":{//未交款周期
	                    	String start = (String) ((List) item.get("Child")).get(0);
	                     	String end = (String) ((List) item.get("Child")).get(1);
	                    	paramAry.put("OverduePaymentStartDay", (start == "-" ? "0" : start));
	                    	paramAry.put("OverduePaymentEndDay", (end == "-" ? "3650" : end));
                    	}
                        break;
                    }
            	}
            }
			Map<String,Object> map = iBOpportunityService.mCustomerYQWJKList_Select(paramAry);
			return Result.ok(map);
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ResponseBody
	@ApiOperation(value = "逾期客户未签约列表", notes = "逾期客户未签约列表")
	@RequestMapping(value = "/mCustomerYQWQYList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mCustomerYQWQYList_Select(@RequestBody JSONObject jsonParam) {
		try{
			Map<String,Object> paramAry = jsonParam.getJSONObject("_param");
			paramAry.put("OverdueContractStartDay", "0");
			paramAry.put("OverdueContractEndDay", "3650");
			if (!StringUtils.isEmpty(paramAry.get("Filter"))){
            	List<Map<String,Object>> Filter = (List) paramAry.get("Filter");
            	for (Map<String,Object> item : Filter){
                	String TagID = (String) item.get("TagID");
                	switch (TagID){
                    	case "2D0B4FD7-22C0-4225-A300-6AF34683DCAE":{//未签约周期
	                    	String start = (String) ((List) item.get("Child")).get(0);
	                     	String end = (String) ((List) item.get("Child")).get(1);
	                    	paramAry.put("OverdueContractStartDay", (start == "-" ? "0" : start));
	                    	paramAry.put("OverdueContractEndDay", (end == "-" ? "3650" : end));
                    	}
                        break;
                    }
            	}
            }
			Map<String,Object> map = iBOpportunityService.mCustomerYQWQYList_Select(paramAry);
			return Result.ok(map);
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ResponseBody
	@ApiOperation(value = "逾期客户未认购列表", notes = "逾期客户未认购列表")
	@RequestMapping(value = "/mCustomerYQWRGList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mCustomerYQWRGList_Select(@RequestBody JSONObject jsonParam) {
		try{
			Map paramMap = (HashMap)jsonParam.get("_param");
			Map<String,Object> map = iBOpportunityService.mCustomerYQWRGList_Select(paramMap);
			return Result.ok(map);
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ResponseBody
	@ApiOperation(value = "逾期客户未跟进列表", notes = "逾期客户未跟进列表")
	@RequestMapping(value = "/mCustomerYQWGJList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mCustomerYQWGJList_Select(@RequestBody JSONObject jsonParam) {
		try{
			Map paramMap = (HashMap)jsonParam.get("_param");
			Map<String,Object> map = iBOpportunityService.mCustomerYQWGJList_Select(paramMap);
			return Result.ok(map);
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ResponseBody
	@ApiOperation(value = "逾期客户顾问列表", notes = "逾期客户顾问列表")
	@RequestMapping(value = "/mCustomerAdviserList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mCustomerAdviserList_Select(@RequestBody JSONObject jsonParam) {
		try{
			Map paramMap = (HashMap)jsonParam.get("_param");
			List<Map<String,Object>> Filter = (List<Map<String, Object>>) paramMap.get("Filter");
			paramMap.put("OverdueContractStartDay", "0");
            paramMap.put("OverdueContractEndDay", "3650");
            paramMap.put("OverduePaymentStartDay", "0");
            paramMap.put("OverduePaymentEndDay", "3650");
            
			if(!StringUtils.isEmpty(paramMap.get("Filter"))){
				for(Map<String,Object> item : Filter){
					String TagID = (String) item.get("TagID");
                    switch (TagID){
                        case "2D0B4FD7-22C0-4225-A300-6AF34683DCAE":{//未签约周期
                    		String start = (String) ((List)item.get("Child")).get(0);
                            String end = (String) ((List)item.get("Child")).get(1);
                            paramMap.put("OverdueContractStartDay", (start == "-" ? "0" : start));
                            paramMap.put("OverdueContractEndDay", (end == "-" ? "3650" : end));
						}
                        break;
                        case "883F5965-7D43-4EC9-918C-CF09BD625460":{//未交款周期
                        	String start = (String) ((List)item.get("Child")).get(0);
                            String end = (String) ((List)item.get("Child")).get(1);
                            paramMap.put("OverduePaymentStartDay", (start == "-" ? "0" : start));
                            paramMap.put("OverduePaymentEndDay", (end == "-" ? "3650" : end));
                      	}
                    	break;
                    }
				}
			}
			Map<String,Object> result = null;
			if (!StringUtils.isEmpty(paramMap.get("RequestType")) && "YQWQY".equals(paramMap.get("Code"))){
				result = iBOpportunityService.R_ACYQWQYList_Select(paramMap);
			}else if(!StringUtils.isEmpty(paramMap.get("RequestType")) && "YQWJK".equals(paramMap.get("Code"))){
				result = iBOpportunityService.R_ACYQWJKList_Select(paramMap);
			}else{
				result = iBOpportunityService.mCustomerAdviserList_Select(paramMap);
			}
			return Result.ok(result);
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ResponseBody
	@ApiOperation(value = "逾期客户头部统计", notes = "逾期客户头部统计")
	@RequestMapping(value = "/mCustomerYQCateList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mCustomerYQCateList_Select(@RequestBody JSONObject jsonParam) {
		try{
			Map paramMap = (HashMap)jsonParam.get("_param");
			return Result.ok(iBOpportunityService.mCustomerYQCateList_Select(paramMap));
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ResponseBody
	@ApiOperation(value = "客户管理分类", notes = "客户管理分类")
	@RequestMapping(value = "/mCustomerCateList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mCustomerCateList_Select(@RequestBody JSONObject jsonParam) {
		try{
			Map paramMap = (HashMap)jsonParam.get("_param");
			return Result.ok(iBOpportunityService.mCustomerCateList_Select(paramMap));
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ResponseBody
	@ApiOperation(value = "顾问分配", notes = "案场营销经理顾问分配")
	@RequestMapping(value = "/mCustomerYXJLAdviser_Update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mCustomerYXJLAdviser_Update(@RequestBody JSONObject jsonParam) {
		try{
			Map paramMap = (HashMap)jsonParam.get("_param");
            //1.丢失中的客户不允许重分配
            List<Map<String,Object>> valid = iBOpportunitygiveupService.GiveUpListStatusFind(paramMap);
            if (valid != null && valid.size() > 0){
                return Result.errormsg(1,"丢失中的客户不能重新分配，请先处理丢失申请！");
            }
            iBOpportunitygiveupService.mCustomerYXJLAdviser_Update(paramMap);
            //2.营销经理重新分配【协作人】置空
            iBOpportunitygiveupService.mCustomerYXJLSalePartnerSetNull_Update(paramMap);
			return Result.ok(new ArrayList());
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ResponseBody
	@ApiOperation(value = "分接顾问查询", notes = "案场营销经理分配顾问查询")
	@RequestMapping(value = "/mCustomerYXJLAdviserList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mCustomerYXJLAdviserList_Select(@RequestBody JSONObject jsonParam) {
		try{
			Map paramMap = (HashMap)jsonParam.get("_param");
			int PageIndex = (int)paramMap.get("PageIndex");//页面索引
            int PageSize = (int)paramMap.get("PageSize");//每页数量
            String ProjectID = (String)paramMap.get("ProjectID");
			StringBuilder whereSb = new StringBuilder();
            StringBuilder OrderSb = new StringBuilder();
            if (!StringUtils.isEmpty(paramMap.get("Filter"))){
                whereSb.append(" and GroupID in('" + paramMap.get("Filter").toString() + "')");
            }
            if (!StringUtils.isEmpty(paramMap.get("Sort"))){
                if ("4E2A0673-9987-C488-57DF-CB367655BDEA".equals(paramMap.get("Sort"))){//累计分配最多
                    whereSb.append(" ORDER BY  DayTotalCount desc");
                }
                if ("1552E084-1238-6E31-96B1-3ABE59895281".equals(paramMap.get("Sort"))){//累计分配最少
                    whereSb.append(" ORDER BY  DayTotalCount asc ");
                }

                if ("27427A81-9E4B-375C-AED7-A00D1AA38864".equals(paramMap.get("Sort"))){//客户总数最多
                    whereSb.append(" ORDER BY  CustomerTotalCount desc");
                }
                if ("46DCCC7C-D52E-901B-0A3F-A77B14641D73".equals(paramMap.get("Sort"))){//客户总数最少
                    whereSb.append(" ORDER BY  CustomerTotalCount asc ");
                }

                if ("518DC5AA-3F06-A3EC-4FCC-B33098619A77".equals(paramMap.get("Sort"))){//正在跟进最多
                    whereSb.append(" ORDER BY  FollowTotalCount Desc ");
                }
                if ("EE4354BB-B111-E704-F390-0109F2513BA4".equals(paramMap.get("Sort"))){//正在跟进最少
                    whereSb.append(" ORDER BY  FollowTotalCount asc ");
                }
            }else{
                whereSb.append(" ORDER BY  DayTotalCount desc");

            }
            IPage page = new Page(PageIndex, PageSize);
			IPage<Map<String, Object>> ob = iBOpportunitygiveupService.mCustomerYXJLAdviserList_Select(page,ProjectID,whereSb.toString(),OrderSb.toString(),SiteUrl);
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("List", ob.getRecords());
			map.put("AllCount", ob.getTotal());
			map.put("PageSize", ob.getSize());
			return Result.ok(map);
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ResponseBody
	@ApiOperation(value = "营销经理公共资源池", notes = "案场营销经理客户待分配用户查询")
	@RequestMapping(value = "/mCustomerYXJLList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mCustomerYXJLList_Select(@RequestBody JSONObject jsonParam) {
		try{
			Map paramMap = (HashMap)jsonParam.get("_param");
			int PageIndex = (int)paramMap.get("PageIndex");//页面索引
			int PageSize = (int)paramMap.get("PageSize");//每页数量
			String ProjectID = (String)paramMap.get("ProjectID");
			StringBuilder whereSb = new StringBuilder();
			StringBuilder OrderSb = new StringBuilder();
			if (!StringUtils.isEmpty(paramMap.get("Filter"))){
				whereSb.append(" and AdviserOrgID in('" + paramMap.get("Filter").toString() + "')");
			}
			if (!StringUtils.isEmpty(paramMap.get("Sort"))){
				if ("7500BF27-A088-1975-240B-7F0D247E7A7B".equals(paramMap.get("Sort"))){//最新回收
                    whereSb.append(" ORDER BY  RecoveryCreateTime desc");
                }
                if ("BD2933BB-4948-B57D-4916-FF991AFA7FA8".equals(paramMap.get("Sort"))){//最早回收
                    whereSb.append(" ORDER BY  RecoveryCreateTime asc ");
                }
                if ("F19A7E4E-743F-5F75-AE06-9CF8FF84B46F".equals(paramMap.get("Sort"))){//最多跟进
                    whereSb.append(" ORDER BY  FollowupCountValue desc");
                }
                if ("2FF60BA7-8AAE-03BB-6C59-38EAD42D2C1F".equals(paramMap.get("Sort"))){//最少跟进
                    whereSb.append(" ORDER BY  FollowupCountValue asc ");
                }
			}else{
				whereSb.append(" ORDER BY  RecoveryCreateTime desc");
			}
			IPage page = new Page(PageIndex, PageSize);
			IPage<Map<String, Object>> ob = iBOpportunitygiveupService.mCustomerYXJLList_Select(page,ProjectID,whereSb.toString(),OrderSb.toString());
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("List", ob.getRecords());
			map.put("AllCount", ob.getTotal());
			map.put("PageSize", ob.getSize());
			return Result.ok(map);
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ResponseBody
	@ApiOperation(value = "顾问列表查询", notes = "案场营销经理分配顾问列表")
	@RequestMapping(value = "/mCustomerXSJLAdviserList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mCustomerXSJLAdviserList_Select(@RequestBody JSONObject jsonParam) {
		try{
			Map paramMap = (HashMap)jsonParam.get("_param");
			int PageIndex = (int)paramMap.get("PageIndex");//页面索引
			int PageSize = (int)paramMap.get("PageSize");//每页数量
			String ProjectID = (String)paramMap.get("ProjectID");
			StringBuilder whereSb = new StringBuilder();
			StringBuilder OrderSb = new StringBuilder();
			whereSb.append(" and GroupID in('" + paramMap.get("OrgID") + "')");
			if (!StringUtils.isEmpty(paramMap.get("Filter"))){
				whereSb.append(" and SaleUserID in('" + paramMap.get("Filter").toString() + "')");
			}
			if (!StringUtils.isEmpty(paramMap.get("Sort"))){
				if ("4E2A0673-9987-C488-57DF-CB367655BDEA".equals(paramMap.get("Sort"))){//累计分配最多
                    whereSb.append(" ORDER BY  DayTotalCount desc");
                }
                if ("1552E084-1238-6E31-96B1-3ABE59895281".equals(paramMap.get("Sort"))){//累计分配最少
                    whereSb.append(" ORDER BY  DayTotalCount asc ");
                }
                if ("27427A81-9E4B-375C-AED7-A00D1AA38864".equals(paramMap.get("Sort"))){//客户总数最多
                    whereSb.append(" ORDER BY  CustomerTotalCount desc");
                }
                if ("46DCCC7C-D52E-901B-0A3F-A77B14641D73".equals(paramMap.get("Sort"))){//客户总数最少
                    whereSb.append(" ORDER BY  CustomerTotalCount asc ");
                }
                if ("518DC5AA-3F06-A3EC-4FCC-B33098619A77".equals(paramMap.get("Sort"))){//正在跟进最多
                    whereSb.append(" ORDER BY  FollowTotalCount Desc ");
                }
                if ("EE4354BB-B111-E704-F390-0109F2513BA4".equals(paramMap.get("Sort"))){//正在跟进最少
                    whereSb.append(" ORDER BY  FollowTotalCount asc ");
                }
			}else{
				whereSb.append(" ORDER BY  DayTotalCount desc");
			}
			IPage page = new Page(PageIndex, PageSize);
			IPage<Map<String, Object>> ob = iBOpportunitygiveupService.mCustomerYXJLAdviserList_Select(page,ProjectID,whereSb.toString(),OrderSb.toString(),SiteUrl);
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("List", ob.getRecords());
			map.put("AllCount", ob.getTotal());
			map.put("PageSize", ob.getSize());
			/*if (model.Ctype != null)
            {
                if (model.Ctype == "1")
                {
                    JObject p = new JObject();
                    p.Add("ID", model.ClueID);
                    p.Add("ValidationMode", "4");
                    var vdata = (JObject)JsonDataHelper.GetNormalData("CaseFieCustomerDetail_Update", out errmsg, debug);
                }
            }*/
			return Result.ok(map);
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ResponseBody
	@ApiOperation(value = "激活", notes = "案场销售经理激活")
	@RequestMapping(value = "/mCustomerXSJLActive_Update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mCustomerXSJLActive_Update(@RequestBody JSONObject jsonParam) {
		try{
			Map paramMap = (HashMap)jsonParam.get("_param");
			String OpportunityIDNew = "";
            String OpportunityID = (String) paramMap.get("OpportunityID");
            if (!StringUtils.isEmpty(OpportunityID)){
                int count = OpportunityID.split(",").length;
                String[] OpportunityIDNewList = new String[count];
                for (int i = 0; i < count; i++){
                    OpportunityIDNewList[i] = UUID.randomUUID().toString();
                }
                OpportunityIDNew = String.join(",", OpportunityIDNewList);
            }
            paramMap.put("OpportunityIDNew", OpportunityIDNew);
            iBOpportunitygiveupService.mCustomerYXJLActive_Update(paramMap);
			return Result.ok(new ArrayList());
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ResponseBody
	@ApiOperation(value = "回收", notes = "案场销售经理回收")
	@RequestMapping(value = "/mCustomerXSJLRecovery_Update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mCustomerXSJLRecovery_Update(@RequestBody JSONObject jsonParam) {
		try{
			Map paramMap = (HashMap)jsonParam.get("_param");
			iBOpportunitygiveupService.mCustomerYXJLRecovery_Update(paramMap);
			return Result.ok(new ArrayList());
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ResponseBody
	@ApiOperation(value = "分配", notes = "案场销售经理顾问分配")
	@RequestMapping(value = "/mCustomerXSJLAdviser_Update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mCustomerXSJLAdviser_Update(@RequestBody JSONObject jsonParam) {
		try{
			Map paramMap = (HashMap)jsonParam.get("_param");
            //1.丢失中的客户不允许重分配
            List<Map<String,Object>> valid = iBOpportunitygiveupService.GiveUpListStatusFind(paramMap);
            if (valid != null && valid.size() > 0){
                return Result.errormsg(1,"丢失中的客户不能重新分配，请先处理丢失申请！");
            }
            iBOpportunitygiveupService.mCustomerYXJLAdviser_Update(paramMap);
            //2.营销经理重新分配【协作人】置空
            iBOpportunitygiveupService.mCustomerYXJLSalePartnerSetNull_Update(paramMap);
			return Result.ok(new ArrayList());
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ResponseBody
	@ApiOperation(value = "客户列表页", notes = "案场销售经理客户查询")
	@RequestMapping(value = "/mCustomerXSJLList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mCustomerXSJLList_Select(@RequestBody JSONObject jsonParam) {
		try{
			JSONObject paramMap = jsonParam.getJSONObject("_param");
			List<FilterItem> Filter = JSON.parseArray(paramMap.getString("Filter"),FilterItem.class);
			String KeyWord = (String) paramMap.get("KeyWord");
			String ProjectID = (String) paramMap.get("ProjectID");
			String OrgID = (String) paramMap.get("OrgID");
			String Type = (String) paramMap.get("Type");
			String Sort = (String) paramMap.get("Sort");
			
			StringBuilder whereSb = new StringBuilder();
            StringBuilder OrderSb = new StringBuilder();
            if (!StringUtils.isEmpty(KeyWord)){
                whereSb.append(" and (CustomerMobile like '%" + KeyWord + "%' OR CustomerName like '%" + KeyWord + "%')");
            }
            if (!OrgID.equals(ProjectID)){
                whereSb.append(" and AdviserOrgID='" + OrgID + "'");
            }
			
            switch (Type){
                case "GJKH"://跟进客户
                    whereSb.append(" and OpportunityStatusValue <> 6 and RecoveryID = '' and LostID = ''");
                    if (Filter != null && Filter.size() > 0){
                        for (FilterItem filterItem : Filter){
                            if (filterItem != null && filterItem.getTagID() != null && filterItem.getChild() != null && filterItem.getChild().size() > 0){
                                switch (filterItem.getTagID()){
                                    case "691CC268-51A0-4D45-9266-516926C3DD55"://客户状态
                                        for (int i = 0; i < filterItem.getChild().size(); i++){
                                            switch (filterItem.getChild().get(i)){
                                                case "05867891-C4B0-43F7-896F-FBB48C340147": filterItem.getChild().set(i, "1"); break;//问询
                                                case "A53F17C1-A9F7-46FA-A185-E3242B8BDBB4": filterItem.getChild().set(i, "2"); break;//看房
                                                case "114B5471-EA83-4A6E-B038-E7304ACA5C8C": filterItem.getChild().set(i, "3"); break;//认购中
                                                case "B2761972-56A1-4759-9A94-D8256D377D88": filterItem.getChild().set(i, "4"); break;//认购
                                                case "519E5657-4CC4-44B5-A547-B479689670F5": filterItem.getChild().set(i, "5"); break;//签约
                                                case "ED2E370A-21B2-40C7-B7AF-C299D23A2077": filterItem.getChild().set(i, "7"); break;//退房
                                            }
                                        }
                                        whereSb.append(" and OpportunityStatusValue in('" + String.join("','", filterItem.getChild()) + "')");
                                        break;
                                    case "087DF0A6-F8D6-4F59-9D1F-F4679CF5607C"://意向等级
                                        for (int i = 0; i < filterItem.getChild().size(); i++){
                                            switch (filterItem.getChild().get(i)){
                                                case "D32508DC-4192-4275-AD55-BD1DF0790C5B": filterItem.getChild().set(i, "2A357E4A-90D7-5D69-C209-E26CFA5839FA"); break;//必买
                                                case "FA5364B5-7A31-43AB-834D-9E1F11278BFF": filterItem.getChild().set(i, "DF2057E2-303B-1F14-4075-069668D3A3BE"); break;//较高
                                                case "8F09F3E0-5181-4310-83E1-5FC72FFB9A18": filterItem.getChild().set(i, "FA35879A-CCE4-D332-0FAB-ADB57EBCAC9D"); break;//低
                                                case "CD396C52-11F6-4385-B033-148B41679F1E": filterItem.getChild().set(i, "9CEA46E8-A3ED-409E-646C-F38A5EAC383E"); break;//一般
                                                case "692BD4F1-6445-4271-AC83-0A1002303523": filterItem.getChild().set(i, "84640879-F4A7-CB87-E39B-F18070BCA568"); break;//无
                                            }
                                        }
                                        whereSb.append(" and CustomerLevelValue in('" + String.join("','", filterItem.getChild()) + "')");
                                        break;
                                    case "443DF083-51C0-487F-A1C6-C33884BCB9D5"://置业顾问
                                        whereSb.append(" and AdviserID in('" + String.join("','", filterItem.getChild()) + "')");
                                        break;
                                    case "D75EFC06-0BC9-470D-9508-41F26AE2098E"://未跟进
                                        int days = 0;
                                        for (int i = 0; i < filterItem.getChild().size(); i++){
                                            switch (filterItem.getChild().get(i)){
                                                case "C98D5943-64D9-4DFC-9315-B57C5595C48B":
                                                    if (i == 0) days = 3;
                                                    days = days >= 3 ? 3 : days;
                                                    break;//≥3天
                                                case "C03AD70E-31BE-421B-9237-6D9445B90B56":
                                                    if (i == 0) days = 5;
                                                    days = days >= 5 ? 5 : days;
                                                    break;//≥5天
                                                case "16561320-9678-4F09-BE4D-89F395EFD3EF":
                                                    if (i == 0) days = 7;
                                                    days = days >= 7 ? 7 : days;
                                                    break;//≥7天
                                            }
                                        }
                                        if (days > 0){
                                            whereSb.append(" and TheLatestFollowUpDateTime <= DATEADD(d,-" + days + ",GETDATE())");
                                        }
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                    }
                    if (!StringUtils.isEmpty(Sort)){
                        if ("CB162139-5453-4AC9-B04F-EBC45C71C0D1".equals(Sort)){//最新跟新
                            OrderSb.append(" ORDER BY  TheLatestFollowUpDateTime desc");
                        }
                        if ("077BD4D7-53D3-4E6B-9459-C63D421A9905".equals(Sort)){//最早跟进
                            OrderSb.append(" ORDER BY  TheLatestFollowUpDateTime asc ");
                        }
                        if ("F14E1843-1738-4F03-BBA2-972CADCC6C2B".equals(Sort)){//最多跟进
                            OrderSb.append(" ORDER BY  FollowupCountValue desc");
                        }
                        if ("1DA39A0C-AB62-497F-B3CC-74CE49CEF122".equals(Sort)){//最少跟进
                            OrderSb.append(" ORDER BY  FollowupCountValue asc ");
                        }
                    }else{
                        OrderSb.append(" ORDER BY  TheLatestFollowUpDateTime DESC");
                    }
                    if (OrderSb.length() == 0){
                        OrderSb.append(" ORDER BY  TheLatestFollowUpDateTime DESC");
                    }
                    break;
                case "GGKH"://公共客户
                    whereSb.append(" and RecoveryID <> ''");
                    if (Filter != null && Filter.size() > 0){
                        for (FilterItem filterItem : Filter){
                            if (filterItem != null && filterItem.getTagID() != null && filterItem.getChild() != null && filterItem.getChild().size() > 0){
                                switch (filterItem.getTagID()){
                                    case "4D6878A8-C939-4F5C-A8CF-68F64D825BE8"://置业顾问
                                        whereSb.append(" and AdviserID in('" + String.join("','", filterItem.getChild()) + "')");
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                    }
                    if (!StringUtils.isEmpty(Sort)){
                        if ("8ECAA16B-A707-43F6-B220-164782840879".equals(Sort)){//最新回收
                            OrderSb.append(" ORDER BY  RecoveryCreateTime desc");
                        }
                        if ("27ECECF6-63C4-4CC1-B542-618657F43219".equals(Sort)){//最早回收
                            OrderSb.append(" ORDER BY  RecoveryCreateTime asc ");
                        }
                        if ("80697595-0188-4FE2-ACB5-E6AF9F2A81E0".equals(Sort)){//最多跟进
                            OrderSb.append(" ORDER BY  FollowupCountValue desc");
                        }
                        if ("821D3346-041F-41A7-AEC5-99A2DCF57B7D".equals(Sort)){//最少跟进
                            OrderSb.append(" ORDER BY  FollowupCountValue asc ");
                        }
                    }else{
                        OrderSb.append(" ORDER BY  RecoveryCreateTime DESC");
                    }
                    if (OrderSb.length() == 0){
                        OrderSb.append(" ORDER BY  RecoveryCreateTime DESC");
                    }
                    break;
                case "DSKH"://丢失客户
                    whereSb.append(" AND OpportunityStatusValue=6 AND LEN(AdviserID) = 36 AND LostID IS NOT NULL AND LostID<>'' ");
                    if (Filter != null && Filter.size() > 0){
                        for (FilterItem filterItem : Filter){
                            if (filterItem != null && filterItem.getTagID() != null && filterItem.getChild() != null && filterItem.getChild().size() > 0)
                            {
                                switch (filterItem.getTagID()){
                                    case "19DCCE1C-557B-4ECC-8001-376578B3EF07"://置业顾问
                                        whereSb.append(" and AdviserID in('" + String.join("','", filterItem.getChild()) + "')");
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                    }
                    if (!StringUtils.isEmpty(Sort)){
                        if ("45D66D53-F5B5-4B09-B3A9-5AF652F23093".equals(Sort)){//最新丢失
                            OrderSb.append(" ORDER BY  LostCreateTime desc");
                        }
                        if ("3635B58D-A7B1-4846-90A7-F485F1336A15".equals(Sort)){//最早丢失
                            OrderSb.append(" ORDER BY  LostCreateTime asc ");
                        }
                    } else{
                        OrderSb.append(" ORDER BY  LostCreateTime DESC");
                    }
                    if (OrderSb.length() == 0)
                    {
                        OrderSb.append(" ORDER BY  LostCreateTime DESC");
                    }
                    break;
            }
            paramMap.put("WHERE", whereSb.toString());
            paramMap.put("ORDER", OrderSb.toString());
			
			Map<String, Object> ob = iBOpportunitygiveupService.mCustomerXSJLList_Select(paramMap);
			return Result.ok(ob);
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ResponseBody
	@ApiOperation(value = "客户公共池", notes = "客户公共池查询")
	@RequestMapping(value = "/mCustomerGGCList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mCustomerGGCList_Select(@RequestBody JSONObject jsonParam) {
		try{
			System.out.println("==========================>>>>>"+jsonParam);
			JSONObject paramMap = jsonParam.getJSONObject("_param");
			String KeyWord = (String) paramMap.get("KeyWord");
			List<FilterItem> Filter = JSON.parseArray(paramMap.getString("Filter"),FilterItem.class);
			String GroupID = (String) paramMap.get("GroupID");
			String Sort = (String) paramMap.get("Sort");
			
			StringBuilder whereSb = new StringBuilder();
            StringBuilder orderSb = new StringBuilder();
//            boolean isMobile = Regex.IsMatch(KeyWord, @"^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$");
            //手机或姓名
            if (!StringUtils.isEmpty(KeyWord)){
                whereSb.append(" and (CustomerName Like'%"+KeyWord+"%' or CustomerMobile Like'%"+KeyWord+"%') ");
            }
            //筛选条件
            paramMap = whereScreen(whereSb,Filter,paramMap);
            
            //排序字段
            if ("BB4DEEF7-26E2-412E-B946-1695742A6702".equals(Sort) || "AB4DEEF7-26E2-412E-B946-1695742A6702".equals(Sort))
            {//创建时间正序
                orderSb.append(" ORDER BY  A.CreateTime  ");
            }else if ("BB4DEEF7-26E2-412E-B946-1695742A6703".equals(Sort) || "AB4DEEF7-26E2-412E-B946-1695742A6703".equals(Sort))
            {//创建时间倒序
                orderSb.append(" ORDER BY A.TheLatestFollowUpDate desc ");
            }else if ("BB4DEEF7-26E2-412E-B946-1695742A6704".equals(Sort) || "AB4DEEF7-26E2-412E-B946-1695742A6704".equals(Sort))
            {//跟进时间正序
                orderSb.append(" ORDER BY A.TheLatestFollowUpDate  ");
            }else if ("BB4DEEF7-26E2-412E-B946-1695742A6705".equals(Sort) || "AB4DEEF7-26E2-412E-B946-1695742A6705".equals(Sort))
            {//跟进时间倒序
                orderSb.append(" ORDER BY A.TheLatestFollowUpDate  desc ");
            }else{
                orderSb.append(" ORDER BY  A.TheLatestFollowUpDate desc ");
            }
			
//            paramMap.put("WHERE", whereSb.toString());
            paramMap.put("ORDER", orderSb.toString());
            Map<String,Object> data = iBCustomerpublicpoolService.mCustomerGGCList_Select(paramMap);
            data = iBCustomerpublicpoolService.mCustomerGGCGrabDetail_Select(data, paramMap);
			return Result.ok(data);
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	private JSONObject whereScreen(StringBuilder whereSb, List<FilterItem> Filter, JSONObject paramMap) {
		String GroupID = (String) paramMap.get("GroupID");
		if (Filter != null && Filter.size() > 0){
        	boolean tagId = false;
            for(FilterItem filterItem : Filter){
            	if("EB4DEEF7-26E2-412E-B946-1695742A6704".equals(filterItem.getTagID())
            			|| "CB4DEEF7-26E2-412E-B946-1695742A6704".equals(filterItem.getTagID())){
            		tagId = true;
            		break;
            	}
            }
        	
        	if (tagId == true){//Filter.contains("EB4DEEF7-26E2-412E-B946-1695742A6704")){
            	paramMap.put("GroupID", Filter.get(0).getChild().get(0));
                String filterstr = mGetFilterGroupList_Select(paramMap.getString("GroupID"));
                if (filterstr != null && !filterstr.equals("")){
                	List<FilterItem> filter = JSONObject.parseArray(filterstr, FilterItem.class);
                    Filter = filter;
                }else{
                    Filter = null;
                }
            }
            for (FilterItem filterItem : Filter){
                if (filterItem != null && filterItem.getTagID() != null){
                    //客储等级-顾问
                    if ("EB4DEEF7-26E2-412E-B946-1695742A6702".equals(filterItem.getTagID())){
                        if (filterItem.getChild() != null && filterItem.getChild().size() > 0){
                            for (int i = 0; i < filterItem.getChild().size(); i++){
                                switch (filterItem.getChild().get(i)){
                                    case "EB4DEEF7-26E2-412E-B946-1695742A6706": 
                                    	filterItem.getChild().set(i, "41FA0234-F8AE-434F-8BCD-6E9BE1D059DA"); 
                                    	break;//1级
                                    case "EB4DEEF7-26E2-412E-B946-1695742A6707": 
                                    	filterItem.getChild().set(i, "FC420696-6F6C-40B1-BE17-96FCEC75B0F2"); 
                                    	break;//1.5级
                                    case "EB4DEEF7-26E2-412E-B946-1695742A6708": 
                                    	filterItem.getChild().set(i, "ED0AD9E6-AF72-424C-9EE0-9884FF31FA42"); 
                                    	break;//2级
                                }
                            }
                            whereSb.append(" and A.CustomerRank in('" + String.join("','", filterItem.getChild()) + "')");
                        }
                    }
                    //客储等级-自渠
                    if ("CB4DEEF7-26E2-412E-B946-1695742A6702".equals(filterItem.getTagID())){
                    	if (filterItem.getChild() != null && filterItem.getChild().size() > 0){
                    		for (int i = 0; i < filterItem.getChild().size(); i++){
                    			switch (filterItem.getChild().get(i)){
                    				case "CB4DEEF7-26E2-412E-B946-1695742A6706": 
                    					filterItem.getChild().set(i, "41FA0234-F8AE-434F-8BCD-6E9BE1D059DA"); 
                    					break;//1级
                    				case "CB4DEEF7-26E2-412E-B946-1695742A6707": 
                    					filterItem.getChild().set(i, "FC420696-6F6C-40B1-BE17-96FCEC75B0F2"); 
                    					break;//1.5级
                    				case "CB4DEEF7-26E2-412E-B946-1695742A6708": 
                    					filterItem.getChild().set(i, "ED0AD9E6-AF72-424C-9EE0-9884FF31FA42");
                    					break;//2级
                    			}
                    		}
                    		whereSb.append(" and A.CustomerRank in('" + String.join("','", filterItem.getChild()) + "')");
                    	}
                    }
                    //意向级别-顾问
                    if ("EB4DEEF7-26E2-412E-B946-1695742A6703".equals(filterItem.getTagID())){
                        if (filterItem.getChild() != null && filterItem.getChild().size() > 0){
                            for (int i = 0; i < filterItem.getChild().size(); i++){
                                switch (filterItem.getChild().get(i)){
                                    case "EB4DEEF7-26E2-412E-B946-1695742A6709": 
                                    	filterItem.getChild().set(i, "2A357E4A-90D7-5D69-C209-E26CFA5839FA"); 
                                    	break;//A级
                                    case "EB4DEEF7-26E2-412E-B946-1695742A6710": 
                                    	filterItem.getChild().set(i, "DF2057E2-303B-1F14-4075-069668D3A3BE"); 
                                    	break;//B级
                                    case "EB4DEEF7-26E2-412E-B946-1695742A6711": 
                                    	filterItem.getChild().set(i, "9CEA46E8-A3ED-409E-646C-F38A5EAC383E"); 
                                    	break;//C级
                                    case "EB4DEEF7-26E2-412E-B946-1695742A6712": 
                                    	filterItem.getChild().set(i, "FA35879A-CCE4-D332-0FAB-ADB57EBCAC9D"); 
                                    	break;//D级
                                }
                            }
                            whereSb.append(" and A.CustomerLevel in('" + String.join("','", filterItem.getChild()) + "')");
                        }
                    }
                    //意向级别-自渠
                    if ("CB4DEEF7-26E2-412E-B946-1695742A6703".equals(filterItem.getTagID())){
                    	if (filterItem.getChild() != null && filterItem.getChild().size() > 0){
                    		for (int i = 0; i < filterItem.getChild().size(); i++){
                    			switch (filterItem.getChild().get(i)){
                    			case "CB4DEEF7-26E2-412E-B946-1695742A6709": 
                    				filterItem.getChild().set(i, "2A357E4A-90D7-5D69-C209-E26CFA5839FA");
                    				break;//A级
                    			case "CB4DEEF7-26E2-412E-B946-1695742A6710": 
                    				filterItem.getChild().set(i, "DF2057E2-303B-1F14-4075-069668D3A3BE"); 
                    				break;//B级
                    			case "CB4DEEF7-26E2-412E-B946-1695742A6711": 
                    				filterItem.getChild().set(i, "9CEA46E8-A3ED-409E-646C-F38A5EAC383E"); 
                    				break;//C级
                    			case "CB4DEEF7-26E2-412E-B946-1695742A6712": 
                    				filterItem.getChild().set(i, "FA35879A-CCE4-D332-0FAB-ADB57EBCAC9D"); 
                    				break;//D级
                    			}
                    		}
                    		whereSb.append(" and A.CustomerLevel in('" + String.join("','", filterItem.getChild()) + "')");
                    	}
                    }
                }
            }
        }
		paramMap.put("WHERE", whereSb.toString());
		return paramMap;
	}
	private String mGetFilterGroupList_Select(String groupID) {
		QueryWrapper<BCustomerfiltergroup> wrapper = new QueryWrapper<BCustomerfiltergroup>();
		wrapper.eq("ID", groupID);
//		SELECT Filter FROM dbo.B_CustomerFilterGroup WHERE ID='{GroupID}'
		BCustomerfiltergroup g = iBCustomerfiltergroupService.getOne(wrapper);
		return g.getFilter();
	}
	@ResponseBody
	@ApiOperation(value = "分享传播池", notes = "分享传播池查询")
	@RequestMapping(value = "/mCustomerFXCBList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mCustomerFXCBList_Select(@RequestBody JSONObject jsonParam) {
		try{
			Map paramMap = (HashMap)jsonParam.get("_param");
			String KeyWord = (String) paramMap.get("KeyWord");
			
			StringBuilder whereSb = new StringBuilder();
            //手机或姓名
            if (!StringUtils.isEmpty(KeyWord)){
                whereSb.append(" and (CWXU.NickName Like'%"+KeyWord+"%' or CWXU.Mobile Like'%"+KeyWord+"%') ");
            }
			
            paramMap.put("Where", whereSb.toString());
            Map<String,Object> data = iBCustomerpublicpoolService.mCustomerSharePageList_Select(paramMap);
            data = iBCustomerpublicpoolService.mCustomerFXCBGrabDetail_Select(data, paramMap);
			return Result.ok(data);
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ResponseBody
	@ApiOperation(value = "公共池抢客", notes = "客户公共池抢客")
	@RequestMapping(value = "/mCustomerGGCList_Insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mCustomerGGCList_Insert(@RequestBody JSONObject jsonParam) {
		try{
			Map paramMap = (HashMap)jsonParam.get("_param");
			String JobID = (String) paramMap.get("JobID");
			String PublicIDs = (String) paramMap.get("PublicIDs");
			int ErrCode = 0;
			String ErrMsg = "";
			Map<String,Object> data = null;
			//判断当前用户身份 ， 置业顾问与自渠
			if (!StringUtils.isEmpty(JobID) && "48FC928F-6EB5-4735-BF2B-29B1F591A582".equals(JobID)){//自渠
                if (PublicIDs != null && !"".equals(PublicIDs)){
                    StringBuilder sucbuilder = new StringBuilder();
                    StringBuilder errbuilder = new StringBuilder();
                    String[] arr = PublicIDs.split(",");
                    for (String item : arr){
                        if (StringUtils.isEmpty(item)){
                            continue;
                        }else{
                            Map<String,Object> obj = new HashMap<String,Object>();
                            obj.put("PublicID", item);
                            //获取公共池客户的机会，线索以及状态， 并且将其置为无效
                            data = iBCustomerpublicpoolService.mGetPublicPoolDetail_Select(obj);
                            if (data != null && data.size() > 0){
                                if (data.get("IsDel") != null && "0".equals(data.get("IsDel")) ){
                                    String userId = (String) paramMap.get("UserID");
                                    String projectId = (String) paramMap.get("ProjectID");
                                    String mobile = (String) data.get("Mobile");
                                    String name = (String) data.get("Name");
                                    String channelTypeId = GetChannelTypeId(userId, projectId, "48FC928F-6EB5-4735-BF2B-29B1F591A582");
                                    ChannelRegisterModel channelRegisterModel = iBChannelService.newChannelRegisterModel(userId, channelTypeId, projectId);
                                    if (StringUtils.isEmpty(channelRegisterModel.getUserRule().getRuleID())){
                                        return Result.errormsg(21, "未找到该渠道的报备规则");
                                    }
                                    Map<String, Object> CustomerValidate = iBChannelService.ValidateForReport(mobile, projectId,channelRegisterModel);
                                    if ((int)CustomerValidate.get("InvalidType") != 0)
                                    {
                                        ErrCode = (int) CustomerValidate.get("InvalidType");
                                        ErrMsg = iBChannelService.GetMessageForReturn((int)CustomerValidate.get("InvalidType"), channelRegisterModel.getUserRule());
                                        //抢客失败，将线索机会返还之前的状态
                                        Map<String,Object> jParameter = new HashMap<String,Object>();
                                        jParameter.put("OpportunityID", data.get("OpportunityID"));
                                        jParameter.put("ClueID", data.get("ClueID"));
                                        jParameter.put("OpportunityStatus", data.get("OpportunityStatus"));
                                        jParameter.put("ClueStatus", data.get("ClueStatus"));
                                        mSetCustomerDetail_Update(jParameter);
                                        if (errbuilder.length() > 0){
                                            errbuilder.append("," + name);
                                            continue;
                                        }else{
                                            errbuilder.append(name);
                                            continue;
                                        }
                                    }
                                    Map<String,Object> param = new HashMap<String,Object>();
                                    param.put("ClueID",data.get("ClueID"));
                                    param.put("ProjectID", paramMap.get("ProjectID"));
                                    param.put("UserID", paramMap.get("UserID"));
                                    param.put("RoleID", paramMap.get("JobID"));
                                    param.put("PublicID", item);
                                    iBCustomerpublicpoolService.mGGCCustomerByZQDetail_Update(param);
                                    //符合报备规则
                                    if (sucbuilder.length() > 0){
                                        sucbuilder.append("," + data.get("Name"));
                                    }else{
                                        sucbuilder.append(data.get("Name"));
                                    }
                                }else{
                                    if (errbuilder.length() > 0){
                                        errbuilder.append("," + data.get("Name"));
                                    }else{
                                        errbuilder.append(data.get("Name"));
                                    }
                                }
                            }else{
                                continue;
                            }
                        }
                    }
                    if (sucbuilder.length() > 0){
                        ErrMsg += sucbuilder + "抢客成功;";
                    }
                    if (errbuilder.length() > 0){
                        ErrMsg += errbuilder + "抢客失败;";
                    }
                }
            }else if(paramMap.get("JobID") != null && "0269F35E-B32D-4D12-8496-4E6E4CE597B7".equals(paramMap.get("JobID"))){//置业顾问
                String Msg = iBCustomerpublicpoolService.mCustomerGGCList_Insert(paramMap);
                if ("0".equals(Msg)){
                    ErrMsg = "抢客成功";
                }else{
                    ErrMsg = Msg;
                }
            }else{
                ErrMsg = "当前身份不符，没有抢客权限";
            }
			return Result.ok(ErrMsg);
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	/**
	 * 抢客失败，将线索机会返还之前的状态
	 */
	private void mSetCustomerDetail_Update(Map<String, Object> jParameter) {
		//UPDATE dbo.B_Opportunity SET Status ={OpportunityStatus} WHERE ID = '{OpportunityID}'
		//		  UPDATE dbo.B_Clue SET Status ={ClueStatus} WHERE ID = '{ClueID}'
		BOpportunity unity = new BOpportunity();
		unity.setStatus((int)jParameter.get("OpportunityStatus"));
		unity.setId(jParameter.get("OpportunityID").toString());
		iBOpportunityService.updateById(unity);
		BClue clue = new BClue();
		clue.setStatus((int)jParameter.get("ClueStatus"));
		clue.setId(jParameter.get("ClueID").toString());
		iBClueService.updateById(clue);
	}
	/**
	 * 获取用户身份
	 */
	private String GetChannelTypeId(String userId, String projectId, String roleId) {
		Map<String,Object> channelTypeObj = new HashMap<String,Object>();
        channelTypeObj.put("MemberID", userId);
        channelTypeObj.put("ProjectID", projectId);
        channelTypeObj.put("RoleID", roleId);
        Map<String,Object> channelInfo = iBSalesgroupService.mShareChannelTypeID_Select(channelTypeObj);
        String channelTypeId = "";
        if (channelInfo != null && channelInfo.size() > 0){
            channelTypeId = (String) channelInfo.get("adviserGroupID");
        }
        return channelTypeId;
	}
	@ResponseBody
	@ApiOperation(value = "传播池抢客", notes = "传播池抢客")
	@RequestMapping(value = "/mCustomerFXCBList_Insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mCustomerFXCBList_Insert(@RequestBody JSONObject jsonParam) {
		JSONObject Parameter = (JSONObject)jsonParam.getJSONObject("_param");
		
		Result entity = new Result();
        StringBuilder errbuilder = new StringBuilder();
        StringBuilder successbuilder = new StringBuilder();
        String errmsg = "成功";
        try{
            if (StringUtils.isEmpty(Parameter.get("FXCBIDs"))){
                entity.setErrmsg("请输入分享传播池ID");
                entity.setErrcode(99);
                return entity;
            }
            CustomerHelp customerHelp = new CustomerHelp();
            List<Map<String,Object>> ArrCustomer = new ArrayList();
            ArrCustomer = iBCustomerpublicpoolService.mShareCustomerList_Select(Parameter);
            String userId = Parameter.get("UserID") == null ? "" : Parameter.get("UserID").toString();
            String adviserGroupID = Parameter.get("JobID") == null ? "" : Parameter.get("JobID").toString();
            String projectId = Parameter.get("ProjectID") == null ? "" : Parameter.get("ProjectID").toString();
            if (ArrCustomer.size() > 0){
                for (Map<String,Object> item : ArrCustomer){
                    Parameter.put("Mobile", item.get("Mobile"));
                    Parameter.put("NickName", item.get("NickName"));
                    Parameter.put("Gender", item.get("Gender"));
                    String mobile = item.get("Mobile") == null ? "" : item.get("Mobile").toString();
                    int Category = item.get("Category") == null ? 0 : (int) item.get("Category");
                    String ShareWXUserID = item.get("ShareWXUserID") == null ? "" : item.get("ShareWXUserID").toString();
                    String nickName = item.get("NickName") == null ? "" : item.get("NickName").toString();
                    String gender = item.get("Gender") == null ? "" : item.get("Gender").toString();
                    String SaleUserName = item.get("SaleUserName") == null ? "" : item.get("SaleUserName").toString();
                    if (adviserGroupID.equalsIgnoreCase("0269F35E-B32D-4D12-8496-4E6E4CE597B7")){
                        String sqlKey = "";
                        if (!StringUtils.isEmpty(mobile)){//验证手机号码
                        	JSONObject j_re = customerHelp.CustomerOpportunityExist(projectId, mobile);
                            if (j_re.getBoolean("status")){//存在老机会_老客户 
                                Map<String,Object> para1 = new HashMap<String,Object>();
                                para1.put("FXCBID", item.get("FXCBID"));
                                para1.put("Status", 3);
                                para1.put("UserID", userId);
                                iASharepoolService.mSharePoolDetail_Update(para1);
                                if (errbuilder.length() > 0){
                                    errbuilder.append("," + nickName);
                                    continue;
                                }else{
                                    errbuilder.append(nickName);
                                    continue;
                                }
                            }else{//新机会
                            	//验证是否存在线索
                                int IsClueExist = customerHelp.ClueExist(projectId, mobile);
                                boolean IsNoAllotRole = customerHelp.GetProjectIsNoAllotRole(projectId);
                                if (IsClueExist >= 0 && IsNoAllotRole){//如果开启分接 则不允许置业顾问操作渠道报备客户
                                	Map<String,Object> para1 = new HashMap<String,Object>();
                                	para1.put("FXCBID", item.get("FXCBID"));
                                    para1.put("Status", 3);
                                    para1.put("UserID", userId);
                                    iASharepoolService.mSharePoolDetail_Update(para1);
                                    errbuilder.append(nickName);
                                    continue;
                                }else if (IsClueExist == 1){//竞争带看,不允许置业顾问报备
                                	Map<String,Object> para1 = new HashMap<String,Object>();
                                	para1.put("FXCBID", item.get("FXCBID"));
                                    para1.put("Status", 3);
                                    para1.put("UserID", userId);
                                    iASharepoolService.mSharePoolDetail_Update(para1);
                                    errbuilder.append(nickName);
                                    continue;
                                }
                                j_re = customerHelp.CustomerExist(mobile);
                                if (!j_re.getBoolean("status")){//新机会_新客户
                                    Parameter.put("CustomerID", UUID.randomUUID().toString());
                                    sqlKey = "mFXCBNewCustomerGWDetail_Insert";
                                    Parameter.put("CustomerName", nickName);
                                    Parameter.put("FirstName", "");
                                    Parameter.put("LastName", nickName);
                                }else{//新机会_老客户
                                    Parameter.put("CustomerID", ((JSONObject)j_re.get("CustomerObj")).get("CustomerID"));
                                    sqlKey = "mFXCBOldCustomerGWDetail_Insert";
                                    Parameter.put("LastName", ((JSONObject)j_re.get("CustomerObj")).get("LastName"));
                                    Parameter.put("FirstName", ((JSONObject)j_re.get("CustomerObj")).get("FirstName"));
                                    Parameter.put("CustomerName", ((JSONObject)j_re.get("CustomerObj")).get("CustomerName"));
                                }
                            }
                            //保存数据
                            if (entity.getErrcode() == 0)
                            {
	                            Parameter.put("TrackType", "CCF6D2BF-8915-4EF9-B7CD-67979D00A0E3");//抢客
	                            Parameter.put("OpportunityID", UUID.randomUUID().toString());
	                            Parameter.put("Mobile", mobile);
	                            Parameter.put("SaleUserID", userId);
	                            Parameter.put("CustomerTag", "");
	                            Parameter.put("Status", 1);
	                            Parameter.put("IsCustomerFirstEdit", 0);
	                            Parameter.put("IntentProjectID", projectId);
	                            if (Category == 1){
	                                Parameter.put("CognitiveChannel", item.get("MediaLargeID"));
	                                Parameter.put("CognitiveChannelSub", item.get("MediaChildID"));
	                            }else{
	                                Parameter.put("CognitiveChannel", "");
	                                Parameter.put("CognitiveChannelSub", "");
	                            }
	                            Parameter.put("CustomerFirstEditTime", "");
	                            Parameter.put("OpportunitySource", "899CD8F1-7E1A-42B9-B4E9-6EAFB428EAEF");//分享传播
	                            String ClueID = ((JSONObject)j_re.get("CustomerObj")).get("ClueID") != null ? ((JSONObject)j_re.get("CustomerObj")).get("ClueID").toString() : "";
	                            Parameter.put("ClueID", ClueID);
	                            Parameter.put("Status", 1);
	                            Parameter.put("IsCustomerFirstEdit", 1);
	                            Parameter.put("CustomerFirstEditTime", DateTime.now().toString());
	                            iASharepoolService.CustomerGWDetail(sqlKey, Parameter);
	                            //添加线索
	                            customerHelp.ClueUpdate(Parameter);
	                            //增加意向项目
	                            customerHelp.IntentProjectAdd(Parameter);
	                            if (StringUtils.isEmpty(ClueID)){
	                            	JSONObject obj1 = new JSONObject();
	                                Map jobdata = iCWxuserService.mGetShareDetail_Select(Parameter);
	                                if (!StringUtils.isEmpty(jobdata.get("SaleUserID"))){
	                                    obj1.put("NewSaleUserName", jobdata.get("SaleUserName"));
	                                    obj1.put("FollwUpUserID", jobdata.get("SaleUserID"));
	                                    obj1.put("FollwUpUserRole", jobdata.get("JobID"));
	                                }else{
	                                    obj1.put("NewSaleUserName", "");
	                                    obj1.put("FollwUpUserID", "");
	                                    obj1.put("FollwUpUserRole", "");
	                                }
                                
	                                obj1.put("FollwUpType", ActionType.分享沉淀.toString());
	                                obj1.put("SalesType", 1);
	                                obj1.put("OldSaleUserName", "");
	                                obj1.put("FollwUpWay", "");
	                                obj1.put("FollowUpContent", "客户沉淀");
	                                obj1.put("IntentionLevel", "");
	                                obj1.put("OrgID", Parameter.get("OrgID"));
	                                obj1.put("OpportunityID", Parameter.get("OpportunityID"));
	                                obj1.put("ClueID", "");
	                                obj1.put("NextFollowUpDate", "");
	                                CustomerActionVo customerActionVo1 = JSONObject.parseObject(obj1.toJSONString(), CustomerActionVo.class);
	                                iVCustomergwlistSelectService.CustomerFollowUp_Insert(customerActionVo1);
	                                JSONObject obj2 = new JSONObject();
	                                obj2.put("FollwUpType", ActionType.顾问报备.toString());
	                                obj2.put("SalesType", 1);
	                                obj2.put("NewSaleUserName", "");
	                                obj2.put("OldSaleUserName", "");
	                                obj2.put("FollwUpUserID", Parameter.get("UserID"));
	                                obj2.put("FollwUpWay", "");
	                                obj2.put("FollowUpContent", "抢客-报备");
	                                obj2.put("IntentionLevel", "");
	                                obj2.put("OrgID", Parameter.get("OrgID"));
	                                obj2.put("FollwUpUserRole", Parameter.get("JobID"));
	                                obj2.put("OpportunityID", Parameter.get("OpportunityID"));
	                                obj2.put("ClueID", "");
	                                obj2.put("NextFollowUpDate", "");
	                                CustomerActionVo customerActionVo2 = JSONObject.parseObject(obj2.toJSONString(), CustomerActionVo.class);
	                                iVCustomergwlistSelectService.CustomerFollowUp_Insert(customerActionVo2);
	                            }
	                            //增加跟进记录
	                            JSONObject obj = new JSONObject();
	                            obj.put("FollwUpType", ActionType.分配顾问.toString());
	                            obj.put("SalesType", 1);
	                            obj.put("NewSaleUserName", SaleUserName);
	                            obj.put("OldSaleUserName", "");
	                            obj.put("FollwUpUserID", Parameter.get("UserID"));
	                            obj.put("FollwUpWay", "");
	                            obj.put("FollowUpContent", "抢客-登记");
	                            obj.put("IntentionLevel", Parameter.get("CustomerLevel"));
	                            obj.put("OrgID", Parameter.get("OrgID"));
	                            obj.put("FollwUpUserRole", Parameter.get("JobID"));
	                            obj.put("OpportunityID", Parameter.get("OpportunityID"));
	                            obj.put("ClueID", "");
	                            obj.put("NextFollowUpDate", Parameter.get("NextFollowUpDate"));
	                            CustomerActionVo customerActionVo = JSONObject.parseObject(obj.toJSONString(), CustomerActionVo.class);
	                            iVCustomergwlistSelectService.CustomerFollowUp_Insert(customerActionVo);
	                            //分配待跟进
	                            String UserID = userId;
	                            String ProjectID = (String) Parameter.get("ProjectID");
	                            String BizID = (String) Parameter.get("OpportunityID");
	                            String BizType = "Opportunity";
	                            String Subject = MessageType.分配待跟进.toString();
	                            String Content = "通过分享传播池抢客所得";
	                            String Receiver = (String) Parameter.get("UserID");
	                            iSystemMessageService.Detail_Insert(UserID, ProjectID, BizID, BizType, Subject, Content, Receiver, MessageType.分配待跟进.getTypeID(), true);
	                            //同步明源客户数据
	                            customerHelp.SyncCustomer(Parameter.get("OpportunityID").toString(), 0);
	                            if (successbuilder.length() > 0){
	                                successbuilder.append("," + nickName);
	                            }else{
	                                successbuilder.append(nickName);
	                            }
	                            //修改分享传播池信息  置为无效
	                            Map<String,Object> para = new HashMap<String,Object>();
	                            para.put("FXCBID", item.get("FXCBID"));
	                            para.put("Status", 2);
	                            para.put("UserID", userId);
	                            iASharepoolService.mSharePoolDetail_Update(para);
                            }else{
                                entity.setErrcode(1);
                                entity.setErrmsg("失败");
                            }
                        }else{
                            entity.setErrcode(1);
                            entity.setErrmsg("手机号码必填！");
                        }
                    }else if (adviserGroupID.equalsIgnoreCase("48FC928F-6EB5-4735-BF2B-29B1F591A582")){   //自渠
                        String channelTypeId = GetChannelTypeId(userId, projectId, "48FC928F-6EB5-4735-BF2B-29B1F591A582");
                        ChannelRegisterModel channelRegisterModel = iBChannelService.newChannelRegisterModel(userId, channelTypeId, projectId);
                        if (StringUtils.isEmpty(channelRegisterModel.getUserRule().getRuleID())){
                            entity.setErrcode(21);
                            entity.setErrmsg("未找到该渠道的报备规则");
                            return entity;
                        }
                        Map<String,Object> CustomerValidate = iBChannelService.ValidateForReport(mobile, projectId,channelRegisterModel);
                        if ((int)CustomerValidate.get("InvalidType") != 0){
                            //修改分享传播池内信息，并返回APP抢客失败信息
                            entity.setErrcode((int)CustomerValidate.get("InvalidType"));
                            entity.setErrmsg(iBChannelService.GetMessageForReturn((int)CustomerValidate.get("InvalidType"), channelRegisterModel.getUserRule()));
                            //修改分享传播池信息  置为无效
                            Map<String,Object> para1 = new HashMap<String,Object>();
                            para1.put("FXCBID", item.get("FXCBID"));
                            para1.put("Status", 3);
                            para1.put("UserID", userId);
                            iASharepoolService.mSharePoolDetail_Update(para1);
                            if (errbuilder.length() > 0){
                                errbuilder.append("," + nickName);
                                continue;
                            }else{
                                errbuilder.append(nickName);
                                continue;
                            }
                        }
                        Map<String,Object> jParameter = new HashMap<String,Object>();
                        jParameter.put("Mobile", mobile);
                        jParameter.put("Name", nickName);
                        jParameter.put("Gender", gender);
                        Map<String,Object> data = iASharepoolService.CustomerPotential_Update(jParameter);
                        if (data != null && data.size() > 0){
                            Parameter.put("CustomerID", data.get("CustomerPotentialID"));
                        }else{
                            Parameter.put("CustomerID", UUID.randomUUID().toString());
                        }
                        Parameter.put("AdviserGroupID", adviserGroupID);
                        if (Category == 1){
                            Parameter.put("CognitiveChannel", item.get("MediaLargeID"));
                            Parameter.put("CognitiveChannelSub", item.get("MediaChildID"));
                        }else{
                            Parameter.put("CognitiveChannel", "");
                            Parameter.put("CognitiveChannelSub", "");
                        }
                        Parameter.put("ChannelUserID", "");
                        Parameter.put("ChannelTaskID", "");
                        Parameter.put("LastName", nickName);
                        Parameter.put("CardID", "");
                        Parameter.put("CardType", "");
                        Parameter.put("AcceptFactor", "");
                        Parameter.put("FirstName", "");
                        Parameter.put("SourceType", "899CD8F1-7E1A-42B9-B4E9-6EAFB428EAEF");//分享传播
                        Parameter.put("Remark", "");//分享传播
                        Parameter.put("IntentProjectID", projectId);
                        Parameter.put("CustomerTag", "");
                        Parameter.put("ClueID", UUID.randomUUID().toString());
                        Parameter.put("RuleID", channelRegisterModel.getUserRule().getRuleID());
                        Parameter.put("InvalidType", CustomerValidate.get("InvalidType"));
                        Parameter.put("InvalidReason", CustomerValidate.get("Message"));
                        Parameter.put("InvalidTime", (boolean)CustomerValidate.get("Tag") == true ? "" : DateTime.now().toString());
                        Parameter.put("ComeOverdueTime", channelRegisterModel.getUserRule().getComeOverdueTime());
                        Parameter.put("TradeOverdueTime", channelRegisterModel.getUserRule().getTradeOverdueTime());
                        Parameter.put("IsSelect", channelRegisterModel.getUserRule().getProtectRule().getIsSelect());
                        Parameter.put("ConfirmUserId", "99");
                        Parameter.put("OppID", CustomerValidate.get("OppID"));
                        mCustomerPotentialZQDetail_Insert(Parameter);
                        JSONObject obj1 = new JSONObject();
                        Map<String,Object> jobdata = iCWxuserService.mGetShareDetail_Select(Parameter);
                        if (jobdata.get("SaleUserID") != null && jobdata.get("SaleUserID").toString() != "")
                        {
                            obj1.put("NewSaleUserName", jobdata.get("SaleUserName"));
                            obj1.put("FollwUpUserID", jobdata.get("SaleUserID"));
                            obj1.put("FollwUpUserRole", jobdata.get("JobID"));
                        }
                        else
                        {
                            obj1.put("NewSaleUserName", "");
                            obj1.put("FollwUpUserID", "");
                            obj1.put("FollwUpUserRole", "");
                        }
                        obj1.put("FollwUpType", ActionType.分享沉淀.toString());
                        obj1.put("SalesType", 1);
                        obj1.put("OldSaleUserName", "");
                        obj1.put("FollwUpWay", "");
                        obj1.put("FollowUpContent", "客户沉淀");
                        obj1.put("IntentionLevel", "");
                        obj1.put("OrgID", Parameter.get("OrgID"));
                        //obj1.Add("FollwUpUserRole", ConvertHelper.ToString(Parameter["JobID"]));
                        obj1.put("OpportunityID", "");
                        obj1.put("ClueID", Parameter.get("ClueID"));
                        obj1.put("NextFollowUpDate", "");
                        CustomerActionVo customerActionVo1 = JSONObject.parseObject(obj1.toJSONString(), CustomerActionVo.class);
                        iVCustomergwlistSelectService.CustomerFollowUp_Insert(customerActionVo1);
                        JSONObject obj2 = new JSONObject();
                        obj2.put("FollwUpType", ActionType.顾问报备.toString());
                        obj2.put("SalesType", 1);
                        obj2.put("NewSaleUserName", "");
                        obj2.put("OldSaleUserName", "");
                        obj2.put("FollwUpUserID", userId);
                        obj2.put("FollwUpWay", "");
                        obj2.put("FollowUpContent", "抢客-报备");
                        obj2.put("IntentionLevel", "");
                        obj2.put("OrgID", Parameter.get("OrgID"));
                        obj2.put("FollwUpUserRole",Parameter.get("JobID"));
                        obj2.put("OpportunityID", "");
                        obj2.put("ClueID", Parameter.get("ClueID"));
                        obj2.put("NextFollowUpDate", "");
                        CustomerActionVo customerActionVo2 = JSONObject.parseObject(obj2.toJSONString(), CustomerActionVo.class);
                        iVCustomergwlistSelectService.CustomerFollowUp_Insert(customerActionVo2);
                        //修改分享传播池信息  置为无效
                        if (successbuilder.length() > 0){
                            successbuilder.append("," + nickName);
                        }else{
                            successbuilder.append(nickName);
                        }
                        Map<String,Object> para = new HashMap<String,Object>();
                        para.put("FXCBID", item.get("FXCBID"));
                        para.put("Status", 2);
                        para.put("UserID", userId);
                        iASharepoolService.mSharePoolDetail_Update(para);
                    }
                }
            }else{
                entity.setErrmsg("客户不符合规则");
                entity.setErrcode(99);
            }
        }catch (Exception ex){
            entity.setErrmsg(ex.getMessage());
            entity.setErrcode(99);
        }
        if (successbuilder.length() > 0)
        {
            entity.setErrmsg(entity.getErrmsg() + successbuilder + "抢客成功;");
        }
        if (errbuilder.length() > 0)
        {
            entity.setErrmsg(entity.getErrmsg() + errbuilder + "抢客失败;");
        }
        return entity;
	}
	private void mCustomerPotentialZQDetail_Insert(JSONObject parameter) {
		Map<String,Object> pmap = JSONObject.parseObject(parameter.toJSONString(), Map.class);
        pmap.put("Name", parameter.getString("LastName")+parameter.getString("FirstName"));
		List<Map<String,Object>> valid_1_map = bCustomerpotentialMapper.mCustomerPotentialZQDetail_Insert_valid_1(pmap);
        if(valid_1_map!=null && valid_1_map.size()>0){
        	bCustomerpotentialMapper.mCustomerPotentialZQDetail_Insert_step2(pmap);
        }else{
        	bCustomerpotentialMapper.mCustomerPotentialZQDetail_Insert_step1(pmap);
        }
        List<Map<String,Object>> valid_2_map = bCustomerpotentialMapper.mCustomerPotentialZQDetail_Insert_valid_2(pmap);
        if(valid_2_map!=null && valid_2_map.size()>0){
        	bCustomerpotentialMapper.mCustomerPotentialZQDetail_Insert_step4(pmap);
        }else{
        	bCustomerpotentialMapper.mCustomerPotentialZQDetail_Insert_step3(pmap);
        }
        bCustomerpotentialMapper.mCustomerPotentialZQDetail_Insert_step5(pmap);
        bCustomerpotentialMapper.mCustomerPotentialZQDetail_Insert_step6(pmap);
        bCustomerpotentialMapper.mCustomerPotentialZQDetail_Insert_step7(pmap);
        if(StringUtils.isEmpty(parameter.getString("OppID"))){
        	bCustomerpotentialMapper.mCustomerPotentialZQDetail_Insert_step8(pmap);
        }
        pmap.put("CustomerRank", "41FA0234-F8AE-434F-8BCD-6E9BE1D059DA");
    	pmap.put("UpDownStatus", 1);
    	bCustomerpotentialMapper.P_ClueCustomerRank(pmap);
	}
	@ResponseBody
	@ApiOperation(value = "盘客列表", notes = "案场销售经理盘客销售顾问查询")
	@RequestMapping(value = "/mCustomerXSJLSalesUserList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mCustomerXSJLSalesUserList_Select(@RequestBody JSONObject jsonParam) {
		try{
			Map paramMap = (HashMap)jsonParam.get("_param");
			String Sort = (String) paramMap.get("Sort");
			String Filter = (String) paramMap.get("Filter");
			String OrgID = (String) paramMap.get("OrgID");
			String ProjectID = (String) paramMap.get("ProjectID");
			int PageIndex = (int)paramMap.get("PageIndex");//页面索引
			int PageSize = (int)paramMap.get("PageSize");//每页数量
			StringBuilder whereSb = new StringBuilder();
            StringBuilder OrderSb = new StringBuilder();
            if (!StringUtils.isEmpty(Filter)){
                if ("82F40CAC-AA13-9930-F056-D343E9B3363D".equals(Filter)){//  <=10% 资料完整度
                    whereSb.append(" and IntegrityRungs <=10");
                }
                if ("934AD524-2A4A-45F8-CEAE-16BEE872C5B1".equals(Filter)){
                    whereSb.append(" and IntegrityRungs <=20");
                }
                if ("D19C87C3-4AAC-A0C3-84F4-B5725549FA68".equals(Filter)){
                    whereSb.append(" and IntegrityRungs <=30");
                }
                if ("845CC1B4-8560-3419-EF2F-3A3F7F6579AC".equals(Filter)){
                    whereSb.append(" and IntegrityRungs <=40");
                }
                if ("B1E47E14-8154-D9B2-6E01-A7378CAB2073".equals(Filter)){
                    whereSb.append(" and IntegrityRungs <=60");
                }
                if ("E08D654A-CCBC-8828-B4F6-DC74E7302841".equals(Filter)){
                    whereSb.append(" and IntegrityRungs <=70");
                }
                if ("329E9467-FB43-096B-ABF2-242CA939CA31".equals(Filter)){
                    whereSb.append(" and IntegrityRungs >70");
                }
            }
            if (!StringUtils.isEmpty(Sort)){
                if ("D0744FA8-7597-0632-4314-72E08B35C48C".equals(Sort)){//累计客户最多
                    whereSb.append(" ORDER BY  TotalCount desc");
                }
                if ("A9451ED9-71C0-774C-1C68-FC51E2E69311".equals(Sort)){//累计客户最少
                    whereSb.append(" ORDER BY  TotalCount asc ");
                }
                if ("BD616A5B-3F6E-A44E-1776-018EA33F35DE".equals(Sort)){//未成交客户最多
                    whereSb.append(" ORDER BY  UnsettledCount desc");
                }
                if ("57E86DCA-72E1-DF73-DEFD-DD08C33BFE8B".equals(Sort)){//未成交客户最少
                    whereSb.append(" ORDER BY  UnsettledCount asc ");
                }
                if ("BD5C9F06-D039-C03D-512D-5CC3045900F5".equals(Sort)){//即将逾期最多
                    whereSb.append(" ORDER BY  OverdueCount Desc ");
                }
                if ("0145A667-A4E7-8B55-CC68-59460941FFD5".equals(Sort)) {//即将逾期最少
                    whereSb.append(" ORDER BY  OverdueCount asc ");
                }
                if ("732BF8BC-5B41-DF50-F00F-D22F292E360F".equals(Sort)) {//即将回收最多
                    whereSb.append(" ORDER BY  RecycleCount Desc ");
                }
                if ("CA46A147-EF3D-D122-13FD-75817FF9585D".equals(Sort)){//即将回收最少
                    whereSb.append(" ORDER BY  RecycleCount asc ");
                }
            }else{
                whereSb.append(" ORDER BY  CreateTime desc");
            }
            IPage page = new Page(PageIndex,PageSize);
            IPage<Map<String,Object>> result = 
            		iVCustomerxsjlsalesuserlistSelectService.mCustomerXSJLSalesUserList_Select(page,OrgID,ProjectID,whereSb.toString(),OrderSb.toString(),SiteUrl);
            Map<String, Object> map = new HashMap<String,Object>();
			map.put("List", result.getRecords());
			map.put("AllCount", result.getTotal());
			map.put("PageSize", result.getSize());
            
            return Result.ok(map);
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ResponseBody
	@ApiOperation(value = "案场销售经理盘客客户查询", notes = "案场销售经理盘客销售顾问客户查询")
	@RequestMapping(value = "/mCustomerXSJLSalesUserCustomerList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mCustomerXSJLSalesUserCustomerList_Select(@RequestBody JSONObject jsonParam) {
		try{
			Map paramMap = (HashMap)jsonParam.get("_param");
			String Sort = (String) paramMap.get("Sort");
			String Filter = (String) paramMap.get("Filter");
			String SaleUserID = (String) paramMap.get("SaleUserID");
			String ProjectID = (String) paramMap.get("ProjectID");
			int PageIndex = (int)paramMap.get("PageIndex");//页面索引
			int PageSize = (int)paramMap.get("PageSize");//每页数量
			StringBuilder whereSb = new StringBuilder();
            StringBuilder OrderSb = new StringBuilder();
            if (!StringUtils.isEmpty(Filter)){
                if ("9114DF84-58E7-BD3A-B63A-5807821EA272".equals(Filter)){//  "最近跟进>=30天
                    whereSb.append("and  IntervalDay >=30");
                }
                if ("E6A0BEFC-FEF9-7FFF-E495-AF01391AF341".equals(Filter)){//  最近跟进>=60天
                    whereSb.append("and  IntervalDay >=30");
                }
                if ("12070050-1CEB-A085-51EB-B732B5E0717D".equals(Filter)){//  最近跟进>=90天
                    whereSb.append("and  IntervalDay >=90");
                }
                if ("2AB369D1-99B6-60FD-4998-A7A36242FF48".equals(Filter)){//  即将回收<=1天
                    whereSb.append("and  RecycleDay <=1");
                }
                if ("2464F001-B98A-A232-E627-45E6556A4E6A".equals(Filter)){//  即将回收<=3天
                    whereSb.append("and  RecycleDay <=3");
                }
                if ("B76DCF24-7A87-EF66-C176-645844E5EFC8".equals(Filter)){//  即将回收<=5天
                    whereSb.append("and  RecycleDay <=5");
                }
            }
            if (!StringUtils.isEmpty(Sort)){
                if ("2C92B9B1-4628-C62F-4277-94F40B9D7510".equals(Sort)){//最近创建
                    whereSb.append(" ORDER BY  CreateTime desc");
                }
                if ("E0B27CA7-B76C-0643-63B6-EA6F27BD7578".equals(Sort)){//最远创建
                    whereSb.append(" ORDER BY  CreateTime asc ");
                }
                if ("7F903589-EB0A-94C5-8E3D-E44178871EB7".equals(Sort)){//最近跟进
                    whereSb.append(" ORDER BY  IntervalDay asc, FollowState desc");
                }
                if ("05D73D87-4657-F4DC-3E27-AE47D005C7EC".equals(Sort)){//最远跟进
                    whereSb.append(" ORDER BY  IntervalDay desc , FollowState asc  ");
                }
            }else{
                whereSb.append(" ORDER BY  CreateTime desc");
            }

            IPage page = new Page(PageIndex,PageSize);
			IPage<Map<String,Object>> result = 
					iVCustomerxsjlsalesuserlistSelectService.mCustomerXSJLSalesUserCustomerList_Select(page,SaleUserID,ProjectID,whereSb.toString(),OrderSb.toString());
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("List", result.getRecords());
			map.put("AllCount", result.getTotal());
			map.put("PageSize", result.getSize());
			return Result.ok(map);
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ResponseBody
	@ApiOperation(value = "案场销售经理客户丢失审批", notes = "案场营销经理客户丢失审批")
	@RequestMapping(value = "/mCustomerYXJLLoseDetail_Update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mCustomerYXJLLoseDetail_Update(@RequestBody JSONObject jsonParam) {
		try{
			Map paramMap = (HashMap)jsonParam.get("_param");
			String approveState = (String) paramMap.get("ApproveState");
            if ("1".equals(approveState)){//驳回申请
                iBOpportunitygiveupService.mCustomerYXJLLoseDetail_Rejected(paramMap);
            }
            if ("2".equals(approveState)){//通过申请
                iBOpportunitygiveupService.mCustomerYXJLLoseDetail_Pass(paramMap);
                //丢失审核后协作人置空
                iBOpportunitygiveupService.mCustomerYXJLSalePartnerSetNull_Update(paramMap);
                //同步明源客户数据
                iCustomerHelp.SyncCustomer(paramMap.get("OpportunityID").toString(), 0);
            }
			return Result.ok(new ArrayList());
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
}
