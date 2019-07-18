package com.tahoecn.xkc.mapper.customer;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tahoecn.xkc.model.customer.BCustomer;
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
public interface BCustomerManagerMapper extends BaseMapper<BCustomer> {
    IPage<Map<String,Object>> CustomerManagePageList_Select(IPage page, Map<String, Object> map);

    Map<String,Object> CustomerNEWDetail_SelectGT(Map<String, Object> map);

    Map<String,Object> CustomerNEWDetail_SelectLT(Map<String, Object> map);

    List<Map<String,Object>> CustomerNEWFollowUp_Select(Map<String, Object> map);

    List<Map<String,Object>> CustomerNEWSignInfo_Select(Map<String, Object> map);

    List<Map<String,Object>> CustomerNEWPayInfo_Select(Map<String, Object> map);

    IPage<Map<String,Object>> SetExcelToCustomerChange_BakList(Map<String, Object> map, StringBuilder sqlWhere);
}
