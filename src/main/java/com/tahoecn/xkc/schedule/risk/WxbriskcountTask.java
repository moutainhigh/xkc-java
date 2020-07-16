package com.tahoecn.xkc.schedule.risk;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tahoecn.core.date.DateUtil;
import com.tahoecn.xkc.mapper.customer.STrade2CstMapper;
import com.tahoecn.xkc.mapper.opportunity.BOpportunityMapper;
import com.tahoecn.xkc.mapper.project.BProjectMapper;
import com.tahoecn.xkc.mapper.risk.BCustomerattachMapper;
import com.tahoecn.xkc.mapper.risk.BMongotosqlservererrorlogMapper;
import com.tahoecn.xkc.mapper.risk.BWxbriskcountMapper;
import com.tahoecn.xkc.model.mongo.FaceDetectCustomer;
import com.tahoecn.xkc.model.mongo.FaceDetectCustomerFreshCardInfo;
import com.tahoecn.xkc.model.mongo.FaceDetectProjectThirdMapping;
import com.tahoecn.xkc.model.risk.BCustomerattach;
import com.tahoecn.xkc.model.risk.BMongotosqlservererrorlog;
import com.tahoecn.xkc.model.risk.BWxbriskcount;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * @description:
 * @author: 张晓东
 * @time: 2020/6/17 21:52
 */

@Component
public class WxbriskcountTask {
    private Logger log = LoggerFactory.getLogger(WxbriskcountTask.class);

    @Resource
    private BMongotosqlservererrorlogMapper bMongotosqlservererrorlogMapper;

    @Resource
    private BWxbriskcountMapper bWxbriskcountMapper;

    @Resource
    private BOpportunityMapper bOpportunityMapper;

    @Resource
    private BCustomerattachMapper bCustomerattachMapper;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Resource
    private STrade2CstMapper sTrade2CstMapper;

    @Resource
    private BProjectMapper bProjectMapper;

    private static final Date AFRESH_PULL_TIME = new Date(1594883999455l);//重新刷数据设置跑数据前一天时间

