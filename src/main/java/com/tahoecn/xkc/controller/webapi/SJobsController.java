package com.tahoecn.xkc.controller.webapi;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tahoecn.xkc.common.utils.ThreadLocalUtils;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.job.SJobs;
import com.tahoecn.xkc.model.sys.SAccount;
import com.tahoecn.xkc.model.sys.SCommonjobs;
import com.tahoecn.xkc.service.job.ISJobsService;
import com.tahoecn.xkc.service.sys.ISAccountService;
import com.tahoecn.xkc.service.sys.ISCommonjobsService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.tahoecn.xkc.controller.TahoeBaseController;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YYY
 * @since 2019-07-04
 */
@RestController
@RequestMapping("/webapi/sJobs")
public class SJobsController extends TahoeBaseController {

    @Autowired
    private ISJobsService jobsService;

    @Autowired
    private ISAccountService accountService;

    @ApiOperation(value = "获取岗位列表", notes = "获取岗位列表")
    @ApiImplicitParams({ @ApiImplicitParam(name = "pageNum", value = "当前页数", dataType = "int") ,
            @ApiImplicitParam(name = "pageSize", value = "每页大小", dataType = "int") })
    @RequestMapping(value = "/SystemJobList_Select", method = {RequestMethod.POST})
    public Result SystemJobList_Select(String AuthCompanyID,String ProductID,String OrgID){
        String substring = OrgID.substring(0, 10);
        List<Map<String,Object>> list=jobsService.SystemJobList_Select(AuthCompanyID,ProductID,OrgID);
        return Result.ok(list);
    }

    @ApiOperation(value = "获取当前和下属所有组织岗位", notes = "获取当前和下属所有组织岗位")
    @ApiImplicitParams({ @ApiImplicitParam(name = "pageNum", value = "当前页数", dataType = "int") ,
            @ApiImplicitParam(name = "pageSize", value = "每页大小", dataType = "int") })
    @RequestMapping(value = "/SystemJobAllList_Select", method = {RequestMethod.POST})
    public Result SystemJobAllList_Select(String AuthCompanyID,String ProductID,String OrgID,int Pageindex,int Pagesize){
        IPage page=new Page(Pageindex,Pagesize);
        IPage<Map<String,Object>> list=jobsService.SystemJobAllList_Select(page,AuthCompanyID,ProductID,OrgID);
        return Result.ok(list);
    }

    @ApiOperation(value = "新增Jobs信息", notes = "新增Jobs信息")
    @RequestMapping(value = "/SystemJob_Insert", method = {RequestMethod.POST})
    public Result SystemJob_Insert(@RequestBody SJobs jobs){
        jobs.setCreator(ThreadLocalUtils.getUserName());
        jobs.setCreateTime(new Date());
        jobs.setIsDel(0);
        boolean save = jobsService.save(jobs);
        if (save){
            return Result.okm("成功");
        }
        return Result.errormsg(99,"新增失败");
    }

    @ApiOperation(value = "更新Jobs信息", notes = "更新Jobs信息")
    @RequestMapping(value = "/SystemJob_Update", method = {RequestMethod.POST})
    public Result SystemJob_Update(SJobs jobs){
        jobs.setEditor(ThreadLocalUtils.getUserName());
        jobs.setEditTime(new Date());
        boolean update = jobsService.updateById(jobs);
        if (update){
            return Result.okm("成功");
        }
        return Result.errormsg(99,"更新失败");
    }

    @ApiOperation(value = "删除岗位", notes = "删除岗位")
    @RequestMapping(value = "/SystemJob_Delete", method = {RequestMethod.POST})
    public Result SystemJob_Delete(String ID){
        SJobs jobs=new SJobs();
        jobs.setId(ID);
        jobs.setIsDel(1);
        jobsService.updateById(jobs);
        return Result.errormsg(99,"新增失败");
    }
    @ApiOperation(value = "岗位功能授权", notes = "岗位功能授权")
    @RequestMapping(value = "/SystemJobAuth_Insert", method = {RequestMethod.POST})
    public Result SystemJobAuth_Insert(String Menus,String JobID){
        boolean b=jobsService.SystemJobAuth_Insert(Menus,JobID);
        if (b){
            return Result.okm("成功");
        }
        return Result.errormsg(99,"授权失败");
    }

    @ApiOperation(value = "岗位数据授权", notes = "岗位数据授权")
    @RequestMapping(value = "/SystemJobAuthOrg_Insert", method = {RequestMethod.POST})
    public Result SystemJobAuthOrg_Insert(String OrgIDS,String JobID){
        boolean b=jobsService.SystemJobAuthOrg_Insert(OrgIDS,JobID);
        if (b){
            return Result.okm("成功");
        }
        return Result.errormsg(99,"授权失败");
    }

    @ApiOperation(value = "获取岗位下的人员", notes = "获取岗位下的人员")
    @ApiImplicitParams({ @ApiImplicitParam(name = "pageNum", value = "当前页数", dataType = "int") ,
            @ApiImplicitParam(name = "pageSize", value = "每页大小", dataType = "int") })
    @RequestMapping(value = "/SystemUserList_Select", method = {RequestMethod.POST})
    public Result SystemUserList_Select(String JobID,String AuthCompanyID){

        List<SAccount> list=jobsService.SystemUserList_Select(JobID,AuthCompanyID);
        return Result.ok(list);
    }


    @ApiOperation(value = "岗位人员新增", notes = "岗位人员新增")
    @RequestMapping(value = "/SystemJobUser_Insert", method = {RequestMethod.POST})
    public Result SystemJobUser_Insert(SAccount account,String JobID){
        boolean save =jobsService.SystemJobUser_Insert(account,JobID);

        if (save){
            return Result.okm("成功");
        }
        return Result.errormsg(99,"人员新增失败");
    }

    @ApiOperation(value = "获取所有人员", notes = "获取所有人员")
    @RequestMapping(value = "/SystemOrgUserList_Select", method = {RequestMethod.POST})
    public Result SystemOrgUserList_Select(String UserName,String EmployeeName,String JobID,int Pageindex, int Pagesize){
        IPage page=new Page(Pageindex,Pagesize);
        IPage<SAccount> list=jobsService.SystemOrgUserList_Select(page,UserName,EmployeeName,JobID);
        return Result.ok(list);
    }
//    UserIDS 逗号分隔
    @ApiOperation(value = "组织岗位引入人员", notes = "组织岗位引入人员")
    @RequestMapping(value = "/SystemJobUserRel_Insert", method = {RequestMethod.POST})
    public Result SystemJobUserRel_Insert(String UserIDS,String JobID){
        boolean b=jobsService.SystemJobUserRel_Insert(UserIDS,JobID);
        if (b){
            return Result.okm("成功");
        }
        return Result.errormsg(99,"引入人员失败");
    }
//    UserIDS逗号分隔 为人员在SACC表ID
    @ApiOperation(value = "移除用户", notes = "移除用户")
    @RequestMapping(value = "/SystemJobUserRel_Delete", method = {RequestMethod.POST})
    public Result SystemJobUserRel_Delete(String UserIDS,String JobID){
        boolean b=jobsService.SystemJobUserRel_Delete(UserIDS,JobID);
        if (b){
            return Result.okm("成功");
        }
        return Result.errormsg(99,"删除人员失败");
    }
}
