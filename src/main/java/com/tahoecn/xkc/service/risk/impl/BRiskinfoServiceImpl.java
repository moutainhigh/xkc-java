package com.tahoecn.xkc.service.risk.impl;

import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tahoecn.core.date.DateUtil;
import com.tahoecn.core.json.JSONResult;
import com.tahoecn.xkc.common.enums.TipsEnum;
import com.tahoecn.xkc.common.utils.ExcelUtil;
import com.tahoecn.xkc.common.utils.ResultUtil;
import com.tahoecn.xkc.mapper.channel.BChannelorgMapper;
import com.tahoecn.xkc.mapper.customer.BClueMapper;
import com.tahoecn.xkc.mapper.customer.BCustomerMapper;
import com.tahoecn.xkc.mapper.customer.STrade2CstMapper;
import com.tahoecn.xkc.mapper.opportunity.BOpportunityMapper;
import com.tahoecn.xkc.mapper.org.SOrganizationMapper;
import com.tahoecn.xkc.mapper.project.BProjectMapper;
import com.tahoecn.xkc.mapper.risk.*;
import com.tahoecn.xkc.mapper.rule.BClueruleMapper;
import com.tahoecn.xkc.mapper.salegroup.BSalesgroupmemberMapper;
import com.tahoecn.xkc.mapper.salegroup.BSalesuserMapper;
import com.tahoecn.xkc.model.channel.BChannelorg;
import com.tahoecn.xkc.model.customer.BCustomer;
import com.tahoecn.xkc.model.opportunity.BOpportunity;
import com.tahoecn.xkc.model.org.SOrganization;
import com.tahoecn.xkc.model.project.BProject;
import com.tahoecn.xkc.model.risk.BCustomerattach;
import com.tahoecn.xkc.model.risk.BRiskconfig;
import com.tahoecn.xkc.model.risk.BRiskinfo;
import com.tahoecn.xkc.model.risk.BRisknnamelog;
import com.tahoecn.xkc.model.risk.vo.TiskInfoFindVO;
import com.tahoecn.xkc.model.risk.vo.TiskInfoTreeVO;
import com.tahoecn.xkc.model.risk.vo.TiskInfoVO;
import com.tahoecn.xkc.model.risk.vo.TiskTreeFindVO;
import com.tahoecn.xkc.model.salegroup.BSalesuser;
import com.tahoecn.xkc.service.mongo.FaceDetectCustomerService;
import com.tahoecn.xkc.service.risk.IBRiskinfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author YYY
 * @since 2020-05-06
 */
@Service
public class BRiskinfoServiceImpl extends ServiceImpl<BRiskinfoMapper, BRiskinfo> implements IBRiskinfoService {

    //集团顶级id
    private final static String topBloc = "BFD658A5-F645-468C-A11C-FB1689C1A166";
    //天
    int day = 1000 * 60 * 60 * 24;
    //小时
    int hour = 1000 * 60 * 60;
    //分钟
    int minute = 1000 * 60;
    @Resource
    private SOrganizationMapper sOrganizationMapper;

    @Resource
    private BProjectMapper bProjectMapper;

    @Resource
    private BChannelorgMapper bChannelorgMapper;

    @Resource
    private BSalesgroupmemberMapper bSalesgroupmemberMapper;

    @Resource
    private BRiskconfigMapper bRiskconfigMapper;

    @Resource
    private BCustomermobilesearchMapper bCustomermobilesearchMapper;

    @Resource
    private BRisknnamelogMapper bRisknnamelogMapper;

    @Resource
    private BOpportunityMapper bOpportunityMapper;

    @Resource
    private BClueruleMapper bClueruleMapper;

    @Resource
    private STrade2CstMapper sTrade2CstMapper;

    @Resource
    private BCustomerattachMapper bCustomerattachMapper;

    @Resource
    private BSalesuserMapper bSalesuserMapper;

    @Resource
    private BClueMapper bClueMapper;

    @Resource
    private BCustomerMapper bCustomerMapper;

    @Autowired
    private FaceDetectCustomerService faceDetectCustomerService;

    @Override
    public JSONResult info(String id) {
        BRiskinfo bRiskinfo = this.baseMapper.selectById(id);
        Map result = null;
        if (null != bRiskinfo) {
            switch (bRiskinfo.getRiskType()) {
                case 0://防截客
                    result = protectCustomer(bRiskinfo);
                    break;
                case 1://人脸识别
                    result = face(bRiskinfo);
                    break;
                case 2://搜电
                    result = searchMobile(bRiskinfo);
                    break;
                case 3://修改姓名
                    result = editName(bRiskinfo);
                    break;
                case 4://联名
                    result = jointName(bRiskinfo);
                    break;
                case 5://短期成交
                    result = shortDeal(bRiskinfo);
                    break;
                case 7://认筹未刷卡
                    result = unverified(bRiskinfo);
                    break;
            }
        }
        return ResultUtil.setJsonResult(TipsEnum.Success.getCode(), result);
    }


    @Override
    public IPage list(TiskInfoFindVO vo) {
        QueryWrapper<BRiskinfo> wrapper = new QueryWrapper();
        if (StringUtils.isNotEmpty(vo.getRegionalId()) ||
                StringUtils.isNotEmpty(vo.getCityId()) ||
                StringUtils.isNotEmpty(vo.getProjectId())) {
            return findInfoData(vo, directFindInfo(vo, wrapper));
        } else /* if (null == vo.getLevel()|| vo.getLevel() == 0)*/ {//集团
            return findInfoData(vo, wrapper);
        }/* else if (vo.getLevel() == 3) {//项目
            tiskInfoTreeVos.addAll(findInfoData(vo, wrapper.eq("ProjectId", vo.getId())));
        } else if (vo.getLevel() == 2) {//地产公司
            Object[] objects = this.bProjectMapper.selectList(new QueryWrapper<BProject>() {{
                eq("Status", 1);
                eq("IsDel", 0);
                eq("BUGUID", vo.getId());
                eq("Level", 1);
            }}).stream().map(i -> i.getId()).toArray();
            if (null != objects && objects.length > 1) {
                tiskInfoTreeVos.addAll(findInfoData(vo, wrapper.in("ProjectId", objects)));
            }
        } else {//板块,区域
            tiskInfoTreeVos.addAll(findInfoData(vo, wrapper.in("CityId", this.sOrganizationMapper.selectList(new QueryWrapper<SOrganization>() {{
                eq("Status", 1);
                eq("IsDel", 0);
                eq("PID", vo.getId());
            }}).stream().map(SOrganization::getId).toArray())));
        }*/
    }

    @Override
    public List<TiskInfoTreeVO> listTree(TiskTreeFindVO vo) {
        List<TiskInfoTreeVO> tiskInfoTreeVos = Lists.newArrayList();
        if (StringUtils.isNotEmpty(vo.getRegionalId()) ||
                StringUtils.isNotEmpty(vo.getCityId()) ||
                StringUtils.isNotEmpty(vo.getProjectId())) {
            tiskInfoTreeVos.addAll(directFind(vo));
//        } else if (null == vo.getLevel()) {
//            //查询顶级集团痛点总数
//            tiskInfoTreeVos.add(listTreeStart(vo.getStartTime(), vo.getEndTime()));
        } else if (null != vo.getLevel() && vo.getLevel() >= 2) {//查询项目痛点总数
            tiskInfoTreeVos.addAll(projectListTree(vo));
        } else {
            tiskInfoTreeVos.addAll(blocListTree(vo));
        }
        return tiskInfoTreeVos;
    }

