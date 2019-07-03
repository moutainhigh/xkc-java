package com.tahoecn.xkc.controller.webapi.H5;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tahoecn.xkc.common.constants.GlobalConstants;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.service.channel.IBChanneluserService;
import com.tahoecn.xkc.service.customer.IBClueService;
import com.tahoecn.xkc.service.customer.IVABrokerMycustomersService;
import com.tahoecn.xkc.service.project.IABrokerprojectService;
import com.tahoecn.xkc.service.project.IBProjectService;
import com.tahoecn.xkc.service.project.IBProjectcollectionService;
import com.tahoecn.xkc.service.sys.ISystemMessageService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author YYY
 * @since 2019-06-26
 */
@RestController
@RequestMapping("/H5")
public class H5Controller extends TahoeBaseController {

    @Autowired
    private IABrokerprojectService brokerprojectService;
    @Autowired
    private IBProjectService projectService;
    @Autowired
    private IBProjectcollectionService projectcollectionService;
    @Autowired
    private IBChanneluserService channeluserService;
    @Autowired
    private IVABrokerMycustomersService mycustomersService;
    @Autowired
    private ISystemMessageService messageService;
    @Autowired
    private IBClueService clueService;

    //已测
    @ApiOperation(value = "获取城市", notes = "获取城市")
    @RequestMapping(value = "/mBrokerCityList_Select", method = {RequestMethod.POST})
    public Result mBrokerCityList_Select(String AppID) {
        List<HashMap<String, String>> list = brokerprojectService.mBrokerCityList_Select(AppID);
        Result result = new Result();
        result.setErrcode(GlobalConstants.S_CODE);
        result.setErrmsg("成功");
        result.setData(list);
        return result;
        //前两条空,数据问题
    }
//已测
    @ApiOperation(value = "获取房源项目列表", notes = "获取房源项目列表")
    @RequestMapping(value = "/mBrokerProjectList_SelectN", method = {RequestMethod.POST})
    public Result mBrokerProjectList_SelectN(String OrgID, String Name, String CityID,
                                             int PageIndex, int PageSize) {
        //获取是否是机构的人
        //不为空表示登录进来的肯定是机构的人
        List<Map<String, Object>> list;
        IPage page=new Page(PageIndex,PageSize);
        if (StringUtils.isNotBlank(OrgID)) {
            list = projectService.findByOrgID(page,OrgID);
        } else {
            list = projectService.ProjectInfoList_SelectN(page,Name, CityID);
        }
        Map<String, Object> map=new HashMap<>();
        map.put("List",list);
        Result result = new Result();
        result.setErrcode(GlobalConstants.S_CODE);
        result.setErrmsg("成功");
        result.setData(map);
        return result;
    }

    //已测
    @ApiOperation(value = "首页-房源详情列表", notes = "首页-房源详情列表")
    @RequestMapping(value = "/mBrokerProjectDetail_Select", method = {RequestMethod.POST})
    public Result mBrokerProjectDetail_Select(String BrokerProjectID, String ChannelOrgID) {
        HashMap<String, Object> map = brokerprojectService.mBrokerProjectDetail_Select(BrokerProjectID, ChannelOrgID);
        Result result = new Result();
        result.setErrcode(GlobalConstants.S_CODE);
        result.setErrmsg("成功");
        result.setData(map);
        return result;
    }

    //已测
    @ApiOperation(value = "当前项目是否被收藏", notes = "当前项目是否被收藏")
    @RequestMapping(value = "/mBrokerProjectCollectionIsExist_Select", method = {RequestMethod.POST})
    public Result mBrokerProjectCollectionIsExist_Select(String ProjectID, String UserID) {
        Result result = new Result();
        if (StringUtils.isBlank(ProjectID)) {
            result.setErrcode(GlobalConstants.E_CODE);
            result.setErrmsg("项目ID不可以为空");
            return result;
        }
        if (StringUtils.isBlank(UserID)) {
            result.setErrcode(GlobalConstants.E_CODE);
            result.setErrmsg("用户ID不可以为空");
            return result;
        }
        int total = projectcollectionService.mBrokerProjectCollectionIsExist_Select(ProjectID, UserID);
        Map map = new HashMap();
        map.put("total", total);
        result.setErrcode(GlobalConstants.S_CODE);
        result.setErrmsg("成功");
        result.setData(map);
        return result;
    }

    @ApiOperation(value = "登录账号", notes = "登录账号")
    @RequestMapping(value = "/mLoginTK_SelectN", method = {RequestMethod.POST})
    public Result mLoginTK_SelectN(String Mobile, String Password) {
        Date date = new Date();
        String time = String.format(date.toString(), "yyyyMMddHHmm");
        Map<String, Object> map = new HashMap();
        if (StringUtils.equals(time, Password)) {
            map = channeluserService.mLoginTK_SelectN(Mobile);
        } else {
            //未完成
        }
        Result result = new Result();
        result.setErrcode(GlobalConstants.S_CODE);
        result.setErrmsg("成功");
        result.setData(map);
        return result;
    }

