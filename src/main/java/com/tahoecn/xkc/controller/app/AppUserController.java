package com.tahoecn.xkc.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tahoecn.security.SecureUtil;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.channel.BChanneluser;
import com.tahoecn.xkc.model.project.SAccountuserproject;
import com.tahoecn.xkc.model.project.SAccountuserprojectjob;
import com.tahoecn.xkc.model.sys.SAccount;
import com.tahoecn.xkc.service.channel.IBChanneluserService;
import com.tahoecn.xkc.service.project.ISAccountuserprojectService;
import com.tahoecn.xkc.service.project.ISAccountuserprojectjobService;
import com.tahoecn.xkc.service.sys.IBVerificationcodeService;
import com.tahoecn.xkc.service.sys.ISAccountService;
import com.tahoecn.xkc.service.user.ISAccountusertypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YYY
 * @since 2019-06-25
 */
@RestController
@Api(tags = "APP-用户接口", value = "APP-用户接口")
@RequestMapping("/app/user")
public class AppUserController extends TahoeBaseController {
	
    @Autowired
    private IBChanneluserService iBChanneluserService;
    @Autowired
    private ISAccountuserprojectService iSAccountuserprojectService;
    @Autowired
    private ISAccountuserprojectjobService iSAccountuserprojectjobService;
    @Autowired
    private ISAccountusertypeService iSAccountusertypeService;
    @Autowired
    private ISAccountService iISAccountService;
    @Autowired
    private IBVerificationcodeService iBVerificationcodeService;
    
	@ResponseBody
    @ApiOperation(value = "用户基本信息查询", notes = "用户基本信息查询")
    @RequestMapping(value = "/mUserDetail_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mUserDetail_Select(@RequestBody JSONObject jsonParam) {
    	try{
    		// 直接将json信息打印出来
    		System.out.println(jsonParam.toJSONString());
            Map paramMap = (HashMap)jsonParam.get("_param");
            String ID = (String)paramMap.get("ID").toString();//ID
    		Map<String,Object> map = new HashMap<String,Object>();
    		map.put("ID", ID);
    		
    		List<BChanneluser> userNews = iBChanneluserService.ChannelUser_Detail_FindById(map);
    		return Result.ok(userNews);
    	}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
    }
	
	@ResponseBody
    @ApiOperation(value = "用户切换项目", notes = "用户切换项目")
    @RequestMapping(value = "/mUserProjectChange_Update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mUserProjectChange_Update(@RequestBody JSONObject jsonParam) {
    	try{
    		// 直接将json信息打印出来
            System.out.println(jsonParam.toJSONString());
            Map paramMap = (HashMap)jsonParam.get("_param");
            String ProjectID = (String)paramMap.get("ProjectID");
            String UserID = (String)paramMap.get("UserID");
    		QueryWrapper<SAccountuserproject> wrapper = new QueryWrapper<SAccountuserproject>();
    		wrapper.eq("UserID", UserID);
    		SAccountuserproject exit = iSAccountuserprojectService.getOne(wrapper);
    		SAccountuserproject userproject = new SAccountuserproject();
    		if(exit == null){
    			userproject.setUserID(UserID);
    			userproject.setProjectID(ProjectID);
    			iSAccountuserprojectService.save(userproject);
    		}else{
    			Map<String,Object> map = new HashMap<String,Object>();
    			map.put("UserID", UserID);
    			map.put("ProjectID", ProjectID);
    			iSAccountuserprojectService.changeUserProject(map);
    		}
    		return Result.ok("切换成功");
    	}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
    }

    @ResponseBody
    @ApiOperation(value = "用户切换岗位", notes = "用户切换岗位")
    @RequestMapping(value = "/mUserProjectJobChange_Update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mUserProjectJobChange_Update(@RequestBody JSONObject jsonParam) {
    	try{
    		// 直接将json信息打印出来
            System.out.println(jsonParam.toJSONString());
            Map paramMap = (HashMap)jsonParam.get("_param");
            String ProjectID = (String)paramMap.get("ProjectID");
            String UserID = (String)paramMap.get("UserID");
            String JobCode = (String)paramMap.get("JobCode");
    		QueryWrapper<SAccountuserprojectjob> wrapper = new QueryWrapper<SAccountuserprojectjob>();
    		wrapper.eq("UserID", UserID);
    		wrapper.eq("ProjectID", ProjectID);
    		SAccountuserprojectjob exit = iSAccountuserprojectjobService.getOne(wrapper);
    		SAccountuserprojectjob userprojectJob = new SAccountuserprojectjob();
    		if(exit == null){
    			userprojectJob.setUserID(UserID);
    			userprojectJob.setProjectID(ProjectID);
    			userprojectJob.setJobCode(JobCode);
    			iSAccountuserprojectjobService.save(userprojectJob);
    		}else{
    			Map<String,Object> map = new HashMap<String,Object>();
    			map.put("UserID", UserID);
    			map.put("ProjectID", ProjectID);
    			map.put("JobCode", JobCode);
    			iSAccountuserprojectjobService.mUserProjectJobChange_Update(map);
    		}
    		return Result.ok("切换成功");
    	}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
    }
    
