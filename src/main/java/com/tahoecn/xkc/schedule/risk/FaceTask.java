package com.tahoecn.xkc.schedule.risk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tahoecn.core.date.DateUtil;
import com.tahoecn.xkc.common.utils.RiskBatchLogUtils;
import com.tahoecn.xkc.mapper.opportunity.BOpportunityMapper;
import com.tahoecn.xkc.mapper.risk.BRiskbatchlogMapper;
import com.tahoecn.xkc.mapper.risk.BRiskconfigMapper;
import com.tahoecn.xkc.mapper.risk.BRiskinfoMapper;
import com.tahoecn.xkc.model.mongo.FaceDetectCustomer;
import com.tahoecn.xkc.model.risk.BRiskbatchlog;
import com.tahoecn.xkc.model.risk.BRiskconfig;
import com.tahoecn.xkc.model.risk.BRiskinfo;
import com.tahoecn.xkc.service.mongo.FaceDetectCustomerService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
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

    @Autowired
    private FaceDetectCustomerService faceDetectCustomerService;

    @Resource
    private BRiskinfoMapper bRiskinfoMapper;

    @Resource
    private BOpportunityMapper bOpportunityMapper;

    @Transactional
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
            /*List<BRiskbatchlog> bRiskbatchlogs = this.bRiskbatchlogMapper.selectList(new QueryWrapper<BRiskbatchlog>() {{
                eq("RiskType", 1);
                eq("Status", 2);
                orderByDesc("DataMaxTime");
            }});*/

            // 风控项目配置
            Map<String, BRiskconfig> bRiskconfigMap = this.bRiskconfigMapper.selectList(new QueryWrapper<BRiskconfig>() {{
                eq("Type", 1);
                eq("IsDel", 0);
                eq("Status", 1);
            }}).stream().collect(Collectors.toMap(i -> i.getProjectID().toString().toUpperCase(), i -> i));

            /*Date startTime = DateUtil.yesterday();
            if (bRiskbatchlogs.size() > 0 && null != bRiskbatchlogs.get(0).getDataMaxTime()) {
                startTime = bRiskbatchlogs.get(0).getDataMaxTime();
            }*/
            this.bRiskbatchlogMapper.updateById(RiskBatchLogUtils.running(bRiskbatchlog));
