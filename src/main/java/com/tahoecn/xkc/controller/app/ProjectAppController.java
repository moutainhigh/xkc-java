package com.tahoecn.xkc.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.ResponseMessage;
import com.tahoecn.xkc.model.project.SAccountuserproject;
import com.tahoecn.xkc.model.project.SAccountuserprojectjob;
import com.tahoecn.xkc.service.project.ISAccountuserprojectService;
import com.tahoecn.xkc.service.project.ISAccountuserprojectjobService;

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
@Api(tags = "APP-项目", value = "APP-项目")
@RequestMapping("/app")
public class ProjectAppController extends TahoeBaseController {

    @Autowired
    private ISAccountuserprojectService iSAccountuserprojectService;
    @Autowired
    private ISAccountuserprojectjobService iSAccountuserprojectjobService;

    @ResponseBody
    @ApiOperation(value = "用户切换项目", notes = "用户切换项目")
    @RequestMapping(value = "/mUserProjectChange_Update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage mUserProjectChange_Update(@RequestBody JSONObject jsonParam) {
    	try{
    		// 直接将json信息打印出来
            System.out.println(jsonParam.toJSONString());
            Map paramMap = (HashMap)jsonParam.get("_param");
            String ProjectID = (String)paramMap.get("ProjectID");
            String UserID = (String)paramMap.get("UserID");
    		QueryWrapper<SAccountuserproject> wrapper = new QueryWrapper<SAccountuserproject>();
    		wrapper.eq("UserID", UserID);
    		SAccountuserproject exit = iSAccountuserprojectService.getOne(wrapper);
    		SAccountuserproject userproject = new SAccountuserproject();
    		if(exit == null){
    			userproject.setUserID(UserID);
    			userproject.setProjectID(ProjectID);
    			iSAccountuserprojectService.save(userproject);
    		}else{
    			Map<String,Object> map = new HashMap<String,Object>();
    			map.put("UserID", UserID);
    			map.put("ProjectID", ProjectID);
    			iSAccountuserprojectService.changeUserProject(map);
    		}
    		return ResponseMessage.ok("切换成功");
    	}catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("系统异常，请联系管理员");
		}
    }

    @ResponseBody
    @ApiOperation(value = "用户切换岗位", notes = "用户切换岗位")
    @RequestMapping(value = "/mUserProjectJobChange_Update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage mUserProjectJobChange_Update(@RequestBody JSONObject jsonParam) {
    	try{
    		// 直接将json信息打印出来
            System.out.println(jsonParam.toJSONString());
            Map paramMap = (HashMap)jsonParam.get("_param");
            String ProjectID = (String)paramMap.get("ProjectID");
            String UserID = (String)paramMap.get("UserID");
            String JobCode = (String)paramMap.get("JobCode");
    		QueryWrapper<SAccountuserprojectjob> wrapper = new QueryWrapper<SAccountuserprojectjob>();
    		wrapper.eq("UserID", UserID);
    		wrapper.eq("ProjectID", ProjectID);
    		SAccountuserprojectjob exit = iSAccountuserprojectjobService.getOne(wrapper);
    		SAccountuserprojectjob userprojectJob = new SAccountuserprojectjob();
    		if(exit == null){
    			userprojectJob.setUserID(UserID);
    			userprojectJob.setProjectID(ProjectID);
    			userprojectJob.setJobCode(JobCode);
    			iSAccountuserprojectjobService.save(userprojectJob);
    		}else{
    			Map<String,Object> map = new HashMap<String,Object>();
    			map.put("UserID", UserID);
    			map.put("ProjectID", ProjectID);
    			map.put("JobCode", JobCode);
    			iSAccountuserprojectjobService.changeUserProjectJob(map);
    		}
    		return ResponseMessage.ok("切换成功");
    	}catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("系统异常，请联系管理员");
		}
    }
}
