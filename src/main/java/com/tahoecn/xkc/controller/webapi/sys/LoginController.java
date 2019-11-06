package com.tahoecn.xkc.controller.webapi.sys;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.landray.sso.client.EKPSSOContext;
import com.landray.sso.client.oracle.CookieUtil;
import com.tahoecn.core.json.JSONResult;
import com.tahoecn.log.Log;
import com.tahoecn.log.LogFactory;
import com.tahoecn.security.SecureUtil;
import com.tahoecn.uc.sso.SSOConfig;
import com.tahoecn.uc.sso.SSOHelper;
import com.tahoecn.uc.sso.common.CookieHelper;
import com.tahoecn.uc.sso.common.util.HttpUtil;
import com.tahoecn.xkc.common.constants.GlobalConstants;
import com.tahoecn.xkc.common.utils.ThreadLocalUtils;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.model.sys.SAccount;
import com.tahoecn.xkc.model.sys.SMenus;
import com.tahoecn.xkc.service.sys.ISAccountService;
import com.tahoecn.xkc.service.sys.ISMenusService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    public JSONResult login() {
        String userName = ThreadLocalUtils.getUserName();
        QueryWrapper<SAccount> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SAccount::getStatus, 1);
        wrapper.lambda().eq(SAccount::getIsDel, 0);
        wrapper.lambda().eq(SAccount::getUserName, userName);
        //wrapper.lambda().eq(SAccount::getPassword, SecureUtil.md5(pwd));
        SAccount account = accountService.getOne(wrapper);
        if (account != null){
            redisTemplate.opsForValue().set(userName, account, 2, TimeUnit.HOURS);
            ThreadLocalUtils.setUser(account);
        }else{
            return markError("用户不存在");
        }

        //不需要HashMap<String,String> userJob = accountService.getUserJob(userName);
        List<HashMap<String,String>> userProduct = accountService.getUserPorduct(userName);
        if (userProduct == null||userProduct.size()==0){
            return markError("账号无权限登录系统");
        }else{
            String productId = userProduct.get(0).get("ID");

            //不需要HashMap<String,String> userProject = accountService.getUserPorject(userJob.get("UserID"),productId);
            List<HashMap<String,String>> userJobMenus = accountService.getUserJobMenus(userName,productId);
            if (userJobMenus == null||userJobMenus.size()==0){
                return markError("账号无权限登录系统");
            }
            //不需要List<HashMap<String,String>> userWXApp = accountService.getUserWXApp(userName);
            List<HashMap<String,String>> userJobs = accountService.getUserJobs(userName,productId);
            //HashMap<String,String> currJob = userJobs.get(0);
            //不需要List<HashMap<String,String>> jobFunctions = accountService.getJobFunctions(userName,productId);

            List<HashMap<String,String>> jobProject = accountService.getJobProject(userName);

            //List<HashMap<String,String>> userMenu = userJobMenus.stream().filter(j -> Objects.equals(j.get("JobID"), currJob.get("ID"))).collect(Collectors.toList());

            //不需要List<HashMap<String,String>> userFunc = jobFunctions.stream().filter(j -> Objects.equals(j.get("JobID"), currJob.get("ID"))).collect(Collectors.toList());
            /*不需要String homeUrl = "";
            if (userMenu.stream().filter(c -> c.get("Url").equals(userProduct.get(0).get("Url"))).count() > 0){
                homeUrl = userProduct.get(0).get("Url");
            }else{
                homeUrl = userMenu.stream().filter(c -> StringUtils.isNotEmpty(c.get("Url"))).findFirst().get().get("Url");
            }*/
            HashMap<String,Object> result = new HashMap<>();
            result.put("User",account);
            //result.put("UserMenu",userMenu);
            /*result.put("UserMenu2",userProject);
            result.put("UserFunc",userFunc);
            result.put("UserProductProject",userProject);
            result.put("UserProductWXApp",userWXApp);*/

            //result.put("UserProduct",userProduct);
            //result.put("CurrProduct",userProduct.get(0));
            result.put("UserProductJob",userJobs);
            //result.put("CurrJob",currJob);
            result.put("UserProductJobMenu",userJobMenus);
            //result.put("UserProductJobFunc",jobFunctions);
            result.put("JobProject",jobProject);
            //result.put("Url",homeUrl);
            return markSuccess(result);
        }

    }

    @ApiOperation(value = "logout", notes = "logout")
    @RequestMapping(value = "/logout", method = {RequestMethod.GET})
    public JSONResult logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        EKPSSOContext.removeThreadLocal();
        SSOHelper.logout(request,response);
        //CookieHelper.
        String logOutUrl = SSOConfig.getInstance().getLogoutUrl();
        String ucwebUrl = SSOConfig.getInstance().getUcwebUrl();
        HashMap<String,String> map = new HashMap<>();
        map.put("sysId", SSOHelper.getSysId());
        logOutUrl = HttpUtil.encodeRetURL(logOutUrl,SSOConfig.getInstance().getParamReturl(),ucwebUrl,map);
        return markSuccess(logOutUrl);

    }
}
