package com.tahoecn.xkc.controller.webapi;


import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.service.sys.IBMediachildService;
import com.tahoecn.xkc.service.sys.IBMedialargeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.tahoecn.xkc.controller.TahoeBaseController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YYY
 * @since 2019-08-06
 */
@RestController
@RequestMapping("/webapi/bMedialarge")
public class BMedialargeController extends TahoeBaseController {

    @Autowired
    private IBMedialargeService medialargeService;

    @ApiOperation(value = "媒体类别管理--树形", notes = "媒体类别管理--树形")
    @RequestMapping(value = "/getMediaLargeList_Tree", method = {RequestMethod.GET})
    public Result getMediaLargeList_Tree(String ProjectID){
        List<Map<String,Object>> list=medialargeService.getMediaList_Select(ProjectID);
        return Result.ok(list);
    }

    @ApiOperation(value = "媒体新增或编辑", notes = "媒体新增或编辑")
    @RequestMapping(value = "/Media_SaveOrUpdate", method = {RequestMethod.POST})
    public Result Media_SaveOrUpdate(String ProjectID,String MediaLargeID,String Name,String ShortName,int ListIndex,int Status){
        Result result=medialargeService.Media_SaveOrUpdate(ProjectID,MediaLargeID,Name,ShortName,ListIndex,Status);
        return result;
    }

    @ApiOperation(value = "媒体启用禁用", notes = "媒体启用禁用")
    @RequestMapping(value = "/Media_Status", method = {RequestMethod.POST})
    public Result Media_Status(String MediaLargeID,String ID,int Status){
        Result result=medialargeService.Media_Status(MediaLargeID,ID,Status);
        return result;
    }

    @ApiOperation(value = "媒体删除", notes = "媒体删除")
    @RequestMapping(value = "/Media_Delete", method = {RequestMethod.POST})
    public Result Media_Delete(String MediaLargeID,String ID){
        Result result=medialargeService.Media_Delete(MediaLargeID,ID);
        return result;
    }


}
