package com.tahoecn.xkc.service.miniprogram;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.core.json.JSONResult;
import com.tahoecn.xkc.model.channel.BChanneluser;
import com.tahoecn.xkc.model.miniprogram.vo.RelationshipVO;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 思为小程序组织关系服务类
 * </p>
 *
 * @author zhaoyan
 * @since 2020-04-01
 */
public interface IRelationshipService extends IService<BChanneluser> {

    /**
     * 获取组织角色
     *
     * @param request
     * @param relationshipVO
     * @return
     * @throws Exception
     */
    public JSONResult getOrgRole(HttpServletRequest request, RelationshipVO relationshipVO) throws Exception;

    /**
     * 获取人员项目
     *
     * @param request
     * @param relationshipVO
     * @return
     * @throws Exception
     */
    public JSONResult getProjectPerson(HttpServletRequest request, RelationshipVO relationshipVO) throws Exception;

    /**
     * 获取组织人员
     *
     * @param request
     * @return
     * @throws Exception
     */
    public JSONResult getOrgPerson(HttpServletRequest request) throws Exception;

    /**
     * 获取项目
     *
     * @param request
     * @return
     * @throws Exception
     */
    public JSONResult getProject(HttpServletRequest request) throws Exception;

    /**
     * 获取角色
     *
     * @param request
     * @param relationshipVO
     * @return
     * @throws Exception
     */
    public JSONResult getRole(HttpServletRequest request, RelationshipVO relationshipVO) throws Exception;

    /**
     * 获取人员
     *
     * @param request
     * @param relationshipVO
     * @return
     * @throws Exception
     */
    public JSONResult getPerson(HttpServletRequest request, RelationshipVO relationshipVO) throws Exception;

    /**
     * 获取客户
     *
     * @param request
     * @param relationshipVO
     * @return
     * @throws Exception
     */
    public JSONResult getCustomer(HttpServletRequest request, RelationshipVO relationshipVO) throws Exception;

    /**
     * 获取中介机构信息
     *
     * @param request
     * @return
     * @throws Exception
     */
    public JSONResult getIntermediaryAgency(HttpServletRequest request) throws Exception;
}
