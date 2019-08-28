package com.tahoecn.xkc.service.dict.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.common.utils.ThreadLocalUtils;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.mapper.customer.VCustomergwlistSelectMapper;
import com.tahoecn.xkc.mapper.dict.SDictionaryMapper;
import com.tahoecn.xkc.model.dict.BTag;
import com.tahoecn.xkc.model.dict.SDictionary;
import com.tahoecn.xkc.model.salegroup.BSalesgroup;
import com.tahoecn.xkc.model.sys.SCity;
import com.tahoecn.xkc.service.customer.IBCustomerfiltergroupService;
import com.tahoecn.xkc.service.dict.IBTagService;
import com.tahoecn.xkc.service.dict.ISDictionaryService;
import com.tahoecn.xkc.service.project.IBProjectService;
import com.tahoecn.xkc.service.project.IVProjectroomService;
import com.tahoecn.xkc.service.salegroup.IBSalesgroupService;
import com.tahoecn.xkc.service.salegroup.IBSalesuserService;
import com.tahoecn.xkc.service.sys.ISCityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-06-27
 */
@Service
public class SDictionaryServiceImpl extends ServiceImpl<SDictionaryMapper, SDictionary> implements ISDictionaryService {
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
    private VCustomergwlistSelectMapper vCustomergwlistSelectMapper;

    @Autowired
    private IVProjectroomService projectroomService;


    @Override
    public List<Map<String, String>> AgenCertificatesList_SelectN() {
        return baseMapper.AgenCertificatesList_SelectN();
    }

