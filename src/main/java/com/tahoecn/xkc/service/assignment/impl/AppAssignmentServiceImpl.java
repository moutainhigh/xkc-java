package com.tahoecn.xkc.service.assignment.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.tahoecn.log.Log;
import com.tahoecn.log.LogFactory;
import com.tahoecn.xkc.common.constants.AppAssignmentConstants;
import com.tahoecn.xkc.mapper.assignment.ASharelogdetailMapper;
import com.tahoecn.xkc.mapper.assignment.AShareprojectMapper;
import com.tahoecn.xkc.mapper.assignment.AShareqrcodeparameterMapper;
import com.tahoecn.xkc.mapper.assignment.BProjectsharemanualconfigureMapper;
import com.tahoecn.xkc.mapper.customer.ASharepoolMapper;
import com.tahoecn.xkc.mapper.project.BProjectMapper;
import com.tahoecn.xkc.mapper.salegroup.BSalesgroupmemberMapper;
import com.tahoecn.xkc.mapper.salegroup.BSalesuserMapper;
import com.tahoecn.xkc.mapper.user.CWxuserMapper;
import com.tahoecn.xkc.model.assignment.ASharelogdetail;
import com.tahoecn.xkc.model.assignment.AShareproject;
import com.tahoecn.xkc.model.assignment.AShareqrcodeparameter;
import com.tahoecn.xkc.model.assignment.BProjectsharemanualconfigure;
import com.tahoecn.xkc.model.customer.ASharepool;
import com.tahoecn.xkc.model.project.BProject;
import com.tahoecn.xkc.model.salegroup.BSalesgroupmember;
import com.tahoecn.xkc.model.salegroup.BSalesuser;
import com.tahoecn.xkc.model.user.CWxuser;
import com.tahoecn.xkc.model.vo.AppAssignmentPrameterVO;
import com.tahoecn.xkc.service.assignment.AppAssignmentService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @ProJectName: xkc
 * @Author: zwc  zwc_503@163.com
 * @CreateTime: 2020-01-06 15:19
 * @Description: //TODO 移动端指定分配假报备客户实现类
 **/
@Service
@Transactional
public class AppAssignmentServiceImpl implements AppAssignmentService {

    private static final Log log = LogFactory.get();

    @Resource
    private BProjectMapper bProjectMapper;

    @Resource
    private ASharepoolMapper aSharepoolMapper;

    @Resource
    private BSalesgroupmemberMapper bSalesgroupmemberMapper;

    @Resource
    private BSalesuserMapper bSalesuserMapper;

    @Resource
    private AShareprojectMapper aShareprojectMapper;

    @Resource
    private BProjectsharemanualconfigureMapper bProjectsharemanualconfigureMapper;

    @Resource
    private ASharelogdetailMapper aSharelogdetailMapper;

    @Resource
    private AShareqrcodeparameterMapper aShareqrcodeparameterMapper;

    @Resource
    private CWxuserMapper cWxuserMapper;


