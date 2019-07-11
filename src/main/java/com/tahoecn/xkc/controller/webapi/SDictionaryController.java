package com.tahoecn.xkc.controller.webapi;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tahoecn.xkc.common.utils.ThreadLocalUtils;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.dict.SDictionary;
import com.tahoecn.xkc.model.sys.SCommonjobs;
import com.tahoecn.xkc.service.dict.ISDictionaryService;
import com.tahoecn.xkc.service.sys.ISCommonjobsService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YYY
 * @since 2019-06-26
 */
@RestController
@RequestMapping("/sDictionary")
public class SDictionaryController extends TahoeBaseController {
    @Autowired
    private ISDictionaryService dictionaryService;

    @ApiOperation(value = "获取所有参数", notes = "获取所有参数")
    @RequestMapping(value = "/SystemAllParams_Select", method = {RequestMethod.POST})
    public Result SystemAllParams_Select(String AuthCompanyID,String ProductID,int Pageindex,int Pagesize){
        IPage page=new Page(Pageindex,Pagesize);
        QueryWrapper<SDictionary> wrapper=new QueryWrapper<>();
        wrapper.eq("IsDel",0).eq("AuthCompanyID",AuthCompanyID).eq("ProductID",ProductID);
        wrapper.lt("Levels",3);
        wrapper.orderByAsc("Levels","ListIndex");
        IPage page1 = dictionaryService.page(page, wrapper);
        return Result.ok(page1);
    }

    @ApiOperation(value = "启用/禁用参数", notes = "启用/禁用参数")
    @RequestMapping(value = "/SystemParamStatus_Update", method = {RequestMethod.POST})
    public Result SystemParamStatus_Update(String ID,int Status){
        SDictionary dictionary=new SDictionary();
        dictionary.setId(ID);
        dictionary.setStatus(Status);
        boolean b = dictionaryService.updateById(dictionary);
        if (b){
            return Result.okm("成功");
        }
        return Result.errormsg(99,"修改失败");
    }

    @ApiOperation(value = "新增参数", notes = "新增参数")
    @RequestMapping(value = "/SystemParam_Insert", method = {RequestMethod.POST})
    public Result SystemParam_Insert(SDictionary dictionary){
        String pid = dictionary.getPid();
        SDictionary byId = dictionaryService.getById(pid);
        String fullPath = byId.getFullPath();
        String newFullPath;
        if (StringUtils.isBlank(fullPath)){
            newFullPath=dictionary.getDictName();
        }else {
            newFullPath=fullPath+"/"+dictionary.getDictName();
        }
        dictionary.setFullPath(newFullPath);
        boolean save = dictionaryService.save(dictionary);
        if (save){
            return Result.okm("成功");
        }
        return Result.errormsg(99,"新增失败");
    }

    @ApiOperation(value = "编辑修改参数", notes = "编辑修改参数")
    @RequestMapping(value = "/SystemParam_Update", method = {RequestMethod.POST})
    public Result SystemParam_Update(SDictionary dictionary){
        boolean b=dictionaryService.SystemParam_Update(dictionary);
        if (b){
            return Result.okm("成功");
        }
        return Result.errormsg(99,"修改失败");
    }


    @ApiOperation(value = "删除参数", notes = "删除参数")
    @RequestMapping(value = "/SystemParam_Delete", method = {RequestMethod.POST})
    public Result SystemParam_Delete(String ID){
      //先修改为删除状态 在通过路径判断把带有他路径的子项都改为删除状态
        boolean b=dictionaryService.SystemParam_Delete(ID);
        if (b){
            return Result.okm("成功");
        }
        return Result.errormsg(99,"删除失败");
    }
}
