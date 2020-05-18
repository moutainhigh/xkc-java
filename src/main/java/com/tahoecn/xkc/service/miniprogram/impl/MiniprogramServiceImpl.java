package com.tahoecn.xkc.service.miniprogram.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tahoecn.core.json.JSONResult;
import com.tahoecn.xkc.async.BOpportunityOtherRunnable;
import com.tahoecn.xkc.async.ExecutorsUtils;
import com.tahoecn.xkc.common.enums.*;
import com.tahoecn.xkc.common.utils.NetUtil;
import com.tahoecn.xkc.common.utils.PhoneUtil;
import com.tahoecn.xkc.common.utils.ResultUtil;
import com.tahoecn.xkc.converter.CareerConsCustConverter;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.mapper.customer.BCustomerpotentialMapper;
import com.tahoecn.xkc.mapper.customer.VCustomergwlistSelectMapper;
import com.tahoecn.xkc.mapper.sys.SysAccessRecordMapper;
import com.tahoecn.xkc.model.channel.BChanneluser;
import com.tahoecn.xkc.model.miniprogram.BOpportunityOther;
import com.tahoecn.xkc.model.miniprogram.vo.customerreport.MBrokerReportVO;
import com.tahoecn.xkc.model.miniprogram.vo.customerreport.MCustomerPotentialVO;
import com.tahoecn.xkc.model.miniprogram.vo.customerreport.MDynatownCustomerVO;
import com.tahoecn.xkc.model.sys.SFormsession;
import com.tahoecn.xkc.model.sys.SysAccessRecord;
import com.tahoecn.xkc.model.vo.CGWDetailModel;
import com.tahoecn.xkc.model.vo.ChannelRegisterModel;
import com.tahoecn.xkc.model.vo.CustomerActionVo;
import com.tahoecn.xkc.service.channel.IBChannelService;
import com.tahoecn.xkc.service.channel.IBChanneluserService;
import com.tahoecn.xkc.service.customer.IBClueService;
import com.tahoecn.xkc.service.customer.ICustomerHelp;
import com.tahoecn.xkc.service.customer.ICustomerPotentialTemplate;
import com.tahoecn.xkc.service.customer.IVCustomergwlistSelectService;
import com.tahoecn.xkc.service.customer.impl.PotentialCustomerServiceImpl;
import com.tahoecn.xkc.service.customer.impl.VCustomergwlistSelectServiceImpl;
import com.tahoecn.xkc.service.miniprogram.IBOpportunityOtherService;
import com.tahoecn.xkc.service.miniprogram.IMiniprogramService;
import com.tahoecn.xkc.service.project.IBProjectService;
import com.tahoecn.xkc.service.sys.ISFormsessionService;
import com.tahoecn.xkc.service.sys.ISysAccessRecordService;
import com.tahoecn.xkc.service.sys.ISystemMessageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * <p>
 * 思为小程序客户报备服务实现类
 * </p>
 *
 * @author zhaoyan
 * @since 2020-04-07
 */
@Service
public class MiniprogramServiceImpl implements IMiniprogramService {

    @Resource
    private ISysAccessRecordService sysAccessRecordService;

    @Resource
    private SysAccessRecordMapper sysAccessRecordMapper;

    @Resource
    private ISFormsessionService iSFormsessionService;

    @Resource
    private ISystemMessageService iSystemMessageService;

    @Autowired
    private IBProjectService projectService;

    @Resource
    private IBChanneluserService channeluserService;

    @Resource
    private ICustomerPotentialTemplate iCustomerPotentialTemplate;

    @Resource
    private IBChannelService iBChannelService;

    @Resource
    private IBClueService clueService;

    @Resource
    private BCustomerpotentialMapper bCustomerpotentialMapper;

    @Resource
    private ICustomerHelp customerTemplate;

    @Resource
    private VCustomergwlistSelectMapper vCustomergwlistSelectMapper;

    @Resource
    private VCustomergwlistSelectServiceImpl vCustomergwlistSelectServiceImpl;

    @Resource
    private PotentialCustomerServiceImpl potentialCustomerServiceImpl;

    @Resource
    private IVCustomergwlistSelectService iVCustomergwlistSelectService;

    @Resource
    private IBOpportunityOtherService ibOpportunityOtherService;

    @Override
    public JSONResult getFormSessionId(HttpServletRequest request, Map<String, Object> map) throws Exception {
        // 记录接口访问信息
        SysAccessRecord sysAccessRecord = sysAccessRecordService.getSysAccessRecord(request);
        if (null == map) {
            sysAccessRecord.setInterfaceState("1");
            sysAccessRecord.setReason("参数不能为空");
            sysAccessRecordMapper.insert(sysAccessRecord);
            return new JSONResult(1, "参数不能为空");
        }
        String param = JSONArray.toJSONString(map);
        SFormsession sess = new SFormsession();
        sess.setId(UUID.randomUUID().toString().toUpperCase());
        sess.setIp(NetUtil.getRemoteAddr2(request));
        sess.setData(param);
        sess.setCreateTime(new Date());
        sess.setStatus(1);
        iSFormsessionService.save(sess);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("formSessionId", sess.getId());
        sysAccessRecord.setInterfaceState("0");
        sysAccessRecord.setReason("formSessionId获取成功");
        sysAccessRecordMapper.insert(sysAccessRecord);
        return ResultUtil.setJsonResult(new JSONResult<>(), result, null, TipsEnum.Success.getCode(), TipsEnum.Success.getMsg());
    }