    /*
     * @Author zwc   zwc_503@163.com
     * @Date 15:48 2020/1/6
     * @Param
     * @return
     * @Version 1.0
     * @Description //TODO 查询到待分配的假报备客户
     **/
    @Override
    public List<CWxuser> selectSharePoolListForAssignment(String thisUserId, String projectId, String jobId) {
        // 首先获取到项目对象
        BProject project = bProjectMapper.selectById(projectId);
        // 获取到该项目的分享项目对象
        QueryWrapper<AShareproject> shareprojectQueryWrapper = new QueryWrapper<>();
        shareprojectQueryWrapper.eq("ProjectID", projectId);
        shareprojectQueryWrapper.eq("IsDel", 0);
        AShareproject shareproject = aShareprojectMapper.selectOne(shareprojectQueryWrapper);
        // 判断 shareSnatchingMode 字段为0的情况（指定分配）其他情况不做处理
        if (project.getShareSnatchingMode().intValue() != 0) {
            throw new RuntimeException("当前登录人所属项目的shareSnatchingMode字段不为0，故不处理");
        }
        // 根据项目id和当前登录人id，查询B_ProjcetShareManualConfigure表查询数据，取出其中的childId
        QueryWrapper<BProjectsharemanualconfigure> projectsharemanualconfigureQueryWrapper = new QueryWrapper<>();
        projectsharemanualconfigureQueryWrapper.eq("ProjectID", projectId);
        projectsharemanualconfigureQueryWrapper.eq("DistributionUserID", thisUserId);
        projectsharemanualconfigureQueryWrapper.eq("IsDel", 0);
        List<BProjectsharemanualconfigure> projectsharemanualconfigureList = bProjectsharemanualconfigureMapper.selectList(projectsharemanualconfigureQueryWrapper);
        if (CollectionUtils.isEmpty(projectsharemanualconfigureList)) {
            throw new RuntimeException("当前登录人所属项目的projectsharemanualconfigureList数据为空，故不处理");
        }
        // 获取到子类的id集合
        List<String> mediaChildIdList = projectsharemanualconfigureList.stream().map(BProjectsharemanualconfigure::getMediaChildID).collect(Collectors.toList());
        // 获取该关联项目下的待分配假报备客户
        QueryWrapper<ASharepool> sharepoolQueryWrapper = new QueryWrapper<>();
        sharepoolQueryWrapper.eq("ShareProjectID", shareproject.getId());
        sharepoolQueryWrapper.eq("Category", 0);
        sharepoolQueryWrapper.eq("IsDel", 0);
        List<ASharepool> sharepoolList = aSharepoolMapper.selectList(sharepoolQueryWrapper);
        if (CollectionUtils.isEmpty(sharepoolList)) {
            throw new RuntimeException("当前登录人所属项目的sharepoolList数据为空，故不处理");
        }
        // 取出其中的WXUserId
        List<String> wxUserIdList = sharepoolList.stream().map(ASharepool::getWXUserID).collect(Collectors.toList());
        // 取出分享日志详情表数据 (去重后数量小于等于wxuserid集合数量)
        QueryWrapper<ASharelogdetail> sharelogdetailQueryWrapper = new QueryWrapper<>();
        sharelogdetailQueryWrapper.in("WXUserID", wxUserIdList);
        sharelogdetailQueryWrapper.eq("ShareProjectID", shareproject.getId());
        sharelogdetailQueryWrapper.eq("Category", 1);
        sharelogdetailQueryWrapper.eq("IsDel", 0);
        List<ASharelogdetail> sharelogdetailList = aSharelogdetailMapper.selectList(sharelogdetailQueryWrapper);
        if (CollectionUtils.isEmpty(sharelogdetailList)) {
            throw new RuntimeException("当前登录人所属项目的sharelogdetailList数据为空，故不处理");
        }
        // 取出其中的shareLogId集合
        List<String> shareLogIdList = sharelogdetailList.stream().map(ASharelogdetail::getShareLogID).collect(Collectors.toList());
        // 取出分享二维码参数表数据
        QueryWrapper<AShareqrcodeparameter> shareqrcodeparameterQueryWrapper = new QueryWrapper<>();
        shareqrcodeparameterQueryWrapper.in("ID", shareLogIdList);
        shareqrcodeparameterQueryWrapper.in("SID", mediaChildIdList);
        shareqrcodeparameterQueryWrapper.eq("IsDel", 0);
        List<AShareqrcodeparameter> shareqrcodeparameterList = aShareqrcodeparameterMapper.selectList(shareqrcodeparameterQueryWrapper);
        if (CollectionUtils.isEmpty(shareqrcodeparameterList)) {
            throw new RuntimeException("当前登录人所属项目的shareqrcodeparameterList数据为空，故不处理");
        }
        // 取出分享二维码表中的id集合（这个id集合就是sharelogdetailList中的shareLogId集合）
        List<String> shareLogIdListWithWXUserIdList = shareqrcodeparameterList.stream().map(AShareqrcodeparameter::getId).collect(Collectors.toList());
        // 取出最终要返回给客户的微信用户id集合
        List<String> lastWXUserIdList = sharelogdetailList.stream().filter(s -> shareLogIdListWithWXUserIdList.contains(s.getShareLogID())).map(ASharelogdetail::getWXUserID).collect(Collectors.toList());
        // 去C_WXUser表 查出来数据并返回
        List<CWxuser> wxuserList = cWxuserMapper.selectBatchIds(lastWXUserIdList);
        // todo 是否需要把 shareProjectId && wxuserId 一起返回给前端待定
        return wxuserList;
    }

