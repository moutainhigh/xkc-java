package com.tahoecn.xkc.schedule.risk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tahoecn.core.date.DateUtil;
import com.tahoecn.xkc.common.utils.RiskBatchLogUtils;
import com.tahoecn.xkc.mapper.customer.STrade2CstMapper;
import com.tahoecn.xkc.mapper.risk.BCustomerattachMapper;
import com.tahoecn.xkc.mapper.risk.BRiskbatchlogMapper;
import com.tahoecn.xkc.mapper.risk.BRiskconfigMapper;
import com.tahoecn.xkc.mapper.risk.BRiskinfoMapper;
import com.tahoecn.xkc.model.risk.BRiskbatchlog;
import com.tahoecn.xkc.model.risk.BRiskconfig;
import com.tahoecn.xkc.model.risk.BRiskinfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @description: 短期成交任务
 * @author: 张晓东
 * @time: 2020/4/30 16:45
 */
@Component
public class ShortDealTask {

    private Logger log = LoggerFactory.getLogger(ShortDealTask.class);

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

// 查询是否存在运行中的搜电批任务, 存在直接返回
        List<BRiskbatchlog> runs = this.bRiskbatchlogMapper.selectList(new QueryWrapper<BRiskbatchlog>() {{
            eq("RiskType", 5);
            eq("Status", 1);
        }});
        if (runs != null && runs.size() > 0) {
            log.error("fk bacth error, exist running task, cron:{}", DateUtil.now());
            return;
        }
        // 记录任务运行状态
        BRiskbatchlog bRiskbatchlog = RiskBatchLogUtils.start(5);
        this.bRiskbatchlogMapper.insert(bRiskbatchlog);

