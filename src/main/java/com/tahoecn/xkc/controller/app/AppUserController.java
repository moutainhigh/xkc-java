package com.tahoecn.xkc.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.ResponseMessage;
import com.tahoecn.xkc.model.channel.BChanneluser;
import com.tahoecn.xkc.service.channel.IBChanneluserService;

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
@Api(tags = "APP-用户接口", value = "APP-用户接口")
@RequestMapping("/app/user")
public class AppUserController extends TahoeBaseController {
	
    @Autowired
    private IBChanneluserService iBChanneluserService;

	@ResponseBody
    @ApiOperation(value = "用户基本信息查询", notes = "用户基本信息查询")
    @RequestMapping(value = "/mUserDetail_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage mUserDetail_Select(@RequestBody JSONObject jsonParam) {
    	try{
    		// 直接将json信息打印出来
    		System.out.println(jsonParam.toJSONString());
            Map paramMap = (HashMap)jsonParam.get("_param");
            String ID = (String)paramMap.get("ID").toString();//ID
    		Map<String,Object> map = new HashMap<String,Object>();
    		map.put("ID", ID);
    		
    		List<BChanneluser> userNews = iBChanneluserService.ChannelUser_Detail_FindById(map);
    		return ResponseMessage.ok(userNews);
    	}catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("系统异常，请联系管理员");
		}
    }

}
