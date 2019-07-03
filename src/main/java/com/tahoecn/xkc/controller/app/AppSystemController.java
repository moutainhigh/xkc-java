package com.tahoecn.xkc.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.ResponseMessage;
import com.tahoecn.xkc.model.sys.BAppupgrade;
import com.tahoecn.xkc.model.sys.BSystemad;
import com.tahoecn.xkc.service.sys.IBAppupgradeService;
import com.tahoecn.xkc.service.sys.IBSystemadService;
import com.tahoecn.xkc.service.sys.ISLogsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.List;
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
@Api(tags = "APP-系统接口", value = "APP-系统接口")
@RequestMapping("/app/systemAD")
public class AppSystemController extends TahoeBaseController {

    @Autowired
    private IBSystemadService iBSystemadService;
    @Autowired
    private IBAppupgradeService iBAppupgradeService;
    @Autowired
    private ISLogsService iSLogsService;

	@ResponseBody
    @ApiOperation(value = "广告接口", notes = "广告接口")
    @RequestMapping(value = "/SystemAD_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage SystemAD_Select(@RequestBody JSONObject jsonParam) {
    	try{
    		// 直接将json信息打印出来
            System.out.println(jsonParam.toJSONString());
            Map paramMap = (HashMap)jsonParam.get("_param");
            String ADType = (String)paramMap.get("ADType").toString();
    		Map<String,Object> map = new HashMap<String,Object>();
    		map.put("ADType", ADType);
    		List<BSystemad> ataAd = iBSystemadService.SystemAD_Detail_Find(map);//获取一条信息-(默认)
    		return ResponseMessage.ok(ataAd!= null && ataAd.size()!=0 ?ataAd.get(0):"");
    	}catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("系统异常，请联系管理员");
		}
    }
	
	@ResponseBody
    @ApiOperation(value = "APP版本信息", notes = "APP版本信息")
    @RequestMapping(value = "/mSystemAppVersion_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage mSystemAppVersion_Select(@RequestBody JSONObject jsonParam) {
    	try{
            Map paramMap = (HashMap)jsonParam.get("_param");
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
            logMap.put("Data", jsonParam.toString());
            iSLogsService.SystemLogsDetail_Insert(logMap,request);
    		//2.获取信息
    		List<BAppupgrade> bAppupgrade = iBAppupgradeService.SystemAppVersion_Select(map);
    		
    		return ResponseMessage.ok(bAppupgrade!= null && bAppupgrade.size()!=0 ?bAppupgrade.get(0):"");
    	}catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("系统异常，请联系管理员");
		}
    }
}
