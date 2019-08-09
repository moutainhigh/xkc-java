package com.tahoecn.xkc.service.customer.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hutool.core.date.DateUtil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tahoecn.xkc.common.enums.CustomerModeType;
import com.tahoecn.xkc.common.utils.JSONUtil;
import com.tahoecn.xkc.converter.CareerConsCustConverter;
import com.tahoecn.xkc.converter.Result;
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
import com.tahoecn.xkc.service.customer.IMYService;
import com.tahoecn.xkc.service.customer.IProjectService;

@SuppressWarnings("unchecked")
@Service
@Transactional(readOnly=true)
public class CustomerHelp implements ICustomerHelp {

	@Resource
	private VCustomergwlistSelectMapper vCustomergwlistSelectMapper;
	@Resource
	private RedisTemplate<String, String> redisTemplate;
	@Resource
	private IProjectService iProjectService;
	@Resource
	private IMYService iMYService;

	@Override
	public JSONObject OpportunityInfo(String opportunityID) {
		try {
			String where = "  AND ID ='" + opportunityID + "' ";
			Map<String, Object> map = vCustomergwlistSelectMapper.OpportunityDetail_Select(where);
			if(map!=null && map.size()>0){
            	return new JSONObject(map);
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public JSONObject OpportunityInfo(String ProjectID, String Mobile) {
		try {
            String where  = "  AND ProjectID ='" + ProjectID + "' AND Mobile='" + Mobile + "' ORDER BY CreateTime DESC ";
            Map<String, Object> map = vCustomergwlistSelectMapper.OpportunityDetail_Select(where);
            if(map!=null && map.size()>0){
            	return new JSONObject(map);
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional(readOnly=false)
	public CustomerModelVo InitCustomerModeData(CSearchModelVo model,
			String jsonFileName, JSONObject CustomerObj, String customerModeType) {
		CustomerModelVo customerModel = null;
		try {
			customerModel = InitCustomerModelByFileName(jsonFileName);
			if (customerModel != null) {
				List<DicInfo> dicList = InitCustomerDicModel("CustomerDic.json");
				if (CustomerObj!=null) {
					customerModel.setCustomerID(CustomerObj.getString("CustomerID"));
					customerModel.setOpportunityID(CustomerObj.getString("OpportunityID"));
					customerModel.setClueID(CustomerObj.getString("ClueID"));
					customerModel.setSpareMobile(CustomerObj.getString("SpareMobile")!=null?CustomerObj.getString("SpareMobile"):"");
					customerModel.setUseMobile(CustomerObj.getString("UseMobile")!=null?CustomerObj.getString("UseMobile"):"");
					customerModel.setCustomerMobile(CustomerObj.getString("CustomerMobile")!=null?CustomerObj.getString("CustomerMobile"):"");
					model.setCustomerPotentialID(CustomerObj.getString("CustomerPotentialID"));
					String fieldKey = null;
					for (DicInfo item : dicList) {
						fieldKey = ("Option".equals(item.getType()) || "OptionRadio".equals(item.getType())) ? item.getFieldName() + "Name" : item.getFieldName();
						item.setValue(CustomerObj.getString(fieldKey));
						String tvalueID = CustomerObj.getString(item.getFieldName());
						if(tvalueID!=null){
							tvalueID.replaceAll("\r\n", "").replaceAll("\r", "").replaceAll("\n", "");
						}
						item.setValueID(tvalueID);
					}
				}
				//
				// 查询当前项目信息
				Result project_r = iProjectService.Detail_FindById(model.getProjectID());
				JSONObject project = (JSONObject) project_r.getData();
				int IsNoAllotRole = project.getIntValue("IsNoAllotRole");
				int IsNoMobileVerify = project.getIntValue("IsNoMobileVerify");
				int IsNoCustomerRank = project.getIntValue("IsNoCustomerRank");
				String jobCode = model.getJobCode();
				DicInfo dicInfo = new DicInfo();
				int panelIndex = 0;
				Map<String, ChildItem> deleteItem = new HashMap<String, ChildItem>();
				for (PanelItem panelItem : customerModel.getPanel()) {
					for (ChildItem childItem : panelItem.getChild()) {
						// 处理选项项目
						if ("Option".equals(childItem.getType())
								|| "OptionTag".equals(childItem.getType())
								|| "OptionCity".equals(childItem.getType())
								|| "OptionRadio".equals(childItem.getType())
								|| "OptionTextArea".equals(childItem.getType())) { // 初始化option值
							switch (childItem.getID()) {
							case "7B44EDBA-FCB7-4040-A5FF-DDED0F01C76D": {// 意向项目
								String where = " and ID='"+ model.getProjectID()+ "' and level='1'";
								List<Map<String, Object>> optionList = vCustomergwlistSelectMapper.sProject_Select(where);
								if (optionList!=null && optionList.size() > 0) {
									childItem.setValue(String.valueOf(optionList.get(0).get("Name")));
									childItem.setValueID(String.valueOf(optionList.get(0).get("ID")));
									String optionL = JSONArray.toJSONString(optionList);
									childItem.setOption(JSONArray.parseArray(optionL, OptionItem.class));
								}
							}
								break;
							case "BFD616A1-118F-4C9F-986F-BAA32A1A7EA3": {// 意向项目分期
								String where = " and PID='"+ model.getProjectID()+ "' and level='2'";
								List<Map<String, Object>> optionList = vCustomergwlistSelectMapper.sProject_Select(where);
								if (optionList!=null && optionList.size() > 0) {
									childItem.setValue(String.valueOf(optionList.get(0).get("Name")));
									childItem.setValueID(String.valueOf(optionList.get(0).get("ID")));
									String optionL = JSONArray.toJSONString(optionList);
									childItem.setOption(JSONArray.parseArray(optionL, OptionItem.class));
								}
							}
								break;
							case "F1725D6B-D1F7-4BC3-8C35-20FAB53A1602": {// 认知途径
								List<Map<String, Object>> optionList = vCustomergwlistSelectMapper.SystemDictionaryXSRZTJList_Select();
								if (optionList!=null && optionList.size() > 0) {
									childItem.setValue(String.valueOf(optionList.get(0).get("Name")));
									childItem.setValueID(String.valueOf(optionList.get(0).get("ID")));
									String optionL = JSONArray.toJSONString(optionList);
									childItem.setOption(JSONArray.parseArray(optionL, OptionItem.class));
								}

								if (customerModeType
										.equals(CustomerModeType.分接_新机会_老客户
												.getTypeID())
										|| customerModeType
												.equals(CustomerModeType.分接_新机会_新客户_新潜在客户
														.getTypeID())
										|| customerModeType
												.equals(CustomerModeType.分接_新机会_新客户_老潜在客户
														.getTypeID())
										|| customerModeType
												.equals(CustomerModeType.顾问_新机会_老客户
														.getTypeID())
										|| customerModeType
												.equals(CustomerModeType.顾问_新机会_新客户_老潜在客户
														.getTypeID())
										|| customerModeType
												.equals(CustomerModeType.顾问_新机会_新客户_新潜在客户
														.getTypeID())) {
									childItem.setIsEdit(1);
								}
							}
								break;
							case "BDDBD5B0-C1D2-4D76-96B4-C88C51C46AC0": {// 认知媒体
								childItem.setOption(GetRZMTOptionList(model.getProjectID()));
							}
								break;
							case "480B60B2-1EE1-4A31-A810-072184A1E9D7":{// 跟进方式
								List<OptionItem> itemList = GetOptionList(childItem.getID(), false);
								if (itemList != null && jobCode.equals("GW")
										&& IsNoAllotRole == 0) {// 开启分接置业顾问只能录入来电、去电、问询、外展接待
									List<String> followIdList = new ArrayList<String>();
									followIdList.add("A79A1057-D4DC-497C-8C81-8F93E422C819");
									followIdList.add("F0942939-A90E-4915-81D7-7752919B0F72");
									followIdList.add("44775694-7C97-455C-B48E-154C6BFE2D94");
									followIdList.add("EEB32C04-5B7C-4676-A5DC-5F95E56370EB");

									List<OptionItem> re_option = new ArrayList<OptionItem>();

									// 模糊处
									for (OptionItem optionItem : itemList) {
										if (followIdList.contains(optionItem.getID())) {
											re_option.add(optionItem);
										}
									}
									childItem.setOption(re_option);
								} else {
									childItem.setOption(itemList);
								}
							}
								break;
							default:
								childItem.setOption(GetOptionList(childItem.getID(), false));
								break;
							}
						}
						for (DicInfo p : dicList) {
							if (p.getDicID().equals(childItem.getID())) {
								dicInfo = p;
								break;
							}
						}
						if (dicInfo != null) {// 字典存在
							childItem.setValue(StringUtils.isEmpty(dicInfo.getValue()) ? childItem.getValue(): dicInfo.getValue());
							childItem.setValueID(StringUtils.isEmpty(dicInfo.getValueID()) ? childItem.getValueID(): dicInfo.getValueID());
							if (childItem.getID().equals("8F6CC088-D5DD-48D2-BA3B-3A7AA4A1DB36")) {// 来访时间
								if (StringUtils.isEmpty(childItem.getValue().trim())) {
									childItem.setValue(DateUtil.format(new Date(), "yyyy/MM/dd HH:mm"));
									childItem.setValueID(DateUtil.format(new Date(), "yyyy/MM/dd HH:mm"));
								}
							}
							if (childItem.getID().equals("21685728-54C5-4268-8371-62413CE42841")) {// 主电话
								if (IsNoMobileVerify == 1) {
									childItem.setType("Text");
								}
								if (StringUtils.isEmpty(childItem.getValue().trim())) {
									childItem.setValue(model.getMobile());
								}
							}
							if (childItem.getID().equals("21685728-54C5-4268-8371-62413CE42832")) {// 副电话
								if (IsNoMobileVerify == 1) {
									childItem.setType("Text");
								}
							}
							if (childItem.getID().equals("61C9B9E1-B2DE-4112-B9B3-C87E23E581BC")) {// 客户级别
								if (IsNoCustomerRank == 1) {
									deleteItem.put(childItem.getID() + "_"+ panelIndex, childItem);
								}
							}
							if (childItem.getID().equals("A977C068-98A9-4184-AD39-5E645778CB5D")) {// 是否收小筹
								if (IsNoCustomerRank == 1) {
									deleteItem.put(childItem.getID() + "_"+ panelIndex, childItem);
								}
							}
							switch (customerModeType) {
							case "1":
								if (childItem.getID().equals("9134B1C9-B300-41CA-9FCD-D119661CB5F6")) {// 来访人数
									childItem.setValue("");
									childItem.setValueID("");
								}
								break;
							case "21":
								if (childItem.getID().equals("149F778D-4244-46F6-908F-D33A363A5B58")) {// 姓名
									childItem.setIsEdit(0);
								}
								if (childItem.getID().equals("21685728-54C5-4268-8371-62413CE42841")) {// 主手机
									childItem.setIsEdit(0);
								}
								if (childItem.getID().equals("21685728-54C5-4268-8371-62413CE42832")) {// 副手机
									childItem.setIsEdit(0);
								}
								if (childItem.getID().equals("E72C340D-4092-467A-9B8F-5138DBDCA43B")) {// 性别
									childItem.setIsEdit(0);
								}
								if (childItem.getID().equals("1FA31185-3B00-47AA-8F5E-7AA3D53205A5")) {// 置业顾问
									childItem.setValue(String.valueOf(CustomerObj.get("SaleUserName")));
									childItem.setValueID(String.valueOf(CustomerObj.get("SaleUserID")));
								}
								if (childItem.getID().equals("BB66EE25-E48E-4A7B-BA1D-DB03FBC87500")) {// 备注
									childItem.setIsEdit(0);
								}
								if (childItem.getID().equals("F1725D6B-D1F7-4BC3-8C35-20FAB53A1602")) {// 渠道客户初始化
									String where = " and ClueID='"+ customerModel.getClueID() + "'";
									List<Map<String, Object>>  ClueObjList = vCustomergwlistSelectMapper.sCustomerPotentialClue(where);
									Map<String, Object> ClueObj = null;
									if(ClueObjList!=null && ClueObjList.size()>0){
										ClueObj = ClueObjList.get(0);
									}
									if (ClueObj!=null && ClueObj.size() > 0) {
										childItem.setValue(String.valueOf(ClueObj.get("Name")));
										childItem.setValueID(String.valueOf(ClueObj.get("ID")));
									}
									childItem.setIsEdit(0);
								}
								break;
							case "23":
								if (childItem.getID().equals("9134B1C9-B300-41CA-9FCD-D119661CB5F6")) {// 来访人数
									childItem.setValue("");
									childItem.setValueID("");
								}
								if (childItem.getID().equals("4D35ABCF-E61C-4650-9F55-0D2D66548CF0")) {// 备注
									childItem.setValue("");
									childItem.setValueID("");
								}
								if (childItem.getID().equals("F1725D6B-D1F7-4BC3-8C35-20FAB53A1602")) {// 渠道客户初始化
									ChildItem clueOpportunitySource = GetClueOpportunitySource(childItem, model);
									childItem.setOption(clueOpportunitySource.getOption());
									childItem.setValue(clueOpportunitySource.getValue());
									childItem.setValueID(clueOpportunitySource.getValueID());
								}
								break;
							case "2":
								if (childItem.getID().equals("1FA31185-3B00-47AA-8F5E-7AA3D53205A5")) {// 置业顾问
									childItem.setValue(CustomerObj.getString("SaleUserName"));
									childItem.setValueID(CustomerObj.getString("SaleUserID"));
								}
								if (childItem.getID().equals("9134B1C9-B300-41CA-9FCD-D119661CB5F6")) {// 来访人数
									childItem.setValue("");
									childItem.setValueID("");
								}
								if (childItem.getID().equals("4D35ABCF-E61C-4650-9F55-0D2D66548CF0")) {// 备注
									childItem.setValue("");
									childItem.setValueID("");
								}
								if (childItem.getID().equals("F1725D6B-D1F7-4BC3-8C35-20FAB53A1602")) {// 渠道客户初始化
									ChildItem clueOpportunitySource = GetClueOpportunitySource(childItem, model);
									childItem.setOption(clueOpportunitySource.getOption());
									childItem.setValue(clueOpportunitySource.getValue());
									childItem.setValueID(clueOpportunitySource.getValueID());
								}
								break;
							case "3":
								if (childItem.getID().equals("9134B1C9-B300-41CA-9FCD-D119661CB5F6")) {// 来访人数
									childItem.setValue("");
									childItem.setValueID("");
								}
								if (childItem.getID().equals("4D35ABCF-E61C-4650-9F55-0D2D66548CF0")) {// 备注
									childItem.setValue("");
									childItem.setValueID("");
								}
								if (childItem.getID().equals("F1725D6B-D1F7-4BC3-8C35-20FAB53A1602")) {// 渠道客户初始化
									ChildItem clueOpportunitySource = GetClueOpportunitySource(childItem, model);
									childItem.setOption(clueOpportunitySource.getOption());
									childItem.setValue(clueOpportunitySource.getValue());
									childItem.setValueID(clueOpportunitySource.getValueID());
								}
								break;
							case "4":
								if (childItem.getID().equals("9134B1C9-B300-41CA-9FCD-D119661CB5F6")) {// 来访人数
									childItem.setValue("");
									childItem.setValueID("");
								}
								break;
							case "11":
								break;
							case "12":
								if (childItem.getID().equals("4D35ABCF-E61C-4650-9F55-0D2D66548CF0")) {// 备注
									childItem.setValue("");
									childItem.setValueID("");
								}
								// if (IsNoAllotRole == 1)
								// {//项目没有开启分接角色,启用客户来源选择
								// }
								// 取消分接判断
								if (childItem.getID().equals("F1725D6B-D1F7-4BC3-8C35-20FAB53A1602")) {// 渠道客户初始化
									ChildItem clueOpportunitySource = GetClueOpportunitySource(childItem, model);
									childItem.setOption(clueOpportunitySource.getOption());
									childItem.setValue(clueOpportunitySource.getValue());
									childItem.setValueID(clueOpportunitySource.getValueID());
								}
								break;
							case "13":
								if (childItem.getID().equals("4D35ABCF-E61C-4650-9F55-0D2D66548CF0")) {// 备注
									childItem.setValue("");
									childItem.setValueID("");
								}
								// if (IsNoAllotRole == 1)
								// {//项目没有开启分接角色,启用客户来源选择
								// }
								// 取消分接判断
								if (childItem.getID().equals("F1725D6B-D1F7-4BC3-8C35-20FAB53A1602")) {// 渠道客户初始化
									ChildItem clueOpportunitySource = GetClueOpportunitySource(childItem, model);
									childItem.setOption(clueOpportunitySource.getOption());
									childItem.setValue(clueOpportunitySource.getValue());
									childItem.setValueID(clueOpportunitySource.getValueID());
								}
								break;
							case "14":
								break;
							case "82":
								if (childItem.getID().equals("61C9B9E1-B2DE-4112-B9B3-C87E23E581BC")) {// 客户级别处理,如果是3级,是否收小筹,不可以编辑
									childItem.setIsEdit(0);
								}
								break;
							case "99":
								break;
							default:
								break;
							}
							// 关联权益人时
							if (model.getIsEquity()!=null && model.getIsEquity().equals("1")) {
								// 初始化手机号
								if (childItem.getID().equals("21685728-54C5-4268-8371-62413CE42841")) {
									childItem.setValue(StringUtils.isEmpty(childItem.getValue().trim()) ? model.getMobile() : childItem.getValue());
								}
								// 除了客户标签,备注非必填,下次跟进 其他都必填
								if (!childItem.getID().equals("47A4BAF0-F946-4A8A-81F1-D675BEED6DE2") && !childItem.getID().equals("4D35ABCF-E61C-4650-9F55-0D2D66548CF0") && !childItem.getID().equals("7E6CAE73-F032-4E3A-9551-C6F7DA2AEC10")) {
									childItem.setIsMust(1);
									childItem.setPlaceholder("必填");
								} else {
									childItem.setIsMust(0);
								}
								childItem.setIsHide(0);
								switch (customerModeType) {
								case "82":
									if (!childItem.getID().equals("480B60B2-1EE1-4A31-A810-072184A1E9D7") && !childItem.getID().equals("08289FD5-999A-4A9F-94D5-B85507575404") && !childItem.getID().equals("600DEB36-F5E0-4BA3-B7FA-1A244F0773AB") && !childItem.getID().equals("7E6CAE73-F032-4E3A-9551-C6F7DA2AEC10")) {
										if (childItem.getIsMustShow() == 0) {// 非必有
											if (!StringUtils.isEmpty(childItem.getValueID()) && !StringUtils.isEmpty(childItem.getValue())) {// 隐藏已填写选项
												childItem.setIsHide(1);
											} else {
												childItem.setIsHide(0);
											}
										}
									}
									break;
								default:
									break;
								}
							} else {
								switch (customerModeType) {
								case "82":
									if (!"0".equals(model.getIsCustomerFirstEdit())) {// 非首次访问
										if (!childItem.getID().equals("480B60B2-1EE1-4A31-A810-072184A1E9D7") && !childItem.getID().equals("08289FD5-999A-4A9F-94D5-B85507575404") && !childItem.getID().equals("600DEB36-F5E0-4BA3-B7FA-1A244F0773AB") && !childItem.getID().equals("7E6CAE73-F032-4E3A-9551-C6F7DA2AEC10")) {
											if (childItem.getIsMustShow() == 0) {// 非必有
												if (!StringUtils.isEmpty(childItem.getValueID()) && !StringUtils.isEmpty(childItem.getValue())) {// 隐藏已填写选项
													childItem.setIsHide(1);
												} else {
													childItem.setIsHide(0);
												}
												if (childItem.getID().equals("0BFD0AEF-2C16-4A91-94D3-9CBF114DC78A") && StringUtils.isEmpty(childItem.getValue())) {// 媒体子类
													childItem.setIsHide(1);
												}
											}
										}
									} else {// 首访
											// 取消首访
											// if (childItem.ID ==
											// "480B60B2-1EE1-4A31-A810-072184A1E9D7")
											// {//跟进方式
											// deleteItem.Add(childItem.ID + "_"
											// + panelIndex, childItem);
											// }
											// if (childItem.ID ==
											// "600DEB36-F5E0-4BA3-B7FA-1A244F0773AB")
											// {//跟进内容
											// deleteItem.Add(childItem.ID + "_"
											// + panelIndex, childItem);
											// }
									}
									break;
								default:
									break;
								}
							}
							if (childItem.getID().equals("F1725D6B-D1F7-4BC3-8C35-20FAB53A1602")) {// 渠道客户初始化
								if (childItem.getOption()!=null && childItem.getOption().size() <= 1) {
									childItem.setIsEdit(0);
								}
							}
							if (childItem.getID().equals("480B60B2-1EE1-4A31-A810-072184A1E9D7")) {// 跟进方式
								childItem.setValue("");
								childItem.setValueID("");
							}
							if (childItem.getID().equals("600DEB36-F5E0-4BA3-B7FA-1A244F0773AB")) {// 跟进内容
								childItem.setValue("");
								childItem.setValueID("");
							}
						} else {// 字典不存在
								// childItem.Value = "";
								// childItem.ValueID = "";
						}
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
		} catch (Exception ex) {
			throw ex;
		}
		return customerModel;
	}

	@Override
	public CustomerModelVo InitCustomerModelByFileName(String jsonFile) {
		String jsonStr = "";
		if (redisTemplate.hasKey(jsonFile)) {
			jsonStr = redisTemplate.opsForValue().get(jsonFile);
		} else {
			jsonStr = JSONUtil.readJsonFile(jsonFile);
			redisTemplate.opsForValue().set(jsonFile, jsonStr);
		}
		CustomerModelVo customerModelVo = JSONObject.parseObject(jsonStr,CustomerModelVo.class);
		return customerModelVo;
	}
	
	@Override
	public List<DicInfo> InitCustomerDicModel(String jsonFile) {
		String jsonStr = "";
		if (redisTemplate.hasKey(jsonFile)) {
			jsonStr = redisTemplate.opsForValue().get(jsonFile);
		} else {
			jsonStr = JSONUtil.readJsonFile(jsonFile);
			redisTemplate.opsForValue().set(jsonFile, jsonStr);
		}
		List<DicInfo> list = JSONArray.parseArray(jsonStr, DicInfo.class);
		return list;
	}

	@Override
	public List<OptionItem> GetRZMTOptionList(String projectID) {
		List<OptionItem> list = new ArrayList<OptionItem>();
		try {
			if (list == null || list.size() == 0) {
				list = new ArrayList<OptionItem>();
				List<Map<String, Object>> jarry = vCustomergwlistSelectMapper.SystemDictionaryRZMTList_Select(projectID);
				if (jarry!=null && jarry.size() > 0) {
					JSONObject PIDObject = new JSONObject();
					for (Map<String, Object> item : jarry) {
						String PID = String.valueOf(item.get("PID"));
						String PName = String.valueOf(item.get("PName"));
						String ID = String.valueOf(item.get("ID"));
						String Name = String.valueOf(item.get("Name"));
						if (PIDObject.get(PID) == null) {
							PIDObject.put(PID, PID);
							OptionItem optionItem = new OptionItem();
							optionItem.setID(PID);
							optionItem.setName(PName);
							optionItem.setChild(new ArrayList<OptionItem>());
							list.add(optionItem);

						}
						if (!StringUtils.isEmpty(ID)) {
							list.get(list.size() - 1).setIsSubMust(1);
							OptionItem optionItem = new OptionItem();
							optionItem.setID(ID);
							optionItem.setName(Name);
							optionItem.setChild(new ArrayList<OptionItem>());
							list.get(list.size() - 1).getChild().add(optionItem);
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<OptionItem> GetOptionList(String pid, Boolean isall) {
		List<OptionItem> list = new ArrayList<OptionItem>();
		try {
			if (list == null || list.size() == 0) {
				List<Map<String, Object>> jarry = new ArrayList<Map<String, Object>>();
				if (isall) {
					jarry = vCustomergwlistSelectMapper.DictionaryAllList_Select(pid);
				} else {
					jarry = vCustomergwlistSelectMapper.DictionaryList_Select(pid);
				}
				if (jarry!=null && jarry.size() > 0) {
					for (Map<String, Object> item : jarry) {
						OptionItem optionItem = new OptionItem();
						optionItem.setID(String.valueOf(item.get("ID")));
						optionItem.setName(String.valueOf(item.get("DictName")));
						list.add(optionItem);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ChildItem GetClueOpportunitySource(ChildItem childItem,
			CSearchModelVo model) {
		String where = "";
		if (!StringUtils.isEmpty(model.getCustomerPotentialID())) {
			where = " and( Status = 1 or (Status=2 and '1' not in  (select top 1 '1' as c   from  B_Opportunity where clueid='"
					+ model.getCustomerPotentialID()
					+ "' and ProjectID='"
					+ model.getProjectID()
					+ "')) )   and Isdel = 0 and ID ='"
					+ model.getCustomerPotentialID()
					+ "' and IntentProjectID='"
					+ model.getProjectID()
					+ "' order by Status ASC,CreateTime DESC";
		} else {
			where = " and( Status = 1 or (Status=2 and '1' not in  (select top 1 '1' as c   from  B_Opportunity where CustomerMobile='"
					+ model.getMobile()
					+ "' and ProjectID='"
					+ model.getProjectID()
					+ "' AND Status != 6)) )   and Isdel = 0 and CustomerMobile ='"
					+ model.getMobile()
					+ "' and IntentProjectID='"
					+ model.getProjectID()
					+ "' order by Status ASC,CreateTime DESC";
		}
		List<Map<String, Object>> optionList_data = vCustomergwlistSelectMapper.sCustomerPotentialClue(where);
		Map<String, Object> optionList = null;
		if(optionList_data!=null && optionList_data.size()>0){
			optionList = optionList_data.get(0);
		}
		if (optionList!=null && optionList.size() > 0) {// 存在线索
			List<OptionItem> ClueList = new ArrayList<OptionItem>();
			Number number = (Number)optionList.get("RuleType");
			int RuleType = number.intValue();
			Boolean HasChoose = false;
			if (RuleType == 1) {// 竞争带看规则线索
				for (OptionItem item : childItem.getOption()) {
					if (item.getID().equals(
							"0390CD8C-D6D4-4C92-995B-08C7E18E6EC2")) {// 增加自然到访
						HasChoose = true;
						ClueList.add(item);
						break;
					}
				}
			}
			if (!HasChoose) {
				Number numb = (Number)optionList.get("IsChoose");
				HasChoose = numb.intValue() == 1 ? true: false;
				OptionItem optionItem = new OptionItem();
				optionItem.setID(String.valueOf(optionList.get("ID")));
				optionItem.setName(String.valueOf(optionList.get("Name")));
				optionItem.setIsChoose(numb.intValue());
				ClueList.add(optionItem);
			}
			if (HasChoose) {
				childItem.setValue(ClueList.get(0).getName());
				childItem.setValueID(ClueList.get(0).getID());
			} else {
				childItem.setValue("");
				childItem.setValueID("");
				if (RuleType != 1) {
					for (OptionItem item : childItem.getOption()) {
						if (item.getID().equals(
								"0390CD8C-D6D4-4C92-995B-08C7E18E6EC2")) {// 增加自然到访
							ClueList.add(0, item);
							childItem.setValue(item.getName());
							childItem.setValueID(item.getID());
							break;
						}
					}
				}
			}
			childItem.setOption(ClueList);
		}
		return childItem;
	}

	@Override
	public void SyncCustomer(String opportunityID, int optionType) {
		try {
			// 如果开启销支，验证客储等级
			Map<String, Object> objRank = vCustomergwlistSelectMapper.mCustomerAllotRoleAndRank_Select(opportunityID);
			if (objRank!=null && objRank.size() == 0) {
				if (optionType == 0 || optionType == 1) {
					String where = "  AND OpportunityID ='" + opportunityID+ "' ";
					Map<String, Object> customerObj = vCustomergwlistSelectMapper.OpportunityDetail_Select(where);
					String CustomerID = String.valueOf(customerObj.get("CustomerID"));
					JSONObject Parameter = new JSONObject();
					Parameter.put("CustomerID", CustomerID);
					Result re = iMYService.CustomerDetail_Insert(Parameter);
					System.out.println("明源同步1"+re.getErrmsg());
				}
				if (optionType == 0 || optionType == 2) {
					JSONObject Parameter = new JSONObject();
					Parameter.put("OpportunityID", opportunityID);
					Result re = iMYService.OpportunityDetail_Insert(Parameter);
					System.out.println("明源同步2"+re.getErrmsg());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public JSONObject GetParameters(CGWDetailModel model) {
		JSONObject json = new JSONObject();
		List<DicInfo> dicList = InitCustomerDicModel("CustomerDic.json");
		for (DicInfo dicInfo : dicList) {
			Item item = null;
			List<Item> itemList = model.getItemList();
			for (Item it : itemList) {
				if (it.getID().equals(dicInfo.getDicID())) {
					item = it;
				}
			}
			dicInfo.setValue(item != null ? item.getValue() : "");
			if(dicInfo.getValue()==null){
            	dicInfo.setValue("");
            }
			json.put(dicInfo.getFieldName(),dicInfo.getValue().replaceAll("'", ""));
		}
		json.put("OpportunityID", model.getOpportunityID());
		json.put("ClueID", model.getClueID());
		json.put("CustomerID", model.getCustomerID());
		json.put("ProjectID", model.getProjectID());
		json.put("JobID", model.getJobID());
		json.put("OrgID", model.getOrgID());
		json.put("UserID", model.getUserID());
		json.put("Editor", model.getUserID());
		return json;
	}
	

	@Override
	public JSONObject GetParameters(CGWDetailModel model, JSONObject obj) {
		JSONObject json = new JSONObject();
		if (obj != null && obj.size() > 0){
			List<DicInfo> dicList = InitCustomerDicModel("CustomerDic.json");
            for (DicInfo dicInfo : dicList){
                List<Item> itemList = model.getItemList();
                Item item = null;
    			for (Item it : itemList) {
    				if (it.getID().equals(dicInfo.getDicID())) {
    					item = it;
    				}
    			}
                if (item == null){
                	//字典不包含参数则初始化历史值补空
                    dicInfo.setValue(obj.getString(dicInfo.getFieldName()));
                }else{
                	//字典包含参数 参数值赋值到对应字典值
                    dicInfo.setValue(item.getValue());
                }
                if(dicInfo.getValue()==null){
                	dicInfo.setValue("");
                }
                json.put(dicInfo.getFieldName(),dicInfo.getValue().replaceAll("'", ""));
            }
            json.put("OpportunityID", model.getOpportunityID());
    		json.put("CustomerID", model.getCustomerID());
    		json.put("ProjectID", model.getProjectID());
    		json.put("JobID", model.getJobID());
    		json.put("OrgID", model.getOrgID());
    		json.put("UserID", model.getUserID());
    		json.put("Editor", model.getUserID());
            return json;
        }else{
            return GetParameters(model);
        }
	}
	

	@Override
	public JSONObject CustomerOpportunityExist(String ProjectID, String Mobile) {
		JSONObject customerObj = new JSONObject();
		// 1.根据手机号项目ID码判断是否为机会客户
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("ProjectID", ProjectID);
			param.put("Mobile", Mobile);
			result = vCustomergwlistSelectMapper.CustomerOpportunity_Exist(param);
			if (result!=null && result.size() > 0) {
				customerObj.put("CustomerObj", new JSONObject(result));
				customerObj.put("status", true);
			} else {
				customerObj.put("status", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			customerObj.put("status", false);
		}
		return customerObj;
	}

	@Override
	public JSONObject CustomerExist(String Mobile) {
		JSONObject customerObj = new JSONObject();
        try{
        	List<Map<String, Object>> result = vCustomergwlistSelectMapper.Customer_Exist(Mobile);
        	if(result!=null && result.size()>0){
        		customerObj.put("status", true);
        		customerObj.put("CustomerObj", new JSONObject(result.get(0)));
        	}else{
        		customerObj.put("status", false);
        	}
        }catch (Exception e){
        	e.printStackTrace();
        	customerObj.put("status", false);
        }
        return customerObj;
	}

	@Override
	public Map<String, String> GetOpportunitySource(JSONObject parameter) {
		Map<String, String> map = new HashMap<String, String>();
		String ClueID = "";
        String opportunitySourceID = "";
        String OpportunitySource = parameter.getString("OpportunitySource").trim();
        List<OptionItem> option = GetOptionList("F1725D6B-D1F7-4BC3-8C35-20FAB53A1602", true);
        Boolean b = false;
        for(OptionItem optionItem : option){
        	if(optionItem.getID().equals(OpportunitySource)){
        		b=true;
        		break;
        	}
        }
        if (StringUtils.isEmpty(OpportunitySource) || OpportunitySource.equals("自然访客")){//自然访客
            opportunitySourceID = "0390CD8C-D6D4-4C92-995B-08C7E18E6EC2";
        }else if (b){//存在客户来源ID
            opportunitySourceID = OpportunitySource;
        }else{
            String where = " and  Status in (1,2) and Isdel = 0 and ClueID ='" + OpportunitySource + "'";
            List<Map<String, Object>> clueInfoList = vCustomergwlistSelectMapper.sCustomerPotentialClue(where);
    		Map<String, Object> clueInfo = null;
    		if(clueInfoList!=null && clueInfoList.size()>0){
    			clueInfo = clueInfoList.get(0);
    		}
            if (clueInfo!=null && clueInfo.size() == 0){//未找到线索则渠道仍然是自然来访
                opportunitySourceID = "0390CD8C-D6D4-4C92-995B-08C7E18E6EC2";
            }else{//找到线索则判断线索的渠道
                ClueID = OpportunitySource;
                opportunitySourceID = CareerConsCustConverter.GetOpportunitySourceByAdviserGroup(String.valueOf(clueInfo.get("AdviserGroupID")));
                if (StringUtils.isEmpty(opportunitySourceID)){//自然访客
                    opportunitySourceID = "0390CD8C-D6D4-4C92-995B-08C7E18E6EC2";
                }
            }
        }
        map.put("clueID", ClueID);
        map.put("opportunitySourceID", opportunitySourceID);
        return map;
	}

	
	@Override
	public void ClueUpdate(JSONObject parameter) {
        try{
            String clueID =parameter.getString("ClueID");

            if (!StringUtils.isEmpty(clueID)){ //|线索非空
                //1 验证是否存在已选线索
            	String projectID = parameter.getString("ProjectID");
            	List<Map<String,Object>> clueSiblingList = vCustomergwlistSelectMapper.sClueSiblingList_Select(clueID, projectID);
                if (clueSiblingList!=null && clueSiblingList.size() > 0){//1.1 Y存在已选线索
                    //1.1.2.1 Y存在多条线索， 则更新当前线索的潜在客户的其他线索置为失效状态 
                    ClueCustomerUpdate(parameter);
                }else{
                	//1.2 N不存在已选线索创建全新线索客户信息
                    parameter.put("ClueID", UUID.randomUUID().toString());
                    ClueCustomerInsert(parameter);
                }
            }else{//||线索空
                //1验证手机号码是否存在线索
                String mobile = parameter.getString("Mobile");
                String projectID =parameter.getString("ProjectID");
                List<Map<String,Object>> clueList = ClueCustomerList_Select(mobile, projectID);
                if (clueList!=null && clueList.size() == 0){//1.1 不存在手机号码客户创建全新线索
                    parameter.put("ClueID", UUID.randomUUID().toString());
                    ClueCustomerInsert(parameter);
                }else{
                    //1.2.2.1 全部手机客户的线索设置为失效
                    parameter.put("ClueID", clueList.get(0).get("ID"));
                    ClueCustomerFailureUpdate(parameter);
                    //1.2.2.2 新增全新线索
                    parameter.put("ClueID", UUID.randomUUID().toString());
                    ClueCustomerInsert(parameter);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
	}
	@Override
	@Transactional(readOnly=false)
	public Boolean ClueCustomerUpdate(JSONObject param){
        try{
        	Map<String,Object> pmap = JSONObject.parseObject(param.toJSONString(),Map.class);
        	vCustomergwlistSelectMapper.sClueCustomer_Update_step1(pmap);
            vCustomergwlistSelectMapper.sClueCustomer_Update_step2(pmap);
            vCustomergwlistSelectMapper.sClueCustomer_Update_step3(pmap);
            return true;
        }catch (Exception e){
        	e.printStackTrace();
        	throw new RuntimeException();
        }
    }
	@Override
	@Transactional(readOnly=false)
	public Boolean ClueCustomerInsert(JSONObject param){
        try{
        	Map<String,Object> pmap = JSONObject.parseObject(param.toJSONString(),Map.class);
        	List<Map<String,Object>> valid_1_map = vCustomergwlistSelectMapper.sClueCustomer_Insert_step1_valid_1(pmap);
        	if(valid_1_map==null || valid_1_map.size()==0){
        		vCustomergwlistSelectMapper.sClueCustomer_Insert_step1_insert_1(pmap);
        	}
        	List<Map<String,Object>> valid_2_map = vCustomergwlistSelectMapper.sClueCustomer_Insert_step1_valid_2(pmap);
        	if(valid_2_map==null || valid_2_map.size()==0){
        		vCustomergwlistSelectMapper.sClueCustomer_Insert_step1_insert_2(pmap);
        	}
        	vCustomergwlistSelectMapper.sClueCustomer_Insert_step2(pmap);
        	vCustomergwlistSelectMapper.sClueCustomer_Insert_step3(pmap);
            return true;
        }catch (Exception e){
        	e.printStackTrace();
        	throw new RuntimeException();
        }

    }
	@Override
	public List<Map<String,Object>> ClueCustomerList_Select(String Mobile, String ProjectID){
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
        try{
        	result = vCustomergwlistSelectMapper.sClue_Select(Mobile, ProjectID);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
	
	@Override
	@Transactional(readOnly=false)
	public Boolean ClueCustomerFailureUpdate(JSONObject param){
        try{
        	String clueID = param.getString("ClueID");
        	String projectID = param.getString("ProjectID");
        	vCustomergwlistSelectMapper.sClueCustomerFailure_Update(clueID, projectID);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

	@Override
	@Transactional(readOnly=false)
	public Result IntentProjectAdd(JSONObject paramAry) {
        Result entity = new Result();
        try{
            String StageIntentProjectID =paramAry.getString("StageIntentProjectID");
            String FollwUpWay = paramAry.getString("FollwUpWay");
            String[] IntentProjectID = StageIntentProjectID.split(",");
            if (FollwUpWay.equals("E30825AA-B894-4A5F-AF55-24CAC34C8F1F")){//来访
                paramAry.put("SalesStatus", 2);
            }else{
            	paramAry.put("SalesStatus", 1);
            }
            Map<String,Object> pmap = JSONObject.parseObject(paramAry.toJSONString(),Map.class);
            for (int i = 0; i < IntentProjectID.length; i++){
            	pmap.put("IntentProjectID", IntentProjectID[i]);
                Map<String,Object> re = vCustomergwlistSelectMapper.sStageCustomerAttachDetail_Insert_step1(pmap);
                int LastSalesStatus = 0;
                if(re!=null && re.size()>0){
                	Number SalesStatus = (Number)re.get("SalesStatus");
                	LastSalesStatus = SalesStatus.intValue();
                }
                List<Integer> list = Arrays.asList(new Integer[]{0, 4, 5, 6, 8});
                if(list.contains(LastSalesStatus)){
                	if(LastSalesStatus!=0){
                		pmap.put("SalesStatus", 3);
                	}
                	vCustomergwlistSelectMapper.sStageCustomerAttachDetail_Insert_step2(pmap);
                }else{
                	vCustomergwlistSelectMapper.sStageCustomerAttachDetail_Insert_step3(pmap);
                }
            }
        }catch (Exception e){
            entity.setErrcode(1);
            entity.setErrmsg("服务器异常！");
        }
        return entity;
	}
	
	@Override
	@Transactional(readOnly=false)
	public Boolean CustomerMobileSearch(String ProjectID,String Mobile,String JobCode,String UserID){
        try{
            Map<String,Object> pram = new HashMap<String, Object>();
            pram.put("ProjectID", ProjectID);
            pram.put("Mobile", Mobile);
            pram.put("JobCode", JobCode);
            pram.put("UserID", UserID);
            Map<String,Object> re1 = vCustomergwlistSelectMapper.CustomerMobileSearchDetail_Insert_step1(pram);
            String OpportunityID = "";
            if(re1!=null && re1.size()>0){
            	OpportunityID = re1.get("ID").toString();
            	pram.put("OpportunityID", OpportunityID);
            }
            vCustomergwlistSelectMapper.CustomerMobileSearchDetail_Insert_step2(pram);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
	@Override
	public Boolean ComeOverdueClue_Update(String ProjectID, String Mobile, String UserID, String OrgID, String JobID){
        //1.根据手机号项目ID码判断
        try{
        	Map<String,Object> pram = new HashMap<String, Object>();
        	pram.put("ProjectID", ProjectID);
        	pram.put("Mobile", Mobile);
        	pram.put("UserID", UserID);
        	pram.put("OrgID", OrgID);
        	pram.put("JobID", JobID);
        	vCustomergwlistSelectMapper.ComeOverdueClue_Update_step1(pram);
        	vCustomergwlistSelectMapper.ComeOverdueClue_Update_step2(pram);
        	return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

	@Override
	public int ClueExist(String ProjectID, String Mobile) {
        String where = " and( Status = 1 or (Status=2 and '1' not in  (select top 1 '1' as c   from  B_Opportunity where CustomerMobile='" + Mobile + "' and ProjectID='" + ProjectID + "' and Status<>6)) )   and Isdel = 0 and CustomerMobile ='" + Mobile + "' and IntentProjectID='" + ProjectID + "' order by Status ASC,CreateTime DESC";
        List<Map<String, Object>> optionList_data = vCustomergwlistSelectMapper.sCustomerPotentialClue(where);
		Map<String, Object> optionList = null;
		if(optionList_data!=null && optionList_data.size()>0){
			optionList = optionList_data.get(0);
		}
        if (optionList!=null && optionList.size() > 0){
        	Number number = (Number)optionList.get("RuleType");
            int RuleType = number.intValue();
            if (RuleType == 1){//竞争带看规则线索
                return 1;
            }else{
                return 0;
            }
        }else{
            return -1;
        }
	}

	@Override
	public Boolean GetProjectIsNoAllotRole(String projectId) {
		Map<String, Object> obj = vCustomergwlistSelectMapper.ProjectIsNoAllot_Select(projectId);
        if(obj!=null && obj.size()>0){
            Number num = (Number)obj.get("IsNoAllotRole");
            return num.intValue() == 0;//0.开启 1.关闭
        }else{
            return false;
        }
	}

	@Override
	public JSONObject CustomerPotentialExist(String Mobile) {
        JSONObject result = new JSONObject();
        try{
            Map<String,Object> re_map = vCustomergwlistSelectMapper.CustomerPotential_Exist(Mobile);
            if(re_map!=null && re_map.size()>0){
            	 result.put("CustomerObj", new JSONObject(re_map));
                 result.put("status", true);
            }else{
            	result.put("status", false);
            }
        }catch (Exception e){
        	result.put("status", false);
        	e.printStackTrace();
        }
        return result;
	}

	@Override
	public JSONObject OpportunityCustomer(String opportunityID) {
		//1.根据机会判断是否为机会客户
        try{
            Map<String,Object> result = vCustomergwlistSelectMapper.sCustomerGWSearch_Select(opportunityID);
            if (result!=null && result.size() > 0){
                String AllCustomer ="";
                if(result.get("AllCustomer")!=null){
                	AllCustomer = result.get("AllCustomer").toString();
                }
                if (StringUtils.isEmpty(AllCustomer)){
                    JSONObject j_o = new JSONObject();
                    j_o.put("CustomerID", result.get("CustomerID"));
                    j_o.put("CustomerName", result.get("CustomerName"));
                    j_o.put("CustomerMobile", result.get("CustomerName"));
                    JSONArray j_a = new JSONArray();
                    j_a.add(j_o);
                    result.put("SubscriberCustomer", j_a.toJSONString());
                }else{
                    result.put("SubscriberCustomer", AllCustomer);
                }
                result.put("SubscriberCustomerName", result.get("CustomerName"));
                return new JSONObject(result);
            }
        }catch (Exception e){
        	e.printStackTrace();
        }
        return null;
	}

	@Override
	public Boolean CustomerIsCanOperate(String opportunityID,String userID) {
        List<Map<String,Object>> obj = vCustomergwlistSelectMapper.mCustomerSalePartnerIsCanOperate_Select(opportunityID, userID);
        if (obj!=null && obj.size() > 0){
            return false;
        }
        return true;
	}

}
