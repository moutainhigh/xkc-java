package com.tahoecn.xkc.controller.app;

import com.tahoecn.xkc.controller.TahoeBaseController;

import io.swagger.annotations.Api;

import org.springframework.web.bind.annotation.RequestMapping;
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
@Api(tags = "APP-用户接口", value = "APP-用户接口")
@RequestMapping("/app/user")
public class AppUserController extends TahoeBaseController {

}
