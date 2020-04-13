package com.tahoecn.xkc.controller.webapi.customer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tahoecn.xkc.common.utils.ThreadLocalUtils;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.customer.BCustomerWhiteList;
import com.tahoecn.xkc.model.vo.CustomerWhiteListReq;
import com.tahoecn.xkc.model.vo.DelCustomerWhiteListReq;
import com.tahoecn.xkc.service.customer.IBCustomerWhiteListService;
import com.tahoecn.xkc.service.sys.ISyncCustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mystic
 */
@Api(tags = "客户")
@RestController
@RequestMapping("/webapi/customer/whiteList")
public class CustomerWhiteListController extends TahoeBaseController {

    @Autowired
    private IBCustomerWhiteListService ibCustomerWhiteListService;

    @Autowired
    private ISyncCustomerService syncCustomerService;

    @ApiOperation(value = "新增VIP白名单人员", notes = "新增VIP白名单人员")
    @RequestMapping(value = "/addOrUpdate", method = {RequestMethod.POST})
    public Result addOrUpdate(@RequestBody CustomerWhiteListReq whiteList) {
        String customerID = whiteList.getCustomerID();
        String customerMobile = whiteList.getCustomerMobile();

        QueryWrapper<BCustomerWhiteList> wrapper = new QueryWrapper<>();

        wrapper.eq("CustomerID", customerID).eq("IsDel", 0);
        int count = ibCustomerWhiteListService.count(wrapper);
        if (count > 0) {
            return Result.okm("用户已存在");
        }

        wrapper = new QueryWrapper<>();
        wrapper.eq("CustomerID", customerID).eq("IsDel", 1);
        count = ibCustomerWhiteListService.count(wrapper);

        if (count > 0) {
            this.syncCustomerService.updateByCustomerID(
                    customerID,
                    customerMobile,
                    ThreadLocalUtils.getUserName());

            return Result.okm("成功");
        }

        this.syncCustomerService.save(
                customerID,
                customerMobile,
                ThreadLocalUtils.getUserName());

        return Result.okm("成功");
    }

    @ApiOperation(value = "删除VIP白名单人员", notes = "删除VIP白名单人员")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestBody DelCustomerWhiteListReq req) {
        this.syncCustomerService.deleteByCustomerID(
                req.getCustomerID(),
                ThreadLocalUtils.getUserName());

        return Result.okm("成功");
    }
}
