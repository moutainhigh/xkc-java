package com.tahoecn.xkc.controller.webapi.customer;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tahoecn.log.Log;
import com.tahoecn.log.LogFactory;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.service.customer.IBCustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;

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
                                                    Integer OpportunityStatus) {
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
        IPage<HashMap<String,Object>> result = customerService.customerChangePageList_Select(page,projectID,sqlWhere.toString());
        return Result.ok(result);
    }

}
