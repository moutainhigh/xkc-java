package com.tahoecn.xkc.controller.miniprogram;

import com.tahoecn.core.json.JSONResult;
import com.tahoecn.xkc.common.enums.TipsEnum;
import com.tahoecn.xkc.common.utils.ResultUtil;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.model.miniprogram.vo.customerreport.MDynatownCustomerVO;
import com.tahoecn.xkc.service.customer.ICustomerHelp;
import com.tahoecn.xkc.service.miniprogram.ISubmitSaleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * <p>
 * 思为小程序提交销售系统
 * </p>
 *
 * @author zhaoyan
 * @since 2020-04-08
 */
@Api(tags = "提交销售系统", value = "提交销售系统")
@RestController
@RequestMapping("/submitSale")
public class SubmitSaleController extends TahoeBaseController {

    @Resource
    private ISubmitSaleService submitSaleService;

    @Resource
    private ICustomerHelp customerTemplate;

    /**
     * 置业顾问报备客户
     *
     * @param mDynatownCustomerVO
     * @return
     */
    @ApiOperation(value = "提交销售系统", notes = "提交销售系统")
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public JSONResult submit(HttpServletRequest request, @RequestBody MDynatownCustomerVO mDynatownCustomerVO) {
        try {
            JSONResult jsonResult = submitSaleService.submit(request, mDynatownCustomerVO);
            String OpportunityID = mDynatownCustomerVO.getOpportunityId();
            customerTemplate.SyncCustomer(OpportunityID, 0);
            return jsonResult;
        } catch (Exception e) {
            //记录错误日志
            e.printStackTrace();
            return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), e.getMessage());
        }
    }
}
