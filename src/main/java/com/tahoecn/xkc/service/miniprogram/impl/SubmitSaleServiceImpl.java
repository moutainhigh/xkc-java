package com.tahoecn.xkc.service.miniprogram.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.core.json.JSONResult;
import com.tahoecn.xkc.common.enums.ActionType;
import com.tahoecn.xkc.common.enums.ItemEnum;
import com.tahoecn.xkc.mapper.channel.BChanneluserMapper;
import com.tahoecn.xkc.mapper.customer.VCustomergwlistSelectMapper;
import com.tahoecn.xkc.model.channel.BChanneluser;
import com.tahoecn.xkc.model.miniprogram.vo.customerreport.MDynatownCustomerVO;
import com.tahoecn.xkc.model.vo.CGWDetailModel;
import com.tahoecn.xkc.model.vo.CustomerActionVo;
import com.tahoecn.xkc.service.customer.ICustomerHelp;
import com.tahoecn.xkc.service.customer.impl.VCustomergwlistSelectServiceImpl;
import com.tahoecn.xkc.service.miniprogram.ISubmitSaleService;
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
 * 思为小程序提交销售系统服务实现类
 * </p>
 *
 * @author zhaoyan
 * @since 2020-04-08
 */
@Service
public class SubmitSaleServiceImpl extends ServiceImpl<BChanneluserMapper, BChanneluser> implements ISubmitSaleService {

    @Resource
    private ICustomerHelp customerTemplate;

    @Resource
    private VCustomergwlistSelectMapper vCustomergwlistSelectMapper;

    @Resource
    private VCustomergwlistSelectServiceImpl vCustomergwlistSelectServiceImpl;

