package com.tahoecn.xkc.controller.webapi.channel;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.jdbc.TimeUtil;
import com.tahoecn.core.json.JSONResult;
import com.tahoecn.xkc.model.dto.ChannelDto;
import com.tahoecn.xkc.service.channel.IBChannelorgService;
import io.micrometer.core.instrument.util.TimeUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
 * @since 2019-06-25
 */
@RestController
@RequestMapping("/channel")
public class ChannelController extends TahoeBaseController {


    @Autowired
    private IBChannelorgService channelorgService;

    @ApiOperation(value = "分销渠道列表", notes = "分页获取分销渠道列表")
    @ApiImplicitParams({ @ApiImplicitParam(name = "pageNum", value = "当前页数", dataType = "int") ,
            @ApiImplicitParam(name = "pageSize", value = "每页大小", dataType = "int") })
    @RequestMapping(value = "/ChannelList_SelectN", method = {RequestMethod.GET})
    public JSONResult ChannelList_SelectN(int pageSize,int pageNum,
                                          String ProjectID, String OrgName, String OrgLeaderName, String UserName,
                                          String OrgLeaderMobile,String Status, String CreatorName, Date begin, Date end) {

        Map map=new HashMap<>();
        map.put("ProjectID",ProjectID);
//        map.put("OrgName",OrgName);
//        map.put("OrgLeaderName",OrgLeaderName);
//        map.put("UserName",UserName);
//        map.put("Status",Status);
//        map.put("CreatorName",CreatorName);
//        map.put("begin",begin);
//        map.put("end",end);


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

        Page page=new Page(pageNum,pageSize);
        StringBuilder sqlWhere = new StringBuilder();
        if (StringUtils.isNotBlank(OrgName))
        {
            sqlWhere.append("AND a.OrgName like '%{"+OrgName+"}%'");
        }
        if (StringUtils.isNotBlank(OrgLeaderMobile))
        {
            sqlWhere.append("AND b.Mobile like '%"+OrgLeaderMobile+"%'");
        }
        if (StringUtils.isNotBlank(OrgLeaderName))
        {
            sqlWhere.append("AND b.Name like '%{"+OrgLeaderName+"}%'");
        }

        if (StringUtils.isNotBlank(UserName))
        {
            sqlWhere.append("AND b.UserName like '%{"+UserName+"}%'");
        }
        if (StringUtils.isNotBlank(Status))
        {
            sqlWhere.append("AND b.Status like '%{"+Status+"}%'");
        }
        if (begin!=null)
        {
            sqlWhere.append("AND a.CreateTime > '{"+begin+"}'");
        }
        if (end!=null)
        {
            sqlWhere.append("AND a.CreateTime <  '{"+end+"}'");
        }
        //CreatorName不确定表和字段名
        if (StringUtils.isNotBlank(CreatorName))
        {
            sqlWhere.append("AND a.CreatorName like '%{"+CreatorName+"}%'");
        }
        map.put("sqlWhere",sqlWhere.toString());

        System.out.println("+++++++++++"+sqlWhere);

        List<ChannelDto> list=channelorgService.getList(page,ProjectID,sqlWhere.toString());
        System.out.println(list);
        JSONResult jsonResult=new JSONResult();
        jsonResult.setMsg("SUCCESS");
        jsonResult.setData(list);
        return jsonResult;
    }

}