    @Override
    public void listExport(TiskInfoFindVO vo, HttpServletResponse response) throws Exception {
        List<ExcelExportEntity> entity = new ArrayList<ExcelExportEntity>();
        entity.add(new ExcelExportEntity("区域", "regionalName"));
        entity.add(new ExcelExportEntity("项目", "projectName"));
        entity.add(new ExcelExportEntity("风险类别", "riskType"));
        entity.add(new ExcelExportEntity("客户名称", "customerName"));
        entity.add(new ExcelExportEntity("手机号", "customerMobile"));
        entity.add(new ExcelExportEntity("客户状态", "customerStatusName"));
        entity.add(new ExcelExportEntity("置业顾问", "saleUserName"));
        entity.add(new ExcelExportEntity("经纪人", "reportUserName"));
        entity.add(new ExcelExportEntity("渠道名称", "orgName"));
        entity.add(new ExcelExportEntity("渠道类型", "adviserGroupName"));
        entity.add(new ExcelExportEntity("原渠道类型", "yAdviserGroupName"));
        entity.add(new ExcelExportEntity("原渠道名称", "yOrgName"));
        entity.add(new ExcelExportEntity("原置业顾问姓名", "ySaleUserName"));
        entity.add(new ExcelExportEntity("原经纪人", "yReportUserName"));
        entity.add(new ExcelExportEntity("统计时间", "createTime"));
        Map<Integer, List<Map<String, Object>>> datasMap = this.baseMapper.listExport(vo)
                .stream().collect(Collectors.groupingBy(i -> Integer.valueOf(i.get("RiskType").toString())));
        List<Map<String, Object>> maps = datasMap.keySet().stream().map(i -> {
            if (i == 4) {
                return datasMap.get(i).stream().map(this::changeTiskInfo2).collect(Collectors.toList());
            } else {
                return datasMap.get(i).stream().map(this::changeTiskInfo).collect(Collectors.toList());
            }
        }).flatMap(Collection::stream).distinct().map(i -> changeDate(i)).collect(Collectors.toList());

        /*List<Map<String, Object>> maps = (List<Map<String, Object>>)datasMap.keySet().stream().map(i -> {
            if (i == 6) {
                List l = Lists.newArrayList();
                datasMap.get(i).stream().
                        collect()).
                        forEach((k, v) -> l.add(v.stream().map(this::changeTiskInfo2).collect(Collectors.toList())));
                return l;
            } else {
                return datasMap.get(i).stream().map(this::changeTiskInfo).collect(Collectors.toList());
            }
        }).flatMap(i -> ((List)i).stream()).distinct().map(this::changeDate).collect(Collectors.toList());*/
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
        String name = "风控痛点列表" + dtf2.format(time) + ".xls";
        ExcelUtil.exportExcel(entity, maps, name, response);
    }

    @Override
    public void listTreeExport(HttpServletResponse response, String regionalId, String cityId,
                               String projectId, Date startTime, Date endTime) throws Exception {
        List<ExcelExportEntity> entity = new ArrayList<ExcelExportEntity>();
        entity.add(new ExcelExportEntity("区域", "regionalName"));
        entity.add(new ExcelExportEntity("城市", "cityName"));
        entity.add(new ExcelExportEntity("项目", "projectName"));
        entity.add(new ExcelExportEntity("防截客", "protectCustomer"));
        entity.add(new ExcelExportEntity("人脸识别", "face"));
        entity.add(new ExcelExportEntity("搜电未报备", "searchMobile"));
        entity.add(new ExcelExportEntity("修改客户姓名", "editName"));
        entity.add(new ExcelExportEntity("联名购房", "jointName"));
        entity.add(new ExcelExportEntity("短期成交", "shortDeal"));
        entity.add(new ExcelExportEntity("认筹未刷证", "unverified"));
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
        String name = "风控痛点总数" + dtf2.format(time) + ".xls";
        List<Map<String, Object>> exportBloc = getExportBloc(regionalId, cityId, projectId, startTime, endTime);
        ExcelUtil.exportExcel(entity, exportBloc, name, response);
    }

    //认筹未刷卡
    private Map unverified(BRiskinfo bRiskinfo) {
        return new HashMap() {{
            put("projectName", bRiskinfo.getProjectName());//项目名称
            put("riskType", bRiskinfo.getRiskType());//风险类型
            put("customerName", bRiskinfo.getCustomerName());//客户姓名
            put("customerMobile", bRiskinfo.getCustomerMobile());//联系方式
            putAll(currentAndFirst(bRiskinfo));
            //报备
            Map dm = this;
            put("report", new HashMap() {{
                put("orgName", dm.get("relatedOrgName"));//渠道名称
                put("reportUserName", dm.get("relatedReportUserName"));//报备人
                put("adviserGroupName", dm.get("relatedAdviserGroupName"));//渠道类型
                put("reportTime", bRiskinfo.getReportTime());//报备时间
            }});
            Map faceImg = getFaceImg(bRiskinfo);
            //到访
            put("visit", new HashMap() {{
                put("saleUserName", bRiskinfo.getSaleUserName());//置业顾问：李强
                put("theFirstVisitDate", bRiskinfo.getTheFirstVisitDate());//到访时间
                put("faceImg", faceImg.get("imgUrl"));//人脸识别http://mobilesaleadmin.tahoecndemo.com:5678/
                put("firstFaceTime", faceImg.get("firstFaceTime"));//识别时间
            }});
            //查询attach后查询销售
            BCustomerattach bCustomerattach = bCustomerattachMapper.selectOne(new QueryWrapper<BCustomerattach>() {{
                eq("OpportunityID", bRiskinfo.getOpportunityId());
                gt("SalesStatus", 2);
            }});
            List<Map<String, Object>> subscribe = sTrade2CstMapper.subscribe(bCustomerattach.getId());
            //认购
            put("subscribe", new ArrayList() {{
                subscribe.stream().forEach(i -> {
                    add(new HashMap() {{
                        put("saleUserName", bRiskinfo.getSaleUserName());//置业顾问：李强
                        put("subscribeTime", DateUtil.formatDate((Date)i.get("QSDate")));//认购时间：2020-04-06  19:10:54
                        put("houseName", i.get("RoomCode"));//房源名称：莲葩园12号楼一单元0802
                        put("houseArea", i.get("BldArea"));//建筑面积：120m²
                    }});
                });
            }});
            List<Map<String, Object>> agreement = sTrade2CstMapper.agreement(bCustomerattach.getId());
            //签约agreement
            put("contract", new ArrayList() {{
                agreement.stream().forEach(i -> {
                    add(new HashMap() {{
                        put("saleUserName", bRiskinfo.getSaleUserName());//置业顾问：李强
                        put("contractTime", DateUtil.formatDate((Date)i.get("ContractTime")));//签约时间：2020-04-08  15:30:26
                        put("timeDdiffer", (((Date) i.get("ContractTime")).getTime() - bRiskinfo.getReportTime().getTime()) / day);//时间差值：10小时
                        put("houseName", i.get("RoomCode"));//房源名称：莲葩园12号楼一单元0802
                        put("houseArea", i.get("BldArea"));//建筑面积：120m²
                    }});
                });
            }});
        }};
    }

