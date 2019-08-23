package com.tahoecn.xkc.controller.app;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tahoecn.security.SecureUtil;
import com.tahoecn.xkc.common.utils.NetUtil;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.channel.BChanneluser;
import com.tahoecn.xkc.model.sys.BAppupgrade;
import com.tahoecn.xkc.model.sys.SAccount;
import com.tahoecn.xkc.model.sys.SFormsession;
import com.tahoecn.xkc.service.channel.IBChannelService;
import com.tahoecn.xkc.service.channel.IBChanneluserService;
import com.tahoecn.xkc.service.sys.IBAppupgradeService;
import com.tahoecn.xkc.service.sys.IBVerificationcodeService;
import com.tahoecn.xkc.service.sys.ISAccountService;
import com.tahoecn.xkc.service.sys.ISFormsessionService;
import com.tahoecn.xkc.service.sys.ISLogsService;
import com.tahoecn.xkc.service.user.ISAccountusertypeService;

@RestController
@Api(tags = "ipad-系统接口", value = "ipad-系统接口")
@RequestMapping("/ipad/system")
public class IPadSystemController extends TahoeBaseController{
	
	@Autowired
    private ISLogsService iSLogsService;
	@Autowired
    private IBAppupgradeService iBAppupgradeService;
	@Autowired 
    private ISFormsessionService iSFormsessionService;
	@Autowired
    private IBChannelService iBChannelService;
	@Autowired
    private IBVerificationcodeService iBVerificationcodeService;
	@Autowired
    private IBChanneluserService iBChanneluserService;
	@Autowired
    private ISAccountusertypeService iSAccountusertypeService;
	@Autowired
    private ISAccountService iISAccountService;
	
	@ResponseBody
    @ApiOperation(value = "检查更新", notes = "版本信息")
    @RequestMapping(value = "/mSystemAppVersion_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mSystemAppVersion_Select(@RequestBody JSONObject paramAry) {
		try{
            Map paramMap = (HashMap)paramAry.get("_param");
            String Platform = (String) paramMap.get("Platform");
            String AppName = (String) paramMap.get("AppName");
            String Model = (String) paramMap.get("Model");
            String DeviceCode = (String) paramMap.get("DeviceCode");
    		
            Map<String,Object> map = new HashMap<String,Object>();
    		map.put("Platform", Platform);
    		map.put("AppName", AppName);
    		map.put("Model", Model);
    		
    		//1.APP版本日志记录
    		Map<String,Object> logMap = new HashMap<String,Object>();
            logMap.put("BizType", "AppVersion");
            logMap.put("BizDesc", "获取App版本,平台:" + Platform + ",App:" + AppName);
            logMap.put("Ext1", Model);
            logMap.put("Ext2", DeviceCode);
            logMap.put("Ext3", Platform);
            logMap.put("Ext4", AppName);
            logMap.put("Data", paramAry.toString());
            iSLogsService.SystemLogsDetail_Insert(logMap,request);
    		//2.获取信息
    		List<BAppupgrade> bAppupgrade = iBAppupgradeService.SystemAppVersion_Select(map);
    		
    		return Result.ok(bAppupgrade!= null && bAppupgrade.size()!=0 ?bAppupgrade.get(0):"");
    	}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
    }
	
