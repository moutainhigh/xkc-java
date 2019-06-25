package com.tahoecn.xkc.controller.webapi.sys;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tahoecn.core.json.JSONResult;
import com.tahoecn.log.Log;
import com.tahoecn.log.LogFactory;
import com.tahoecn.security.SecureUtil;
import com.tahoecn.xkc.common.constants.GlobalConstants;
import com.tahoecn.xkc.common.utils.ThreadLocalUtils;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.model.sys.SAccount;
import com.tahoecn.xkc.model.sys.SMenus;
import com.tahoecn.xkc.service.sys.ISAccountService;
import com.tahoecn.xkc.service.sys.ISMenusService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YYY
 * @since 2019-06-21
 */
@RestController
@RequestMapping("/webapi/sys/login")
public class LoginController extends TahoeBaseController {
    private static final Log log = LogFactory.get();

    @Autowired
    private ISAccountService accountService;

    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation(value = "login", notes = "login")
    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public JSONResult login(String userName,String pwd,String isSSO) {
        if (GlobalConstants.Y.equals(isSSO)){
            QueryWrapper<SAccount> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(SAccount::getStatus, 1);
            wrapper.lambda().eq(SAccount::getIsDel, 0);
            wrapper.lambda().eq(SAccount::getUserName, userName);
            wrapper.lambda().eq(SAccount::getPassword, SecureUtil.md5(pwd));
            SAccount account = accountService.getOne(wrapper);
            if (account != null){
                redisTemplate.opsForValue().set(userName, account, 2, TimeUnit.HOURS);
                ThreadLocalUtils.setUser(account);
            }else{
                return markError("用户不存在");
            }

            HashMap<String,String> userJob = accountService.getUserJob(userName);
            List<HashMap<String,String>> userProduct = accountService.getUserPorduct(userName);
            if (userJob == null){
                return markError("账号无权限登录系统");
            }else{
                String productId = userProduct.get(0).get("ID");

                HashMap<String,String> userPorject = accountService.getUserPorject(userJob.get("UserID"),productId);
                List<HashMap<String,String>> userJobMenus = accountService.getUserJobMenus(userName,productId);
                if (userJobMenus == null){
                    return markError("账号无权限登录系统");
                }
                List<HashMap<String,String>> userWXApp = accountService.getUserWXApp(userName);
                List<HashMap<String,String>> userJobs = accountService.getUserJobs(userName,productId);
                HashMap<String,String> currJob = userJobs.get(0);
                List<HashMap<String,String>> jobFunctions = accountService.getJobFunctions(userName,productId);
                List<HashMap<String,String>> jobProject = accountService.getJobProject(userName);

                List<HashMap<String,String>> aaa = userJobMenus.stream().filter(j -> Objects.equals(j.get("JobID"), currJob.get("ID"))).collect(Collectors.toList());
                System.out.println("啊 = " + aaa);
                log.debug("adsfasdfasdfasdf");
            }
        }
        return markSuccess();
    }
}
