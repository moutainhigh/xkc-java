package com.tahoecn.xkc.controller.webapi;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.ResponseMessage;
import com.tahoecn.xkc.model.rule.BSalescenterrule;
import com.tahoecn.xkc.service.rule.IBSalescenterruleService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/bSalescenterrule")
public class BSalescenterruleController extends TahoeBaseController {
    @Autowired
    private IBSalescenterruleService iBSalescenterruleService;

    @ApiImplicitParams({@ApiImplicitParam(name = "projectId", value = "项目Id", required = true, dataType = "String") })
    @ApiOperation(value = "案场保护期规则查询接口", notes = "案场保护期规则查询接口")
    @RequestMapping(value = "/SalesCenterRule_Select", method = { RequestMethod.GET })
    public ResponseMessage SalesCenterRule_Select(@RequestParam(required = true) String projectId) {
        QueryWrapper<BSalescenterrule> queryWrapper = new QueryWrapper<BSalescenterrule>();
        queryWrapper.eq(StringUtils.isNotBlank(projectId), "ProjectID", projectId);
        queryWrapper.eq("IsDel",0);
        queryWrapper.eq("Status",1);
        BSalescenterrule salescenterrule = (BSalescenterrule)iBSalescenterruleService.getObj(queryWrapper);
        return ResponseMessage.ok(salescenterrule);
    }
}
