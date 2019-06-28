package com.tahoecn.xkc.mapper.sys;


import com.tahoecn.xkc.model.vo.UnreadCountVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-06-17
 */
public interface SystemMessageMapper {

    List<UnreadCountVo> UnreadCountListByMessageType_Select(@Param("projectId")String projectId, @Param("userId") String userId);
}
