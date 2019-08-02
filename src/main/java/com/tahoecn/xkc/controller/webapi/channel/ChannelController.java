package com.tahoecn.xkc.controller.webapi.channel;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tahoecn.core.json.JSONResult;
import com.tahoecn.xkc.common.constants.GlobalConstants;
import com.tahoecn.xkc.common.utils.ExcelUtil;
import com.tahoecn.xkc.common.utils.ThreadLocalUtils;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.channel.BChannelorg;
import com.tahoecn.xkc.model.channel.BChanneluser;
import com.tahoecn.xkc.model.channel.BPojectchannelorgrel;
import com.tahoecn.xkc.model.dto.ChannelInsertDto;
import com.tahoecn.xkc.model.rule.BClueruleAdvisergroup;
import com.tahoecn.xkc.service.channel.IBChannelorgService;
import com.tahoecn.xkc.service.channel.IBChanneluserService;
import com.tahoecn.xkc.service.channel.IBPojectchannelorgrelService;
import com.tahoecn.xkc.service.channel.impl.BPojectchannelorgrelServiceImpl;
import com.tahoecn.xkc.service.dict.ISDictionaryService;
import com.tahoecn.xkc.service.rule.IBClueruleAdvisergroupService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author YYY
 * @since 2019-06-25
 */
@RestController
@RequestMapping("/webapi/channel")
public class ChannelController extends TahoeBaseController {


    @Autowired
    private IBChannelorgService channelorgService;

    @Autowired
    private IBChanneluserService channeluserService;

    @Autowired
    private ISDictionaryService dictionaryService;

    @Autowired
    IBPojectchannelorgrelService pojectchannelorgrelService;

    @Autowired
    IBClueruleAdvisergroupService clueruleAdvisergroupService;


