package com.tahoecn.xkc.controller.webapi.sys;


import com.tahoecn.core.json.JSONResult;
import com.tahoecn.log.Log;
import com.tahoecn.log.LogFactory;
import com.tahoecn.xkc.common.constants.GlobalConstants;
import com.tahoecn.xkc.model.sys.SMenus;
import com.tahoecn.xkc.service.sys.ISMenusService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.tahoecn.xkc.controller.TahoeBaseController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YYY
 * @since 2019-06-17
 */
@RestController
@RequestMapping("/webapi/sys/sMenus")
public class SMenusController extends TahoeBaseController {
    private static final Log log = LogFactory.get();

    @Autowired
    private ISMenusService isMenusService;

    @ApiOperation(value = "sample menus", notes = "menus")
    @RequestMapping(value = "/menusList", method = {RequestMethod.GET})
    public JSONResult menusList() {
        List<SMenus> list = isMenusService.list();
        return markSuccess(list);
    }
}