    //短期
    private Map shortDeal(BRiskinfo bRiskinfo) {
        return new HashMap() {{
            put("projectName", bRiskinfo.getProjectName());//项目名称
            put("riskType", bRiskinfo.getRiskType());//风险类型
            put("customerName", bRiskinfo.getCustomerName());//客户姓名
            put("customerMobile", bRiskinfo.getCustomerMobile());//联系方式
            putAll(currentAndFirst(bRiskinfo));
            //报备
            Map dm = this;
            put("report", new HashMap() {{
                put("orgName", dm.get("relatedOrgName"));//渠道名称
                put("reportUserName", dm.get("relatedReportUserName"));//报备人
                put("adviserGroupName", dm.get("relatedAdviserGroupName"));//渠道类型
                put("reportTime", bRiskinfo.getReportTime());//报备时间
            }});
            Map faceImg = getFaceImg(bRiskinfo);
            //到访
            put("visit", new HashMap() {{
                put("saleUserName", bRiskinfo.getSaleUserName());//置业顾问：李强
                put("theFirstVisitDate", bRiskinfo.getTheFirstVisitDate());//到访时间
                put("faceImg", faceImg.get("imgUrl"));//人脸识别http://mobilesaleadmin.tahoecndemo.com:5678/
                put("firstFaceTime", faceImg.get("firstFaceTime"));//识别时间
            }});
            //查询attach后查询销售
            BCustomerattach bCustomerattach = bCustomerattachMapper.selectOne(new QueryWrapper<BCustomerattach>() {{
                eq("OpportunityID", bRiskinfo.getOpportunityId());
                gt("SalesStatus", 2);
            }});
            List<Map<String, Object>> subscribe = sTrade2CstMapper.subscribe(bCustomerattach.getId());
            //认购
            put("subscribe", new ArrayList() {{
                subscribe.stream().forEach(i -> {
                    add(new HashMap() {{
                        put("saleUserName", bRiskinfo.getSaleUserName());//置业顾问：李强
                        put("subscribeTime", DateUtil.formatDate((Date)i.get("QSDate")));//认购时间：2020-04-06  19:10:54
                        put("timeDdiffer", (((Date) i.get("QSDate")).getTime() - bRiskinfo.getReportTime().getTime()) / hour);//时间差值：10小时
                        put("houseName", i.get("RoomCode"));//房源名称：莲葩园12号楼一单元0802
                        put("houseArea", i.get("BldArea"));//建筑面积：120m²
                    }});
                });
            }});
            //时间限定
            put("delimit", new HashMap() {{
                BRiskconfig bRiskconfig = bRiskconfigMapper.selectById(bRiskinfo.getRiskConfigId());
                put("shortDealTime", bRiskconfig.getShortDealTime());//配置值：2天
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(bRiskinfo.getReportTime());
                calendar.set(Calendar.HOUR_OF_DAY, 23);
                calendar.set(Calendar.MINUTE, 59);
                calendar.set(Calendar.SECOND, 59);
                Date d = calendar.getTime();
                put("defineTime", DateUtil.date(d.getTime() + (bRiskconfig.getShortDealTime() * day)));//限定时间：2020-04-07  09:10:54
            }});
            List<Map<String, Object>> agreement = sTrade2CstMapper.agreement(bCustomerattach.getId());
            //签约agreement
            put("contract", new ArrayList() {{
                agreement.stream().forEach(i -> {
                    add(new HashMap() {{
                        put("saleUserName", bRiskinfo.getSaleUserName());//置业顾问：李强
                        put("contractTime", DateUtil.formatDate((Date)i.get("ContractTime")));//签约时间：2020-04-08  15:30:26
                        put("timeDdiffer", (((Date) i.get("ContractTime")).getTime() - bRiskinfo.getReportTime().getTime()) / day);//时间差值：10小时
                        put("houseName", i.get("RoomCode"));//房源名称：莲葩园12号楼一单元0802
                        put("houseArea", i.get("BldArea"));//建筑面积：120m²
                    }});
                });
            }});
        }};
    }

    //联名
    private Map jointName(BRiskinfo bRiskinfo) {
        return new HashMap() {{
            put("riskType", bRiskinfo.getRiskType());//风险类型
            put("projectName", bRiskinfo.getProjectName());//项目名称
            List<BRiskinfo> bRiskconfigs = baseMapper.selectList(new QueryWrapper<BRiskinfo>() {{
                eq("RiskType", 6);
                eq("JointNameMasterId", bRiskinfo.getId());
            }});
            List user = Lists.newArrayList();
            List report = Lists.newArrayList();
            List visit = Lists.newArrayList();
            List subscribe = Lists.newArrayList();
            List<Map<String, Object>> agreement = Lists.newArrayList();
            //主
            jointNameChange(bRiskinfo, user, report, visit, subscribe, agreement);
            //从
            bRiskconfigs.stream().forEach(i -> {
                jointNameChange(i, user, report, visit, subscribe, agreement);
            });
            //用户信息
            put("user", user);
            //报备
            put("report", report);
            //到访
            put("visit", visit);
            //认购
            put("subscribe", subscribe);
            //签约
            if (null != agreement && agreement.size() > 0)
                put("contract", new ArrayList() {{
                    agreement.stream().forEach(i -> {
                        add(new HashMap() {{
                            put("saleUserName", bRiskinfo.getSaleUserName());//置业顾问：李强
                            put("contractTime", DateUtil.formatDate((Date)i.get("ContractTime")));//签约时间：2020-04-08  15:30:26
                            put("houseName", i.get("RoomCode"));//房源名称：莲葩园12号楼一单元0802
                            put("houseArea", i.get("BldArea"));//建筑面积：120m²
                        }});
                    });
                }});
        }};
    }

    private void jointNameChange(BRiskinfo bRiskinfo, List user, List report, List visit, List subscribe, List<Map<String, Object>> agreement) {
        Map<String, Object> currentAndFirst = currentAndFirst(bRiskinfo);
        currentAndFirst.put("customerName", bRiskinfo.getCustomerName());
        currentAndFirst.put("customerMobile", bRiskinfo.getCustomerMobile());
        user.add(currentAndFirst);
        report.add(new HashMap() {{
            put("orgName", currentAndFirst.get("relatedOrgName"));//渠道名称
            put("reportUserName", currentAndFirst.get("relatedReportUserName"));//报备人
            put("adviserGroupName", currentAndFirst.get("relatedAdviserGroupName"));//渠道类型
            put("reportTime", bRiskinfo.getReportTime());//报备时间
        }});
        Map faceImg = getFaceImg(bRiskinfo);
        visit.add(new HashMap() {{
            put("saleUserName", bRiskinfo.getSaleUserName());//置业顾问：李强
            put("theFirstVisitDate", bRiskinfo.getTheFirstVisitDate());//到访时间
            put("faceImg", faceImg.get("imgUrl"));//人脸识别http://mobilesaleadmin.tahoecndemo.com:5678/
            put("firstFaceTime", faceImg.get("firstFaceTime"));//识别时间
        }});
        //查询attach后查询销售
        BCustomerattach bCustomerattach = bCustomerattachMapper.selectOne(new QueryWrapper<BCustomerattach>() {{
            eq("OpportunityID", bRiskinfo.getOpportunityId());
            gt("SalesStatus", 2);
        }});
        List<Map<String, Object>> subscribeDate = sTrade2CstMapper.subscribe(bCustomerattach.getId());
        subscribeDate.stream().forEach(i1 -> {
            subscribe.add(new HashMap() {{
                put("saleUserName", bRiskinfo.getSaleUserName());//置业顾问：李强
                put("subscribeTime", DateUtil.formatDate((Date) i1.get("QSDate")));//认购时间：2020-04-06  19:10:54
                put("houseName", i1.get("RoomCode"));//房源名称：莲葩园12号楼一单元0802
                put("houseArea", i1.get("BldArea"));//建筑面积：120m²
            }});
        });
        //签约
        List<Map<String, Object>> agreementFind = sTrade2CstMapper.agreement(bCustomerattach.getId());
        if (null != agreementFind && agreementFind.size() > 0) agreement.addAll(agreementFind);
    }

