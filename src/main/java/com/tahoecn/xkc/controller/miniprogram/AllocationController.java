package com.tahoecn.xkc.controller.miniprogram;


import com.tahoecn.core.json.JSONResult;
import com.tahoecn.xkc.common.enums.TipsEnum;
import com.tahoecn.xkc.common.utils.ResultUtil;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.model.miniprogram.vo.allocation.ChannelVO;
import com.tahoecn.xkc.model.miniprogram.vo.allocation.CourtCaseVO;
import com.tahoecn.xkc.service.miniprogram.IAllocationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 思为小程序客户分配
 * </p>
 *
 * @author zhaoyan
 * @since 2020-04-08
 */
@Api(tags = "客户分配", value = "客户分配")
@RestController
@RequestMapping("/allocation")
public class AllocationController extends TahoeBaseController {

    @Resource
    private IAllocationService allocationService;

    /**
     * 案场分配客户
     *
     * @param courtCase
     * @return
     */
    @ApiOperation(value = "案场分配客户", notes = "案场分配客户")
    @RequestMapping(value = "/courtCase", method = RequestMethod.POST)
    public JSONResult courtCase(HttpServletRequest request, @RequestBody CourtCaseVO courtCase) {
        try {
            return allocationService.courtCase(request, courtCase);
        } catch (Exception e) {
            //记录错误日志
            e.printStackTrace();
            return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), e.getMessage());
        }
    }

    /**
     * 自渠分配客户
     *
     * @param channelVO
     * @return
     */
    @ApiOperation(value = "自渠分配客户", notes = "自渠分配客户")
    @RequestMapping(value = "/channel", method = RequestMethod.POST)
    public JSONResult channel(HttpServletRequest request, @RequestBody ChannelVO channelVO) {
        try {
            return allocationService.channel(request, channelVO);
        } catch (Exception e) {
            //记录错误日志
            e.printStackTrace();
            return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), e.getMessage());
        }
    }
}
