package com.tahoecn.xkc.controller.webapi;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    @ApiOperation(value = "查询线索规则接口", notes = "查询线索规则接口")
    @RequestMapping(value = "/RuleClue_Select", method = { RequestMethod.GET })
    public Result RuleClue_Select(@RequestParam(required = true) String projectId, @RequestParam(required = true) String protectSource) {
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
                List<BClueruleGourpVo> clueruleGourpVoList = iRuleAppService.getFenxiao(projectId,protectSourceInt,cluerule.getId());
                cluerule.setClueruleGourpVoList(clueruleGourpVoList);
            }else{//0.推荐 1.自有
                List<BClueruleGourpVo> clueruleGourpVoList = iRuleAppService.getZiyouOrTuijian(projectId,protectSourceInt,cluerule.getId());
                cluerule.setClueruleGourpVoList(clueruleGourpVoList);
            }
        }
        return Result.ok(clueruleList);
    }

    //未分配渠道
    @ApiImplicitParams({@ApiImplicitParam(name = "projectId", value = "项目Id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "protectSource", value = "保护来源:0.推荐 1.自有 2.分销", required = true, dataType = "String")})
    @ApiOperation(value = "未分配渠道接口", notes = "未分配渠道接口")
    @RequestMapping(value = "/ClueRuleUnassignedList_Select", method = { RequestMethod.GET })
    public Result ClueRuleUnassignedList_Select(@RequestParam(required = true) String projectId, @RequestParam(required = true) Integer protectSource) {
        if (protectSource == 0 || protectSource == 1)
        {
            List<Map<String,Object>> unassignedOneList = iRuleAppService.ClueRuleUnassignedOneList_Select(projectId,protectSource);
            return Result.ok(unassignedOneList);
        }
        else if (protectSource == 2)
        {
            List<Map<String,Object>> unassignedTwoList = iRuleAppService.ClueRuleUnassignedTwoList_Select(projectId,protectSource);
            return Result.ok(unassignedTwoList);
        }
        return null;
    }

    //未分配渠道添加规则
    @ApiImplicitParams({@ApiImplicitParam(name = "ids", value = "ids", required = true, dataType = "String"),
            @ApiImplicitParam(name = "clueRuleId", value = "clueRuleId", required = true, dataType = "String"),
            @ApiImplicitParam(name = "userId", value = "userId", required = true, dataType = "String")})
    @ApiOperation(value = "未分配渠道添加规则接口", notes = "未分配渠道添加规则接口")
    @RequestMapping(value = "/ClueRuleAdviserGroup_Insert", method = { RequestMethod.POST })
    public Result ClueRuleAdviserGroup_Insert(@RequestParam(required = true)String ids, @RequestParam(required = true)String clueRuleId,@RequestParam(required = true)String userId) {
        String[] idStrArr = ids.split(",");
        for(int i =0;i<idStrArr.length;i++){
            QueryWrapper<BClueruleAdvisergroup> clueRuleAdviserGroupWrapper = new QueryWrapper<>();
            clueRuleAdviserGroupWrapper.eq("IsDel",0);
            clueRuleAdviserGroupWrapper.eq("ClueRuleID",clueRuleId);
            clueRuleAdviserGroupWrapper.eq("AdviserGroupID",idStrArr[i]);
            int count = iBClueruleAdvisergroupService.count(clueRuleAdviserGroupWrapper);
            if(count == 0){
                BClueruleAdvisergroup item = new BClueruleAdvisergroup();
                item.setId(UUID.randomUUID().toString());
                item.setClueRuleID(clueRuleId);
                item.setAdviserGroupID(idStrArr[i]);
                item.setCreator(userId);
                item.setCreateTime(new Date());
                item.setEditor(null);
                item.setEditTime(null);
                item.setIsDel(0);
                item.setStatus(1);
                iBClueruleAdvisergroupService.save(item);
            }
        }
        return Result.ok("添加成功");
    }

	@ApiOperation(value = "渠道规则设置-禁用/启用/删除", notes = "渠道规则：0禁用、1启用、2删除")
	@RequestMapping(value = "/ClueRuleDetail_Delete", method = { RequestMethod.POST })
	public Result ClueRuleDetail_Delete(String clueRuleId,String status,String projectId,Integer protectSource) {
    	//1.检查参数完整性
    	if(StringUtils.isBlank(clueRuleId) || StringUtils.isBlank(status)
    			|| StringUtils.isBlank(projectId)
    			|| protectSource == null){
    		return Result.errormsg(0,"参数录入不完整，请检查参数信息");
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
        		return Result.ok("删除成功");
        	}else{//禁用、启用
        		iBClueruleService.updateClueRuleStatusById(map);
        		iBClueruleService.updateAdviserGroupStatusById(map);
        		return Result.ok("修改成功");
        	}
    	}catch (Exception e) {
    		e.printStackTrace();
            return Result.errormsg(0,"系统错误，请联系管理员");
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
	public Result RuleClue_Insert(String projectId, String calMode,
			String ruleName, String ruleType, String creator, String protectSource) {
    	//1.检查参数完整性
    	if(StringUtils.isBlank(projectId) || StringUtils.isBlank(calMode)
    			|| StringUtils.isBlank(ruleName)|| StringUtils.isBlank(ruleType)|| StringUtils.isBlank(creator)
    			|| StringUtils.isBlank(protectSource)){
    		return Result.errormsg(0,"参数录入不完整，请检查参数信息");
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
        	return Result.ok(bCluerule.getId());
    	}catch (Exception e) {
            e.printStackTrace();
            return Result.errormsg(0,"系统错误，请联系管理员");
        }
	}

    @ApiOperation(value = "保存线索规则接口", notes = "保存线索规则接口")
    @ResponseBody
    @RequestMapping(value = "/RuleClue_Update", method = { RequestMethod.POST })
    public Result RuleClue_Update(@RequestBody BClueruleVo bClueruleVo){

        BCluerule bCluerule = bClueruleVo.getbCluerule();
        String IsUpdateGroup = bClueruleVo.getIsUpdateGroup();
        String grouplist = bClueruleVo.getGrouplist();

        if (bCluerule.getProtectSource() == 1)//自有渠道
        {
            if (bCluerule.getFollowUpOverdueDays() <= 3)
            {
                return Result.errormsg(0,"保护期需大于3天");
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
        if(bCluerule.getRuleType()!=null && bCluerule.getRuleType()==1)//竞争带看默认变成实时验证
        {
            bCluerule.setValidationMode(2);
        }

        if(IsUpdateGroup!=null && !"".equals(IsUpdateGroup)) {
            iBClueruleService.saveOrUpdate(bCluerule);
        }else{
            iBClueruleAdvisergroupService.RuleClue_Update(bCluerule,grouplist);
        }
        return Result.ok("成功");
    }
}
