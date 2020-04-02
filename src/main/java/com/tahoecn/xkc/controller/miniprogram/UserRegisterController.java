package com.tahoecn.xkc.controller.miniprogram;

import com.tahoecn.core.json.JSONResult;
import com.tahoecn.log.Log;
import com.tahoecn.log.LogFactory;
import com.tahoecn.xkc.common.enums.TipsEnum;
import com.tahoecn.xkc.common.utils.ResultUtil;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.model.miniprogram.vo.UserRegisterVO;
import com.tahoecn.xkc.service.miniprogram.IUserRegisterService;
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
 * 思为小程序外渠角色注册接口
 * </p>
 *
 * @author zhaoyan
 * @since 2020-03-31
 */
@Api(tags = "外渠角色注册接口", value = "外渠角色注册接口")
@RestController
@RequestMapping("/userRegister")
public class UserRegisterController extends TahoeBaseController {

    /**
     * 日志记录
     */
    private static Log logger = LogFactory.get(UserRegisterController.class);

    @Resource
    private IUserRegisterService userRegisterService;

    @ApiOperation(value = "外渠角色注册接口", notes = "外渠角色注册接口")
    @RequestMapping(value = "/userRegister", method = {RequestMethod.POST})
    public JSONResult userRegister(HttpServletRequest request, @RequestBody UserRegisterVO userRegisterVO) {
        try {
            return userRegisterService.userRegister(request, userRegisterVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), e.getMessage());
        }
    }
}
