package com.tahoecn.xkc.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tahoecn.core.date.DateTime;
import com.tahoecn.xkc.common.utils.NetUtil;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.ResponseMessage;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.sys.BAppupgrade;
import com.tahoecn.xkc.model.sys.BSystemad;
import com.tahoecn.xkc.model.sys.SCity;
import com.tahoecn.xkc.model.sys.SFormsession;
import com.tahoecn.xkc.service.customer.IBCustomerService;
import com.tahoecn.xkc.service.dict.ISDictionaryService;
import com.tahoecn.xkc.service.salegroup.IBSalesuserService;
import com.tahoecn.xkc.service.sys.IBAppupgradeService;
import com.tahoecn.xkc.service.sys.IBSystemadService;
import com.tahoecn.xkc.service.sys.IBVerificationcodeService;
import com.tahoecn.xkc.service.sys.ISCityService;
import com.tahoecn.xkc.service.sys.ISFormsessionService;
import com.tahoecn.xkc.service.sys.ISLogsService;
import com.tahoecn.xkc.service.uc.CsSendSmsLogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@Api(tags = "APP-系统接口", value = "APP-系统接口")
@RequestMapping("/app/system")
public class AppSystemController extends TahoeBaseController {

	@Value("${ImgSiteUrl}")
	private String imgSiteUrl;
	@Value("${ShareUrl}")
	private String ShareUrl;
	@Value("${yuanshiID}")
	private String yuanshiID;
	@Value("${WXDetailPagePath}")
	private String WXDetailPagePath;
    @Autowired
    private IBSystemadService iBSystemadService;
    @Autowired
    private IBAppupgradeService iBAppupgradeService;
    @Autowired
    private ISLogsService iSLogsService;
    @Autowired
    private IBVerificationcodeService iBVerificationcodeService;
    @Autowired 
	private CsSendSmsLogService csSendSmsLogService;
    @Autowired 
    private ISFormsessionService iSFormsessionService;
    @Autowired 
    private IBCustomerService iBCustomerService;
    @Autowired 
    private IBSalesuserService iBSalesuserService;
    @Autowired
    private ISDictionaryService dictionaryService;
    @Autowired
    private ISCityService cityService;
    
	@ResponseBody
    @ApiOperation(value = "广告接口", notes = "广告接口")
    @RequestMapping(value = "/SystemAD_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result SystemAD_Select(@RequestBody JSONObject jsonParam) {
    	try{
    		// 直接将json信息打印出来
            System.out.println(jsonParam.toJSONString());
            Map paramMap = (HashMap)jsonParam.get("_param");
            String ADType = (String)paramMap.get("ADType").toString();
    		Map<String,Object> map = new HashMap<String,Object>();
    		map.put("ADType", ADType);
    		List<Map<String,Object>> ataAd = iBSystemadService.SystemAD_Detail_Find(map);//获取一条信息-(默认)
    		return Result.ok(ataAd!= null && ataAd.size()!=0 ?ataAd.get(0):"");
    	}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
    }
	
	@ResponseBody
    @ApiOperation(value = "APP版本信息", notes = "APP版本信息")
    @RequestMapping(value = "/mSystemAppVersion_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mSystemAppVersion_Select(@RequestBody JSONObject jsonParam) {
    	try{
            Map paramMap = (HashMap)jsonParam.get("_param");
            String Platform = (String) paramMap.get("Platform");
            String AppName = (String) paramMap.get("AppName");
            String Model = (String) paramMap.get("Model");
            String DeviceCode = (String) paramMap.get("DeviceCode");
    		
            Map<String,Object> map = new HashMap<String,Object>();
    		map.put("Platform", Platform);
    		map.put("AppName", AppName);
    		map.put("Model", Model);
    		
    		//1.APP版本日志记录
    		Map<String,Object> logMap = new HashMap<String,Object>();
            logMap.put("BizType", "AppVersion");
            logMap.put("BizDesc", "获取App版本,平台:" + Platform + ",App:" + AppName);
            logMap.put("Ext1", Model);
            logMap.put("Ext2", DeviceCode);
            logMap.put("Ext3", Platform);
            logMap.put("Ext4", AppName);
            logMap.put("Data", jsonParam.toString());
            iSLogsService.SystemLogsDetail_Insert(logMap,request);
    		//2.获取信息
    		List<BAppupgrade> bAppupgrade = iBAppupgradeService.SystemAppVersion_Select(map);
    		
    		return Result.ok(bAppupgrade!= null && bAppupgrade.size()!=0 ?bAppupgrade.get(0):"");
    	}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
    }
	
