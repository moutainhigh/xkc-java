package com.tahoecn.xkc.controller.webapi.customer;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tahoecn.log.Log;
import com.tahoecn.log.LogFactory;
import com.tahoecn.xkc.common.utils.ExcelUtil;
import com.tahoecn.xkc.common.utils.ExcelUtilsTest;
import com.tahoecn.xkc.common.utils.ThreadLocalUtils;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.service.customer.IBCustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static org.apache.coyote.http11.Constants.a;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YYY
 * @since 2019-06-21
 */
@Api(tags = "客户")
@RestController
@RequestMapping("/webapi/customer")
public class CustomerController extends TahoeBaseController {
    private static final Log log = LogFactory.get();

    @Autowired
    private IBCustomerService customerService;

    @ApiOperation(value = "渠道客户列表")
    @RequestMapping(value = "/CustomerChangePageList_Select", method = {RequestMethod.GET})
    public Result CustomerChangePageList_Select(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize,String projectID,
                                                    String CustomerName,
                                                    String CustomerMobile,
                                                    String ReportUserName,
                                                    String ReportUserMobile,
                                                    String CustomerRank,
                                                    Integer Status,
                                                    String type,
                                                    String SourceType,
                                                String ReportTime_Start,
                                                String ReportTime_End,
                                                String TheFirstVisitDate_Start,
                                                String TheFirstVisitDate_End,
                                                    Integer OpportunityStatus,String isExcel) {
        StringBuffer sqlWhere = new StringBuffer();


        //客户姓名
        if (StringUtils.isNotEmpty(CustomerName)) {
            sqlWhere.append(" AND t.CustomerName like '%").append(CustomerName).append("%'");
        }
        //客户手机号
        if (StringUtils.isNotEmpty(CustomerMobile)) {
            sqlWhere.append(" AND t.CustomerMobile like '%").append(CustomerName).append("%'");
        }
        //报备人姓名
        if (StringUtils.isNotEmpty(ReportUserName)) {
            sqlWhere.append(" AND t.ReportUserName like '%").append(CustomerName).append("%'");
        }
        //报备人手机号
        if (StringUtils.isNotEmpty(ReportUserMobile)) {
            sqlWhere.append(" AND t.ReportUserMobile like '%").append(CustomerName).append("%'");
        }
        //客储等级
        if (StringUtils.isNotEmpty(CustomerRank)) {
            sqlWhere.append(" AND t.CustomerRank='").append(CustomerName).append("'");
        }
        //客户状态
        if (Status != null) {
            sqlWhere.append(" AND t.Status =  ").append(Status);
        }
        //成交状态
        if (OpportunityStatus != null) {
            sqlWhere.append("  AND t.OpportunityStatus= ").append(OpportunityStatus);
        }

        //渠道类型
        if (StringUtils.isNotEmpty(type) && StringUtils.isNotEmpty(SourceType)) {
            if ("one".equals(type))//选中一级菜单（自有渠道）
                sqlWhere.append(" AND t.SourceTypeID IN ('80D2A7B1-115A-4F3A-BB7D-F227F641C5F1','266E0F4F-2EE1-4305-9115-49DDE2186D57','B32BB4EC-74C5-4F7C-BF85-F9A02452B8A2','3D98E549-CD23-4301-B5AD-5F1AAAFA9EE7','709CD8F1-7E1A-42B9-B4E9-6EAFB428EAEF')");//外展、外呼、拦截、圈层、外拓
            else if ("two".equals(type))//选中一级菜单（分销中介）
                sqlWhere.append(" AND t.SourceTypeID  = 'E4DFA1D5-95F9-4D89-B754-E7CC81D58196'");//分销中介
            else if ("three".equals(type))//选中一级菜单（推荐渠道）
                sqlWhere.append(" AND t.SourceTypeID IN ('7F4E0089-E21D-0F97-DC48-0DBF0740367D','BA06AE1D-E29A-4BC7-A811-A26E103B5E7E','798A45A6-9169-4E5C-BEE3-1CDB158F5D69')");//老业主、员工推荐、自由经纪
            else if ("2".equals(type))
                sqlWhere.append(" AND t.ReportUserOrg='").append(SourceType).append("' ");
                else if ("0".equals(type))
            sqlWhere.append(" AND t.SourceTypeID = '").append(SourceType).append("' ");
        }

        //报备时间
        if (StringUtils.isNotEmpty(ReportTime_Start)) {
            sqlWhere.append(" and  t.ReportTime>='").append(ReportTime_Start).append("'");
        }
        //报备时间
        if (StringUtils.isNotEmpty(ReportTime_End)) {
            sqlWhere.append(" and  t.ReportTime<='").append(ReportTime_End).append("'");
        }
        //首次到访
        if (StringUtils.isNotEmpty(TheFirstVisitDate_Start)) {
            sqlWhere.append(" and  t.TheFirstVisitDate>='").append(TheFirstVisitDate_Start).append("'");
        }
        //首次到访
        if (StringUtils.isNotEmpty(TheFirstVisitDate_End)) {
            sqlWhere.append(" and  t.TheFirstVisitDate<='").append(TheFirstVisitDate_End).append("'");
        }

        IPage page = new Page(pageNum,pageSize);
        IPage<Map<String,Object>> result = customerService.customerChangePageList_Select(page,projectID,sqlWhere.toString());

        if (StringUtils.isNotEmpty(isExcel)){
            customerExcel(projectID,sqlWhere.toString());
            return null;
        }
        return Result.ok(result);
    }



