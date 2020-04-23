package com.tahoecn.xkc.controller.miniprogram;

import com.tahoecn.core.json.JSONResult;
import com.tahoecn.xkc.common.enums.TipsEnum;
import com.tahoecn.xkc.common.utils.ResultUtil;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.model.miniprogram.vo.customerreport.MBrokerReportVO;
import com.tahoecn.xkc.model.miniprogram.vo.customerreport.MCustomerPotentialVO;
import com.tahoecn.xkc.model.miniprogram.vo.customerreport.MDynatownCustomerVO;
import com.tahoecn.xkc.service.miniprogram.IMiniprogramService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * <p>
 * 思为小程序客户报备
 * </p>
 *
 * @author zhaoyan
 * @since 2020-04-01
 */
@Api(tags = "客户报备", value = "客户报备")
@RestController
@RequestMapping("/report")
public class MiniprogramController extends TahoeBaseController {

    @Resource
    private IMiniprogramService miniprogramService;

    /**
     * 获取报备FormSessionId
     *
     * @param map
     * @return
     */
    @ApiOperation(value = "获取报备FormSessionId", notes = "获取报备FormSessionId")
    @RequestMapping(value = "/getFormSessionId", method = RequestMethod.POST)
    public JSONResult getFormSessionId(HttpServletRequest request, @RequestBody Map<String, Object> map) {
        try {
            return miniprogramService.getFormSessionId(request, map);
        } catch (Exception e) {
            //记录错误日志
            e.printStackTrace();
            return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), e.getMessage());
        }
    }

    /**
     * 置业顾问报备客户
     *
     * @param mDynatownCustomerVO
     * @return
     */
    @ApiOperation(value = "置业顾问报备客户", notes = "置业顾问报备客户")
    @RequestMapping(value = "/dynatownReport", method = RequestMethod.POST)
    public JSONResult dynatownReport(HttpServletRequest request, @RequestBody MDynatownCustomerVO mDynatownCustomerVO) {
        try {
            return miniprogramService.dynatownReport(request, mDynatownCustomerVO);
        } catch (Exception e) {
            //记录错误日志
            e.printStackTrace();
            return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), e.getMessage());
        }
    }

    /**
     * 自渠人员报备客户
     *
     * @param mCustomerPotentialVO
     * @return
     */
    @ApiOperation(value = "自渠人员报备客户", notes = "自渠人员报备客户")
    @RequestMapping(value = "/customerPotentialReport", method = RequestMethod.POST)
    public JSONResult customerPotentialReport(HttpServletRequest request, @RequestBody MCustomerPotentialVO mCustomerPotentialVO) {
        try {
            return miniprogramService.customerPotentialReport(request, mCustomerPotentialVO);
        } catch (Exception e) {
            //记录错误日志
            e.printStackTrace();
            return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), e.getMessage());
        }
    }

    /**
     * 自由经纪人/中介同行报备客户
     *
     * @param mBrokerReportVO
     * @return
     */
    @ApiOperation(value = "自由经纪人/中介同行报备客户", notes = "自由经纪人/中介同行报备客户")
    @RequestMapping(value = "/freedomReport", method = RequestMethod.POST)
    public JSONResult freedomReport(HttpServletRequest request, @RequestBody MBrokerReportVO mBrokerReportVO) {
        try {
            return miniprogramService.freedomReport(request, mBrokerReportVO);
        } catch (Exception e) {
            //记录错误日志
            e.printStackTrace();
            return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), e.getMessage());
        }
    }
}
