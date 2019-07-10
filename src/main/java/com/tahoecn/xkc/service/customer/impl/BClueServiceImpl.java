package com.tahoecn.xkc.service.customer.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.customer.BClueMapper;
import com.tahoecn.xkc.model.customer.BClue;
import com.tahoecn.xkc.model.customer.BCustomerpotential;
import com.tahoecn.xkc.model.customer.BOpportunity;
import com.tahoecn.xkc.model.project.BProject;
import com.tahoecn.xkc.service.channel.IBChanneluserService;
import com.tahoecn.xkc.service.customer.IBClueService;
import com.tahoecn.xkc.service.customer.IBCustomerpotentialService;
import com.tahoecn.xkc.service.customer.IBOpportunityService;
import com.tahoecn.xkc.service.project.IBProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.Configuration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-03
 */
@Service
public class BClueServiceImpl extends ServiceImpl<BClueMapper, BClue> implements IBClueService {

    @Autowired
    private IBClueService clueService;

    @Autowired
    private IBOpportunityService opportunityService;

    @Autowired
    private IBCustomerpotentialService customerpotentialService;

    @Autowired
    private IBChanneluserService channeluserService;

    @Autowired
    private IBProjectService projectService;

    @Override
    public Map<String, Object> mBrokerCustomerDetail_Select(String clueID) {

        Map<String, Object> map = baseMapper.BrokerClueDetail_Select(clueID);
        if (map.size() > 0) {
            //获取轨迹记录
            List<Map<String, Object>> list = baseMapper.TrackList_Select(clueID);
            map.put("List", list);
        }

        return map;
    }

    /**
     * 验证报备客户是否有效
     *
     * @param userID
     * @param mobile
     * @param projectId
     * @param map
     */
    @Override
    public Map<String, Object> ValidateForReport(String userID, String mobile, String projectId, Map<String, Object> map) {
        //验证渠道人员是否在做重复报备
        Map returnMap = new HashMap<String, Object>();
        QueryWrapper<BClue> wrapper = new QueryWrapper<>();
        wrapper.eq("Mobile", mobile).eq("IntentProjectID", projectId)
                .eq("ReportUserID", userID).in("Status", 1, 2);
        int count = this.count(wrapper);
        if (count != 0) {
            returnMap.put("Tag", false);
            returnMap.put("InvalidType", 9);
            return returnMap;
        }
        //竞争带看并且是现场确认模式
        if ((int) map.get("RuleType") == 1 && (int) map.get("ValidationMode") == 1) {
            //判断是否存在报备保护模式的有效线索，由于两种模式互斥，所以需要单独判断
            boolean b = clueService.IsExistReportProtectClue_Select(projectId, mobile);
            if (b) {
                returnMap.put("Tag", false);
                returnMap.put("InvalidType", 4);
                return returnMap;
            } else {
                //仅提示报备成功
                returnMap.put("Tag", true);
                returnMap.put("InvalidType", 0);
                return returnMap;
            }
        }
        //实时确认
        else {
            //仅允许报备新客户
            if ((int) map.get("IsOnlyAllowNew") == 1) {
                //验证该项目是否已存在销售机会
                QueryWrapper<BOpportunity> qw = new QueryWrapper();
                qw.eq("CustomerMobile", mobile).eq("ProjectID", projectId);
                int c = opportunityService.count(qw);
                //存在销售机会
                if (c > 0) {
                    returnMap.put("Tag", false);
                    returnMap.put("InvalidType", 2);
                } else {//不存在销售机会

                    //查询项目下存在该手机号的有效线索
                    int i = 0;
                    List<Map<String, Object>> list = clueService.RuleClueList_Select(projectId, mobile, userID);
                    for (Map<String, Object> somap : list) {
                        if ((int) somap.get("Status") == 2) {
                            i++;
                        }
                    }
                    if (i > 0) {
                        //报备保护
                        if ((int) map.get("RuleType") == 0) {
                            returnMap.put("InvalidType", 4);
                        } else {
                            returnMap.put("InvalidType", 5);
                        }
                        returnMap.put("Tag", false);
                        return returnMap;
                    }
                    //该手机号为新客户（在允许报备老客户时，此段无意义，仅存在于报备新客户）
                    if (list.size() == 0) {
                        returnMap.put("Tag", true);
                        returnMap.put("InvalidType", 0);
                        return returnMap;
                    }
                    //该手机号线索已存在，此条件下需要进一步证明规则类型
                    if (list.size() == 1) {
                        //线索为竞争带看，并且当前渠道用户规则也为竞争带看
                        if ((int) map.get("RuleType") == 1 && (int) list.get(0).get("RuleType") == 1) {
                            returnMap.put("Tag", true);
                            returnMap.put("InvalidType", 0);
                        } else {//线索为报备保护
                            returnMap.put("Tag", false);
                            returnMap.put("InvalidType", 4);
                        }
                    }
                    //该手机号存在多条线索，说明报备模式为竞争带看
                    else {
                        //当前渠道的报备规则也为竞争带看
                        if ((int) map.get("RuleType") == (int) list.get(0).get("RuleType")) {
                            returnMap.put("Tag", true);
                            returnMap.put("InvalidType", 0);
                        }
                        //当前渠道的报备规则为报备保护，报备规则模式不同则互斥
                        else {
                            returnMap.put("Tag", false);
                            returnMap.put("InvalidType", 4);
                        }

                    }
                    return returnMap;
                }

            }
        }
        return returnMap;
    }

