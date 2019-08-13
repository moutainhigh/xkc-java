package com.tahoecn.xkc.controller.app;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.dto.GWCustomerPageDto;
import com.tahoecn.xkc.service.customer.IVCustomergwlistSelectService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @since 2019-06-25
 */
@RestController
@Api(tags = "APP-置业顾问接口", value = "APP-置业顾问接口")
@RequestMapping("/app/careerCons")
public class CareerConsCustController extends TahoeBaseController {

    @Autowired
    private IVCustomergwlistSelectService iVCustomergwlistSelectService;
    /**
     * 职业顾问客户列表
     * @param gWCustomerPageDto
     * @return
     */
	@ResponseBody
    @ApiOperation(value = "置业顾问客户列表", notes = "职业顾问客户列表")
    @RequestMapping(value = "/mCustomerGWList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mCustomerGWList_Select(@RequestBody JSONObject paramAry) {
		GWCustomerPageDto gWCustomerPageDto = new GWCustomerPageDto();
		JSONObject json = paramAry.getJSONObject("_param");
		gWCustomerPageDto = JSON.parseObject(json.toJSONString(), GWCustomerPageDto.class);
		return iVCustomergwlistSelectService.customerList(gWCustomerPageDto);
    }
	
	
	@ResponseBody
    @ApiOperation(value = "置业顾问设置子集信息时待选客户列表", notes = "职业顾问设置子集信息时待选客户列表")
    @RequestMapping(value = "/mCustomerGWList_ForChild_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mCustomerGWList_ForChild_Select(@RequestBody JSONObject paramAry) {
		GWCustomerPageDto gWCustomerPageDto = new GWCustomerPageDto();
		JSONObject json = paramAry.getJSONObject("_param");
		gWCustomerPageDto = JSON.parseObject(json.toJSONString(), GWCustomerPageDto.class);
		return iVCustomergwlistSelectService.customerListForSetChild(gWCustomerPageDto);
    }
	
