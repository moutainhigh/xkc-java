package com.tahoecn.xkc.service.customer.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.landray.sso.client.oracle.StringUtil;
import com.tahoecn.xkc.mapper.customer.BCustomerManagerMapper;
import com.tahoecn.xkc.mapper.customer.BCustomerMapper;
import com.tahoecn.xkc.model.customer.BCustomer;
import com.tahoecn.xkc.service.customer.IBCustomerManagerService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-05
 */
@Service
public class BCustomerManagerServiceImpl extends ServiceImpl<BCustomerMapper, BCustomer> implements IBCustomerManagerService {

    @Autowired
    private BCustomerManagerMapper bCustomerManagerMapper;

    @Override
    public IPage<Map<String, Object>> CustomerManagePageList_Select(IPage page, Map<String, Object> map) {

        StringBuilder sqlWhere = new StringBuilder();
        StringBuilder ClueWhere = new StringBuilder();//线索搜索条件
        StringBuilder OppWhere = new StringBuilder();//机会搜索条件
        StringBuilder Where = new StringBuilder();
        StringBuilder WhereLast = new StringBuilder();

        String ProjectID = (String)map.get("ProjectID");
        String SaleTeamID = (String)map.get("SaleTeamID");
        String type = (String)map.get("type");
        String SourceType = (String)map.get("SourceType");
        String Status = (String)map.get("Status");
        String CustomerRank = (String)map.get("CustomerRank");
        String ChannelName = (String)map.get("ChannelName");
        String CustomerName = (String)map.get("CustomerName");
        String CustomerMobile = (String)map.get("CustomerMobile");
        String ReportUserName = (String)map.get("ReportUserName");
        String ReportUserMobile = (String)map.get("ReportUserMobile");
        String SaleUserName = (String)map.get("SaleUserName");
        String CreateTime_Start = (String)map.get("CreateTime_Start");
        String CreateTime_End = (String)map.get("CreateTime_End");
        String TheFirstVisitDate_Start = (String)map.get("TheFirstVisitDate_Start");
        String TheFirstVisitDate_End = (String)map.get("TheFirstVisitDate_End");
        String ZJDF_Start = (String)map.get("ZJDF_Start");
        String ZJDF_End = (String)map.get("ZJDF_End");
        String TheLatestFollowUpDate_Start = (String)map.get("TheLatestFollowUpDate_Start");
        String TheLatestFollowUpDate_End = (String)map.get("TheLatestFollowUpDate_End");
        String IsExcel = (String)map.get("IsExcel");

        if (StringUtil.isNotNull(ProjectID))
        {

        }
        else
        {
            return null;
        }
        //销售团队
        if (StringUtil.isNotNull(SaleTeamID))
        {
            OppWhere.append("AND SM.ReceptionGroupID ='"+SaleTeamID+"'");
        }


        //渠道类型
        if (StringUtil.isNotNull(SaleTeamID) && StringUtil.isNotNull(SourceType))
        {
            if ("one".equals(type))//选中一级菜单（自有渠道）
            {
                ClueWhere.append(" AND b.SourceType IN ('80D2A7B1-115A-4F3A-BB7D-F227F641C5F1','266E0F4F-2EE1-4305-9115-49DDE2186D57','B32BB4EC-74C5-4F7C-BF85-F9A02452B8A2','3D98E549-CD23-4301-B5AD-5F1AAAFA9EE7','709CD8F1-7E1A-42B9-B4E9-6EAFB428EAEF')");//外展、外呼、拦截、圈层、外拓
                OppWhere.append(" AND d.SourceType IN ('80D2A7B1-115A-4F3A-BB7D-F227F641C5F1','266E0F4F-2EE1-4305-9115-49DDE2186D57','B32BB4EC-74C5-4F7C-BF85-F9A02452B8A2','3D98E549-CD23-4301-B5AD-5F1AAAFA9EE7','709CD8F1-7E1A-42B9-B4E9-6EAFB428EAEF')");//外展、外呼、拦截、圈层、外拓
            }
            else if ("two".equals(type))//选中一级菜单（分销中介）
            {
                ClueWhere.append(" AND b.SourceType  = 'E4DFA1D5-95F9-4D89-B754-E7CC81D58196'");//分销中介
                OppWhere.append(" AND d.SourceType  = 'E4DFA1D5-95F9-4D89-B754-E7CC81D58196'");//分销中介
            }
            else if ("three".equals(type))//选中一级菜单（推荐渠道）
            {
                ClueWhere.append(" AND b.SourceType IN ('7F4E0089-E21D-0F97-DC48-0DBF0740367D','BA06AE1D-E29A-4BC7-A811-A26E103B5E7E','798A45A6-9169-4E5C-BEE3-1CDB158F5D69')");//老业主、员工推荐、自由经纪
                OppWhere.append(" AND d.SourceType IN ('7F4E0089-E21D-0F97-DC48-0DBF0740367D','BA06AE1D-E29A-4BC7-A811-A26E103B5E7E','798A45A6-9169-4E5C-BEE3-1CDB158F5D69')");//老业主、员工推荐、自由经纪
            }
            else if ("2".equals(type))
            {
                ClueWhere.append(" AND b.ReportUserOrg='"+SourceType+"'");
                OppWhere.append(" AND d.ReportUserOrg='"+SourceType+"'");
            }
            else if ("0".equals(type))
            {
                //sqlWhere.append(" AND m.SourceType = '{0}' ", param["SourceType"].ToString());
                ClueWhere.append(" AND b.SourceType ='"+SourceType+"'");
                OppWhere.append(" AND d.SourceType ='"+SourceType+"'");
            }

        }

        //客户状态
        if (StringUtil.isNotNull(Status))
        {
            if ("11".equals(Status))//线索（报备）
                sqlWhere.append(" AND m.ClueStatus=1 AND m.OpportunityStatus=0");
            else if ("12".equals(Status))//线索（确认带看）
                sqlWhere.append(" AND m.ClueStatus= 2  AND m.OpportunityStatus=0");
            else if ("13".equals(Status))//线索（无效）
                sqlWhere.append(" AND m.ClueStatus= 3  AND m.OpportunityStatus=0");
            else if ("6".equals(Status))   //机会丢失为6  线索丢失为4
                sqlWhere.append(" AND m.ClueStatus= 4 AND m.OpportunityStatus=6 ");
            else//其余为机会的状态
                sqlWhere.append(" AND m.OpportunityStatus='"+Status+"'");
        }
        //客储等级
        if (StringUtil.isNotNull(CustomerRank))
        {

            //sqlWhere.append(" AND m.CustomerRank = '{0}' ", param["CustomerRank"]);
            ClueWhere.append(" AND b.CustomerRank = '"+CustomerRank+"'");
            OppWhere.append(" AND b.CustomerRank =  '"+CustomerRank+"'");
        }
        //渠道人所属机构
        if (StringUtil.isNotNull(ChannelName))
        {
            sqlWhere.append(" AND m.ChannelName = '"+ChannelName+"'");
        }
        //客户姓名
        if (StringUtil.isNotNull(CustomerName))
        {
            //sqlWhere.append(" AND m.CustomerName like '%{0}%' ", param["CustomerName"]);
            ClueWhere.append(" AND b.Name like '%"+CustomerName+"%'");
            OppWhere.append(" AND b.CustomerName like '%"+CustomerName+"%'");
        }
        //客户手机号
        if (StringUtil.isNotNull(CustomerMobile))
        {
            //sqlWhere.append(" AND m.CustomerMobile like '%{0}%' ", param["CustomerMobile"]);
            ClueWhere.append(" AND b.Mobile like '%"+CustomerMobile+"%' ");
            OppWhere.append(" AND b.CustomerMobile like '%"+CustomerMobile+"%'");
        }
        //报备人姓名
        if (StringUtil.isNotNull(ReportUserName))
        {
            //sqlWhere.append(" AND m.ReportUserName like '%{0}%' ", param["ReportUserName"]);
            ClueWhere.append(" AND b.ReportUserName like '%"+ReportUserName+"%' ");
            OppWhere.append(" AND d.ReportUserName like '%"+ReportUserName+"%' ");
        }
        //报备人手机号
        if (StringUtil.isNotNull(ReportUserMobile))
        {
            //sqlWhere.append(" AND m.ReportUserMobile like '%{0}%' ", param["ReportUserMobile"]);
            ClueWhere.append(" AND b.ReportUserMobile like '%"+ReportUserMobile+"%' ");
            OppWhere.append(" AND d.ReportUserMobile like '%"+ReportUserMobile+"%' ");
        }
        //置业顾问名称
        if (StringUtil.isNotNull(SaleUserName))
        {
            //sqlWhere.append(" AND m.SaleUserName = '{0}' ", param["SaleUserName"]);
            OppWhere.append(" AND b.SaleUserName = '"+SaleUserName+"'");
            ClueWhere.append(" AND b.ID IS NULL --查询置业顾问  不需要线索， 过滤掉所有线索");
        }
        //创建时间
        if (StringUtil.isNotNull(CreateTime_Start))
        {
            sqlWhere.append(" and  m.CreateTime>='"+CreateTime_Start+"'");
        }
        //创建时间
        if (StringUtil.isNotNull(CreateTime_End))
        {
            sqlWhere.append(" and  m.CreateTime<='"+CreateTime_End+"'");
        }
        //首访时间TheFirstVisitDate_Start
        if (StringUtil.isNotNull(TheFirstVisitDate_Start))
        {
            sqlWhere.append(" and  m.TheFirstVisitDate>='"+TheFirstVisitDate_Start+"'");
        }
        //首访时间
        if (StringUtil.isNotNull(TheFirstVisitDate_End))
        {
            sqlWhere.append(" and  m.TheFirstVisitDate<='"+TheFirstVisitDate_End+"'");
        }
        //最近到访时间
        if (StringUtil.isNotNull(ZJDF_Start))
        {
            Where.append(" and  CreateTime>='"+ZJDF_Start+"'");
            WhereLast.append("");
        }
        //最近到访时间
        if (StringUtil.isNotNull(ZJDF_End))
        {
            Where.append(" and  CreateTime<='"+ZJDF_End+"'");
            WhereLast.append(" AND s.ZJDF IS NOT NULL AND s.ZJDF<>''");
        }
        //跟进时间--开始
        if (StringUtil.isNotNull(TheLatestFollowUpDate_Start))
        {
            sqlWhere.append(" AND m.TheLatestFollowUpDate >= '"+TheLatestFollowUpDate_Start+"' ");
        }

        //跟进时间--结束
        if (StringUtil.isNotNull(TheLatestFollowUpDate_End))
        {
            sqlWhere.append(" AND m.TheLatestFollowUpDate <= '"+TheLatestFollowUpDate_End+"' ");
        }

        map.put("Where",Where.toString());
        map.put("sqlWhere",sqlWhere.toString());
        map.put("ClueWhere",ClueWhere.toString());
        map.put("OppWhere",OppWhere.toString());
        map.put("WhereLast",WhereLast.toString());

        if (StringUtil.isNotNull(IsExcel) && IsExcel.equals("1"))
        {
            return bCustomerManagerMapper.SetExcelToCustomerChange_BakList(map,sqlWhere);
        }
        return bCustomerManagerMapper.CustomerManagePageList_Select(page,map);
    }
}