    @Override
    public JSONResult submit(HttpServletRequest request, MDynatownCustomerVO mDynatownCustomerVO) throws Exception {
        JSONResult jsonResult = new JSONResult();
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
            String opportunityID = mDynatownCustomerVO.getOpportunityId();
            String userID = mDynatownCustomerVO.getUserId();
            if (!customerTemplate.CustomerIsCanOperate(opportunityID, userID)) {
                jsonResult.setCode(1);
                jsonResult.setMsg("开启异地销售，置业顾问申请客户丢失后，协作人不能对客户进行操作");
                return jsonResult;
            }
            JSONObject opportunityObj = OpportunityCustomer(model.getOpportunityID());
            JSONObject parameter = customerTemplate.GetParameters(model, opportunityObj);
            String Mobile = parameter.getString("Mobile");
            // 验证手机号码
            if (!StringUtils.isEmpty(Mobile)) {
                // 手机号码+项目ID验证是否存在机会客户
                JSONObject re = customerTemplate.CustomerOpportunityExist(model.getProjectID(), Mobile);
                // 客户信息已存在
                if (re.getBoolean("status")) {
                    Map<String, Object> pmap = JSONObject.parseObject(parameter.toJSONString(), Map.class);
                    pmap.put("Name", parameter.getString("LastName") + parameter.getString("FirstName"));
                    vCustomergwlistSelectMapper.mCustomerSubscribeDetail_Insert_step1(pmap);
                    List<Map<String, Object>> step2_map = vCustomergwlistSelectMapper.mCustomerSubscribeDetail_Insert_step2_valid(pmap);
                    if (step2_map != null && step2_map.size() > 0) {
                        vCustomergwlistSelectMapper.mCustomerSubscribeDetail_Insert_step2_update(pmap);
                    } else {
                        vCustomergwlistSelectMapper.mCustomerSubscribeDetail_Insert_step2_insert(pmap);
                    }
                    vCustomergwlistSelectMapper.mCustomerSubscribeDetail_Insert_step3(pmap);
                    vCustomergwlistSelectMapper.mCustomerSubscribeDetail_Insert_step4(pmap);
                    vCustomergwlistSelectMapper.mCustomerSubscribeDetail_Insert_step5(pmap);
                    vCustomergwlistSelectMapper.mCustomerSubscribeDetail_Insert_step6(pmap);
                    vCustomergwlistSelectMapper.mCustomerSubscribeDetail_Insert_step7(pmap);
                    vCustomergwlistSelectMapper.mCustomerSubscribeDetail_Insert_step8(pmap);

                    String IsLittleBooking = parameter.getString("IsLittleBooking");
                    if (IsLittleBooking.equals("1DCCBDB8-AD44-44D4-B23A-571A38337D5C")) {
                        pmap.put("CustomerRank", "D47878CE-D560-44C0-A6F8-4C92CCAC9EE0");
                        pmap.put("UpDownStatus", 1);
                        vCustomergwlistSelectMapper.P_OpportunityCustomerRank(pmap);
                    } else if (IsLittleBooking.equals("DFE6406C-120B-45E5-9293-DD093E416C68")) {
                        pmap.put("CustomerRank", "D47878CE-D560-44C0-A6F8-4C92CCAC9EE0");
                        pmap.put("UpDownStatus", 2);
                        vCustomergwlistSelectMapper.P_OpportunityCustomerRank(pmap);
                    }
                    pmap.put("ClueID", "");
                    vCustomergwlistSelectMapper.P_SyncClueOpportunity_Update(pmap);

                    // 增加意向项目
                    customerTemplate.IntentProjectAdd(parameter);
                    String IsLittleBookingOld = opportunityObj.getString("IsLittleBooking");
                    if (StringUtils.isEmpty(IsLittleBookingOld)) {
                        IsLittleBookingOld = "DFE6406C-120B-45E5-9293-DD093E416C68";
                    }
                    // 小筹
                    if (IsLittleBooking.equals("1DCCBDB8-AD44-44D4-B23A-571A38337D5C") && !IsLittleBookingOld.equals(IsLittleBooking)) {
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
                    // 退小筹
                    if (IsLittleBooking.equals("DFE6406C-120B-45E5-9293-DD093E416C68") && !IsLittleBookingOld.equals(IsLittleBooking)) {
                        JSONObject obj2 = new JSONObject();
                        obj2.put("FollwUpType", "退小筹");
                        obj2.put("FollwUpTypeID", ActionType.退小筹.getValue());
                        obj2.put("SalesType", 1);
                        obj2.put("NewSaleUserName", "");
                        obj2.put("OldSaleUserName", "");
                        obj2.put("FollwUpUserID", "99");
                        obj2.put("FollwUpWay", "");
                        obj2.put("FollowUpContent", "退小筹");
                        obj2.put("IntentionLevel", "");
                        obj2.put("OrgID", "");
                        obj2.put("FollwUpUserRole", "");
                        obj2.put("OpportunityID", parameter.getString("OpportunityID"));
                        obj2.put("ClueID", "");
                        obj2.put("NextFollowUpDate", "");
                        CustomerActionVo customerActionVo = JSONObject.parseObject(obj2.toJSONString(), CustomerActionVo.class);
                        vCustomergwlistSelectServiceImpl.CustomerFollowUp_Insert(customerActionVo);
                    }
                    jsonResult.setCode(0);
                    jsonResult.setMsg("成功");
                }
                // 省去图片生成处理
                if (StringUtils.isNotEmpty(mDynatownCustomerVO.getRoomId())) {
                    Map<String, Object> objParam = new HashMap<String, Object>();
                    objParam.put("ProjectID", mDynatownCustomerVO.getProjectId());
                    objParam.put("OpportunityID", mDynatownCustomerVO.getOpportunityId());
                    objParam.put("CustomerID", mDynatownCustomerVO.getCustomerId());
                    objParam.put("RoomID", mDynatownCustomerVO.getRoomId());
                    objParam.put("UserID", mDynatownCustomerVO.getUserId());
                    vCustomergwlistSelectMapper.CustomerLockRoomDetail_Insert(objParam);
                }
            }
        }
        return jsonResult;
    }

    /**
     * 提交销售系统ItemList补充
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

    public JSONObject OpportunityCustomer(String opportunityID) {
        //1.根据机会判断是否为机会客户
        try {
            Map<String, Object> result = vCustomergwlistSelectMapper.sCustomerGWSearch_Select(opportunityID);
            return new JSONObject(result);
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONObject();
        }

    }
}
