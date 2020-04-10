package com.tahoecn.xkc.service.miniprogram;

import com.tahoecn.core.json.JSONResult;
import com.tahoecn.xkc.model.miniprogram.vo.customerreport.MBrokerReportVO;
import com.tahoecn.xkc.model.miniprogram.vo.customerreport.MCustomerPotentialVO;
import com.tahoecn.xkc.model.miniprogram.vo.customerreport.MDynatownCustomerVO;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 思为小程序客户报备服务类
 * </p>
 *
 * @author zhaoyan
 * @since 2020-04-07
 */
public interface IMiniprogramService {

    /**
     * 获取FormSessionId
     *
     * @param request
     * @param map
     * @return
     * @throws Exception
     */
    public JSONResult getFormSessionId(HttpServletRequest request, Map<String, Object> map) throws Exception;

    /**
     * 置业顾问报备客户
     *
     * @param request
     * @param mDynatownCustomerVO
     * @return
     * @throws Exception
     */
    public JSONResult dynatownReport(HttpServletRequest request, MDynatownCustomerVO mDynatownCustomerVO) throws Exception;

    /**
     * 自渠人员报备客户
     *
     * @param request
     * @param mCustomerPotentialVO
     * @return
     * @throws Exception
     */
    public JSONResult customerPotentialReport(HttpServletRequest request, MCustomerPotentialVO mCustomerPotentialVO) throws Exception;

    /**
     * 自由经纪人/中介同行报备客户
     *
     * @param request
     * @param mBrokerReportVO
     * @return
     * @throws Exception
     */
    public JSONResult freedomReport(HttpServletRequest request, MBrokerReportVO mBrokerReportVO) throws Exception;
}
