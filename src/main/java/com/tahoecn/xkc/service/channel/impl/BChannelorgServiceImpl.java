package com.tahoecn.xkc.service.channel.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.core.json.JSONResult;
import com.tahoecn.security.SecureUtil;
import com.tahoecn.xkc.common.constants.GlobalConstants;
import com.tahoecn.xkc.mapper.channel.BChannelorgMapper;
import com.tahoecn.xkc.mapper.channel.BChanneluserMapper;
import com.tahoecn.xkc.mapper.channel.BPojectchannelorgrelMapper;
import com.tahoecn.xkc.mapper.rule.BClueruleAdvisergroupMapper;
import com.tahoecn.xkc.model.channel.BChannelorg;
import com.tahoecn.xkc.model.channel.BChanneluser;
import com.tahoecn.xkc.model.channel.BPojectchannelorgrel;
import com.tahoecn.xkc.model.dto.ChannelDto;
import com.tahoecn.xkc.model.dto.ChannelInsertDto;
import com.tahoecn.xkc.model.rule.BClueruleAdvisergroup;
import com.tahoecn.xkc.service.channel.IBChannelorgService;
import com.tahoecn.xkc.service.channel.IBChanneluserService;
import com.tahoecn.xkc.service.rule.IBClueruleAdvisergroupService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-06-25
 */
@Service
public class BChannelorgServiceImpl extends ServiceImpl<BChannelorgMapper, BChannelorg> implements IBChannelorgService {

    @Autowired
    private BChannelorgMapper channelorgMapper;
    @Autowired
    private IBChannelorgService channelorgService;
    @Autowired
    private BChanneluserMapper channeluserMapper;
    @Autowired
    private IBChanneluserService channeluserService;
    @Autowired
    private BPojectchannelorgrelMapper pojectchannelorgrelMapper;
    @Autowired
    private BClueruleAdvisergroupMapper clueruleAdvisergroupMapper;
    @Autowired
    private IBClueruleAdvisergroupService clueruleAdvisergroupService;

