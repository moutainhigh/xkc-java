package com.tahoecn.xkc.controller.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tahoecn.xkc.common.enums.CustomerModeType;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.customer.BCustomerpotentialfiltergroup;
import com.tahoecn.xkc.model.customer.VOpportunity;
import com.tahoecn.xkc.model.vo.CSearchModel;
import com.tahoecn.xkc.model.vo.CustomerModel;
import com.tahoecn.xkc.service.customer.IBCustomerpotentialfiltergroupService;
import com.tahoecn.xkc.service.customer.IVOpportunityService;

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
}
