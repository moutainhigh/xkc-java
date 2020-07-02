package com.tahoecn.xkc.service.mongo.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.tahoecn.xkc.mapper.mongo.FaceDetectCustomerMapper;
import com.tahoecn.xkc.model.mongo.FaceDetectCustomer;
import com.tahoecn.xkc.model.mongo.FaceDetectCustomerDetail;
import com.tahoecn.xkc.model.mongo.FaceDetectImageDatail;
import com.tahoecn.xkc.model.mongo.FaceDetectProjectThirdMapping;
import com.tahoecn.xkc.service.mongo.FaceDetectCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: 张晓东
 * @time: 2020/6/3 11:08
 */
@Service
public class FaceDetectCustomerServiceImpl implements FaceDetectCustomerService {

    @Autowired
    private FaceDetectCustomerMapper faceDetectCustomerMapper;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Map<String, Object> findCustomerInfo(String projectId, String idCard) {
        List<FaceDetectProjectThirdMapping> faceDetectProjectThirdMappings = this.mongoTemplate.find(new Query(Criteria.where("projectId").is(projectId)), FaceDetectProjectThirdMapping.class);
        List<FaceDetectCustomer> faceDetectCustomers = null;
        FaceDetectCustomerDetail faceDetectCustomerDetail = null;
        List<FaceDetectImageDatail> faceDetectImageDatails = null;
        FaceDetectProjectThirdMapping faceDetectProjectThirdMapping = null;
        if (null != faceDetectProjectThirdMappings && faceDetectProjectThirdMappings.size() > 0) {
            faceDetectProjectThirdMapping = faceDetectProjectThirdMappings.get(0);
            faceDetectCustomers = this.mongoTemplate.find(new Query(Criteria.where("projectId").is(faceDetectProjectThirdMappings.get(0).getProjectId()).and("idNumber").is(idCard)), FaceDetectCustomer.class);
            if (null != faceDetectCustomers && faceDetectCustomers.size() > 0) {
                faceDetectCustomerDetail = this.mongoTemplate.findById(faceDetectCustomers.get(0).getId(), FaceDetectCustomerDetail.class);
                if (null != faceDetectCustomerDetail && null != faceDetectCustomerDetail.getMatchFaces() && faceDetectCustomerDetail.getMatchFaces().size() > 0) {
                    faceDetectImageDatails = this.mongoTemplate.find(new Query(Criteria.where("faceToken").in(
                            (Set)faceDetectCustomerDetail.getMatchFaces().stream().map(i -> ((Map) i).get("faceToken").toString()).collect(Collectors.toSet())
                    )), FaceDetectImageDatail.class);
                }
            }
        }
        Map result = Maps.newHashMap();
        result.put("faceDetectCustomers", faceDetectCustomers);
        result.put("faceDetectCustomerDetail", faceDetectCustomerDetail);
        result.put("faceDetectImageDatails", faceDetectImageDatails);
        result.put("faceDetectProjectThirdMapping", faceDetectProjectThirdMapping);
        return result;
    }

    @Override
    public List<FaceDetectCustomer> scanFaceDetectCustomer(Date startTime, Date endTime) {
        return this.mongoTemplate.find(new Query(Criteria.where("freshCardTime").exists(true).gte(startTime).lte(endTime)), FaceDetectCustomer.class);
    }

    @Override
    public Map<String, Object> firstFace(Map<String, Object> customerInfo) {
        Map result = new HashMap();
        result.put("firstFaceTime", null);
        result.put("imgUrl", null);
        result.put("videoUrl", null);
        if (null == customerInfo.get("faceDetectCustomers") || ((List) customerInfo.get("faceDetectCustomers")).size() <= 0 ||
                null == customerInfo.get("faceDetectCustomerDetail") || null == customerInfo.get("faceDetectImageDatails") ||
                ((List) customerInfo.get("faceDetectImageDatails")).size() <= 0) return result;
        AtomicReference<Date> firstFaceTime = new AtomicReference();
        AtomicReference<String> imgUrl = new AtomicReference();
        AtomicReference<String> videoUrl = new AtomicReference();
        ((List) customerInfo.get("faceDetectImageDatails")).stream().forEach(i -> {
            FaceDetectImageDatail faceDetectImageDatail = (FaceDetectImageDatail) i;
            if (null == firstFaceTime.get()) {
                firstFaceTime.set(faceDetectImageDatail.getSnapDate());
                imgUrl.set(faceDetectImageDatail.getPanoramicView());
                videoUrl.set(faceDetectImageDatail.getVideo());
            } else if (faceDetectImageDatail.getSnapDate().compareTo(firstFaceTime.get()) < 0) {
                firstFaceTime.set(faceDetectImageDatail.getSnapDate());
                imgUrl.set(faceDetectImageDatail.getPanoramicView());
                videoUrl.set(faceDetectImageDatail.getVideo());
            }
        });
        result.put("firstFaceTime", firstFaceTime.get());
        result.put("imgUrl", imgUrl.get());
        result.put("videoUrl", videoUrl.get());
        return result;
    }

    @Override
    public Map<String, Object> findCustomerInfoOrXkcProjectId(String projectId, String idCard) {
        List<FaceDetectProjectThirdMapping> faceDetectProjectThirdMappings = this.mongoTemplate.find(new Query(Criteria.where("outerProjectId").is(projectId)), FaceDetectProjectThirdMapping.class);
        List<FaceDetectCustomer> faceDetectCustomers = null;
        FaceDetectCustomerDetail faceDetectCustomerDetail = null;
        List<FaceDetectImageDatail> faceDetectImageDatails = null;
        if (null != faceDetectProjectThirdMappings && faceDetectProjectThirdMappings.size() > 0) {
            faceDetectCustomers = this.mongoTemplate.find(new Query(Criteria.where("projectId").is(faceDetectProjectThirdMappings.get(0).getProjectId()).and("idNumber").is(idCard)), FaceDetectCustomer.class);
            if (null != faceDetectCustomers && faceDetectCustomers.size() > 0) {
                faceDetectCustomerDetail = this.mongoTemplate.findById(faceDetectCustomers.get(0).getId(), FaceDetectCustomerDetail.class);
                if (null != faceDetectCustomerDetail && null != faceDetectCustomerDetail.getMatchFaces() && faceDetectCustomerDetail.getMatchFaces().size() > 0) {
                    faceDetectImageDatails = this.mongoTemplate.find(new Query(Criteria.where("faceToken").in(
                            (Set)faceDetectCustomerDetail.getMatchFaces().stream().map(i -> ((Map) i).get("faceToken").toString()).collect(Collectors.toSet())
                    )), FaceDetectImageDatail.class);
                }
            }
        }
        Map result = Maps.newHashMap();
        result.put("faceDetectCustomers", faceDetectCustomers);
        result.put("faceDetectCustomerDetail", faceDetectCustomerDetail);
        result.put("faceDetectImageDatails", faceDetectImageDatails);
        return result;
    }
}
