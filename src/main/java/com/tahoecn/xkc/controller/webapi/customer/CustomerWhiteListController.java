package com.tahoecn.xkc.controller.webapi.customer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.tahoecn.xkc.common.utils.ThreadLocalUtils;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.customer.BCustomerWhiteList;
import com.tahoecn.xkc.model.vo.CustomerWhiteListReq;
import com.tahoecn.xkc.model.vo.DelCustomerWhiteListReq;
import com.tahoecn.xkc.service.customer.IBCustomerWhiteListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

/**
 * @author mystic
 */
@Api(tags = "客户")
@RestController
@RequestMapping("/webapi/customer/whiteList")
public class CustomerWhiteListController extends TahoeBaseController {

    @Autowired
    private IBCustomerWhiteListService ibCustomerWhiteListService;

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
            BCustomerWhiteList bCustomerWhiteList = new BCustomerWhiteList();
            UpdateWrapper<BCustomerWhiteList> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set("IsDel", 0)
                    .set("CustomerMobile", customerMobile)
                    .set("EditeTime", new Date())
                    .set("Editor", ThreadLocalUtils.getUserName())
                    .eq("CustomerID", customerID);
            this.ibCustomerWhiteListService.update(bCustomerWhiteList, updateWrapper);
            return Result.okm("成功");
        }

        BCustomerWhiteList bCustomerWhiteList = new BCustomerWhiteList();
        bCustomerWhiteList.setId(UUID.randomUUID().toString().toUpperCase());
        bCustomerWhiteList.setCustomerID(customerID);
        bCustomerWhiteList.setCustomerMobile(customerMobile);
        bCustomerWhiteList.setCreateTime(new Date());
        bCustomerWhiteList.setCreator(ThreadLocalUtils.getUserName());
        bCustomerWhiteList.setIsDel(0);

        this.ibCustomerWhiteListService.save(bCustomerWhiteList);
        return Result.okm("成功");
    }

    @ApiOperation(value = "删除VIP白名单人员", notes = "删除VIP白名单人员")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestBody DelCustomerWhiteListReq req) {
        BCustomerWhiteList bCustomerWhiteList = new BCustomerWhiteList();
        UpdateWrapper<BCustomerWhiteList> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("IsDel", 1)
                .set("EditeTime", new Date())
                .set("Editor", ThreadLocalUtils.getUserName())
                .eq("CustomerID", req.getCustomerID());
        this.ibCustomerWhiteListService.update(bCustomerWhiteList, updateWrapper);
        return Result.okm("成功");
    }
}
