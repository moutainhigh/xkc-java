package com.tahoecn.xkc.controller.webapi;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.ResponseMessage;
import com.tahoecn.xkc.model.channel.BChannelorg;
import com.tahoecn.xkc.model.dict.SDictionary;
import com.tahoecn.xkc.model.rule.BCluerule;
import com.tahoecn.xkc.model.rule.BClueruleAdvisergroup;
import com.tahoecn.xkc.model.vo.BClueruleGourpVo;
import com.tahoecn.xkc.service.dict.ISDictionaryService;
import com.tahoecn.xkc.service.rule.IBClueruleAdvisergroupService;
import com.tahoecn.xkc.service.rule.IBClueruleService;
import com.tahoecn.xkc.service.rule.IRuleAppService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
@Api(tags = "渠道规则设置", value = "渠道规则设置")
@RequestMapping("/webapi/ruleApp")
public class RuleAppController extends TahoeBaseController {

    @Autowired
    private IBClueruleService iBClueruleService;

    @Autowired
    private ISDictionaryService iSDictionaryService;

    @Autowired
    private IBClueruleAdvisergroupService iBClueruleAdvisergroupService;

    @Autowired
    private IRuleAppService iRuleAppService;

    //规则设置：自有渠道，分销中介，推荐渠道
    @ApiImplicitParams({@ApiImplicitParam(name = "projectId", value = "项目Id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "protectSource", value = "保护来源:0.推荐 1.自有 2.分销", required = true, dataType = "String")})
    @ApiOperation(value = "获取项目信息接口", notes = "获取项目信息接口")
    @RequestMapping(value = "/RuleClue_Select", method = { RequestMethod.GET })
    public ResponseMessage RuleClue_Select(@RequestParam(required = true) String projectId,@RequestParam(required = true) String protectSource) {
        Integer protectSourceInt = Integer.parseInt(protectSource);
    	QueryWrapper<BCluerule> queryWrapper = new QueryWrapper<BCluerule>();
        queryWrapper.eq(StringUtils.isNotBlank(projectId), "ProjectID", projectId);
        queryWrapper.eq("ProtectSource", protectSourceInt);
        queryWrapper.eq("isdel", 0);
        //先看线索规则表有没有，没有就要初始化数据
        int clueruleCount = iBClueruleService.count(queryWrapper);
        if(clueruleCount==0){
            //初始化线索规则
            BCluerule newCluerule = new BCluerule();
            newCluerule.setProjectID(projectId);
            newCluerule.setCalMode(0);
            newCluerule.setRuleName("报备保护规则");
            newCluerule.setRuleType(0);
            newCluerule.setCreator("sys");
            newCluerule.setVersionStartTime(new Date());
            newCluerule.setIsDel(0);
            newCluerule.setStatus(1);
            newCluerule.setProtectSource(protectSourceInt);
            iBClueruleService.save(newCluerule);

            //初始化线索规则群体
            if(protectSourceInt==2){//2.分销
                List<BChannelorg> channelorgList = iRuleAppService.getChanelorgList(projectId);
                for(int i=0;i<channelorgList.size();i++){
                    BChannelorg channelorg = channelorgList.get(i);
                    BClueruleAdvisergroup clueruleAdvisergroup = new BClueruleAdvisergroup();
                    clueruleAdvisergroup.setClueRuleID(newCluerule.getId());
                    clueruleAdvisergroup.setAdviserGroupID(channelorg.getId());
                    clueruleAdvisergroup.setCreator("sys");
                    clueruleAdvisergroup.setCreateTime(new Date());
                    clueruleAdvisergroup.setIsDel(0);
                    clueruleAdvisergroup.setStatus(1);
                    iBClueruleAdvisergroupService.save(clueruleAdvisergroup);
                }
            }else{//0.推荐 1.自有
                QueryWrapper<SDictionary> dictQueryWrapper = new QueryWrapper<SDictionary>();
                dictQueryWrapper.eq("pid", "5316B9EF-2AA7-43BA-A8C8-14A5CA368C95");
                if(protectSourceInt==0){
                    dictQueryWrapper.eq("Ext1", 0);
                }else if(protectSourceInt==1){
                    dictQueryWrapper.eq("Ext1", 1);
                }
                List<SDictionary> dictList = iSDictionaryService.list(dictQueryWrapper);
                for(int i=0;i<dictList.size();i++){
                    SDictionary dict = dictList.get(i);
                    BClueruleAdvisergroup clueruleAdvisergroup = new BClueruleAdvisergroup();
                    clueruleAdvisergroup.setClueRuleID(newCluerule.getId());
                    clueruleAdvisergroup.setAdviserGroupID(dict.getId());
                    clueruleAdvisergroup.setCreator("sys");
                    clueruleAdvisergroup.setCreateTime(new Date());
                    clueruleAdvisergroup.setIsDel(0);
                    clueruleAdvisergroup.setStatus(1);
                    iBClueruleAdvisergroupService.save(clueruleAdvisergroup);
                }
            }
        }

        QueryWrapper<BCluerule> clueruleQueryWrapper = new QueryWrapper<BCluerule>();
        clueruleQueryWrapper.eq(StringUtils.isNotBlank(projectId), "ProjectID", projectId);
        clueruleQueryWrapper.eq("ProtectSource", protectSourceInt);
        clueruleQueryWrapper.eq("isdel", 0);
        clueruleQueryWrapper.isNull("versionendtime");
        clueruleQueryWrapper.orderByAsc("versionstarttime");
        List<BCluerule> clueruleList = iBClueruleService.list(clueruleQueryWrapper);

        for(int i=0;i<clueruleList.size();i++){
            BCluerule cluerule = clueruleList.get(i);
            if(protectSourceInt==2) {//2.分销
                List<BClueruleGourpVo> clueruleGourpVoList = iRuleAppService.getFenxiao(projectId,protectSourceInt);
                cluerule.setClueruleGourpVoList(clueruleGourpVoList);
            }else{//0.推荐 1.自有
                List<BClueruleGourpVo> clueruleGourpVoList = iRuleAppService.getZiyouOrTuijian(projectId,protectSourceInt);
                cluerule.setClueruleGourpVoList(clueruleGourpVoList);
            }
        }
        return ResponseMessage.ok(clueruleList);
    }
    
    @ApiImplicitParams({@ApiImplicitParam(name = "clueRuleId", value = "规则ID", required = true, dataType = "String"),
        @ApiImplicitParam(name = "status", value = "渠道规则：0禁用、1启用、2删除", required = true, dataType = "String"),
        @ApiImplicitParam(name = "projectId", value = "项目Id", required = true, dataType = "String"),
        @ApiImplicitParam(name = "protectSource", value = "保护来源:0.推荐 1.自有 2.分销", required = true, dataType = "String")})
	@ApiOperation(value = "渠道规则设置-禁用/启用/删除", notes = "渠道规则：0禁用、1启用、2删除")
	@RequestMapping(value = "/ClueRuleDetail_Delete", method = { RequestMethod.POST })
	public ResponseMessage ClueRuleDetail_Delete(@RequestParam(required = true) String clueRuleId,
			@RequestParam(required = true) String status,
			@RequestParam(required = true) String projectId,
			@RequestParam(required = true) String protectSource) {
    	//1.检查参数完整性
    	if(StringUtils.isBlank(clueRuleId) || StringUtils.isBlank(status)
    			|| StringUtils.isBlank(projectId)
    			|| StringUtils.isBlank(protectSource)){
    		return ResponseMessage.error("参数录入不完整，请检查参数信息");
    	}
    	try{
    		//2.封装参数
        	Map<String,Object> map = new HashMap<String,Object>();
        	map.put("clueRuleId", clueRuleId);
        	map.put("protectSource", protectSource);
        	map.put("projectId", projectId);
        	map.put("status", status);
        	if("2".equals(status)){//删除
        		iBClueruleService.delClueruleById(clueRuleId);
        		QueryWrapper<BCluerule> wrapper = new QueryWrapper<BCluerule>();
        		wrapper.eq("IsDel", 0);
        		wrapper.eq("Creator", "sys");
        		wrapper.eq("ProtectSource", protectSource);
        		wrapper.eq("ProjectID", projectId);
        		int count = iBClueruleService.count(wrapper);
        		if(count == 1){
        			iBClueruleService.updateAdviserGroup(map);
        		}
        		return ResponseMessage.ok("删除成功");
        	}else{//禁用、启用
        		iBClueruleService.updateClueRuleStatusById(map);
        		iBClueruleService.updateAdviserGroupStatusById(map);
        		return ResponseMessage.ok("修改成功");
        	}
    	}catch (Exception e) {
    		e.printStackTrace();
            return ResponseMessage.error("系统错误，请联系管理员");
		}
	}
    
    @ApiImplicitParams({@ApiImplicitParam(name = "projectId", value = "项目ID", required = true, dataType = "String"),
    	@ApiImplicitParam(name = "calMode", value = "计算模式：0=按天，1=按24小时", required = true, dataType = "String"),
    	@ApiImplicitParam(name = "ruleName", value = "规则名称", required = true, dataType = "String"),
    	@ApiImplicitParam(name = "ruleType", value = "规则类型", required = true, dataType = "String"),
    	@ApiImplicitParam(name = "creator", value = "创建人", required = true, dataType = "String"),
    	@ApiImplicitParam(name = "protectSource", value = "保护来源:0.推荐 1.自有 2.分销", required = true, dataType = "String")
    })
	@ApiOperation(value = "渠道规则设置-新增规则", notes = "新增规则")
	@RequestMapping(value = "/RuleClue_Insert", method = { RequestMethod.POST })
	public ResponseMessage RuleClue_Insert(String projectId, String calMode,
			String ruleName, String ruleType, String creator, String protectSource) {
    	//1.检查参数完整性
    	if(StringUtils.isBlank(projectId) || StringUtils.isBlank(calMode)
    			|| StringUtils.isBlank(ruleName)|| StringUtils.isBlank(ruleType)|| StringUtils.isBlank(creator)
    			|| StringUtils.isBlank(protectSource)){
    		return ResponseMessage.error("参数录入不完整，请检查参数信息");
    	}
    	try{
    		BCluerule bCluerule = new BCluerule();
    		bCluerule.setProjectID(projectId);
    		bCluerule.setCalMode(Integer.parseInt(calMode));
    		bCluerule.setRuleName(ruleName);
    		bCluerule.setRuleType(Integer.parseInt(ruleType));
    		bCluerule.setCreator(creator);
    		bCluerule.setVersionStartTime(new Date());
    		bCluerule.setIsDel(0);
    		bCluerule.setStatus(1);
    		bCluerule.setProtectSource(Integer.parseInt(protectSource));
    		iBClueruleService.save(bCluerule);
        	return ResponseMessage.ok(bCluerule.getId());
    	}catch (Exception e) {
    		e.printStackTrace();
            return ResponseMessage.error("系统错误，请联系管理员");
		}
	}

//    //保存线索规则
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "ID", value = "ID", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "UserID", value = "UserID", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "ProjectID", value = "ProjectID", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "CalMode", value = "CalMode", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "RuleName", value = "RuleName", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "RuleType", value = "RuleType", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "ProtectSource", value = "ProtectSource", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "IsUpdateGroup", value = "IsUpdateGroup", required = true, dataType = "Integer"),
//            @ApiImplicitParam(name = "ValidationMode", value = "ValidationMode", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "IsOnlyAllowNew", value = "IsOnlyAllowNew", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "ProtectTypeID", value = "ProtectTypeID", required = true, dataType = "Integer"),
//            @ApiImplicitParam(name = "OverdueTime", value = "OverdueTime", required = true, dataType = "Integer"),
//            @ApiImplicitParam(name = "OldOwnerLimit", value = "OldOwnerLimit", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "FollowUpOverdueDays", value = "FollowUpOverdueDays", required = true, dataType = "Integer"),
//            @ApiImplicitParam(name = "IsPermanent", value = "IsPermanent", required = true, dataType = "Integer"),
//            @ApiImplicitParam(name = "IsSelect", value = "IsSelect", required = true, dataType = "Integer"),
//            @ApiImplicitParam(name = "IsPreIntercept", value = "IsPreIntercept", required = true, dataType = "Integer"),
//            @ApiImplicitParam(name = "PreInterceptTime", value = "PreInterceptTime", required = true, dataType = "Integer"),
//            @ApiImplicitParam(name = "UserBehaviorID", value = "UserBehaviorID", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "IsProtectVisit", value = "IsProtectVisit", required = true, dataType = "Integer"),
//            @ApiImplicitParam(name = "ProtectVisitTime", value = "ProtectVisitTime", required = true, dataType = "Integer"),
//            @ApiImplicitParam(name = "ProtectVisitRemindTime", value = "ProtectVisitRemindTime", required = true, dataType = "Integer"),
//            @ApiImplicitParam(name = "IsProtect", value = "IsProtect", required = true, dataType = "Integer"),
//            @ApiImplicitParam(name = "ProtectTime", value = "ProtectTime", required = true, dataType = "Integer"),
//            @ApiImplicitParam(name = "ProtectRemindTime", value = "ProtectRemindTime", required = true, dataType = "Integer")
//    })
//    @ApiOperation(value = "保存线索规则接口", notes = "保存线索规则接口")
//    @RequestMapping(value = "/RuleClue_Update", method = { RequestMethod.GET })
//    public ResponseMessage RuleClue_Update(@RequestParam(required = true) String ID,
//                                           @RequestParam(required = true) String UserID,
//                                           @RequestParam(required = true) String ProjectID,
//                                           @RequestParam(required = true) String CalMode,
//                                           @RequestParam(required = true) String RuleName,
//                                           @RequestParam(required = true) String RuleType,
//                                           @RequestParam(required = true) String ProtectSource,
//                                           @RequestParam(required = true) String IsUpdateGroup,
//                                           @RequestParam(required = true) String ValidationMode,
//                                           @RequestParam(required = true) String IsOnlyAllowNew,
//                                           @RequestParam(required = true) String ProtectTypeID,
//                                           @RequestParam(required = true) String OverdueTime,
//                                           @RequestParam(required = true) String OldOwnerLimit,
//                                           @RequestParam(required = true) String FollowUpOverdueDays,
//                                           @RequestParam(required = true) String IsPermanent,
//                                           @RequestParam(required = true) String IsSelect,
//                                           @RequestParam(required = true) String IsPreIntercept,
//                                           @RequestParam(required = true) String PreInterceptTime,
//                                           @RequestParam(required = true) String UserBehaviorID,
//                                           @RequestParam(required = true) String IsProtectVisit,
//                                           @RequestParam(required = true) String ProtectVisitTime,
//                                           @RequestParam(required = true) String ProtectVisitRemindTime,
//                                           @RequestParam(required = true) String IsProtect,
//                                           @RequestParam(required = true) String ProtectTime,
//                                           @RequestParam(required = true) String ProtectRemindTime) {
//
//
//
//        return null;
//    }

    @ApiOperation(value = "保存线索规则接口", notes = "保存线索规则接口")
    @RequestMapping(value = "/RuleClue_Update", method = { RequestMethod.POST })
    public ResponseMessage RuleClue_Update(@RequestBody BCluerule bCluerule, @RequestParam(required = true) String IsUpdateGroup, @RequestParam(required = true) String grouplist){

        if (bCluerule.getProtectSource() == 1)//自有渠道
        {
            if (bCluerule.getFollowUpOverdueDays() <= 3)
            {
                return ResponseMessage.error("保护期需大于3天");
            }
        }

        if (bCluerule.getProtectSource()==null)
        {
            bCluerule.setProtectSource(0);
        }
        if (bCluerule.getOldOwnerLimit()==null)
        {
            bCluerule.setOldOwnerLimit(0);
        }
        if (bCluerule.getIsPermanent()==null)
        {
            bCluerule.setIsPermanent(0);
        }
        if(bCluerule.getIsPreIntercept()==null)
        {
            bCluerule.setIsPreIntercept(0);
        }
        if (bCluerule.getPreInterceptTime()==null)
        {
            bCluerule.setPreInterceptTime(0);
        }
        if(bCluerule.getOverdueTime()==null)
        {
            bCluerule.setOverdueTime(0);
        }
        if(bCluerule.getProtectTypeID()==null)
        {
            bCluerule.setProtectTypeID(0);
        }
        if(bCluerule.getRuleType()==1)//竞争带看默认变成实时验证
        {
            bCluerule.setValidationMode(2);
        }

        if(IsUpdateGroup!=null && !"".equals(IsUpdateGroup)) {
            iBClueruleService.saveOrUpdate(bCluerule);
        }else{
            iBClueruleAdvisergroupService.RuleClue_Update(bCluerule,grouplist);
        }
        return ResponseMessage.ok();
    }
}
