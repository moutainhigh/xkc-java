package com.tahoecn.xkc.controller.app;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.service.ipad.IIpadService;

@RestController
@Api(tags = "ipad-业务接口", value = "ipad-业务接口")
@RequestMapping("/ipad/business")
public class IPadController extends TahoeBaseController{
	
	@Resource
	private IIpadService iIpadService;
	
	@ResponseBody
    @ApiOperation(value = "项目列表", notes = "项目列表")
    @RequestMapping(value = "/mLFProjectList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mLFProjectList_Select(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iIpadService.mLFProjectList_Select(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "切换项目", notes = "切换项目")
    @RequestMapping(value = "/mLFUSerProjectChange_Update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mLFUSerProjectChange_Update(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iIpadService.UserProjectChange_Update(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "置业顾问列表", notes = "置业顾问列表")
    @RequestMapping(value = "/mLFCustomerGWList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mLFCustomerGWList_Select(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iIpadService.mLFCustomerGWList_Select(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "置业顾问列表排序", notes = "置业顾问列表排序")
    @RequestMapping(value = "/mLFCustomerGWList_Select_Sort", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mLFCustomerGWList_Select_Sort(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iIpadService.mLFCustomerGWList_Select_Sort(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "客户详情", notes = "客户详情")
    @RequestMapping(value = "/mLFCustomerDetailByMobile_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mLFCustomerDetailByMobile_Select(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iIpadService.mLFCustomerDetailByMobile_Select(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "客户登记(报备)", notes = "客户登记(报备)")
    @RequestMapping(value = "/mLFCustomerDetail_Insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mLFCustomerDetail_Insert(@RequestBody JSONObject paramAry) {
		JSONArray JA = paramAry.getJSONArray("_param");
		Result re = new Result();
		try {
			for(int i=0;i<JA.size();i++){
				JSONObject param = JA.getJSONObject(i);
				re = iIpadService.mLFCustomerDetail_Insert(param);
				//更新置业过问列表顺序
				iIpadService.updateSortCodeAndTime(param.getString("UserID"),param.getString("SalesUserID"));
			}
			re.setErrcode(0);
			re.setErrmsg("登记成功！");
		} catch (Exception e) {
			re.setErrcode(1);
			re.setErrmsg("系统异常");
			e.printStackTrace();
		}
		return re;
    }
	
	@ResponseBody
    @ApiOperation(value = "待分配列表", notes = "待分配列表")
    @RequestMapping(value = "/mLFCustomerNeedFPList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mLFCustomerNeedFPList_Select(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iIpadService.mLFCustomerNeedFPList_Select(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "接待记录列表", notes = "接待记录列表")
    @RequestMapping(value = "/mLFReceptRecordList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mLFReceptRecordList_Select(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iIpadService.mLFReceptRecordList_Select(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "接待记录客户列表（客户列表）", notes = "接待记录客户列表（客户列表）")
    @RequestMapping(value = "/mLFReceptRecordCustomerList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mLFReceptRecordCustomerList_Select(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iIpadService.mLFReceptRecordCustomerList_Select(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "接待记录置业顾问列表", notes = "接待记录置业顾问列表")
    @RequestMapping(value = "/mLFReceptRecordList_Select_forSaleUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mLFReceptRecordList_Select_forSaleUser(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iIpadService.mLFReceptRecordList_Select_forSaleUser(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "置业顾问签到", notes = "置业顾问签到")
    @RequestMapping(value = "/addSaleUserSign", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result addSaleUserSign(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iIpadService.addSaleUserSign(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "置业顾问列表自定义排序", notes = "置业顾问列表自定义排序")
    @RequestMapping(value = "/sortSaleUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result sortSaleUser(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		String saleUserIDs = json.getString("SaleUserIDs");
		String userID = json.getString("UserID");
		return iIpadService.sortSaleUser(saleUserIDs,userID);
    }
	
	@ResponseBody
    @ApiOperation(value = "设置置业顾问状态", notes = "置业顾问列表自定义排序")
    @RequestMapping(value = "/SetSaleUserStatus", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result SetSaleUserStatus(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iIpadService.SetSaleUserStatus(json);
    }
	
}
