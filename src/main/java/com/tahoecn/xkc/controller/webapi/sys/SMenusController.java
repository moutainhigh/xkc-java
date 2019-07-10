package com.tahoecn.xkc.controller.webapi.sys;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tahoecn.core.json.JSONResult;
import com.tahoecn.log.Log;
import com.tahoecn.log.LogFactory;
import com.tahoecn.xkc.common.constants.GlobalConstants;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.sys.SMenus;
import com.tahoecn.xkc.service.sys.ISMenusService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.tahoecn.xkc.controller.TahoeBaseController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @RequestMapping(value = "/menusList", method = {RequestMethod.POST})
    public Result menusList() {
        List<SMenus> list = isMenusService.list();
        return Result.ok(list);
    }

    /**
     * 数据字典
     */
    @ApiOperation(value = "数据字典", notes = "数据字典")
    @RequestMapping(value = "/mSystemDictionaryDetail_Select", method = {RequestMethod.POST})
    public Result mSystemDictionaryDetail_Select(@RequestBody JSONObject jsonParam) {
        HashMap<String,Object> param=(HashMap)jsonParam.get("_param");
        Result result=isMenusService.SystemDictionaryDetail(param);
        return result;
    }

    @ApiOperation(value = "获取所有菜单信息", notes = "获取所有菜单信息")
    @RequestMapping(value = "/SystemMenusList_Select", method = {RequestMethod.POST})
    public Result SystemMenusList_Select(@RequestBody JSONObject jsonParam) {
        Map map = (HashMap)jsonParam.get("_param");
        int PageIndex=(int) map.get("Pageindex");
        int PageSize=(int) map.get("Pagesize");
        IPage page = new Page(PageIndex, PageSize);
        List<Map<String,Object>> list=isMenusService.SystemMenusList_Select(page);
        return Result.ok(list);
    }

}
