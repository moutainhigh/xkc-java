package com.tahoecn.xkc.service.miniprogram.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.core.json.JSONResult;
import com.tahoecn.xkc.common.enums.ActionType;
import com.tahoecn.xkc.common.enums.MessageHandleType;
import com.tahoecn.xkc.converter.CareerConsCustConverter;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.mapper.channel.BChanneluserMapper;
import com.tahoecn.xkc.mapper.customer.BCustomerpotentialMapper;
import com.tahoecn.xkc.mapper.customer.VCustomergwlistSelectMapper;
import com.tahoecn.xkc.model.channel.BChanneluser;
import com.tahoecn.xkc.model.miniprogram.vo.followrecord.ChannelRecoerdVO;
import com.tahoecn.xkc.model.miniprogram.vo.followrecord.CourtCaseRecordVO;
import com.tahoecn.xkc.model.vo.CustomerActionVo;
import com.tahoecn.xkc.service.customer.ICustomerHelp;
import com.tahoecn.xkc.service.customer.IVCustomergwlistSelectService;
import com.tahoecn.xkc.service.customer.impl.PotentialCustomerServiceImpl;
import com.tahoecn.xkc.service.customer.impl.VCustomergwlistSelectServiceImpl;
import com.tahoecn.xkc.service.miniprogram.IFollowUpRecordService;
import com.tahoecn.xkc.service.sys.ISystemMessageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 思为小程序跟进记录实现类
 * </p>
 *
 * @author zhaoyan
 * @since 2020-04-08
 */
@Service
public class FollowUpRecordServiceImpl extends ServiceImpl<BChanneluserMapper, BChanneluser> implements IFollowUpRecordService {

    @Resource
    private VCustomergwlistSelectMapper vCustomergwlistSelectMapper;

    @Resource
    private VCustomergwlistSelectServiceImpl vCustomergwlistSelectServiceImpl;

    @Resource
    private ICustomerHelp customerTemplate;

    @Resource
    private ISystemMessageService iSystemMessageService;

    @Resource
    private PotentialCustomerServiceImpl potentialCustomerServiceImpl;

    @Resource
    private IVCustomergwlistSelectService iVCustomergwlistSelectService;

    @Resource
    private BCustomerpotentialMapper bCustomerpotentialMapper;

