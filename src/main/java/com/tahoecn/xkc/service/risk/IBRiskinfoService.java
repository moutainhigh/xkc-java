package com.tahoecn.xkc.service.risk;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.core.json.JSONResult;
import com.tahoecn.xkc.model.risk.BRiskinfo;
import com.tahoecn.xkc.model.risk.vo.TiskInfoFindVO;
import com.tahoecn.xkc.model.risk.vo.TiskInfoVO;
import com.tahoecn.xkc.model.risk.vo.TiskTreeFindVO;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author YYY
 * @since 2020-05-06
 */
public interface IBRiskinfoService extends IService<BRiskinfo> {

    /**
     * @description: 风控痛点树查询
     * @return:
     * @author: 张晓东
     * @time: 2020/5/21 9:34
     */
    List listTree(TiskTreeFindVO vo);


    /**
     * @description: 风控痛点列表查询
     * @return:
     * @author: 张晓东
     * @time: 2020/5/21 9:34
     */
    IPage list(TiskInfoFindVO vo);

    /**
     * @description: 查询痛点明细
     * @return:
     * @author: 张晓东
     * @time: 2020/5/22 9:21
     */
    JSONResult info(String id);

    /**
     * @description: 风控痛点列表导出
     * @return:
     * @author: 张晓东
     * @time: 2020/5/27 15:15
     */
    void listExport(TiskInfoFindVO vo, HttpServletResponse response) throws Exception;

    /**
     * @description: 风控痛点树导出
     * @return:
     * @author: 张晓东
     * @time: 2020/5/27 18:09
     */
    void listTreeExport(HttpServletResponse response, String regionalId, String cityId, String projectId, Date startTime, Date endTime) throws Exception;
}
