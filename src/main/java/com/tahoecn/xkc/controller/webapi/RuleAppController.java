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
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
            @ApiImplicitParam(name = "protectSource", value = "保护来源:0.推荐 1.自有 2.分销", required = true, dataType = "Integer")})
    @ApiOperation(value = "获取项目信息接口", notes = "获取项目信息接口")
    @RequestMapping(value = "/RuleClue_Select", method = { RequestMethod.GET })
    public ResponseMessage RuleClue_Select(@RequestParam(required = true) String projectId,@RequestParam(required = true) Integer protectSource) {
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

}
