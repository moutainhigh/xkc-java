package com.tahoecn.xkc.service.risk.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.tahoecn.xkc.mapper.risk.BWxbriskcountMapper;
import com.tahoecn.xkc.model.risk.BWxbriskcount;
import com.tahoecn.xkc.model.risk.vo.*;
import com.tahoecn.xkc.service.risk.IBWxbriskcountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author YYY
 * @since 2020-06-17
 */

@Service
public class BWxbriskcountServiceImpl extends ServiceImpl<BWxbriskcountMapper, BWxbriskcount> implements IBWxbriskcountService {

    private List<String> zyqd = new ArrayList() {{
        add("80D2A7B1-115A-4F3A-BB7D-F227F641C5F1");//外展
        add("266E0F4F-2EE1-4305-9115-49DDE2186D57");//外呼
        add("B32BB4EC-74C5-4F7C-BF85-F9A02452B8A2");//拦截
        add("8FDFDE89-E1F1-43DE-9094-92D77B22FC1F");//圈层
        add("709CD8F1-7E1A-42B9-B4E9-6EAFB428EAEF");//外拓
    }};

    private String fxzj = new String("E4DFA1D5-95F9-4D89-B754-E7CC81D58196");

    private List<String> tjqd = new ArrayList() {{
        add("7F4E0089-E21D-0F97-DC48-0DBF0740367D");//老业主
        add("BA06AE1D-E29A-4BC7-A811-A26E103B5E7E");//员工推荐
        add("798A45A6-9169-4E5C-BEE3-1CDB158F5D69");//自由经纪
        add("86D702BC-F30F-4091-B520-CA0909CADCDD");//案场联动
    }};

    private String zyfk = new String("0390CD8C-D6D4-4C92-995B-08C7E18E6EC2");

