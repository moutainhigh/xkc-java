package com.tahoecn.xkc.controller.webapi.report;


import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tahoecn.core.json.JSONResult;
import com.tahoecn.xkc.common.utils.ExcelUtil;
import com.tahoecn.xkc.common.utils.ThreadLocalUtils;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.channel.BChannelorg;
import com.tahoecn.xkc.model.channel.BChanneluser;
import com.tahoecn.xkc.model.channel.BPojectchannelorgrel;
import com.tahoecn.xkc.model.dto.ChannelInsertDto;
import com.tahoecn.xkc.model.rule.BClueruleAdvisergroup;
import com.tahoecn.xkc.service.channel.IBChannelorgService;
import com.tahoecn.xkc.service.channel.IBChanneluserService;
import com.tahoecn.xkc.service.channel.IBPojectchannelorgrelService;
import com.tahoecn.xkc.service.dict.ISDictionaryService;
import com.tahoecn.xkc.service.report.ReportService;
import com.tahoecn.xkc.service.rule.IBClueruleAdvisergroupService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author YYY
 * @since 2019-07-22
 */
@RestController
@RequestMapping("/webapi/report")
public class ReportController extends TahoeBaseController {


    @Autowired
    private ReportService reportService;


    @ApiOperation(value = "客储动态监测表", notes = "客储动态监测表")
    @RequestMapping(value = "/CustomerRank_Select", method = {RequestMethod.GET})
    public Result CustomerRank_Select(String orglevel, String orgID, String startDate, String endDate,String AccountID,
                                      String isSelect,String dl) {
        startDate = startDate + " 00:00:00";
        endDate = endDate + " 23:59:59";
        StringBuilder sqlWhere = new StringBuilder();
        if (StringUtils.isNotEmpty(dl)) {
            if ("1".equals(dl)){
                sqlWhere.append("SELECT * FROM kcview ORDER BY kcview.area,kcview.city,kcview.project");
                List<Map<String,Object>> result = reportService.CustomerRankNew_Select(orglevel,AccountID,startDate,endDate,sqlWhere.toString());
                CustomerRank_DownLoad(result);
            }
            if ("2".equals(dl)){
                List<Map<String,Object>> result = reportService.CustomerRankDetail_Select(orgID,startDate,endDate);
                CustomerRankDetail_DownLoad(result);
            }

            return null;
        }

        if ("4".equals(orglevel)){  //地产
            sqlWhere.append("SELECT '地产板块' Area,'' city,'' project,SUM(one) one,SUM(two) two,SUM(three) three,SUM(four) four,SUM(five) five,SUM(rg) rg,SUM(qy) qy,'BFD658A5-F645-468C-A11C-FB1689C1A166' haschildren,'5' level FROM kcview WHERE PID='BFD658A5-F645-468C-A11C-FB1689C1A166' GROUP BY PID");
        }else if ("5".equals(orglevel)) {    //区域
            if ("0".equals(isSelect)){  //展开查询
                sqlWhere.append("SELECT  Area,'' City,'' project,SUM(one) one,SUM(two) two,SUM(three) three,SUM(four) four,SUM(five) five,SUM(rg) rg,SUM(qy) qy,haschildren,'6' level FROM kcview WHERE PID='BFD658A5-F645-468C-A11C-FB1689C1A166' GROUP BY Area,haschildren");
            } else if ("1".equals(isSelect)){   //首次加载和搜索查询
                sqlWhere.append("SELECT  Area,'' City,'' project,SUM(one) one,SUM(two) two,SUM(three) three,SUM(four) four,SUM(five) five,SUM(rg) rg,SUM(qy) qy,haschildren,'6' level FROM kcview WHERE POrgID='"+orgID+"' GROUP BY Area,haschildren");
            }
        } else if ("6".equals(orglevel)){   //城市
            if ("0".equals(isSelect)){  //展开查询
                sqlWhere.append("SELECT City Area,'','' project,SUM(one) one,SUM(two) two,SUM(three) three,SUM(four) four,SUM(five) five,SUM(rg) rg,SUM(qy) qy,haschildren,'7' level FROM kcview WHERE POrgID='"+orgID+"' GROUP BY Area,city,haschildren");
            } else if ("1".equals(isSelect)){   //首次加载和搜索查询
                sqlWhere.append("SELECT City Area,'','' project,SUM(one) one,SUM(two) two,SUM(three) three,SUM(four) four,SUM(five) five,SUM(rg) rg,SUM(qy) qy,haschildren,'7' level FROM kcview WHERE COrgID='"+orgID+"' GROUP BY Area,city,haschildren");
            }
        } else if ("7".equals(orglevel)) {   //项目
            if ("0".equals(isSelect)){  //展开查询
                sqlWhere.append( "SELECT project Area,'', '',one,two,three,four,five,rg,qy,'' haschildren,'10' level FROM kcview WHERE COrgID='"+orgID+"'");
            } else if ("1".equals(isSelect)){   //首次加载和搜索查询
                sqlWhere.append( "SELECT project Area,'', '',one,two,three,four,five,rg,qy,'' haschildren,'10' level FROM kcview WHERE ProjectID='"+orgID+"'");
            }
        } else if ("9".equals(orglevel)) {   //置业顾问
            sqlWhere.append( "SELECT Area,'', '',one,two,three,four,five,rg,qy,'' haschildren,'10' level FROM kcview");
            List<Map<String,Object>> result = reportService.CustomerRankSalesNew_Select(orgID,startDate,endDate,sqlWhere.toString());
        }

        List<Map<String,Object>> result = reportService.CustomerRankNew_Select(orglevel,AccountID,startDate,endDate,sqlWhere.toString());

        return Result.ok(result);
    }


    private void CustomerRank_DownLoad(List<Map<String,Object>> result) {
        List<ExcelExportEntity> entity = new ArrayList<>();
        entity.add(new ExcelExportEntity("区域", "area"));
        entity.add(new ExcelExportEntity("城市", "city"));
        entity.add(new ExcelExportEntity("项目", "project"));
        entity.add(new ExcelExportEntity("1级", "one"));
        entity.add(new ExcelExportEntity("1.5级", "two"));
        entity.add(new ExcelExportEntity("2级", "three"));
        entity.add(new ExcelExportEntity("2.5级", "four"));
        entity.add(new ExcelExportEntity("3级", "five"));
        entity.add(new ExcelExportEntity("认购", "rg"));
        entity.add(new ExcelExportEntity("签约", "qy"));

        try {
            LocalDateTime time= LocalDateTime.now();
            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
            String name = dtf2.format(time) + ".xlsx";
            ExcelUtil.exportExcel(entity,result,name,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void CustomerRankDetail_DownLoad(List<Map<String,Object>> result) {
        List<ExcelExportEntity> entity = new ArrayList<>();
        entity.add(new ExcelExportEntity("项目", "IntentProjectName"));
        entity.add(new ExcelExportEntity("置业顾问", "SaleUserName"));
        entity.add(new ExcelExportEntity("客户姓名", "CustomerName"));
        entity.add(new ExcelExportEntity("客户手机", "CustomerMobile"));
        entity.add(new ExcelExportEntity("级别升降", "CustomerRankValue"));
        entity.add(new ExcelExportEntity("升降时间", "CreateTime"));

        try {
            LocalDateTime time= LocalDateTime.now();
            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
            String name = dtf2.format(time) + ".xlsx";
            ExcelUtil.exportExcel(entity,result,name,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
