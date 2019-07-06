package com.tahoecn.xkc.service.customer.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.common.enums.CustomerModeType;
import com.tahoecn.xkc.common.utils.JSONUtil;
import com.tahoecn.xkc.mapper.customer.VOpportunityMapper;
import com.tahoecn.xkc.mapper.dict.SDictionaryMapper;
import com.tahoecn.xkc.mapper.project.BProjectMapper;
import com.tahoecn.xkc.model.customer.VOpportunity;
import com.tahoecn.xkc.model.dict.SDictionary;
import com.tahoecn.xkc.model.project.BProject;
import com.tahoecn.xkc.model.vo.CSearchModel;
import com.tahoecn.xkc.model.vo.ChildItem;
import com.tahoecn.xkc.model.vo.CustomerModel;
import com.tahoecn.xkc.model.vo.DicInfo;
import com.tahoecn.xkc.model.vo.OptionItem;
import com.tahoecn.xkc.model.vo.PanelItem;
import com.tahoecn.xkc.service.customer.IVOpportunityService;

import springfox.documentation.spring.web.json.Json;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-04
 */
@Service
public class VOpportunityServiceImpl extends ServiceImpl<VOpportunityMapper, VOpportunity> implements IVOpportunityService {

	@Autowired
	private VOpportunityMapper vOpportunityMapper;
	@Autowired
	private BProjectMapper bProjectMapper;
	@Autowired
	private SDictionaryMapper sDictionaryMapper;
	/**
	 * 机会信息
	 */
	@Override
	public List<Map<String,Object>> OpportunityInfo(String opportunityID) {
		Map<String,Object> pram = new HashMap<String,Object>();
		pram.put("sqlWhere", "  AND ID ='" + opportunityID + "' ");
		return vOpportunityMapper.OpportunityDetail_Select(pram);
	}
	/**
	 * 初始化客户动态结构数据
	 */
	@Override
	public CustomerModel InitCustomerModeData(CSearchModel model, String jsonFileName, Map<String,Object> customerObj,
			String customerModeType) {
		CustomerModel customerModel = null;
		//1.获取模板数据结构
        String jsonMap = JSONUtil.readJsonFile(jsonFileName);
        customerModel = JSON.parseObject(JSON.toJSONString(jsonMap), CustomerModel.class);
		//2.初始化属性值
        if(customerModel != null){
			//3.获取字典
        	String dictMap = JSONUtil.readJsonFile("CustomerDic.json");
        	List<DicInfo> dicList = (List<DicInfo>) JSON.parseObject(JSON.toJSONString(jsonMap), CustomerModel.class);
        	//4.初始化值
        	if(customerObj != null){
        		customerModel.setCustomerID(customerObj.get("CustomerID").toString());
                customerModel.setOpportunityID(customerObj.get("OpportunityID").toString());
                customerModel.setClueID(customerObj.get("ClueID").toString());
                model.setCustomerPotentialID("CustomerPotentialID");
                //4.1初始化字典值
                String fieldKey = "";
                for(DicInfo item : dicList){
                    fieldKey = "Option".equals(item.getType()) || "OptionRadio".equals(item.getType()) ? item.getFieldName() + "Name" : item.getFieldName();
                    item.setValue(customerObj.get(fieldKey).toString());
                    item.setValueID(customerObj.get(item.getFieldName().replace("\r\n  ", "").replace("\r", "").replace("\n", "")).toString());
                }
        	}
        	//4.2查询当前项目信息
        	BProject project = Detail_FindById(model.getProjectID());
            int IsNoAllotRole = project.getIsNoAllotRole();
            int IsNoMobileVerify = project.getIsNoMobileVerify();
            int IsNoCustomerRank = project.getIsNoCustomerRank();
            String jobCode = model.getJobCode();
        	//5.初始化结构值
            DicInfo dicInfo = null;
            int panelIndex = 0;
            List<Map<String, Object>> deleteItem = new ArrayList<Map<String, Object>>();
            for (PanelItem panelItem : customerModel.getPanel()){
            	int childIndex = 0;
            	for (ChildItem childItem : panelItem.getChild()){
            		//处理选项项目
            		if (childItem.Type == "Option" || childItem.Type == "OptionTag" || childItem.Type == "OptionCity" || childItem.Type == "OptionRadio" || childItem.Type == "OptionTextArea"){
            			//初始化option值
            			switch (childItem.getID()){
            			case "7B44EDBA-FCB7-4040-A5FF-DDED0F01C76D":{//意向项目
                            childItem.setOption(new ArrayList<OptionItem>());
                            Map<String,Object> paramAry = new HashMap<String,Object>();
                            paramAry.put("sqlWhere", " and ID='" + model.getProjectID().toString() + "' and level='1'");
                            List<Map<String,Object>> proList = vOpportunityMapper.sProject_Select(paramAry);//项目列表
                            List<OptionItem> optionList = new ArrayList<OptionItem>();
                            for(Map<String,Object> m : proList){
                            	OptionItem e = new OptionItem();
                            	e.setID(m.get("ID").toString());
                            	e.setName(m.get("Name").toString());
                            	optionList.add(e);
                            }
                            if (proList != null && proList.size() > 0){
                                childItem.setValue(proList.get(0).get("Name").toString());
                                childItem.setValueID(proList.get(0).get("ID").toString());
                                childItem.setOption(optionList);
                            }
                        }
                        break;
            			case "BFD616A1-118F-4C9F-986F-BAA32A1A7EA3":{//意向项目分期
            				childItem.setOption(new ArrayList<OptionItem>());
            				Map<String,Object> paramAry = new HashMap<String,Object>();
                            paramAry.put("sqlWhere", " and PID='" + model.getProjectID().toString() + "' and level='2'");
                            List<Map<String,Object>> proList = vOpportunityMapper.sProject_Select(paramAry);
                            List<OptionItem> optionList = new ArrayList<OptionItem>();
                            for(Map<String,Object> m : proList){
                            	OptionItem e = new OptionItem();
                            	e.setID(m.get("ID").toString());
                            	e.setName(m.get("Name").toString());
                            	optionList.add(e);
                            }
                            if (proList != null && proList.size() > 0){
                                childItem.setValue(proList.get(0).get("Name").toString());
                                childItem.setValueID(proList.get(0).get("ID").toString());
                                childItem.setOption(optionList);
                            }
                        }
                        break;
            			case "F1725D6B-D1F7-4BC3-8C35-20FAB53A1602":{//认知途径
            				childItem.setOption(new ArrayList<OptionItem>());
            				Map<String,Object> paramAry = new HashMap<String,Object>();
                            paramAry.put("Project", model.getProjectID());
                            paramAry.put("UserID", model.getUserID());
                            List<Map<String,Object>> proList = vOpportunityMapper.SystemDictionaryXSRZTJList_Select(paramAry);
                            List<OptionItem> optionList = new ArrayList<OptionItem>();
                            for(Map<String,Object> m : proList){
                            	OptionItem e = new OptionItem();
                            	e.setID(m.get("ID").toString());
                            	e.setName(m.get("Name").toString());
                            	optionList.add(e);
                            }
                            if (proList != null && proList.size() > 0){
                            	childItem.setValue(proList.get(0).get("Name").toString());
                                childItem.setValueID(proList.get(0).get("ID").toString());
                                childItem.setOption(optionList);
                            }
                            if (customerModeType.equals(CustomerModeType.分接_新机会_老客户.getTypeID()) ||
                                customerModeType.equals(CustomerModeType.分接_新机会_新客户_新潜在客户.getTypeID()) ||
                                customerModeType.equals(CustomerModeType.分接_新机会_新客户_老潜在客户.getTypeID()) ||
                                customerModeType.equals(CustomerModeType.顾问_新机会_老客户.getTypeID()) ||
                                customerModeType.equals(CustomerModeType.顾问_新机会_新客户_老潜在客户.getTypeID()) ||
                                customerModeType.equals(CustomerModeType.顾问_新机会_新客户_新潜在客户.getTypeID())){
                                childItem.IsEdit = 1;
                            }
                        }
                        break;
            			case "BDDBD5B0-C1D2-4D76-96B4-C88C51C46AC0":{//认知媒体
                            childItem.setOption(GetRZMTOptionList(model.ProjectID));
                        }
                        break;
            			case "480B60B2-1EE1-4A31-A810-072184A1E9D7":{//跟进方式
	                        List<OptionItem> itemList = GetOptionList(childItem.ID,false);
	                        if(itemList != null && itemList.size() > 0 && jobCode =="GW" && IsNoAllotRole == 0){//开启分接置业顾问只能录入来电、去电、问询、外展接待
	                            List<String> followIdList = new ArrayList<String>();
	                            followIdList.add("A79A1057-D4DC-497C-8C81-8F93E422C819");
	                            followIdList.add("F0942939-A90E-4915-81D7-7752919B0F72");
	                            followIdList.add("44775694-7C97-455C-B48E-154C6BFE2D94");
	                            followIdList.add("EEB32C04-5B7C-4676-A5DC-5F95E56370EB");
//	                            itemList = itemList.Where(followIdList.Contains(ID)).ToList();
	                            List<OptionItem> temp = new ArrayList<OptionItem>();
	                            for(OptionItem o : itemList){
	                            	if(followIdList.contains(o.getID())){
	                            		temp.add(o);
	                            	}
	                            }
	                            itemList = temp;
	                        }
	                        childItem.Option = itemList;
            			}
                        break;
            			default:
            				childItem.Option = GetOptionList(childItem.ID,false);
                        break;
            			}
            		}
//            		dicInfo = dicList.Where(p => p.DicID == childItem.ID).FirstOrDefault();
            		for(DicInfo p : dicList){
            			if(p.getDicID().equals(childItem.getID()) ){
            				dicInfo = p;
            				break;
            			}
            		}
                    if (dicInfo != null){//字典存在
                        childItem.setValue(StringUtils.isEmpty(dicInfo.getValue()) ? childItem.getValue() : dicInfo.getValue());
                        childItem.setValueID(StringUtils.isEmpty(dicInfo.getValueID()) ? childItem.getValueID() : dicInfo.getValueID());
                        //通用处理信息
                        if ("8F6CC088-D5DD-48D2-BA3B-3A7AA4A1DB36".equals(childItem.getID())){//来访时间
                            if (StringUtils.isEmpty(childItem.Value)){
                            	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                                childItem.setValue(sdf.format(new Date()));
                                childItem.setValueID(sdf.format(new Date()));
                            }
                        }
                        if ("21685728-54C5-4268-8371-62413CE42841".equals(childItem.getID())){//电话
                            if (IsNoMobileVerify == 1){
                                childItem.Type = "Text";
                            }
                            if (StringUtils.isEmpty(childItem.Value)){
                                childItem.Value = model.Mobile;
                            }
                        }
                        if ("61C9B9E1-B2DE-4112-B9B3-C87E23E581BC".equals(childItem.getID())){//客户级别
                            if (IsNoCustomerRank == 1){
                            	Map<String,Object> map1 = new HashMap<String,Object>();
                            	map1.put("panelIndex", panelIndex);
                            	map1.put("childItem", childItem);
                                deleteItem.add(map1);
                            }
                        }
                        if ("A977C068-98A9-4184-AD39-5E645778CB5D".equals(childItem.getID())){//是否收小筹
                            if (IsNoCustomerRank == 1){
                            	Map<String,Object> map2 = new HashMap<String,Object>();
//                            	map2.put(childItem.getID() + "_" + panelIndex, childItem);
                            	map2.put("panelIndex", panelIndex);
                            	map2.put("childItem", childItem);
                                deleteItem.add(map2);
                            }
                        }
                        //不同模板处理信息
                        switch (customerModeType){
                            case "1"://CustomerModeType.分接_老机会_老客户.getTypeID():
                                if ("9134B1C9-B300-41CA-9FCD-D119661CB5F6".equals(childItem.getID())){//来访人数
                                    childItem.setValue("");
                                    childItem.setValueID("");
                                }
                                break;
                            case "21"://CustomerModeType.分接_老机会_老客户_未分配.getTypeID():
                                if ("149F778D-4244-46F6-908F-D33A363A5B58".equals(childItem.getID())){//姓名
                                    childItem.setIsEdit(0);
                                }
                                if ("21685728-54C5-4268-8371-62413CE42841".equals(childItem.getID())){//手机
                                    childItem.setIsEdit(0);
                                }
                                if ("E72C340D-4092-467A-9B8F-5138DBDCA43B".equals(childItem.getID())) {//性别
                                    childItem.setIsEdit(0);
                                }
                                if ("1FA31185-3B00-47AA-8F5E-7AA3D53205A5".equals(childItem.getID())){//置业顾问
                                    childItem.setValue(customerObj.get("SaleUserName").toString());
                                    childItem.setValueID(customerObj.get("SaleUserID").toString());
                                }
                                if ("BB66EE25-E48E-4A7B-BA1D-DB03FBC87500".equals(childItem.getID())){//备注
                                    childItem.setIsEdit(0);
                                }
                                //客户渠道处理
                                if ("F1725D6B-D1F7-4BC3-8C35-20FAB53A1602".equals(childItem.getID())){//渠道客户初始化 
                                	Map<String,Object> map = new HashMap<String,Object>();
                                	map.put("sqlWhere", " and ClueID='" + customerModel.ClueID + "'");
                                    List<Map<String,Object>> ClueObj = vOpportunityMapper.sCustomerPotentialClue(map);
                                    if (ClueObj != null && ClueObj.size() > 0){
                                        childItem.setValue(ClueObj.get(0).get("Name").toString());
                                        childItem.setValueID(ClueObj.get(0).get("ID").toString());
                                    }
                                    childItem.IsEdit = 0;
                                }
                                break;
                            case "23"://CustomerModeType.分接_老机会_老客户_公共客户.getTypeID():
                                if ("9134B1C9-B300-41CA-9FCD-D119661CB5F6".equals(childItem.getID())){//来访人数
                                	childItem.setValue("");
                                    childItem.setValueID("");
                                }
                                if ("4D35ABCF-E61C-4650-9F55-0D2D66548CF0".equals(childItem.getID())){//备注
                                	childItem.setValue("");
                                    childItem.setValueID("");
                                }
                                //客户渠道处理
                                if ("F1725D6B-D1F7-4BC3-8C35-20FAB53A1602".equals(childItem.getID())){//渠道客户初始化 
                                    ChildItem clueOpportunitySource = GetClueOpportunitySource(childItem, model);
                                    childItem.setOption(clueOpportunitySource.getOption());
                                    childItem.setValue(clueOpportunitySource.getValue());
                                    childItem.setValueID(clueOpportunitySource.getValueID());
                                }
                                break;
                            case "2"://CustomerModeType.分接_新机会_老客户.getTypeID():
                                if ("1FA31185-3B00-47AA-8F5E-7AA3D53205A5".equals(childItem.getID())){//置业顾问
                                    childItem.setValue(customerObj.get("SaleUserName").toString());
                                    childItem.setValueID(customerObj.get("SaleUserID").toString());
                                }
                                if ("9134B1C9-B300-41CA-9FCD-D119661CB5F6".equals(childItem.getID())){//来访人数
                                	childItem.setValue("");
                                    childItem.setValueID("");
                                }
                                if ("4D35ABCF-E61C-4650-9F55-0D2D66548CF0".equals(childItem.getID())){//备注
                                	childItem.setValue("");
                                    childItem.setValueID("");
                                }
                                //客户渠道处理
                                if ("F1725D6B-D1F7-4BC3-8C35-20FAB53A1602".equals(childItem.getID())){//渠道客户初始化 
                                    ChildItem clueOpportunitySource = GetClueOpportunitySource(childItem, model);
                                    childItem.setOption(clueOpportunitySource.getOption());
                                    childItem.setValue(clueOpportunitySource.getValue());
                                    childItem.setValueID(clueOpportunitySource.getValueID());
                                }
                                break;
                            case "3"://CustomerModeType.分接_新机会_新客户_老潜在客户.getTypeID():
                                if ("9134B1C9-B300-41CA-9FCD-D119661CB5F6".equals(childItem.getID())){//来访人数
                                	childItem.setValue("");
                                    childItem.setValueID("");
                                }
                                if ("4D35ABCF-E61C-4650-9F55-0D2D66548CF0".equals(childItem.getID())){//备注
                                	childItem.setValue("");
                                    childItem.setValueID("");
                                }
                                //客户渠道处理
                                if ("F1725D6B-D1F7-4BC3-8C35-20FAB53A1602".equals(childItem.getID())){//渠道客户初始化 
                                    ChildItem clueOpportunitySource = GetClueOpportunitySource(childItem, model);
                                    childItem.setOption(clueOpportunitySource.getOption());
                                    childItem.setValue(clueOpportunitySource.getValue());
                                    childItem.setValueID(clueOpportunitySource.getValueID());
                                }
                                break;
                            case "4"://CustomerModeType.分接_新机会_新客户_新潜在客户.getTypeID():
                                if ("9134B1C9-B300-41CA-9FCD-D119661CB5F6".equals(childItem.getID())){//来访人数
                                	childItem.setValue("");
                                    childItem.setValueID("");
                                }
                                break;
                            case "11"://CustomerModeType.顾问_老机会_老客户.getTypeID():
                                break;
                            case "12"://CustomerModeType.顾问_新机会_老客户.getTypeID():
                                if ("4D35ABCF-E61C-4650-9F55-0D2D66548CF0".equals(childItem.getID())){//备注
                                	childItem.setValue("");
                                    childItem.setValueID("");
                                }
                                if ("F1725D6B-D1F7-4BC3-8C35-20FAB53A1602".equals(childItem.getID())){//渠道客户初始化 
                                    ChildItem clueOpportunitySource = GetClueOpportunitySource(childItem, model);
                                    childItem.Option = clueOpportunitySource.Option;
                                    childItem.Value = clueOpportunitySource.Value;
                                    childItem.ValueID = clueOpportunitySource.ValueID;
                                }
                                break;
                            case "13"://CustomerModeType.顾问_新机会_新客户_老潜在客户.getTypeID():
                                if ("4D35ABCF-E61C-4650-9F55-0D2D66548CF0".equals(childItem.getID())){//备注
                                	childItem.setValue("");
                                    childItem.setValueID("");
                                }
                                if ("F1725D6B-D1F7-4BC3-8C35-20FAB53A1602".equals(childItem.getID())){//渠道客户初始化 
                                    ChildItem clueOpportunitySource = GetClueOpportunitySource(childItem, model);
                                    childItem.Option = clueOpportunitySource.Option;
                                    childItem.Value = clueOpportunitySource.Value;
                                    childItem.ValueID = clueOpportunitySource.ValueID;
                                }
                                break;
                            case "14"://CustomerModeType.顾问_新机会_新客户_新潜在客户.getTypeID():
                                break;
                            case "82"://CustomerModeType.顾问_客户_更新.getTypeID():
                                if ("61C9B9E1-B2DE-4112-B9B3-C87E23E581BC".equals(childItem.getID())){//客户级别处理,如果是3级,是否收小筹,不可以编辑 
                                    childItem.IsEdit = 0;
                                }
                                break;
                            case "99"://CustomerModeType.无.getTypeID():
                                break;
                            default:
                                break;
                        }
                        //关联权益人处理
                        //关联权益人时
                        if (model.IsEquity == "1"){
                            //初始化手机号
                            if ("21685728-54C5-4268-8371-62413CE42841".equals(childItem.getID())){
                                childItem.setValue(StringUtils.isEmpty(childItem.getValue()) ? model.getMobile() : childItem.getValue());
                            }
                            //除了客户标签,备注非必填,下次跟进 其他都必填
                            if (!"47A4BAF0-F946-4A8A-81F1-D675BEED6DE2".equals(childItem.getID())
                                && !"4D35ABCF-E61C-4650-9F55-0D2D66548CF0".equals(childItem.getID())
                                && !"7E6CAE73-F032-4E3A-9551-C6F7DA2AEC10".equals(childItem.getID())){
                                childItem.setIsMust(1);
                                childItem.setPlaceholder("必填");
                            }else{
                                childItem.IsMust = 0;
                            }
                            childItem.IsHide = 0;
                            switch (customerModeType){
                                case "82"://CustomerModeType.顾问_客户_更新.getTypeID():
                                    if (!"480B60B2-1EE1-4A31-A810-072184A1E9D7".equals(childItem.getID())
                                    && !"08289FD5-999A-4A9F-94D5-B85507575404".equals(childItem.getID())
                                    && !"600DEB36-F5E0-4BA3-B7FA-1A244F0773AB".equals(childItem.getID())
                                    && !"7E6CAE73-F032-4E3A-9551-C6F7DA2AEC10".equals(childItem.getID())){
                                        if (childItem.IsMustShow == 0){//非必有
                                            if (!StringUtils.isEmpty(childItem.ValueID) && !StringUtils.isEmpty(childItem.Value)){//隐藏已填写选项
                                                childItem.setIsHide(1);
                                            }else{
                                                childItem.setIsHide(0);
                                            }
                                        }
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }else{
                            switch (customerModeType){
                                case "82"://CustomerModeType.顾问_客户_更新.getTypeID():
                                    if (!model.getIsCustomerFirstEdit().equals("0"))
                                    {//非首次访问
                                        if (!"480B60B2-1EE1-4A31-A810-072184A1E9D7".equals(childItem.getID())
                                            && !"08289FD5-999A-4A9F-94D5-B85507575404".equals(childItem.getID())
                                            && !"600DEB36-F5E0-4BA3-B7FA-1A244F0773AB".equals(childItem.getID())
                                            && !"7E6CAE73-F032-4E3A-9551-C6F7DA2AEC10".equals(childItem.getID())){
                                            if (childItem.IsMustShow == 0){//非必有
                                                if (!StringUtils.isEmpty(childItem.getValueID()) && !StringUtils.isEmpty(childItem.getValue())){//隐藏已填写选项
                                                    childItem.setIsHide(1);
                                                }else{
                                                    childItem.setIsHide(0);
                                                }
                                                if ("0BFD0AEF-2C16-4A91-94D3-9CBF114DC78A".equals(childItem.getID()) && StringUtils.isEmpty(childItem.getValue()))
                                                {//媒体子类
                                                    childItem.setIsHide(1);
                                                }
                                            }
                                        }
                                    }else{//首访
//                                        取消首访
//                                        if (childItem.ID == "480B60B2-1EE1-4A31-A810-072184A1E9D7")
//                                        {//跟进方式
//                                            deleteItem.Add(childItem.ID + "_" + panelIndex, childItem);
//                                        }
//                                        if (childItem.ID == "600DEB36-F5E0-4BA3-B7FA-1A244F0773AB")
//                                        {//跟进内容
//                                            deleteItem.Add(childItem.ID + "_" + panelIndex, childItem);
//                                        }
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                        //客户渠道处理
                        if ("F1725D6B-D1F7-4BC3-8C35-20FAB53A1602".equals(childItem.getID())){//渠道客户初始化 
                            if (childItem.getOption().size() <= 1){
                                childItem.setIsEdit(0);
                            }
                        }
                        if ("480B60B2-1EE1-4A31-A810-072184A1E9D7".equals(childItem.getID())){//跟进方式
                        	childItem.setValue("");
                            childItem.setValueID("");
                        }
                        if ("600DEB36-F5E0-4BA3-B7FA-1A244F0773AB".equals(childItem.getID())){//跟进内容
                        	childItem.setValue("");
                            childItem.setValueID("");
                        }
                    }
                    else{//字典不存在
                    	childItem.setValue("");
                        childItem.setValueID("");
                    }
                    childIndex++;
            	}
            	panelIndex++;
            }
            for (Map<String,Object> item : deleteItem){
                int index = (int) item.get("panelIndex");
                customerModel.getPanel().get(0).getChild().remove(item.get("childItem"));
            }
		}
		return customerModel;
	}
	private ChildItem GetClueOpportunitySource(ChildItem childItem, CSearchModel model) {
		Map<String,Object> obj = new HashMap<String,Object>();
        if (!StringUtils.isEmpty(model.getCustomerPotentialID())){
            obj.put("sqlWhere", " and( Status = 1 or (Status=2 and '1' not in  (select top 1 '1' as c   from  B_Opportunity where clueid='" + model.getCustomerPotentialID() + "' and ProjectID='" + model.getProjectID() + "')) )   and Isdel = 0 and ID ='" + model.getCustomerPotentialID() + "' and IntentProjectID='" + model.getProjectID() + "' order by Status ASC,CreateTime DESC");
        }else{
            obj.put("sqlWhere", " and( Status = 1 or (Status=2 and '1' not in  (select top 1 '1' as c   from  B_Opportunity where CustomerMobile='" + model.getMobile() + "' and ProjectID='" + model.getProjectID() + "' AND Status != 6)) )   and Isdel = 0 and CustomerMobile ='" + model.getMobile() + "' and IntentProjectID='" + model.getProjectID() + "' order by Status ASC,CreateTime DESC");
        }
        List<Map<String,Object>> optionList = vOpportunityMapper.sCustomerPotentialClue(obj);
        if (optionList != null && optionList.size() > 0){//存在线索
            List<OptionItem> ClueList = new ArrayList<OptionItem>();
            int RuleType = (int) optionList.get(0).get("RuleType");
            boolean HasChoose = false;
            if (RuleType == 1){//竞争带看规则线索
                for (OptionItem item : childItem.getOption()){
                    if (item.getID().equals("0390CD8C-D6D4-4C92-995B-08C7E18E6EC2")){//增加自然到访
                        HasChoose = true;
                        ClueList.add(item);
                        break;
                    }
                }
            }
            for (Map<String,Object> item : optionList){
                if (!HasChoose){
                	HasChoose = item.get("IsChoose").equals("1") ? true : false;
                	ClueList.add(new OptionItem(item.get("ID").toString(), item.get("Name").toString(), (int) item.get("IsChoose")));
                }
            }
            if (HasChoose){
                childItem.setValue(ClueList.get(0).getName());
                childItem.setValueID(ClueList.get(0).getID());
            }else{
                childItem.setValue("");
                childItem.setValueID("");
                if (RuleType != 1){
                    for (OptionItem item : childItem.Option){
                        if (item.getID().equals("0390CD8C-D6D4-4C92-995B-08C7E18E6EC2")){//增加自然到访
                            ClueList.add(0, item);
                            childItem.setValue(item.getName());
                            childItem.setValueID(item.getID());
                            break;
                        }
                    }
                }
            }
            childItem.Option = ClueList;
        }
        return childItem;
	}
	/**
	 * 根据父ID获取option字典集合
	 * @param iD
	 * @return
	 */
	private List<OptionItem> GetOptionList(String pid,boolean isall) {
        List<OptionItem> list = new ArrayList<OptionItem>();
//        String sqlid = isall ? "DictionaryAllList_Select" : "DictionaryList_Select";
        QueryWrapper<SDictionary> wrapper = new QueryWrapper<SDictionary>();
        wrapper.eq("PID", pid);
        if(!isall){// 查询字典集合
        	wrapper.eq("IsDel", 0);
        	wrapper.eq("Status", 1);
        }//否则查询所有字典集合
        List<SDictionary> jarry = sDictionaryMapper.selectList(wrapper);
        if (jarry != null && jarry.size() > 0){
            for (SDictionary item : jarry){
                list.add(new OptionItem(item.getId(), item.getDictName(),null));
            }
        }
        return list;
	}
	/**
	 * 根据id获取对象
	 * @param projectID
	 * @return
	 */
	private BProject Detail_FindById(String projectID) {
		Map<String,Object> paramAry = new HashMap<String,Object>();
        paramAry.put("ID", projectID);
        QueryWrapper<BProject> wrapper = new QueryWrapper<BProject>();
        wrapper.eq("ID", projectID);
        return bProjectMapper.selectOne(wrapper);
	}
	/**
	 * 获取认知媒体及媒体子类
	 * @param projectID
	 * @return
	 */
	private List<OptionItem> GetRZMTOptionList(String projectID) {
        List<OptionItem> list = new ArrayList<OptionItem>();
        List<Map<String,Object>> jarry = vOpportunityMapper.SystemDictionaryRZMTList_Select(projectID);
        if (jarry != null && jarry.size() > 0){
        	Map<String,Object> PIDObject = new HashMap<String,Object>();
            for (Map<String,Object> item : jarry){
                String PID = item.get("PID").toString();
                String PName = item.get("PName").toString();
                String ID = item.get("ID").toString();
                String Name = item.get("Name").toString();
                if (PIDObject.get(PID) == null){
                    PIDObject.put(PID, PID);
                    list.add(new OptionItem(PID,PName,new ArrayList<OptionItem>()));
                }
                if (!StringUtils.isEmpty(ID)){
                    list.get(list.size() -1).setIsSubMust(1);
                    list.get(list.size() -1).getChild().add(new OptionItem(ID, Name, new ArrayList<OptionItem>()));
                }
            }
        }
        return list;
	}

}
