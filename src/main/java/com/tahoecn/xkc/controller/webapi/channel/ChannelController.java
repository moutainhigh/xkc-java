package com.tahoecn.xkc.controller.webapi.channel;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.jdbc.TimeUtil;
import com.tahoecn.core.json.JSONResult;
import com.tahoecn.xkc.model.dto.ChannelDto;
import com.tahoecn.xkc.model.dto.ChannelInsertDto;
import com.tahoecn.xkc.service.channel.IBChannelorgService;
import com.tahoecn.xkc.service.channel.IBChanneluserService;
import com.tahoecn.xkc.service.dict.ISDictionaryService;
import io.micrometer.core.instrument.util.TimeUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
 * @since 2019-06-25
 */
@RestController
@RequestMapping("/channel")
public class ChannelController extends TahoeBaseController {


    @Autowired
    private IBChannelorgService channelorgService;

    @Autowired
    private IBChanneluserService channeluserService;

    @Autowired
    private ISDictionaryService dictionaryService;

    @ApiOperation(value = "分销渠道列表", notes = "分页获取分销渠道列表")
    @ApiImplicitParams({ @ApiImplicitParam(name = "pageNum", value = "当前页数", dataType = "int") ,
            @ApiImplicitParam(name = "pageSize", value = "每页大小", dataType = "int") })
    @RequestMapping(value = "/ChannelList_SelectN", method = {RequestMethod.GET})
    public JSONResult ChannelList_SelectN(int pageSize,int pageNum,
                                          String ProjectID, String OrgName, String OrgLeaderName, String UserName,
                                          String OrgLeaderMobile,String Status, String CreatorName, Date begin, Date end) {

        Map map=new HashMap<>();
        map.put("ProjectID",ProjectID);

        /**
         * {"_datatype":"text","_param":{"
         * ProjectID":"cd69e423-63b5-e711-80c7-00505686c900",
         * "IsExcel":"0",
         * "PageSize":"6",
         * "PageIndex":1,"
         * OrgName":"",
         * "OrgLeaderName":"",
         * "OrgLeaderMobile":"","
         * OrgLeaderUserName":"","
         * OrgLeaderStatu":"","
         * CreateStartTime":"","
         * CreateEndTime":"","
         * CreatorID":""}}:
         */
        map.put("pageNum",pageNum);
        map.put("pageSize",pageSize);

        StringBuilder sqlWhere = new StringBuilder();
        if (StringUtils.isNotBlank(OrgName))
        {
            sqlWhere.append("AND a.OrgName like '%"+OrgName+"%'");
        }
        if (StringUtils.isNotBlank(OrgLeaderMobile))
        {
            sqlWhere.append("AND b.Mobile like '%"+OrgLeaderMobile+"%'");
        }
        if (StringUtils.isNotBlank(OrgLeaderName))
        {
            sqlWhere.append("AND b.Name like '%"+OrgLeaderName+"%'");
        }

        if (StringUtils.isNotBlank(UserName))
        {
            sqlWhere.append("AND b.UserName like '%"+UserName+"%'");
        }
        if (StringUtils.isNotBlank(Status))
        {
            sqlWhere.append("AND b.Status like '%"+Status+"%'");
        }
        if (begin!=null)
        {
            sqlWhere.append("AND a.CreateTime > '"+begin+"'");
        }
        if (end!=null)
        {
            sqlWhere.append("AND a.CreateTime <  '"+end+"'");
        }
        //CreatorName不确定表和字段名
        if (StringUtils.isNotBlank(CreatorName))
        {
            sqlWhere.append("AND a.CreatorName like '%"+CreatorName+"%'");
        }
        map.put("sqlWhere",sqlWhere.toString());

//todo 导出判断 筛选字段未确认
        Map list=channelorgService.getList(ProjectID,sqlWhere.toString(),pageSize,pageNum);
        JSONResult jsonResult=new JSONResult();
        jsonResult.setMsg("SUCCESS");
        jsonResult.setData(list);
        return jsonResult;
    }

    //分销中介、推荐渠道列表-审核人查询
    @ApiOperation(value = "推荐渠道列表-审核人查询", notes = "推荐渠道列表-审核人查询")
    @RequestMapping(value = "/AgenApproverList_SelectN", method = {RequestMethod.GET})
    public JSONResult AgenApproverList_SelectN(){

        List<Map<String,String>> list=channeluserService.AgenApproverList();
        JSONResult jsonResult=new JSONResult();
        jsonResult.setMsg("SUCCESS");
        jsonResult.setData(list);
        return jsonResult;
    }

    //新增
    /**
     * {"_datatype":"text","_param":{"
     * ProjectID":"cd69e423-63b5-e711-80c7-00505686c900","
     * OrgID":"","
     * OrgName":"qqq","
     * OrgShortName":"","
     * Bizlicense":"/Uploads/image/20190627/9486178f28b0352fae139bafc18d370c.png","
     * LeaderID":"","
     * UserName":"qqq","
     * Name":"qqq","
     * Mobile":"11111111222","
     * Status":"1","
     * ProjectIDs":"CD69E423-63B5-E711-80C7-00505686C900","
     * RuleIDs":["CD69E423-63B5-E711-80C7-00505686C900,"],"
     * UserID":"6C883173-489C-47A4-97D2-3601CB7CEDFD"}}:
     */
    @ApiOperation(value = "新增渠道机构", notes = "新增渠道机构")
    @RequestMapping(value = "/ChannelDetail_InsertN", method = {RequestMethod.POST})
    public JSONResult ChannelDetail_InsertN(@RequestBody ChannelInsertDto channelInsertDto){

        //未完成
        channelorgService.ChannelDetail_InsertN(channelInsertDto);
        JSONResult jsonResult=new JSONResult();
        jsonResult.setMsg("SUCCESS");
        return jsonResult;
    }

    @ApiOperation(value = "分销中介、推荐渠道列表-证件类型列表查询", notes = "分销中介、推荐渠道列表-证件类型列表查询")
    @RequestMapping(value = "/AgenCertificatesList_SelectN", method = {RequestMethod.GET})
    public JSONResult AgenCertificatesList_SelectN(){

        List<Map<String,String>> list=dictionaryService.AgenCertificatesList_SelectN();
        JSONResult jsonResult=new JSONResult();
        jsonResult.setData(list);
        jsonResult.setMsg("SUCCESS");
        return jsonResult;
    }

    @ApiOperation(value = "渠道管理-所属机构列表", notes = "渠道管理-所属机构列表")
    @RequestMapping(value = "/ChannelOrgAllList_SelectN", method = {RequestMethod.GET})
    public JSONResult ChannelOrgAllList_SelectN(String ProjectID,String pOrgName){
        List<Map<String,String>> list=channelorgService.ChannelOrgAllList_SelectN(ProjectID,pOrgName);
        JSONResult jsonResult=new JSONResult();
        jsonResult.setData(list);
        jsonResult.setMsg("SUCCESS");
        return jsonResult;
    }

    @ApiOperation(value = "修改机构状态", notes = "修改机构状态")
    @RequestMapping(value = "/ChannelStatus_UpdateN", method = {RequestMethod.GET})
    public JSONResult ChannelStatus_UpdateN(String ID,String UserID,String Status){
        channelorgService.ChannelStatus_UpdateN(ID,UserID,Status);
        JSONResult jsonResult=new JSONResult();
        jsonResult.setMsg("SUCCESS");
        return jsonResult;
    }



}