    @ApiOperation(value = "微信-JSAPI包信息", notes = "微信-JSAPI包信息")
    @RequestMapping(value = "/mWeixinJsApiPackage_Select", method = {RequestMethod.POST})
    public Result mWeixinJsApiPackage_Select(String BrokerProjectID, String ChannelOrgID) {


        Result result = new Result();
        result.setErrcode(GlobalConstants.S_CODE);
        result.setErrmsg("成功");
//        result.setData(map);
        return result;
    }

    //已测
    @ApiOperation(value = "获取个人中心的统计数据", notes = "获取个人中心的统计数据")
    @RequestMapping(value = "/mBrokerMyCenter_Select", method = {RequestMethod.POST})
    public Result mBrokerMyCenter_Select(String BrokerID) {
        Map<String, Object> map = channeluserService.BrokerMyCenter_Select(BrokerID);
        Result result = new Result();
        result.setErrcode(GlobalConstants.S_CODE);
        result.setErrmsg("成功");
        result.setData(map);
        return result;
    }


    //已测
    @ApiOperation(value = "获取我的客户列表", notes = "获取我的客户列表")
    @RequestMapping(value = "/mGetMyCustomers_Select", method = {RequestMethod.POST})
    public Result mGetMyCustomers_Select(int Sort, String Filter, String CustomerInfo, String BrokerID,
                                         int PageIndex, int PageSize) {
        IPage page = new Page(PageIndex, PageSize);
        List<Map<String, Object>> list = mycustomersService.mGetMyCustomers_Select(page, Sort, Filter, CustomerInfo, BrokerID);
        Result result = new Result();
        result.setErrcode(GlobalConstants.S_CODE);
        result.setErrmsg("成功");
        result.setData(list);
        return result;
    }

    //已测
    @ApiOperation(value = "获取渠道人员", notes = "获取渠道人员")
    @RequestMapping(value = "/mBrokerChannelUserDetail_Select", method = {RequestMethod.POST})
    public Result mBrokerChannelUserDetail_Select(String UserID) {
        Result result = new Result();
        Map<String, Object> map = channeluserService.mBrokerChannelUserDetail_Select(UserID);
        result.setErrcode(GlobalConstants.S_CODE);
        result.setData(map);
        result.setErrmsg("成功");
        return result;
    }

    //未测,需完成上传
    @ApiOperation(value = "修改个人证件信息", notes = "修改个人证件信息")
    @RequestMapping(value = "/mBrokerChannelUserCardDetail_Update", method = {RequestMethod.POST})
    public Result mBrokerChannelUserCardDetail_Update(String UserID, String CertificatesName, String CertificatesType,
                                                      String CertificatesNo, String CertificatesPicFace, String CertificatesPicBack) {
        int i = channeluserService.mBrokerChannelUserCardDetail_Update(UserID, CertificatesName, CertificatesType, CertificatesNo, CertificatesPicFace, CertificatesPicBack);
        Result result = new Result();
        result.setErrcode(GlobalConstants.S_CODE);
        result.setErrmsg("成功");
//        result.setData(list);
        return result;
    }

    //已测 但IsRead 值待确定
    @ApiOperation(value = "消息列表", notes = "消息列表")
    @RequestMapping(value = "/mMessageAllList_Select", method = {RequestMethod.POST})
    public Result mMessageAllList_Select(String UserID, int PageIndex, int PageSize) {

        IPage page = new Page(PageIndex, PageSize);
        List<Map<String, Object>> list = messageService.mMessageAllList_Select(page, UserID);
        Result result = new Result();
        result.setErrcode(GlobalConstants.S_CODE);
        result.setErrmsg("成功");
        result.setData(list);
        return result;
    }

    @ApiOperation(value = "查询用户收藏的所有项目", notes = "查询用户收藏的所有项目")
    @RequestMapping(value = "/mBrokerProjectCollectionList_Select", method = {RequestMethod.POST})
    public Result mBrokerProjectCollectionList_Select(String UserID, int PageIndex, int PageSize) {
        Result result = new Result();
        if (StringUtils.isBlank(UserID)) {
            result.setErrcode(GlobalConstants.E_CODE);
            result.setErrmsg("用户ID不可以为空");
            return result;
        }
        IPage page = new Page(PageIndex, PageSize);
        List<Map<String, Object>> list = projectcollectionService.mBrokerProjectCollectionList_Select(page, UserID);
        result.setErrcode(GlobalConstants.S_CODE);
        result.setErrmsg("成功");
        result.setData(list);
        return result;
    }

    //未测
    @ApiOperation(value = "修改个人银行卡信息", notes = "修改个人银行卡信息")
    @RequestMapping(value = "/mBrokerChannelUserBankCardDetail_Update", method = {RequestMethod.POST})
    public Result mBrokerChannelUserBankCardDetail_Update(String UserID, String BankCardPerson, String BankCardCreate, String BankCard, String BankCardProvince,
                                                          String BankCardCity, String BankCardArea, String BankCardBranch, String BankCardPic) {

        int i = channeluserService.mBrokerChannelUserBankCardDetail_Update(UserID, BankCardPerson, BankCardCreate, BankCard, BankCardProvince,
                BankCardCity, BankCardArea, BankCardBranch, BankCardPic);
        Result result = new Result();
        if (i == 1) {
            result.setErrcode(GlobalConstants.S_CODE);
            result.setErrmsg("成功");
            return result;
        }
        result.setErrcode(GlobalConstants.E_CODE);
        result.setErrmsg("修改失败");
        return result;
    }

