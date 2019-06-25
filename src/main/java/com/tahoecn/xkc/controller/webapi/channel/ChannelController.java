package com.tahoecn.xkc.controller.webapi.channel;


import com.tahoecn.core.json.JSONResult;
import com.tahoecn.xkc.model.dto.ChannelDto;
import com.tahoecn.xkc.service.channel.IBChannelorgService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
    public JSONResult ChannelList_SelectN(
                                          String ProjectID, String OrgName, String OrgLeaderName, String UserName,
                                          String StatuName, String CreatorName, Date begin, Date end) {

        Map map=new HashMap<>();
        map.put("ProjectID",ProjectID);
        map.put("OrgName",OrgName);
        map.put("OrgLeaderName",OrgLeaderName);
        map.put("UserName",UserName);
        map.put("StatuName",StatuName);
        map.put("CreatorName",CreatorName);
        map.put("begin",begin);
        map.put("end",end);
//        map.put("pageNum",pageNum);
//        map.put("pageSize",pageSize);

        List<ChannelDto> list=channelorgService.getList(map);
        JSONResult jsonResult=new JSONResult();
        return jsonResult;
    }

}
