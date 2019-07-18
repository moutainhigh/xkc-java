package com.tahoecn.xkc.controller.app;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.channel.BChanneltask;
import com.tahoecn.xkc.model.channel.BChanneltaskarea;
import com.tahoecn.xkc.service.channel.IBChanneltaskService;
import com.tahoecn.xkc.service.channel.IBChanneltaskareaService;
import com.tahoecn.xkc.service.channel.IBChanneluserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YYY
 * @since 2019-07-04
 */
@RestController
@Api(tags = "APP-客储接口", value = "APP-客储接口")
@RequestMapping("/app/KC")
public class AppKCController extends TahoeBaseController {
	
	@Value("${SiteUrl}")
	private String SiteUrl;
	
    @Autowired
    private IBChanneltaskService iBChanneltaskService;

    @Autowired
    private IBChanneltaskareaService iBChanneltaskareaService;
    
    @Autowired
    private IBChanneluserService iBChanneluserService;

	@ResponseBody
    @ApiOperation(value = "新建任务", notes = "新建任务")
    @RequestMapping(value = "/mChannelTask_Insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mChannelTask_Insert(@RequestBody JSONObject jsonParam) {
		Result re=new Result();
    	try{
    		// 直接将json信息打印出来
    		System.out.println(jsonParam.toJSONString());
            Map paramMap = (HashMap)jsonParam.get("_param");
            String ChannelTypeID = (String)paramMap.get("ChannelTypeID").toString();//
            String CustomerTarget = (String)paramMap.get("CustomerTarget").toString();//
            String EndTime = (String)paramMap.get("EndTime").toString();//
            String Name = (String)paramMap.get("Name").toString();//
            String StartTime = (String)paramMap.get("StartTime").toString();//
            String TaskAreaID = (String)paramMap.get("TaskAreaID").toString();//
            String TaskAreaName = (String)paramMap.get("TaskAreaName").toString();//
            String WorkEndTime = (String)paramMap.get("WorkEndTime").toString();//
            String WorkRange = (String)paramMap.get("WorkRange").toString();//
            String WorkStartTime = (String)paramMap.get("WorkStartTime").toString();//
            String Platform = (String)paramMap.get("Platform").toString();//
            String ProjectID = (String)paramMap.get("ProjectID").toString();//
            String UserID = (String)paramMap.get("UserID").toString();//
            
    		Map<String,Object> map = new HashMap<String,Object>();
    		map.put("ChannelTypeID", ChannelTypeID);
    		map.put("CustomerTarget", CustomerTarget);
    		map.put("EndTime", EndTime);
    		map.put("Name", Name);
    		map.put("StartTime", StartTime);
    		map.put("TaskAreaID", TaskAreaID);
    		map.put("TaskAreaName", TaskAreaName);
    		map.put("WorkEndTime", WorkEndTime);
    		map.put("WorkRange", WorkRange);
    		map.put("WorkStartTime", WorkStartTime);
    		map.put("Platform", Platform);
    		map.put("ProjectID", ProjectID);
    		map.put("UserID", UserID);
    		String ChannelTaskID = getUUID();
    		System.out.println(ChannelTaskID);
    		map.put("ChannelTaskID", ChannelTaskID);
    		String TaskCode = iBChanneltaskService.mChannelTask_Insert(map);
    		int TaskCode1 = Integer.parseInt(TaskCode); 
    		if(TaskCode != null) {
    			TaskCode1++;
    			TaskCode = String.valueOf(TaskCode1);
    		}
    		else {
    			TaskCode = "100001";
    		}
    		
    		map.put("TaskCode",  TaskCode);
    	
    		
    		iBChanneltaskService.mChannelTask_Insert2(map);
    		iBChanneltaskService.mChannelTask_Insert3(map);
    		Map<String, Object> obj = iBChanneltaskService.mChannelTask_Insert4(map);
    		
    		return Result.ok(obj);
    	}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
    }
	
