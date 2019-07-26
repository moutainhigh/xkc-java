package com.tahoecn.xkc.controller.webapi;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.dto.ParameterDto;
import com.tahoecn.xkc.model.project.BProject;
import com.tahoecn.xkc.model.rule.BRemindrule;
import com.tahoecn.xkc.model.rule.BSalescenterrule;
import com.tahoecn.xkc.service.project.IBProjectService;
import com.tahoecn.xkc.service.rule.IBRemindruleService;
import com.tahoecn.xkc.service.rule.IBSalescenterruleService;
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
@RequestMapping("/webapi/parameter")
public class ParameterDetailController extends TahoeBaseController {
    @Autowired
    private IBProjectService iBProjectService;

    @Autowired
    private IBSalescenterruleService iBSalescenterruleService;

    @Autowired
    private IBRemindruleService iBRemindruleService;

    @ApiImplicitParams({@ApiImplicitParam(name = "projectId", value = "项目Id", required = true, dataType = "String") })
    @ApiOperation(value = "项目参数设置接口", notes = "项目参数设置接口")
    @RequestMapping(value = "/parameterSelect", method = { RequestMethod.GET })
    public Result parameterSelect(@RequestParam(required = true) String projectId) {

        Map<String,Object> result = new HashMap<String,Object>();

        QueryWrapper<BProject> queryWrapper = new QueryWrapper<BProject>();
        queryWrapper.eq(StringUtils.isNotBlank(projectId), "ID", projectId);
        BProject project = (BProject)iBProjectService.getObj(queryWrapper);

        result.put("project",project);
        //案场保护期规则查询
        QueryWrapper<BSalescenterrule> salesCenterRuleWrapper = new QueryWrapper<BSalescenterrule>();
        salesCenterRuleWrapper.eq(StringUtils.isNotBlank(projectId), "ProjectID", projectId);
        salesCenterRuleWrapper.eq("IsDel",0);
        salesCenterRuleWrapper.eq("Status",1);
        BSalescenterrule salesCenterRule = (BSalescenterrule)iBSalescenterruleService.getObj(salesCenterRuleWrapper);
        result.put("salesCenterRule",salesCenterRule);

        //案场消息提醒
        QueryWrapper<BRemindrule> remindRuleWrapper = new QueryWrapper<BRemindrule>();
        remindRuleWrapper.eq(StringUtils.isNotBlank(projectId), "ProjectID", projectId);
        remindRuleWrapper.eq("IsDel",0);
        remindRuleWrapper.eq("Status",1);
        remindRuleWrapper.isNotNull("VersionEndTime");
        remindRuleWrapper.orderByDesc("VersionStartTime");
        List<BRemindrule> remindRuleList = iBRemindruleService.list(remindRuleWrapper);
        result.put("remindRuleList",remindRuleList);

        return Result.ok(result);
    }

    @ApiOperation(value = "项目参数修改接口", notes = "项目参数修改接口")
    @ResponseBody
    @RequestMapping(value = "/parameterUpdate", method = { RequestMethod.POST })
    public Result parameterSelect(@RequestBody ParameterDto parameterDto) {

        String UserId = parameterDto.getUserId();

        BProject bProject = parameterDto.getbProject();

        BRemindrule bRemindrule = parameterDto.getbRemindrule();

        BSalescenterrule bSalescenterrule = parameterDto.getbSalescenterrule();

        iBProjectService.updateById(bProject);

        //案场保护期规则更新
        UpdateWrapper<BSalescenterrule> saleCenterRuleUpdateWrapper = new UpdateWrapper<>();
        BSalescenterrule updateCenterRule = new BSalescenterrule();
        updateCenterRule.setStatus(0);
        updateCenterRule.setEditor(UserId);
        updateCenterRule.setEditTime(new Date());
        saleCenterRuleUpdateWrapper.eq("ProjectID",bSalescenterrule.getProjectID());
        iBSalescenterruleService.update(updateCenterRule,saleCenterRuleUpdateWrapper);

        //案场保护期规则新增
        bSalescenterrule.setIsDel(0);
        bSalescenterrule.setStatus(1);
        bSalescenterrule.setEditTime(new Date());
        bSalescenterrule.setEditor(UserId);
        bSalescenterrule.setCreator(UserId);
        bSalescenterrule.setCreateTime(new Date());
        iBSalescenterruleService.save(bSalescenterrule);

        //案场消息提醒
        UpdateWrapper<BRemindrule> remindruleUpdateWrapper = new UpdateWrapper<>();
        remindruleUpdateWrapper.eq("ProjectID",bRemindrule.getProjectID());
        remindruleUpdateWrapper.isNull("VersionEndTime");
        iBRemindruleService.update(bRemindrule,remindruleUpdateWrapper);
        bRemindrule.setRuleStartTime(new Date());
        bRemindrule.setCreator(UserId);
        bRemindrule.setVersionStartTime(new Date());
        iBRemindruleService.save(bRemindrule);
        return Result.ok("更新成功");
    }

}
