package com.tahoecn.xkc.mapper.customer;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tahoecn.xkc.model.customer.BCustomer;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-05
 */
public interface BCustomerMapper extends BaseMapper<BCustomer> {

    IPage<Map<String, Object>> customerChangePageList_Select(IPage page, @Param("projectID") String projectID, @Param("sqlWhere") String sqlWhere);
    List<Map<String,Object>> excelToCustomerChangeList(@Param("projectID")String projectID, @Param("sqlWhere")String sqlWhere);
    List<Map<String,Object>> getDistributionList_Select(@Param("projectID")String projectID);
    IPage<Map<String,Object>> CustomerChange_BakPageList_Select(IPage page, @Param("projectID") String projectID, @Param("sqlWhere") String sqlWhere);
    List<Map<String,Object>> SetExcelToCustomerChange_BakList(@Param("projectID")String projectID, @Param("sqlWhere")String sqlWhere);
    Map<String,Object> CustomerChangeDetailAll_Select(@Param("projectID")String projectID, @Param("customerID")String customerID, @Param("clueID")String clueID,@Param("CustomerID")String CustomerID);
    Map<String,Object> CustomerChangeDetailAll_Select2(@Param("projectID")String projectID, @Param("customerID")String customerID, @Param("clueID")String clueID);
    Map<String,Object> getB_OpportunityByClueID(@Param("clueID")String clueID);
}
