package com.tahoecn.xkc.controller.miniprogram;


import com.tahoecn.core.json.JSONResult;
import com.tahoecn.log.Log;
import com.tahoecn.log.LogFactory;
import com.tahoecn.xkc.common.enums.TipsEnum;
import com.tahoecn.xkc.common.utils.ResultUtil;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.model.miniprogram.vo.RelationshipVO;
import com.tahoecn.xkc.service.miniprogram.IRelationshipService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 思为小程序项目-人员-组织关系类
 * </p>
 *
 * @author zhaoyan
 * @since 2020-04-01
 */
@Api(tags = "项目人员组织关系", value = "项目人员组织关系")
@RestController
@RequestMapping("/relationship")
public class RelationshipController extends TahoeBaseController {

    /**
     * 日志记录
     */
    private static Log logger = LogFactory.get(RelationshipController.class);

    @Resource
    private IRelationshipService relationshipService;

    @ApiOperation(value = "获取组织角色", notes = "获取组织角色")
    @RequestMapping(value = "/getOrgRole", method = {RequestMethod.POST})
    public JSONResult getOrgRole(HttpServletRequest request, @RequestBody RelationshipVO relationshipVO) {
        try {
            return relationshipService.getOrgRole(request, relationshipVO);
        } catch (Exception e) {
            //记录错误日志
            e.printStackTrace();
            return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), e.getMessage());
        }
    }

    @ApiOperation(value = "获取人员项目", notes = "获取人员项目")
    @RequestMapping(value = "/getProjectPerson", method = {RequestMethod.POST})
    public JSONResult getProjectPerson(HttpServletRequest request, @RequestBody RelationshipVO relationshipVO) {
        try {
            return relationshipService.getProjectPerson(request, relationshipVO);
        } catch (Exception e) {
            //记录错误日志
            e.printStackTrace();
            return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), e.getMessage());
        }
    }

    @ApiOperation(value = "获取组织人员", notes = "获取组织人员")
    @RequestMapping(value = "/getOrgPerson", method = {RequestMethod.POST})
    public JSONResult getOrgPerson(HttpServletRequest request) {
        try {
            return relationshipService.getOrgPerson(request);
        } catch (Exception e) {
            //记录错误日志
            e.printStackTrace();
            return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), e.getMessage());
        }
    }

    @ApiOperation(value = "获取项目", notes = "获取项目")
    @RequestMapping(value = "/getProject", method = {RequestMethod.POST})
    public JSONResult getProject(HttpServletRequest request) {
        try {
            return relationshipService.getProject(request);
        } catch (Exception e) {
            //记录错误日志
            e.printStackTrace();
            return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), e.getMessage());
        }
    }

    @ApiOperation(value = "获取人员", notes = "获取人员")
    @RequestMapping(value = "/getPerson", method = {RequestMethod.POST})
    public JSONResult getPerson(HttpServletRequest request, @RequestBody RelationshipVO relationshipVO) {
        try {
            return relationshipService.getPerson(request, relationshipVO);
        } catch (Exception e) {
            //记录错误日志
            e.printStackTrace();
            return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), e.getMessage());
        }
    }

    @ApiOperation(value = "获取角色", notes = "获取角色")
    @RequestMapping(value = "/getRole", method = {RequestMethod.POST})
    public JSONResult getRole(HttpServletRequest request, @RequestBody RelationshipVO relationshipVO) {
        try {
            return relationshipService.getRole(request, relationshipVO);
        } catch (Exception e) {
            //记录错误日志
            e.printStackTrace();
            return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), e.getMessage());
        }
    }

    @ApiOperation(value = "获取客户", notes = "获取客户")
    @RequestMapping(value = "/getCustomer", method = {RequestMethod.POST})
    public JSONResult getCustomer(HttpServletRequest request, @RequestBody RelationshipVO relationshipVO) {
        try {
            return relationshipService.getCustomer(request, relationshipVO);
        } catch (Exception e) {
            //记录错误日志
            e.printStackTrace();
            return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), e.getMessage());
        }
    }

    @ApiOperation(value = "获取中介机构信息", notes = "获取中介机构信息")
    @RequestMapping(value = "/getIntermediaryAgency", method = {RequestMethod.POST})
    public JSONResult getIntermediaryAgency(HttpServletRequest request) {
        try {
            return relationshipService.getIntermediaryAgency(request);
        } catch (Exception e) {
            //记录错误日志
            e.printStackTrace();
            return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), e.getMessage());
        }
    }
}
