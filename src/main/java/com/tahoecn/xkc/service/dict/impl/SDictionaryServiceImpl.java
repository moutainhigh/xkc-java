package com.tahoecn.xkc.service.dict.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.dict.SDictionaryMapper;
import com.tahoecn.xkc.model.dict.SDictionary;
import com.tahoecn.xkc.service.dict.ISDictionaryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Override
    public List<Map<String, String>> AgenCertificatesList_SelectN() {
        return baseMapper.AgenCertificatesList_SelectN();
    }

    @Override
    public List<Map<String,Object>> ListByCode_Select(String dictCodes) {
        return baseMapper.ListByCode_Select(dictCodes);
    }
}
