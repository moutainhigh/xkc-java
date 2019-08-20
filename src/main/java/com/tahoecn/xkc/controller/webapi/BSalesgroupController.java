package com.tahoecn.xkc.controller.webapi;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.landray.sso.client.oracle.StringUtil;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.ResponseMessage;
import com.tahoecn.xkc.model.salegroup.BSalesgroup;
import com.tahoecn.xkc.service.salegroup.IBSalesgroupService;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 树形结构：项目--团队--组 前端控制器
 * </p>
 *
 * @author YYY
 * @since 2019-07-04
 */
@RestController
@RequestMapping("/webapi/salesgroup")
public class BSalesgroupController extends TahoeBaseController {

    @Autowired
    private IBSalesgroupService iBSalesgroupService;

    @ApiOperation(value = "新增或编辑团队接口", notes = "新增或编辑团队接口")
    @RequestMapping(value = "/SalesGroupTeam_UpdateNew", method = { RequestMethod.POST })
    public ResponseMessage SalesGroupTeam_UpdateNew(@RequestParam(required = true) String ID,
                                                    @RequestParam(required = true) String ProjectID,
                                                    @RequestParam(required = true) String UserID,
                                                    @RequestParam(required = true) String Name,
                                                    @RequestParam(required = true) String ShortName,
                                                    @RequestParam(required = true) Integer Nature,
                                                    @RequestParam(required = true) Integer Status,
                                                    @RequestParam(required = true) String DataType) {
        Map<String,Object> result = new HashMap<String,Object>();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("ID",ID);
        map.put("ProjectID",ProjectID);
        map.put("UserID",UserID);
        map.put("Name",Name);
        map.put("ShortName",ShortName);
        map.put("Nature",Nature);
        map.put("Status",Status);
        map.put("DataType",DataType);
        QueryWrapper<BSalesgroup> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Name", Name);
        queryWrapper.eq("Nature", Nature);
        queryWrapper.eq("'{ProjectID}'", ProjectID);
        queryWrapper.eq("IsDel", 0);

        BSalesgroup salesgroup = (BSalesgroup)iBSalesgroupService.getObj(queryWrapper);

        if(salesgroup!=null){
            return ResponseMessage.error("团队名称不能重复");
        }
        BSalesgroup bSalesgroupinsert = new BSalesgroup();
        if(StringUtil.isNotNull(ID)){
            bSalesgroupinsert.setId(ID);
        }
        bSalesgroupinsert.setPid(null);
        bSalesgroupinsert.setProjectID(ProjectID);
        bSalesgroupinsert.setName(Name);
        bSalesgroupinsert.setShortName(ShortName);
        bSalesgroupinsert.setNature(Nature);
        bSalesgroupinsert.setCreator(UserID);
        bSalesgroupinsert.setCreateTime(new Date());
        bSalesgroupinsert.setIsDel(0);
        bSalesgroupinsert.setStatus(Status);
        iBSalesgroupService.saveOrUpdate(bSalesgroupinsert);
        return ResponseMessage.ok("操作成功");
    }

    @ApiOperation(value = "新增或编辑团队接口", notes = "新增或编辑团队接口")
    @RequestMapping(value = "/UserTeamStatus_Update", method = { RequestMethod.GET })
    public ResponseMessage UserTeamStatus_Update(
                                                 @RequestParam(required = true) String ReceptionGroupID,
                                                 @RequestParam(required = true) String UserID,
                                                 @RequestParam(required = true) Integer status) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("ReceptionGroupID",ReceptionGroupID);
        map.put("UserID",UserID);
        map.put("status",status);
        iBSalesgroupService.UserTeamStatus_Update(map);
        return ResponseMessage.ok("操作成功");
    }

    @ApiOperation(value = "删除团队接口", notes = "删除团队接口")
    @RequestMapping(value = "/UserTeam_DeleteNew", method = { RequestMethod.GET })
    public ResponseMessage UserTeam_DeleteNew(
            //{"_datatype":"text","_param":{"ReceptionGroupID":"7b10b60c-2bd7-4ee0-af39-e7085036fa72",
            // "Tp":"1","UserID":"6C883173-489C-47A4-97D2-3601CB7CEDFD"}}:
                                              @RequestParam(required = true) String ReceptionGroupID,
                                              @RequestParam(required = true) String UserID) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("ReceptionGroupID",ReceptionGroupID);
        map.put("UserID",UserID);
        iBSalesgroupService.UserTeam_DeleteNew(map);
        return ResponseMessage.ok("操作成功");
    }






}
