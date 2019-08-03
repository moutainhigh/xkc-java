package com.tahoecn.xkc.controller.webapi.sys;


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.tahoecn.xkc.common.utils.ExcelUtil;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.sys.SAccount;
import com.tahoecn.xkc.service.sys.ISLogsService;

import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YYY
 * @since 2019-07-01
 */
@RestController
@RequestMapping("/webapi/sys/sLogs")
public class SLogsController extends TahoeBaseController {
	
	@Autowired
    private ISLogsService iSLogsService;
	
	@ResponseBody
    @ApiOperation(value = "操作日志", notes = "操作日志")
    @RequestMapping(value = "/mBrokerCustomerDetail_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mBrokerCustomerDetail_Select( String BizType, String Ext1, String Ext3, String IP, String CreateTimeStart, String CreateTimeEnd, int PageIndex, int PageSize, String IsExcel) {
        try {
       
            StringBuilder str = new StringBuilder();
            Map<String, Object> map = new HashMap<String, Object>();
            Map<String, Object> data = new HashMap<String, Object>();
            if(BizType != null && BizType.length() > 0) {
            	str.append(" AND BizType like'%").append(BizType).append("%'");
            }
            if(Ext1 != null && Ext1.length() > 0) {
            	str.append(" AND Ext1 like'%").append(Ext1).append("%'");
            }
            if(Ext3 != null && Ext3.length() > 0) {
            	str.append(" AND Ext3 like'%").append(Ext3).append("%'");
            }
            if(CreateTimeStart != null && CreateTimeStart.length() > 0) {
            	str.append(" AND CreateTime >= '" +CreateTimeStart+ "'").append(" AND CreateTime <= '" +CreateTimeEnd+ "'");
            }
            if(IP != null && IP.length() > 0) {
            	str.append(" AND IP like'%").append(IP).append("%'");
            } 
            map.put("str1",str.toString());
            map.put("PageIndex",PageIndex);
            map.put("PageSize",PageSize);
            List<Map<String, Object>> obj = iSLogsService.mBrokerCustomerDetail_Select(map);
            int CoutAll = iSLogsService.mBrokerCustomerDetail_SelectAll(map);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            for(int x=0;x<obj.size();x++) {
            	String sd1 = sdf.format(obj.get(x).get("CreateTime"));
            	obj.get(x).put("CreateTime", sd1);
            }
            data.put("List", obj);
            data.put("Count", CoutAll);
            data.put("PageSize",PageSize);
            if(IsExcel != null && IsExcel.length() > 0) {
            	mBrokerCustomerDetail_SelectN(obj);
            	return null;
            }
        	return Result.ok(data);
        }catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
        }
    }
	private void mBrokerCustomerDetail_SelectN(List<Map<String, Object>> obj) {
		
        List<ExcelExportEntity> entity = new ArrayList<ExcelExportEntity>();
        entity.add(new ExcelExportEntity("序号", "num"));
        entity.add(new ExcelExportEntity("请求类型", "BizType"));
        entity.add(new ExcelExportEntity("请求说明", "BizDesc"));
        entity.add(new ExcelExportEntity("IP", "IP"));
        entity.add(new ExcelExportEntity("方法名", "Ext1"));
        entity.add(new ExcelExportEntity("请求路径", "Ext2"));
        entity.add(new ExcelExportEntity("请求终端", "Ext3"));
        entity.add(new ExcelExportEntity("参数", "Data"));
        entity.add(new ExcelExportEntity("请求时间", "CreateTime"));
        try {
            LocalDateTime time= LocalDateTime.now();
            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
            String name = dtf2.format(time) + ".xls";
            ExcelUtil.exportExcel(entity,obj,name,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
