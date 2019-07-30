package com.tahoecn.xkc.service.customer;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.customer.BCustomer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-05
 */
public interface IBCustomerService extends IService<BCustomer> {

    IPage<Map<String,Object>> customerChangePageList_Select(IPage page, String projectID, String sqlWhere);

    List<Map<String,Object>> setExcelToCustomerChangeList(String projectID, String sqlWhere);

    List<Map<String,Object>> GetDistributionList_Select(String project);

    IPage<Map<String,Object>> CustomerChange_BakPageList_Select(IPage page, String projectID, String sqlWhere);

    List<Map<String,Object>> SetExcelToCustomerChange_BakList(String projectID, String sqlWhere);

    Map<String,Object> CustomerChangeDetailAll_Select(String projectId, String customerID, String clueID);
    /**
     * 验证是否是本项目老业主
     */
    List<Map<String,Object>> IsProjectOwner_Select(String projectId, String phone);

    List<Map<String,Object>> SourceTypeChangeList_Select(String Mobile, String projectID,String sqlWhere,String ClueID);

    void SourceTypeChangeDetail_Update(String oldClueID, String clueID, String userID, String reason, String enclosure);

    void ExtendProtectDetail_Update(String protectNum, String clueID, String userID, String reason, String enclosure);

    void SetInvalidDetail_Update(String clueID, String reason, String enclosure, String userID);

    void ChangeSourceTypeDetail_Update(String clueID, String SourceType, String reason, String enclosure,String userID,String type);

    void ChangeCognitiveChannelDetail_Update(String clueID, String cognitiveChannel, String reason, String enclosure, String userID, String type);

    IPage<Map<String,Object>> CustomerGuidePageList_Select(Integer pageIndex, Integer pageSize, String sql);

    List<Map<String,Object>> CustomerGuideDownLoadByIDList_Select(String arr);

    List<Map<String,Object>> CustomerGuideDownLoadList_Select(String sqlWhere);

    Map<String,Object> CustomerNEWDetailAll_Select(String projectID, String customerID, String reportUserID, String opportunityID);

    Map<String,Object> CustomerGuidePrintDetail_Select(String ProjectID, String UserID, String ID);

    void CustomerGuidePrintDetail_Insert(Map<String, Object> result);

    void CustomerGuidePrintDetail_Update(Map<String, Object> result);

    IPage<Map<String,Object>> CustomerLockRoomPageList_Select(IPage page, String sqlWhere);

    List<Map<String,Object>> CustomerLockRoomDownLoadByIDList_Select(String s);

    List<Map<String,Object>> CustomerLockRoomDownLoadList_Select(StringBuilder sqlWhere);

    /*
     * 线上预约客户列表
     */
	IPage<Map<String, Object>> mCustomerShareList_Select(IPage page, String UserID, String ProjectID);

    int findPrintNumByDate(String serialNumberText);
}
