package com.tahoecn.xkc.controller.webapi.sys;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import com.tahoecn.xkc.model.sys.SMenusXkc;
import com.tahoecn.xkc.service.sys.ISMenusService;
import com.tahoecn.xkc.service.sys.ISMenusXkcService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
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
    private ISMenusXkcService isMenusService;

//    @ApiOperation(value = "sample menus", notes = "menus")
//    @RequestMapping(value = "/menusList", method = {RequestMethod.POST})
//    public Result menusList() {
//        List<SMenus> list = isMenusService.list();
//        return Result.ok(list);
//    }



    @ApiOperation(value = "获取所有菜单信息", notes = "获取所有菜单信息")
    @RequestMapping(value = "/SystemMenusList_Select", method = {RequestMethod.POST})
    public Result SystemMenusList_Select() {
        List<Map<String,Object>> list=isMenusService.SystemMenusList_Select();

        ArrayList<Map<String,Object>> resultList=new ArrayList();
        ArrayList<Map<String,Object>> resultListNew=new ArrayList();

        for (Map<String, Object> record : list) {
            if (StringUtils.equals((String)record.get("PID"),"-1")){
                resultList.add(record);
            }
        }
        for (Map<String, Object> map : resultList) {//最外循环
            List<Map<String,Object>> temp=new ArrayList<>();
            for (Map<String, Object> stringObjectMap : list) {//全部循环
                if (StringUtils.equals((String)map.get("ID"),(String)stringObjectMap.get("PID"))){
                    stringObjectMap.put("pText",map.get("MenuName"));
                    temp.add(stringObjectMap);
                }
            }
            map.put("children",temp);
            resultListNew.add(map);
        }
        return Result.ok(resultListNew);
    }

    @ApiOperation(value = "启用/禁用菜单", notes = "启用/禁用菜单")
    @RequestMapping(value = "/SystemMenuStatus_Update", method = {RequestMethod.POST})
    public Result SystemMenuStatus_Update(@RequestBody SMenusXkc menus) {
        boolean b = isMenusService.updateById(menus);
        if (b) {
            return Result.okm("成功");
        }
        return Result.errormsg(99,"修改失败");
    }

    @ApiOperation(value = "新增菜单", notes = "新增菜单")
    @RequestMapping(value = "/SystemMenu_Insert", method = {RequestMethod.POST})
    public Result SystemMenu_Insert(@RequestBody SMenusXkc menus) {
        menus.setId(UUID.randomUUID().toString());
        menus.setCreator(ThreadLocalUtils.getUserName());
        menus.setCreateTime(new Date());
        menus.setIsDel(0);
        //判断层级
        if (!"-1".equals(menus.getPid())){
            SMenusXkc byId = isMenusService.getById(menus.getPid());
            menus.setLevels(byId.getLevels()+1);
        }else {
            menus.setLevels(1);
        }
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
    public Result SystemMenu_Update(@RequestBody SMenusXkc menus) {

        Result result=isMenusService.SystemMenu_Update(menus);

        return result;
    }

}