	@ResponseBody
    @ApiOperation(value = "新建任务地点", notes = "新建任务地点")
    @RequestMapping(value = "/mChannelTaskArea_Insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mChannelTaskArea_Insert(@RequestBody JSONObject jsonParam) {
		Result re=new Result();
    	try{
    		// 直接将json信息打印出来
    		System.out.println(jsonParam.toJSONString());
            Map paramMap = (HashMap)jsonParam.get("_param");
            String Longitude = (String)paramMap.get("Longitude").toString();//经度
            String Dimension = (String)paramMap.get("Dimension").toString();//纬度
            String UserID = (String)paramMap.get("UserID").toString();//用户ID
    		Map<String,Object> map = new HashMap<String,Object>();
    		map.put("Longitude", Longitude);
    		map.put("Dimension", Dimension);
    		map.put("UserID", UserID);
    		
    		iBChanneltaskareaService.mChannelTaskArea_Insert(map);
    		
    		return re.ok("添加成功");
    		
    	}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1, "系统异常，请联系管理员");
			
		}
    }
	
	@ResponseBody
    @ApiOperation(value = "任务地点列表", notes = "任务地点列表")
    @RequestMapping(value = "/mChannelTaskAreaList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mChannelTaskAreaList_Select(@RequestBody JSONObject jsonParam) {
		Map<String,Object> result=new HashMap<String,Object>();
		Result re=new Result();
    	try{
    		// 直接将json信息打印出来
    		System.out.println(jsonParam.toJSONString());
            Map paramMap = (HashMap)jsonParam.get("_param");
            String UserID = (String)paramMap.get("UserID").toString();//用户ID
            int PageIndex = (int)paramMap.get("PageIndex");//页面索引
            int PageSize = (int)paramMap.get("PageSize");//每页数量
            
            IPage page = new Page(PageIndex, PageSize);
    		Map<String,Object> map = new HashMap<String,Object>();
    		map.put("UserID", UserID);
            
    		int AllCount=iBChanneltaskareaService.mChannelTaskAreaList_SelectAllCount(map);
    		List<BChanneltaskarea> a = iBChanneltaskareaService.mChannelTaskAreaList_Select(page, UserID);
    		result.put("List", a);
    		result.put("AllCount", AllCount);
    		result.put("PageSize", PageSize);
    		
    		return re.ok(result);
    		
    	}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1, "系统异常，请联系管理员");
			
		}
    }
	

	
	@ResponseBody
    @ApiOperation(value = "结束任务", notes = "结束任务")
    @RequestMapping(value = "/mChannelTaskClose_Update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mChannelTaskClose_Update(@RequestBody JSONObject jsonParam) {
		Result re=new Result();
    	try{
    		// 直接将json信息打印出来
    		System.out.println(jsonParam.toJSONString());
            Map paramMap = (HashMap)jsonParam.get("_param");
            String UserID = (String)paramMap.get("UserID").toString();//用户ID
            String ChannelTaskID = (String)paramMap.get("ChannelTaskID").toString();//任务ID
    		Map<String,Object> map = new HashMap<String,Object>();
    		map.put("UserID", UserID);
    		map.put("ChannelTaskID", ChannelTaskID);
    		
    		iBChanneltaskService.mChannelTaskClose_Update(map);
    		iBChanneltaskService.mChannelTaskClose_Update2(map);
    		List<BChanneltask> a = iBChanneltaskService.mChannelTaskClose_Update3(map);
    		return re.ok(a);
    		
    	}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1, "系统异常，请联系管理员");
			
		}
    }
	
	@ResponseBody
    @ApiOperation(value = "删除兼职成员", notes = "删除兼职成员")
    @RequestMapping(value = "/mChannelTempPerson_Delete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mChannelTempPerson_Delete(@RequestBody JSONObject jsonParam) {
		Result re=new Result();
    	try{
    		// 直接将json信息打印出来
    		System.out.println(jsonParam.toJSONString());
            Map paramMap = (HashMap)jsonParam.get("_param");
            String UserID = (String)paramMap.get("UserID").toString();//用户ID
            String ChannelUserID = (String)paramMap.get("ChannelUserID").toString();//渠道用户ID
    		Map<String,Object> map = new HashMap<String,Object>();
    		map.put("UserID", UserID);
    		map.put("ChannelUserID", ChannelUserID);
    		
    		iBChanneluserService.mChannelTempPerson_Delete(map);
    		return re.ok(true);
    		
    	}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1, "系统异常，请联系管理员");
			
		}
    }
	
	@ResponseBody
    @ApiOperation(value = "专员考勤", notes = "专员考勤")
    @RequestMapping(value = "/mChannelCheckClockTotal_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mChannelCheckClockTotal_Select(@RequestBody JSONObject jsonParam) {
		Result re=new Result();
    	try{
    		// 直接将json信息打印出来
    		System.out.println(jsonParam.toJSONString());
    		Map paramMap = (HashMap)jsonParam.get("_param");
    		String ChannelTaskID = (String)paramMap.get("ChannelTaskID").toString();//任务ID
            String CheckDate = (String)paramMap.get("CheckDate").toString();//
            int PageIndex = (int)paramMap.get("PageIndex");//页面索引
            int PageSize = (int)paramMap.get("PageSize");//每页数量
            
            IPage page = new Page(PageIndex, PageSize);
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("ChannelTaskID", ChannelTaskID);
    		map.put("CheckDate", CheckDate);
    		List<Map<String,Object>> obj = iBChanneluserService.mChannelCheckClockTotal_Select(page, ChannelTaskID,CheckDate);
    		if(obj != null && obj.size() > 0) {
    			return re.ok(obj);
    		}
    		else {
    			return re.ok("没有考勤记录");
    		}
    	}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1, "系统异常，请联系管理员");
			
		}
    }
	
	@ResponseBody
    @ApiOperation(value = "详细考勤", notes = "详细考勤")
    @RequestMapping(value = "/mChannelTaskCheckClockList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mChannelTaskCheckClockList_Select(@RequestBody JSONObject jsonParam) {
		Map<String, Object> result=new HashMap<String,Object>();
		Result re=new Result();
    	try{
    		// 直接将json信息打印出来
    		System.out.println(jsonParam.toJSONString());
    		Map paramMap = (HashMap)jsonParam.get("_param");
    		String ChannelTaskID = (String)paramMap.get("ChannelTaskID").toString();//任务ID
            String CheckDate = (String)paramMap.get("CheckDate").toString();//
            int PageIndex = 1;//页面索引
            int PageSize = 99999;//每页数量
            
            IPage page = new Page(PageIndex, PageSize);
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("ChannelTaskID", ChannelTaskID);
    		map.put("CheckDate", CheckDate);
    		int AllCount=iBChanneluserService.mChannelTaskCheckClockList_SelectAllCount(map);
    		List<Map<String,Object>> obj = iBChanneluserService.mChannelTaskCheckClockList_Select(page, ChannelTaskID,CheckDate);
    		result.put("List", obj);
    		result.put("AllCount", AllCount);
    		result.put("PageSize", PageSize);
    		return re.ok(result);
    		
    	}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1, "系统异常，请联系管理员");
			
		}
    }
	
	@ResponseBody
    @ApiOperation(value = "打卡状态查询", notes = "打卡状态查询")
    @RequestMapping(value = "/mChannelCheckClockPage_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mChannelCheckClockPage_Select(@RequestBody JSONObject jsonParam) {
		Result re=new Result();
    	try{
    		// 直接将json信息打印出来
    		System.out.println(jsonParam.toJSONString());
    		Map paramMap = (HashMap)jsonParam.get("_param");
    		String UserID = (String)paramMap.get("UserID").toString();//用户ID
            String CheckDate = (String)paramMap.get("CheckDate").toString();//打卡日期
            String ChannelTaskID = (String)paramMap.get("ChannelTaskID").toString();//任务ID
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("UserID", UserID);
            map.put("CheckDate", CheckDate);
            map.put("ChannelTaskID", ChannelTaskID);
    		List<Map<String,Object>> a = iBChanneluserService.mChannelCheckClockPage_Select(map);
    		return re.ok(a);
    	}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1, "系统异常，请联系管理员");
			
		}
    }
	
	@ResponseBody
    @ApiOperation(value = "考勤信息查询", notes = "考勤信息查询")
    @RequestMapping(value = "/mChannelCheckClockPerson_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mChannelCheckClockPerson_Select(@RequestBody JSONObject jsonParam) {
		Map<String, Object> result = new HashMap<String, Object>();
		Result re=new Result();
    	try{
    		// 直接将json信息打印出来
    		System.out.println(jsonParam.toJSONString());
    		Map paramMap = (HashMap)jsonParam.get("_param");
    		String UserID = (String)paramMap.get("UserID").toString();//用户ID
            String CheckDate = (String)paramMap.get("CheckDate").toString();//打卡日期
            int PageIndex = 1;//页面索引
            int PageSize = 99999;//每页数量
            
            IPage page = new Page(PageIndex, PageSize);
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("UserID", UserID);
            map.put("CheckDate", CheckDate);
            int AllCount=iBChanneluserService.mChannelCheckClockPerson_SelectAllCount(map);
    		List<Map<String,Object>> a = iBChanneluserService.mChannelCheckClockPerson_Select(page, UserID, CheckDate);
    		result.put("List", a);
    		result.put("AllCount", AllCount);
    		result.put("PageSize", PageSize);
    		return re.ok(result);
    	}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1, "系统异常，请联系管理员");
			
		}
    }
	
	@ResponseBody
    @ApiOperation(value = "兼职考勤日历打卡信息", notes = "兼职考勤日历打卡信息")
    @RequestMapping(value = "/mChannelCheckClockCountDaily_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mChannelCheckClockCountDaily_Select(@RequestBody JSONObject jsonParam) {
		Result re=new Result();
    	try{
    		// 直接将json信息打印出来
    		System.out.println(jsonParam.toJSONString());
    		Map paramMap = (HashMap)jsonParam.get("_param");
    		String UserID = (String)paramMap.get("UserID").toString();//用户ID
            String CurrentMonth = (String)paramMap.get("CurrentMonth").toString();//打卡日期
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("UserID", UserID);
            map.put("CurrentMonth", CurrentMonth);
    		List<Map<String,Object>> a = iBChanneluserService.mChannelCheckClockCountDaily_Select(map);
    		return re.ok(a);
    	}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1, "系统异常，请联系管理员");
			
		}
    }
	
	@ResponseBody
    @ApiOperation(value = "获取兼职是否有任务", notes = "获取兼职是否有任务")
    @RequestMapping(value = "/mChannelTaskIsHaveUndone_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mChannelTaskIsHaveUndone_Select(@RequestBody JSONObject jsonParam) {
		Map<String, Object> result = new HashMap<String, Object>();
		Result re=new Result();
    	try{
    		// 直接将json信息打印出来
    		System.out.println(jsonParam.toJSONString());
    		Map paramMap = (HashMap)jsonParam.get("_param");
    		String UserID = (String)paramMap.get("UserID").toString();//用户ID
            String ProjectID = (String)paramMap.get("ProjectID").toString();//打卡日期
            String JobCode = (String)paramMap.get("JobCode").toString();
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("UserID", UserID);
            map.put("ProjectID", ProjectID);
            map.put("JobCode", JobCode);
            List<Map<String,Object>> res=null;
            if(JobCode.equals("ZQ")) {
            	res = iBChanneltaskService.mChannelTaskIsHaveUndoneZQ_Select(map);
            }
            else if(JobCode.equals("JZ")) {
            	res = iBChanneltaskService.mChannelTaskIsHaveUndoneJZ_Select(map);
            }
            result.put("IsHave", res != null && res.size() > 0 ? 1 : 0);
    		return re.ok(result);
    	}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1, "系统异常，请联系管理员");
			
		}
    }
	
	@ResponseBody
    @ApiOperation(value = "考勤异常", notes = "考勤异常")
    @RequestMapping(value = "/mChannelUserAbnormal_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mChannelUserAbnormal_Select(@RequestBody JSONObject jsonParam) {
		Map<String, Object> result = new HashMap<String, Object>();
		Result re=new Result();
    	try{
    		// 直接将json信息打印出来
    		System.out.println(jsonParam.toJSONString());
    		Map paramMap = (HashMap)jsonParam.get("_param");
    		String ChannelTaskID = (String)paramMap.get("ChannelTaskID").toString();//用户ID
    		
            int PageIndex = (int)paramMap.get("PageIndex");//页面索引
            int PageSize = (int)paramMap.get("PageSize");//每页数量
            IPage page = new Page(PageIndex, PageSize);
            
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("ChannelTaskID", ChannelTaskID);
            if(ChannelTaskID == null && ChannelTaskID.length() > 0) {
            	re.ok("任务ID参数为空");
            }
            
            map.put("SiteUrl", SiteUrl);//获取SIteUrl的配置
            int AllCount=iBChanneluserService.mChannelUserAbnormal_SelectAllCount(map);
    		List<Map<String,Object>> a = iBChanneluserService.mChannelUserAbnormal_Select(page, ChannelTaskID,SiteUrl);
    		result.put("List", a);
    		result.put("AllCount", AllCount);
    		result.put("PageSize", PageSize);
    		return re.ok(result);
    	}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1, "系统异常，请联系管理员");
		}
    }
	
	@ResponseBody
    @ApiOperation(value = "打卡", notes = "打卡")
    @RequestMapping(value = "/mChannelCheckClock_Insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mChannelCheckClock_Insert(@RequestBody JSONObject jsonParam) {
		Result re=new Result();
    	try{
    		// 直接将json信息打印出来
    		System.out.println(jsonParam.toJSONString());
    		Map paramMap = (HashMap)jsonParam.get("_param");
            String ChannelTaskID = (String)paramMap.get("ChannelTaskID").toString();//任务ID
            String Longitude = (String)paramMap.get("Longitude").toString();//
            String Dimension = (String)paramMap.get("Dimension").toString();//
            String Photo = (String)paramMap.get("Photo").toString();//
            String DeviceCode = (String)paramMap.get("DeviceCode").toString();//设备序列号
            String CheckType = (String)paramMap.get("CheckType").toString();//
            String TaskAreaID = (String)paramMap.get("TaskAreaID").toString();//
            String TaskAreaName = (String)paramMap.get("TaskAreaName").toString();//
            String UserID = (String)paramMap.get("UserID").toString();//用户ID
            Map<String,Object> map = new HashMap<String,Object>();
            //map.put("ID", ID);
    		map.put("ChannelTaskID", ChannelTaskID);
    		map.put("Longitude", Longitude);
    		map.put("Dimension", Dimension);
    		map.put("Photo", Photo);
    		map.put("DeviceCode", DeviceCode);
    		map.put("CheckType", CheckType);
    		map.put("TaskAreaID", TaskAreaID);
    		map.put("TaskAreaName", TaskAreaName);
    		map.put("UserID", UserID);
    		
    		if(ChannelTaskID == null &&ChannelTaskID.length() == 0) {
    			return Result.errormsg(1, "用户ID参数为空");
    		}
    		if(UserID == null && UserID.length() == 0) {
    			return Result.errormsg(1, "用户ID参数为空");
    		}
    		Map<String,Object> obj = iBChanneltaskService.mChannelCheckClockTopOne_Select(map);
    		//修改 
    		if(obj != null && obj.size() > 0 && obj.get("DeviceCode").toString().contentEquals(DeviceCode) == false) {
    			return Result.errormsg(2, "一台手机当天仅支持一个账号打卡");
    		}
    		Map<String,Object> obj2 = iBChanneltaskService.mChannelCheckClockByDeviceCode_Select(map);
    		if(obj2 != null && obj2.size() > 0 && obj2.get("Creator").toString().equals(UserID) == false) {
    			return Result.errormsg(2, "一台手机当天仅支持一个账号打卡");
    		}
    		String ID = getUUID();
    		map.put("ID", ID);
    		boolean res = iBChanneltaskService.mChannelCheckClock_Insert(map);
    		
    		return re.ok(res);
    	}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1, "系统异常，请联系管理员");
			
		}
    }
	
	@ResponseBody
    @ApiOperation(value = "分配专员客户", notes = "分配专员客户")
    @RequestMapping(value = "/mChannelTempPersonQuitCus_Update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mChannelTempPersonQuitCus_Update(@RequestBody JSONObject jsonParam) {
		Result re=new Result();
    	try{
    		// 直接将json信息打印出来
    		System.out.println(jsonParam.toJSONString());
    		Map paramMap = (HashMap)jsonParam.get("_param");
    		String UserID = (String)paramMap.get("UserID").toString();//用户ID
            String NewChannelUserID = (String)paramMap.get("NewChannelUserID").toString();//
            String OldChannelUserID = (String)paramMap.get("OldChannelUserID").toString();//
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("UserID", UserID);
            map.put("NewChannelUserID", NewChannelUserID);
            map.put("OldChannelUserID", OldChannelUserID);
            iBChanneltaskService.mChannelTempPersonQuitCus_Update(map);
            iBChanneltaskService.mChannelTempPersonQuitCus_Update2(map);
            iBChanneltaskService.mChannelTempPersonQuitCus_Update3(map);
    		return re.ok("true");
    	}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1, "系统异常，请联系管理员");
			
		}
    }
	
	@ResponseBody
    @ApiOperation(value = "领取任务", notes = "领取任务")
    @RequestMapping(value = "/mChannelTaskAccept_Insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mChannelTaskAccept_Insert(@RequestBody JSONObject jsonParam) {
		Result re=new Result();
    	try{
    		// 直接将json信息打印出来
    		System.out.println(jsonParam.toJSONString());
    		Map paramMap = (HashMap)jsonParam.get("_param");
    		String UserID = (String)paramMap.get("UserID").toString();//用户ID
            String ChannelTaskID = (String)paramMap.get("ChannelTaskID").toString();//
            String TaskType = (String)paramMap.get("TaskType").toString();//
            String ReportUserID = (String)paramMap.get("ReportUserID").toString();//
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("UserID", UserID);
            map.put("ChannelTaskID", ChannelTaskID);
            map.put("TaskType", TaskType);
            map.put("ReportUserID", ReportUserID);
            int has = iBChanneltaskService.mChannelTaskAccept_Insert(map);
            System.out.println(has);
            if(has == 0) {
            	iBChanneltaskService.mChannelTaskAccept_Insert2(map);
            }
            else {
            	iBChanneltaskService.mChannelTaskAccept_Insert3(map);
            }
            iBChanneltaskService.mChannelTaskAccept_Update(map);
            List<Map<String, Object>> obj = iBChanneltaskService.mChannelTaskAccept_Select(map);
            
    		return re.ok(obj);
    	}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1, "系统异常，请联系管理员");
			
		}
    }
	
	@ResponseBody
    @ApiOperation(value = "分配客户和兼职", notes = "分配客户和兼职")
    @RequestMapping(value = "/mChannelLeaderQuit_Update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mChannelLeaderQuit_Update(@RequestBody JSONObject jsonParam) {
		Result re=new Result();
    	try{
    		// 直接将json信息打印出来
    		System.out.println(jsonParam.toJSONString());
    		Map paramMap = (HashMap)jsonParam.get("_param");
    		String UserID = (String)paramMap.get("UserID").toString();//用户ID
            String OldUserID = (String)paramMap.get("OldUserID").toString();//
            String ProjectID = (String)paramMap.get("ProjectID").toString();//
            String NewUserID = (String)paramMap.get("NewUserID").toString();//
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("UserID", UserID);
            map.put("OldUserID", OldUserID);
            map.put("ProjectID", ProjectID);
            map.put("NewUserID", NewUserID);
           iBChanneltaskService.mChannelLeaderQuit_Insert(map);
           iBChanneltaskService.mChannelLeaderQuit_Update(map);
           iBChanneltaskService.mChannelLeaderQuit_Update2(map);
           iBChanneltaskService.mChannelLeaderQuit_Insert2(map);
           iBChanneltaskService.mChannelLeaderQuit_Update3(map);
           iBChanneltaskService.mChannelLeaderQuit_Update4(map);
           
    		return re.ok(true);
    	}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1, "系统异常，请联系管理员");
			
		}
    }
	
	@ResponseBody
    @ApiOperation(value = "经理团队列表", notes = "经理团队列表")
    @RequestMapping(value = "/mChannelLeaderList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mChannelLeaderList_Select(@RequestBody JSONObject jsonParam) {

		Result re=new Result();
    	try{
    		// 直接将json信息打印出来
    		System.out.println(jsonParam.toJSONString());
    		Map paramMap = (HashMap)jsonParam.get("_param");
            String ProjectID = (String)paramMap.get("ProjectID").toString();//
            String IsAll = (String)paramMap.get("IsAll").toString();//
            String Filter = (String)paramMap.get("Filter").toString();//
           
            int PageIndex = (int)paramMap.get("PageIndex");//页面索引
            int PageSize = (int)paramMap.get("PageSize");//每页数量
            IPage page = new Page(PageIndex, PageSize);
            
            String sqlWhere = "";
            if(IsAll.equals("0")) {
            	sqlWhere += " AND a.IsDel=0 AND a.Status=1";
            }
            if(Filter != null && Filter.length() > 0) {
            	sqlWhere += "AND (b.Name LIKE '%"+Filter+"%' OR b.TelPhone LIKE '%"+Filter+"%')";
            }
            IPage<Map<String, Object>> obj=iBChanneltaskService.mChannelLeaderList_Select(page, ProjectID, sqlWhere);
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("PageSize", obj.getSize());
            map.put("AllCount", obj.getTotal());
            map.put("List", obj.getRecords());
    		return re.ok(map);
    	}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1, "系统异常，请联系管理员");
			
		}
    }
	
	@ResponseBody
    @ApiOperation(value = "是否保存锁房图片到本地", notes = "是否保存锁房图片到本地")
    @RequestMapping(value = "/CustomerLockRoomClientSaveDetail_Update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result CustomerLockRoomClientSaveDetail_Update(@RequestBody JSONObject jsonParam) {
		Result re=new Result();
    	try{
    		// 直接将json信息打印出来
    		System.out.println(jsonParam.toJSONString());
    		Map paramMap = (HashMap)jsonParam.get("_param");
    		String UserID = (String)paramMap.get("UserID").toString();//用户ID
            String OpportunityID = (String)paramMap.get("OpportunityID").toString();//
            String ProjectID = (String)paramMap.get("ProjectID").toString();//
            String CustomerID = (String)paramMap.get("CustomerID").toString();//
            String RoomID = (String)paramMap.get("RoomID").toString();//
            String IsClientSave = (String)paramMap.get("IsClientSave").toString();//
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("UserID", UserID);
            map.put("IsClientSave", IsClientSave);
            StringBuilder Where = new StringBuilder();
            if(ProjectID != null && ProjectID.length() > 0) {
            	Where.append(" and ProjectID = '").append(ProjectID).append("'");
            }
            else {
            	return Result.errormsg(2, "项目ID不能为空");
            }
            if(OpportunityID != null && OpportunityID.length() > 0) {
            	Where.append(" and OpportunityID = '").append(OpportunityID).append("'");
            }
            else {
            	return Result.errormsg(2, "机会ID不能为空");
            }
            if(CustomerID != null && CustomerID.length() > 0) {
            	Where.append(" and CustomerID = '").append(CustomerID).append("'");
            }
            else {
            	return Result.errormsg(2, "客户ID不能为空");
            }
			if(RoomID != null && RoomID.length() > 0) {
				Where.append(" and RoomID = '").append(RoomID).append("'");
			}
			else {
            	return Result.errormsg(2, "房间ID不能为空");
            }
			System.out.println(Where);
            map.put("Where", Where);
           iBChanneltaskService.CustomerLockRoomClientSaveDetail_Update(map);
           
           
    		return re.ok("");
    	}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1, "系统异常，请联系管理员");
			
		}
    }
	
	@ResponseBody
    @ApiOperation(value = "经理作战图任务列表", notes = "经理作战图任务列表")
    @RequestMapping(value = "/mChannelLeaderTaskList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mChannelLeaderTaskList_Select(@RequestBody JSONObject jsonParam) {
		List result=new ArrayList();
		Result re=new Result();
    	try{
    		// 直接将json信息打印出来
    		System.out.println(jsonParam.toJSONString());
            Map paramMap = (HashMap)jsonParam.get("_param");
            String ProjectID = (String)paramMap.get("ProjectID").toString();///
            String TaskList ;//
            
    		Map<String,Object> map = new HashMap<String,Object>();
    		map.put("ProjectID", ProjectID);
    		List<Map<String, Object>> a = iBChanneltaskService.mChannelLeaderTaskList_Select(map);
    		Map<String,Object> obj1 = new HashMap<String,Object>();
    		Map<String,Object> obj2 = new HashMap<String,Object>();
    		Map<String,Object> obj3 = new HashMap<String,Object>();
    		Map<String,Object> obj4 = new HashMap<String,Object>();
    		List a1 = new ArrayList();
    		List a2 = new ArrayList();
    		List a3 = new ArrayList();
    		List a4 = new ArrayList();
    		int i1=0;
    		int i2=0;
    		int i3=0;
    		int i4=0;
    		obj1.put("TaskType","0E88065E-AF3E-4905-8809-7BD30610323F");
    		obj1.put("TaskTypeName","拦截");
    		for(Map<String, Object> x : a) {
    			if(a.get(i1).get("TaskTypeName").equals("拦截")) {
    				a1.add(a.get(i1));
    			}
    			i1++;
    		}
    		obj1.put("TaskList",a1);
    		result.add(obj1);
    		
    		obj2.put("TaskType","FC09F0DF-D3DF-4378-91C0-7146EC451F43");
    		obj2.put("TaskTypeName","圈层");
    		for(Map<String, Object> x : a) {
    			if(a.get(i2).get("TaskTypeName").equals("圈层")) {
    				a2.add(a.get(i2));
    			}
    			i2++;
    		}
    		obj2.put("TaskList",a2);
    		result.add(obj2);
    		
    		obj3.put("TaskType","C07D5987-ACDD-40B8-9CBD-6257AA59C88C");
    		obj3.put("TaskTypeName","外拓");
    		for(Map<String, Object> x : a) {
    			if(a.get(i3).get("TaskTypeName").equals("外拓")) {
    				a3.add(a.get(i3));
    			}
    			i3++;
    		}
    		obj3.put("TaskList",a3);
    		result.add(obj3);
    		
    		obj4.put("TaskType","0E88065E-AF3E-4905-8809-7BD30610323F");
    		obj4.put("TaskTypeName","外展");
    		for(Map<String, Object> x : a) {
    			if(a.get(i4).get("TaskTypeName").equals("外展")) {
    				a4.add(a.get(i4));
    			}
    			i4++;
    		}
    		obj4.put("TaskList",a4);
    		result.add(obj4);

    		return re.ok(result);
    		
    	}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1, "系统异常，请联系管理员");
			
		}
    }
	
	@ResponseBody
    @ApiOperation(value = "任务列表查询", notes = "任务列表查询")
    @RequestMapping(value = "/mChannelTaskList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mChannelTaskList_Select(@RequestBody JSONObject jsonParam) {
		Result re=new Result();
    	try{
    		// 直接将json信息打印出来
    		System.out.println(jsonParam.toJSONString());
    		Map paramMap = (HashMap)jsonParam.get("_param");
    		String UserID = (String)paramMap.get("UserID").toString();//用户ID
            String ProjectID = (String)paramMap.get("ProjectID").toString();//打卡日期
            String JobCode = (String)paramMap.get("JobCode").toString();//任务ID
            String ChannelTaskCode = "";
            if(paramMap.get("ChannelTaskCode") != null) {
            	ChannelTaskCode = (String)paramMap.get("ChannelTaskCode").toString();
            }
            String TaskStatus = "";
            if(paramMap.get("TaskStatus") != null) {
            	TaskStatus = (String)paramMap.get("TaskStatus").toString();
            }
            int PageIndex = (int)paramMap.get("PageIndex");//页面索引
            int PageSize = (int)paramMap.get("PageSize");//每页数量
            
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("PageIndex", PageIndex);
            map.put("PageSize", PageSize);
            
            String errmsg = "";
            StringBuilder whereStr = new StringBuilder();
            StringBuilder JZCode = new StringBuilder();;
            if(JobCode.equals("ZQ")) {
            	if(ProjectID != null && ProjectID.length() > 0)
            		whereStr.append(" AND a.ProjectID = '").append(ProjectID).append("'");
            }
            if(TaskStatus != null && TaskStatus.length() > 0) {
            	if(JobCode.equals("ZQ")) {
            		whereStr.append(" AND a.Status = '").append(TaskStatus).append("'");
            	}
            	else if(JobCode.equals("JZ")) {
            		whereStr.append(" AND b.Status = '").append(TaskStatus).append("'");
            	}
            }
            if(JobCode != null && JobCode.length() > 0) {
            	if(JobCode.equals("ZQ")) {
            		if(ChannelTaskCode != null && ChannelTaskCode.length() > 0) {
            			whereStr.append(" AND a.TaskCode like '%").append(ChannelTaskCode).append("%'");
            		}
            		whereStr.append(" AND a.Creator = '").append(UserID).append("'");
            	}
            	if (JobCode.equals("JZ")){
            		if(ChannelTaskCode != null && ChannelTaskCode.length() > 0) {
            			whereStr.append(" AND a.TaskCode = '").append(ChannelTaskCode).append("'");
            		}
            		else {
            			whereStr.append(" AND b.ChannelUserID = '").append(UserID).append("'");
            		}
            		if(TaskStatus.equals("1") || TaskStatus.equals("2")) {
            			//判断该兼职是否有所属专员
            			Map<String, Object> objChannelUser =iBChanneltaskService.mChannelUserByID_Select(UserID);
            			if(objChannelUser.get("ReportUserID") != null) {
            				whereStr.append(" AND a.Creator = '").append(objChannelUser.get("ReportUserID")).append("'");
            			}
            		}
            		JZCode.append(" AND ChannelUserID = '").append(UserID).append("'");
            	}
            }
            map.put("whereStr", whereStr);
            map.put("JZCode", JZCode);
            //
            //搜索任务时
            if(JobCode.equals("JZ") && ChannelTaskCode != null && ChannelTaskCode.length() > 0) {
            	//判断该兼职是否有所属专员
            	Map<String, Object> objChannelUser = iBChanneltaskService.mChannelUserByID_Select(UserID);
    			String reportUserID = (String) objChannelUser.get("ReportUserID");//专员ID
    			
    			if(reportUserID != null && reportUserID.length() > 0) {
    				
    				System.out.println(whereStr);
    				System.out.println(JZCode);
    				List<Map<String,Object>> objData = iBChanneltaskService.mChannelTaskList_Select(map);
    				int All = iBChanneltaskService.mChannelTaskList_SelectAllCount(map);
    				
    	    		if(All > 0) {
    	    			String Creator = (String) objData.get(0).get("Creator");//任务创建者
    	    			if(Creator != reportUserID) {
    	    				Map<String, Object> a = new HashMap<String, Object>();
    	    	    		a.put("List", "[]");
    	    	    		a.put("AllCount", 0);
    	    	    		a.put("PageSize", PageSize);
    	    				return re.on(a);
    	    			}
    	    		}
    			}
            }
            List<Map<String,Object>> objListData = iBChanneltaskService.mChannelTaskList_Select(map);
            int AllCount = iBChanneltaskService.mChannelTaskList_SelectAllCount(map);
    		Map<String, Object> result = new HashMap<String, Object>();
    		result.put("List", objListData);
    		result.put("AllCount", AllCount);
    		//result.put("AllCount", objListData.getCurrent() > 0 ? objListData.getCurrent() : "未找到对应任务");
    		result.put("PageSize", PageSize);
    		return re.ok(result);
    	}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1, "系统异常，请联系管理员");
			
		}
    }
	
	@ResponseBody
    @ApiOperation(value = "专员团队管理列表", notes = "专员团队管理列表")
    @RequestMapping(value = "/mChannelTempPersonList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mChannelTempPersonList_Select(@RequestBody JSONObject jsonParam) {

		Result re=new Result();
    	try{
    		// 直接将json信息打印出来
    		System.out.println(jsonParam.toJSONString());
    		Map paramMap = (HashMap)jsonParam.get("_param");
            String ProjectID = (String)paramMap.get("ProjectID").toString();//
            String JobCode = (String)paramMap.get("JobCode").toString();//
            String UserID = (String)paramMap.get("UserID").toString();//
            String ChannelTaskID = (String)paramMap.get("ChannelTaskID").toString();//
            String Filter = (String)paramMap.get("Filter").toString();//
            String AppID = "";
            if(paramMap.get("AppID") != null) {
            	AppID =(String)paramMap.get("AppID").toString();
            }
            Map<String,Object> map = new HashMap<String,Object>();
            
            int PageIndex = (int)paramMap.get("PageIndex");//页面索引
            int PageSize = (int)paramMap.get("PageSize");//每页数量
            StringBuilder sb = new StringBuilder();
            String errmsg = "";
            StringBuilder taskID = new StringBuilder();
            if(JobCode.equals("ZQ")) {
            	//项目下所有兼职
            	if(ProjectID != null && ProjectID.length() > 0 ) {
            		sb.append(" AND c.ProjectID='").append(ProjectID).append("'");
            	}
            	//专员下所有兼职
            	if(UserID != null && UserID.length() > 0 ) {
            		sb.append(" AND a.ReportUserID='").append(UserID).append("'");
            	}
            	//专员+任务下所有兼职
            	if(ChannelTaskID != null &&UserID.length() > 0 ) {
            		taskID.append("   AND c.ID='").append(ChannelTaskID).append("'");
            	}
            	if(Filter != null && Filter.length() > 0) {
            		sb.append(" AND (a.Name LIKE '%").append(Filter).append("%' OR a.Mobile LIKE '%").append(Filter).append("%')");
            	}
            }
            else if(JobCode.equals("ZQFZR")) {
            	//专员下所有兼职
            	if(UserID != null && UserID.length() > 0){
            		sb.append(" AND a.ReportUserID='").append(UserID).append("'");
            	}
            	if(Filter != null && Filter.length() > 0) {
            		sb.append(" AND (a.Name LIKE '%").append(Filter).append("%' OR a.Mobile LIKE '%").append(Filter).append("%')");
            	}
            }
            else if(AppID.equals("PC")) {
            	if(ProjectID != null) {
            		sb.append(" AND c.ProjectID='").append(ProjectID).append("'");
            		sb.append(" AND c.Status=2");
            	}
            }
           if(sb.length() == 0) {
        	   return Result.errormsg(-1, "任务兼职列表-缺少查询参数");
           }
           String TaskID = taskID.toString();
           String sqlWhere = sb.toString();
           
           map.put("sqlWhere", sqlWhere);
           map.put("ChannelTaskID",ChannelTaskID);
           map.put("taskID", TaskID);
           map.put("PageIndex", PageIndex);
           map.put("PageSize", PageSize);
           Map<String,Object> obj = new HashMap<String,Object>();
           if(JobCode.equals("ZQ") && ChannelTaskID != null && ChannelTaskID.length() > 0) {
        	   
        	   List<Map<String,Object>> Data =  iBChanneltaskService.mChannelTaskTempPersonList_Select(map);
        	   int AllCount = iBChanneltaskService.mChannelTaskTempPersonList_SelectAllCount(map);
        	   obj.put("List", Data);
        	   obj.put("AllCount", AllCount);
        	   obj.put("PageSize", PageSize);
        	   return re.ok(obj);
           }
           else {
        	   List<Map<String,Object>> Data = iBChanneltaskService.mChannelTempPersonList_Select(map);
        	   int AllCount = iBChanneltaskService.mChannelTempPersonList_SelectAllCount(map);
        	   obj.put("List", Data);
        	   obj.put("AllCount", AllCount);
        	   obj.put("PageSize", PageSize);
        	   return re.ok(obj);
           }
    	}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1, "系统异常，请联系管理员");
			
		}
    }
	
	
	/*
	 * 生成UUID
	 */
	public static String getUUID(){
        UUID uuid=UUID.randomUUID();
        String str = uuid.toString(); 
        String a= str.toUpperCase();
        return a;
      }
}

