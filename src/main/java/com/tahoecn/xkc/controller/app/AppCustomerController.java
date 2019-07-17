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
import com.tahoecn.xkc.service.customer.IVCustomergwlistSelectService;
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
	
	@Value("${SiteUrl}")
    private String SiteUrl;
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
    @Autowired
    private IVCustomergwlistSelectService iVCustomergwlistSelectService;

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
}
