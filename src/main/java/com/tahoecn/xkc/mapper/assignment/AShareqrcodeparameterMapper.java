package com.tahoecn.xkc.mapper.assignment;

import com.tahoecn.xkc.model.assignment.AShareqrcodeparameter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 由于小程序二维码参数长度限制在32个字节 所有先把要携带的参数保存到本表 Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2020-01-06
 */
public interface AShareqrcodeparameterMapper extends BaseMapper<AShareqrcodeparameter> {

}
