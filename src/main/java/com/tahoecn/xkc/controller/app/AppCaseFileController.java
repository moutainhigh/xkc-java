package com.tahoecn.xkc.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tahoecn.xkc.common.enums.ActionType;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.customer.BClue;
import com.tahoecn.xkc.model.customer.BCustomer;
import com.tahoecn.xkc.model.customer.VCustomerfjlistSelect;
import com.tahoecn.xkc.model.opportunity.BOpportunity;
import com.tahoecn.xkc.model.rule.BCluerule;
import com.tahoecn.xkc.model.vo.ChannelRegisterModel;
import com.tahoecn.xkc.model.vo.CustomerActionVo;
import com.tahoecn.xkc.service.customer.IBClueService;
import com.tahoecn.xkc.service.customer.IBCustomerService;
import com.tahoecn.xkc.service.customer.IVCustomerfjlistSelectService;
import com.tahoecn.xkc.service.customer.impl.VCustomergwlistSelectServiceImpl;
import com.tahoecn.xkc.service.opportunity.IBOpportunityService;
import com.tahoecn.xkc.service.rule.IBClueruleService;

import cn.hutool.core.date.DateTime;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YYY
 * @since 2019-06-25
 */
@RestController
@Api(tags = "APP-案场助手", value = "APP-案场助手")
@RequestMapping("/app/casefile")
public class AppCaseFileController extends TahoeBaseController {
	@Value("${SiteUrl}")
    private String SiteUrl;
    @Autowired
    private IBClueService iBClueService;
    @Autowired
    private IBClueruleService iBClueruleService;
    @Autowired
    private IVCustomerfjlistSelectService iVCustomerfjlistSelectService;
    @Autowired
    private IBOpportunityService iBOpportunityService;
    @Autowired
    private IBClueService clueService;
    @Autowired
    private VCustomergwlistSelectServiceImpl iVCustomergwlistSelectService;
    @Autowired
    private IBCustomerService iBCustomerService;
    
