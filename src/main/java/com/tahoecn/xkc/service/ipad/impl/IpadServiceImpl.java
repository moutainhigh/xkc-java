package com.tahoecn.xkc.service.ipad.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.hutool.core.date.DateUtil;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tahoecn.xkc.common.enums.ActionType;
import com.tahoecn.xkc.common.enums.MessageType;
import com.tahoecn.xkc.converter.CareerConsCustConverter;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.mapper.customer.VCustomerfjlistSelectMapper;
import com.tahoecn.xkc.mapper.customer.VCustomergwlistSelectMapper;
import com.tahoecn.xkc.mapper.ipad.IpadMapper;
import com.tahoecn.xkc.model.vo.CGWDetailModel;
import com.tahoecn.xkc.model.vo.CPageModel;
import com.tahoecn.xkc.model.vo.CSearchModelVo;
import com.tahoecn.xkc.model.vo.ChildItem;
import com.tahoecn.xkc.model.vo.CustomerActionVo;
import com.tahoecn.xkc.model.vo.OptionItem;
import com.tahoecn.xkc.service.customer.ICustomerHelp;
import com.tahoecn.xkc.service.customer.IVCustomergwlistSelectService;
import com.tahoecn.xkc.service.ipad.IIpadService;
import com.tahoecn.xkc.service.sys.ISystemAccountService;
import com.tahoecn.xkc.service.sys.ISystemMessageService;

@SuppressWarnings("unchecked")
@Service
public class IpadServiceImpl implements IIpadService {

	@Resource
	private IpadMapper ipadMapper;
	@Value("${SiteUrl}")
    private String SiteUrl;
	@Resource
	private VCustomerfjlistSelectMapper vCustomerfjlistSelectMapper;
	@Resource
	private ICustomerHelp customerTemplate;
	@Resource
	private VCustomergwlistSelectMapper vCustomergwlistSelectMapper;
	@Resource
	private ISystemMessageService iSystemMessageService;
	@Resource
	private ISystemAccountService iSystemAccountService;
	@Resource
	private IVCustomergwlistSelectService iVCustomergwlistSelectService;
	
	@Override
	public Result mLFProjectList_Select(JSONObject param) {
		Result re = new Result();
		try {
			JSONObject data = new JSONObject();
			JSONArray areaJaary = new JSONArray();
	        Map<String,Object> pmap = JSONObject.parseObject(param.toJSONString(),Map.class);
	        List<Map<String, Object>>  projectArray = ipadMapper.mLFProjectList_Select(pmap);
	        if (projectArray!=null && projectArray.size() > 0){
	            //去重复 取区域列表
	        	//去重复 取公司机构列表
	        	List<Map<String,Object>> areaList = new ArrayList<>();
	        	List<Map<String,Object>> orgList = new ArrayList<>();
	        	List<String> areaKeys = new ArrayList<>();
	        	List<String> orgKeys = new ArrayList<>();
	        	for(Map<String,Object> item : projectArray){
	        		if(item.get("AreaID")!=null){
	        			String areaKey = item.get("AreaID").toString();
	        			if(!areaKeys.contains(areaKey)){
	        				areaList.add(item);
	        				areaKeys.add(areaKey);
	        			}
	        		}
	        		if(item.get("OrganizationID")!=null){
	        			String orgKey = item.get("OrganizationID").toString();
	        			if(!orgKeys.contains(orgKey)){
	        				orgList.add(item);
	        				orgKeys.add(orgKey);
	        			}
	        		}
	        	}

	            for (Map<String,Object> areaItem : areaList){
	            	JSONObject areaObj = new JSONObject();
	                areaObj.put("AreaID", areaItem.get("AreaID")); //区域ID
	                areaObj.put("AreaName", areaItem.get("AreaName")); //区域名称
	                JSONArray orgArray = new JSONArray();
	                for(Map<String,Object> orgItem : orgList){
	                    if (orgItem.get("AreaName")!=null && areaItem.get("AreaName")!=null && orgItem.get("AreaName").toString().equals(areaItem.get("AreaName").toString())){
	                    	JSONObject org = new JSONObject();
	                        org.put("OrganizationID", orgItem.get("OrganizationID")); //公司机构ID
	                        org.put("OrganizationName", orgItem.get("OrganizationName")); //公司机构名称

	                        JSONArray proArray = new JSONArray();
	                        for (Map<String,Object> proItem : projectArray){
	                            if (proItem.get("OrganizationName")!=null && orgItem.get("OrganizationName")!=null && proItem.get("OrganizationName").toString().equals(orgItem.get("OrganizationName").toString())){
	                            	JSONObject proObj = new JSONObject();
	                                proObj.put("ProjectID",proItem.get("ProjectID")); //项目ID
	                                proObj.put("ProjectName",proItem.get("ProjectName")); //项目名称
	                                proArray.add(proObj);
	                            }
	                        }
	                        org.put("ProjectList", proArray);
	                        orgArray.add(org);
	                    }
	                }
	                areaObj.put("OrganizationList", orgArray);
	                areaJaary.add(areaObj);
	            }
	        }
	        data.put("EstatePlate", "地产板块");
	        data.put("AreaList", areaJaary);
	        re.setData(data);
	        re.setErrcode(0);
	        re.setErrmsg("成功");
		} catch (Exception e) {
			re.setErrcode(1);
		    re.setErrmsg("服务器异常！");
			e.printStackTrace();
		}
        return re;
	}

	@Override
	public Result UserProjectChange_Update(JSONObject Parameter) {
		Result entity = new Result();
        try{
        	Map<String,Object> pmap = JSONObject.parseObject(Parameter.toJSONString(),Map.class);
        	List<Map<String,Object>> valid = ipadMapper.mUserProjectChange_Update_valid(pmap);
        	if(valid!=null && valid.size()>0){
        		ipadMapper.mUserProjectChange_Update_update(pmap);
        	}else{
        		ipadMapper.mUserProjectChange_Update_insert(pmap);
        	}
            entity.setErrcode(0);
            entity.setErrmsg("成功");
        }catch (Exception e){
            entity.setErrcode(110);
            entity.setErrmsg("项目更换异常！");
            e.printStackTrace();
        }
        return entity;
	}

	@Override
	public Result mLFCustomerGWList_Select(JSONObject paramAry) {
		Result entity = new Result();
        if (paramAry==null || paramAry.size() == 0){
            entity.setErrcode(1);
            entity.setErrmsg("参数错误");
            return entity;
        }try{
            CPageModel model = JSONObject.parseObject(paramAry.toJSONString(), CPageModel.class);
            StringBuilder whereSb = new StringBuilder();
            StringBuilder OrderSb = new StringBuilder();
            if (!StringUtils.isEmpty(model.getKeyWord())){
                whereSb.append(" and (Name LIKE '%"+model.getKeyWord()+"%' OR GroupName LIKE '%"+model.getKeyWord()+"%' OR Mobile LIKE '%"+model.getKeyWord()+"%')");
            }
            paramAry.put("WHERE", whereSb.toString());
            paramAry.put("ORDER", OrderSb.toString());
            paramAry.put("SiteUrl", SiteUrl);
            //顾问列表
            Map<String,Object> pmap = JSONObject.parseObject(paramAry.toJSONString(),Map.class);
            List<Map<String, Object>> allGwJArray = vCustomerfjlistSelectMapper.sCustomerFJAdviserList_Select(pmap);
            //去重复 取组集合
            List<Map<String, Object>> groupList = new ArrayList<>();
            List<String> groupKeys = new ArrayList<>();
            for(Map<String, Object> item : allGwJArray){
            	if(item.get("GroupID")!=null){
            		String groupKey = item.get("GroupID").toString();
            		if(!groupKeys.contains(groupKey)){
            			groupKeys.add(groupKey);
            			groupList.add(item);
            		}
            	}
            }

            JSONObject data = new JSONObject();
            JSONArray groupJArry = new JSONArray();

            //添加全部分组
            if (StringUtils.isEmpty(model.getKeyWord())){
            	JSONObject totalGroupObj = new JSONObject();
                totalGroupObj.put("GroupID", "");
                totalGroupObj.put("GroupName", "全部");
                JSONArray gwJarry1 = new JSONArray();
                for(Map<String, Object> gwItem : allGwJArray){
                	JSONObject gwObj = new JSONObject();
                    gwObj.put("SaleUserID",gwItem.get("ID"));
                    gwObj.put("SaleUserName",gwItem.get("Name"));
                    gwObj.put("HeadImg",gwItem.get("HeadImg"));
                    gwObj.put("GroupName",gwItem.get("GroupName"));
                    gwObj.put("Mobile",gwItem.get("Mobile"));
                    gwObj.put("ReceptCount",gwItem.get("DayTotalCount"));
                    gwObj.put("IsSigned", 0);
                    gwJarry1.add(gwObj);
                }
                totalGroupObj.put("SaleUserList", gwJarry1);
                groupJArry.add(totalGroupObj);
            }
            //循环添加分组
            for(Map<String, Object> groupItem : groupList){
            	JSONObject groupObj = new JSONObject();
                groupObj.put("GroupID",groupItem.get("GroupID"));
                groupObj.put("GroupName",groupItem.get("GroupName"));

                JSONArray gwJarry = new JSONArray();
                //循环添加顾问
                for(Map<String, Object> gwItem : allGwJArray){
                    if (gwItem.get("GroupID")!=null && groupItem.get("GroupID")!=null && gwItem.get("GroupID").toString().equals(groupItem.get("GroupID").toString())){
                    	JSONObject gwObj = new JSONObject();
                        gwObj.put("SaleUserID",gwItem.get("ID"));  //顾问ID
                        gwObj.put("SaleUserName",gwItem.get("Name")); //顾问姓名
                        gwObj.put("HeadImg",gwItem.get("HeadImg")); //顾问头像
                        gwObj.put("GroupName",gwItem.get("GroupName"));
                        gwObj.put("Mobile",gwItem.get("Mobile"));
                        gwObj.put("ReceptCount",gwItem.get("DayTotalCount")); //接待数量
                        gwObj.put("IsSigned", 0);
                        gwJarry.add(gwObj);
                    }
                }
                groupObj.put("SaleUserList", gwJarry);
                groupJArry.add(groupObj);
            }
            data.put("GroupList", groupJArry);
            entity.setData(data);
            entity.setErrcode(0);
            entity.setErrmsg("成功");
        }catch (Exception e){
            entity.setErrcode(1);
            entity.setErrmsg("服务器异常");
            e.printStackTrace();
        }
        return entity;
	}

