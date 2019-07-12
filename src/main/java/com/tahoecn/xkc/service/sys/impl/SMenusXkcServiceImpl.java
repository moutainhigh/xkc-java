package com.tahoecn.xkc.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.common.utils.ThreadLocalUtils;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.mapper.sys.SMenusXkcMapper;
import com.tahoecn.xkc.model.dict.BTag;
import com.tahoecn.xkc.model.salegroup.BSalesgroup;
import com.tahoecn.xkc.model.sys.SCity;
import com.tahoecn.xkc.model.sys.SMenus;
import com.tahoecn.xkc.model.sys.SMenusXkc;
import com.tahoecn.xkc.service.customer.IBCustomerfiltergroupService;
import com.tahoecn.xkc.service.dict.IBTagService;
import com.tahoecn.xkc.service.dict.ISDictionaryService;
import com.tahoecn.xkc.service.project.IBProjectService;
import com.tahoecn.xkc.service.project.IVProjectroomService;
import com.tahoecn.xkc.service.salegroup.IBSalesgroupService;
import com.tahoecn.xkc.service.salegroup.IBSalesuserService;
import com.tahoecn.xkc.service.sys.ISCityService;
import com.tahoecn.xkc.service.sys.ISMenusXkcService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-12
 */
@Service
public class SMenusXkcServiceImpl extends ServiceImpl<SMenusXkcMapper, SMenusXkc> implements ISMenusXkcService {
    @Autowired
    private ISCityService cityService;

    @Autowired
    private IBTagService tagService;

    @Autowired
    private ISDictionaryService dictionaryService;

    @Autowired
    private IBProjectService projectService;

    @Autowired
    private IBCustomerfiltergroupService customerfiltergroupService;

    @Autowired
    private IBSalesgroupService salesgroupService;

    @Autowired
    private IBSalesuserService salesuserService;

    @Autowired
    private IVProjectroomService projectroomService;

