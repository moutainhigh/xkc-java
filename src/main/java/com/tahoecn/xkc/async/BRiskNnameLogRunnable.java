package com.tahoecn.xkc.async;

import com.alibaba.fastjson.JSONObject;
import com.tahoecn.xkc.mapper.customer.BClueMapper;
import com.tahoecn.xkc.mapper.dict.SDictionaryMapper;
import com.tahoecn.xkc.model.customer.BClue;
import com.tahoecn.xkc.model.dict.SDictionary;
import com.tahoecn.xkc.model.risk.BRisknnamelog;
import com.tahoecn.xkc.service.risk.IBRisknnamelogService;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @description: 风控修改客户姓名记录
 * @author: 张晓东
 * @time: 2020/5/6 14:40
 */
public class BRiskNnameLogRunnable extends BaseRunnable {

    private JSONObject before;
    private Map after;
    private IBRisknnamelogService service;
    private BClueMapper bClueMapper;
    private SDictionaryMapper sDictionaryMapper;

    public BRiskNnameLogRunnable(JSONObject before, Map after, IBRisknnamelogService service,
                                 BClueMapper bCluemapper, SDictionaryMapper sDictionaryMapper) {
        this.before = before;
        this.after = after;
        this.service = service;
        this.bClueMapper = bCluemapper;
        this.sDictionaryMapper = sDictionaryMapper;
    }

    @Override
    void invoke() throws Exception {
        if (true) return;
        if (!this.before.getString("CustomerName").equals(this.after.get("Name").toString())) {
            BClue clue = this.bClueMapper.selectById(new BClue() {{
                setId(before.getString("ClueID"));
            }});
            SDictionary sDictionary = null;
            if (null != clue) {
                this.sDictionaryMapper.selectById(new SDictionary() {{
                    setId(clue.getAdviserGroupID());
                }});
            }
            this.service.insert(new BRisknnamelog() {{
                setId(UUID.randomUUID().toString());
                setProjectID(before.getString("ProjectID"));
                setCustomerMobile(before.getString("Mobile"));
                setCustomerOldName(before.getString("CustomerName"));
                setCustomerNewName(after.get("Name").toString());
                setClueId(before.getString("ClueID"));
                setOpportunityId(before.getString("OpportunityID"));
                setSaleUserID(before.getString("SaleUserID"));
                setSaleUserName(before.getString("SaleUserName"));
                if (null != sDictionary) {
                    setAdviserGroupID(StringUtils.isNotEmpty(sDictionary.getId()) ? sDictionary.getId() : "");//渠道类型Id
                    setAdviserGroupName(StringUtils.isNotEmpty(sDictionary.getDictName()) ? sDictionary.getDictName() : "");//渠道类型名称
                }
                if (null != clue) {
                    setReportUserID(StringUtils.isNotEmpty(clue.getReportUserID()) ? clue.getReportUserID() : "");//报备人Id
                    setReportUserName(StringUtils.isNotEmpty(clue.getReportUserName()) ? clue.getReportUserName() : "");//报备人姓名
                }
                setCreateTime(new Date(System.currentTimeMillis()));
            }});
        }
    }
}
