package com.tahoecn.xkc.service.miniprogram.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.core.json.JSONResult;
import com.tahoecn.xkc.mapper.channel.BChanneluserMapper;
import com.tahoecn.xkc.mapper.sys.SysAccessRecordMapper;
import com.tahoecn.xkc.model.channel.BChanneluser;
import com.tahoecn.xkc.model.miniprogram.vo.allocation.ChannelVO;
import com.tahoecn.xkc.model.miniprogram.vo.allocation.CourtCaseVO;
import com.tahoecn.xkc.model.sys.SysAccessRecord;
import com.tahoecn.xkc.service.channel.IBChanneltaskService;
import com.tahoecn.xkc.service.miniprogram.IAllocationService;
import com.tahoecn.xkc.service.opportunity.IBOpportunityService;
import com.tahoecn.xkc.service.sys.ISysAccessRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 思为小程序客户分配服务实现类
 * </p>
 *
 * @author zhaoyan
 * @since 2020-04-08
 */
@Service
public class AllocationServiceImpl extends ServiceImpl<BChanneluserMapper, BChanneluser> implements IAllocationService {

    @Resource
    private IBOpportunityService iBOpportunityService;

    @Resource
    private ISysAccessRecordService sysAccessRecordService;

    @Resource
    private SysAccessRecordMapper sysAccessRecordMapper;

    @Resource
    private IBChanneltaskService iBChanneltaskService;

    @Override
    @Transactional
    public JSONResult courtCase(HttpServletRequest request, CourtCaseVO courtCase) throws Exception {
        // 记录接口访问信息
        SysAccessRecord sysAccessRecord = sysAccessRecordService.getSysAccessRecord(request);
        JSONResult jsonResult = new JSONResult();
        // 参数转换
        Map paramMap = new HashMap();
        paramMap.put("AdviserID", courtCase.getAdviserId());
        paramMap.put("Code", courtCase.getCode());
        paramMap.put("JobCode", courtCase.getJobCode());
        paramMap.put("JobID", courtCase.getJobId());
        paramMap.put("OrgID", courtCase.getOrgId());
        paramMap.put("ProjectID", courtCase.getProjectId());
        paramMap.put("UserID", courtCase.getUserId());
        paramMap.put("UserTrueName", courtCase.getUserTrueName());
        if (null == courtCase) {
            jsonResult.setCode(1);
            jsonResult.setMsg("courtCase不能为空");
            sysAccessRecord.setInterfaceState("1");
            sysAccessRecord.setReason("courtCase不能为空");
            sysAccessRecordMapper.insert(sysAccessRecord);
            return jsonResult;
        }
        String AdviserID = "";
        String OpportunityID = "";
        String LostID = "";
        String PublicID = "";
        List<String> OpportunityIDList = courtCase.getOpportunityIdList();
        List<String> AdviserIDList = courtCase.getAdviserIdList();
        List<String> LostIDList = courtCase.getLostIdList();
        List<String> PublicIDList = courtCase.getPublicIdList();
        if (AdviserIDList != null) {
            for (String item : AdviserIDList) {
                AdviserID += item + ",";
            }
            AdviserID = AdviserID.length() > 0 ? AdviserID.substring(0, AdviserID.length() - 1) : AdviserID;
        }
        if (OpportunityIDList != null) {
            for (String item : OpportunityIDList) {
                OpportunityID += item + ",";
            }
            OpportunityID = OpportunityID.length() > 0 ? OpportunityID.substring(0, OpportunityID.length() - 1) : OpportunityID;
        }
        if (LostIDList != null) {
            for (String item : LostIDList) {
                LostID += item + ",";
            }
            LostID = LostID.length() > 0 ? LostID.substring(0, LostID.length() - 1) : LostID;
        }
        if (PublicIDList != null) {
            for (String item : PublicIDList) {
                PublicID += item + ",";
            }
            PublicID = PublicID.length() > 0 ? PublicID.substring(0, PublicID.length() - 1) : PublicID;
        }
        paramMap.put("OpportunityIDList", OpportunityID);
        paramMap.put("AdviserIDList", AdviserID);
        paramMap.put("LostIDList", LostID);
        paramMap.put("PublicIDList", PublicID);
        iBOpportunityService.mCustomerAllotAdviser_Update(paramMap);
        // 公共池客户重新分配【协作人】置空
        String code = (String) paramMap.get("Code");
        if (code == "GG") {
            iBOpportunityService.mCustomerSalePartnerSetNull_Update(paramMap);
        }
        jsonResult.setCode(0);
        jsonResult.setMsg("成功");
        sysAccessRecord.setInterfaceState("1");
        sysAccessRecord.setReason("成功");
        sysAccessRecordMapper.insert(sysAccessRecord);
        return jsonResult;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONResult channel(HttpServletRequest request, ChannelVO channelVO) throws Exception {
        // 记录接口访问信息
        SysAccessRecord sysAccessRecord = sysAccessRecordService.getSysAccessRecord(request);
        JSONResult jsonResult = new JSONResult();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("UserID", channelVO.getUserId());
        map.put("OldUserID", channelVO.getOldUserId());
        map.put("ProjectID", channelVO.getProjectId());
        map.put("NewUserID", channelVO.getNewUserId());
        map.put("UserDisName", channelVO.getUserDisName());
        iBChanneltaskService.mChannelLeaderQuit_Insert(map);
        iBChanneltaskService.mChannelLeaderQuit_Update(map);
        iBChanneltaskService.mChannelLeaderQuit_Update2(map);
        iBChanneltaskService.mChannelLeaderQuit_Insert2(map);
        iBChanneltaskService.mChannelLeaderQuit_Update3(map);
        iBChanneltaskService.mChannelLeaderQuit_Update4(map);
        sysAccessRecord.setInterfaceState("0");
        sysAccessRecord.setReason("成功");
        sysAccessRecordMapper.insert(sysAccessRecord);
        jsonResult.setCode(0);
        jsonResult.setMsg("成功");
        return jsonResult;
    }
}