    @Override
    public Result SystemDictionaryDetail(HashMap<String,Object> param) {
        try {


            String IDAlias = "ID";
            String ChildAlias = "Child";
            String DictNameAlias = "DictName";
            String TypeAlias = "Type";


            //此处的 Alias不懂
//        Map<String,String> Alias = (Map<String, String>) param.get("Alias");
//        if (Alias!=null){
//            IDAlias=Alias.get("IDAlias");
//            ChildAlias=Alias.get("ChildAlias");
//            DictNameAlias=Alias.get("DictNameAlias");
//        }
            boolean isCity = false;
            boolean isKHSXGZGW = false;
            boolean isFPGWSXGZFJ = false;
            boolean isFPGWSXGZYXJL = false;
            boolean isGGCKHSXGZYXJL = false;
            boolean isGWGJKHSXGZXSJL = false;
            boolean isGWGGKHSXGZXSJL = false;
            boolean isGWDSKHSXGZXSJL = false;
            boolean isPKSXGZXSJL = false;
            boolean isFJHX = false;
            boolean isKHSXGZZQ = false;
            boolean isKHGGCSXGZGW = false;
            boolean isKHGGCSXGZZQ = false;
            Map<String,Object> res=new HashMap<>();
            String DictCodes = "";
            //遍历参数
            for (String Code : param.keySet()) {
                switch (Code){
                    case "CITY":
                        isCity = true;
                        break;

                    default:
                        if ("KHSXGZGW".equals(Code))//客户筛选规则(顾问)
                        {
                            isKHSXGZGW = true;
                        }
                        if ("FPGWSXGZFJ".equals(Code))//分配顾问筛选规则(分接)
                        {
                            isFPGWSXGZFJ = true;
                        }
                        if ("FPGWSXGZYXJL".equals(Code))//分配顾问筛选规则(营销经理)
                        {
                            isFPGWSXGZYXJL = true;
                        }
                        if ("GGCKHSXGZYXJL".equals(Code))//公共池客户筛选规则(营销经理)
                        {
                            isGGCKHSXGZYXJL = true;
                        }
                        if ("GWGJKHSXGZXSJL".equals(Code))//顾问公共客户排序规则(销售经理)
                        {
                            isGWGJKHSXGZXSJL = true;
                        }
                        if ("GWGGKHSXGZXSJL".equals(Code))//顾问公共客户筛选规则(销售经理)
                        {
                            isGWGGKHSXGZXSJL = true;
                        }
                        if ("GWDSKHSXGZXSJL".equals(Code))//顾问丢失客户筛选规则(销售经理)
                        {
                            isGWDSKHSXGZXSJL = true;
                        }
                        if ("PKSXGZXSJL".equals(Code))//盘客筛选规则(销售经理)
                        {
                            isPKSXGZXSJL = true;
                        }
                        if ("FJHX".equals(Code))//房间户型
                        {
                            isFJHX = true;
                        }
                        if ("KHSXGZZQ".equals(Code))//客户筛选规则(自渠)
                        {
                            isKHSXGZZQ = true;
                        }
                        if ("KHGGCSXGZGW".equals(Code))//客户公共池筛选规则(顾问)
                        {
                            isKHGGCSXGZGW = true;
                        }
                        if ("KHGGCSXGZZQ".equals(Code))//客户公共池筛选规则(自渠)
                        {
                            isKHGGCSXGZZQ = true;
                        }
                        if (res.get("Code")== null)
                        {
                            DictCodes = " OR B.DictCode = '"+Code+"'";
//                        res.Add(new JProperty(Code, new JArray())); 这句不知道啥
                            res.put(Code,new ArrayList<>());
                        }
                        break;
                }
            }
            //region 城市处理
            if (isCity){
                QueryWrapper<SCity> wrapper=new QueryWrapper<>();
                wrapper.eq("Status",1).le("Levels",4).orderByAsc("Levels","StandardIndex");
                List<SCity> list= new ArrayList<>();
                try {
                    list = cityService.list(wrapper);
                } catch (Exception e) {
                    e.printStackTrace();
                    return Result.errormsg(99,"数据库查询异常");
                }
                Map<String,Map<String,Object>> province = new HashMap<>();
                Map<String,Map<String,Object>> city = new HashMap<>();
                for (SCity sCity : list) {
                    String PID=sCity.getPid();
                    int Levels=sCity.getLevels();
                    if (Levels==2){
                        String ID=sCity.getId();
                        Map<String,Object> dict = new HashMap<>();
                        dict.put("IDAlias",ID);
                        dict.put("DictNameAlias",sCity.getDispName());
                        dict.put("ChildAlias",new ArrayList<>());
                        province.put(ID,dict);
                    }
                    if (Levels==3){
                        if (province.get("PID") != null)
                        {
                            String ID=sCity.getId();
                            Map<String,Object> dict = new HashMap<>();
                            dict.put("IDAlias",ID);
                            dict.put("DictNameAlias",sCity.getDispName());
                            dict.put("ChildAlias","");
                            dict.put("PID",PID);
                            city.put(ID,dict);
                        }
                    }
                    if (Levels == 4)
                    {
                        if (city.get("PID")!= null)
                        {
                            String ID=sCity.getId();
                            Map<String,Object> dict = new HashMap<>();
                            dict.put("IDAlias",ID);
                            dict.put("DictNameAlias",sCity.getDispName());
                            Map<String,Object> pid = city.get("PID");
                            ArrayList  childAlias = (ArrayList) pid.get("ChildAlias");
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
                            ArrayList  arrayList= (ArrayList) province.get("PID").get("ChildAlias");
                            arrayList.add(city.get(key));
                        }
                    }
                    for (String key : province.keySet()) {
                        resJa.add(province.get(key));
                    }
                    res.put("City",resJa);
                }
            }
//        通用数据字典处理
            List<Map<String,Object>> dictArray=new ArrayList<>();

            try {
                dictArray=dictionaryService.ListByCode_Select(DictCodes);
            } catch (Exception e) {
                e.printStackTrace();
                return Result.errormsg(99,"数据库查询异常");
            }
            //是否开启分接
            int i=projectService.ProjectIsNoAllot_Select((String)param.get("ProjectID"));
            boolean IsNoAllotRole= i==0;
            Map<String,Object> one = new HashMap<>();
            for (Map<String, Object> map : dictArray) {
                String PDictCode = (String) map.get("PDictCode");
                int Levels = (int) map.get("Levels");
                if (Levels == 2)
                {
                    if (StringUtils.equals("KHGJFS",PDictCode)&& (StringUtils.equals("ZQ",(String)param.get("JobCode")) ||StringUtils.equals("ZQJL",(String)param.get("JobCode"))||StringUtils.equals("ZQFZR",(String)param.get("JobCode")) ||StringUtils.equals("JZ",(String)param.get("JobCode")) && StringUtils.equals("E30825AA-B894-4A5F-AF55-24CAC34C8F1F",(String)map.get("ID"))))
                    {//排除自渠和自渠经理的售场接待 跟进方式
                    }
                    else if (IsNoAllotRole && StringUtils.equals("KHGJFS",PDictCode) && StringUtils.equals("GW",(String)param.get("JobCode")) && StringUtils.equals("E30825AA-B894-4A5F-AF55-24CAC34C8F1F",(String)map.get("ID")))
                    {//如果开启了分接 排除置业顾问的售场接待跟进方式

                    }
                    else if (StringUtils.equals("KHSXGZZQ",PDictCode) && StringUtils.equals("JZ",(String)param.get("JobCode"))  && (StringUtils.equals("9526CC1F-47CE-4479-8EB8-AFE73ADAF1F4",(String)map.get("ID")) || StringUtils.equals("885AC55E-7FC5-467A-9809-82AC9867363D",(String)map.get("ID")) ))
                    {//兼职客户排除“我关注的”和“标签”
                    }
                    else
                    {
                        String DictCode = (String) map.get("DictCode");
                        Map<String,Object> dict = new HashMap<>();
                        dict.put(IDAlias,map.get("ID"));
                        dict.put(DictNameAlias,map.get("DictName"));
                        //dict.Add(new JProperty(TypeAlias, jo["Ext4"]==null?"":jo["Ext4"].ToString()));
                        dict.put(TypeAlias,map.get("Ext4"));
                        dict.put(ChildAlias,new ArrayList<>());
                        one.put(DictCode,dict);
                    }
                }
                if (Levels == 3)
                {
                    if (one.get("PDictCode") != null)
                    {
                        Map<String,Object> dict = new HashMap<>();
                        dict.put(IDAlias,map.get("ID"));
                        dict.put(DictNameAlias,map.get("DictName"));
                        Map<String,Object> pDictCode = (Map<String, Object>) one.get("PDictCode");
                        ArrayList childAlias = (ArrayList) pDictCode.get("ChildAlias");
                        childAlias.add(dict);
                    }
                }
            }
            for (Map<String, Object> map : dictArray) {
                String PDictCode = (String) map.get("PDictCode");
                int Levels = (int) map.get("Levels");
                if (Levels == 2)
                {
                    String DictCode = (String) map.get("DictCode");
                    if (res.get("PDictCode")!= null && one.get("DictCode") != null)
                    {
                        ArrayList pDictCode = (ArrayList) res.get("PDictCode");
                        pDictCode.add(one.get("DictCode"));
                    }
                }
            }

//      处理客户筛选规则(顾问)数据
            if (isKHSXGZGW)
            {
                String userID = (String) param.get("UserID");
                QueryWrapper<BTag> wrapper=new QueryWrapper<>();
                wrapper.eq("IsDel",0).eq("Status",1).eq("Creator",userID);
                wrapper.orderByAsc("Name");
                List<BTag> list;
                try {
                    list = tagService.list(wrapper);
                } catch (Exception e) {
                    e.printStackTrace();
                    return Result.errormsg(101,"数据库查询异常");
                }
                ArrayList arrayList = (ArrayList) res.get("KHSXGZGW");
                HashMap<String,Object> hashMap = (HashMap<String, Object>) arrayList.get(3);
                hashMap.put("Child",list);
                arrayList.add(3,hashMap);
                res.put("KHSXGZGW",arrayList);
                List<Map<String,Object>> arr=customerfiltergroupService.groupList((String) param.get("JobCode"),(String)param.get("ProjectID"),(String)param.get("UserID"));
                List groupArr=new ArrayList();
                for (int j = 0; j < arr.size(); j++)
                {
                    HashMap<String,Object> dat = new HashMap<>();
                    dat.put("ID",arr.get(j).get("ID"));
                    dat.put("DictName",arr.get(j).get("DictName"));
                    dat.put("Type","group");
                    dat.put("Filter",arr.get(i).get("Filter"));
                    List<Map<String,Object>> dct= (List) arr.get(i).get("FilterDesc");
                    List FilterDesc=new ArrayList();
                    for (Map<String, Object> map : dct) {
                        for (String key : map.keySet()) {
                            Map<String, Object> filterobj=new HashMap<>();
                            filterobj.put("DictName",key);
                            filterobj.put("ID",map.get(key));
                            filterobj.put("Type","group");
                            FilterDesc.add(filterobj);
                        }
                    }
                    dat.put("FilterDesc",FilterDesc);
                    groupArr.add(dat);
                }
                List<Map<String,Object>> khsxgzgw = (List<Map<String, Object>>) res.get("KHSXGZGW");
                Map<String, Object> map = khsxgzgw.get(4);
                map.put("Child",groupArr);
                khsxgzgw.add(4,map);
                res.put("KHSXGZGW",khsxgzgw);
            }
//        处理分配顾问筛选规则(分接)数据
            if (isFPGWSXGZFJ){
                Map<String,Object> dictTop=new HashMap<>();
                dictTop.put(IDAlias,"");
                dictTop.put(DictNameAlias,"全部");
                dictTop.put(ChildAlias,new ArrayList<>());
                res.put("FPGWSXGZFJ",dictTop);
                QueryWrapper<BSalesgroup> wrapper=new QueryWrapper();
                wrapper.eq("IsDel",0).eq("Status",1).eq("ProjectID",param.get("ProjectID"));
                wrapper.gt("Nature",0);
                List<BSalesgroup> groupArray = salesgroupService.list(wrapper);
                for (BSalesgroup bSalesgroup : groupArray) {
                    Map<String,Object> dict = new HashMap<>();
                    dict.put(IDAlias,bSalesgroup.getId());
                    dict.put(DictNameAlias,bSalesgroup.getName());
                    dict.put(ChildAlias,new ArrayList<>());
                    res.put("FPGWSXGZFJ",dict);
                }
            }
//        处理分配顾问筛选规则(营销经理)数据
            if (isFPGWSXGZYXJL)
            {
                Map<String,Object> dictTop=new HashMap<>();
                dictTop.put(IDAlias,"");
                dictTop.put(DictNameAlias,"全部");
                dictTop.put(ChildAlias,new ArrayList<>());
                res.put("FPGWSXGZYXJL",dictTop);
                if ("YXJL".equals((String) param.get("JobCode"))){
                    QueryWrapper<BSalesgroup> wrapper=new QueryWrapper();
                    wrapper.eq("IsDel",0).eq("Status",1).eq("ProjectID",param.get("ProjectID"));
                    wrapper.gt("Nature",0);
                    List<BSalesgroup> groupArray = salesgroupService.list(wrapper);
                    for (BSalesgroup bSalesgroup : groupArray) {
                        Map<String,Object> dict = new HashMap<>();
                        dict.put(IDAlias,bSalesgroup.getId());
                        dict.put(DictNameAlias,bSalesgroup.getName());
                        dict.put(ChildAlias,new ArrayList<>());
                        res.put("FPGWSXGZYXJL",dict);
                    }
                }
                if ("XSJL".equals(param.get("JobCode"))){
                    String ProjectID= (String) param.get("ProjectID");
                    String TeamID= (String) param.get("OrgID");
                    List<Map<String,Object>> saleArray=null;
                    if (!StringUtils.isAllBlank(ProjectID,TeamID)){
                        saleArray=salesuserService.UserSalesList_Select(ProjectID,TeamID);
                        for (Map<String, Object> map : saleArray) {
                            Map<String,Object> dict = new HashMap<>();
                            dict.put(IDAlias,map.get("ID"));
                            dict.put(DictNameAlias,map.get("Name"));
                            res.put("FPGWSXGZYXJL",dict);
                        }
                    }

                }

            }
//            处理公共池客户筛选规则(营销经理)数据
            if (isGGCKHSXGZYXJL)
            {
                Map<String,Object> dictTop=new HashMap<>();
                dictTop.put(IDAlias,"");
                dictTop.put(DictNameAlias,"全部");
                dictTop.put(ChildAlias,new ArrayList<>());
                res.put("GGCKHSXGZYXJL",dictTop);
                if ("YXJL".equals(param.get("JobCode"))){
                    QueryWrapper<BSalesgroup> wrapper=new QueryWrapper();
                    wrapper.eq("IsDel",0).eq("Status",1).eq("ProjectID",param.get("ProjectID"));
                    wrapper.gt("Nature",0);
                    List<BSalesgroup> groupArray = salesgroupService.list(wrapper);
                    for (BSalesgroup bSalesgroup : groupArray) {
                        Map<String,Object> dict = new HashMap<>();
                        dict.put(IDAlias,bSalesgroup.getId());
                        dict.put(DictNameAlias,bSalesgroup.getName());
                        dict.put(ChildAlias,new ArrayList<>());
                        res.put("GGCKHSXGZYXJL",dict);
                    }
                }
                if ("XSJL".equals(param.get("JobCode"))){
                    String ProjectID= null;
                    String TeamID= (String) param.get("OrgID");
                    List<Map<String,Object>> saleArray=null;
                    if (!StringUtils.isAllBlank(ProjectID,TeamID)){
                        saleArray=salesuserService.UserSalesList_Select(ProjectID,TeamID);
                        for (Map<String, Object> map : saleArray) {
                            Map<String,Object> dict = new HashMap<>();
                            dict.put(IDAlias,map.get("ID"));
                            dict.put(DictNameAlias,map.get("Name"));
                            res.put("GGCKHSXGZYXJL",dict);
                        }
                    }
                }
            }
//        处理顾问跟进客户筛选规则(销售经理)数据
            if (isGWGJKHSXGZXSJL)
            {
                String TeamID=null;
                if (!StringUtils.equals((String)param.get("OrgID"),(String)param.get("ProjectID"))){
                    TeamID= (String) param.get("OrgID");
                }
                List<Map<String,Object>> saleArray=null;
                if (StringUtils.isNotBlank(TeamID)){
                    saleArray=salesuserService.UserSalesList_Select(null,TeamID);
                    for (Map<String, Object> map : saleArray) {
                        Map<String,Object> dict = new HashMap<>();
                        dict.put(IDAlias,map.get("ID"));
                        dict.put(DictNameAlias,map.get("Name"));
                        List<Map<String,Object>> gwgjkhsxgzxsjl = (List<Map<String, Object>>) res.get("GWGJKHSXGZXSJL");
                        Map<String, Object> objectMap = gwgjkhsxgzxsjl.get(2);
                        objectMap.put("Child",dict);
                        gwgjkhsxgzxsjl.add(2,objectMap);
                    }
                }

            }
//        处理顾问公共客户筛选规则(销售经理)数据
            if (isGWGGKHSXGZXSJL)
            {
                String TeamID=null;
                if (!StringUtils.equals((String)param.get("OrgID"),(String)param.get("ProjectID"))){
                    TeamID= (String) param.get("OrgID");
                    List<Map<String,Object>> saleArray=salesuserService.UserSalesList_Select(null,TeamID);
                    for (Map<String, Object> map : saleArray) {
                        Map<String,Object> dict = new HashMap<>();
                        dict.put(IDAlias,map.get("ID"));
                        dict.put(DictNameAlias,map.get("Name"));
                        List<Map<String,Object>> gwgjkhsxgzxsjl = (List<Map<String, Object>>) res.get("GWGJKHSXGZXSJL");
                        Map<String, Object> objectMap = gwgjkhsxgzxsjl.get(2);
                        objectMap.put("Child",dict);
                        gwgjkhsxgzxsjl.add(2,objectMap);
                    }
                }
            }
//        处理顾问公共客户筛选规则(销售经理)数据
            if (isGWGGKHSXGZXSJL)
            {
                String TeamID=null;
                if (!StringUtils.equals((String)param.get("OrgID"),(String)param.get("ProjectID"))){
                    TeamID= (String) param.get("OrgID");
                    List<Map<String,Object>> saleArray=salesuserService.UserSalesList_Select(null,TeamID);
                    for (Map<String, Object> map : saleArray) {
                        Map<String,Object> dict = new HashMap<>();
                        dict.put(IDAlias,map.get("ID"));
                        dict.put(DictNameAlias,map.get("Name"));
                        List<Map<String,Object>> gwgjkhsxgzxsjl = (List<Map<String, Object>>) res.get("GWGGKHSXGZXSJL");
                        Map<String, Object> objectMap = gwgjkhsxgzxsjl.get(0);
                        objectMap.put("Child",dict);
                        gwgjkhsxgzxsjl.add(0,objectMap);
                    }
                }
            }
//        处理顾问丢失客户筛选规则(销售经理)数据
            if (isGWDSKHSXGZXSJL)
            {
                String TeamID=null;
                if (!StringUtils.equals((String)param.get("OrgID"),(String)param.get("ProjectID"))){
                    TeamID= (String) param.get("OrgID");
                    List<Map<String,Object>> saleArray=salesuserService.UserSalesList_Select(null,TeamID);
                    for (Map<String, Object> map : saleArray) {
                        Map<String,Object> dict = new HashMap<>();
                        dict.put(IDAlias,map.get("ID"));
                        dict.put(DictNameAlias,map.get("Name"));
                        List<Map<String,Object>> gwgjkhsxgzxsjl = (List<Map<String, Object>>) res.get("GWDSKHSXGZXSJL");
                        Map<String, Object> objectMap = gwgjkhsxgzxsjl.get(0);
                        objectMap.put("Child",dict);
                        gwgjkhsxgzxsjl.add(0,objectMap);
                    }
                }
            }
//        处理盘客筛选规则(销售经理) 数据
            if (isPKSXGZXSJL)
            {
                Map<String,Object> dictTop=new HashMap<>();
                dictTop.put(IDAlias,"");
                dictTop.put(DictNameAlias,"全部");
                dictTop.put(ChildAlias,new ArrayList<>());
//            ((JArray)res["PKSXGZXSJL"]).AddFirst(dictTop);  AddFirst插入到list第一位置??
                res.put("PKSXGZXSJL",dictTop);
            }
//        处理房间户型数据
            if (isFJHX)
            {
                List<Map<String,Object>> list=projectroomService.RoomTypeList_Select((String)param.get("BuildingID"));
                for (Map<String, Object> map : list) {
                    Map<String,Object> dict = new HashMap<>();
                    dict.put(IDAlias,map.get("RoomType"));
                    dict.put(DictNameAlias,map.get("RoomType"));
                    dict.put(ChildAlias,new ArrayList<>());
                    res.put("FJHX",dict);
                }
            }
//        处理客户筛选规则(自渠)数据
            if (isKHSXGZZQ)
            {
                String userID = (String) param.get("UserID");
                QueryWrapper<BTag> wrapper=new QueryWrapper<>();
                wrapper.eq("IsDel",0).eq("Status",1).eq("Creator",userID);
                wrapper.orderByAsc("Name");
                List<BTag> list= tagService.list(wrapper);

                if ("JZ".equals(param.get("JobCode"))){
                    List<Map<String,Object>> khsxgzgw = (List<Map<String, Object>>) res.get("KHSXGZZQ");
                    Map<String, Object> map = khsxgzgw.get(3);
                    map.put("Child",list);
                    khsxgzgw.add(3,map);
                    res.put("KHSXGZZQ",khsxgzgw);
                }
//            客户分组集合查询
                List<Map<String,Object>> arr=customerfiltergroupService.groupList((String) param.get("JobCode"),(String)param.get("ProjectID"),(String)param.get("UserID"));
                List groupArr=new ArrayList();
                for (int j = 0; j < arr.size(); j++)
                {
                    HashMap<String,Object> dat = new HashMap<>();
                    dat.put("ID",arr.get(j).get("ID"));
                    dat.put("DictName",arr.get(j).get("DictName"));
                    dat.put("Type","group");
                    dat.put("Filter",arr.get(i).get("Filter"));
                    List<Map<String,Object>> dct= (List) arr.get(i).get("FilterDesc");
                    List FilterDesc=new ArrayList();
                    for (Map<String, Object> map : dct) {
                        for (String key : map.keySet()) {
                            Map<String, Object> filterobj=new HashMap<>();
                            filterobj.put("DictName",key);
                            filterobj.put("ID",map.get(key));
                            filterobj.put("Type","group");
                            FilterDesc.add(filterobj);
                        }
                    }
                    dat.put("FilterDesc",FilterDesc);
                    groupArr.add(dat);
                }
                if ("JZ".equals(param.get("JobCode")))
                {
                    List<Map<String,Object>> khsxgzgw = (List<Map<String, Object>>) res.get("KHSXGZZQ");
                    Map<String, Object> map = khsxgzgw.get(4);
                    map.put("Child",list);
                    khsxgzgw.add(4,map);
                    res.put("KHSXGZZQ",khsxgzgw);
                }else {
                    List<Map<String,Object>> khsxgzgw = (List<Map<String, Object>>) res.get("KHSXGZZQ");
                    Map<String, Object> map = khsxgzgw.get(2);
                    map.put("Child",list);
                    khsxgzgw.add(2,map);
                    res.put("KHSXGZZQ",khsxgzgw);
                }
            }
//        处理客户公共池筛选规则(顾问)
            if (isKHGGCSXGZGW) {
//            客户分组集合查询
                List<Map<String, Object>> arr = customerfiltergroupService.groupList((String) param.get("JobCode"), (String) param.get("ProjectID"), (String) param.get("UserID"));
                List groupArr = new ArrayList();
                for (int j = 0; j < arr.size(); j++) {
                    HashMap<String, Object> dat = new HashMap<>();
                    dat.put("ID", arr.get(j).get("ID"));
                    dat.put("DictName", arr.get(j).get("DictName"));
                    dat.put("Type", "group");
                    dat.put("Filter", arr.get(i).get("Filter"));
                    List<Map<String, Object>> dct = (List) arr.get(i).get("FilterDesc");
                    List FilterDesc = new ArrayList();
                    for (Map<String, Object> map : dct) {
                        for (String key : map.keySet()) {
                            Map<String, Object> filterobj = new HashMap<>();
                            filterobj.put("DictName", key);
                            filterobj.put("ID", map.get(key));
                            filterobj.put("Type", "group");
                            FilterDesc.add(filterobj);
                        }
                    }
                    dat.put("FilterDesc", FilterDesc);
                    groupArr.add(dat);
                }
                List<Map<String,Object>> khsxgzgw = (List<Map<String, Object>>) res.get("KHGGCSXGZGW");
                Map<String, Object> map = khsxgzgw.get(2);
                map.put("Child",groupArr);
                khsxgzgw.add(2,map);
                res.put("KHSXGZZQ",khsxgzgw);
            }
//        处理客户公共池筛选规则(自渠)
            if (isKHGGCSXGZZQ)
            {
//            客户分组集合查询
                List<Map<String, Object>> arr = customerfiltergroupService.groupList((String) param.get("JobCode"), (String) param.get("ProjectID"), (String) param.get("UserID"));
                List groupArr = new ArrayList();
                for (int j = 0; j < arr.size(); j++) {
                    HashMap<String, Object> dat = new HashMap<>();
                    dat.put("ID", arr.get(j).get("ID"));
                    dat.put("DictName", arr.get(j).get("DictName"));
                    dat.put("Type", "group");
                    dat.put("Filter", arr.get(i).get("Filter"));
                    List<Map<String, Object>> dct = (List) arr.get(i).get("FilterDesc");
                    List FilterDesc = new ArrayList();
                    for (Map<String, Object> map : dct) {
                        for (String key : map.keySet()) {
                            Map<String, Object> filterobj = new HashMap<>();
                            filterobj.put("DictName", key);
                            filterobj.put("ID", map.get(key));
                            filterobj.put("Type", "group");
                            FilterDesc.add(filterobj);
                        }
                    }
                    dat.put("FilterDesc", FilterDesc);
                    groupArr.add(dat);
                }
                List<Map<String,Object>> khsxgzgw = (List<Map<String, Object>>) res.get("KHGGCSXGZZQ");
                Map<String, Object> map = khsxgzgw.get(2);
                map.put("Child",groupArr);
                khsxgzgw.add(2,map);
                res.put("KHGGCSXGZZQ",khsxgzgw);
            }
            List<Map<String, Object>> data=new ArrayList<>();
            if (res.size() >= 1)
            {
                for (String key : res.keySet()) {
                    data.add((Map<String, Object>) res.get(key));
                }
            }
            return Result.ok(data);
        }catch (Exception e){
            e.printStackTrace();
            return Result.errormsg(99,"数据字典错误");
        }
    }

