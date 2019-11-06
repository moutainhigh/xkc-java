package com.tahoecn.xkc.service.user;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.user.SAccountusertype;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-04
 */
public interface ISAccountusertypeService extends IService<SAccountusertype> {

	/**
	 * 用户密码信息
	 */
	List<Map<String, Object>> SalesUserPwdDetail_Select(Map<String, Object> map);

	/**
	 * 修改用户密码资料
	 */
	void SalesUserPwdDetail_Update(Map<String, Object> map);
	/**
	 * 修改用户密码资料
	 */
	void SalesUserForgetPwdDetail_Update(Map<String, Object> map);
	/**
	 * 修改用户密码资料
	 */
	void SAccountusertypePassWord_Update(String Password, String UserName);

}
