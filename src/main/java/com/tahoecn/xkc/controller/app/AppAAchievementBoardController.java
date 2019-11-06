package com.tahoecn.xkc.controller.app;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.service.project.IAAchievementBoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(tags = "APP-业绩看板接口", value = "置业顾问业绩看板接口")
@RestController
@RequestMapping("/app/achievementBoard")
public class AppAAchievementBoardController extends TahoeBaseController {
	@Autowired
	private IAAchievementBoardService iAAchievementBoardService;

	@ApiOperation(value = "销售业绩数据", notes = "置业顾问业绩看板-首页销售业绩数据,详情")
	@RequestMapping(value = "/mYJKBSalesPerformanceData_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mYJKBSalesPerformanceData_Select(@RequestBody JSONObject jsonParam) {
		JSONObject param = jsonParam.getJSONObject("_param");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd 23:59:59");
        if (StringUtils.isEmpty(param.get("ProjectID"))){
            return Result.errormsg(1, "参数错误");
        }
        if (StringUtils.isEmpty(param.get("StartDate"))){
        	param.put("StartDate","1900/01/01");
        }
        if (StringUtils.isEmpty(param.get("EndDate"))){
        	param.put("EndDate", sdf.format(new Date()));
        }else{
        	param.put("EndDate", sdf.format(param.getDate("EndDate")));
        }
        //获取认购、认筹、签约、回款、数据
        Map<String,Object> obj = iAAchievementBoardService.mYJKBSalesPerformanceData_Select(param);
        if (obj.size() == 0){
            obj.put("OrderCount", "0");
            obj.put("OrderAmount", "0.00");
            obj.put("BookingCount", "0");
            obj.put("BookingAmount", "0.00");
            obj.put("ContractCount", "0");
            obj.put("ContractAmount", "0.00");
            obj.put("PaybackCount", "0");
            obj.put("PaybackAmount", "0.00");
        }
        //获取逾期未签约数据
        Map<String,Object> objOC = iAAchievementBoardService.mSalesPerformanceOverdueContractData_Select(param);
        //获取逾期款数据
        Map<String,Object> objOP = iAAchievementBoardService.mSalesPerformanceOverduePaymentData_Select(param);
        if(objOC == null){
        	obj.put("OverdueContractCount", 0);
        	obj.put("OverdueContractAmount", "0.00");
        }else{
        	obj.put("OverdueContractCount", objOC.get("OverdueContractCount")!= null ? objOC.get("OverdueContractCount") : 0);
        	obj.put("OverdueContractAmount", objOC.get("OverdueContractAmount")!=null ? objOC.get("OverdueContractAmount").toString() : "0.00");
        }
        if(objOP == null){
        	obj.put("OverduePaymentCount", 0);
        	obj.put("OverduePaymentAmount", "0.00"); 
        }else{
        	obj.put("OverduePaymentCount",  objOP.get("OverduePaymentCount")!= null ? objOP.get("OverduePaymentCount") : 0);
        	obj.put("OverduePaymentAmount", objOP.get("OverduePaymentAmount")!=null ? objOP.get("OverduePaymentAmount").toString() : "0.00"); 
        }
        return Result.ok(obj);
	}
	@ApiOperation(value = "客储达成首页数据", notes = "置业顾问业绩看板-客储达成首页数据")
	@RequestMapping(value = "/mYJKBCustomerRankData_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mYJKBCustomerRankData_Select(@RequestBody JSONObject jsonParam) {
		JSONObject param = jsonParam.getJSONObject("_param");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd 23:59:59");
		if (StringUtils.isEmpty(param.get("ProjectID"))){
			return Result.errormsg(1, "参数错误");
		}
		if (StringUtils.isEmpty(param.get("StartDate"))){
			param.put("StartDate","1900/01/01");
		}
		if (StringUtils.isEmpty(param.get("EndDate"))){
			param.put("EndDate", sdf.format(new Date()));
		}else{
			param.put("EndDate", sdf.format(param.getDate("EndDate")));
		}
		List<Map<String,Object>> obj = iAAchievementBoardService.mYJKBCustomerRankData_Select(param);
		return Result.ok(obj);
	}
	@ApiOperation(value = "首页业绩排名列表", notes = "置业顾问业绩看板-首页业绩排名列表")
	@RequestMapping(value = "/mYJKBSaleUserRankList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mYJKBSaleUserRankList_Select(@RequestBody JSONObject jsonParam) {
		JSONObject param = jsonParam.getJSONObject("_param");
		int PageSize = param.getIntValue("PageSize");
		int PageIndex = param.getIntValue("PageIndex");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd 23:59:59");
		if (StringUtils.isEmpty(param.get("ProjectID"))){
			return Result.errormsg(1, "参数错误");
		}
		if (StringUtils.isEmpty(param.get("StartDate"))){
			param.put("StartDate","1900/01/01");
		}
		if (StringUtils.isEmpty(param.get("EndDate"))){
			param.put("EndDate", sdf.format(new Date()));
		}else{
			param.put("EndDate", sdf.format(param.getDate("EndDate")));
		}
		StringBuilder orderBySb = new StringBuilder();
        if (!StringUtils.isEmpty(param.getString("Sort"))){
            //判断排序方式
            if ("1".equals(param.getString("Sort"))){
                orderBySb.append("ORDER BY ContractCount DESC");
            }else if ("2".equals(param.getString("Sort"))){
                orderBySb.append("ORDER BY ContractCount ASC");
            }else if ("3".equals(param.getString("Sort"))){
                orderBySb.append("ORDER BY ContractAmount DESC");
            }else if ("4".equals(param.getString("Sort"))){
                orderBySb.append("ORDER BY ContractAmount ASC");
            }
        }else{
            orderBySb.append("ORDER BY ContractAmount DESC");
        }
        param.put("ORDERBY",orderBySb.toString());
        param.put("WHERE", "");
        IPage page = new Page(PageIndex, PageSize);
        Map<String,Object> objData = iAAchievementBoardService.mYJKBSaleUserRankList_Select(param);
        //根据UserID获取当前排名
        param.remove("WHERE");
        param.put("WHERE", String.format(" AND SaleUserID = '"+param.get("UserID")+"'"));
        //获取当前排名
        Map<String,Object> objRank = iAAchievementBoardService.mYJKBSaleUserRankList_Select(param);
        int noPerRank = 0; //没有业绩，不在列表里时的排名
        if ((int)objData.get("AllCount") > 0){
            noPerRank = (int) objData.get("AllCount") + 1;
        }
        long curRank = (objRank.get("List") != null && ((List)objRank.get("List")).size() > 0) ? (Long) ((Map)((List)objRank.get("List")).get(0)).get("RankNum") : noPerRank;
        objData.put("Current",curRank);
        Map<String,Object> map = new HashMap<String,Object>(); 
        map.put("AllCount", objData.get("AllCount"));
        map.put("CurrentRank", objData.get("Current"));
        map.put("List", objData.get("List"));
        map.put("PageSize", objData.get("PageSize"));
		return Result.ok(map);
	}
	@ApiOperation(value = "客储达成详情列表", notes = "置业顾问业绩看板-客储达成详情列表")
	@RequestMapping(value = "/mYJKBCustomerRankDetailList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mYJKBCustomerRankDetailList_Select(@RequestBody JSONObject jsonParam) {
		JSONObject param = jsonParam.getJSONObject("_param");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd 23:59:59");
		if (StringUtils.isEmpty(param.get("ProjectID"))){
			return Result.errormsg(1, "参数错误");
		}
		if (StringUtils.isEmpty(param.get("StartDate"))){
			param.put("StartDate","1900/01/01");
		}
		if (StringUtils.isEmpty(param.get("EndDate"))){
			param.put("EndDate", sdf.format(new Date()));
		}else{
			param.put("EndDate", sdf.format(param.getDate("EndDate")));
		}
		//排序
        StringBuilder strOrderBy = new StringBuilder();
        String customerRank = param.getString("CustomerRank");
        if ("41FA0234-F8AE-434F-8BCD-6E9BE1D059DA".equals(customerRank)){ //1级客储
            strOrderBy.append(" ORDER BY ReportTime DESC");
        }else if ("FC420696-6F6C-40B1-BE17-96FCEC75B0F2".equals(customerRank)){ //1.5级
            strOrderBy.append(" ORDER BY OutReceptTime DESC");
        }else if ("ED0AD9E6-AF72-424C-9EE0-9884FF31FA42".equals(customerRank)){ //2级
            strOrderBy.append(" ORDER BY FirstVisitTime DESC");
        }else if ("D47878CE-D560-44C0-A6F8-4C92CCAC9EE0".equals(customerRank)){  //2.5级
            strOrderBy.append(" ORDER BY SmallPlanTime DESC");
        }else if ("652C3B40-64DE-4E6F-984F-BDBD592E6CA3".equals(customerRank)){  //3级
            strOrderBy.append(" ORDER BY BigPlanTime DESC");
        }
        param.put("ORDERBY", strOrderBy.toString());
        Map<String,Object> objData = iAAchievementBoardService.mYJKBCustomerRankDetailList_Select(param);
		return Result.ok(objData);
	}
	@ApiOperation(value = "认购详情列表", notes = "置业顾问业绩看板-销售业绩-认购详情列表")
	@RequestMapping(value = "/mYJKBOrderDetailList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mYJKBOrderDetailList_Select(@RequestBody JSONObject jsonParam) {
		JSONObject param = jsonParam.getJSONObject("_param");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd 23:59:59");
		if (StringUtils.isEmpty(param.get("ProjectID"))){
			return Result.errormsg(1, "参数错误");
		}
		if (StringUtils.isEmpty(param.get("StartDate"))){
			param.put("StartDate","1900/01/01");
		}
		if (StringUtils.isEmpty(param.get("EndDate"))){
			param.put("EndDate", sdf.format(new Date()));
		}else{
			param.put("EndDate", sdf.format(param.getDate("EndDate")));
		}
		Map<String,Object> obj = iAAchievementBoardService.mYJKBOrderDetailList_Select(param);
		return Result.ok(obj);
	}
	@ApiOperation(value = "签约详情列表", notes = "置业顾问业绩看板-销售业绩-签约详情列表")
	@RequestMapping(value = "/mYJKBContractDetailList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mYJKBContractDetailList_Select(@RequestBody JSONObject jsonParam) {
		JSONObject param = jsonParam.getJSONObject("_param");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd 23:59:59");
		if (StringUtils.isEmpty(param.get("ProjectID"))){
			return Result.errormsg(1, "参数错误");
		}
		if (StringUtils.isEmpty(param.get("StartDate"))){
			param.put("StartDate","1900/01/01");
		}
		if (StringUtils.isEmpty(param.get("EndDate"))){
			param.put("EndDate", sdf.format(new Date()));
		}else{
			param.put("EndDate", sdf.format(param.getDate("EndDate")));
		}
		Map<String,Object> obj = iAAchievementBoardService.mYJKBContractDetailList_Select(param);
		return Result.ok(obj);
	}
	@ApiOperation(value = "逾期未签约详情列表", notes = "置业顾问业绩看板-销售业绩-逾期未签约详情列表")
	@RequestMapping(value = "/mYJKBOverdueContractDetailList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mYJKBOverdueContractDetailList_Select(@RequestBody JSONObject jsonParam) {
		JSONObject param = jsonParam.getJSONObject("_param");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd 23:59:59");
		if (StringUtils.isEmpty(param.get("ProjectID"))){
			return Result.errormsg(1, "参数错误");
		}
		if (StringUtils.isEmpty(param.get("StartDate"))){
			param.put("StartDate","1900/01/01");
		}
		if (StringUtils.isEmpty(param.get("EndDate"))){
			param.put("EndDate", sdf.format(new Date()));
		}else{
			param.put("EndDate", sdf.format(param.getDate("EndDate")));
		}
		Map<String,Object> obj = iAAchievementBoardService.mYJKBOverdueContractDetailList_Select(param);
		return Result.ok(obj);
	}
	@ApiOperation(value = "认筹详情列表", notes = "置业顾问业绩看板-销售业绩-认筹详情列表")
	@RequestMapping(value = "/mYJKBBookingDetailList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mYJKBBookingDetailList_Select(@RequestBody JSONObject jsonParam) {
		JSONObject param = jsonParam.getJSONObject("_param");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd 23:59:59");
		if (StringUtils.isEmpty(param.get("ProjectID"))){
			return Result.errormsg(1, "参数错误");
		}
		if (StringUtils.isEmpty(param.get("StartDate"))){
			param.put("StartDate","1900/01/01");
		}
		if (StringUtils.isEmpty(param.get("EndDate"))){
			param.put("EndDate", sdf.format(new Date()));
		}else{
			param.put("EndDate", sdf.format(param.getDate("EndDate")));
		}
		Map<String,Object> obj = iAAchievementBoardService.mYJKBBookingDetailList_Select(param);
		return Result.ok(obj);
	}
	@ApiOperation(value = "回款详情列表", notes = "置业顾问业绩看板-销售业绩-回款详情列表")
	@RequestMapping(value = "/mYJKBPaybackDetailList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mYJKBPaybackDetailList_Select(@RequestBody JSONObject jsonParam) {
		JSONObject param = jsonParam.getJSONObject("_param");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd 23:59:59");
		if (StringUtils.isEmpty(param.get("ProjectID"))){
			return Result.errormsg(1, "参数错误");
		}
		if (StringUtils.isEmpty(param.get("StartDate"))){
			param.put("StartDate","1900/01/01");
		}
		if (StringUtils.isEmpty(param.get("EndDate"))){
			param.put("EndDate", sdf.format(new Date()));
		}else{
			param.put("EndDate", sdf.format(param.getDate("EndDate")));
		}
		Map<String,Object> obj = iAAchievementBoardService.mYJKBPaybackDetailList_Select(param);
		return Result.ok(obj);
	}
	@ApiOperation(value = "逾期款详情列表", notes = "置业顾问业绩看板-销售业绩-逾期款详情列表")
	@RequestMapping(value = "/mYJKBOverduePayDetailList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mYJKBOverduePayDetailList_Select(@RequestBody JSONObject jsonParam) {
		JSONObject param = jsonParam.getJSONObject("_param");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd 23:59:59");
		if (StringUtils.isEmpty(param.get("ProjectID"))){
			return Result.errormsg(1, "参数错误");
		}
		if (StringUtils.isEmpty(param.get("StartDate"))){
			param.put("StartDate","1900/01/01");
		}
		if (StringUtils.isEmpty(param.get("EndDate"))){
			param.put("EndDate", sdf.format(new Date()));
		}else{
			param.put("EndDate", sdf.format(param.getDate("EndDate")));
		}
		Map<String,Object> obj = iAAchievementBoardService.mYJKBOverduePayDetailList_Select(param);
		return Result.ok(obj);
	}
}