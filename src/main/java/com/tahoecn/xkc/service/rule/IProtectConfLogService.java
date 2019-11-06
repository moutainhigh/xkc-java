package com.tahoecn.xkc.service.rule;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.rule.ProtectConfLog;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-08-13
 */
public interface IProtectConfLogService extends IService<ProtectConfLog> {

    List<Map<String, Object>> getProtectConfLogList(String projectName, String groupDictName, Integer protectSource, String ruleName, String editorName, Date start, Date end);
}
