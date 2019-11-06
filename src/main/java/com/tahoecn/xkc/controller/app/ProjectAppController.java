package com.tahoecn.xkc.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.project.BProject;
import com.tahoecn.xkc.model.vo.FrVo;
import com.tahoecn.xkc.model.vo.UnitVo;
import com.tahoecn.xkc.service.project.IBProjectService;
import com.tahoecn.xkc.service.project.IBRoomService;
import com.tahoecn.xkc.service.project.IVProjectbuildingService;
import com.tahoecn.xkc.service.project.IVProjectroomService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
@Api(tags = "APP-项目房源", value = "APP-项目")
@RequestMapping("/app/project")
public class ProjectAppController extends TahoeBaseController {

	@Autowired 
	private IVProjectbuildingService iVProjectbuildingService;
	@Autowired 
	private IVProjectroomService iVProjectroomService;
	@Autowired 
	private IBRoomService iBRoomService;
	@Autowired
    private IBProjectService projectService;
	
	@ResponseBody
    @ApiOperation(value = "房源列表GW", notes = "房源列表GW")
    @RequestMapping(value = "/mProjectHouseList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mProjectHouseList_Select(@RequestBody JSONObject jsonParam) {
    	try{
            Map paramMap = (HashMap)jsonParam.get("_param");
            String BuildingID = (String) paramMap.get("BuildingID");
            String ProjectID = (String) paramMap.get("ProjectID");
            String UserID = (String) paramMap.get("UserID");
            List<Map<String,Object>> projectObject = null;
            if (StringUtils.isEmpty(BuildingID)){
                projectObject = iVProjectbuildingService.BuildingDetailByProjectIDTop_Select(paramMap);//楼栋信息查询
                if (projectObject != null && projectObject.size() > 0){
                	paramMap.put("BuildingID", projectObject.get(0).get("BuildingID"));
                }
            }else{
                projectObject = iVProjectbuildingService.BuildingDetail_Select(paramMap);//楼栋信息查询
            }
            List<Map<String,Object>> roomArray = iVProjectroomService.RoomList_Select(paramMap);//获取项目房间列表信息
            Map<String,Object> project = new LinkedHashMap<String,Object>();
            Map<String,Object> floor = new LinkedHashMap<String,Object>();
            Map<String,UnitVo> unit = new LinkedHashMap<String,UnitVo>();
            //根据项目id查询是否需要隐藏金额
            QueryWrapper<BProject> wrapper = new QueryWrapper<BProject>();
            wrapper.eq("ID", ProjectID);
            wrapper.eq("Status","1");
            wrapper.eq("IsDel","0");
            BProject isHide = projectService.getOne(wrapper);
            for (Map<String,Object> item : roomArray){
                String RoomFloorName = (String) item.get("RoomFloorName");
                String RoomUnit = (String) item.get("RoomUnit");
                RoomUnit = StringUtils.isEmpty(RoomUnit) ? "-" : RoomUnit;
                //单元处理
                if (unit.get(RoomUnit) == null){
                	UnitVo ut = new UnitVo();
                    ut.setRoomUnit(item.get("RoomUnit").toString());
                    ut.setRoomMaxCount(0);
                    ut.setRoomFloorList(new LinkedList<FrVo>());
                    ut.setRoomFloorObj(new LinkedHashMap<String,FrVo>());
                    unit.put(RoomUnit, ut);
                }
                //房间处理
                Map<String,Object> room = new HashMap<String,Object>();
                room.put("FloorNum", item.get("FloorNum"));
                room.put("RoomID", item.get("RoomID"));
                room.put("RoomName", item.get("RoomName"));
                room.put("RoomArea", item.get("RoomArea"));
                
                //xkc修改---根据PC端配置
                //b_project 中根据projectid查询(HouseList隐藏房源列表价格0:隐藏，1:显示)
                if(isHide.getHouseList() != null && isHide.getHouseList() == 0){
                	room.put("RoomPrice", "****/㎡");
                	room.put("RoomTotal", "****");
                }else{
                	room.put("RoomPrice", item.get("RoomPrice"));
                	room.put("RoomTotal", item.get("RoomTotal"));
                	if(item.get("RoomPrice") == null || "".equals(item.get("RoomPrice"))){
                		room.put("RoomPrice", "--/㎡");
                	}
                	if(item.get("RoomTotal") == null || "".equals(item.get("RoomTotal"))){
                		room.put("RoomTotal", "--");
                	}
                }
//                if (ProjectID.toUpperCase().equals("252B3699-51B2-E711-80C7-00505686C900") 
//                		&& UserID.toUpperCase().equals("06C66C64-B490-4A44-B928-98009CD671F4")){
//                    room.put("RoomPrice", "***㎡");
//                    room.put("RoomTotal", "****");
//                }else{
//                		room.put("RoomPrice", item.get("RoomPrice"));
//                		room.put("RoomTotal", item.get("RoomTotal"));
//                	}
                room.put("RoomType", item.get("RoomType"));
                room.put("RoomFloorName", item.get("RoomFloorName"));
                room.put("RoomStatus", item.get("RoomStatus"));
                room.put("RoomSaleStatus", item.get("RoomSaleStatus"));
                //单元楼层处理
                if (unit.get(RoomUnit).getRoomFloorObj().get(RoomFloorName) == null){
                    List<Map<String,Object>> roomlist = new ArrayList<Map<String,Object>>();
                    roomlist.add(room);
                    FrVo fr = new FrVo();
                    fr.setRoomFloorName(item.get("RoomFloorName").toString());
                    fr.setRoomList(roomlist);
                    unit.get(RoomUnit).getRoomFloorObj().put(RoomFloorName, fr);
                }else{
                	unit.get(RoomUnit).getRoomFloorObj().get(RoomFloorName).getRoomList().add(room);
                }
                //楼层统一列表处理
                if (floor.get(RoomFloorName) == null){
                    floor.put(RoomFloorName, RoomFloorName);
                }

            }
//            List<> floorArray = new ArrayList();
			for(String key : unit.keySet()){
				UnitVo value = unit.get(key);
			    
                String RoomUnit = value.getRoomUnit();
                RoomUnit = StringUtils.isEmpty(RoomUnit) ? "-" : RoomUnit;
                int RoomMaxCount = unit.get(RoomUnit).getRoomMaxCount();
                for(String RoomKey : value.getRoomFloorObj().keySet()){
                	FrVo RoomFloorObj = value.getRoomFloorObj().get(RoomKey);
                    int currRoomCount = RoomFloorObj.getRoomList().size();
                    if (currRoomCount > RoomMaxCount){
                    	unit.get(RoomUnit).setRoomMaxCount(currRoomCount);
                        RoomMaxCount = currRoomCount;
                    }
                }
            }
			List<UnitVo> unitArray = new LinkedList<UnitVo>();
            for(String key : unit.keySet()){
            	UnitVo item = unit.get(key);
                int RoomMaxCount = item.getRoomMaxCount();
                String RoomUnit = item.getRoomUnit();
                RoomUnit = StringUtils.isEmpty(RoomUnit) ? "-" : RoomUnit;
                for(String roomkey : floor.keySet()){
                	Object roomFloor = floor.get(roomkey);
                    String RoomFloorName = roomkey;
                    FrVo RoomFloor = item.getRoomFloorObj().get(RoomFloorName);
                    if (RoomFloor != null){
                        for (int i = RoomFloor.getRoomList().size(); i < RoomMaxCount; i++){
                            Map<String,Object> room = new HashMap<String,Object>();
                            room.put("FloorNum", "");
                            room.put("RoomID", "");
                            room.put("RoomName", "");
                            room.put("RoomArea", "");
                            room.put("RoomPrice", "");
                            room.put("RoomTotal", "");
                            room.put("RoomType", "");
                            room.put("RoomFloorName", "");
                            room.put("RoomStatus", "");
                            room.put("RoomSaleStatus", "");
                            RoomFloor.getRoomList().add(room);
                        }
                        item.getRoomFloorList().add(RoomFloor);
                    }else{
                    	List<Map<String,Object>> rl = new LinkedList<Map<String,Object>>();
                        for (int i = 0; i < RoomMaxCount; i++)
                        {
                        	Map<String,Object> room = new HashMap<String,Object>();
                            room.put("FloorNum", "");
                            room.put("RoomID", "");
                            room.put("RoomName", "");
                            room.put("RoomArea", "");
                            room.put("RoomPrice", "");
                            room.put("RoomTotal", "");
                            room.put("RoomType", "");
                            room.put("RoomFloorName", "");
                            room.put("RoomStatus", "");
                            room.put("RoomSaleStatus", "");
                            rl.add(room);
                        }
                        FrVo fr = new FrVo();
                        fr.setRoomFloorName(RoomFloorName);
                        fr.setRoomList(rl);
                        item.getRoomFloorList().add(fr);
                    }
                }
                item.setRoomMaxCount(0);
                item.setRoomFloorObj(null);
                unitArray.add(item);
            }
            if (projectObject != null && projectObject.size() > 0){
                project.put("ProjectID", projectObject.get(0).get("ProjectID"));
                project.put("ProjectName", projectObject.get(0).get("ProjectName"));
                project.put("BuildingID", projectObject.get(0).get("BuildingID"));
                project.put("BuildingName", projectObject.get(0).get("BuildingName"));
                project.put("BuildingUnSaleCount", projectObject.get(0).get("BuildingUnSaleCount"));
                project.put("BuildingSaleCount", projectObject.get(0).get("BuildingSaleCount"));
                project.put("BuildingUnSaleRate", projectObject.get(0).get("BuildingUnSaleRate"));
                project.put("BuildingSaleRate", projectObject.get(0).get("BuildingSaleRate"));
                project.put("RoomUnitList", unitArray);
            }
            return Result.ok(project);
    	}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
    }

