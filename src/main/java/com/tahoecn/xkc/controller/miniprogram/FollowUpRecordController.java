package com.tahoecn.xkc.controller.miniprogram;


import com.tahoecn.core.json.JSONResult;
import com.tahoecn.xkc.common.enums.TipsEnum;
import com.tahoecn.xkc.common.utils.ResultUtil;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.model.miniprogram.vo.followrecord.ChannelRecoerdVO;
import com.tahoecn.xkc.model.miniprogram.vo.followrecord.CourtCaseRecordVO;
import com.tahoecn.xkc.service.miniprogram.IFollowUpRecordService;
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
 * 思为小程序跟进记录
 * </p>
 *
 * @author zhaoyan
 * @since 2020-04-08
 */
@Api(tags = "跟进记录", value = "跟进记录")
@RestController
@RequestMapping("/followUpRecord")
public class FollowUpRecordController extends TahoeBaseController {

    @Resource
    private IFollowUpRecordService followUpRecordService;

    /**
     * 案场跟进记录
     *
     * @param courtCaseRecordVO
     * @return
     */
    @ApiOperation(value = "案场跟进记录", notes = "案场跟进记录")
    @RequestMapping(value = "/courtCaseRecord", method = RequestMethod.POST)
    public JSONResult courtCaseRecord(HttpServletRequest request, @RequestBody CourtCaseRecordVO courtCaseRecordVO) {
        try {
            return followUpRecordService.courtCaseRecord(request, courtCaseRecordVO);
        } catch (Exception e) {
            //记录错误日志
            e.printStackTrace();
            return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), e.getMessage());
        }
    }

    /**
     * 自渠跟进记录
     *
     * @param channelRecoerdVO
     * @return
     */
    @ApiOperation(value = "自渠跟进记录", notes = "自渠跟进记录")
    @RequestMapping(value = "/channelRecord", method = RequestMethod.POST)
    public JSONResult channelRecord(HttpServletRequest request, @RequestBody ChannelRecoerdVO channelRecoerdVO) {
        try {
            return followUpRecordService.channelRecord(request, channelRecoerdVO);
        } catch (Exception e) {
            //记录错误日志
            e.printStackTrace();
            return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), e.getMessage());
        }
    }
}


