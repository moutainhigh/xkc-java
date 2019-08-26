package com.tahoecn.xkc.controller.webapi;


import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tahoecn.xkc.common.utils.ExcelUtil;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    @ApiOperation(value = "变更保护期记录", notes = "变更保护期记录")
    @RequestMapping(value = "/protectConfLogSelect", method = {RequestMethod.GET})
    public Result protectConfLogSelect(String IsExcel,String ProjectName, String GroupDictName,Integer ProtectSource, String RuleName, String EditorName, Date Start,Date End,@RequestParam(defaultValue = "1")int Pageindex, @RequestParam(defaultValue = "10")int Pagesize) {
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
            wrapper.eq("ProtectSource",ProtectSource);
        }
        if (Start!=null){
            wrapper.ge("CreateTime", DateUtil.beginOfDay(Start));
        }
        if (End!=null){
            wrapper.le("CreateTime",DateUtil.endOfDay(End));
        }
        wrapper.orderByDesc("CreateTime");
        if (StringUtils.isNotBlank(IsExcel)){
            SetExcelN(ProjectName,  GroupDictName, ProtectSource,  RuleName,  EditorName,  Start, End);
            return null;
        }
        IPage iPage = protectConfLogService.page(page, wrapper);
        return Result.ok(iPage);
    }

    private void SetExcelN(String ProjectName, String GroupDictName,Integer ProtectSource, String RuleName, String EditorName, Date Start,Date End) {
        List<Map<String,Object>> list=protectConfLogService.getProtectConfLogList(ProjectName,GroupDictName,ProtectSource,RuleName,EditorName,DateUtil.beginOfDay(Start),DateUtil.endOfDay(End));
        String ExcelName;
        List<ExcelExportEntity> entity;

            //推荐渠道
            ExcelName = "项目保护期变更记录";
            entity = new ArrayList<ExcelExportEntity>();
            entity.add(new ExcelExportEntity("项目名称", "ProjectName"));
            entity.add(new ExcelExportEntity("所属渠道", "ProtectSource"));
            entity.add(new ExcelExportEntity("所属团队/机构", "GroupDictName"));
            entity.add(new ExcelExportEntity("保护期规则", "RuleName"));
            entity.add(new ExcelExportEntity("保护期方式", "IsSelect"));
            entity.add(new ExcelExportEntity("原保护期时长", "OriProtectDays"));
            entity.add(new ExcelExportEntity("变更保护期时长", "ChangeProtectDays"));
            entity.add(new ExcelExportEntity("变更人", "EditorName"));
            entity.add(new ExcelExportEntity("变更日期", "CreateTime"));

        try {
            LocalDateTime time= LocalDateTime.now();
            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
            String name = dtf2.format(time) + ".xls";
            ExcelUtil.exportExcel(entity,list,name,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
