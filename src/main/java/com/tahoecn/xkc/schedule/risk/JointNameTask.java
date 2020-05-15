package com.tahoecn.xkc.schedule.risk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tahoecn.core.date.DateUtil;
import com.tahoecn.xkc.common.utils.RiskBatchLogUtils;
import com.tahoecn.xkc.mapper.risk.BCustomerattachMapper;
import com.tahoecn.xkc.mapper.risk.BRiskbatchlogMapper;
import com.tahoecn.xkc.mapper.risk.BRiskconfigMapper;
import com.tahoecn.xkc.mapper.risk.STrade2CstMapper;
import com.tahoecn.xkc.model.risk.BRiskbatchlog;
import com.tahoecn.xkc.model.risk.BRiskconfig;
import org.aspectj.weaver.ast.Var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @description: 联名购房任务
 * @author: 张晓东
 * @time: 2020/4/30 16:42
 */
@Component
public class JointNameTask {

    private Logger log = LoggerFactory.getLogger(JointNameTask.class);

    @Resource
    private BRiskbatchlogMapper bRiskbatchlogMapper;

    @Resource
    private BRiskconfigMapper bRiskconfigMapper;

    @Resource
    private STrade2CstMapper sTrade2CstMapper;

    @Resource
    private BCustomerattachMapper bCustomerattachMapper;


    @Transactional
    public void task() {


        // 查询是否存在运行中的联名购房批任务, 存在直接返回
        List<BRiskbatchlog> runs = this.bRiskbatchlogMapper.selectList(new QueryWrapper<BRiskbatchlog>() {{
            eq("RiskType", 4);
            eq("Status", 1);
        }});
        if (runs != null && runs.size() > 0) {
            log.error("fk bacth error, exist running task, cron:{}", DateUtil.now());
            return;
        }
        // 记录任务运行状态
        BRiskbatchlog bRiskbatchlog = RiskBatchLogUtils.start(0);
        this.bRiskbatchlogMapper.insert(bRiskbatchlog);

        try {
            // 查询已完成中,数据时间最大的防截客数据, 存在直接使用, 不存在为初始跑批
            List<BRiskbatchlog> bRiskbatchlogs = this.bRiskbatchlogMapper.selectList(new QueryWrapper<BRiskbatchlog>() {{
                eq("RiskType", 4);
                eq("Status", 2);
                orderByDesc("DataMaxTime");
            }});

            // 风控项目配置
            Map<String, BRiskconfig> bRiskconfigMap = this.bRiskconfigMapper.selectList(new QueryWrapper<BRiskconfig>() {{
                eq("Type", 1);
                eq("IsDel", 0);
                eq("Status", 1);
            }}).stream().collect(Collectors.toMap(BRiskconfig::getProjectID, i -> i));

            Date startTime = DateUtil.yesterday();
            if (runs != null && bRiskbatchlogs.size() > 0) {
                startTime = bRiskbatchlogs.get(0).getDataMaxTime();
            }
            this.bRiskbatchlogMapper.updateById(RiskBatchLogUtils.running(bRiskbatchlog));
            AtomicReference<Date> dataMaxTime = new AtomicReference<>(DateUtil.date());

            Map<String, List<Map<String, Object>>> searchJointName = this.sTrade2CstMapper.
                    searchJointName(startTime, DateUtil.date()).stream()
                    .collect(Collectors.groupingBy(i -> i.get("RoomGUID").toString()));

            searchJointName.forEach((k, v) -> {
                record(v, bRiskconfigMap, dataMaxTime);
            });

            searchJointName.keySet().stream().forEach(i -> {
                String masterId = UUID.randomUUID().toString();
                searchJointName.get(i).stream().forEach(i1 -> {
                    dataMaxTime.set(serchMaxTime(i1, dataMaxTime.get()));//获取数据最大时间
                });
            });
            this.bRiskbatchlogMapper.updateById(RiskBatchLogUtils.success(bRiskbatchlog, dataMaxTime.get()));
        } catch (Exception e) {
            log.error("fk batcg error : ProtectCustomerTask : {}", e.getMessage());
            this.bRiskbatchlogMapper.updateById(RiskBatchLogUtils.failure(bRiskbatchlog, e.getMessage()));
        }

    }

