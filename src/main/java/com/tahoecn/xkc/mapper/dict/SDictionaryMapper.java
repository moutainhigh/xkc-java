package com.tahoecn.xkc.mapper.dict;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.dict.SDictionary;
import org.apache.ibatis.annotations.Param;

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

    List<Map<String,Object>> ListByCode_Select(@Param("DictCodes") String dictCodes);

    List<Map<String, Object>> list(@Param("pid") String pid);

    List<Map<String, Object>> getMediaLargeList();

    List<Map<String, Object>> getMediaChildList(@Param("pid")String pid,@Param("projectID")String projectID);
}
