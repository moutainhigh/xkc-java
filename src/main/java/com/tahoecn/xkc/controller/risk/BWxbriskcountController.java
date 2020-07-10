package com.tahoecn.xkc.controller.risk;


import com.tahoecn.core.json.JSONResult;
import com.tahoecn.xkc.common.enums.TipsEnum;
import com.tahoecn.xkc.common.utils.ResultUtil;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.model.risk.vo.WxbRiskInfoPageVO;
import com.tahoecn.xkc.model.risk.vo.WxbRiskStatisticalPageVO;
import com.tahoecn.xkc.service.risk.IBWxbriskcountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器 旺小宝风控报表
 * </p>
 *
 * @author YYY
 * @since 2020-06-17
 */

@RestController
@RequestMapping("/bWxbriskcount")
@Api(tags = "旺小宝风控报表")
public class BWxbriskcountController extends TahoeBaseController {

    @Autowired
    private IBWxbriskcountService service;

    @ApiOperation(value = "旺小宝-风控-统计报表", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vo", value = "旺小宝数据传入数据类",
                    paramType = "query", required = true, dataType = "WxbRiskStatisticalPageVO")
    })
    @PostMapping(value = "/statistical")
    public JSONResult record(@Valid @RequestBody WxbRiskStatisticalPageVO vo, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors())
                return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), bindingResult.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)));

            return ResultUtil.setJsonResult(TipsEnum.Success.getCode(), this.service.statistical(vo));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), e.getMessage());
        }
    }

    @ApiOperation(value = "旺小宝-风控-统计报表明细", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vo", value = "旺小宝数据传入数据类",
                    paramType = "query", required = true, dataType = "WxbRiskInfoPageVO")
    })
    @PostMapping(value = "/list")
    public JSONResult list(@Valid @RequestBody WxbRiskInfoPageVO vo, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors())
                return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), bindingResult.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)));
            return ResultUtil.setJsonResult(TipsEnum.Success.getCode(), this.service.list(vo));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), e.getMessage());
        }
    }


    @ApiOperation(value = "旺小宝-风控-label查询", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vo", value = "旺小宝-风控-label查询",
                    paramType = "query", required = true, dataType = "WxbRiskInfoPageVO")
    })
    @PostMapping(value = "/label")
    public JSONResult label(@Valid @RequestBody WxbRiskInfoPageVO vo, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors())
                return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), bindingResult.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)));
            Map label = this.service.label(vo);
            label.remove("wrapper");
            return ResultUtil.setJsonResult(TipsEnum.Success.getCode(), label);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), e.getMessage());
        }
    }

    @ApiOperation(value = "旺小宝-风控-导出", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vo", value = "旺小宝-风控-导出",
                    paramType = "query", required = true, dataType = "WxbRiskInfoPageVO")
    })
    @GetMapping(value = "/export")
    public JSONResult export(HttpServletResponse response, WxbRiskInfoPageVO vo) {
        try {
            this.service.export(vo, response);
            return ResultUtil.setJsonResult(TipsEnum.Success.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), e.getMessage());
        }
    }
}
