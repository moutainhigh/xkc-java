package com.tahoecn.xkc.service.customer.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hutool.core.date.DateUtil;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tahoecn.xkc.common.enums.CustomerPotentialModeType;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.mapper.customer.BCustomerpotentialMapper;
import com.tahoecn.xkc.mapper.customer.VCustomergwlistSelectMapper;
import com.tahoecn.xkc.model.vo.CGWDetailModel;
import com.tahoecn.xkc.model.vo.CGWDetailModel.Item;
import com.tahoecn.xkc.model.vo.CSearchModelVo;
import com.tahoecn.xkc.model.vo.ChildItem;
import com.tahoecn.xkc.model.vo.CustomerModelVo;
import com.tahoecn.xkc.model.vo.DicInfo;
import com.tahoecn.xkc.model.vo.OptionItem;
import com.tahoecn.xkc.model.vo.PanelItem;
import com.tahoecn.xkc.service.customer.ICustomerHelp;
import com.tahoecn.xkc.service.customer.ICustomerPotentialTemplate;
import com.tahoecn.xkc.service.customer.IProjectService;

@Service
@Transactional(readOnly=true)
public class CustomerPotentialTemplateImpl implements ICustomerPotentialTemplate {

	@Resource
	private BCustomerpotentialMapper bCustomerpotentialMapper;
	@Resource
	private VCustomergwlistSelectMapper vCustomergwlistSelectMapper;
	@Resource
	private ICustomerHelp iCustomerHelp;
	@Resource
	private IProjectService iProjectService;
	
