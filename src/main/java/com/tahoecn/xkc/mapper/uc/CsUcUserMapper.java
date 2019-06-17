package com.tahoecn.xkc.mapper.uc;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.CsUcUser;
import com.tahoecn.xkc.model.vo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * UC用户信息表 Mapper 接口
 * </p>
 *
 * @author zghw
 * @since 2018-11-15
 */
public interface CsUcUserMapper extends BaseMapper<CsUcUser> {

    List<UserVo> findUserByOrgId(@Param("orgId") String orgId);
    List<UserVo> findUsersByUsername(@Param("username") String username,@Param("usercode") String usercode);
}