    @ApiOperation(value = "分销渠道列表", notes = "分页获取分销渠道列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "当前页数", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小", dataType = "int")})
    @RequestMapping(value = "/ChannelList_SelectN", method = {RequestMethod.GET})
    public Result ChannelList_SelectN(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize,
                                          String ProjectID, String OrgName, String OrgLeaderName, String UserName,String OrgLeaderUserName,
                                          String OrgLeaderMobile, String Status, String CreatorName, Date begin, Date end,String IsExcel) {

        Map map = new HashMap<>();
        map.put("ProjectID", ProjectID);
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);

        StringBuilder sqlWhere = new StringBuilder();
        if (StringUtils.isNotBlank(OrgName)) {
            sqlWhere.append("AND a.OrgName like '%" + OrgName + "%'");
        }
        if (StringUtils.isNotBlank(OrgLeaderMobile)) {
            sqlWhere.append("AND b.Mobile like '%" + OrgLeaderMobile + "%'");
        }
        if (StringUtils.isNotBlank(OrgLeaderName)) {
            sqlWhere.append("AND b.Name like '%" + OrgLeaderName + "%'");
        }
        if (StringUtils.isNotBlank(OrgLeaderUserName)) {
            sqlWhere.append("AND b.UserName like '%"+OrgLeaderUserName+"%'");
        }
        if (StringUtils.isNotBlank(UserName)) {
            sqlWhere.append("AND b.UserName like '%" + UserName + "%'");
        }
        if (StringUtils.isNotBlank(Status)) {
            if (Objects.equals(Status, "0") || Objects.equals(Status, "1")){
                sqlWhere.append("AND b.Status = '" + Status + "'");
            }
        }
        if (begin != null) {
            sqlWhere.append("AND a.CreateTime > '" + begin + "'");
        }
        if (end != null) {
            sqlWhere.append("AND a.CreateTime <  '" + end + "'");
        }
        //CreatorName不确定表和字段名
        if (StringUtils.isNotBlank(CreatorName)) {
            sqlWhere.append("AND sa.EmployeeName like '%" + CreatorName + "%'");
        }
        map.put("sqlWhere", sqlWhere.toString());

        if (StringUtils.isNotEmpty(IsExcel)) {
            ChannelExcelList_SelectN(ProjectID,sqlWhere);
            return null;
        }

        Map list = channelorgService.getList(ProjectID, sqlWhere.toString(), pageSize, pageNum);
        return Result.ok(list);
    }

    private void ChannelExcelList_SelectN(String projectID, StringBuilder sqlWhere) {
        List<Map<String,Object>> result = channelorgService.ChannelExcelList_SelectN(projectID,sqlWhere);
        List<ExcelExportEntity> entity = new ArrayList<ExcelExportEntity>();
        entity.add(new ExcelExportEntity("机构编码", "OrgCode"));
        entity.add(new ExcelExportEntity("机构名称", "OrgName"));
        entity.add(new ExcelExportEntity("机构简称", "OrgShortName"));
        entity.add(new ExcelExportEntity("资质附件", "BizLicense"));
        entity.add(new ExcelExportEntity("机构状态", "StatuName"));
        entity.add(new ExcelExportEntity("负责人姓名", "OrgLeaderName"));
        entity.add(new ExcelExportEntity("负责人手机号", "OrgLeaderMobile"));
        entity.add(new ExcelExportEntity("负责人帐号", "UserName"));
        entity.add(new ExcelExportEntity("帐号状态", "OrgLeaderStatuName"));
        entity.add(new ExcelExportEntity("创建时间", "CreateTime"));
        entity.add(new ExcelExportEntity("创建人", "CreatorName"));


        try {
            LocalDateTime time= LocalDateTime.now();
            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
            String name = dtf2.format(time) + ".xls";
            ExcelUtil.exportExcel(entity,result,name,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @ApiOperation(value = "渠道管理-所属机构列表---导入中查询")
    @RequestMapping(value = "/ChannelOrgList_Select", method = {RequestMethod.GET})
    public Result ChannelOrgList_Select(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize,String pOrgName){
        StringBuilder sqlWhere = new StringBuilder();
        if (StringUtils.isNotEmpty(pOrgName)) {
            sqlWhere.append("AND a.OrgName like '%").append(pOrgName).append("%'");
        }
        IPage<Map<String,Object>> result = channeluserService.ChannelOrgList_Select(pageNum,pageSize,sqlWhere.toString());
        return Result.ok(result);
    }

    @ApiOperation(value = "机构导入添加报备项目")
    @RequestMapping(value = "/ChannelOrgImport_Insert", method = {RequestMethod.GET})
    public Result ChannelOrgImport_Insert(String OrgID,String ProjectID,String UserID,String RuleIDs){
        channeluserService.ChannelOrgImport_Insert(OrgID,ProjectID,UserID,RuleIDs);
        return Result.ok("");
    }


    /**
     * {"_datatype":"text","_param":{"
     * ProjectID":"cd69e423-63b5-e711-80c7-00505686c900","
     * OrgID":"","
     * OrgName":"qqq","
     * OrgShortName":"","
     * Bizlicense":"/Uploads/image/20190627/9486178f28b0352fae139bafc18d370c.png","
     * LeaderID":"","
     * UserName":"qqq","
     * Name":"qqq","
     * Mobile":"11111111222","
     * Status":"1","
     * ProjectIDs":"CD69E423-63B5-E711-80C7-00505686C900","
     * RuleIDs":["CD69E423-63B5-E711-80C7-00505686C900,"],"
     * UserID":"6C883173-489C-47A4-97D2-3601CB7CEDFD"}}:
     */

    @ApiOperation(value = "新增渠道机构", notes = "新增渠道机构")
    @RequestMapping(value = "/ChannelDetail_InsertN", method = {RequestMethod.POST})
    @Transactional(rollbackFor = Exception.class)
    public Result ChannelDetail_InsertN(ChannelInsertDto channelInsertDto) {

        try{
            if (StringUtils.isEmpty(channelInsertDto.getOrgID())){
                //首先查询这个项目下机构名称是否重复
                int countOrg = channelorgService.ChannelOrgNameIsExist_SelectN(channelInsertDto.getOrgName(), null);
                if (countOrg != 0) {
                    return Result.errormsg(90,"该项目下存在重复的机构名称");
                }
                //再判断用户名是否重复
                int countUser = channeluserService.ChannelAgenUserNameIsExist_SelectN(channelInsertDto.getUserName(), null);
                if (countUser != 0) {
                    return Result.errormsg(90,"存在重复的登录帐号");
                }
                //再判断手机号是否重复
                int countMobile = channeluserService.ChannelAgenMobileIsExist_SelectN(channelInsertDto.getMobile(), null);
                if (countMobile != 0) {
                    return Result.errormsg(90,"存在重复的负责人电话");
                }

                String OrgID = UUID.randomUUID().toString();
                String LeaderID = UUID.randomUUID().toString();
                channelInsertDto.setOrgID(OrgID);
                channelInsertDto.setLeaderID(LeaderID);
                channelorgService.ChannelDetail_InsertN(channelInsertDto);

                //添加这个机构的项目权限SQL语句
                String[] ProjectIDs = channelInsertDto.getProjectIDs().split(",");

                for (String projectID : ProjectIDs) {
                    BPojectchannelorgrel pojectchannelorgrel = new BPojectchannelorgrel();
                    pojectchannelorgrel.setOrgID(channelInsertDto.getOrgID());
                    pojectchannelorgrel.setProjectID(projectID);
                    pojectchannelorgrel.setCreator(ThreadLocalUtils.getUserName());
                    pojectchannelorgrel.setCreateTime(new Date());
                    pojectchannelorgrel.setIsDel(0);
                    pojectchannelorgrel.setStatus(1);
                    pojectchannelorgrelService.save(pojectchannelorgrel);
                }

                //添加这个机构的规则
                if (StringUtils.isNotBlank(channelInsertDto.getRuleIDs())) {
                    JSONArray RuleIDs = JSON.parseArray(channelInsertDto.getRuleIDs());
                    String[] RuleID = RuleIDs.get(0).toString().split(",");
                    for (String ruleID : RuleID) {
                        if (StringUtils.isNotBlank(ruleID)) {
                            //如果ruleID为空，表示没有在新增机构的时候给这个项目选择现有规则，就跳过这条数据
                            BClueruleAdvisergroup clueruleAdvisergroup = new BClueruleAdvisergroup();
                            clueruleAdvisergroup.setClueRuleID(ruleID);
                            clueruleAdvisergroup.setAdviserGroupID(channelInsertDto.getOrgID());
                            clueruleAdvisergroup.setCreator(ThreadLocalUtils.getUserName());
                            clueruleAdvisergroup.setCreateTime(new Date());
                            clueruleAdvisergroup.setIsDel(0);
                            clueruleAdvisergroup.setStatus(1);
                            clueruleAdvisergroupService.save(clueruleAdvisergroup);
                        }
                    }
                }
            }else {
                String sqlWhere = "AND ID <>'" + channelInsertDto.getOrgID() + "'";
                int countOrg = channelorgService.ChannelOrgNameIsExist_SelectN(channelInsertDto.getOrgName(), sqlWhere);
                if (countOrg > 0) {
                    return Result.errormsg(90,"该项目下存在重复的机构名称");
                }
                //再判断用户名是否重复
                sqlWhere = "AND ID <>'" + channelInsertDto.getLeaderID() + "'";
                int countUser = channeluserService.ChannelAgenUserNameIsExist_SelectN(channelInsertDto.getUserName(), sqlWhere);
                if (countUser > 0) {
                    return Result.errormsg(90,"存在重复的登录帐号");
                }
                //再判断手机号是否重复
                int countMobile = channeluserService.ChannelAgenMobileIsExist_SelectN(channelInsertDto.getMobile(), sqlWhere);
                if (countMobile > 0) {
                    return Result.errormsg(90,"存在重复的负责人电话");
                }

                BChannelorg channelorg = new BChannelorg();
                channelorg.setOrgName(channelInsertDto.getOrgName());
                channelorg.setOrgShortName(channelInsertDto.getOrgShortName());
                channelorg.setMobile(channelInsertDto.getMobile());
                channelorg.setResponsible(channelInsertDto.getName());
                channelorg.setStatus(Integer.valueOf(channelInsertDto.getStatus()));
                channelorg.setEditor(ThreadLocalUtils.getUserName());
                channelorg.setEditTime(new Date());
                channelorg.setBizLicense(channelInsertDto.getBizlicense());
                channelorg.setId(channelInsertDto.getOrgID());
                channelorgService.updateById(channelorg);

                BChanneluser channeluser = new BChanneluser();
                channeluser.setId(channelInsertDto.getLeaderID());
                channeluser.setUserName(channelInsertDto.getUserName());
                channeluser.setMobile(channelInsertDto.getMobile());
                channeluser.setName(channelInsertDto.getName());
                channeluser.setStatus(Integer.valueOf(channelInsertDto.getStatus()));
                channeluser.setEditor(ThreadLocalUtils.getUserName());
                channeluser.setEditeTime(new Date());
                channeluserService.updateById(channeluser);

                //编辑这个机构的项目权限   这里可以先全部删除再进行新增
                //先将这个机构的项目权限全部设置为删除状态
                pojectchannelorgrelService.updateToDelete(channelInsertDto.getOrgID(), channelInsertDto.getUserID());
                //然后重新新增
                String[] ProjectIDs = channelInsertDto.getProjectIDs().split(",");
                for (String projectID : ProjectIDs) {
                    BPojectchannelorgrel pojectchannelorgrel = new BPojectchannelorgrel();
                    pojectchannelorgrel.setOrgID(channelInsertDto.getOrgID());
                    pojectchannelorgrel.setProjectID(projectID);
                    pojectchannelorgrel.setCreator(ThreadLocalUtils.getUserName());
                    pojectchannelorgrel.setCreateTime(new Date());
                    pojectchannelorgrel.setIsDel(0);
                    pojectchannelorgrel.setStatus(1);
                    pojectchannelorgrelService.save(pojectchannelorgrel);
                }
                //编辑这个机构下所设置的所有规则  不能直接全删在新增，会出问题，要进行判断是否已经设置了规则，
                // 根据项目ID和机构ID查询，设置了就Update 没设置就新增，目前没有删除，所以不搞删除，删除了项目权限，也就没有了规则
                //处理规则参数IDs    逗号分隔的字符串数组， 每个item里又逗号分隔了数据ID，ProjectID，RuleID
                //可能有删除掉的项目，要把这个机构这些项目下的规则删除掉

                StringBuilder ProjectIDWhere = new StringBuilder();
                JSONArray RuleIDs = JSON.parseArray(channelInsertDto.getRuleIDs());
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
                    flag = clueruleAdvisergroupService.updateRules(channelInsertDto.getOrgID(), ThreadLocalUtils.getUserName(), rProjectID, ClueRuleID, ProjectIDWhereStr);
                    if (!flag) {
                        return Result.errormsg(500,"数据库修改错误");
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.errormsg(500,"数据库修改错误");
        }

        return Result.ok("");
    }

    @ApiOperation(value = "修改机构状态", notes = "修改机构状态")
    @RequestMapping(value = "/ChannelStatus_UpdateN", method = {RequestMethod.GET})
    public Result ChannelStatus_UpdateN(String ID, String UserID, String Status) {
        channelorgService.ChannelStatus_UpdateN(ID, UserID, Status);
        return Result.ok("");
    }


    @ApiOperation(value = "推荐渠道列表-审核人查询", notes = "推荐渠道列表-审核人查询")
    @RequestMapping(value = "/AgenApproverList_SelectN", method = {RequestMethod.GET})
    public JSONResult AgenApproverList_SelectN() {

        List<Map<String, String>> list = channeluserService.AgenApproverList();
        JSONResult jsonResult = new JSONResult();
        jsonResult.setMsg("SUCCESS");
        jsonResult.setData(list);
        return jsonResult;
    }

    @ApiOperation(value = "分销中介、推荐渠道列表-证件类型列表查询", notes = "分销中介、推荐渠道列表-证件类型列表查询")
    @RequestMapping(value = "/AgenCertificatesList_SelectN", method = {RequestMethod.GET})
    public JSONResult AgenCertificatesList_SelectN() {

        List<Map<String, String>> list = dictionaryService.AgenCertificatesList_SelectN();
        JSONResult jsonResult = new JSONResult();
        jsonResult.setData(list);
        jsonResult.setMsg("SUCCESS");
        return jsonResult;
    }

    @ApiOperation(value = "渠道管理-所属机构列表", notes = "渠道管理-所属机构列表")
    @RequestMapping(value = "/ChannelOrgAllList_SelectN", method = {RequestMethod.GET})
    public JSONResult ChannelOrgAllList_SelectN(String ProjectID, String pOrgName) {
        List<Map<String, String>> list = channelorgService.ChannelOrgAllList_SelectN(ProjectID, pOrgName);
        JSONResult jsonResult = new JSONResult();
        jsonResult.setData(list);
        jsonResult.setMsg("SUCCESS");
        return jsonResult;
    }

    private void SetExcelN(List<Map<String,Object>> result,Integer PageType) {

        String ExcelName;
        List<ExcelExportEntity> entity;
        if (PageType == 0) {
            ExcelName = "渠道-分销中介";
            entity = new ArrayList<ExcelExportEntity>();
            entity.add(new ExcelExportEntity("经纪人用户名", "UserName"));
            entity.add(new ExcelExportEntity("经纪人姓名", "Name"));
            entity.add(new ExcelExportEntity("性别", "Gender"));
            entity.add(new ExcelExportEntity("经纪人手机号", "Mobile"));
            entity.add(new ExcelExportEntity("证件", "CertificatesTypeName"));
            entity.add(new ExcelExportEntity("证件号", "CertificatesNo"));
            entity.add(new ExcelExportEntity("证件照上", "CertificatesPicFace"));
            entity.add(new ExcelExportEntity("证件照下", "CertificatesPicBack"));
            entity.add(new ExcelExportEntity("开户行", "BankCardCreate"));
            entity.add(new ExcelExportEntity("银行卡号", "BankCard"));
            entity.add(new ExcelExportEntity("银行卡照片", "BankCardPic"));
            entity.add(new ExcelExportEntity("所属机构编码", "ChannelOrgCode"));
            entity.add(new ExcelExportEntity("所属机构", "ChannelOrgName"));
            entity.add(new ExcelExportEntity("渠道身份", "DictName"));
            entity.add(new ExcelExportEntity("注册时间", "CreateTime"));
            entity.add(new ExcelExportEntity("帐号状态", "StatusName"));
            entity.add(new ExcelExportEntity("邀请人", "InviterName"));
            entity.add(new ExcelExportEntity("审核人", "ApproverName"));
            entity.add(new ExcelExportEntity("审核时间", "ApprovalDate"));
            entity.add(new ExcelExportEntity("审核状态", "ApprovalStatuss"));
        } else {
            //推荐渠道
            ExcelName = "渠道-推荐渠道";
            entity = new ArrayList<ExcelExportEntity>();
            entity.add(new ExcelExportEntity("经纪人用户名", "UserName"));
            entity.add(new ExcelExportEntity("经纪人姓名", "Name"));
            entity.add(new ExcelExportEntity("性别", "Gender"));
            entity.add(new ExcelExportEntity("经纪人手机号", "Mobile"));
            entity.add(new ExcelExportEntity("证件", "CertificatesTypeName"));
            entity.add(new ExcelExportEntity("证件号", "CertificatesNo"));
            entity.add(new ExcelExportEntity("证件照上", "CertificatesPicFace"));
            entity.add(new ExcelExportEntity("证件照下", "CertificatesPicBack"));
            entity.add(new ExcelExportEntity("开户行", "BankCardCreate"));
            entity.add(new ExcelExportEntity("银行卡号", "BankCard"));
            entity.add(new ExcelExportEntity("银行卡照片", "BankCardPic"));
            entity.add(new ExcelExportEntity("渠道身份", "DictName"));
            entity.add(new ExcelExportEntity("注册时间", "CreateTime"));
            entity.add(new ExcelExportEntity("帐号状态", "StatusName"));
            entity.add(new ExcelExportEntity("邀请人", "InviterName"));
            entity.add(new ExcelExportEntity("审核人", "ApproverName"));
            entity.add(new ExcelExportEntity("审核时间", "ApprovalDate"));
            entity.add(new ExcelExportEntity("审核状态", "ApprovalStatuss"));
        }


        try {
            LocalDateTime time= LocalDateTime.now();
            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
            String name = dtf2.format(time) + ".xls";
            ExcelUtil.exportExcel(entity,result,name,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * 重复了 都不用

    @ApiOperation(value = "分销中介、推荐渠道列表", notes = "分销中介、推荐渠道列表")
    @RequestMapping(value = "/AgenList_SelectN", method = {RequestMethod.GET})
    public JSONResult AgenList_SelectN(String ProjectID, String IsExcel,
                                       String PageType, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize, String Name,
                                       String UserMobile, String UserCardID,
                                       String UserOrgName, String PassStatu,
                                       String UserStatu, String CreateStartTime,
                                       String CreateEndTime, String ApprovalStartTime,
                                       String ApprovalEndTime, String ApprovalUserID,
                                        String IsHaveChannelOrg,String fieldName,String orderType) {
        StringBuffer Where = new StringBuffer();
        StringBuffer OrderBy = new StringBuffer();
        Page page = new Page(pageNum,pageSize);
        IPage<Map<String, String>> list = channelorgService.AgenList_SelectN(page,Where, OrderBy);
        JSONResult jsonResult = new JSONResult();
        jsonResult.setData(list);
        jsonResult.setMsg("SUCCESS");
        return jsonResult;
    }
//新增或更新渠道机构


     * {"_datatype":"text","_param":{
     * "ProjectID":"cd69e423-63b5-e711-80c7-00505686c900",
     * "OrgID":"a38bd356-5906-4784-9a9e-495cbb64114c",
     * "OrgName":"qqqq","
     * OrgShortName":"qqqq",
     * "Bizlicense":"/Uploads/image/20190627/9486178f28b0352fae139bafc18d370c.png",
     * "LeaderID":"3efb9a91-4ce5-4d46-95eb-8b2cd97fe0f9",
     * "UserName":"qqqq",
     * "Name":"qqqq",
     * "Mobile":"111111111112",
     * "Status":"0",
     * "ProjectIDs":"CD69E423-63B5-E711-80C7-00505686C900",
     * "RuleIDs":["CD69E423-63B5-E711-80C7-00505686C900,835107BA-7B40-4D65-B7DF-6F263CBB5D73"],
     * "UserID":"6C883173-489C-47A4-97D2-3601CB7CEDFD"}}:

    @Deprecated
    @ApiOperation(value = "新增或修改渠道机构", notes = "新增或修改渠道机构")
    @RequestMapping(value = "/ChannelDetail_InsertN", method = {RequestMethod.GET})
    public JSONResult ChannelDetail_InsertN(String ProjectID, String OrgID, String OrgName, String OrgShortName,
                                            String Bizlicense, String LeaderID, String UserName,
                                            String Name, String Mobile, String Status,
                                            String ProjectIDs, String RuleIDs, String UserID) {
        JSONResult jsonResult = new JSONResult();
        //新增
        //首先查询这个项目下机构名称是否重复
        //添加机构和机构负责人信息SQL语句 查询数据库最大值再+1
        String OrgCode = channelorgService.getOrgCode();
        //添加机构SQL
        //添加机构负责人SQL
        //添加这个机构的项目权限SQL语句
        //添加这个机构的规则
        //处理规则参数IDs
        //如果ruleID为空，表示没有在新增机构的时候给这个项目选择现有规则，就跳过这条数据
        //执行拼接好的SQL
        Map<String, String> map = new HashMap();
        map.put("ProjectID", ProjectID);
        map.put("OrgID", OrgID);
        map.put("OrgName", OrgName);
        map.put("OrgShortName", OrgShortName);
        map.put("Bizlicense", Bizlicense);
        map.put("LeaderID", LeaderID);
        map.put("UserName", UserName);
        map.put("Name", Name);
        map.put("Mobile", Mobile);
        map.put("Status", Status);
        map.put("ProjectIDs", ProjectIDs);
        map.put("RuleIDs", RuleIDs);
        map.put("UserID", UserID);
        map.put("OrgCode", OrgCode);
        JSONResult flag = channelorgService.insertOrg(map);
        //修改
        //首先查询这个项目下机构名称是否重复
        //再判断用户名是否重复
        //再判断手机号是否重复
        //添加机构和机构负责人信息SQL语句

        jsonResult.setMsg("SUCCESS");
        return jsonResult;
    }

     */





















    @ApiOperation(value = "分销/推荐渠道列表PageType=0分销,PageType=1推荐渠道", notes = "推荐渠道列表")
    @RequestMapping(value = "/AgenList_SelectN", method = {RequestMethod.GET})
    public Result AgenList_SelectN(Integer PageType, String ProjectID, String ChannelTypeID, String Name, String PassStatu
            , Date CreateStartTime, Date CreateEndTime, String ApprovalUserID,@RequestParam(defaultValue = "1") Integer Pageindex, @RequestParam(defaultValue = "10") Integer Pagesize,String IsExcel){
        if (StringUtils.isNotEmpty(IsExcel)) {
            Pagesize = -1;
        }
        IPage page=new Page(Pageindex,Pagesize);
        IPage<Map<String,Object>> list=channeluserService.AgenList_SelectN(page,PageType,ProjectID,ChannelTypeID,Name,PassStatu
                ,CreateStartTime,CreateEndTime,ApprovalUserID);

        if (StringUtils.isNotEmpty(IsExcel)) {
            SetExcelN(list.getRecords(),PageType);
            return null;
        }

        return Result.ok(list);
    }

    @ApiOperation(value = "分销中介、推荐渠道修改状态", notes = "分销中介、推荐渠道修改状态")
    @RequestMapping(value = "/AgenStatus_UpdateN", method = {RequestMethod.POST})
    public Result AgenStatus_UpdateN(String ID, int Status){
        boolean b=channeluserService.AgenStatus_UpdateN(ID,Status);
        if (b){
            return Result.okm("成功");
        }
        return Result.errormsg(99,"修改失败");
    }
    @ApiOperation(value = "分销中介、推荐渠道列表-身份列表查询", notes = "分销中介、推荐渠道列表-身份列表查询")
    @RequestMapping(value = "/AgenChannelTypeList_SelectN", method = {RequestMethod.GET})
    public Result AgenChannelTypeList_SelectN(){
       List<Map<String,Object>> list=channeluserService.AgenChannelTypeList_SelectN();

        return Result.ok(list);
    }

    @ApiOperation(value = "分销中介、推荐渠道信息编辑/禁用", notes = "分销中介、推荐渠道信息编辑/禁用")
    @RequestMapping(value = "/AgenInfo_UpdateN", method = {RequestMethod.POST})
    public Result AgenInfo_UpdateN(@RequestBody BChanneluser channeluser){
        Result result=channeluserService.AgenInfo_UpdateN(channeluser);
        return result;
    }

    @ApiOperation(value = "分销中介、推荐渠道重置密码", notes = "分销中介、推荐渠道重置密码")
    @RequestMapping(value = "/AgenResetPassword_UpdateN", method = {RequestMethod.POST})
    public Result AgenResetPassword_UpdateN(String ID){
        BChanneluser channeluser=new BChanneluser();
        channeluser.setId(ID);
        channeluser.setEditor(ThreadLocalUtils.getUserName());
        channeluser.setEditeTime(new Date());
        //默认密码 123321
        channeluser.setPassword("C8837B23FF8AAA8A2DDE915473CE0991");
        boolean b = channeluserService.updateById(channeluser);
        if (b){
            return Result.okm("成功");
        }
        return Result.errormsg(99,"修改失败");
    }
}