    //修改姓名
    private Map editName(BRiskinfo bRiskinfo) {
        return new HashMap() {{
            put("projectName", bRiskinfo.getProjectName());//项目名称
            put("riskType", bRiskinfo.getRiskType());//风险类型
            put("customerName", bRiskinfo.getCustomerName());//客户姓名
            put("customerMobile", bRiskinfo.getCustomerMobile());//联系方式
            putAll(currentAndFirst(bRiskinfo));
            //报备
            Map dm = this;
            put("report", new HashMap() {{
                put("orgName", dm.get("relatedOrgName"));//渠道名称
                put("reportUserName", dm.get("relatedReportUserName"));//报备人
                put("adviserGroupName", dm.get("relatedAdviserGroupName"));//渠道类型
                put("reportTime", bRiskinfo.getReportTime());//报备时间
            }});
            Map faceImg = getFaceImg(bRiskinfo);
            //到访
            put("visit", new HashMap() {{
                put("saleUserName", bRiskinfo.getSaleUserName());//置业顾问：李强
                put("theFirstVisitDate", bRiskinfo.getTheFirstVisitDate());//到访时间
                put("faceImg", faceImg.get("imgUrl"));//人脸识别http://mobilesaleadmin.tahoecndemo.com:5678/
                put("firstFaceTime", faceImg.get("firstFaceTime"));//识别时间
            }});
            List<BRisknnamelog> bRisknnamelogs = bRisknnamelogMapper.selectList(new QueryWrapper<BRisknnamelog>() {{
                eq("OpportunityId", bRiskinfo.getOpportunityId());
                orderByAsc("CreateTime");
            }});
            //修改客户姓名
            put("updateCustomerName", new ArrayList() {{
                bRisknnamelogs.stream().forEach(i1 -> {
                    add(new HashMap() {{
                        put("customerOldName", i1.getCustomerOldName());//客户原姓名
                        put("customerNewName", i1.getCustomerNewName());//修改后姓名
                        put("saleUserName", i1.getSaleUserName());//修改人
                        put("updateTime", i1.getCreateTime());//修改时间
                    }});
                });
            }});
            putAll(subscribeAndContract(bRiskinfo));
        }};
    }

    //搜电
    private Map searchMobile(BRiskinfo bRiskinfo) {
        return new HashMap() {{
            put("projectName", bRiskinfo.getProjectName());//项目名称
            put("riskType", bRiskinfo.getRiskType());//风险类型
            put("customerName", bRiskinfo.getCustomerName());//客户姓名
            put("customerMobile", bRiskinfo.getCustomerMobile());//联系方式
            putAll(currentAndFirst(bRiskinfo));
            /*List<BCustomermobilesearch> bCustomermobilesearches =  bCustomermobilesearchMapper.selectList(new QueryWrapper<BCustomermobilesearch>() {{
                eq("OpportunityId", bRiskinfo.getOpportunityId());
                orderByAsc("CreateTime");
            }});*/
            List<Map<String, Object>> bCustomermobilesearches = bCustomermobilesearchMapper.fkSearchMobileInfo(
                    bRiskinfo.getProjectId(),
                    bRiskinfo.getCustomerMobile(),
                    bRiskinfo.getReportTime()
            );
            //搜电未报备
            put("searchMobileNo", new ArrayList() {{
                bCustomermobilesearches.stream().forEach(i -> {
                    add(new HashMap() {{
                        BSalesuser bSalesuser = bSalesuserMapper.selectById((String) i.get("Creator"));
                        put("searchPeople", bSalesuser.getName());//搜索人：王坤（置业顾问)
                        put("timeDdiffer", (bRiskinfo.getReportTime().getTime() - ((Date) i.get("CreateTime")).getTime()) / day);//时间差值：4天
                        put("searchTime", i.get("CreateTime"));//搜索时间：2020-04-02 09:20:29
                    }});
                });
            }});
            //报备
            Map dm = this;
            put("report", new HashMap() {{
                put("orgName", dm.get("relatedOrgName"));//渠道名称
                put("reportUserName", dm.get("relatedReportUserName"));//报备人
                put("adviserGroupName", dm.get("relatedAdviserGroupName"));//渠道类型
                put("reportTime", bRiskinfo.getReportTime());//报备时间
            }});
            if (StringUtils.isEmpty(bRiskinfo.getOpportunityId())) {
                Map<String, Object> fkSearchInfo = bClueMapper.fkSearchInfo(bRiskinfo.getClueId());
                if (StringUtils.isNotEmpty((String) fkSearchInfo.get("ID"))) {
                    bRiskinfo.setOpportunityId((String) fkSearchInfo.get("ID"));
                    bRiskinfo.setOpportunityId((String) fkSearchInfo.get("ID"));
                }
            }
            //到访
            if (StringUtils.isNotEmpty(bRiskinfo.getOpportunityId())) {
                Map faceImg = getFaceImg(bRiskinfo);
                Map<String, Object> fkSearchInfo = bOpportunityMapper.fkSearchInfo(bRiskinfo.getOpportunityId());
                put("visit", new HashMap() {{
                    put("saleUserName", fkSearchInfo.get("SaleUserName"));//置业顾问：李强
                    put("timeDdiffer", (((Date) fkSearchInfo.get("TheFirstVisitDate")).getTime() - ((Date) fkSearchInfo.get("ReportTime")).getTime()) / hour);//时间差值：10分钟
                    put("theFirstVisitDate", fkSearchInfo.get("TheFirstVisitDate"));//到访时间
                    put("faceImg", faceImg.get("imgUrl"));//人脸识别http://mobilesaleadmin.tahoecndemo.com:5678/
                    put("firstFaceTime", faceImg.get("firstFaceTime"));//识别时间
                }});
            }
            putAll(subscribeAndContract(bRiskinfo));
        }};
    }

    //人脸识别
    private Map face(BRiskinfo bRiskinfo) {
        return new HashMap() {{
            put("projectName", bRiskinfo.getProjectName());//项目名称
            put("riskType", bRiskinfo.getRiskType());//风险类型
            put("customerName", bRiskinfo.getCustomerName());//客户姓名
            put("customerMobile", bRiskinfo.getCustomerMobile());//联系方式
            putAll(currentAndFirst(bRiskinfo));
            //人脸识别
            put("faceDiscern", new HashMap() {{
                put("firstTime", bRiskinfo.getFirstFaceTime());//首访时间：2020-04-06 09:20:29
                put("imgLink", bRiskinfo.getImgPath());//图片链接：http://mobilesaleadmin.tahoecndemo.com:5678/
                put("videoLink", "");//短视频链接：http://mobilesaleadmin.tahoecndemo.com:5678/
            }});
            //报备
            Map dm = this;
            put("report", new HashMap() {{
                put("orgName", dm.get("relatedOrgName"));//渠道名称
                put("reportUserName", dm.get("relatedReportUserName"));//报备人
                put("adviserGroupName", dm.get("relatedAdviserGroupName"));//渠道类型
                put("reportTime", bRiskinfo.getReportTime());//报备时间
            }});
            Map faceImg = getFaceImg(bRiskinfo);
            //到访
            put("visit", new HashMap() {{
                put("saleUserName", bRiskinfo.getSaleUserName());//置业顾问：李强
                put("theFirstVisitDate", bRiskinfo.getTheFirstVisitDate());//到访时间
                put("faceImg", faceImg.get("imgUrl"));//人脸识别http://mobilesaleadmin.tahoecndemo.com:5678/
                put("firstFaceTime", faceImg.get("firstFaceTime"));//识别时间
            }});
            putAll(subscribeAndContract(bRiskinfo));
        }};
    }

