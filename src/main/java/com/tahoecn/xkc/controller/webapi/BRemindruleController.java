package com.tahoecn.xkc.controller.webapi;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.ResponseMessage;
import com.tahoecn.xkc.model.rule.BRemindrule;
import com.tahoecn.xkc.service.rule.IBRemindruleService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/bRemindrule")
public class BRemindruleController extends TahoeBaseController {
    @Autowired
    private IBRemindruleService iBRemindruleService;

    @ApiImplicitParams(
            {@ApiImplicitParam(name = "projectId", value = "项目Id", required = true, dataType = "String")})
    @ApiOperation(value = "案场消息提醒接口", notes = "案场消息提醒接口")
    @RequestMapping(value = "/RuleRemind_Select", method = { RequestMethod.GET })
    public ResponseMessage RuleRemind_Select(@RequestParam(required = true) String projectId) {
        /*
        SELECT TOP 1  *
                FROM B_RemindRule
        WHERE IsDel=0 AND Status=1 AND VersionEndTime IS NULL AND ProjectID='{ProjectID}'
        ORDER BY VersionStartTime  DESC
        */
        QueryWrapper<BRemindrule> queryWrapper = new QueryWrapper<BRemindrule>();
        queryWrapper.eq(StringUtils.isNotBlank(projectId), "ProjectID", projectId);
        queryWrapper.eq("IsDel",0);
        queryWrapper.eq("Status",1);
        queryWrapper.isNotNull("VersionEndTime");
        queryWrapper.orderByDesc("VersionStartTime");
        List<BRemindrule> remindruleList = iBRemindruleService.list(queryWrapper);
        if(remindruleList!=null && remindruleList.size()>0) {
            return ResponseMessage.ok(remindruleList.get(0));
        }
        return ResponseMessage.ok();
    }
}
