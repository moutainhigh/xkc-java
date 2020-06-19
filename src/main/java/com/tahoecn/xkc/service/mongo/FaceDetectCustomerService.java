package com.tahoecn.xkc.service.mongo;

import com.tahoecn.xkc.model.mongo.FaceDetectCustomer;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: 张晓东
 * @time: 2020/6/3 11:07
 */
public interface FaceDetectCustomerService {

    /**
     * @param projectId 项目id
     * @param idCard    身份证号
     * @description: 过去客户详情数据
     * @return:
     * @author: 张晓东
     * @time: 2020/6/11 16:24
     */
    Map<String, Object> findCustomerInfo(String projectId, String idCard);

    /**
     * @param startTime
     * @param endTime
     * @description: 扫描刷证数据
     * @return:
     * @author: 张晓东
     * @time: 2020/6/11 16:25
     */
    List<FaceDetectCustomer> scanFaceDetectCustomer(Date startTime, Date endTime);


    /**
     * @description: 最早人脸数据
     * @return:
     * @author: 张晓东
     * @time: 2020/6/12 14:16
     */
    Map<String, Object> firstFace(Map<String, Object> customerInfo);

}