    // 验证是否存在一条有效线索模式为报备保护
    @Override
    public boolean IsExistReportProtectClue_Select(String projectId, String mobile) {
        Map<String, Object> map = baseMapper.IsExistReportProtectClue_Select(projectId, mobile);
        return map.size() > 0;
    }

    @Override
    public List<Map<String, Object>> RuleClueList_Select(String projectId, String mobile, String userID) {
        return baseMapper.RuleClueList_Select(projectId, mobile, userID);
    }

    @Override
    public String getMessage(int invalidType, Map<String, Object> map) {
        String msg=new String();
        switch (invalidType)
        {
            case 0:
                msg = (int)map.get("RuleType") == 1 && (int)map.get("ValidationMode") == 1 ? "报备成功，现场确认" : "报备成功，实时验证";
                break;
            case 1:
                msg = "老业主";
                break;
            case 2:
                msg = "老客户";
                break;
            case 3:
                msg = "防截客";
                break;
            case 4:
                msg = "被报备";
                break;
            case 5:
                msg = "被带看";
                break;
            case 6:
                msg = "保护期";
                break;
            case 7:
                msg = "到访逾期";
                break;
            case 8:
                msg = "成交逾期";
                break;
            case 9:
                msg = "重复报备";
                break;
            case 10:
                msg = "跟进丢失";
                break;
            case 11:
                msg = "本项目老业主!";
                break;
            case 12:
                msg = "跟进逾期";
                break;
                default:
        }
        return msg;
    }

    @Override
    public String GetMessageForReturn(int invalidType, Map<String, Object> map) {
        String msg=new String();
        switch (invalidType)
        {
            case 0:
                msg = (int)map.get("ValidationMode") == 1 ? "报备成功，为避免其他渠道抢先带看，请尽快到案场进行带客确认！" : "报备成功，您可以在“我的客户”中查看客户的最新状态";
                break;
            case 1:
                msg = "报备无效，该客户为集团老业主！";
                break;
            case 2:
                msg = "报备无效，该客户为项目老客户！";
                break;
            case 3:
                msg = "报备无效，不满足防截客条件！";
                break;
            case 4:
                msg = "报备无效，该客户已被其他渠道报备！";
                break;
            case 5:
                msg = "报备无效，该客户已被其他渠道带看！";
                break;
            case 6:
                msg = "报备无效，该客户处于保护期内！";
                break;
            case 7:
                msg = "报备无效，该客户到访逾期！";
                break;
            case 8:
                msg = "报备无效，该客户成交逾期！";
                break;
            case 9:
                msg = "报备无效，该客户已被您报备！";
                break;
            case 10:
                msg = "该客户在案场跟进过程中丢失！";
                break;
            case 11:
                msg = "报备无效，该客户为本项目老业主！";
                break;
            default:
        }
        return msg;
    }

