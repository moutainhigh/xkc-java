package com.tahoecn.xkc.controller.webapi.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.service.sys.ITaskService;

import io.swagger.annotations.ApiOperation;

/**
 * TODO
 * @ClassName:  TaskController
 * @author: wq_qycc
 * @date:   2019年8月2日
 */
@RestController
@RequestMapping("/webapi/sys/task")
public class TaskController extends TahoeBaseController {

	@Autowired
	private ITaskService taskService;

	@ApiOperation(value = "获取服务列表", notes = "获取服务列表")
	@RequestMapping(value = "/ServiceList_Select", method = { RequestMethod.GET })
	public Result ServiceList_Select(String code) {
		taskService.taskRun(code);
		return Result.ok("");
	}

}
