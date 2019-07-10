package com.tahoecn.xkc.controller.webapi.sys;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.sys.SAccount;
import com.tahoecn.xkc.service.sys.ISAccountService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.tahoecn.xkc.controller.TahoeBaseController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
@RequestMapping("/sAccount")
public class SAccountController extends TahoeBaseController {
    @Autowired
    private ISAccountService accountService;

    //已测
    @ApiOperation(value = "获取组织下的人员(用户管理)", notes = "获取组织下的人员(用户管理)")
    @RequestMapping(value = "/SystemUserListByOrgID_Select", method = {RequestMethod.POST})
    public Result SystemUserListByOrgID_Select(@RequestBody JSONObject jsonParam) {
        Map paramMap = (HashMap)jsonParam.get("_param");
        int PageIndex=(int) paramMap.get("Pageindex");
        int PageSize=(int) paramMap.get("Pagesize");
        String AuthCompanyID=(String) paramMap.get("AuthCompanyID");
        String OrgID=(String) paramMap.get("OrgID");
        String Key=(String) paramMap.get("Key");
        IPage page = new Page(PageIndex, PageSize);
        List<Map<String,Object>> list=accountService.SystemUserListByOrgID_Select(page,AuthCompanyID,OrgID,Key);
        return Result.ok(list);
    }

    //
    @ApiOperation(value = "新增人员(用户管理)", notes = "新增人员(用户管理)")
    @RequestMapping(value = "/SystemUser_Insert", method = {RequestMethod.POST})
    public Result SystemUser_Insert(@RequestBody JSONObject jsonParam) {
        Map paramMap = (HashMap)jsonParam.get("_param");

        SAccount account=new SAccount();
        account.setUserName((String) paramMap.get("UserName"));
        account.setPassword("C8837B23FF8AAA8A2DDE915473CE0991");
        account.setAccountType((int) paramMap.get("AccountType"));
        account.setEmployeeCode((String) paramMap.get("EmployeeCode"));
        account.setEmployeeName((String) paramMap.get("EmployeeName"));
        account.setGender((int) paramMap.get("Gender"));
        account.setOfficeTel((String) paramMap.get("OfficeTel"));
        account.setOfficeMail((String) paramMap.get("OfficeMail"));
        account.setMobile((String) paramMap.get("Mobile"));
        account.setPostCode((String) paramMap.get("PostCode"));
        account.setAddress((String) paramMap.get("Address"));
        account.setUserOrgID((String) paramMap.get("UserOrgID"));
        account.setAuthCompanyID((String) paramMap.get("AuthCompanyID"));
        account.setCreator((String) paramMap.get("Creator"));
        account.setCreateTime(new Date());
        account.setStatus((int) paramMap.get("Status"));
        account.setIsDel(0);
        accountService.save(account);
        return Result.okm("成功");
    }

}
