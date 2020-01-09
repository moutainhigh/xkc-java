package com.tahoecn.xkc.controller.app;

import com.tahoecn.log.Log;
import com.tahoecn.log.LogFactory;
import com.tahoecn.xkc.common.constants.AppAssignmentConstants;
import com.tahoecn.xkc.converter.ResponseMessage;
import com.tahoecn.xkc.model.salegroup.BSalesuser;
import com.tahoecn.xkc.model.user.CWxuser;
import com.tahoecn.xkc.model.vo.APPAssignmentVO;
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
    public ResponseMessage selectSharePoolListForAssignment(@RequestBody APPAssignmentVO appAssignmentVO) {
        if (null == appAssignmentVO) {
            return ResponseMessage.error("未传登录人信息！", 410);
        }
        // 当前登录人ID
        String thisUserId = appAssignmentVO.getUserId();
        // 项目ID
        String projectId = appAssignmentVO.getProjectId();
        // 当前登录人的jobId
        String jobId = appAssignmentVO.getJobId();
        // 参数校验
        if (StringUtils.isBlank(thisUserId) || StringUtils.isBlank(projectId) || StringUtils.isBlank(jobId)) {
            return ResponseMessage.error("未获取到登录人信息！", 410);
        }
        // 判断只有当前登录人的jobID为  案场负责人  或者  自渠负责人  才处理，否则不处理
        if (!AppAssignmentConstants.PROPERTY_LEADER_ID.equals(jobId) && !AppAssignmentConstants.SINCE_THE_CANAL_LEADER_ID.equals(jobId)) {
            return ResponseMessage.error("当前登录人非案场负责人且非自渠负责人！", 420);
        }
        // 业务逻辑操作
        try {
            // 查询到返回结果（待分配的假报备客户）
            List<CWxuser> wxuserList = appAssignmentService.selectSharePoolListForAssignment(thisUserId, projectId, jobId);
            return ResponseMessage.ok(wxuserList);
        } catch (RuntimeException e) {
            e.printStackTrace();
            log.error("query false report customer list is RuntimeException : " + e.getMessage());
            return ResponseMessage.error(e.getMessage(), 430);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("query false report customer list is Error : " + e);
            return ResponseMessage.error("查询假报备客户列表错误！", 400);
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
    public ResponseMessage selectPropertyOrSinceTheCanalList(@RequestBody APPAssignmentVO appAssignmentVO) {
        if (null == appAssignmentVO) {
            return ResponseMessage.error("未传登录人信息！", 410);
        }
        // 当前登录人ID
        String thisUserId = appAssignmentVO.getUserId();
        // 项目ID
        String projectId = appAssignmentVO.getProjectId();
        // 当前登录人的jobId
        String jobId = appAssignmentVO.getJobId();
        // 参数校验
        if (StringUtils.isBlank(thisUserId) || StringUtils.isBlank(projectId) || StringUtils.isBlank(jobId)) {
            return ResponseMessage.error("未获取到登录人信息！", 410);
        }
        // 判断只有当前登录人的jobID为  案场负责人  或者  自渠负责人  才处理，否则不处理
        if (!AppAssignmentConstants.PROPERTY_LEADER_ID.equals(jobId) && !AppAssignmentConstants.SINCE_THE_CANAL_LEADER_ID.equals(jobId)) {
            return ResponseMessage.error("当前登录人非案场负责人且非自渠负责人！", 420);
        }
        // 业务逻辑操作
        try {
            // 查询顾问列表
            List<BSalesuser> salesuserList = appAssignmentService.selectPropertyOrSinceTheCanalList(thisUserId, projectId, jobId);
            return ResponseMessage.ok(salesuserList);
        } catch (RuntimeException e) {
            e.printStackTrace();
            log.error("query property or since the canal list is RuntimeException : " + e.getMessage());
            return ResponseMessage.error(e.getMessage(), 430);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("query property or since the canal list is Error : " + e);
            return ResponseMessage.error("查询假报备客户列表错误！", 400);
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
    public ResponseMessage savePropertyOrSinceTheCanal(@RequestBody APPAssignmentVO appAssignmentVO) {
        if (null == appAssignmentVO || CollectionUtils.isEmpty(appAssignmentVO.getSharePoolVOList()) || StringUtils.isBlank(appAssignmentVO.getAdviserId())) {
            return ResponseMessage.error("参数为空！", 440);
        }
        // 当前登录人ID
        String thisUserId = appAssignmentVO.getUserId();
        // 项目ID
        String projectId = appAssignmentVO.getProjectId();
        // 当前登录人的jobId
        String jobId = appAssignmentVO.getJobId();
        // 参数校验
        if (StringUtils.isBlank(thisUserId) || StringUtils.isBlank(projectId) || StringUtils.isBlank(jobId)) {
            return ResponseMessage.error("未获取到登录人信息！", 410);
        }
        // 判断只有当前登录人的jobID为  案场负责人  或者  自渠负责人  才处理，否则不处理
        if (!AppAssignmentConstants.PROPERTY_LEADER_ID.equals(jobId) && !AppAssignmentConstants.SINCE_THE_CANAL_LEADER_ID.equals(jobId)) {
            return ResponseMessage.error("当前登录人非案场负责人且非自渠负责人！", 420);
        }
        // 业务逻辑操作
        try {
            // 分配成功后的假报备客户sharepool表id集合
            List<String> sharePoolIdList = appAssignmentService.savePropertyOrSinceTheCanal(thisUserId, projectId, jobId, appAssignmentVO.getSharePoolVOList(), appAssignmentVO.getAdviserId());
            log.info("save property or since the canal is success, the sharepoolIdList is : {} " + sharePoolIdList);
            return ResponseMessage.ok(sharePoolIdList);
        } catch (RuntimeException e) {
            e.printStackTrace();
            log.error("save property or since the canal is RuntimeException : " + e.getMessage());
            return ResponseMessage.error(e.getMessage(), 430);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("save property or since the canal is Error : " + e);
            return ResponseMessage.error("查询假报备客户列表错误！", 400);
        }
    }
}
