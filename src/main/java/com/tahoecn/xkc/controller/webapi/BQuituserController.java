package com.tahoecn.xkc.controller.webapi;


import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tahoecn.xkc.common.utils.ExcelUtil;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.ResponseMessage;
import com.tahoecn.xkc.service.quit.IBQuituserService;
import com.tahoecn.xkc.service.salegroup.IBSalesgroupmemberService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/webapi/quitUser")
public class BQuituserController extends TahoeBaseController {

    @Autowired
    private IBQuituserService IBQuituserService;

    private IBSalesgroupmemberService iSaleGroupMemberService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "PageSize", value = "PageSize", required = true, dataType = "String"),
            @ApiImplicitParam(name = "PageIndex", value = "PageIndex", required = true, dataType = "String"),
            @ApiImplicitParam(name = "ProjectID", value = "项目Id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "IsExcel", value = "IsExcel", required = true, dataType = "String"),
            @ApiImplicitParam(name = "Name", value = "Name", required = true, dataType = "String"),
            @ApiImplicitParam(name = "Mobile", value = "Mobile", required = true, dataType = "String"),
            @ApiImplicitParam(name = "UserName", value = "UserName", required = true, dataType = "String"),
            @ApiImplicitParam(name = "TeamName", value = "TeamName", required = true, dataType = "String"),
            @ApiImplicitParam(name = "IsDispose", value = "IsDispose", required = true, dataType = "String")})
    @ApiOperation(value = "离职/调岗案场自渠接口", notes = "离职/调岗案场自渠接口")
    @RequestMapping(value = "/QuitUserOwnTeamList_Select", method = { RequestMethod.GET })
    public ResponseMessage QuitUserOwnTeamList_Select(
            @RequestParam(required = true) String PageSize,
            @RequestParam(required = true) String PageIndex,
            @RequestParam(required = true) String ProjectID,
            @RequestParam(required = true) String IsExcel,
            @RequestParam(required = true) String Name,
            @RequestParam(required = true) String Mobile,
            @RequestParam(required = true) String UserName,
            @RequestParam(required = true) String TeamName,
            @RequestParam(required = true) String IsDispose) {
        Map<String,Object> result = new HashMap<String,Object>();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("ProjectID",ProjectID);
        map.put("IsExcel",IsExcel);
        map.put("Name",Name);
        map.put("Mobile",Mobile);
        map.put("UserName",UserName);
        map.put("TeamName",TeamName);
        map.put("IsDispose",IsDispose);

        IPage page = new Page();
        page.setSize(Integer.valueOf(PageSize));
        page.setCurrent(Integer.valueOf(PageIndex));

        result.put("List", IBQuituserService.QuitUserOwnTeamList_Select(page,map));

        return ResponseMessage.ok(result);
    }

    private void QuitUserOwnTeamListAll_Select(Map<String,Object> map){
        List<ExcelExportEntity> entity = new ArrayList<ExcelExportEntity>();
        entity.add(new ExcelExportEntity("用户名","UserName"));
        entity.add(new ExcelExportEntity("手机号","Mobile"));
        entity.add(new ExcelExportEntity("姓名", "Name"));
        entity.add(new ExcelExportEntity("所属团队","TeamName"));
        entity.add(new ExcelExportEntity("帐号状态","StatusName"));
        entity.add(new ExcelExportEntity("客户数量","CustomerCount"));
        entity.add(new ExcelExportEntity("离职或调岗时间", "CreateTime"));
        entity.add(new ExcelExportEntity("是否处理","IsDispose"));
        entity.add(new ExcelExportEntity("处理时间","EditeTime"));
        IPage page = new Page();
        page.setSize(Integer.valueOf(99999));
        page.setCurrent(Integer.valueOf(1));
        IPage<Map<String,Object>> result = IBQuituserService.QuitUserOwnTeamList_Select(page,map);

        try {
            LocalDateTime time= LocalDateTime.now();
            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
            String name = "离职或调岗案场自渠人员"+dtf2.format(time) + ".xlsx";
            ExcelUtil.exportExcel(entity,result.getRecords(),name,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    @ApiImplicitParams({
            @ApiImplicitParam(name = "PageSize", value = "PageSize", required = true, dataType = "String"),
            @ApiImplicitParam(name = "PageIndex", value = "PageIndex", required = true, dataType = "String"),
            @ApiImplicitParam(name = "ProjectID", value = "项目Id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "IsExcel", value = "IsExcel", required = true, dataType = "String"),
            @ApiImplicitParam(name = "Name", value = "Name", required = true, dataType = "String"),
            @ApiImplicitParam(name = "Mobile", value = "Mobile", required = true, dataType = "String"),
            @ApiImplicitParam(name = "UserName", value = "UserName", required = true, dataType = "String"),
            @ApiImplicitParam(name = "TeamName", value = "TeamName", required = true, dataType = "String"),
            @ApiImplicitParam(name = "IsDispose", value = "IsDispose", required = true, dataType = "String")})
    @ApiOperation(value = "离职/调岗案场销售接口", notes = "离职/调岗案场销售接口")
    @RequestMapping(value = "/QuitUserSalesTeamList_Select", method = { RequestMethod.GET })
    public ResponseMessage QuitUserSalesTeamList_Select(
            @RequestParam(required = true) String PageSize,
            @RequestParam(required = true) String PageIndex,
            @RequestParam(required = true) String ProjectID,
            @RequestParam(required = true) String IsExcel,
            @RequestParam(required = true) String Name,
            @RequestParam(required = true) String Mobile,
            @RequestParam(required = true) String UserName,
            @RequestParam(required = true) String TeamName,
            @RequestParam(required = true) String IsDispose) {
        Map<String,Object> result = new HashMap<String,Object>();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("ProjectID",ProjectID);
        map.put("IsExcel",IsExcel);
        map.put("Name",Name);
        map.put("Mobile",Mobile);
        map.put("UserName",UserName);
        map.put("TeamName",TeamName);
        map.put("IsDispose",IsDispose);
        IPage page = new Page();
        page.setSize(Integer.valueOf(PageSize));
        page.setCurrent(Integer.valueOf(PageIndex));
        if (StringUtils.isNotEmpty(IsExcel)){
            QuitUserSalesTeamListAll_Select(map);
            return null;
        }
        result.put("List", IBQuituserService.QuitUserSalesTeamList_Select(page,map));

        return ResponseMessage.ok(result);
    }


    private void QuitUserSalesTeamListAll_Select(Map<String,Object> map){
        List<ExcelExportEntity> entity = new ArrayList<ExcelExportEntity>();
        entity.add(new ExcelExportEntity("用户名","UserName"));
        entity.add(new ExcelExportEntity("手机号","Mobile"));
        entity.add(new ExcelExportEntity("姓名", "Name"));
        entity.add(new ExcelExportEntity("所属团队","TeamName"));
        entity.add(new ExcelExportEntity("帐号状态","StatusName"));
        entity.add(new ExcelExportEntity("客户数量","CustomerCount"));
        entity.add(new ExcelExportEntity("离职或调岗时间", "CreateTime"));
        entity.add(new ExcelExportEntity("是否处理","IsDispose"));
                entity.add(new ExcelExportEntity("处理时间","EditeTime"));
        IPage page = new Page();
        page.setSize(Integer.valueOf(99999));
        page.setCurrent(Integer.valueOf(1));
        IPage<Map<String,Object>> result = IBQuituserService.QuitUserSalesTeamList_Select(page,map);

        try {
            LocalDateTime time= LocalDateTime.now();
            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
            String name = "离职或调岗案场销售人员"+dtf2.format(time) + ".xlsx";
            ExcelUtil.exportExcel(entity,result.getRecords(),name,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "PageSize", value = "PageSize", required = true, dataType = "String"),
            @ApiImplicitParam(name = "PageIndex", value = "PageIndex", required = true, dataType = "String"),
            @ApiImplicitParam(name = "IsExcel", value = "IsExcel", required = true, dataType = "String"),
            @ApiImplicitParam(name = "Name", value = "Name", required = true, dataType = "String"),
            @ApiImplicitParam(name = "Mobile", value = "Mobile", required = true, dataType = "String"),
            @ApiImplicitParam(name = "UserName", value = "UserName", required = true, dataType = "String"),
            @ApiImplicitParam(name = "TeamName", value = "TeamName", required = true, dataType = "String"),
            @ApiImplicitParam(name = "IsDispose", value = "IsDispose", required = true, dataType = "String")})
    @ApiOperation(value = "离职/调岗渠道人员接口", notes = "离职/调岗渠道人员接口")
    @RequestMapping(value = "/QuitUserChannelList_Select", method = { RequestMethod.GET })
    public ResponseMessage QuitUserChannelList_Select(
            @RequestParam(required = true) String PageSize,
            @RequestParam(required = true) String PageIndex,
            @RequestParam(required = true) String ProjectID,
            @RequestParam(required = true) String IsExcel,
            @RequestParam(required = true) String Name,
            @RequestParam(required = true) String Mobile,
            @RequestParam(required = true) String UserName,
            @RequestParam(required = true) String TeamName,
            @RequestParam(required = true) String IsDispose) {
        Map<String,Object> result = new HashMap<String,Object>();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("ProjectID",ProjectID);
        map.put("IsExcel",IsExcel);
        map.put("Name",Name);
        map.put("Mobile",Mobile);
        map.put("UserName",UserName);
        map.put("TeamName",TeamName);
        map.put("IsDispose",IsDispose);
        if (StringUtils.isNotEmpty(IsExcel)){
            QuitUserChannelListAll_Select(map);
            return null;
        }
        IPage page = new Page();
        page.setSize(Integer.valueOf(PageSize));
        page.setCurrent(Integer.valueOf(PageIndex));
        result.put("List", IBQuituserService.QuitUserChannelList_Select(page,map));

        return ResponseMessage.ok(result);
    }

    private void QuitUserChannelListAll_Select(Map<String,Object> map){
        List<ExcelExportEntity> entity = new ArrayList<ExcelExportEntity>();
        entity.add(new ExcelExportEntity("用户名","UserName"));
        entity.add(new ExcelExportEntity("手机号","Mobile"));
        entity.add(new ExcelExportEntity("姓名", "Name"));
        entity.add(new ExcelExportEntity("所属机构","OrgName"));
        entity.add(new ExcelExportEntity("帐号状态","StatusName"));
        entity.add(new ExcelExportEntity("客户数量","CustomerCount"));
        entity.add(new ExcelExportEntity("离职或调岗时间", "CreateTime"));
        entity.add(new ExcelExportEntity("是否处理","IsDispose"));
        entity.add(new ExcelExportEntity("处理时间","EditeTime"));
        IPage page = new Page();
        page.setSize(Integer.valueOf(99999));
        page.setCurrent(Integer.valueOf(1));
        IPage<Map<String,Object>> result = IBQuituserService.QuitUserChannelList_Select(page,map);

        try {
            LocalDateTime time= LocalDateTime.now();
            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
            String name = "离职或调岗渠道人员"+dtf2.format(time) + ".xlsx";
            ExcelUtil.exportExcel(entity,result.getRecords(),name,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
