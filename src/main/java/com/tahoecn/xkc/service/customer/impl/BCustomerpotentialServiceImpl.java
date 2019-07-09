package com.tahoecn.xkc.service.customer.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.customer.BCustomerpotentialMapper;
import com.tahoecn.xkc.model.customer.BCustomerpotential;
import com.tahoecn.xkc.service.customer.IBCustomerpotentialService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-06
 */
@Service
public class BCustomerpotentialServiceImpl extends ServiceImpl<BCustomerpotentialMapper, BCustomerpotential> implements IBCustomerpotentialService {

    @Override
    public String getOpportunitySourceByAdviserGroup(String adviserGroupID) {
        String res = "";
        switch (adviserGroupID)
        {
            case "46830C26-0E01-4041-8054-3865CCDD26AD"://自由经纪
                res = "798A45A6-9169-4E5C-BEE3-1CDB158F5D69";//自由经纪
                break;
            case "32C92DA0-DA13-4C21-A55E-A1D16955882C"://中介同行
                res = "E4DFA1D5-95F9-4D89-B754-E7CC81D58196";//分销中介
                break;
            case "EB4AD331-F4AD-46D6-889A-D45575ECEE66"://老业主
                res = "7F4E0089-E21D-0F97-DC48-0DBF0740367D";//老业主推荐
                break;
            case "725FA5F6-EC92-4DC6-8D47-A8E74B7829AD"://泰禾员工
                res = "BA06AE1D-E29A-4BC7-A811-A26E103B5E7E";//员工推荐
                break;
            case "0E88065E-AF3E-4905-8809-7BD30610323F"://外展
                res = "80D2A7B1-115A-4F3A-BB7D-F227F641C5F1";//外展
                break;
            case "DBCF76B2-BB1D-438C-B562-7A6FF3D9163A"://外呼
                res = "266E0F4F-2EE1-4305-9115-49DDE2186D57";//外呼
                break;
            case "8D70D821-3BD4-46D1-8342-8B16C4BE642A"://拦截
                res = "B32BB4EC-74C5-4F7C-BF85-F9A02452B8A2";//拦截
                break;
            case "FC09F0DF-D3DF-4378-91C0-7146EC451F43"://圈群
                res = "8FDFDE89-E1F1-43DE-9094-92D77B22FC1F";//圈群
                break;
            case "C07D5987-ACDD-40B8-9CBD-6257AA59C88C"://社区
                res = "709CD8F1-7E1A-42B9-B4E9-6EAFB428EAEF";//社区
                break;
                default:
        }
        return res;
    }
}