	@Override
	public Result mLFCustomerDetailByMobile_Select(JSONObject paramAry) {
		Result entity = new Result();
		try {
			CSearchModelVo model = JSONObject.parseObject(paramAry.toJSONString(),CSearchModelVo.class);
	        JSONObject returnDataObj = new JSONObject();
	        JSONObject CustomerObj = new JSONObject();
	        if (!StringUtils.isEmpty(model.getMobile())){
	        	customerTemplate.CustomerMobileSearch(model.getProjectID(), model.getMobile(), model.getJobCode(), model.getUserID());
	        	customerTemplate.ComeOverdueClue_Update(model.getProjectID(), model.getMobile(), model.getUserID(), model.getOrgID(), model.getJobID());
	        }
	        int isNew = 1; //是否新客户
	        int isAlloc = 0; //是否分配
	        int isReAlloc = 0; //若是老客户 是否允许再次分配  0否 1是

	        String customerID = "";  //客户ID
	        String customerName = "";  //客户名称
	        String customerGender = ""; //客户性别
	        String customerMobile = ""; //客户性别
	        String opportunitySourceName = "";  //来源
	        JSONArray optionArray = new JSONArray(); //来源子项
	        Integer ruleType = null;  //规则类型，0为报备保护规则，1为竞争带看规则.
	        String remark = ""; //备注
	        String saleUserID = ""; //顾问ID
	        String saleUserName = "";//顾问姓名
	        String groupName = "";//顾问所在组
	        String headImg = ""; //顾问头像
	        String reportTime = ""; //报备时间
	        String theFirstVisitDate = ""; //首访日期
	        String customerTag = ""; //顾客标签
	        String salePartnerID = ""; //协作人ID

	        //验证是否为老机会老客户
	        JSONObject re_j = customerTemplate.CustomerOpportunityExist(model.getProjectID(), model.getMobile());
	        if (re_j.getBooleanValue("status")){//是老客户
	        	CustomerObj = re_j.getJSONObject("CustomerObj");
	            saleUserID = CustomerObj.getString("SaleUserID");
	            salePartnerID =CustomerObj.getString("SalePartnerID");
	            if ((saleUserID != null && !"".equals(saleUserID) && saleUserID.length() > 0 && "C4C09951-FA39-4982-AAD1-E72D9D4C3899".equals(saleUserID))){//为无  CustomerModeType.分接_老机会_老客户_未分配
	                isNew = 0;
	                isAlloc = 0;
	            }if ((saleUserID != null && !"".equals(saleUserID) && saleUserID.length() > 0 && !"C4C09951-FA39-4982-AAD1-E72D9D4C3899".equals(saleUserID)) || !"".equals(salePartnerID)){//不为无  CustomerModeType.分接_老机会_老客户
	                isNew = 0;
	                isAlloc = 1;
	            }
	            if (!StringUtils.isEmpty(paramAry.getString("VisitAddress"))){
	                Map<String,Object> proParam = new HashMap<>();
	                proParam.put("ID", model.getProjectID());
	                Map<String, Object> objProject = ipadMapper.Project_Detail_FindById(proParam);
	                Number numberIsLocationValidate = (Number)objProject.get("IsLocationValidate");
	                Integer IsLocationValidate = numberIsLocationValidate.intValue(); //是否开启地理位置验证 1.开启 0.不开启
	                Number numberIsOffSiteSale = (Number)objProject.get("IsOffSiteSale");
	                Integer IsOffSiteSale = numberIsOffSiteSale.intValue(); //是否开启异地销售 1.开启 0.不开启

	                String SaleGroupName = CustomerObj.getString("SaleGroupName"); //顾问分组
	                String FirstVisitAddress = CustomerObj.getString("FirstVisitAddress");//首访地址
	                String VisitAddress = paramAry.getString("VisitAddress");
	                //如果首访地址不为空，是否异地根据顾问所在团队判断
	                if ("".equals(FirstVisitAddress) &&  SaleGroupName.contains(VisitAddress.substring(0, VisitAddress.length() - 1))){
	                	//如果首访地址为空，并且客户到访本地案场
	                    isReAlloc = 0;
	                }
	                if (IsLocationValidate == 1 && IsOffSiteSale == 1 &&  "".equals(FirstVisitAddress) && !SaleGroupName.contains(VisitAddress.substring(0, VisitAddress.length() - 1)) && "".equals(salePartnerID)){
	                	//如果首访地址为空，客户到访异地案场
	                    isReAlloc = 1;
	                }
	                if (!"".equals(FirstVisitAddress) && FirstVisitAddress != null && !FirstVisitAddress.equals(VisitAddress) && "".equals(salePartnerID)){
	                	//如果首访地址不为空，是否异地根据首访地址判断
	                    isReAlloc = 1;
	                }
	            }
	            paramAry.put("SiteUrl", SiteUrl);
	            Map<String,Object> pmap = JSONObject.parseObject(paramAry.toJSONString(),Map.class);
	            
	            List<Map<String, Object>>  lfCustomerDetailObj_list = ipadMapper.mLFCustomerDetailByMobile_Select(pmap);
	            if(lfCustomerDetailObj_list!=null && lfCustomerDetailObj_list.size()>0){
	            	Map<String, Object> lfCustomerDetailObj = lfCustomerDetailObj_list.get(0);
		            customerID = lfCustomerDetailObj.get("CustomerID")!=null?lfCustomerDetailObj.get("CustomerID").toString():"";  //客户ID
		            customerName = lfCustomerDetailObj.get("CustomerName")!=null?lfCustomerDetailObj.get("CustomerName").toString():"";  //客户名称
		            customerGender =lfCustomerDetailObj.get("CustomerGender")!=null?lfCustomerDetailObj.get("CustomerGender").toString():""; //客户性别
		            customerMobile =lfCustomerDetailObj.get("CustomerMobile")!=null?lfCustomerDetailObj.get("CustomerMobile").toString():"";//客户性别
		            opportunitySourceName =lfCustomerDetailObj.get("OpportunitySourceName")!=null?lfCustomerDetailObj.get("OpportunitySourceName").toString():""; //来源
		            remark = lfCustomerDetailObj.get("Remark")!=null?lfCustomerDetailObj.get("Remark").toString():""; //备注
		            saleUserName = lfCustomerDetailObj.get("SaleUserName")!=null?lfCustomerDetailObj.get("SaleUserName").toString():"";//顾问姓名
		            groupName = lfCustomerDetailObj.get("GroupName")!=null?lfCustomerDetailObj.get("GroupName").toString():"";//顾问所在组
		            headImg = lfCustomerDetailObj.get("SaleHeadImg")!=null?lfCustomerDetailObj.get("SaleHeadImg").toString():""; //顾问头像
		            reportTime = lfCustomerDetailObj.get("ReportTime")!=null?lfCustomerDetailObj.get("ReportTime").toString():""; //报备时间
		            theFirstVisitDate = lfCustomerDetailObj.get("TheFirstVisitDate")!=null?lfCustomerDetailObj.get("TheFirstVisitDate").toString():""; //首访日期
		            customerTag = lfCustomerDetailObj.get("CustomerTag")!=null?lfCustomerDetailObj.get("CustomerTag").toString():"";//顾客标签
	            }

	            model.setCustomerPotentialID(CustomerObj.getString("CustomerPotentialID")); //潜在客户ID
	            JSONObject reSource = GetClueOpportunitySource(model);
	            ChildItem clueOpportunitySource = null;
	            if(reSource!=null && reSource.size()>0){
	            	clueOpportunitySource = (ChildItem) reSource.get("childItem");
	            	ruleType = reSource.getInteger("ruleType");
	            }
	            if (clueOpportunitySource != null && clueOpportunitySource.getOption().size() > 0){
	                for(OptionItem item : clueOpportunitySource.getOption()){
	                	JSONObject obj1 = new JSONObject();
	                    obj1.put("ID", item.getID());
	                    obj1.put("Name", item.getName());
	                    obj1.put("IsChoose", item.getIsChoose());
	                    optionArray.add(obj1);
	                }
	            }
	        }else{//新客户
	            isReAlloc = 1;
	            JSONObject re_j_1 = customerTemplate.CustomerExist(model.getMobile());
	            if (!re_j_1.getBooleanValue("status")){//不存在客户信息
	                isNew = 1;
	                isAlloc = 0;
	                Map<String,Object> pmap = JSONObject.parseObject(paramAry.toJSONString(),Map.class);
	                Map<String,Object> lfCustomerPotentialDetailObj = ipadMapper.mLFCustomerPotentialDetailByMobile_Select(pmap);
	                if(lfCustomerPotentialDetailObj!=null && lfCustomerPotentialDetailObj.size()>0){
	                	Number number = (Number)lfCustomerPotentialDetailObj.get("RuleType");
	                	if (number.intValue()== 1){//如果是竞争带看
	                        entity.setErrcode(1);
	                        entity.setErrmsg("客户已存在，请扫码确认");
	                        return entity;
	                    }
	                    customerName = lfCustomerPotentialDetailObj.get("CustomerName")!=null?lfCustomerPotentialDetailObj.get("CustomerName").toString():"";  //客户名称
	                    customerGender = lfCustomerPotentialDetailObj.get("CustomerGender")!=null?lfCustomerPotentialDetailObj.get("CustomerGender").toString():"";//客户性别
	                    customerMobile = lfCustomerPotentialDetailObj.get("CustomerMobile")!=null?lfCustomerPotentialDetailObj.get("CustomerMobile").toString():"";//客户性别
	                    opportunitySourceName = lfCustomerPotentialDetailObj.get("OpportunitySourceName")!=null?lfCustomerPotentialDetailObj.get("OpportunitySourceName").toString():"";//来源
	                    model.setCustomerPotentialID(CustomerObj.getString("CustomerPotentialID")); //潜在客户ID
	                    
	                    JSONObject reSource = GetClueOpportunitySource(model);
	                    ChildItem clueOpportunitySource = null;
	                    if(reSource!=null && reSource.size()>0){
	                    	clueOpportunitySource = (ChildItem) reSource.get("childItem");//获取来源
	                    	ruleType = reSource.getInteger("ruleType");
	                    }
	                    if (clueOpportunitySource != null && clueOpportunitySource.getOption().size() > 0){
	                        for (OptionItem item : clueOpportunitySource.getOption()){
	                        	JSONObject obj1 = new JSONObject();
	                            obj1.put("ID", item.getID());
	                            obj1.put("Name", item.getName());
	                            obj1.put("IsChoose", item.getIsChoose());
	                            optionArray.add(obj1);
	                        }
	                    }
	                    opportunitySourceName = optionArray.getJSONObject(0).getString("Name");
	                }
	            }else{
	            	CustomerObj = re_j_1.getJSONObject("CustomerObj");
	                isNew = 0;
	                isAlloc = 0;
	                customerID = CustomerObj.getString("CustomerID");  //客户ID
	                customerName = CustomerObj.getString("CustomerName");  //客户名称
	                customerGender = CustomerObj.getString("CustomerGender"); //客户性别
	                customerMobile = CustomerObj.getString("CustomerMobile"); //客户性别
	                remark = CustomerObj.getString("Remark");//备注

	                model.setCustomerPotentialID(CustomerObj.getString("CustomerPotentialID")); //潜在客户ID
	                JSONObject reSource = GetClueOpportunitySource(model);
	                ChildItem clueOpportunitySource = null;
	                if(reSource!=null && reSource.size()>0){
	                	clueOpportunitySource = (ChildItem) reSource.get("childItem");//获取来源
	                	ruleType = reSource.getInteger("ruleType");
	                }
	                if (clueOpportunitySource != null && clueOpportunitySource.getOption().size() > 0){
	                    for (OptionItem item : clueOpportunitySource.getOption()){
	                    	JSONObject obj1 = new JSONObject();
	                        obj1.put("ID", item.getID());
	                        obj1.put("Name", item.getName());
	                        obj1.put("IsChoose", item.getIsChoose());
	                        optionArray.add(obj1);
	                    }
	                }
	                opportunitySourceName = optionArray.getJSONObject(0).getString("Name");
	            }
	        }
	        //如果是公共客户池客户，销支没有分配权限，只能查看
	        Map<String,Object> pmap = JSONObject.parseObject(paramAry.toJSONString(),Map.class);
	        Map<String, Object> objCustomerPublicPool = ipadMapper.mCustomerPublicPoolByMobile_Select(pmap);
	        if (objCustomerPublicPool!=null && objCustomerPublicPool.size() > 0){
	            isNew = 0;
	            isAlloc = 1;
	            isReAlloc = 0;
	        }
	        returnDataObj.put("IsNew", isNew); //是否新客户 0否 1是
	        returnDataObj.put("IsAlloc", isAlloc); //是否分配
	        returnDataObj.put("IsReAlloc", isReAlloc); //是否允许再次分配 0否 1是
	        returnDataObj.put("CustomerID", customerID);
	        returnDataObj.put("CustomerName", customerName);
	        returnDataObj.put("CustomerGender", customerGender);
	        returnDataObj.put("OpportunitySourceName", opportunitySourceName);
	        returnDataObj.put("SourceOption", optionArray);
	        returnDataObj.put("Remark", remark);
	        returnDataObj.put("RuleType", ruleType);  //规则类型，0为报备保护规则，1为竞争带看规则.
	        returnDataObj.put("SaleUserID", saleUserID); //顾问ID
	        returnDataObj.put("SaleUserName", saleUserName == "" ? "系统" : saleUserName); //顾问姓名
	        returnDataObj.put("GroupName", groupName); //顾问组名称
	        returnDataObj.put("SaleHeadImg", headImg); //顾问头像
	        returnDataObj.put("CustomerMobile", customerMobile); //客户电话
	        returnDataObj.put("ReportTime", reportTime); //报备日期
	        returnDataObj.put("FirstComeTime", theFirstVisitDate); //首访日期
	        returnDataObj.put("CustomerTag", customerTag); //顾客标签
	        
	        entity.setData(returnDataObj);
	        entity.setErrcode(0);
	        entity.setErrmsg("");
		} catch (Exception e) {
			entity.setErrcode(1);
	        entity.setErrmsg("服务器异常！");
			e.printStackTrace();
		}
        return entity;
	}
	
