package com.tahoecn.xkc.controller.webapi.sys;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tahoecn.core.json.JSONResult;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.model.sys.SCommonjobs;
import com.tahoecn.xkc.service.sys.ISCommonjobsService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YYY
 * @since 2019-06-26
 */
@RestController
@RequestMapping("/webapi/sys/CommonJob")
public class SCommonjobsController extends TahoeBaseController {
	
	@Autowired
	private ISCommonjobsService ISCommonjobsService;
	

    @ApiOperation(value = "获取通用岗位列表(查询)", notes = "分页获取获取通用岗位列表（查询）")
    @ApiImplicitParams({ @ApiImplicitParam(name = "pageNum", value = "当前页数", dataType = "int") ,
            @ApiImplicitParam(name = "pageSize", value = "每页大小", dataType = "int") })
    @RequestMapping(value = "/SystemCommonJobsList_Select", method = {RequestMethod.GET})
    
    public JSONResult SystemCommonJobsList_Select(String authCompanyID,String productID, String jobName){
    	Map map=new HashMap<>();
    	JSONResult jsonResult=new JSONResult();
    	
    	map.put("authCompanyID", authCompanyID);
    	map.put("productID", productID);
    	map.put("jobName", jobName);
    	
    	List list = ISCommonjobsService.SystemCommonJobsList_Select(map);
    	jsonResult.setData(list);
    	return jsonResult;
    }
    
    @ApiOperation(value = "获取通用岗位列表", notes = "分页获取获取通用岗位列表")
    @ApiImplicitParams({ @ApiImplicitParam(name = "pageNum", value = "当前页数", dataType = "int") ,
            @ApiImplicitParam(name = "pageSize", value = "每页大小", dataType = "int") })
    @RequestMapping(value = "/SystemCommonJobsList_SelectList", method = {RequestMethod.GET})
    
    public JSONResult SystemCommonJobsList_SelectList(){
    	JSONResult jsonResult=new JSONResult();
    	List<SCommonjobs> list = ISCommonjobsService.SystemCommonJobsList_SelectList();
    	jsonResult.setData(list);
    	return jsonResult;
    }
    
    
    @ApiOperation(value = "查询岗位名称是否存在", notes = "查询岗位名称是否存在")
    @RequestMapping(value = "SystemCommonJobNameIsExists_Select", method = {RequestMethod.GET})
    public JSONResult SystemCommonJobNameIsExists_Select(String authCompanyID,String productID, String jobName){
    	Map map=new HashMap<>();
    	JSONResult jsonResult=new JSONResult();
    	
    	map.put("authCompanyID", authCompanyID);
    	map.put("productID", productID);
    	map.put("jobName", jobName);
    	Boolean result = ISCommonjobsService.SystemCommonJobNameIsExists_Select(map);
    	jsonResult.setData(result);
    	return jsonResult;
    }
    
    @ApiOperation(value = "启用/禁用通用岗位", notes = "启用/禁用通用岗位")
    @ApiImplicitParams({ @ApiImplicitParam(name = "Status", value = "状态", dataType = "string"),
    	@ApiImplicitParam(name = "id", value = "id", dataType = "string")
    	})
    @RequestMapping(value = "/SystemCommonJobStatus_Update", method = {RequestMethod.GET})
    public JSONResult SystemCommonJobStatus_Update(String status,String id){
    	Map map=new HashMap<>();
    	map.put("status", status);
    	map.put("id", id);
    	JSONResult jsonResult=new JSONResult();
    	ISCommonjobsService.SystemCommonJobStatus_Update(map);
    	jsonResult.setData("启用/禁用成功");
    	return jsonResult;
    }
    
    @ApiOperation(value = "删除通用岗位", notes = "删除通用岗位")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "id", dataType = "string")
    	})
    @RequestMapping(value = "/SystemCommonJob_Delete", method = {RequestMethod.GET})
    public JSONResult SystemCommonJob_Delete(String id){
    	Map map=new HashMap<>();
    	JSONResult jsonResult=new JSONResult();
    	map.put("id", id);
    	ISCommonjobsService.SystemCommonJob_Delete(map);
    	jsonResult.setData("删除通用岗位成功");
    	return jsonResult;
    }
    
   @ApiOperation(value = "新增通用岗位", notes = "新增通用岗位")
   @ApiImplicitParams({@ApiImplicitParam(name = "jobCode", value = "jobCode", dataType = "string"),
	   @ApiImplicitParam(name = "jobName", value = "jobName", dataType = "string"),
	   @ApiImplicitParam(name = "JobDesc", value = "JobDesc", dataType = "string"),
	   @ApiImplicitParam(name = "authCompanyID", value = "AuthCompanyID", dataType = "string"),
	   @ApiImplicitParam(name = "productID", value = "ProductID", dataType = "string"),
	   @ApiImplicitParam(name = "editor", value = "Editor", dataType = "string"),
	   @ApiImplicitParam(name = "editTime", value = "EditTime", dataType = "string"),
	   @ApiImplicitParam(name = "status", value = "Status", dataType = "string"),
	   @ApiImplicitParam(name = "isDel", value = "IsDel", dataType = "string"),
    	})
   @RequestMapping(value = "/SystemCommonJob_Insert", method = {RequestMethod.GET})    
   public JSONResult SystemCommonJob_Insert(String jobCode,String jobName,String JobDesc,String authCompanyID,
		   String productID,String editor,String editTime,String status,String isDel
		   ){
	   	Map map=new HashMap<>();
	   	map.put("jobCode", jobCode);
	   	map.put("jobName", jobName);
	   	map.put("JobDesc",JobDesc );
	   	map.put("authCompanyID", authCompanyID);
	   	map.put("productID",productID );
	   	map.put("editor",editor );
	   	map.put("editTime",editTime );
	   	map.put("status",status );
	   	map.put("isDel",isDel);
	   	JSONResult jsonResult=new JSONResult();
	   	String result = ISCommonjobsService.SystemCommonJob_Insert(map);
	   	if(StringUtils.isNotEmpty(result)){
	   		jsonResult.setData("新增成功id:"+result);
	   	}else {
	   		jsonResult.setData("新增失败");
		}
	   
	   	return jsonResult;
   }
   
   @ApiOperation(value = "更新通用岗位", notes = "更新通用岗位")
   @ApiImplicitParams({@ApiImplicitParam(name = "jobCode", value = "jobCode", dataType = "string"),
	   @ApiImplicitParam(name = "jobName", value = "jobName", dataType = "string"),
	   @ApiImplicitParam(name = "JobDesc", value = "JobDesc", dataType = "string"),
	   @ApiImplicitParam(name = "editor", value = "Editor", dataType = "string"),
	   @ApiImplicitParam(name = "status", value = "Status", dataType = "string"),
	   @ApiImplicitParam(name = "id", value = "id", dataType = "string"),
    	})
   @RequestMapping(value = "/SystemCommonJob_Update", method = {RequestMethod.GET})    
   public JSONResult SystemCommonJob_Update(String jobCode,String jobName,String JobDesc,
		   String editor,String id
		   ){
	   	Map map=new HashMap<>();
	   	map.put("jobCode", jobCode);
	   	map.put("jobName", jobName);
	   	map.put("JobDesc",JobDesc );
	   	map.put("id", id);
	   	map.put("editor",editor );
	   	JSONResult jsonResult=new JSONResult();
	   	ISCommonjobsService.SystemCommonJob_Update(map);
	   	jsonResult.setData("新增成功");
	   	return jsonResult;
   }
}
