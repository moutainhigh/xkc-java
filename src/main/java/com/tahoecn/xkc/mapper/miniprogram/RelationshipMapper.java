package com.tahoecn.xkc.mapper.miniprogram;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.miniprogram.vo.RelationshipVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 项目-人员-组织关系 Mapper 接口
 * </p>
 *
 * @author zhaoyan
 * @since 2020-04-01
 */
public interface RelationshipMapper extends BaseMapper<RelationshipVO> {

    /**
     * 获取组织角色
     *
     * @param relationshipVO
     * @return
     */
    List<RelationshipVO> getOrgRole(@Param("relationship") RelationshipVO relationshipVO);

    /**
     * 获取组织角色
     *
     * @param projectId
     * @return
     */
    List<RelationshipVO> getOrgPerson1(@Param("projectId") String projectId);

    /**
     * 获取组织角色
     *
     * @param projectId
     * @return
     */
    List<RelationshipVO> getOrgPerson2(@Param("projectId") String projectId);

    /**
     * 获取组织角色
     *
     * @return
     */
    List<RelationshipVO> getOrgPerson3();

    /**
     * 获取项目
     *
     * @return
     */
    List<RelationshipVO> getProject(RelationshipVO relationshipVO);

    /**
     * 获取角色
     *
     * @param relationshipVO
     * @return
     */
    List<RelationshipVO> getRole(@Param("relationship") RelationshipVO relationshipVO);

    /**
     * 获取人员
     *
     * @param relationshipVO
     * @return
     */
    List<RelationshipVO> getPerson(@Param("relationship") RelationshipVO relationshipVO);

    /**
     * 获取客户
     *
     * @param relationshipVO
     * @return
     */
    List<Map> getCustomer1(@Param("relationship") RelationshipVO relationshipVO);

    /**
     * 获取客户
     *
     * @param relationshipVO
     * @return
     */
    List<Map> getCustomer2(@Param("relationship") RelationshipVO relationshipVO);

    /**
     * 获取中介机构
     *
     * @return
     */
    List<Map> getIntermediaryAgency();
}
