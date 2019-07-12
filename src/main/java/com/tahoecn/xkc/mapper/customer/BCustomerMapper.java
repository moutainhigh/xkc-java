package com.tahoecn.xkc.mapper.customer;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tahoecn.xkc.model.customer.BCustomer;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-05
 */
public interface BCustomerMapper extends BaseMapper<BCustomer> {

    IPage<Map<String, Object>> customerChangePageList_Select(IPage page, @Param("projectID") String projectID, @Param("sqlWhere") String sqlWhere);

    List<Map<String, Object>> excelToCustomerChangeList(@Param("projectID") String projectID, @Param("sqlWhere") String sqlWhere);

    List<Map<String, Object>> getDistributionList_Select(@Param("projectID") String projectID);

    IPage<Map<String, Object>> CustomerChange_BakPageList_Select(IPage page, @Param("projectID") String projectID, @Param("sqlWhere") String sqlWhere);

    List<Map<String, Object>> SetExcelToCustomerChange_BakList(@Param("projectID") String projectID, @Param("sqlWhere") String sqlWhere);

    Map<String, Object> CustomerChangeDetailAll_Select(@Param("projectID") String projectID, @Param("customerID") String customerID, @Param("clueID") String clueID);

    /**
     * 验证是否是本项目老业主
     */
    List<Map<String, Object>> IsProjectOwner_Select(String projectId, String phone);

    Map<String, Object> CustomerChangeDetailAll_Select(@Param("projectID") String projectID, @Param("customerID") String customerID, @Param("clueID") String clueID, @Param("CustomerID") String CustomerID);

    Map<String, Object> CustomerChangeDetailAll_Select2(@Param("projectID") String projectID, @Param("customerID") String customerID, @Param("clueID") String clueID);

    Map<String, Object> getB_OpportunityByClueID(@Param("clueID") String clueID);

    Map<String, Object> getB_CustomerPotential(@Param("customerID") String customerID);

    Map<String, Object> getB_Customer(@Param("customerID") String customerID);

    List<Map<String, Object>> CustomerFollowUp_Select(@Param("projectID")String projectId, @Param("mobile")String mobile);

    List<Map<String, Object>> CustomerSignInfo_Select(@Param("projectID")String projectId, @Param("customerID")String customerID);

    List<Map<String,Object>> CustomerPayInfo_Select(@Param("projectID")String projectId, @Param("customerID")String customerID);

    List<Map<String,Object>> CustomerSourceTypeChange_Select(@Param("projectID")String projectId, @Param("customerID")String customerID);

    List<Map<String,Object>> CustomerExtendProtect_Select(@Param("projectID")String projectId, @Param("customerID")String customerID);

    List<Map<String,Object>> CustomerSourceType_Select(@Param("projectID")String projectId, @Param("customerID")String customerID);

    List<Map<String,Object>> CustomerSetInvalid_Select(@Param("projectID")String projectId, @Param("customerID")String customerID);

    List<Map<String,Object>> CustomerCognitiveChannel_Select(@Param("projectID")String projectId, @Param("customerID")String customerID);

    List<Map<String,Object>> SourceTypeChangeList_Select(@Param("Mobile")String Mobile, @Param("projectID")String projectID, @Param("sqlWhere") String sqlWhere, @Param("ClueID") String ClueID);


    void SourceTypeChangeDetail_Update(@Param("oldClueID")String oldClueID, @Param("clueID")String clueID,
                                        @Param("userID")String userID, @Param("reason")String reason, @Param("enclosure")String enclosure);

    void ExtendProtectDetail_Update(@Param("protectNum")String protectNum, @Param("clueID")String clueID, @Param("userID")String userID,
                                    @Param("reason")String reason, @Param("enclosure")String enclosure);

    void SetInvalidDetail_Update(@Param("clueID")String clueID, @Param("reason")String reason,
                                 @Param("enclosure")String enclosure, @Param("userID")String userID);

    void ChangeSourceTypeDetail_Update(@Param("clueID")String clueID, @Param("SourceType")String SourceType,@Param("reason")String reason,
                                       @Param("enclosure")String enclosure,@Param("userID")String userID, @Param("type")String type);


    void ChangeCognitiveChannelDetail_Update(@Param("clueID")String clueID, @Param("cognitiveChannel")String cognitiveChannel, @Param("reason")String reason,
                                             @Param("enclosure")String enclosure, @Param("userID")String userID, @Param("type")String type);

    IPage<Map<String,Object>> CustomerGuidePageList_Select(IPage page,@Param("sql")String sql);

    List<Map<String,Object>> CustomerGuideDownLoadByIDList_Select(@Param("arr")String arr);

    List<Map<String,Object>> CustomerGuideDownLoadList_Select(@Param("sqlWhere")String sqlWhere);

    Map<String,Object> getB_OpportunityById(@Param("opportunityID")String opportunityID);

    Map<String,Object> CustomerNEWDetail_Select(@Param("projectID")String projectID, @Param("customerID")String customerID,
                                                @Param("reportUserID")String reportUserID);
    Map<String,Object> CustomerNEWDetail_Select2(@Param("projectID")String projectID, @Param("customerID")String customerID,
                                                @Param("opportunityID")String opportunityID);

    List<Map<String,Object>> CustomerNEWFollowUp_Select(@Param("projectID")String projectID, @Param("customerID")String customerID,
                                                        @Param("opportunityID")String opportunityID);

    List<Map<String,Object>> CustomerNEWSignInfo_Select(@Param("projectID")String projectID, @Param("customerID")String customerID);

    List<Map<String,Object>> CustomerNEWPayInfo_Select(@Param("projectID")String projectID, @Param("customerID")String customerID);

    int getB_CustomerGuide(@Param("projectID")String projectID);

    String getB_Project(@Param("projectID")String projectID);

    String getS_Account(@Param("userID")String userID);

    Map<String,Object> CustomerGuidePrintDetail_Select(@Param("num")int num, @Param("num1")String num1, @Param("username")String username,
                                                       @Param("userID")String userID, @Param("id")String id);

    void CustomerGuidePrintDetail_Insert(Map<String, Object> result);

    void CustomerGuidePrintDetail_Update(Map<String, Object> result);
}
