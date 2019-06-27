package com.tahoecn.xkc.service.channel;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.channel.BChannelorg;
import com.tahoecn.xkc.model.dto.ChannelDto;

import java.util.HashMap;
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
public interface IBChannelorgService extends IService<BChannelorg> {

    List<ChannelDto> getList(Page page,String ProjectID,String sqlWhere);
}
