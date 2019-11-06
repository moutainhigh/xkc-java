package com.tahoecn.xkc.controller.webapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.dict.SDictionary;
import com.tahoecn.xkc.model.salegroup.BSalesgroup;
import com.tahoecn.xkc.service.dict.ISDictionaryService;
import com.tahoecn.xkc.service.salegroup.IBSalesgroupService;
import com.tahoecn.xkc.service.salegroup.IBSalesgroupmemberService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author YYY
 * @since 2019-06-25
 */
@RestController
@RequestMapping("/webapi/saleGroupMember")
public class BSaleGroupMemberController extends TahoeBaseController {

	@Autowired
	private IBSalesgroupmemberService iSaleGroupMemberService;

	@Autowired
	private ISDictionaryService iSDictionaryService;

	@Autowired
	private IBSalesgroupService iBSalesgroupService;

	@ApiImplicitParams({
			@ApiImplicitParam(name = "ProjectID", value = "ProjectID", required = true, dataType = "String") })
	@ApiOperation(value = "人员管理", notes = "人员管理")
	@RequestMapping(value = "/SalesGroupMemberList_Select", method = { RequestMethod.GET })
	public Result SalesGroupMemberList_Select(String ProjectID) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ProjectID", ProjectID);
		List<Map<String, Object>> list = iSaleGroupMemberService.SalesGroupMemberList_Select(map);
		result.put("List", list);
		QueryWrapper<SDictionary> dictWrapper = new QueryWrapper<>();

		QueryWrapper<BSalesgroup> salesgroupWrapper = new QueryWrapper<>();
		salesgroupWrapper.eq("ProjectID", ProjectID);
		salesgroupWrapper.eq("IsDel", 0);
		salesgroupWrapper.orderByAsc("CreateTime");
		List<BSalesgroup> salesgroupList = iBSalesgroupService.list(salesgroupWrapper);

		result.put("salesgroupList", salesgroupList);

		// 获取渠道类型
		dictWrapper.eq("PID", "0770B012-897B-49DF-A11F-A36B94D0178A");
		dictWrapper.eq("IsDel", 0);
		dictWrapper.eq("Status", 1);
		List<SDictionary> channelTypeList = iSDictionaryService.list(dictWrapper);
		result.put("channelTypeList", channelTypeList);

		return Result.ok(result);
	}

	@ApiImplicitParams({
			@ApiImplicitParam(name = "ProjectID", value = "ProjectID", required = true, dataType = "String"),
			@ApiImplicitParam(name = "ReceptionGroupID", value = "ReceptionGroupID", required = true, dataType = "String"),
			@ApiImplicitParam(name = "UserID", value = "UserID", required = true, dataType = "String"),
			@ApiImplicitParam(name = "RoleID", value = "RoleID", required = true, dataType = "String"),
			@ApiImplicitParam(name = "Tp", value = "Tp", required = true, dataType = "String"),
			@ApiImplicitParam(name = "PageIndex", value = "PageIndex", required = true, dataType = "String"),
			@ApiImplicitParam(name = "PageSize", value = "PageSize", required = true, dataType = "String"),
			@ApiImplicitParam(name = "Kw", value = "Kw", dataType = "String") })
	@ApiOperation(value = "查询团队人员列表接口", notes = "查询团队人员列表接口")
	@RequestMapping(value = "/SalesGroupTeamList_Select", method = { RequestMethod.GET })
	public Result SalesGroupTeamList_Select(@RequestParam(required = true) String ProjectID,
			@RequestParam(required = true) String ReceptionGroupID, @RequestParam(required = true) String UserID,
			@RequestParam(required = true) String RoleID, @RequestParam(required = true) String Tp,
			@RequestParam(required = true) String PageIndex, @RequestParam(required = true) String PageSize,
			String Kw) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ProjectID", ProjectID);
		map.put("ReceptionGroupID", ReceptionGroupID);
		map.put("UserID", UserID);
		map.put("RoleID", RoleID);
		map.put("Tp", Tp);
		map.put("PageIndex", PageIndex);
		map.put("PageSize", PageSize);
		map.put("Kw", Kw);

		IPage<Map<String, Object>> page = new Page<>();
		page.setSize(Integer.valueOf(PageSize));
		page.setCurrent(Integer.valueOf(PageIndex));

		result.put("List", iSaleGroupMemberService.SalesGroupTeamList_Select(page, map));
		return Result.ok(result);
	}

	@ApiImplicitParams({ @ApiImplicitParam(name = "Ids", value = "Ids", required = true, dataType = "String"),
			@ApiImplicitParam(name = "RoleID", value = "RoleID", required = true, dataType = "String"),
			@ApiImplicitParam(name = "ProjectID", value = "ProjectID", required = true, dataType = "String"),
			@ApiImplicitParam(name = "PersonId", value = "PersonId", required = true, dataType = "String"),
			@ApiImplicitParam(name = "RemoveIds", value = "RemoveIds", required = true, dataType = "String"),
			@ApiImplicitParam(name = "UserID", value = "UserID", required = true, dataType = "String"),
			@ApiImplicitParam(name = "RoleName", value = "RoleName", required = true, dataType = "String") })
	@ApiOperation(value = "添加移除成员接口", notes = "添加移除成员接口")
	@RequestMapping(value = "/SalesGroupMembers_Insert", method = { RequestMethod.POST })
	public Result SalesGroupMembers_Insert(@RequestParam(required = true) String Ids,
			@RequestParam(required = true) String ProjectID, @RequestParam(required = true) String RoleID,
			@RequestParam(required = true) String PersonId, @RequestParam(required = true) String RemoveIds,
			@RequestParam(required = true) String UserID, @RequestParam(required = true) String RoleName,
			String ReceptionGroupID, String custCount) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Ids", Ids);
		map.put("ProjectID", ProjectID);
		map.put("RoleID", RoleID);
		map.put("PersonId", PersonId);
		map.put("RemoveIds", RemoveIds);
		map.put("UserID", UserID);
		map.put("RoleName", RoleName);
		map.put("ReceptionGroupID", ReceptionGroupID);
		map.put("custCount", custCount);

		return iSaleGroupMemberService.SalesGroupMembers_Insert(map);
	}

	@ApiImplicitParams({ @ApiImplicitParam(name = "PersonId", value = "PersonId", required = true, dataType = "String"),
			@ApiImplicitParam(name = "IsLeadingOfficial", value = "IsLeadingOfficial", required = true, dataType = "Integer"),
			@ApiImplicitParam(name = "ReceptionGroupID", value = "ReceptionGroupID", required = true, dataType = "String") })
	@ApiOperation(value = "设置自渠负责人", notes = "设置自渠负责人")
	@RequestMapping(value = "/setZqLeader", method = { RequestMethod.POST })
	public Result setZqLeader(@RequestParam(required = true) String PersonId,
			@RequestParam(required = true) Integer IsLeadingOfficial, String ReceptionGroupID) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("PersonId", PersonId);
		map.put("IsLeadingOfficial", IsLeadingOfficial);
		map.put("ReceptionGroupID", ReceptionGroupID);

		return iSaleGroupMemberService.setZqLeader(map);
	}

}