	@Override
	public CustomerModelVo InitCustomerPotentialModeData(CSearchModelVo model,
			String jsonFileName, JSONObject CustomerObj,
			String customerPotentialModeType) {
        CustomerModelVo customerModel = null;
        try{
            customerModel = iCustomerHelp.InitCustomerModelByFileName(jsonFileName);
            if (customerModel != null){
            	List<DicInfo> dicList = iCustomerHelp.InitCustomerDicModel("CustomerPotentialDic.json");
                if (CustomerObj.size() > 0){
                	customerModel.setClueID(CustomerObj.getString("ClueID"));
                    customerModel.setCustomerID(CustomerObj.getString("CustomerPotentialID"));
                    String fieldKey ="";
                    for(DicInfo item : dicList){
                        fieldKey = ("Option".equals(item.getType()) || "OptionRadio".equals(item.getType())) ? item.getFieldName() + "Name" : item.getFieldName();
                        item.setValue(CustomerObj.getString(fieldKey));
                        String FieldName = CustomerObj.getString(item.getFieldName());
                        if(!StringUtils.isEmpty(FieldName)){
                        	FieldName.replaceAll("\r\n  ", "").replaceAll("\r", "").replaceAll("\n", "");
                        }
                        item.setValueID(FieldName);
                    }
                }
                customerModel.setOrgID(model.getOrgID());
                //查询当前项目信息
                Result project = iProjectService.Detail_FindById(model.getProjectID());
                JSONObject projectData = (JSONObject)project.getData();
                int IsNoMobileVerify = projectData.getIntValue("IsNoMobileVerify");
                int IsNoCustomerRank = projectData.getIntValue("IsNoCustomerRank");
                DicInfo dicInfo = null;
                int panelIndex = 0;
                Map<String,ChildItem> deleteItem = new HashMap<String,ChildItem>();
                for(PanelItem panelItem : customerModel.getPanel()){
                    int childIndex = 0;
                    for(ChildItem childItem : panelItem.getChild()){
                        //处理选项项目
                        if ("Option".equals(childItem.getType()) || "OptionTag".equals(childItem.getType()) || "OptionCity".equals(childItem.getType()) || "OptionRadio".equals(childItem.getType()) || "OptionTextArea".equals(childItem.getType())){ 
                        	//初始化option值
                            switch (childItem.getID()){
                                case "C4FBE218-B26A-48A0-88FF-F6CB2DCD1A72":{//归属任务
                                        if (customerPotentialModeType.equals(CustomerPotentialModeType.自渠_新线索_新潜在客户.getTypeID()) ||
                                            customerPotentialModeType.equals(CustomerPotentialModeType.自渠_新线索_老潜在客户.getTypeID()) || 
                                            customerPotentialModeType.equals(CustomerPotentialModeType.自渠_客户_更新.getTypeID())){
                                        	Map<String,Object> paramAry = new HashMap<String, Object>();
                                            String whereStr = " AND b.ProjectID='"+model.getProjectID()+"'";
                                            if ("JZ".equals(model.getJobCode())){
                                                whereStr += " AND a.ChannelUserID='"+model.getUserID()+"'";
                                            }else{
                                                whereStr +=" AND b.Creator='"+model.getUserID()+"'";
                                            }
                                            paramAry.put("WHERE", whereStr);
                                            List<Map<String, Object>> optionList = bCustomerpotentialMapper.mChannelTaskListZQ_Select(paramAry);
                                            if (customerPotentialModeType.equals(CustomerPotentialModeType.自渠_客户_更新.getTypeID())){
                                                if (!StringUtils.isEmpty(model.getClueID())){
                                                	Map<String,Object> param = new HashMap<String, Object>();
                                                    param.put("ClueID", model.getClueID());
                                                    optionList =bCustomerpotentialMapper.mChannelTaskGetByClueID_Select(param);
                                                }
                                            }
                                            if (optionList.size() > 0){
                                            	childItem.setValue(String.valueOf(optionList.get(0).get("Name")));
                                                childItem.setValueID(String.valueOf(optionList.get(0).get("ID")));
                                                String optionL = JSONArray.toJSONString(optionList);
            									childItem.setOption(JSONArray.parseArray(optionL, OptionItem.class));
                                            }
                                            if (optionList.size() <= 1){
                                                childItem.setIsEdit(0);
                                            }
                                        }else{
                                            deleteItem.put(childItem.getID() + "_" + panelIndex, childItem);
                                        }
                                    }
                                    break;
                                case "7B44EDBA-FCB7-4040-A5FF-DDED0F01C76D":{//意向项目
                                        if (customerPotentialModeType.equals(CustomerPotentialModeType.自渠_新线索_新潜在客户.getTypeID()) ||
                                            customerPotentialModeType.equals(CustomerPotentialModeType.自渠_新线索_老潜在客户.getTypeID())){
                                            String where = " and ID='" + model.getProjectID() + "' and level='1'";
                                            List<Map<String, Object>> optionList = vCustomergwlistSelectMapper.sProject_Select(where);
                                            if (optionList.size() > 0){
                                                childItem.setValue(String.valueOf(optionList.get(0).get("Name")));
                                                childItem.setValueID(String.valueOf(optionList.get(0).get("ID")));
                                                String optionL = JSONArray.toJSONString(optionList);
            									childItem.setOption(JSONArray.parseArray(optionL, OptionItem.class));
                                            }
                                        }
                                    }
                                    break;
                                case "F1725D6B-D1F7-4BC3-8C35-20FAB53A1602":{//认知途径
                                		Map<String,Object> paramAry = new HashMap<String, Object>();
                                        paramAry.put("Project", model.getProjectID());
                                        paramAry.put("UserID", model.getUserID());
                                        paramAry.put("ChannelTypeID", model.getChannelTypeID());
                                        List<Map<String, Object>> optionList = bCustomerpotentialMapper.SystemDictionaryZQRZTJList_Select(paramAry);
                                        if (optionList.size() > 0){
                                            childItem.setValue(String.valueOf(optionList.get(0).get("Name")));
                                            childItem.setValueID(String.valueOf(optionList.get(0).get("ID")));
                                            String optionL = JSONArray.toJSONString(optionList);
        									childItem.setOption(JSONArray.parseArray(optionL, OptionItem.class));
                                        }
                                        if (customerPotentialModeType.equals(CustomerPotentialModeType.自渠_新线索_新潜在客户.getTypeID())||
                                            customerPotentialModeType.equals(CustomerPotentialModeType.自渠_新线索_老潜在客户.getTypeID())){
                                            childItem.setIsEdit(1);
                                        }
                                        if (optionList.size() <= 1){
                                            childItem.setIsEdit(0);
                                        }
                                    }
                                    break;
                                case "BDDBD5B0-C1D2-4D76-96B4-C88C51C46AC0":{//认知媒体
                                        childItem.setOption(new ArrayList<OptionItem>());
                                        if (customerPotentialModeType.equals(CustomerPotentialModeType.自渠_新线索_新潜在客户.getTypeID())||
                                            customerPotentialModeType.equals(CustomerPotentialModeType.自渠_新线索_老潜在客户.getTypeID())||
                                            customerPotentialModeType.equals(CustomerPotentialModeType.自渠_客户_更新.getTypeID())){
                                            childItem.setOption(iCustomerHelp.GetRZMTOptionList(model.getProjectID()));
                                        }
                                    }
                                    break;
                                case "480B60B2-1EE1-4A31-A810-072184A1E9D7":{//跟进方式
                                        childItem.setOption(iCustomerHelp.GetOptionList(childItem.getID(), false));
                                        for (int i = 0; i < childItem.getOption().size(); i++){
                                            if ("E30825AA-B894-4A5F-AF55-24CAC34C8F1F".equals(childItem.getOption().get(i).getID())){
                                            	//删除售场接待
                                                childItem.getOption().remove(childItem.getOption().get(i));
                                                break;
                                            }
                                        }
                                    }
                                    break;
                                default:
                                    childItem.setOption(iCustomerHelp.GetOptionList(childItem.getID(), false));
                                    break;
                            }
                        }
                        for (DicInfo p : dicList) {
							if (p.getDicID().equals(childItem.getID())) {
								dicInfo = p;
								break;
							}
						}
                        if (dicInfo != null){//字典存在
                            childItem.setValue(StringUtils.isEmpty(dicInfo.getValue()) ? childItem.getValue() : dicInfo.getValue());
                            childItem.setValueID(StringUtils.isEmpty(dicInfo.getValueID())? childItem.getValueID() : dicInfo.getValueID());
                            if ("8F6CC088-D5DD-48D2-BA3B-3A7AA4A1DB36".equals(childItem.getID())){
                            	//来访时间
                                if (StringUtils.isEmpty(childItem.getValue())){
                                    childItem.setValue(DateUtil.format(new Date(), "yyyy/MM/dd HH:mm"));
									childItem.setValueID(DateUtil.format(new Date(), "yyyy/MM/dd HH:mm"));
                                }
                            }
                            if ("21685728-54C5-4268-8371-62413CE42841".equals(childItem.getID())){//电话
                                if (IsNoMobileVerify == 1){
                                    childItem.setType("Text");
                                }
                                if (StringUtils.isEmpty(childItem.getValue())){
                                    childItem.setValue(model.getMobile());
                                }
                            }
                            if ("61C9B9E1-B2DE-4112-B9B3-C87E23E581BC".equals(childItem.getID())){//客户级别
                                if (IsNoCustomerRank == 1){
                                    deleteItem.put(childItem.getID() + "_" + panelIndex, childItem);
                                }
                            }
                            switch (customerPotentialModeType){
                                case "1":
                                    break;
                                case "3":
                                    if ("4D35ABCF-E61C-4650-9F55-0D2D66548CF0".equals(childItem.getID())){//备注
                                        childItem.setValue("");
                                        childItem.setValueID("");
                                    }
                                    break;
                                case "2":
                                    if ("4D35ABCF-E61C-4650-9F55-0D2D66548CF0".equals(childItem.getID())){//备注
                                    	childItem.setValue("");
                                        childItem.setValueID("");
                                    }
                                    break;
                                case "81":
                                    break;
                                case "82":
                                    break;
                                case "99":
                                    break;
                                default:
                                    break;
                            }
                            if ("480B60B2-1EE1-4A31-A810-072184A1E9D7".equals(childItem.getID())){//跟进方式
                            	childItem.setValue("");
                                childItem.setValueID("");
                            }
                            if ("600DEB36-F5E0-4BA3-B7FA-1A244F0773AB".equals(childItem.getID())){//跟进内容
                            	childItem.setValue("");
                                childItem.setValueID("");
                            }
                        }else{//字典不存在
                         //childItem.Value = "";
                         //childItem.ValueID = "";
                        }
                        childIndex++;
                    }
                    panelIndex++;
                }
                for (Map.Entry<String, ChildItem> entry : deleteItem.entrySet()) {
					String mapKey = entry.getKey();
					ChildItem mapValue = entry.getValue();
					int index = Integer.parseInt(mapKey.split("_")[1]);
					customerModel.getPanel().get(index).getChild().remove(mapValue);
				}
            }
        }catch (Exception e){
            throw e;
        }
        return customerModel;
	}