//            AtomicReference<Date> dataMaxTime = new AtomicReference<>(startTime);
            this.faceDetectCustomerService.scanFaceDetectCustomer(JointNameTask.getMinAndMaxDate().get("min"), JointNameTask.getMinAndMaxDate().get("max")).stream()
                    .forEach(i -> {
                        /*dataMaxTime.set(serchMaxTime(i, dataMaxTime.get()));//获取数据最大时间*/
                        record(i, bRiskconfigMap);//记录风险数据
                    });
            this.bRiskbatchlogMapper.updateById(RiskBatchLogUtils.success(bRiskbatchlog, null));
        } catch (Exception e) {
            log.error("fk batcg error : ProtectCustomerTask : {}", e.getMessage());
            this.bRiskbatchlogMapper.updateById(RiskBatchLogUtils.failure(bRiskbatchlog, e.getMessage()));
        }
    }

    private void record(FaceDetectCustomer faceDetectCustomer, Map<String, BRiskconfig> bRiskconfigMap) {

        if (StringUtils.isNotEmpty(faceDetectCustomer.getProjectId()) &&
                bRiskconfigMap.containsKey(faceDetectCustomer.getProjectId().toUpperCase()) &&
                bRiskconfigMap.get(faceDetectCustomer.getProjectId().toUpperCase()).getIsFace() == 1) {

            //人脸明细
            Map<String, Object> customerInfo = faceDetectCustomerService.findCustomerInfo(faceDetectCustomer.getProjectId(), faceDetectCustomer.getIdNumber());
            if (null == customerInfo.get("faceDetectCustomers") || ((List) customerInfo.get("faceDetectCustomers")).size() <= 0 ||
                    null == customerInfo.get("faceDetectCustomerDetail") || null == customerInfo.get("faceDetectImageDatails") ||
                    ((List) customerInfo.get("faceDetectImageDatails")).size() <= 0) return;

            //客储明细
            Map<String, Object> fkSearchFaceInfo = bOpportunityMapper.fkSearchFaceInfo(faceDetectCustomer.getProjectId(), faceDetectCustomer.getIdNumber());
            if (null == fkSearchFaceInfo) return;

            Map<String, Object> firstFace = faceDetectCustomerService.firstFace(customerInfo);
            Date reportTime = (Date) fkSearchFaceInfo.get("ReportTime");
            if (null != firstFace.get("firstFaceTime") && reportTime.compareTo((Date) firstFace.get("firstFaceTime")) >= 0) {
                // 一大堆业务逻辑
                this.bRiskinfoMapper.insert(new BRiskinfo() {{
                    setId(UUID.randomUUID().toString());
                    setRiskConfigId(bRiskconfigMap.get(fkSearchFaceInfo.get("ProjectID").toString().toUpperCase()).getId());
                    setRegionalId((String) fkSearchFaceInfo.get("RegionalId"));
                    setRegionalName((String) fkSearchFaceInfo.get("RegionalName"));
                    setCityId((String) fkSearchFaceInfo.get("CityId"));
                    setCityName((String) fkSearchFaceInfo.get("CityName"));
                    setProjectId((String) fkSearchFaceInfo.get("ProjectID").toString().toUpperCase());
                    setProjectName((String) fkSearchFaceInfo.get("Name"));
                    setRiskType(1);
                    setCreateTime(DateUtil.date());
                    setClueId((String) fkSearchFaceInfo.get("ClueID"));
                    setOpportunityId((String) fkSearchFaceInfo.get("ID"));
                    setCustomerName((String) fkSearchFaceInfo.get("CustomerName"));
                    setCustomerMobile((String) fkSearchFaceInfo.get("CustomerMobile"));
                    setCustomerStatus(fkSearchFaceInfo.get("CustomerStatus") != null ? Integer.valueOf(fkSearchFaceInfo.get("CustomerStatus").toString()) : null);
                    setCustomerStatusName((String) fkSearchFaceInfo.get("CustomerStatusName"));
                    setReportUserID((String) fkSearchFaceInfo.get("ReportUserID"));
                    setReportUserName((String) fkSearchFaceInfo.get("ReportUserName"));
                    setAdviserGroupID((String) fkSearchFaceInfo.get("AdviserGroupID"));
                    setAdviserGroupName((String) fkSearchFaceInfo.get("AdviserGroupName"));
                    setReportTime(reportTime);
                    setTheFirstVisitDate((Date) fkSearchFaceInfo.get("TheFirstVisitDate"));
                    setSaleUserID((String) fkSearchFaceInfo.get("SaleUserID"));
                    setSaleUserName((String) fkSearchFaceInfo.get("SaleUserName"));
                    setOrgId((String) fkSearchFaceInfo.get("OrgId"));
                    setOpportunitySource((String) fkSearchFaceInfo.get("OpportunitySource"));
                    setFirstFaceTime((Date) firstFace.get("firstFaceTime"));
                    setImgPath((String) firstFace.get("imgUrl"));
                    setRiskDesc(
                            new StringBuilder("存在人脸识别风险, 项目名为:{")
                                    .append(fkSearchFaceInfo.get("Name"))
                                    .append("}, 渠道类型为:{")
                                    .append(fkSearchFaceInfo.get("AdviserGroupName"))
                                    .append("}, 报备人为:{")
                                    .append(fkSearchFaceInfo.get("ReportUserName"))
                                    .append("}, 报备时间为:{")
                                    .append(fkSearchFaceInfo.get("ReportTime"))
                                    .append("}, 人脸识别时间为:{")
                                    .append(firstFace.get("firstFaceTime"))
                                    .append("}")
                                    .toString()
                    );
                }});
            }
        }
    }

    /*private Date serchMaxTime(Map<String, Object> i, Date date) {
        Date result = (Date) i.get("CreateTime");
        if (date != null) {
            result = result.compareTo(date) >= 0 ? result : date;
        }
        return result;
    }*/
}
