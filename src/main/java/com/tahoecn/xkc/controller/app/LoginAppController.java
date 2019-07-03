package com.tahoecn.xkc.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.ResponseMessage;
import com.tahoecn.xkc.service.sys.ISAppdeviceService;
import com.tahoecn.xkc.service.sys.ISLogsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YYY
 * @since 2019-06-25
 */
@RestController
@Api(tags = "APP-登录接口", value = "APP-登录接口")
@RequestMapping("/app/login")
public class LoginAppController extends TahoeBaseController {

    @Autowired
    private ISAppdeviceService iSAppdeviceService;
    @Autowired
    private ISLogsService iSLogsService;

	@ResponseBody
    @ApiOperation(value = "登出", notes = "登出")
    @RequestMapping(value = "/mUserLogout_Insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage mUserLogout_Insert(@RequestBody JSONObject jsonParam) {
    	try{
    		// 直接将json信息打印出来
            System.out.println(jsonParam.toJSONString());
            Map paramMap = (HashMap)jsonParam.get("_param");
            String jobCode = (String)paramMap.get("JobCode");
    		Map<String,Object> map = new HashMap<String,Object>();
    		map.put("UserID", (String)paramMap.get("UserID"));
    		map.put("DeviceCode", (String)paramMap.get("DeviceCode"));
    		map.put("Platform", (String)paramMap.get("Platform"));
    		map.put("AppName", (String)paramMap.get("AppName"));
    		//1.解绑设备信息
    		iSAppdeviceService.SystemAppDeviceDetail_Update(map);
    		//2.登出日志记录
    		Map<String,Object> logMap = new HashMap<String,Object>();
            logMap.put("BizType", "LogoutACSuccess");
            logMap.put("BizDesc", "案场登出成功,账号:" + (String)paramMap.get("UserName"));
            logMap.put("Ext1", (String)paramMap.get("Model"));
            logMap.put("Ext2", (String)paramMap.get("DeviceCode"));
            logMap.put("Ext3", (String)paramMap.get("UserName"));
            logMap.put("Ext4", (String)paramMap.get("AppName"));
            logMap.put("Data", jsonParam);
            iSLogsService.SystemLogsDetail_Insert(logMap, request);
            
    		return ResponseMessage.ok("案场登出成功,账号:" + (String)paramMap.get("UserName"));
    	}catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("系统异常，请联系管理员");
		}
    }
}
