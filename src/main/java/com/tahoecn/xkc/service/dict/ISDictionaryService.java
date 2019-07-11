package com.tahoecn.xkc.service.dict;

import com.tahoecn.xkc.model.dict.SDictionary;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-06-27
 */
public interface ISDictionaryService extends IService<SDictionary> {

    List<Map<String, String>> AgenCertificatesList_SelectN();

    List<Map<String,Object>> ListByCode_Select(String dictCodes);

    boolean SystemParam_Delete(String id);

    boolean SystemParam_Update(SDictionary dictionary);
}
