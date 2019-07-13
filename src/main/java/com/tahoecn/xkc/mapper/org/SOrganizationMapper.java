package com.tahoecn.xkc.mapper.org;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tahoecn.xkc.model.org.SOrganization;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-06
 */
public interface SOrganizationMapper extends BaseMapper<SOrganization> {
    void userOrgInsert(@Param("UserID") String UserID, @Param("ProjectID") String ProjectID);

    IPage<Map<String,Object>> SystemOrganization_Select(IPage page,@Param("authCompanyID") String authCompanyID, @Param("orgID")String orgID,
                                                        @Param("productID")String productID, @Param("pid")String pid,
                                                        @Param("status")String status);

    List<Map<String, Object>> SystemOrganizationChec_Select(@Param("pid")String pid);

}
