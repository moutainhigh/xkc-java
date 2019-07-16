package com.tahoecn.xkc.service.customer.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.customer.BCustomerMapper;
import com.tahoecn.xkc.model.customer.BCustomer;
import com.tahoecn.xkc.service.customer.IBCustomerService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-05
 */
@Service
public class BCustomerServiceImpl extends ServiceImpl<BCustomerMapper, BCustomer> implements IBCustomerService {


    @Override
    public IPage<Map<String, Object>> customerChangePageList_Select(IPage page, String projectID, String sqlWhere) {
        return baseMapper.customerChangePageList_Select(page, projectID, sqlWhere);
    }

    @Override
    public List<Map<String, Object>> setExcelToCustomerChangeList(String projectID, String sqlWhere) {
        return baseMapper.excelToCustomerChangeList(projectID, sqlWhere);
    }

    @Override
    public List<Map<String, Object>> GetDistributionList_Select(String project) {
        return baseMapper.getDistributionList_Select(project);
    }

    @Override
    public IPage<Map<String, Object>> CustomerChange_BakPageList_Select(IPage page, String projectID, String sqlWhere) {
        return baseMapper.CustomerChange_BakPageList_Select(page, projectID, sqlWhere);
    }

    @Override
    public List<Map<String, Object>> SetExcelToCustomerChange_BakList(String projectID, String sqlWhere) {
        return baseMapper.SetExcelToCustomerChange_BakList(projectID, sqlWhere);
    }

    @Override
    public Map<String,Object> CustomerChangeDetailAll_Select(String projectId, String customerID, String clueID) {
        Map<String,Object> result = new HashMap<>();
        Map<String, Object> opportunity = baseMapper.getB_OpportunityByClueID(clueID);
        //客户基本信息
        Map<String, Object> customerChangeDetail;
        if (opportunity != null) {
            customerChangeDetail = baseMapper.CustomerChangeDetailAll_Select(projectId, customerID, clueID, opportunity.get("CustomerID").toString());
        } else {
            customerChangeDetail = baseMapper.CustomerChangeDetailAll_Select2(projectId, customerID, clueID);
        }


        Map<String, Object> customerPotential = baseMapper.getB_CustomerPotential(customerID);
        if (customerPotential == null) {
            customerPotential = baseMapper.getB_Customer(customerID);
        }
        //查询客户跟进记录
        List<Map<String, Object>> customerFollowUp = baseMapper.CustomerFollowUp_Select(projectId, customerPotential.get("Mobile").toString());
        //查询客户签约信息
        List<Map<String, Object>> customerSignInfo = baseMapper.CustomerSignInfo_Select(projectId, customerID);
        //查询客户付款信息
        List<Map<String, Object>> CustomerPayInfo = baseMapper.CustomerPayInfo_Select(projectId, "%"+customerID+"%");
        //渠道归属变更记录
        List<Map<String, Object>> CustomerSourceTypeChange = baseMapper.CustomerSourceTypeChange_Select(projectId, customerID);
        //保护期变更记录
        List<Map<String, Object>> CustomerExtendProtect = baseMapper.CustomerExtendProtect_Select(projectId, customerID);
        //渠道类型变更
        List<Map<String, Object>> CustomerSourceType = baseMapper.CustomerSourceType_Select(projectId, customerID);
        //无效记录
        List<Map<String, Object>> CustomerSetInvalid = baseMapper.CustomerSetInvalid_Select(projectId, customerID);
        //认知媒体记录
        List<Map<String, Object>> CustomerCognitiveChannel = baseMapper.CustomerCognitiveChannel_Select(projectId, customerID);

        result.put("CustomerChangeDetail",customerChangeDetail);
        result.put("CustomerFollowUp",customerFollowUp);
        result.put("CustomerSignInfo",customerSignInfo);
        result.put("CustomerPayInfo",CustomerPayInfo);
        result.put("CustomerSourceTypeChange",CustomerSourceTypeChange);
        result.put("CustomerExtendProtect",CustomerExtendProtect);
        result.put("CustomerSourceType",CustomerSourceType);
        result.put("CustomerSetInvalid",CustomerSetInvalid);
        result.put("CustomerCognitiveChannel",CustomerCognitiveChannel);
        return result;
    }

    @Override
    public List<Map<String, Object>> SourceTypeChangeList_Select(String Mobile, String projectID,String sqlWhere,String ClueID) {
        return baseMapper.SourceTypeChangeList_Select(Mobile, projectID,sqlWhere,ClueID);
    }

