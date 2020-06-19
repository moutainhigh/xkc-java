package com.tahoecn.xkc.controller.risk;


import com.tahoecn.core.json.JSONResult;
import com.tahoecn.xkc.common.enums.TipsEnum;
import com.tahoecn.xkc.common.utils.ResultUtil;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.model.risk.vo.RiskConfigProjectVO;
import com.tahoecn.xkc.model.risk.vo.RiskConfigVo;
import com.tahoecn.xkc.service.risk.IBRiskconfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.stream.Collectors;

/**
 * @description: 风控规则配置
 * @author: 张晓东
 * @time: 2020/5/6 16:47
 */
@Api(tags = "风控规则配置")
@RestController
@RequestMapping("/bRiskconfig")
public class BRiskconfigController extends TahoeBaseController {

    @Resource
    private IBRiskconfigService service;

    /**
     * @param type      类型:0 集团 1 项目
     * @param projectId 项目id
     * @description: 风控规则配置查询
     * @author: 张晓东
     * @time: 2020/5/6 17:17
     */
    @ApiOperation(value = "风控规则配置查询", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型:0 集团 1 项目",
                    paramType = "query", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "projectId", value = "项目id",
                    paramType = "query", required = true, dataType = "String")
    })
    @GetMapping(value = "/search")
    public JSONResult search(HttpServletRequest request, Integer type, String projectId) {
        try {
            if (null == type) {
                return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), "type不能为空");
            }
            if (type == 1 && null == projectId) {
                return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), "projectId不能为空");
            }
            return this.service.search(request, type, projectId);
        } catch (Exception e) {
            //记录错误日志
            e.printStackTrace();
            return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), e.getMessage());
        }
    }

    /**
     * @description: 风控规则配置修改
     * @return:
     * @author: 张晓东
     * @time: 2020/5/6 18:46
     */
    @ApiOperation(value = "风控规则配置修改", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vo", value = "风控配置替代VO",
                    paramType = "query", required = true, dataType = "RiskConfigVo")
    })
    @PostMapping(value = "/replace")
    public JSONResult replace(HttpServletRequest request, @Valid @RequestBody RiskConfigVo vo, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), bindingResult.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)));
            }
            return this.service.replace(request, vo);
        } catch (Exception e) {
            //记录错误日志
            e.printStackTrace();
            return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), e.getMessage());
        }
    }

    /**
     * @description: 集团下达风控规则配置到项目
     * @return:
     * @author: 张晓东
     * @time: 2020/5/6 18:46
     */
    @ApiOperation(value = "集团下达风控规则配置到项目", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "flag", value = "验证规则true",
                    paramType = "query", required = true, dataType = "Boolean")
    })
    @GetMapping(value = "/release")
    public JSONResult release(HttpServletRequest request, Boolean flag) {
        try {
            if (null == flag || flag == false) {
                return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), "flag不能为空并且必须为true");
            }
            return this.service.release(request);
        } catch (Exception e) {
            //记录错误日志
            e.printStackTrace();
            return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), e.getMessage());
        }
    }

    /**
     * @description: 风控配置项目列表
     * @return:
     * @author: 张晓东
     * @time: 2020/6/6 11:25
     */
    @ApiOperation(value = "风控配置项目列表", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vo", value = "风控项目列表配置查询vo",
                    paramType = "query", required = true, dataType = "RiskConfigProjectVO")
    })
    @PostMapping(value = "/projectList")
    public JSONResult projectList(HttpServletRequest request, @RequestBody RiskConfigProjectVO vo) {
        try {
            return this.service.projectList(request, vo);
        } catch (Exception e) {
            //记录错误日志
            e.printStackTrace();
            return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), e.getMessage());
        }
    }

}
