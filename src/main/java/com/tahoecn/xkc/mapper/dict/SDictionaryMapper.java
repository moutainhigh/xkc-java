package com.tahoecn.xkc.mapper.dict;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tahoecn.xkc.model.dict.SDictionary;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
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

    List<Map<String, Object>> getMediaLargeList_New();

    List<Map<String, Object>> getMediaChildListByPid(@Param("pid")String pid,@Param("projectID")String projectID);

    List<Map<String, Object>> getMediaChildList(@Param("projectID")String projectID);

//    IPage<Map<String, Object>> getMediaLargeList(IPage page);

    IPage<Map<String, Object>> getMediaChildListByPid(IPage page,@Param("pid")String pid,@Param("projectID")String projectID);

    void updateMediaStatus(@Param("id")String id, @Param("status")int status);

    void saveMediaLarge(@Param("id")String id, @Param("dictName")String dictName, @Param("listIndex")int listIndex, @Param("creator")String creator);

    void saveMediaChild(@Param("id")String id, @Param("dictName")String dictName, @Param("listIndex")int listIndex,
                        @Param("creator")String creator,  @Param("projectID")String projectID, @Param("pid")String pid);
}