    //防截客
    private Map protectCustomer(BRiskinfo bRiskinfo) {
        return new HashMap() {{
            put("projectName", bRiskinfo.getProjectName());//项目名称
            put("riskType", bRiskinfo.getRiskType());//风险类型
            put("customerName", bRiskinfo.getCustomerName());//客户姓名
            put("customerMobile", bRiskinfo.getCustomerMobile());//联系方式
            putAll(currentAndFirst(bRiskinfo));
            //报备
            Map dm = this;
            put("report", new HashMap() {{
                put("orgName", dm.get("relatedOrgName"));//渠道名称
                put("reportUserName", dm.get("relatedReportUserName"));//报备人
                put("adviserGroupName", dm.get("relatedAdviserGroupName"));//渠道类型
                put("reportTime", bRiskinfo.getReportTime());//报备时间
            }});
            //项目防拦截
            put("projectProtect", new HashMap() {{
                put("protectTime", bRiskinfo.getPreInterceptTime());//防拦截时间：30分钟
                put("defineTime", DateUtil.date(bRiskinfo.getReportTime().getTime() + (bRiskinfo.getPreInterceptTime() * 60 * 1000)));//限定时间：2020-04-06 09:30:54
            }});
            Map faceImg = getFaceImg(bRiskinfo);
            //到访
            put("visit", new HashMap() {{
                put("saleUserName", bRiskinfo.getSaleUserName());//置业顾问：李强
//                put("timeDdiffer", (bRiskinfo.getTheFirstVisitDate().getTime() - bRiskinfo.getReportTime().getTime()) / minute);//时间差值：10分钟
                long a = bRiskinfo.getTheFirstVisitDate().getTime() - bRiskinfo.getReportTime().getTime();
                long b = a / minute;
                put("timeDdiffer", b);//时间差值：10分钟
                put("theFirstVisitDate", bRiskinfo.getTheFirstVisitDate());//到访时间
                put("faceImg", faceImg.get("imgUrl"));//人脸识别http://mobilesaleadmin.tahoecndemo.com:5678/
                put("firstFaceTime", faceImg.get("firstFaceTime"));//识别时间
            }});
            //风控时间限定
            put("timeDefine", new HashMap() {{
                BRiskconfig bRiskconfig = bRiskconfigMapper.selectById(bRiskinfo.getRiskConfigId());
                put("configTime", bRiskconfig.getProtectTime());//配置值：30分钟
                //项目时间
                put("defineTime", DateUtil.date(bRiskinfo.getReportTime().getTime() + (bRiskinfo.getPreInterceptTime() * 60 * 1000) + (bRiskconfig.getProtectTime() * 60 * 1000)));//限定时间：2020-04-06  10:10:54
            }});
            //时间限定
            put("delimit", new HashMap() {{
                BRiskconfig bRiskconfig = bRiskconfigMapper.selectById(bRiskinfo.getRiskConfigId());
                put("shortDealTime", bRiskconfig.getShortDealTime());//配置值：24小时
                put("defineTime", DateUtil.date(bRiskinfo.getReportTime().getTime() + (bRiskconfig.getShortDealTime() * minute)));//限定时间：2020-04-07  09:10:54
            }});
            putAll(subscribeAndContract(bRiskinfo));
        }};
    }

    private Map subscribeAndContract(BRiskinfo bRiskinfo) {
        return new HashMap() {{
            //查询attach后查询销售
            BCustomerattach bCustomerattach = bCustomerattachMapper.selectOne(new QueryWrapper<BCustomerattach>() {{
                eq("OpportunityID", bRiskinfo.getOpportunityId());
                gt("SalesStatus", 2);
            }});
            if (StringUtils.isNotEmpty(bRiskinfo.getOpportunityId()) && null != bCustomerattach) {
                List<Map<String, Object>> subscribe = sTrade2CstMapper.subscribe(bCustomerattach.getId());
                if (null != subscribe && subscribe.size() > 0) {
                    //认购
                    put("subscribe", new ArrayList() {{
                        subscribe.stream().forEach(i -> {
                            add(new HashMap() {{
                                put("saleUserName", bRiskinfo.getSaleUserName());//置业顾问：李强
                                put("subscribeTime", DateUtil.formatDate((Date)i.get("QSDate")));//认购时间：2020-04-06  19:10:54
                                put("houseName", i.get("RoomCode"));//房源名称：莲葩园12号楼一单元0802
                                put("houseArea", i.get("BldArea"));//建筑面积：120m²
                            }});
                        });
                    }});
                }
                List<Map<String, Object>> agreement = sTrade2CstMapper.agreement(bCustomerattach.getId());
                //签约agreement
                if (null != agreement && agreement.size() > 0) {
                    put("contract", new ArrayList() {{
                        agreement.stream().forEach(i -> {
                            add(new HashMap() {{
                                put("saleUserName", bRiskinfo.getSaleUserName());//置业顾问：李强
                                put("contractTime", DateUtil.formatDate((Date)i.get("ContractTime")));//签约时间：2020-04-08  15:30:26
                                put("houseName", i.get("RoomCode"));//房源名称：莲葩园12号楼一单元0802
                                put("houseArea", i.get("BldArea"));//建筑面积：120m²
                            }});
                        });
                    }});
                }
            }
        }};
    }

    private Map<String, Object> currentAndFirst(BRiskinfo bRiskinfo) {
        return new HashMap<String, Object>() {{
            String orgName = bRiskinfo.getAdviserGroupName();//渠道名称
            String adviserGroupName = "销售人员";//渠道类型
            String reportUserName = bRiskinfo.getSaleUserName();//报备人姓名
            Map<String, Object> saleGroupName = bSalesgroupmemberMapper.findSaleGroupName(
                    StringUtils.isNotEmpty(bRiskinfo.getSaleUserID()) ? bRiskinfo.getSaleUserID() : bRiskinfo.getReportUserID(),
                    bRiskinfo.getProjectId()
            );
            if (null == bRiskinfo.getAdviserGroupID() && saleGroupName != null) {
                orgName = saleGroupName.get("ChannelType") + "-" + saleGroupName.get("Name");
            }
            if (StringUtils.isNotEmpty(bRiskinfo.getReportUserID())) {//中介
                adviserGroupName = bRiskinfo.getAdviserGroupName();
                reportUserName = bRiskinfo.getReportUserName();
                if (StringUtils.isNotEmpty(bRiskinfo.getOrgId())) {
                    BChannelorg bChannelorg = bChannelorgMapper.selectById(bRiskinfo.getOrgId());
                    if (null != bChannelorg) orgName = bChannelorg.getOrgName();
                }
            }
            //原有
            put("relatedSaleUserName", bRiskinfo.getSaleUserName());//置业顾问姓名
            put("relatedOrgName", orgName);
            put("relatedAdviserGroupName", adviserGroupName);
            put("relatedReportUserName", reportUserName);

            //-------------------------------------------------------------------------------------------------------------------------------
            Map<String, Object> fkSearchCurrentInfo = null;
            if (StringUtils.isNotEmpty(bRiskinfo.getOpportunityId())) {
                fkSearchCurrentInfo = bOpportunityMapper.fkSearchCurrentInfo(bRiskinfo.getCustomerMobile(), bRiskinfo.getProjectId());
            } else {
                fkSearchCurrentInfo = bClueMapper.fkSearchCurrentInfo(bRiskinfo.getCustomerMobile(), bRiskinfo.getProjectId());
            }

            String orgName2 = (String) fkSearchCurrentInfo.get("adviserGroupName");//渠道名称
            String adviserGroupName2 = "销售人员";//渠道类型bRiskinfo.get()
            String reportUserName2 = (String) fkSearchCurrentInfo.get("SaleUserName");//报备人姓名
            Map<String, Object> saleGroupName2 = bSalesgroupmemberMapper.findSaleGroupName(
                    StringUtils.isNotEmpty((String) fkSearchCurrentInfo.get("SaleUserID")) ? (String) fkSearchCurrentInfo.get("SaleUserID") : (String) fkSearchCurrentInfo.get("ReportUserID"),
                    bRiskinfo.getProjectId()
            );
            if (StringUtils.isEmpty((String) fkSearchCurrentInfo.get("adviserGroupName")) && saleGroupName2 != null) {
                orgName2 = saleGroupName2.get("ChannelType") + "-" + saleGroupName2.get("Name");
            }
            if (StringUtils.isNotEmpty((String) fkSearchCurrentInfo.get("ReportUserName"))) {//中介
                adviserGroupName2 = (String) fkSearchCurrentInfo.get("adviserGroupName");
                reportUserName2 = (String) fkSearchCurrentInfo.get("ReportUserName");
                if (StringUtils.isNotEmpty((String) fkSearchCurrentInfo.get("OrgID"))) {
                    BChannelorg bChannelorg = bChannelorgMapper.selectById((String) fkSearchCurrentInfo.get("OrgID"));
                    if (null != bChannelorg) orgName2 = bChannelorg.getOrgName();
                }
            }
            //现有
            put("orgName", orgName2);//渠道类型
            put("adviserGroupName", adviserGroupName2);//渠道类型名称
            put("saleUserName", fkSearchCurrentInfo.get("SaleUserName"));//置业顾问姓名
            put("reportUserName", reportUserName2);//报备人姓名

        }};
    }