    private void record(List<Map<String, Object>> v, Map<String, BRiskconfig> bRiskconfigMap,
                        AtomicReference<Date> dataMaxTime) {

        if (null != v && v.size() > 0) {
            Object[] ID = new Object[v.size()];
            Object[] ProjectID = new Object[v.size()];
            Object[] ClueID = new Object[v.size()];
            Object[] Name = new Object[v.size()];
            Object[] CustomerName = new Object[v.size()];
            Object[] CustomerMobile = new Object[v.size()];
            Object[] CustomerStatus = new Object[v.size()];
            Object[] CustomerStatusName = new Object[v.size()];
            Object[] ReportUserID = new Object[v.size()];
            Object[] ReportUserName = new Object[v.size()];
            Object[] AdviserGroupID = new Object[v.size()];
            Object[] AdviserGroupName = new Object[v.size()];
            Object[] ReportTime = new Object[v.size()];
            Object[] TheFirstVisitDate = new Object[v.size()];
            Object[] SaleUserID = new Object[v.size()];
            Object[] SaleUserName = new Object[v.size()];
            Object[] CityId = new Object[v.size()];
            Object[] CityName = new Object[v.size()];
            Object[] RegionalId = new Object[v.size()];
            Object[] RegionalName = new Object[v.size()];
            Object[] IsPreIntercept = new Object[v.size()];
            Object[] PreInterceptTime = new Object[v.size()];
            Object[] CreateTime = new Object[v.size()];
            Object[] OrgId = new Object[v.size()];
            Object[] OpportunitySource = new Object[v.size()];
            for (int i = 0; i < v.size(); i++) {
                Map<String, Object> xsOppGuid = bCustomerattachMapper.fkJointName(v.get(i).get("xsOppGuid").toString());
                ID[i] = xsOppGuid.get("ID");
                ProjectID[i] = xsOppGuid.get("ProjectID");
                ClueID[i] = xsOppGuid.get("ClueID");
                Name[i] = xsOppGuid.get("Name");
                CustomerName[i] = xsOppGuid.get("CustomerName");
                CustomerMobile[i] = xsOppGuid.get("CustomerMobile");
                CustomerStatus[i] = xsOppGuid.get("CustomerStatus");
                CustomerStatusName[i] = xsOppGuid.get("CustomerStatusName");
                ReportUserID[i] = xsOppGuid.get("ReportUserID");
                ReportUserName[i] = xsOppGuid.get("ReportUserName");
                AdviserGroupID[i] = xsOppGuid.get("AdviserGroupID");
                AdviserGroupName[i] = xsOppGuid.get("AdviserGroupName");
                ReportTime[i] = xsOppGuid.get("ReportTime");
                TheFirstVisitDate[i] = xsOppGuid.get("TheFirstVisitDate");
                SaleUserID[i] = xsOppGuid.get("SaleUserID");
                SaleUserName[i] = xsOppGuid.get("SaleUserName");
                CityId[i] = xsOppGuid.get("CityId");
                CityName[i] = xsOppGuid.get("CityName");
                RegionalId[i] = xsOppGuid.get("RegionalId");
                RegionalName[i] = xsOppGuid.get("RegionalName");
                IsPreIntercept[i] = xsOppGuid.get("IsPreIntercept");
                PreInterceptTime[i] = xsOppGuid.get("PreInterceptTime");
                CreateTime[i] = xsOppGuid.get("CreateTime");
                OrgId[i] = xsOppGuid.get("OrgId");
                OpportunitySource[i] = xsOppGuid.get("OpportunitySource");
            }
            if (ProjectID.length > 0 && bRiskconfigMap.containsKey(ProjectID[0].toString())
                    && bRiskconfigMap.get(ProjectID[0].toString()).getIsJointName() == 1) {

            }
        }


            /*bRiskinfoMapper.insert(new BRiskinfo() {{
                setId(UUID.randomUUID().toString());
                setRiskConfigId(bRiskconfigMap.get(i.get("ProjectID")).getId());
                setRegionalId((String)i.get("RegionalId"));
                setRegionalName((String)i.get("RegionalName"));
                setCityId((String)i.get("CityId"));
                setCityName((String)i.get("CityName"));
                setProjectId((String)i.get("ProjectID"));
                setProjectName((String)i.get("Name"));
                setRiskType(2);
                setCreateTime(DateUtil.date());
                setClueId((String)i.get("ClueID"));
                setOpportunityId((String)i.get("ID"));
                setCustomerName((String)i.get("CustomerName"));
                setCustomerMobile((String)i.get("CustomerMobile"));
                setCustomerStatus(i.get("CustomerStatus") != null ? Integer.valueOf(i.get("CustomerStatus").toString()): null);
                setCustomerStatusName((String)i.get("CustomerStatusName"));
                setReportUserID((String)i.get("ReportUserID"));
                setReportUserName((String)i.get("ReportUserName"));
                setAdviserGroupID((String)i.get("AdviserGroupID"));
                setAdviserGroupName((String)i.get("AdviserGroupName"));
                setReportTime((Date) i.get("ReportTime"));
                setTheFirstVisitDate((Date) i.get("TheFirstVisitDate"));
                setSaleUserID((String)i.get("SaleUserID"));
                setSaleUserName((String)i.get("SaleUserName"));
                setOrgId((String)i.get("OrgId"));
                setOpportunitySource((String)i.get("OpportunitySource"));
                setRiskDesc(
                        new StringBuilder("存在搜电未报备风险, 项目名为:{")
                                .append(i.get("Name"))
                                .append("}, 搜索手机号为:{")
                                .append(i.get("CustomerMobile"))
                                .append("}, 搜索次数为:{")
                                .append(fkSearchMobileInfos.size())
                                .append("}")
                                .toString()
                );
            }});*/

    }

    private Date serchMaxTime(Map<String, Object> i, Date date) {
        Date result = (Date) i.get("QSDate");
        if (date != null) {
            result = result.getTime() > date.getTime() ? result : date;
        }
        return result;
    }
}