    /*
     * @Author zwc   zwc_503@163.com
     * @Date 10:06 2020/1/7
     * @Param
     * @return
     * @Version 1.0
     * @Description //TODO 查询案场负责人下的或者自渠负责人下的顾问列表
     **/
    @Override
    public List<BSalesuser> selectPropertyOrSinceTheCanalList(String thisUserId, String projectId, String jobId) {
        // 定义roleId
        String roleId = "";
        if (AppAssignmentConstants.PROPERTY_LEADER_ID.equals(jobId)) {
            // 案场负责人
            roleId = AppAssignmentConstants.PROPERTY_ROLE_ID;
        } else {
            // 自渠负责人
            roleId = AppAssignmentConstants.SINCE_THE_CANAL_ROLE_ID;
        }
        // 去B_SaleGroupMember表中查数据
        QueryWrapper<BSalesgroupmember> salesgroupmemberQueryWrapper = new QueryWrapper<>();
        salesgroupmemberQueryWrapper.eq("ProjectID", projectId);
        salesgroupmemberQueryWrapper.eq("RoleID", roleId);
        salesgroupmemberQueryWrapper.eq("IsDel", 0);
        salesgroupmemberQueryWrapper.eq("Status", 1);
        List<BSalesgroupmember> salesgroupmemberList = bSalesgroupmemberMapper.selectList(salesgroupmemberQueryWrapper);
        if (CollectionUtils.isEmpty(salesgroupmemberList)) {
            throw new RuntimeException("当前登录人所属项目的salesgroupmemberList数据为空，故不处理");
        }
        // 取出其中的memberId集合
        List<String> memberIdList = salesgroupmemberList.stream().map(BSalesgroupmember::getMemberID).collect(Collectors.toList());
        // 去C_WXUser表中查数据
        // 去B_SaleGroupMember表中查数据
        QueryWrapper<CWxuser> wxuserQueryWrapper = new QueryWrapper<>();
        wxuserQueryWrapper.in("BindChannelUserID", memberIdList);
        wxuserQueryWrapper.eq("IsDel", 0);
        List<CWxuser> wxuserList = cWxuserMapper.selectList(wxuserQueryWrapper);
        if (CollectionUtils.isEmpty(wxuserList)) {
            throw new RuntimeException("当前登录人所属项目的绑定后的wxuserList数据为空，故不处理");
        }
        // 取出其中的memberId集合
        List<String> bindChannelUserIdList = wxuserList.stream().map(CWxuser::getBindChannelUserID).collect(Collectors.toList());
        // 去B_SalesUser表中查数据 并返回
        List<BSalesuser> salesuserList = bSalesuserMapper.selectBatchIds(bindChannelUserIdList);
        return salesuserList;
    }