	@ResponseBody
    @ApiOperation(value = "置业顾问设置客户成员信息", notes = "置业顾问设置客户成员信息")
    @RequestMapping(value = "/setCustomerChild_update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result setCustomerChild_update(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iVCustomergwlistSelectService.updateParentID(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "置业顾问客户基本信息", notes = "职业顾问客户基本信息")
    @RequestMapping(value = "/mCustomerGWBase_Select", method = RequestMethod.POST)
    public Result mCustomerGWBase_Select(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iVCustomergwlistSelectService.customerBase(json);
    }
	
	
	@ResponseBody
    @ApiOperation(value = "置业顾问跟进记录", notes = "置业顾问跟进记录")
    @RequestMapping(value = "/mCustomerFollowUpList_Select", method = RequestMethod.POST)
    public Result mCustomerFollowUpList_Select(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iVCustomergwlistSelectService.mCustomerFollowUpList_Select(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "置业顾问销售轨迹", notes = "置业顾问销售轨迹")
    @RequestMapping(value = "/mCustomerTrackList_Select", method = RequestMethod.POST)
    public Result mCustomerTrackList_Select(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iVCustomergwlistSelectService.TrackList(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "置业顾问新增跟进记录", notes = "置业顾问新增跟进记录")
    @RequestMapping(value = "/mCustomerFollowUpDetail_Insert", method = RequestMethod.POST)
    public Result mCustomerFollowUpDetail_Insert(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iVCustomergwlistSelectService.mCustomerFollowUpDetail_Insert(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "置业顾问关注客户", notes = "置业顾问关注客户")
    @RequestMapping(value = "/mUserFollowDetail_Insert", method = RequestMethod.POST)
    public Result mUserFollowDetail_Insert(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iVCustomergwlistSelectService.UserFollowDetail_Insert(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "置业顾问取消关注客户", notes = "置业顾问取消关注客户")
    @RequestMapping(value = "/mUserFollowDetail_Delete", method = RequestMethod.POST)
    public Result mUserFollowDetail_Delete(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iVCustomergwlistSelectService.UserFollowDetail_Delete(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "置业顾问客户标签新增", notes = "置业顾问客户标签新增")
    @RequestMapping(value = "/mCustomerTagDetail_Insert", method = RequestMethod.POST)
    public Result mCustomerTagDetail_Insert(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iVCustomergwlistSelectService.CustomerTagAdd(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "置业顾问顾问标签新增", notes = "置业顾问顾问标签新增")
    @RequestMapping(value = "/mCounselorTagDetail_Insert", method = RequestMethod.POST)
    public Result mCounselorTagDetail_Insert(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iVCustomergwlistSelectService.TagAdd(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "置业顾问顾问标签列表", notes = "置业顾问顾问标签列表")
    @RequestMapping(value = "/mCounselorTagList_Select", method = RequestMethod.POST)
    public Result mCounselorTagList_Select(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iVCustomergwlistSelectService.TagList(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "置业顾问客户意向列表", notes = "置业顾问客户意向列表")
    @RequestMapping(value = "/mCustomerIntentProjectList_Select", method = RequestMethod.POST)
    public Result mCustomerIntentProjectList_Select(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iVCustomergwlistSelectService.IntentProjectList(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "置业顾问客户意向新增", notes = "置业顾问客户意向新增")
    @RequestMapping(value = "/mCustomerIntentProjectList_Insert", method = RequestMethod.POST)
    public Result mCustomerIntentProjectList_Insert(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iVCustomergwlistSelectService.IntentProjectAdd(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "置业顾问项目列表", notes = "置业顾问项目列表")
    @RequestMapping(value = "/mProjectList_Select", method = RequestMethod.POST)
    public Result mProjectList_Select(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iVCustomergwlistSelectService.ProjectList(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "置业顾问客户资料信息", notes = "置业顾问客户资料信息")
    @RequestMapping(value = "/mCustomerGWDetail_Select", method = RequestMethod.POST)
    public Result mCustomerGWDetail_Select(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iVCustomergwlistSelectService.Detail(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "置业顾问客户丢失详细信息", notes = "置业顾问客户丢失详细信息")
    @RequestMapping(value = "/mCustomerGiveUpDetail_Select", method = RequestMethod.POST)
    public Result mCustomerGiveUpDetail_Select(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iVCustomergwlistSelectService.GiveUpDetail(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "置业顾问丢失申请提交", notes = "置业顾问丢失申请提交")
    @RequestMapping(value = "/mCustomerGiveUpDetail_Insert", method = RequestMethod.POST)
    public Result mCustomerGiveUpDetail_Insert(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iVCustomergwlistSelectService.GiveUpApplyAdd(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "置业顾问获取丢失状态", notes = "置业顾问获取丢失状态")
    @RequestMapping(value = "/mCustomerGiveUpStatus_Select", method = RequestMethod.POST)
    public Result mCustomerGiveUpStatus_Select(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iVCustomergwlistSelectService.GiveUpStatusFind(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "置业顾问成交信息", notes = "置业顾问成交信息")
    @RequestMapping(value = "/mCustomerDealDetail_Select", method = RequestMethod.POST)
    public Result mCustomerDealDetail_Select(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iVCustomergwlistSelectService.DealDetail(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "置业顾问修改用户信息", notes = "置业顾问修改用户信息")
    @RequestMapping(value = "/mUserDetail_Update", method = RequestMethod.POST)
    public Result mUserDetail_Update(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iVCustomergwlistSelectService.mUserDetail_Update(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "置业顾问案场顾问客户登记", notes = "置业顾问案场顾问客户登记")
    @RequestMapping(value = "/mCustomerGWDetail_Insert", method = RequestMethod.POST)
    public Result mCustomerGWDetail_Insert(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iVCustomergwlistSelectService.mCustomerGWDetail_Insert(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "置业顾问顾问新增客户查询", notes = "置业顾问顾问新增客户查询")
    @RequestMapping(value = "/mCustomerGWSearch_Select", method = RequestMethod.POST)
    public Result mCustomerGWSearch_Select(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iVCustomergwlistSelectService.CustomerFind(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "置业顾问顾问完善客户资料", notes = "置业顾问顾问完善客户资料")
    @RequestMapping(value = "/mCustomerGWDetail_Update", method = RequestMethod.POST)
    public Result mCustomerGWDetail_Update(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iVCustomergwlistSelectService.mCustomerGWDetail_Update(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "置业顾问客户认购查询", notes = "置业顾问客户认购查询")
    @RequestMapping(value = "/mCustomerSubscribeDetail_Select", method = RequestMethod.POST)
    public Result mCustomerSubscribeDetail_Select(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iVCustomergwlistSelectService.SubscribeFind(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "置业顾问客户认购", notes = "置业顾问客户认购")
    @RequestMapping(value = "/mCustomerSubscribeDetail_Insert", method = RequestMethod.POST)
    public Result mCustomerSubscribeDetail_Insert(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iVCustomergwlistSelectService.mCustomerSubscribeDetail_Insert(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "置业顾问关联权益人列表", notes = "置业顾问关联权益人列表")
    @RequestMapping(value = "/mCustomerRelPeople_Select", method = RequestMethod.POST)
    public Result mCustomerRelPeople_Select(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iVCustomergwlistSelectService.RelPeopleList(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "置业顾问获取关注列表", notes = "置业顾问获取关注列表")
    @RequestMapping(value = "/mUserFollowList_Select", method = RequestMethod.POST)
    public Result mUserFollowList_Select(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iVCustomergwlistSelectService.UserFollowList_Select(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "置业顾问获取客户信息变更记录", notes = "置业顾问获取关注列表")
    @RequestMapping(value = "/getCustomerChangeList", method = RequestMethod.POST)
    public Result getCustomerChangeList(String OpportunityID) {
		return iVCustomergwlistSelectService.getCustomerChangeList(OpportunityID);
    }
	
	@ResponseBody
    @ApiOperation(value = "置业顾问上传头像", notes = "置业顾问上传头像")
    @RequestMapping(value = "/mSystemUploader_Insert", method = {RequestMethod.POST},headers="content-type=multipart/form-data")
    public Result mSystemUploader_Insert(@RequestParam("file") MultipartFile file) {
		Result re = new Result();
		try {
			MultipartFile[] files = {file};
			String filePath = this.uploadFiles(files);
			re.setData(filePath);
			re.setErrcode(0);
			re.setErrmsg("成功");
		} catch (Exception e) {
			re.setErrcode(1);
			re.setErrmsg("系统异常");
			e.printStackTrace();
		}
		return re;
    }
}