    //
    @ApiOperation(value = "生成FormSessionID", notes = "生成FormSessionID")
    @RequestMapping(value = "/mSystemFormSession_Insert", method = {RequestMethod.POST})
    public Result mSystemFormSession_Insert(String UserID, int PageIndex, int PageSize) {
        //将FormSessionID IP Data存入表S_FormSession 其中Data为传入参数  ID为生成

        Result result = new Result();
        result.setErrcode(GlobalConstants.S_CODE);
        result.setErrmsg("成功");
//        result.setData(list);
        return result;
    }

    //未完成
    @ApiOperation(value = "渠道报备客户--提交", notes = "渠道报备客户--提交")
    @RequestMapping(value = "/mBrokerReport_Insert", method = {RequestMethod.POST})
    public Result mBrokerReport_Insert(String UserID) {
        Result result = new Result();

        result.setErrcode(GlobalConstants.S_CODE);
//        result.setData(map);
        result.setErrmsg("成功");
        return result;
    }

    //未测  第二条语句卡住
    @ApiOperation(value = "获取客户详情", notes = "获取客户详情")
    @RequestMapping(value = "/mBrokerCustomerDetail_Select", method = {RequestMethod.POST})
    public Result mBrokerCustomerDetail_Select(String UserID, String ClueID) {
        Result result = new Result();
        Map<String, Object> map = clueService.mBrokerCustomerDetail_Select(ClueID);
        result.setErrcode(GlobalConstants.S_CODE);
        result.setData(map);
        result.setErrmsg("成功");
        return result;
    }

    //未测
    @ApiOperation(value = "修改个人基本信息", notes = "修改个人基本信息")
    @RequestMapping(value = "/mBrokerChannelUserDetail_Upate", method = {RequestMethod.POST})
    public Result mBrokerChannelUserDetail_Upate(String UserID, String Name, String Gender, String Mobile, String ChannelTypeID) {
        Result result = new Result();
        int i = channeluserService.mBrokerChannelUserDetail_Upate(UserID, Name, Gender, Mobile, ChannelTypeID);
        if (i == 1) {
            result.setData(true);
            result.setErrcode(GlobalConstants.S_CODE);
            result.setErrmsg("成功");
            return result;
        } else {
            result.setErrcode(GlobalConstants.E_CODE);
            result.setData(false);
            result.setErrmsg("修改失败");
            return result;
        }
    }

    //已测
    @ApiOperation(value = "项目收藏", notes = "项目收藏")
    @RequestMapping(value = "/BrokerProjectCollection_Insert", method = {RequestMethod.POST})
    public Result BrokerProjectCollection_Insert(String UserID, String ProjectID) {
        //ProjectID：这里目前前台传了A_BrokerProject表的主键ID，注意一下，不是项目表的ID
        Result result = new Result();
        if (StringUtils.isBlank(UserID)) {
            result.setErrcode(GlobalConstants.E_CODE);
            result.setErrmsg("用户ID不可以为空");
            return result;
        }
        if (StringUtils.isBlank(ProjectID)) {
            result.setErrcode(GlobalConstants.E_CODE);
            result.setErrmsg("项目ID不可以为空");
            return result;
        }
        int i = projectcollectionService.BrokerProjectCollection_Insert(UserID, ProjectID);
        if (i > 0) {
            result.setErrcode(GlobalConstants.S_CODE);
            result.setErrmsg("成功");
            return result;
        } else {
            result.setErrcode(GlobalConstants.E_CODE);
            result.setErrmsg("项目收藏失败");
            return result;
        }
    }

    //已测
    @ApiOperation(value = "取消项目收藏", notes = "取消项目收藏")
    @RequestMapping(value = "/mBrokerProjectCollection_Delete", method = {RequestMethod.POST})
    public Result mBrokerProjectCollection_Delete(String UserID, String ProjectID) {
        //ProjectID：这里目前前台传了A_BrokerProject表的主键ID，注意一下，不是项目表的ID
        Result result = new Result();
        if (StringUtils.isBlank(UserID)) {
            result.setErrcode(GlobalConstants.E_CODE);
            result.setErrmsg("用户ID不可以为空");
            return result;
        }
        if (StringUtils.isBlank(ProjectID)) {
            result.setErrcode(GlobalConstants.E_CODE);
            result.setErrmsg("项目ID不可以为空");
            return result;
        }
        int i = projectcollectionService.BrokerProjectCollection_Delete(UserID, ProjectID);
        if (i > 0) {
            result.setErrcode(GlobalConstants.S_CODE);
            result.setErrmsg("成功");
            return result;
        } else {
            result.setErrcode(GlobalConstants.E_CODE);
            result.setErrmsg("项目收藏失败");
            return result;
        }
    }


}
