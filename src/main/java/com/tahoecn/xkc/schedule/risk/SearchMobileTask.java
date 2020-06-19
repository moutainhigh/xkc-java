package com.tahoecn.xkc.schedule.risk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tahoecn.core.date.DateField;
import com.tahoecn.core.date.DateUtil;
import com.tahoecn.xkc.common.utils.RiskBatchLogUtils;
import com.tahoecn.xkc.mapper.customer.BClueMapper;
import com.tahoecn.xkc.mapper.opportunity.BOpportunityMapper;
import com.tahoecn.xkc.mapper.risk.BCustomermobilesearchMapper;
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
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @description: 搜电未报备任务
 * @author: 张晓东
 * @time: 2020/4/30 16:41
 */
@Component
public class SearchMobileTask {

    private Logger log = LoggerFactory.getLogger(SearchMobileTask.class);

    @Resource
    private BRiskbatchlogMapper bRiskbatchlogMapper;

    @Resource
    private BRiskconfigMapper bRiskconfigMapper;

    @Resource
    private BClueMapper bClueMapper;

    @Resource
    private BRiskinfoMapper bRiskinfoMapper;

    @Resource
    private BCustomermobilesearchMapper bCustomermobilesearchMapper;

    @Resource
    private BOpportunityMapper bOpportunityMapper;

    @Transactional
    public void task() {

        // 查询是否存在运行中的搜电批任务, 存在直接返回
        List<BRiskbatchlog> runs = this.bRiskbatchlogMapper.selectList(new QueryWrapper<BRiskbatchlog>() {{
            eq("RiskType", 2);
            eq("Status", 1);
        }});
        if (runs != null && runs.size() > 0) {
            log.error("fk bacth error, exist running task, cron:{}", DateUtil.now());
            return;
        }
        // 记录任务运行状态
        BRiskbatchlog bRiskbatchlog = RiskBatchLogUtils.start(2);
        this.bRiskbatchlogMapper.insert(bRiskbatchlog);

        try {
            // 查询已完成中,数据时间最大的防截客数据, 存在直接使用, 不存在为初始跑批
            List<BRiskbatchlog> bRiskbatchlogs = this.bRiskbatchlogMapper.selectList(new QueryWrapper<BRiskbatchlog>() {{
                eq("RiskType", 2);
                eq("Status", 2);
                orderByDesc("DataMaxTime");
            }});

            // 风控项目配置
            Map<String, BRiskconfig> bRiskconfigMap = this.bRiskconfigMapper.selectList(new QueryWrapper<BRiskconfig>() {{
                eq("Type", 1);
                eq("IsDel", 0);
                eq("Status", 1);
            }}).stream().collect(Collectors.toMap(i -> i.getProjectID().toUpperCase(), i -> i));

            Date startTime = DateUtil.yesterday();
            if (runs != null && bRiskbatchlogs.size() > 0 && null != bRiskbatchlogs.get(0).getDataMaxTime()) {
                startTime = bRiskbatchlogs.get(0).getDataMaxTime();
            }
            this.bRiskbatchlogMapper.updateById(RiskBatchLogUtils.running(bRiskbatchlog));
            AtomicReference<Date> dataMaxTime = new AtomicReference<>(startTime);
            this.bClueMapper.fkSearchMobile(startTime, DateUtil.date()).stream()
                    .filter(i -> StringUtils.isNotEmpty((String)i.get("ProjectID")) &&
                            bRiskconfigMap.get(i.get("ProjectID").toString().toUpperCase()) != null
                            && bRiskconfigMap.get(i.get("ProjectID").toString().toUpperCase()).getIsSearchMobile() == 1)
                    .forEach(i -> {
                        dataMaxTime.set(serchMaxTime(i, dataMaxTime.get()));//获取数据最大时间
                        record(i, bRiskconfigMap);//记录风险数据
                    });
            this.bRiskbatchlogMapper.updateById(RiskBatchLogUtils.success(bRiskbatchlog, DateUtil.offset(dataMaxTime.get(), DateField.SECOND, 1)));
        } catch (Exception e) {e.printStackTrace();
            log.error("fk batcg error : ProtectCustomerTask : {}" , e.getMessage());
            this.bRiskbatchlogMapper.updateById(RiskBatchLogUtils.failure(bRiskbatchlog, e.getMessage()));
        }
    }

    private void record(Map<String, Object> clueM, Map<String, BRiskconfig> bRiskconfigMap) {
        List<Map<String, Object>> fkSearchMobileInfos = this.bCustomermobilesearchMapper.fkSearchMobileInfo(
                clueM.get("ProjectID").toString().toUpperCase(),
                (String) clueM.get("CustomerMobile"),
                (Date) clueM.get("ReportTime")
        );

        if (null != fkSearchMobileInfos && fkSearchMobileInfos.size()>0){
            Map<String, Object> i = bClueMapper.fkSearchInfo((String) clueM.get("ClueID"));
            bRiskinfoMapper.insert(new BRiskinfo() {{
                setId(UUID.randomUUID().toString());
                setRiskConfigId(bRiskconfigMap.get(i.get("ProjectID").toString().toUpperCase()).getId());
                setRegionalId((String)i.get("RegionalId"));
                setRegionalName((String)i.get("RegionalName"));
                setCityId((String)i.get("CityId"));
                setCityName((String)i.get("CityName"));
                setProjectId((String)i.get("ProjectID").toString().toUpperCase());
                setProjectName((String)i.get("Name"));
                setRiskType(2);
                setCreateTime(DateUtil.date());
                setClueId((String)i.get("ClueID"));
                setOpportunityId((String)i.get("ID"));
                setCustomerName((String)i.get("CustomerName"));
                setCustomerMobile((String)i.get("CustomerMobile"));
                int CustomerStatus = i.get("CustomerStatus") != null ? Integer.valueOf(i.get("CustomerStatus").toString()): 2;
                if (StringUtils.isEmpty((String)i.get("ID"))) CustomerStatus =1 ;
                setCustomerStatus(CustomerStatus);
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
            }});
        }
    }

    private Date serchMaxTime(Map<String, Object> i, Date date) {
        Date result = (Date) i.get("ReportTime");
        if (date != null) {
            result = result.compareTo(date) >= 0 ? result : date;
        }
        return result;
    }

}
