package com.tahoecn.xkc.mapper.opportunity;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.opportunity.BOpportunity;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-04
 */
public interface BOpportunityMapper extends BaseMapper<BOpportunity> {

    int getCustomerCount(@Param("projectId") String projectId, @Param("MemberID") String memberId);

    /**
     * 客户确认归属
     */
    void mCustomerBelong_Update(Map<String, Object> paramMap);
	/*List<Map<String,Object>> getYQWGJ(Map<String,Object> paramMap);
	List<Map<String,Object>> getYQWRG(Map<String,Object> paramMap);
	List<Map<String,Object>> getYQWQY(Map<String,Object> paramMap);
	List<Map<String,Object>> getYQWJK(Map<String,Object> paramMap);
	void updateCustomerRecovery1(Map<String,Object> paramMap);
	void updateCustomerRecovery2(Map<String,Object> paramMap);
	void updateCustomerRecovery3(Map<String,Object> paramMap);
	void updateCustomerRecovery4(Map<String,Object> paramMap);
	void updateCustomerRecovery5(Map<String,Object> paramMap);*/

    /**
     * 回归
     */
    void mCustomerRecovery_Update(Map<String, Object> paramMap);

    /**
     * 客户分配置业顾问
     */
    void mCustomerAllotAdviser_Update(Map<String, Object> paramMap);

    /**
     * 公共池客户重新分配协作人置空
     */
    void mCustomerSalePartnerSetNull_Update(Map<String, Object> paramMap);

    /**
     * 自有渠道客户列表
     */
    List<List<?>> mCustomerZYQDList_Select(Map<String, Object> paramMap);

    /**
     * 丢失客户列表
     */
    List<List<?>> mCustomerDSList_Select(Map<String, Object> paramMap);

    /**
     * 正常跟进列表
     */
    List<List<?>> mCustomerGJList_Select(Map<String, Object> paramMap);

    /**
     * 撞单客户列表
     */
    List<List<?>> mCustomerZDList_Select(Map<String, Object> paramMap);

    /**
     * 公共客户列表
     */
    List<List<?>> mCustomerGGList_Select(Map<String, Object> paramMap);

    /**
     * 逾期客户未交款列表
     */
    List<List<?>> mCustomerYQWJKList_Select(Map<String, Object> paramMap);

    /**
     * 逾期客户未签约列表
     */
    List<List<?>> mCustomerYQWQYList_Select(Map<String, Object> paramMap);

    /**
     * 逾期客户未认购列表
     */
    List<List<?>> mCustomerYQWRGList_Select(Map<String, Object> paramMap);

    /**
     * 逾期客户未跟进列表
     */
    List<List<?>> mCustomerYQWGJList_Select(Map<String, Object> paramMap);

    /**
     * 客户置业顾问列表
     */
    List<List<?>> mCustomerAdviserList_Select(Map<String, Object> paramMap);

    /**
     * 逾期未签约列表
     */
    List<List<?>> R_ACYQWQYList_Select(Map<String, Object> paramMap);

    /**
     * 逾期未交款列表
     */
    List<List<?>> R_ACYQWJKList_Select(Map<String, Object> paramMap);

    /**
     * 逾期客户头部统计
     */
    List<Map<String, Object>> mCustomerYQCateList_Select(Map<String, Object> paramMap);

    /**
     * 客户管理分类
     */
    List<Map<String, Object>> mCustomerCateList_Select(Map<String, Object> paramMap);

    /**
     * @description: 风控查询防截客数据
     * @return:
     * @author: 张晓东
     * @time: 2020/5/8 18:05
     */
    List<Map<String, Object>> fkProtectCustomer(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /**
     * @description: 风控查询修改用户姓名数据
     * @return:
     * @author: 张晓东
     * @time: 2020/5/8 18:05
     */
    Map<String, Object> fkSearchInfo(@Param("id") String id);

    /**
     * @description: 查当前的置业顾问和报备人
     * @return:
     * @author: 张晓东
     * @time: 2020/5/23 14:53
     */
    Map<String, Object> fkSearchCurrentInfo(@Param("mobile") String mobile, @Param("projectId") String projectId);

    /**
     * @description: 风控人脸明细查询
     * @return:
     * @author: 张晓东
     * @time: 2020/6/11 18:00
     */
    Map<String, Object> fkSearchFaceInfo(@Param("projectId") String projectId, @Param("cardId") String cardId);


    /**
     *
     * @description: 旺小宝统计报表
     * @return:
     * @author: 张晓东
     * @time: 2020/6/22 15:55
     */
    List<Map<String, Object>> wxbriskcountTaskInfo(@Param("projectId") String projectId, @Param("cardId") String cardId);

}