        try {
            // 查询已完成中,数据时间最大的防截客数据, 存在直接使用, 不存在为初始跑批
            /*List<BRiskbatchlog> bRiskbatchlogs = this.bRiskbatchlogMapper.selectList(new QueryWrapper<BRiskbatchlog>() {{
                eq("RiskType", 5);
                eq("Status", 2);
                orderByDesc("DataMaxTime");
            }});*/

            // 风控项目配置
            Map<String, BRiskconfig> bRiskconfigMap = this.bRiskconfigMapper.selectList(new QueryWrapper<BRiskconfig>() {{
                eq("Type", 1);
                eq("IsDel", 0);
                eq("Status", 1);
            }}).stream().collect(Collectors.toMap(i -> i.getProjectID().toUpperCase(), i -> i));

            /*Date startTime = DateUtil.yesterday();
            if (bRiskbatchlogs.size() > 0 && null != bRiskbatchlogs.get(0).getDataMaxTime()) {
                startTime = bRiskbatchlogs.get(0).getDataMaxTime();
            }*/
            this.bRiskbatchlogMapper.updateById(RiskBatchLogUtils.running(bRiskbatchlog));
            /*AtomicReference<Date> dataMaxTime = new AtomicReference<>(startTime);*/
            this.sTrade2CstMapper.searchShortDeal(JointNameTask.getMinAndMaxDate().get("min"), JointNameTask.getMinAndMaxDate().get("max")).stream()
                    .forEach(i -> {
//                        dataMaxTime.set(serchMaxTime(i, dataMaxTime.get()));//获取数据最大时间
                        record(i, bRiskconfigMap);//记录风险数据
                    });
            this.bRiskbatchlogMapper.updateById(RiskBatchLogUtils.success(bRiskbatchlog, null));
        } catch (Exception e) {
            log.error("fk batcg error : ProtectCustomerTask : {}", e.getMessage());
            this.bRiskbatchlogMapper.updateById(RiskBatchLogUtils.failure(bRiskbatchlog, e.getMessage()));
        }
    }

    //天
    int day = 1000 * 60 * 60 * 24;

    private void record(Map<String, Object> shortDeal, Map<String, BRiskconfig> bRiskconfigMap) {
        Map<String, Object> xsOppGuid = this.bCustomerattachMapper.fkJointNameOrShortDealOrUnverified(shortDeal.get("xsOppGuid").toString());

        if (null != xsOppGuid && xsOppGuid.size() > 0 && StringUtils.isNotEmpty((String) xsOppGuid.get("ProjectID")) &&
                bRiskconfigMap.containsKey(xsOppGuid.get("ProjectID").toString().toUpperCase()) &&
                bRiskconfigMap.get(xsOppGuid.get("ProjectID").toString().toUpperCase()).getIsShortDeal() == 1) {
            Date contractTime = (Date) shortDeal.get("ContractTime");
            Date reportTime = (Date) xsOppGuid.get("ReportTime");
            if (null != contractTime && null != reportTime) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(contractTime);
                calendar.set(Calendar.HOUR_OF_DAY, 23);
                calendar.set(Calendar.MINUTE, 59);
                calendar.set(Calendar.SECOND, 59);
                Date d = calendar.getTime();
                long l = d.getTime() - reportTime.getTime();
                int projectID = (bRiskconfigMap.get(xsOppGuid.get("ProjectID").toString().toUpperCase()).getShortDealTime()) * day;
                if (l <= projectID)
                    bRiskinfoMapper.insert(new BRiskinfo() {{
                        setId(UUID.randomUUID().toString());
                        setRiskConfigId(bRiskconfigMap.get(xsOppGuid.get("ProjectID").toString().toUpperCase()).getId());
                        setRegionalId((String) xsOppGuid.get("RegionalId"));
                        setRegionalName((String) xsOppGuid.get("RegionalName"));
                        setCityId((String) xsOppGuid.get("CityId"));
                        setCityName((String) xsOppGuid.get("CityName"));
                        setProjectId((String) xsOppGuid.get("ProjectID").toString().toUpperCase());
                        setProjectName((String) xsOppGuid.get("Name"));
                        setRiskType(5);
                        setCreateTime(DateUtil.date());
                        setClueId((String) xsOppGuid.get("ClueID"));
                        setOpportunityId((String) xsOppGuid.get("ID"));
                        setCustomerName((String) xsOppGuid.get("CustomerName"));
                        setCustomerMobile((String) xsOppGuid.get("CustomerMobile"));
                        setCustomerStatus(xsOppGuid.get("CustomerStatus") != null ? Integer.valueOf(xsOppGuid.get("CustomerStatus").toString()) : null);
                        setCustomerStatusName((String) xsOppGuid.get("CustomerStatusName"));
                        setReportUserID((String) xsOppGuid.get("ReportUserID"));
                        setReportUserName((String) xsOppGuid.get("ReportUserName"));
                        setAdviserGroupID((String) xsOppGuid.get("AdviserGroupID"));
                        setAdviserGroupName((String) xsOppGuid.get("AdviserGroupName"));
                        setReportTime((Date) xsOppGuid.get("ReportTime"));
                        setTheFirstVisitDate((Date) xsOppGuid.get("TheFirstVisitDate"));
                        setSaleUserID((String) xsOppGuid.get("SaleUserID"));
                        setSaleUserName((String) xsOppGuid.get("SaleUserName"));
                        setOrgId((String) xsOppGuid.get("OrgId"));
                        setOpportunitySource((String) xsOppGuid.get("OpportunitySource"));
                        setSubscribeTime((Date) shortDeal.get("SubscribeTime")); //认购时间
                        setContractTime((Date) shortDeal.get("ContractTime"));// 签约时间
                        setRiskDesc(
                                new StringBuilder("存在短期成交风险, 项目名为:{")
                                        .append(xsOppGuid.get("Name"))
                                        .append("}, 报备时间为:{")
                                        .append(xsOppGuid.get("ReportTime"))
                                        .append("}, 签约时间为:{")
                                        .append(shortDeal.get("ContractTime"))
                                        .append("}")
                                        .toString()
                        );
                    }});
            }
        }
    }

    private Date serchMaxTime(Map<String, Object> i, Date date) {
        Date result = (Date) i.get("ContractTime");
        if (date != null) {
            result = result.getTime() > date.getTime() ? result : date;
        }
        return result;
    }
}
