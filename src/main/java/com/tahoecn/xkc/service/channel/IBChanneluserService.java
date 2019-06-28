package com.tahoecn.xkc.service.channel;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.channel.BChanneluser;
import com.tahoecn.xkc.model.dto.ChannelDto;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-06-25
 */
public interface IBChanneluserService extends IService<BChanneluser> {

    List<Map<String, String>> AgenApproverList();

}
