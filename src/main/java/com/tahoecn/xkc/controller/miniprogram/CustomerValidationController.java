package com.tahoecn.xkc.controller.miniprogram;

import com.tahoecn.core.json.JSONResult;
import com.tahoecn.log.Log;
import com.tahoecn.log.LogFactory;
import com.tahoecn.xkc.common.enums.TipsEnum;
import com.tahoecn.xkc.common.utils.ResultUtil;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.model.miniprogram.vo.UserRegisterVO;
import com.tahoecn.xkc.service.miniprogram.ICustomerValidationService;
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
 * 思为小程序客储用户中心验证类接口
 * </p>
 *
 * @author zhaoyan
 * @since 2020-04-01
 */
@Api(tags = "客储用户中心验证类接口", value = "客储用户中心验证类接口")
@RestController
@RequestMapping("/customerValidation")
public class CustomerValidationController extends TahoeBaseController {

    /**
     * 日志记录
     */
    private static Log logger = LogFactory.get(CustomerValidationController.class);

    @Resource
    private ICustomerValidationService customerValidationService;

    @ApiOperation(value = "获取登录信息", notes = "获取登录信息")
    @RequestMapping(value = "/getCustomerMessage", method = {RequestMethod.POST})
    public JSONResult getCustomerMessage(HttpServletRequest request, @RequestBody UserRegisterVO userRegisterVO) {
        try {
            return customerValidationService.getCustomerMessage(request, userRegisterVO);
        } catch (Exception e) {
            //记录错误日志
            e.printStackTrace();
            return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), e.getMessage());
        }
    }
}
