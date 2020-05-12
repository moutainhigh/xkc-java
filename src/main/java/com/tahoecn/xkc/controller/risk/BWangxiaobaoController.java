package com.tahoecn.xkc.controller.risk;

import com.tahoecn.core.json.JSONResult;
import com.tahoecn.xkc.common.enums.TipsEnum;
import com.tahoecn.xkc.common.utils.ResultUtil;
import com.tahoecn.xkc.model.risk.vo.WangXiaobaoVO;
import com.tahoecn.xkc.service.risk.IBWangxiaobaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.stream.Collectors;

/**
 * @description: 旺小宝[人脸识别]刷证数据对接
 * @author: 张晓东
 * @time: 2020/5/11 15:02
 */
@Api(tags = "旺小宝")
@RestController
@RequestMapping("/wxb")
public class BWangxiaobaoController {

    @Autowired
    private IBWangxiaobaoService service;


    @ApiOperation(value = "旺小宝[人脸识别]刷证数据", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vo", value = "旺小宝数据传入数据类",
                    paramType = "query", required = true, dataType = "WangXiaobaoVO")
    })
    @PostMapping(value = "/record")
    public JSONResult record(HttpServletRequest request, @Valid @RequestBody WangXiaobaoVO vo, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), bindingResult.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)));
            }
            return this.service.record(request, vo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), e.getMessage());
        }
    }

}
