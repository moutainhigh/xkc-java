package com.tahoecn.xkc.schedule.risk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tahoecn.core.date.DateUtil;
import com.tahoecn.xkc.common.utils.RiskBatchLogUtils;
import com.tahoecn.xkc.mapper.risk.BRiskbatchlogMapper;
import com.tahoecn.xkc.mapper.risk.BRiskconfigMapper;
import com.tahoecn.xkc.mapper.risk.BRiskinfoMapper;
import com.tahoecn.xkc.mapper.risk.BWangxiaobaoMapper;
import com.tahoecn.xkc.model.risk.BRiskbatchlog;
import com.tahoecn.xkc.model.risk.BRiskconfig;
import com.tahoecn.xkc.model.risk.BRiskinfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @description: 人脸识别任务
 * @author: 张晓东
 * @time: 2020/4/30 16:51
 */
@Component
public class FaceTask {

    private Logger log = LoggerFactory.getLogger(FaceTask.class);

    @Resource
    private BRiskbatchlogMapper bRiskbatchlogMapper;

    @Resource
    private BRiskconfigMapper bRiskconfigMapper;

    @Resource
    private BWangxiaobaoMapper bWangxiaobaoMapper;

    @Resource
    private BRiskinfoMapper bRiskinfoMapper;

    public void task() {
// 查询是否存在运行中的搜电批任务, 存在直接返回
        List<BRiskbatchlog> runs = this.bRiskbatchlogMapper.selectList(new QueryWrapper<BRiskbatchlog>() {{
            eq("RiskType", 1);
            eq("Status", 1);
        }});
        if (runs != null && runs.size() > 0) {
            log.error("fk bacth error, exist running task, cron:{}", DateUtil.now());
            return;
        }
        // 记录任务运行状态
        BRiskbatchlog bRiskbatchlog = RiskBatchLogUtils.start(1);
        this.bRiskbatchlogMapper.insert(bRiskbatchlog);

        try {
            // 查询已完成中,数据时间最大的防截客数据, 存在直接使用, 不存在为初始跑批
            List<BRiskbatchlog> bRiskbatchlogs = this.bRiskbatchlogMapper.selectList(new QueryWrapper<BRiskbatchlog>() {{
                eq("RiskType", 1);
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
            if (bRiskbatchlogs.size() > 0) {
                startTime = bRiskbatchlogs.get(0).getDataMaxTime();
            }
            this.bRiskbatchlogMapper.updateById(RiskBatchLogUtils.running(bRiskbatchlog));
            AtomicReference<Date> dataMaxTime = new AtomicReference<>(DateUtil.date());

            this.bWangxiaobaoMapper.searchFace(startTime, DateUtil.date()).stream()
                    .forEach(i -> {
                        dataMaxTime.set(serchMaxTime(i, dataMaxTime.get()));//获取数据最大时间
                        record(i, bRiskconfigMap);//记录风险数据
                    });
            this.bRiskbatchlogMapper.updateById(RiskBatchLogUtils.success(bRiskbatchlog, dataMaxTime.get()));
        } catch (Exception e) {
            log.error("fk batcg error : ProtectCustomerTask : {}", e.getMessage());
            this.bRiskbatchlogMapper.updateById(RiskBatchLogUtils.failure(bRiskbatchlog, e.getMessage()));
        }
    }

    private void record(Map<String, Object> i, Map<String, BRiskconfig> bRiskconfigMap) {

        if (bRiskconfigMap.containsKey(i.get("ProjectID")) &&
                bRiskconfigMap.get(i.get("ProjectID")).getIsFace() == 1) {

            Date firstFaceTime = (Date) i.get("FirstFaceTime");
            Date reportTime = (Date) i.get("ReportTime");

            if (reportTime.compareTo(firstFaceTime) >= 0) {

                // 一大堆业务逻辑
                this.bRiskinfoMapper.insert(new BRiskinfo() {{
                    setId(UUID.randomUUID().toString());
                    setRiskConfigId(bRiskconfigMap.get(i.get("ProjectID")).getId());
                    setRegionalId((String) i.get("RegionalId"));
                    setRegionalName((String) i.get("RegionalName"));
                    setCityId((String) i.get("CityId"));
                    setCityName((String) i.get("CityName"));
                    setProjectId((String) i.get("ProjectID"));
                    setProjectName((String) i.get("Name"));
                    setRiskType(1);
                    setCreateTime(DateUtil.date());
                    setClueId((String) i.get("ClueID"));
                    setOpportunityId((String) i.get("ID"));
                    setCustomerName((String) i.get("CustomerName"));
                    setCustomerMobile((String) i.get("CustomerMobile"));
                    setCustomerStatus(i.get("CustomerStatus") != null ? Integer.valueOf(i.get("CustomerStatus").toString()) : null);
                    setCustomerStatusName((String) i.get("CustomerStatusName"));
                    setReportUserID((String) i.get("ReportUserID"));
                    setReportUserName((String) i.get("ReportUserName"));
                    setAdviserGroupID((String) i.get("AdviserGroupID"));
                    setAdviserGroupName((String) i.get("AdviserGroupName"));
                    setReportTime(reportTime);
                    setTheFirstVisitDate((Date) i.get("TheFirstVisitDate"));
                    setSaleUserID((String) i.get("SaleUserID"));
                    setSaleUserName((String) i.get("SaleUserName"));
                    setOrgId((String) i.get("OrgId"));
                    setOpportunitySource((String) i.get("OpportunitySource"));
                    setFirstFaceTime(firstFaceTime);
                    setRiskDesc(
                            new StringBuilder("存在人脸识别风险, 项目名为:{")
                                    .append(i.get("Name"))
                                    .append("}, 渠道类型为:{")
                                    .append(i.get("AdviserGroupName"))
                                    .append("}, 报备人为:{")
                                    .append(i.get("ReportUserName"))
                                    .append("}, 报备时间为:{")
                                    .append(i.get("ReportTime"))
                                    .append("}, 人脸识别时间为:{")
                                    .append(i.get("ContractTime"))
                                    .append("}")
                                    .toString()
                    );
                }});
            }
        }
    }

    private Date serchMaxTime(Map<String, Object> i, Date date) {
        Date result = (Date) i.get("CreateTime");
        if (date != null) {
            result = result.compareTo(date) >= 0 ? result : date;
        }
        return result;
    }
}