	@ResponseBody
    @ApiOperation(value = "房源详情GW", notes = "房源详情GW")
    @RequestMapping(value = "/mProjectHouseDetail_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mProjectHouseDetail_Select(@RequestBody JSONObject jsonParam) {
    	try{
    		@SuppressWarnings("unchecked")
			Map<String, Object> paramMap = (HashMap<String, Object>)jsonParam.get("_param");
    		List<Map<String,Object>> re = iBRoomService.RoomDetail_Select(paramMap);
            String UserID = (String) paramMap.get("UserID");
            String ProjectID = (String) paramMap.get("ProjectID");
            //根据项目id查询是否需要隐藏金额
            QueryWrapper<BProject> wrapper = new QueryWrapper<BProject>();
            wrapper.eq("ID", ProjectID);
            wrapper.eq("Status","1");
            wrapper.eq("IsDel","0");
            BProject isHide = projectService.getOne(wrapper);
            if(re != null && re.size() > 0){
            	/*if (ProjectID.toUpperCase().equals("252B3699-51B2-E711-80C7-00505686C900") 
            			&& UserID.toUpperCase().equals("06C66C64-B490-4A44-B928-98009CD671F4")){
            		re.get(0).put("BldPrice","****元");
            		re.get(0).put("TnPrice","****元");
            		re.get(0).put("Total","****元"); 
            	}*/
            	//xkc修改---根据PC端配置
                //b_project 中根据projectid查询(HouseList隐藏房源列表价格 0:隐藏，1:显示)
                if(isHide.getHouseDetail() != null && isHide.getHouseDetail() == 0){
                	re.get(0).put("BldPrice","****元");
            		re.get(0).put("TnPrice","****元");
            		re.get(0).put("Total","****元");
                }else{
                	if(re.get(0).get("BldPrice") == null || "".equals(re.get(0).get("BldPrice"))){
                		re.get(0).put("BldPrice", "--元");
                	}
                	if(re.get(0).get("TnPrice") == null || "".equals(re.get(0).get("TnPrice"))){
                		re.get(0).put("TnPrice", "--元");
                	}
                	if(re.get(0).get("Total") == null || "".equals(re.get(0).get("Total"))){
                		re.get(0).put("Total", "--元");
                	}
                }
            	return Result.ok(re.get(0));
            }else{
            	return Result.ok("");
            }
    	}catch(Exception e){
    		e.printStackTrace();
    		return Result.errormsg(1, "系统异常，请联系管理员");
    	}
	}
	@ResponseBody
    @ApiOperation(value = "楼栋选择列表", notes = "楼栋选择列表")
    @RequestMapping(value = "/mProjectBuildingList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mProjectBuildingList_Select(@RequestBody JSONObject jsonParam) {
    	try{
    		@SuppressWarnings("unchecked")
			Map<String, Object> paramMap = (HashMap<String, Object>)jsonParam.get("_param");
            List<Map<String,Object>> buildArray = iVProjectbuildingService.BuildingList_Select(paramMap);
            Map<String,Object> project = new HashMap<String, Object>();
            for(Map<String,Object> item : buildArray){
                String ProjectID = (String) item.get("ProjectID");
                Map<String,Object> bd = new HashMap<String, Object>();
                bd.put("BuildingID", (String) item.get("BuildingID"));
                bd.put("BuildingName", (String) item.get("BuildingName"));
                bd.put("BuildingUnSaleCount", (int) item.get("BuildingUnSaleCount"));
                bd.put("BuildingSaleCount", (int) item.get("BuildingSaleCount"));
                if (project.get(ProjectID) == null)
                {
                    List<Map<String,Object>> bdl = new ArrayList<Map<String,Object>>();
                    bdl.add(bd);
                    Map<String,Object> pj = new HashMap<String,Object>();
                    pj.put("ProjectID", (String) item.get("ProjectID"));
                    pj.put("ProjectName", (String) item.get("ProjectName"));
                    pj.put("BuildingList", bdl);
                    project.put(ProjectID, pj);
                }else{
                	((List)((Map)project.get(ProjectID)).get("BuildingList")).add(bd);
                }
            }
            List<Object> projectArray = new ArrayList<Object>();
            for(Object item : project.keySet()){
                projectArray.add(project.get(item));
            }
            return Result.ok(projectArray);
    	}catch(Exception e){
    		e.printStackTrace();
    		return Result.errormsg(1, "系统异常，请联系管理员");
    	}
	}
	@ResponseBody
	@ApiOperation(value = "项目房源列表(营销经理)", notes = "项目房源列表(营销经理)")
	@RequestMapping(value = "/mProjectHouseListYXJL_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result mProjectHouseListYXJL_Select(@RequestBody JSONObject jsonParam) {
		try{
			@SuppressWarnings("unchecked")
			Map<String, Object> paramMap = (HashMap<String, Object>)jsonParam.get("_param");
            String BuildingID = (String) paramMap.get("BuildingID");
            List<Map<String,Object>> projectObject = null;
            if (StringUtils.isEmpty(BuildingID)){
                projectObject = iVProjectbuildingService.BuildingDetailByProjectIDTop_Select(paramMap);//楼栋信息查询
                if (projectObject != null && projectObject.size() > 0){
                	paramMap.put("BuildingID", projectObject.get(0).get("BuildingID"));
                }
            }else{
                projectObject = iVProjectbuildingService.BuildingDetail_Select(paramMap);//楼栋信息查询
            }
            List<Map<String,Object>> roomArray = iVProjectroomService.RoomListYXJL_Select(paramMap);
            Map<String,Object> project = new HashMap<String,Object>();
            Map<String,Object> floor = new HashMap<String,Object>();
            Map<String,UnitVo> unit = new HashMap<String,UnitVo>();
            for (Map<String,Object> item : roomArray){
            	String RoomFloorName = (String) item.get("RoomFloorName");
                String RoomUnit = (String) item.get("RoomUnit");
                RoomUnit = StringUtils.isEmpty(RoomUnit) ? "-" : RoomUnit;
                //单元处理
                if (unit.get(RoomUnit) == null){
                	UnitVo ut = new UnitVo();
                    ut.setRoomUnit(item.get("RoomUnit").toString());
                    ut.setRoomMaxCount(0);
                    ut.setRoomFloorList(new LinkedList<FrVo>());
                    ut.setRoomFloorObj(new LinkedHashMap<String,FrVo>());
                    unit.put(RoomUnit, ut);
                }
                //房间处理
                Map<String,Object> room = new HashMap<String,Object>();
                room.put("FloorNum", item.get("FloorNum"));
                room.put("RoomID", item.get("RoomID"));
                room.put("RoomName", item.get("RoomName"));
                room.put("RoomArea", item.get("RoomArea"));
                room.put("RoomPrice", item.get("RoomPrice"));
                room.put("RoomTotal", item.get("RoomTotal"));
                room.put("RoomType", item.get("RoomType"));
                room.put("RoomStatus", item.get("RoomStatus"));
                //单元楼层处理
                if (unit.get(RoomUnit).getRoomFloorObj().get(RoomFloorName) == null){
                    List<Map<String,Object>> roomlist = new ArrayList<Map<String,Object>>();
                    roomlist.add(room);
                    FrVo fr = new FrVo();
                    fr.setRoomFloorName(item.get("RoomFloorName").toString());
                    fr.setRoomList(roomlist);
                    unit.get(RoomUnit).getRoomFloorObj().put(RoomFloorName, fr);
                }else{
                	unit.get(RoomUnit).getRoomFloorObj().get(RoomFloorName).getRoomList().add(room);
                }
                //楼层统一列表处理
                if (floor.get(RoomFloorName) == null){
                    floor.put(RoomFloorName, RoomFloorName);
                }
            }
//            JArray floorArray = new JArray();
            for(String key : unit.keySet()){
				UnitVo value = unit.get(key);
				String RoomUnit = value.getRoomUnit();
				RoomUnit = StringUtils.isEmpty(RoomUnit) ? "-" : RoomUnit;
				int RoomMaxCount = unit.get(RoomUnit).getRoomMaxCount();
				for(String RoomKey : value.getRoomFloorObj().keySet()){
                	FrVo RoomFloorObj = value.getRoomFloorObj().get(RoomKey);
                	int currRoomCount = RoomFloorObj.getRoomList().size();
                    if (currRoomCount > RoomMaxCount){
                    	unit.get(RoomUnit).setRoomMaxCount(currRoomCount);
                        RoomMaxCount = currRoomCount;
                    }
                }
            }
            List<UnitVo> unitArray = new ArrayList<UnitVo>();
            for(String key : unit.keySet()){
            	UnitVo item = unit.get(key);
            	int RoomMaxCount = item.getRoomMaxCount();
                String RoomUnit = item.getRoomUnit();
                RoomUnit = StringUtils.isEmpty(RoomUnit) ? "-" : RoomUnit;
                for(String roomkey : floor.keySet()){
                    String RoomFloorName = roomkey;
                    FrVo RoomFloor = item.getRoomFloorObj().get(RoomFloorName);
                    if (RoomFloor != null){
                    	for (int i = RoomFloor.getRoomList().size(); i < RoomMaxCount; i++){
                            Map<String,Object> room = new HashMap<String,Object>();
                            room.put("FloorNum", "");
                            room.put("RoomID", "");
                            room.put("RoomName", "");
                            room.put("RoomArea", "");
                            room.put("RoomPrice", "");
                            room.put("RoomTotal", "");
                            room.put("RoomType", "");
                            room.put("RoomStatus", "");
                            RoomFloor.getRoomList().add(room);
                        }
                    	item.getRoomFloorList().add(RoomFloor);
                    }else{
                    	List<Map<String,Object>> rl = new ArrayList<Map<String,Object>>();
                        for (int i = 0; i < RoomMaxCount; i++)
                        {
                        	Map<String,Object> room = new HashMap<String,Object>();
                            room.put("FloorNum", "");
                            room.put("RoomID", "");
                            room.put("RoomName", "");
                            room.put("RoomArea", "");
                            room.put("RoomPrice", "");
                            room.put("RoomTotal", "");
                            room.put("RoomType", "");
                            room.put("RoomStatus", "");
                            rl.add(room);
                        }
                        FrVo fr = new FrVo();
                        fr.setRoomFloorName(RoomFloorName);
                        fr.setRoomList(rl);
                        item.getRoomFloorList().add(fr);
                    }
                }
//                ((JObject)item.Value).Remove("RoomMaxCount");
//                ((JObject)item.Value).Remove("RoomFloorObj");
                item.setRoomMaxCount(0);
                item.setRoomFloorObj(null);
                unitArray.add(item);
            }
            if (projectObject != null && projectObject.size() > 0){
                project.put("ProjectID", projectObject.get(0).get("ProjectID"));
                project.put("ProjectName", projectObject.get(0).get("ProjectName"));
                project.put("BuildingID", projectObject.get(0).get("BuildingID"));
                project.put("BuildingName", projectObject.get(0).get("BuildingName"));
                project.put("RoomUnitList", unitArray);
            }
            return Result.ok(project);
		}catch(Exception e){
			e.printStackTrace();
			return Result.errormsg(1, "系统异常，请联系管理员");
		}
	}
	@ResponseBody
    @ApiOperation(value = "项目房源详情(营销经理)", notes = "项目房源详情(营销经理)")
    @RequestMapping(value = "/mProjectHouseDetailYXJL_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mProjectHouseDetailYXJL_Select(@RequestBody JSONObject jsonParam) {
    	try{
    		@SuppressWarnings("unchecked")
			Map<String, Object> paramMap = (HashMap<String, Object>)jsonParam.get("_param");
    		String ProjectID = (String) paramMap.get("ProjectID");
    		List<Map<String,Object>> re = iBRoomService.RoomDetailYXJL_Select(paramMap);
          //根据项目id查询是否需要隐藏金额
            QueryWrapper<BProject> wrapper = new QueryWrapper<BProject>();
            wrapper.eq("ID", ProjectID);
            wrapper.eq("Status","1");
            wrapper.eq("IsDel","0");
            BProject isHide = projectService.getOne(wrapper);
            if(re != null && re.size() > 0){
            	//xkc修改---根据PC端配置
                //b_project 中根据projectid查询(HouseList隐藏房源列表价格0:隐藏，1:显示)
                if(isHide.getHouseDetail() != null && isHide.getHouseDetail() == 0){
                	re.get(0).put("BldPrice","****");
            		re.get(0).put("TnPrice","****");
            		re.get(0).put("Total","****");
                }else{
                	if(re.get(0).get("BldPrice") == null || "".equals(re.get(0).get("BldPrice"))){
                		re.get(0).put("BldPrice", "--");
                	}
                	if(re.get(0).get("TnPrice") == null || "".equals(re.get(0).get("TnPrice"))){
                		re.get(0).put("TnPrice", "--");
                	}
                	if(re.get(0).get("Total") == null || "".equals(re.get(0).get("Total"))){
                		re.get(0).put("Total", "--");
                	}
                }
            	return Result.ok(re.get(0));
            }else{
            	return Result.ok("");
            }
    	}catch(Exception e){
    		e.printStackTrace();
    		return Result.errormsg(1, "系统异常，请联系管理员");
    	}
	}

    @ApiOperation(value = "区域项目列表", notes = "区域项目列表")
    @RequestMapping(value = "/ProjectList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result ProjectList_Select(@RequestBody JSONObject jsonParam) {
    	System.out.println("==================>>>>>>"+jsonParam);
        Map<String, Object> paramMap = (HashMap<String, Object>)jsonParam.get("_param");
        Object Name = paramMap.get("Name");
        String UserID = (String) paramMap.get("UserID");
        List<Map<String,Object>> list;
        if (Name!=null){
            String key= (String) Name;
            list=projectService.ProjectList_Select(key,UserID);
        }else {
            list=projectService.ProjectList_Select(null,UserID);
        }
        return Result.ok(list);
    }
}
