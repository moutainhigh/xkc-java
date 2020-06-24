package com.tahoecn.xkc.service.risk;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.risk.BWxbriskcount;
import com.tahoecn.xkc.model.risk.vo.WxbRiskInfoPageVO;
import com.tahoecn.xkc.model.risk.vo.WxbRiskStatisticalPageVO;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author YYY
 * @since 2020-06-17
 */

public interface IBWxbriskcountService extends IService<BWxbriskcount> {

    IPage statistical(WxbRiskStatisticalPageVO vo);

    Map list(WxbRiskInfoPageVO vo);

    Map label(WxbRiskInfoPageVO vo);
}
