package com.tahoecn.xkc.controller.webapi;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.ResponseMessage;
import com.tahoecn.xkc.model.channel.BChannelorg;
import com.tahoecn.xkc.model.dict.SDictionary;
import com.tahoecn.xkc.model.rule.BCluerule;
import com.tahoecn.xkc.model.rule.BClueruleAdvisergroup;
import com.tahoecn.xkc.model.vo.BClueruleGourpVo;
import com.tahoecn.xkc.model.vo.BClueruleVo;
import com.tahoecn.xkc.service.dict.ISDictionaryService;
import com.tahoecn.xkc.service.rule.IBClueruleAdvisergroupService;
import com.tahoecn.xkc.service.rule.IBClueruleService;
import com.tahoecn.xkc.service.rule.IRuleAppService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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
@RequestMapping("/ruleApp")
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
            @ApiImplicitParam(name = "protectSource1", value = "保护来源:0.推荐 1.自有 2.分销", required = true, dataType = "String")})
    @ApiOperation(value = "获取项目信息接口", notes = "获取项目信息接口")
    @RequestMapping(value = "/RuleClue_Select", method = { RequestMethod.GET })
    public ResponseMessage RuleClue_Select(@RequestParam(required = true) String projectId,@RequestParam(required = true) String protectSource1) {
        Integer protectSource = Integer.parseInt(protectSource1);
    	QueryWrapper<BCluerule> queryWrapper = new QueryWrapper<BCluerule>();
        queryWrapper.eq(StringUtils.isNotBlank(projectId), "ProjectID", projectId);
        queryWrapper.eq(StringUtils.isNotBlank(projectId), "ProtectSource", protectSource);
        queryWrapper.eq("isdel", 0);
        //先看线索规则表有没有，没有就要初始化数据
        BCluerule cluerule = (BCluerule)iBClueruleService.getObj(queryWrapper);
        if(cluerule==null){
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
            newCluerule.setProtectSource(protectSource);
            iBClueruleService.save(newCluerule);

            //初始化线索规则群体
            if(protectSource==2){//2.分销
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
                if(protectSource==0){
                    dictQueryWrapper.eq("Ext1", 0);
                }else if(protectSource==1){
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
        clueruleQueryWrapper.eq("ProtectSource", protectSource);
        clueruleQueryWrapper.eq("isdel", 0);
        clueruleQueryWrapper.isNull("versionstarttime");
        clueruleQueryWrapper.orderByAsc("versionstarttime");
        List<BCluerule> clueruleList = iBClueruleService.list(clueruleQueryWrapper);
        List<BClueruleVo> clueruleVoList = new ArrayList<BClueruleVo>();
        BeanUtils.copyProperties(clueruleList,clueruleVoList);

        for(int i=0;i<clueruleVoList.size();i++){
            BClueruleVo clueruleVo = clueruleVoList.get(i);
            if(protectSource==2) {//2.分销
                List<BClueruleGourpVo> clueruleGourpVoList = iRuleAppService.getFenxiao(projectId,protectSource);
                clueruleVo.setClueruleGourpVoList(clueruleGourpVoList);
            }else{//0.推荐 1.自有
                List<BClueruleGourpVo> clueruleGourpVoList = iRuleAppService.getZiyouOrTuijian(projectId,protectSource);
                clueruleVo.setClueruleGourpVoList(clueruleGourpVoList);
            }
        }

        return ResponseMessage.ok(clueruleVoList);
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
    
	@ApiOperation(value = "渠道规则设置-编辑", notes = "编辑规则")
	@RequestMapping(value = "/RuleClue_Update", method = { RequestMethod.POST })
	public ResponseMessage RuleClue_Update(@RequestBody BCluerule bCluerule,String isUpdateGroup) {
		//1.检查参数完整性
    	if(StringUtils.isBlank(isUpdateGroup) 
    			|| StringUtils.isBlank(bCluerule.getId())){
    		return ResponseMessage.error("参数录入不完整，请检查参数信息");
    	}
		try{
//			if(bCluerule.getProtectSource() != null 
//					&& "1".equals(bCluerule.getProtectSource())){//自有渠道
//				if(bCluerule.getFollowUpOverdueDays()){
//					
//				}
//			}
			
//			
//			
//			
//			
//			
//			
//			
//    		if("0".equals(isUpdateGroup)){
//    			bCluerule.setEditor(Editor);
//    			bCluerule.setProjectID(ProjectID);
//    			bCluerule.setCalMode(CalMode);
//    			bCluerule.setRuleName(RuleName);
//    			bCluerule.setRuleType(RuleType);
//    			bCluerule.setProtectSource(ProtectSource);
//    			bCluerule.setValidationMode(ValidationMode);
//    			bCluerule.setIsOnlyAllowNew(IsOnlyAllowNew);
//    			bCluerule.setProjectTypeID(ProjectID);
//    			bCluerule.setOverdueTime(OverdueTime);
//    			bCluerule.setOldOwnerLimit(OldOwnerLimit);
//    			bCluerule.setIsPermanent(IsPermanent);
//    			bCluerule.setIsSelect(IsSelect);
//    			bCluerule.setIsPreIntercept(IsPreIntercept);
//    			bCluerule.setPreInterceptTime(PreInterceptTime);
//    			bCluerule.setUserBehaviorID(UserBehaviorID);
//    			bCluerule.setIsProtectVisit(IsProtectVisit);
//    			bCluerule.setProtectVisitTime(ProtectVisitTime);
//    			bCluerule.setProtectVisitRemindTime(ProtectVisitRemindTime);
//    			bCluerule.setIsProtect(IsProtect);
//    			bCluerule.setProtectTime(ProtectTime);
//    			bCluerule.setProtectRemindTime(ProtectRemindTime);
//    			bCluerule.setFollowUpOverdueDays(FollowUpOverdueDays);
//    			bCluerule.setTakeEffectTime(new Date());
//    			
//    			
////    			bCluerule.setVersionEndTime(VersionEndTime);
//    			
    			iBClueruleService.updateById(bCluerule);
//    		}else{
//    			
//    		}
    		
//    		iBClueruleService.updateAdviserGroupById(map);
    		return ResponseMessage.ok("修改成功");
    	}catch (Exception e) {
    		e.printStackTrace();
            return ResponseMessage.error("系统错误，请联系管理员");
		}
	}

}
