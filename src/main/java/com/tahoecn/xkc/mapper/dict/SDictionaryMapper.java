package com.tahoecn.xkc.mapper.dict;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.dict.SDictionary;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-06-27
 */
public interface SDictionaryMapper extends BaseMapper<SDictionary> {

    List<Map<String, String>> AgenCertificatesList_SelectN();
}