	@ResponseBody
    @ApiOperation(value = "获取Session", notes = "获取FormSessionID")
    @RequestMapping(value = "/mSystemFormSession_Insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mSystemFormSession_Insert(@RequestBody JSONObject paramAry) {
		try{
			Map paramMap = (HashMap)paramAry.get("_param");
			SFormsession sess = new SFormsession();
			sess.setIp(NetUtil.getRemoteAddr(request));
			sess.setData(JSONObject.toJSONString(paramMap));
			sess.setCreateTime(new Date());
			sess.setStatus(1);
			iSFormsessionService.save(sess);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("FormSessionID", sess.getId());
			return Result.ok(map);
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
    }
	
	@ResponseBody
    @ApiOperation(value = "退出登录", notes = "退出登录")
    @RequestMapping(value = "/mLFLogout_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mLFLogout_Select(@RequestBody JSONObject paramAry) {
		try{
			
			// 直接将json信息打印出来
            System.out.println(paramAry.toJSONString());
            Map paramMap = (HashMap)paramAry.get("_param");
    		//2.登出日志记录
    		Map<String,Object> logMap = new HashMap<String,Object>();
            logMap.put("BizType", "LogoutLFSuccess");
            logMap.put("BizDesc", "来访登出成功,账号:" + (String)paramMap.get("UserName"));
            logMap.put("Ext3", (String)paramMap.get("UserName"));
            logMap.put("Data", paramAry.toJSONString());
            iSLogsService.SystemLogsDetail_Insert(logMap, request);
    		return Result.ok("来访登出成功,账号:" + (String)paramMap.get("UserName"));
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
    }
	@ResponseBody
	@ApiOperation(value = "扫码确认", notes = "扫码确认")
	@RequestMapping(value = "/mCaseFieCustomerDetail_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mCaseFieCustomerDetail_Select(@RequestBody JSONObject paramAry) {
		try{
			return iBChannelService.mCaseFieCustomerDetail_Select(paramAry);
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ResponseBody
    @ApiOperation(value = "忘记密码", notes = "忘记密码")
    @RequestMapping(value = "/mUserForgetPwd_Update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mUserForgetPwd_Update(@RequestBody JSONObject jsonParam) {
    	try{
    		System.out.println("------------------------------------------"+jsonParam);
    		JSONObject paramMap = jsonParam.getJSONObject("_param");
            String Password = (String)paramMap.get("Password");//密码
            String RePassword = (String)paramMap.get("RePassword");//确认密码
            String Mobile = (String)paramMap.get("Mobile");//手机号码
            String AuthCode = (String)paramMap.get("AuthCode");//验证码
            
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("Mobile", Mobile);
            map.put("Password", SecureUtil.md5(Password).toUpperCase());
            //1.校验验证码
            if(!iBVerificationcodeService.Verification(Mobile,AuthCode)){
            	return Result.errormsg(1,"验证码验证失败");
            }
            if(Password.equals(RePassword)){
            	//2.是否为兼职
            	QueryWrapper<BChanneluser> wrapper = new  QueryWrapper<BChanneluser>();
            	wrapper.eq("Mobile", Mobile);
            	List<BChanneluser> Channeluser = iBChanneluserService.list(wrapper);
                if (Channeluser != null && Channeluser.size() > 0){
                	if(Channeluser.size() > 1){
                		return Result.errormsg(91, "根据手机号查询存在多个用户，请联系系统管理员");
                	}else{
	                    iBChanneluserService.ChannelUserForgetPassWord_Update(map);
	                    return Result.ok("用户修改密码成功");
                	}
                }else{
                	//3.查询是否为UC用户
                	QueryWrapper<SAccount> wrapper1 = new  QueryWrapper<SAccount>();
                	wrapper1.eq("Mobile", Mobile);
                	List<SAccount> sAccount = iISAccountService.list(wrapper1);
                	if(sAccount == null || sAccount.size() == 0){
                		return Result.errormsg(91, "用户信息不正确");
                	}else if(sAccount != null && sAccount.size() > 1){
                		return Result.errormsg(91, "根据手机号查询存在多个用户，请联系系统管理员");
                	}else if(sAccount != null && sAccount.get(0).getAccountType() == 1){
                		return Result.errormsg(91, "请到泰信重置修改密码");
                	}else{
                		//4.UC用户修改密码
                		iSAccountusertypeService.SalesUserForgetPwdDetail_Update(map);
                        return Result.ok("用户密码修改成功");
                	}
                }
            }else{
            	return Result.errormsg(90, "密码与确认密码不一致");
            }
    	}catch(Exception e){
    		e.printStackTrace();
    		return Result.errormsg(1, "系统异常，请联系管理员");
    	}
    }
}
