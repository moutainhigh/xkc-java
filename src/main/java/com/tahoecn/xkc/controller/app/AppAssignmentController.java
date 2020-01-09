package com.tahoecn.xkc.controller.app;

import com.alibaba.fastjson.JSON;
import com.tahoecn.log.Log;
import com.tahoecn.log.LogFactory;
import com.tahoecn.xkc.common.constants.AppAssignmentConstants;
import com.tahoecn.xkc.config.MyJPushClient;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.assignment.BProjectsharemanualconfigure;
import com.tahoecn.xkc.model.salegroup.BSalesuser;
import com.tahoecn.xkc.model.user.CWxuser;
import com.tahoecn.xkc.model.vo.APPAssignmentVO;
import com.tahoecn.xkc.model.vo.APPSendJPushVO;
import com.tahoecn.xkc.service.assignment.AppAssignmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ProJectName: xkc
 * @Author: zwc  zwc_503@163.com
 * @CreateTime: 2020-01-06 15:13
 * @Description: //TODO 移动端指定分配假报备客户
 **/
@Api(tags = "移动端指定分配模块", value = "安卓和ios端的指定分配页面模块")
@RestController
@RequestMapping(value = "/app/assignment")
public class AppAssignmentController {

    private static final Log log = LogFactory.get();

    @Autowired
    private AppAssignmentService appAssignmentService;

    @Autowired
    private MyJPushClient myJPushClient;

