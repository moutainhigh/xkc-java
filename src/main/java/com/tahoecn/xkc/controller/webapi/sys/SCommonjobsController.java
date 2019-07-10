package com.tahoecn.xkc.controller.webapi.sys;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tahoecn.xkc.common.utils.ThreadLocalUtils;
import com.tahoecn.xkc.converter.Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tahoecn.core.json.JSONResult;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.model.sys.SCommonjobs;
import com.tahoecn.xkc.service.sys.ISCommonjobsService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YYY
 * @since 2019-06-26
 */
@RestController
@RequestMapping("/webapi/sys/CommonJob")
public class SCommonjobsController extends TahoeBaseController {
	
	@Autowired
	private ISCommonjobsService ISCommonjobsService;
	
//已测
    @ApiOperation(value = "获取通用岗位列表(查询)", notes = "分页获取获取通用岗位列表（查询）")
    @ApiImplicitParams({ @ApiImplicitParam(name = "pageNum", value = "当前页数", dataType = "int") ,
            @ApiImplicitParam(name = "pageSize", value = "每页大小", dataType = "int") })
    @RequestMapping(value = "/SystemCommonJobsList_Select", method = {RequestMethod.POST})
    public Result SystemCommonJobsList_Select(String AuthCompanyID,String ProductID,String JobName,int Pageindex,int Pagesize){
        IPage page = new Page(Pageindex, Pagesize);
    	List list = ISCommonjobsService.SystemCommonJobsList_Select(page,AuthCompanyID,ProductID,JobName);
    	return Result.ok(list);
    }
    
    @ApiOperation(value = "获取通用岗位列表", notes = "分页获取获取通用岗位列表")
    @ApiImplicitParams({ @ApiImplicitParam(name = "pageNum", value = "当前页数", dataType = "int") ,
            @ApiImplicitParam(name = "pageSize", value = "每页大小", dataType = "int") })
    @RequestMapping(value = "/SystemCommonJobsList_SelectList", method = {RequestMethod.POST})
    
    public Result SystemCommonJobsList_SelectList(){
    	List<SCommonjobs> list = ISCommonjobsService.SystemCommonJobsList_SelectList();
    	return Result.ok(list);
    }
    
    
    @ApiOperation(value = "查询岗位名称是否存在", notes = "查询岗位名称是否存在")
    @RequestMapping(value = "SystemCommonJobNameIsExists_Select", method = {RequestMethod.POST})
    public Result SystemCommonJobNameIsExists_Select(String AuthCompanyID,String ProductID,String JobName){
    	Boolean result = ISCommonjobsService.SystemCommonJobNameIsExists_Select(AuthCompanyID,ProductID,JobName);
    	return Result.ok(result);
    }
    
    @ApiOperation(value = "启用/禁用通用岗位", notes = "启用/禁用通用岗位")
    @ApiImplicitParams({ @ApiImplicitParam(name = "Status", value = "状态", dataType = "string"),
    	@ApiImplicitParam(name = "id", value = "id", dataType = "string")
    	})
    @RequestMapping(value = "/SystemCommonJobStatus_Update", method = {RequestMethod.POST})
    public Result SystemCommonJobStatus_Update(@RequestBody SCommonjobs commonjobs){

        //未判断不成功情况
    	ISCommonjobsService.updateById(commonjobs);

    	return Result.ok("启用/禁用成功");
    }
    
    @ApiOperation(value = "删除通用岗位", notes = "删除通用岗位")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "id", dataType = "string")
    	})
    @RequestMapping(value = "/SystemCommonJob_Delete", method = {RequestMethod.POST})
    public Result SystemCommonJob_Delete(@RequestBody SCommonjobs commonjobs){
        commonjobs.setIsDel(1);
    	ISCommonjobsService.updateById(commonjobs);
    	return Result.okm("成功");
    }
    
   @ApiOperation(value = "新增通用岗位", notes = "新增通用岗位")
   @ApiImplicitParams({@ApiImplicitParam(name = "jobCode", value = "jobCode", dataType = "string"),
	   @ApiImplicitParam(name = "jobName", value = "jobName", dataType = "string"),
	   @ApiImplicitParam(name = "JobDesc", value = "JobDesc", dataType = "string"),
	   @ApiImplicitParam(name = "authCompanyID", value = "AuthCompanyID", dataType = "string"),
	   @ApiImplicitParam(name = "productID", value = "ProductID", dataType = "string"),
	   @ApiImplicitParam(name = "editor", value = "Editor", dataType = "string"),
	   @ApiImplicitParam(name = "editTime", value = "EditTime", dataType = "string"),
	   @ApiImplicitParam(name = "status", value = "Status", dataType = "string"),
	   @ApiImplicitParam(name = "isDel", value = "IsDel", dataType = "string"),
    	})
   @RequestMapping(value = "/SystemCommonJob_Insert", method = {RequestMethod.POST})
   public Result SystemCommonJob_Insert(@RequestBody SCommonjobs commonjobs){
        commonjobs.setCreator(ThreadLocalUtils.getUserName());
        commonjobs.setCreateTime(new Date());
	   	commonjobs.setIsDel(0);
       boolean save = ISCommonjobsService.save(commonjobs);
       if (save){
           return Result.okm("成功");
       }
       return Result.errormsg(99,"新增失败");
   }
   
   @ApiOperation(value = "更新通用岗位", notes = "更新通用岗位")
   @ApiImplicitParams({@ApiImplicitParam(name = "jobCode", value = "jobCode", dataType = "string"),
	   @ApiImplicitParam(name = "jobName", value = "jobName", dataType = "string"),
	   @ApiImplicitParam(name = "JobDesc", value = "JobDesc", dataType = "string"),
	   @ApiImplicitParam(name = "editor", value = "Editor", dataType = "string"),
	   @ApiImplicitParam(name = "status", value = "Status", dataType = "string"),
	   @ApiImplicitParam(name = "id", value = "id", dataType = "string"),
    	})
   @RequestMapping(value = "/SystemCommonJob_Update", method = {RequestMethod.POST})
   public Result SystemCommonJob_Update(@RequestBody SCommonjobs commonjobs){
        commonjobs.setEditor(ThreadLocalUtils.getUserName());
        commonjobs.setEditTime(new Date());
	   	ISCommonjobsService.updateById(commonjobs);
	   	return Result.okm("成功");
   }




}