    /*
     * @Author zwc   zwc_503@163.com
     * @Date 10:34 2020/1/7
     * @Param
     * @return
     * @Version 1.0
     * @Description //TODO 分配客户给顾问
     **/
    @Override
    public List<String> savePropertyOrSinceTheCanal(String thisUserId, String projectId, String jobId, List<AppAssignmentPrameterVO> sharePoolVOList, String adviserId) {
        // 获取到该项目的分享项目对象
        QueryWrapper<AShareproject> shareprojectQueryWrapper = new QueryWrapper<>();
        shareprojectQueryWrapper.eq("ProjectID", projectId);
        shareprojectQueryWrapper.eq("IsDel", 0);
        AShareproject shareproject = aShareprojectMapper.selectOne(shareprojectQueryWrapper);
        // 拿到分享项目ID
        String shareProjectId = shareproject.getId();
        // 根据所选择的顾问ID，查询C_WXUSER表中的id，用来当做shareWXUserID
        QueryWrapper<CWxuser> cWxuserQueryWrapper = new QueryWrapper<>();
        cWxuserQueryWrapper.eq("BindChannelUserID", adviserId);
        cWxuserQueryWrapper.eq("IsDel", 0);
        CWxuser wxuser = cWxuserMapper.selectOne(cWxuserQueryWrapper);
        // 拿到该顾问唯一的分享微信用户ID
        String shareWXuserId = wxuser.getId();
        // 根据所选择的顾问ID，查询A_shareQRCodePrameter表中的id，用来当做shareLogID
        QueryWrapper<AShareqrcodeparameter> shareqrcodeparameterQueryWrapper = new QueryWrapper<>();
        shareqrcodeparameterQueryWrapper.eq("SID", adviserId);
        shareqrcodeparameterQueryWrapper.eq("IsDel", 0);
        AShareqrcodeparameter shareqrcodeparameter = aShareqrcodeparameterMapper.selectOne(shareqrcodeparameterQueryWrapper);
        // 拿到该顾问唯一的分享微信用户ID
        String shareLogId = shareqrcodeparameter.getId();
        // todo 取出来adviserGroupId (写死的roleId ，这里的需求只有两种)
        String adviserGroupId = "";
        if (AppAssignmentConstants.PROPERTY_LEADER_ID.equals(jobId)) {
            // 案场负责人
            adviserGroupId = AppAssignmentConstants.PROPERTY_ROLE_ID;
        } else {
            // 自渠负责人
            adviserGroupId = AppAssignmentConstants.SINCE_THE_CANAL_ROLE_ID;
        }
        // 定义AsharePool表中的ID，用于逻辑删
        List<String> sharePoolIdList = Lists.newArrayList();
        // 定义公共的AshareLogDetail对象用于新增
        ASharelogdetail sharelogdetail;
        // 定义公共的Asharepool对象用于逻辑删
        ASharepool sharepool;
        // 遍历组装对象
        for (AppAssignmentPrameterVO prameterVO : sharePoolVOList) {
            // 重新指向地址
            sharelogdetail = new ASharelogdetail();
            // 赋值
            sharelogdetail.setWXUserID(prameterVO.getWxUserId());
            sharelogdetail.setIsDel(0);
            sharelogdetail.setIsAuthorize(1);
            sharelogdetail.setCategory(1);
            sharelogdetail.setStatus(1);
            sharelogdetail.setShareProjectID(shareProjectId);
            sharelogdetail.setAdviserGroupID(adviserGroupId);
            sharelogdetail.setShareLogID(shareLogId);
            sharelogdetail.setShareWXUserID(shareWXuserId);
            sharelogdetail.setCreator(thisUserId);
            sharelogdetail.setCreateTime(new Date());
            sharelogdetail.setEditeTime(new Date());
            sharelogdetail.setId(UUID.randomUUID().toString());
            // 新增入库
            aSharelogdetailMapper.insert(sharelogdetail);
            // 返回前台处理成功的分享池数据ID集合
            sharePoolIdList.add(prameterVO.getThisId());
            // 将分享池sharePool中的数据逻辑删
            sharepool = new ASharepool();
            // 赋值
            sharepool.setEditor(thisUserId);
            sharepool.setEditeTime(new Date());
            sharepool.setIsDel(1);
            sharepool.setId(prameterVO.getThisId());
            aSharepoolMapper.updateById(sharepool);
        }
        return sharePoolIdList;
    }

    /*
     * @Author zwc   zwc_503@163.com
     * @Date 16:56 2020/1/7
     * @Param
     * @return
     * @Version 1.0
     * @Description //TODO 查看是否显示 ‘潜客手动分配’ 节点
     **/
    @Override
    public List<BProjectsharemanualconfigure> checkAssignmentFlag(String thisUserId, String projectId, String jobId) {
        // 首先获取到项目对象
        BProject project = bProjectMapper.selectById(projectId);
        // 判断 shareSnatchingMode 字段为0的情况（指定分配）其他情况不做处理
        if (project.getShareSnatchingMode().intValue() != 0) {
            throw new RuntimeException("当前登录人所属项目的shareSnatchingMode字段不为0，故不处理");
        }
        // 根据项目id和当前登录人id，查询B_ProjcetShareManualConfigure表查询数据，取出其中的childId
        QueryWrapper<BProjectsharemanualconfigure> projectsharemanualconfigureQueryWrapper = new QueryWrapper<>();
        projectsharemanualconfigureQueryWrapper.eq("ProjectID", projectId);
        projectsharemanualconfigureQueryWrapper.eq("DistributionUserID", thisUserId);
        projectsharemanualconfigureQueryWrapper.eq("IsDel", 0);
        List<BProjectsharemanualconfigure> projectsharemanualconfigureList = bProjectsharemanualconfigureMapper.selectList(projectsharemanualconfigureQueryWrapper);
        return projectsharemanualconfigureList;
    }
}
