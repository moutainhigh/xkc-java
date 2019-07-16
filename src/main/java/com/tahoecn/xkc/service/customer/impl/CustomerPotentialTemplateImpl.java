package com.tahoecn.xkc.service.customer.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.tahoecn.xkc.model.vo.CSearchModelVo;
import com.tahoecn.xkc.model.vo.CustomerModelVo;
import com.tahoecn.xkc.service.customer.ICustomerHelp;
import com.tahoecn.xkc.service.customer.ICustomerPotentialTemplate;

@Service
@Transactional(readOnly=true)
public class CustomerPotentialTemplateImpl implements ICustomerPotentialTemplate {

	@Resource
	private ICustomerHelp iCustomerHelp;
	
	@Override
	public CustomerModelVo InitCustomerPotentialModeData(CSearchModelVo model,
			String jsonFileName, JSONObject CustomerObj,
			String customerPotentialModeType) {/*
        CustomerModelVo customerModel = null;
        try{
            customerModel = iCustomerHelp.InitCustomerModelByFileName(jsonFileName);
            if (customerModel != null){
                var dicList = InitCustomerDicModel();
                if (CustomerObj.Count > 0)
                {
                    customerModel.ClueID = Convert.ToString(CustomerObj["ClueID"]);
                    customerModel.CustomerID = Convert.ToString(CustomerObj["CustomerPotentialID"]);
                    string fieldKey = string.Empty;
                    foreach (DicInfo item in dicList)
                    {
                        fieldKey = (item.Type == "Option" || item.Type == "OptionRadio") ? item.FieldName + "Name" : item.FieldName;
                        item.Value = Convert.ToString(CustomerObj[fieldKey]);
                        item.ValueID = Convert.ToString(CustomerObj[item.FieldName]).Replace("\r\n  ", "").Replace("\r", "").Replace("\n", "");
                    }
                }
                customerModel.OrgID = model.OrgID;
                //查询当前项目信息
                var project = new ProjectService().Detail_FindById(model.ProjectID, debug);
                int IsNoMobileVerify = ConvertHelper.ToInt(((JObject)project.Data)["IsNoMobileVerify"]);
                int IsNoCustomerRank = ConvertHelper.ToInt(((JObject)project.Data)["IsNoCustomerRank"]);
                DicInfo dicInfo;
                int panelIndex = 0;
                Dictionary<string, ChildItem> deleteItem = new Dictionary<string, ChildItem>();
                foreach (PanelItem panelItem in customerModel.Panel)
                {
                    int childIndex = 0;
                    foreach (ChildItem childItem in panelItem.Child)
                    {
                        //处理选项项目
                        if (childItem.Type == "Option" || childItem.Type == "OptionTag" || childItem.Type == "OptionCity" || childItem.Type == "OptionRadio" || childItem.Type == "OptionTextArea")
                        { //初始化option值
                            switch (childItem.ID)
                            {
                                case "C4FBE218-B26A-48A0-88FF-F6CB2DCD1A72"://归属任务
                                    {
                                        if (customerPotentialModeType == CustomerPotentialModeType.自渠_新线索_新潜在客户 ||
                                            customerPotentialModeType == CustomerPotentialModeType.自渠_新线索_老潜在客户 || customerPotentialModeType == CustomerPotentialModeType.自渠_客户_更新)
                                        {
                                            JObject paramAry = new JObject();
                                            string whereStr = string.Format(" AND b.ProjectID='{0}'", Convert.ToString(model.ProjectID));
                                            if (model.JobCode == "JZ")
                                            {
                                                whereStr += string.Format(" AND a.ChannelUserID='{0}'", Convert.ToString(model.UserID));
                                            }
                                            else
                                            {
                                                whereStr += string.Format(" AND b.Creator='{0}'", Convert.ToString(model.UserID));
                                            }

                                            paramAry.Add("WHERE", whereStr);
                                            JArray optionList = ((JArray)JsonDataHelper.GetListData("mChannelTaskListZQ_Select", paramAry, out errmsg, debug));
                                            if (customerPotentialModeType == CustomerPotentialModeType.自渠_客户_更新)
                                            {
                                                if (!string.IsNullOrWhiteSpace(model.ClueID))
                                                {
                                                    JObject param = new JObject();
                                                    param.Add("ClueID", model.ClueID);
                                                    optionList = ((JArray)JsonDataHelper.GetListData("mChannelTaskGetByClueID_Select", param, out errmsg, debug));
                                                }
                                            }
                                            if (optionList.Count > 0)
                                            {
                                                childItem.Value = Convert.ToString(optionList[0]["Name"]);
                                                childItem.ValueID = Convert.ToString(optionList[0]["ID"]);
                                                childItem.Option = JsonHelper.DeserializeJsonToList<OptionItem>(optionList.ToString());
                                            }
                                            if (optionList.Count <= 1)
                                            {
                                                childItem.IsEdit = 0;
                                            }
                                        }
                                        else
                                        {
                                            deleteItem.Add(childItem.ID + "_" + panelIndex, childItem);
                                        }
                                    }
                                    break;
                                case "7B44EDBA-FCB7-4040-A5FF-DDED0F01C76D"://意向项目
                                    {
                                        if (customerPotentialModeType == CustomerPotentialModeType.自渠_新线索_新潜在客户 ||
                                            customerPotentialModeType == CustomerPotentialModeType.自渠_新线索_老潜在客户)
                                        {
                                            JObject paramAry = new JObject();
                                            paramAry.Add("WHERE", " and ID='" + Convert.ToString(model.ProjectID) + "' and level='1'");
                                            JArray optionList = ((JArray)JsonDataHelper.GetListData("sProject_Select", paramAry, out errmsg, debug));
                                            if (optionList.Count > 0)
                                            {
                                                childItem.Value = Convert.ToString(optionList[0]["Name"]);
                                                childItem.ValueID = Convert.ToString(optionList[0]["ID"]);
                                                childItem.Option = JsonHelper.DeserializeJsonToList<OptionItem>(optionList.ToString());
                                            }
                                        }
                                    }
                                    break;
                                case "F1725D6B-D1F7-4BC3-8C35-20FAB53A1602"://认知途径
                                    {
                                        JObject paramAry = new JObject();
                                        paramAry.Add("Project", model.ProjectID);
                                        paramAry.Add("UserID", model.UserID);
                                        paramAry.Add("ChannelTypeID", model.ChannelTypeID);
                                        JArray optionList = ((JArray)JsonDataHelper.GetListData("SystemDictionaryZQRZTJList_Select", paramAry, out errmsg, debug));
                                        if (optionList.Count > 0)
                                        {
                                            childItem.Value = Convert.ToString(optionList[0]["Name"]);
                                            childItem.ValueID = Convert.ToString(optionList[0]["ID"]);
                                            childItem.Option = JsonHelper.DeserializeJsonToList<OptionItem>(optionList.ToString());
                                        }
                                        if (customerPotentialModeType == CustomerPotentialModeType.自渠_新线索_新潜在客户 ||
                                            customerPotentialModeType == CustomerPotentialModeType.自渠_新线索_老潜在客户)
                                        {
                                            childItem.IsEdit = 1;
                                        }
                                        if (optionList.Count <= 1)
                                        {
                                            childItem.IsEdit = 0;
                                        }
                                    }
                                    break;
                                case "BDDBD5B0-C1D2-4D76-96B4-C88C51C46AC0"://认知媒体
                                    {
                                        childItem.Option = new List<OptionItem>();
                                        if (customerPotentialModeType == CustomerPotentialModeType.自渠_新线索_新潜在客户 ||
                                            customerPotentialModeType == CustomerPotentialModeType.自渠_新线索_老潜在客户 ||
                                            customerPotentialModeType == CustomerPotentialModeType.自渠_客户_更新)
                                        {
                                            childItem.Option = GetRZMTOptionList(model.ProjectID, debug);
                                        }
                                    }
                                    break;
                                case "480B60B2-1EE1-4A31-A810-072184A1E9D7"://跟进方式
                                    {
                                        childItem.Option = GetOptionList(childItem.ID, debug);
                                        for (int i = 0; i < childItem.Option.Count; i++)
                                        {
                                            if (childItem.Option[i].ID == "E30825AA-B894-4A5F-AF55-24CAC34C8F1F")
                                            {//删除售场接待
                                                childItem.Option.Remove(childItem.Option[i]);
                                                break;
                                            }
                                        }
                                    }
                                    break;
                                default:
                                    childItem.Option = GetOptionList(childItem.ID, debug);
                                    break;
                            }
                        }
                        dicInfo = dicList.Where(p => p.DicID == childItem.ID).FirstOrDefault();
                        if (dicInfo != null)
                        {//字典存在

                            childItem.Value = string.IsNullOrEmpty(dicInfo.Value) ? childItem.Value : dicInfo.Value;
                            childItem.ValueID = string.IsNullOrEmpty(dicInfo.ValueID) ? childItem.ValueID : dicInfo.ValueID;
                            #region 通用处理信息
                            if (childItem.ID == "8F6CC088-D5DD-48D2-BA3B-3A7AA4A1DB36")
                            {//来访时间
                                if (string.IsNullOrWhiteSpace(childItem.Value))
                                {
                                    childItem.Value = DateTime.Now.ToString("yyyy/MM/dd HH:mm");
                                    childItem.ValueID = DateTime.Now.ToString("yyyy/MM/dd HH:mm");
                                }
                            }
                            if (childItem.ID == "21685728-54C5-4268-8371-62413CE42841")
                            {//电话
                                if (IsNoMobileVerify == 1)
                                {
                                    childItem.Type = "Text";
                                }
                                if (string.IsNullOrWhiteSpace(childItem.Value))
                                {
                                    childItem.Value = model.Mobile;
                                }
                            }
                            if (childItem.ID == "61C9B9E1-B2DE-4112-B9B3-C87E23E581BC")
                            {//客户级别
                                if (IsNoCustomerRank == 1)
                                {
                                    deleteItem.Add(childItem.ID + "_" + panelIndex, childItem);
                                }
                            }
                            switch (customerPotentialModeType)
                            {
                                case CustomerPotentialModeType.自渠_老线索_老潜在客户:
                                    break;
                                case CustomerPotentialModeType.自渠_新线索_新潜在客户:
                                    if (childItem.ID == "4D35ABCF-E61C-4650-9F55-0D2D66548CF0")
                                    {//备注
                                        childItem.Value = "";
                                        childItem.ValueID = "";
                                    }
                                    break;
                                case CustomerPotentialModeType.自渠_新线索_老潜在客户:
                                    if (childItem.ID == "4D35ABCF-E61C-4650-9F55-0D2D66548CF0")
                                    {//备注
                                        childItem.Value = "";
                                        childItem.ValueID = "";
                                    }
                                    break;
                                case CustomerPotentialModeType.自渠_客户_详情:
                                    break;
                                case CustomerPotentialModeType.自渠_客户_更新:
                                    break;
                                case CustomerPotentialModeType.无:
                                    break;
                                default:
                                    break;
                            }
                            if (childItem.ID == "480B60B2-1EE1-4A31-A810-072184A1E9D7")
                            {//跟进方式
                                childItem.Value = "";
                                childItem.ValueID = "";
                            }
                            if (childItem.ID == "600DEB36-F5E0-4BA3-B7FA-1A244F0773AB")
                            {//跟进内容
                                childItem.Value = "";
                                childItem.ValueID = "";
                            }
                        }else{//字典不存在
                         //childItem.Value = "";
                         //childItem.ValueID = "";
                        }
                        childIndex++;
                    }
                    panelIndex++;
                }
                foreach (var item in deleteItem)
                {
                    int index = ConvertHelper.ToInt(item.Key.Split('_')[1]);
                    customerModel.Panel[index].Child.Remove(item.Value);
                }
            }
        }
        catch (Exception e){
            throw e;
        }
        return customerModel;
	*/
		return null;}
}
