package com.tahoecn.xkc.mapper.customer;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tahoecn.xkc.model.customer.VABrokerMycustomers;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 1 Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-02
 */
public interface VABrokerMycustomersMapper extends BaseMapper<VABrokerMycustomers> {

    List<Map<String, Object>> mGetMyCustomers_Select(IPage page,@Param("Where")String where, @Param("Order") String order,  @Param("Search")String search);
    VABrokerMycustomers selectClue(@Param("clueId") String clueId);
	List<VABrokerMycustomers> selectMyCustomer(@Param("reportUserId") String reportUserId, @Param("order") String order, @Param("nameOrMobile") String nameOrMobile, @Param("status") String status, @Param("projectName") String projectName);
	
}