    /*
     * @Author zwc   zwc_503@163.com
     * @Date 15:23 2020/1/6
     * @Param
     * @return
     * @Version 1.0
     * @Description //TODO 假报备客户查询
     **/
    @ApiOperation(value = "假报备客户查询", httpMethod = "POST", notes = "假报备客户查询接口")
    @PostMapping(value = "/selectSharePoolListForAssignment")
    public Result selectSharePoolListForAssignment(@RequestBody APPAssignmentVO appAssignmentVO) {
        if (null == appAssignmentVO) {
            return Result.errormsg(410, "未传登录人信息！");
        }
        // 当前登录人ID
        String thisUserId = appAssignmentVO.getUserId();
        // 项目ID
        String projectId = appAssignmentVO.getProjectId();
        // 当前登录人的jobId
        String jobId = appAssignmentVO.getJobId();
        // 参数校验
        if (StringUtils.isBlank(thisUserId) || StringUtils.isBlank(projectId) || StringUtils.isBlank(jobId)) {
            return Result.errormsg(410, "未获取到登录人信息！");
        }
        // 判断只有当前登录人的jobID为  案场负责人  或者  自渠负责人  才处理，否则不处理
        if (!AppAssignmentConstants.PROPERTY_LEADER_ID.equals(jobId) && !AppAssignmentConstants.SINCE_THE_CANAL_LEADER_ID.equals(jobId)) {
            return Result.errormsg(420, "当前登录人非案场负责人且非自渠负责人！");
        }
        // 业务逻辑操作
        try {
            // 查询到返回结果（待分配的假报备客户）
            List<CWxuser> wxuserList = appAssignmentService.selectSharePoolListForAssignment(thisUserId, projectId, jobId);
            return Result.ok(wxuserList);
        } catch (RuntimeException e) {
            e.printStackTrace();
            log.error("query false report customer list is RuntimeException : " + e.getMessage());
            return Result.errormsg(430, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("query false report customer list is Error : " + e);
            return Result.errormsg(400, "查询假报备客户列表错误！");
        }
    }

    /*
     * @Author zwc   zwc_503@163.com
     * @Date 9:54 2020/1/7
     * @Param
     * @return
     * @Version 1.0
     * @Description //TODO 查询案场负责人下的或者自渠负责人下的顾问列表
     **/
    @ApiOperation(value = "查询顾问列表", httpMethod = "POST", notes = "查询案场负责人或者自渠负责人下的顾问列表")
    @PostMapping(value = "/selectPropertyOrSinceTheCanalList")
    public Result selectPropertyOrSinceTheCanalList(@RequestBody APPAssignmentVO appAssignmentVO) {
        if (null == appAssignmentVO) {
            return Result.errormsg(410, "未传登录人信息！");
        }
        // 当前登录人ID
        String thisUserId = appAssignmentVO.getUserId();
        // 项目ID
        String projectId = appAssignmentVO.getProjectId();
        // 当前登录人的jobId
        String jobId = appAssignmentVO.getJobId();
        // 参数校验
        if (StringUtils.isBlank(thisUserId) || StringUtils.isBlank(projectId) || StringUtils.isBlank(jobId)) {
            return Result.errormsg(410, "未传登录人信息！");
        }
        // 判断只有当前登录人的jobID为  案场负责人  或者  自渠负责人  才处理，否则不处理
        if (!AppAssignmentConstants.PROPERTY_LEADER_ID.equals(jobId) && !AppAssignmentConstants.SINCE_THE_CANAL_LEADER_ID.equals(jobId)) {
            return Result.errormsg(420, "当前登录人非案场负责人且非自渠负责人！");
        }
        // 业务逻辑操作
        try {
            // 查询顾问列表
            List<BSalesuser> salesuserList = appAssignmentService.selectPropertyOrSinceTheCanalList(thisUserId, projectId, jobId);
            return Result.ok(salesuserList);
        } catch (RuntimeException e) {
            e.printStackTrace();
            log.error("query property or since the canal list is RuntimeException : " + e.getMessage());
            return Result.errormsg(430, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("query property or since the canal list is Error : " + e);
            return Result.errormsg(400, "查询假报备客户列表错误！");
        }
    }

    /*
     * @Author zwc   zwc_503@163.com
     * @Date 10:28 2020/1/7
     * @Param
     * @return
     * @Version 1.0
     * @Description //TODO 分配客户给顾问
     **/
    @ApiOperation(value = "分配客户给顾问", notes = "分配客户给顾问")
    @RequestMapping(value = "/savePropertyOrSinceTheCanal", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result savePropertyOrSinceTheCanal(@RequestBody APPAssignmentVO appAssignmentVO) {
        if (null == appAssignmentVO || CollectionUtils.isEmpty(appAssignmentVO.getSharePoolVOList()) || StringUtils.isBlank(appAssignmentVO.getAdviserId())) {
            return Result.errormsg(440, "参数为空！");
        }
        // 当前登录人ID
        String thisUserId = appAssignmentVO.getUserId();
        // 项目ID
        String projectId = appAssignmentVO.getProjectId();
        // 当前登录人的jobId
        String jobId = appAssignmentVO.getJobId();
        // 参数校验
        if (StringUtils.isBlank(thisUserId) || StringUtils.isBlank(projectId) || StringUtils.isBlank(jobId)) {
            return Result.errormsg(410, "未传登录人信息！");
        }
        // 判断只有当前登录人的jobID为  案场负责人  或者  自渠负责人  才处理，否则不处理
        if (!AppAssignmentConstants.PROPERTY_LEADER_ID.equals(jobId) && !AppAssignmentConstants.SINCE_THE_CANAL_LEADER_ID.equals(jobId)) {
            return Result.errormsg(420, "当前登录人非案场负责人且非自渠负责人！");
        }
        // 业务逻辑操作
        try {
            // 分配成功后的假报备客户sharepool表id集合
            List<String> sharePoolIdList = appAssignmentService.savePropertyOrSinceTheCanal(thisUserId, projectId, jobId, appAssignmentVO.getSharePoolVOList(), appAssignmentVO.getAdviserId());
            log.info("save property or since the canal is success, the sharepoolIdList is : {} " + sharePoolIdList);
            return Result.ok(sharePoolIdList);
        } catch (RuntimeException e) {
            e.printStackTrace();
            log.error("save property or since the canal is RuntimeException : " + e.getMessage());
            return Result.errormsg(430, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("save property or since the canal is Error : " + e);
            return Result.errormsg(400, "查询假报备客户列表错误！");
        }
    }

    /*
     * @Author zwc   zwc_503@163.com
     * @Date 16:50 2020/1/7
     * @Param
     * @return
     * @Version 1.0
     * @Description //TODO 查看是否显示 ‘潜客手动分配’ 节点
     **/
    @ApiOperation(value = "是否显示分配节点", notes = "查看是否显示 ‘潜客手动分配’ 节点")
    @RequestMapping(value = "/checkAssignmentFlag", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result checkAssignmentFlag(@RequestBody APPAssignmentVO appAssignmentVO) {
        if (null == appAssignmentVO) {
            return Result.errormsg(440, "参数为空！");
        }
        // 当前登录人ID
        String thisUserId = appAssignmentVO.getUserId();
        // 项目ID
        String projectId = appAssignmentVO.getProjectId();
        // 当前登录人的jobId
        String jobId = appAssignmentVO.getJobId();
        // 参数校验
        if (StringUtils.isBlank(thisUserId) || StringUtils.isBlank(projectId) || StringUtils.isBlank(jobId)) {
            return Result.errormsg(410, "未传登录人信息！");
        }
        // 判断只有当前登录人的jobID为  案场负责人  或者  自渠负责人  才处理，否则不处理
        if (!AppAssignmentConstants.PROPERTY_LEADER_ID.equals(jobId) && !AppAssignmentConstants.SINCE_THE_CANAL_LEADER_ID.equals(jobId)) {
            return Result.errormsg(420, "当前登录人非案场负责人且非自渠负责人！");
        }
        // 业务逻辑操作
        try {
            // 查看是否显示 ‘潜客手动分配’ 节点
            List<BProjectsharemanualconfigure> projectsharemanualconfigureList = appAssignmentService.checkAssignmentFlag(thisUserId, projectId, jobId);
            if (CollectionUtils.isEmpty(projectsharemanualconfigureList)) {
                return Result.ok(false);
            }
            return Result.ok(true);
        } catch (RuntimeException e) {
            e.printStackTrace();
            log.error("query projectShareManualConfigureList is RuntimeException : " + e.getMessage());
            return Result.errormsg(430, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("query projectShareManualConfigureList is Error : " + e);
            return Result.errormsg(400, "校验出现错误！");
        }
    }

    /*
     * @Author zwc   zwc_503@163.com
     * @Date 10:50 2020/1/8
     * @Param
     * @return
     * @Version 1.0
     * @Description //TODO  极光推送接口，提供给.NET端调用
     **/
    @ApiOperation(value = "推送所有安卓和IOS", notes = "发送给平台全部的Andriod和IOS用户数据")
    @PostMapping(value = "/sendJPushContentAndriodAndIOS")
    public Result sendJPushContentAndriodAndIOS(@RequestBody APPSendJPushVO appSendJPushVO) {
        if (null == appSendJPushVO) {
            return Result.errormsg(440, "参数为空！");
        }
        log.info("send JPush content andriod and IOS paramters is : {}", appSendJPushVO);
        // 当前登录人ID
        String thisUserId = appSendJPushVO.getUserId();
        // 项目ID
        String projectId = appSendJPushVO.getProjectId();
        // 当前登录人的jobId
        String jobId = appSendJPushVO.getJobId();
        // 通知内容标题
        String notificationTitle = appSendJPushVO.getNotificationTitle();
        // 消息标题
        String msgTitle = appSendJPushVO.getMsgTitle();
        // 消息内容
        String msgContent = appSendJPushVO.getMsgContent();
        // 扩展字段
        String extras = appSendJPushVO.getExtras();
        // 参数校验
        if (StringUtils.isBlank(thisUserId) || StringUtils.isBlank(projectId) || StringUtils.isBlank(jobId) ||
                StringUtils.isBlank(notificationTitle) || StringUtils.isBlank(msgTitle) || StringUtils.isBlank(msgContent) || StringUtils.isBlank(extras)) {
            return Result.errormsg(410, "未传登录人信息！");
        }
        // 业务逻辑操作
        try {
            // 调用推送全部 Andriod and IOS 接口
            Object result = myJPushClient.sendToAll(notificationTitle, msgTitle, msgContent, extras);
            if (result instanceof String) {
                // 推送失败
                return Result.errormsg(420, result.toString());
            } else if (result instanceof Integer) {
                return Result.ok("推送成功！");
            } else {
                return Result.errormsg(420, JSON.toJSONString(result));
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            log.error("send JPush content andriod and IOS is RuntimeException : " + e.getMessage());
            return Result.errormsg(430, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("send JPush content andriod and IOS is Error : " + e);
            return Result.errormsg(400, "校验出现错误！");
        }
    }
}
