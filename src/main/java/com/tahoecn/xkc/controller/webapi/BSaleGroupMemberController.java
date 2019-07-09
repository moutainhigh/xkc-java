package com.tahoecn.xkc.controller.webapi;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.ResponseMessage;
import com.tahoecn.xkc.service.salegroup.IBSalesgroupmemberService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YYY
 * @since 2019-06-25
 */
@RestController
public class BSaleGroupMemberController extends TahoeBaseController {

    @Autowired
    private IBSalesgroupmemberService iSaleGroupMemberService;

    @ApiImplicitParams({ @ApiImplicitParam(name = "ProjectID", value = "ProjectID", required = true, dataType = "String")})
    @ApiOperation(value = "人员管理", notes = "人员管理")
    @RequestMapping(value = "/SalesGroupMemberList_Select", method = { RequestMethod.GET })
    public ResponseMessage SalesGroupMemberList_Select(@RequestParam(required = true) String ProjectID) {
        Map<String,Object> result = new HashMap<String,Object>();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("ProjectID",ProjectID);
        result.put("List",iSaleGroupMemberService.SalesGroupMemberList_Select(map));
        return ResponseMessage.ok(result);
    }


    @ApiImplicitParams({ @ApiImplicitParam(name = "ProjectID", value = "ProjectID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "ReceptionGroupID", value = "ReceptionGroupID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "UserID", value = "UserID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "RoleID", value = "RoleID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "Tp", value = "Tp", required = true, dataType = "String"),
            @ApiImplicitParam(name = "PageIndex", value = "PageIndex", required = true, dataType = "String"),
            @ApiImplicitParam(name = "PageSize", value = "PageSize", required = true, dataType = "String"),
            @ApiImplicitParam(name = "Kw", value = "Kw", required = true, dataType = "String")})
    @ApiOperation(value = "查询团队人员列表接口", notes = "查询团队人员列表接口")
    @RequestMapping(value = "/SalesGroupTeamList_Select", method = { RequestMethod.GET })
    public ResponseMessage SalesGroupTeamList_Select(@RequestParam(required = true) String ProjectID,
                @RequestParam(required = true) String ReceptionGroupID,
                @RequestParam(required = true) String UserID,
                @RequestParam(required = true) String RoleID,
                @RequestParam(required = true) String Tp,
                @RequestParam(required = true) String PageIndex,
                @RequestParam(required = true) String PageSize,
                @RequestParam(required = true) String Kw) {
        Map<String,Object> result = new HashMap<String,Object>();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("ProjectID",ProjectID);
        map.put("ReceptionGroupID",ReceptionGroupID);
        map.put("UserID",UserID);
        map.put("RoleID",RoleID);
        map.put("Tp",Tp);
        map.put("PageIndex",PageIndex);
        map.put("PageSize",PageSize);
        map.put("Kw",Kw);

        IPage page = new Page();
        page.setSize(Integer.valueOf(PageSize));
        page.setCurrent(Integer.valueOf(PageIndex));

        result.put("List",iSaleGroupMemberService.SalesGroupTeamList_Select(page,map));
        return ResponseMessage.ok(result);
    }



    @ApiImplicitParams({
            @ApiImplicitParam(name = "Ids", value = "Ids", required = true, dataType = "String"),
            @ApiImplicitParam(name = "RoleID", value = "RoleID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "ProjectID", value = "ProjectID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "PersonId", value = "PersonId", required = true, dataType = "String"),
            @ApiImplicitParam(name = "RemoveIds", value = "RemoveIds", required = true, dataType = "String"),
            @ApiImplicitParam(name = "UserID", value = "UserID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "RoleName", value = "RoleName", required = true, dataType = "String")
            })
    @ApiOperation(value = "添加移除成员接口", notes = "添加移除成员接口")
    @RequestMapping(value = "/SalesGroupMembers_Insert", method = { RequestMethod.GET })
    public ResponseMessage SalesGroupMembers_Insert(@RequestParam(required = true) String Ids,
                                                     @RequestParam(required = true) String ProjectID,
                                                     @RequestParam(required = true) String RoleID,
                                                     @RequestParam(required = true) String PersonId,
                                                     @RequestParam(required = true) String RemoveIds,
                                                     @RequestParam(required = true) String UserID,
                                                     @RequestParam(required = true) String RoleName) {
        Map<String,Object> result = new HashMap<String,Object>();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("Ids",Ids);
        map.put("ProjectID",ProjectID);
        map.put("RoleID",RoleID);
        map.put("PersonId",PersonId);
        map.put("RemoveIds",RemoveIds);
        map.put("UserID",UserID);
        map.put("RoleName",RoleName);

        iSaleGroupMemberService.SalesGroupMembers_Insert(map);
        return ResponseMessage.ok();
    }





}
