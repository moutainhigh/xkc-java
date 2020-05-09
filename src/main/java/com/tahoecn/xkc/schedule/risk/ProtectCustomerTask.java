package com.tahoecn.xkc.schedule.risk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tahoecn.core.date.DateUtil;
import com.tahoecn.xkc.common.utils.RiskBatchLogUtils;
import com.tahoecn.xkc.mapper.opportunity.BOpportunityMapper;
import com.tahoecn.xkc.mapper.risk.BRiskbatchlogMapper;
import com.tahoecn.xkc.mapper.risk.BRiskconfigMapper;
import com.tahoecn.xkc.mapper.risk.BRiskinfoMapper;
import com.tahoecn.xkc.model.risk.BRiskbatchlog;
import com.tahoecn.xkc.model.risk.BRiskconfig;
import com.tahoecn.xkc.model.risk.BRiskinfo;
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
 * @description: 防截客定时任务
 * @author: 张晓东
 * @time: 2020/4/30 16:40
 */
@Component
public class ProtectCustomerTask {

    private Logger log = LoggerFactory.getLogger(ProtectCustomerTask.class);

    @Resource
    private BOpportunityMapper bOpportunityMapper;
    @Resource
    private BRiskconfigMapper bRiskconfigMapper;
    @Resource
    private BRiskbatchlogMapper bRiskbatchlogMapper;
    @Resource
    private BRiskinfoMapper bRiskinfoMapper;

    @Transactional
    public void task() {

        // 查询是否存在运行中的防截客批任务, 存在直接返回
        List<BRiskbatchlog> runs = this.bRiskbatchlogMapper.selectList(new QueryWrapper<BRiskbatchlog>() {{
            eq("RiskType", 0);
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
                eq("RiskType", 0);
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
            AtomicReference<Date> dataMaxTime = new AtomicReference<>();
            this.bOpportunityMapper.fkProtectCustomer(startTime, DateUtil.date()).stream()
                    .filter(i -> bRiskconfigMap.get(i.get("ProjectID")) != null
                            && bRiskconfigMap.get(i.get("ProjectID")).getIsProtectCustomer() == 1)
                    .forEach(i -> {
                        dataMaxTime.set(serchMaxTime(i, dataMaxTime.get()));//获取数据最大时间
                        record(i, bRiskconfigMap);//记录风险数据
                    });
            this.bRiskbatchlogMapper.updateById(RiskBatchLogUtils.success(bRiskbatchlog, dataMaxTime.get()));
        } catch (Exception e) {
            log.error("fk batcg error : ProtectCustomerTask : {}" , e.getMessage());
            this.bRiskbatchlogMapper.updateById(RiskBatchLogUtils.failure(bRiskbatchlog, e.getMessage()));
        }
    }

    private Date serchMaxTime(Map<String, Object> i, Date date) {
        Date result = (Date) i.get("CreateTime");
        if (date != null) {
            result = result.getTime() > date.getTime() ? result : date;
        }
        return result;
    }


    private void record(Map<String, Object> i, Map<String, BRiskconfig> bRiskconfigMap) {
        // 项目防截客时间
        int preInterceptTime = 0;
        if (Integer.valueOf(i.get("IsPreIntercept").toString()) == 1) {
            preInterceptTime = Integer.valueOf(i.get("PreInterceptTime").toString());
        }
        //到访时间
        long theFirstVisitDate = ((Date) i.get("TheFirstVisitDate")).getTime();
        //报备时间
        long reportTime = ((Date) i.get("ReportTime")).getTime();
        //风控防截客时间
        long riskInterceptTime = bRiskconfigMap.get(i.get("ProjectID")).getProtectTime();

        if (((theFirstVisitDate - reportTime) > (preInterceptTime * 60 * 1000)) &&
                ((theFirstVisitDate - reportTime) <= ((preInterceptTime + riskInterceptTime) * 60 * 1000))) {
            int finalPreInterceptTime = preInterceptTime;
            bRiskinfoMapper.insert(new BRiskinfo() {{
                setId(UUID.randomUUID().toString());
                setRiskConfigId(bRiskconfigMap.get(i.get("ProjectID")).getId());
                setRegionalId((String)i.get("RegionalId"));
                setRegionalName((String)i.get("RegionalName"));
                setCityId((String)i.get("CityId"));
                setCityName((String)i.get("CityName"));
                setProjectId((String)i.get("ProjectID"));
                setProjectName((String)i.get("Name"));
                setRiskType(0);
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
                setProjectProtectCustomerTime(DateUtil.date(reportTime + (finalPreInterceptTime * 60 * 1000)));
                setRiskProtectCustomerTime(DateUtil.date(reportTime + (finalPreInterceptTime * 60 * 1000) + (riskInterceptTime * 60 * 1000)));
                setRiskDesc(
                        new StringBuilder("存在防截客风险, 项目防截客时间为:")
                                .append(i.get("PreInterceptTime"))
                                .append(", 风控防截客时间为:")
                                .append(bRiskconfigMap.get(i.get("ProjectID")).getProtectTime())
                                .append(". 报备时间为:{")
                                .append(DateUtil.formatDateTime((Date) i.get("ReportTime")))
                                .append("}, 到访时间为:{")
                                .append(DateUtil.formatDateTime((Date) i.get("TheFirstVisitDate")))
                                .append("}")
                                .toString()
                );
            }});
        }
    }

}