    @Override
    public List<Map<String,Object>> SystemMenusList_Select() {
        return baseMapper.SystemMenusList_Select();
    }

    @Override
    public void SystemMenu_Insert(SMenus menus) {
        baseMapper.SystemMenu_Insert(menus);
    }

    @Override
    public boolean SystemMenu_Update(SMenus menu) {
        try {
            String ID = menu.getId();
            String MenuSysName = menu.getMenuSysName();
            String MenuName = menu.getMenuName();
            String Url = menu.getUrl();
            String ImageUrl = menu.getImageUrl();
            String IconClass = menu.getIconClass();
            int IsHomePage = menu.getIsHomePage();
            int IsShow = menu.getIsShow();
            int Levels = menu.getLevels();
            int ListIndex = menu.getListIndex();
            int IsLast = menu.getIsLast();
            String Editor = ThreadLocalUtils.getUserName();
            int Status = menu.getStatus();
            String OldPath=baseMapper.getOldPath(ID);
            String NewPath=baseMapper.getNewPath(ID);
            NewPath=NewPath+"/"+MenuSysName;
            SMenusXkc menus=new SMenusXkc();
            menus.setId(ID);
            menus.setMenuSysName(MenuSysName);
            menus.setMenuName(MenuName);
            menus.setUrl(Url);
            menus.setImageUrl(ImageUrl);
            menus.setIconClass(IconClass);
            menus.setIsHomePage(IsHomePage);
            menus.setIsShow(IsShow);
            menus.setLevels(Levels);
            menus.setListIndex(ListIndex);
            menus.setIsLast(IsLast);
            menus.setEditor(Editor);
            menus.setEditTime(new Date());
            menus.setStatus(Status);
            menus.setFullPath(NewPath);
            baseMapper.updateById(menus);
            if (StringUtils.isNotBlank(OldPath)){
                QueryWrapper<SMenusXkc> wrapper=new QueryWrapper<>();
                wrapper.likeRight("FullPath",OldPath);
                List<SMenusXkc> list = baseMapper.selectList(wrapper);
                for (SMenusXkc sMenus : list) {
                    String fullPath = sMenus.getFullPath();
                    String replace = fullPath.replace(OldPath, NewPath);
                    sMenus.setFullPath(replace);
                    baseMapper.updateById(sMenus);
                }
            }
            if (IsLast==1){
                QueryWrapper<SMenusXkc> wrapper=new QueryWrapper<>();
                wrapper.likeRight("FullPath",NewPath);
                wrapper.eq("ID",ID);
                List<SMenusXkc> list = baseMapper.selectList(wrapper);
                for (SMenusXkc sMenus : list) {
                    sMenus.setIsLast(0);
                    baseMapper.updateById(sMenus);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }

    @Override
    public List<Map<String, Object>> SystemCommonJobAuth_Select(String userID, String authCompanyID, String productID, String jobID) {




        return null;
    }
}
