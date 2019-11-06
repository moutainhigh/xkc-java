package com.tahoecn.xkc.controller.webapi.sys;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.sys.SServicelog;
import com.tahoecn.xkc.service.sys.ISServiceService;
import com.tahoecn.xkc.service.sys.ISServicelogService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
 * @since 2019-07-25
 */
@RestController
@RequestMapping("/webapi/sys/sService")
public class SServiceController extends TahoeBaseController {

    @Autowired
    private ISServiceService serviceService;
    @Autowired
    private ISServicelogService servicelogService;


    @ApiOperation(value = "获取服务列表", notes = "获取服务列表")
    @RequestMapping(value = "/ServiceList_Select", method = {RequestMethod.GET})
    public Result ServiceList_Select(@RequestParam(defaultValue = "1")int Pageindex, @RequestParam(defaultValue = "10")int Pagesize) {

        IPage<Map<String,Object>> list=serviceService.ServiceList_Select(Pageindex,Pagesize);
        return Result.ok(list);
    }

    /**
     * 查看历史
     * @param ServiceID
     * @param Status 0为全部,1成功,2失败
     * @param Pageindex
     * @param Pagesize
     * @return
     */
    @ApiOperation(value = "查看历史", notes = "查看历史")
    @RequestMapping(value = "/ServiceLogListByService_Select", method = {RequestMethod.GET})
    public Result ServiceLogListByService_Select(String ServiceID,int Status,@RequestParam(defaultValue = "1")int Pageindex, @RequestParam(defaultValue = "10")int Pagesize) {
        IPage page=new Page(Pageindex,Pagesize);
        IPage<Map<String,Object>> list=serviceService.ServiceLogListByService_Select(page,ServiceID,Status);
        return Result.ok(list);
    }

    @ApiOperation(value = "查看原因", notes = "查看原因")
    @RequestMapping(value = "/ServiceLogReason_Select", method = {RequestMethod.GET})
    public Result ServiceLogReason_Select(String ServiceLogID) {
        QueryWrapper<SServicelog> wrapper=new QueryWrapper<>();
        wrapper.select("FailureReasons");
        wrapper.eq("ID",ServiceLogID);
        Map<String, Object> map = servicelogService.getMap(wrapper);
        return Result.ok(map);
    }
}
