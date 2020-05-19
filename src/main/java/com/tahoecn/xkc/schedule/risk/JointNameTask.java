package com.tahoecn.xkc.schedule.risk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tahoecn.core.date.DateUtil;
import com.tahoecn.xkc.common.utils.RiskBatchLogUtils;
import com.tahoecn.xkc.mapper.risk.*;
import com.tahoecn.xkc.model.risk.BRiskbatchlog;
import com.tahoecn.xkc.model.risk.BRiskconfig;
import com.tahoecn.xkc.model.risk.BRiskinfo;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.weaver.ast.Var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collector;
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

    @Resource
    private BRiskinfoMapper bRiskinfoMapper;

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

            this.bRiskbatchlogMapper.updateById(RiskBatchLogUtils.success(bRiskbatchlog, dataMaxTime.get()));
        } catch (Exception e) {
            log.error("fk batcg error : ProtectCustomerTask : {}", e.getMessage());
            this.bRiskbatchlogMapper.updateById(RiskBatchLogUtils.failure(bRiskbatchlog, e.getMessage()));
        }

    }

    private void record(List<Map<String, Object>> v, Map<String, BRiskconfig> bRiskconfigMap, AtomicReference<Date> dataMaxTime) {
        if (null != v && v.size() > 1) {
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
                dataMaxTime.set(serchMaxTime(v.get(i), dataMaxTime.get()));//获取数据最大时间
                Map<String, Object> xsOppGuid = bCustomerattachMapper.fkJointNameOrShortDeal(v.get(i).get("xsOppGuid").toString());
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
            if (bRiskconfigMap.containsKey(ProjectID[0])
                    && bRiskconfigMap.get(ProjectID[0]).getIsJointName() == 1) {
                Integer jointNameType = bRiskconfigMap.get(ProjectID[0]).getJointNameType();

                if (null != jointNameType && jointNameType == 0) {
                    boolean flag = false;
                    Object obj = null;
                    for (Object o : AdviserGroupID) {
                        if (null ==  obj) {
                            obj = o;
                        } else if (!flag) {
                            flag = !obj.equals(o);
                        }
                    }
                    if (flag) {
                        String masterId = UUID.randomUUID().toString();
                        for (int i = 0; i < ID.length; i++) {
                            insertData(bRiskconfigMap, masterId, (Date)v.get(i).get("QSDate"),ID[i],ProjectID[i],ClueID[i],Name[i]
                                    ,CustomerName[i],CustomerMobile[i],CustomerStatus[i],CustomerStatusName[i]
                                    ,ReportUserID[i],ReportUserName[i],AdviserGroupID[i],AdviserGroupName[i]
                                    ,ReportTime[i],TheFirstVisitDate[i],SaleUserID[i],SaleUserName[i]
                                    ,CityId[i],CityName[i],RegionalId[i],RegionalName[i],IsPreIntercept[i]
                                    ,PreInterceptTime[i],CreateTime[i],OrgId[i],OpportunitySource[i]);
                        }
                        insertData(bRiskconfigMap, null, (Date)v.get(0).get("QSDate"), Arrays.stream(ID).map(i -> (String)i).collect(Collectors.joining(",")),
                                Arrays.stream(ProjectID).map(i -> (String)i).collect(Collectors.joining(",")),
                                Arrays.stream(ClueID).map(i -> (String)i).collect(Collectors.joining(",")),
                                Arrays.stream(Name).map(i -> (String)i).collect(Collectors.joining(",")),
                                Arrays.stream(CustomerName).map(i -> (String)i).collect(Collectors.joining(",")),
                                Arrays.stream(CustomerMobile).map(i -> (String)i).collect(Collectors.joining(",")),
                                Arrays.stream(CustomerStatus).map(i -> (String)i).collect(Collectors.joining(",")),
                                Arrays.stream(CustomerStatusName).map(i -> (String)i).collect(Collectors.joining(",")),
                                Arrays.stream(ReportUserID).map(i -> (String)i).collect(Collectors.joining(",")),
                                Arrays.stream(ReportUserName).map(i -> (String)i).collect(Collectors.joining(",")),
                                Arrays.stream(AdviserGroupID).map(i -> (String)i).collect(Collectors.joining(",")),
                                Arrays.stream(AdviserGroupName).map(i -> (String)i).collect(Collectors.joining(",")),
                                Arrays.stream(ReportTime).map(i -> (String)i).collect(Collectors.joining(",")),
                                Arrays.stream(TheFirstVisitDate).map(i -> (String)i).collect(Collectors.joining(",")),
                                Arrays.stream(SaleUserID).map(i -> (String)i).collect(Collectors.joining(",")),
                                Arrays.stream(SaleUserName).map(i -> (String)i).collect(Collectors.joining(",")),
                                Arrays.stream(CityId).map(i -> (String)i).collect(Collectors.joining(",")),
                                Arrays.stream(CityName).map(i -> (String)i).collect(Collectors.joining(",")),
                                Arrays.stream(RegionalId).map(i -> (String)i).collect(Collectors.joining(",")),
                                Arrays.stream(RegionalName).map(i -> (String)i).collect(Collectors.joining(",")),
                                Arrays.stream(IsPreIntercept).map(i -> (String)i).collect(Collectors.joining(",")),
                                Arrays.stream(PreInterceptTime).map(i -> (String)i).collect(Collectors.joining(",")),
                                Arrays.stream(CreateTime).map(i -> (String)i).collect(Collectors.joining(",")),
                                Arrays.stream(OrgId).map(i -> (String)i).collect(Collectors.joining(",")),
                                Arrays.stream(OpportunitySource).map(i -> (String)i).collect(Collectors.joining(",")));
                    }
                } else if (null != jointNameType && jointNameType == 1) {
                    //获取案场报备索引
                    Integer index = null;
                    int count = 0 ;
                    for (int i = 0; i < AdviserGroupID.length; i++) {
                        if (StringUtils.isEmpty((String)AdviserGroupID[i])) {
                            index = i;
                            count++;
                        }
                    }
                    if (count < v.size() && null != index) {
                        //循环判断案场报备时间是否小于渠道报备时间
                        boolean flag = false;
                        for (int i = 0; i < ReportTime.length; i++) {
                            if (i != index)  {
                                flag = ((Date)ReportTime[i]).getTime() < ((Date)ReportTime[index]).getTime();
                            }
                        }
                        //案场时间大于渠道时间为风险数据
                        if (flag) {
                            String masterId = UUID.randomUUID().toString();
                            for (int i = 0; i < ID.length; i++) {
                                insertData(bRiskconfigMap, masterId, (Date)v.get(i).get("QSDate"),ID[i],ProjectID[i],ClueID[i],Name[i]
                                        ,CustomerName[i],CustomerMobile[i],CustomerStatus[i],CustomerStatusName[i]
                                        ,ReportUserID[i],ReportUserName[i],AdviserGroupID[i],AdviserGroupName[i]
                                        ,ReportTime[i],TheFirstVisitDate[i],SaleUserID[i],SaleUserName[i]
                                        ,CityId[i],CityName[i],RegionalId[i],RegionalName[i],IsPreIntercept[i]
                                        ,PreInterceptTime[i],CreateTime[i],OrgId[i],OpportunitySource[i]);
                            }
                            insertData(bRiskconfigMap, null, (Date)v.get(0).get("QSDate"), Arrays.stream(ID).map(i -> (String)i).collect(Collectors.joining(",")),
                                    Arrays.stream(ProjectID).map(i -> (String)i).collect(Collectors.joining(",")),
                                    Arrays.stream(ClueID).map(i -> (String)i).collect(Collectors.joining(",")),
                                    Arrays.stream(Name).map(i -> (String)i).collect(Collectors.joining(",")),
                                    Arrays.stream(CustomerName).map(i -> (String)i).collect(Collectors.joining(",")),
                                    Arrays.stream(CustomerMobile).map(i -> (String)i).collect(Collectors.joining(",")),
                                    Arrays.stream(CustomerStatus).map(i -> (String)i).collect(Collectors.joining(",")),
                                    Arrays.stream(CustomerStatusName).map(i -> (String)i).collect(Collectors.joining(",")),
                                    Arrays.stream(ReportUserID).map(i -> (String)i).collect(Collectors.joining(",")),
                                    Arrays.stream(ReportUserName).map(i -> (String)i).collect(Collectors.joining(",")),
                                    Arrays.stream(AdviserGroupID).map(i -> (String)i).collect(Collectors.joining(",")),
                                    Arrays.stream(AdviserGroupName).map(i -> (String)i).collect(Collectors.joining(",")),
                                    Arrays.stream(ReportTime).map(i -> (String)i).collect(Collectors.joining(",")),
                                    Arrays.stream(TheFirstVisitDate).map(i -> (String)i).collect(Collectors.joining(",")),
                                    Arrays.stream(SaleUserID).map(i -> (String)i).collect(Collectors.joining(",")),
                                    Arrays.stream(SaleUserName).map(i -> (String)i).collect(Collectors.joining(",")),
                                    Arrays.stream(CityId).map(i -> (String)i).collect(Collectors.joining(",")),
                                    Arrays.stream(CityName).map(i -> (String)i).collect(Collectors.joining(",")),
                                    Arrays.stream(RegionalId).map(i -> (String)i).collect(Collectors.joining(",")),
                                    Arrays.stream(RegionalName).map(i -> (String)i).collect(Collectors.joining(",")),
                                    Arrays.stream(IsPreIntercept).map(i -> (String)i).collect(Collectors.joining(",")),
                                    Arrays.stream(PreInterceptTime).map(i -> (String)i).collect(Collectors.joining(",")),
                                    Arrays.stream(CreateTime).map(i -> (String)i).collect(Collectors.joining(",")),
                                    Arrays.stream(OrgId).map(i -> (String)i).collect(Collectors.joining(",")),
                                    Arrays.stream(OpportunitySource).map(i -> (String)i).collect(Collectors.joining(",")));
                        }
                    }
                }
            }
        }
    }

    private void insertData(Map<String, BRiskconfig> bRiskconfigMap, String masterId, Date qsDate,Object ID,
                            Object ProjectID,Object ClueID,Object Name,Object CustomerName,Object CustomerMobile,
                            Object CustomerStatus,Object CustomerStatusName,Object ReportUserID,Object ReportUserName,
                            Object AdviserGroupID,Object AdviserGroupName,Object ReportTime,Object TheFirstVisitDate,
                            Object SaleUserID,Object SaleUserName,Object CityId,Object CityName,Object RegionalId,
                            Object RegionalName,Object IsPreIntercept,Object PreInterceptTime,Object CreateTime,
                            Object OrgId,Object OpportunitySource) {
        bRiskinfoMapper.insert(new BRiskinfo() {{
            if (StringUtils.isNotEmpty(masterId)) {
                setId(masterId);
                setRiskType(4);
                setRiskDesc(
                        new StringBuilder("存在联名购房风险, 项目名为:{")
                                .append(Name)
                                .append("}, 渠道类型为:{")
                                .append(AdviserGroupName)
                                .append("}, 报备人为:{")
                                .append(ReportUserName)
                                .append("}, 报备时间为:{")
                                .append(ReportTime)
                                .append("}")
                                .toString()
                );
            } else {
                setId(UUID.randomUUID().toString());
                setRiskType(6);
                setJointNameMasterId(masterId);
            }
            setRiskConfigId(bRiskconfigMap.get(ProjectID).getId());
            setRegionalId((String)RegionalId);
            setRegionalName((String)RegionalName);
            setCityId((String)CityId);
            setCityName((String)CityName);
            setProjectId((String)ProjectID);
            setProjectName((String)Name);
            setCreateTime(DateUtil.date());
            setClueId((String)ClueID);
            setOpportunityId((String)ID);
            setCustomerName((String)CustomerName);
            setCustomerMobile((String)CustomerMobile);
            setCustomerStatus(CustomerStatus != null ? Integer.valueOf((String)CustomerStatus): null);
            setCustomerStatusName((String)CustomerStatusName);
            setReportUserID((String)ReportUserID);
            setReportUserName((String)ReportUserName);
            setAdviserGroupID((String)AdviserGroupID);
            setAdviserGroupName((String)AdviserGroupName);
            setReportTime((Date) ReportTime);
            setTheFirstVisitDate((Date)TheFirstVisitDate);
            setSaleUserID((String)SaleUserID);
            setSaleUserName((String)SaleUserName);
            setOrgId((String)OrgId);
            setOpportunitySource((String)OpportunitySource);
            setSubscribeTime(qsDate);
        }});
    }


    private Date serchMaxTime(Map<String, Object> i, Date date) {
        Date result = (Date) i.get("QSDate");
        if (date != null) {
            result = result.getTime() > date.getTime() ? result : date;
        }
        return result;
    }
}
