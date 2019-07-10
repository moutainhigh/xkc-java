package com.tahoecn.xkc.mapper.job;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.job.SJobs;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-04
 */
public interface SJobsMapper extends BaseMapper<SJobs> {
    void userJobInsert(@Param("UserID") String UserID, @Param("RoleID") String RoleID,@Param("ProjectID") String ProjectID);
}
