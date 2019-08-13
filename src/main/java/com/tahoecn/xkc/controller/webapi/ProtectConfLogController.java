package com.tahoecn.xkc.controller.webapi;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.mapper.ipad.IpadMapper;
import com.tahoecn.xkc.model.rule.ProtectConfLog;
import com.tahoecn.xkc.service.rule.IProtectConfLogService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
 * @since 2019-08-13
 */
@RestController
@RequestMapping("/webapi/protectConfLog")
public class ProtectConfLogController extends TahoeBaseController {

    @Autowired
    private IProtectConfLogService protectConfLogService;

    @ApiOperation(value = "获取服务列表", notes = "获取服务列表")
    @RequestMapping(value = "/protectConfLogSelect", method = {RequestMethod.GET})
    public Result protectConfLogSelect(String ProjectName, String GroupDictName,Integer ProtectSource, String RuleName, String EditorName, Date Start,Date End,@RequestParam(defaultValue = "1")int Pageindex, @RequestParam(defaultValue = "10")int Pagesize) {
        IPage page=new Page(Pageindex,Pagesize);
        QueryWrapper<ProtectConfLog> wrapper=new QueryWrapper<>();
        if (StringUtils.isNotBlank(ProjectName)){
            wrapper.like("ProjectName",ProjectName);
        }
        if (StringUtils.isNotBlank(GroupDictName)){
            wrapper.like("GroupDictName",GroupDictName);
        }
        if (StringUtils.isNotBlank(RuleName)){
            wrapper.like("RuleName",RuleName);
        }
        if (StringUtils.isNotBlank(EditorName)){
            wrapper.like("EditorName",EditorName);
        }
        if (ProtectSource!=null){
            wrapper.ge("ProtectSource",ProtectSource);
        }
        if (Start!=null){
            wrapper.ge("CreateTime",ProjectName);
        }
        if (End!=null){
            wrapper.le("CreateTime",ProjectName);
        }
        IPage iPage = protectConfLogService.page(page, wrapper);
        return Result.ok(iPage);
    }

}
