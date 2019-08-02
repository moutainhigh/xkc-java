package com.tahoecn.xkc.service.quit.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.quit.BQuituserMapper;
import com.tahoecn.xkc.model.quit.BQuituser;
import com.tahoecn.xkc.service.quit.IBQuituserService;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-06-26
 */
@Service
public class BQuituserServiceImpl extends ServiceImpl<BQuituserMapper, BQuituser> implements IBQuituserService {

    @Autowired
    private BQuituserMapper BQuituserMapper;

    @Override
    public IPage<Map<String, Object>> QuitUserOwnTeamList_Select(IPage page, Map<String, Object> map) {
        setSqlWhere(map);
        return BQuituserMapper.QuitUserOwnTeamList_Select(page,map);
    }

    @Override
    public IPage<Map<String, Object>> QuitUserSalesTeamList_Select(IPage page, Map<String, Object> map) {

        StringBuffer sqlWhere = new StringBuffer();

        String Name = (String)map.get("Name");
        String Mobile = (String)map.get("Mobile");
        String UserName = (String)map.get("UserName");
        String TeamName = (String)map.get("TeamName");
        String ProjectID = (String)map.get("ProjectID");
        String IsDispose = (String)map.get("IsDispose");
        String OrgName = (String)map.get("OrgName");
        String IsExcel = (String)map.get("IsExcel");

        if(StringUtils.isNotEmpty(IsExcel) && "1".equals(IsExcel)) {
            //用户姓名
            if (StringUtils.isNotEmpty(Name)) {
                sqlWhere.append(" AND CU.Name LIKE '%" + Name + "%'");
            }
            //用户手机号
            if (StringUtils.isNotEmpty(Mobile)) {
                sqlWhere.append(" AND CU.Mobile LIKE '%" + Mobile + "%'");
            }
            //登录帐号
            if (StringUtils.isNotEmpty(UserName)) {
                sqlWhere.append(" AND CU.UserName LIKE '%" + UserName + "%'");
            }
            //机构名称
            if (StringUtils.isNotEmpty(OrgName)) {
                sqlWhere.append(" AND CO.OrgName LIKE '%" + UserName + "%'");
            }


            //是否处理
            if (StringUtils.isNotEmpty(IsDispose)) {
                if (IsDispose.equals("0")) {
                    sqlWhere.append(" AND QU.IsDispose=0");
                } else if (IsDispose.equals("1")) {
                    sqlWhere.append(" AND QU.IsDispose=1");
                }
            }
        }

        setSqlWhere(map);
        return BQuituserMapper.QuitUserSalesTeamList_Select(page,map);
    }

    @Override
    public IPage<Map<String, Object>> QuitUserChannelList_Select(IPage page, Map<String, Object> map) {
        StringBuffer sqlWhere = new StringBuffer();
        String Name = (String)map.get("Name");
        String Mobile = (String)map.get("Mobile");
        String UserName = (String)map.get("UserName");
        String TeamName = (String)map.get("TeamName");
        String ProjectID = (String)map.get("ProjectID");
        String IsDispose = (String)map.get("IsDispose");
        String OrgName = (String)map.get("OrgName");
        String IsExcel = (String)map.get("IsExcel");
        //用户姓名
        if (StringUtils.isNotEmpty(Name)) {
            sqlWhere.append(" AND CU.Name LIKE '%" + Name + "%'");
        }
        //用户手机号
        if (StringUtils.isNotEmpty(Mobile)) {
            sqlWhere.append(" AND CU.Mobile LIKE '%" + Mobile + "%'");
        }
        //登录帐号
        if (StringUtils.isNotEmpty(UserName)) {
            sqlWhere.append(" AND CU.UserName LIKE '%" + UserName + "%'");
        }
        //机构名称
        if (StringUtils.isNotEmpty(OrgName)) {
            sqlWhere.append(" AND CO.OrgName LIKE '%" + UserName + "%'");
        }
        //是否处理
        if (StringUtils.isNotEmpty(IsDispose)) {
            if (IsDispose.equals("0")) {
                sqlWhere.append(" AND QU.IsDispose=0");
            } else if (IsDispose.equals("1")) {
                sqlWhere.append(" AND QU.IsDispose=1");
            }
        }
        map.put("sqlWhere",sqlWhere.toString());
        return BQuituserMapper.QuitUserChannelList_Select(page,map);
    }

    private void setSqlWhere(Map<String, Object> map) {
        StringBuffer sqlWhere = new StringBuffer();

        String Name = (String)map.get("Name");
        String Mobile = (String)map.get("Mobile");
        String UserName = (String)map.get("UserName");
        String TeamName = (String)map.get("TeamName");
        String ProjectID = (String)map.get("ProjectID");
        String IsDispose = (String)map.get("IsDispose");
        //用户姓名
        if (StringUtils.isNotEmpty(Name))
        {
            sqlWhere.append(" AND A.EmployeeName LIKE '%"+Name+"%'");
        }
        //用户手机号
        if (StringUtils.isNotEmpty(Mobile))
        {
            sqlWhere.append(" AND A.Mobile LIKE '%"+Mobile+"%'");
        }
        //登录帐号
        if (StringUtils.isNotEmpty(UserName))
        {
            sqlWhere.append(" AND A.UserName LIKE '%"+UserName+"%'");
        }
        //机构名称
        if (StringUtils.isNotEmpty(TeamName))
        {
            sqlWhere.append(" AND SG.Name LIKE '%"+TeamName+"%'");
        }
        //项目ID
        if (StringUtils.isNotEmpty(ProjectID))
        {
            sqlWhere.append(" AND SG.ProjectID LIKE '%"+ProjectID+"%' ");
        }
        //是否处理
        if (StringUtils.isNotEmpty(IsDispose))
        {
            if (IsDispose.equals("0"))
            {
                sqlWhere.append(" AND QU.IsDispose=0");
            }
            else if (IsDispose.equals("1"))
            {
                sqlWhere.append(" AND QU.IsDispose=1");
            }
        }
        map.put("sqlWhere",sqlWhere.toString());
    }

}