    @Override
    @Transactional
    public JSONResult dynatownReport(HttpServletRequest request, MDynatownCustomerVO mDynatownCustomerVO) throws Exception {
        JSONResult jsonResult = new JSONResult();
        String returnOpportunityId = "";
        // 记录接口访问信息
        SysAccessRecord sysAccessRecord = sysAccessRecordService.getSysAccessRecord(request);
        if (!StringUtils.isEmpty(mDynatownCustomerVO.getFormSessionId())) {
            String formSessionID = mDynatownCustomerVO.getFormSessionId();
            Long RowCount = vCustomergwlistSelectMapper.mSystemFormSessionStatus_Select_count(formSessionID);
            if (RowCount.intValue() == 0) {
                vCustomergwlistSelectMapper.mSystemFormSessionStatus_Select_update(formSessionID);
            }
            if (RowCount.intValue() > 0) {
                sysAccessRecord.setInterfaceState("1");
                sysAccessRecord.setReason("不能重复请求！");
                sysAccessRecordMapper.insert(sysAccessRecord);
                return new JSONResult(1, "不能重复请求！");
            }
        }
        JSONObject CustomerObj = new JSONObject();
        // CGWDetailModel model = JSONObject.parseObject(paramAry.toJSONString(), CGWDetailModel.class);
        if (null != mDynatownCustomerVO && mDynatownCustomerVO.getItemList() != null && mDynatownCustomerVO.getItemList().size() > 0) {
            //初始化参数
            CGWDetailModel model = new CGWDetailModel();
            model.setUserID(mDynatownCustomerVO.getUserId());
            model.setUserTrueName(mDynatownCustomerVO.getUserTrueName());
            model.setProjectID(mDynatownCustomerVO.getProjectId());
            model.setClueID(mDynatownCustomerVO.getClueId());
            model.setOrgID(mDynatownCustomerVO.getOrgId());
            model.setJobID(mDynatownCustomerVO.getJobId());
            model.setOpportunityID(mDynatownCustomerVO.getOpportunityId());
            model.setCustomerID(mDynatownCustomerVO.getCustomerId());
            model.setItemList(getItemList(mDynatownCustomerVO.getItemList()));
            model.setJobCode(mDynatownCustomerVO.getJobCode());
            model.setUseMobile(mDynatownCustomerVO.getUseMobile());
            JSONObject parameter = customerTemplate.GetParameters(model);
            if (!parameter.isEmpty()) {
                String sqlKey = "";
                String Mobile = parameter.getString("Mobile");
                if (!StringUtils.isEmpty(Mobile)) {
                    // 验证手机号码
                    JSONObject j_re = customerTemplate.CustomerOpportunityExist(model.getProjectID(), Mobile);
                    if (j_re.getBoolean("status")) {
                        // 存在老机会_老客户
                        CustomerObj = j_re.getJSONObject("CustomerObj");
                        jsonResult.setCode(1);
                        jsonResult.setMsg("此手机号客户已存在,销售顾问:" + CustomerObj.getString("SaleUserName"));
                        // 发送报备失败消息
                        customerTemplate.sendBBSBMsg(CustomerObj.getString("OpportunityID"), model.getUserTrueName(), model.getUserID());
                        return jsonResult;
                    } else {
                        // 新机会
                        JSONObject j_re_1 = customerTemplate.CustomerExist(Mobile);
                        String status = "status";
                        if (!j_re_1.getBoolean(status)) {
                            // 新机会_新客户
                            parameter.put("CustomerID", UUID.randomUUID().toString());
                            sqlKey = "mNewCustomerGWDetail_Insert";
                        } else {
                            // 新机会_老客户
                            CustomerObj = j_re_1.getJSONObject("CustomerObj");
                            parameter.put("CustomerID", CustomerObj.getString("CustomerID"));
                            sqlKey = "mOldCustomerGWDetail_Insert";
                        }
                        jsonResult.setCode(0);
                    }
                    if (jsonResult.getCode() == 0) {
                        parameter.put("TrackType", "BC2F967F-8FFE-1F52-49F6-CBCDFE8D044A");
                        parameter.put("OpportunityID", UUID.randomUUID().toString());
                        returnOpportunityId = parameter.getString("OpportunityID");
                        parameter.put("SaleUserID", model.getUserID());
                        parameter.put("Status", 1);
                        parameter.put("IsCustomerFirstEdit", 0);
                        parameter.put("CustomerFirstEditTime", "");
                        // 客户来源
                        String ClueID = "";
                        Map<String, String> re_map = customerTemplate.GetOpportunitySource(parameter);
                        ClueID = String.valueOf(re_map.get("clueID"));
                        parameter.put("ClueID", ClueID);
                        parameter.put("OpportunitySource", String.valueOf(re_map.get("opportunitySourceID")));
                        // 客户分配
                        String projectID = parameter.getString("ProjectID");
                        if (!"".equals(ClueID)) {
                            Map<String, Object> re_map_step1 = vCustomergwlistSelectMapper.RemindRuleAllotDetail_Select_step1(ClueID);
                            if (re_map_step1 != null && re_map_step1.size() > 0) {
                                String protectSource = re_map_step1.get("ProtectSource") != null ? re_map_step1.get("ProtectSource").toString() : "";
                                if (!StringUtils.isEmpty(protectSource)) {
                                    Map<String, Object> re_map_step2 = vCustomergwlistSelectMapper.RemindRuleAllotDetail_Select_step2(projectID, protectSource);
                                    if (re_map_step2 != null && re_map_step2.size() > 0) {
                                        int AllotRemind = 0;
                                        String allotRemind = "AllotRemind";
                                        if (re_map_step2.get(allotRemind) != null) {
                                            Number number = (Number) re_map_step2.get(allotRemind);
                                            AllotRemind = number.intValue();
                                        }
                                        String ReportUserID = "";
                                        ClueID = "";
                                        if (AllotRemind > 0) {
                                            if (re_map_step1.get("ReportUserID") != null) {
                                                ReportUserID = String.valueOf(re_map_step1.get("ReportUserID"));
                                            }
                                            if (re_map_step1.get("ClueID") != null) {
                                                ClueID = String.valueOf(re_map_step1.get("ClueID"));
                                            }
                                        }
                                        if (!"".equals(ClueID) && !"".equals(ReportUserID)) {
                                            String UserID = parameter.getString("UserID");
                                            String ProjectID = parameter.getString("ProjectID");
                                            String Content = "客户" + parameter.getString("LastName") + parameter.getString("FirstName") + "、" + parameter.getString("Mobile") + "(客户分配提醒)";
                                            iSystemMessageService.Detail_Insert(UserID, ProjectID, ClueID, "Clue", "客户分配提醒", Content, ReportUserID, MessageType.系统通知.getTypeID(), true);
                                        }
                                    }
                                }
                            }
                        }
                        String str = "E30825AA-B894-4A5F-AF55-24CAC34C8F1F";
                        String key = "FollwUpWay";
                        if (str.equals(parameter.getString(key))) {
                            // 跟进方式为到访时 机会状态设置为到访(2),其他情况为问询(1)
                            parameter.put("Status", 2);
                            parameter.put("IsCustomerFirstEdit", 1);
                            parameter.put("CustomerFirstEditTime", DateUtil.format(new Date(), "yyyy/MM/dd HH:mm:ss"));
                        }
                        parameter.put("Name", parameter.getString("LastName") + parameter.getString("FirstName"));
                        Map<String, Object> parameterMap = JSONObject.parseObject(parameter.toJSONString(), Map.class);
                        String str2 = "mNewCustomerGWDetail_Insert";
                        String str3 = "mOldCustomerGWDetail_Insert";
                        if (sqlKey.equals(str2)) {
                            // 添加新客户
                            vCustomergwlistSelectMapper.mNewCustomerGWDetail_Insert_step1(parameterMap);
                            vCustomergwlistSelectMapper.mNewCustomerGWDetail_Insert_step2(parameterMap);
                            vCustomergwlistSelectMapper.mNewCustomerGWDetail_Insert_step3(parameterMap);
                            vCustomergwlistSelectMapper.mNewCustomerGWDetail_Insert_step4(parameterMap);
                            vCustomergwlistSelectMapper.mNewCustomerGWDetail_Insert_step5(parameterMap);

                        } else if (sqlKey.equals(str3)) {
                            // 顾问新增已有客户机会信息
                            vCustomergwlistSelectMapper.mOldCustomerGWDetail_Insert_step1(parameterMap);
                            vCustomergwlistSelectMapper.mOldCustomerGWDetail_Insert_step2(parameterMap);

                            List<Map<String, Object>> step3_map = vCustomergwlistSelectMapper.mOldCustomerGWDetail_Insert_step3_vaild(parameterMap);
                            if (step3_map != null && step3_map.size() > 0) {
                                vCustomergwlistSelectMapper.mOldCustomerGWDetail_Insert_step3_update(parameterMap);
                            } else {
                                vCustomergwlistSelectMapper.mOldCustomerGWDetail_Insert_step3_insert(parameterMap);
                            }
                            vCustomergwlistSelectMapper.mOldCustomerGWDetail_Insert_step4(parameterMap);
                            vCustomergwlistSelectMapper.mOldCustomerGWDetail_Insert_step5(parameterMap);
                            vCustomergwlistSelectMapper.mOldCustomerGWDetail_Insert_step6(parameterMap);
                            vCustomergwlistSelectMapper.mOldCustomerGWDetail_Insert_step7(parameterMap);
                        }

                        parameterMap.put("CustomerRank", "41FA0234-F8AE-434F-8BCD-6E9BE1D059DA");
                        parameterMap.put("UpDownStatus", 1);
                        vCustomergwlistSelectMapper.P_OpportunityCustomerRank(parameterMap);
                        String FollwUpWay = parameter.getString("FollwUpWay");
                        if (FollwUpWay.equals("EEB32C04-5B7C-4676-A5DC-5F95E56370EB") || FollwUpWay.equals("44775694-7C97-455C-B48E-154C6BFE2D94")) {
                            parameterMap.put("CustomerRank", "FC420696-6F6C-40B1-BE17-96FCEC75B0F2");
                            vCustomergwlistSelectMapper.P_OpportunityCustomerRank(parameterMap);
                        } else if (FollwUpWay.equals("E30825AA-B894-4A5F-AF55-24CAC34C8F1F")) {
                            parameterMap.put("CustomerRank", "ED0AD9E6-AF72-424C-9EE0-9884FF31FA42");
                            vCustomergwlistSelectMapper.P_OpportunityCustomerRank(parameterMap);
                        }
                        String IsLittleBooking = parameter.getString("IsLittleBooking");
                        if (IsLittleBooking.equals("1DCCBDB8-AD44-44D4-B23A-571A38337D5C")) {
                            parameterMap.put("CustomerRank", "D47878CE-D560-44C0-A6F8-4C92CCAC9EE0");
                            vCustomergwlistSelectMapper.P_OpportunityCustomerRank(parameterMap);
                        }
                        parameterMap.put("ClueID", "");
                        vCustomergwlistSelectMapper.P_SyncClueOpportunity_Update(parameterMap);

                        // 添加线索
                        customerTemplate.ClueUpdate(parameter);
                        // 增加意向项目
                        customerTemplate.IntentProjectAdd(parameter);
                        if (StringUtils.isEmpty(ClueID)) {
                            JSONObject obj1 = new JSONObject();
                            obj1.put("FollwUpType", "顾问报备");
                            obj1.put("FollwUpTypeID", ActionType.顾问报备.getValue());
                            obj1.put("SalesType", 1);
                            obj1.put("NewSaleUserName", "");
                            obj1.put("OldSaleUserName", "");
                            obj1.put("FollwUpUserID", parameter.getString("UserID"));
                            obj1.put("FollwUpWay", "");
                            obj1.put("FollowUpContent", "");
                            obj1.put("IntentionLevel", "");
                            obj1.put("OrgID", parameter.getString("OrgID"));
                            obj1.put("FollwUpUserRole", parameter.getString("JobID"));
                            obj1.put("OpportunityID", parameter.getString("OpportunityID"));
                            obj1.put("ClueID", "");
                            obj1.put("NextFollowUpDate", "");
                            CustomerActionVo customerActionVo = JSONObject.parseObject(obj1.toJSONString(), CustomerActionVo.class);
                            vCustomergwlistSelectServiceImpl.CustomerFollowUp_Insert(customerActionVo);
                        }
                        // 增加跟进记录
                        String FollwUpType = CareerConsCustConverter.GetCustomerActionByFollowUpWay(parameter.getString("FollwUpWay"));
                        if (!StringUtils.isEmpty(FollwUpType)) {
                            JSONObject obj = new JSONObject();
                            obj.put("FollwUpType", FollwUpType);
                            obj.put("FollwUpTypeID", ActionType.valueOf(FollwUpType).getValue());
                            obj.put("SalesType", 1);
                            obj.put("NewSaleUserName", "");
                            obj.put("OldSaleUserName", "");
                            obj.put("FollwUpUserID", parameter.getString("UserID"));
                            obj.put("FollwUpWay", parameter.getString("FollwUpWay"));
                            obj.put("FollowUpContent", parameter.getString("FollowUpContent"));
                            obj.put("IntentionLevel", parameter.getString("CustomerLevel"));
                            obj.put("OrgID", parameter.getString("OrgID"));
                            obj.put("FollwUpUserRole", parameter.getString("JobID"));
                            obj.put("OpportunityID", parameter.getString("OpportunityID"));
                            obj.put("ClueID", "");
                            obj.put("NextFollowUpDate", parameter.getString("NextFollowUpDate"));
                            CustomerActionVo customerActionVo = JSONObject.parseObject(obj.toJSONString(), CustomerActionVo.class);
                            vCustomergwlistSelectServiceImpl.CustomerFollowUp_Insert(customerActionVo);
                            // 客户到访
                            String follwUpType = "售场接待";
                            if (follwUpType.equals(FollwUpType)) {
                                // 售场接待
                                Map<String, Object> pmp = JSONObject.parseObject(parameter.toJSONString(), Map.class);
                                customerTemplate.sendKHDFMsg(pmp);
                            }

                        }
                        String tIsLittleBooking = parameter.getString("IsLittleBooking");
                        if (tIsLittleBooking.equals("1DCCBDB8-AD44-44D4-B23A-571A38337D5C")) {
                            // 小筹
                            JSONObject obj2 = new JSONObject();
                            obj2.put("FollwUpType", "小筹");
                            obj2.put("FollwUpTypeID", ActionType.小筹.getValue());
                            obj2.put("SalesType", 1);
                            obj2.put("NewSaleUserName", "");
                            obj2.put("OldSaleUserName", "");
                            obj2.put("FollwUpUserID", "99");
                            obj2.put("FollwUpWay", "");
                            obj2.put("FollowUpContent", "小筹");
                            obj2.put("IntentionLevel", "");
                            obj2.put("OrgID", "");
                            obj2.put("FollwUpUserRole", "");
                            obj2.put("OpportunityID", parameter.getString("OpportunityID"));
                            obj2.put("ClueID", "");
                            obj2.put("NextFollowUpDate", "");
                            CustomerActionVo customerActionVo = JSONObject.parseObject(obj2.toJSONString(), CustomerActionVo.class);
                            vCustomergwlistSelectServiceImpl.CustomerFollowUp_Insert(customerActionVo);
                        }
                        String opportunityID = parameter.getString("OpportunityID");
                        String userID = parameter.getString("UserID");
                        // 客户机会跟进记录更新
                        vCustomergwlistSelectServiceImpl.CustomerOpportunityFollowUpDetail_Update(opportunityID, userID);
                        // 处理跟进类待办为已办
                        String[] BizIDs = new String[]{opportunityID};
                        iSystemMessageService.DetailByHandle_Update(BizIDs, "Opportunity", MessageHandleType.新增跟进.getValue());
                        // 同步明源客户数据
                        customerTemplate.SyncCustomer(opportunityID, 0);
                        final String roId = returnOpportunityId;
                        ExecutorsUtils.fkExecute(new BOpportunityOtherRunnable(ibOpportunityOtherService, new BOpportunityOther() {{
                            setId(UUID.randomUUID().toString());
                            setOpportunityID(roId);
                        }}));
                    }
                } else {
                    jsonResult.setCode(1);
                    jsonResult.setMsg("手机号码必填！");
                }
            } else {
                jsonResult.setCode(1);
                jsonResult.setMsg("误参数格式错！");
            }
        } else {
            jsonResult.setCode(1);
            jsonResult.setMsg("参数不完整！");
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("opportunityId", returnOpportunityId);
        jsonResult.setMsg("成功");
        jsonResult.setData(result);
        return jsonResult;
    }

    @Override
    @Transactional
    public JSONResult customerPotentialReport(HttpServletRequest request, MCustomerPotentialVO mCustomerPotentialVO) throws Exception {
        JSONResult jsonResult = new JSONResult();
        jsonResult.setMsg("成功");
        if (!StringUtils.isEmpty(mCustomerPotentialVO.getFormSessionId())) {
            String formSessionID = mCustomerPotentialVO.getFormSessionId();
            Long RowCount = vCustomergwlistSelectMapper.mSystemFormSessionStatus_Select_count(formSessionID);
            if (RowCount > 0) {
                jsonResult.setCode(1);
                jsonResult.setMsg("不能重复请求！");
                return jsonResult;
            }
        }
        if (mCustomerPotentialVO != null && mCustomerPotentialVO.getItemList() != null && mCustomerPotentialVO.getItemList().size() > 0) {
            //初始化参数
            CGWDetailModel model = new CGWDetailModel();
            model.setUserID(mCustomerPotentialVO.getUserId());
            model.setUserTrueName(mCustomerPotentialVO.getUserTrueName());
            model.setProjectID(mCustomerPotentialVO.getProjectId());
            model.setClueID(mCustomerPotentialVO.getClueId());
            model.setOrgID(mCustomerPotentialVO.getOrgId());
            model.setJobID(mCustomerPotentialVO.getJobId());
            model.setOpportunityID(mCustomerPotentialVO.getOpportunityId());
            model.setChannelTypeID(mCustomerPotentialVO.getChannelTypeId());
            model.setCustomerID(mCustomerPotentialVO.getCustomerId());
            model.setItemList(getItemList(mCustomerPotentialVO.getItemList()));
            model.setJobCode(mCustomerPotentialVO.getJobCode());
            model.setUseMobile(mCustomerPotentialVO.getUseMobile());
            JSONObject Parameter = iCustomerPotentialTemplate.GetParameters(model);
            if (Parameter.size() > 0) {
                String Mobile = Parameter.getString("Mobile");
                String ChannelTaskID = Parameter.getString("ChannelTaskID");
                if (!StringUtils.isEmpty(ChannelTaskID)) {
                    // 渠道任务ID
                    Map<String, Object> channelTaskParam = new HashMap<String, Object>();
                    channelTaskParam.put("ChannelTaskID", ChannelTaskID);
                    Result channelTaskEntiry = potentialCustomerServiceImpl.mChannelTaskDetail_Select(channelTaskParam);
                    if (channelTaskEntiry.getErrcode() == 0 && channelTaskEntiry.getData() != null) {
                        JSONObject channelTask = (JSONObject) channelTaskEntiry.getData();
                        if (channelTask.size() > 0) {
                            String TaskType = channelTask.getString("TaskType");
                            String Creator = channelTask.getString("Creator");
                            if (TaskType.length() > 0) {
                                model.setChannelTypeID(TaskType);
                                Parameter.put("ChannelUserID", model.getUserID());
                                Parameter.put("UserID", Creator);
                            }
                        }
                    }
                }
                if (StringUtils.isEmpty(model.getChannelTypeID())) {
                    // 没有渠道身份ID
                    jsonResult.setCode(1);
                    jsonResult.setMsg("您当前角色不是渠道身份，不能报备");
                    return jsonResult;
                }
                ChannelRegisterModel channelRegisterModel = iBChannelService.newChannelRegisterModel(model.getUserID(), model.getChannelTypeID(), model.getProjectID());
                if (StringUtils.isEmpty(channelRegisterModel.getUserRule().getRuleID())) {
                    jsonResult.setCode(1);
                    jsonResult.setMsg("未找到该渠道的报备规则");
                    return jsonResult;
                }
                Result CustomerValidate = potentialCustomerServiceImpl.ValidateForReport(Mobile, model.getProjectID(), channelRegisterModel.getUserRule(), channelRegisterModel.getChannelUserId());
                if (CustomerValidate.getErrcode() != 0) {
                    jsonResult.setCode(CustomerValidate.getErrcode());
                    jsonResult.setMsg(potentialCustomerServiceImpl.GetMessageForReturn(CustomerValidate.getErrcode(), channelRegisterModel.getUserRule()));
                    return jsonResult;
                }
                Parameter.put("AdviserGroupID", model.getChannelTypeID());
                Parameter.put("ClueID", UUID.randomUUID().toString());
                Parameter.put("CustomerID", StringUtils.isEmpty(Parameter.getString("CustomerID")) ? UUID.randomUUID().toString() : Parameter.getString("CustomerID"));
                Parameter.put("RuleID", channelRegisterModel.getUserRule().getRuleID());
                Parameter.put("InvalidType", CustomerValidate.getErrcode());
                Parameter.put("InvalidReason", "");
                Parameter.put("InvalidTime", "");
                Parameter.put("ComeOverdueTime", channelRegisterModel.getUserRule().getComeOverdueTime());
                Parameter.put("TradeOverdueTime", channelRegisterModel.getUserRule().getTradeOverdueTime());
                Parameter.put("IsSelect", channelRegisterModel.getUserRule().getProtectRule().getIsSelect());
                Parameter.put("ConfirmUserId", "99");
                Map<String, Object> td = (Map<String, Object>) CustomerValidate.getData();
                if (td != null && td.size() > 0) {
                    Parameter.put("OppID", td.get("OppID"));
                } else {
                    Parameter.put("OppID", "");
                }
                Map<String, Object> pmap = JSONObject.parseObject(Parameter.toJSONString(), Map.class);
                pmap.put("Name", Parameter.getString("LastName") + Parameter.getString("FirstName"));
                List<Map<String, Object>> valid_1_map = bCustomerpotentialMapper.mCustomerPotentialZQDetail_Insert_valid_1(pmap);
                if (valid_1_map != null && valid_1_map.size() > 0) {
                    bCustomerpotentialMapper.mCustomerPotentialZQDetail_Insert_step2(pmap);
                } else {
                    bCustomerpotentialMapper.mCustomerPotentialZQDetail_Insert_step1(pmap);
                }
                List<Map<String, Object>> valid_2_map = bCustomerpotentialMapper.mCustomerPotentialZQDetail_Insert_valid_2(pmap);
                if (valid_2_map != null && valid_2_map.size() > 0) {
                    bCustomerpotentialMapper.mCustomerPotentialZQDetail_Insert_step4(pmap);
                } else {
                    bCustomerpotentialMapper.mCustomerPotentialZQDetail_Insert_step3(pmap);
                }
                bCustomerpotentialMapper.mCustomerPotentialZQDetail_Insert_step5(pmap);
                bCustomerpotentialMapper.mCustomerPotentialZQDetail_Insert_step6(pmap);
                bCustomerpotentialMapper.mCustomerPotentialZQDetail_Insert_step7(pmap);
                if (!com.alibaba.druid.util.StringUtils.isEmpty(Parameter.getString("OppID"))) {
                    bCustomerpotentialMapper.mCustomerPotentialZQDetail_Insert_step8(pmap);
                }
                pmap.put("CustomerRank", "41FA0234-F8AE-434F-8BCD-6E9BE1D059DA");
                pmap.put("UpDownStatus", 1);
                bCustomerpotentialMapper.P_ClueCustomerRank(pmap);
                Boolean res = true;
                if (res) {
                    JSONObject obj1 = new JSONObject();
                    obj1.put("FollwUpType", "渠道报备");
                    obj1.put("FollwUpTypeID", ActionType.渠道报备.getValue());
                    obj1.put("SalesType", 3);
                    obj1.put("NewSaleUserName", "");
                    obj1.put("OldSaleUserName", "");
                    obj1.put("FollwUpUserID", Parameter.getString("UserID"));
                    obj1.put("FollwUpWay", "");
                    obj1.put("FollowUpContent", "");
                    obj1.put("IntentionLevel", "");
                    obj1.put("OrgID", Parameter.getString("OrgID"));
                    obj1.put("FollwUpUserRole", Parameter.getString("JobID"));
                    obj1.put("OpportunityID", "");
                    obj1.put("ClueID", Parameter.getString("ClueID"));
                    obj1.put("NextFollowUpDate", "");
                    CustomerActionVo customerActionVo = JSONObject.parseObject(obj1.toJSONString(), CustomerActionVo.class);
                    iVCustomergwlistSelectService.CustomerFollowUp_Insert(customerActionVo);
                    //增加跟进记录
                    String FollwUpType = CareerConsCustConverter.GetCustomerActionByFollowUpWay(Parameter.getString("FollwUpWay"));
                    if (!com.alibaba.druid.util.StringUtils.isEmpty(FollwUpType)) {
                        JSONObject obj = new JSONObject();
                        obj.put("FollwUpType", FollwUpType);
                        obj.put("FollwUpTypeID", ActionType.valueOf(FollwUpType).getValue());
                        obj.put("SalesType", 3);
                        obj.put("NewSaleUserName", "");
                        obj.put("OldSaleUserName", "");
                        obj.put("FollwUpUserID", Parameter.getString("UserID"));
                        obj.put("FollwUpWay", Parameter.getString("FollwUpWay"));
                        obj.put("FollowUpContent", Parameter.getString("FollowUpContent"));
                        obj.put("IntentionLevel", Parameter.getString("CustomerLevel"));
                        obj.put("OrgID", Parameter.getString("OrgID"));
                        obj.put("FollwUpUserRole", Parameter.getString("JobID"));
                        obj.put("OpportunityID", "");
                        obj.put("ClueID", Parameter.getString("ClueID"));
                        obj.put("NextFollowUpDate", Parameter.getString("NextFollowUpDate"));
                        CustomerActionVo customerActionVo1 = JSONObject.parseObject(obj.toJSONString(), CustomerActionVo.class);
                        iVCustomergwlistSelectService.CustomerFollowUp_Insert(customerActionVo1);
                    }
                    // 潜在客户线索跟进记录更新
                    potentialCustomerServiceImpl.CustomerPotentialClueFollowUpDetail_Update(Parameter);
                }
                ExecutorsUtils.fkExecute(new BOpportunityOtherRunnable(ibOpportunityOtherService, new BOpportunityOther() {{
                    setId(UUID.randomUUID().toString());
                    setClueID(Parameter.getString("ClueID"));
                }}));
                jsonResult.setCode(0);
                jsonResult.setMsg("成功");
            } else {
                jsonResult.setCode(1);
                jsonResult.setMsg("参数格式错误！");
            }
        }
        return jsonResult;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONResult freedomReport(HttpServletRequest request, MBrokerReportVO mBrokerReportVO) throws Exception {
        JSONResult jsonResult = new JSONResult();
        // 记录接口访问信息
        SysAccessRecord sysAccessRecord = sysAccessRecordService.getSysAccessRecord(request);
        // 相关参数校验
        JSONResult validate = validateFreedomReport(sysAccessRecord, mBrokerReportVO);
        if (validate != null) {
            return validate;
        }
        String userID = mBrokerReportVO.getUserId();
        String mobile = mBrokerReportVO.getMobile();
        String projectId = mBrokerReportVO.getIntentProjectID();
        String adviserGroupID = mBrokerReportVO.getAdviserGroupId();
        // 获取报备用户所适用的规则
        ChannelRegisterModel channelRegisterModel = iBChannelService.newChannelRegisterModel(userID, adviserGroupID, projectId);
        if (StringUtils.isEmpty(channelRegisterModel.getUserRule().getRuleID())) {
            sysAccessRecord.setInterfaceState("1");
            sysAccessRecord.setReason("报备失败！所选项目未配置报备规则，请联系管理员！");
            sysAccessRecordMapper.insert(sysAccessRecord);
            return new JSONResult(1, "报备失败！所选项目未配置报备规则，请联系管理员！");
        }
        // 验证报备客户是否有效
        Map<String, Object> CustomerValidate = iBChannelService.ValidateForReport(mobile, projectId, channelRegisterModel);
        String channelOrgId = channeluserService.getChannelOrgID(userID, adviserGroupID);
        Map<String, Object> userRule = new HashMap<>();
        userRule.put("RuleType", channelRegisterModel.getUserRule().getRuleType());
        userRule.put("ValidationMode", channelRegisterModel.getUserRule().getImmissionRule().getValidationMode());
        String msg = clueService.getMessage((int) CustomerValidate.get("InvalidType"), userRule);
        CustomerValidate.put("Message", msg);
        String reMsg = iBChannelService.GetMessageForReturn((int) CustomerValidate.get("InvalidType"), channelRegisterModel.getUserRule());
        jsonResult.setMsg(reMsg);
        // 允许报备
        String invalidType = CustomerValidate.get("InvalidType").toString();
        if (Integer.valueOf(invalidType) == 0) {
            // 通过有效验证
            int status = 0;
            String tag = CustomerValidate.get("Tag").toString();
            if (Boolean.valueOf(tag)) {
                // 竞争带看
                if (channelRegisterModel.getUserRule().getRuleType() == 1) {
                    //创建新线索，线索状态是待确认
                    status = 1;
                } else {
                    // 报备保护
                    // 创建新线索，线索状态是待分配，插入到访逾期时间
                    status = 2;
                }
            } else {
                // 验证不通过
                // 创建新线索，状态是无效，无效时间是当前时间，无效类型取，无效原因取
                status = 3;
            }
            // 线索报备验证后，创建线索信息
            boolean b = clueService.createClue(channelOrgId, CustomerValidate, channelRegisterModel.getUserRule(), status, mBrokerReportVO);
            if (reMsg != null) {
                jsonResult.setMsg(reMsg);
            }
            if (b) {
                jsonResult.setCode(0);
                sysAccessRecord.setInterfaceState("0");
                sysAccessRecord.setReason(reMsg);
                sysAccessRecordMapper.insert(sysAccessRecord);
            } else {
                jsonResult.setCode(1);
                jsonResult.setMsg("存储错误,请联系管理员");
                sysAccessRecord.setInterfaceState("1");
                sysAccessRecord.setReason("存储错误,请联系管理员");
                sysAccessRecordMapper.insert(sysAccessRecord);
            }
        } else {
            // 不允许报备
            // 存在销售机会且SaleUserID不为空 发送报备失败消息
            BChanneluser byId = channeluserService.getById(userID);
            Map<String, Object> obj = new HashMap<String, Object>();
            obj.put("ProjectID", projectId);
            obj.put("CustomerMobile", mobile);
            Map<String, Object> opp = bCustomerpotentialMapper.ValidOpp_Select(obj);
            if (CollectionUtil.isNotEmpty(opp)) {
                customerTemplate.sendBBSBMsg((String) opp.get("ID"), byId.getName(), userID);
            }
            jsonResult.setCode(1);
            sysAccessRecord.setInterfaceState("1");
            sysAccessRecord.setReason(reMsg);
            sysAccessRecordMapper.insert(sysAccessRecord);
        }
        return jsonResult;
    }

    /**
     * 置业顾问报备客户ItemList转换
     *
     * @param paramList
     * @return
     */
    private List<CGWDetailModel.Item> getItemList(List<Map<String, String>> paramList) throws Exception {
        List<CGWDetailModel.Item> result = new ArrayList<CGWDetailModel.Item>();
        ItemEnum itemEnum = null;
        for (Map map : paramList) {
            if (null != ItemEnum.getEnumByCode(String.valueOf(map.get("name")))) {
                itemEnum = ItemEnum.getEnumByCode(String.valueOf(map.get("name")));
                CGWDetailModel.Item item = new CGWDetailModel.Item();
                item.setID(itemEnum.getMessage());
                item.setName(String.valueOf(map.get("name")));
                item.setValue(String.valueOf(map.get("value")));
                result.add(item);
            }
        }
        return result;
    }

    /**
     * 自由经纪人/自渠人员报备客户参数校验
     *
     * @param sysAccessRecord
     * @param mBrokerReportVO
     * @return
     */
    private JSONResult validateFreedomReport(SysAccessRecord sysAccessRecord, MBrokerReportVO mBrokerReportVO) throws Exception {
        JSONResult result = null;
        if (null == mBrokerReportVO) {
            sysAccessRecord.setInterfaceState("1");
            sysAccessRecord.setReason("mBrokerReportVO参数不能为空");
            sysAccessRecordMapper.insert(sysAccessRecord);
            result = new JSONResult(1, "mBrokerReportVO参数不能为空");
        }
        // 验证是否重复请求
        if (StringUtils.isBlank(mBrokerReportVO.getFormSessionId())) {
            sysAccessRecord.setInterfaceState("1");
            sysAccessRecord.setReason("FormSessionID不可为null");
            sysAccessRecordMapper.insert(sysAccessRecord);
            result = new JSONResult(1, "FormSessionID不可为null");
        }
        // 验证手机号格式
        if (PhoneUtil.isNotValidChinesePhone(mBrokerReportVO.getMobile())) {
            sysAccessRecord.setInterfaceState("1");
            sysAccessRecord.setReason("手机号格式错误");
            sysAccessRecordMapper.insert(sysAccessRecord);
            result = new JSONResult(1, "手机号格式错误");
        }
        // 查询是否已经存在无效的FormSessionID,不存在则更新为无效状态
        int RowCount = iSFormsessionService.checkFormSessionID(mBrokerReportVO.getFormSessionId());
        if (RowCount == 1) {
            sysAccessRecord.setInterfaceState("1");
            sysAccessRecord.setReason("不能重复请求！");
            sysAccessRecordMapper.insert(sysAccessRecord);
            result = new JSONResult(1, "不能重复请求！");
        }
        if (StringUtils.isBlank(mBrokerReportVO.getAdviserGroupId())) {
            sysAccessRecord.setInterfaceState("1");
            sysAccessRecord.setReason("未能识别报备人的身份");
            sysAccessRecordMapper.insert(sysAccessRecord);
            result = new JSONResult(1, "未能识别报备人的身份");
        }
        // 1.不允许报备自己 0.允许报备自己  IsReportOwn
        int IsReportOwn = projectService.isReport(mBrokerReportVO.getIntentProjectID(), mBrokerReportVO.getUserId(), mBrokerReportVO.getMobile());
        if (IsReportOwn == 1) {
            sysAccessRecord.setInterfaceState("1");
            sysAccessRecord.setReason("不允许报备自己");
            sysAccessRecordMapper.insert(sysAccessRecord);
            result = new JSONResult(1, "不允许报备自己");
        }
        // 没有ChannelOrgID 的不能报备
        int IsReport = channeluserService.isReport(mBrokerReportVO.getUserId());
        // 没有ChannelOrgID 的不能报备
        if (IsReport == -2) {
            sysAccessRecord.setInterfaceState("1");
            sysAccessRecord.setReason("没有所属机构的人员，不能报备");
            sysAccessRecordMapper.insert(sysAccessRecord);
            result = new JSONResult(1, "没有所属机构的人员，不能报备");
        }
        // ChannelOrgID禁用状态的不能报备
        if (IsReport == 0) {
            sysAccessRecord.setInterfaceState("1");
            sysAccessRecord.setReason("所属机构在禁用状态，不能报备");
            sysAccessRecordMapper.insert(sysAccessRecord);
            result = new JSONResult(1, "所属机构在禁用状态，不能报备");
        }
        return result;
    }
}