	@ResponseBody
    @ApiOperation(value = "助手首页统计", notes = "助手首页统计")
    @RequestMapping(value = "/mCaseFieDetail_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mCaseFieDetail_Select(@RequestBody JSONObject jsonParam) {
    	try{
            @SuppressWarnings("rawtypes")
			Map paramMap = (HashMap)jsonParam.get("_param");
            //查询
            Map<String,Object> ob = iBClueService.CaseFieDetail_Select(paramMap);
            Map<String,Object> pa = new HashMap<String,Object>();
            pa.put("PageSize", "1");
            pa.put("ProjectID", jsonParam.get("ProjectID"));
            pa.put("PageIndex", "1");
            pa.put("Mobile", "");
            pa.put("OrgID", jsonParam.get("OrgID"));
            pa.put("Filter", "");
            pa.put("UserID", jsonParam.get("UserID"));
            pa.put("Sort", "");
            List<VCustomerfjlistSelect> full = iVCustomerfjlistSelectService.CustomerList(pa);
            ob.put("Visit", full.size());
            return Result.ok(ob);
    	}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
    }
	@ResponseBody
	@ApiOperation(value = "扫码确认", notes = "扫码确认")
	@RequestMapping(value = "/mCaseFieCustomerDetail_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mCaseFieCustomerDetail_Select(@RequestBody JSONObject jsonParam) {
		try{
			@SuppressWarnings("rawtypes")
			Map paramMap = (HashMap)jsonParam.get("_param");
            //参数验证
//            ParameterEntity pe = Parameter.ToObject<ParameterEntity>();
            if (StringUtils.isEmpty(paramMap.get("TypeID"))){
                return Result.errormsg(1, "参数 TypeID 缺失");
            }
            if (!StringUtils.isEmpty(paramMap.get("TypeID")) && paramMap.get("TypeID").equals("1")){
            	if(StringUtils.isEmpty(paramMap.get("TypeID"))
            			|| StringUtils.isEmpty(paramMap.get("TokerUserID"))
            			|| StringUtils.isEmpty(paramMap.get("SourceType"))
            			|| StringUtils.isEmpty(paramMap.get("ProjectID"))
            			|| StringUtils.isEmpty(paramMap.get("ClueID"))){
            		return Result.errormsg(1, "参数缺失");
            	}
            }
            //判断当前项目和意向项目是否一致 不一致则返回错误
            paramMap.put("SiteUrl", SiteUrl);
            List<Map<String,Object>> ob = iBClueService.CaseFieCustomerDetail_Select(paramMap);
            if (ob.get(0).get("IntentProjectID").toString().toLowerCase() != paramMap.get("ProjectID").toString().toLowerCase()){
                return Result.errormsg(9,"报备项目和案场项目不一致,请确认带客项目");
            }
            //验证规则 TypeID 表示是否验证规则 1 表示验证 其他表示不验证
            //TypeID 表示是否验证规则 1 表示验证 其他表示不验证
            if (paramMap.get("TypeID").equals("1")){
                return clueConfirm(paramMap);
            }
            //查询
            //获取客户信息
            ob = iBClueService.CaseFieCustomerDetail_Select(paramMap);
            //获取线索信息
            int invalidType = (int) ob.get(0).get("InvalidType");//无效类型
            String InvalidReason = (String) ob.get(0).get("InvalidReason");//无效说明
            //获取无效信息
            Map<String,Object> RuleP = new HashMap<String,Object>();
            RuleP.put("Mobile", ob.get(0).get("Mobile"));
            List<Map<String,Object>> inval = iBClueService.CaseFieInvalDetail_Select(RuleP);
            //获取规则信息
            RuleP = new HashMap<String,Object>();
            RuleP.put("ID", ob.get(0).get("RuleID"));
            BCluerule rule = iBClueruleService.CaseFieRuleDetail_Select(RuleP);
            //组建规则说明 根据规则id获取规则
            List<String> ruleList = new ArrayList<String>();
            if (rule.getIsOnlyAllowNew().equals("1")){
                ruleList.add(rule.getRuleName() + "，仅允许报备项目新客户");
            }
            if (rule.getIsOnlyAllowNew().equals("0")){
                if (rule.getOldOwnerLimit().equals("0")){
                    ruleList.add(rule.getRuleName() + "，允许报备新客户或者不在保护期的老客户，且不允许报备集团老业主");
                }
                if (rule.getOldOwnerLimit().equals("1")){
                    ruleList.add(rule.getRuleName() + "，允许报备新客户或者不在保护期的老客户，且仅允许报备其他项目的老业主");
                }
            }
            if (rule.getFollowUpOverdueDays() > 0){
                ruleList.add("保护期：距离上一次跟进时间超过" + rule.getFollowUpOverdueDays() + "天未跟进自动失效");
            }
            if (rule.getIsProtect().equals("1")){
                String leixing = "";
                if (rule.getUserBehaviorID().equals("1")){
                    leixing = ("签约");
                }
                if (rule.getUserBehaviorID().equals("2")){
                    leixing = ("认购");
                }
                if (rule.getUserBehaviorID().equals("3")){
                    leixing = ("认筹");
                }
                if (rule.getIsSelect().equals("1")){
                    ruleList.add("保护期模式：两段式 报备-到访-" + leixing);
                    ruleList.add("到访保护期" + rule.getProtectVisitTime() + "天，提前" + rule.getProtectVisitRemindTime() + "天提醒");
                }else{
                    ruleList.add("启用保护期模式：一段式 报备-" + leixing);
                }
                ruleList.add(leixing + "保护期" + rule.getProtectTime() + "天，提前" + rule.getProtectRemindTime() + "天提醒");

            }
            if (rule.getIsPreIntercept().equals("1")){
                ruleList.add("启用防截客时间" + rule.getPreInterceptTime() + "分钟");
            }
            ruleList.add("规则在:" + rule.getTakeEffectTime() + "生效");
            //无效信息 根据手机号获取有效的线索
            List<Map<String,Object>> invalidList = new ArrayList<Map<String,Object>>();
            Map<String,Object> jos = null;
            //老客户
            if (invalidType == 2){
                jos = new HashMap<String,Object>();
                jos.put("Name", "无效说明");
                jos.put("Value", InvalidReason);
                invalidList.add(jos);
                jos = new HashMap<String,Object>();
                jos.put("Name", "职业顾问名称");
                jos.put("Value", inval.get(0).get("ClSaleUserName"));
                invalidList.add(jos);
                jos = new HashMap<String,Object>();
                jos.put("Name", "报备时间");
                jos.put("Value", inval.get(0).get("ClCreateTime"));
                invalidList.add(jos);
            }else if(invalidType == 4){
	            //被其他渠道报备
	            jos = new HashMap<String,Object>();
	            jos.put("Name", "无效说明");
	            jos.put("Value", InvalidReason);
	            invalidList.add(jos);
	            jos = new HashMap<String,Object>();
	            jos.put("Name", "报备人");
	            jos.put("Value", inval.get(0).get("ClCreator"));
	            invalidList.add(jos);
	            jos = new HashMap<String,Object>();
	            jos.put("Name", "报备时间");
	            jos.put("Value", inval.get(0).get("ClCreateTime"));
	            invalidList.add(jos);
            }else if (invalidType == 5){
	            //被其他渠道带看
	            jos = new HashMap<String,Object>();
	            jos.put("Name", "无效说明");
	            jos.put("Value", InvalidReason);
	            invalidList.add(jos);
	            jos = new HashMap<String,Object>();
	            jos.put("Name", "报备人");
	            jos.put("Value", inval.get(0).get("ClCreator"));
	            invalidList.add(jos);
	            jos = new HashMap<String,Object>();
	            jos.put("Name", "报备时间");
	            jos.put("Value", inval.get(0).get("ClCreateTime"));
	            invalidList.add(jos);
            }else if (invalidType == 1){
	            //报备的客户为集团老业主
	            jos = new HashMap<String,Object>();
	            jos.put("Name", "无效说明");
	            jos.put("Value", InvalidReason);
	            invalidList.add(jos);
	            jos = new HashMap<String,Object>();
	            jos.put("Name", "职业顾问名称");
	            jos.put("Value", inval.get(0).get("ClSaleUserName"));
	            invalidList.add(jos);
	            jos = new HashMap<String,Object>();
	            jos.put("Name", "报备时间");
	            jos.put("Value", inval.get(0).get("ClCreateTime"));
	            invalidList.add(jos);
            }else if (invalidType == 3 || invalidType == 7 || invalidType == 8){
	            //防截客时间内到访
	            jos = new HashMap<String,Object>();
	            jos.put("Name", "无效说明");
	            jos.put("Value", InvalidReason);
	            invalidList.add(jos);
            }else if (invalidType == 0){
	            //默认
	            jos = new HashMap<String,Object>();
	            jos.put("Name", "无效说明");
	            jos.put("Value", InvalidReason);
	            invalidList.add(jos);
	            jos = new HashMap<String,Object>();
	            jos.put("Name", "报备人");
	            jos.put("Value", inval.get(0).get("ClCreator"));
	            invalidList.add(jos);
	            jos = new HashMap<String,Object>();
	            jos.put("Name", "报备时间");
	            jos.put("Value", inval.get(0).get("ClCreateTime"));
	            invalidList.add(jos);
			}else{
			    jos = new HashMap<String,Object>();
			    jos.put("Name", "无效说明");
			    jos.put("Value", InvalidReason);
			    invalidList.add(jos);
			    jos = new HashMap<String,Object>();
			    jos.put("Name", "报备人");
			    jos.put("Value", inval.get(0).get("ClCreator"));
			    invalidList.add(jos);
			    jos = new HashMap<String,Object>();
			    jos.put("Name", "报备时间");
			    jos.put("Value", inval.get(0).get("ClCreateTime"));
			    invalidList.add(jos);
			}
            //组建json
            Map<String,Object> jo = new HashMap<String,Object>();
            jo.put("ClueList", ob);
            jo.put("CluruleListeList", ruleList);
            return Result.ok(jo);
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	private Result clueConfirm(Map Parameter) {
        String errmsg = "";
        String Data = "";
        String channelTypeID = getChannelTypeID(Parameter.get("ClueID").toString());
        if (StringUtils.isEmpty(channelTypeID)){
        	return Result.errormsg(1,"未找到渠道身份");
        }
        ChannelRegisterModel channel = new ChannelRegisterModel(Parameter.get("TokerUserID").toString(), channelTypeID, Parameter.get("ProjectID").toString());
        Map<String,Object> ruleValidate = ValidateForConfirmation(Parameter.get("ClueID").toString(),channel);
        String invalidReason = clueService.getMessage((int)ruleValidate.get("InvalidType"),(Map)channel.getUserRule());
        DateTime visitTime = DateTime.now();
        Parameter.put("VisitTime", visitTime);
        Parameter.put("ConfirmUserId", Parameter.get("UserID"));

        //通过有效验证
        if ((boolean) ruleValidate.get("Tag")){
            String tradeOverTime = "";
            Parameter.put("TradeOverdueTime", tradeOverTime);
            return new Result(iBClueService.ClueConfirm_Update(Parameter),"带看确认成功！",0);
        }else{//验证不通过
            if ((int)ruleValidate.get("InvalidType") == -1){
                return Result.errormsg(1, ruleValidate.get("Message").toString());
            }
            errmsg = clueService.GetMessageForReturn((int)ruleValidate.get("InvalidType"),(Map)channel.getUserRule());
            Parameter.put("InvalidType", ruleValidate.get("InvalidType"));
            Parameter.put("InvalidReason", invalidReason);
            //更新当前验证的线索的状态为3（无效）、确认时间、确认人、无效类型和无效原因
            iBClueService.ClueConfirmInvalid_Update(Parameter);
        }
        String follwUpType = (boolean)ruleValidate.get("Tag") == true ? ActionType.确客有效.toString() : ActionType.报备无效.toString();
        CustomerActionVo customerActionVo = new CustomerActionVo();
		customerActionVo.setFollwUpType(follwUpType);
		customerActionVo.setFollwUpTypeID(ActionType.valueOf(follwUpType).getValue());
		customerActionVo.setSalesType(4);
		customerActionVo.setNewSaleUserName("");
		customerActionVo.setOldSaleUserName("");
		customerActionVo.setFollwUpUserID(Parameter.get("UserID").toString());
		customerActionVo.setFollwUpWay("");
		customerActionVo.setFollowUpContent("");
		customerActionVo.setIntentionLevel("");
		customerActionVo.setOrgID(Parameter.get("OrgID").toString());
		customerActionVo.setFollwUpUserRole(Parameter.get("JobID").toString());
		customerActionVo.setOpportunityID("");
		customerActionVo.setClueID(Parameter.get("ClueID").toString());
		customerActionVo.setNextFollowUpDate("");
        iVCustomergwlistSelectService.CustomerFollowUp_Insert(customerActionVo);
        return Result.ok(errmsg);
	}
	private Map<String,Object> ValidateForConfirmation(String ClueID,ChannelRegisterModel channel) {
		Map<String,Object> msg = new HashMap<String,Object>();
		QueryWrapper<BClue> wrapper = new QueryWrapper<BClue>();
        wrapper.eq("id", ClueID);
        wrapper.eq("IsDel", 0);
        BClue obj = iBClueService.getOne(wrapper);
        String phone = obj.getMobile();
        String projectId = obj.getIntentProjectID();
        //该线索已经被确认过
        if (obj.getStatus() == 2){
            msg.put("Tag", false);
            msg.put("InvalidType", -1);
            msg.put("Message", "该线索已被案场确认过！");
            return msg;
        }
        //判断待验证线索是否已经失效
        if (obj.getStatus() == 3){
        	msg.put("Tag", false);
            msg.put("InvalidType", obj.getInvalidType());
            msg.put("Message", obj.getInvalidReason());
            return msg;
        }
        //到访超过保护期
        if (IsOverdueCome(obj)){
        	msg.put("Tag", false);
            msg.put("InvalidType", 7);
            return msg;
        }
        //是否在防截客时间到访
        if (IsPreIntercept(obj.getCreateTime(),channel)){
        	msg.put("Tag", false);
            msg.put("InvalidType", 3);
            return msg;
        }
        msg = InstantConfirmation(phone, projectId,channel);
        return msg;
	}
	private Map<String, Object> InstantConfirmation(String phone, String projectId,ChannelRegisterModel channel) {
		Map<String,Object> msg = new HashMap<String,Object>();
        if (channel.getUserRule().getImmissionRule().getIsOnlyAllowNew() == 1){//仅允许报备新客户
            //验证该项目是否已存在销售机会
            boolean flag = IsExistOpportunity(phone, projectId);
            if (flag){//存在销售机会
            	msg.put("Tag", false);
                msg.put("InvalidType", 2);
            }else{//不存在销售机会
                msg = validateClue(phone, projectId,channel);
            }
        }else{//允许报备满足条件的老客户（新客户+不在渠道保护期和案场保护期的老客户）
            //老业主限制，0.不允许报备老业主 1.仅允许报备其它项目老业主
            if (channel.getUserRule().getImmissionRule().getOldOwnerLimit() == 0){
                //验证是否集团老业主
            	QueryWrapper<BCustomer> wrapper = new QueryWrapper<BCustomer>();
                wrapper.eq("Status", 1);
                wrapper.eq("IsOwner", 1);
                wrapper.eq("Mobile", phone);
                BCustomer result = iBCustomerService.getOne(wrapper);
                if (result == null){
                	msg.put("Tag", false);
                    msg.put("InvalidType", 1);
                    return msg;
                }
            }else{
                //验证是否是本项目老业主
            	List<Map<String,Object>> pro = iBCustomerService.IsProjectOwner_Select(projectId, phone);
                if (pro == null || pro.size() == 0){
                    msg.put("Tag", false);
                    msg.put("InvalidType", 11);
                    return msg;
                }
            }
            msg = validateClue(phone, projectId,channel);
            if ((boolean)msg.get("Tag") == false)
                return msg;
            msg = validateOpp(phone, projectId);
        }
        return msg;
	}
	/**
	 * 验证是否满足不存在机会或机会没有置业顾问
	 */
	private Map<String, Object> validateOpp(String phone, String projectId) {
		Map<String, Object> msg = new HashMap<String, Object>();
		QueryWrapper<BOpportunity> wrapper = new QueryWrapper<BOpportunity>();
        wrapper.in("status", 1,2,3,4,5);
        wrapper.eq("CustomerMobile", phone);
        wrapper.eq("ProjectID", projectId);
        BOpportunity opp = iBOpportunityService.getOne(wrapper);
        //不存在销售机会
        if(opp == null){
        	msg.put("Tag", true);
            msg.put("InvalidType", 0);
        }else{//存在销售机会
            if(StringUtils.isEmpty(opp.getSaleUserID())){
                msg.put("Tag", true);
                msg.put("InvalidType", 0);
                msg.put("IsExsitOpp", true);
                msg.put("OppID", opp.getId());
            }else{
            	msg.put("Tag", false);
                msg.put("InvalidType", 6);
            }
        }
        return msg;
	}
	/**
	 * 其他条件满足的情况下，验证报备的线索是否有效
	 */
	private Map<String, Object> validateClue(String phone, String projectId,ChannelRegisterModel channel) {
		Map<String, Object> msg = new HashMap<String, Object>();
        //查询项目下存在该手机号的有效线索
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("IntentProjectID", projectId);
        obj.put("Mobile", phone);
        obj.put("ReportUserID", channel.getChannelUserId());
        List<Map<String, Object>> clues = iBClueService.getClueList(obj);
        //该项目已经存在已确认线索
        for(Map<String, Object> c : clues){
        	if((int)c.get("Status") == 2){
        		//报备保护
                if (channel.getUserRule().getRuleType() == 0)
                {
                    msg.put("InvalidType", 4);
                }else{
                	msg.put("InvalidType", 4);
                }
                msg.put("Tag", false);
                return msg;
        	}
        }
        if (clues.size() == 0){//该手机号为新客户（在允许报备老客户时，此段无意义，仅存在于报备新客户）
        	msg.put("Tag", true);
        	msg.put("InvalidType", 0);
            return msg;
        }
        if (clues.size() == 1){//该手机号线索已存在，此条件下需要进一步证明规则类型
            int ruleType = (int) clues.get(0).get("RuleType");
            if (channel.getUserRule().getRuleType() == ruleType && ruleType == 1){//线索为竞争带看，并且当前渠道用户规则也为竞争带看
            	msg.put("Tag", true);
            	msg.put("InvalidType", 0);
            }else{//线索为报备保护
            	msg.put("Tag", false);
            	msg.put("InvalidType", 4);
            }
        }
        else//该手机号存在多条线索，说明报备模式为竞争带看
        {
            int ruleType = (int) clues.get(0).get("RuleType");
            if (channel.getUserRule().getRuleType() == ruleType){//当前渠道的报备规则也为竞争带看
            	msg.put("Tag", true);
            	msg.put("InvalidType", 0);
            }else{//当前渠道的报备规则为报备保护，报备规则模式不同则互斥
            	msg.put("Tag", false);
            	msg.put("InvalidType", 4);
            }
        }
        return msg;
	}
	/**
	 * 验证是否存在销售机会（不区分机会状态）
	 */
	private boolean IsExistOpportunity(String phone, String projectId) {
		QueryWrapper<BOpportunity> wrapper = new QueryWrapper<BOpportunity>();
        wrapper.eq("CustomerMobile", phone);
        wrapper.eq("ProjectID", projectId);
        wrapper.eq("IsDel", 0);
        wrapper.ne("Status", 6);
        BOpportunity result = iBOpportunityService.getOne(wrapper);
        return result == null ? false : true;
	}
	private boolean IsPreIntercept(Date createTime,ChannelRegisterModel channel) {
		if (channel.getUserRule().getProtectRule().getIsPreIntercept() == 1){
            //报备时间+防截客周期小于等于当前时间
            if ((createTime.getMinutes()+channel.getUserRule().getProtectRule().getPreInterceptTime()) <= DateTime.now().getMinutes()){
                return false;
            }else{
                return true;
            }
        }
        else//没有防截客机制
            return false;
	}
	private boolean IsOverdueCome(BClue clue) {
        Map<String,Object> obj = new HashMap<String,Object>();
        obj.put("ClueID", clue.getId());
        Map<String,Object> data = iBClueService.IsOverdueCome_Select(obj);
        return Integer.parseInt(data.get("IsOverdueCome").toString()) == 0 ? false : true;
	}
	private String getChannelTypeID(String ClueID) {
        QueryWrapper<BClue> wrapper = new QueryWrapper<BClue>();
        wrapper.eq("id", ClueID);
        BClue result = iBClueService.getOne(wrapper);
        return result != null ?result.getAdviserGroupID():"";
	}
	@ResponseBody
    @ApiOperation(value = "客户列表(待确认 已确认 无效)", notes = "客户列表(待确认 已确认 无效)")
    @RequestMapping(value = "/mCaseFieToBeConfirmedList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mCaseFieToBeConfirmedList_Select(@RequestBody JSONObject jsonParam) {
    	try{
            @SuppressWarnings("rawtypes")
			Map paramMap = (HashMap)jsonParam.get("_param");
            int PageIndex = (int)paramMap.get("PageIndex");//页面索引
            int PageSize = (int)paramMap.get("PageSize");//每页数量
            String ProjectID = (String)paramMap.get("ProjectID");
            String Status = (String)paramMap.get("Status");
            //查询
            String where = "";
            if (!StringUtils.isEmpty(paramMap.get("Condition"))){
            	where = "  and (c.name = '" + paramMap.get("Condition") + "' or c.Mobile = '" + paramMap.get("Condition") + "')  ";
            }
//            paramMap.put("sqlWhere", where);
            IPage page = new Page(PageIndex, PageSize);
			IPage<Map<String, Object>> ob = iBClueService.CaseFieToBeConfirmedList_Select(page,Status,ProjectID,where);
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
	@ApiOperation(value = "带分配列表", notes = "带分配列表")
	@RequestMapping(value = "/mCaseFieDistributionList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mCaseFieDistributionList_Select(@RequestBody JSONObject jsonParam) {
		try{
			@SuppressWarnings("rawtypes")
			Map paramMap = (HashMap)jsonParam.get("_param");
			int PageIndex = (int)paramMap.get("PageIndex");//页面索引
            int PageSize = (int)paramMap.get("PageSize");//每页数量
            String ProjectID = (String)paramMap.get("ProjectID");//每页数量
			//查询
			String where = "";
			if (!StringUtils.isEmpty(paramMap.get("Condition"))){
				where = "  and (c.name like '%" + paramMap.get("Condition") + "%' or c.Mobile like '%" + paramMap.get("Condition") + "%')  ";
			}
//			paramMap.put("sqlWhere", where);
			IPage page = new Page(PageIndex, PageSize);
			IPage<Map<String, Object>> ob = iBClueService.CaseFieDistributionList_Select(page,ProjectID,where);
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
	@ApiOperation(value = "报备查询", notes = "报备查询")
	@RequestMapping(value = "/mCaseFielInquiriesList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mCaseFielInquiriesList_Select(@RequestBody JSONObject jsonParam) {
		try{
			Map paramMap = (HashMap)jsonParam.get("_param");
			int PageIndex = (int)paramMap.get("PageIndex");//页面索引
			int PageSize = (int)paramMap.get("PageSize");//每页数量
			String ProjectID = (String)paramMap.get("ProjectID");
			//查询
			String where = "and 1=2";
			Map<String, Object> map = new HashMap<String,Object>();
			if (!StringUtils.isEmpty(paramMap.get("Condition"))){
				where = "  and (c.name = '" + paramMap.get("Condition") + "' or c.Mobile = '" + paramMap.get("Condition") + "')  ";
				IPage page = new Page(PageIndex, PageSize);
				IPage<Map<String, Object>> ob = iBClueService.CaseFielInquiriesList_Select(page,ProjectID,where);
				map.put("List", ob.getRecords());
				map.put("AllCount", ob.getTotal());
				map.put("PageSize", ob.getSize());
			}else{
				map.put("List", new ArrayList());
				map.put("AllCount", 0);
				map.put("PageSize", PageSize);
			}
			return Result.ok(map);
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
}