    private void customerExcel(String projectID, String sqlWhere){
        List<ExcelExportEntity> entity = new ArrayList<ExcelExportEntity>();
        entity.add(new ExcelExportEntity("客户姓名", "CustomerName"));
        entity.add(new ExcelExportEntity("手机号", "CustomerMobile"));
        entity.add(new ExcelExportEntity("客储等级", "CustomerRank"));
        entity.add(new ExcelExportEntity("客户状态", "Status"));
        entity.add(new ExcelExportEntity("原因", "InvalidReason"));
        entity.add(new ExcelExportEntity("项目名称", "ProjectName"));
        entity.add(new ExcelExportEntity("到访逾期时间", "ComeOverdueTime"));
        entity.add(new ExcelExportEntity("成交逾期时间", "TradeOverdueTime"));
        entity.add(new ExcelExportEntity("确认人", "ConfirmUserName"));
        entity.add(new ExcelExportEntity("确认时间", "ConfirmTime"));
        entity.add(new ExcelExportEntity("置业顾问", "SaleUserName"));
        entity.add(new ExcelExportEntity("渠道类型", "SourceType"));
        entity.add(new ExcelExportEntity("报备人", "ReportUserName"));
        entity.add(new ExcelExportEntity("报备人手机号", "ReportUserMobile"));
        entity.add(new ExcelExportEntity("报备时间", "ReportTime"));
        entity.add(new ExcelExportEntity("所属机构", "ChannelName"));
        entity.add(new ExcelExportEntity("首访时间", "TheFirstVisitDate"));
        entity.add(new ExcelExportEntity("最近到访", "ZJDF"));
        entity.add(new ExcelExportEntity("成交记录", "Room"));

        List<Map<String,Object>> result = customerService.setExcelToCustomerChangeList(projectID,sqlWhere);

        try {
            LocalDateTime time= LocalDateTime.now();
            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
            String name = dtf2.format(time) + ".xlsx";
            ExcelUtil.exportExcel(entity,result,name,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "渠道客户列表查询参数")
    @RequestMapping(value = "/GetDistributionList_Select", method = {RequestMethod.GET})
    public Result GetDistributionList_Select(String project) {
        List<Map<String,Object>> result = customerService.GetDistributionList_Select(project);
        return Result.ok(result);
    }

    @ApiOperation(value = "渠道客户变更列表")
    @RequestMapping(value = "/CustomerChange_BakPageList_Select", method = {RequestMethod.GET})
    public Result CustomerChange_BakPageList_Select(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize,String projectID,
                                                    String CustomerName,
                                                    String CustomerMobile,
                                                    Integer Status,
                                                    String ReportTime_Start,
                                                    String ReportTime_End,
                                                    String isExcel) {
        IPage page = new Page(pageNum,pageSize);
        StringBuilder sqlWhere = new StringBuilder();


        if (StringUtils.isNotEmpty(CustomerName)) {
            sqlWhere.append(" AND t.CustomerName like '%").append(CustomerName).append("%'");
        }
        
        //客户姓名
        if (StringUtils.isNotEmpty(CustomerName)) {
            sqlWhere.append(" AND t.CustomerName like '%").append(CustomerName).append("%'");
        }
        //客户手机号
        if (StringUtils.isNotEmpty(CustomerMobile)) {
            sqlWhere.append(" AND t.CustomerMobile like '%").append(CustomerName).append("%'");
        }
        //客户状态
        if (Status != null) {
            if (Status == 1){//无效
                sqlWhere.append(" AND t.Statu = 3 ");
            } else{//有效
                sqlWhere.append(" AND t.Statu <> 3 ");
            }
        }

        //报备时间
        if (StringUtils.isNotEmpty(ReportTime_Start)) {
            sqlWhere.append(" and  t.ReportTime>='").append(ReportTime_Start).append("'");
        }
        //报备时间
        if (StringUtils.isNotEmpty(ReportTime_End)) {
            sqlWhere.append(" and  t.ReportTime<='").append(ReportTime_End).append("'");
        }

        if (StringUtils.isNotEmpty(isExcel)){
            customerChangeExcel(projectID,sqlWhere.toString());
            return null;
        }
        
        IPage<Map<String,Object>> result = customerService.CustomerChange_BakPageList_Select(page,projectID,sqlWhere.toString());
        return Result.ok(result);
    }


    private void customerChangeExcel(String projectID, String sqlWhere){
        List<ExcelExportEntity> entity = new ArrayList<ExcelExportEntity>();
        entity.add(new ExcelExportEntity("客户姓名", "CustomerName"));
        entity.add(new ExcelExportEntity("手机号", "CustomerMobile"));
        entity.add(new ExcelExportEntity("客储等级", "CustomerRank"));
        entity.add(new ExcelExportEntity("客户状态", "Status"));
        entity.add(new ExcelExportEntity("原因", "InvalidReason"));
        entity.add(new ExcelExportEntity("项目名称", "ProjectName"));
        entity.add(new ExcelExportEntity("到访逾期时间", "ComeOverdueTime"));
        entity.add(new ExcelExportEntity("成交逾期时间", "TradeOverdueTime"));
        entity.add(new ExcelExportEntity("确认人", "ConfirmUserName"));
        entity.add(new ExcelExportEntity("确认时间", "ConfirmTime"));
        entity.add(new ExcelExportEntity("置业顾问", "SaleUserName"));
        entity.add(new ExcelExportEntity("渠道类型", "SourceType"));
        entity.add(new ExcelExportEntity("报备人", "ReportUserName"));
        entity.add(new ExcelExportEntity("报备人手机号", "ReportUserMobile"));
        entity.add(new ExcelExportEntity("报备时间", "ReportTime"));
        entity.add(new ExcelExportEntity("所属机构", "ChannelName"));
        entity.add(new ExcelExportEntity("首访时间", "TheFirstVisitDate"));
        entity.add(new ExcelExportEntity("最近到访", "ZJDF"));
        entity.add(new ExcelExportEntity("认筹时间", "RCTime"));
        entity.add(new ExcelExportEntity("认购时间", "RGTime"));
        entity.add(new ExcelExportEntity("签约时间", "QYTime"));

        List<Map<String,Object>> result = customerService.SetExcelToCustomerChange_BakList(projectID,sqlWhere);

        try {
            LocalDateTime time= LocalDateTime.now();
            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
            String name = dtf2.format(time) + ".xlsx";
            ExcelUtil.exportExcel(entity,result,name,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @ApiOperation(value = "渠道客户列表详情")
    @RequestMapping(value = "/CustomerChangeDetailAll_Select", method = {RequestMethod.GET})
    public Result CustomerChangeDetailAll_Select(String projectId,String customerID,String clueID) {
        Map<String,Object> result = customerService.CustomerChangeDetailAll_Select(projectId,customerID,clueID);
        return Result.ok(result);
    }

    @ApiOperation(value = "归属变更查询")
    @RequestMapping(value = "/SourceTypeChangeList_Select", method = {RequestMethod.GET})
    public Result SourceTypeChangeList_Select(String ReportUserName,String ReportUserMobile,String Mobile,String projectID,String ClueID) {
        StringBuilder sqlWhere = new StringBuilder();

        //客户姓名
        if (StringUtils.isNotEmpty(ReportUserName)) {
            sqlWhere.append(" AND b.ReportUserName like '%").append(ReportUserName).append("%'");
        }
        //客户手机号
        if (StringUtils.isNotEmpty(ReportUserMobile)) {
            sqlWhere.append(" AND b.ReportUserMobile like '%").append(ReportUserMobile).append("%'");
        }

        List<Map<String,Object>> result = customerService.SourceTypeChangeList_Select(Mobile,projectID,sqlWhere.toString(),ClueID);
        return Result.ok(result);
    }

    @ApiOperation(value = "pc渠道变更")
    @RequestMapping(value = "/SourceTypeChangeDetail_Update", method = {RequestMethod.POST})
    public Result SourceTypeChangeDetail_Update(String OldClueID,String ClueID,String UserID,String Reason,String Enclosure) {
        //UserID = ThreadLocalUtils.getUserId();
        customerService.SourceTypeChangeDetail_Update(OldClueID,ClueID,UserID,Reason,Enclosure);
        return Result.ok("成功");
    }

    @ApiOperation(value = "延长保护期")
    @RequestMapping(value = "/ExtendProtectDetail_Update", method = {RequestMethod.POST})
    public Result ExtendProtectDetail_Update(String ClueID,String ProtectNum,String Reason,String Enclosure,String UserID) {
        customerService.ExtendProtectDetail_Update(ProtectNum,ClueID,UserID,Reason,Enclosure);
        return Result.ok("成功");
    }

    @ApiOperation(value = "设为无效")
    @RequestMapping(value = "/SetInvalidDetail_Update", method = {RequestMethod.POST})
    public Result SetInvalidDetail_Update(String ClueID,String Reason,String Enclosure,String UserID) {
        customerService.SetInvalidDetail_Update(ClueID,Reason,Enclosure,UserID);
        return Result.ok("成功");
    }

    @ApiOperation(value = "变更渠道来源")
    @RequestMapping(value = "/ChangeSourceTypeDetail_Update", method = {RequestMethod.POST})
    public Result ChangeSourceTypeDetail_Update(String clueID, String SourceType, String reason, String enclosure,String userID,String type){
        customerService.ChangeSourceTypeDetail_Update(clueID, SourceType, reason, enclosure,userID,type);
        return Result.ok("成功");
    }

    @ApiOperation(value = "变更认知媒体")
    @RequestMapping(value = "/ChangeCognitiveChannelDetail_Update", method = {RequestMethod.POST})
    public Result ChangeCognitiveChannelDetail_Update(String clueID, String CognitiveChannel, String reason, String enclosure,String userID,String type){
        customerService.ChangeCognitiveChannelDetail_Update(clueID, CognitiveChannel, reason, enclosure,userID,type);
        return Result.ok("成功");
    }

    @ApiOperation(value = "带看确认单打印列表")
    @RequestMapping(value = "/CustomerGuidePageList_Select", method = {RequestMethod.GET})
    public Result CustomerGuidePageList_Select(String ProjectID, String CustomerMobile, String ReceptionPlace, String Status,
                                               String PrintStatus,String DFSJ,String IsExcel,@RequestParam(defaultValue = "1") Integer pageNum,
                                               @RequestParam(defaultValue = "10") Integer pageSize,String checkarr[]){

        StringBuilder sqlWhere = new StringBuilder();
        if (StringUtils.isNotEmpty(ProjectID)) {
            sqlWhere.append(" AND o.ProjectID = '").append(ProjectID).append("'");
        }else{
            return Result.ok("");
        }

        //客户手机号
        if (StringUtils.isNotEmpty(CustomerMobile)) {
            sqlWhere.append(" AND o.CustomerMobile like '%").append(CustomerMobile).append("%'");
        }

        //接待地点
        if (StringUtils.isNotEmpty(ReceptionPlace)) {
            sqlWhere.append(" AND ca.VisitAddress like '%").append(ReceptionPlace).append("%'");
        }

        //到访时间
        if (StringUtils.isNotEmpty(DFSJ)) {
            switch (DFSJ) {
                case "1"://近3天
                    sqlWhere.append(" AND datediff(day,ca.VisitTime,getdate())<= 3 and datediff(day,ca.VisitTime,getdate())>= 0 ");
                    break;
                case "2"://近5天
                    sqlWhere.append(" AND datediff(day,ca.VisitTime,getdate())<= 5 and datediff(day,ca.VisitTime,getdate())>= 0 ");
                    break;
                case "3"://近10天
                    sqlWhere.append(" AND datediff(day,ca.VisitTime,getdate())<= 10 and datediff(day,ca.VisitTime,getdate())>= 0 ");
                    break;
                case "4"://近一月
                    sqlWhere.append("  AND datediff(day,ca.VisitTime,getdate())<= 30 and datediff(day,ca.VisitTime,getdate())>= 0 ");
                    break;
            }
        }

        //打印状态
        if (StringUtils.isNotEmpty(PrintStatus)) {
            //已经打印
            if (Objects.equals(PrintStatus, "1")) {
                sqlWhere.append(" AND ISNULL(ca.SerialNumber,'') <> '' ");
            }
            //未打印
            else {
                sqlWhere.append("  AND ISNULL(ca.SerialNumber,'') = '' ");
            }
        }

        //状态
        if (StringUtils.isNotEmpty(Status)) {
            if (Objects.equals(Status, "1")) {
                sqlWhere.append(" AND o.Status<>6 ");
            } else {
                sqlWhere.append(" AND o.Status=6 ");
            }
        }
        if (StringUtils.isNotEmpty(IsExcel)){
            SetExcelToCustomerGuideList(checkarr,sqlWhere.toString());
            return null;
        }
        IPage<Map<String,Object>> result = customerService.CustomerGuidePageList_Select(pageNum,pageSize,sqlWhere.toString());
        return Result.ok(result);
    }

    private void SetExcelToCustomerGuideList(String[] checkarr,String sqlWhere){

        List<Map<String, Object>> result;
        if (checkarr != null && checkarr.length != 0) {
            StringBuilder arr = new StringBuilder();
            arr.append(" and  ca.ID IN ( ");
            for (int i = 0; i < checkarr.length; i++) {
                if (i + 1 == checkarr.length) {
                    arr.append("'").append(checkarr[i]).append("'");
                } else {
                    arr.append("'").append(checkarr[i]).append("'").append(",");
                }
            }
            arr.append(" )");
            result = customerService.CustomerGuideDownLoadByIDList_Select(arr.toString());
        } else {
            result = customerService.CustomerGuideDownLoadList_Select(sqlWhere);
        }

        List<ExcelExportEntity> entity = new ArrayList<ExcelExportEntity>();
        entity.add(new ExcelExportEntity("客户姓名", "CustomerName"));
        entity.add(new ExcelExportEntity("手机号", "CustomerMobile"));
        entity.add(new ExcelExportEntity("客储等级", "CustomerRank"));
        entity.add(new ExcelExportEntity("客户状态", "Status"));
        entity.add(new ExcelExportEntity("渠道来源", "SourceType"));
        entity.add(new ExcelExportEntity("所属机构", "OrgName"));
        entity.add(new ExcelExportEntity("渠道人员", "ReportUserName"));
        entity.add(new ExcelExportEntity("置业顾问", "SaleUserName"));
        entity.add(new ExcelExportEntity("协作人", "SalePartnerName"));
        entity.add(new ExcelExportEntity("报备时间", "ReportTime"));
        entity.add(new ExcelExportEntity("首次到访时间", "TheFirstVisitDate"));
        entity.add(new ExcelExportEntity("最近到访时间", "TheLatestVisitDate"));
        entity.add(new ExcelExportEntity("最近跟进时间", "TheLatestFollowUpDate"));
        entity.add(new ExcelExportEntity("分配地", "VisitAddress"));
        entity.add(new ExcelExportEntity("流水号", "SerialNumber"));
        entity.add(new ExcelExportEntity("打印状态", "PrintStatus"));


        try {
            LocalDateTime time= LocalDateTime.now();
            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
            String name = dtf2.format(time) + ".xlsx";
            ExcelUtil.exportExcel(entity,result,name,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "带看确认单列表-详情")
    @RequestMapping(value = "/CustomerNEWDetailAll_Select", method = {RequestMethod.GET})
    public Result CustomerNEWDetailAll_Select(String ProjectID, String CustomerID, String ReportUserID, String OpportunityID){
        Map<String,Object> result = customerService.CustomerNEWDetailAll_Select(ProjectID, CustomerID, ReportUserID, OpportunityID);
        return Result.ok(result);
    }

    @ApiOperation(value = "带看确认单打印")
    @RequestMapping(value = "/CustomerGuidePrintDetail_Select", method = {RequestMethod.POST})
    public Result CustomerGuidePrintDetail_Select(String ProjectID, String UserID, String ID){
        Map<String,Object> result = customerService.CustomerGuidePrintDetail_Select(ProjectID, UserID, ID);
        StringBuilder OpenHtml = new StringBuilder();
        StringBuilder html = new StringBuilder();
        if (result.get("CustomerGuideTemplate")!=null&& !Objects.equals(result.get("CustomerGuideTemplate").toString(), "") &&
                result.get("Rise") != null && !Objects.equals(result.get("Rise").toString(), "") &&
                result.get("SerialNumber") != null && !Objects.equals(result.get("SerialNumber").toString(), "")){
            JSONArray jsonObject = JSONObject.parseArray(result.get("CustomerGuideTemplate").toString());
            jsonObject = jsonObject.stream().filter(a-> ((JSONObject) a).getString("IsCheck").equals("1")).collect(Collectors.toCollection(JSONArray::new));
            jsonObject.forEach(o ->{
                switch (((JSONObject) o).getString("title")){
                    case "客户信息":
                        ((JSONObject) o).put("Name", (result.get("CustomerName") == null ? "" : result.get("CustomerName")) + "(" + (result.get("CustomerMobile") == null ? "" : result.get("CustomerMobile")) + ")");
                        break;
                    case "渠道信息":
                        ((JSONObject) o).put("Name", (result.get("SourceType") == null ? "" : result.get("SourceType")) + "-" + (result.get("OrgName") == null ? "" : result.get("OrgName").toString()) + "-" + (result.get("ReportUserName") == null ? "" : result.get("ReportUserName").toString()) + "-" + (result.get("ReportUserMobile") == null ? "" : result.get("ReportUserMobile").toString()));
                        break;
                    case "流水号":
                        ((JSONObject) o).put("Name", result.get("Rise").toString() + result.get("Mark").toString() + result.get("SerialNumberText").toString());
                        break;
                    default:
                        ((JSONObject) o).put("Name", result.get(((JSONObject) o).getString("key")) == null ? "" : result.get(((JSONObject) o).getString("key")).toString());
                        break;
                }
            });


            OpenHtml.append("<div id='PrintContent' runat='server'>");
            html.append("<div class='listBox'>");
            html.append("<h3>");
            html.append("<span class='title'>带看确认单</span>");
            JSONObject SerialNumberText = (JSONObject)jsonObject.stream().filter(a-> ((JSONObject) a).getString("IsCheck").equals("1") && ((JSONObject) a).getString("key").equals("SerialNumber")).findFirst().get();
            if (SerialNumberText != null){
                html.append("<span class='serial'>").append(SerialNumberText.get("title")).append(":").append(SerialNumberText.get("Name")).append("</span>");
                jsonObject.remove(SerialNumberText);
            }
            html.append("</h3>");
            html.append("<ul class='clearfix'>");
            jsonObject.forEach(o ->{
                html.append("<li>");
                html.append("<span>").append(((JSONObject) o).get("title")).append("：</span>");
                html.append("<span>").append(((JSONObject) o).get("Name")).append(" </span>");
                html.append("</li>");
            });
            html.append("</ul>");
            html.append("</div>");


            result.put("CustomerInfoText", (result.get("CustomerName") == null ? "" : result.get("CustomerName")) + "(" + (result.get("CustomerMobile") == null ? "" : result.get("CustomerMobile")) + ")");
            result.put("ChannelInfoText", (result.get("SourceType") == null ? "" : result.get("SourceType").toString()) + "-" + (result.get("OrgName") == null ? "" : result.get("OrgName").toString()) + "-" + (result.get("ReportUserName") == null ? "" : result.get("ReportUserName").toString()) + "-" + (result.get("ReportUserMobile") == null ? "" : result.get("ReportUserMobile").toString()));
            result.put("FirstSalesUserText", (result.get("FirstSalesUser") == null ? "" : result.get("FirstSalesUser")));
            result.put("PartnerText", (result.get("Partner") == null ? "" : result.get("Partner")));
            result.put("ReportTimeText", (result.get("ReportTime") == null ? "" : result.get("ReportTime")));
            result.put("FirstTimeText", (result.get("FirstVisitTime") == null ? "" : result.get("FirstVisitTime")));
            result.put("RevisitTimeText", (result.get("ReVisitTime") == null ? "" : result.get("ReVisitTime")));
            result.put("PrintingTimeText", (result.get("PrintingTime") == null ? "" : result.get("PrintingTime")));
            result.put("PrintingAddressText", (result.get("PrintingAddress") == null ? "" : result.get("PrintingAddress")));
            result.put("PrintingUserText", (result.get("PrintingUser") == null ? "" : result.get("PrintingUser")));
            result.put("RiseText", (result.get("Rise") == null ? "" : result.get("Rise")));
            result.put("MarkText", (result.get("Mark") == null ? "" : result.get("Mark")));
            result.put("SerialNumberText",(result.get("SerialNumberText") == null ? "" : result.get("SerialNumberText")));

            customerService.CustomerGuidePrintDetail_Insert(result);
            customerService.CustomerGuidePrintDetail_Update(result);
        }
        return Result.ok(OpenHtml.toString() + html.toString() + html.toString() + "</div>");
    }

}
