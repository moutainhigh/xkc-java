package com.tahoecn.xkc.mapper.customer;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.customer.BCustomerfiltergroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-08
 */
public interface BCustomerfiltergroupMapper extends BaseMapper<BCustomerfiltergroup> {

    List<Map<String, Object>> groupList(@Param("JobCode") String jobCode, @Param("ProjectID")String projectID, @Param("UserID")String userID);
    /**
	 * 客户筛选分组列表
	 */
	List<Map<String, Object>> mCustomerFilterGroupList_Select(Map<String, Object> paramMap);
}