    @Override
    public Map getList(String ProjectID, String sqlWhere, int pageSize, int pageNum) {
//        if (page==null){
//            return channelorgMapper.getList(map);
//        }
        List<ChannelDto> list = channelorgMapper.getList(ProjectID, sqlWhere, pageSize, pageNum);
        Integer recordCount = channelorgMapper.getRecordCount(ProjectID, sqlWhere);
        Map map = new HashMap();
        map.put("list", list);
        map.put("recordCount", recordCount);
        return map;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void ChannelDetail_InsertN(ChannelInsertDto channelInsertDto) {
        baseMapper.ChannelDetail_InsertN(channelInsertDto);
        channeluserMapper.ChannelDetail_InsertN(channelInsertDto);
    }

    @Override
    public List<Map<String, String>> ChannelOrgAllList_SelectN(String projectID, String pOrgName) {
        return baseMapper.ChannelOrgAllList_SelectN(projectID, pOrgName);
    }

    @Override
    public void ChannelStatus_UpdateN(String id, String userID, String status) {
        baseMapper.ChannelStatus_UpdateN(id, userID, status);
        channeluserMapper.ChannelStatus_UpdateN(id, userID, status);
    }

    @Override
    public int ChannelOrgNameIsExist_SelectN(String orgName,String sqlWhere) {
        return baseMapper.ChannelOrgNameIsExist_SelectN(orgName,sqlWhere);
    }

    @Override
    public String getOrgCode() {
        return String.format("%06d", Integer.valueOf(baseMapper.getOrgCode()) + 1);
    }

    @Override
    public IPage<Map<String, String>> AgenList_SelectN(IPage page,StringBuffer where, StringBuffer orderBy) {
        return baseMapper.AgenList_SelectN(page,where,orderBy);
    }

    @Override
    public List<Map<String, Object>> ChannelExcelList_SelectN(String projectID, StringBuilder sqlWhere) {
        return baseMapper.ChannelExcelList_SelectN(projectID,sqlWhere);
    }

    @Override
    public List<Map<String, Object>> getParentOrg() {
        return baseMapper.getParentOrg();
    }

    /**
     * 新增和修改机构
     *
     * @param map
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONResult insertOrg(Map<String, String> map) {

        JSONResult jsonResult = new JSONResult();
        //新增
        try {
            if (StringUtils.isNotBlank(map.get("OrgID"))) {
                //首先查询这个项目下机构名称是否重复
                int countOrg = channelorgService.ChannelOrgNameIsExist_SelectN(map.get("OrgName"), null);
                if (countOrg != 0) {
                    jsonResult.setCode(GlobalConstants.E_CODE);
                    jsonResult.setMsg("该项目下存在重复的机构名称");
                    return jsonResult;
                }
                //再判断用户名是否重复
                int countUser = channeluserService.ChannelAgenUserNameIsExist_SelectN(map.get("UserName"), null);
                if (countUser != 0) {
                    jsonResult.setCode(GlobalConstants.E_CODE);
                    jsonResult.setMsg("存在重复的登录帐号");
                    return jsonResult;
                }
                //再判断手机号是否重复
                int countMobile = channeluserService.ChannelAgenMobileIsExist_SelectN(map.get("Mobile"), null);
                if (countMobile != 0) {
                    jsonResult.setCode(GlobalConstants.E_CODE);
                    jsonResult.setMsg("存在重复的负责人电话");
                    return jsonResult;
                }
                //添加机构SQL

                //处理规则参数IDs
                //如果ruleID为空，表示没有在新增机构的时候给这个项目选择现有规则，就跳过这条数据
                //执行拼接好的SQL
                //新增 OrgID错误
                String OrgID = UUID.randomUUID().toString();
                String ChannelTypeID = UUID.randomUUID().toString();
                BChannelorg channelorg = new BChannelorg();
                channelorg.setId(OrgID);
                channelorg.setOrgCode(map.get("OrgCode"));
                channelorg.setOrgName(map.get("OrgName"));
                channelorg.setOrgShortName(map.get("OrgShortName"));
                //ChannelTypeID是写死的 不知道为什么 没逻辑...
                channelorg.setChannelTypeID(map.get("32C92DA0-DA13-4C21-A55E-A1D16955882C"));
                channelorg.setOrgCategory(1);
                channelorg.setMobile(map.get("Mobile"));
                channelorg.setResponsible(map.get("Name"));
                channelorg.setApprovalStatus(1);
                channelorg.setApprovalDate(new Date());
                channelorg.setCreator(map.get("UserID"));
                channelorg.setBizLicense(map.get("Bizlicense"));
                channelorg.setIsDel(0);
                channelorg.setStatus(1);
                baseMapper.insert(channelorg);
                //添加机构负责人SQL
                BChanneluser channeluser = new BChanneluser();
                channeluser.setChannelTypeID(ChannelTypeID);
                channeluser.setUserName(map.get("UserName"));
                channeluser.setChannelTypeID("32C92DA0-DA13-4C21-A55E-A1D16955882C");
                channeluser.setChannelType("中介同行");
                channeluser.setPassword(SecureUtil.md5("123321"));
                channeluser.setMobile(map.get("Mobile"));
                channeluser.setName(map.get("Name"));
                channeluser.setChannelOrgCode(map.get("OrgCode"));
                channeluser.setChannelOrgID(OrgID);
                channeluser.setJob(0);
                channeluser.setApprovalStatus(1);
                channeluser.setApprover(map.get("UserID"));
                channeluser.setApprovalDate(new Date());
                channeluser.setCreator(map.get("UserID"));
                channeluser.setCreateTime(new Date());
                channeluser.setIsDel(0);
                channeluser.setStatus(Integer.valueOf(map.get("Status")));
                channeluserService.save(channeluser);

                //添加这个机构的项目权限SQL语句

                String[] ProjectIDs = map.get("ProjectIDs").split(",");

                for (String projectID : ProjectIDs) {
                    BPojectchannelorgrel pojectchannelorgrel = new BPojectchannelorgrel();
                    pojectchannelorgrel.setOrgID(OrgID);
                    pojectchannelorgrel.setProjectID(projectID);
                    pojectchannelorgrel.setCreator(map.get("UserID"));
                    pojectchannelorgrel.setCreateTime(new Date());
                    pojectchannelorgrel.setIsDel(0);
                    pojectchannelorgrel.setStatus(1);
                    pojectchannelorgrelMapper.insert(pojectchannelorgrel);
                }
                //添加这个机构的规则
                if (StringUtils.isNotBlank(map.get("RuleIDs"))) {
                    JSONArray RuleIDs = JSON.parseArray(map.get("RuleIDs"));
                    String[] RuleID = RuleIDs.get(0).toString().split(",");
                    for (String ruleID : RuleID) {
                        if (StringUtils.isNotBlank(ruleID)) {
                            //如果ruleID为空，表示没有在新增机构的时候给这个项目选择现有规则，就跳过这条数据
                            BClueruleAdvisergroup clueruleAdvisergroup = new BClueruleAdvisergroup();
                            clueruleAdvisergroup.setClueRuleID(ruleID);
                            clueruleAdvisergroup.setAdviserGroupID(OrgID);
                            clueruleAdvisergroup.setCreator(map.get("UserID"));
                            clueruleAdvisergroup.setCreateTime(new Date());
                            clueruleAdvisergroup.setIsDel(0);
                            clueruleAdvisergroup.setStatus(1);
                            clueruleAdvisergroupMapper.insert(clueruleAdvisergroup);
                        }
                    }
                }
            }
            //修改
            else {
                String sqlWhere = "AND ID <>'" + map.get("OrgID") + "'";
                int countOrg = channelorgService.ChannelOrgNameIsExist_SelectN(map.get("OrgName"), sqlWhere);
                if (countOrg > 0) {
                    jsonResult.setCode(GlobalConstants.E_CODE);
                    jsonResult.setMsg("该项目下存在重复的机构名称");
                    return jsonResult;
                }
                //再判断用户名是否重复
                sqlWhere = "AND ID <>'" + map.get("LeaderID") + "'";
                int countUser = channeluserService.ChannelAgenUserNameIsExist_SelectN(map.get("UserName"), sqlWhere);
                if (countUser > 0) {
                    jsonResult.setCode(GlobalConstants.E_CODE);
                    jsonResult.setMsg("存在重复的登录帐号");
                    return jsonResult;
                }
                //再判断手机号是否重复
                int countMobile = channeluserService.ChannelAgenMobileIsExist_SelectN(map.get("Mobile"), sqlWhere);
                if (countMobile > 0) {
                    jsonResult.setCode(GlobalConstants.E_CODE);
                    jsonResult.setMsg("存在重复的负责人电话");
                    return jsonResult;
                }
                //修改B_ChannelOrg  B_ChannelUser
                //UPDATE [dbo].[B_ChannelOrg] SET OrgName='{OrgName}',OrgShortName='{OrgShortName}',Mobile='{Mobile}',
                // Responsible='{Name}',Status='{Status}',Editor='{UserID}',EditTime=getdate(),Bizlicense='{Bizlicense}'
                // WHERE ID='{OrgID}'
                //UPDATE [dbo].[B_ChannelUser] SET UserName='{UserName}',Mobile='{Mobile}',Name='{Name}',Status='{Status}',
                // Editor='{UserID}',EditeTime=getdate() WHERE ID='{LeaderID}'
                BChannelorg channelorg = new BChannelorg();
                channelorg.setOrgName(map.get("OrgName"));
                channelorg.setOrgShortName(map.get("OrgShortName"));
                channelorg.setMobile(map.get("Mobile"));
                channelorg.setResponsible(map.get("Name"));
                channelorg.setStatus(Integer.valueOf(map.get("Status")));
                channelorg.setEditor(map.get("UserID"));
                channelorg.setEditTime(new Date());
                channelorg.setBizLicense(map.get("Bizlicense"));
                channelorg.setId(map.get("OrgID"));
                channelorgService.updateById(channelorg);

                BChanneluser channeluser = new BChanneluser();
                channeluser.setId(map.get("LeaderID"));
                channeluser.setUserName(map.get("UserName"));
                channeluser.setMobile(map.get("Mobile"));
                channeluser.setName(map.get("Name"));
                channeluser.setStatus(Integer.valueOf(map.get("Status")));
                channeluser.setEditor(map.get("UserID"));
                channeluser.setEditeTime(new Date());
                channeluserService.updateById(channeluser);

                //编辑这个机构的项目权限   这里可以先全部删除再进行新增
                //先将这个机构的项目权限全部设置为删除状态
                pojectchannelorgrelMapper.updateToDelete(map.get("OrgID"), map.get("UserID"));
                //然后重新新增
                String[] ProjectIDs = map.get("ProjectIDs").split(",");
                for (String projectID : ProjectIDs) {
                    BPojectchannelorgrel pojectchannelorgrel = new BPojectchannelorgrel();
                    pojectchannelorgrel.setOrgID(map.get("OrgID"));
                    pojectchannelorgrel.setProjectID(projectID);
                    pojectchannelorgrel.setCreator(map.get("UserID"));
                    pojectchannelorgrel.setCreateTime(new Date());
                    pojectchannelorgrel.setIsDel(0);
                    pojectchannelorgrel.setStatus(1);
                    pojectchannelorgrelMapper.insert(pojectchannelorgrel);
                }
                //编辑这个机构下所设置的所有规则  不能直接全删在新增，会出问题，要进行判断是否已经设置了规则，
                // 根据项目ID和机构ID查询，设置了就Update 没设置就新增，目前没有删除，所以不搞删除，删除了项目权限，也就没有了规则
                //处理规则参数IDs    逗号分隔的字符串数组， 每个item里又逗号分隔了数据ID，ProjectID，RuleID
                //可能有删除掉的项目，要把这个机构这些项目下的规则删除掉

                StringBuilder ProjectIDWhere = new StringBuilder();
                JSONArray RuleIDs = JSON.parseArray(map.get("RuleIDs"));
                for (Object ruleID : RuleIDs) {
                    //拼接project条件
                    String[] split = ruleID.toString().split(",");
                    ProjectIDWhere.append("'" + split[0] + "',");
                }
                boolean flag = false;
                //去掉最后一个逗号
                String ProjectIDWhereStr = ProjectIDWhere.substring(0, ProjectIDWhere.length() - 1);
                for (Object ruleID : RuleIDs) {
                    String[] split = ruleID.toString().split(",");
                    String rProjectID = split[0];
                    String ClueRuleID = split[1];
                    flag = clueruleAdvisergroupService.updateRules(map.get("OrgID"), map.get("UserID"), rProjectID, ClueRuleID, ProjectIDWhereStr);
                    if (!flag) {
                        jsonResult.setCode(GlobalConstants.E_CODE);
                        jsonResult.setMsg("数据库修改错误!");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            jsonResult.setCode(GlobalConstants.E_CODE);
            jsonResult.setMsg("数据库修改错误!");
            return jsonResult;
        }
        jsonResult.setCode(GlobalConstants.S_CODE);
        jsonResult.setMsg("SUCCESS");
        return jsonResult;
    }
}
