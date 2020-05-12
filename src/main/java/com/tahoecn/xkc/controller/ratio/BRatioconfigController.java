package com.tahoecn.xkc.controller.ratio;


import com.tahoecn.core.json.JSONResult;
import com.tahoecn.xkc.common.enums.TipsEnum;
import com.tahoecn.xkc.common.utils.ResultUtil;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.model.ratio.vo.RatioconfigVO;
import com.tahoecn.xkc.service.ratio.IBRatioconfigService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 * 渠道占比配置
 *
 * @author YYY
 * @since 2020-05-12
 */
@RestController
@RequestMapping("/bRatioconfig")
public class BRatioconfigController extends TahoeBaseController {

    @Autowired
    private IBRatioconfigService service;

    /**
     * @description: 风控规则配置更新, id存在修改, id不存在新增
     * @return:
     * @author: 张晓东
     * @time: 2020/5/6 18:46
     */
    @ApiOperation(value = "渠道占比配置更新", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vo", value = "渠道占比配置参数",
                    paramType = "query", required = true, dataType = "RatioconfigVO")
    })
    @PostMapping(value = "/replace")
    public JSONResult replace(HttpServletRequest request, @Valid @RequestBody RatioconfigVO vo, BindingResult bindingResult) {
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
     * @description: 风控规则配置主配置查询
     * @return:
     * @author: 张晓东
     * @time: 2020/5/6 18:46
     */
    @ApiOperation(value = "渠道占比配置查询", httpMethod = "GET")
    @GetMapping(value = "/listConfig")
    public JSONResult listConfig(HttpServletRequest request) {
        try {
            return this.service.listConfig();
        } catch (Exception e) {
            //记录错误日志
            e.printStackTrace();
            return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), e.getMessage());
        }
    }


    /**
     * @description: 删除风控规则配置
     * @return:
     * @author: 张晓东
     * @time: 2020/5/12 18:12
     */
    @ApiOperation(value = "删除风控规则配置", httpMethod = "GET")
    @GetMapping(value = "/remove/{id}")
    public JSONResult remove(HttpServletRequest request, @PathVariable("id") String id) {
        try {
            if (StringUtils.isEmpty(id)) ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), "id不能为空");
            return this.service.iRemove(request, id);
        } catch (Exception e) {
            //记录错误日志
            e.printStackTrace();
            return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), e.getMessage());
        }
    }


    /**
     * @param all true为全部禁用
     * @param id  当all为false是, id必传
     * @description: 禁用风控规则配置
     * @return:
     * @author: 张晓东
     * @time: 2020/5/12 18:12
     */
    @ApiOperation(value = "禁用风控规则配置", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "all", value = "全部禁用?",
                    paramType = "query", required = true, dataType = "Boolean"),
            @ApiImplicitParam(name = "id", value = "项目id",
                    paramType = "query", required = true, dataType = "String")
    })
    @GetMapping(value = "/disable")
    public JSONResult disable(HttpServletRequest request, Boolean all, String id) {
        try {
            if (null == all) ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), "all不能为空");
            if (!all && StringUtils.isEmpty(id)) ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), "id不能为空");
            return this.service.disable(request, all, id);
        } catch (Exception e) {
            //记录错误日志
            e.printStackTrace();
            return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), e.getMessage());
        }
    }

    /**
     * @description: 下发风控规则配置
     * @return:
     * @author: 张晓东
     * @time: 2020/5/12 19:53
     */
    @ApiOperation(value = "下发风控规则配置", httpMethod = "GET")
    @GetMapping(value = "/command")
    public JSONResult command(HttpServletRequest request) {
        try {
            return this.service.command(request);
        } catch (Exception e) {
            //记录错误日志
            e.printStackTrace();
            return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), e.getMessage());
        }
    }


    @ApiOperation(value = "启用风控规则配置", httpMethod = "GET")
    @GetMapping(value = "/enable/{id}")
    public JSONResult enable(HttpServletRequest request, @PathVariable("id") String id) {
        try {
            if (StringUtils.isEmpty(id)) return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), "id不能为空");
            return this.service.enable(request, id);
        } catch (Exception e) {
            //记录错误日志
            e.printStackTrace();
            return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), e.getMessage());
        }
    }

}