    @Override
    public List<Map<String,Object>> ListByCode_Select(String dictCodes) {
        return baseMapper.ListByCode_Select(dictCodes);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean SystemParam_Delete(String id) {
        //先修改为删除状态 在通过路径判断把带有他路径的子项都改为删除状态
        try {
            SDictionary dictionary=new SDictionary();
            dictionary.setId(id);
            dictionary.setIsDel(1);
            baseMapper.updateById(dictionary);
            //FullPath全路径 不为空
            if (StringUtils.isNotBlank(dictionary.getFullPath())){
                String ProductID=dictionary.getProductID();
                String newFullPath=dictionary.getFullPath()+"/";
                QueryWrapper<SDictionary> wrapper=new QueryWrapper<>();
                wrapper.eq("ProductID",ProductID);
                wrapper.likeRight("FullPath",newFullPath);
                //查询出子项
                List<SDictionary> list = baseMapper.selectList(wrapper);
                //修改子项
                for (SDictionary sDictionary : list) {
                    sDictionary.setIsDel(1);
                    baseMapper.updateById(sDictionary);
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
    @Transactional(rollbackFor = Exception.class)
    public boolean SystemParam_Update(SDictionary dictionary) {
        try {
            SDictionary byId = this.getById(dictionary.getId());
            String OldPath=byId.getFullPath();
            SDictionary byId1 = this.getById(byId.getPid());
            String fullPath = byId1.getFullPath();
            String NewPath;
            if (StringUtils.isBlank(fullPath)){
                NewPath=dictionary.getDictName();
            }else {
                NewPath=fullPath+"/"+dictionary.getDictName();
            }
            String ProductID=byId.getProductID();
            dictionary.setFullPath(NewPath);
            dictionary.setEditor(ThreadLocalUtils.getUserName());
            dictionary.setEditTime(new Date());
            baseMapper.updateById(dictionary);
            if (StringUtils.isNotBlank(OldPath)){
                QueryWrapper<SDictionary> wrapper=new QueryWrapper<>();
                wrapper.eq("ProductID",ProductID);
                wrapper.likeRight("FullPath",OldPath);
                List<SDictionary> list = this.list(wrapper);
                for (SDictionary sDictionary : list) {
                    String fullPath1 = sDictionary.getFullPath();
                    fullPath1.replace(OldPath,NewPath+"/");
                    sDictionary.setFullPath(fullPath1);
                    this.updateById(sDictionary);
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
    public List<Map<String, Object>> SystemAllParams_Select_Tree(String pid,String ProjectID,String Media) {
        //参数第一层查询
        if ("-1".equals(pid))
        {
        List<Map<String,Object>> list=baseMapper.list(pid);
        List<Map<String, Object>> result=new ArrayList<>();

        for (Map<String, Object> map : list) {
            List<Map<String, Object>> id = baseMapper.list((String) map.get("ID"));
            if (id.size()>0){
                map.put("hasChild",true);
            }else {
                map.put("hasChild",false);
            }
            result.add(map);
        }
//          Map<String,Object> map=new HashMap<>();
//          map.put("DictName","媒体类别管理");
//          map.put("Media","Media");
//          map.put("hasChild",true);
//          result.add(map);
          return result;
         }
         //非第一层查询
         else {
             //选择原参数管理项的子集
             if (Media==null){
                 List<Map<String,Object>> list=baseMapper.list(pid);
                 List<Map<String, Object>> result=new ArrayList<>();

                 for (Map<String, Object> map : list) {
                     List<Map<String, Object>> id = baseMapper.list((String) map.get("ID"));
                     if (id.size()>0){
                         map.put("hasChild",true);
                     }else {
                         map.put("hasChild",false);
                     }
                     result.add(map);
                 }
                 return result;
             }
             //选择媒体类别管理子集
             else {
                 //媒体查询
                 List<Map<String, Object>> mediaLargeList_tree = getMediaLargeList_Tree(pid, ProjectID);
                 return mediaLargeList_tree;
             }
        }





    }

    @Override
    public Result SystemDictionaryDetail(HashMap<String,Object> param) {
        try {
            String IDAlias = "ID";
            String ChildAlias = "Child";
            String DictNameAlias = "DictName";
            String TypeAlias = "Type";
	        Map<String,String> Alias = (Map<String, String>) param.get("Alias");
	        if (Alias!=null){
	            IDAlias=Alias.get("IDAlias");
	            ChildAlias=Alias.get("ChildAlias");
	            DictNameAlias=Alias.get("DictNameAlias");
	        }
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
            String[] CodeList = param.get("Code").toString().toUpperCase().split(",");
            //遍历参数
            for (String Code : CodeList) {
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
                        if (res.get(Code)== null)
                        {
                            DictCodes = DictCodes + " OR B.DictCode = '"+Code+"'";
                            res.put(Code,new ArrayList<>());
                        }
                        break;
                }
            }
            //region 城市处理
            if (isCity){
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
                Short Levels = (Short) map.get("Levels");
                if (Levels == 2)
                {
                    if (StringUtils.equals("KHGJFS",PDictCode)&& (StringUtils.equals("ZQ",(String)param.get("JobCode")) ||StringUtils.equals("ZQJL",(String)param.get("JobCode"))||StringUtils.equals("ZQFZR",(String)param.get("JobCode")) ||StringUtils.equals("JZ",(String)param.get("JobCode"))) && StringUtils.equals("E30825AA-B894-4A5F-AF55-24CAC34C8F1F",(String)map.get("ID")))
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
                    if (one.get(PDictCode) != null)
                    {
                        Map<String,Object> dict = new HashMap<>();
                        dict.put(IDAlias,map.get("ID"));
                        dict.put(DictNameAlias,map.get("DictName"));
                        Map<String,Object> pDictCode = (Map<String, Object>) one.get(PDictCode);
                        ArrayList childAlias = (ArrayList) pDictCode.get(ChildAlias);
                        childAlias.add(dict);
                    }
                }
            }
            for (Map<String, Object> map : dictArray) {
                String PDictCode = (String) map.get("PDictCode");
                Short Levels = (Short) map.get("Levels");
                if (Levels == 2)
                {
                    String DictCode = (String) map.get("DictCode");
                    if (res.get(PDictCode)!= null && one.get(DictCode) != null)
                    {
                        ArrayList pDictCode = (ArrayList) res.get(PDictCode);
                        pDictCode.add(one.get(DictCode));
                    }
                }
            }

//      处理客户筛选规则(顾问)数据
            if (isKHSXGZGW)
            {
                String userID = (String) param.get("UserID");
                List<Map<String, Object>> list = vCustomergwlistSelectMapper.TagList_Select(userID);
                ((Map) ((List) res.get("KHSXGZGW")).get(3)).put("Child",list);
                List<Map<String,Object>> arr=customerfiltergroupService.groupList((String) param.get("JobCode"),(String)param.get("ProjectID"),(String)param.get("UserID"));
                List groupArr=new ArrayList();
                for (int j = 0; j < arr.size(); j++)
                {
                    HashMap<String,Object> dat = new HashMap<>();
                    dat.put("ID",arr.get(j).get("ID"));
                    dat.put("DictName",arr.get(j).get("Name"));
                    dat.put("Type","group");
                    dat.put("Filter",JSON.parseArray((String) arr.get(j).get("Filter")));
                    List<Object> dct= JSON.parseArray((String) arr.get(j).get("FilterDesc"));
                    List FilterDesc=new ArrayList();
                    for (Object temap : dct) {
                    	Map<String, Object> map = (Map<String, Object>)temap;
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
                ((Map) ((List) res.get("KHSXGZGW")).get(4)).put("Child",groupArr);
            }
//        处理分配顾问筛选规则(分接)数据
            if (isFPGWSXGZFJ){
            	List<Map<String,Object>> pk = new ArrayList<Map<String,Object>>();
                Map<String,Object> dictTop=new HashMap<>();
                dictTop.put(IDAlias,"");
                dictTop.put(DictNameAlias,"全部");
                dictTop.put(ChildAlias,new ArrayList<>());
                pk.add(dictTop);
                QueryWrapper<BSalesgroup> wrapper=new QueryWrapper();
                wrapper.eq("IsDel",0).eq("Status",1).eq("ProjectID",param.get("ProjectID"));
                wrapper.gt("Nature",0);
                List<BSalesgroup> groupArray = salesgroupService.list(wrapper);
                for (BSalesgroup bSalesgroup : groupArray) {
                    Map<String,Object> dict = new HashMap<>();
                    dict.put(IDAlias,bSalesgroup.getId());
                    dict.put(DictNameAlias,bSalesgroup.getName());
                    dict.put(ChildAlias,new ArrayList<>());
                    pk.add(dict);
                }
                res.put("FPGWSXGZFJ",pk);
            }
//        处理分配顾问筛选规则(营销经理)数据
            if (isFPGWSXGZYXJL){
            	List<Map<String,Object>> pk = new ArrayList<Map<String,Object>>();
                Map<String,Object> dictTop=new HashMap<>();
                dictTop.put(IDAlias,"");
                dictTop.put(DictNameAlias,"全部");
                dictTop.put(ChildAlias,new ArrayList<>());
                pk.add(dictTop);
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
                        pk.add(dict);
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
                            pk.add(dict);
                        }
                    }
                }
                res.put("FPGWSXGZYXJL",pk);
            }
//            处理公共池客户筛选规则(营销经理)数据
            if (isGGCKHSXGZYXJL){
            	List<Map<String,Object>> pk = new ArrayList<Map<String,Object>>();
                Map<String,Object> dictTop=new HashMap<>();
                dictTop.put(IDAlias,"");
                dictTop.put(DictNameAlias,"全部");
                dictTop.put(ChildAlias,new ArrayList<>());
                pk.add(dictTop);
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
                        pk.add(dict);
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
                            pk.add(dict);
                        }
                    }
                }
                res.put("GGCKHSXGZYXJL",pk);
            }
//        处理顾问跟进客户筛选规则(销售经理)数据
            if (isGWGJKHSXGZXSJL){
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
                        ((List) ((Map) ((List) res.get("GWGJKHSXGZXSJL")).get(2)).get("Child")).add(dict);
                    }
                }
            }
//        处理顾问公共客户筛选规则(销售经理)数据
            if (isGWGGKHSXGZXSJL){
                String TeamID=null;
                if (!StringUtils.equals((String)param.get("OrgID"),(String)param.get("ProjectID"))){
                    TeamID= (String) param.get("OrgID");
                    List<Map<String,Object>> saleArray=salesuserService.UserSalesList_Select(null,TeamID);
                    for (Map<String, Object> map : saleArray) {
                        Map<String,Object> dict = new HashMap<>();
                        dict.put(IDAlias,map.get("ID"));
                        dict.put(DictNameAlias,map.get("Name"));
                        ((List) ((Map) ((List) res.get("GWGGKHSXGZXSJL")).get(0)).get("Child")).add(dict);
                    }
                }
            }
//        处理顾问丢失客户筛选规则(销售经理)数据
            if (isGWDSKHSXGZXSJL){
                String TeamID=null;
                if (!StringUtils.equals((String)param.get("OrgID"),(String)param.get("ProjectID"))){
                    TeamID= (String) param.get("OrgID");
                    List<Map<String,Object>> saleArray=salesuserService.UserSalesList_Select(null,TeamID);
                    for (Map<String, Object> map : saleArray) {
                        Map<String,Object> dict = new HashMap<>();
                        dict.put(IDAlias,map.get("ID"));
                        dict.put(DictNameAlias,map.get("Name"));
                        ((List) ((Map) ((List) res.get("GWDSKHSXGZXSJL")).get(0)).get("Child")).add(dict);
                    }
                }
            }
//        处理盘客筛选规则(销售经理) 数据
            if (isPKSXGZXSJL){
                Map<String,Object> dictTop=new HashMap<>();
                dictTop.put(IDAlias,"");
                dictTop.put(DictNameAlias,"全部");
                dictTop.put(ChildAlias,new ArrayList<>());
//            ((JArray)res["PKSXGZXSJL"]).AddFirst(dictTop);  AddFirst插入到list第一位置??
                List<Map<String,Object>> pk = new ArrayList<Map<String,Object>>();
                pk.add(dictTop);
                res.put("PKSXGZXSJL",pk);
            }
//        处理房间户型数据
            if (isFJHX){
                List<Map<String,Object>> list=projectroomService.RoomTypeList_Select((String)param.get("BuildingID"));
                List<Map<String,Object>> pk = new ArrayList<Map<String,Object>>();
                for (Map<String, Object> map : list) {
                    Map<String,Object> dict = new HashMap<>();
                    dict.put(IDAlias,map.get("RoomType"));
                    dict.put(DictNameAlias,map.get("RoomType"));
                    dict.put(ChildAlias,new ArrayList<>());
                    pk.add(dict);
                }
                res.put("FJHX",pk);
            }
//        处理客户筛选规则(自渠)数据
            if (isKHSXGZZQ){
                String userID = (String) param.get("UserID");
                List<Map<String,Object>> list= tagService.BTaglist(userID);

                if (!"JZ".equals(param.get("JobCode"))){
                	((Map) ((List) res.get("KHSXGZZQ")).get(3)).put("Child",list);
                }
                //客户分组集合查询
                List<Map<String,Object>> arr=customerfiltergroupService.groupList((String) param.get("JobCode"),(String)param.get("ProjectID"),(String)param.get("UserID"));
                List groupArr=new ArrayList();
                for (int j = 0; j < arr.size(); j++)
                {
                    HashMap<String,Object> dat = new HashMap<>();
                    dat.put("ID",arr.get(j).get("ID"));
                    dat.put("DictName",arr.get(j).get("Name"));
                    dat.put("Type","group");
                    dat.put("Filter",JSON.parseArray((String) arr.get(j).get("Filter")));
                    List<Object> dct= JSON.parseArray((String) arr.get(j).get("FilterDesc"));
                    List FilterDesc=new ArrayList();
                    for (Object temap : dct) {
                    	Map<String, Object> map = (Map<String, Object>)temap;
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
                if (!"JZ".equals(param.get("JobCode")))
                {
                	((Map) ((List) res.get("KHSXGZZQ")).get(4)).put("Child",groupArr);
                }else {
                	((Map) ((List) res.get("KHSXGZZQ")).get(2)).put("Child",groupArr);
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
                    dat.put("DictName", arr.get(j).get("Name"));
                    dat.put("Type", "group");
                    dat.put("Filter", JSON.parseArray((String) arr.get(j).get("Filter")));
                    List<Object> dct= JSON.parseArray((String) arr.get(j).get("FilterDesc"));
                    List FilterDesc=new ArrayList();
                    for (Object temap : dct) {
                    	Map<String, Object> map = (Map<String, Object>)temap;
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
                ((Map) ((List) res.get("KHGGCSXGZGW")).get(2)).put("Child",groupArr);
            }
//        处理客户公共池筛选规则(自渠)
            if (isKHGGCSXGZZQ){
//            客户分组集合查询
                List<Map<String, Object>> arr = customerfiltergroupService.groupList((String) param.get("JobCode"), (String) param.get("ProjectID"), (String) param.get("UserID"));
                List groupArr = new ArrayList();
                for (int j = 0; j < arr.size(); j++) {
                    HashMap<String, Object> dat = new HashMap<>();
                    dat.put("ID", arr.get(j).get("ID"));
                    dat.put("DictName", arr.get(j).get("Name"));
                    dat.put("Type", "group");
                    dat.put("Filter", JSON.parseArray((String) arr.get(j).get("Filter")));
                    List<Object> dct= JSON.parseArray((String) arr.get(j).get("FilterDesc"));
                    List FilterDesc=new ArrayList();
                    for (Object temap : dct) {
                    	Map<String, Object> map = (Map<String, Object>)temap;
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
                ((Map) ((List) res.get("KHGGCSXGZZQ")).get(2)).put("Child",groupArr);
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
            System.out.println("1===================>>>>>>>"+data);
            for(Object list : data){
            	Map<String,Object> map = (Map<String, Object>) list;
            	if("客户状态".equals(map.get("DictName"))){
            		List<Map<String,Object>> Child = (List<Map<String, Object>>) map.get("Child");
            		//修改---客户状态改字段名称
            		List<String> id = new ArrayList<String>();
            		for(Map<String,Object> c: Child){
            			if("询问".equals(c.get("DictName"))){
            				c.put(DictNameAlias,"报备");
            			}
            			if("看房".equals(c.get("DictName"))
            					|| "认购中".equals(c.get("DictName"))){
            				id.add((String)c.get("ID"));
            				c.put(DictNameAlias,"到访");
            			}
            			if("丢失".equals(c.get("DictName"))){
            				c.put(DictNameAlias,"无效");
            			}
            		}
            		for(Map<String, Object> c : Child){
        				if("到访".equals(c.get("DictName"))){
        	            	c.put("ID",id);
        	            }
        			}
        			HashSet h = new HashSet(Child);   
        			Child.clear();   
        			Child.addAll(h);
            	}
            }
            System.out.println("2==================>>>>>>>"+data);
            return Result.ok(data);
            
        }catch (Exception e){
            e.printStackTrace();
            return Result.errormsg(99,"数据字典错误");
        }
    }
    @Override
    public Result PCSystemDictionaryDetail(HashMap<String,Object> param) {

            try {
                String IDAlias = "ID";
                String ChildAlias = "Child";
                String DictNameAlias = "DictName";

                Map<String,String> Alias = (Map<String, String>) param.get("Alias");
                if (Alias!=null){
                    IDAlias=Alias.get("IDAlias");
                    ChildAlias=Alias.get("ChildAlias");
                    DictNameAlias=Alias.get("DictNameAlias");
                }
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

                Map<String,Object> res=new HashMap<>();
                String DictCodes = "";
                String[] CodeList = param.get("Code").toString().toUpperCase().split(",");
                //遍历参数
                for (String Code : CodeList) {
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

                            if (res.get(Code)== null)
                            {
                                DictCodes = DictCodes + " OR B.DictCode = '"+Code+"'";
                                res.put(Code,new ArrayList<>());
                            }
                            break;
                    }
                }
                //region 城市处理
                if (isCity){
                    QueryWrapper<SCity> wrapper=new QueryWrapper<>();
                    wrapper.eq("Status",1).le("Levels",4).orderByAsc("Levels","StandardIndex");
                    List<SCity> list =  cityService.list(wrapper);
                    Map<String,Map<String,Object>> province = new HashMap<>();
                    Map<String,Map<String,Object>> city = new HashMap<>();
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
                }
//        通用数据字典处理
                List<Map<String,Object>> dictArray=new ArrayList<>();

                try {
                    dictArray=dictionaryService.ListByCode_Select(DictCodes);
                } catch (Exception e) {
                    e.printStackTrace();
                    return Result.errormsg(99,"数据库查询异常");
                }

                Map<String,Object> one = new HashMap<>();
                for (Map<String, Object> map : dictArray) {
                    String PDictCode = (String) map.get("PDictCode");
                    int Levels = (int) map.get("Levels");
                    if (Levels == 2)
                    {

                            String DictCode = (String) map.get("DictCode");
                            Map<String,Object> dict = new HashMap<>();
                            dict.put(IDAlias,map.get("ID"));
                            dict.put(DictNameAlias,map.get("DictName"));

                            dict.put(ChildAlias,new ArrayList<>());
                            one.put(DictCode,dict);

                    }
                    if (Levels == 3)
                    {
                        if (one.get(PDictCode) != null)
                        {
                            Map<String,Object> dict = new HashMap<>();
                            dict.put(IDAlias,map.get("ID"));
                            dict.put(DictNameAlias,map.get("DictName"));
                            Map<String,Object> pDictCode = (Map<String, Object>) one.get(PDictCode);
                            ArrayList childAlias = (ArrayList) pDictCode.get(ChildAlias);
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
                        if (res.get(PDictCode)!= null )
                        {
                            ArrayList pDictCode = (ArrayList) res.get(PDictCode);
                            pDictCode.add(one.get(DictCode));
                        }
                    }
                }

//      处理客户筛选规则(顾问)数据
                if (isKHSXGZGW)
                {
                    String userID = (String) param.get("UserID");
                    List<Map<String, Object>> list = vCustomergwlistSelectMapper.TagList_Select(userID);
                    ((Map) ((List) res.get("KHSXGZGW")).get(3)).put("Child",list);

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
                if (isFPGWSXGZYXJL){
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
                if (isGGCKHSXGZYXJL){
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
                if (isGWGJKHSXGZXSJL){
                    String TeamID=null;

                        TeamID= (String) param.get("OrgID");

                    List<Map<String,Object>> saleArray=null;
                    if (StringUtils.isNotBlank(TeamID)){
                        saleArray=salesuserService.UserSalesList_Select(null,TeamID);
                        for (Map<String, Object> map : saleArray) {
                            Map<String,Object> dict = new HashMap<>();
                            dict.put(IDAlias,map.get("ID"));
                            dict.put(DictNameAlias,map.get("Name"));
                            ((List) ((Map) ((List) res.get("GWGJKHSXGZXSJL")).get(2)).get("Child")).add(dict);
                        }
                    }
                }
//        处理顾问公共客户筛选规则(销售经理)数据
                if (isGWGGKHSXGZXSJL){
                    String TeamID=null;

                        TeamID= (String) param.get("OrgID");
                        List<Map<String,Object>> saleArray=salesuserService.UserSalesList_Select(null,TeamID);
                        for (Map<String, Object> map : saleArray) {
                            Map<String,Object> dict = new HashMap<>();
                            dict.put(IDAlias,map.get("ID"));
                            dict.put(DictNameAlias,map.get("Name"));
                            ((List) ((Map) ((List) res.get("GWGGKHSXGZXSJL")).get(0)).get("Child")).add(dict);
                        }

                }
//        处理顾问丢失客户筛选规则(销售经理)数据
                if (isGWDSKHSXGZXSJL){
                    String TeamID=null;

                        TeamID= (String) param.get("OrgID");
                        List<Map<String,Object>> saleArray=salesuserService.UserSalesList_Select(null,TeamID);
                        for (Map<String, Object> map : saleArray) {
                            Map<String,Object> dict = new HashMap<>();
                            dict.put(IDAlias,map.get("ID"));
                            dict.put(DictNameAlias,map.get("Name"));
                            ((List) ((Map) ((List) res.get("GWDSKHSXGZXSJL")).get(0)).get("Child")).add(dict);
                        }

                }
//        处理盘客筛选规则(销售经理) 数据
                if (isPKSXGZXSJL){
                    Map<String,Object> dictTop=new HashMap<>();
                    dictTop.put(IDAlias,"");
                    dictTop.put(DictNameAlias,"全部");
                    dictTop.put(ChildAlias,new ArrayList<>());
//            ((JArray)res["PKSXGZXSJL"]).AddFirst(dictTop);  AddFirst插入到list第一位置??
                    List<Map<String,Object>> pk = new ArrayList<Map<String,Object>>();
                    pk.add(dictTop);
                    res.put("PKSXGZXSJL",pk);
                }
//        处理房间户型数据
                if (isFJHX){
                    List<Map<String,Object>> list=projectroomService.RoomTypeList_Select((String)param.get("BuildingID"));
                    List<Map<String,Object>> pk = new ArrayList<Map<String,Object>>();
                    for (Map<String, Object> map : list) {
                        Map<String,Object> dict = new HashMap<>();
                        dict.put(IDAlias,map.get("RoomType"));
                        dict.put(DictNameAlias,map.get("RoomType"));
                        dict.put(ChildAlias,new ArrayList<>());
                        pk.add(dict);
                    }
                    res.put("FJHX",pk);
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

            }catch (Exception e){
                e.printStackTrace();
                return Result.errormsg(99,"数据字典错误");
            }
    }

    @Override
    public List<Map<String, Object>> getMediaLargeList_Tree(String pid,String projectID) {
        if (StringUtils.equals("-1",pid)){
            List<Map<String,Object>> list=baseMapper.getMediaLargeList_New();
            for (Map<String, Object> map : list) {
                map.put("hasChild",true);
                map.put("Media","Media");
            }
            return list;
        }else {
            List<Map<String,Object>> childList=baseMapper.getMediaChildListByPid(pid,projectID);
            for (Map<String, Object> map : childList) {
                map.put("hasChild",false);
                map.put("Media","Media");
            }
            return childList;
        }
    }


//    @Override
//    public IPage<Map<String,Object>> getMediaList(IPage page, String pid, String projectID) {
//        if (pid==null){
//            IPage<Map<String,Object>> list=baseMapper.getMediaLargeList(page);
//            for (Map<String, Object> map : list.getRecords()) {
//                map.put("hasChild",true);
//                map.put("Media","Media");
//            }
//            return list;
//        }else {
//            IPage<Map<String,Object>> childList=baseMapper.getMediaChildListByPid(page,pid,projectID);
//            for (Map<String, Object> map : childList.getRecords()) {
//                map.put("hasChild",false);
//                map.put("Media","Media");
//            }
//            return childList;
//        }
//    }

    @Override
    public void updateMediaStatus(String id, int status) {
        baseMapper.updateMediaStatus(id,status);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result saveMedia(String pid, String id, String dictName, int listIndex,  String projectID) {
        try {
            String Creator=ThreadLocalUtils.getUserName();
            //pid==-1为父级B_MediaLarge
            if ("-1".equals(pid)){
            baseMapper.saveMediaLarge(id,dictName,listIndex,Creator);
            }
            //pid!=-1 为子集B_MediaChild
            else {
                baseMapper.saveMediaChild(id,dictName,listIndex,Creator,projectID,pid);
            }

        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.errormsg(99,"新增失败");
        }
        return Result.okm("成功");
    }


}