    @Override
    public IPage statistical(WxbRiskStatisticalPageVO vo) {
        List<String> dictId = null;
        String channelCompanyId = null;
        if (StringUtils.isNotEmpty(vo.getType()) || StringUtils.isNotEmpty(vo.getSourceType())) {
            switch (vo.getType()) {
                case "one":
                    dictId = Lists.newArrayList();
                    dictId.addAll(zyqd);
                    break;
                case "two":
                    dictId = Lists.newArrayList();
                    dictId.add(fxzj);
                    break;
                case "three":
                    dictId = Lists.newArrayList();
                    dictId.addAll(tjqd);
                    break;
                case "0":
                    if (zyqd.contains(vo.getSourceType().toUpperCase()) || tjqd.contains(vo.getSourceType().toUpperCase())
                            || zyfk.equals(vo.getSourceType().toUpperCase())) {
                        dictId = Lists.newArrayList();
                        dictId.add(vo.getSourceType());
                    } else {
                        channelCompanyId = vo.getSourceType();
                    }
                    break;
            }
        }
        vo.setDictId(dictId);
        vo.setChannelCompanyId(channelCompanyId);
        Long total = this.baseMapper.pageCount(vo);
        List<Map> tmpData = this.baseMapper.pageList(vo);
        List<WxbRiskStatisticalResultVO> data = tmpData.stream().map(i -> {
            List<BWxbriskcount> bWxbriskcounts = this.baseMapper.selectList(new QueryWrapper<BWxbriskcount>() {{
                eq("RegionalId", i.get("RegionalId"));
                eq("CityId", i.get("CityId"));
                eq("ProjectId", i.get("ProjectId"));
                eq("DictId", i.get("DictId"));
                if (StringUtils.isNotEmpty((String) i.get("ChannelCompanyId")))
                    eq("ChannelCompanyId", i.get("ChannelCompanyId"));
                if (null != vo.getStartTime() && null != vo.getEndTime())
                    between("FreshCardTime", vo.getStartTime(), vo.getEndTime());
            }});
            WxbRiskStatisticalResultVO wxbRiskStatisticalResultVO = new WxbRiskStatisticalResultVO();
            for (BWxbriskcount bWxbriskcount : bWxbriskcounts) {
                wxbRiskStatisticalResultVO.setRegionalName(bWxbriskcount.getRegionalName());//区域
                wxbRiskStatisticalResultVO.setCityName(bWxbriskcount.getCityName());//城市
                wxbRiskStatisticalResultVO.setProjectName(bWxbriskcount.getProjectName());//项目
                if (StringUtils.isNotEmpty(bWxbriskcount.getDictId())) {
                    if (zyqd.contains(bWxbriskcount.getDictId())) {
                        wxbRiskStatisticalResultVO.setDictName("自有渠道"); //渠道来源名称
                        wxbRiskStatisticalResultVO.setChannelCompany(bWxbriskcount.getDictName());// 渠道机构
                    } else if (fxzj.equals(bWxbriskcount.getDictId())) {
                        wxbRiskStatisticalResultVO.setDictName("分销中介"); //渠道来源名称
                        wxbRiskStatisticalResultVO.setChannelCompany(bWxbriskcount.getChannelCompany());// 渠道机构
                    } else if (tjqd.contains(bWxbriskcount.getDictId())) {
                        wxbRiskStatisticalResultVO.setDictName("推荐渠道"); //渠道来源名称
                        wxbRiskStatisticalResultVO.setChannelCompany(bWxbriskcount.getDictName());// 渠道机构
                    } else if (zyfk.equals(bWxbriskcount.getDictId())) {
                        wxbRiskStatisticalResultVO.setDictName("自然访客"); //渠道来源名称
                        wxbRiskStatisticalResultVO.setChannelCompany(bWxbriskcount.getDictName());// 渠道机构
                    }
                }
                if (null != bWxbriskcount.getFreshCardTime())
                    wxbRiskStatisticalResultVO.setCardCustomers(wxbRiskStatisticalResultVO.getCardCustomers() + 1);//刷证客户
                if (null != bWxbriskcount.getSubscribeTime())
                    wxbRiskStatisticalResultVO.setSubscribeCustomers(wxbRiskStatisticalResultVO.getSubscribeCustomers() + 1);
                if (null != bWxbriskcount.getFinishTime())
                    wxbRiskStatisticalResultVO.setContractCustomers(wxbRiskStatisticalResultVO.getContractCustomers() + 1);
                if (null != bWxbriskcount.getRiskStatus() && bWxbriskcount.getRiskStatus() == 0) {
                    wxbRiskStatisticalResultVO.setYsdqz(wxbRiskStatisticalResultVO.getYsdqz() + 1);
                } else if (null != bWxbriskcount.getRiskStatus() && bWxbriskcount.getRiskStatus() == 1) {
                    wxbRiskStatisticalResultVO.setNoOk(wxbRiskStatisticalResultVO.getNoOk() + 1);
                } else if (null != bWxbriskcount.getRiskStatus() && bWxbriskcount.getRiskStatus() == 2) {
                    wxbRiskStatisticalResultVO.setOk(wxbRiskStatisticalResultVO.getOk() + 1);
                }
            }
            wxbRiskStatisticalResultVO.setYsqz(wxbRiskStatisticalResultVO.getOk() + wxbRiskStatisticalResultVO.getNoOk() + wxbRiskStatisticalResultVO.getYsdqz());//疑似确认
            String result = "0";
            if (wxbRiskStatisticalResultVO.getCardCustomers() > 0) {
                NumberFormat numberFormat = NumberFormat.getInstance();
                numberFormat.setMaximumFractionDigits(2);
                result = numberFormat.format(wxbRiskStatisticalResultVO.getOk() / wxbRiskStatisticalResultVO.getCardCustomers() * 100);
            }
            wxbRiskStatisticalResultVO.setRate(result);//风险率
            return wxbRiskStatisticalResultVO;
        }).collect(Collectors.toList());
        return new Page() {{
            setRecords(data);
            setTotal(total);
        }};
    }


