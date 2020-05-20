package com.tahoecn.xkc.service.risk;

import com.tahoecn.core.json.JSONResult;
import com.tahoecn.xkc.model.risk.BWangxiaobao;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.risk.vo.WangXiaobaoVO;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2020-05-11
 */
public interface IBWangxiaobaoService extends IService<BWangxiaobao> {

    JSONResult record(HttpServletRequest request, WangXiaobaoVO vo);
}
