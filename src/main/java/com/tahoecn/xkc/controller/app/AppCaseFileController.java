package com.tahoecn.xkc.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.customer.VCustomerfjlistSelect;
import com.tahoecn.xkc.service.channel.IBChannelService;
import com.tahoecn.xkc.service.customer.IBClueService;
import com.tahoecn.xkc.service.customer.IVCustomerfjlistSelectService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
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
@Api(tags = "APP-案场助手", value = "APP-案场助手")
@RequestMapping("/app/casefile")
public class AppCaseFileController extends TahoeBaseController {
	@Value("${SiteUrl}")
    private String SiteUrl;
    @Autowired
    private IBClueService iBClueService;
    @Autowired
    private IVCustomerfjlistSelectService iVCustomerfjlistSelectService;
    @Autowired
    private IBChannelService iBChannelService;
    
	@ResponseBody
    @ApiOperation(value = "助手首页统计", notes = "助手首页统计")
    @RequestMapping(value = "/mCaseFieDetail_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mCaseFieDetail_Select(@RequestBody JSONObject jsonParam) {
    	try{
            @SuppressWarnings("rawtypes")
			Map paramMap = (HashMap)jsonParam.get("_param");
            //查询
            Map<String,Object> ob = iBClueService.CaseFieDetail_Select(paramMap);
            Map<String,Object> pa = new HashMap<String,Object>();
            pa.put("PageSize", "1");
            pa.put("ProjectID", paramMap.get("ProjectID"));
            pa.put("PageIndex", "1");
            pa.put("Mobile", "");
            pa.put("OrgID", paramMap.get("OrgID"));
            pa.put("Filter", "");
            pa.put("UserID", paramMap.get("UserID"));
            pa.put("Sort", "");
            List<VCustomerfjlistSelect> full = iVCustomerfjlistSelectService.CustomerList(pa);
            ob.put("Visit", full.size());
            return Result.ok(ob);
    	}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
    }
	@ResponseBody
	@ApiOperation(value = "扫码确认", notes = "扫码确认")
	@RequestMapping(value = "/mCaseFieCustomerDetail_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mCaseFieCustomerDetail_Select(@RequestBody JSONObject jsonParam) {
		try{
			return iBChannelService.mCaseFieCustomerDetail_Select(jsonParam);
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}

	@ResponseBody
    @ApiOperation(value = "客户列表(待确认 已确认 无效)", notes = "客户列表(待确认 已确认 无效)")
    @RequestMapping(value = "/mCaseFieToBeConfirmedList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mCaseFieToBeConfirmedList_Select(@RequestBody JSONObject jsonParam) {
    	try{
            @SuppressWarnings("rawtypes")
			Map paramMap = (HashMap)jsonParam.get("_param");
            int PageIndex = (int)paramMap.get("PageIndex");//页面索引
            int PageSize = (int)paramMap.get("PageSize");//每页数量
            String ProjectID = (String)paramMap.get("ProjectID");
            String Status = (String)paramMap.get("Status");
            //查询
            String where = "";
            if (!StringUtils.isEmpty(paramMap.get("Condition"))){
            	where = "  and (c.name = '" + paramMap.get("Condition") + "' or c.Mobile = '" + paramMap.get("Condition") + "')  ";
            }
//            paramMap.put("sqlWhere", where);
            IPage page = new Page(PageIndex, PageSize);
			IPage<Map<String, Object>> ob = iBClueService.CaseFieToBeConfirmedList_Select(page,Status,ProjectID,where);
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("List", ob.getRecords());
			map.put("AllCount", ob.getTotal());
			map.put("PageSize", ob.getSize());
			return Result.ok(map);
    	}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
    }
	@ResponseBody
	@ApiOperation(value = "带分配列表", notes = "带分配列表")
	@RequestMapping(value = "/mCaseFieDistributionList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mCaseFieDistributionList_Select(@RequestBody JSONObject jsonParam) {
		try{
			@SuppressWarnings("rawtypes")
			Map paramMap = (HashMap)jsonParam.get("_param");
			int PageIndex = (int)paramMap.get("PageIndex");//页面索引
            int PageSize = (int)paramMap.get("PageSize");//每页数量
            String ProjectID = (String)paramMap.get("ProjectID");
            String UserID = (String)paramMap.get("UserID");
			//查询
			String where = "";
			if (!StringUtils.isEmpty(paramMap.get("Condition"))){
				where = "  and (c.name like '%" + paramMap.get("Condition") + "%' or c.Mobile like '%" + paramMap.get("Condition") + "%')  ";
			}
//			paramMap.put("sqlWhere", where);
			IPage page = new Page(PageIndex, PageSize);
			IPage<Map<String, Object>> ob = iBClueService.CaseFieDistributionList_Select(page,ProjectID,UserID,where);
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("List", ob.getRecords());
			map.put("AllCount", ob.getTotal());
			map.put("PageSize", ob.getSize());
			return Result.ok(map);
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
	@ResponseBody
	@ApiOperation(value = "报备查询", notes = "报备查询")
	@RequestMapping(value = "/mCaseFielInquiriesList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mCaseFielInquiriesList_Select(@RequestBody JSONObject jsonParam) {
		try{
			Map paramMap = (HashMap)jsonParam.get("_param");
			int PageIndex = (int)paramMap.get("PageIndex");//页面索引
			int PageSize = (int)paramMap.get("PageSize");//每页数量
			String ProjectID = (String)paramMap.get("ProjectID");
			//查询
			String where = "and 1=2";
			Map<String, Object> map = new HashMap<String,Object>();
			if (!StringUtils.isEmpty(paramMap.get("Condition"))){
				where = "  and (c.name = '" + paramMap.get("Condition") + "' or c.Mobile = '" + paramMap.get("Condition") + "')  ";
				IPage page = new Page(PageIndex, PageSize);
				IPage<Map<String, Object>> ob = iBClueService.CaseFielInquiriesList_Select(page,ProjectID,where);
				map.put("List", ob.getRecords());
				map.put("AllCount", ob.getTotal());
				map.put("PageSize", ob.getSize());
			}else{
				map.put("List", new ArrayList());
				map.put("AllCount", 0);
				map.put("PageSize", PageSize);
			}
			return Result.ok(map);
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
}