	@Override
	public JSONObject GetParameters(CGWDetailModel model) {
		List<DicInfo> dicList = iCustomerHelp.InitCustomerDicModel("CustomerPotentialDic.json");
		JSONObject parameter = new JSONObject();
        for(DicInfo dicInfo : dicList){
        	Item item = null;
            for(Item it : model.getItemList()){
            	if(it.getID().equals(dicInfo.getDicID())){
            		item = it;
            		break;
            	}
            }
            dicInfo.setValue(item != null?item.getValue():"");
            parameter.put(dicInfo.getFieldName(), dicInfo.getValue().replaceAll("'", ""));
        }
        parameter.put("ClueID", model.getClueID());
        parameter.put("CustomerID'", model.getCustomerID());
        parameter.put("ProjectID", model.getProjectID());
        parameter.put("JobID", model.getJobID());
        parameter.put("OrgID", model.getOrgID());
        parameter.put("UserID", model.getUserID());
        parameter.put("Editor", model.getUserID());
        return parameter;
	}

	@Override
	public JSONObject GetParameters(CGWDetailModel model, JSONObject obj) {
		if (obj != null && obj.size() > 0){
			List<DicInfo> dicList = iCustomerHelp.InitCustomerDicModel("CustomerPotentialDic.json");
			JSONObject parameter = new JSONObject();
            for(DicInfo dicInfo : dicList){
            	Item item = null;
                for(Item it : model.getItemList()){
                	if(it.getID().equals(dicInfo.getDicID())){
                		item = it;
                		break;
                	}
                }
                if (item == null){//字典不包含参数则初始化历史值补空
                    dicInfo.setValue(obj.getString(dicInfo.getFieldName()));
                }else{//字典包含参数 参数值赋值到对应字典值
                    dicInfo.setValue(item.getValue());
                }
                if (StringUtils.isEmpty(dicInfo.getValue())){//如果字典值
                    dicInfo.setValue("");
                }
                parameter.put(dicInfo.getFieldName(), dicInfo.getValue().replaceAll("'", ""));
            }
            parameter.put("ClueID", model.getClueID());
            parameter.put("CustomerID", StringUtils.isEmpty(model.getCustomerID())?obj.getString("CustomerID"):"");
            parameter.put("ProjectID", model.getProjectID());
            parameter.put("JobID", model.getJobID());
            parameter.put("JobCode", model.getJobCode());
            parameter.put("OrgID", model.getOrgID());
            parameter.put("UserID", model.getUserID());
            parameter.put("Editor", model.getUserID());
            return parameter;
        }else{
            return GetParameters(model);
        }
	}

}
