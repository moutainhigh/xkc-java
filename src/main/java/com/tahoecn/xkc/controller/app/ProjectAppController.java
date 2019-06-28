package com.tahoecn.xkc.controller.app;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.landray.sso.client.util.StringUtil;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.ResponseMessage;
import com.tahoecn.xkc.model.project.SAccountuserproject;
import com.tahoecn.xkc.service.project.ISAccountuserprojectService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/app/project")
public class ProjectAppController extends TahoeBaseController {

    @Autowired
    private ISAccountuserprojectService iSAccountuserprojectService;

    @ResponseBody
    @ApiOperation(value = "用户切换项目", notes = "用户切换项目")
    @ApiImplicitParams({@ApiImplicitParam(name = "projectId", value = "项目Id", required = true, dataType = "String"),
    	@ApiImplicitParam(name = "userId", value = "用户Id", required = true, dataType = "String")})
    @RequestMapping(value = "/mUserProjectChange_Update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage mUserProjectChange_Update(String userId, String projectId) {
    	if(StringUtil.isNull(projectId) || StringUtil.isNull(userId)){
    		return ResponseMessage.error("参数录入不完整，请检查参数信息");
    	}
    	try{
    		QueryWrapper<SAccountuserproject> wrapper = new QueryWrapper<SAccountuserproject>();
    		wrapper.eq("UserID", userId);
    		SAccountuserproject exit = iSAccountuserprojectService.getOne(wrapper);
    		SAccountuserproject userproject = new SAccountuserproject();
    		if(exit == null){
    			userproject.setUserID(userId);
    			userproject.setProjectID(projectId);
    			iSAccountuserprojectService.save(userproject);
    		}else{
    			Map<String,Object> map = new HashMap<String,Object>();
    			map.put("userId", userId);
    			map.put("projectId", projectId);
    			iSAccountuserprojectService.changeUserProject(map);
    		}
    		return ResponseMessage.ok("切换成功");
    	}catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("系统异常，请联系管理员");
		}
    }

}