    @Override
    public Map list(WxbRiskInfoPageVO vo) {
        Wrapper<BWxbriskcount> wrapper = new QueryWrapper<BWxbriskcount>() {{
            if (StringUtils.isNotEmpty(vo.getRegionalId()))
                eq("RegionalId", vo.getRegionalId());//区域主键
            if (StringUtils.isNotEmpty(vo.getCityId()))
                eq("CityId", vo.getCityId());//城市主键
            if (StringUtils.isNotEmpty(vo.getProjectId()))
                eq("ProjectId", vo.getProjectId());//项目主键
            if (StringUtils.isNotEmpty(vo.getHomeName()))
                eq("House", vo.getHomeName());//房间
            if (StringUtils.isNotEmpty(vo.getAgent()))
                like("Agent", "%" + vo.getAgent() + "%");//经纪人
            if (StringUtils.isNotEmpty(vo.getSalerName()))
                like("SalerName", "%" + vo.getSalerName() + "%");//置业顾问
            if (null != vo.getRiskStatus())
                eq("RiskStatus", vo.getRiskStatus());//风险类型0疑似风险1无风险2确认风险3未知客户
            if (null != vo.getReportStartTime() && null != vo.getReportEndTime())
                between("ReportTime", vo.getReportStartTime(), vo.getReportEndTime());//报备时间
            if (null != vo.getFirstPhotoStartTime() && null != vo.getFirstPhotoEndTime())
                between("FirstPhotoTime", vo.getFirstPhotoStartTime(), vo.getFirstPhotoEndTime());//抓拍时间
            if (null != vo.getSubscribeStartTime() && null != vo.getSubscribeEndTime())
                between("SubscribeTime", vo.getSubscribeStartTime(), vo.getSubscribeEndTime());//认购时间
            if (null != vo.getFinishStartTime() && null != vo.getFinishEndTime())
                between("FinishTime", vo.getFinishStartTime(), vo.getFinishEndTime());//签约时间
            if (null != vo.getFreshCardStartTime() && null != vo.getFreshCardEndTime())
                between("FinishTime", vo.getFreshCardStartTime(), vo.getFreshCardEndTime());//统计(刷证)时间
            if (StringUtils.isNotEmpty(vo.getType()) || StringUtils.isNotEmpty(vo.getSourceType())) {
                switch (vo.getType()) {
                    case "one":
                        in("DictId", zyqd);
                        break;
                    case "two":
                        eq("DictId", fxzj);
                        break;
                    case "three":
                        in("DictId", tjqd);
                        break;
                    case "0":
                        if (zyqd.contains(vo.getSourceType().toUpperCase()) || tjqd.contains(vo.getSourceType().toUpperCase())
                                || zyfk.equals(vo.getSourceType().toUpperCase())) {
                            eq("DictId", vo.getSourceType());
                        } else {
                            eq("ChannelCompanyId", vo.getSourceType());
                        }
                        break;
                }
            }
            if (null != vo.getStyle()) {
                switch (vo.getStyle()) {
                    case 1://全部客户
                        //全部客户不处理
                        break;
                    case 2://刷证客户
                        isNotNull("FreshCardTime");
                        break;
                    case 3://认证失败
                        lambda().and(wrapper -> wrapper.isNotNull(BWxbriskcount::getHasPass).or().gt(BWxbriskcount::getHasPass, 0));
                        break;
                    case 4://报备客户
                        isNotNull("ReportTime");
                        break;
                    case 5://成交客户
                        isNotNull("FinishTime");
                        break;
                    case 6://未知客户
                        eq("RiskStatus", 3);
                        break;
                    case 7://疑似风险
                        in("RiskStatus", 0, 1, 2);
                        break;
                    case 8://疑似待确认
                        eq("RiskStatus", 0);
                        break;
                    case 9://确认风险
                        eq("RiskStatus", 2);
                        break;
                    case 10://确认无风险
                        eq("RiskStatus", 1);
                        break;
                }
            }
        }};
        IPage<BWxbriskcount> pages = super.page(new Page(vo.getPageNum(), vo.getPageSize()), wrapper);
        WxbLabelVO wxbLabel = new WxbLabelVO();
        List<WxbRiskInfoResultVO> data = pages.getRecords().stream().map(i -> {
            wxbLabel.setAllCustomer(wxbLabel.getAllCustomer() + 1);//全部客户
            if (null != i.getFreshCardTime())
                wxbLabel.setFreshCardCustomer(wxbLabel.getFreshCardCustomer() + 1);//刷证客户
            if (null == i.getHasPass() || i.getHasPass() < 1)
                wxbLabel.setAttestFail(wxbLabel.getAttestFail() + 1);//认证失败
            if (null != i.getReportTime())
                wxbLabel.setReportCustomer(wxbLabel.getReportCustomer() + 1);//报备客户
            if (null != i.getFinishTime())
                wxbLabel.setDealCustomer(wxbLabel.getDealCustomer() + 1);//成交客户
            if (null != i.getRiskStatus() && i.getRiskStatus() == 3)
                wxbLabel.setUnknownCustomer(wxbLabel.getUnknownCustomer() + 1);//未知客户
            if (null != i.getRiskStatus() && i.getRiskStatus() == 0)
                wxbLabel.setYsdqz(wxbLabel.getYsdqz() + 1);//疑似待确认
            if (null != i.getRiskStatus() && i.getRiskStatus() == 2)
                wxbLabel.setOk(wxbLabel.getOk() + 1);//确认风险
            if (null != i.getRiskStatus() && i.getRiskStatus() == 1)
                wxbLabel.setNoOk(wxbLabel.getNoOk() + 1);//确认无风险
            WxbRiskInfoResultVO wxbRiskInfoResult = new WxbRiskInfoResultVO() {{
                setRegionalName(i.getRegionalName());//区域
                setCityName(i.getCityName());//城市
                setProjectName(i.getProjectName());//项目
                setHouse(i.getHouse());//房间编号
                setCustomerName(i.getCustomerName());//客户姓名
                if (StringUtils.isNotEmpty(i.getDictId())) {
                    if (zyqd.contains(i.getDictId())) {
                        setDictName("自有渠道"); //渠道来源名称
                        setChannelCompany(i.getDictName());// 渠道机构
                    } else if (fxzj.equals(i.getDictId())) {
                        setDictName("分销中介"); //渠道来源名称
                        setChannelCompany(i.getChannelCompany());// 渠道机构
                    } else if (tjqd.contains(i.getDictId())) {
                        setDictName("推荐渠道"); //渠道来源名称
                        setChannelCompany(i.getDictName());// 渠道机构
                    } else if (zyfk.equals(i.getDictId())) {
                        setDictName("自然访客"); //渠道来源名称
                        setChannelCompany(i.getDictName());// 渠道机构
                    }
                }
                setAgent(i.getAgent());//经纪人
                setSalerName(i.getSalerName());//置业顾问
                setSubscribeMoney(i.getSubscribeMoney());//认购金额
                setContractMoney(i.getContractMoney());//签约金额
                setReportTime(i.getReportTime());//报备时间
                setFirstPhotoTime(i.getFirstPhotoTime());//首次抓拍时间
                setFreshCardTime(i.getFreshCardTime());//刷证时间
                setSubscribeTime(i.getSubscribeTime());//认购时间
                setFinishTime(i.getFinishTime());//签约时间
                setRiskStatus(null != i.getRiskStatus()
                        ? i.getRiskStatus() == 0
                        ? "疑似风险"
                        : i.getRiskStatus() == 1
                        ? "无风险"
                        : i.getRiskStatus() == 2
                        ? "确认风险"
                        : i.getRiskStatus() == 3
                        ? "未知客户"
                        : ""
                        : "");//风险类别:0疑似风险1无风险2确认风险3未知客户
            }};
            return wxbRiskInfoResult;
        }).collect(Collectors.toList());
        wxbLabel.setYsqz(wxbLabel.getOk() + wxbLabel.getNoOk() + wxbLabel.getYsdqz());//疑似确认风险
        String result = "0";
        if (wxbLabel.getFreshCardCustomer() > 0) {
            NumberFormat numberFormat = NumberFormat.getInstance();
            numberFormat.setMaximumFractionDigits(2);
            result = numberFormat.format(wxbLabel.getOk() / wxbLabel.getFreshCardCustomer() * 100);
        }
        wxbLabel.setRate(result);//风险率
        return new HashMap() {{
            put("records", data);
            put("size", pages.getSize());
            put("total", pages.getTotal());
            put("current", pages.getCurrent());
            put("pages", pages.getPages());
            put("label", wxbLabel);
        }};
    }
}
