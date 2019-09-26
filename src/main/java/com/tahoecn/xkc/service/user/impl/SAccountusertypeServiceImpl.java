package com.tahoecn.xkc.service.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.user.SAccountusertypeMapper;
import com.tahoecn.xkc.model.user.SAccountusertype;
import com.tahoecn.xkc.service.user.ISAccountusertypeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-04
 */
@Service
public class SAccountusertypeServiceImpl extends ServiceImpl<SAccountusertypeMapper, SAccountusertype> implements ISAccountusertypeService {

	@Autowired
	private SAccountusertypeMapper sAccountusertypeMapper;
	
	/**
	 * 用户密码信息
	 */
	@Override
	public List<Map<String, Object>> SalesUserPwdDetail_Select(Map<String, Object> map) {
		return sAccountusertypeMapper.SalesUserPwdDetail_Select(map);
	}

	/**
	 * 修改用户密码资料
	 */
	@Override
	public void SalesUserPwdDetail_Update(Map<String, Object> map) {
		sAccountusertypeMapper.SalesUserPwdDetail_Update(map);
	}
	/**
	 * 修改用户密码资料
	 */
	@Override
	public void SalesUserForgetPwdDetail_Update(Map<String, Object> map) {
		sAccountusertypeMapper.SalesUserForgetPwdDetail_Update(map);
	}
	/**
	 * 修改用户密码资料
	 */
	@Override
	public void SAccountusertypePassWord_Update(String Password, String UserName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Password", Password);
		map.put("UserName", UserName);
		sAccountusertypeMapper.SAccountusertypePassWord_Update(map);
	}

}