    @Override
    public void SourceTypeChangeDetail_Update(String oldClueID, String clueID, String userID, String reason, String enclosure) {
        baseMapper.SourceTypeChangeDetail_Update( oldClueID,  clueID,  userID,  reason,  enclosure);
    }

    @Override
    public void ExtendProtectDetail_Update(String protectNum, String clueID, String userID, String reason, String enclosure) {
        baseMapper.ExtendProtectDetail_Update(protectNum,clueID,userID,reason,enclosure);
    }

    @Override
    public void SetInvalidDetail_Update(String clueID, String reason, String enclosure, String userID) {
        baseMapper.SetInvalidDetail_Update(clueID, reason, enclosure, userID);
    }

    @Override
    public void ChangeSourceTypeDetail_Update(String clueID, String SourceType, String reason, String enclosure,String userID,String type) {
        baseMapper.ChangeSourceTypeDetail_Update(clueID, SourceType, reason, enclosure,userID,type);
    }

    @Override
    public void ChangeCognitiveChannelDetail_Update(String clueID, String cognitiveChannel, String reason, String enclosure, String userID, String type) {
        baseMapper.ChangeCognitiveChannelDetail_Update(clueID, cognitiveChannel, reason, enclosure,userID,type);
    }

    @Override
    public IPage<Map<String, Object>> CustomerGuidePageList_Select(Integer pageIndex, Integer pageSize, String sql) {
        return baseMapper.CustomerGuidePageList_Select(new Page(pageIndex, pageSize), sql);
    }

    @Override
    public List<Map<String, Object>> CustomerGuideDownLoadByIDList_Select(String arr) {
        return baseMapper.CustomerGuideDownLoadByIDList_Select(arr);
    }

    @Override
    public List<Map<String, Object>> CustomerGuideDownLoadList_Select(String sqlWhere) {
        return baseMapper.CustomerGuideDownLoadList_Select(sqlWhere);
    }

    @Override
    public Map<String, Object> CustomerNEWDetailAll_Select(String projectID, String customerID, String reportUserID, String opportunityID) {
        Map<String,Object> result = new HashMap<>();

        Map<String, Object> Opportunity = baseMapper.getB_OpportunityById(opportunityID);
        //客户基本信息
        Map<String, Object> CustomerNEWDetail;
        if (Opportunity != null) {
            CustomerNEWDetail = baseMapper.CustomerNEWDetail_Select(projectID, customerID, reportUserID);
        } else {
            CustomerNEWDetail = baseMapper.CustomerNEWDetail_Select2(projectID, customerID, opportunityID);
        }

        //查询客户跟进记录
        List<Map<String, Object>> CustomerNEWFollowUp = baseMapper.CustomerNEWFollowUp_Select(projectID, customerID, opportunityID);
        //查询客户签约信息
        List<Map<String, Object>> CustomerNEWSignInfo = baseMapper.CustomerNEWSignInfo_Select(projectID, customerID);
        //查询客户付款信息
        List<Map<String, Object>> CustomerNEWPayInfo = baseMapper.CustomerNEWPayInfo_Select(projectID, "%"+customerID+"%");
        result.put("CustomerNEWDetail",CustomerNEWDetail);
        result.put("CustomerNEWFollowUp",CustomerNEWFollowUp);
        result.put("CustomerNEWSignInfo",CustomerNEWSignInfo);
        result.put("CustomerNEWPayInfo",CustomerNEWPayInfo);
        return result;
    }

    @Override
    public void CustomerGuidePrintDetail_Insert(Map<String, Object> result) {
        baseMapper.CustomerGuidePrintDetail_Insert(result);
    }

    @Override
    public void CustomerGuidePrintDetail_Update(Map<String, Object> result) {
        baseMapper.CustomerGuidePrintDetail_Update(result);
    }

    @Override
    public Map<String, Object> CustomerGuidePrintDetail_Select(String ProjectID, String UserID, String ID) {
        int num = baseMapper.getB_CustomerGuide(ProjectID);
        String num1 = baseMapper.getB_Project(ProjectID);
        String username = baseMapper.getS_Account(UserID);
        return baseMapper.CustomerGuidePrintDetail_Select(num, num1,username,UserID,ID);
    }

    /**
     * 验证是否是本项目老业主
     */
    @Override
    public List<Map<String, Object>> IsProjectOwner_Select(String projectId, String phone) {
        return baseMapper.IsProjectOwner_Select(projectId, phone);
    }

    /*
     * 线上预约客户列表
     */
	@Override
	public IPage<Map<String, Object>> mCustomerShareList_Select(IPage page, String UserID, String ProjectID) {
		return baseMapper.mCustomerShareList_Select(page, UserID, ProjectID);
	}
}
