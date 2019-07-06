package com.tahoecn.xkc.mapper.customer;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tahoecn.xkc.model.customer.BCustomer;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-05
 */
public interface BCustomerMapper extends BaseMapper<BCustomer> {

    IPage<HashMap<String, Object>> customerChangePageList_Select(IPage page,@Param("projectID") String projectID, @Param("sqlWhere") String sqlWhere);
}
