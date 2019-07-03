package com.tahoecn.xkc.mapper.uc;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.CsSendtoall;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 期初发送短信通知表 Mapper 接口
 * </p>
 *
 * @author zghw
 * @since 2018-11-29
 */
public interface CsSendtoallMapper extends BaseMapper<CsSendtoall> {

	List<HashMap<String,Object>> getSendToAll();

}