	@ResponseBody
    @ApiOperation(value = "获取验证码", notes = "获取验证码")
    @RequestMapping(value = "/mVerificationCode_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mVerificationCode_Select(@RequestBody JSONObject jsonParam) {
    	try{
            Map paramMap = (HashMap)jsonParam.get("_param");
            String Mobile = (String)paramMap.get("Mobile").toString();
            Random ran = new Random();
            int verificationCode = ran.nextInt(899999)+100000;
            //写入数据,并调取短信接口
            Map<String,Object> parameter = new HashMap<String,Object>();
            parameter.put("Mobile", Mobile);//string
            parameter.put("VerificationCode", String.valueOf(verificationCode));//string
            iBVerificationcodeService.Detail_Add(parameter);
            csSendSmsLogService.sendSms(Mobile,"【泰禾集团】验证码："+String.valueOf(verificationCode)+"，5分钟内有效","");
            return Result.ok(String.valueOf(verificationCode));
    	}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
    }
	@ResponseBody
	@ApiOperation(value = "置业顾问-获取FormSessionID", notes = "置业顾问-获取FormSessionID")
	@RequestMapping(value = "/mSystemFormSession_Insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mSystemFormSession_Insert(@RequestBody JSONObject jsonParam) {
		try{
			Map paramMap = (HashMap)jsonParam.get("_param");
			SFormsession sess = new SFormsession();
			sess.setIp(NetUtil.getRemoteAddr(request));
			sess.setData(JSONObject.toJSONString(paramMap));
			sess.setCreateTime(new Date());
			sess.setStatus(1);
			iSFormsessionService.save(sess);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("FormSessionID", sess.getId());
			return Result.ok(map);
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	
	@ResponseBody
    @ApiOperation(value = "线上预约客户列表", notes = "线上预约客户列表")
    @RequestMapping(value = "/mCustomerShareList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mCustomerShareList_Select(@RequestBody JSONObject jsonParam) {
    	try{
    		// 直接将json信息打印出来
            System.out.println(jsonParam.toJSONString());
            Map paramMap = (HashMap)jsonParam.get("_param");
            String UserID = (String)paramMap.get("UserID").toString();
            String ProjectID = (String)paramMap.get("ProjectID").toString();
            int PageIndex = (int)paramMap.get("PageIndex");//页面索引
            int PageSize = (int)paramMap.get("PageSize");//每页数量
            IPage page = new Page(PageIndex, PageSize);

    		IPage<Map<String, Object>> ataAd = iBCustomerService.mCustomerShareList_Select(page, UserID, ProjectID);
    		
    		Map<String,Object> map = new HashMap<String,Object>();
    		map.put("List", ataAd.getRecords());
    		map.put("AllCount", ataAd.getTotal());
    		map.put("PageSize", ataAd.getSize());
    		return Result.ok(map);
    	}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
    }
	
	@ResponseBody
    @ApiOperation(value = "分享小程序", notes = "分享小程序")
    @RequestMapping(value = "/mShareAppDetail_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mShareAppDetail_Select(@RequestBody JSONObject jsonParam) {
    	try{
    		// 直接将json信息打印出来
            System.out.println(jsonParam.toJSONString());
            Map paramMap = (HashMap)jsonParam.get("_param");
            String ShareProjectID = (String)paramMap.get("ShareProjectID").toString();
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("ShareProjectID", ShareProjectID);
            map.put("imgSiteUrl", imgSiteUrl);
            WXDetailPagePath = "/"+WXDetailPagePath;
            System.out.println(WXDetailPagePath);
            map.put("WXDetailPagePath", WXDetailPagePath);
            String str = iBSalesuserService.mShareAppDetail_Select1(map);  
            String url = "";
            String[] str1 = str.split(",");
            System.out.println(str1[0]);
            url = str1[0];
            map.put("Url", url);
            Map<String, Object> data = iBSalesuserService.mShareAppDetail_Select(map); 
            if(data.size() == 0 ) {
            	return Result.errormsg(1,"该项目没有分享传播模块信息");
            }
            else {
            	
            	data.put("UserName", yuanshiID);
            	data.put("ShareUrl", ShareUrl);
            	return Result.ok(data);
            }
    	}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
    }

    @ApiOperation(value = "数据字典", notes = "数据字典")
    @RequestMapping(value = "/mSystemDictionaryDetail_Select", method = {RequestMethod.POST})
    public Result mSystemDictionaryDetail_Select(@RequestBody JSONObject jsonParam) {
        HashMap<String,Object> param=(HashMap)jsonParam.get("_param");
        Result result=dictionaryService.SystemDictionaryDetail(param);
        return result;
    }
    @ApiOperation(value = "城市处理", notes = "户籍所在地")
    @RequestMapping(value = "/mSystemCity_Select", method = {RequestMethod.POST})
    public Result mSystemCity_Select(@RequestBody JSONObject jsonParam) {
    	String IDAlias = "ID";
        String ChildAlias = "Child";
        String DictNameAlias = "DictName";
        Map<String,Object> res=new HashMap<>();
    	//region 城市处理
        QueryWrapper<SCity> wrapper=new QueryWrapper<>();
        wrapper.eq("Status",1).le("Levels",4).orderByAsc("Levels","StandardIndex");
        List<SCity> list =  cityService.list(wrapper);
        Map<String,Map<String,Object>> province = new LinkedHashMap<>();
        Map<String,Map<String,Object>> city = new LinkedHashMap<>();
        for (SCity sCity : list) {
	        String PID=sCity.getPid();
	        int Levels=sCity.getLevels();
	        if (Levels==2){
	            String ID=sCity.getId();
	            Map<String,Object> dict = new HashMap<>();
	            dict.put(IDAlias,ID);
	            dict.put(DictNameAlias,sCity.getDispName());
	            dict.put(ChildAlias,new ArrayList<>());
	            province.put(ID,dict);
	        }
            if (Levels==3){
                if (province.get(PID) != null)
                {
                    String ID=sCity.getId();
                    Map<String,Object> dict = new HashMap<>();
                    dict.put(IDAlias,ID);
                    dict.put(DictNameAlias,sCity.getDispName());
                    dict.put(ChildAlias,new ArrayList<>());
                    dict.put("PID",PID);
                    city.put(ID,dict);
                }
            }
            if (Levels == 4)
            {
                if (city.get(PID)!= null)
                {
                    String ID=sCity.getId();
                    Map<String,Object> dict = new HashMap<>();
                    dict.put(IDAlias,ID);
                    dict.put(DictNameAlias,sCity.getDispName());
                    Map<String,Object> pid = city.get(PID);
                    ArrayList  childAlias = (ArrayList) pid.get(ChildAlias);
                    childAlias.add(dict);
                }
            }
        }
        if (province.size()>0){
            List resJa=new ArrayList();
            for (String key : city.keySet()) {
                String PID= (String) city.get(key).get("PID");
                if (province.get(PID)!=null){
                    city.get(key).remove("PID");
                    ArrayList  arrayList= (ArrayList) province.get(PID).get(ChildAlias);
                    arrayList.add(city.get(key));
                }
            }
            for (String key : province.keySet()) {
                resJa.add(province.get(key));
            }
            res.put("City",resJa);
        }
    	List data=new ArrayList();
        if (res.size() == 1){
        	for (String key : res.keySet()) {
                data = (List) res.get(key);
            }
        }
        if (res.size() > 1){
            for (String key : res.keySet()) {
                data.add(res.get(key));
            }
        }
        return Result.ok(data);
    }
}