    public void task() {
        List<FaceDetectCustomer> faceDetectCustomers = null;
        BWxbriskcount bWxbriskcount = bWxbriskcountMapper.one();
        Date startTime = JointNameTask.getMinAndMaxDate().get("min");
        Date endTime = JointNameTask.getMinAndMaxDate().get("max");
        if (null == bWxbriskcount || (null != AFRESH_PULL_TIME && isEffectiveDate(AFRESH_PULL_TIME, startTime, endTime))) {
            faceDetectCustomers = this.mongoTemplate.findAll(FaceDetectCustomer.class);
        } else {
            faceDetectCustomers = mongoTemplate.find(
                    new Query(new Criteria().orOperator(
                            Criteria.where("createTime").exists(true).gte(startTime).lte(endTime),
                            Criteria.where("updateTime").exists(true).gte(startTime).lte(endTime))),
                    FaceDetectCustomer.class);
        }
        faceDetectCustomers.forEach(i -> {
            try {
                List<FaceDetectProjectThirdMapping> faceDetectProjectThirdMappings = mongoTemplate.find(new Query(Criteria.where("projectId").is(i.getProjectId())), FaceDetectProjectThirdMapping.class);
                if (null != faceDetectProjectThirdMappings && faceDetectProjectThirdMappings.size() > 0) {
                    String projectId = faceDetectProjectThirdMappings.get(0).getOuterProjectId();
                    String projectName = faceDetectProjectThirdMappings.get(0).getProjectName();
                    List<Map<String, Object>> fkSearchFaceInfos = bOpportunityMapper.wxbriskcountTaskInfo(projectId, i.getIdNumber());
                    BWxbriskcount target = null;
                    Long fdcfciCount = mongoTemplate.count(new Query(Criteria.where("customerId").is(new ObjectId(i.getId()))), FaceDetectCustomerFreshCardInfo.class);
                    if (null != fkSearchFaceInfos && fkSearchFaceInfos.size() > 0 && StringUtils.isNotEmpty(i.getFinishNo())) {
                        Map<String, Object> fkSearchFaceInfo = fkSearchFaceInfos.get(0);
                        BCustomerattach bCustomerattach = bCustomerattachMapper.selectOne(new QueryWrapper<BCustomerattach>() {{
                            eq("OpportunityID", (String) fkSearchFaceInfo.get("ID"));
                            gt("SalesStatus", 2);
                        }});
                        //认购
                        List<Map<String, Object>> subscribe = null;
                        //签约
                        List<Map<String, Object>> agreement = null;
                        if (null != bCustomerattach) {
                            subscribe = sTrade2CstMapper.subscribe(bCustomerattach.getId());
                            agreement = sTrade2CstMapper.agreement(bCustomerattach.getId());
                        }
                        List<Map<String, Object>> subscribeCopy = subscribe;
                        List<Map<String, Object>> agreementCopy = agreement;
                        target = new BWxbriskcount() {{
                            setId(i.getId());//主键
                            setCustomerName(i.getName());//客户姓名
                            setCustomerCardId(i.getIdNumber());//客户身份证号
                            setAgent(i.getAgent());//经纪人
                            setSalerName(i.getSalerName());//置业顾问
                            setReportTime(i.getReportTime());//报备时间
                            setFirstPhotoTime(i.getFirstPhotoTime());//首次抓拍时间
                            setFreshCardTime(i.getFreshCardTime());//刷证时间
                            setSubscribeTime(i.getSubscriptionTime());//认购时间
                            setFinishTime(i.getFinishTime());//签约时间
                            setCreateTime(DateUtil.date());//创建时间
                            if (null != i.getFreshCardTime()) {
                                setHasPass(fdcfciCount.intValue());//认证数量,0为未刷证,-1为认证失败
                            } else {
                                setHasPass(0);
                                if (fdcfciCount.intValue() > 0) setHasPass(-1);
                            }
                            setOpportunityId((String) fkSearchFaceInfo.get("ID"));//机会id
                            setRegionalId((String) fkSearchFaceInfo.get("RegionalId"));//区域主键
                            setRegionalName((String) fkSearchFaceInfo.get("RegionalName"));//区域名称
                            setCityId((String) fkSearchFaceInfo.get("CityId"));//城市主键
                            setCityName((String) fkSearchFaceInfo.get("CityName"));//城市名称
                            setDictId((String) fkSearchFaceInfo.get("DictId"));//渠道来源
                            setDictName((String) fkSearchFaceInfo.get("DictName"));//渠道来源
                            setChannelCompanyId((String) fkSearchFaceInfo.get("ReportUserOrg"));//渠道机构ID
                            setChannelCompany((String) fkSearchFaceInfo.get("OrgName"));//渠道机构
                            setCustomerId((String) fkSearchFaceInfo.get("CustomerId"));
                            setProjectId(projectId);//项目主键
                            setProjectName(projectName);//项目名称
                            setHouse(null != subscribeCopy && subscribeCopy.size() > 0 ? (String) subscribeCopy.get(0).get("RoomCode") : null);//房间编号
                            setSubscribeMoney(null != subscribeCopy && subscribeCopy.size() > 0 ? ((BigDecimal) subscribeCopy.get(0).get("CjTotal")).floatValue() : null);//认购金额
                            setContractMoney(null != agreementCopy && agreementCopy.size() > 0 ? ((BigDecimal) agreementCopy.get(0).get("HtTotal")).floatValue() : null);//签约金额
                            if (StringUtils.isNotEmpty(i.getRiskStatus()) && i.getRiskStatus().equals("RISK") && StringUtils.isEmpty(i.getRiskApproveStatus())) {
                                setRiskStatus(0);//风险类别:0疑似风险
                            } else if (StringUtils.isNotEmpty(i.getRiskStatus()) && i.getRiskStatus().equals("RISK") && i.getRiskApproveStatus().equals("NORMAL")) {
                                setRiskStatus(1);//风险类别:1无风险
                            } else if (StringUtils.isNotEmpty(i.getRiskStatus()) && i.getRiskStatus().equals("RISK") && i.getRiskApproveStatus().equals("RISK")) {
                                setRiskStatus(2);//风险类别:2确认风险
                            } else if (StringUtils.isNotEmpty(i.getRiskStatus()) && i.getRiskStatus().equals("UNKNOWN")) {
                                setRiskStatus(3);//风险类别:3未知客户
                            }
                        }};
                    } else {
                        Map<String, Object> searchProjectNexus = this.bProjectMapper.searchProjectNexus(projectId);
                        target = new BWxbriskcount() {{
                            setId(i.getId());//主键
                            setCustomerName(i.getName());//客户姓名
                            setCustomerCardId(i.getIdNumber());//客户身份证号
                            setAgent(i.getAgent());//经纪人
                            setSalerName(i.getSalerName());//置业顾问
                            setReportTime(i.getReportTime());//报备时间
                            setFirstPhotoTime(i.getFirstPhotoTime());//首次抓拍时间
                            setFreshCardTime(i.getFreshCardTime());//刷证时间
                            setSubscribeTime(i.getFinishTime());//认购时间
                            setFinishTime(i.getFinishTime());//签约时间
                            setCreateTime(DateUtil.date());//创建时间
                            if (null != i.getFreshCardTime()) {
                                setHasPass(fdcfciCount.intValue());//认证数量,0为未刷证,-1为认证失败
                            } else {
                                setHasPass(0);
                                if (fdcfciCount.intValue() > 0) setHasPass(-1);
                            }
                            if (null != searchProjectNexus) {
                                setRegionalId((String) searchProjectNexus.get("RegionalId"));//区域主键
                                setRegionalName((String) searchProjectNexus.get("RegionalName"));//区域名称
                                setCityId((String) searchProjectNexus.get("CityId"));//城市主键
                                setCityName((String) searchProjectNexus.get("CityName"));//城市名称
                            }
                            setProjectId(projectId);//项目主键
                            setProjectName(projectName);//项目名称
                            /*
                                setDictId((String) fkSearchFaceInfo.get("DictId"));//渠道来源
                                setDictName((String) fkSearchFaceInfo.get("DictName"));//渠道来源
                                setChannelCompanyId((String) fkSearchFaceInfo.get("ReportUserOrg"));//渠道机构ID
                                setChannelCompany((String) fkSearchFaceInfo.get("OrgName"));//渠道机构
                            */
                            setHouse(i.getFinishNo());//房间编号
                            if (StringUtils.isNotEmpty(i.getRiskStatus()) && i.getRiskStatus().equals("RISK") && StringUtils.isEmpty(i.getRiskApproveStatus())) {
                                setRiskStatus(0);//风险类别:0疑似风险
                            } else if (StringUtils.isNotEmpty(i.getRiskStatus()) && i.getRiskStatus().equals("RISK") && i.getRiskApproveStatus().equals("NORMAL")) {
                                setRiskStatus(1);//风险类别:1无风险
                            } else if (StringUtils.isNotEmpty(i.getRiskStatus()) && i.getRiskStatus().equals("RISK") && i.getRiskApproveStatus().equals("RISK")) {
                                setRiskStatus(2);//风险类别:2确认风险
                            } else if (StringUtils.isNotEmpty(i.getRiskStatus()) && i.getRiskStatus().equals("UNKNOWN")) {
                                setRiskStatus(3);//风险类别:3未知客户
                            }
                        }};
                    }
                    if (null == bWxbriskcountMapper.selectById(i.getId())) {
                        bWxbriskcountMapper.insert(target);
                    } else {
                        bWxbriskcountMapper.updateById(target);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                this.bMongotosqlservererrorlogMapper.insert(new BMongotosqlservererrorlog() {{
                    setId(UUID.randomUUID().toString());
                    setMsg("WxbriskcountTask 处理数据异常:" + JSONObject.toJSONString(i));
                    setError(e.getMessage());
                    setCreateTime(new Date());
                }});
                log.error("WxbriskcountTask 处理数据异常 , mongodb table : {} , id : {}", "faceDetectCustomer", i.getId());
            }
        });
    }

    /**
     * @param nowTime   当前时间
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @description: 判断当前时间在时间区间内
     * @return:
     * @author: 张晓东
     * @time: 2020/7/9 16:15
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }
}
