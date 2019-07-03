package com.tahoecn.xkc.service.project;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.project.ABrokerprojecthouseimg;

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
public interface IABrokerprojecthouseimgService extends IService<ABrokerprojecthouseimg> {

    List<Map<String, String>> HouseImgList(String brokerProjectID);
}
