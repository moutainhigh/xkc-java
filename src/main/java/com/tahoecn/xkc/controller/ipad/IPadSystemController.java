package com.tahoecn.xkc.controller.ipad;

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
import com.tahoecn.xkc.common.utils.NetUtil;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.sys.BAppupgrade;
import com.tahoecn.xkc.model.sys.SFormsession;
import com.tahoecn.xkc.service.channel.IBChannelService;
import com.tahoecn.xkc.service.sys.IBAppupgradeService;
import com.tahoecn.xkc.service.sys.ISFormsessionService;
import com.tahoecn.xkc.service.sys.ISLogsService;

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
}
