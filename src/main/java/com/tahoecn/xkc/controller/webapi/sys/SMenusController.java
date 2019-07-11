package com.tahoecn.xkc.controller.webapi.sys;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tahoecn.core.json.JSONResult;
import com.tahoecn.log.Log;
import com.tahoecn.log.LogFactory;
import com.tahoecn.xkc.common.constants.GlobalConstants;
import com.tahoecn.xkc.common.utils.ThreadLocalUtils;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.sys.SAccount;
import com.tahoecn.xkc.model.sys.SMenus;
import com.tahoecn.xkc.service.sys.ISMenusService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.tahoecn.xkc.controller.TahoeBaseController;

import java.util.*;

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
     * 数据字典 H5  不应在本类里
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
    public Result SystemMenusList_Select(int Pageindex,int Pagesize) {
        IPage page = new Page(Pageindex, Pagesize);
        List<Map<String,Object>> list=isMenusService.SystemMenusList_Select(page);
        return Result.ok(list);
    }

    @ApiOperation(value = "启用/禁用菜单", notes = "启用/禁用菜单")
    @RequestMapping(value = "/SystemMenuStatus_Update", method = {RequestMethod.POST})
    public Result SystemMenuStatus_Update(@RequestBody SMenus menus) {
        boolean b = isMenusService.updateById(menus);
        if (b) {
            return Result.okm("成功");
        }
        return Result.errormsg(99,"修改失败");
    }

    @ApiOperation(value = "新增菜单", notes = "新增菜单")
    @RequestMapping(value = "/SystemMenu_Insert", method = {RequestMethod.POST})
    public Result SystemMenu_Insert(@RequestBody SMenus menus) {
        menus.setId(UUID.randomUUID().toString());
        menus.setCreator(ThreadLocalUtils.getUserName());
        menus.setCreateTime(new Date());
        menus.setIsDel(0);
        try {
            isMenusService.SystemMenu_Insert(menus);
        } catch (Exception e) {
            e.printStackTrace();
            Result.errormsg(99,"新增菜单错误");
        }
        return Result.okm("成功");
    }

    @ApiOperation(value = "编辑菜单", notes = "编辑菜单")
    @RequestMapping(value = "/SystemMenu_Update", method = {RequestMethod.POST})
    public Result SystemMenu_Update(@RequestBody SMenus menus) {

        boolean b=isMenusService.SystemMenu_Update(menus);
        if (b){
            return Result.okm("成功");
        }
        return Result.errormsg(99,"修改失败");
    }

}
