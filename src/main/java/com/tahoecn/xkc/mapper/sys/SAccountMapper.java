package com.tahoecn.xkc.mapper.sys;

import com.tahoecn.xkc.model.sys.SAccount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-06-21
 */
public interface SAccountMapper extends BaseMapper<SAccount> {

    HashMap<String,String> getUserJob(@Param("userName") String userName);
}
