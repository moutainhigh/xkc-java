package com.tahoecn.xkc.service.customer.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.common.ReturnEntity;
import com.tahoecn.xkc.mapper.customer.VCustomergwlistSelectMapper;
import com.tahoecn.xkc.model.customer.VCustomergwlistSelect;
import com.tahoecn.xkc.model.dto.GWCustomerPageDto;
import com.tahoecn.xkc.model.dto.GWCustomerPageDto.FilterItem;
import com.tahoecn.xkc.service.customer.IVCustomergwlistSelectService;

/**
 * <p>
 * 1 服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-01
 */
@Service
public class VCustomergwlistSelectServiceImpl extends ServiceImpl<VCustomergwlistSelectMapper, VCustomergwlistSelect> implements IVCustomergwlistSelectService {

	@Override
	public ReturnEntity customerList(GWCustomerPageDto model) {
		ReturnEntity entity = new ReturnEntity();
        try {
        	StringBuilder whereSb = new StringBuilder();
        	StringBuilder orderSb = new StringBuilder();
        	if(!StringUtils.isEmpty(model.getKeyWord())){
        		whereSb.append(" and (CustomerName Like'"+model.getKeyWord()+"' or CustomerMobile Like'"+model.getKeyWord()+"') ");
        	}
        	if(model.getFilter() != null && model.getFilter().size()>0){
        		for(FilterItem filterItem:model.getFilter()){
        			if(filterItem!=null && filterItem.getTagID()!=null){
        				if(filterItem.getTagID().equals("3775DF2B-5BF4-F5A3-9D07-6B3B2549CE47")){
        					if(filterItem.getChild()!=null && filterItem.getChild().size()>0){
        						whereSb.append(" and  IsCare ='1'");
        					}
        				}
        				//客户状态
        				if(filterItem.getTagID().equals("DB251A8A-23BB-73B4-E586-FA67379EE732")){
        					if(filterItem.getChild()!=null && filterItem.getChild().size()>0){
        						for(int i = 0; i < filterItem.getChild().size(); i++){
        							switch (filterItem.getChild().get(i)) {
									case "17018427-C01B-0E2A-E62D-900B50EF2581":
										filterItem.getChild().add(i, "1");
										break;
									case "6A91A1AB-FC05-2BC0-8C08-0C3FCB07D865":
										filterItem.getChild().add(i, "2");
										break;
									case "3962D6C3-1141-F62F-2181-9F6AA82E0A6C":
										filterItem.getChild().add(i, "3");
										break;
									case "3089CC46-2736-2B3C-3B9C-6D44D059ACCD":
										filterItem.getChild().add(i, "4");
										break;
									case "E9C2D9B3-4538-CBDD-73DE-AEA7444DB20E":
										filterItem.getChild().add(i, "5");
										break;
									case "43A67B86-E775-158F-F897-30E78F5C8D4C":
										filterItem.getChild().add(i, "7");
										break;
									}
        						}
        						whereSb.append(" and OpportunityStatusValue in('" + String.join("','", filterItem.getChild()) + "')");
        					}
        				}
        				//意向级别
        				if(filterItem.getTagID().equals("9BB9797E-8A64-08D5-4754-4189D185D3A9")){
        					if(filterItem.getChild()!=null && filterItem.getChild().size()>0){
        						for(int i = 0; i < filterItem.getChild().size(); i++){
        							switch (filterItem.getChild().get(i)) {
									case "CB61E620-7589-C99B-F834-7EA3203D75AB":
										filterItem.getChild().add(i, "2A357E4A-90D7-5D69-C209-E26CFA5839FA");
										break;
									case "ABC6231B-225A-1EC6-8BD6-5165D911BD4E":
										filterItem.getChild().add(i, "DF2057E2-303B-1F14-4075-069668D3A3BE");
										break;
									case "55EE9A36-4A34-36C6-5B77-8BC1227A156C":
										filterItem.getChild().add(i, "FA35879A-CCE4-D332-0FAB-ADB57EBCAC9D");
										break;
									case "B8922623-E5E4-04BF-1A0B-DEE35CBC9A08":
										filterItem.getChild().add(i, "9CEA46E8-A3ED-409E-646C-F38A5EAC383E");
										break;
									case "AE005A89-BB77-2E81-A8BA-05CB619654B5":
										filterItem.getChild().add(i, "84640879-F4A7-CB87-E39B-F18070BCA568");
										break;
									}
        						}
        						whereSb.append(" and CustomerLevel in('" + String.join("','", filterItem.getChild()) + "')");
        					}
        				}
        				//标签
        				if(filterItem.getTagID().equals("432680C6-9872-0A1F-4BEA-3F1D0EE73095")){
        					if(filterItem.getChild()!=null && filterItem.getChild().size()>0){
        						whereSb.append(" and CustomerID in(select  CustomerID  from  B_CustomerTag  t where t.TagName in('" + String.join("','", filterItem.getChild()) + "'))");
        					}
        				}
        			}
        		}
        	}
        	
        	if(!StringUtils.isEmpty(model.getSort())){
        		if (model.getSort() == "F5F8788E-EB27-E49D-86D6-BCFAFC181493"){//最新跟进
                    orderSb.append(" ORDER BY  TheLatestFollowUpDate desc ");
                }
                if (model.getSort() == "7A5EDE88-5EBB-D5EB-C3F3-45959B855B14"){//最早跟进
                    orderSb.append(" ORDER BY  TheLatestFollowUpDate  asc ");
                }
                if (model.getSort() == "CC0DE53C-A4A7-FB72-36EE-6BFAA4820B5C"){//最新创建
                    orderSb.append(" ORDER BY  CreateTime desc ");
                }
                if (model.getSort() == "47C99D99-FBB2-F7DC-2384-5DE728E9D657"){//最早创建
                    orderSb.append(" ORDER BY  CreateTime asc ");
                }
        	}else{
        		orderSb.append(" ORDER BY  TheLatestFollowUpDate Desc ");
        	}
        	model.setWhere(whereSb.toString());
        	model.setOrder(orderSb.toString());
        	entity.setData(null);
        	entity.setErrMsg("成功");
		} catch (Exception e) {
			entity.setErrMsg("服务器异常");
			entity.setErrCode(1);
			e.printStackTrace();
		}
		return entity;
	}

}
