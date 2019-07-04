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
@Api(tags = "APP-项目房源", value = "APP-项目")
@RequestMapping("/app/project")
public class ProjectAppController extends TahoeBaseController {

    

    
}
