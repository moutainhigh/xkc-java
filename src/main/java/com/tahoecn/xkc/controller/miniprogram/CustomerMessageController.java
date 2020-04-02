package com.tahoecn.xkc.controller.miniprogram;


import com.tahoecn.core.json.JSONResult;
import com.tahoecn.log.Log;
import com.tahoecn.log.LogFactory;
import com.tahoecn.xkc.common.utils.ResultUtil;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.model.miniprogram.vo.CustomerVO;
import com.tahoecn.xkc.model.miniprogram.vo.RelationshipVO;
import com.tahoecn.xkc.service.miniprogram.ICustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 思为小程序客户信息获取
 * </p>
 *
 * @author zhaoyan
 * @since 2020-04-01
 */
@Api(tags = "客户信息获取", value = "客户信息获取")
@RestController
@RequestMapping("/customer")
public class CustomerMessageController extends TahoeBaseController {
    /**
     * 日志记录
     */
    private static Log logger = LogFactory.get(CustomerMessageController.class);

    @Resource
    private ICustomerService customerService;

    @ApiOperation(value = "获取客户列表", notes = "获取客户列表")
    @RequestMapping(value = "/getCustomerList", method = {RequestMethod.POST})
    public JSONResult getCustomerList(HttpServletRequest request, @RequestBody RelationshipVO relationshipVO) {
        try {
            return customerService.getCustomerList(request, relationshipVO);
        } catch (Exception e) {
            //记录错误日志
            logger.error(e);
            e.printStackTrace();
            return ResultUtil.getFailJsonResult();
        }
    }

    @ApiOperation(value = "获取客户详情", notes = "获取客户详情")
    @RequestMapping(value = "/getCustomerDetail", method = {RequestMethod.POST})
    public JSONResult getCustomerDetail(HttpServletRequest request, @RequestBody CustomerVO customerVO) {
        try {
            return customerService.getCustomerDetail(request, customerVO.getOpportunityId());
        } catch (Exception e) {
            //记录错误日志
            logger.error(e);
            e.printStackTrace();
            return ResultUtil.getFailJsonResult();
        }
    }

    @ApiOperation(value = "获取客户跟进记录", notes = "获取客户跟进记录")
    @RequestMapping(value = "/getCustomerFollowRecord", method = {RequestMethod.POST})
    public JSONResult getCustomerFollowRecord(HttpServletRequest request, @RequestBody CustomerVO customerVO) {
        try {
            return customerService.getCustomerFollowRecord(request, customerVO.getOpportunityId());
        } catch (Exception e) {
            //记录错误日志
            logger.error(e);
            e.printStackTrace();
            return ResultUtil.getFailJsonResult();
        }
    }
}
