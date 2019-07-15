package com.tahoecn.xkc.controller.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tahoecn.xkc.common.enums.CustomerModeType;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.vo.CSearchModel;
import com.tahoecn.xkc.model.vo.CustomerModel;
import com.tahoecn.xkc.service.customer.IBCustomerfiltergroupService;
import com.tahoecn.xkc.service.customer.IBCustomerpotentialfiltergroupService;
import com.tahoecn.xkc.service.customer.IBOpportunitygiveupService;
import com.tahoecn.xkc.service.customer.IVOpportunityService;
import com.tahoecn.xkc.service.opportunity.IBOpportunityService;

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
	
    @Autowired
    private IVOpportunityService iVOpportunityService;
    @Autowired
    private IBCustomerpotentialfiltergroupService iBCustomerpotentialfiltergroupService;
    @Autowired
    private IBCustomerfiltergroupService iBCustomerfiltergroupService;
    @Autowired
    private IBOpportunityService iBOpportunityService;
    @Autowired
    private IBOpportunitygiveupService iBOpportunitygiveupService;

	@ResponseBody
    @ApiOperation(value = "案场销售经理客户丢失审批详细", notes = "案场销售经理客户丢失审批详细")
    @RequestMapping(value = "/mCustomerYXJLLoseDetail_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mCustomerYXJLLoseDetail_Select(@RequestBody JSONObject jsonParam) {
    	try{
            Map paramMap = (HashMap)jsonParam.get("_param");
    		
    		CSearchModel model = JSON.parseObject(JSON.toJSONString(paramMap), CSearchModel.class);
            CustomerModel customerModel = new CustomerModel();
            List<Map<String,Object>> CustomerObj = iVOpportunityService.OpportunityInfo(model.getOpportunityID());
            if (CustomerObj != null  && CustomerObj.size() > 0){
                String customerModeType = CustomerModeType.顾问_客户_详情.getTypeID();
                customerModel = iVOpportunityService.InitCustomerModeData(model, "GWGiveUpCustomer.json", CustomerObj.get(0), customerModeType);
                return Result.ok(JSONObject.toJSON(customerModel));
            }else{
            	return Result.errormsg(11,"不存在客户信息！");
            }
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
			Map paramMap = (HashMap)jsonParam.get("_param");
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
			return Result.ok(iBCustomerpotentialfiltergroupService.mCustomerPotentialFilterGroupList_Select(paramMap));
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
			Map paramMap = (HashMap)jsonParam.get("_param");
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
			return Result.ok(iBCustomerfiltergroupService.mCustomerFilterGroupList_Select(paramMap));
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
			return Result.ok("成功");
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
			Map paramMap = (HashMap)jsonParam.get("_param");
			Map<String,Object> map = iBOpportunityService.mCustomerYQWJKList_Select(paramMap);
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
			Map paramMap = (HashMap)jsonParam.get("_param");
			Map<String,Object> map = iBOpportunityService.mCustomerYQWQYList_Select(paramMap);
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
			if (StringUtils.isEmpty(paramMap.get("RequestType")) && "YQWQY".equals(paramMap.get("Code"))){
				result = iBOpportunityService.R_ACYQWQYList_Select(paramMap);
			}else if(StringUtils.isEmpty(paramMap.get("RequestType")) && "YQWJK".equals(paramMap.get("Code"))){
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
}