	public JSONObject GetClueOpportunitySource(CSearchModelVo model){
		JSONObject re = new JSONObject();
		try {
			Integer ruleType = null;
	        ChildItem childItem = new ChildItem();
	        Map<String,Object> obj = new HashMap<>();
	        if (!StringUtils.isEmpty(model.getCustomerPotentialID())){
	            obj.put("WHERE", " and( Status = 1 or (Status=2 and '1' not in  (select top 1 '1' as c   from  B_Opportunity where clueid='" + model.getCustomerPotentialID() + "' and ProjectID='" + model.getProjectID() + "')) )   and Isdel = 0 and ID ='" + model.getCustomerPotentialID() + "' and IntentProjectID='" + model.getProjectID() + "' order by Status ASC,CreateTime DESC");
	        }else{
	            obj.put("WHERE", " and( Status = 1 or (Status=2 and '1' not in  (select top 1 '1' as c   from  B_Opportunity where CustomerMobile='" + model.getMobile() + "' and ProjectID='" + model.getProjectID() + "' AND Status != 6)) )   and Isdel = 0 and CustomerMobile ='" + model.getMobile() + "' and IntentProjectID='" + model.getProjectID() + "' order by Status ASC,CreateTime DESC");
	        }
	        List<Map<String, Object>> optionList = ipadMapper.sCustomerPotentialClue(obj);
	        if (optionList!=null && optionList.size() > 0){//存在线索
	            List<OptionItem> ClueList = new ArrayList<OptionItem>();
	            Number RuleTypeN = (Number)optionList.get(0).get("RuleType");
	            int RuleType = RuleTypeN.intValue();
	            Number IsChooseN = (Number)optionList.get(0).get("IsChoose");
	            int IsChoose = IsChooseN.intValue();
	            ruleType = RuleType;
	            Boolean HasChoose = false;
	            if (RuleType == 1){//竞争带看规则线索
	                //增加自然到访
	                if (IsChoose!=1){
	                    HasChoose = true;
	                    OptionItem tOptionItem = new OptionItem("0390CD8C-D6D4-4C92-995B-08C7E18E6EC2", "自然访客", 1);
	                    ClueList.add(tOptionItem);
	                }
	            }
	            for (Map<String, Object> item : optionList){
	            	Number IsChooseN1 = (Number)item.get("IsChoose");
	                if (!HasChoose){
	                	HasChoose = IsChooseN1.intValue() == 1 ? true : false;
	                }
	                OptionItem tOptionItem = new OptionItem(item.get("ID").toString(), item.get("Name").toString(),IsChooseN1.intValue());
	                ClueList.add(tOptionItem);
	            }
	            if (HasChoose){
	                childItem.setValue(ClueList.get(0).getName());
	                childItem.setValueID(ClueList.get(0).getID());
	            }else{
	                childItem.setValue("");
	                childItem.setValueID("");
	                if (RuleType != 1){
	                    HasChoose = true;
	                    OptionItem tOptionItem = new OptionItem("0390CD8C-D6D4-4C92-995B-08C7E18E6EC2", "自然访客", 1);
	                    ClueList.set(0, tOptionItem);
	                }
	            }
	            childItem.setOption(ClueList);
	        }else{
	            ruleType = null;
	            List<OptionItem> ClueList = new ArrayList<OptionItem>();
	            ClueList.add(new OptionItem ("0390CD8C-D6D4-4C92-995B-08C7E18E6EC2", "自然访客", 1));
	            childItem.setOption(ClueList);
	        }
	        re.put("ruleType", ruleType);
	        re.put("childItem", childItem);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return re;
    }

	@Override
	public Result mLFCustomerDetail_Insert(JSONObject paramAry) {
		Result entity = new Result();
		entity.setErrcode(0);
        if (!StringUtils.isEmpty(paramAry.getString("FormSessionID"))){
        	
        	String formSessionID = paramAry.getString("FormSessionID");
        	Long RowCount = vCustomergwlistSelectMapper.mSystemFormSessionStatus_Select_count(formSessionID);
        	if(RowCount.intValue()==0){
        		vCustomergwlistSelectMapper.mSystemFormSessionStatus_Select_update(formSessionID);
        	}
            if (RowCount.intValue()> 0){
                entity.setErrcode(1);
                entity.setErrmsg("不能重复请求！");
                return entity;
            }
        }
        try{
            JSONArray itemList = new JSONArray();
            JSONObject mobileObj = new JSONObject();
            mobileObj.put("ID", "21685728-54C5-4268-8371-62413CE42841");
            mobileObj.put("Name", "电话");
            mobileObj.put("Value", paramAry.getString("CustomerMobile"));
            itemList.add(mobileObj);

            String customerName = paramAry.getString("CustomerName");;
            JSONObject nameObj = new JSONObject();
            nameObj.put("ID", "AEC5E071-B01F-4B41-942B-8BDC2112E06A");
            nameObj.put("Name", "姓名");
            nameObj.put("Value", customerName);
            itemList.add(nameObj);

            JSONObject firstNameObj = new JSONObject();
            firstNameObj.put("ID", "149F778D-4244-46F6-908F-D33A363A5B58");
            firstNameObj.put("Name", "姓");
            firstNameObj.put("Value", customerName);
            itemList.add(firstNameObj);

            JSONObject lastNameObj = new JSONObject();
            lastNameObj.put("ID", "7F298EE2-2B58-4C57-A0FF-FD2CDDF32BC6");
            lastNameObj.put("Name", "名");
            lastNameObj.put("Value", "");
            itemList.add(lastNameObj);

            JSONObject genderObj = new JSONObject();
            genderObj.put("ID", "E72C340D-4092-467A-9B8F-5138DBDCA43B");
            genderObj.put("Name", "性别");
            String CustomerGenderName = paramAry.getString("CustomerGender");
            genderObj.put("Value", "男".equals(CustomerGenderName) || "50827B18-5BCC-454C-B658-09AF4328D2A0".equals(CustomerGenderName) ? "50827B18-5BCC-454C-B658-09AF4328D2A0" : "EC3936F8-82DC-49AF-A8EB-153730359DE7");
            itemList.add(genderObj);

            JSONObject sourceObj = new JSONObject();
            sourceObj.put("ID", "F1725D6B-D1F7-4BC3-8C35-20FAB53A1602");
            sourceObj.put("Name", "渠道来源");
            sourceObj.put("Value",paramAry.getString("OpportunitySourceName"));
            itemList.add(sourceObj);

            JSONObject remarkObj = new JSONObject();
            remarkObj.put("ID", "4D35ABCF-E61C-4650-9F55-0D2D66548CF0");
            remarkObj.put("Name", "备注");
            remarkObj.put("Value",paramAry.getString("Remark"));
            itemList.add(remarkObj);

            JSONObject saleUserIDObj = new JSONObject();
            saleUserIDObj.put("ID", "1FA31185-3B00-47AA-8F5E-7AA3D53205A5");
            saleUserIDObj.put("Name", "置业顾问");
            saleUserIDObj.put("Value", paramAry.getString("SalesUserID"));
            itemList.add(saleUserIDObj);

            JSONObject visitAddressObj = new JSONObject();
            visitAddressObj.put("ID", "DC48D7A7-C086-408F-A68D-63C423A13F92");
            visitAddressObj.put("Name", "来访地点");
            visitAddressObj.put("Value", paramAry.getString("VisitAddress"));
            itemList.add(visitAddressObj);
            paramAry.put("ItemList", itemList);

            JSONObject CustomerObj = new JSONObject();
            CGWDetailModel model = JSONObject.parseObject(paramAry.toJSONString(),CGWDetailModel.class);
            if (model != null && model.getItemList() != null && model.getItemList().size() > 0){
                //初始化参数
            	JSONObject parameter = customerTemplate.GetParameters(model);
                parameter.put("VisitType","E0C5FDD1-800B-39F5-1A20-C0A5A3C3B450"); //默认为来访
                parameter.put("VisitTime",DateUtil.format(new Date(), "yyyy/MM/dd HH:mm:ss"));
                parameter.put("IsIPad","1");

                String OldSaleUserID = "";
                String SaleUserID = parameter.getString("SaleUserID");
                if (parameter.size() > 0) {
                    Boolean IsNew = true;
                    String sqlKey = "";
                    String Mobile = parameter.getString("Mobile");
                    if (!StringUtils.isEmpty(Mobile)){
                        if (!StringUtils.isEmpty(parameter.getString("ClueID"))){
                        	Map<String,Object> objClueStatus = ipadMapper.mCustomerClueStatus_Select(parameter.getString("ClueID"));
                        	Number StatusN= (Number)objClueStatus.get("Status");
                            if (objClueStatus.size() > 0 && objClueStatus.get("Status")!=null && StatusN.intValue() == 3){
                                entity.setErrcode(1);
                                entity.setErrmsg("不能分配无效客户!");
                                return entity;
                            }
                        }
                        JSONObject re_j = customerTemplate.CustomerOpportunityExist(model.getProjectID(), Mobile);
                        if (re_j.getBooleanValue("status")){//存在老机会_老客户 
                        	CustomerObj = re_j.getJSONObject("CustomerObj");
                            parameter.put("CustomerID",CustomerObj.getString("CustomerID"));
                            parameter.put("OpportunityID",CustomerObj.getString("OpportunityID"));
                            OldSaleUserID = CustomerObj.getString("SaleUserID");
                            parameter.put("LastName",CustomerObj.getString("LastName"));
                            parameter.put("FirstName",CustomerObj.getString("FirstName"));
                            if (!StringUtils.isEmpty(CustomerObj.getString("SaleUserID"))){
                                SaleUserID = CustomerObj.getString("SaleUserID");
                            }
                            String SalePartnerID = CustomerObj.getString("SalePartnerID"); //协作人ID
                            sqlKey = "mCustomerFJDetail_Update";
                            IsNew = false;
                            //开启异地销售时，老客户允许异地二次分配
                            Map<String,Object> proParam = new HashMap<String, Object>();
                            proParam.put("ID", model.getProjectID());
                            Map<String,Object> objProject = ipadMapper.Project_Detail_FindById(proParam);
                            Number numberIsLocationValidate =(Number)objProject.get("IsLocationValidate");
                            int IsLocationValidate = numberIsLocationValidate.intValue(); //是否开启地理位置验证 1.开启 0.不开启
                            Number numberIsOffSiteSale =(Number)objProject.get("IsOffSiteSale");
                            int IsOffSiteSale = numberIsOffSiteSale.intValue(); //是否开启异地销售 1.开启 0.不开启
                            String SaleGroupName = CustomerObj.getString("SaleGroupName"); //顾问分组
                            String FirstVisitAddress = CustomerObj.getString("FirstVisitAddress");//首访地址
                            String VisitAddress = parameter.getString("VisitAddress");//本来来访地址
                            
                            int isReAlloc = 0; //如果是老客户，是否允许二次分配 0否 1是
                            if (!StringUtils.isEmpty(paramAry.getString("VisitAddress"))){
                                //如果首访地址不为空，是否异地根据顾问所在团队判断
                                if ("".equals(FirstVisitAddress) && SaleGroupName.contains(VisitAddress.substring(0, VisitAddress.length() - 1))){
                                	//如果首访地址为空，并且客户到访本地案场
                                    isReAlloc = 0;
                                }
                                if (IsLocationValidate == 1 && IsOffSiteSale == 1 && "".equals(FirstVisitAddress) && !SaleGroupName.contains(VisitAddress.substring(0, VisitAddress.length() - 1)) && "".equals(SalePartnerID)){
                                	//如果首访地址为空，客户到访异地案场
                                    isReAlloc = 1;
                                }
                                if (FirstVisitAddress != null && !"".equals(FirstVisitAddress) && !FirstVisitAddress.equals(VisitAddress) && "".equals(SalePartnerID)){
                                	//如果首访地址不为空，是否异地根据首访地址判断
                                    isReAlloc = 1;
                                }
                                if (isReAlloc == 1){
                                    if (IsLocationValidate != 1){
                                        entity.setErrcode(11);
                                        entity.setErrmsg("未开启地理位置验证！");
                                        return entity;
                                    }
                                    if (IsOffSiteSale != 1){
                                        entity.setErrcode(12);
                                        entity.setErrmsg("未开启异地销售！");
                                        return entity;
                                    }
                                }
                            }
                            parameter.put("IsReAlloc",isReAlloc); 
                            //公共客户不允许分接跟进
                            if (!"XSZC".equals(paramAry.getString("JobCode"))){
                                if (OldSaleUserID.length() == 0){
                                    entity.setErrcode(1);
                                    entity.setErrmsg("不能跟进公共客户！");
                                    return entity;
                                }
                            }
                        }
                        else{//新机会
                        	JSONObject re_j_1 = customerTemplate.CustomerExist(Mobile);
                            if (!re_j_1.getBooleanValue("status")){//新机会_新客户
                                parameter.put("CustomerID",UUID.randomUUID().toString());
                                //1.根据手机号码查询拓客客户信息 
                                JSONObject re_j_2 = customerTemplate.CustomerPotentialExist(Mobile);
                                if (re_j_2.getBooleanValue("status")){//存在拓客客户信息
                                	CustomerObj = re_j_2.getJSONObject("CustomerObj");
                                    parameter.put("CardType",CustomerObj.getString("CardType"));
                                    parameter.put("CardID",CustomerObj.getString("CardID"));
                                    parameter.put("AcceptFactor",CustomerObj.getString("AcceptFactor"));
                                    parameter.put("AgeGroup",CustomerObj.getString("AgeGroup"));
                                    parameter.put("DomicilePlace",CustomerObj.getString("DomicilePlace"));
                                    parameter.put("HomeAddress",CustomerObj.getString("HomeAddress"));
                                    parameter.put("HomeArea",CustomerObj.getString("HomeArea"));
                                    parameter.put("WorkArea",CustomerObj.getString("WorkArea"));
                                    parameter.put("Marriage",CustomerObj.getString("Marriage"));
                                    parameter.put("Family",CustomerObj.getString("Family"));
                                    parameter.put("Industry",CustomerObj.getString("Industry"));
                                    parameter.put("PropertyNum",CustomerObj.getString("PropertyNum"));
                                }
                                sqlKey = "mCustomerFJDetail_Insert";
                            }else{//新机会_老客户
                            	CustomerObj = re_j_1.getJSONObject("CustomerObj");
                                parameter.put("CustomerID",CustomerObj.getString("CustomerID"));
                                JSONObject OldCustomerObj = customerTemplate.OpportunityInfo(model.getProjectID(), Mobile);
                                if (OldCustomerObj.size() > 0){//本项目存在历史机会,直接分配给之前的顾问
                                    parameter.put("LastName",OldCustomerObj.getString("LastName"));
                                    parameter.put("FirstName",OldCustomerObj.getString("FirstName"));
                                }
                                sqlKey = "mOldCustomerFJDetail_Insert";
                            }
                            parameter.put("OpportunityID",UUID.randomUUID().toString());
                            IsNew = true;
                        }
                        //销售轨迹类别
                        parameter.put("TrackType","BC2F967F-8FFE-1F52-49F6-CBCDFE8D044A");
                        //来电/来访
                        int status = "E0C5FDD1-800B-39F5-1A20-C0A5A3C3B450".equals(parameter.getString("VisitType")) ? 2 : 1;
                        parameter.put("Status",status);
                        if (IsNew){
                            //客户来源
                            String ClueID = "";
                            JSONObject OpporSource = GetOpportunitySource(parameter);
                            if(OpporSource.size()>0){
                            	ClueID = OpporSource.getString("clueID");
                            	parameter.put("OpportunitySource",  OpporSource.getString("opportunitySource"));
                            }
                            if (!StringUtils.isEmpty(ClueID)){
                                parameter.put("ClueID",ClueID);
                            }else{
                                parameter.put("ClueID","");
                            }
                        }
                        String IntentionLevel = CustomerObj.getString("CustomerLevel");
                        if (StringUtils.isEmpty(IntentionLevel)){
                            IntentionLevel = "FA35879A-CCE4-D332-0FAB-ADB57EBCAC9D";
                        }
                        String VisitType = parameter.getString("VisitType");
                        String FollwUpWay = "";
                        if ("E0C5FDD1-800B-39F5-1A20-C0A5A3C3B450".equals(VisitType)){//来访
                            FollwUpWay = "E30825AA-B894-4A5F-AF55-24CAC34C8F1F";
                        }else{//来电
                            FollwUpWay = "A79A1057-D4DC-497C-8C81-8F93E422C819";
                        }
                        String FollwUpType = CareerConsCustConverter.GetCustomerActionByFollowUpWay(FollwUpWay);
                        if (SaleUserID != null && !"".equals(SaleUserID) && SaleUserID.length() > 0){
                        	 parameter.put("Name", parameter.getString("LastName")+parameter.getString("FirstName"));
                             Map<String,Object> pmap =JSONObject.parseObject(parameter.toJSONString(), Map.class);
                             if (pmap.get("ClueID")==null){
                            	 pmap.put("ClueID","");
                             }
                             if(sqlKey.equals("mCustomerFJDetail_Update")){
                             	Map<String, Object> step1_map = vCustomerfjlistSelectMapper.mCustomerFJDetail_Update_step1(pmap);
                             	if(step1_map!=null && step1_map.size()>0){
                             		int Status = parameter.getIntValue("Status");
                                 	int IsIPad = parameter.getIntValue("IsIPad");
                                 	List<Integer> list = Arrays.asList(new Integer[]{1,2,3,4});
                                 	String tSaleUserID= step1_map.get("SaleUserID").toString();
                                 	Number numberStatus = (Number)step1_map.get("Status");
                                 	int tStatus = numberStatus.intValue();
                                 	if(Status==2 && list.contains(tStatus) && (tSaleUserID.equals("C4C09951-FA39-4982-AAD1-E72D9D4C3899") || tSaleUserID.equals(""))){
                                 		vCustomerfjlistSelectMapper.mCustomerFJDetail_Update_step2(pmap);
                                 	}
                                 	String tSalePartnerID = null;
                                 	String tFirstVisitAddress = step1_map.get("FirstVisitAddress").toString();
                                 	String tReVisitAddress = step1_map.get("ReVisitAddress").toString();
                                 	if(step1_map.get("SalePartnerID")!=null){
                                 		tSalePartnerID = step1_map.get("SalePartnerID").toString();
                                 	}
                                 	if(IsIPad==1 && Status==2 && tSalePartnerID==null && "".equals(tFirstVisitAddress) && "".equals(tReVisitAddress) && !StringUtils.isEmpty(paramAry.getString("VisitAddress")) && parameter.getIntValue("IsReAlloc")==0 ){
                                 		vCustomerfjlistSelectMapper.mCustomerFJDetail_Update_step3(pmap);
                                 	}
                                 	if(IsIPad==1 && Status==2 && !tSaleUserID.equals("C4C09951-FA39-4982-AAD1-E72D9D4C3899") && tSalePartnerID==null && "".equals(tReVisitAddress) && !tFirstVisitAddress.equals(paramAry.getString("VisitAddress")) && parameter.getIntValue("IsReAlloc")==1){
                                 		vCustomerfjlistSelectMapper.mCustomerFJDetail_Update_step4(pmap);
                                 	}
                                 	if("E0C5FDD1-800B-39F5-1A20-C0A5A3C3B450".equals(parameter.getString("VisitType"))){
                                 		pmap.put("CustomerRank", "ED0AD9E6-AF72-424C-9EE0-9884FF31FA42");
                                 		pmap.put("UpDownStatus", 1);
                                 		vCustomergwlistSelectMapper.P_OpportunityCustomerRank(pmap);
                                 	}
                                 	vCustomerfjlistSelectMapper.mCustomerFJDetail_Update_step5(pmap);
                             	}
                             	
                             }else if(sqlKey.equals("mCustomerFJDetail_Insert")){
                             	vCustomerfjlistSelectMapper.mCustomerFJDetail_Insert_step1(pmap);
                             	vCustomerfjlistSelectMapper.mCustomerFJDetail_Insert_step2(pmap);
                             	vCustomerfjlistSelectMapper.mCustomerFJDetail_Insert_step3(pmap);
                             	vCustomerfjlistSelectMapper.mCustomerFJDetail_Insert_step4(pmap);
                             	
                             	pmap.put("CustomerRank", "41FA0234-F8AE-434F-8BCD-6E9BE1D059DA");
                             	pmap.put("UpDownStatus", 1);
                             	vCustomergwlistSelectMapper.P_OpportunityCustomerRank(pmap);
                             	if("E0C5FDD1-800B-39F5-1A20-C0A5A3C3B450".equals(parameter.getString("VisitType"))){
                             		pmap.put("CustomerRank", "ED0AD9E6-AF72-424C-9EE0-9884FF31FA42");
                             		pmap.put("UpDownStatus", 1);
                             		vCustomergwlistSelectMapper.P_OpportunityCustomerRank(pmap);
                             	}
                             	vCustomerfjlistSelectMapper.mCustomerFJDetail_Insert_step5(pmap);
                             	pmap.put("ClueID","");
                             	vCustomergwlistSelectMapper.P_SyncClueOpportunity_Update(pmap);
                             	
                             }else if(sqlKey.equals("mOldCustomerFJDetail_Insert")){
                             	vCustomerfjlistSelectMapper.mOldCustomerFJDetail_Insert_step1(pmap);
                             	vCustomerfjlistSelectMapper.mOldCustomerFJDetail_Insert_step2(pmap);
                             	vCustomerfjlistSelectMapper.mOldCustomerFJDetail_Insert_step3(pmap);
                             	pmap.put("CustomerRank", "41FA0234-F8AE-434F-8BCD-6E9BE1D059DA");
                             	pmap.put("UpDownStatus", 1);
                             	vCustomergwlistSelectMapper.P_OpportunityCustomerRank(pmap);
                             	if("E0C5FDD1-800B-39F5-1A20-C0A5A3C3B450".equals(parameter.getString("VisitType"))){
                             		pmap.put("CustomerRank", "ED0AD9E6-AF72-424C-9EE0-9884FF31FA42");
                             		pmap.put("UpDownStatus", 1);
                             		vCustomergwlistSelectMapper.P_OpportunityCustomerRank(pmap);
                             	}
                             	vCustomerfjlistSelectMapper.mOldCustomerFJDetail_Insert_step4(pmap);
                             	pmap.put("ClueID","");
                             	vCustomergwlistSelectMapper.P_SyncClueOpportunity_Update(pmap);
                            }
                            Boolean result = true;
                            if (result){
                                if (IsNew){
                                	customerTemplate.ClueUpdate(parameter);
                                    if (!"C4C09951-FA39-4982-AAD1-E72D9D4C3899".equals(SaleUserID)){//不为无
                                    	//判断分配的置业顾问是否为无
                                        if (entity.getErrcode() == 0){
                                        	String UserID = parameter.getString("UserID");
                                            String ProjectID = parameter.getString("ProjectID");
                                            String BizID = parameter.getString("OpportunityID");
                                            String BizType = "Opportunity";
                                            String Subject = MessageType.分配待跟进.getTypeID();
                                            String Content = "客户" + parameter.getString("LastName") + parameter.getString("FirstName") + "、" +  parameter.getString("Mobile") + MessageType.到访提醒.getTypeID();
                                            String Receiver = parameter.getString("SaleUserID");
                                            iSystemMessageService.Detail_Insert(UserID, ProjectID, BizID, BizType, Subject, Content, Receiver,  MessageType.分配待跟进.getTypeID(), true);
                                        }
                                        String ClueID = parameter.getString("ClueID");
                                        //客户分配
                                        if(!StringUtils.isEmpty(ClueID)){
                                        	Map<String,Object> re_map_step1 = vCustomergwlistSelectMapper.RemindRuleAllotDetail_Select_step1(ClueID);
                                        	if(re_map_step1!=null && re_map_step1.size()>0){
                                        		String protectSource = String.valueOf(re_map_step1.get("ProtectSource"));
                                                String projectID = parameter.getString("ProjectID");
                                                Map<String,Object> re_map_step2 = vCustomergwlistSelectMapper.RemindRuleAllotDetail_Select_step2(projectID, protectSource);
                                                
                                                String ReportUserID = "";
                                                ClueID = "";
                                                
                                                Number AllotRemind = (Number)re_map_step2.get("AllotRemind");
                                                if(AllotRemind.intValue()>0){
                                                	if(re_map_step1.get("ReportUserID")!=null){
                                                		ReportUserID = String.valueOf(re_map_step1.get("ReportUserID"));
                                                	}
                                                	if(re_map_step1.get("ClueID")!=null){
                                                		ClueID = String.valueOf(re_map_step1.get("ClueID"));
                                                	}
                                                }
                                                if (!"".equals(ClueID) && !"".equals(ReportUserID)){
                                                	if (!SaleUserID.equals("C4C09951-FA39-4982-AAD1-E72D9D4C3899")){
                                                		String UserID = parameter.getString("UserID");
                                                        String ProjectID = parameter.getString("ProjectID");
                                                        String Content = "客户" + parameter.getString("LastName") + parameter.getString("FirstName") + "、" + parameter.getString("Mobile") + "(客户分配提醒)";
                                                        iSystemMessageService.Detail_Insert(UserID, ProjectID, ClueID, "Clue", "客户分配提醒", Content, ReportUserID, MessageType.系统通知.getTypeID(), true);
                                                	}
                                                }
                                        	}
                                        }
                                        //客户到访
                                        if ("售场接待".equals(FollwUpType)){//售场接待
                                        	String projectID = parameter.getString("ProjectID");
                                        	String tClueID = parameter.getString("ClueID");
                                        	String opportunityID = parameter.getString("OpportunityID");
                                            Map<String,Object> res = vCustomergwlistSelectMapper.RemindRuleArriveDetail_Select(opportunityID, tClueID);
                                            String reportUserID = "";
                                            String tprotectSource = "";
                                            if(res!=null && res.get("clueID")!=null){
                                            	tClueID = res.get("clueID").toString();
                                            }
                                            if(res!=null && res.get("reportUserID")!=null){
                                            	reportUserID = res.get("reportUserID").toString();
                                            }
                                            if(res!=null && res.get("protectSource")!=null){
                                            	tprotectSource = res.get("protectSource").toString();
                                            }
                                            if (!StringUtils.isEmpty(tClueID) &&  !StringUtils.isEmpty(reportUserID)){
                                            	String LastName = "";
                                            	String FirstName = "";
                                            	String tMobile = "";
                                            	if(!"".equals(tprotectSource)){
                                                	Map<String,Object> resf =vCustomergwlistSelectMapper.RemindRuleArriveDetail_Select_f(projectID, tprotectSource);
                                                	int customerVisitsRemind = 0;
                                                	if(resf!=null && resf.get("customerVisitsRemind")!=null){
                                                		Number customerVisitsRemindN = (Number)resf.get("customerVisitsRemind");
                                                		customerVisitsRemind = customerVisitsRemindN.intValue();
                                                	}
                                                	if(customerVisitsRemind>0){
                                                		Map<String,Object> ress = vCustomergwlistSelectMapper.RemindRuleArriveDetail_Select_s(tClueID);
                                                		if(ress!=null && ress.get("LastName")!=null){
                                                			LastName = ress.get("LastName").toString();
                                                		}
                                                		if(ress!=null && ress.get("FirstName")!=null){
                                                			FirstName = ress.get("FirstName").toString();
                                                		}
                                                		if(ress!=null && ress.get("Mobile")!=null){
                                                			tMobile = ress.get("Mobile").toString();
                                                		}
                                                	}
                                                }
                                                String UserID =paramAry.getString("UserID");
                                                String ProjectID = paramAry.getString("ProjectID");
                                                String Content = "客户" +LastName +FirstName + "、" + tMobile + "(" + MessageType.到访提醒.getTypeID()+ ")";
                                                Map<String,Object> parameter_1 = new HashMap<String,Object>();
                                                parameter_1.put("ProjectID", ProjectID);
                                                parameter_1.put("BizID", res.get("ClueID").toString());
                                                parameter_1.put("BizType", "Clue");
                                                parameter_1.put("Subject", "客户到访提醒");
                                                parameter_1.put("Content", Content);
                                                parameter_1.put("Receiver",res.get("ReportUserID").toString());
                                                parameter_1.put("MessageType",MessageType.带看通知.getTypeID());
                                                parameter_1.put("Sender", UserID);
                                                parameter_1.put("Creator", UserID);
                                                parameter_1.put("IsNeedPush", true);
                                                iSystemMessageService.SystemMessageDetail_Insert(parameter_1);
                                            }
                                        
                                        }
                                        
                                        String userID = parameter.getString("SaleUserID");
                                        Result Account = iSystemAccountService.SystemAccountDetail_Select(userID);
                                        String NewSaleUserName = "";
                                        if (Account.getErrcode() == 0){
                                        	JSONObject AccountData = (JSONObject) Account.getData();
                                            if (AccountData.size() > 0){
                                                NewSaleUserName = AccountData.getString("EmployeeName");
                                            }
                                        }
                                        JSONObject obj1 = new JSONObject();
                                        obj1.put("FollwUpType", "分配顾问");
                                        obj1.put("FollwUpTypeID", ActionType.分配顾问.getValue());
                                        obj1.put("SalesType", 1);
                                        obj1.put("NewSaleUserName", NewSaleUserName);
                                        obj1.put("OldSaleUserName", "");
                                        obj1.put("FollwUpUserID",parameter.getString("UserID"));
                                        obj1.put("FollwUpWay", "");
                                        obj1.put("FollowUpContent", "");
                                        obj1.put("IntentionLevel", "");
                                        obj1.put("OrgID",parameter.getString("OrgID"));
                                        obj1.put("FollwUpUserRole",parameter.getString("JobID"));
                                        obj1.put("OpportunityID", parameter.getString("OpportunityID"));
                                        obj1.put("ClueID", "");
                                        obj1.put("NextFollowUpDate", "");
                                        CustomerActionVo customerActionVo = JSONObject.parseObject(obj1.toJSONString(), CustomerActionVo.class);
                                        iVCustomergwlistSelectService.CustomerFollowUp_Insert(customerActionVo);
                                    }
                                    //增加跟进记录
                                    if (!StringUtils.isEmpty(FollwUpType)){
                                        JSONObject obj = new JSONObject();
                                        obj.put("FollwUpType", FollwUpType);
                                        obj.put("FollwUpTypeID", ActionType.valueOf(FollwUpType).getValue());
                                        obj.put("SalesType", 1);
                                        obj.put("NewSaleUserName", "");
                                        obj.put("OldSaleUserName", "");
                                        obj.put("FollwUpUserID",parameter.getString("UserID"));
                                        obj.put("FollwUpWay", FollwUpWay);
                                        obj.put("FollowUpContent", FollwUpType);
                                        obj.put("IntentionLevel", IntentionLevel);//默认D级
                                        obj.put("OrgID",parameter.getString("OrgID"));
                                        obj.put("FollwUpUserRole", parameter.getString("JobID"));
                                        obj.put("OpportunityID", parameter.getString("OpportunityID"));
                                        obj.put("ClueID", "");
                                        obj.put("NextFollowUpDate", "");
                                        CustomerActionVo customerActionVo = JSONObject.parseObject(obj.toJSONString(), CustomerActionVo.class);
                                        iVCustomergwlistSelectService.CustomerFollowUp_Insert(customerActionVo);
                                    }
                                    String opportunityID = parameter.getString("OpportunityID");
                                    String userID = parameter.getString("UserID");
                                    iVCustomergwlistSelectService.CustomerOpportunityFollowUpDetail_Update(opportunityID, userID);//客户机会跟进记录更新
                                }else{
                                    if (!"C4C09951-FA39-4982-AAD1-E72D9D4C3899".equals(SaleUserID)){//不为无
                                    	//判断分配的置业顾问是否为无
                                        if ("C4C09951-FA39-4982-AAD1-E72D9D4C3899".equals(OldSaleUserID)){//原顾问为无,新顾问不为无,发送分配待跟进消息和分配跟进记录
                                            if (entity.getErrcode() == 0){
                                                String UserID = parameter.getString("UserID");
                                                String ProjectID = parameter.getString("ProjectID");
                                                String BizID = parameter.getString("OpportunityID");
                                                String BizType = "Opportunity";
                                                String Subject = MessageType.分配待跟进.getTypeID();
                                                String Content = "客户" + parameter.getString("LastName") + parameter.getString("FirstName") + "、" + parameter.getString("Mobile") + MessageType.到访提醒.getTypeID();
                                                String Receiver = parameter.getString("SaleUserID");
                                                iSystemMessageService.Detail_Insert(UserID, ProjectID, BizID, BizType, Subject, Content, Receiver, MessageType.分配待跟进.getTypeID(), true);
                                            }
                                            //客户分配
                                            String ClueID = parameter.getString("ClueID");
                                            if(!StringUtils.isEmpty(ClueID)){
                                                Map<String,Object> re_map_step1 = vCustomergwlistSelectMapper.RemindRuleAllotDetail_Select_step1(ClueID);
                                                String protectSource = String.valueOf(re_map_step1.get("ProtectSource"));
                                                String projectID = parameter.getString("ProjectID");
                                                Map<String,Object> re_map_step2 = vCustomergwlistSelectMapper.RemindRuleAllotDetail_Select_step2(projectID, protectSource);
                                                String ReportUserID = "";
                                                ClueID = "";
                                                Number AllotRemindN = (Number)re_map_step2.get("AllotRemind");
                                                if(AllotRemindN.intValue()>0){
                                                	if(re_map_step1.get("ReportUserID")!=null){
                                                		ReportUserID = String.valueOf(re_map_step1.get("ReportUserID"));
                                                	}
                                                	if(re_map_step1.get("ClueID")!=null){
                                                		ClueID = String.valueOf(re_map_step1.get("ClueID"));
                                                	}
                                                }
                                                if (!"".equals(ClueID) && !"".equals(ReportUserID)){
                                                	if (!SaleUserID.equals("C4C09951-FA39-4982-AAD1-E72D9D4C3899")){
                                                		String UserID = parameter.getString("UserID");
                                                        String ProjectID = parameter.getString("ProjectID");
                                                        String Content = "客户" + parameter.getString("LastName") + parameter.getString("FirstName") + "、" + parameter.getString("Mobile") + "(客户分配提醒)";
                                                        iSystemMessageService.Detail_Insert(UserID, ProjectID, ClueID, "Clue", "客户分配提醒", Content, ReportUserID, MessageType.系统通知.getTypeID(), true);
                                                	}
                                                }
                                            }
                                            
                                            String userID = parameter.getString("SaleUserID");
                                            Result Account = iSystemAccountService.SystemAccountDetail_Select(userID);
                                            String NewSaleUserName = "";
                                            if (Account.getErrcode() == 0){
                                            	JSONObject AccountData = (JSONObject) Account.getData();
                                                if (AccountData.size() > 0){
                                                    NewSaleUserName =AccountData.getString("EmployeeName");
                                                }
                                            }
                                            JSONObject obj1 = new JSONObject();
                                            obj1.put("FollwUpType", "分配顾问");
                                            obj1.put("FollwUpTypeID", ActionType.分配顾问.getValue());
                                            obj1.put("SalesType", 1);
                                            obj1.put("NewSaleUserName", NewSaleUserName);
                                            obj1.put("OldSaleUserName", "");
                                            obj1.put("FollwUpUserID",parameter.getString("UserID"));
                                            obj1.put("FollwUpWay", "");
                                            obj1.put("FollowUpContent", "");
                                            obj1.put("IntentionLevel", "");
                                            obj1.put("OrgID", parameter.getString("OrgID"));
                                            obj1.put("FollwUpUserRole",parameter.getString("JobID"));
                                            obj1.put("OpportunityID", parameter.getString("OpportunityID"));
                                            obj1.put("ClueID", "");
                                            obj1.put("NextFollowUpDate", "");
                                            CustomerActionVo customerActionVo = JSONObject.parseObject(obj1.toJSONString(), CustomerActionVo.class);
                                            iVCustomergwlistSelectService.CustomerFollowUp_Insert(customerActionVo);
                                        }else{
                                            if ("E30825AA-B894-4A5F-AF55-24CAC34C8F1F".equals(FollwUpWay)){
                                            	if (entity.getErrcode() == 0 && "1".equals(model.getIsSend())){
                                                    String UserID = parameter.getString("UserID");
                                                    String ProjectID = parameter.getString("ProjectID");
                                                    String BizID = parameter.getString("OpportunityID");
                                                    String BizType = "Opportunity";
                                                    String Subject = MessageType.到访提醒.getTypeID();
                                                    String Content = "客户" + parameter.getString("LastName") + parameter.getString("FirstName") + "、" + parameter.getString("Mobile") + MessageType.到访提醒.getTypeID();
                                                    String Receiver = parameter.getString("SaleUserID");
                                                    iSystemMessageService.Detail_Insert(UserID, ProjectID, BizID, BizType, Subject, Content, Receiver, MessageType.到访提醒.getTypeID(), true);
                                                }
                                                //客户到访
                                                String ClueID = parameter.getString("ClueID");
                                                if(!StringUtils.isEmpty(ClueID)){
                                                    Map<String,Object> re_map_step1 = vCustomergwlistSelectMapper.RemindRuleAllotDetail_Select_step1(ClueID);
                                                    String protectSource = String.valueOf(re_map_step1.get("ProtectSource"));
                                                    String projectID = parameter.getString("ProjectID");
                                                    Map<String,Object> re_map_step2 = vCustomergwlistSelectMapper.RemindRuleAllotDetail_Select_step2(projectID, protectSource);
                                                    String ReportUserID = "";
                                                    ClueID = "";
                                                    Number AllotRemindN = (Number)re_map_step2.get("AllotRemind");
                                                    if(AllotRemindN.intValue()>0){
                                                    	if(re_map_step1.get("ReportUserID")!=null){
                                                    		ReportUserID = String.valueOf(re_map_step1.get("ReportUserID"));
                                                    	}
                                                    	if(re_map_step1.get("ClueID")!=null){
                                                    		ClueID = String.valueOf(re_map_step1.get("ClueID"));
                                                    	}
                                                    }
                                                    if (!"".equals(ClueID) && !"".equals(ReportUserID)){
                                                    	String UserID = parameter.getString("UserID");
                                                        String ProjectID = parameter.getString("ProjectID");
                                                        String Content = "客户" + parameter.getString("LastName") + parameter.getString("FirstName") + "、" + parameter.getString("Mobile") + MessageType.到访提醒.getTypeID();
                                                        iSystemMessageService.Detail_Insert(UserID, ProjectID, ClueID, "Clue", "客户分配提醒", Content, ReportUserID, MessageType.带看通知.getTypeID(), true);
                                                    }
                                                }
                                            }
                                            //增加跟进记录
                                            if (!StringUtils.isEmpty(FollwUpType)){
                                            	JSONObject obj = new JSONObject();
                                                obj.put("FollwUpType", FollwUpType);
                                                obj.put("FollwUpTypeID", ActionType.valueOf(FollwUpType).getValue());
                                                obj.put("SalesType", 1);
                                                obj.put("NewSaleUserName", "");
                                                obj.put("OldSaleUserName", "");
                                                obj.put("FollwUpUserID", parameter.getString("UserID"));
                                                obj.put("FollwUpWay", FollwUpWay);
                                                obj.put("FollowUpContent", FollwUpType);
                                                obj.put("IntentionLevel", IntentionLevel);//默认D级
                                                obj.put("OrgID", parameter.getString("OrgID"));
                                                obj.put("FollwUpUserRole", parameter.getString("JobID"));
                                                obj.put("OpportunityID", parameter.getString("OpportunityID"));
                                                obj.put("ClueID", "");
                                                obj.put("NextFollowUpDate", "");
                                                CustomerActionVo customerActionVo = JSONObject.parseObject(obj.toJSONString(), CustomerActionVo.class);
                                                iVCustomergwlistSelectService.CustomerFollowUp_Insert(customerActionVo);
                                            }
                                            String opportunityID = parameter.getString("OpportunityID");
                                            String userID = parameter.getString("UserID");
                                            iVCustomergwlistSelectService.CustomerOpportunityFollowUpDetail_Update(opportunityID, userID);//客户机会跟进记录更新
                                        }
                                    }
                                }
                            }else{
                                entity.setErrcode(1);
                    	        entity.setErrmsg("失败");
                            }
                        }else{
                            entity.setErrcode(1);
                	        entity.setErrmsg("参数缺失！");
                        }
                    }else{
                        entity.setErrcode(1);
            	        entity.setErrmsg("手机号码为空！");
                    }
                }else{
                    entity.setErrcode(1);
        	        entity.setErrmsg("参数格式错误！");
                }

            }else{
                entity.setErrcode(1);
    	        entity.setErrmsg("参数不完整！");
            }
        }catch (Exception e){
        	entity.setErrcode(1);
	        entity.setErrmsg("服务器异常！");
            throw e;
        }
        if (entity.getErrcode() == 0){
            Map<String,Object> param = new HashMap<>();
            param.put("Mobile",paramAry.getString("CustomerMobile"));
            param.put("ProjectID",paramAry.getString("ProjectID"));
            param.put("SiteUrl", SiteUrl);
            List<Map<String,Object>> lFCustomerDetailObj = ipadMapper.mLFCustomerDetailByMobile_Select(param);
            Map<String,Object> data  = lFCustomerDetailObj.get(0);
            String dataStr = new JSONObject(data).toJSONString();
            JSONObject data_re = JSONObject.parseObject(dataStr);
            entity.setData(data_re);
        }
        return entity;
	}
	
	
	public JSONObject GetOpportunitySource(JSONObject parameter){
		JSONObject re = new JSONObject();
		try {
			JSONObject CustomerObj = new JSONObject();
			JSONObject re_j = customerTemplate.CustomerOpportunityExist(parameter.getString("ProjectID"), parameter.getString("Mobile"));
			if(re_j.getBooleanValue("status")){
				CustomerObj = re_j.getJSONObject("CustomerObj");
			}
	        CSearchModelVo model = JSONObject.parseObject(parameter.toJSONString(),CSearchModelVo.class);
	        model.setCustomerPotentialID(CustomerObj.getString("CustomerPotentialID"));
	        String OpportunitySource = "";
	        JSONObject reSource = GetClueOpportunitySource(model);
	        ChildItem clueOpportunitySource = null;
	        if(reSource!=null && reSource.size()>0){
	        	clueOpportunitySource = (ChildItem) reSource.get("childItem");//获取来源
	        }
	        if (clueOpportunitySource != null && clueOpportunitySource.getOption().size() > 0){
	            OpportunitySource = clueOpportunitySource.getOption().get(0).getID();
	        }
	        String ClueID = "";
	        String opportunitySourceID = "";
	        List<OptionItem> option =customerTemplate.GetOptionList("F1725D6B-D1F7-4BC3-8C35-20FAB53A1602", true);
	        Boolean hasCount = false;
	        for(OptionItem item : option){
	        	if(item.getID().equals(OpportunitySource)){
	        		hasCount = true;
	        		break;
	        	}
	        }
	        if (StringUtils.isEmpty(OpportunitySource) || "自然访客".equals(OpportunitySource)){
	        	//自然访客
	            opportunitySourceID = "0390CD8C-D6D4-4C92-995B-08C7E18E6EC2";
	        }else if (hasCount){//存在客户来源ID
	            opportunitySourceID = OpportunitySource;
	        }else{
	            Map<String,Object> obj = new HashMap<>();
	            obj.put("WHERE", " and  Status in (1,2) and Isdel = 0 and ClueID ='" + OpportunitySource + "'");
	            List<Map<String, Object>>  clueInfo = ipadMapper.sCustomerPotentialClue(obj);
	            if (clueInfo.size() == 0){//未找到线索则渠道仍然是自然来访
	                opportunitySourceID = "0390CD8C-D6D4-4C92-995B-08C7E18E6EC2";
	            }else{//找到线索则判断线索的渠道
	                ClueID = OpportunitySource;
	                if(clueInfo.get(0).get("AdviserGroupID")!=null){
	                	opportunitySourceID = CareerConsCustConverter.GetOpportunitySourceByAdviserGroup(clueInfo.get(0).get("AdviserGroupID").toString());
	                }
	                if (StringUtils.isEmpty(opportunitySourceID)){
	                	//自然访客
	                    opportunitySourceID = "0390CD8C-D6D4-4C92-995B-08C7E18E6EC2";
	                }
	            }
	        }
	        re.put("clueID", ClueID);
	        re.put("opportunitySourceID", opportunitySourceID);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return re;
	}

	@Override
	public Result mLFCustomerNeedFPList_Select(JSONObject paramAry) {
		Result re = new Result();
        CPageModel model = JSONObject.parseObject(paramAry.toJSONString(),CPageModel.class);
        StringBuilder whereSb = new StringBuilder();
        if (!StringUtils.isEmpty(model.getKeyWord())){
            whereSb.append(" AND (CustomerName LIKE '%"+model.getKeyWord()+"%' OR CustomerMobile LIKE '%"+model.getKeyWord()+"%' OR SpareMobile Like '%"+model.getKeyWord()+"%')");
        }
        String sourceType = paramAry.getString("SourceType");
        if (!StringUtils.isEmpty(sourceType)){
            if ("1".equals(sourceType)) {//分销中介
                whereSb.append(" AND SourceType = 'E4DFA1D5-95F9-4D89-B754-E7CC81D58196'");
            }
            if ("2".equals(sourceType)){//员工推荐
                whereSb.append(" AND SourceType = 'BA06AE1D-E29A-4BC7-A811-A26E103B5E7E'");
            }
            if ("3".equals(sourceType)){ //自由经纪
                whereSb.append(" AND SourceType = '798A45A6-9169-4E5C-BEE3-1CDB158F5D69'");
            }
            if ("4".equals(sourceType)){//自有渠道
                whereSb.append(" AND (SourceType = '80D2A7B1-115A-4F3A-BB7D-F227F641C5F1' " +
                    "OR SourceType = '266E0F4F-2EE1-4305-9115-49DDE2186D57'" +
                    "OR SourceType = 'B32BB4EC-74C5-4F7C-BF85-F9A02452B8A2'" +
                    "OR SourceType = '8FDFDE89-E1F1-43DE-9094-92D77B22FC1F'" +
                    "OR SourceType = 'C07D5987-ACDD-40B8-9CBD-6257AA59C88C'" +
                    "OR SourceType = '709CD8F1-7E1A-42B9-B4E9-6EAFB428EAEF' " +
                    "OR SourceType = '8FDFDE89-E1F1-43DE-9094-92D77B22FC1F')");
            }
            if ("5".equals(sourceType)){//老业主推荐
                whereSb.append(" AND SourceType = '7F4E0089-E21D-0F97-DC48-0DBF0740367D'");
            }
        }
        paramAry.put("WHERE", whereSb.toString());
        //待分配列表
        Map<String,Object> pmap = JSONObject.parseObject(paramAry.toJSONString(),Map.class);
        List<Map<String, Object>> pageObj = ipadMapper.mLFCustomerNeedFPList_Select(pmap);
        Long AllCount = ipadMapper.mLFCustomerNeedFPList_Select_count(pmap);
        if (pageObj != null &&  pageObj.size() > 0){
            re.setErrcode(0);
            re.setErrmsg("成功");
            JSONObject j_data = new JSONObject();
        	j_data.put("List", pageObj);
        	j_data.put("AllCount", AllCount);
        	j_data.put("PageSize", paramAry.getInteger("PageSize"));
        	re.setData(j_data);
            return re;
        }
        re.setErrcode(1);
        re.setErrmsg("暂无数据");
        return re;
	}

	@Override
	public Result mLFReceptRecordList_Select(JSONObject paramAry) {
		Result re = new Result();
		CPageModel model = JSONObject.parseObject(paramAry.toJSONString(),CPageModel.class);
        StringBuilder whereSb = new StringBuilder();
        if (!StringUtils.isEmpty(model.getKeyWord())){
            whereSb.append(" AND (CASE WHEN ca.IsOld = 0 OR ISNULL(o.SalePartnerID,'') = '' THEN su.Name ELSE su1.Name END) LIKE '%"+model.getKeyWord()+"%'");
        }
        if (!StringUtils.isEmpty(paramAry.getString("ReceptTime"))){
            String time = paramAry.getString("ReceptTime");
            whereSb.append(" AND CONVERT(NVARCHAR(10),ca.VisitTime,111) = '"+time+"'");
        }
        paramAry.put("WHERE", whereSb.toString());
        paramAry.put("SiteUrl", SiteUrl);
        //接待记录列表
        Map<String,Object> pmap = JSONObject.parseObject(paramAry.toJSONString(),Map.class);
        IPage<Map<String,Object>> page =new Page<Map<String,Object>>();
    	page.setSize(paramAry.getLongValue("PageSize"));
    	page.setCurrent(paramAry.getLongValue("PageIndex"));
        IPage<Map<String, Object>> pageObj = ipadMapper.mLFReceptRecordList_Select(page,pmap);
        if (pageObj != null && pageObj.getRecords()!=null && pageObj.getRecords().size() > 0){
            re.setErrcode(0);
            re.setErrmsg("成功");
            JSONObject j_data = new JSONObject();
        	j_data.put("List", pageObj.getRecords());
        	j_data.put("AllCount", pageObj.getTotal());
        	j_data.put("PageSize", pageObj.getSize());
        	re.setData(j_data);
            return re;
        }
        re.setErrcode(1);
        re.setErrmsg("暂无数据");
        return re;
	}

	@Override
	public Result mLFReceptRecordCustomerList_Select(JSONObject paramAry) {
		Result re = new Result();
		CPageModel model = JSONObject.parseObject(paramAry.toJSONString(),CPageModel.class);
        StringBuilder whereSb = new StringBuilder();
        if (!StringUtils.isEmpty(model.getKeyWord())){
            whereSb.append(" AND (o.CustomerName LIKE '%"+model.getKeyWord()+"%' OR c.Mobile LIKE '%"+model.getKeyWord()+"%')");
        }
        if (!StringUtils.isEmpty(paramAry.getString("ReceptTime"))){
            String time = paramAry.getString("ReceptTime");
            whereSb.append(" AND CONVERT(NVARCHAR(10),ca.VisitTime,120) = '"+time+"'");
        }
        paramAry.put("WHERE", whereSb.toString());
        paramAry.put("SiteUrl", SiteUrl);
        //接待记录列表
        Map<String,Object> pmap = JSONObject.parseObject(paramAry.toJSONString(),Map.class);
        IPage<Map<String,Object>> page =new Page<Map<String,Object>>();
    	page.setSize(paramAry.getLongValue("PageSize"));
    	page.setCurrent(paramAry.getLongValue("PageIndex"));
        IPage<Map<String, Object>> pageObj = ipadMapper.mLFReceptRecordCustomerList_Select(page,pmap);
        if (pageObj != null && pageObj.getRecords()!=null && pageObj.getRecords().size() > 0){
            JSONObject j_data = new JSONObject();
        	j_data.put("List", pageObj.getRecords());
        	j_data.put("AllCount", pageObj.getTotal());
        	j_data.put("PageSize", pageObj.getSize());
        	re.setErrcode(0);
            re.setErrmsg("成功");
        	re.setData(j_data);
            return re;
        }
        re.setErrcode(1);
        re.setErrmsg("暂无数据");
        return re;
	}

	@Override
	public Result mLFReceptRecordList_Select_forSaleUser(JSONObject paramAry) {
		Result re = new Result();
        StringBuilder whereSb = new StringBuilder();
        if (!StringUtils.isEmpty(paramAry.getString("BeginReceptTime")) && !StringUtils.isEmpty(paramAry.getString("EndReceptTime")) ){
            String begintime = paramAry.getString("BeginReceptTime");
            String endtime = paramAry.getString("EndReceptTime");
            whereSb.append(" AND CONVERT(NVARCHAR(10),ca.VisitTime,111) BETWEEN '"+begintime+"' AND '"+endtime+"'");
        }
        paramAry.put("WHERE", whereSb.toString());
        paramAry.put("SiteUrl", SiteUrl);
        //接待记录列表
        Map<String,Object> pmap = JSONObject.parseObject(paramAry.toJSONString(),Map.class);
        List<Map<String, Object>> pageObj = ipadMapper.mLFReceptRecordList_Select_forSaleUser(pmap);
        
        Map<String,List<Map<String, Object>>> re_data = new HashMap<>();
        if (pageObj != null && pageObj.size() > 0){
        	for(Map<String, Object> map : pageObj){
        		if(re_data.containsKey(map.get("GroupName").toString())){
        			re_data.get(map.get("GroupName").toString()).add(map);
        		}else{
        			List<Map<String, Object>> m = new ArrayList<Map<String,Object>>();
        			m.add(map);
        			re_data.put(map.get("GroupName").toString(), m);
        		}
        	}
        	JSONArray re_ja = new JSONArray();
        	for(Map.Entry<String,List<Map<String, Object>>> entry : re_data.entrySet()){
        		JSONObject json_data = new JSONObject();
        		String mapKey = entry.getKey();
        		List<Map<String, Object>> mapValue = entry.getValue();
        		json_data.put("Type", mapKey);
        		json_data.put("Data", mapValue);
        		re_ja.add(json_data);
        	}
            re.setErrcode(0);
            re.setErrmsg("成功");
        	re.setData(re_ja);
            return re;
        }
        re.setErrcode(1);
        re.setErrmsg("暂无数据");
        return re;
	}

	@Override
	public Result addSaleUserSign(JSONObject paramAry) {
		Result re = new Result();
		try {
			Map<String,Object> pmap = new HashMap<>();
			pmap.put("ID", UUID.randomUUID().toString());
			pmap.put("SalesSupervisorID", paramAry.getString("UserID"));
			pmap.put("SaleUserID", paramAry.getString("SaleUserID"));
			String SignInDate = DateUtil.format(new Date(), "yyyy-MM-dd");
			pmap.put("SignInDate", SignInDate);
			Map<String, Object> data = ipadMapper.selectByDateAndSaleUserID(pmap);
			if(data!=null && data.size()>0){
				re.setErrcode(1);
		        re.setErrmsg("不可重复签到");
		        return re;
			}
			Long maxSortCode = ipadMapper.getMaxSortByDate(SignInDate);
			pmap.put("SortCode", maxSortCode.intValue()+1);
			ipadMapper.addSaleUserSign(pmap);
			re.setErrcode(0);
            re.setErrmsg("成功");
		} catch (Exception e) {
			re.setErrcode(1);
	        re.setErrmsg("系统异常");
			e.printStackTrace();
		}
		return re;
	}

	@Override
	public Result mLFCustomerGWList_Select_Sort(JSONObject paramAry) {
		Result entity = new Result();
        if (paramAry==null || paramAry.size() == 0){
            entity.setErrcode(1);
            entity.setErrmsg("参数错误");
            return entity;
        }try{
            CPageModel model = JSONObject.parseObject(paramAry.toJSONString(), CPageModel.class);
            StringBuilder whereSb = new StringBuilder();
            if (!StringUtils.isEmpty(model.getKeyWord())){
                whereSb.append(" and (Name LIKE '%"+model.getKeyWord()+"%' OR GroupName LIKE '%"+model.getKeyWord()+"%'  OR Mobile LIKE '%"+model.getKeyWord()+"%')");
            }
            paramAry.put("WHERE", whereSb.toString());
            paramAry.put("SiteUrl", SiteUrl);
            //顾问列表
            Map<String,Object> pmap = JSONObject.parseObject(paramAry.toJSONString(),Map.class);
            List<Map<String, Object>> allGwJArray = vCustomerfjlistSelectMapper.sCustomerFJAdviserList_Select_Sort(pmap);
          
            entity.setData(allGwJArray);
            entity.setErrcode(0);
            entity.setErrmsg("成功");
        }catch (Exception e){
            entity.setErrcode(1);
            entity.setErrmsg("服务器异常");
            e.printStackTrace();
        }
        return entity;
	}
	
	/**
	 * 报备成功更新排序顺序
	 * @param SalesSupervisorID
	 * @param SaleUserID
	 */
	@Override
	public void updateSortCodeAndTime(String SalesSupervisorID,String SaleUserID){
		Map<String,Object> pmap = new HashMap<>();
		pmap.put("SalesSupervisorID", SalesSupervisorID);
		pmap.put("SaleUserID", SaleUserID);
		String SignInDate = DateUtil.format(new Date(), "yyyy-MM-dd");
		pmap.put("SignInDate", SignInDate);
		Map<String, Object> data = ipadMapper.selectByDateAndSaleUserID(pmap);
		if(data!=null && data.size()>0){
			Long maxSortCode = ipadMapper.getMaxSortByDate(SignInDate);
			pmap.put("SortCode", maxSortCode.intValue()+1);
			ipadMapper.updateSortCodeAndTime(pmap);
		}
	}

	@Override
	public Result sortSaleUser(String saleUserIDs,String userID) {
		Result re = new Result();
		try {
			if(StringUtils.isEmpty(userID)){
				re.setErrcode(1);
	            re.setErrmsg("用户ID为空");
	            return re;
			}
			if(StringUtils.isEmpty(saleUserIDs)){
				re.setErrcode(1);
	            re.setErrmsg("置业顾问ID为空");
	            return re;
			}
			Map<String,Object> pmap = new HashMap<>();
			pmap.put("SalesSupervisorID", userID);
			String SignInDate = DateUtil.format(new Date(), "yyyy-MM-dd");
			pmap.put("SignInDate", SignInDate);
			String[] saleUserIDGroup = saleUserIDs.split(",");
			int index = 1;
			for(String saleUserID : saleUserIDGroup){
				pmap.put("SaleUserID", saleUserID);
				pmap.put("SortCode", index);
				ipadMapper.updateSortCode(pmap);
				index++;
			}
			re.setErrcode(0);
            re.setErrmsg("成功");
		} catch (Exception e) {
			re.setErrcode(1);
            re.setErrmsg("系统异常");
			e.printStackTrace();
		}
		return re;
	}
	
	

}
