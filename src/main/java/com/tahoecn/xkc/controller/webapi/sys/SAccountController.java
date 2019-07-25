package com.tahoecn.xkc.controller.webapi.sys;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tahoecn.xkc.common.utils.ThreadLocalUtils;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.salegroup.BSalesuser;
import com.tahoecn.xkc.model.sys.SAccount;
import com.tahoecn.xkc.service.salegroup.IBSalesuserService;
import com.tahoecn.xkc.service.sys.ISAccountService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tahoecn.xkc.controller.TahoeBaseController;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YYY
 * @since 2019-07-09
 */
@RestController
@RequestMapping("/webapi/sys/sAccount")
public class SAccountController extends TahoeBaseController {
    @Autowired
    private ISAccountService accountService;

    @Autowired
    private IBSalesuserService salesuserService;


//    @ApiOperation(value = "获取组织下的人员(用户管理)", notes = "获取组织下的人员(用户管理)")
//    @RequestMapping(value = "/SystemUserListByOrgID_Select", method = {RequestMethod.GET})
//    public Result SystemUserListByOrgID_Select(String AuthCompanyID,String OrgID, @RequestParam(required = false) String Name, @RequestParam(defaultValue = "0",required = false)int Type,
//                                               @RequestParam(defaultValue = "1")int Pageindex, @RequestParam(defaultValue = "10")int Pagesize) {
//        IPage page = new Page(Pageindex, Pagesize);
//        IPage<Map<String,Object>> list=accountService.SystemUserListByOrgID_Select(page,AuthCompanyID,OrgID,Name,Type);
//        return Result.ok(list);
//    }

    @ApiOperation(value = "获取组织下的人员(改后)", notes = "获取组织下的人员(改后)")
    @RequestMapping(value = "/SystemUserListByOrgID_Select", method = {RequestMethod.GET})
    public Result SystemUserListByOrgID_Select(String AuthCompanyID,String OrgID, @RequestParam(required = false) String Name, @RequestParam(defaultValue = "0",required = false)int Type,
                                               @RequestParam(defaultValue = "1")int Pageindex, @RequestParam(defaultValue = "10")int Pagesize) {
        IPage page = new Page(Pageindex, Pagesize);
        IPage<Map<String,Object>> list=accountService.SystemUserListByOrgID_SelectN(page,AuthCompanyID,OrgID,Name,Type);
        return Result.ok(list);
    }

    //
    @ApiOperation(value = "新增人员(用户管理)", notes = "新增人员(用户管理)")
    @RequestMapping(value = "/SystemUser_Insert", method = {RequestMethod.POST})
    public Result SystemUser_Insert(@RequestBody SAccount account) {
        account.setCreator(ThreadLocalUtils.getUserName());
        account.setPassword("C8837B23FF8AAA8A2DDE915473CE0991");
        account.setCreateTime(new Date());
        account.setIsDel(0);
        accountService.save(account);
        return Result.okm("成功");
    }

    @ApiOperation(value = "编辑人员(用户管理)", notes = "编辑人员(用户管理)")
    @RequestMapping(value = "/SystemUser_Update", method = {RequestMethod.POST})
    public Result SystemUser_Update(@RequestBody SAccount account) {
        account.setEditor(ThreadLocalUtils.getUserName());
        account.setEditTime(new Date());

        accountService.updateById(account);
//        同时更改掉B_SalesUser表里面的信息      B_SalesUser表ID 与s_account表ID相同?
        BSalesuser salesuser=new BSalesuser();
        salesuser.setId(account.getId());
        salesuser.setUserName(account.getUserName());
        salesuser.setTelPhone(account.getMobile());
        salesuser.setEditTime(new Date());
        salesuser.setEditor(ThreadLocalUtils.getUserName());
        salesuserService.updateById(salesuser);
        return Result.okm("成功");
    }

    @ApiOperation(value = "删除人员(用户管理)", notes = "删除人员(用户管理)")
    @RequestMapping(value = "/SystemUser_Delete", method = {RequestMethod.POST})
    public Result SystemUser_Delete(@RequestBody SAccount account) {
        account.setIsDel(1);
        accountService.updateById(account);
        return Result.okm("成功");
    }

    @ApiOperation(value = "启用/禁用(用户管理)", notes = "启用/禁用(用户管理)")
    @RequestMapping(value = "/SystemUser_Status", method = {RequestMethod.POST})
    public Result SystemUser_Status(@RequestBody SAccount account) {
        accountService.updateById(account);
        return Result.okm("成功");
    }




}
