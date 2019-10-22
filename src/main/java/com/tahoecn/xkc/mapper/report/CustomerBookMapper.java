package com.tahoecn.xkc.mapper.report;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.customer.CustomerBook;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-08-28
 */
public interface CustomerBookMapper extends BaseMapper<CustomerBook> {

    List<Map<String,Object>> listOpp(@Param("id") String id);

    List<Map<String,Object>> listClue(@Param("id") String id);

    List<Map<String,Object>> customerPayInfo(@Param("id") String id);

    List<Map<String,Object>> CustomerNEWSignInfo_Select(String customerID);
}
