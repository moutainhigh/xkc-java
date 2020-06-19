package com.tahoecn.xkc.controller.risk;


import com.tahoecn.core.date.DateUtil;
import com.tahoecn.core.json.JSONResult;
import com.tahoecn.xkc.common.enums.TipsEnum;
import com.tahoecn.xkc.common.utils.ResultUtil;
import com.tahoecn.xkc.model.risk.vo.TiskInfoFindVO;
import com.tahoecn.xkc.model.risk.vo.TiskTreeFindVO;
import com.tahoecn.xkc.service.risk.IBRiskinfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * @description: 风控痛点明细
 * @author: 张晓东
 * @time: 2020/5/21 9:18
 */
@Api(tags = "风控痛点明细")
@RestController
@RequestMapping("/bRiskinfo")
public class BRiskinfoController {

    @Autowired
    private IBRiskinfoService service;

    /**
     * @description: 风控痛点树查询
     * @author: 张晓东
     * @time: 2020/5/6 17:17
     */
    @ApiOperation(value = "风控痛点树查询", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vo", value = "风控痛点树查询对象",
                    paramType = "query", required = true, dataType = "TiskTreeFindVO")
    })
    @PostMapping(value = "/listTree")
    public JSONResult listTree(@RequestBody TiskTreeFindVO vo) {
        try {
            return ResultUtil.setJsonResult(TipsEnum.Success.getCode(), this.service.listTree(vo));
        } catch (Exception e) {
            //记录错误日志
            e.printStackTrace();
            return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), e.getMessage());
        }
    }


    /**
     * @description: 风控痛点树导出
     * @author: 张晓东
     * @time: 2020/5/6 17:17
     */
    @ApiOperation(value = "风控痛点树导出", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "regionalId", value = "区域主键",
                    paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "cityId", value = "城市主键",
                    paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "projectId", value = "项目主键",
                    paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间",
                    paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间",
                    paramType = "query", required = true, dataType = "String")
    })
    @GetMapping(value = "/listTreeExport")
    public JSONResult listTreeExport(HttpServletResponse response, String regionalId, String cityId,
                                     String projectId, Date startTime, Date endTime) {
        try {
            if (null != startTime) startTime = DateUtil.parseDateTime(DateUtil.formatDate(startTime) + " 00:00:00");
            if (null != endTime) endTime = DateUtil.parseDateTime(DateUtil.formatDate(endTime) + " 23:59:59");
            this.service.listTreeExport(response, regionalId, cityId, projectId, startTime, endTime);
            return ResultUtil.setJsonResult(TipsEnum.Success.getCode());
        } catch (Exception e) {
            //记录错误日志
            e.printStackTrace();
            return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), e.getMessage());
        }
    }

    /**
     * @description: 风控痛点列表查询
     * @author: 张晓东
     * @time: 2020/5/6 17:17
     */
    @ApiOperation(value = "风控痛点列表查询", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vo", value = "vo",
                    paramType = "query", required = false, dataType = "TiskInfoFindVO")
    })
    @PostMapping(value = "/list")
    public JSONResult list(@Valid @RequestBody TiskInfoFindVO vo, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), bindingResult.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)));
            }
            return ResultUtil.setJsonResult(TipsEnum.Success.getCode(), this.service.list(vo));
        } catch (Exception e) {
            //记录错误日志
            e.printStackTrace();
            return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), e.getMessage());
        }
    }

    /**
     * @description: 风控痛点列表导出
     * @author: 张晓东
     * @time: 2020/5/6 17:17
     */
    @ApiOperation(value = "风控痛点列表导出", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名",
                    paramType = "query", required = false, dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "统计时间",
                    paramType = "query", required = false, dataType = "Date"),
            @ApiImplicitParam(name = "endTime", value = "统计时间",
                    paramType = "query", required = false, dataType = "Date"),
            @ApiImplicitParam(name = "saleUserName", value = "置业顾问",
                    paramType = "query", required = false, dataType = "String"),
            @ApiImplicitParam(name = "customerMobile", value = "手机号",
                    paramType = "query", required = false, dataType = "String"),
            @ApiImplicitParam(name = "adviserGroupID", value = "相关渠道",
                    paramType = "query", required = false, dataType = "String"),
            @ApiImplicitParam(name = "riskType", value = "风险类别",
                    paramType = "query", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "reportUserName", value = "经纪人",
                    paramType = "query", required = false, dataType = "String"),
            @ApiImplicitParam(name = "customerStatus", value = "客户状态",
                    paramType = "query", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "regionalId", value = "区域主键",
                    paramType = "query", required = false, dataType = "String"),
            @ApiImplicitParam(name = "cityId", value = "城市主键",
                    paramType = "query", required = false, dataType = "String"),
            @ApiImplicitParam(name = "projectId", value = "项目主键",
                    paramType = "query", required = false, dataType = "String")
    })
    @GetMapping(value = "/listExport")
    public JSONResult listExport(HttpServletResponse response, String userName, Date startTime, Date endTime, Integer riskType,
                                 String saleUserName, String customerMobile, String adviserGroupID, String reportUserName,
                                 Integer customerStatus, String regionalId, String cityId, String projectId) {
        try {
            this.service.listExport(new TiskInfoFindVO() {{
                setUserName(userName);
                setStartTime(startTime);
                setEndTime(endTime);
                setRiskType(riskType);
                setSaleUserName(saleUserName);
                setCustomerMobile(customerMobile);
                setAdviserGroupID(adviserGroupID);
                setReportUserName(reportUserName);
                setCustomerStatus(customerStatus);
                setRegionalId(regionalId);
                setCityId(cityId);
                setProjectId(projectId);
            }}, response);
            return ResultUtil.setJsonResult(TipsEnum.Success.getCode());
        } catch (Exception e) {
            //记录错误日志
            e.printStackTrace();
            return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), e.getMessage());
        }
    }

    /**
     * @description: 风控痛点明细查询
     * @author: 张晓东
     * @time: 2020/5/6 17:17
     */
    @ApiOperation(value = "风控痛点明细查询", httpMethod = "GET")
    @GetMapping(value = "/info/{id}")
    public JSONResult info(@PathVariable("id") String id) {
        try {
            if (StringUtils.isEmpty(id)) return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), "id不能为空");
            return this.service.info(id);
        } catch (Exception e) {
            //记录错误日志
            e.printStackTrace();
            return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), e.getMessage());
        }
    }


}
