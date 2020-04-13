package com.tahoecn.xkc.service.sys;

/**
 * @author mystic
 */
public interface ISyncCustomerService {

    /**
     * 根据客户ID更新白名单
     * @param customerID
     * @param customerMobile
     * @param editor
     */
    void updateByCustomerID(String customerID, String customerMobile, String editor);

    /**
     * 保存至白名单
     * @param customerID
     * @param customerMobile
     * @param editor
     */
    void save(String customerID, String customerMobile, String editor);

    /**
     * 根据客户ID删除白名单客户
     * @param customerID
     * @param editor
     */
    void deleteByCustomerID(String customerID, String editor);
}
