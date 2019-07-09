package com.tahoecn.xkc.service.customer.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.customer.VCustomerfjlistSelectMapper;
import com.tahoecn.xkc.model.customer.VCustomerfjlistSelect;
import com.tahoecn.xkc.service.customer.IVCustomerfjlistSelectService;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 2 服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-08
 */
@Service
public class VCustomerfjlistSelectServiceImpl extends ServiceImpl<VCustomerfjlistSelectMapper, VCustomerfjlistSelect> implements IVCustomerfjlistSelectService {

	/**
	 * 客户列表
	 */
	@Override
	public List<VCustomerfjlistSelect> CustomerList(Map<String, Object> pa) {
//        CPageModel model = paramAry.ToObject<CPageModel>();
        StringBuffer whereSb = new StringBuffer();
        StringBuffer orderSb = new StringBuffer();
//        boolean isMobile = Regex.IsMatch(model.Mobile, @"^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$");
        //手机/或姓名
        if (!StringUtils.isEmpty(pa.get("Mobile"))){
            whereSb.append(" and (CustomerName Like'%"+pa.get("Mobile")+"%' or CustomerMobile Like'%"+pa.get("Mobile")+"%') ");
        }
        //今日来访
        if (!StringUtils.isEmpty(pa.get("Filter"))){
            if (pa.get("Filter").equals("42464192-8347-2067-CDA9-A5D45CA0EBE7")){
                whereSb.append(" and  isday =0 ");
            }
            if (pa.get("Filter").equals("40FEA720-8014-6232-2C66-220F37B68E42")){
                whereSb.append(" and  isday =1 ");
            }
        }else{
            whereSb.append(" and  isday =0 ");
        }
        //排序
        if (!StringUtils.isEmpty(pa.get("Sort"))){
            if (pa.get("Sort").equals("7D3688E3-0857-B27D-360D-07C53D20DAD8")){//最新来访
                orderSb.append(" Order By visitingTime desc ");
            }else{//最早来访
                orderSb.append(" Order By visitingTime asc");
            }
        }else{
            orderSb.append(" Order By visitingTime desc ");

        }
        pa.put("WHERE", whereSb.toString());
        pa.put("ORDER", orderSb.toString());

        return baseMapper.sCustomerFJList_Select(pa);
	}

}
