package com.tahoecn.xkc.mapper.risk;

import com.tahoecn.xkc.model.risk.BCustomerattach;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2020-05-15
 */
public interface BCustomerattachMapper extends BaseMapper<BCustomerattach> {

    Map<String, Object> fkJointName(@Param("id")String id);

}
