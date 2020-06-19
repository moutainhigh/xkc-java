package com.tahoecn.xkc.schedule.risk;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tahoecn.core.date.DateField;
import com.tahoecn.core.date.DateTime;
import com.tahoecn.core.date.DateUtil;
import com.tahoecn.xkc.common.utils.RiskBatchLogUtils;
import com.tahoecn.xkc.mapper.customer.BClueMapper;
import com.tahoecn.xkc.mapper.opportunity.BOpportunityMapper;
import com.tahoecn.xkc.mapper.risk.BRiskbatchlogMapper;
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
 * @description:
 * @author: 张晓东
 * @time: 2020/6/5 15:29
 */
@Component
public class FkUpdateInfoTask {

    private Logger log = LoggerFactory.getLogger(FkUpdateInfoTask.class);

    @Resource
    private BClueMapper bClueMapper;

    @Resource
    private BOpportunityMapper bOpportunityMapper;

    @Resource
    private BRiskinfoMapper bRiskinfoMapper;

    @Resource
    private BRiskbatchlogMapper bRiskbatchlogMapper;

    @Transactional
    public void task() {
        DateTime startTime = DateUtil.date();
        try {
            List<BRiskinfo> bRiskinfos = bRiskinfoMapper.selectList(new QueryWrapper<BRiskinfo>());
            bRiskinfos.forEach(i -> {
                if (i.getRiskType() != 4) {
                    if (StringUtils.isNotEmpty(i.getOpportunityId())) {
                        Map<String, Object> fkSearchInfo = bOpportunityMapper.fkSearchInfo(i.getOpportunityId());
                        if (i.getCustomerStatus() != fkSearchInfo.get("CustomerStatus")) {
                            bRiskinfoMapper.updateById(new BRiskinfo(){{
                                setId(i.getId());
                                setCustomerStatus(((Short) fkSearchInfo.get("CustomerStatus")).intValue());
                                setCustomerStatusName((String)fkSearchInfo.get("CustomerStatusName"));
                            }});
                        }
                    } else {
                        Map<String, Object> fkSearchInfo = bClueMapper.fkSearchInfo(i.getClueId());
                        if (StringUtils.isNotEmpty((String)fkSearchInfo.get("ID"))) {
                            bRiskinfoMapper.updateById(new BRiskinfo(){{
                                setId(i.getId());
                                setOpportunityId((String) fkSearchInfo.get("ID"));
                                setCustomerStatus(((Short) fkSearchInfo.get("CustomerStatus")).intValue());
                                setCustomerStatusName((String)fkSearchInfo.get("CustomerStatusName"));
                            }});
                        }
                    }
                }
            });
            this.bRiskbatchlogMapper.insert(new BRiskbatchlog(){{
                setId(UUID.randomUUID().toString());
                setRiskType(8);
                setStartTime(startTime);
                setEndTime(DateUtil.date());
                setStatus(2);
            }});
        } catch (Exception e) {
            e.printStackTrace();
            this.bRiskbatchlogMapper.insert(new BRiskbatchlog(){{
                setId(UUID.randomUUID().toString());
                setRiskType(8);
                setStartTime(startTime);
                setEndTime(DateUtil.date());
                setStatus(3);
                setErrorMsg(e.getMessage());
            }});
        }

    }

}
