package com.tahoecn.xkc.common.utils;

import com.tahoecn.core.date.DateUtil;
import com.tahoecn.xkc.model.risk.BRiskbatchlog;

import java.util.Date;
import java.util.UUID;

/**
 * @description: 风控批处理信息表生成utils
 * @author: 张晓东
 * @time: 2020/5/7 16:34
 */
public class RiskBatchLogUtils {

    /**
     * @description: 风控批处理信息开始阶段
     * @return:
     * @author: 张晓东
     * @time: 2020/5/7 16:36
     */
    public static BRiskbatchlog start(int riskType) {
        return new BRiskbatchlog() {{
            setId(UUID.randomUUID().toString());
            setRiskType(riskType);
            setStartTime(DateUtil.date());
            setStatus(0);
        }};
    }

    /**
     * @description: 风控批处理信息运行中阶段
     * @return:
     * @author: 张晓东
     * @time: 2020/5/7 16:36
     */

    public static BRiskbatchlog running(BRiskbatchlog bRiskbatchlog) {
        BRiskbatchlog clone = bRiskbatchlog.clone();
        clone.setStatus(1);
        return clone;
    }

    /**
     * @description: 风控批处理信息成功阶段
     * @return:
     * @author: 张晓东
     * @time: 2020/5/7 16:36
     */
    public static BRiskbatchlog success(BRiskbatchlog bRiskbatchlog, Date dataMaxTime) {
        BRiskbatchlog clone = bRiskbatchlog.clone();
        clone.setStatus(2);
        clone.setDataMaxTime(dataMaxTime);
        clone.setEndTime(DateUtil.date());
        return clone;
    }

    /**
     * @description: 风控批处理信息失败阶段
     * @return:
     * @author: 张晓东
     * @time: 2020/5/7 16:36
     */
    public static BRiskbatchlog failure(BRiskbatchlog bRiskbatchlog, String errorMsg) {
        BRiskbatchlog clone = bRiskbatchlog.clone();
        clone.setStatus(3);
        clone.setEndTime(DateUtil.date());
        clone.setErrorMsg(errorMsg);
        return clone;
    }


}
