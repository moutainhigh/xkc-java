package com.tahoecn.xkc.service.assignment;

import com.tahoecn.xkc.model.salegroup.BSalesuser;
import com.tahoecn.xkc.model.user.CWxuser;
import com.tahoecn.xkc.model.vo.AppAssignmentPrameterVO;

import java.util.List;

/**
 * @ProJectName: xkc
 * @Author: zwc  zwc_503@163.com
 * @CreateTime: 2020-01-06 15:18
 * @Description: //TODO 移动端指定分配假报备接口
 **/
public interface AppAssignmentService {

    /*
     * @Author zwc   zwc_503@163.com
     * @Date 15:48 2020/1/6
     * @Param
     * @return
     * @Version 1.0
     * @Description //TODO 查询到待分配的假报备客户
     **/
    List<CWxuser> selectSharePoolListForAssignment(String thisUserId, String projectId, String jobId);

    /*
     * @Author zwc   zwc_503@163.com
     * @Date 10:06 2020/1/7
     * @Param
     * @return
     * @Version 1.0
     * @Description //TODO  查询案场负责人下的或者自渠负责人下的顾问列表
     **/
    List<BSalesuser> selectPropertyOrSinceTheCanalList(String thisUserId, String projectId, String jobId);

    /*
     * @Author zwc   zwc_503@163.com
     * @Date 10:34 2020/1/7
     * @Param
     * @return
     * @Version 1.0
     * @Description //TODO  分配客户给顾问
     **/
    List<String> savePropertyOrSinceTheCanal(String thisUserId, String projectId, String jobId, List<AppAssignmentPrameterVO> sharePoolVOList, String adviserId);
}