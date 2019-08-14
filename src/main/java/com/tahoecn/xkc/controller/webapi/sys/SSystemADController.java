package com.tahoecn.xkc.controller.webapi.sys;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.sys.BAppupgrade;
import com.tahoecn.xkc.service.sys.IBAppupgradeService;
import com.tahoecn.xkc.service.sys.IBSystemadService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/webapi/sys/SystemAD")
public class SSystemADController extends TahoeBaseController {
	@Value("${SystemADPath}")
	private String SystemADPath;

	@Autowired
    private IBSystemadService iBSystemadService;
	@Autowired
    private IBAppupgradeService iBAppupgradeService;
	
	@ResponseBody
    @ApiOperation(value = "广告配置", notes = "广告配置")
    @RequestMapping(value = "/SystemAD_Insert", method = {RequestMethod.POST})
    public Result SystemAD_Insert(String Title, String ShareContent,@RequestParam String PictureURL,@RequestParam MultipartFile image) {
        try {
        	String ID = UUID.randomUUID().toString();
        	String exName = image.getOriginalFilename();
    		StringBuilder PicturePath = new StringBuilder(SystemADPath);
    		//获取当前时间
    		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
    		String times = formatter.format(new Date());
    		PicturePath.append(times+exName);
    		//图片存放路径
    		File file = new File(PicturePath.toString());
    		Map<String,Object> map = new HashMap<String,Object>();
    		map.put("ID", ID);
    		map.put("Title", Title);
    		map.put("ShareContent", ShareContent);
    		map.put("PicturePath", PicturePath.toString());
    		map.put("PictureURL", PictureURL);
    		if(!file.getParentFile().exists()) {
    		    file.getParentFile().mkdirs();
    		}
    		image.transferTo(file);
    		System.out.println(PicturePath.toString());
    		iBSystemadService.SystemAD_Update(map);//修改原有的广告数据
    		iBSystemadService.SystemAD_Insert(map);//插入新的广告数据
    		return Result.ok("保存成功");
        }catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
        }
    }
	@ResponseBody
	@ApiOperation(value = "APP版本获取", notes = "获取APP版本信息")
	@RequestMapping(value = "/mSystemAppVersion_Select", method = {RequestMethod.GET})
	public Result mSystemAppVersion_Select() {
		try {
			List<Map<String,Object>> bAppupgrade = iBAppupgradeService.SystemAppVersionList_Select();
    		return Result.ok(bAppupgrade);
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ResponseBody
	@ApiOperation(value = "APP版本修改", notes = "APP版本信息修改")
	@ApiImplicitParams({@ApiImplicitParam(name = "VersionID_ANDROID" , value = "APP版本ID_ANDROID" , required = true , dataType = "String"),
		@ApiImplicitParam(name = "AppVersionCode_ANDROID" , value = "APP版本号_ANDROID" , required = true , dataType = "String"),
		@ApiImplicitParam(name = "Url_ANDROID" , value = "下载地址_ANDROID" , required = true , dataType = "String"),
		@ApiImplicitParam(name = "VersionID_IOS" , value = "APP版本ID_IOS" , required = true , dataType = "String"),
		@ApiImplicitParam(name = "AppVersionCode_IOS" , value = "APP版本号_IOS" , required = true , dataType = "String"),
		@ApiImplicitParam(name = "Url_IOS" , value = "下载地址_IOS" , required = true , dataType = "String"),
		@ApiImplicitParam(name = "VersionID_IPAD" , value = "APP版本ID_IPAD" , required = true , dataType = "String"),
		@ApiImplicitParam(name = "AppVersionCode_IPAD" , value = "APP版本号_IPAD" , required = true , dataType = "String"),
		@ApiImplicitParam(name = "Url_IPAD" , value = "下载地址_IPAD" , required = true , dataType = "String")})
	@RequestMapping(value = "/mSystemAppVersion_Update", method = {RequestMethod.POST})
	public Result mSystemAppVersion_Update(String VersionID_ANDROID,String AppVersionCode_ANDROID,String Url_ANDROID,
			String VersionID_IOS,String AppVersionCode_IOS,String Url_IOS,
			String VersionID_IPAD,String AppVersionCode_IPAD,String Url_IPAD) {
		try {
			iBAppupgradeService.SystemAppVersion_Update(VersionID_ANDROID,AppVersionCode_ANDROID,Url_ANDROID,
					VersionID_IOS,AppVersionCode_IOS,Url_IOS,
					VersionID_IPAD,AppVersionCode_IPAD,Url_IPAD);
			return Result.ok("保存成功");
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
}