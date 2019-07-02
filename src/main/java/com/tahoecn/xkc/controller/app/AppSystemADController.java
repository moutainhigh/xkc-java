package com.tahoecn.xkc.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.ResponseMessage;
import com.tahoecn.xkc.model.sys.BSystemad;
import com.tahoecn.xkc.service.sys.IBSystemadService;

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
@Api(tags = "APP-广告接口", value = "APP-广告接口")
@RequestMapping("/app/systemAD")
public class AppSystemADController extends TahoeBaseController {

    @Autowired
    private IBSystemadService iBSystemadService;

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
}
