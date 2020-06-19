package com.tahoecn.xkc.async;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tahoecn.core.date.DateUtil;
import com.tahoecn.xkc.mapper.opportunity.BOpportunityMapper;
import com.tahoecn.xkc.mapper.risk.BRiskconfigMapper;
import com.tahoecn.xkc.mapper.risk.BRiskinfoMapper;
import com.tahoecn.xkc.mapper.risk.BRisknnamelogMapper;
import com.tahoecn.xkc.model.risk.BRiskconfig;
import com.tahoecn.xkc.model.risk.BRiskinfo;
import com.tahoecn.xkc.model.risk.BRisknnamelog;
import com.tahoecn.xkc.model.vo.CGWDetailModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @description: 提交认购, 监控修改用户姓名风险
 * @author: 张晓东
 * @time: 2020/5/9 19:17
 */
public class EditNameRunnable extends BaseRunnable {

    private CGWDetailModel cgwDetailModel;
    private BRiskconfigMapper bRiskconfigMapper;
    private BRisknnamelogMapper bRisknnamelogMapper;
    private BRiskinfoMapper bRiskinfoMapper;
    private BOpportunityMapper bOpportunityMapper;

    /**
     * @param bRiskconfigMapper   风控配置mappre
     * @param bRisknnamelogMapper 修改姓名记录mapper
     * @param bRiskinfoMapper     风控表
     * @description:
     * @author: 张晓东
     * @time: 2020/5/11 10:01
     */
    public EditNameRunnable(CGWDetailModel cgwDetailModel, BRiskconfigMapper bRiskconfigMapper, BRisknnamelogMapper bRisknnamelogMapper,
                            BRiskinfoMapper bRiskinfoMapper, BOpportunityMapper bOpportunityMapper) {
        this.cgwDetailModel = cgwDetailModel;
        this.bRiskconfigMapper = bRiskconfigMapper;
        this.bRisknnamelogMapper = bRisknnamelogMapper;
        this.bRiskinfoMapper = bRiskinfoMapper;
        this.bOpportunityMapper = bOpportunityMapper;
    }

    @Override
    @Transactional
    void invoke() throws Exception {
        //修改姓名日志表/mCustomerSubscribeDetail_Insert
        List<BRisknnamelog> bRisknnamelogs = this.bRisknnamelogMapper.selectList(new QueryWrapper<BRisknnamelog>() {{
            eq("ProjectID", cgwDetailModel.getProjectID());
            eq("CustomerMobile", cgwDetailModel.getUseMobile());
        }});

        // 风控项目配置
        BRiskconfig bRiskconfig = this.bRiskconfigMapper.selectOne(new QueryWrapper<BRiskconfig>() {{
            eq("Type", 1);
            eq("IsDel", 0);
            eq("Status", 1);
            eq("ProjectID", cgwDetailModel.getProjectID());
        }});

        if (null != bRisknnamelogs && null != bRiskconfig && bRiskconfig.getIsEditName() == 1
                && bRisknnamelogs.size() > 0 && bRiskconfig.getEditNameCount() > 0
                && bRisknnamelogs.size() >= bRiskconfig.getEditNameCount()) {
            boolean flag = false;
            long count = bRisknnamelogs.size();
            long surnameCount = bRisknnamelogs.stream().
                    filter(i -> !i.getCustomerOldName().substring(0, 1).equals(i.getCustomerNewName().substring(0, 1))).
                    count();
            if (bRiskconfig.getEditNameType() == 0 && surnameCount >= bRiskconfig.getEditNameCount()) {
                count = surnameCount;
                flag = true;
            } else if (surnameCount >= bRiskconfig.getEditNameCount()) {
                flag = true;
            }
            if (flag) {
                Map<String, Object> result = bOpportunityMapper.fkSearchInfo(cgwDetailModel.getOpportunityID());
                record(bRiskconfig, result, count);//记录风险数据
            }
        }
    }

    private void record(BRiskconfig bRiskconfig, Map<String, Object> i, Long count) {
        bRiskinfoMapper.insert(new BRiskinfo() {{
            setId(UUID.randomUUID().toString());//主键
            setRiskConfigId(bRiskconfig.getId()); //风控配置表id
            setRiskType(3);//风控类型:0 防截客1人脸识别2搜电未报备3修改客户名称4联名购房5短期成交
            setCreateTime(DateUtil.date());//创建时间, 当前时间
            setRegionalId((String) i.get("RegionalId"));
            setRegionalName((String) i.get("RegionalName"));
            setCityId((String) i.get("CityId"));
            setCityName((String) i.get("CityName"));
            setProjectId((String) i.get("ProjectID"));
            setProjectName((String) i.get("Name"));
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
            setReportTime((Date) i.get("ReportTime"));
            setTheFirstVisitDate((Date) i.get("TheFirstVisitDate"));
            setSaleUserID((String) i.get("SaleUserID"));
            setSaleUserName((String) i.get("SaleUserName"));
            setOrgId((String) i.get("OrgId"));
            setOpportunitySource((String) i.get("OpportunitySource"));
            setRiskDesc(
                    new StringBuilder("存在修改用户姓名风险, 修改类型:{")
                            .append(bRiskconfig.getEditNameType() == 0 ? "修改姓氏" : "修改名称")
                            .append("}, 配置修改次数: {")
                            .append(bRiskconfig.getEditNameCount())
                            .append("}, 实际修改次数为: {}")
                            .append(count)
                            .append("}")
                            .toString()
            );
        }});
    }

}
