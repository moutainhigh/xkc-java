package com.tahoecn.xkc.service.project;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.project.ABrokerprojectimg;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-02
 */
public interface IABrokerprojectimgService extends IService<ABrokerprojectimg> {

    List<Map<String, String>> TopImgList(String brokerProjectID);
}
