package com.tahoecn.xkc.controller.webapi;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tahoecn.xkc.common.utils.ThreadLocalUtils;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.dto.SMenusXkcDto;
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
import org.springframework.web.bind.annotation.*;

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
    public Result SystemJob_Update(@RequestBody SJobs jobs){
        jobs.setEditor(ThreadLocalUtils.getUserName());
        jobs.setEditTime(new Date());
        boolean update = jobsService.updateById(jobs);
        if (update){
            return Result.okm("成功");
        }
        return Result.errormsg(99,"更新失败");
    }

    @ApiOperation(value = "启用/禁用岗位", notes = "启用/禁用岗位")
    @RequestMapping(value = "/SystemJob_Status", method = {RequestMethod.POST})
    public Result SystemJob_Status(String ID,int Status){
        SJobs jobs=new SJobs();
        jobs.setId(ID);
        jobs.setStatus(Status);
        boolean b = jobsService.updateById(jobs);
        if (b){
            return Result.okm("成功");
        }
        return Result.errormsg(99,"启用/禁用失败");
    }

    @ApiOperation(value = "删除岗位", notes = "删除岗位")
    @RequestMapping(value = "/SystemJob_Delete", method = {RequestMethod.POST})
    public Result SystemJob_Delete(String ID){
        SJobs jobs=new SJobs();
        jobs.setId(ID);
        jobs.setIsDel(1);
        boolean b = jobsService.updateById(jobs);
        if (b){
            return Result.okm("成功");
        }
        return Result.errormsg(99,"删除失败");
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


    @ApiOperation(value = "岗位数据授权列表", notes = "岗位数据授权列表")
    @RequestMapping(value = "/SystemJobAuthOrg_Select", method = {RequestMethod.POST})
    public Result SystemJobAuthOrg_Select(String PID,String JobID){
        List<Map<String, Object>> list=jobsService.SystemJobAuthOrg_Select(PID,JobID);

        return Result.ok(list);
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
    public Result SystemJobUser_Insert(String jobID,String userName,int accountType,String employeeCode,String employeeName,int gender
            ,String officeTel,String mobile,String officeMail,String postCode,int status,String address,String authCompanyID,String productID){
        SAccount sAccount=new SAccount();
        sAccount.setUserName(userName);
        sAccount.setAccountType(accountType);
        sAccount.setEmployeeCode(employeeCode);
        sAccount.setEmployeeName(employeeName);
        sAccount.setGender(gender);
        sAccount.setOfficeTel(officeTel);
        sAccount.setMobile(mobile);
        sAccount.setOfficeMail(officeMail);
        sAccount.setPostCode(postCode);
        sAccount.setStatus(status);
        sAccount.setAddress(address);
        sAccount.setAuthCompanyID(authCompanyID);
        sAccount.setProductID(productID);

        boolean save =jobsService.SystemJobUser_Insert(sAccount,jobID);

        if (save){
            return Result.okm("成功");
        }
        return Result.errormsg(99,"人员新增失败");
    }

    //SystemUser_Update
    @ApiOperation(value = "岗位人员编辑", notes = "岗位人员编辑")
    @RequestMapping(value = "/SystemJobUser_Update", method = {RequestMethod.POST})
    public Result SystemJobUser_Update(SAccount account){
        boolean save =jobsService.SystemJobUser_Update(account);

        if (save){
            return Result.okm("成功");
        }
        return Result.errormsg(99,"人员新增失败");
    }

    @ApiOperation(value = "岗位人员启用/禁用", notes = "岗位人员启用/禁用")
    @RequestMapping(value = "/SystemJobUser_Status", method = {RequestMethod.POST})
    public Result SystemJobUser_Status(SAccount account){
        boolean save =accountService.updateById(account);
        if (save){
            return Result.okm("成功");
        }
        return Result.errormsg(99,"失败");
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

    @ApiOperation(value = "APP功能授权列表", notes = "APP功能授权列表")
    @RequestMapping(value = "/CommonJobList_Select", method = {RequestMethod.POST})
    public Result CommonJobList_Select(){
        List<Map<String, Object>> list=jobsService.CommonJobList_Select();
        return Result.ok(list);
    }

    @ApiOperation(value = "APP功能授权(树形结构)", notes = "APP功能授权(树形结构)")
    @RequestMapping(value = "/MenuOrFunIDList_Select_Tree", method = {RequestMethod.POST})
    public Result MenuOrFunIDList_Select_Tree(){
        List<SMenusXkcDto> list=jobsService.MenuOrFunIDList_Select_Tree();
        return Result.ok(list);
    }
    @ApiOperation(value = "APP功能授权(树形结构)回显信息", notes = "APP功能授权(树形结构)回显信息")
    @RequestMapping(value = "/MenuOrFunIDList_Select", method = {RequestMethod.POST})
    public Result MenuOrFunIDList_Select(String JobID){
        List<Map<String, Object>> list=jobsService.MenuOrFunIDList_Select(JobID);
        return Result.ok(list);
    }

    @ApiOperation(value = "APP新增或修改功能授权", notes = "APP新增或修改功能授权")
    @RequestMapping(value = "/FunctionAuthorization_Insert", method = {RequestMethod.POST})
    public Result FunctionAuthorization_Insert(String JobID,String MainID,String SonID){
        Boolean b=jobsService.FunctionAuthorization_Insert(JobID,MainID,SonID);
        if (b){
            return Result.ok("成功");
        }
        return Result.errormsg(99,"修改或新增失败");
    }
}