    @ResponseBody
    @ApiOperation(value = "用户修改密码", notes = "用户修改密码")
    @RequestMapping(value = "/mUserPwdDetail_Update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mUserPwdDetail_Update(@RequestBody JSONObject jsonParam) {
    	try{
    		System.out.println("------------------------------------------"+jsonParam);
    		Map paramMap = (HashMap)jsonParam.get("_param");
            String JobCode = (String)paramMap.get("JobCode");
            String Password = (String)paramMap.get("Password");
            String RePassword = (String)paramMap.get("RePassword");
            String OldPassword = (String)paramMap.get("OldPassword");
            String UserID = (String)paramMap.get("UserID");
            
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("UserID", UserID);
            map.put("OldPassword", SecureUtil.md5(OldPassword).toUpperCase());
            map.put("Password", SecureUtil.md5(Password).toUpperCase());
            if("JZ".equals(JobCode.toUpperCase())){
            	if(Password.equals(RePassword)){
                    List<BChanneluser> obj = iBChanneluserService.ChannelUserPassWord_Select(map);//修改密码-判断原密码是否正确
                    if (obj != null && obj.size() > 0){
                        iBChanneluserService.ChannelUserPassWord_Update(map);
                        return Result.ok("用户修改密码成功");
                    }
                    else{
                        return Result.errormsg(9, "当前密码错误");
                    }
            	}else{
            		return Result.errormsg(90, "密码与确认密码不一致");
            	}
            }else{//用户基本信息修改
            	return UserPwdDetail_Update(paramMap);
            }
    	}catch(Exception e){
    		e.printStackTrace();
    		return Result.errormsg(1, "系统异常，请联系管理员");
    	}
    }

    /**
     * 用户基本信息修改
     * @param map
     */
	private Result UserPwdDetail_Update(Map<String, Object> paramMap) {
        String Password = (String) paramMap.get("Password");
        String RePassword = (String) paramMap.get("RePassword");
        String OldPassword = (String) paramMap.get("OldPassword");
        String UserID = (String) paramMap.get("UserID");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("OldPassword", OldPassword);
        map.put("UserID", UserID);
        map.put("Password", SecureUtil.md5(Password).toUpperCase());
        if (Password.equals(RePassword)){
        	QueryWrapper<SAccount> wrapper = new  QueryWrapper<SAccount>();
        	wrapper.eq("ID", UserID);
        	SAccount s = iISAccountService.getOne(wrapper);
        	if(s == null){
        		return Result.errormsg(91, "用户信息不正确");
        	}else if(s != null && s.getAccountType() == 1){
        		return Result.errormsg(91, "请到泰信重置修改密码");
        	}
            List<Map<String,Object>> obj = iSAccountusertypeService.SalesUserPwdDetail_Select(map);
            if (obj != null && obj.size() > 0){
                String OldPasswordMD5 = SecureUtil.md5((String) obj.get(0).get("OldPassword")).toUpperCase();
                String ReOldPasswordMD5 = (String) obj.get(0).get("ReOldPassword");
                if (OldPasswordMD5.equals(ReOldPasswordMD5)){
                	iSAccountusertypeService.SalesUserPwdDetail_Update(map);
                    return Result.ok("用户密码修改成功");
                }else{
                	return Result.errormsg(92, "原密码不正确");
                }
            }else{
            	return Result.errormsg(91, "用户信息不正确");
            }
        }else{
            return Result.errormsg(90, "密码与确认密码不一致");
        }
	}
	@ResponseBody
    @ApiOperation(value = "忘记密码", notes = "忘记密码")
    @RequestMapping(value = "/mUserForgetPwd_Update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mUserForgetPwd_Update(@RequestBody JSONObject jsonParam) {
    	try{
    		System.out.println("------------------------------------------"+jsonParam);
    		JSONObject paramMap = jsonParam.getJSONObject("_param");
            String Password = (String)paramMap.get("Password");//密码
            String RePassword = (String)paramMap.get("RePassword");//确认密码
            String Mobile = (String)paramMap.get("Mobile");//手机号码
            String AuthCode = (String)paramMap.get("AuthCode");//验证码
            
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("Mobile", Mobile);
            map.put("Password", SecureUtil.md5(Password).toUpperCase());
            //1.校验验证码
            if(!iBVerificationcodeService.Verification(Mobile,AuthCode)){
            	return Result.errormsg(1,"验证码验证失败");
            }
            if(Password.equals(RePassword)){
            	//2.是否为兼职
            	QueryWrapper<BChanneluser> wrapper = new  QueryWrapper<BChanneluser>();
            	wrapper.eq("Mobile", Mobile);
            	List<BChanneluser> Channeluser = iBChanneluserService.list(wrapper);
                if (Channeluser != null && Channeluser.size() > 0){
                	if(Channeluser.size() > 1){
                		return Result.errormsg(91, "根据手机号查询存在多个用户，请联系系统管理员");
                	}else{
	                    iBChanneluserService.ChannelUserForgetPassWord_Update(map);
	                    return Result.ok("用户修改密码成功");
                	}
                }else{
                	//3.查询是否为UC用户
                	QueryWrapper<SAccount> wrapper1 = new  QueryWrapper<SAccount>();
                	wrapper1.eq("Mobile", Mobile);
                	List<SAccount> sAccount = iISAccountService.list(wrapper1);
                	if(sAccount == null || sAccount.size() == 0){
                		return Result.errormsg(91, "用户信息不正确");
                	}else if(sAccount != null && sAccount.size() > 1){
                		return Result.errormsg(91, "根据手机号查询存在多个用户，请联系系统管理员");
                	}else if(sAccount != null && sAccount.get(0).getAccountType() == 1){
                		return Result.errormsg(91, "请到泰信重置修改密码");
                	}else{
                		//4.UC用户修改密码
                		iSAccountusertypeService.SalesUserForgetPwdDetail_Update(map);
                        return Result.ok("用户密码修改成功");
                	}
                }
            }else{
            	return Result.errormsg(90, "密码与确认密码不一致");
            }
    	}catch(Exception e){
    		e.printStackTrace();
    		return Result.errormsg(1, "系统异常，请联系管理员");
    	}
    }
}
