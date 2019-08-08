package com.tahoecn.xkc.controller.app;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.mapper.opportunity.BOpportunityMapper;
import com.tahoecn.xkc.mapper.project.BProjectMapper;
import com.tahoecn.xkc.mapper.project.BRoomMapper;
import com.tahoecn.xkc.mapper.sys.SAccountMapper;
import com.tahoecn.xkc.model.opportunity.BOpportunity;
import com.tahoecn.xkc.model.project.BProject;
import com.tahoecn.xkc.model.project.BRoom;
import com.tahoecn.xkc.model.sys.SAccount;
import com.tahoecn.xkc.service.house.IBHouseplanService;
import com.tahoecn.xkc.service.project.IAPropertyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "APP-置业计划接口", value = "APP-置业计划接口")
@RestController
@RequestMapping("/app/aProperty")
public class AppAPropertyController extends TahoeBaseController {
	
	@Autowired
	private IAPropertyService iAPropertyService;
	@Autowired
	private BOpportunityMapper bOpportunityMapper;
	@Autowired
	private BRoomMapper bRoomMapper;
	@Autowired
	private IBHouseplanService iBHouseplanService;
	@Autowired
	private BProjectMapper bProjectMapper;
	@Autowired
	private SAccountMapper sAccountMapper;
	
	@ApiOperation(value = "查询折扣", notes = "查询折扣")
	@RequestMapping(value = "/mPropertyDiscountList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mPropertyDiscountList_Select(@RequestBody JSONObject jsonParam) {
		try {
			JSONObject param = jsonParam.getJSONObject("_param");
			int PageSize = param.getIntValue("PageSize");
			int PageIndex = param.getIntValue("PageIndex");
            if (!StringUtils.isEmpty(param.getString("SaleUserID"))){
            	param.put("Parameter", " and SaleUserID ='" + param.getString("SaleUserID") + "'");
            }else{
            	param.put("Parameter", "");
            }
            IPage<Map<String,Object>> page = new Page<Map<String,Object>>(PageIndex, PageSize);
            Map<String,Object> map = iAPropertyService.Discount_List_Select(page, param);
            return Result.ok(map);
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ApiOperation(value = "添加折扣", notes = "添加折扣")
	@RequestMapping(value = "/mPropertyDiscountDetail_Insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mPropertyDiscountDetail_Insert(@RequestBody JSONObject jsonParam) {
		try {
			JSONObject param = jsonParam.getJSONObject("_param");
			return Result.ok(iAPropertyService.Discount_Detail_Add(param));
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ApiOperation(value = "删除折扣", notes = "删除折扣")
	@RequestMapping(value = "/mPropertyDiscountDetail_Delete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mPropertyDiscountDetail_Delete(@RequestBody JSONObject jsonParam) {
		try {
			JSONObject param = jsonParam.getJSONObject("_param");
			iAPropertyService.Discount_Detail_DeleteById(param);
			return Result.ok("删除成功");
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}

	@ApiOperation(value = "查询房间信息", notes = "查询房间信息")
	@RequestMapping(value = "/mHouseOpportunity_Detail_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mHouseOpportunity_Detail_Select(@RequestBody JSONObject jsonParam) {
		try {
			JSONObject param = jsonParam.getJSONObject("_param");
			String CustomerMobile = param.getString("CustomerMobile");
//			select top (1)  *from B_Opportunity where CustomerMobile='{CustomerMobile}'
            QueryWrapper<BOpportunity> wrapper = new QueryWrapper<BOpportunity>();
            wrapper.eq("CustomerMobile", CustomerMobile);
            List<BOpportunity> bo = bOpportunityMapper.selectList(wrapper);
			return Result.ok(bo.get(0));
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ApiOperation(value = "置业计划-房间信息", notes = "置业计划详情")
	@RequestMapping(value = "/mPropertyPlanRoomDetail_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mPropertyPlanRoomDetail_Select(@RequestBody JSONObject jsonParam) {
		try {
			JSONObject param = jsonParam.getJSONObject("_param");
			String RoomID = param.getString("RoomID");
//			select top (1) *From B_Room where id='{ID}'
			QueryWrapper<BRoom> wrapper = new QueryWrapper<BRoom>();//根据id获取房间信息
			wrapper.eq("ID", RoomID);
			List<BRoom> bo = bRoomMapper.selectList(wrapper);
			return Result.ok(bo.get(0));
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ApiOperation(value = "置业计划新增", notes = "置业计划新增")
	@RequestMapping(value = "/mPropertyPlanDetail_Insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mPropertyPlanDetail_Insert(@RequestBody JSONObject jsonParam) {
		try {
			JSONObject param = jsonParam.getJSONObject("_param");
			return Result.ok(iBHouseplanService.HousePlan_Detail_Add(param));
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ApiOperation(value = "置业计划详情", notes = "置业计划详情")
	@RequestMapping(value = "/mPropertyPlanDetail_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mPropertyPlanDetail_Select(@RequestBody JSONObject jsonParam) {
		try {
			JSONObject param = jsonParam.getJSONObject("_param");
            if (!StringUtils.isEmpty(param.getString("ID"))){
            	param.put("Parameter", " and A.ID ='" + param.getString("ID") + "'");
            }else{
            	param.put("Parameter", "");
            }
            return Result.ok(iBHouseplanService.HousePlan_Detail_Find(param));
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ApiOperation(value = "产讯项目信息", notes = "产讯项目信息")
	@RequestMapping(value = "/mHousePorject_Detail_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mHousePorject_Detail_Select(@RequestBody JSONObject jsonParam) {
		try {
			JSONObject param = jsonParam.getJSONObject("_param");
			String ID = param.getString("ID");
			//select * from  B_Project where ID='{ID}'
			QueryWrapper<BProject> wrapper = new QueryWrapper<BProject>();//获取项目信息
			wrapper.eq("ID", ID);
			BProject bp = bProjectMapper.selectOne(wrapper);
			return Result.ok(bp);
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ApiOperation(value = "查询置业顾问信息", notes = "查询置业顾问信息")
	@RequestMapping(value = "/mHouseAccount_Detail_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mHouseAccount_Detail_Select(@RequestBody JSONObject jsonParam) {
		try {
			JSONObject param = jsonParam.getJSONObject("_param");
			String ID = param.getString("ID");
//			select top (1)  *from S_Account where ID='{ID}'
			QueryWrapper<SAccount> wrapper = new QueryWrapper<SAccount>();//获取项目信息
			wrapper.eq("ID", ID);
			SAccount sa = sAccountMapper.selectOne(wrapper);
			return Result.ok(sa);
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ApiOperation(value = "项目简介", notes = "项目简介")
	@RequestMapping(value = "/mWeiBookMicroBuildingBookDetail_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mWeiBookMicroBuildingBookDetail_Select(@RequestBody JSONObject jsonParam) {
		try {
			JSONObject param = jsonParam.getJSONObject("_param");
			param.put("Parameter", " and ProjectID ='" + param.get("ProjectID") + "'");
			return Result.ok(iBHouseplanService.MicroBuildingBook_Detail_Find(param));
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
}
