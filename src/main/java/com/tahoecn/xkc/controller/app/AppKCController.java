package com.tahoecn.xkc.controller.app;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
	
    @Autowired
    private IBChanneltaskService iBChanneltaskService;

    @Autowired
    private IBChanneltaskareaService iBChanneltaskareaService;
    
    @Autowired
    private IBChanneluserService iBChanneluserService;

	private Object list;
//	@ResponseBody
//    @ApiOperation(value = "新建任务", notes = "新建任务")
//    @RequestMapping(value = "/mChannelTask_Insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public Result mChannelTask_Insert(@RequestBody JSONObject jsonParam) {
//		Result re=new Result();
//    	try{
//    		// 直接将json信息打印出来
//    		System.out.println(jsonParam.toJSONString());
//            Map paramMap = (HashMap)jsonParam.get("_param");
//            String ChannelTypeID = (String)paramMap.get("ChannelTypeID").toString();//
//            String CustomerTarget = (String)paramMap.get("CustomerTarget").toString();//
//            String EndTime = (String)paramMap.get("EndTime").toString();//
//            String Name = (String)paramMap.get("Name").toString();//
//            String StartTime = (String)paramMap.get("StartTime").toString();//
//            String TaskAreaID = (String)paramMap.get("TaskAreaID").toString();//
//            String TaskAreaName = (String)paramMap.get("TaskAreaName").toString();//
//            String WorkEndTime = (String)paramMap.get("WorkEndTime").toString();//
//            String WorkRange = (String)paramMap.get("WorkRange").toString();//
//            String WorkStartTime = (String)paramMap.get("WorkStartTime").toString();//
//            String Platform = (String)paramMap.get("Platform").toString();//
//            String ProjectID = (String)paramMap.get("ProjectID").toString();//
//            String UserID = (String)paramMap.get("UserID").toString();//
//            
//    		Map<String,Object> map = new HashMap<String,Object>();
//    		map.put("ChannelTypeID", ChannelTypeID);
//    		map.put("CustomerTarget", CustomerTarget);
//    		map.put("EndTime", EndTime);
//    		map.put("Name", Name);
//    		map.put("StartTime", StartTime);
//    		map.put("TaskAreaID", TaskAreaID);
//    		map.put("TaskAreaName", TaskAreaName);
//    		map.put("WorkEndTime", WorkEndTime);
//    		map.put("WorkRange", WorkRange);
//    		map.put("WorkStartTime", WorkStartTime);
//    		map.put("Platform", Platform);
//    		map.put("ProjectID", ProjectID);
//    		map.put("UserID", UserID);
//    		
//    		iBChanneltaskService.mChannelTask_Insert(map);
//    		iBChanneltaskService.mChannelTask_Insert2(map);
//    		iBChanneltaskService.mChannelTask_Insert3(map);
//    		List<BChanneltask> a=iBChanneltaskService.mChannelTask_Insert4(map);
//    		
//    		return Result.ok(a);
//    	}catch (Exception e) {
//			e.printStackTrace();
//			return Result.errormsg(1,"系统异常，请联系管理员");
//		}
//    }
	
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
		Map<String,Object> result=new HashMap<String,Object>();
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
            String CheckDate = (String)paramMap.get("CheckDate").toString();//打卡日期
            int PageIndex = (int)paramMap.get("PageIndex");//页面索引
            int PageSize = (int)paramMap.get("PageSize");//每页数量
            
            IPage page = new Page(PageIndex, PageSize);
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("ChannelTaskID", ChannelTaskID);
            if(ChannelTaskID == null) {
            	re.ok("任务ID参数为空");
            }
            //int AllCount=iBChanneluserService.mChannelUserAbnormal_SelectAllCount(map);
    		//List<Map<String,Object>> a = iBChanneluserService.mChannelUserAbnormal_Select(page, ChannelTaskID);
    		//result.put("List", a);
    		//result.put("AllCount", AllCount);
    		result.put("PageSize", PageSize);
    		return re.ok(result);
    	}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1, "系统异常，请联系管理员");
		}
    }
	
	
}

