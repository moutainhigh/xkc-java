package com.tahoecn.xkc.mapper.rule;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.rule.ProtectConfLog;
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
 * @since 2019-08-13
 */
public interface ProtectConfLogMapper extends BaseMapper<ProtectConfLog> {

    List<Map<String, Object>> getProtectConfLogList(@Param("projectName") String projectName, @Param("groupDictName")String groupDictName, @Param("protectSource")Integer protectSource, @Param("ruleName")String ruleName, @Param("editorName")String editorName, @Param("start")Date start, @Param("end")Date end);
}
