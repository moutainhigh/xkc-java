package com.tahoecn.xkc.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.sys.BVerificationcode;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-04
 */
public interface BVerificationcodeMapper extends BaseMapper<BVerificationcode> {

    BVerificationcode checkAuthCode(@Param("mobile") String mobile);
}
