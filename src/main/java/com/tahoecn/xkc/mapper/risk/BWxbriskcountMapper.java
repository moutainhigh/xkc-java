package com.tahoecn.xkc.mapper.risk;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.risk.BWxbriskcount;
import com.tahoecn.xkc.model.risk.vo.WxbRiskStatisticalPageVO;

import java.util.List;
import java.util.Map;

public interface BWxbriskcountMapper extends BaseMapper<BWxbriskcount> {

    BWxbriskcount one();

    List<Map> pageList(WxbRiskStatisticalPageVO vo);

    Long pageCount(WxbRiskStatisticalPageVO vo);

}
