package com.tahoecn.xkc.controller.webapi;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.ResponseMessage;
import com.tahoecn.xkc.model.rule.BRemindchannel;
import com.tahoecn.xkc.service.rule.IBRemindchannelService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YYY
 * @since 2019-06-25
 */
@RestController
@RequestMapping("/bRemindchannel")
public class BRemindchannelController extends TahoeBaseController {
    @Autowired
    private IBRemindchannelService iBRemindchannelService;

    @ApiImplicitParams(
            {@ApiImplicitParam(name = "projectId", value = "项目Id", required = true, dataType = "String"),
                    @ApiImplicitParam(name = "channelSource", value = "渠道来源0.推荐 1.自有 2.分销", required = true, dataType = "int")})
    @ApiOperation(value = "渠道消息提醒查询接口", notes = "渠道消息提醒查询接口")
    @RequestMapping(value = "/ChannelMessageRemindDetail_Select", method = { RequestMethod.GET })
    public ResponseMessage ChannelMessageRemindDetail_Select(@RequestParam(required = true) String projectId,@RequestParam(required = true) String channelSource) {
        QueryWrapper<BRemindchannel> queryWrapper = new QueryWrapper<BRemindchannel>();
        queryWrapper.eq(StringUtils.isNotBlank(projectId), "ProjectID", projectId);
        queryWrapper.eq(StringUtils.isNotBlank(channelSource), "ChannelSource", channelSource);
        queryWrapper.eq("IsDel",0);
        queryWrapper.eq("Status",1);
        BRemindchannel remindchannel = (BRemindchannel)iBRemindchannelService.getObj(queryWrapper);
        return ResponseMessage.ok(remindchannel);
    }
}