    private QueryWrapper<BRiskinfo> directFindInfo(TiskInfoFindVO vo, QueryWrapper wrapper) {
        if (StringUtils.isNotEmpty(vo.getProjectId())) {
            wrapper.eq("ProjectId", vo.getProjectId());
        } else {
            if (StringUtils.isNotEmpty(vo.getCityId())) {
                wrapper.eq("CityId", vo.getCityId());
            } else {
                wrapper.eq("RegionalId", vo.getRegionalId());
            }
        }
        return wrapper;
    }

    private IPage findInfoData(TiskInfoFindVO vo, QueryWrapper<BRiskinfo> wrapper) {
        if (StringUtils.isNotEmpty(vo.getUserName()))
            wrapper.like("CustomerName", "%" + vo.getUserName() + "%");
        if (null != vo.getStartTime() && null != vo.getEndTime())
            wrapper.between("CreateTime", vo.getStartTime(), vo.getEndTime());
        if (StringUtils.isNotEmpty(vo.getSaleUserName()))
            wrapper.like("SaleUserName", "%" + vo.getSaleUserName() + "%");
        if (StringUtils.isNotEmpty(vo.getCustomerMobile()))
            wrapper.eq("CustomerMobile", vo.getCustomerMobile());
        if (StringUtils.isNotEmpty(vo.getAdviserGroupID()) && vo.getAdviserGroupID().equals("zrfk")) {
            wrapper.isNull("AdviserGroupID");
        } else if (StringUtils.isNotEmpty(vo.getAdviserGroupID())) {
            //相关渠道
            wrapper.eq("AdviserGroupID", vo.getAdviserGroupID());
        }
        if (null != vo.getRiskType()) {
            wrapper.eq("riskType", vo.getRiskType());
        } else {
            wrapper.notIn("riskType", 6);
        }
        if (StringUtils.isNotEmpty(vo.getReportUserName()))
            wrapper.like("ReportUserName", "%" + vo.getReportUserName() + "%");
        if (null != vo.getCustomerStatus())
            wrapper.eq("CustomerStatus", vo.getCustomerStatus());

        /*Map<Integer, List<BRiskinfo>> datasMap = this.baseMapper.selectList(wrapper).stream().
                collect(Collectors.groupingBy(BRiskinfo::getRiskType));*/
        IPage page = super.page(new Page(vo.getPageNum(), vo.getPageSize()), wrapper);
        Map<Integer, List<BRiskinfo>> datasMap = (Map<Integer, List<BRiskinfo>>) page.getRecords().stream().
                collect(Collectors.groupingBy(BRiskinfo::getRiskType));
        List collect = datasMap.keySet().stream().map(i -> {
            if (i == 4) {
                return datasMap.get(i).stream().map(this::changeTiskInfo2).collect(Collectors.toList());
            } else {
                return datasMap.get(i).stream().map(this::changeTiskInfo).collect(Collectors.toList());
            }
        }).flatMap(Collection::stream).distinct().collect(Collectors.toList());
        page.setRecords(collect);
        return page;
    }