    /**
     * // 线索报备验证后，创建线索信息
     * @param channelOrgId
     * @param ruleValidate
     * @param userRule
     * @param status
     * @param paramMap
     * @return
     */
    @Override
    public boolean createClue(String channelOrgId, Map<String, Object> ruleValidate, Map<String, Object> userRule, int status, Map paramMap) {
        QueryWrapper<BCustomerpotential> wrapper=new QueryWrapper<>();
        wrapper.eq("Mobile",paramMap.get("Mobile"));
        List<BCustomerpotential> list = customerpotentialService.list(wrapper);
        BCustomerpotential cp=new BCustomerpotential();
        if (list.size()==0){
            cp.setName((String) paramMap.get("Name"));
            cp.setLastName((String) paramMap.get("Name"));
            cp.setGender((String) paramMap.get("Gender"));
            cp.setMobile((String) paramMap.get("Mobile"));
            cp.setCreator((String) paramMap.get("99"));
            cp.setCreateTime(new Date());
            cp.setIsDel(0);
            cp.setStatus(1);
            customerpotentialService.save(cp);
        }else {
            cp.setId(list.get(0).getId());
            cp.setName((String) paramMap.get("Name"));
            cp.setLastName((String) paramMap.get("Name"));
            cp.setGender((String) paramMap.get("Gender"));
            cp.setMobile((String) paramMap.get("Mobile"));
            cp.setCreator((String) paramMap.get("99"));
            cp.setCreateTime(new Date());
            cp.setIsDel(0);
            cp.setStatus(1);
            customerpotentialService.updateById(cp);
        }
        String customerPotentialID = cp.getId();
        Date InvalidTime= (boolean) ruleValidate.get("Tag") ? null : new Date();
        String sourceType = customerpotentialService.getOpportunitySourceByAdviserGroup((String) paramMap.get("AdviserGroupID"));
        String ChannelIdentify=channelOrgId;
        Date ComeOverdueTime=(Date)userRule.get("ComeOverdueTime");
        Date TradeOverdueTime=(Date)userRule.get("TradeOverdueTime");

        int IsSelect=(int)userRule.get("IsSelect");
        String ConfirmUserId="";
        if ((int)userRule.get("ValidationMode")==2){
             ConfirmUserId="99";
        }
        //获取报备人信息以及适配的规则
        Map<String,Object> map=channeluserService.GetReportUserInfo_Select((String)paramMap.get("UserID"),(String)paramMap.get("IntentProjectID"),ChannelIdentify);
        BClue clue=new BClue();
        clue.setCustomerPotentialID(customerPotentialID);
        clue.setName((String) paramMap.get("Name"));
        clue.setLastName((String) paramMap.get("Name"));
        clue.setGender((String) paramMap.get("Gender"));
        clue.setMobile((String) paramMap.get("Mobile"));
        clue.setIntentProjectID((String) paramMap.get("IntentProjectID"));
        BProject project = projectService.getById((String) paramMap.get("IntentProjectID"));
        clue.setIntentProjectName(project.getName());
        clue.setRemark((String) paramMap.get("Remark"));
        clue.setReportUserID((String) paramMap.get("UserID"));
        clue.setReportUserName((String) map.get("ReportUserName"));
        clue.setReportUserMobile((String) paramMap.get("ReportUserMobile"));
        clue.setReportUserOrg((String) paramMap.get("ReportUserOrg"));
        clue.setRuleID((String) paramMap.get("RuleID"));
        clue.setInvalidType((int) ruleValidate.get("InvalidType"));
        clue.setInvalidTime(InvalidTime);
        clue.setInvalidReason((String) ruleValidate.get("Message"));
        clue.setComeOverdueTime(ComeOverdueTime);
        clue.setTradeOverdueTime(TradeOverdueTime);
        clue.setAdviserGroupID((String) paramMap.get("AdviserGroupID"));
        clue.setSourceType(sourceType);
        clue.setIsSelect(IsSelect);
        clue.setConfirmTime(new Date());
        clue.setCreator("99");
        clue.setCreateTime(new Date());
        clue.setIsDel(0);
        clue.setStatus(status);
        clue.setConfirmUserId(ConfirmUserId);
        boolean save = clueService.save(clue);
        if (save){
            //允许报备老客户模式下，报备的线索有效，刷新机会中线索信息

        }

        return false;
    }

    /**
	 * 案场助手统计
	 */
	@Override
	public Map<String, Object> CaseFieDetail_Select(Map<String, Object> paramMap) {
		return baseMapper.CaseFieDetail_Select(paramMap);
	}
	/**
	 * 客户信息
	 */
	@Override
	public List<Map<String, Object>> CaseFieCustomerDetail_Select(Map<String, Object> paramMap) {
		return baseMapper.CaseFieCustomerDetail_Select(paramMap);
	}
	/**
	 * 客户无效信息
	 */
	@Override
	public List<Map<String, Object>> CaseFieInvalDetail_Select(Map<String, Object> paramMap) {
		return baseMapper.CaseFieInvalDetail_Select(paramMap);
	}
	/**
	 * 验证是否存超过到访保护期
	 */
	@Override
	public Map<String, Object> IsOverdueCome_Select(Map<String, Object> obj) {
		return baseMapper.IsOverdueCome_Select(obj);
	}
	
	/**
	 * 扫码确认线索无效后，更新线索信息
	 */
	@Override
	public void ClueConfirmInvalid_Update(Map parameter){
		baseMapper.ClueConfirmInvalid_Update(parameter);
	}
	/**
	 * 根据手机号、项目id获取有效线索
	 */
	@Override
	public List<Map<String, Object>> getClueList(Map<String, Object> obj) {
		return baseMapper.RuleClueList_Select(obj);
	}
	@Override
	public Object ClueConfirm_Update(Map<String, Object> parameter){
		return baseMapper.ClueConfirm_Update(parameter);
	}
	/**
	 * 待确认查询
	 */
	@Override
	public List<Map<String, Object>> CaseFieToBeConfirmedList_Select(Map<String, Object> parameter){
		return baseMapper.CaseFieToBeConfirmedList_Select(parameter);
	}
}
