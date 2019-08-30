package com.tahoecn.xkc.controller.app;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tahoecn.security.SecureUtil;
import com.tahoecn.xkc.common.utils.JwtTokenUtil;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.sys.BVerificationcode;
import com.tahoecn.xkc.model.sys.SAccount;
import com.tahoecn.xkc.model.sys.SAppdevice;
import com.tahoecn.xkc.service.channel.IBChanneluserService;
import com.tahoecn.xkc.service.salegroup.IBSalesuserService;
import com.tahoecn.xkc.service.sys.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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
@Api(tags = "ipad-登录接口", value = "ipad-登录接口")
@RequestMapping("/ipad/login")
public class LoginIpadController extends TahoeBaseController {

    @Autowired
    private ISLogsService iSLogsService;

    @Autowired
    private ISAccountService accountService;

    @Autowired
    private IBSalesuserService salesuserService;

    @Autowired
    private IBVerificationcodeService verificationcodeService;

    @Value("MobileSiteUrl")
    private String MobileSiteUrl;

    @Value("CaseLinkageUrl")
    private String CaseLinkageUrl;

    @Value("${uc_api_url}")
    private String baseUrl;

    @Value("${uc_sysId}")
    private String sysId;

    @Value("${uc_priv_key}")
    private String privKey;

    @Value("${ImgSiteUrl}")
    private String imgSiteUrl;

    @ApiOperation(value = "ipad移动端来访登录", notes = "ipad移动端来访登录")
    @RequestMapping(value = "/mLFLogin_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mLFLogin_Select(@RequestBody JSONObject jsonParam) {
        Map<String,Object> paramMap = (HashMap)jsonParam.get("_param");
        //登录日志记录
        String UserName= (String) paramMap.get("UserName");


        String MobileNum = (String) paramMap.get("MobileNum");
        String Code = (String) paramMap.get("Code");

        if (StringUtils.isNotEmpty(MobileNum)) {
            if (StringUtils.isEmpty(Code)){
                return Result.errormsg(1, "请输入验证码");
            }
            BVerificationcode vc = verificationcodeService.checkAuthCode(MobileNum);
            if (vc == null || !StringUtils.equals(Code, vc.getVerificationCode())) {
                return Result.errormsg(1, "验证码验证失败");
            }
            paramMap.put("Mobile",MobileNum);
        }



        Map<String, Object> log=new HashMap<>();
        log.put("BizType","LoginLF");
        log.put("BizDesc","来访登录成功,账号:"+UserName);
        log.put("Data",jsonParam.toJSONString());
        log.put("Ext3",UserName);

        iSLogsService.SystemLogsDetail_Insert(log,request);
        if (paramMap.get("Password")!=null){
            String Password = (String) paramMap.get("Password");
            paramMap.put("Password",SecureUtil.md5(Password));
        }
        Map<String,Object> res=salesuserService.mLFLogin_Select(paramMap);
        if (res==null){
        return Result.errormsg(9,"用户名不存在");
        }
        //是否开启分接/销支 0.开启 1.关闭
        short isNoAllotRole = (short) res.get("IsNoAllotRole");
        //允许登录设备类型 0.都不允许 1.只允许APP登录 2.只允许ipad登录 3.允许所有设备登录
        short allowDeviceType = (short) res.get("AllowDeviceType");
        String jobCode = (String) res.get("JobCode");
        short accountType = (short) res.get("AccountType");
        String password = (String) paramMap.get("Password");
        String projectID = (String) res.get("ProjectID");

        // 0.禁用 1.开启
        short accountStatus = (short) res.get("AccountStatus");
        if (isNoAllotRole == 1 && ("XSZC").equals(jobCode)){
            return Result.errormsg(10,"请联系管理员开通销管角色");
        }
        if (allowDeviceType != 2 && allowDeviceType != 3)
        {
            return Result.errormsg(10,"无法登录,此账号所属项目没有开通ipad登录权限");
        }
        if (accountStatus == 0)
        {
            return Result.errormsg(10,"该账号已被禁用");
        }
        if (DateUtil.format(new Date(),"yyyyMMddHHmm").equals(password)){
            if (accountType==1)
            {
                // 用户验证
                if (StringUtils.isEmpty(Code)) {
                    String s = accountService.checkUCUser(UserName, password);
                    JSONObject ucResult = JSONObject.parseObject(s);
                    if (0 != ucResult.getInteger("code")) {
                        return Result.errormsg(11, "登录异常" + ucResult.getString("msg"));
                    }
                }
            }
            else {
                if (StringUtils.isEmpty(Code)) {
                    String Password = (String) res.get("Password");
                    String RePassword = (String) res.get("RePassword");
                    if (StringUtils.equals(Password, RePassword)) {
                        return Result.errormsg(10, "用户名密码不正确");
                    }
                }
            }
        }
        if (paramMap.get("UserID")!=null){
            paramMap.put("UserID",res.get("UserID"));
        }else {
            paramMap.put("UserID",res.get("UserID"));
        }
        String headImg = (String) res.get("HeadImg");
        String newHeadImg=imgSiteUrl+headImg;
        res.put("HeadImg",newHeadImg);

        //登录成功日志记录
        Map<String, Object> logMap=new HashMap<>();
        logMap.put("BizType","LoginLFSuccess");
        logMap.put("BizDesc","来访登录成功,账号:"+paramMap.get("UserName"));
        logMap.put("Data",jsonParam.toJSONString());
        logMap.put("Ext3",(String) paramMap.get("UserName"));
        iSLogsService.SystemLogsDetail_Insert(logMap,request);
        //移除无用信息
        res.remove("Password");
        res.remove("RePassword");
        res.remove("ChannelTypeID");
        res.remove("AccountStatus");
        return Result.ok(res);
    }
}
