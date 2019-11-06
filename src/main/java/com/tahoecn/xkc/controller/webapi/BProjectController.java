package com.tahoecn.xkc.controller.webapi;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.ResponseMessage;
import com.tahoecn.xkc.model.project.BProject;
import com.tahoecn.xkc.service.project.IBProjectService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/bProject")
public class BProjectController extends TahoeBaseController {
    @Autowired
    private IBProjectService iBProjectService;

    @ApiImplicitParams({@ApiImplicitParam(name = "projectId", value = "项目Id", required = true, dataType = "String") })
    @ApiOperation(value = "获取项目信息接口", notes = "获取项目信息接口")
    @RequestMapping(value = "/ProjectParameterDetail_Select", method = { RequestMethod.GET })
    public ResponseMessage ProjectParameterDetail_Select(@RequestParam(required = true) String projectId) {
        QueryWrapper<BProject> queryWrapper = new QueryWrapper<BProject>();
        queryWrapper.eq(StringUtils.isNotBlank(projectId), "ID", projectId);
        BProject project = (BProject)iBProjectService.getObj(queryWrapper);
        return ResponseMessage.ok(project);
    }

}
