package com.tahoecn.xkc.mapper.mongo;

import com.tahoecn.xkc.model.mongo.FaceDetectCustomer;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @description:
 * @author: 张晓东
 * @time: 2020/6/3 11:02
 */
public interface FaceDetectCustomerMapper extends MongoRepository<FaceDetectCustomer, String> {
}