    private String properWord(String msg1, String msg2) {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.isEmpty(msg1) ? "{}" : msg1);
        sb.append(",");
        sb.append(StringUtils.isEmpty(msg2) ? "{}" : msg2);
        return sb.toString();
    }

    private TiskInfoVO changeTiskInfo2(Object o) {
        BRiskinfo bRiskinfo = this.bRiskinfo(o);
        List<BRiskinfo> jointNames = this.baseMapper.selectList(new QueryWrapper<BRiskinfo>() {{
            eq("JointNameMasterId", bRiskinfo.getId());
        }});
        return new TiskInfoVO() {{
            setId(bRiskinfo.getId());//主键
            setRegionalName(properWord(bRiskinfo.getRegionalName(), jointNames.get(0).getRegionalName()));//区域
            setProjectName(properWord(bRiskinfo.getProjectName(), jointNames.get(0).getProjectName()));//    项目
            setCustomerName(properWord(bRiskinfo.getCustomerName(), jointNames.get(0).getCustomerName()));//    客户名称
            setCustomerMobile(properWord(bRiskinfo.getCustomerMobile(), jointNames.get(0).getCustomerMobile()));//    手机号
            setCustomerStatusName(properWord(bRiskinfo.getCustomerStatusName(), jointNames.get(0).getCustomerStatusName()));//    客户状态
            setRiskType(bRiskinfo.getRiskType());//    风险类别
            setCreateTime(bRiskinfo.getCreateTime());//saleUserName
            Map<String, Object> currentAndFirstMap = currentAndFirst(bRiskinfo);
            Map<String, Object> jointNameMap = currentAndFirst(jointNames.get(0));
            setOrgName(properWord((String) currentAndFirstMap.get("orgName"), (String) jointNameMap.get("orgName")));//渠道名称
            setAdviserGroupName(properWord((String) currentAndFirstMap.get("adviserGroupName"), (String) jointNameMap.get("adviserGroupName")));//    渠道类型
            setSaleUserName(properWord((String) currentAndFirstMap.get("saleUserName"), (String) jointNameMap.get("saleUserName")));//    置业顾问
            setReportUserName(properWord((String) currentAndFirstMap.get("reportUserName"), (String) jointNameMap.get("reportUserName")));//    经纪人
            setyOrgName(properWord((String) currentAndFirstMap.get("relatedOrgName"), (String) jointNameMap.get("relatedOrgName")));//    原渠道类型
            setyAdviserGroupName(properWord((String) currentAndFirstMap.get("relatedAdviserGroupName"), (String) jointNameMap.get("relatedAdviserGroupName")));//    原渠道名称
            setySaleUserName(properWord((String) currentAndFirstMap.get("relatedSaleUserName"), (String) jointNameMap.get("relatedSaleUserName")));//原置业顾问姓名
            setyReportUserName(properWord((String) currentAndFirstMap.get("relatedReportUserName"), (String) jointNameMap.get("relatedReportUserName")));//    原经纪人
        }};
    }

    private BRiskinfo bRiskinfo(Object o) {
        BRiskinfo bRiskinfo = null;
        if (o instanceof Map) {
            Map<String, Object> map = (Map) o;
            bRiskinfo = new BRiskinfo() {{
                setId((String) map.get("ID"));
                setRegionalName((String) map.get("RegionalName"));
                setProjectName((String) map.get("ProjectName"));//    项目
                setCustomerName((String) map.get("CustomerName"));//    客户名称
                setCustomerMobile((String) map.get("CustomerMobile"));//    手机号
                setCustomerStatusName((String) map.get("CustomerStatusName"));//    客户状态
                setRiskType(Integer.parseInt(map.get("RiskType").toString()));//    风险类别
                setCreateTime((Date) map.get("CreateTime"));//saleUserName
                setSaleUserName((String) map.get("SaleUserName"));
                setProjectId((String) map.get("ProjectId"));
                setSaleUserID((String) map.get("SaleUserID"));
                setReportUserID((String) map.get("ReportUserID"));
                setAdviserGroupName((String) map.get("AdviserGroupName"));
                setReportUserName((String) map.get("ReportUserName"));
                setOrgId((String) map.get("OrgId"));
            }};
        } else {
            bRiskinfo = (BRiskinfo) o;
        }
        return bRiskinfo;
    }

    private TiskInfoVO changeTiskInfo(Object o) {
        BRiskinfo bRiskinfo = this.bRiskinfo(o);
        return new TiskInfoVO() {{
            setId(bRiskinfo.getId());//主键
            setRegionalName(bRiskinfo.getRegionalName());//区域
            setProjectName(bRiskinfo.getProjectName());//    项目
            setCustomerName(bRiskinfo.getCustomerName());//    客户名称
            setCustomerMobile(bRiskinfo.getCustomerMobile());//    手机号
            setCustomerStatusName(bRiskinfo.getCustomerStatusName());//    客户状态
            setRiskType(bRiskinfo.getRiskType());//    风险类别
            setCreateTime(bRiskinfo.getCreateTime());//saleUserName
            Map<String, Object> currentAndFirstMap = currentAndFirst(bRiskinfo);
            setOrgName((String) currentAndFirstMap.get("orgName"));//渠道名称
            setAdviserGroupName((String) currentAndFirstMap.get("adviserGroupName"));//    渠道类型
            setSaleUserName((String) currentAndFirstMap.get("saleUserName"));//    置业顾问
            setReportUserName((String) currentAndFirstMap.get("reportUserName"));//    经纪人
            setyOrgName((String) currentAndFirstMap.get("relatedOrgName"));//    原渠道类型
            setyAdviserGroupName((String) currentAndFirstMap.get("relatedAdviserGroupName"));//    原渠道名称
            setySaleUserName((String) currentAndFirstMap.get("relatedSaleUserName"));//原置业顾问姓名
            setyReportUserName((String) currentAndFirstMap.get("relatedReportUserName"));//    原经纪人
        }};
    }

    private Collection<? extends TiskInfoTreeVO> directFind(TiskTreeFindVO vo) {
        List<TiskInfoTreeVO> result = Lists.newArrayList();
        if (StringUtils.isNotEmpty(vo.getProjectId())) {
            BProject bProject = this.bProjectMapper.selectById(vo.getProjectId());
            if (null != bProject)
                result.add(new TiskInfoTreeVO() {{
                    setId(bProject.getId());
                    setLevel(3);
                    setTitel(bProject.getName());
                    baseMapper.selectList(new QueryWrapper<BRiskinfo>() {{
                        eq("ProjectId", bProject.getId());
                        if (null != vo.getStartTime() && null != vo.getEndTime())
                            between("CreateTime", vo.getStartTime(), vo.getEndTime());
                    }}).stream().forEach(i1 -> changeDataCount(i1, this));
                    setChild(false);
                }});
        } else {
            SOrganization sOrganization = this.sOrganizationMapper.selectOne(new QueryWrapper<SOrganization>() {{
                eq("Status", 1);
                eq("IsDel", 0);
                if (StringUtils.isNotEmpty(vo.getCityId())) {
                    eq("ID", vo.getCityId());
                } else {
                    eq("ID", vo.getRegionalId());
                }
            }});
            if (null != sOrganization)
                result.add(new TiskInfoTreeVO() {{
                    setId(sOrganization.getId());
                    setTitel(sOrganization.getOrgName());
                    baseMapper.selectList(new QueryWrapper<BRiskinfo>() {{
                        if (StringUtils.isNotEmpty(vo.getCityId())) {
                            eq("CityId", sOrganization.getId());
                        } else {
                            eq("RegionalId", sOrganization.getId());
                        }
                        if (null != vo.getStartTime() && null != vo.getEndTime())
                            between("CreateTime", vo.getStartTime(), vo.getEndTime());
                    }}).stream().forEach(i1 -> changeDataCount(i1, this));
                    if (StringUtils.isNotEmpty(vo.getCityId())) {
                        setLevel(2);
                        setRegionalId(sOrganization.getPid());
                        setCityId(sOrganization.getId());
                        setProjectId(null);
                    } else {
                        setLevel(1);
                        setRegionalId(sOrganization.getId());
                        setCityId(null);
                        setProjectId(null);
                    }
                    setChild(isChild());
                }});
        }
        return result;
    }

    private Collection<? extends TiskInfoTreeVO> blocListTree(TiskTreeFindVO vo) {
        ArrayList<TiskInfoTreeVO> tiskInfoTreeVos = Lists.newArrayList();
        List<SOrganization> sOrganizations = this.sOrganizationMapper.selectList(new QueryWrapper<SOrganization>() {{
            eq("Status", 1);
            eq("IsDel", 0);
            if (null == vo.getLevel()) {
                eq("PID", topBloc);
            } else {
                eq("PID", vo.getId());
            }
        }});
        sOrganizations.stream().forEach(i -> {
            TiskInfoTreeVO tiskInfoTreeVO = new TiskInfoTreeVO();
            tiskInfoTreeVO.setId(i.getId());
            tiskInfoTreeVO.setTitel(i.getOrgName());
            if (null == vo.getLevel()) {
                tiskInfoTreeVO.setLevel(1);
            } else {
                tiskInfoTreeVO.setLevel(vo.getLevel() + 1);
            }
            this.baseMapper.selectList(new QueryWrapper<BRiskinfo>() {{
                notIn("RiskType", 6);
                if (null == vo.getLevel()) {
                    eq("RegionalId", i.getId());
                } else if (vo.getLevel() == 1) {
                    eq("CityId", i.getId());
                }
                if (null != vo.getStartTime() && null != vo.getEndTime())
                    between("CreateTime", vo.getStartTime(), vo.getEndTime());
            }}).stream().forEach(i1 -> changeDataCount(i1, tiskInfoTreeVO));
            tiskInfoTreeVO.setProjectId(null);
            if (null == vo.getLevel()) {
                tiskInfoTreeVO.setRegionalId(i.getId());
                tiskInfoTreeVO.setCityId(null);
            } else if (vo.getLevel() == 1) {
                tiskInfoTreeVO.setCityId(i.getId());
                tiskInfoTreeVO.setRegionalId(i.getPid());
            }
            tiskInfoTreeVO.setChild(tiskInfoTreeVO.isChild());
            tiskInfoTreeVos.add(tiskInfoTreeVO);
        });
        return tiskInfoTreeVos;
    }

    private List<TiskInfoTreeVO> projectListTree(TiskTreeFindVO vo) {
        ArrayList<TiskInfoTreeVO> tiskInfoTreeVos = Lists.newArrayList();
        this.bProjectMapper.selectList(new QueryWrapper<BProject>() {{
            eq("BUGUID", vo.getId());
            eq("Status", 1);
            eq("IsDel", 0);
            eq("Level", 1);
        }}).stream().forEach(i -> {
            tiskInfoTreeVos.add(new TiskInfoTreeVO() {{
                setId(i.getId());
                setLevel(vo.getLevel() + 1);
                setTitel(i.getName());
                baseMapper.selectList(new QueryWrapper<BRiskinfo>() {{
                    eq("ProjectId", i.getId());
                    notIn("RiskType", 6);
                    if (null != vo.getStartTime() && null != vo.getEndTime())
                        between("CreateTime", vo.getStartTime(), vo.getEndTime());
                }}).stream().forEach(i1 -> changeDataCount(i1, this));
                setChild(false);
            }});
        });
        return tiskInfoTreeVos;
    }

    private TiskInfoTreeVO listTreeStart(Date startTime, Date endTime) {
        TiskInfoTreeVO tiskInfoTreeVO = new TiskInfoTreeVO() {{
            setId(topBloc);
            setTitel("集团合计");
            setLevel(0);
        }};
        this.baseMapper.selectList(new QueryWrapper<BRiskinfo>() {{
            notIn("RiskType", 4);
            if (null != startTime && null != endTime) between("CreateTime", startTime, endTime);
        }}).stream().forEach(i -> changeDataCount(i, tiskInfoTreeVO));
        tiskInfoTreeVO.setRegionalId(null);
        tiskInfoTreeVO.setCityId(null);
        tiskInfoTreeVO.setProjectId(null);
        tiskInfoTreeVO.setChild(tiskInfoTreeVO.isChild());
        if (tiskInfoTreeVO.getJointName() > 0) tiskInfoTreeVO.setJointName(tiskInfoTreeVO.getJointName() / 2);
        return tiskInfoTreeVO;
    }

    private void changeDataCount(BRiskinfo bRiskinfo, TiskInfoTreeVO tiskInfoTreeVO) {
        tiskInfoTreeVO.setRegionalId(bRiskinfo.getRegionalId());
        tiskInfoTreeVO.setCityId(bRiskinfo.getCityId());
        tiskInfoTreeVO.setProjectId(bRiskinfo.getProjectId());
        switch (bRiskinfo.getRiskType()) {
            case 0:
                tiskInfoTreeVO.setProtectCustomer(tiskInfoTreeVO.getProtectCustomer() + 1);
                break;
            case 1:
                tiskInfoTreeVO.setFace(tiskInfoTreeVO.getFace() + 1);
                break;
            case 2:
                tiskInfoTreeVO.setSearchMobile(tiskInfoTreeVO.getSearchMobile() + 1);
                break;
            case 3:
                tiskInfoTreeVO.setEditName(tiskInfoTreeVO.getEditName() + 1);
                break;
            case 4:
                tiskInfoTreeVO.setJointName(tiskInfoTreeVO.getJointName() + 1);
                break;
            case 5:
                tiskInfoTreeVO.setShortDeal(tiskInfoTreeVO.getShortDeal() + 1);
                break;
//            case 6:
//                tiskInfoTreeVO.setJointName(tiskInfoTreeVO.getJointName() + 1);
//                break;
            case 7:
                tiskInfoTreeVO.setUnverified(tiskInfoTreeVO.getUnverified() + 1);
                break;
        }
    }

    private Map<String, Object> changeDate(Object obj) {
        if (!(obj instanceof TiskInfoVO)) return null;
        TiskInfoVO tiskInfoVO = (TiskInfoVO) obj;
        return new HashMap() {{
            put("regionalName", tiskInfoVO.getRegionalName());
            put("projectName", tiskInfoVO.getProjectName());
            put("customerName", tiskInfoVO.getCustomerName());
            put("customerMobile", tiskInfoVO.getCustomerMobile());
            put("customerStatusName", tiskInfoVO.getCustomerStatusName());
            put("saleUserName", tiskInfoVO.getSaleUserName());
            put("reportUserName", tiskInfoVO.getReportUserName());
            put("orgName", tiskInfoVO.getOrgName());
            put("adviserGroupName", tiskInfoVO.getAdviserGroupName());
            put("riskType", changeRiskType(tiskInfoVO.getRiskType()));
            put("createTime", tiskInfoVO.getCreateTime());
            put("yAdviserGroupName", tiskInfoVO.getyAdviserGroupName());
            put("yOrgName", tiskInfoVO.getyOrgName());
            put("ySaleUserName", tiskInfoVO.getySaleUserName());
            put("yReportUserName", tiskInfoVO.getyReportUserName());
        }};
    }

    private String changeRiskType(Integer riskType) {
        switch (riskType) {
            case 0:
                return "防截客";
            case 1:
                return "人脸识别";
            case 2:
                return "搜电未报备";
            case 3:
                return "修改客户名称";
            case 4:
                return "联名购房";
            case 5:
                return "短期成交";
            case 7:
                return "认筹未刷卡";
            default:
                return "未知";
        }
    }

    private List<Map<String, Object>> getExportBloc(String regionalId, String cityId, String projectId, Date startTime, Date endTime) {
        List<Map<String, Object>> result = Lists.newArrayList();
        this.baseMapper.findNot6Bloc(startTime, endTime, regionalId, cityId, projectId).stream()
                .collect(Collectors.groupingBy(BRiskinfo::getProjectId))
                .forEach((k, v) -> result.add(getExportMap(v)));
        return result;
    }

    private Map<String, Object> getExportMap(List<BRiskinfo> v) {
        return new HashMap() {{
            AtomicReference<String> regionalName = new AtomicReference<>("");
            AtomicReference<String> cityName = new AtomicReference<>("");
            AtomicReference<String> projectName = new AtomicReference<>("");
            AtomicReference<String> projectId = new AtomicReference<>("");
            AtomicReference<Integer> protectCustomer = new AtomicReference<>(0);
            AtomicReference<Integer> face = new AtomicReference<>(0);
            AtomicReference<Integer> searchMobile = new AtomicReference<>(0);
            AtomicReference<Integer> editName = new AtomicReference<>(0);
            AtomicReference<Integer> shortDeal = new AtomicReference<>(0);
            AtomicReference<Integer> nverified = new AtomicReference<>(0);
            AtomicReference<Integer> jointName = new AtomicReference<>(0);
            v.stream().forEach(i -> {
                regionalName.set(i.getRegionalName());
                cityName.set(i.getCityName());
                projectName.set(i.getProjectName());
                projectId.set(i.getProjectId());
                switch (i.getRiskType()) {
                    case 0:
                        protectCustomer.set(protectCustomer.get() + 1);
                        break;
                    case 1:
                        face.set(face.get() + 1);
                        break;
                    case 2:
                        searchMobile.set(searchMobile.get() + 1);
                        break;
                    case 3:
                        editName.set(editName.get() + 1);
                        break;
                    case 4:
                        jointName.set(jointName.get() + 1);
                        break;
                    case 5:
                        shortDeal.set(shortDeal.get() + 1);
                        break;
                    case 7:
                        nverified.set(nverified.get() + 1);
                        break;
                }
            });
            put("regionalName", regionalName.get());//区域
            put("cityName", cityName.get());//城市
            put("projectName", projectName.get());//项目
            put("projectId", projectId.get());
            put("protectCustomer", protectCustomer.get());//防截客
            put("face", face.get());//人脸识别
            put("searchMobile", searchMobile.get());//搜电未报备
            put("editName", editName.get());//修改客户姓名
            put("jointName", jointName.get());//联名购房
            put("shortDeal", shortDeal.get());//短期成交
            put("unverified", nverified.get());//认筹未刷证
        }};

    }

    private Map getFaceImg(BRiskinfo bRiskinfo) {
        if (StringUtils.isEmpty(bRiskinfo.getOpportunityId())) return Maps.newHashMap();
        BOpportunity bOpportunity = bOpportunityMapper.selectById(bRiskinfo.getOpportunityId());
        Map<String, Object> customerInfo = faceDetectCustomerService.findCustomerInfoOrXkcProjectId(bRiskinfo.getProjectId(), bCustomerMapper.selectById(bOpportunity.getCustomerID()).getCardID());
        return faceDetectCustomerService.firstFace(customerInfo);
    }

}
