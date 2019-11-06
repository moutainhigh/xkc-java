package com.tahoecn.xkc.controller.webapi;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tahoecn.xkc.common.utils.ThreadLocalUtils;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.org.SOrganization;
import com.tahoecn.xkc.service.org.ISOrganizationService;
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
 * @since 2019-07-06
 */
@RestController
@RequestMapping("/webapi/sOrganization")
public class SOrganizationController extends TahoeBaseController {

    @Autowired
    private ISOrganizationService organizationService;

    @ApiOperation(value = "获取组织列表--弃用", notes = "获取组织列表--弃用")
    @RequestMapping(value = "/SystemOrganization_Select", method = {RequestMethod.POST})
    public Result SystemOrganization_Select(String OrgID,String AuthCompanyID,String ProductID,String Status,String PID, int Pageindex, int Pagesize) {
        IPage page=new Page(Pageindex,Pagesize);
        IPage<Map<String,Object>> list=organizationService.SystemOrganization_Select(page,AuthCompanyID,OrgID,ProductID,PID,Status);
        return Result.ok(list);
    }

    @ApiOperation(value = "获取组织列表(全部)", notes = "获取组织列表(全部)")
    @RequestMapping(value = "/SystemOrganizationChec_Select", method = {RequestMethod.POST})
    public Result SystemOrganizationChec_Select(String pid){
       List<Map<String,Object>> list=organizationService.SystemOrganizationChec_Select(pid);
          return Result.ok(list);
    }

    @ApiOperation(value = "获取组织列表(项目)", notes = "获取组织列表(项目)")
    @RequestMapping(value = "/SystemProject_Select", method = {RequestMethod.POST})
    public Result SystemProject_Select(String pid){
        List<Map<String,Object>> list=organizationService.SystemProject_Select(pid);
        return Result.ok(list);
    }


    @ApiOperation(value = "新增组织", notes = "新增组织")
    @RequestMapping(value = "/SystemOrganizationDetail_Insert", method = {RequestMethod.POST})
    public Result SystemOrganizationDetail_Insert(int OrgCategory,String AuthCompanyID,String ProductID,
                                                  String OrgName,String OrgShortName,String pid,int ListIndex) {
        SOrganization organization=new SOrganization();
        SOrganization byId = organizationService.getById(pid);
        String FullPath=byId.getFullPath()+"/"+OrgName;
        organization.setFullPath(FullPath);
        organization.setCreateTime(new Date());
        organization.setCreator(ThreadLocalUtils.getUserName());
        organization.setStatus(1);
        organization.setIsDel(0);
        organization.setCurrentPoint(0d);
        organization.setListIndex(ListIndex);
        organization.setOrgCategory(OrgCategory);
        organization.setLevels(byId.getLevels()+1);
        organization.setAuthCompanyID(AuthCompanyID);
        organization.setProductID(ProductID);
        organization.setOrgName(OrgName);
        organization.setOrgShortName(OrgShortName);
        organization.setPid(pid);
        boolean save = organizationService.save(organization);
        if (save){
            return Result.ok(organization);
        }
        return Result.errormsg(99,"新增失败");
    }

    @ApiOperation(value = "修改更新组织", notes = "修改更新组织")
    @RequestMapping(value = "/SystemOrganizationDetail_Update", method = {RequestMethod.POST})
    public Result SystemOrganizationDetail_Update(String ID,int OrgCategory,String AuthCompanyID,String ProductID,
                                                  String OrgName,String OrgShortName,String pid,int ListIndex) {
        SOrganization organization=new SOrganization();
        organization.setOrgCategory(OrgCategory);
        organization.setAuthCompanyID(AuthCompanyID);
        organization.setProductID(ProductID);
        organization.setOrgName(OrgName);
        organization.setOrgShortName(OrgShortName);
        organization.setListIndex(ListIndex);
        organization.setId(ID);
        organization.setPid(pid);
        boolean b=organizationService.SystemOrganizationDetail_Updatez(organization);
        if (b){
            return Result.okm("成功");
        }
        return Result.errormsg(99,"修改失败");
    }

    @ApiOperation(value = "启用/禁用组织", notes = "启用/禁用组织")
    @RequestMapping(value = "/SystemOrganizationStatus_Update", method = {RequestMethod.POST})
    public Result SystemOrganizationStatus_Update(String ID,int Status) {
        SOrganization organization=new SOrganization();
        organization.setId(ID);
        organization.setStatus(Status);
        organization.setEditor(ThreadLocalUtils.getUserName());
        organization.setEditTime(new Date());
        boolean b = organizationService.updateById(organization);
        if (b){
            return Result.okm("成功");
        }
        return Result.errormsg(99,"修改失败");
    }


}