    @Override
    public JSONResult courtCaseRecord(HttpServletRequest request, CourtCaseRecordVO courtCaseRecordVO) throws Exception {
        JSONResult jsonResult = new JSONResult();
        String opportunityID = courtCaseRecordVO.getOpportunityId() != null ? courtCaseRecordVO.getOpportunityId() : "";
        String userID = courtCaseRecordVO.getUserId();
        String customerID = courtCaseRecordVO.getCustomerId();
        String mode = courtCaseRecordVO.getMode();
        List<Map<String, Object>> list = vCustomergwlistSelectMapper.mCustomerSalePartnerIsCanOperate_Select(opportunityID, userID);
        if (list != null && list.size() > 0) {
            jsonResult.setCode(1);
            jsonResult.setMsg("开启异地销售，置业顾问申请客户丢失后，协作人不能对客户进行操作");
            return jsonResult;
        }
        // 客户跟进记录新增
        if (!StringUtils.isEmpty(mode)) {
            if (mode.equals("EEB32C04-5B7C-4676-A5DC-5F95E56370EB") || mode.equals("44775694-7C97-455C-B48E-154C6BFE2D94") || mode.equals("E30825AA-B894-4A5F-AF55-24CAC34C8F1F")) {
                vCustomergwlistSelectMapper.mCustomerFollowUpDetail_Insert(opportunityID, customerID, userID, mode);
            }
        }
        String follwUpType = CareerConsCustConverter.GetCustomerActionByFollowUpWay(mode);
        //获取子集信息
        List<String> opportunityIDlist = new ArrayList<String>();
        if (!StringUtils.isEmpty(follwUpType)) {
            String OpportunityID = courtCaseRecordVO.getOpportunityId();
            if (OpportunityID != null && !"".equals(OpportunityID)) {

                opportunityIDlist.add(OpportunityID);
                try {
                    Map<String, Object> opportunityInfo = vCustomergwlistSelectMapper.selectOpportunityByID(OpportunityID);
                    if (opportunityInfo.get("ParentID") != null) {
                        String ParentID = opportunityInfo.get("ParentID").toString();
                        List<String> paramParent = new ArrayList<String>();
                        paramParent.add(ParentID);
                        opportunityIDlist.add(ParentID);
                        List<Map<String, Object>> childs = vCustomergwlistSelectMapper.SelectOpportunityByParentID(paramParent);
                        if (childs != null && childs.size() > 0) {
                            for (Map<String, Object> child : childs) {
                                if (!OpportunityID.equals(child.get("OpportunityID").toString())) {
                                    opportunityIDlist.add(child.get("OpportunityID").toString());
                                }
                            }
                        }
                    } else {
                        List<Map<String, Object>> childs = vCustomergwlistSelectMapper.SelectOpportunityByParentID(opportunityIDlist);
                        if (childs != null && childs.size() > 0) {
                            for (Map<String, Object> child : childs) {
                                opportunityIDlist.add(child.get("OpportunityID").toString());
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 包含了父id
                for (String oppoID : opportunityIDlist) {
                    CustomerActionVo customerActionVo = new CustomerActionVo();
                    customerActionVo.setFollwUpType(follwUpType);
                    customerActionVo.setFollwUpTypeID(ActionType.valueOf(follwUpType).getValue());
                    customerActionVo.setSalesType(1);
                    customerActionVo.setNewSaleUserName("");
                    customerActionVo.setOldSaleUserName("");
                    customerActionVo.setFollwUpUserID(courtCaseRecordVO.getUserId());
                    customerActionVo.setFollwUpWay(courtCaseRecordVO.getMode());
                    customerActionVo.setFollowUpContent(courtCaseRecordVO.getContent());
                    customerActionVo.setIntentionLevel(courtCaseRecordVO.getLevel());
                    customerActionVo.setOrgID(courtCaseRecordVO.getOrgId());
                    customerActionVo.setFollwUpUserRole(courtCaseRecordVO.getJobId());
                    customerActionVo.setOpportunityID(oppoID);
                    customerActionVo.setClueID("");
                    customerActionVo.setNextFollowUpDate(courtCaseRecordVO.getNextTime());
                    Result re = vCustomergwlistSelectServiceImpl.CustomerFollowUp_Insert(customerActionVo);
                    jsonResult.setCode(re.getErrcode());
                    jsonResult.setMsg(re.getErrmsg());
                }
            }
            if (follwUpType.equals("售场接待")) {
                // 客户到访
                Map<String, Object> pmp = new HashMap<String, Object>();
                pmp.put("NextTime", courtCaseRecordVO.getNextTime());
                pmp.put("Mode", courtCaseRecordVO.getMode());
                pmp.put("ProjectID", courtCaseRecordVO.getProjectId());
                pmp.put("UserTrueName", courtCaseRecordVO.getUserTrueName());
                pmp.put("CustomerID", courtCaseRecordVO.getCustomerId());
                pmp.put("OrgID", courtCaseRecordVO.getOrgId());
                pmp.put("OpportunityID", courtCaseRecordVO.getOpportunityId());
                pmp.put("UserID", courtCaseRecordVO.getUserId());
                pmp.put("JobCode", courtCaseRecordVO.getJobCode());
                pmp.put("Content", courtCaseRecordVO.getContent());
                pmp.put("Level", courtCaseRecordVO.getLevel());
                pmp.put("JobID", courtCaseRecordVO.getJobId());
                customerTemplate.sendKHDFMsg(pmp);
            }
        }
        // 包含了父id
        for (String oppoID : opportunityIDlist) {
            // 客户机会跟进记录更新
            vCustomergwlistSelectServiceImpl.CustomerOpportunityFollowUpDetail_Update(oppoID, userID);
            // 处理跟进类待办为已办
            String[] BizIDs = new String[]{oppoID};
            iSystemMessageService.DetailByHandle_Update(BizIDs, "Opportunity", MessageHandleType.新增跟进.getValue());
        }
        jsonResult.setCode(0);
        jsonResult.setMsg("成功");
        return jsonResult;
    }

    @Override
    public JSONResult channelRecord(HttpServletRequest request, ChannelRecoerdVO channelRecoerdVO) throws Exception {
        JSONResult jsonResult = new JSONResult();
        channelRecoerdVO.setFollwUpUserId(channelRecoerdVO.getUserId());
        String ChannelTaskID = channelRecoerdVO.getChannelTaskId();
        if (!StringUtils.isEmpty(ChannelTaskID)) {
            // 渠道任务ID
            Map<String, Object> channelTaskParam = new HashMap<String, Object>();
            channelTaskParam.put("ChannelTaskID", ChannelTaskID);
            Result channelTaskEntiry = potentialCustomerServiceImpl.mChannelTaskDetail_Select(channelTaskParam);
            if (channelTaskEntiry.getErrcode() == 0) {
                JSONObject channelTaskList = (JSONObject) channelTaskEntiry.getData();
                if (channelTaskList != null && channelTaskList.size() > 0) {
                    String Creator = channelTaskList.getString("Creator");
                    if (Creator.length() > 0) {
                        channelRecoerdVO.setFollwUpUserId(Creator);
                    }
                }
            }
        }
        // 增加跟进记录
        String FollwUpType = CareerConsCustConverter.GetCustomerActionByFollowUpWay(channelRecoerdVO.getMode());
        if (!StringUtils.isEmpty(FollwUpType)) {
            JSONObject obj = new JSONObject();
            obj.put("FollwUpType", FollwUpType);
            obj.put("FollwUpTypeID", ActionType.valueOf(FollwUpType).getValue());
            obj.put("SalesType", 3);
            obj.put("NewSaleUserName", "");
            obj.put("OldSaleUserName", "");
            obj.put("FollwUpUserID", channelRecoerdVO.getFollwUpUserId());
            obj.put("FollwUpWay", channelRecoerdVO.getMode());
            obj.put("FollowUpContent", channelRecoerdVO.getContent());
            obj.put("IntentionLevel", channelRecoerdVO.getLevel());
            obj.put("OrgID", channelRecoerdVO.getOrgId());
            obj.put("FollwUpUserRole", channelRecoerdVO.getJobId());
            obj.put("OpportunityID", "");
            obj.put("ClueID", channelRecoerdVO.getClueId());
            obj.put("NextFollowUpDate", channelRecoerdVO.getNextTime());
            obj.put("Creator", channelRecoerdVO.getUserId());
            obj.put("Editor", channelRecoerdVO.getUserId());
            CustomerActionVo customerActionVo = JSONObject.parseObject(obj.toJSONString(), CustomerActionVo.class);
            iVCustomergwlistSelectService.CustomerFollowUp_Insert(customerActionVo);
        }
        // 潜在客户线索跟进记录更新
        CustomerPotentialClueFollowUpDetail_Update(channelRecoerdVO);
        // 处理跟进类待办为已办
        iSystemMessageService.DetailByHandle_Update(new String[]{channelRecoerdVO.getClueId()}, "Clue", MessageHandleType.新增跟进.getValue());
        jsonResult.setCode(0);
        jsonResult.setMsg("成功");
        return jsonResult;
    }

    private JSONResult CustomerPotentialClueFollowUpDetail_Update(ChannelRecoerdVO channelRecoerdVO) {
        JSONResult jsonResult = new JSONResult();
        try {
            Map<String, Object> pmap = new HashMap<String, Object>();
            pmap.put("NextTime", channelRecoerdVO.getNextTime());
            pmap.put("Mode", channelRecoerdVO.getMode());
            pmap.put("ProjectID", channelRecoerdVO.getProjectId());
            pmap.put("ClueID", channelRecoerdVO.getClueId());
            pmap.put("UserTrueName", channelRecoerdVO.getUserTrueName());
            pmap.put("CustomerID", channelRecoerdVO.getCustomerId());
            pmap.put("OrgID", channelRecoerdVO.getOrgId());
            pmap.put("UserID", channelRecoerdVO.getUserId());
            pmap.put("JobCode", channelRecoerdVO.getJobCode());
            pmap.put("Content", channelRecoerdVO.getContent());
            pmap.put("Level", channelRecoerdVO.getLevel());
            pmap.put("JobID", channelRecoerdVO.getJobId());
            pmap.put("ChannelTaskID", channelRecoerdVO.getChannelTaskId());
            pmap.put("FollwUpUserID", channelRecoerdVO.getFollwUpUserId());
            bCustomerpotentialMapper.CustomerPotentialClueFollowUpDetail_Update(pmap);
            jsonResult.setCode(0);
            jsonResult.setMsg("成功！");
        } catch (Exception e) {
            jsonResult.setCode(1);
            jsonResult.setMsg("服务器出现异常！");
            e.printStackTrace();
        }
        return jsonResult;
    }
}
