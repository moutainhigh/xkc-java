package com.tahoecn.xkc.service.miniprogram;


import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.core.json.JSONResult;
import com.tahoecn.xkc.model.channel.BChanneluser;
import com.tahoecn.xkc.model.miniprogram.vo.followrecord.ChannelRecoerdVO;
import com.tahoecn.xkc.model.miniprogram.vo.followrecord.CourtCaseRecordVO;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 思为小程序跟进记录服务类
 * </p>
 *
 * @author zhaoyan
 * @since 2020-04-08
 */
public interface IFollowUpRecordService extends IService<BChanneluser> {

    /**
     * 案场跟进记录
     *
     * @param request
     * @param courtCaseRecordVO
     * @return
     * @throws Exception
     */
    public JSONResult courtCaseRecord(HttpServletRequest request, CourtCaseRecordVO courtCaseRecordVO) throws Exception;

    /**
     * 自渠跟进记录
     *
     * @param request
     * @param channelRecoerdVO
     * @return
     * @throws Exception
     */
    public JSONResult channelRecord(HttpServletRequest request, ChannelRecoerdVO channelRecoerdVO) throws Exception;

}
